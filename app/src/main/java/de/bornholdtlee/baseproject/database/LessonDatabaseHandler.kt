package de.bornholdtlee.baseproject.database

import de.bornholdtlee.baseproject.base.BaseApplication
import de.bornholdtlee.baseproject.injection.IInjection
import de.bornholdtlee.baseproject.injection.components.AppComponent
import de.bornholdtlee.baseproject.model.Lesson

class LessonDatabaseHandler(application: BaseApplication) : BaseDatabaseHandler(application), IInjection {

    //    @Inject
//    lateinit var lessonBox: Box<Lesson>
    var allLessons = ArrayList<Lesson>()

    override fun inject(appComponent: AppComponent) {
//        appComponent.inject(this)
    }

    fun upsert(lesson: Lesson) {
//        lessonBox.put(lesson)
        lesson.id = allLessons.size.toLong()
        allLessons.add(lesson)
    }

    fun getLessons(ids: List<Int>): List<Lesson> {
        val lessons = ArrayList<Lesson>()
        for (lesson in allLessons) {
            if (lesson.id.toInt() in ids) {
                lessons.add(lesson)
            }
        }
        return lessons
    }

    fun getLesson(id: Int): Lesson? {
        for (lesson in allLessons) {
            if (lesson.id.toInt() == id) {
                return lesson
            }
        }
        return null
    }


}
