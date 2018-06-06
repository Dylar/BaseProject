package de.bornholdtlee.baseproject.controller

import de.bornholdtlee.baseproject.base.BaseApplication
import de.bornholdtlee.baseproject.database.LessonDatabaseHandler
import de.bornholdtlee.baseproject.injection.IInjection
import de.bornholdtlee.baseproject.injection.components.AppComponent
import de.bornholdtlee.baseproject.model.Attendee
import de.bornholdtlee.baseproject.model.Lesson
import de.bornholdtlee.baseproject.model.Organizer
import org.joda.time.DateTime
import javax.inject.Inject

class LessonController(baseApplication: BaseApplication) : BaseController(baseApplication), IInjection {

    @Inject
    lateinit var lessonDBHandler: LessonDatabaseHandler

    override fun inject(appComponent: AppComponent) {
        appComponent.inject(this)
    }

    fun createLesson(name: String,
                     description: String = "",
                     organizer: List<Organizer> = ArrayList(),
                     attendees: List<Attendee> = ArrayList()): Lesson {
        val lesson = Lesson()
        lesson.createdAt = DateTime.now()
        lesson.updatedAt = DateTime.now()
        lesson.name = name
        lesson.description = description
        lessonDBHandler.upsert(lesson)
//        lesson.organizer.addAll(organizer)
//        lesson.attendees.addAll(attendees)
//        lessonDBHandler.upsert(lesson)
        return lesson

    }
}

