package de.bornholdtlee.baseproject.database.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserData(
        @PrimaryKey var uuid: String,
        @ColumnInfo(name = "first_name") var firstName: String?,
        @ColumnInfo(name = "last_name") var lastName: String?,
        @ColumnInfo(name = "age") var age: Int?
)