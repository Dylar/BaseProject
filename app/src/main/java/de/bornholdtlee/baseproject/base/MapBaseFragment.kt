package de.bornholdtlee.baseproject.base

import android.Manifest
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.karumi.dexter.Dexter
import com.karumi.dexter.listener.single.PermissionListener
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import android.R.attr.strokeWidth
import android.R.attr.strokeColor
import android.R.attr.fillColor
import android.R.attr.radius
import android.graphics.Color
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.JointType
import com.google.android.gms.maps.model.RoundCap
import com.google.android.gms.maps.model.PolylineOptions
import android.R.attr.strokeWidth
import android.R.attr.strokeColor
import com.google.android.gms.maps.model.PolygonOptions




abstract class MapBaseFragment<T : IBaseView, P : BasePresenter<T>> : MVPFragment<T, P>() {

    lateinit var mapView: MapView

    lateinit var googleMap: GoogleMap

    open val myLocationEnabled: Boolean = true

    abstract val mapViewId: Int

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Dexter.withActivity(activity)
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(object : PermissionListener {
                    override fun onPermissionGranted(response: PermissionGrantedResponse) {
                        initMap()
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

    private fun initMap() {
        mapView.getMapAsync(object : OnMapReadyCallback {

            @Throws(SecurityException::class)
            override fun onMapReady(mMap: GoogleMap) {
                googleMap = mMap

                // For showing a move to my location button
                googleMap.isMyLocationEnabled = myLocationEnabled

                addTestData()
               }
        })

    }

    private fun addTestData() {
        addMarker(-34.0, 151.0, "Marker Title", "Marker Description")
        zoomCamera(-34.0, 151.0, 12f)
        addCircle(LatLng(-44.0, 151.0))
        addPolyline(-34.0, 151.0, -54.0, 181.0)
        addPolygone()
    }

    fun addPolygone(vararg locations : LatLng) {

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
        googleMap.addMarker(MarkerOptions()
                .position(latLng)
                .title("Marker Title")
                .snippet("Marker Description"))
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

}
