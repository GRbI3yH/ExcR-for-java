package com.example.chahlovkirill.exchangerate.Presenters;

import android.content.Context;
import android.content.Intent;

import com.example.chahlovkirill.exchangerate.Activity.MapGoogleActivity;
import com.example.chahlovkirill.exchangerate.Adapters.BanksAdapter;
import com.example.chahlovkirill.exchangerate.AppSetting.Settings;
import com.example.chahlovkirill.exchangerate.Model.BankViewModel;
import com.example.chahlovkirill.exchangerate.Model.BankModel;
import com.example.chahlovkirill.exchangerate.Model.CityModel;
import com.example.chahlovkirill.exchangerate.Model.EExchangeAction;
import com.example.chahlovkirill.exchangerate.Model.Gis2Model.Gis2Model;
import com.example.chahlovkirill.exchangerate.Services.CoupBanksCurrency;
import com.example.chahlovkirill.exchangerate.Services.DataService;
import com.example.chahlovkirill.exchangerate.Services.IControlListener;
import com.example.chahlovkirill.exchangerate.Services.TransferBanks;

import java.util.Collections;
import java.util.List;

/**
 * Created by chahlov.kirill on 23/01/17.
 */

public class TabBanksPresenter implements IControlListener {

    public TabBanksPresenter(Context context){
        this.context = context;
    }

    private Context context;
    private List<BankModel> banks;
    private List<BankViewModel> banksCurrency ;
    private BanksAdapter adapter;
    //private DataServices dataServices;

    public BanksAdapter getAdapter(){
        return adapter = new BanksAdapter( context , banksCurrency, this);
    }

    public void  LoadModelOfSetting(){
        banksCurrency = TransferBanks.Transfer( //достаем из настроек и сортируем
                Settings.getBanks(context),
                Settings.getToSelectedCurrency(context),
                context
        );
        Collections.sort(banksCurrency,BankViewModel.bankCurrencyModelComparator );
    }

    public void DownloadModelOfServices(){

        //DATASERVISE <-------<
        String selectCities = String.valueOf(Settings.getToSelectedCityID(context));
        DataService.getInstance().addListener(this);
        DataService.getInstance().BanksDownload(selectCities);
    }

    public void ButtonSortCurrency(EExchangeAction mode){

        for (BankViewModel bankCurrency :banksCurrency){
            bankCurrency.setCurrencyOf(mode);
        }
        Collections.sort(banksCurrency,BankViewModel.bankCurrencyModelComparator );

        if(mode == EExchangeAction.EURSell || mode == EExchangeAction.USDSell){
            banksCurrency = CoupBanksCurrency.Coup(banksCurrency);
        }

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
        banksCurrency = TransferBanks.Transfer(
                banks,
                Settings.getToSelectedCurrency(context),
                context
        );
        Collections.sort(banksCurrency,BankViewModel.bankCurrencyModelComparator );
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
}