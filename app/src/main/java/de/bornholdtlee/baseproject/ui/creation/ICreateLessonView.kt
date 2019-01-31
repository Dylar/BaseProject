package de.bornholdtlee.baseproject.ui.creation

import de.bornholdtlee.baseproject.base.IBaseView
import de.bornholdtlee.baseproject.database.room.LessonData
import de.bornholdtlee.baseproject.model.Lesson

interface ICreateLessonView : IBaseView {

    fun initView(lesson: LessonData)
    fun navigateToDescriptionScreen()
    fun navigateToOrganizerScreen()
    fun finishCreation()

}