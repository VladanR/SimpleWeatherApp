package com.example.roronoazoro.simpleweatherapp.presenter;


import com.example.roronoazoro.simpleweatherapp.SimpleWeatherApp;
import com.example.roronoazoro.simpleweatherapp.api.FiveDaysForecastRequest;
import com.example.roronoazoro.simpleweatherapp.api.ForecastApi;
import com.example.roronoazoro.simpleweatherapp.model.FiveDaysForecast;
import com.example.roronoazoro.simpleweatherapp.view.datamodel.ForecastDataModel;
import com.example.roronoazoro.simpleweatherapp.view.fragment.ForecastFragment;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForecastPresenter {
    private final static String API_KEY = "ff899ff8eae9147b683e4ce1da890925";

    @Inject protected ForecastApi api;
    private ForecastFragment view;
    private double latitude;
    private double longitude;

    public ForecastPresenter() {
        SimpleWeatherApp.graph().inject(this);
    }

    public void setLocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void setView(ForecastFragment view) {
        this.view = view;
    }

    public void getForecast() {
        if (API_KEY.isEmpty()) {
            view.showApiKeyEmptyError();
            return;
        }
        final FiveDaysForecastRequest request = new FiveDaysForecastRequest.Builder()
                .setApiKey(API_KEY)
                .setLatitude(latitude)
                .setLongitude(longitude)
                .setUnits("metric")
                .build();

        Call<FiveDaysForecast> call = api.getFiveDaysForecast(request);
        call.enqueue(new Callback<FiveDaysForecast>() {
            @Override
            public void onResponse(Call<FiveDaysForecast> call, Response<FiveDaysForecast> response) {
                if (view == null) return;

                if (response.isSuccessful()) {
                    FiveDaysForecast forecast = response.body();
                    ForecastDataModel model = new ForecastDataModel(forecast);
                    view.render(model);
                } else {
                    view.showError();
                }
            }

            @Override
            public void onFailure(Call<FiveDaysForecast>call, Throwable t) {
                if (view == null) return;

                view.showError();
            }
        });
    }
}
