package de.bornholdtlee.baseproject.database.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LessonDao {
    @Query("SELECT * FROM LessonData")
    fun getAll(): List<LessonData>

    @Query("SELECT * FROM LessonData WHERE uuid IN (:lessonIds)")
    fun loadAllByIds(lessonIds: IntArray): List<LessonData>

//    @Query("SELECT * FROM LessonData WHERE first_name LIKE :first AND " +
//            "last_name LIKE :last LIMIT 1")
//    fun findByName(first: String, last: String): UserData

    @Insert
    fun insertAll(vararg lessons: LessonData)

    @Delete
    fun delete(lesson: LessonData)
}