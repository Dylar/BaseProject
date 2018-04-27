package de.bornholdtlee.defaultproject.ui.main

import android.os.Bundle
import de.bornholdtlee.defaultproject.base.BaseActivity
import de.bornholdtlee.defaultproject.utils.HockeyAppUtils

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        showFragment(MainFragment.createInstance())
    }

    override fun onResume() {
        super.onResume()

        HockeyAppUtils.checkForCrashes(this)
    }
}