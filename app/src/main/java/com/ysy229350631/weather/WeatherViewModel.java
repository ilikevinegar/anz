package com.ysy229350631.weather;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class WeatherViewModel extends ViewModel {
    private MediatorLiveData<List<WeatherItem>> weatherItemData = new MediatorLiveData<>();
    private FlickrFetchr fetchr = new FlickrFetchr();

    public WeatherViewModel() {
        Log.d("aaaaa","PhotoGalleryViewModel created");
        LiveData<List<CityItem>> cityData = fetchr.fetchCitys();
        weatherItemData.addSource(cityData, cityItems -> {
            for (CityItem cityItem : cityItems) {
                String weaId = cityItem.getWeaid();
                LiveData<List<WeatherItem>> weatherData = fetchr.fetchWeathers(weaId);
                weatherItemData.addSource(weatherData, weatherItems -> {
                    weatherItemData.setValue(weatherItems);
                });
            }
        });
    }

    public LiveData<List<WeatherItem>> getWeatherItemData() {
        return weatherItemData;
    }
}
