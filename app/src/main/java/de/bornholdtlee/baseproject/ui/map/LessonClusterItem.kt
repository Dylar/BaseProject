package de.bornholdtlee.baseproject.ui.map

import de.bornholdtlee.baseproject.base.map.BaseClusterItem
import de.bornholdtlee.baseproject.model.Lesson

class LessonClusterItem(val lesson: Lesson)
    : BaseClusterItem(lesson.location, lesson.name, lesson.description) {

    override val icon: Int = android.R.drawable.sym_action_call
//    override val icon: Int = lesson.organizer.get(0).image
}