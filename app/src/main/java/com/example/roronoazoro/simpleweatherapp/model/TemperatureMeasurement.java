package com.example.roronoazoro.simpleweatherapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class TemperatureMeasurement {

    @SerializedName("dt")
    private long dateTime;

    @SerializedName("main")
    private Main main;

    @SerializedName("weather")
    private List<Weather> weather;

    public Date getDateTime() {
        return new Date(dateTime * 1000);
    }

    public Main getMain() {
        return main;
    }

    public List<Weather> getWeather() {
        return weather;
    }

}