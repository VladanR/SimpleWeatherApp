package com.example.roronoazoro.simpleweatherapp.model;

import com.google.gson.annotations.SerializedName;

public class Main {

    @SerializedName("humidity")
    private int humidity;

    @SerializedName("temp_min")
    private double tempMin;

    @SerializedName("temp_max")
    private double tempMax;

    public Integer getHumidity() {
        return humidity;
    }

    public Double getTempMin() {
        return tempMin;
    }

    public Double getTempMax() {
        return tempMax;
    }
}
