package com.example.chahlovkirill.exchangerate.DataProvider;

import android.content.Context;
import android.util.Log;

import com.example.chahlovkirill.exchangerate.AppSetting.Settings;
import com.example.chahlovkirill.exchangerate.Model.BankModel;
import com.example.chahlovkirill.exchangerate.Model.CityModel;
import com.example.chahlovkirill.exchangerate.Model.EExchangeAction;
import com.example.chahlovkirill.exchangerate.Model.Gis2Model.Gis2Model;
import com.example.chahlovkirill.exchangerate.Services.ServicesAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by chahlov.kirill on 03/02/17.
 */

public class DataProvider implements IDataProvider {

    private static DataProvider ourInstance = new DataProvider();

    public static DataProvider getInstance() {
        return ourInstance;
    }

    private static List<IDataProviderOutput> listeners = new ArrayList<IDataProviderOutput>();

    public void  addListener(IDataProviderOutput listener){
        listeners.add(listener);
    }

    public void  removeListener(IDataProviderOutput listener){
        listeners.remove(listener);
    }

    public void  clearListener(IDataProviderOutput listener){
        listeners.clear();
    }

    private DataProvider(){
    }


    @Override
    public void getBanks(String idSelectCity) {
        for (IDataProviderOutput cl:listeners){
            cl.didReceiveBanks(Settings.getBanks());
        }
        ServicesAPI.getCallBanksModel(idSelectCity, new Callback<List<BankModel>>(){
            @Override
            public void onResponse(Call<List<BankModel>> call, Response<List<BankModel>> response) {
                if (response.isSuccessful() && response.body() != null){
                    for (IDataProviderOutput cl:listeners){
                        cl.didReceiveBanks(response.body());
                    }
                    Log.i("ServicesAPI = ","didReceiveBanks");
                }
            }

            @Override
            public void onFailure(Call<List<BankModel>> call, Throwable t) {
                Log.i("ServicesAPI","Банки не загрузились");
            }
        });
    }

    @Override
    public void setBanks(List<BankModel> banks) {
        Settings.setBanks(banks);
    }

    @Override
    public void getReceiveSelectCurrencyForSorting() {
        for (IDataProviderOutput cl:listeners){
            cl.didReceiveSelectCurrencyForSorting(Settings.getToSelectedCurrency());
        }
    }

    @Override
    public void setReceiveSelectCurrencyForSorting(EExchangeAction mode) {
        Settings.setSelectCurrency(mode);
    }

    @Override
    public void getCities() {
        for (IDataProviderOutput cl:listeners){
            cl.didReceiveCities(Settings.getCities());
        }

        ServicesAPI.getCallCitiesModel(new Callback<List<CityModel>>(){
            @Override
            public void onResponse(Call<List<CityModel>> call, Response<List<CityModel>> response) {
                Log.i("ServicesAPI","didReceiveCities");
                if (response.isSuccessful() && response.body() != null){
                    int cityId = Settings.getSelectCity().getId();
                    for (CityModel city:response.body()){
                        if (city.getId()== cityId){
                            city.setSelected(true);
                            break;
                        }
                    }
                    for (IDataProviderOutput cl:listeners){
                        cl.didReceiveCities(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<CityModel>> call, Throwable t) {
                Log.i("ServicesAPI","города не загрузились");
            }
        });
    }

    @Override
    public void setCities(List<CityModel> cities) {
        Settings.setCities(cities);
    }

    @Override
    public void getTheSelectedCity() {
        for (IDataProviderOutput cl:listeners){
            cl.didReceiveTheSelectedCity(Settings.getSelectCity());
        }
    }

    @Override
    public void setTheSelectedCity(CityModel city) {
        Settings.setSelectCity(city);
    }

    @Override
    public void getGis2Data(String whatBank, String where, int page) {
        ServicesAPI.getCallGis2ModelSearch(whatBank, where , page, new Callback<Gis2Model>(){
            @Override
            public void onResponse(Call<Gis2Model> call, Response<Gis2Model> response) {
                Log.i("ServicesAPI","didReceiveGis2Data");
                if (response.isSuccessful() && response.body() != null){
                    for (IDataProviderOutput cl:listeners){
                        cl.didReceiveGis2Data(response.body());
                    }

                }
            }

            @Override
            public void onFailure(Call<Gis2Model> call, Throwable t) {
                Log.i("ServicesAPI","2Gis не загрузились");
            }
        });
    }
}
