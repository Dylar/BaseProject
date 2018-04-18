package de.bornholdtlee.defaultproject.utils;

import android.app.Activity;

import net.hockeyapp.android.CrashManager;
import net.hockeyapp.android.CrashManagerListener;

import de.bornholdtlee.defaultproject.BuildConfig;
import lombok.Getter;

public final class HockeyAppUtils {

    private HockeyAppUtils() {
    }

    /**
     * Info:
     * Diese Abfrage sollte nicht in der Splashscreen-Activity ausgeführt werden,
     * sondern erst in der darauf folgenden Activity
     */
    public static void checkForCrashes(Activity activity) {
        CrashManager.register(activity, BuildConfig.HOCKEY_APP_ID);
    }

    public static void checkForCrashes(Activity activity, CrashListener listener) {
        CrashManager.register(activity, BuildConfig.HOCKEY_APP_ID, listener);
    }

    public abstract static class CrashListener extends CrashManagerListener {

        @Getter
        private boolean crashFound;

        @Override
        public void onNewCrashesFound() {
            crashFound = true;
        }

        @Override
        public void onConfirmedCrashesFound() {
            crashFound = false;
            onDialogHandled();
        }

        @Override
        public void onCrashesSent() {
            crashFound = false;
            onDialogHandled();
        }

        @Override
        public void onCrashesNotSent() {
            crashFound = false;
            onDialogHandled();
        }

        @Override
        public void onUserDeniedCrashes() {
            crashFound = false;
            onDialogHandled();
        }

        public abstract void onDialogHandled();
    }
}