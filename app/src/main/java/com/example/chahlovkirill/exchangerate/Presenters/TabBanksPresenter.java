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
import com.example.chahlovkirill.exchangerate.Services.CoupBanksView;
import com.example.chahlovkirill.exchangerate.Services.DataService;
import com.example.chahlovkirill.exchangerate.Services.IControlListener;
import com.example.chahlovkirill.exchangerate.Services.TransferBanksInBankView;

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
    private List<BankViewModel> banksView ;
    private BanksAdapter adapter;

    public BanksAdapter getAdapter(){
        return adapter = new BanksAdapter( context , banksView, this);
    }

    public void  LoadingFromSettings(){
        banksView = TransferBanksInBankView.Transfer(
                Settings.getBanks(context),
                Settings.getToSelectedCurrency(context),
                context
        );
        Collections.sort(banksView,BankViewModel.bankCurrencyModelComparator);
    }

    public void DownloadFromServices(){
        DataService.getInstance().addListener(this);
        DataService.getInstance().BanksDownload(String.valueOf(Settings.getToSelectedCityID(context)));
    }

    public void ButtonSortCurrency(EExchangeAction mode){
        for (BankViewModel bankView :banksView){
            bankView.setCurrencyOf(mode);
        }
        Collections.sort(banksView,BankViewModel.bankCurrencyModelComparator );
        if(mode == EExchangeAction.EURSell || mode == EExchangeAction.USDSell){
            banksView = CoupBanksView.Coup(banksView);
        }
        UpdateAdapter();
    }

    public void GoToMapGoogleActivity(String selectedBank){
        Intent intent = new Intent(context, MapGoogleActivity.class);
        intent.putExtra("SelectedBank",selectedBank);
        context.startActivity(intent);
    }

    @Override
    public void onBanksDownloaded(List<BankModel> Banks) {
        this.banks = Banks;
        banksView = TransferBanksInBankView.Transfer(
                banks,
                Settings.getToSelectedCurrency(context),
                context
        );
        Collections.sort(banksView,BankViewModel.bankCurrencyModelComparator );
        UpdateAdapter();
    }
    private void UpdateAdapter(){
        if (adapter != null) {
            adapter.clear();
            adapter.addAll(this.banksView);
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