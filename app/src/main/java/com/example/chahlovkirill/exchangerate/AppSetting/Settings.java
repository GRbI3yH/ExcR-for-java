package com.example.chahlovkirill.exchangerate.AppSetting;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.*;

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

    private static final String APP_PREFERENCES = "ExchangeRate";
    private static String selectCityID_key = "selectCityID_key";

    public static void setselectCityID(int selectcity, Context ctx) {
        saveSetting(selectCityID_key,String.valueOf(selectcity),ctx);
    }
    public static int getToSelectedCityID(Context ctx){
        String selectCity = loadSetting(selectCityID_key,ctx);
        if(selectCity != null){
            return Integer.parseInt(selectCity);
        }
        return 4212;
    }


    private static String selectCityName_key = "selectCityName_key";
    public static void setselectCityName(String selectcity, Context ctx) {
        saveSetting(selectCityName_key,selectcity,ctx);
    }
    public static String getToSelectedCityName(Context ctx){
        String selectCity = loadSetting(selectCityName_key,ctx);
        if(selectCity != null){
            return selectCity;
        }
        return "Москва";
    }

    private static String selectBank_key = "selectBank_key";
    public static void setselectBank(String selectBank, Context ctx) {
        saveSetting(selectBank_key,selectBank,ctx);
    }
    public static String getToSelectedBank(Context ctx){
        String selectBank = loadSetting(selectBank_key,ctx);
        if(selectBank != null){
            return selectBank;
        }
        return "Москва";
    }


    private static String currency_key = "currency_key";
    public static void setSelectCurrency(EExchangeAction mode, Context ctx) {
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
    public static EExchangeAction getToSelectedCurrency(Context ctx){
        String mode =  loadSetting(currency_key,ctx);
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
        return EExchangeAction.EURBuy;
    }



    private static Gson gson = new GsonBuilder().create();

    private static String cities_key = "Cities_key";
    public static void setCities(List<CityModel> cities, Context ctx){
        String json = gson.toJson(cities);
        saveSetting(cities_key, json, ctx);
    }

    public static List<CityModel> getCities(Context ctx){

         String json =  loadSetting(cities_key ,ctx);

         if (json != null){
            Type listOfTestObject = new TypeToken<ArrayList<CityModel>>(){}.getType();
            ArrayList<CityModel> cities = gson.fromJson(json, listOfTestObject);

            String selectCity = String.valueOf(Settings.getToSelectedCityID(ctx));

            if( (selectCity != null) && (cities != null)){
                for (CityModel CM : cities) {
                    if (CM.getId() == Integer.parseInt(selectCity) )
                        CM.setSelected(true);
                }
            }
            return cities;
        }
        return new ArrayList<CityModel>();
    }

    private static String banks_key = "Banks_key";
    public static void setBanks(List<BankModel> banks, Context ctx){

        String json = gson.toJson(banks);
        saveSetting(banks_key, json, ctx);
    }
    public static List<BankModel> getBanks(Context ctx){
        String Banks = loadSetting(banks_key,ctx);
        if (Banks != null){
            String json =  Banks;
            Type listOfTestObject = new TypeToken<ArrayList<BankModel>>(){}.getType();
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