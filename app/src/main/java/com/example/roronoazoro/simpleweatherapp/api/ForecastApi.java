package com.example.roronoazoro.simpleweatherapp.api;

import com.example.roronoazoro.simpleweatherapp.model.FiveDaysForecast;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface ForecastApi {

    @GET("forecast")
    Call<FiveDaysForecast> getFiveDaysForecast(@QueryMap FiveDaysForecastRequest request);
}
