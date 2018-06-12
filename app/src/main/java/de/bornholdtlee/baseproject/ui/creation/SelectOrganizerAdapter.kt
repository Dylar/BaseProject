package de.bornholdtlee.baseproject.ui.creation

import android.content.Context
import android.view.View
import de.bornholdtlee.baseproject.R
import de.bornholdtlee.baseproject.base.recyclerview.BaseSimpleRecyclerAdapter
import de.bornholdtlee.baseproject.base.recyclerview.IAdapterPresenter
import de.bornholdtlee.baseproject.model.Organizer

class SelectOrganizerAdapter(context: Context, presenter: IAdapterPresenter<Organizer>) : BaseSimpleRecyclerAdapter<Organizer, OrganizerViewHolder>(context, presenter) {
    override val layoutId: Int = R.layout.adapter_select_organizer

    override fun newHolder(presenter: IAdapterPresenter<Organizer>, view: View): OrganizerViewHolder {
        return OrganizerViewHolder(presenter, view)
    }
}