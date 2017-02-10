package com.example.chahlovkirill.exchangerate.Presenters.PresentersBankDetails;

import android.content.Context;

import com.example.chahlovkirill.exchangerate.Fragments.FragmentsBankDetails.TabBankDetailsFragment;
import com.example.chahlovkirill.exchangerate.Model.BankViewModel;

/**
 * Created by chahlov.kirill on 08/02/17.
 */

public class TabBankDetailsPresenter {

    public TabBankDetailsPresenter(Context context, TabBankDetailsFragment fragment,BankViewModel bankView){
        tabBankDetailsFragment = fragment;
        this.context = context;
        this.bankView = bankView;
    }

    private TabBankDetailsFragment tabBankDetailsFragment;
    private Context context;
    private BankViewModel bankView;

    public BankViewModel getBank(){
                return bankView;
    }
}
