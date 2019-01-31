package de.bornholdtlee.baseproject.database

import de.bornholdtlee.baseproject.base.BaseApplication
import de.bornholdtlee.baseproject.database.room.Database
import de.bornholdtlee.baseproject.database.room.LessonDao
import de.bornholdtlee.baseproject.database.room.LessonData
import de.bornholdtlee.baseproject.injection.IInjection
import de.bornholdtlee.baseproject.injection.components.AppComponent
import de.bornholdtlee.baseproject.model.Lesson
import javax.inject.Inject

class LessonRepository(application: BaseApplication) : BaseRepository(application), IInjection {

    @Inject
    lateinit var database: Database

    @Inject
    lateinit var lessonDao: LessonDao

    companion object {
        private var allLessons = ArrayList<Lesson>()
    }

    override fun inject(appComponent: AppComponent) {
//        appComponent.inject(this)
    }

    fun upsert(lesson: LessonData) {

//        lessonBox.put(lesson)
        lesson.id = allLessons.size.toLong()
        lessonDao.insertAll(lesson)
//        allLessons.add(lesson)
    }

    fun getAll(): List<Lesson> {
        return ArrayList()//database.lessonDao().getAll()
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
