package de.bornholdtlee.defaultproject.ui.main

import android.os.Bundle
import de.bornholdtlee.defaultproject.R
import de.bornholdtlee.defaultproject.TAB_HOME
import de.bornholdtlee.defaultproject.TAB_MAP
import de.bornholdtlee.defaultproject.base.NavigationBaseActivity
import de.bornholdtlee.defaultproject.ui.map.MapFragment
import de.bornholdtlee.defaultproject.utils.HockeyAppUtils

class MainActivity : NavigationBaseActivity() {

    override val bottomMenuLayout: Int = R.menu.navigation_menu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onTabSelected(TAB_HOME, false)
    }

    override fun onResume() {
        super.onResume()
        HockeyAppUtils.checkForCrashes(this)
    }

    override fun onTabSelected(position: Int, wasSelected: Boolean): Boolean {
        if (!wasSelected) {
            when (position) {
                TAB_MAP -> showFragment(MainFragment.createInstance())
                TAB_HOME -> showFragment(MapFragment.createInstance())
                else ->
                    return false
            }
            return true
        }
        return false
    }
}