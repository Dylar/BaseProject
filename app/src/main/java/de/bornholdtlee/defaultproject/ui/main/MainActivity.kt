package de.bornholdtlee.defaultproject.ui.main

import android.os.Bundle

import de.bornholdtlee.defaultproject.R
import de.bornholdtlee.defaultproject.base.BaseActivity
import de.bornholdtlee.defaultproject.utils.HockeyAppUtils

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showFragment(MainFragment(), CONTAINER, true)
    }

    override fun onResume() {
        super.onResume()

        HockeyAppUtils.checkForCrashes(this)
    }

    companion object {

        val CONTAINER = R.id.activity_main_container
    }
}