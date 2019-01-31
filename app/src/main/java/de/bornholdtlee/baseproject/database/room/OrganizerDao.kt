package de.bornholdtlee.baseproject.database.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import net.hockeyapp.android.metrics.model.User

@Dao
interface OrganizerDao {
    @Insert
    fun insert(organizerData: OrganizerData): Completable

//    @Query("SELECT * FROM UserData INNER JOIN organizerdata ON UserData.uuid = organizerdata.userId WHERE organizerdata.lessonId = :lessonId")
//    fun getOrganizerForLesson(lessonId: Int): List<User> //TODO

//    @Query("SELECT * FROM repo INNER JOIN user_repo_join ON
//            repo . id = user_repo_join . repoId WHERE
//            user_repo_join . userId =:userId"   )
//    List<Repo> getRepositoriesForUsers(final int userId);
}