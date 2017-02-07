package com.example.chahlovkirill.exchangerate.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RadioButton;

import com.example.chahlovkirill.exchangerate.AppSetting.Settings;
import com.example.chahlovkirill.exchangerate.DataProvider.DataProvider;
import com.example.chahlovkirill.exchangerate.Model.EExchangeAction;
import com.example.chahlovkirill.exchangerate.Presenters.TabBanksPresenter;
import com.example.chahlovkirill.exchangerate.R;

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

    private View view;
    private RadioButton buttonRUBToUSD;
    private RadioButton buttonUSDToRUB;
    private RadioButton buttonRUBtoEUR;
    private RadioButton buttonEURtoRUB;
    private TabBanksPresenter tabBanksPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.banks_tab_exc_rate, container, false);
        buttonRUBToUSD = (RadioButton)view.findViewById(R.id.RUBToUSD);
        buttonUSDToRUB = (RadioButton)view.findViewById(R.id.USDToRUB);
        buttonRUBtoEUR = (RadioButton)view.findViewById(R.id.RUBtoEUR);
        buttonEURtoRUB = (RadioButton)view.findViewById(R.id.EURtoRUB);
        listViewBanks = (ListView)view.findViewById(R.id.lvBankM);
        tabBanksPresenter = new TabBanksPresenter(getContext(),this);
        listViewBanks.setAdapter(tabBanksPresenter.getAdapter());
        buttonRUBToUSD.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                tabBanksPresenter.ButtonSortCurrency(EExchangeAction.USDBuy);
            }
        });

        buttonUSDToRUB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                tabBanksPresenter.ButtonSortCurrency(EExchangeAction.USDSell);
            }
        });

        buttonRUBtoEUR.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                tabBanksPresenter.ButtonSortCurrency(EExchangeAction.EURBuy);
            }
        });

        buttonEURtoRUB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                tabBanksPresenter.ButtonSortCurrency((EExchangeAction.EURSell));
            }
        });
        return view;
    }

    public void selectedBattonCurrency(EExchangeAction mode){
        switch (mode){
            case USDBuy:
                buttonRUBToUSD.setChecked(true);
            break;
            case USDSell:
                buttonUSDToRUB.setChecked(true);
            break;
            case EURBuy:
                buttonRUBtoEUR.setChecked(true);
            break;
            case EURSell:
                buttonEURtoRUB.setChecked(true);
            break;
        }
    }

    @Override
    public void onDestroy() {
        DataProvider.getInstance().removeListener(tabBanksPresenter);
        Log.i("onDestroy","TabBanksFragment");
        super.onDestroy();
    }
}
