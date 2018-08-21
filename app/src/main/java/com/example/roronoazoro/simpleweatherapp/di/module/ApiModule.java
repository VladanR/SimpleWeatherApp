package com.example.roronoazoro.simpleweatherapp.di.module;

import com.example.roronoazoro.simpleweatherapp.api.ForecastApi;
import com.example.roronoazoro.simpleweatherapp.presenter.ForecastPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    @Singleton
    @Provides
    public Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    public ForecastApi getWeatherApi(Retrofit retrofit) {
        return retrofit.create(ForecastApi.class);
    }

    @Provides
    public ForecastPresenter getPresenter() {
        return new ForecastPresenter();
    }
}
