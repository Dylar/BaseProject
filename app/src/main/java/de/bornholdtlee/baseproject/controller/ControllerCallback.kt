package de.bornholdtlee.baseproject.controller

open class ControllerCallback<O> {
    open fun onSuccess(response: List<O>?) {
        //implement if needed
        if (response == null || response.isEmpty()) {
            onSuccess()
        } else {
            onSuccess(response[0])
        }
    }

    open fun onSuccess(response: O) {
        //implement if needed
        onSuccess()
    }

    open fun onSuccess() {
        //implement if needed
    }

    open fun onFailure() {
        //implement if needed
    }

}
