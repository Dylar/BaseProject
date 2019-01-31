package de.bornholdtlee.baseproject.ui.creation

import android.text.TextUtils
import android.widget.EditText
import butterknife.BindView
import butterknife.OnClick
import de.bornholdtlee.baseproject.R
import de.bornholdtlee.baseproject.database.room.LessonData
import de.bornholdtlee.baseproject.model.Lesson

class CreateLessonNameFragment : CreateLessonBaseFragment() {

    companion object {
        fun createInstance(): CreateLessonNameFragment {
            return CreateLessonNameFragment()
        }
    }

    @BindView(R.id.fragment_create_lesson_name)
    lateinit var nameET: EditText

    override val layoutId: Int
        get() = R.layout.fragment_create_lesson_start

    override fun refreshView(lesson: LessonData) {
        nameET.setText(lesson.name)
    }

    @OnClick(R.id.fragment_create_lesson_btn_next)
    fun onNextClicked() {
        if (TextUtils.isEmpty(nameET.text)) {
            nameET.error = getString(R.string.fragment_create_lesson_name_error)
        } else {
            presenter.onSetName(nameET.text.toString())
        }
    }
}