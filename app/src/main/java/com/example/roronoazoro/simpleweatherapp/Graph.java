package com.example.roronoazoro.simpleweatherapp;

import com.example.roronoazoro.simpleweatherapp.di.module.ApiModule;
import com.example.roronoazoro.simpleweatherapp.presenter.ForecastPresenter;
import com.example.roronoazoro.simpleweatherapp.view.fragment.ForecastFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApiModule.class})

public interface Graph {

    void inject(ForecastPresenter presenter);
    void inject(ForecastFragment fragment);

    final class Initializer {
        public static Graph init(SimpleWeatherApp application) {
            return DaggerGraph.builder()
                    .apiModule(new ApiModule())
                    .build();
        }
    }
}
