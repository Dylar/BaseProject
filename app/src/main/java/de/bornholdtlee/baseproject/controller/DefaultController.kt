package de.bornholdtlee.baseproject.controller

import de.bornholdtlee.baseproject.api.RetrofitInterface
import de.bornholdtlee.baseproject.api.ApiRxConsumer
import de.bornholdtlee.baseproject.base.BaseApplication
import de.bornholdtlee.baseproject.injection.IInjection
import de.bornholdtlee.baseproject.injection.components.AppComponent
import de.bornholdtlee.baseproject.model.Organizer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DefaultController(baseApplication: BaseApplication) : BaseController(baseApplication), IInjection {

    @Inject
    lateinit var retrofitInterface: RetrofitInterface

    override fun inject(appComponent: AppComponent) {
        appComponent.inject(this)
    }

    fun startDownload(callback: Callback) {
        retrofitInterface.loadQuestions("android")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : ApiRxConsumer<Organizer>() {

                    override fun onSuccess(response: Organizer?): Boolean {
                        callback.onSuccess()
                        return false
                    }

                    override fun onError(response: Any): Boolean {
                        callback.onClientError()
                        return true
                    }
                })
    }

    interface Callback {

        fun onSuccess()

        fun onClientError()
    }
}
