package com.example.chahlovkirill.exchangerate.Services;

import android.content.Context;

import com.example.chahlovkirill.exchangerate.AppSetting.Settings;
import com.example.chahlovkirill.exchangerate.Model.BankViewModel;
import com.example.chahlovkirill.exchangerate.Model.BankModel;
import com.example.chahlovkirill.exchangerate.Model.EExchangeAction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chahlov.kirill on 20/01/17.
 */


public class TransferBanksInBankView{

    public static List<BankViewModel> Transfer(List<BankModel> Banks, EExchangeAction mode, Context context){
        List<BankViewModel> BanksCurrency = new ArrayList<BankViewModel>()  ;
        if (Banks != null){
            switch (mode)
            {
                case EURBuy:
                    Settings.setSelectCurrency(EExchangeAction.EURBuy,context);
                    break;

                case EURSell:
                    Settings.setSelectCurrency(EExchangeAction.EURSell,context);
                    break;

                case USDBuy:
                    Settings.setSelectCurrency(EExchangeAction.USDBuy,context);
                    break;

                case USDSell:
                    Settings.setSelectCurrency(EExchangeAction.USDSell,context);
                    break;
            }
            for (BankModel bank : Banks)
            {
                BanksCurrency.add(new BankViewModel(bank, mode));
            }
        }
        return BanksCurrency;
    }
}
