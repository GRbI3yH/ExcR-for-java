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

    private List<CityModel> Cities;
    private Context context;
    private CitiesAdapter adapter;

    public TabCitiesPresenter(Context context){
        this.context = context;
    }

    public CitiesAdapter getAdapter(){
        return adapter = new CitiesAdapter( context , Cities);
    }
    public void LoadModelOfSetting(){
        Cities = Setting.getCities(context);

    }

    public void DownloadModelOfServices(){
        cash2cashAPI.getCallCitiesModel(new Callback<List<CityModel>>(){
            @Override
            public void onResponse(Call<List<CityModel>> call, Response<List<CityModel>> response) {
                if (response.isSuccessful() && response.body() != null){
                    Cities = response.body() ;

                    Setting.setCities(Cities,context);

                    String selectCity = Setting.getselectCity(context);

                    for (CityModel city :Cities){
                        if (String.valueOf(city.getId()).equals(selectCity)){
                            city.setSelected(true);
                        }
                    }
                    adapter = new  CitiesAdapter( context , Cities);
                }
            }

            @Override
            public void onFailure(Call<List<CityModel>> call, Throwable t) {
            }
        });

    }
}
