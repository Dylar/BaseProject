package de.bornholdtlee.baseproject.ui.creation

import de.bornholdtlee.baseproject.base.IBaseView
import de.bornholdtlee.baseproject.model.Lesson

interface ICreateLessonView : IBaseView {

    fun initView(lesson: Lesson)
    fun navigateToDescriptionScreen()
    fun navigateToOrganizerScreen()
    fun finishCreation()

}