package com.example.chahlovkirill.exchangerate.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by chahlov.kirill on 19/01/17.
 */

public class BankViewModel extends BankModel implements Serializable {

    public BankViewModel(BankModel bankmodel, EExchangeAction mode){

        this.setBankId(bankmodel.getBankId());
        this.setName(bankmodel.getName());
        this.setEURBuy(bankmodel.getEURBuy());
        this.setEURSell(bankmodel.getEURSell());
        this.setUSDBuy(bankmodel.getUSDBuy());
        this.setUSDSell(bankmodel.getUSDSell());
        this.setMessage(bankmodel.getMessage());
        switch (mode)
        {
            case EURBuy:
                setCurrency(this.getEURBuy());
                break;
            case EURSell:
                setCurrency(this.getEURSell());
                break;
            case USDBuy:
                setCurrency(this.getUSDBuy());
                break;
            case USDSell:
                setCurrency(this.getUSDSell());
                break;
        }
    }

    @SerializedName("Currency")
    @Expose
    private double Currency;
    public double getCurrency() {
        return this.Currency;
    }
    public void setCurrency(double Currency) {
        this.Currency = Currency;
    }

    public int compareTo(BankViewModel o) {
        if (this.getCurrency()<o.getCurrency()){
            return -1;
        }
        else if(this.getCurrency()>o.getCurrency()){
            return 1;
        }
        return 0;
    }
    public static Comparator<BankViewModel> bankCurrencyModelComparator = new Comparator<BankViewModel>() {
        @Override
        public int compare(BankViewModel o1, BankViewModel o2) {
            Double C1 = o1.getCurrency();
            Double C2 = o2.getCurrency();
            return C1.compareTo(C2);
        }
    };

    public void setCurrencyOf(EExchangeAction mode){
        switch (mode)
        {
            case EURBuy:
                setCurrency(this.getEURBuy());
                break;

            case EURSell:
                setCurrency(this.getEURSell());
                break;

            case USDBuy:
                setCurrency(this.getUSDBuy());
                break;

            case USDSell:
                setCurrency(this.getUSDSell());
                break;
        }
    }
}
