package de.bornholdtlee.baseproject.ui.creation

import com.google.android.gms.maps.model.LatLng
import de.bornholdtlee.baseproject.base.BaseApplication
import de.bornholdtlee.baseproject.base.BasePresenter
import de.bornholdtlee.baseproject.base.recyclerview.IAdapterPresenter
import de.bornholdtlee.baseproject.base.recyclerview.IAdapterView
import de.bornholdtlee.baseproject.controller.LessonController
import de.bornholdtlee.baseproject.controller.OrganizerController
import de.bornholdtlee.baseproject.injection.IInjection
import de.bornholdtlee.baseproject.injection.components.AppComponent
import de.bornholdtlee.baseproject.model.Lesson
import de.bornholdtlee.baseproject.model.Organizer
import javax.inject.Inject

class CreateLessonPresenter(application: BaseApplication, view: ICreateLessonView)
    : BasePresenter<ICreateLessonView>(application, view), IAdapterPresenter<Organizer>, IInjection {

    @Inject
    lateinit var organizerController: OrganizerController
    @Inject
    lateinit var lessonController: LessonController

    private val lesson: Lesson = Lesson()

    override val adapterItemCount: Int
        get() = organizerController.organizerRepository.getAll().size

    override fun inject(appComponent: AppComponent) {
        appComponent.inject(this)
    }

    fun onSetLatLng(latLng: LatLng) {
        lesson.location = latLng
    }

    fun onCreate() {
        getView().initView(lesson)
    }

    fun onSetName(name: String) {
        lesson.name = name
        getView().navigateToDescriptionScreen()
    }

    fun onSetDescription(description: String) {
        lesson.description = description
        getView().navigateToOrganizerScreen()
    }

    override fun onBindAtPosition(holder: IAdapterView<Organizer>, position: Int) {
        holder.bindValues(organizerController.organizerRepository.getAll()[position])
    }

    override fun onItemClicked(model: Organizer) {
        lesson.organizer.add(model)
        lessonController.createLesson(lesson)
        getView().finishCreation()
    }

    override fun onItemLongClicked(model: Organizer) {

    }

}
