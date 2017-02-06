package com.example.chahlovkirill.exchangerate.DataProvider;

import com.example.chahlovkirill.exchangerate.Model.BankModel;
import com.example.chahlovkirill.exchangerate.Model.CityModel;
import com.example.chahlovkirill.exchangerate.Model.EExchangeAction;
import com.example.chahlovkirill.exchangerate.Model.Gis2Model.Gis2Model;

import java.util.List;

/**
 * Created by chahlov.kirill on 03/02/17.
 */

public interface IDataProviderOutput{

    public void didReceiveCities(List<CityModel> cities);

    public void didReceiveTheSelectedCity(CityModel city);

    public void didReceiveBanks(List<BankModel> banks);

    public void didReceiveSelectCurrencyForSorting(EExchangeAction mode);

    public void didReceiveGis2Data (Gis2Model gis2);
}