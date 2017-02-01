package com.example.chahlovkirill.exchangerate.Presenters;

import android.content.Context;

import com.example.chahlovkirill.exchangerate.Adapters.CitiesAdapter;
import com.example.chahlovkirill.exchangerate.AppSetting.Setting;
import com.example.chahlovkirill.exchangerate.Model.BankModel;
import com.example.chahlovkirill.exchangerate.Model.CityModel;
import com.example.chahlovkirill.exchangerate.Model.Gis2Model.Gis2Model;
import com.example.chahlovkirill.exchangerate.Services.DataService;
import com.example.chahlovkirill.exchangerate.Services.IControlListener;

import java.util.List;

/**
 * Created by chahlov.kirill on 23/01/17.
 */

public class TabCitiesPresenter implements IControlListener {

    private List<CityModel> cities;
    private Context context;
    private CitiesAdapter adapter;

    public TabCitiesPresenter(Context context){
        this.context = context;
    }

    public CitiesAdapter getAdapter(){
        return adapter = new CitiesAdapter( context , cities, this);
    }

    public void LoadModelOfSetting(){
        cities = Setting.getCities(context);
        MakeTheCitySelectedInTheModel();

    }

    public void DownloadModelOfServices(){

        //DATASERVISE <-------<

        DataService.getInstance().addListener(this);
        DataService.getInstance().CitiesDownload();

    }
    private void MakeTheCitySelectedInTheModel(){

        String selectCity = String.valueOf(Setting.getselectCityID(context));

        for (CityModel city :cities){
            if (String.valueOf(city.getId()).equals(selectCity)){
                city.setSelected(true);
                break;
            }
        }
    }

    public void CityItemClick(CityModel city){
        for (CityModel cityEdit: cities){
            if (cityEdit.getSelected()){
                cityEdit.setSelected(false);
            }
        }
        city.setSelected(true);
        Setting.setselectCityID(city.getId(),context);

        if (adapter != null) {
            adapter.clear();
            adapter.addAll(cities);
            adapter.notifyDataSetChanged();
        }

        DataService.getInstance().BanksDownload(String.valueOf(city.getId()));
    }

    @Override
    public void onCitiesDownloaded(List<CityModel> cities) {
        Setting.setCities(cities,context);
        this.cities = cities;
        MakeTheCitySelectedInTheModel();
        if (adapter != null) {
            adapter.clear();
            adapter.addAll(this.cities);
            adapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onBanksDownloaded(List<BankModel> banks) {

    }

    @Override
    public void onGis2DataSearchDownload(Gis2Model Gis2) {

    }
}