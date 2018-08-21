package com.example.roronoazoro.simpleweatherapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.roronoazoro.simpleweatherapp.view.fragment.ForecastFragment;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final double NIS_LAT = 43.3169868;
    private static final double NIS_LON = 21.8955421;
    private static final double BEL_LAT = 44.7955942;
    private static final double BEL_LON = 20.45557802;
    public static final int LOCATION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        onCurrentLocationClicked();
    }

    private void showWeather(String cityName, double lat, double lon) {
        ForecastFragment fragment = ForecastFragment.newInstance(cityName,lat, lon);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.weather_frame, fragment)
                .commit();
    }

    @OnClick(R.id.nis)
    protected void onNisClicked() {
        showWeather(getString(R.string.nis),NIS_LAT,NIS_LON);
    }

    @OnClick(R.id.belgrade)
    protected void onBelgradeClicked() {
        showWeather(getString(R.string.belgrade),BEL_LAT,BEL_LON);
    }

    @OnClick(R.id.current_location)
    protected void onCurrentLocationClicked() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest
                    .permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST_CODE);
            return;
        }
        FusedLocationProviderClient location = LocationServices.getFusedLocationProviderClient(this);
        location.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    double currentLongitude = location.getLongitude();
                    double currentLatitude = location.getLatitude();
                    Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                    List<Address> addresses = null;
                    try {
                        addresses = geocoder.getFromLocation(currentLatitude,currentLongitude, 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (addresses.size() > 0) {
                        String currentCityName = addresses.get(0).getLocality();
                        showWeather(currentCityName,currentLatitude,currentLongitude);
                    }

                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case LOCATION_REQUEST_CODE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    onCurrentLocationClicked();
                } else {
                    Toast.makeText(this, "Please add location permission", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }
}
