package de.bornholdtlee.defaultproject.ui.main

import android.os.Bundle
import de.bornholdtlee.defaultproject.R
import de.bornholdtlee.defaultproject.base.NavigationBaseActivity
import de.bornholdtlee.defaultproject.ui.map.MapFragment
import de.bornholdtlee.defaultproject.utils.HockeyAppUtils

class MainActivity : NavigationBaseActivity() {

    override val bottomMenuLayout: Int
        get() = R.menu.navigation_menu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onTabSelected(0, false)
    }

    override fun onResume() {
        super.onResume()
        HockeyAppUtils.checkForCrashes(this)
    }

    override fun onTabSelected(position: Int, wasSelected: Boolean): Boolean {
        if(!wasSelected){
            when(position){
                0 -> showFragment(MainFragment.createInstance())
                1 -> showFragment(MapFragment.createInstance())
            }

        }
        return true
    }
}