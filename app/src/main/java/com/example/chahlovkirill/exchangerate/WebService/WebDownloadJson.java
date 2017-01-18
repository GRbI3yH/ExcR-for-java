package com.example.chahlovkirill.exchangerate.WebService;

/**
 * Created by chahlov.kirill on 18/01/17.
 */
import android.util.Log;

import com.example.chahlovkirill.exchangerate.Model.CitiyModel;
import com.example.chahlovkirill.exchangerate.Services.DataService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataOfServise {
    //public static DataService dataservice = new DataService();
    private final String BASE_URL = "http://wsv3.cash2cash.ru/ExRatesJson.svc";
    //private Gson gson = new GsonBuilder().create();

    public static List<T> getData(){
        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            DataService service = retrofit.create(DataService.class);

            Call<List<CitiyModel>> call = service.getCitiy();
            call.enqueue(new Callback<List<CitiyModel>>() {
                @Override
                public void onResponse(Call<List<CitiyModel>> call, Response<List<CitiyModel>> response) {
                    this.CitiyModel = response.body();
                    return this.CitiyModel;
                }

                @Override
                public void onFailure(Call<List<CitiyModel>> call, Throwable t) {
                    Log.d("onFailure", t.toString());
                }
            });
        }
        catch (Exception e){

        }
    }

}