package de.bornholdtlee.baseproject.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import butterknife.BindView
import butterknife.ButterKnife
import de.bornholdtlee.baseproject.R
import de.bornholdtlee.baseproject.base.navigation.NavigationBaseActivity
import de.bornholdtlee.baseproject.enums.AnimationType
import de.bornholdtlee.baseproject.injection.IInjection
import de.bornholdtlee.baseproject.injection.components.AppComponent
import de.bornholdtlee.baseproject.ui.main.MainFragment
import de.bornholdtlee.baseproject.utils.UiUtils
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

    companion object {
        var isAppInForeground: Boolean = false
            private set
    }

    @Inject
    lateinit var uiUtils: UiUtils

    @BindView(R.id.activity_base_loading_indicator)
    lateinit var loadingIndicator: View

    open val animationType: AnimationType
        get() = AnimationType.NONE

    val appComponent: AppComponent
        get() = (application as BaseApplication).appComponent

    protected open val layoutId: Int = R.layout.activity_container

    protected open val contentContainerId: Int = R.id.activity_container

    protected open val actionbarCallback: ActionbarHandler.ActionbarCallback
        get() = ActionbarHandler.ActionbarCallback()

    protected open val actionbarHandler: ActionbarHandler
        get() = ActionbarHandler(actionbarCallback)

    var baseView: View? = null
        private set

    open val allowBackPress: Boolean = true
    open val hasToolbar: Boolean = true

    open fun getContext(): Context? {
        return this
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    fun getCurrentContent(): BaseFragment? {
        return supportFragmentManager.findFragmentById(contentContainerId) as BaseFragment?
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

        ButterKnife.bind(this)
    }

    private fun initToolbar(view: View) {
        val toolbar = view.findViewById<Toolbar>(R.id.activity_base_toolbar)
        if (hasToolbar) {
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
        val currentContent: BaseFragment? = getCurrentContent()
        if (currentContent?.allowBackPress == true && allowBackPress) {
            if (currentContent is MainFragment) {
                finish()
            } else {
                super.onBackPressed()
            }
        }
    }

    fun showLoadingIndicator(activate: Boolean) {
        loadingIndicator.visibility = if (activate) VISIBLE else GONE
    }

    fun setToolbarTitle(title: String) {
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.title = title
        }
    }

    @JvmOverloads
    fun showFragment(fragment: BaseFragment,
                     containerViewResId: Int = contentContainerId,
                     shouldAddToBackStack: Boolean = true,
                     clearTop: Boolean = false,
                     replace: Boolean = true) {
        val fragmentName: String = fragment.javaClass.name

        if (clearTop) { //maybe not functional TODO
            val fragmentPopped: Boolean = supportFragmentManager.popBackStackImmediate(fragmentName, 0)
            if (fragmentPopped) {
                return
            }
        }

        var fragmentToAdd = fragment
        if (fragment.singleInstance) {
            fragmentToAdd = supportFragmentManager.findFragmentByTag(fragmentName) as BaseFragment?
                    ?: fragment
        }

        val fragmentTransaction = supportFragmentManager.beginTransaction()

        uiUtils.setAnimation(fragmentToAdd, fragmentTransaction)

        if (replace) {
            fragmentTransaction.replace(containerViewResId, fragmentToAdd, fragmentName)
        } else {
            fragmentTransaction.add(containerViewResId, fragmentToAdd, fragmentName)
        }

        if (shouldAddToBackStack) {
            fragmentTransaction.addToBackStack(fragmentName)
        }

        fragmentTransaction.commit()
    }

    override fun finish() {
        super.finish()
        uiUtils.closeKeyboard(this, baseView!!)
        if (AnimationType.NONE != animationType) {
            uiUtils.setAnimation(this, false)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        isAppInForeground = true
        getCurrentContent()?.onActivityResult(requestCode, resultCode, data)
    }

}
