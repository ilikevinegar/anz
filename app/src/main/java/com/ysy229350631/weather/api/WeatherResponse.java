package com.ysy229350631.weather.api;

import com.google.gson.annotations.SerializedName;
import com.ysy229350631.weather.WeatherItem;

import java.util.List;

public class WeatherResponse {
    @SerializedName("weather")
    private List<WeatherItem> mWeatherItems;

    public List<WeatherItem> getWeatherItems() {
        return mWeatherItems;
    }

    public void setWeatherItems(List<WeatherItem> weatherItems) {
        mWeatherItems = weatherItems;
    }
}
