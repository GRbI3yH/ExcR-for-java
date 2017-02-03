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

    public static void setContext(Context ccontext){
        context = ccontext;
    }

    private static final String APP_PREFERENCES = "ExchangeRate";
    private static Context context;

    private static String selectCity = "selectCity";
    public static void setSelectCity(CityModel city){
        setTheSelectCityID(city.getId());
        setTheSelectCityName(city.getName());
    }
    public static CityModel getSelectCity(){
        CityModel city = new CityModel();
        city.setId(getTheSelectedCityID());
        city.setName(getTheSelectedCityName());
        city.setSelected(true);
        return city;
    }

    private static String selectCityID_key = "selectCityID_key";
    private static void setTheSelectCityID(int selectcity) {//обЪединить 1
        saveSetting(selectCityID_key,String.valueOf(selectcity));
    }
    private static int getTheSelectedCityID(){
        String selectCity = loadSetting(selectCityID_key);
        if(selectCity != null){
            return Integer.parseInt(selectCity);
        }
        return 7701;
    }

    private static String selectCityName_key = "selectCityName_key";
    private static void setTheSelectCityName(String selectcity) {//обЪединить 1
        saveSetting(selectCityName_key,selectcity);
    }
    private static String getTheSelectedCityName(){
        String selectCity = loadSetting(selectCityName_key);
        if(selectCity != null){
            return selectCity;
        }
        return "Москва";
    }

    private static String currency_key = "currency_key";
    public static void setSelectCurrency(EExchangeAction mode) {
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
    public static EExchangeAction getToSelectedCurrency(){
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
            Type listOfTestObject = new TypeToken<ArrayList<CityModel>>(){}.getType();
            ArrayList<CityModel> cities = gson.fromJson(json, listOfTestObject);

            String selectCity = String.valueOf(Settings.getTheSelectedCityID());

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
    public static void setBanks(List<BankModel> banks){

        String json = gson.toJson(banks);
        saveSetting(banks_key, json);
    }
    public static List<BankModel> getBanks(){
        String Banks = loadSetting(banks_key);
        if (Banks != null){
            String json =  Banks;
            Type listOfTestObject = new TypeToken<ArrayList<BankModel>>(){}.getType();
            return gson.fromJson(json, listOfTestObject);
        }
        return new ArrayList<BankModel>();
    }

    private static void saveSetting(String key,String value){
        SharedPreferences sPref = context.getSharedPreferences (APP_PREFERENCES, Context.MODE_PRIVATE);
        Editor editor = sPref.edit();
        editor.putString(key,value);
        editor.commit();
    }
    private static String loadSetting(String key){
        SharedPreferences sPref = context.getSharedPreferences (APP_PREFERENCES, Context.MODE_PRIVATE);
        String s = "";
        return sPref.getString(key, null);
    }
}