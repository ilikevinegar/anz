package com.ysy229350631.weather.api;

public class FlickrResponse {
    private CityResponse citys;
    private WeatherResponse weathers;

    public CityResponse getCitys() {
        return citys;
    }

    public void setCitys(CityResponse citys) {
        this.citys = citys;
    }

    public WeatherResponse getWeathers() {
        return weathers;
    }

    public void setWeathers(WeatherResponse weathers) {
        this.weathers = weathers;
    }
}
