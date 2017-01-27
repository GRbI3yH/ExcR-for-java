package com.example.chahlovkirill.exchangerate.Services;

import com.example.chahlovkirill.exchangerate.Model.Gis2Model.Gis2Model;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by chahlov.kirill on 27/01/17.
 */

public interface IGis2API {
    @GET("search")
    public Call<List<Gis2Model>> getGis2Data(@QueryMap Map<String,String> data);
}
