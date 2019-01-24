package de.bornholdtlee.baseproject.base.recyclerview


import android.view.View
import androidx.recyclerview.widget.RecyclerView
import butterknife.ButterKnife

abstract class BaseViewHolder<M>(presenter: IAdapterPresenter<M>, view: View) : RecyclerView.ViewHolder(view), IAdapterView<M> {

    val presenter: IAdapterPresenter<M> = presenter

    init {
        ButterKnife.bind(this, view)
    }

    abstract override fun bindValues(model: M)
}
