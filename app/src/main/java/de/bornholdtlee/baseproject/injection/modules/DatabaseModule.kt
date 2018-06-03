package de.bornholdtlee.baseproject.injection.modules

import dagger.Module
import dagger.Provides
import de.bornholdtlee.baseproject.base.BaseApplication
import de.bornholdtlee.baseproject.database.LessonDatabaseHandler
import de.bornholdtlee.baseproject.model.Attendee
import de.bornholdtlee.baseproject.model.Lesson
import de.bornholdtlee.baseproject.model.MyObjectBox
import de.bornholdtlee.baseproject.model.Organizer
import io.objectbox.Box
import io.objectbox.BoxStore
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(application: BaseApplication): BoxStore? {
        //        BoxStore boxStore = MyObjectBox.builder().androidContext(application).build();
        //        if (BuildConfig.DEBUG) {
        //            new AndroidObjectBrowser(boxStore).constructionsStart(application);
        //        }
        return MyObjectBox.builder().androidContext(application).build()
    }

    @Provides
    @Singleton
    fun provideOrganizerBox(store: BoxStore): Box<Organizer> {
        return store.boxFor(Organizer::class.java)
    }

    @Provides
    @Singleton
    fun provideAttendeeBox(store: BoxStore): Box<Attendee> {
        return store.boxFor(Attendee::class.java)
    }

    @Provides
    @Singleton
    fun provideLessonBox(store: BoxStore): Box<Lesson> {
        return store.boxFor(Lesson::class.java)
    }

    @Provides
    @Singleton
    fun provideLessonHandlerBox(application: BaseApplication): LessonDatabaseHandler{
        return LessonDatabaseHandler(application)
    }
}
