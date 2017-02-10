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

import com.example.chahlovkirill.exchangerate.Activity.BankDetailsActivity;
import com.example.chahlovkirill.exchangerate.DataProvider.DataProvider;
import com.example.chahlovkirill.exchangerate.Model.BankViewModel;
import com.example.chahlovkirill.exchangerate.Model.EDistans;
import com.example.chahlovkirill.exchangerate.Model.Gis2Model.Result;
import com.example.chahlovkirill.exchangerate.Presenters.PresentersBankDetails.TabCashMachinesPresenter;
import com.example.chahlovkirill.exchangerate.R;

import java.util.ArrayList;

/**
 * Created by chahlov.kirill on 08/02/17.
 */

public class TabСashMachinesFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    public TabСashMachinesFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static TabСashMachinesFragment newInstance(int sectionNumber) {
        TabСashMachinesFragment fragment = new TabСashMachinesFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    private View view;
    private TabCashMachinesPresenter tabCashMachinesPresenter;
    private BankViewModel bankView;
    private Button buttonSelectDistance;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tab_cash_machines, container, false);
        tabCashMachinesPresenter = new TabCashMachinesPresenter(getContext(),this,bankView);
        ListView cashMachineslistView = (ListView)view.findViewById(R.id.cashmachines_listview);
        cashMachineslistView.setAdapter(tabCashMachinesPresenter.getAdapter());
        TextView TextViewShowAll = (TextView) view.findViewById(R.id.cashmachines_ShowAll);
        buttonSelectDistance =(Button)view.findViewById(R.id.cashmachines_select_distance);
        buttonSelectDistance.setOnClickListener(setDistClickListener);
        TextViewShowAll.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                tabCashMachinesPresenter.ClickResultsAll();
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
                            DataProvider.getInstance().onSelectDistance(DistansMode , 1);
                            onResume();
                        }
                    }).show();
        }
    };

    @Override
    public void onDestroy() {
        DataProvider.getInstance().removeListener(tabCashMachinesPresenter);
        Log.e("onDestroy","TabСashMachinesFragment");
        super.onDestroy();
    }
    @Override
    public void onResume() {

        super.onResume();
        Log.d("TabСashMachinesFragment", "onResume");
    }
    @Override
    public void onDestroyView() {
        DataProvider.getInstance().removeListenerDistans(tabCashMachinesPresenter);
        tabCashMachinesPresenter.setGis2Results(new ArrayList<Result>());
        super.onDestroyView();
        Log.d("TabСashMachinesFragment", "onDestroyView");
    }
    public void setBank(BankViewModel bankView){
        this.bankView = bankView;
    }
}
