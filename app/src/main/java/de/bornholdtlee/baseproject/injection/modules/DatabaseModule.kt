package de.bornholdtlee.baseproject.injection.modules

import dagger.Module
import dagger.Provides
import de.bornholdtlee.baseproject.base.BaseApplication
import de.bornholdtlee.baseproject.database.LessonRepository
import de.bornholdtlee.baseproject.database.OrganizerRepository
import de.bornholdtlee.baseproject.injection.ApplicationScope
import de.bornholdtlee.baseproject.model.Attendee
import de.bornholdtlee.baseproject.model.Lesson
import de.bornholdtlee.baseproject.model.Organizer
import io.objectbox.Box
import io.objectbox.BoxStore

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
//    fun provideLessonBox(store: BoxStore): Box<Lesson> {
//        return store.boxFor(Lesson::class.java)
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
}
