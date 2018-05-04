package de.bornholdtlee.defaultproject.controller

import de.bornholdtlee.defaultproject.base.BaseApplication
import de.bornholdtlee.defaultproject.api.RetrofitInterface
import javax.inject.Inject

class DefaultController(baseApplication: BaseApplication) {

    var retrofitInterface: RetrofitInterface? = null
        @Inject set

    init {
        baseApplication.appComponent.inject(this)
    }

    fun startDownload(callback: Callback) {
//        retrofitInterface!!.loadQuestions("android")
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe(object : RxConsumer<QuestionList>() {
//
//                    override fun onSuccess(response: Response<QuestionList>) {
//                        callback.onSuccess()
//                    }
//
//                    override fun onClientError(response: Response<QuestionList>) {
//                        super.onClientError(response)
//
//                        callback.onClientError()
//                    }
//                })
    }

    interface Callback {

        fun onSuccess()

        fun onClientError()
    }
}
