package de.bornholdtlee.defaultproject.api

import io.reactivex.functions.Consumer
import retrofit2.Response
import java.net.HttpURLConnection

abstract class ApiRxConsumer<T> : Consumer<Response<T>> {

    companion object {
        private const val STATUS_SUCCESS = 200
        private const val STATUS_REDIRECT = 300
        private const val STATUS_CLIENT_ERROR = 400
        private const val STATUS_SERVER_ERROR = 500
        private const val STATUS_UNKNOWN = 600
    }

    override fun accept(response: Response<T>) {
        val statusCode = response.code()
        val handled = when (statusCode) {
            in STATUS_SUCCESS..(STATUS_REDIRECT - 1) -> processSuccess(response, statusCode)
            in STATUS_REDIRECT..(STATUS_CLIENT_ERROR - 1) -> processRedirect(response, statusCode)
            in STATUS_CLIENT_ERROR..(STATUS_SERVER_ERROR - 1) -> processClientError(response, statusCode)
            in STATUS_SERVER_ERROR..(STATUS_UNKNOWN - 1) -> processServerError(response, statusCode)
            else -> onUnknownStatusCode(response)
        }

        if (!handled) {
            onError(response)
        }
    }

    private fun processSuccess(response: Response<T>, statusCode: Int): Boolean {
        return when (statusCode) {
            HttpURLConnection.HTTP_OK -> onSuccess(response.body())
            HttpURLConnection.HTTP_CREATED -> onCreated(response.body())
            HttpURLConnection.HTTP_ACCEPTED -> onAccepted(response.body())
            else -> onUnknownSuccess(response)
        }
    }

    private fun processRedirect(response: Response<T>, statusCode: Int): Boolean {
        return onRedirect(response)
    }

    private fun processClientError(response: Response<T>, statusCode: Int): Boolean {
        val handled: Boolean = when (statusCode) {
            HttpURLConnection.HTTP_BAD_REQUEST -> onBadRequest(response)
            HttpURLConnection.HTTP_UNAUTHORIZED -> onUnauthorized(response)
            HttpURLConnection.HTTP_FORBIDDEN -> onForbidden(response)
            HttpURLConnection.HTTP_NOT_FOUND -> onNotFound(response)
            HttpURLConnection.HTTP_CONFLICT -> onConflict(response)
            else -> onUnknownClientError(response)
        }

        return !handled && onClientError(response)
    }

    private fun processServerError(response: Response<T>, statusCode: Int): Boolean {
        val handled: Boolean = when (statusCode) {
            HttpURLConnection.HTTP_INTERNAL_ERROR -> onInternalError(response)
            HttpURLConnection.HTTP_BAD_GATEWAY -> onBadGateway(response)
            HttpURLConnection.HTTP_UNAVAILABLE -> onUnavailable(response)
            HttpURLConnection.HTTP_GATEWAY_TIMEOUT -> onGatewayTimeout(response)
            else -> onUnknownServerError(response)
        }

        return !handled && onServerError(response)
    }

    // # # # # # SUCCESS

    /**
     * Http-Status 200
     */
    open fun onSuccess(response: T?): Boolean {
        return false
    }

    /**
     * Http-Status 202
     */
    open fun onAccepted(body: T?): Boolean {
        return false
    }

    /**
     * Http-Status 201
     */
    open fun onCreated(body: T?): Boolean {
        return false
    }

    /**
     * Http-Status in range of 203 to 299
     */
    open fun onUnknownSuccess(response: Response<T>): Boolean {
        return false
    }

    // # # # # # REDIRECT

    /**
     * Http-Status in range of 300 to 399
     */
    open fun onRedirect(response: Response<T>): Boolean {
        return false
    }

    // # # # # # CLIENT ERROR

    /**
     * Http-Status in range of 400 to 499
     */
    open fun onClientError(response: Response<T>): Boolean {
        return false
    }

    /**
     * Http-Status 402 and in range from 405 to 499
     */
    open fun onUnknownClientError(response: Response<T>): Boolean {
        return false
    }

    /**
     * Http-Status 409
     */
    open fun onConflict(response: Response<T>): Boolean {
        return false
    }

    /**
     * Http-Status 404
     */
    open fun onNotFound(response: Response<T>): Boolean {
        return false
    }

    /**
     * Http-Status 403
     */
    open fun onForbidden(response: Response<T>): Boolean {
        return false
    }

    /**
     * Http-Status 401
     */
    open fun onUnauthorized(response: Response<T>): Boolean {
        return false
    }

    /**
     * Http-Status 400
     */
    open fun onBadRequest(response: Response<T>): Boolean {
        return false
    }

    // # # # # # SERVER ERROR

    /**
     * Http-Status 500 to 599
     */
    open fun onServerError(response: Response<T>): Boolean {
        return false
    }

    /**
     * Http-Status 500
     */
    open fun onInternalError(response: Response<T>): Boolean {
        return false
    }

    /**
     * Http-Status 502
     */
    open fun onBadGateway(response: Response<T>): Boolean {
        return false
    }

    /**
     * Http-Status 503
     */
    open fun onUnavailable(response: Response<T>): Boolean {
        return false
    }

    /**
     * Http-Status 504
     */
    open fun onGatewayTimeout(response: Response<T>): Boolean {
        return false
    }

    /**
     * Http-Status 501 and in range of 505 to 599
     */
    open fun onUnknownServerError(response: Response<T>): Boolean {
        return false
    }

    // # # # # # OTHER

    /**
     * Http-Status from 0 to 199 and 600 to x
     */
    open fun onUnknownStatusCode(response: Response<T>): Boolean {
        return false
    }

    // # # # # # ERROR

    /**
     * Called when response not handled
     */
    open fun onError(response: Any): Boolean {
        return false
    }

}
