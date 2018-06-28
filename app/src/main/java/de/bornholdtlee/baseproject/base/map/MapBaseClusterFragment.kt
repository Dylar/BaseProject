package de.bornholdtlee.baseproject.base.map

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.View
import butterknife.BindView
import de.bornholdtlee.baseproject.R
import de.bornholdtlee.baseproject.base.BaseFragment
import de.bornholdtlee.baseproject.ui.map.clusteritems.LessonClusterItem
import de.bornholdtlee.baseproject.utils.Logger

class MapBaseClusterFragment : BaseFragment() {

    companion object {
        private const val KEY_DATA_IDS = "keyDataIds"

       fun <CI : BaseClusterItem> createInstance(items: MutableCollection<CI>): MapBaseClusterFragment {
            val frag = MapBaseClusterFragment()

            val ids = ArrayList<Int>()
            for (item in items) {
                ids.add(item.dataId)
            }

            val args = Bundle()
            frag.arguments = args
            args.putIntegerArrayList(KEY_DATA_IDS, ids)
            return frag
        }
    }

    @BindView(R.id.fragment_map_cluster_recyclerview)
    lateinit var recyclerView: RecyclerView

    override val layoutId: Int = R.layout.fragment_map_cluster

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Logger.error("Frag null: ${(parentFragment == null)}")
    }
}