package com.example.chahlovkirill.exchangerate.Fragments.FragmentsBankDetails;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.chahlovkirill.exchangerate.DataProvider.DataProvider;
import com.example.chahlovkirill.exchangerate.Model.BankViewModel;
import com.example.chahlovkirill.exchangerate.Model.CityModel;
import com.example.chahlovkirill.exchangerate.Model.EDistans;
import com.example.chahlovkirill.exchangerate.Model.Gis2Model.Result;
import com.example.chahlovkirill.exchangerate.Presenters.PresentersBankDetails.TabBankDetailsSearchPresenters;
import com.example.chahlovkirill.exchangerate.R;


import java.util.ArrayList;

/**
 * Created by chahlov.kirill on 13/02/17.
 */

public class TabBankDetailsSearchFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    public TabBankDetailsSearchFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static TabBankDetailsSearchFragment newInstance(int sectionNumber) {
        TabBankDetailsSearchFragment fragment = new TabBankDetailsSearchFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    private String request;
    private String filter;
    private View view;
    private TabBankDetailsSearchPresenters tabBankDetailsSearchPresenters;
    private BankViewModel bankView;
    private CityModel city;
    private Button buttonSelectDistance;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tab_bank_details_search, container, false);
        tabBankDetailsSearchPresenters = new TabBankDetailsSearchPresenters(getContext(),bankView,city,request,filter);
        ListView cashMachineslistView = (ListView)view.findViewById(R.id.bankdetailssearch_listview);
        cashMachineslistView.setAdapter(tabBankDetailsSearchPresenters.getAdapter());
        TextView TextViewShowAll = (TextView) view.findViewById(R.id.bankdetailssearch_ShowAll);
        buttonSelectDistance =(Button)view.findViewById(R.id.bankdetailssearch_select_distance);
        buttonSelectDistance.setOnClickListener(setDistClickListener);
        TextViewShowAll.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                tabBankDetailsSearchPresenters.ClickResultsAll();
            }
        });
        return view;
    }

    View.OnClickListener setDistClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String[] distarr = {
                    getResources().getString(R.string.dialog_all),
                    getResources().getString(R.string.dialog_1km),
                    getResources().getString(R.string.dialog_3km),
                    getResources().getString(R.string.dialog_5km)
            };
            new AlertDialog.Builder(getActivity())
                    .setTitle(R.string.dialog_title)
                    .setItems(distarr, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            EDistans DistansMode = EDistans.all;
                            switch (which) {
                                case 0:
                                    DistansMode = EDistans.all;
                                    buttonSelectDistance.setText(R.string.all);
                                    break;
                                case 1:
                                    DistansMode = EDistans.km1;
                                    buttonSelectDistance.setText(R.string.km1);
                                    break;
                                case 2:
                                    DistansMode = EDistans.km3;
                                    buttonSelectDistance.setText(R.string.km3);
                                    break;
                                case 3:
                                    DistansMode = EDistans.km5;
                                    buttonSelectDistance.setText(R.string.km5);
                                    break;
                                default:
                                    break;
                            }
                            tabBankDetailsSearchPresenters.onSelectDistance(DistansMode , 1);
                            onResume();
                        }
                    }).show();
        }
    };

    @Override
    public void onDestroy() {
        DataProvider.getInstance().removeListener(tabBankDetailsSearchPresenters);
        Log.e("TabBankDetailsSearch","onDestroy");
        super.onDestroy();
    }
    @Override
    public void onResume() {

        super.onResume();
        Log.d("TabBankDetailsSearch", "onResume");
    }
    @Override
    public void onDestroyView() {
        tabBankDetailsSearchPresenters.setGis2Results(new ArrayList<Result>());
        super.onDestroyView();
        Log.d("TabBankDetailsSearch", "onDestroyView");
    }
    public void setBankDetailsArgument(BankViewModel bankView, CityModel city, String request, String filter){
        this.filter = filter;
        this.request = request;
        this.bankView = bankView;
        this.city = city;
    }
}
