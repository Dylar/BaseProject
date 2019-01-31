package de.bornholdtlee.baseproject.injection.modules

import androidx.room.Room
import dagger.Module
import dagger.Provides
import de.bornholdtlee.baseproject.BuildConfig
import de.bornholdtlee.baseproject.base.BaseApplication
import de.bornholdtlee.baseproject.database.LessonRepository
import de.bornholdtlee.baseproject.database.OrganizerRepository
import de.bornholdtlee.baseproject.database.room.Database
import de.bornholdtlee.baseproject.database.room.LessonDao
import de.bornholdtlee.baseproject.database.room.UserDao
import de.bornholdtlee.baseproject.injection.ApplicationScope

@Module
class DatabaseModule {

//    @Provides
//    @ApplicationScope
//    fun provideDatabase(application: BaseApplication): BoxStore? {
//        //        BoxStore boxStore = MyObjectBox.builder().androidContext(application).build();
//        //        if (BuildConfig.DEBUG) {
//        //            new AndroidObjectBrowser(boxStore).constructionsStart(application);
//        //        }
//        return MyObjectBox.builder().androidContext(application).build()
//    }
//
//    @Provides
//    @ApplicationScope
//    fun provideOrganizerBox(store: BoxStore): Box<Organizer> {
//        return store.boxFor(Organizer::class.java)
//    }
//
//    @Provides
//    @ApplicationScope
//    fun provideAttendeeBox(store: BoxStore): Box<Attendee> {
//        return store.boxFor(Attendee::class.java)
//    }
//
//    @Provides
//    @ApplicationScope
//    fun provideLessonBox(store: BoxStore): Box<LessonData> {
//        return store.boxFor(LessonData::class.java)
//    }

    @Provides
    @ApplicationScope
    fun provideLessonRepository(application: BaseApplication): LessonRepository {
        return LessonRepository(application)
    }

    @Provides
    @ApplicationScope
    fun provideOrganizerRepository(application: BaseApplication): OrganizerRepository {
        return OrganizerRepository(application)
    }

    @Provides
    @ApplicationScope
    fun roomDatabase(application: BaseApplication): Database {
        return Room.databaseBuilder(
                application,
                Database::class.java, BuildConfig.DATABASE_NAME
        ).build()
    }

    @Provides
    @ApplicationScope
    fun lessonDao(application: Database): LessonDao {
        return application.lessonDao()
    }

    @Provides
    @ApplicationScope
    fun userDao(application: Database): UserDao {
        return application.userDao()
    }

}
