package com.ysy229350631.weather;


import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ysy229350631.weather.api.CityApi;
import com.ysy229350631.weather.api.CityResponse;
import com.ysy229350631.weather.api.FlickrResponse;
import com.ysy229350631.weather.api.WeatherApi;
import com.ysy229350631.weather.api.WeatherResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FlickrFetchr {
    private CityApi cityApi;
    private WeatherApi weatherApi;
    public FlickrFetchr() {
        Retrofit cityretrofit = new Retrofit.Builder()
                .baseUrl("https://sapi.k780.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        cityApi = cityretrofit.create(CityApi.class);
        Retrofit weatherretrofit = new Retrofit.Builder()
                .baseUrl("https://sapi.k780.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        weatherApi = weatherretrofit.create(WeatherApi.class);
    }
    public LiveData<List<CityItem>> fetchCitys(){
        MutableLiveData<List<CityItem>> responseLiveData = new MutableLiveData<>();
        Call<CityResponse> cityRequest = cityApi.fetchCitys();
        cityRequest.enqueue(new Callback<CityResponse>() {
            @Override
            public void onResponse(Call<CityResponse> call, Response<CityResponse> response) {
                CityResponse cityResponse = response.body();
                if (cityResponse != null) {
                    List<CityItem> cityItems = cityResponse.getCityItems();
                    if (cityItems != null) {
                        int count = 0;
                        for (CityItem cityItem : cityItems) {
                            if (count >= 10) {
                                break;
                            }
                            String weaId = cityItem.getWeaid();
                            fetchWeathers(weaId);  // 调用fetchWeathers方法获取天气数据
                            count++;
                        }
                        responseLiveData.setValue(cityItems.subList(0, count));
                    }
                }
            }
            @Override
            public void onFailure(Call<CityResponse> call, Throwable t) {
                Log.d("aaaaa", t.getMessage());
            }
        });
        return responseLiveData;
    }

            public LiveData<List<WeatherItem>> fetchWeathers(String weaId){
        MutableLiveData<List<WeatherItem>> responseLiveData = new MutableLiveData<>();
        Call<WeatherResponse> weatherRequest = weatherApi.fetchWeathers(weaId);
        weatherRequest.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                WeatherResponse weatherResponse = response.body();
                List<WeatherItem> weatherItems = weatherResponse.getWeatherItems();
                responseLiveData.setValue(weatherItems);
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Log.d("aaaaa", t.getMessage());
            }
        });
        return responseLiveData;
    }
}
