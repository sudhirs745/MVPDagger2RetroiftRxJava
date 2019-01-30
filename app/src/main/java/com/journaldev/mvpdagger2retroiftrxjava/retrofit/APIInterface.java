package com.journaldev.mvpdagger2retroiftrxjava.retrofit;

import com.google.gson.JsonObject;
import com.journaldev.mvpdagger2retroiftrxjava.pojo.CryptoData;
import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface APIInterface {

    @GET("ticker/?")
    Observable<List<CryptoData>> getData(@Query("limit") String limit);

    @Headers("Content-Type: application/json")
    @POST("forgotPassword")
    Observable<JsonObject> getforgotDetails(@Body JsonObject body);

}

