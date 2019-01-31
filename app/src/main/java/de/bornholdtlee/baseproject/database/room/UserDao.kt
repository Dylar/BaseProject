package de.bornholdtlee.baseproject.database.room

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg userData: UserData): Completable

    @Delete
    fun delete(vararg userData: UserData): Completable

    @Query("DELETE FROM user WHERE first_name = :firstName AND last_name = :lastName")
    fun deleteByName(firstName: String, lastName: String): Int


    @Query("SELECT * FROM user")
    fun getAll(): Single<List<UserData>>

    @Query("SELECT * FROM user WHERE uuid IN (:userIds)")
    fun getAllByIds(vararg userIds: String): Single<List<UserData>>


    @Query("SELECT * FROM user")
    fun observeAllFlowable(): Flowable<List<UserData>>

    @Query("SELECT * FROM user")
    fun observeAllObserver(): Observable<List<UserData>>

    @Query("SELECT * FROM user WHERE first_name LIKE :name OR last_name LIKE :name")
    fun observeByName(name: String): Flowable<List<UserData>>


    @Query("SELECT * FROM LessonData")
    fun lessons(): Flowable<List<LessonData>>

}