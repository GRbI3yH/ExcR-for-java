package com.example.chahlovkirill.exchangerate.Presenters.PresentersBankDetails;

import android.content.Context;

import com.example.chahlovkirill.exchangerate.Fragments.FragmentsBankDetails.TabСonverterFragment;
import com.example.chahlovkirill.exchangerate.Model.BankViewModel;
import com.example.chahlovkirill.exchangerate.Model.EExchangeAction;

/**
 * Created by chahlov.kirill on 08/02/17.
 */

public class TabConverterPresenter {
    public TabConverterPresenter(Context context, TabСonverterFragment fragment,BankViewModel bankView){
        tabСonverterFragment = fragment;
        this.context = context;
        this.bankView = bankView;
    }

    public BankViewModel getBankView() {
        return bankView;
    }

    public String Convert(String s, EExchangeAction mode){
        if (bankView != null){
            switch (mode){
                case EURBuy:
                    return String.format("%.2f",Double.valueOf(s.toString())   *   bankView.getEURBuy());
                case EURSell:
                    return String.format("%.2f",Double.valueOf(s.toString())  /   bankView.getEURSell());
                case USDBuy:
                    return String.format("%.2f",Double.valueOf(s.toString())   *   bankView.getUSDBuy());
                case USDSell:
                    return String.format("%.2f",Double.valueOf(s.toString())  /   bankView.getUSDSell());
            }
        }
        return "00,00";
    }

    private BankViewModel bankView;
    private TabСonverterFragment tabСonverterFragment;
    private Context context;

}
