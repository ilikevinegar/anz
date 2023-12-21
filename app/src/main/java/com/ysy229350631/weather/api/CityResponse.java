package com.ysy229350631.weather.api;

import com.google.gson.annotations.SerializedName;
import com.ysy229350631.weather.CityItem;

import java.util.List;

public class CityResponse {
    @SerializedName("city")
    private List<CityItem> mCityItems;

    public List<CityItem> getCityItems() {
        return mCityItems;
    }

    public void setCityItems(List<CityItem> cityItems) {
        mCityItems = cityItems;
    }
}
