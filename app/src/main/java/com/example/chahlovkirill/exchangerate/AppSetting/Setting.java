package com.example.chahlovkirill.exchangerate.AppSetting;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.*;
import android.widget.Toast;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.example.chahlovkirill.exchangerate.Model.BankModel;
import com.example.chahlovkirill.exchangerate.Model.CityModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by chahlov.kirill on 19/01/17.
 */

public class Setting {

    public static final String APP_PREFERENCES = "ExchangeRate";
    //private  SharedPreferences sPref = getSharedPreferences (APP_PREFERENCES, Context.MODE_PRIVATE); //;

    private Gson gson = new GsonBuilder().create();

    private String cities_key = "Cities_key";
    public void setCities(List<CityModel> cities, Context ctx){
        String json = gson.toJson(cities);
        saveSetting(cities_key, json, ctx);
    }
    public List<CityModel> getCities(Context ctx){
        if (loadSetting(cities_key,ctx) != null){
            String json =  loadSetting(cities_key ,ctx);
            Type listOfTestObject = new ArrayList<CityModel>(){}.getClass();
            return gson.fromJson(json, listOfTestObject);
        }
        return null;
    }

    private String banks_key = "Banks_key";
    public void setBanks(List<BankModel> banks, Context ctx){

        String json = gson.toJson(banks);
        saveSetting(banks_key, json,ctx);
    }
    public List<BankModel> getBanks(Context ctx){
        if (loadSetting(banks_key,ctx) != null){
            String json =  loadSetting(banks_key ,ctx);
            Type listOfTestObject = new ArrayList<BankModel>(){}.getClass();
            return gson.fromJson(json, listOfTestObject);
        }
        return null;
    }

    private void saveSetting(String key,String value, Context ctx){
        SharedPreferences sPref = ctx.getSharedPreferences (APP_PREFERENCES, Context.MODE_PRIVATE);
        Editor editor = sPref.edit();
        editor.putString(key,value);
        editor.commit();
    }
    private String loadSetting(String key, Context ctx){
        SharedPreferences sPref = ctx.getSharedPreferences (APP_PREFERENCES, Context.MODE_PRIVATE);
        String s = "";
        return sPref.getString(key, null);
    }
}