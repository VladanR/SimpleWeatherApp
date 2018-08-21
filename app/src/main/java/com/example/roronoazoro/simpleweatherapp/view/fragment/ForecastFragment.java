package com.example.roronoazoro.simpleweatherapp.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.roronoazoro.simpleweatherapp.R;
import com.example.roronoazoro.simpleweatherapp.SimpleWeatherApp;
import com.example.roronoazoro.simpleweatherapp.presenter.ForecastPresenter;
import com.example.roronoazoro.simpleweatherapp.view.adapter.ForecastAdapter;
import com.example.roronoazoro.simpleweatherapp.view.datamodel.ForecastDataModel;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForecastFragment extends Fragment {

    private static final String LAT_KEY = "lat";
    private static final String LON_KEY = "lon";
    public static final String CITY_NAME = "city";

    @Inject
    protected ForecastPresenter presenter;

    @BindView(R.id.five_days_forecast)
    RecyclerView fiveDaysForecast;
    @BindView(R.id.min_temp)
    TextView minTemp;
    @BindView(R.id.max_temp) TextView maxTemp;
    @BindView(R.id.icon_weather)
    ImageView weatherIcon;
    @BindView(R.id.weather_description) TextView weatherDescription;
    @BindView(R.id.city_name) TextView cityName;
    @BindView(R.id.humidity) TextView humidity;
    private String city;

    public static ForecastFragment newInstance(String cityName, double latitude, double longitude) {
        ForecastFragment forecastFragment = new ForecastFragment();
        Bundle bundle = new Bundle();
        bundle.putDouble(LAT_KEY, latitude);
        bundle.putDouble(LON_KEY, longitude);
        bundle.putString(CITY_NAME, cityName);
        forecastFragment.setArguments(bundle);
        return forecastFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_current_weather, container, false);
        ButterKnife.bind(this, view);
        SimpleWeatherApp.graph().inject(this);
        double lat = getArguments().getDouble(LAT_KEY, 0);
        double lon = getArguments().getDouble(LON_KEY, 0);
        city = getArguments().getString(CITY_NAME);
        presenter.setView(this);
        presenter.setLocation(lat, lon);
        presenter.getForecast();
        return view;
    }

    public void showApiKeyEmptyError() {
        Toast.makeText(getActivity(), "API key can't be empty", Toast.LENGTH_LONG).show();
    }

    public void render(ForecastDataModel forecast) {
        cityName.setText(city);
        maxTemp.setText(getString(R.string.max_temp) + String.valueOf(forecast.getMaxTemperature()));
        minTemp.setText(getString(R.string.min_temp) + String.valueOf(forecast.getMinTemperature()));
        humidity.setText(getString(R.string.humidity) + String.valueOf(forecast.getHumidity()));
        Picasso.with(getContext())
                .load(forecast.getWeatherIconUrl())
                .fit()
                .centerCrop()
                .into(weatherIcon);

        fiveDaysForecast.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false));
        ForecastAdapter adapter = new ForecastAdapter(forecast.getOtherDaysMeasurements());
        fiveDaysForecast.setAdapter(adapter);
    }

    public void showError() {
        Toast.makeText(getActivity(), "There was an error with API", Toast.LENGTH_LONG).show();
    }
}