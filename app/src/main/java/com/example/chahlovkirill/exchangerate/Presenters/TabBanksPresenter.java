package com.example.chahlovkirill.exchangerate.Presenters;

import android.content.Context;
import android.widget.ListView;

import com.example.chahlovkirill.exchangerate.Adapters.BanksAdapter;
import com.example.chahlovkirill.exchangerate.AppSetting.Setting;
import com.example.chahlovkirill.exchangerate.Model.BankCurrencyModel;
import com.example.chahlovkirill.exchangerate.Model.BankModel;
import com.example.chahlovkirill.exchangerate.Model.EExchangeAction;
import com.example.chahlovkirill.exchangerate.R;
import com.example.chahlovkirill.exchangerate.Services.SortBanks;
import com.example.chahlovkirill.exchangerate.Services.cash2cashAPI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by chahlov.kirill on 23/01/17.
 */

public class TabBanksPresenter {

    public TabBanksPresenter(Context context){
        this.context = context;
    }

    private Context context;
    private List<BankModel> BanksModel;
    private List<BankCurrencyModel> banksCurrency ;
    private BanksAdapter adapter;

    public BanksAdapter getAdapter(){
        return adapter = new BanksAdapter( context , banksCurrency);
    }

    public void LoadModelOfSetting(){
        banksCurrency = SortBanks.Sort( //достаем из настроек и сортируем
                Setting.getBanks(context),
                Setting.getSelectCurrency(context),
                context
        );
    }

    public void DownloadModelOfServices(){
        cash2cashAPI.getCallBanksModel(Setting.getselectCity(context),new Callback<List<BankModel>>(){
            @Override
            public void onResponse(Call<List<BankModel>> call, Response<List<BankModel>> response) {
                if (response.isSuccessful() && response.body() != null){
                    BanksModel = response.body() ;

                    Setting.setBanks(BanksModel,context);
                    banksCurrency = SortBanks.Sort(
                            BanksModel,
                            Setting.getSelectCurrency(context),
                            context
                    );
                    adapter = new BanksAdapter( context , banksCurrency);
                }
            }

            @Override
            public void onFailure(Call<List<BankModel>> call, Throwable t) {

            }
        });
    }
    public void ButtonSortCurrency(EExchangeAction mode){
        List<BankCurrencyModel> banksCurrencyEdit = new ArrayList<BankCurrencyModel>();
        for (BankCurrencyModel bankCurrency :banksCurrency){
            bankCurrency.setCurrencyOf(mode);
            banksCurrencyEdit.add(bankCurrency);
        }
        Collections.sort(banksCurrencyEdit);
        banksCurrency = banksCurrencyEdit;
        adapter.notifyDataSetChanged();
    }
}