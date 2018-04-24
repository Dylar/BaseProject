package de.bornholdtlee.defaultproject.injection.modules;

import net.hockeyapp.android.utils.ImageUtils;

import org.greenrobot.essentials.io.FileUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.bornholdtlee.defaultproject.BaseApplication;
import de.bornholdtlee.defaultproject.utils.NetworkUtils;
import de.bornholdtlee.defaultproject.utils.SharedPreferencesUtils;
import de.bornholdtlee.defaultproject.utils.UiUtils;

@Module
public class UtilsModule {

    @Provides
    @Singleton
    NetworkUtils provideNetworkUtils(BaseApplication context) {
        return new NetworkUtils(context);
    }

    @Provides
    @Singleton
    SharedPreferencesUtils provideSharedPreferencesUtils(BaseApplication context) {
        return new SharedPreferencesUtils(context);
    }

    @Provides
    @Singleton
    UiUtils provideUiUtils() {
        return new UiUtils();
    }

    @Provides
    @Singleton
    ImageUtils provideImageUtils() {
        return new ImageUtils();
    }

    @Provides
    @Singleton
    FileUtils provideFileUtils() {
        return new FileUtils();
    }
//
//    @Provides
//    @Singleton
//    ToastBuilder provideToastBuilder() {
//        return new ToastBuilder();
//    }
//
//    @Provides
//    @Singleton
//    DialogBuilder provideDialogBuilder() {
//        return new DialogBuilder();
//    }

}
