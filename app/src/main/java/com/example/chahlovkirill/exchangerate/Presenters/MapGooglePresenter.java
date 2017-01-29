package com.example.chahlovkirill.exchangerate.Presenters;

import android.content.Context;
import android.util.Log;

import com.example.chahlovkirill.exchangerate.Activity.MapGoogleActivity;
import com.example.chahlovkirill.exchangerate.AppSetting.Setting;
import com.example.chahlovkirill.exchangerate.Model.BankModel;
import com.example.chahlovkirill.exchangerate.Model.CityModel;
import com.example.chahlovkirill.exchangerate.Model.Gis2Model.Gis2Model;
import com.example.chahlovkirill.exchangerate.Model.Gis2Model.Result;
import com.example.chahlovkirill.exchangerate.Services.DataService;
import com.example.chahlovkirill.exchangerate.Services.IControlListener;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chahlov.kirill on 27/01/17.
 */

public class MapGooglePresenter implements IControlListener {

    private List<Gis2Model> gis2List;
    private Context context;

    public MapGooglePresenter(Context context){
        this.context = context;
    }

    public List<LatLng> getPositionBanks(){

        List<LatLng> position = new ArrayList<LatLng>();

        for (Gis2Model gis2 : gis2List) {
//            for (Result gis2Result : gis2.getresult() ) {
//                position.add(new LatLng(Double.valueOf(gis2Result.getLat()),Double.valueOf(gis2Result.getLon())));
//                //map.put( Double.valueOf(gis2Result.getLat()), Double.valueOf(gis2Result.getLon()));
//            }
        }

        return position;
    }

    public boolean DownloadModelOfServices(){

        //DATASERVISE <-------<
        String selectCities = Setting.getselectCity(context);
        String selectBank = Setting.getselectBank(context);

        DataService.getInstance().addListener(this);
        DataService.getInstance().Gis2DataSearchDownload(selectBank, "Москва");

        return true;
    }

    @Override
    public void onGis2DataSearchDownload(List<Gis2Model> Gis2) {
        this.gis2List = Gis2;
        MapGoogleActivity mapGoogleActivity = (MapGoogleActivity) context;

        mapGoogleActivity.renderMarkers (getPositionBanks());
    }

    @Override
    public void onCitiesDownloaded(List<CityModel> cities) {

    }

    @Override
    public void onBanksDownloaded(List<BankModel> banks) {

    }
}
