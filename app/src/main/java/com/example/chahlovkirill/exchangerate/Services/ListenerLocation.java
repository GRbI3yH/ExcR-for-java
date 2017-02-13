package com.example.chahlovkirill.exchangerate.Services;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

/**
 * Created by chahlov.kirill on 09/02/17.
 */

public class ListenerLocation implements LocationListener {

    public static Location getImHere() {
        return imHere;
    }

    private static Location imHere;

    private void checkEnabled(LocationManager locationManager) {
        locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }


    public static void SetUpLocationListener(Context context) // это нужно запустить в самом начале работы программы
    {
        LocationManager locationManager = (LocationManager)
                context.getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new ListenerLocation();
//            //lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10, this);
        if (       ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

        }
        else {
            if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                1000,
                10,
                locationListener);
            imHere = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            }
            else if(locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
            locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                1000,
                10,
                locationListener);
            imHere = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            }
        }
    }

//    private void showLocation(Location location) {
//        if (location == null) return;
//        if (location.getProvider().equals(LocationManager.GPS_PROVIDER)) {
//            imHere = location;
//        }
//        else if (location.getProvider().equals(LocationManager.NETWORK_PROVIDER)) {
//            imHere = location;
//        }
//    }

    @Override
    public void onLocationChanged(Location location) {
        imHere = location;
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
