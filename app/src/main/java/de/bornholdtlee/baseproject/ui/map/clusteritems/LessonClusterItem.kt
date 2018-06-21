package de.bornholdtlee.baseproject.ui.map.clusteritems

import de.bornholdtlee.baseproject.CLUSTER_TYPE_LESSON
import de.bornholdtlee.baseproject.R
import de.bornholdtlee.baseproject.base.map.BaseClusterItem
import de.bornholdtlee.baseproject.model.Lesson

class LessonClusterItem(val lesson: Lesson)
    : BaseClusterItem(lesson.location, title = lesson.name, snippet = lesson.description) {

    override val dataType: String = CLUSTER_TYPE_LESSON
//    override val icon: Int = R.drawable.marker_lesson
    override val icon: Int = lesson.organizer[0].image
}