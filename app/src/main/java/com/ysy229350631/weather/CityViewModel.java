package com.ysy229350631.weather;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class CityViewModel extends ViewModel {
    private LiveData<List<CityItem>> cityItemData;
    public CityViewModel() {
        Log.d("aaaaa","PhotoGalleryViewModel created");
        this.cityItemData = new FlickrFetchr().fetchCitys();
    }

    public LiveData<List<CityItem>> getCityItemData() {
        return cityItemData;
    }

    public void setCityItemData(LiveData<List<CityItem>> cityItemData) {
        this.cityItemData = cityItemData;
    }
}
