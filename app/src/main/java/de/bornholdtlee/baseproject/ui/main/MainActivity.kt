package de.bornholdtlee.baseproject.ui.main

import android.os.Bundle
import de.bornholdtlee.baseproject.*
import de.bornholdtlee.baseproject.base.navigation.NavigationBaseActivity
import de.bornholdtlee.baseproject.ui.lesson.creation.LessonOverviewFragment
import de.bornholdtlee.baseproject.ui.map.MapFragment
import de.bornholdtlee.baseproject.ui.test.BrownbagFragment
import de.bornholdtlee.baseproject.ui.test.TestFragment
import de.bornholdtlee.baseproject.utils.HockeyAppUtils

class MainActivity : NavigationBaseActivity() {

    override val bottomMenuLayout: Int = R.menu.navigation_menu
    override val hasToolbar: Boolean = false //TODO just for brownbag

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
                TAB_HOME -> showFragment(BrownbagFragment.createInstance())
                TAB_MAP -> showFragment(MapFragment.createInstance())
                TAB_LESSON -> showFragment(LessonOverviewFragment.createInstance())
                TAB_TEST -> showFragment(TestFragment.createInstance())
                else -> return false
            }
            return true
        }
        return false
    }
}