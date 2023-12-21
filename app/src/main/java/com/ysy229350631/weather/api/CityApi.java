package com.ysy229350631.weather.api;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CityApi {
    @GET("?app=weather.city&areaType=cn&" +
            "appkey=71550&" +
            "&sign=a891ee5965ab45df4b6838b61d5d85d8&" +
            "format=json")
    Call<CityResponse> fetchCitys();
}
