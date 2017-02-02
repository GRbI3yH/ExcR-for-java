package com.example.chahlovkirill.exchangerate.Services;

import com.example.chahlovkirill.exchangerate.Model.BankViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chahlov.kirill on 02/02/17.
 */

public class CoupBanksCurrency {

    public static List<BankViewModel> Coup(List<BankViewModel> banks){
        List<BankViewModel> banksCoup = new ArrayList<BankViewModel>();
        for (int i = banks.size()-1;i >= 0 ;i--){
            banksCoup.add(banks.get(i));
        }
        return banksCoup;
    }

}
