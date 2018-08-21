package com.example.roronoazoro.simpleweatherapp.view.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.roronoazoro.simpleweatherapp.R;
import com.example.roronoazoro.simpleweatherapp.view.datamodel.ForecastDataModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder> {

    private List<ForecastDataModel> forecast;

    protected static class ForecastViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.weather_icon)
        ImageView weatherIcon;
        @BindView(R.id.day_of_week)
        TextView dayOfWeek;
        @BindView(R.id.max_temperature) TextView maxTemperature;
        @BindView(R.id.min_temperature)  TextView minTemperature;


        private ForecastViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    public ForecastAdapter(List<ForecastDataModel> forecast) {
        this.forecast = forecast;
    }

    @Override
    public ForecastAdapter.ForecastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_five_days_weather,parent, false);
        return new ForecastAdapter.ForecastViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final ForecastAdapter.ForecastViewHolder holder, final int position) {
        int weekDayPosition = forecast.get(position).getDayOfWeek();
        String weekDayName = holder.dayOfWeek.getContext()
                .getResources().getStringArray(R.array.days_of_week)[weekDayPosition];
        holder.dayOfWeek.setText(weekDayName);
        holder.maxTemperature.setText(String.valueOf(forecast.get(position).getMaxTemperature()));
        holder.minTemperature.setText(String.valueOf(forecast.get(position).getMinTemperature()));
        Picasso.with(holder.itemView.getContext())
                .load(forecast.get(position).getWeatherIconUrl())
                .fit()
                .centerCrop()
                .into(holder.weatherIcon);
    }

    @Override
    public int getItemCount() {
        if (forecast == null) {
            return 0;
        }

        return forecast.size();
    }
}