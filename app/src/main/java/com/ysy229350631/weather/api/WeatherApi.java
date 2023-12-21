package com.ysy229350631.weather.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {
    @GET("?app=weather.realtime&" +
            "ag=today,futureDay,lifeIndex,futureHour&" +
            "appkey=71550&" +
            "sign=a891ee5965ab45df4b6838b61d5d85d8&" +
            "format=json")
    Call<WeatherResponse> fetchWeathers(@Query("weaId") String weaId);
}
