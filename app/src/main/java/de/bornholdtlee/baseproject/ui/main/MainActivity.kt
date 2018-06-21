package de.bornholdtlee.baseproject.ui.main

import android.os.Bundle
import de.bornholdtlee.baseproject.R
import de.bornholdtlee.baseproject.TAB_HOME
import de.bornholdtlee.baseproject.TAB_LESSON
import de.bornholdtlee.baseproject.TAB_MAP
import de.bornholdtlee.baseproject.base.navigation.NavigationBaseActivity
import de.bornholdtlee.baseproject.ui.lesson.creation.LessonOverviewFragment
import de.bornholdtlee.baseproject.ui.map.MapFragment
import de.bornholdtlee.baseproject.utils.HockeyAppUtils

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
                TAB_HOME -> showFragment(MainFragment.createInstance())
                TAB_MAP -> showFragment(MapFragment.createInstance())
                TAB_LESSON -> showFragment(LessonOverviewFragment.createInstance())
                else -> return false
            }
            return true
        }
        return false
    }
}