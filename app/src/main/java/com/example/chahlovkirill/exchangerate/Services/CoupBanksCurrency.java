package com.example.chahlovkirill.exchangerate.Services;

import com.example.chahlovkirill.exchangerate.Model.BankCurrencyModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chahlov.kirill on 02/02/17.
 */

public class CoupBanksCurrency {
    public static List<BankCurrencyModel> Coup(List<BankCurrencyModel> banks){
        List<BankCurrencyModel> banksCoup = new ArrayList<BankCurrencyModel>();
        for (int i = banks.size()-1;i >= 0 ;i--){
            banksCoup.add(banks.get(i));
        }
        return banksCoup;
    }
}
