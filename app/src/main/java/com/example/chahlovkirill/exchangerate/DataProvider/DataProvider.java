package com.example.chahlovkirill.exchangerate.DataProvider;

import android.content.Context;
import android.util.Log;

import com.example.chahlovkirill.exchangerate.AppSetting.Settings;
import com.example.chahlovkirill.exchangerate.Model.BankModel;
import com.example.chahlovkirill.exchangerate.Model.CityModel;
import com.example.chahlovkirill.exchangerate.Model.EDistans;
import com.example.chahlovkirill.exchangerate.Model.EExchangeAction;
import com.example.chahlovkirill.exchangerate.Model.Gis2Model.Gis2Model;
import com.example.chahlovkirill.exchangerate.Presenters.PresentersBankDetails.IDistansPresentersEvent;
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
                Log.i("ServicesAPI = ","didReceiveBanks");
                if (response.isSuccessful() && response.body() != null){
                    List<BankModel> banks = response.body();
                    Settings.setBanks(banks);
                    for (IDataProviderOutput cl:listeners){
                        cl.didReceiveBanks(banks);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<BankModel>> call, Throwable t) {
                Log.i("ServicesAPI","Banks are not loaded");
            }
        });
    }

    @Override
    public void setBanks(List<BankModel> banks) {
        Settings.setBanks(banks);
    }

    @Override
    public void getReceiveSelectedCurrencyForSorting() {
        for (IDataProviderOutput cl:listeners){
            cl.didReceiveSelectedCurrencyForSorting(Settings.getTheSelectedCurrency());
        }
    }

    @Override
    public void setReceiveSelectedCurrencyForSorting(EExchangeAction mode) {
        Settings.setTheSelectedCurrency(mode);
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
                    List<CityModel> cities = response.body();
                    Settings.setCities(cities);
                    int cityId = Settings.getTheSelectedCity().getId();
                    for (CityModel city: cities){
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
                Log.i("ServicesAPI","Cities are not loaded");
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
            cl.didReceiveTheSelectedCity(Settings.getTheSelectedCity());
        }
    }

    @Override
    public void setTheSelectedCity(CityModel city) {
        Settings.setTheSelectedCity(city);
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
                Log.i("ServicesAPI","2Gis are not loaded");
            }
        });
    }

    private static List<IDistansPresentersEvent> listenersDistans = new ArrayList<IDistansPresentersEvent>();
    public void  addListenerDistans(IDistansPresentersEvent listener){
        listenersDistans.add(listener);
    }
    public void  removeListenerDistans(IDistansPresentersEvent listener){
        listenersDistans.remove(listener);
    }
    public void  clearListenerDistans(IDistansPresentersEvent listener){
        listenersDistans.clear();
    }

    @Override
    public void onSelectDistance(EDistans mode, int whom) {
        for (IDistansPresentersEvent cl:listenersDistans){
            cl.onSelectDistance(mode, whom);
        }
    }
}
