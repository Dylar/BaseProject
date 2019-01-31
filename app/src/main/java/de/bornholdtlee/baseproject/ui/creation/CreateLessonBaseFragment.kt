package de.bornholdtlee.baseproject.ui.creation

import android.os.Bundle
import android.view.View
import de.bornholdtlee.baseproject.base.mvp.MVPFragment
import de.bornholdtlee.baseproject.database.room.LessonData
import de.bornholdtlee.baseproject.model.Lesson

abstract class CreateLessonBaseFragment : MVPFragment<ICreateLessonView, CreateLessonPresenter>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onCreate()
    }

    abstract fun refreshView(lesson: LessonData)
}