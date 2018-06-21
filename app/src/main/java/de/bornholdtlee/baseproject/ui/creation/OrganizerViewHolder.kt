package de.bornholdtlee.baseproject.ui.creation

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import de.bornholdtlee.baseproject.R
import de.bornholdtlee.baseproject.base.recyclerview.BaseViewHolder
import de.bornholdtlee.baseproject.base.recyclerview.IAdapterPresenter
import de.bornholdtlee.baseproject.model.Organizer

class OrganizerViewHolder(presenter: IAdapterPresenter<Organizer>, view: View) : BaseViewHolder<Organizer>(presenter, view) {

    @BindView(R.id.adapter_select_organizer_image)
    lateinit var imageIV: ImageView

    @BindView(R.id.adapter_select_organizer_name)
    lateinit var nameTV: TextView

    override fun bindValues(model: Organizer) {
        imageIV.setImageResource(model.image)
        nameTV.text = model.name
        itemView.setOnClickListener({ presenter.onItemClicked(model) })
    }
}