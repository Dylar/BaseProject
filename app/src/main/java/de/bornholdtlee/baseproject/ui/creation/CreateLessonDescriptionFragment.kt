package de.bornholdtlee.baseproject.ui.creation

import android.widget.EditText
import butterknife.BindView
import butterknife.OnClick
import de.bornholdtlee.baseproject.R
import de.bornholdtlee.baseproject.database.room.LessonData
import de.bornholdtlee.baseproject.model.Lesson

class CreateLessonDescriptionFragment : CreateLessonBaseFragment() {

    companion object {
        fun createInstance(): CreateLessonDescriptionFragment {
            return CreateLessonDescriptionFragment()
        }
    }

    @BindView(R.id.fragment_create_lesson_description)
    lateinit var descriptionET: EditText

    override val layoutId: Int
        get() = R.layout.fragment_create_lesson_description

    override fun refreshView(lesson: LessonData) {
        descriptionET.setText(lesson.description)
    }

    @OnClick(R.id.fragment_create_lesson_description_btn_next)
    fun onNextClicked() {
        presenter.onSetDescription(descriptionET.text.toString())
    }
}