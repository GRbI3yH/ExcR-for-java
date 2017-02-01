package com.example.chahlovkirill.exchangerate.Presenters;

import android.content.Context;
import android.content.Intent;

import com.example.chahlovkirill.exchangerate.Activity.MapGoogleActivity;
import com.example.chahlovkirill.exchangerate.Adapters.BanksAdapter;
import com.example.chahlovkirill.exchangerate.AppSetting.Setting;
import com.example.chahlovkirill.exchangerate.Model.BankCurrencyModel;
import com.example.chahlovkirill.exchangerate.Model.BankModel;
import com.example.chahlovkirill.exchangerate.Model.CityModel;
import com.example.chahlovkirill.exchangerate.Model.EExchangeAction;
import com.example.chahlovkirill.exchangerate.Model.Gis2Model.Gis2Model;
import com.example.chahlovkirill.exchangerate.Services.DataService;
import com.example.chahlovkirill.exchangerate.Services.IControlListener;
import com.example.chahlovkirill.exchangerate.Services.SortBanks;

import java.util.Collections;
import java.util.List;

/**
 * Created by chahlov.kirill on 23/01/17.
 */

public class TabBanksPresenter implements IControlListener {//implements ControlListener {

    public TabBanksPresenter(Context context){
        this.context = context;
    }

    private Context context;
    private List<BankModel> banks;
    private List<BankCurrencyModel> banksCurrency ;
    private BanksAdapter adapter;
    //private DataServices dataServices;

    public BanksAdapter getAdapter(){
        return adapter = new BanksAdapter( context , banksCurrency, this);
    }

    public void  LoadModelOfSetting(){
        banksCurrency = SortBanks.Sort( //достаем из настроек и сортируем
                Setting.getBanks(context),
                Setting.getSelectCurrency(context),
                context
        );
    }

    public void DownloadModelOfServices(){

        //DATASERVISE <-------<
        String selectCities = String.valueOf(Setting.getselectCityID(context));
        DataService.getInstance().addListener(this);
        DataService.getInstance().BanksDownload(selectCities);
    }

    public void ButtonSortCurrency(EExchangeAction mode){

        for (BankCurrencyModel bankCurrency :banksCurrency){
            //bankCurrency.setName("TEST");
            bankCurrency.setCurrencyOf(mode);
        }

        Collections.sort(banksCurrency,BankCurrencyModel.bankCurrencyModelComparator );

        adapter.clear();
        adapter.addAll(banksCurrency);
        adapter.notifyDataSetChanged();
    }

    public void GoToMapGoogleActivity(){
        Intent intent = new Intent(context, MapGoogleActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onBanksDownloaded(List<BankModel> Banks) {
        this.banks = Banks;
        banksCurrency = SortBanks.Sort(
                banks,
                Setting.getSelectCurrency(context),
                context
        );
        if (adapter != null) {
            adapter.clear();
            adapter.addAll(banksCurrency);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onGis2DataSearchDownload(Gis2Model Gis2) {

    }

    @Override
    public void onCitiesDownloaded(List<CityModel> cities) {

    }

//    @Override
//    public void onDataChanged() {
//        DownloadModelOfServices();
//    }
//    public static void EventDovload(){
//
//        adapter.clear();
//        adapter.addAll(banksCurrency);
//        adapter.notifyDataSetChanged();
//    }
}