package com.example.chahlovkirill.exchangerate.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.chahlovkirill.exchangerate.Model.BankCurrencyModel;
import com.example.chahlovkirill.exchangerate.Model.BankModel;
import com.example.chahlovkirill.exchangerate.Model.CityModel;
import com.example.chahlovkirill.exchangerate.R;

import java.util.List;

/**
 * Created by chahlov.kirill on 19/01/17.
 */

public class  BanksAdapter extends ArrayAdapter<BankCurrencyModel> {
    public BanksAdapter(Context context, List<BankCurrencyModel> City){
        super(context,0,City);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        BankCurrencyModel Bank = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.bank_list_item, parent, false);
        }
        // Lookup view for data population
        TextView bankName = (TextView) convertView.findViewById(R.id.bank_name);
        TextView bankValueCurrency = (TextView) convertView.findViewById(R.id.bank_valueCurrency);
        //TextView tvHome = (TextView) convertView.findViewById(R.id.tvHome);
        // Populate the data into the template view using the data object
        bankName.setText(Bank.getName());
        bankValueCurrency.setText(String.valueOf(Bank.getCurrency()));
        //tvHome.setText(user.hometown);
        // Return the completed view to render on screen
        return convertView;
    }

}