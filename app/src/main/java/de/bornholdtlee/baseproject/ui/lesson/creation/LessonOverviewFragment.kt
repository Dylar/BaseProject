package de.bornholdtlee.baseproject.ui.lesson.creation

import de.bornholdtlee.baseproject.R
import de.bornholdtlee.baseproject.base.BaseFragment

class LessonOverviewFragment : BaseFragment(){

    companion object {
        fun createInstance(): LessonOverviewFragment {
            return LessonOverviewFragment()
        }
    }

    override val layoutId: Int = R.layout.fragment_lesson_overview

}