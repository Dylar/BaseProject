package de.bornholdtlee.baseproject.database

import de.bornholdtlee.baseproject.base.BaseApplication
import de.bornholdtlee.baseproject.injection.IInjection
import de.bornholdtlee.baseproject.injection.components.AppComponent
import de.bornholdtlee.baseproject.model.Lesson
import de.bornholdtlee.baseproject.utils.Logger

class LessonRepository(application: BaseApplication) : BaseRepository(application), IInjection {

    //    @Inject
//    lateinit var lessonBox: Box<Lesson>
    companion object {
        private var allLessons = ArrayList<Lesson>()
    }

    override fun inject(appComponent: AppComponent) {
//        appComponent.inject(this)
    }

    fun upsert(lesson: Lesson) {
//        lessonBox.put(lesson)
        lesson.id = allLessons.size.toLong()
        allLessons.add(lesson)
    }

    fun getAll(): List<Lesson> {
        return allLessons
    }

    fun getByIds(ids: List<Int>): List<Lesson> {
        val lessons = ArrayList<Lesson>()
        for (lesson in allLessons) {
            if (lesson.id.toInt() in ids) {
                lessons.add(lesson)
            }
        }
        return lessons
    }

    fun getById(id: Int): Lesson? {
        for (lesson in allLessons) {
            if (lesson.id.toInt() == id) {
                return lesson
            }
        }
        return null
    }


}
