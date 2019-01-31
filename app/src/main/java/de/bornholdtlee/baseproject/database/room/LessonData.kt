package de.bornholdtlee.baseproject.database.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.android.gms.maps.model.LatLng
import de.bornholdtlee.baseproject.ui.map.components.MapItemInfo
import org.joda.time.DateTime
import java.util.*

@Entity(tableName = "LessonData")
data class LessonData(
        @PrimaryKey var uuid: String = UUID.randomUUID().toString(),
        @ColumnInfo(name = "name") override var name: String = "",
        @ColumnInfo(name = "description") override var description: String = "",
        @ColumnInfo(name = "location") override var location: LatLng? = null,
        @ColumnInfo(name = "image") var image: String? = null,
        @ColumnInfo(name = "startDate") var startDate: DateTime? = DateTime.now(),
        @ColumnInfo(name = "startEnd") var startEnd: DateTime? = startDate,
        @ColumnInfo(name = "createdAt") var createdAt: DateTime? = DateTime.now(),
        @ColumnInfo(name = "updatedAt") var updatedAt: DateTime? = createdAt,
        @ColumnInfo(name = "deletedAt") var deletedAt: DateTime? = null
) : MapItemInfo {
    override var id: Long = uuid.hashCode().toLong()
}