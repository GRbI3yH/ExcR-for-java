package com.example.chahlovkirill.exchangerate.Services;

import android.content.Context;

import com.example.chahlovkirill.exchangerate.AppSetting.Setting;
import com.example.chahlovkirill.exchangerate.Model.BankModel;
import com.example.chahlovkirill.exchangerate.Model.CityModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by chahlov.kirill on 25/01/17.
 */
public class DataService {
    private static DataService ourInstance = new DataService();

    public static DataService getInstance() {
        return ourInstance;
    }

    private DataService() {
    }

    private static List<IControlListener> listeners = new ArrayList<IControlListener>();

    public void CitiesDownload() {
        cash2cashAPI.getCallCitiesModel(new Callback<List<CityModel>>(){
            @Override
            public void onResponse(Call<List<CityModel>> call, Response<List<CityModel>> response) {
                if (response.isSuccessful() && response.body() != null){
                    for (IControlListener cl:listeners){
                        cl.onCitiesDownloaded(response.body());
                        // добавить on bank который принимает в себя город
                    }
                }
            }

            @Override
            public void onFailure(Call<List<CityModel>> call, Throwable t) {
            }
        });
    }

    public void BanksDownload(String selectCity){
        cash2cashAPI.getCallBanksModel(selectCity, new Callback<List<BankModel>>(){
            @Override
            public void onResponse(Call<List<BankModel>> call, Response<List<BankModel>> response) {
                if (response.isSuccessful() && response.body() != null){
                    for (IControlListener cl:listeners){
                        cl.onBanksDownloaded(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<BankModel>> call, Throwable t) {
                System.out.print("fail");
            }
        });
    }

    public void  addListener(IControlListener listener){
        listeners.add(listener);
    }
}
