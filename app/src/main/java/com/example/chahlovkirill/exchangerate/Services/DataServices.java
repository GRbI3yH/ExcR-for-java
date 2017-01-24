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
 * Created by chahlov.kirill on 24/01/17.
 */

public class DataServices {

    private DataServices instance;
    private DataServices (){}

//    private static List<CityModel> cities;
//    private static List<BankModel> banks;


    public static List<CityModel> CitiesDownload() {
        cash2cashAPI.getCallCitiesModel(new Callback<List<CityModel>>(){
            @Override
            public void onResponse(Call<List<CityModel>> call, Response<List<CityModel>> response) {
                if (response.isSuccessful() && response.body() != null){
                    cities =  response.body() ;
                }
            }

            @Override
            public void onFailure(Call<List<CityModel>> call, Throwable t) {
            }
        });
        return cities;
    }


    public static List<BankModel> BanksDownload(Context context) {
        cash2cashAPI.getCallBanksModel(Setting.getselectCity(context),new Callback<List<BankModel>>(){
            @Override
            public void onResponse(Call<List<BankModel>> call, Response<List<BankModel>> response) {
                if (response.isSuccessful() && response.body() != null){
                    banks = response.body() ;
                }
            }

            @Override
            public void onFailure(Call<List<BankModel>> call, Throwable t) {

            }
        });
        return banks;
    }
}
