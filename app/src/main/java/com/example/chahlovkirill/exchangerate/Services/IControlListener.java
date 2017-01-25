package com.example.chahlovkirill.exchangerate.Services;

import android.content.Context;

import com.example.chahlovkirill.exchangerate.Model.BankModel;
import com.example.chahlovkirill.exchangerate.Model.CityModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chahlov.kirill on 24/01/17.
 */

public interface IControlListener {
    void onCitiesDownloaded(List<CityModel> cities);
    void onBanksDownloaded(List<BankModel> banks);
}
