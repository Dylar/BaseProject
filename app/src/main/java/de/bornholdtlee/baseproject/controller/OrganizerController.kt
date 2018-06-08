package de.bornholdtlee.baseproject.controller

import de.bornholdtlee.baseproject.R
import de.bornholdtlee.baseproject.base.BaseApplication
import de.bornholdtlee.baseproject.database.LessonDatabaseHandler
import de.bornholdtlee.baseproject.injection.IInjection
import de.bornholdtlee.baseproject.injection.components.AppComponent
import de.bornholdtlee.baseproject.model.Lesson
import de.bornholdtlee.baseproject.model.Organizer
import org.joda.time.DateTime
import javax.inject.Inject

class OrganizerController(baseApplication: BaseApplication) : BaseController(baseApplication), IInjection {

    @Inject
    lateinit var lessonDBHandler: LessonDatabaseHandler

    override fun inject(appComponent: AppComponent) {
        appComponent.inject(this)
    }

    fun createOrganizer(name: String,
                        icon: Int = R.drawable.abc_ic_star_black_48dp): Lesson {
        val organizer = Organizer()
        organizer.createdAt = DateTime.now()
        organizer.name = name
        lessonDBHandler.upsert(organizer)
//        organizer.organizer.addAll(organizer)
//        organizer.attendees.addAll(attendees)
//        lessonDBHandler.upsert(organizer)
        return organizer

    }
}

