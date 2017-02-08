package com.example.chahlovkirill.exchangerate.Presenters.PresentersBankDetails;

import android.content.Context;

import com.example.chahlovkirill.exchangerate.Adapters.DepartmentAdapter;
import com.example.chahlovkirill.exchangerate.DataProvider.DataProvider;
import com.example.chahlovkirill.exchangerate.DataProvider.IDataProviderOutput;
import com.example.chahlovkirill.exchangerate.Fragments.FragmentsBankDetails.TabDepartmentFragment;
import com.example.chahlovkirill.exchangerate.Model.BankModel;
import com.example.chahlovkirill.exchangerate.Model.CityModel;
import com.example.chahlovkirill.exchangerate.Model.EExchangeAction;
import com.example.chahlovkirill.exchangerate.Model.Gis2Model.Gis2Model;
import com.example.chahlovkirill.exchangerate.Model.Gis2Model.Result;

import java.util.List;

/**
 * Created by chahlov.kirill on 08/02/17.
 */

public class TabDepartmentPresenter implements IDataProviderOutput {
    public TabDepartmentPresenter(Context context, TabDepartmentFragment fragment){
        tabDepartmentFragment = fragment;
        this.context = context;
        DataProvider.getInstance().getGis2Data();
    }

    private TabDepartmentFragment tabDepartmentFragment;
    private Context context;
    private DepartmentAdapter adapter;
    private List<Result> gis2Result;

    public DepartmentAdapter getAdapter(){
        return adapter = new DepartmentAdapter( context , gis2Result, this);
    }

    private void UpdateAdapter(){
        if (adapter != null) {
            adapter.clear();
            adapter.addAll(gis2Result);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void didReceiveGis2Data(Gis2Model gis2) {

    }

    @Override
    public void didReceiveCities(List<CityModel> cities) {

    }

    @Override
    public void didReceiveTheSelectedCity(CityModel city) {

    }

    @Override
    public void didReceiveBanks(List<BankModel> banks) {

    }

    @Override
    public void didReceiveSelectedCurrencyForSorting(EExchangeAction mode) {

    }
}
