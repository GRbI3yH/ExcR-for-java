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

    public static List<BankViewModel> Transfer(List<BankModel> banks, EExchangeAction mode){
        List<BankViewModel> banksView = new ArrayList<BankViewModel>()  ;
        if (banks != null){
            switch (mode)
            {
                case EURBuy:
                    Settings.setTheSelectedCurrency(EExchangeAction.EURBuy);
                    break;

                case EURSell:
                    Settings.setTheSelectedCurrency(EExchangeAction.EURSell);
                    break;

                case USDBuy:
                    Settings.setTheSelectedCurrency(EExchangeAction.USDBuy);
                    break;

                case USDSell:
                    Settings.setTheSelectedCurrency(EExchangeAction.USDSell);
                    break;
            }
            for (BankModel bank : banks)
            {
                banksView.add(new BankViewModel(bank, mode));
            }
        }
        return banksView;
    }
}
