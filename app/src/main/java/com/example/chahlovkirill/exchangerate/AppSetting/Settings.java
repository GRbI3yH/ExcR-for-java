package com.example.chahlovkirill.exchangerate.AppSetting;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.*;
import android.util.Log;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.example.chahlovkirill.exchangerate.Model.BankModel;
import com.example.chahlovkirill.exchangerate.Model.CityModel;
import com.example.chahlovkirill.exchangerate.Model.EExchangeAction;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * Created by chahlov.kirill on 19/01/17.
 */

public class Settings {

    public static void setContext(Context ccontext){
        context = ccontext;
    }

    private static final String APP_PREFERENCES = "ExchangeRate";
    private static Context context;

    private static String selectedCity = "selectedСity_key";
    public static void setTheSelectedCity(CityModel city){
        String json = gson.toJson(city);
        saveSetting(selectedCity, json);

    }
    public static CityModel getTheSelectedCity(){
        String stringCity = loadSetting(selectedCity);
        if (stringCity != null){
            String json =  stringCity;
            Type type = new TypeToken<CityModel>(){}.getType();
            CityModel city = gson.fromJson(json, type);
            city.setSelected(true);
            return city;
        }
        return new CityModel(7701,"Москва",true);
    }

    private static String currency_key = "currency_key";
    public static void setTheSelectedCurrency(EExchangeAction mode) {
        switch (mode){
            case EURBuy:
                saveSetting(currency_key,"0");
                break;
            case EURSell:
                saveSetting(currency_key,"1");
                break;
            case USDBuy:
                saveSetting(currency_key,"2");
                break;
            case USDSell:
                saveSetting(currency_key,"3");
                break;
        }
    }
    public static EExchangeAction getTheSelectedCurrency(){
        String mode =  loadSetting(currency_key);
        if (mode != null){
            switch (mode){
                case "0":
                    return EExchangeAction.EURBuy ;

                case "1":
                    return EExchangeAction.EURSell ;

                case "2":
                    return EExchangeAction.USDBuy ;

                case "3":
                    return EExchangeAction.USDSell ;
            }
        }
        return EExchangeAction.USDBuy;
    }



    private static Gson gson = new GsonBuilder().create();

    private static String cities_key = "Cities_key";
    public static void setCities(List<CityModel> cities){
        String json = gson.toJson(cities);
        saveSetting(cities_key, json);
    }

    public static List<CityModel> getCities(){
         String json =  loadSetting(cities_key);
         if (json != null){
             Type type = new TypeToken<ArrayList<CityModel>>(){}.getType();
             ArrayList<CityModel> cities = gson.fromJson(json, type);
             String selectedCity = String.valueOf(Settings.getTheSelectedCity().getId());
             if( (selectedCity != null) && (cities != null)){
                 for (CityModel CM : cities) {
                    if (CM.getId() == Integer.parseInt(selectedCity) ){
                        CM.setSelected(true);
                        break;
                    }
                }
             }
            return cities;
        }
        return new ArrayList<CityModel>();
    }

    private static String banks_key = "Banks_key";
    public static void setBanks(List<BankModel> banks){
        String json = gson.toJson(banks);
        saveSetting(banks_key, json);
    }
    public static List<BankModel> getBanks(){
        String Banks = loadSetting(banks_key);
        if (Banks != null){
            String json =  Banks;
            Type type = new TypeToken<ArrayList<BankModel>>(){}.getType();
            return gson.fromJson(json, type);
        }
        return new ArrayList<BankModel>();
    }

    private static void saveSetting(String key,String value){
        Log.i("Settings.saveSetting",key);
        SharedPreferences sPref = context.getSharedPreferences (APP_PREFERENCES, Context.MODE_PRIVATE);
        Editor editor = sPref.edit();
        editor.putString(key,value);
        editor.commit();
    }
    private static String loadSetting(String key){
        Log.i("Settings.loadSetting",key);
        SharedPreferences sPref = context.getSharedPreferences (APP_PREFERENCES, Context.MODE_PRIVATE);
        return sPref.getString(key, null);
    }
}