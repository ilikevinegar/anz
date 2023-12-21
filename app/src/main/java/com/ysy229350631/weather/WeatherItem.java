package com.ysy229350631.weather;

import com.google.gson.annotations.SerializedName;

public class WeatherItem {
    @SerializedName("weaid")
    private String mWeaid;
    @SerializedName("cityid")
    private String mCityid;
    @SerializedName("wtNm")
    private String mWtnm;
    @SerializedName("wtTemp")
    private String mWttemp;
    @SerializedName("wtHumi")
    private String mWthumi;
    @SerializedName("wtAqi")
    private String mAqi;

    @SerializedName("wtTemp1")
    private String mWttemp1;
    @SerializedName("wtTemp2")
    private String mWttemp2;
    @SerializedName("wtNm1")
    private String mWtnm1;
    @SerializedName("wtNm2")
    private String mWtnm2;

    public String getWeaid() {
        return mWeaid;
    }

    public void setWeaid(String weaid) {
        mWeaid = weaid;
    }

    public String getCityid() {
        return mCityid;
    }

    public void setCityid(String cityid) {
        mCityid = cityid;
    }

    public String getWtnm() {
        return mWtnm;
    }

    public void setWtnm(String wtnm) {
        mWtnm = wtnm;
    }

    public String getWttemp() {
        return mWttemp;
    }

    public void setWttemp(String wttemp) {
        mWttemp = wttemp;
    }

    public String getWthumi() {
        return mWthumi;
    }

    public void setWthumi(String wthumi) {
        mWthumi = wthumi;
    }

    public String getAqi() {
        return mAqi;
    }

    public void setAqi(String aqi) {
        mAqi = aqi;
    }

    public String getWttemp1() {
        return mWttemp1;
    }

    public void setWttemp1(String wttemp1) {
        mWttemp1 = wttemp1;
    }

    public String getWttemp2() {
        return mWttemp2;
    }

    public void setWttemp2(String wttemp2) {
        mWttemp2 = wttemp2;
    }

    public String getWtnm1() {
        return mWtnm1;
    }

    public void setWtnm1(String wtnm1) {
        mWtnm1 = wtnm1;
    }

    public String getWtnm2() {
        return mWtnm2;
    }

    public void setWtnm2(String wtnm2) {
        mWtnm2 = wtnm2;
    }
}