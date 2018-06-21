package de.bornholdtlee.baseproject.controller

import de.bornholdtlee.baseproject.R
import de.bornholdtlee.baseproject.base.BaseApplication
import de.bornholdtlee.baseproject.database.OrganizerRepository
import de.bornholdtlee.baseproject.injection.IInjection
import de.bornholdtlee.baseproject.injection.components.AppComponent
import de.bornholdtlee.baseproject.model.Organizer
import org.joda.time.DateTime
import javax.inject.Inject

class OrganizerController(baseApplication: BaseApplication) : BaseController(baseApplication), IInjection {

    @Inject
    lateinit var organizerRepository: OrganizerRepository

    override fun inject(appComponent: AppComponent) {
        appComponent.inject(this)
    }

    fun createOrganizer(name: String,
                        image: Int = R.drawable.abc_ic_star_black_48dp)
            : Organizer {
        val organizer = Organizer(name, image)
        organizer.createdAt = DateTime.now()
        organizer.name = name
        organizerRepository.upsert(organizer)
        return organizer

    }
}

