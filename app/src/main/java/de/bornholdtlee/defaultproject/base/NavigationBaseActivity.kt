package de.bornholdtlee.defaultproject.base

import android.graphics.Rect
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View

import butterknife.BindView
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationAdapter
import de.bitb.astroskop.ui.base.NavigationBaseFragment
import de.bornholdtlee.defaultproject.injection.IBind
import de.bornholdtlee.defaultproject.R

abstract class NavigationBaseActivity : BaseActivity(), IBind, AHBottomNavigation.OnTabSelectedListener {

    companion object {
        const val VIEW_HEIGHT_DIFFERENT = 600
    }

    @BindView(R.id.activity_navigation)
    var bottomNavigation: AHBottomNavigation? = null

    abstract val bottomMenuLayout: Int

    protected open val accentColor: Int
        get() = R.color.icon_blue
    protected open val inactiveColor: Int
        get() = R.color.icon_inactive_gray
    protected open val backgroundColor: Int
        get() = R.color.background_gray

    override var allowBackPress: Boolean = true
        get() {
            var allow = false
            val fragment = currentContent
            if (fragment is NavigationBaseFragment && fragment.navigationPosition != 0) {
                bottomNavigation!!.currentItem = 0
                allow = false
            }
            return allow
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupNavigationBar()
        checkKeyBoardUp()
    }

    private fun setupNavigationBar() {
        setBottomNavigationVisible(true)

        bottomNavigation!!.accentColor = ContextCompat.getColor(this, accentColor)
        bottomNavigation!!.inactiveColor = ContextCompat.getColor(this, inactiveColor)
        bottomNavigation!!.defaultBackgroundColor = ContextCompat.getColor(this, backgroundColor)
        bottomNavigation!!.isForceTint = true
        bottomNavigation!!.titleState = AHBottomNavigation.TitleState.ALWAYS_HIDE

        setCurrentTab(0)
        bottomNavigation!!.setOnTabSelectedListener(this)
        val adapter = AHBottomNavigationAdapter(this, bottomMenuLayout)
        adapter.setupWithBottomNavigation(bottomNavigation)
    }

    fun setBottomNavigationVisible(visible: Boolean) {
        bottomNavigation!!.visibility = if (visible) View.VISIBLE else View.GONE
    }

    fun setCurrentTab(currentTab: Int) {
        bottomNavigation!!.currentItem = currentTab
    }

    fun checkKeyBoardUp() {
        baseView!!.viewTreeObserver.addOnGlobalLayoutListener {
            val r = Rect()
            baseView!!.getWindowVisibleDisplayFrame(r)
            val heightDiff = baseView!!.rootView.height - (r.bottom - r.top)
            bottomNavigation!!.visibility = if (heightDiff > VIEW_HEIGHT_DIFFERENT) View.GONE else View.VISIBLE
        }
    }

}