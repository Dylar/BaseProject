package de.bornholdtlee.baseproject.ui.map.clusteritems

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import de.bornholdtlee.baseproject.R
import de.bornholdtlee.baseproject.base.map.BaseClusterInfoAdapter
import de.bornholdtlee.baseproject.base.map.BaseClusterItem
import de.bornholdtlee.baseproject.base.map.BaseClusterRenderer

class LessonClusterInfoAdapter(renderer: BaseClusterRenderer<LessonClusterItem>) : BaseClusterInfoAdapter<LessonClusterItem, BaseClusterRenderer<LessonClusterItem>>(renderer) {
    override val layoutId: Int = R.layout.info_window_layout

    override fun initViews(windowView: View, clusterItem: LessonClusterItem) {
        val title = windowView.findViewById(R.id.title) as TextView
        title.text = clusterItem.title
        val description = windowView.findViewById(R.id.description) as TextView
        description.text = clusterItem.snippet
        val image = windowView.findViewById(R.id.image) as ImageView
        image.setImageResource(clusterItem.icon)
    }
}