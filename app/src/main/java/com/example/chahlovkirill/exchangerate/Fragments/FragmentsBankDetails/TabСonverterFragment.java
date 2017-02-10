package com.example.chahlovkirill.exchangerate.Fragments.FragmentsBankDetails;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.chahlovkirill.exchangerate.Model.BankViewModel;
import com.example.chahlovkirill.exchangerate.Model.EExchangeAction;
import com.example.chahlovkirill.exchangerate.Presenters.PresentersBankDetails.TabConverterPresenter;
import com.example.chahlovkirill.exchangerate.R;

/**
 * Created by chahlov.kirill on 08/02/17.
 */

public class TabСonverterFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private BankViewModel bankView;

    public TabСonverterFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static TabСonverterFragment newInstance(int sectionNumber) {
        TabСonverterFragment fragment = new TabСonverterFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    private View view;
    private TabConverterPresenter tabConverterPresenter;
    private EditText editTextValue;
    private TextView USDBuyText;
    private TextView USDSellText;
    private TextView EURBuyText;
    private TextView EURSellText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tab_converter, container, false);
        editTextValue = (EditText) view.findViewById(R.id.editTextValue);
        USDBuyText = (TextView) view.findViewById(R.id.btnusdtorur);
        USDSellText = (TextView) view.findViewById(R.id.btnrurtousd);
        EURBuyText = (TextView) view.findViewById(R.id.btneurtorur);
        EURSellText = (TextView) view.findViewById(R.id.btnrurtoeur);
        tabConverterPresenter = new TabConverterPresenter(getContext(),this, bankView);

        editTextValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().equals("")){
                    USDBuyText.setText(tabConverterPresenter.Convert(s.toString(), EExchangeAction.USDBuy));
                    USDSellText.setText(tabConverterPresenter.Convert(s.toString(), EExchangeAction.USDSell));
                    EURBuyText.setText(tabConverterPresenter.Convert(s.toString(), EExchangeAction.EURBuy));
                    EURSellText.setText(tabConverterPresenter.Convert(s.toString(), EExchangeAction.EURSell));
                }
                else {
                    USDBuyText.setText("0.00");
                    USDSellText.setText("0.00");
                    EURBuyText.setText("0.00");
                    EURSellText.setText("0.00");
                }
            }
        });

        return view;
    }

    public void setBank(BankViewModel bankView){
        this.bankView = bankView;
    }
}
