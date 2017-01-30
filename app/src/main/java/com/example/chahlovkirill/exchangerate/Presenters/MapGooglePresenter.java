package com.example.chahlovkirill.exchangerate.Presenters;

import android.content.Context;
import android.util.Log;

import com.example.chahlovkirill.exchangerate.Activity.MapGoogleActivity;
import com.example.chahlovkirill.exchangerate.AppSetting.Setting;
import com.example.chahlovkirill.exchangerate.Model.BankModel;
import com.example.chahlovkirill.exchangerate.Model.CityModel;
import com.example.chahlovkirill.exchangerate.Model.Gis2Model.Gis2Model;
import com.example.chahlovkirill.exchangerate.Model.Gis2Model.Result;
import com.example.chahlovkirill.exchangerate.Model.PointItemModel;
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

    private Gis2Model gis2Model;
    private Context context;

    public MapGooglePresenter(Context context){
        this.context = context;
    }

    public List<PointItemModel> getPositionBanks(){

        List<LatLng> position = new ArrayList<LatLng>();
        List<PointItemModel> pointPositionModels = new ArrayList<PointItemModel>();

        if (gis2Model.getresult()!=null){
            for (Result gis2Result : gis2Model.getresult() ) {

                pointPositionModels.add(new PointItemModel(
                        new LatLng(Double.valueOf(gis2Result.getLat()),Double.valueOf(gis2Result.getLon())),
                        gis2Result.getName()
                ));

                //position.add(new LatLng(Double.valueOf(gis2Result.getLat()),Double.valueOf(gis2Result.getLon())));
                //map.put( Double.valueOf(gis2Result.getLat()), Double.valueOf(gis2Result.getLon()));
            }
        }

        return pointPositionModels;
    }

    public boolean DownloadModelOfServices(){

        //DATASERVISE <-------<
        String selectCities = Setting.getselectCityName(context);
        String selectBank = Setting.getselectBank(context);
        //int page = 1;
        Log.e("getBank = ",selectBank);
        DataService.getInstance().addListener(this);
        DataService.getInstance().Gis2DataSearchDownload(selectBank, selectCities, 1);
        //page = 2;

        return true;
    }

    boolean aBoolean = true;
    int page = 1;
    @Override
    public void onGis2DataSearchDownload(Gis2Model Gis2) {
        this.gis2Model = Gis2;
        MapGoogleActivity mapGoogleActivity = (MapGoogleActivity) context;
        if (gis2Model.getresult().size() == 50){ // & aBoolean
            page++;
            DataService.getInstance().Gis2DataSearchDownload(Setting.getselectBank(context), Setting.getselectCityName(context), page);
            //aBoolean = false;
        }

        mapGoogleActivity.renderMarkers (getPositionBanks());
    }

    @Override
    public void onCitiesDownloaded(List<CityModel> cities) {

    }

    @Override
    public void onBanksDownloaded(List<BankModel> banks) {

    }
}
