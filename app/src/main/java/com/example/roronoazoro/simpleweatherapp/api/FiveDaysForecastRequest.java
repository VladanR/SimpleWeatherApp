package com.example.roronoazoro.simpleweatherapp.api;
import java.util.HashMap;

public class FiveDaysForecastRequest extends HashMap<String, Object> {

    private static final String API_KEY = "APPID";
    private static final String LAT = "lat";
    private static final String LON = "lon";
    private static final String UNITS = "units";

    private FiveDaysForecastRequest() { super(); }

    private void setApiKey(String apiKey) {
        this.put(API_KEY, apiKey);
    }

    private void setLat(double latitude) {
        this.put(LAT, latitude);
    }

    private void setLon(double longitude) {
        this.put(LON, longitude);
    }
    private void setUnits(String units) {
        this.put(UNITS, units);
    }

    public static class Builder {

        private String apiKey;
        private double latitude;
        private double longitude;
        private String units;

        public Builder() {}

        public Builder setApiKey(String apiKey) {
            this.apiKey = apiKey;
            return this;
        }

        public Builder setLatitude(double latitude) {
            this.latitude = latitude;
            return this;
        }

        public Builder setLongitude(double longitude) {
            this.longitude = longitude;
            return this;
        }

        public Builder setUnits(String units) {
            this.units = units;
            return this;
        }

        public FiveDaysForecastRequest build() {
            if (apiKey == null) {
                throw new NullPointerException("API key is invalid");
            }

            FiveDaysForecastRequest request = new FiveDaysForecastRequest();
            request.setApiKey(this.apiKey);
            request.setLat(this.latitude);
            request.setLon(this.longitude);
            request.setUnits(this.units);
            return request;
        }
    }
}