package de.bornholdtlee.defaultproject.api;

import java.net.HttpURLConnection;

import io.reactivex.functions.Consumer;
import retrofit2.Response;

public abstract class RxConsumer<T> implements Consumer<Response<T>> {

    private static final int STATUS_SUCCESS = 200;
    private static final int STATUS_REDIRECT = 300;
    private static final int STATUS_CLIENT_ERROR = 400;
    private static final int STATUS_SERVER_ERROR = 500;
    private static final int STATUS_UNKNOWN = 600;

    @Override
    public void accept(Response<T> response) {
        int code = response.code();

        if (code >= STATUS_SUCCESS && code < STATUS_REDIRECT) {
            onSuccess(response);
            processSuccess(response);
        } else if (code >= STATUS_CLIENT_ERROR && code < STATUS_SERVER_ERROR) {
            onClientError(response);
            processClientError(response);
        } else if (code >= STATUS_SERVER_ERROR && code < STATUS_UNKNOWN) {
            onServerError(response);
            processServerError(response);
        }
    }

    private void processSuccess(Response<T> response) {
        switch (response.code()) {
            case HttpURLConnection.HTTP_OK:
                onOk(response);
                break;
            case HttpURLConnection.HTTP_CREATED:
                onCreated(response);
                break;
            case HttpURLConnection.HTTP_ACCEPTED:
                onAccepted(response);
                break;
        }
    }

    private void processClientError(Response<T> response) {
        switch (response.code()) {
            case HttpURLConnection.HTTP_BAD_REQUEST:
                onBadRequest(response);
                break;
            case HttpURLConnection.HTTP_UNAUTHORIZED:
                onUnauthorized(response);
                break;
            case HttpURLConnection.HTTP_FORBIDDEN:
                onForbidden(response);
                break;
            case HttpURLConnection.HTTP_NOT_FOUND:
                onNotFound(response);
                break;
        }
    }

    private void processServerError(Response<T> response) {
        switch (response.code()) {
            case HttpURLConnection.HTTP_INTERNAL_ERROR:
                onInternalError(response);
                break;
            case HttpURLConnection.HTTP_BAD_GATEWAY:
                onBadGateway(response);
                break;
            case HttpURLConnection.HTTP_UNAVAILABLE:
                onUnavailable(response);
                break;
            case HttpURLConnection.HTTP_GATEWAY_TIMEOUT:
                onGatewayTimeout(response);
                break;
        }
    }


    /**
     * status 200 - 299
     */
    public abstract void onSuccess(Response<T> response);

    /**
     * status 400 - 499
     */
    public void onClientError(Response<T> response) {
        // do nothing
    }

    /**
     * status 500 - 599
     */
    public void onServerError(Response<T> response) {
        // do nothing
    }

    // # # # # # SUCCESS

    /**
     * Http-Status 200
     */
    public void onOk(Response<T> response) {
        // do nothing
    }

    /**
     * Http-Status 202
     */
    public void onAccepted(Response<T> response) {
        // do nothing
    }

    /**
     * Http-Status 201
     */
    public void onCreated(Response<T> response) {
        // do nothing
    }

    // # # # # # CLIENT ERROR

    /**
     * Http-Status 404
     */
    public void onNotFound(Response<T> response) {
        // do nothing
    }

    /**
     * Http-Status 403
     */
    public void onForbidden(Response<T> response) {
        // do nothing
    }

    /**
     * Http-Status 401
     */
    public void onUnauthorized(Response<T> response) {
        // do nothing
    }

    /**
     * Http-Status 400
     */
    public void onBadRequest(Response<T> response) {
        // do nothing
    }


    // # # # # # SERVER ERROR

    /**
     * Http-Status 500
     */
    public void onInternalError(Response<T> response) {
        // do nothing
    }

    /**
     * Http-Status 502
     */
    public void onBadGateway(Response<T> response) {
        // do nothing
    }

    /**
     * Http-Status 503
     */
    public void onUnavailable(Response<T> response) {
        // do nothing
    }

    /**
     * Http-Status 504
     */
    public void onGatewayTimeout(Response<T> response) {
        // do nothing
    }
}
