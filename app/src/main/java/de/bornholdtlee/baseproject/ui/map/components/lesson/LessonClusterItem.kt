package de.bornholdtlee.baseproject.ui.map.components.lesson

import de.bornholdtlee.baseproject.CLUSTER_TYPE_LESSON
import de.bornholdtlee.baseproject.base.map.BaseClusterItem
import de.bornholdtlee.baseproject.model.Lesson

class LessonClusterItem(val lesson: Lesson)
    : BaseClusterItem(lesson) {

    override val dataType: String = CLUSTER_TYPE_LESSON
    //    override val icon: Int = R.drawable.marker_lesson
    override val icon: Int = lesson.organizer[0].image
}