package de.bornholdtlee.baseproject.ui.map.clusteritems

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import de.bornholdtlee.baseproject.R
import de.bornholdtlee.baseproject.base.map.BaseClusterItem
import de.bornholdtlee.baseproject.base.map.BaseClusterRenderer
import de.bornholdtlee.baseproject.base.map.BaseClusterInfoAdapter

class MapClusterInfoAdapter(renderer: BaseClusterRenderer) : BaseClusterInfoAdapter(renderer) {
    override val layoutId: Int = R.layout.info_window_layout

    override fun initViews(windowView: View, clusterItem: BaseClusterItem) {
        val title = windowView.findViewById(R.id.title) as TextView
        title.text = clusterItem.title
        val description = windowView.findViewById(R.id.description) as TextView
        description.text = clusterItem.snippet
        val image = windowView.findViewById(R.id.image) as ImageView
        image.setImageResource(clusterItem.icon)
    }
}