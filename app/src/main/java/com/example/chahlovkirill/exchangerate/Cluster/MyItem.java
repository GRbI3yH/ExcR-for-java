package com.example.chahlovkirill.exchangerate.Cluster;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

/**
 * Created by chahlov.kirill on 30/01/17.
 */

public class MyItem implements ClusterItem {
    private final LatLng mPosition;

    public MyItem(double lat,double lng){
        mPosition = new LatLng(lat,lng);
    }

    @Override
    public LatLng getPosition() {
        return mPosition;
    }
}
