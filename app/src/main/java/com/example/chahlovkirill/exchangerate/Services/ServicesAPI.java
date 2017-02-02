package com.example.chahlovkirill.exchangerate.Services;

/**
 * Created by chahlov.kirill on 18/01/17.
 */

import com.example.chahlovkirill.exchangerate.Model.BankModel;
import com.example.chahlovkirill.exchangerate.Model.CityModel;
import com.example.chahlovkirill.exchangerate.Model.Gis2Model.Gis2Model;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.google.gson.Gson;

public class ServicesAPI {
    private static final String cash2cash_URL = "http://wsv3.cash2cash.ru/ExRatesJson.svc/";
    private static final String gis2_URL = "http://catalog.api.2gis.ru/";

    public static void getCallCitiesModel (Callback<List<CityModel>> callbak){
        Retrofit retrofit = getClient(cash2cash_URL);

        Icash2cashAPI cash2cash= retrofit.create(Icash2cashAPI.class);

        Call<List<CityModel>> call = cash2cash.getCities();
        call.enqueue(callbak);
    }
    public static void getCallBanksModel (String idCities, Callback<List<BankModel>> callbak){
        Retrofit retrofit = getClient(cash2cash_URL);
        Icash2cashAPI cash2cash= retrofit.create(Icash2cashAPI.class);

        Map<String,String> map = new HashMap<>();
        map.put("cityId",idCities);

        Call<List<BankModel>> call = cash2cash.getBanks(map);
        call.enqueue(callbak);
    }
    public static void getCallGis2ModelSearch(String whatBank, String where, int page, Callback<Gis2Model> callbak){
        Retrofit retrofit = getClient(gis2_URL);
        IGis2API gis2api = retrofit.create(IGis2API.class);

        Map<String,String> map = new HashMap<>();
        map.put("what","отделения_"+whatBank);//"отделения_"+whatBanks
        map.put("where",where);
        map.put("version","1.3");
        map.put("key","ruiqff5223");
        map.put("pagesize","50");
        map.put("page",String.valueOf(page));

        Call<Gis2Model> call = gis2api.getGis2Data(map);
        call.enqueue(callbak);
    }


    private static Retrofit getClient(String base_url){

        Gson gson = new GsonBuilder().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return  retrofit;
    }
}
