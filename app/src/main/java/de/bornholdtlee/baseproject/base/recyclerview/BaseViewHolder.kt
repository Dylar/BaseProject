package de.bornholdtlee.baseproject.base.recyclerview


import android.support.v7.widget.RecyclerView
import android.view.View
import butterknife.ButterKnife

abstract class BaseViewHolder<M>(presenter: IAdapterPresenter<M>, view: View) : RecyclerView.ViewHolder(view), IAdapterView<M> {

    val presenter: IAdapterPresenter<M> = presenter

    init {
        ButterKnife.bind(this, view)
    }

    abstract override fun bindValues(model: M)
}
