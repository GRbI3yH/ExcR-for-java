package com.example.chahlovkirill.exchangerate.Presenters.PresentersBankDetails;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Parcelable;
import android.util.Log;

import com.example.chahlovkirill.exchangerate.Activity.MapDetailsBankActivity;
import com.example.chahlovkirill.exchangerate.Adapters.BankDetailsSearchAdapter;
import com.example.chahlovkirill.exchangerate.DataProvider.DataProvider;
import com.example.chahlovkirill.exchangerate.DataProvider.IDataProviderOutput;
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
import java.util.List;

/**
 * Created by chahlov.kirill on 13/02/17.
 */

public class TabBankDetailsSearchPresenters implements IDataProviderOutput{//,IDistansPresentersEvent {
    public TabBankDetailsSearchPresenters(Context context, BankViewModel bankView, CityModel city, String request, String filter) {
        this.context = context;
        this.bankView = bankView;
        this.request = request;
        this.filter = filter;
        this.city = city;
        DataProvider.getInstance().addListener(this);
        DataProvider.getInstance().getGis2Data(this.request + " " + bankView.getName(), city.getName(), page, this.request);
    }

    private BankViewModel bankView;
    private Context context;
    private BankDetailsSearchAdapter adapter;

    public void setGis2Results(List<Result> gis2Results) {
        this.gis2Results = gis2Results;
    }

    private List<Result> gis2Results = new ArrayList<Result>();
    private List<Result> filtered2GisResults = new ArrayList<Result>();
    private String request;
    private String filter;
    private int page = 1;
    private CityModel city;

    public BankDetailsSearchAdapter getAdapter() {
        return adapter = new BankDetailsSearchAdapter(context, new ArrayList<Result>(), this);
    }

    private List<Result> SortGis2(List<Result> gis2Results) {
        Location location = ListenerLocation.getImHere();
        if (location != null) {
            location.getLatitude();
            location.getLongitude();
            return SortByDistance.Sort(location.getLatitude(), location.getLongitude(), gis2Results);
        }
        return gis2Results;
    }

    private void UpdateAdapter(List<Result> gis2Results) {
        if (adapter != null) {
            adapter.clear();
            adapter.addAll(gis2Results);
            adapter.notifyDataSetChanged();
        }
    }

    public void ClickItem(List<Result> gis2Results) {
        Intent intent = new Intent(context, MapDetailsBankActivity.class);
        intent.putParcelableArrayListExtra("gis2Results", (ArrayList<? extends Parcelable>) gis2Results);
        intent.putExtra("bankView", bankView);
        context.startActivity(intent);
    }

    public void ClickResultsAll() {
        Intent intent = new Intent(context, MapDetailsBankActivity.class);
        intent.putParcelableArrayListExtra("gis2Results", (ArrayList<? extends Parcelable>) adapter.getGis2Results());
        intent.putExtra("bankView", bankView);
        context.startActivity(intent);
    }

    @Override
    public void didReceiveGis2Data(Gis2Model gis2 ,String request) {
        if (!gis2.getResponse_code().equals("400") & bankView != null) {
            if (this.request.equals(request)) {
                if (gis2.getresult() != null) {
                    int sizeResult = gis2.getresult().size();
                    if (sizeResult == 50 & sizeResult != 0) {
                        page++;
                        DataProvider.getInstance().getGis2Data(this.request + " " + bankView.getName(), city.getName(), page, this.request);
                    }
                    gis2 = Operations2GISModel.VerificationDoNotMatchTheName(
                            Operations2GISModel.CheckMismatchRubric(gis2, filter), bankView.getName());
                    if (gis2.getresult() != null) {
                        for (Result result : gis2.getresult()) {
                            gis2Results.add(result);
                            Log.e("Оставшийся элемент = ", result.getName());
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
    }

    @Override
    public void didReceiveBanks(List<BankModel> banks) {

    }

    @Override
    public void didReceiveSelectedCurrencyForSorting(EExchangeAction mode) {

    }

    public void onSelectDistance(EDistans mode, int whom) {
        filtered2GisResults.clear();
        if (whom == 1) {
            for (Result result : gis2Results) {
                switch (mode) {
                    case all:
                        if (result.getDistances()!= null){
                            filtered2GisResults.add(result);
                        }
                        break;
                    case km1:
                        if (result.getDistances()!= null){
                            if (result.getDistances() < 1.0) {
                                filtered2GisResults.add(result);
                            }
                        }
                        break;
                    case km3:
                        if (result.getDistances()!= null){
                            if (result.getDistances() < 3.0) {
                                filtered2GisResults.add(result);
                            }
                        }
                        break;
                    case km5:
                        if (result.getDistances()!= null){
                            if (result.getDistances() < 5.0) {
                                filtered2GisResults.add(result);
                            }
                        }
                        break;
                }
            }
            UpdateAdapter(filtered2GisResults);
        }
    }
}