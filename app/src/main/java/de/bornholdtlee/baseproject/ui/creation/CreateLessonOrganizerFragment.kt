package de.bornholdtlee.baseproject.ui.creation

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import butterknife.BindView
import de.bornholdtlee.baseproject.R
import de.bornholdtlee.baseproject.model.Lesson

class CreateLessonOrganizerFragment : CreateLessonBaseFragment() {

    companion object {
        fun createInstance(): CreateLessonOrganizerFragment {
            return CreateLessonOrganizerFragment()
        }
    }

    @BindView(R.id.fragment_create_lesson_organizer_recyclerview)
    lateinit var recyclerView: RecyclerView

    override val layoutId: Int
        get() = R.layout.fragment_create_lesson_organizer

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = SelectOrganizerAdapter(context!!, presenter)
    }

    override fun refreshView(lesson: Lesson) {

    }

}