package de.bornholdtlee.defaultproject.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.*
import butterknife.ButterKnife
import de.bitb.astroskop.injection.IBind
import de.bitb.astroskop.injection.IInjection
import de.bornholdtlee.defaultproject.BaseApplication
import de.bornholdtlee.defaultproject.R
import de.bornholdtlee.defaultproject.enums.AnimationType
import de.bornholdtlee.defaultproject.injection.components.AppComponent
import de.bornholdtlee.defaultproject.utils.UiUtils
import lombok.Getter
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

    companion object {

        @Getter
        var isAppInForeground: Boolean = false
            private set
    }

    @Inject
    @Getter
    private var uiUtils: UiUtils? = null

    open val animationType: AnimationType
        get() = AnimationType.NONE

    val appComponent: AppComponent
        get() = (application as BaseApplication).appComponent

    protected open val layoutId: Int
        get() = R.layout.activity_container

    protected open val contentContainerId: Int
        get() = R.id.activity_container

    protected open val actionbarCallback: ActionbarHandler.ActionbarCallback
        get() = ActionbarHandler.ActionbarCallback()

    protected open val actionbarHandler: ActionbarHandler
        get() = ActionbarHandler(actionbarCallback)

    val currentContent: BaseFragment?
        get() = supportFragmentManager.findFragmentById(contentContainerId) as BaseFragment

    @Getter
    var baseView: View? = null
        private set

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (this is IInjection) {
            (this as IInjection).inject(appComponent)
        } else {
            appComponent.inject(this)
        }

        val inflater = LayoutInflater.from(this)
        val activityView = inflater.inflate(layoutId, null)

        baseView = inflater.inflate(R.layout.activity_base, null)

        val container = baseView!!.findViewById<ViewGroup>(R.id.activity_base_content)
        container.removeAllViews()
        container.addView(activityView)

        initToolbar(baseView as View)

        setContentView(baseView)

        if (this !is NavigationBaseActivity) {
            baseView!!.findViewById<View>(R.id.activity_navigation).visibility = View.GONE
        }

        if (this is IBind) {
            ButterKnife.bind(this)
        }

    }


    private fun initToolbar(view: View) {
        val toolbar = view.findViewById<Toolbar>(R.id.activity_base_toolbar)
        if (this is IToolbarView) {
            setSupportActionBar(toolbar)
            supportActionBar!!.setDisplayShowTitleEnabled(true)
            supportActionBar!!.setHomeButtonEnabled(true)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        } else {
            toolbar.visibility = View.GONE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.actionbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        return actionbarHandler!!.onPrepareOptionsMenu(menu) || super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return actionbarHandler!!.onOptionsItemSelected(item.itemId) || super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        isAppInForeground = true
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
    }

    override fun onPause() {
        super.onPause()
        isAppInForeground = false
    }

    override fun onBackPressed() {
        if (currentContent?.allowBackPress == true && allowBackPressed()) {
            if (currentContent is HomeFragment) {
                finish()
            } else {
                super.onBackPressed()
            }
        }
    }

    protected open fun allowBackPressed(): Boolean {
        return true
    }

    fun setToolbarTitle(title: String) {
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.title = title
        }
    }

    fun showFragmentClearTop(fragment: BaseFragment, shouldAddToBackStack: Boolean) {
        showFragmentClearTop(fragment, contentContainerId, shouldAddToBackStack)
    }

    @JvmOverloads
    fun showFragmentClearTop(fragment: BaseFragment, containerViewResId: Int = contentContainerId, shouldAddToBackStack: Boolean = false) {
        val fragmentManager = supportFragmentManager
        val fragmentName = fragment.javaClass.name

        val fragmentPopped = fragmentManager.popBackStackImmediate(fragmentName, 0)

        if (!fragmentPopped) { //fragment not in back stack, create it.
            showFragment(fragment, containerViewResId, shouldAddToBackStack)
        }
    }

    fun showFragment(fragment: BaseFragment, shouldAddToBackStack: Boolean) {
        showFragment(fragment, contentContainerId, shouldAddToBackStack)
    }

    @JvmOverloads
    fun showFragment(fragment: BaseFragment, containerViewResId: Int = contentContainerId, shouldAddToBackStack: Boolean = false) {
        val fragmentName = fragment.javaClass.name
        var fragmentByTag: BaseFragment? = supportFragmentManager.findFragmentByTag(fragmentName) as BaseFragment

        fragmentByTag = if (null == fragmentByTag) fragment else fragmentByTag

        if (fragmentByTag.isAdded) {
            return
        }

        val fragmentTransaction = supportFragmentManager.beginTransaction()

        uiUtils!!.setAnimation(fragmentByTag, fragmentTransaction)

        fragmentTransaction.replace(containerViewResId, fragmentByTag, fragmentName)

        if (shouldAddToBackStack) {
            fragmentTransaction.addToBackStack(fragmentName)
        }

        fragmentTransaction.commit()
    }

    override fun finish() {
        super.finish()
        commonUtils!!.closeKeyboard(this, baseView!!.windowToken)
        if (AnimationType.NONE != animationType) {
            uiUtils!!.setAnimation(this, false)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        isAppInForeground = true
        val frag = currentContent
        frag!!.onActivityResult(requestCode, resultCode, data)
    }

}

//    fun showFragment(fragment: BaseFragment, containerViewResId: Int, shouldAddToBackStack: Boolean) {
//        val fragmentTransaction = supportFragmentManager.beginTransaction()
//        val fragmentName = fragment.javaClass.name
//        val fragmentByTag = supportFragmentManager.findFragmentByTag(fragmentName) as BaseFragment
//
//        if (null == fragmentByTag) {
//            fragmentTransaction.replace(containerViewResId, fragment, fragmentName)
//        } else {
//            fragmentTransaction.replace(containerViewResId, fragmentByTag, fragmentName)
//        }
//
//        if (shouldAddToBackStack) {
//            fragmentTransaction.addToBackStack(fragmentName)
//        }
//        fragmentTransaction.commit()
//    }
//}