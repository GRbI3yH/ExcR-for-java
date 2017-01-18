package com.example.chahlovkirill.exchangerate.Services;
import com.example.chahlovkirill.exchangerate.Model.BankModel;
import com.example.chahlovkirill.exchangerate.Model.CitiyModel;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;

/**
 * Created by chahlov.kirill on 18/01/17.
 */

public class DataService implements IDataService {


    @Override
    public Call<List<CitiyModel>> getCitiy() {
        return null;
    }

    @Override
    public Call<List<BankModel>> getBank(@FieldMap Map<String, String> cityId) {
        return null;
    }


}
