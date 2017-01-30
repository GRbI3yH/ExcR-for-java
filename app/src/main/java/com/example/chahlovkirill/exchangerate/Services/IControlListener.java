package com.example.chahlovkirill.exchangerate.Services;

import com.example.chahlovkirill.exchangerate.Model.BankModel;
import com.example.chahlovkirill.exchangerate.Model.CityModel;
import com.example.chahlovkirill.exchangerate.Model.Gis2Model.Gis2Model;

import java.util.List;

/**
 * Created by chahlov.kirill on 24/01/17.
 */

public interface IControlListener {
    void onCitiesDownloaded(List<CityModel> cities);
    void onBanksDownloaded(List<BankModel> banks);
    void onGis2DataSearchDownload(Gis2Model Gis2);
}
