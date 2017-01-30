package com.example.chahlovkirill.exchangerate.Model;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by chahlov.kirill on 30/01/17.
 */

public class PointItemModel {

    public PointItemModel(LatLng latLng ,String NameBank){
        this.latLng = latLng;
        this.NameBank = NameBank;
    }

    private LatLng latLng;
    private String NameBank;


    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public String getNameBank() {
        return NameBank;
    }

    public void setNameBank(String nameBank) {
        NameBank = nameBank;
    }
}
