package de.bornholdtlee.baseproject.base.navigation

import android.graphics.Rect
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import butterknife.BindView
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationAdapter
import de.bornholdtlee.baseproject.injection.IBind
import de.bornholdtlee.baseproject.R
import de.bornholdtlee.baseproject.base.BaseActivity

abstract class NavigationBaseActivity : BaseActivity(), IBind, AHBottomNavigation.OnTabSelectedListener {

    companion object {
        const val VIEW_HEIGHT_DIFFERENT = 600
    }

    @BindView(R.id.activity_navigation)
    lateinit var bottomNavigation: AHBottomNavigation

    abstract val bottomMenuLayout: Int

    protected open val accentColor: Int = R.color.icon_blue
    protected open val inactiveColor: Int = R.color.icon_inactive_gray
    protected open val backgroundColor: Int = R.color.background_gray

    override var allowBackPress: Boolean = true
        get() {
            var allow = true
            val fragment = getCurrentContent()
            if (fragment is NavigationBaseTab && fragment.navigationPosition != 0) {
                bottomNavigation.currentItem = 0
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

        bottomNavigation.accentColor = ContextCompat.getColor(this, accentColor)
        bottomNavigation.inactiveColor = ContextCompat.getColor(this, inactiveColor)
        bottomNavigation.defaultBackgroundColor = ContextCompat.getColor(this, backgroundColor)
        bottomNavigation.titleState = AHBottomNavigation.TitleState.ALWAYS_HIDE

        bottomNavigation.setOnTabSelectedListener { position, wasSelected ->
            showLoadingIndicator(true)
            val loader: Boolean = this@NavigationBaseActivity.onTabSelected(position, wasSelected)
            showLoadingIndicator(loader)
            loader
        }
        val adapter = AHBottomNavigationAdapter(this, bottomMenuLayout)
        adapter.setupWithBottomNavigation(bottomNavigation)
//        setCurrentTab(TAB_HOME)
    }

    fun setBottomNavigationVisible(visible: Boolean) {
        bottomNavigation.visibility = if (visible) View.VISIBLE else View.GONE
    }

    fun setCurrentTab(currentTab: Int) {
        bottomNavigation.currentItem = currentTab
        for (i in 0..bottomNavigation.itemsCount) {
            bottomNavigation.getItem(i)?.setColor(if (i == currentTab) accentColor else inactiveColor)
        }
    }

    fun checkKeyBoardUp() {
        baseView!!.viewTreeObserver.addOnGlobalLayoutListener {
            val r = Rect()
            baseView!!.getWindowVisibleDisplayFrame(r)
            val heightDiff = baseView!!.rootView.height - (r.bottom - r.top)
            bottomNavigation.visibility = if (heightDiff > VIEW_HEIGHT_DIFFERENT) View.GONE else View.VISIBLE
        }
    }

}