package de.bornholdtlee.baseproject.base.map

import android.content.Context
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.maps.android.clustering.Cluster
import com.google.maps.android.clustering.ClusterManager
import de.bornholdtlee.baseproject.ui.map.components.MapItemInfo

abstract class BaseMapItemComponents<CI : BaseClusterItem, R : BaseClusterRenderer<CI>, A : BaseClusterInfoAdapter<CI, R>>
(private val context: Context, var mapListener: MapListener) :
        ClusterManager.OnClusterClickListener<CI>,
        ClusterManager.OnClusterItemClickListener<CI>, ClusterManager.OnClusterInfoWindowClickListener<CI> {

    open val maxZoom = 20.0f
    private val openClusterOnZoom by lazy { maxZoom - 2 }

    private lateinit var googleMap: GoogleMap
    private lateinit var clusterManager: ClusterManager<CI>
    private lateinit var renderer: R
    private lateinit var clusterInfoAdapter: BaseClusterInfoAdapter<CI, R>

    fun initMapItemComponent(googleMap: GoogleMap) {
        this.googleMap = googleMap
        clusterManager = ClusterManager(context, googleMap)
        clusterManager.setAnimation(true)
        renderer = createRenderer(context, googleMap, clusterManager)
        clusterManager.renderer = renderer

        clusterInfoAdapter = createInfoViewAdapter(renderer)
        clusterManager.markerCollection.setOnInfoWindowAdapter(clusterInfoAdapter)
        googleMap.setInfoWindowAdapter(clusterManager.markerManager)
        setListener(this, this, this)
    }

    abstract fun createRenderer(context: Context, googleMap: GoogleMap, clusterManager: ClusterManager<CI>): R

    abstract fun createInfoViewAdapter(renderer: R): A

    fun setListener(clusterClickListener: ClusterManager.OnClusterClickListener<CI> = this,
                    clusterItemClickListener: ClusterManager.OnClusterItemClickListener<CI> = this,
                    clusterInfoClickListener: ClusterManager.OnClusterInfoWindowClickListener<CI> = this) {
        clusterManager.setOnClusterClickListener(clusterClickListener)
        clusterManager.setOnClusterItemClickListener(clusterItemClickListener)
        clusterManager.setOnClusterInfoWindowClickListener(clusterInfoClickListener)

    }

    fun renderNew() {
        clusterManager.cluster()
    }

    override fun onClusterItemClick(item: CI): Boolean {
        return false
    }

    override fun onClusterClick(cluster: Cluster<CI>?): Boolean {
        return if (googleMap.cameraPosition.zoom >= openClusterOnZoom) {
            openCluster(cluster!!)
            true
        } else {
            zoomOnCluster(cluster!!)
            true
        }
    }

    override fun onClusterInfoWindowClick(p0: Cluster<CI>?) {
        //not implemented
    }

    private fun openCluster(cluster: Cluster<CI>) {
//        baseActivity!!.showFragment(createMapClusterFragment(cluster))
    }

    open fun createMapClusterFragment(cluster: Cluster<CI>): MapBaseClusterFragment {
        return MapBaseClusterFragment.createInstance(cluster.items!!)
    }

    fun onCameraIdle() {
        clusterManager.onCameraIdle()
    }

    fun onMarkerClick(marker: Marker): Boolean {
        return clusterManager.onMarkerClick(marker)
    }

    fun renderMap(items: List<MapItemInfo>) {
        clusterManager.clearItems()
        for (item in items) {
            clusterManager.addItem(createClusterItem(item))
        }
        renderNew()
    }

    abstract fun createClusterItem(item: MapItemInfo): CI

    fun zoomOnCluster(cluster: Cluster<CI>) {
        val positions = ArrayList<LatLng>()
        for (item in cluster.items) {
            positions.add(item.position)
        }
        mapListener.zoomToBorder(positions)
    }
}
