package de.bornholdtlee.defaultproject.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import butterknife.ButterKnife
import de.bornholdtlee.defaultproject.injection.IBind
import de.bitb.astroskop.injection.IInjection
import de.bitb.astroskop.viewbuilder.DialogBuilder
import de.bitb.astroskop.viewbuilder.ToastBuilder
import de.bornholdtlee.defaultproject.R
import de.bornholdtlee.defaultproject.enums.AnimationType

import javax.inject.Inject

abstract class BaseFragment : Fragment() {

    @Inject
    protected var toastBuilder: ToastBuilder? = null

    @Inject
    protected var dialogBuilder: DialogBuilder? = null

    protected var toolbar: IToolbarView? = null

    protected open val animationType: AnimationType
        get() = AnimationType.NONE

    protected open val layoutId: Int
        get() = R.layout.fragment_base

    protected open val actionbarCallback: ActionbarHandler.ActionbarCallback
        get() = ActionbarHandler.ActionbarCallback()

    protected open val actionbarHandler: ActionbarHandler
        get() = ActionbarHandler(actionbarCallback)

    protected open var allowBackPress: Boolean = false
        get() = false

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
        if (this is IInjection) {
            (this as IInjection).inject((activity as BaseActivity).appComponent)
        } else {
            (activity as BaseActivity).appComponent.inject(this)
        }
        super.onCreate(savedInstanceState)
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
