package de.bornholdtlee.baseproject.base.map

import android.Manifest
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.*
import com.google.android.gms.maps.GoogleMap.*
import com.google.android.gms.maps.model.*
import com.google.maps.android.clustering.Cluster
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.ClusterRenderer
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import de.bornholdtlee.baseproject.base.BasePresenter
import de.bornholdtlee.baseproject.base.IBaseView
import de.bornholdtlee.baseproject.base.mvp.MVPFragment
import de.bornholdtlee.baseproject.utils.Logger
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLngBounds
import de.bornholdtlee.baseproject.R

abstract class MapBaseFragment<T : IBaseView, P : BasePresenter<T>> : MVPFragment<T, P>(),
        OnCameraIdleListener, OnCameraMoveListener, OnMarkerClickListener, OnCircleClickListener, OnInfoWindowClickListener
        , OnInfoWindowCloseListener, ClusterManager.OnClusterClickListener<BaseClusterItem>, ClusterManager.OnClusterItemClickListener<BaseClusterItem>, OnMapClickListener, OnMapLongClickListener {

    lateinit var mapView: MapView

    lateinit var googleMap: GoogleMap

    lateinit var clusterManager: ClusterManager<BaseClusterItem>

    open val myLocationEnabled: Boolean = true
    open val isIndoorLevelPickerEnabled: Boolean = true
    open val isMyLocationButtonEnabled: Boolean = true
    open val isMapToolbarEnabled: Boolean = true
    open val isCompassEnabled: Boolean = true
    open val isZoomControlsEnabled: Boolean = true

    abstract val mapViewId: Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = super.onCreateView(inflater, container, savedInstanceState)

        mapView = rootView!!.findViewById(mapViewId) as MapView
        mapView.onCreate(savedInstanceState)

        mapView.onResume() // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(activity!!.applicationContext)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Dexter.withActivity(activity)
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(object : PermissionListener {
                    override fun onPermissionGranted(response: PermissionGrantedResponse) {
                        syncMap()
                    }

                    override fun onPermissionDenied(response: PermissionDeniedResponse) {
                        //nothing
                    }

                    override fun onPermissionRationaleShouldBeShown(permission: PermissionRequest, token: PermissionToken) {
                        //nothing
                    }
                })
                .check()
    }

    private fun syncMap() {
        mapView.getMapAsync(object : OnMapReadyCallback {

            @Throws(SecurityException::class)
            override fun onMapReady(mMap: GoogleMap) {
                googleMap = mMap

                initClusterManager()
                initMapUi()
                initMapListener()
                initMapItems()
            }
        })
    }

    private fun initClusterManager() {
        clusterManager = ClusterManager(context, googleMap)
        clusterManager.setAnimation(true)
        clusterManager.renderer = createRenderer(context!!, googleMap, clusterManager)
    }

    open fun createRenderer(context: Context, googleMap: GoogleMap, clusterManager: ClusterManager<BaseClusterItem>): ClusterRenderer<BaseClusterItem>? {
        return BaseClusterRenderer(context, googleMap, clusterManager)
    }

    @Throws(SecurityException::class)
    private fun initMapUi() {
        googleMap.isMyLocationEnabled = myLocationEnabled
        val uiSettings = googleMap.uiSettings
        uiSettings.isIndoorLevelPickerEnabled = isIndoorLevelPickerEnabled
        uiSettings.isMyLocationButtonEnabled = isMyLocationButtonEnabled
        uiSettings.isMapToolbarEnabled = isMapToolbarEnabled
        uiSettings.isCompassEnabled = isCompassEnabled
        uiSettings.isZoomControlsEnabled = isZoomControlsEnabled
    }

    private fun initMapListener() {
        googleMap.setOnCameraIdleListener(this@MapBaseFragment)
        googleMap.setOnCameraMoveListener(this@MapBaseFragment)
        googleMap.setOnMarkerClickListener(this@MapBaseFragment)
        googleMap.setOnCircleClickListener(this@MapBaseFragment)
        googleMap.setOnInfoWindowClickListener(this@MapBaseFragment)
        googleMap.setOnInfoWindowCloseListener(this@MapBaseFragment)
        googleMap.setOnMapClickListener(this@MapBaseFragment)
        googleMap.setOnMapLongClickListener(this@MapBaseFragment)

        clusterManager.setOnClusterClickListener(this@MapBaseFragment)
        clusterManager.setOnClusterItemClickListener(this@MapBaseFragment)
    }

    abstract fun initMapItems()

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    fun activateModeHybrid() {
        googleMap.mapType = MAP_TYPE_HYBRID
    }

    fun activateModeNormal() {
        googleMap.mapType = MAP_TYPE_NORMAL
    }

    fun activateModeSatellite() {
        googleMap.mapType = MAP_TYPE_SATELLITE
    }

    fun activateModeTerrain() {
        googleMap.mapType = MAP_TYPE_TERRAIN
    }

    fun renderNew() {
        clusterManager.cluster()
    }

    fun zoomCamera(lat: Double, lng: Double, zoom: Float) {
        zoomCamera(LatLng(lat, lng), zoom)
    }

    fun zoomCamera(latLng: LatLng, zoom: Float) {
        val cameraPosition = CameraPosition.Builder()
                .target(latLng)
                .zoom(zoom).build()
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }

    fun zoomOnCluster(cluster: Cluster<BaseClusterItem>){
        val builder = LatLngBounds.builder()
        for (item in cluster.items) {
            builder.include(item.position)
        }
        val bounds = builder.build()
        val padding = context!!.resources.getDimension(R.dimen.eight_grid_unit).toInt()
        googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, padding))
    }

    override fun onCameraIdle() {
        clusterManager.onCameraIdle()
    }

    override fun onCameraMove() {
        Logger.info("onCameraMove not implemented")
    }

    override fun onMapClick(location: LatLng?) {
        Logger.info("onMapClick not implemented")
    }

    override fun onMapLongClick(location: LatLng?) {
        Logger.info("onMapLongClick not implemented")
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        return clusterManager.onMarkerClick(marker)
    }

    override fun onInfoWindowClose(marker: Marker) {
        Logger.info("onInfoWindowClose not implemented")
    }

    override fun onInfoWindowClick(marker: Marker) {
        Logger.info("onInfoWindowClick not implemented")
    }

    override fun onCircleClick(circle: Circle) {
        Logger.info("onCircleClick not implemented")
    }

    override fun onClusterItemClick(item: BaseClusterItem?): Boolean {
        return false
    }

    override fun onClusterClick(cluster: Cluster<BaseClusterItem>?): Boolean {
        return false
    }

    fun addPolygone(vararg locations: LatLng) {

        val issaquah = LatLng(47.5301011, -122.0326191)
        val seattle = LatLng(47.6062095, -122.3320708)
        val bellevue = LatLng(47.6101497, -122.2015159)
        val sammamish = LatLng(47.6162683, -122.0355736)
        val redmond = LatLng(47.6739881, -122.121512)

        val polygonOptions = PolygonOptions()
        polygonOptions.add(issaquah, seattle, bellevue, sammamish, redmond)
        polygonOptions.strokeJointType(JointType.ROUND)
        polygonOptions.strokeColor(Color.RED)
        polygonOptions.strokeWidth(10f)

        googleMap.addPolygon(polygonOptions)
    }

    fun addPolyline(fromLat: Double, fromLng: Double, toLat: Double, toLng: Double) {
        addPolyline(LatLng(fromLat, fromLng), LatLng(toLat, toLng))
    }

    fun addPolyline(fromLocation: LatLng, toLocation: LatLng) {

        val plo = PolylineOptions()
        plo.add(fromLocation)
        plo.add(toLocation)
        plo.color(Color.RED)
        plo.geodesic(true)
        plo.startCap(RoundCap())
        plo.width(20f)
        plo.jointType(JointType.BEVEL)

        googleMap.addPolyline(plo)
    }

    fun addCircle(lat: Double, lng: Double) {
        addCircle(LatLng(lat, lng))
    }

    fun addCircle(location: LatLng) {
        val circleOptions = CircleOptions()
        circleOptions.center(location)
        circleOptions.radius(8500.0)
        circleOptions.fillColor(Color.BLUE)
        circleOptions.strokeColor(Color.RED)
        circleOptions.strokeWidth(4f)

        googleMap.addCircle(circleOptions)
    }

    fun addMarker(lat: Double, lng: Double, title: String, desc: String) {
        val location = LatLng(lat, lng)
        addMarker(location, title, desc)
    }

    fun addMarker(latLng: LatLng, title: String, desc: String) {
        val marker = googleMap.addMarker(MarkerOptions()
                .position(latLng)
                .title(title)
                .snippet(desc))
        marker.showInfoWindow()
    }

}