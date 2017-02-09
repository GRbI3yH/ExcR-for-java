package com.example.chahlovkirill.exchangerate.Fragments.FragmentsBankDetails;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.util.Linkify;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chahlovkirill.exchangerate.Activity.BankDetailsActivity;
import com.example.chahlovkirill.exchangerate.DataProvider.DataProvider;
import com.example.chahlovkirill.exchangerate.Model.BankViewModel;
import com.example.chahlovkirill.exchangerate.Presenters.PresentersBankDetails.TabBankDetailsPresenter;
import com.example.chahlovkirill.exchangerate.R;

/**
 * Created by chahlov.kirill on 08/02/17.
 */

public class TabBankDetailsFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    public TabBankDetailsFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static TabBankDetailsFragment newInstance(int sectionNumber) {
        TabBankDetailsFragment fragment = new TabBankDetailsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    private View view;
    private TabBankDetailsPresenter tabBankDetailsPresenter;
    private BankViewModel bankView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tab_bank_details, container, false);
        tabBankDetailsPresenter = new TabBankDetailsPresenter(getContext(),this);

        TextView NameText = (TextView) view.findViewById(R.id.details_bank_name);
        TextView URlText = (TextView) view.findViewById(R.id.details_URL);
        TextView USDBuyText = (TextView) view.findViewById(R.id.details_USDBuy);
        TextView USDSellText = (TextView) view.findViewById(R.id.details_USDSell);
        TextView EURBuyText = (TextView) view.findViewById(R.id.details_EURBuy);
        TextView EURSellText = (TextView) view.findViewById(R.id.details_EURSell);

        NameText.setText(bankView.getName());
        URlText.setText(bankView.getUrl());
        Linkify.addLinks(URlText, Linkify.WEB_URLS);

        USDBuyText.setText(String.format("%.2f",bankView.getUSDBuy()));
        USDSellText.setText(String.format("%.2f",bankView.getUSDSell()));
        EURBuyText.setText(String.format("%.2f",bankView.getEURBuy()));
        EURSellText.setText(String.format("%.2f",bankView.getEURSell()));

        return view;
    }
    public void setBank(BankViewModel bankView){
        this.bankView = bankView;
    }
}
