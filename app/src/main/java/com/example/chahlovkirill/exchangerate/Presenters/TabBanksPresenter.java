package com.example.chahlovkirill.exchangerate.Presenters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.chahlovkirill.exchangerate.Activity.MainActivity;
import com.example.chahlovkirill.exchangerate.Activity.MapGoogleActivity;
import com.example.chahlovkirill.exchangerate.Adapters.BanksAdapter;
import com.example.chahlovkirill.exchangerate.DataProvider.DataProvider;
import com.example.chahlovkirill.exchangerate.DataProvider.IDataProviderOutput;
import com.example.chahlovkirill.exchangerate.Fragments.TabBanksFragment;
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

    public TabBanksPresenter(Context context,TabBanksFragment fragment){
        tabBanksFragment = fragment;
        this.context = context;
        DataProvider.getInstance().addListener(this);
        DataProvider.getInstance().getTheSelectedCity();
    }

    TabBanksFragment tabBanksFragment;
    private Context context;
    private List<BankModel> banks;
    private List<BankViewModel> banksView =new ArrayList<BankViewModel>();
    private BanksAdapter adapter;
    //private CityModel selectedCity;

    public BanksAdapter getAdapter(){
        return adapter = new BanksAdapter( context , banksView, this);
    }

    public void ButtonSortCurrency(EExchangeAction mode){
        DataProvider.getInstance().setReceiveSelectCurrencyForSorting(mode);Log.i("ButtonSortCurrency","Сохраняю");
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

    public void GoToMapGoogleActivity(BankViewModel bankGoGoogle){
        Intent intent = new Intent(context, MapGoogleActivity.class);
        //intent.putExtra("SelectedBank",selectedBank); // передовать Serializable Parcelable весь целиком
        intent.putExtra("SelectedBank",bankGoGoogle);
        context.startActivity(intent);
    }

    private void UpdateAdapter(){
        if (adapter != null) {
            adapter.clear();
            adapter.addAll(banksView);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void didReceiveBanks(List<BankModel> banks) {
        Log.i("TabBanksPresenter","didReceiveBanks");
        this.banks = banks;
        DataProvider.getInstance().getReceiveSelectCurrencyForSorting();
    }

    @Override
    public void didReceiveSelectCurrencyForSorting(EExchangeAction mode) {
        Log.i("TabBanksPresenter","didReceiveSelectCurrencyForSorting");
        tabBanksFragment.selectBattonCurrency(mode);
        banksView = TransferBanksInBankView.Transfer(banks, mode);
        Collections.sort(banksView,BankViewModel.bankCurrencyModelComparator );

        UpdateAdapter();
    }

    @Override
    public void didReceiveCities(List<CityModel> cities) {
        //Log.i("TabBanksPresenter","didReceiveCities");
    }

    @Override
    public void didReceiveTheSelectedCity(CityModel city) {
        Log.i("TabBanksPresenter","didReceiveTheSelectedCity");
        DataProvider.getInstance().getBanks(String.valueOf(city.getId()));
    }

    @Override
    public void didReceiveGis2Data(Gis2Model gis2) {
        //Log.i("TabBanksPresenter","didReceiveGis2Data");
    }
}