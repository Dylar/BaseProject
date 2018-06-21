package de.bornholdtlee.baseproject.database

import de.bornholdtlee.baseproject.R
import de.bornholdtlee.baseproject.base.BaseApplication
import de.bornholdtlee.baseproject.injection.IInjection
import de.bornholdtlee.baseproject.injection.components.AppComponent
import de.bornholdtlee.baseproject.model.Lesson
import de.bornholdtlee.baseproject.model.Organizer

class OrganizerRepository(application: BaseApplication) : BaseRepository(application), IInjection {

    companion object {
        private var allOrganizer = ArrayList<Organizer>()
    }

    override fun inject(appComponent: AppComponent) {
//        appComponent.inject(this)
    }

    fun upsert(organizer: Organizer) {
        organizer.id = allOrganizer.size.toLong()
        allOrganizer.add(organizer)
    }

    fun getAll(): ArrayList<Organizer> {
        if (allOrganizer.isEmpty()) {
            var organizer = Organizer("Peter", R.drawable.abc_ic_star_black_48dp)
            allOrganizer.add(organizer)

            organizer = Organizer("Olli", R.drawable.abc_ic_menu_copy_mtrl_am_alpha)
            allOrganizer.add(organizer)

        }
        return allOrganizer
    }

    fun getByIds(ids: List<Int>): List<Organizer> {
        val organizers = ArrayList<Organizer>()
        for (organizer in allOrganizer) {
            if (organizer.id.toInt() in ids) {
                organizers.add(organizer)
            }
        }
        return organizers
    }

    fun getById(id: Int): Organizer? {
        for (organizer in allOrganizer) {
            if (organizer.id.toInt() == id) {
                return organizer
            }
        }
        return null
    }


}
