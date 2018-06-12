package de.bornholdtlee.baseproject.ui.creation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.google.android.gms.maps.model.LatLng
import de.bornholdtlee.baseproject.base.BaseActivity
import de.bornholdtlee.baseproject.base.BaseApplication
import de.bornholdtlee.baseproject.base.mvp.MVPActivity
import de.bornholdtlee.baseproject.model.Lesson

class CreateLessonActivity : MVPActivity<ICreateLessonView, CreateLessonPresenter>(), ICreateLessonView {

    companion object {
        const val KEY_LATLNG = "keyLatLng"
        const val REQUEST_CODE = 555

        fun startActivity(baseActivity: BaseActivity, latLng: LatLng) {
            val intent = Intent(baseActivity, CreateLessonActivity::class.java)
            intent.putExtra(KEY_LATLNG, latLng)
            baseActivity.startActivityForResult(intent, REQUEST_CODE)
        }
    }

    override fun createPresenter(application: BaseApplication): CreateLessonPresenter {
        return CreateLessonPresenter(application, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val latLng = intent.extras.getParcelable<LatLng>(KEY_LATLNG)
        presenter.onSetLatLng(latLng)
        showFragment(CreateLessonNameFragment.createInstance())
    }

    override fun initView(lesson: Lesson) {
        val frag = getCurrentContent()
        if (frag is CreateLessonBaseFragment) {
            frag.refreshView(lesson)
        }
    }

    override fun navigateToDescriptionScreen() {
        showFragment(CreateLessonDescriptionFragment.createInstance())
    }

    override fun navigateToOrganizerScreen() {
        showFragment(CreateLessonOrganizerFragment.createInstance())
    }

    override fun finishCreation() {
        setResult(Activity.RESULT_OK)
        finish()
    }
}
