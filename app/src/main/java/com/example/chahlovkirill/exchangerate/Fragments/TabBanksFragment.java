package com.example.chahlovkirill.exchangerate.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;

import com.example.chahlovkirill.exchangerate.Adapters.BanksAdapter;
import com.example.chahlovkirill.exchangerate.AppSetting.Setting;
import com.example.chahlovkirill.exchangerate.Model.BankCurrencyModel;
import com.example.chahlovkirill.exchangerate.Model.BankModel;
import com.example.chahlovkirill.exchangerate.Model.EExchangeAction;
import com.example.chahlovkirill.exchangerate.Presenters.TabBanksPresenter;
import com.example.chahlovkirill.exchangerate.R;
import com.example.chahlovkirill.exchangerate.Services.SortBanks;
import com.example.chahlovkirill.exchangerate.Services.cash2cashAPI;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by chahlov.kirill on 23/01/17.
 */

public class TabBanksFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    public TabBanksFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static TabBanksFragment newInstance(int sectionNumber) {
        TabBanksFragment fragment = new TabBanksFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    private ListView listViewBanks;

    private View rootView;
    private Button buttonRUBToUSD;
    private Button buttonUSDToRUB;
    private Button buttonRUBtoEUR;
    private Button buttonEURtoRUB;
    private TabBanksPresenter banksPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.banks_tab_exc_rate, container, false);
        buttonRUBToUSD = (RadioButton)rootView.findViewById(R.id.RUBToUSD);
        buttonUSDToRUB = (RadioButton)rootView.findViewById(R.id.USDToRUB);
        buttonRUBtoEUR = (RadioButton)rootView.findViewById(R.id.RUBtoEUR);
        buttonEURtoRUB = (RadioButton)rootView.findViewById(R.id.EURtoRUB);
        listViewBanks = (ListView)rootView.findViewById(R.id.lvBankM);

        switch (Setting.getSelectCurrency(getContext())){
            case USDBuy:
                buttonRUBToUSD.setSelected(true);
            break;
            case USDSell:
                buttonUSDToRUB.setSelected(true);
            break;
            case EURBuy:
                buttonRUBtoEUR.setSelected(true);
            break;
            case EURSell:
                buttonEURtoRUB.setSelected(true);
            break;
        }
        banksPresenter = new TabBanksPresenter(getContext());
        banksPresenter.LoadModelOfSetting();
        banksPresenter.DownloadModelOfServices();
        listViewBanks.setAdapter(banksPresenter.getAdapter());

        buttonRUBToUSD.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                banksPresenter.ButtonSortCurrency(EExchangeAction.USDBuy);
                //listViewBanks.setAdapter(banksPresenter.getAdapter());
            }
        });

        buttonUSDToRUB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                banksPresenter.ButtonSortCurrency(EExchangeAction.USDSell);
            }
        });

        buttonRUBtoEUR.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                banksPresenter.ButtonSortCurrency(EExchangeAction.EURBuy);
            }
        });

        buttonEURtoRUB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                banksPresenter.ButtonSortCurrency((EExchangeAction.EURSell));
            }
        });



        return rootView;
    }
}
