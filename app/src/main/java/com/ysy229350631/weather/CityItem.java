package com.ysy229350631.weather;

import com.google.gson.annotations.SerializedName;

public class CityItem {
    @SerializedName("weaId")
    private String mWeaid;
    @SerializedName("cityNm")
    private String mCitynm;
    @SerializedName("cityId")
    private String mCityid;
    @SerializedName("areaNm_1")
    private String mAreanm_1;
    @SerializedName("areaNm_2")
    private String mAreanm_2;

    public String getWeaid() {
        return mWeaid;
    }

    public void setWeaid(String weaid) {
        mWeaid = weaid;
    }

    public String getCitynm() {
        return mCitynm;
    }

    public void setCitynm(String citynm) {
        mCitynm = citynm;
    }

    public String getCityid() {
        return mCityid;
    }

    public void setCityid(String cityid) {
        mCityid = cityid;
    }

    public String getAreanm_1() {
        return mAreanm_1;
    }

    public void setAreanm_1(String areanm_1) {
        mAreanm_1 = areanm_1;
    }

    public String getAreanm_2() {
        return mAreanm_2;
    }

    public void setAreanm_2(String areanm_2) {
        mAreanm_2 = areanm_2;
    }
}