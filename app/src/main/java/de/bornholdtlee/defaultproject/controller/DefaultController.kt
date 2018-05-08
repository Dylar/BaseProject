package de.bornholdtlee.defaultproject.controller

import de.bornholdtlee.defaultproject.api.RetrofitInterface
import de.bornholdtlee.defaultproject.api.ApiRxConsumer
import de.bornholdtlee.defaultproject.base.BaseApplication
import de.bornholdtlee.defaultproject.model.QuestionList
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

class DefaultController(baseApplication: BaseApplication) {

    @Inject
    lateinit var retrofitInterface: RetrofitInterface

    init {
        baseApplication.appComponent.inject(this)
    }

    fun startDownload(callback: Callback) {
        retrofitInterface.loadQuestions("android")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : ApiRxConsumer<QuestionList>() {

                    override fun onSuccess(response: QuestionList?): Boolean {
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
