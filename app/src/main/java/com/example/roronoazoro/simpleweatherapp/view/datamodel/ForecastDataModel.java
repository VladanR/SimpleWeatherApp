package com.example.roronoazoro.simpleweatherapp.view.datamodel;

import com.example.roronoazoro.simpleweatherapp.Utils;
import com.example.roronoazoro.simpleweatherapp.model.FiveDaysForecast;
import com.example.roronoazoro.simpleweatherapp.model.TemperatureMeasurement;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ForecastDataModel {

    public long maxTemperature;
    public long minTemperature;
    public String weatherIconUrl;
    public int humidity;
    public int dayOfWeek;
    public String weatherDescription;
    private List<ForecastDataModel> otherDaysMeasurements;

    private ForecastDataModel() {

    }

    public ForecastDataModel(FiveDaysForecast weather) {
        if (weather.getMeasurements() == null || weather.getMeasurements().size() == 0) {
            return;
        }

        maxTemperature = Math.round(weather.getMeasurements().get(0).getMain().getTempMax());
        minTemperature = Math.round(weather.getMeasurements().get(0).getMain().getTempMin());
        humidity = weather.getMeasurements().get(0).getMain().getHumidity();
        weatherDescription = weather.getMeasurements().get(0).getWeather().get(0).getDescription();
        weatherIconUrl = weather.getMeasurements().get(0).getWeather().get(0).getIcon();

        otherDaysMeasurements = new ArrayList<>();
        Date previousDate = Calendar.getInstance().getTime();

        for (TemperatureMeasurement measurement : weather.getMeasurements()) {
            if (!Utils.isSameDay(previousDate, measurement.getDateTime())) {
                ForecastDataModel dataModel = new ForecastDataModel();
                dataModel.setMaxTemperature(Math.round(measurement.getMain().getTempMax()));
                dataModel.setMinTemperature(Math.round(measurement.getMain().getTempMin()));
                dataModel.setDayOfWeek(Utils.getDayOfWeek(measurement.getDateTime()));
                dataModel.setWeatherIconUrl(measurement.getWeather().get(0).getIcon());
                otherDaysMeasurements.add(dataModel);
                previousDate = measurement.getDateTime();
            } else if (otherDaysMeasurements.size() > 0) {
                ForecastDataModel dataModel = otherDaysMeasurements.get(otherDaysMeasurements.size()-1);
                if (dataModel.getMinTemperature() > measurement.getMain().getTempMin()) {
                    dataModel.setMinTemperature(Math.round(measurement.getMain().getTempMin()));
                }

                if (dataModel.getMaxTemperature() < measurement.getMain().getTempMax()) {
                    dataModel.setMaxTemperature(Math.round(measurement.getMain().getTempMax()));
                }
            }
        }
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }

    private void setMaxTemperature(double maxTemperature) {
        this.maxTemperature = Math.round(maxTemperature);
    }

    public double getMinTemperature() {
        return minTemperature;
    }

    private void setMinTemperature(double minTemperature) {
        this.minTemperature = Math.round(minTemperature);
    }

    public String getWeatherIconUrl() {
        return "http://openweathermap.org/img/w/" + weatherIconUrl+ ".png";
    }

    private void setWeatherIconUrl(String url) {
        this.weatherIconUrl = url;
    }

    public int getHumidity() {
        return humidity;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    private void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public List<ForecastDataModel> getOtherDaysMeasurements() {
        return otherDaysMeasurements;
    }
}
