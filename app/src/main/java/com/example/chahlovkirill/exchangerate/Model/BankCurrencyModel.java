package com.example.chahlovkirill.exchangerate.Model;

import com.example.chahlovkirill.exchangerate.Services.SortBanks;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by chahlov.kirill on 19/01/17.
 */

public class BankCurrencyModel extends BankModel {

    public BankCurrencyModel(BankModel bankmodel, EExchangeAction mode){

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
}
