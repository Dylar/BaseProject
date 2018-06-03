package de.bornholdtlee.baseproject.database

import de.bornholdtlee.baseproject.base.BaseApplication
import de.bornholdtlee.baseproject.injection.IInjection
import de.bornholdtlee.baseproject.injection.components.AppComponent
import de.bornholdtlee.baseproject.model.Lesson
import io.objectbox.Box
import javax.inject.Inject

class LessonDatabaseHandler(application: BaseApplication) : BaseDatabaseHandler(application), IInjection {

    @Inject
    lateinit var lessonBox: Box<Lesson>

    override fun inject(appComponent: AppComponent) {
//        appComponent.inject(this)
    }

    fun upsert(lesson: Lesson) {
        lessonBox.put(lesson)
    }


}
