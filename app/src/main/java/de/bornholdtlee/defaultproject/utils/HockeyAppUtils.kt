package de.bornholdtlee.defaultproject.utils

import android.app.Activity

import net.hockeyapp.android.CrashManager
import net.hockeyapp.android.CrashManagerListener

import de.bornholdtlee.defaultproject.BuildConfig

object HockeyAppUtils {

    /**
     * Info:
     * Diese Abfrage sollte nicht in der Splashscreen-Activity ausgeführt werden,
     * sondern erst in der darauf folgenden Activity
     */
    fun checkForCrashes(activity: Activity) {
        CrashManager.register(activity, BuildConfig.HOCKEY_APP_ID)
    }

    fun checkForCrashes(activity: Activity, listener: CrashListener) {
        CrashManager.register(activity, BuildConfig.HOCKEY_APP_ID, listener)
    }

    abstract class CrashListener : CrashManagerListener() {

        private var crashFound: Boolean = false

        override fun onNewCrashesFound() {
            crashFound = true
        }

        override fun onConfirmedCrashesFound() {
            crashFound = false
            onDialogHandled()
        }

        override fun onCrashesSent() {
            crashFound = false
            onDialogHandled()
        }

        override fun onCrashesNotSent() {
            crashFound = false
            onDialogHandled()
        }

        override fun onUserDeniedCrashes() {
            crashFound = false
            onDialogHandled()
        }

        abstract fun onDialogHandled()
    }
}