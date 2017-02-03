package com.example.chahlovkirill.exchangerate.DataProvider;

import com.example.chahlovkirill.exchangerate.Model.BankModel;
import com.example.chahlovkirill.exchangerate.Model.CityModel;
import com.example.chahlovkirill.exchangerate.Model.EExchangeAction;
import com.example.chahlovkirill.exchangerate.Model.Gis2Model.Gis2Model;

import java.util.List;

/**
 * Created by chahlov.kirill on 03/02/17.
 */

public interface IDataProviderOutput{// extends ICitiesDataProviderOutput ,IBanksDataProviderOutput, IGis2DataProviderOutput {

    //public List<CityModel> didReceiveCities();
    public void didReceiveCities(List<CityModel> cities);

    //public CityModel didReceiveTheSelectedCity();
    public void didReceiveTheSelectedCity(CityModel city);

    //public List<BankModel> didReceiveBanks();
    public void didReceiveBanks(List<BankModel> banks);

    //public EExchangeAction didReceiveSelectCurrencyForSorting();
    public void didReceiveSelectCurrencyForSorting(EExchangeAction mode);

    public void didReceiveGis2Data (Gis2Model gis2);
}