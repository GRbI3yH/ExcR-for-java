package com.example.chahlovkirill.exchangerate.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.chahlovkirill.exchangerate.Adapters.BanksAdapter;
import com.example.chahlovkirill.exchangerate.Adapters.CitiesAdapter;
import com.example.chahlovkirill.exchangerate.AppSetting.Setting;
import com.example.chahlovkirill.exchangerate.Model.BankCurrencyModel;
import com.example.chahlovkirill.exchangerate.Model.BankModel;
import com.example.chahlovkirill.exchangerate.Model.CityModel;
import com.example.chahlovkirill.exchangerate.Model.EExchangeAction;
import com.example.chahlovkirill.exchangerate.R;
import com.example.chahlovkirill.exchangerate.Services.SortBanks;
import com.example.chahlovkirill.exchangerate.Services.cash2cashAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by chahlov.kirill on 20/01/17.
 */

public class TabBanksActivity extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    public TabBanksActivity() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static TabBanksActivity newInstance(int sectionNumber) {
        TabBanksActivity fragment = new TabBanksActivity();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    private List<BankCurrencyModel> bankcurrencymodel = new ArrayList<BankCurrencyModel>();
    private BanksAdapter adapter ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.banks_tab_exc_rate, container, false);

        Button RUBToUSD = (Button)rootView.findViewById(R.id.RUBToUSD);
        RUBToUSD.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                for (BankCurrencyModel BCM :bankcurrencymodel){
                    BCM.setCurrencyOfBCM(EExchangeAction.USDBuy);
                }
                adapter.notifyDataSetChanged();//refresh a ListView
            }
        });

        Button USDToRUB = (Button)rootView.findViewById(R.id.USDToRUB);
        USDToRUB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                for (BankCurrencyModel BCM :bankcurrencymodel){
                    BCM.setCurrencyOfBCM(EExchangeAction.USDSell);
                }
                adapter.notifyDataSetChanged();//refresh a ListView
            }
        });

        Button RUBtoEUR = (Button)rootView.findViewById(R.id.RUBtoEUR);
        RUBtoEUR.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                for (BankCurrencyModel BCM :bankcurrencymodel){
                    BCM.setCurrencyOfBCM(EExchangeAction.EURBuy);
                }
                adapter.notifyDataSetChanged();//refresh a ListView
            }
        });

        Button EURtoRUB = (Button)rootView.findViewById(R.id.EURtoRUB);
        EURtoRUB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                for (BankCurrencyModel BCM :bankcurrencymodel){
                    BCM.setCurrencyOfBCM(EExchangeAction.EURSell);
                }
                adapter.notifyDataSetChanged();//refresh a ListView
            }
        });

        ListView LVCityM = (ListView)rootView.findViewById(R.id.lvBankM);

        bankcurrencymodel = SortBanks.Sort( //достаем из настроек и сортируем
                Setting.getBanks(getContext()),
                Setting.getCurrency(getContext()),
                getContext()
        );

        adapter = new  BanksAdapter( getContext() , bankcurrencymodel);
        if (adapter.equals(null)) LVCityM.setAdapter(adapter);

        cash2cashAPI.getCallBanksModel(Setting.getselectCity(getContext()),new Callback<List<BankModel>>(){
            @Override
            public void onResponse(Call<List<BankModel>> call, Response<List<BankModel>> response) {
                if (response.isSuccessful() && response.body() != null){
                    List<BankModel> bankmodel = response.body() ;

                    Setting.setBanks(bankmodel,getContext());
                    bankcurrencymodel = SortBanks.Sort(
                            bankmodel,
                            Setting.getCurrency(getContext()),
                            getContext()
                    );

                    ListView LVCityM = (ListView)rootView.findViewById(R.id.lvBankM);

                    adapter = new  BanksAdapter( getContext() , bankcurrencymodel);

                    LVCityM.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<BankModel>> call, Throwable t) {
                //TextView CM = (TextView)rootView.findViewById(R.id.section_label);
                //CM.setText("Something went wrong: " + t.getMessage());
            }
        });
        return rootView;
    }
//    public  void RUBToUSD(View view){
//
//    }
//    public void onClick(View view){
//        TextView helloTextView = (TextView)findViewById(R.id.button1);
//        helloTextView.setText("Hello Kitty!");
//    }
}