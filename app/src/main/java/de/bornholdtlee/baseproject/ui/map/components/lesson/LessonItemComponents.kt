package de.bornholdtlee.baseproject.ui.map.components.lesson

import android.content.Context
import com.google.android.gms.maps.GoogleMap
import com.google.maps.android.clustering.ClusterManager
import de.bornholdtlee.baseproject.base.map.BaseMapItemComponents
import de.bornholdtlee.baseproject.base.map.MapListener
import de.bornholdtlee.baseproject.database.room.LessonData
import de.bornholdtlee.baseproject.model.Lesson
import de.bornholdtlee.baseproject.ui.map.components.MapItemInfo

class LessonItemComponents(context: Context, mapListener: MapListener) : BaseMapItemComponents<LessonClusterItem, LessonClusterRenderer, LessonClusterInfoAdapter>(context, mapListener) {
    override fun createClusterItem(item: MapItemInfo): LessonClusterItem {
        return LessonClusterItem(item as LessonData)
    }

    override fun createInfoViewAdapter(renderer: LessonClusterRenderer): LessonClusterInfoAdapter {
        return LessonClusterInfoAdapter(renderer)
    }

    override fun createRenderer(context: Context, googleMap: GoogleMap, clusterManager: ClusterManager<LessonClusterItem>)
            : LessonClusterRenderer = LessonClusterRenderer(context, googleMap, clusterManager)
}