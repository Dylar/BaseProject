package de.bornholdtlee.baseproject.base.recyclerview

interface IAdapterPresenter<M> {

    val adapterItemCount: Int

    fun onBindAtPosition(holder: IAdapterView<M>, position: Int)

    fun onItemClicked(model: M)

    fun onItemLongClicked(model: M)
}
