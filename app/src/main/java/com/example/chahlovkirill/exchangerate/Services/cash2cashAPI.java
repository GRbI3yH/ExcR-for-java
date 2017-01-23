package com.example.chahlovkirill.exchangerate.Services;

/**
 * Created by chahlov.kirill on 18/01/17.
 */
import android.util.Log;

import com.example.chahlovkirill.exchangerate.AppSetting.Setting;
import com.example.chahlovkirill.exchangerate.Model.BankModel;
import com.example.chahlovkirill.exchangerate.Model.CityModel;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class cash2cashAPI {
    //public static DataService dataservice = new DataService();
    private static final String BASE_URL = "http://wsv3.cash2cash.ru/ExRatesJson.svc/";
    //private Gson gson = new GsonBuilder().create();

    public static void getCallCitiesModel (Callback<List<CityModel>> callbak){
        Retrofit retrofit = getClient();

        Icash2cashAPI cash2cash= retrofit.create(Icash2cashAPI.class);

        Call<List<CityModel>> call = cash2cash.getCities();
        call.enqueue(callbak);
    }
    public static void getCallBanksModel (String idCities, Callback<List<BankModel>> callbak){
        Retrofit retrofit = getClient();
        Icash2cashAPI cash2cash= retrofit.create(Icash2cashAPI.class);

        Map<String,String> map = new HashMap<>();
        map.put("cityId",idCities);

        Call<List<BankModel>> call = cash2cash.getBanks(map);
        call.enqueue(callbak);
    }


    private static Retrofit getClient(){

        Gson gson = new GsonBuilder().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return  retrofit;
    }
}
