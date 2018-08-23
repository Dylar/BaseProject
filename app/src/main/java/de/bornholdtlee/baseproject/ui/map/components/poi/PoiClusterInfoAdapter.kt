package de.bornholdtlee.baseproject.ui.map.components.poi

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import de.bornholdtlee.baseproject.R
import de.bornholdtlee.baseproject.base.map.BaseClusterInfoAdapter
import de.bornholdtlee.baseproject.ui.map.components.lesson.LessonClusterRenderer
import de.bornholdtlee.baseproject.ui.map.components.lesson.LessonClusterItem

class PoiClusterInfoAdapter(renderer: PoiClusterRenderer) : BaseClusterInfoAdapter<PoiClusterItem, PoiClusterRenderer>(renderer) {
    override val layoutId: Int = R.layout.info_window_layout

    override fun initViews(windowView: View, clusterItem: PoiClusterItem) {
        val title = windowView.findViewById(R.id.title) as TextView
        title.text = clusterItem.title
        val description = windowView.findViewById(R.id.description) as TextView
        description.text = clusterItem.snippet
        val image = windowView.findViewById(R.id.image) as ImageView
        image.setImageResource(clusterItem.icon)
    }
}