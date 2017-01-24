package com.example.chahlovkirill.exchangerate.Presenters;

import android.content.Context;
import android.widget.ListView;

import com.example.chahlovkirill.exchangerate.Adapters.CitiesAdapter;
import com.example.chahlovkirill.exchangerate.AppSetting.Setting;
import com.example.chahlovkirill.exchangerate.Model.CityModel;
import com.example.chahlovkirill.exchangerate.R;
import com.example.chahlovkirill.exchangerate.Services.cash2cashAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by chahlov.kirill on 23/01/17.
 */

public class TabCitiesPresenter {

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

        Setting.setCities(cities,context);
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
}