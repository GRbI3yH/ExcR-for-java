package com.example.chahlovkirill.exchangerate.Services;

import com.example.chahlovkirill.exchangerate.Model.BankModel;
import com.example.chahlovkirill.exchangerate.Model.CitiyModel;

import java.util.List;
import java.util.Map;
import retrofit2.http.FieldMap ;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by chahlov.kirill on 18/01/17.
 */

public interface IDataService {

    @GET("/Cities")
    public Call<List<CitiyModel>> Citiy();

    @GET("/RatesForCity")
    public Call<List<BankModel>> Bank(@FieldMap Map<String,String> cityId);

}
