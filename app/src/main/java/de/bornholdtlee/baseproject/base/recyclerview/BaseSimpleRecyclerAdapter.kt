package de.bornholdtlee.baseproject.base.recyclerview

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseSimpleRecyclerAdapter<M, H : BaseViewHolder<M>>(context: Context, private val presenter: IAdapterPresenter<M>) : RecyclerView.Adapter<H>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    protected abstract val layoutId: Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): H {
        val view = inflater.inflate(layoutId, parent, false)
        return newHolder(presenter, view)
    }

    override fun onBindViewHolder(holder: H, position: Int) {
        presenter.onBindAtPosition(holder, position)
    }

    override fun getItemCount(): Int {
        return presenter.adapterItemCount
    }

    protected abstract fun newHolder(presenter: IAdapterPresenter<M>, view: View): H
}