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

    public static List<BankCurrencyModel> Sort(List<BankModel> BM, EExchangeAction mode, Context context){
        List<BankCurrencyModel> banksVM = new ArrayList<BankCurrencyModel>()  ;
        if (BM != null){
            Collections.sort(BM);
            switch (mode)
            {
                case EURBuy:
                    Setting.setCurrency(EExchangeAction.EURBuy,context)	;					//настройки
                    break;

                case EURSell:
                    Setting.setCurrency(EExchangeAction.EURSell,context)	;
                    break;

                case USDBuy:
                    Setting.setCurrency(EExchangeAction.USDBuy,context)	;
                    break;

                case USDSell:
                    Setting.setCurrency(EExchangeAction.USDSell,context)	;
                    break;
            }
            for (BankModel bank : BM)
            {
                banksVM.add(new BankCurrencyModel(bank, mode));
            }
        }
        return banksVM;
    }
}
