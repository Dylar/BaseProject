package de.bornholdtlee.defaultproject.base

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

                setMarker(-34.0, 151.0, "Marker Title", "Marker Description")
                zoomCamera(-34.0, 151.0, 12f)
            }
        })

    }

    fun zoomCamera(lat: Double, lng: Double, zoom: Float) {
        val location = LatLng(lat, lng)
        zoomCamera(location, zoom)
    }

    fun zoomCamera(latLng: LatLng, zoom: Float) {
        val cameraPosition = CameraPosition.Builder()
                .target(latLng)
                .zoom(zoom).build()
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }

    fun setMarker(lat: Double, lng: Double, title: String, desc: String) {
        val location = LatLng(lat, lng)
        setMarker(location, title, desc)
    }

    fun setMarker(latLng: LatLng, title: String, desc: String) {
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
