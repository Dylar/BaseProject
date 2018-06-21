package de.bornholdtlee.baseproject.controller

import com.google.android.gms.maps.model.LatLng
import de.bornholdtlee.baseproject.base.BaseApplication
import de.bornholdtlee.baseproject.database.LessonRepository
import de.bornholdtlee.baseproject.injection.IInjection
import de.bornholdtlee.baseproject.injection.components.AppComponent
import de.bornholdtlee.baseproject.model.Attendee
import de.bornholdtlee.baseproject.model.Lesson
import de.bornholdtlee.baseproject.model.Organizer
import org.joda.time.DateTime
import javax.inject.Inject

class LessonController(baseApplication: BaseApplication) : BaseController(baseApplication), IInjection {

    @Inject
    lateinit var lessonRepository: LessonRepository

    override fun inject(appComponent: AppComponent) {
        appComponent.inject(this)
    }

    fun createLesson(lesson: Lesson) {
        lessonRepository.upsert(lesson)
    }

    fun createLesson(name: String,
                     description: String = "",
                     location: LatLng,
                     organizer: MutableList<Organizer> = ArrayList(),
                     attendees: MutableList<Attendee> = ArrayList()): Lesson {
        val lesson = Lesson()
        lesson.location = location
        lesson.createdAt = DateTime.now()
        lesson.updatedAt = DateTime.now()
        lesson.name = name
        lesson.description = description
        lesson.organizer = organizer
        lesson.attendees = attendees
        lessonRepository.upsert(lesson)
        return lesson

    }
}

