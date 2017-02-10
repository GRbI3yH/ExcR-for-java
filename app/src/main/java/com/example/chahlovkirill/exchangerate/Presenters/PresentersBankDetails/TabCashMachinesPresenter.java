package com.example.chahlovkirill.exchangerate.Presenters.PresentersBankDetails;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Adapter;

import com.example.chahlovkirill.exchangerate.Activity.MapDetailsBankActivity;
import com.example.chahlovkirill.exchangerate.Adapters.CashMachinesAdapter;
import com.example.chahlovkirill.exchangerate.DataProvider.DataProvider;
import com.example.chahlovkirill.exchangerate.DataProvider.IDataProviderOutput;
import com.example.chahlovkirill.exchangerate.Fragments.FragmentsBankDetails.TabСashMachinesFragment;
import com.example.chahlovkirill.exchangerate.Model.BankModel;
import com.example.chahlovkirill.exchangerate.Model.BankViewModel;
import com.example.chahlovkirill.exchangerate.Model.CityModel;
import com.example.chahlovkirill.exchangerate.Model.EDistans;
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

public class TabCashMachinesPresenter implements IDataProviderOutput,IDistansPresentersEvent {
    public TabCashMachinesPresenter(Context context, TabСashMachinesFragment fragment, BankViewModel bankView){
        tabСashMachinesFragment = fragment;
        this.context = context;
        this.bankView = bankView;
        DataProvider.getInstance().addListenerDistans(this);
        DataProvider.getInstance().addListener(this);
        DataProvider.getInstance().getTheSelectedCity();
    }

    private BankViewModel bankView;
    private TabСashMachinesFragment tabСashMachinesFragment;
    private Context context;
    private CashMachinesAdapter adapter;
    private List<Result> gis2Results = new ArrayList<Result>();
    private List<Result> gis2ResultsDistanse = new ArrayList<Result>();
    private String what ="Банкоматы_";
    private int page = 1;
    private CityModel city;

    public CashMachinesAdapter getAdapter(){
        return adapter = new CashMachinesAdapter( context, new ArrayList<Result>(), this);
    }

    private List<Result> SortGis2(List<Result> gis2Results){
        Location location = ListenerLocation.getImHere();
        location.getLatitude();
        location.getLongitude();
        return SortByDistance.Sort(location.getLatitude(),location.getLongitude(),gis2Results);
    }

    private void UpdateAdapter(List<Result> gis2Results){
        if (adapter != null) {
            adapter.clear();
            adapter.addAll(gis2Results);
            adapter.notifyDataSetChanged();
        }
    }

    public void ClickItem(List<Result> gis2Results){
        Intent intent = new Intent(context, MapDetailsBankActivity.class);
        intent.putParcelableArrayListExtra("gis2Results",(ArrayList<? extends Parcelable>) gis2Results);
        intent.putExtra("bankView",bankView);
        context.startActivity(intent);
    }

    public void ClickResultsAll(){
        Intent intent = new Intent(context, MapDetailsBankActivity.class);
        intent.putExtra("gis2Results", (ArrayList<? extends Parcelable>) gis2Results);
        intent.putExtra("bankView",bankView);
        context.startActivity(intent);
    }

    @Override
    public void didReceiveGis2Data(Gis2Model gis2) {
        if(!gis2.getResponse_code().equals("400") & bankView != null){
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
                            gis2Results.add(result);
                            Log.e("Оставшийся элемент = ",result.getName());
                        }
                    }
                    UpdateAdapter(SortGis2(gis2Results));
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
        if(city != null & bankView != null){
            DataProvider.getInstance().getGis2Data(what+bankView.getName(),city.getName(),page);
        }
    }

    @Override
    public void didReceiveBanks(List<BankModel> banks) {

    }

    @Override
    public void didReceiveSelectedCurrencyForSorting(EExchangeAction mode) {

    }

    @Override
    public void onSelectDistance(EDistans mode, int whom) {
        gis2ResultsDistanse.clear();
        if (whom == 1){
            for (Result result:gis2Results){
                switch (mode){
                    case all:
                        gis2ResultsDistanse.add(result);
                        break;
                    case km1:
                        if(result.getDistances() < 1.0){
                            gis2ResultsDistanse.add(result);
                        }
                        break;
                    case km3:
                        if(result.getDistances() < 3.0){
                            gis2ResultsDistanse.add(result);
                        }
                        break;
                    case km5:
                        if(result.getDistances() < 5.0){
                            gis2ResultsDistanse.add(result);
                        }
                        break;
                }
            }
            UpdateAdapter(gis2ResultsDistanse);
        }
    }
}