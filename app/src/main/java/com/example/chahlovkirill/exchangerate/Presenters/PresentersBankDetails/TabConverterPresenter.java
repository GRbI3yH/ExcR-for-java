package com.example.chahlovkirill.exchangerate.Presenters.PresentersBankDetails;

import android.content.Context;

import com.example.chahlovkirill.exchangerate.Fragments.FragmentsBankDetails.TabСonverterFragment;

/**
 * Created by chahlov.kirill on 08/02/17.
 */

public class TabConverterPresenter {
    public TabConverterPresenter(Context context, TabСonverterFragment fragment){
        tabСonverterFragment = fragment;
        this.context = context;
    }

    private TabСonverterFragment tabСonverterFragment;
    private Context context;
}
