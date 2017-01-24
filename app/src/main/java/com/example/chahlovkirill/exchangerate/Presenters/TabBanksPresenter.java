package com.example.chahlovkirill.exchangerate.Presenters;

import android.content.Context;
import android.widget.ListView;

import com.example.chahlovkirill.exchangerate.Adapters.BanksAdapter;
import com.example.chahlovkirill.exchangerate.AppSetting.Setting;
import com.example.chahlovkirill.exchangerate.Fragments.TabBanksFragment;
import com.example.chahlovkirill.exchangerate.Model.BankCurrencyModel;
import com.example.chahlovkirill.exchangerate.Model.BankModel;
import com.example.chahlovkirill.exchangerate.Model.EExchangeAction;
import com.example.chahlovkirill.exchangerate.R;
import com.example.chahlovkirill.exchangerate.Services.ControlListener;
import com.example.chahlovkirill.exchangerate.Services.SortBanks;
import com.example.chahlovkirill.exchangerate.Services.cash2cashAPI;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by chahlov.kirill on 23/01/17.
 */

public class TabBanksPresenter {//implements ControlListener {

    public TabBanksPresenter(Context context){
        this.context = context;
    }

    private Context context;
    private List<BankModel> banks;
    private List<BankCurrencyModel> banksCurrency ;
    private BanksAdapter adapter;

    public BanksAdapter getAdapter(){
        return adapter = new BanksAdapter( context , banksCurrency);
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

        Setting.setBanks(banks,context);
        banksCurrency = SortBanks.Sort(
                banks,
                Setting.getSelectCurrency(context),
                context
        );
        adapter.clear();
        adapter.addAll(banksCurrency);
        adapter.notifyDataSetChanged();
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

//    @Override
//    public void onDataChanged(int dataAddedCount) {
//        DownloadModelOfServices();
//    }
//    public static void Maka(){
//
//        adapter.clear();
//        adapter.addAll(banksCurrency);
//        adapter.notifyDataSetChanged();
//    }
}