package com.example.chahlovkirill.exchangerate.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.chahlovkirill.exchangerate.AppSetting.Settings;
import com.example.chahlovkirill.exchangerate.Model.BankViewModel;
import com.example.chahlovkirill.exchangerate.Presenters.TabBanksPresenter;
import com.example.chahlovkirill.exchangerate.R;

import java.util.List;

/**
 * Created by chahlov.kirill on 19/01/17.
 */

public class  BanksAdapter extends ArrayAdapter<BankViewModel> {
    public BanksAdapter(Context context, List<BankViewModel> bank , TabBanksPresenter tabBanksPresenter){
        super(context,0,bank);
        this.tabBanksPresenter = tabBanksPresenter;
    }
    TabBanksPresenter tabBanksPresenter;
    BankViewModel bank;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        bank = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.bank_list_item, parent, false);
        }
        Button goToGoogleMap = (Button)convertView.findViewById(R.id.GoToGoogleMap);
        goToGoogleMap.setTag(bank);
        // Lookup view for data population
        TextView bankName = (TextView) convertView.findViewById(R.id.bank_name);
        TextView bankValueCurrency = (TextView) convertView.findViewById(R.id.bank_valueCurrency);
        // Populate the data into the template view using the data object
        bankName.setText(bank.getName());
        //String t = String.format("$.3f",bank.getCurrency());//String.valueOf(bank.getCurrency());
        bankValueCurrency.setText(String.format("%.2f",bank.getCurrency()));

        goToGoogleMap.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                BankViewModel bcm = (BankViewModel)v.getTag();
                Log.e("setBank = ", bcm.getName());
                Settings.setselectBank(bcm.getName(),getContext());
                tabBanksPresenter.GoToMapGoogleActivity();
            }
        });
        // Return the completed view to render on screen
        return convertView;
    }
}
