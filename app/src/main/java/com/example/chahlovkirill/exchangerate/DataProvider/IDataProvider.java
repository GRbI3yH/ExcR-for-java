package com.example.chahlovkirill.exchangerate.DataProvider;

import com.example.chahlovkirill.exchangerate.Model.BankModel;
import com.example.chahlovkirill.exchangerate.Model.CityModel;
import com.example.chahlovkirill.exchangerate.Model.EDistans;
import com.example.chahlovkirill.exchangerate.Model.EExchangeAction;

import java.util.List;

/**
 * Created by chahlov.kirill on 03/02/17.
 */

public interface IDataProvider {
    public void getBanks(String selectCity);
    public void setBanks(List<BankModel> banks);

    public void getReceiveSelectedCurrencyForSorting();
    public void setReceiveSelectedCurrencyForSorting(EExchangeAction mode);

    public void getCities();
    public void setCities(List<CityModel> cities);

    public void getTheSelectedCity();
    public void setTheSelectedCity(CityModel city);

    public void getGis2Data (String whatBank, String where, int page);

    public void onSelectDistance(EDistans mode, int whom);
}
