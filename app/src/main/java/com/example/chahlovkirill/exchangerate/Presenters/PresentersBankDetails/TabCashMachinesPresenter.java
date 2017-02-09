package com.example.chahlovkirill.exchangerate.Presenters.PresentersBankDetails;

import android.content.Context;
import android.location.Location;
import android.util.Log;
import android.widget.Adapter;

import com.example.chahlovkirill.exchangerate.Adapters.CashMachinesAdapter;
import com.example.chahlovkirill.exchangerate.DataProvider.DataProvider;
import com.example.chahlovkirill.exchangerate.DataProvider.IDataProviderOutput;
import com.example.chahlovkirill.exchangerate.Fragments.FragmentsBankDetails.TabСashMachinesFragment;
import com.example.chahlovkirill.exchangerate.Model.BankModel;
import com.example.chahlovkirill.exchangerate.Model.BankViewModel;
import com.example.chahlovkirill.exchangerate.Model.CityModel;
import com.example.chahlovkirill.exchangerate.Model.EExchangeAction;
import com.example.chahlovkirill.exchangerate.Model.Gis2Model.Gis2Model;
import com.example.chahlovkirill.exchangerate.Model.Gis2Model.Result;
import com.example.chahlovkirill.exchangerate.Services.ListenerLocation;
import com.example.chahlovkirill.exchangerate.Services.Operations2GISModel;
import com.example.chahlovkirill.exchangerate.Services.SortByDistance;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by chahlov.kirill on 08/02/17.
 */

public class TabCashMachinesPresenter implements IDataProviderOutput {
    public TabCashMachinesPresenter(Context context, TabСashMachinesFragment fragment, BankViewModel bankView){
        tabСashMachinesFragment = fragment;
        this.context = context;
        this.bankView = bankView;
        DataProvider.getInstance().addListener(this);
        DataProvider.getInstance().getTheSelectedCity();
    }

    private BankViewModel bankView;
    private TabСashMachinesFragment tabСashMachinesFragment;
    private Context context;
    private CashMachinesAdapter adapter;
    private List<Result> gis2Result = new ArrayList<Result>();
    //private List<Result> result = new ArrayList<Result>();
    private String what ="Банкоматы_";
    private int page = 1;
    private CityModel city;

    public CashMachinesAdapter getAdapter(){
        return adapter = new CashMachinesAdapter( context, new ArrayList<Result>(), this);
    }

    private List<Result> SortGis2(List<Result> gis2Result){
        Location location = ListenerLocation.getImHere();
        location.getLatitude();
        location.getLongitude();
        return SortByDistance.Sort(location.getLatitude(),location.getLongitude(),gis2Result);
    }

    private void UpdateAdapter(List<Result> gis2Result){
        if (adapter != null) {
            adapter.clear();
            adapter.addAll(gis2Result);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void didReceiveGis2Data(Gis2Model gis2) {
        if(!gis2.getResponse_code().equals("400")){
            if(gis2.getWhat().equals(what+bankView.getName())){
                if(gis2.getresult()!= null){
                    int sizeResult = gis2.getresult().size();
                    if (sizeResult == 50 & sizeResult != 0){
                        page++;
                        DataProvider.getInstance().getGis2Data(what+bankView.getName(),city.getName(),page);
                    }

                    gis2 = Operations2GISModel.VerificationDoNotMatchTheName(
                            Operations2GISModel.CheckMismatchRubric(gis2,"Банкоматы"),bankView.getName());
                    if (gis2.getresult() != null){
                        for (Result result:gis2.getresult()) {
                            gis2Result.add(result);
                            Log.e("Оставшийся элемент = ",result.getName());
                        }
                    }
                    UpdateAdapter(SortGis2(gis2Result));
                }
            }
        }
    }

    @Override
    public void didReceiveCities(List<CityModel> cities) {

    }

    @Override
    public void didReceiveTheSelectedCity(CityModel city) {
        this.city = city;
        DataProvider.getInstance().getGis2Data(what+bankView.getName(),city.getName(),page);
    }

    @Override
    public void didReceiveBanks(List<BankModel> banks) {

    }

    @Override
    public void didReceiveSelectedCurrencyForSorting(EExchangeAction mode) {

    }
}