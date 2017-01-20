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
import com.example.chahlovkirill.exchangerate.Model.EExchangeAction;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by chahlov.kirill on 19/01/17.
 */

public class Setting {

    public static final String APP_PREFERENCES = "ExchangeRate";
    //private  SharedPreferences sPref = getSharedPreferences (APP_PREFERENCES, Context.MODE_PRIVATE); //;

    private static String selectCity_key = "selectCity_key";
    public static void setselectCity(String selectcity, Context ctx) {
        saveSetting(selectCity_key,selectcity,ctx);
    }
    public static String getselectCity(Context ctx){
        if(loadSetting(selectCity_key,ctx) != null){
            return loadSetting(selectCity_key,ctx);
        }
        return "4212";
    }


    private static String currency_key = "currency_key";
    public static void setCurrency(EExchangeAction mode, Context ctx) {
        switch (mode){
            case EURBuy:
                saveSetting(currency_key,"0", ctx);
                break;
            case EURSell:
                saveSetting(currency_key,"1", ctx);
                break;
            case USDBuy:
                saveSetting(currency_key,"2", ctx);
                break;
            case USDSell:
                saveSetting(currency_key,"3", ctx);
                break;
        }
    }
    public static EExchangeAction getCurrency(Context ctx){
        if (loadSetting(currency_key,ctx) != null){
            String mode =  loadSetting(currency_key,ctx);
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
        return EExchangeAction.EURBuy;
    }



    private static Gson gson = new GsonBuilder().create();

    private static String cities_key = "Cities_key";
    public static void setCities(List<CityModel> cities, Context ctx){
        String json = gson.toJson(cities);
        saveSetting(cities_key, json, ctx);
    }
    public static List<CityModel> getCities(Context ctx){
        if (loadSetting(cities_key,ctx) != null){
            String json =  loadSetting(cities_key ,ctx);
            Type listOfTestObject = new ArrayList<CityModel>(){}.getClass();
            return gson.fromJson(json, listOfTestObject);
        }
        return new ArrayList<CityModel>();
    }

    private static String banks_key = "Banks_key";
    public static void setBanks(List<BankModel> banks, Context ctx){

        String json = gson.toJson(banks);
        saveSetting(banks_key, json,ctx);
    }
    public static List<BankModel> getBanks(Context ctx){
        if (loadSetting(banks_key,ctx) != null){
            String json =  loadSetting(banks_key ,ctx);
            Type listOfTestObject = new ArrayList<BankModel>(){}.getClass();
            return gson.fromJson(json, listOfTestObject);
        }
        return new ArrayList<BankModel>();
    }

    private static void saveSetting(String key,String value, Context ctx){
        SharedPreferences sPref = ctx.getSharedPreferences (APP_PREFERENCES, Context.MODE_PRIVATE);
        Editor editor = sPref.edit();
        editor.putString(key,value);
        editor.commit();
    }
    private static String loadSetting(String key, Context ctx){
        SharedPreferences sPref = ctx.getSharedPreferences (APP_PREFERENCES, Context.MODE_PRIVATE);
        String s = "";
        return sPref.getString(key, null);
    }
}