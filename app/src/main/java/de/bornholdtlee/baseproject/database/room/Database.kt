package de.bornholdtlee.baseproject.database.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import de.bornholdtlee.baseproject.database.converter.DateConverter
import de.bornholdtlee.baseproject.database.converter.LocationConverter

@Database(entities = arrayOf(UserData::class, LessonData::class), version = 1)
@TypeConverters(DateConverter::class, LocationConverter::class)
abstract class Database : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun lessonDao(): LessonDao
}