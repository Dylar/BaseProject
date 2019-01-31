package de.bornholdtlee.baseproject.database.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(tableName = "OrganizerData",
        primaryKeys = arrayOf("userId", "lessonId"),
        foreignKeys = arrayOf(
                ForeignKey(entity = UserData::class,
                        parentColumns = arrayOf("uuid"),
                        childColumns = arrayOf("userId")),
                ForeignKey(entity = LessonData::class,
                        parentColumns = arrayOf("uuid"),
                        childColumns = arrayOf("lessonId"))))
data class OrganizerData(
        @ColumnInfo(name = "userId") var userId: String,
        @ColumnInfo(name = "lessonId") var lessonId: String
) {
}