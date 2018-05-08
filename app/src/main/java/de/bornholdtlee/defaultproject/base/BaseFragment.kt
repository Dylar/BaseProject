package de.bornholdtlee.defaultproject.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import butterknife.ButterKnife
import de.bornholdtlee.defaultproject.enums.AnimationType
import de.bornholdtlee.defaultproject.injection.IBind
import de.bornholdtlee.defaultproject.injection.IInjection
import de.bornholdtlee.defaultproject.viewbuilder.DialogBuilder
import de.bornholdtlee.defaultproject.viewbuilder.ToastBuilder
import javax.inject.Inject

abstract class BaseFragment : Fragment() {

    @Inject
    lateinit var toastBuilder: ToastBuilder

    @Inject
    lateinit var dialogBuilder: DialogBuilder

    protected var toolbar: IToolbarView? = null

    open val animationType: AnimationType = AnimationType.NONE

    abstract val layoutId: Int

    protected open val actionbarCallback: ActionbarHandler.ActionbarCallback = ActionbarHandler.ActionbarCallback()

    protected open val actionbarHandler: ActionbarHandler
        get() = ActionbarHandler(actionbarCallback)

    open val singleInstance: Boolean = true

    open val allowBackPress: Boolean = false

    fun runOnUiThread(runnable: Runnable) {
        activity?.runOnUiThread(runnable)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is IToolbarView) {
            toolbar = context
        }
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
        if (this is IBind) {
            ButterKnife.bind(this, root)
        }

        setHasOptionsMenu(true)
        return root
    }

    override fun onPrepareOptionsMenu(menu: Menu?) {
        actionbarHandler.onPrepareOptionsMenu(menu)
        super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return actionbarHandler.onOptionsItemSelected(item!!.itemId) || super.onOptionsItemSelected(item)
    }


}
