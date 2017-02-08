package com.example.chahlovkirill.exchangerate.Presenters.PresentersBankDetails;

import android.content.Context;

import com.example.chahlovkirill.exchangerate.Fragments.FragmentsBankDetails.TabBankDetailsFragment;

/**
 * Created by chahlov.kirill on 08/02/17.
 */

public class TabBankDetailsPresenter {

    public TabBankDetailsPresenter(Context context, TabBankDetailsFragment fragment){
        tabBankDetailsFragment = fragment;
        this.context = context;
    }

    private TabBankDetailsFragment tabBankDetailsFragment;
    private Context context;
}
