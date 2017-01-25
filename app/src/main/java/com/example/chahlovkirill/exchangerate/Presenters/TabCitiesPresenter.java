package com.example.chahlovkirill.exchangerate.Presenters;

import android.content.Context;

import com.example.chahlovkirill.exchangerate.Adapters.CitiesAdapter;
import com.example.chahlovkirill.exchangerate.AppSetting.Setting;
import com.example.chahlovkirill.exchangerate.Model.BankModel;
import com.example.chahlovkirill.exchangerate.Model.CityModel;
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
        return adapter = new CitiesAdapter( context , cities);
    }

    public void LoadModelOfSetting(){
        cities = Setting.getCities(context);
        FoundOfSelectCity();

    }

    public void DownloadModelOfServices(){

        //DATASERVISE <-------<

        DataService.getInstance().addListener(this);
        DataService.getInstance().CitiesDownload();

        FoundOfSelectCity();

    }
    private void FoundOfSelectCity(){

        String selectCity = Setting.getselectCity(context);

        for (CityModel city :cities){
            if (String.valueOf(city.getId()).equals(selectCity)){
                city.setSelected(true);
                break;
            }
        }
    }

    @Override
    public void onCitiesDownloaded(List<CityModel> cities) {
        Setting.setCities(cities,context);
        this.cities = cities;
    }

    @Override
    public void onBanksDownloaded(List<BankModel> banks) {

    }
}