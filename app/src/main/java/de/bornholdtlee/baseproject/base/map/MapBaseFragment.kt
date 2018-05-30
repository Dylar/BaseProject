package de.bornholdtlee.baseproject.base.map

import android.Manifest
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import com.google.android.gms.maps.*
import com.karumi.dexter.Dexter
import com.karumi.dexter.listener.single.PermissionListener
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import android.graphics.Color
import com.google.android.gms.maps.GoogleMap.*
import com.google.android.gms.maps.model.*
import com.google.maps.android.clustering.ClusterManager
import de.bornholdtlee.baseproject.base.BasePresenter
import de.bornholdtlee.baseproject.base.IBaseView
import de.bornholdtlee.baseproject.base.mvp.MVPFragment
import de.bornholdtlee.baseproject.injection.IInjection
import de.bornholdtlee.baseproject.utils.Logger

abstract class MapBaseFragment<T : IBaseView, P : BasePresenter<T>> : MVPFragment<T, P>(),
        OnCameraIdleListener, IInjection, OnCameraMoveListener, OnMarkerClickListener, OnCircleClickListener, OnInfoWindowClickListener, OnInfoWindowCloseListener {

    lateinit var mapView: MapView

    lateinit var googleMap: GoogleMap

    open val clusterManager: ClusterManager<BaseClusterItem>
        get() = ClusterManager(context, googleMap)

    open val myLocationEnabled: Boolean = true
    open val isIndoorLevelPickerEnabled: Boolean = true
    open val isMyLocationButtonEnabled: Boolean = true
    open val isMapToolbarEnabled: Boolean = true
    open val isCompassEnabled: Boolean = true
    open val isZoomControlsEnabled: Boolean = true

    abstract val mapViewId: Int

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

                googleMap.isMyLocationEnabled = myLocationEnabled

                val uiSettings = googleMap.uiSettings
                uiSettings.isIndoorLevelPickerEnabled = isIndoorLevelPickerEnabled
                uiSettings.isMyLocationButtonEnabled = isMyLocationButtonEnabled
                uiSettings.isMapToolbarEnabled = isMapToolbarEnabled
                uiSettings.isCompassEnabled = isCompassEnabled
                uiSettings.isZoomControlsEnabled = isZoomControlsEnabled

                googleMap.setOnCameraIdleListener(this@MapBaseFragment)
                googleMap.setOnCameraMoveListener(this@MapBaseFragment)
                googleMap.setOnMarkerClickListener(this@MapBaseFragment)
                googleMap.setOnCircleClickListener(this@MapBaseFragment)
                googleMap.setOnInfoWindowClickListener(this@MapBaseFragment)
                googleMap.setOnInfoWindowCloseListener(this@MapBaseFragment)
                initMap()
            }
        })
    }

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

    abstract fun initMap()

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

    override fun onCameraIdle() {
        Logger.info("onCameraIdle not implemented")
    }

    override fun onCameraMove() {
        Logger.info("onCameraMove not implemented")
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        Logger.info("onMarkerClick not implemented")
        return false
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

    fun zoomCamera(lat: Double, lng: Double, zoom: Float) {
        zoomCamera(LatLng(lat, lng), zoom)
    }

    fun zoomCamera(latLng: LatLng, zoom: Float) {
        val cameraPosition = CameraPosition.Builder()
                .target(latLng)
                .zoom(zoom).build()
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
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
