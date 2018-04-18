package de.bornholdtlee.defaultproject.controller

import javax.inject.Inject

import de.bornholdtlee.defaultproject.DefaultApplication
import de.bornholdtlee.defaultproject.api.RetrofitInterface
import de.bornholdtlee.defaultproject.api.RxConsumer
import de.bornholdtlee.defaultproject.model.QuestionList
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class DefaultController(defaultApplication: DefaultApplication) {

    @Inject
    internal var retrofitInterface: RetrofitInterface? = null

    init {
        defaultApplication.defaultApplicationComponent.inject(this)
    }

    fun startDownload(callback: Callback) {
        retrofitInterface!!.loadQuestions("android")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(object : RxConsumer<QuestionList>() {

                    override fun onSuccess(response: Response<QuestionList>) {
                        callback.onSuccess()
                    }

                    override fun onClientError(response: Response<QuestionList>) {
                        super.onClientError(response)

                        callback.onClientError()
                    }
                })
    }

    interface Callback {

        fun onSuccess()

        fun onClientError()
    }
}
