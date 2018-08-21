package com.example.roronoazoro.simpleweatherapp;

import android.app.Application;

public class SimpleWeatherApp extends Application {

    private static Graph graph;
    private static SimpleWeatherApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        graph = Graph.Initializer.init(this);
    }

    public static Graph graph() {
        return graph;
    }

    public static SimpleWeatherApp getInstance() {
        return instance;
    }

    public void initGraph() {
        graph = Graph.Initializer.init(this);
    }
}