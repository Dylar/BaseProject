package de.bornholdtlee.baseproject.ui.map.components.lesson

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.google.android.gms.maps.GoogleMap
import com.google.maps.android.clustering.ClusterManager
import de.bornholdtlee.baseproject.R
import de.bornholdtlee.baseproject.base.map.BaseClusterRenderer


open class LessonClusterRenderer(context: Context,
                                 map: GoogleMap,
                                 clusterManager: ClusterManager<LessonClusterItem>)
    : BaseClusterRenderer<LessonClusterItem>(context, map, clusterManager) {


    override val itemLayoutId: Int = R.layout.item_map_lesson
    override val clusterLayoutId: Int = R.layout.cluster_map_lesson

    override fun initItemView(itemMapView: View, item: LessonClusterItem) {
        val imageView = itemMapView.findViewById(R.id.image) as ImageView
        imageView.setImageResource(item.icon)
    }

    override fun initClusterView(clusterMapView: View, items: Collection<LessonClusterItem>) {
        val imageView1 = clusterMapView.findViewById(R.id.image1) as ImageView
        val imageView2 = clusterMapView.findViewById(R.id.image2) as ImageView
        val imageView3 = clusterMapView.findViewById(R.id.image3) as ImageView
        val imageView4 = clusterMapView.findViewById(R.id.image4) as ImageView

        val list = ArrayList<LessonClusterItem>()
        for (baseClusterItem in items.iterator()) {
            list.add(baseClusterItem)
        }

        if (list.size > 0) {
            imageView1.setImageResource(list[0].icon)
        }
        if (list.size > 1) {
            imageView2.setImageResource(list[1].icon)
        }
        if (list.size > 2) {
            imageView3.setImageResource(list[2].icon)
        }
        if (list.size > 3) {
            imageView4.setImageResource(list[3].icon)
        }

    }

}