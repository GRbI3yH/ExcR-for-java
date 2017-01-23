package com.example.chahlovkirill.exchangerate.Services;

import com.example.chahlovkirill.exchangerate.Model.BankModel;
import com.example.chahlovkirill.exchangerate.Model.CityModel;

import java.util.List;
import java.util.Map;
import retrofit2.http.FieldMap ;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by chahlov.kirill on 18/01/17.
 */

public interface Icash2cashAPI {

    @GET("Cities")
    public Call<List<CityModel>> getCities();

    @GET("RatesForCity")
    public Call<List<BankModel>> getBanks(@QueryMap Map<String,String> cityId);

}
