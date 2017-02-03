package com.example.chahlovkirill.exchangerate.Presenters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.chahlovkirill.exchangerate.Activity.MapGoogleActivity;
import com.example.chahlovkirill.exchangerate.Adapters.BanksAdapter;
import com.example.chahlovkirill.exchangerate.DataProvider.DataProvider;
import com.example.chahlovkirill.exchangerate.DataProvider.IDataProviderOutput;
import com.example.chahlovkirill.exchangerate.Model.BankViewModel;
import com.example.chahlovkirill.exchangerate.Model.BankModel;
import com.example.chahlovkirill.exchangerate.Model.CityModel;
import com.example.chahlovkirill.exchangerate.Model.EExchangeAction;
import com.example.chahlovkirill.exchangerate.Model.Gis2Model.Gis2Model;
import com.example.chahlovkirill.exchangerate.Services.CoupBanksView;
import com.example.chahlovkirill.exchangerate.Services.TransferBanksInBankView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by chahlov.kirill on 23/01/17.
 */

public class TabBanksPresenter implements IDataProviderOutput {

    public TabBanksPresenter(Context context){
        this.context = context;
    }

    private Context context;
    private List<BankModel> banks;
    private List<BankViewModel> banksView =new ArrayList<BankViewModel>();
    private BanksAdapter adapter;

    public BanksAdapter getAdapter(){
        return adapter = new BanksAdapter( context , banksView, this);
    }

//    public void  LoadingFromSettings(){//как один
//        banksView = TransferBanksInBankView.Transfer(
//                Settings.getBanks(context),
//                Settings.getToSelectedCurrency(context),
//                context
//        );
//        Collections.sort(banksView,BankViewModel.bankCurrencyModelComparator);
//    }
//    public void startCitiesPresenter(){//как один
//        //DataService.getInstance().addListener(this);
//        //DataService.getInstance().CitiesDownload();
//        DataProvider.getInstance().addListener(this);
//        DataProvider.getInstance().getCities();
//    }

//    public void startBanksPresenter(){//как один
//        DataProvider.getInstance().addListener(this);
//        //DataService.getInstance().BanksDownload(String.valueOf(Settings.getTheSelectedCityID(context)));
//        DataProvider.getInstance().getBanks();
//
//    }

    public void ButtonSortCurrency(EExchangeAction mode){
        if (banksView != null){
            for (BankViewModel bankView :banksView){
                bankView.setCurrencyOf(mode);
            }
            Collections.sort(banksView,BankViewModel.bankCurrencyModelComparator );
            if(mode == EExchangeAction.EURSell || mode == EExchangeAction.USDSell){
                banksView = CoupBanksView.Coup(banksView);
            }
            UpdateAdapter();
        }
    }

    public void GoToMapGoogleActivity(String selectedBank){
        Intent intent = new Intent(context, MapGoogleActivity.class);
        intent.putExtra("SelectedBank",selectedBank);
        context.startActivity(intent);
    }

    private void UpdateAdapter(){
        if (adapter != null) {
            adapter.clear();
            adapter.addAll(this.banksView);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void didReceiveBanks(List<BankModel> banks) {
        this.banks = banks;
        DataProvider.getInstance().addListener(this);
        DataProvider.getInstance().getReceiveSelectCurrencyForSorting();
        Log.i("TabBanksPresenter","didReceiveBanks");
    }

    @Override
    public void didReceiveSelectCurrencyForSorting(EExchangeAction mode) {
//        TabBanksPresenter tabBanksPresenter = (TabBanksPresenter) context;вызов выбора кнобки по нажатию
//        tabBanksPresenter.selectBattonCurrency(mode);
        banksView = TransferBanksInBankView.Transfer(banks, mode);
        Collections.sort(banksView,BankViewModel.bankCurrencyModelComparator );
        UpdateAdapter();
        Log.i("TabBanksPresenter","didReceiveSelectCurrencyForSorting");
    }

    @Override
    public void didReceiveCities(List<CityModel> cities) {
        Log.i("TabBanksPresenter","didReceiveCities");
    }

    @Override
    public void didReceiveTheSelectedCity(CityModel city) {
        DataProvider.getInstance().addListener(this);
        //DataService.getInstance().BanksDownload(String.valueOf(Settings.getTheSelectedCityID(context)));
        DataProvider.getInstance().getBanks(String.valueOf(city.getId()));
        Log.i("TabBanksPresenter","didReceiveTheSelectedCity");
    }

    @Override
    public void didReceiveGis2Data(Gis2Model gis2) {
        Log.i("TabBanksPresenter","didReceiveGis2Data");
    }
}