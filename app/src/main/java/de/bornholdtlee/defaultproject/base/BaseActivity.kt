package de.bornholdtlee.defaultproject.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.*
import butterknife.ButterKnife
import de.bitb.astroskop.ui.base.NavigationBaseActivity
import de.bornholdtlee.defaultproject.R
import de.bornholdtlee.defaultproject.enums.AnimationType
import de.bornholdtlee.defaultproject.injection.IBind
import de.bornholdtlee.defaultproject.injection.IInjection
import de.bornholdtlee.defaultproject.injection.components.AppComponent
import de.bornholdtlee.defaultproject.ui.main.MainFragment
import de.bornholdtlee.defaultproject.utils.UiUtils
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

    companion object {
        var isAppInForeground: Boolean = false
            private set
    }

    var uiUtils: UiUtils? = null
        @Inject set

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

    internal var currentContent: BaseFragment? = null
        get() = supportFragmentManager.findFragmentById(contentContainerId) as BaseFragment

    var baseView: View? = null
        private set

    open val allowBackPress: Boolean
        get() = false

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
        return actionbarHandler.onPrepareOptionsMenu(menu) || super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return actionbarHandler.onOptionsItemSelected(item.itemId) || super.onOptionsItemSelected(item)
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
        if (currentContent?.allowBackPress == true && allowBackPress) {
            if (currentContent is MainFragment) {
                finish()
            } else {
                super.onBackPressed()
            }
        }
    }

    fun setToolbarTitle(title: String) {
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.title = title
        }
    }

    @JvmOverloads
    fun showFragment(fragment: BaseFragment, containerViewResId: Int = contentContainerId, shouldAddToBackStack: Boolean = false, clearTop: Boolean = false) {
        val fragmentName: String = fragment.javaClass.name

        if (clearTop) { //maybe not functional TODO
            val fragmentPopped: Boolean = fragmentManager.popBackStackImmediate(fragmentName, 0)
            if (fragmentPopped) {
                return
            }
        }

        var fragmentToAdd = fragment
        if (!fragment.singleInstance) {
            fragmentToAdd = supportFragmentManager.findFragmentByTag(fragmentName) as BaseFragment? ?: fragment
        }

        val fragmentTransaction = supportFragmentManager.beginTransaction()

        uiUtils!!.setAnimation(fragmentToAdd, fragmentTransaction)

        fragmentTransaction.replace(containerViewResId, fragmentToAdd, fragmentName)

        if (shouldAddToBackStack) {
            fragmentTransaction.addToBackStack(fragmentName)
        }

        fragmentTransaction.commit()
    }

    override fun finish() {
        super.finish()
        uiUtils!!.closeKeyboard(this, baseView!!)
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
