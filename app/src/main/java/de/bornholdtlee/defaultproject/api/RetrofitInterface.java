package de.bornholdtlee.defaultproject.api;

import de.bornholdtlee.defaultproject.model.QuestionList;
import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitInterface {

    /**
     * Beispielhafter Aufbau eines Endpoints
     */
    @GET("/2.2/questions?order=desc&sort=creation&site=stackoverflow")
    Observable<Response<QuestionList>> loadQuestions(@Query("tagged") String tags);
}