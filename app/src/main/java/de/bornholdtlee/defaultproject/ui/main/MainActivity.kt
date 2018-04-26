package de.bornholdtlee.defaultproject.ui.main

import de.bornholdtlee.defaultproject.R
import de.bornholdtlee.defaultproject.base.BaseActivity
import de.bornholdtlee.defaultproject.utils.HockeyAppUtils

class MainActivity : BaseActivity() {

    override fun onResume() {
        super.onResume()

        HockeyAppUtils.checkForCrashes(this)
    }
}