package de.bornholdtlee.baseproject.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import butterknife.ButterKnife
import de.bornholdtlee.baseproject.R
import de.bornholdtlee.baseproject.enums.AnimationType
import de.bornholdtlee.baseproject.injection.IInjection
import de.bornholdtlee.baseproject.model.Lesson
import de.bornholdtlee.baseproject.viewbuilder.DialogBuilder
import de.bornholdtlee.baseproject.viewbuilder.ToastBuilder
import javax.inject.Inject

abstract class BaseFragment : Fragment() {

    @Inject
    lateinit var toastBuilder: ToastBuilder

    @Inject
    lateinit var dialogBuilder: DialogBuilder

    var baseActivity: BaseActivity? = null
        get() = activity as BaseActivity

    open val animationType: AnimationType = AnimationType.NONE

    abstract val layoutId: Int

    protected open val actionbarCallback: ActionbarHandler.ActionbarCallback = ActionbarHandler.ActionbarCallback()

    protected open val actionbarHandler: ActionbarHandler
        get() = ActionbarHandler(actionbarCallback)

    open val shouldBindViews: Boolean = true
    open val singleInstance: Boolean = true
    open val allowBackPress: Boolean = true

    open val contentTitle: Int = R.string.app_name

    fun runOnUiThread(runnable: Runnable) {
        activity?.runOnUiThread(runnable)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (this is IInjection) {
            (this as IInjection).inject((activity as BaseActivity).appComponent)
        } else {
            (activity as BaseActivity).appComponent.inject(this)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val root = inflater.inflate(layoutId, null, false)
        if (shouldBindViews) {
            ButterKnife.bind(this, root)
        }

        setHasOptionsMenu(true)
        return root
    }

    override fun onResume() {
        super.onResume()
        baseActivity?.showLoadingIndicator(false)
        baseActivity?.setToolbarTitle(getString(contentTitle))
    }

    override fun onPrepareOptionsMenu(menu: Menu?) {
        actionbarHandler.onPrepareOptionsMenu(menu)
        super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return actionbarHandler.onOptionsItemSelected(item!!.itemId) || super.onOptionsItemSelected(item)
    }

}
