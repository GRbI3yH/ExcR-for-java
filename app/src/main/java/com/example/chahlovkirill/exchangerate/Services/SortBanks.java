package com.example.chahlovkirill.exchangerate.Services;

import android.content.Context;

import com.example.chahlovkirill.exchangerate.AppSetting.Setting;
import com.example.chahlovkirill.exchangerate.Model.BankCurrencyModel;
import com.example.chahlovkirill.exchangerate.Model.BankModel;
import com.example.chahlovkirill.exchangerate.Model.EExchangeAction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by chahlov.kirill on 20/01/17.
 */


public class SortBanks{

    public static List<BankCurrencyModel> Sort(List<BankModel> Banks, EExchangeAction mode, Context context){
        List<BankCurrencyModel> BanksCurrency = new ArrayList<BankCurrencyModel>()  ;
        if (Banks != null){
            //Collections.sort(Banks);
            switch (mode)
            {
                case EURBuy:
                    Setting.setSelectCurrency(EExchangeAction.EURBuy,context)	;					//настройки
                    break;

                case EURSell:
                    Setting.setSelectCurrency(EExchangeAction.EURSell,context)	;
                    break;

                case USDBuy:
                    Setting.setSelectCurrency(EExchangeAction.USDBuy,context)	;
                    break;

                case USDSell:
                    Setting.setSelectCurrency(EExchangeAction.USDSell,context)	;
                    break;
            }
            for (BankModel bank : Banks)
            {
                BanksCurrency.add(new BankCurrencyModel(bank, mode));
            }
            Collections.sort(BanksCurrency,BankCurrencyModel.bankCurrencyModelComparator );
        }
        return BanksCurrency;
    }
}
