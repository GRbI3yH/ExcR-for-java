package com.example.chahlovkirill.exchangerate.Presenters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.chahlovkirill.exchangerate.Adapters.CitiesAdapter;
import com.example.chahlovkirill.exchangerate.DataProvider.DataProvider;
import com.example.chahlovkirill.exchangerate.DataProvider.IDataProviderOutput;
import com.example.chahlovkirill.exchangerate.Model.BankModel;
import com.example.chahlovkirill.exchangerate.Model.CityModel;
import com.example.chahlovkirill.exchangerate.Model.EExchangeAction;
import com.example.chahlovkirill.exchangerate.Model.Gis2Model.Gis2Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by chahlov.kirill on 23/01/17.
 */

public class TabCitiesPresenter implements IDataProviderOutput {

    private List<CityModel> cities;
    private Context context;
    private CitiesAdapter adapter;

    public TabCitiesPresenter(Context context){
        this.context = context;
        adapter = new CitiesAdapter(context, new ArrayList<CityModel>(), this);
        DataProvider.getInstance().addListener(this);
        DataProvider.getInstance().getCities();
        DataProvider.getInstance().getTheSelectedCity();
    }

    public CitiesAdapter getAdapter(){
        return adapter = new CitiesAdapter(context, cities, this);
    }

    public void ClickCity(CityModel city){
        for (CityModel cityEdit: cities){
            if (cityEdit.getSelected()){
                cityEdit.setSelected(false);
            }
        }
        city.setSelected(true);
        UpdateAdapter();
        DataProvider.getInstance().setTheSelectedCity(city);
        DataProvider.getInstance().getBanks(String.valueOf(city.getId()));
    }

    private void UpdateAdapter(){
        if (adapter != null) {
            adapter.clear();
            adapter.addAll(cities);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void didReceiveCities(List<CityModel> cities) {
        Log.i("TabCitiesPresenter","didReceiveCities");
        this.cities = cities;
        UpdateAdapter();

    }

    @Override
    public void didReceiveTheSelectedCity(CityModel city) {
        Log.i("TabCitiesPresenter","didReceiveTheSelectedCity");
    }

    @Override
    public void didReceiveBanks(List<BankModel> banks) {}

    @Override
    public void didReceiveSelectedCurrencyForSorting(EExchangeAction mode) {}

    @Override
    public void didReceiveGis2Data(Gis2Model gis2, String request) {}
}