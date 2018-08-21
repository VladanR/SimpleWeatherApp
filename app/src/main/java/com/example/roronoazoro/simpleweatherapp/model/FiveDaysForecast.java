package com.example.roronoazoro.simpleweatherapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FiveDaysForecast {

    @SerializedName("list")
    private List<TemperatureMeasurement> measurements;

    public List<TemperatureMeasurement> getMeasurements() {
        return measurements;
    }
}

