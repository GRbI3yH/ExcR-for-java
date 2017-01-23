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
    public BanksAdapter(Context context, List<BankCurrencyModel> Bank){
        super(context,0,Bank);
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
        TextView BankName = (TextView) convertView.findViewById(R.id.bank_name);
        TextView BankValueCurrency = (TextView) convertView.findViewById(R.id.bank_valueCurrency);
        // Populate the data into the template view using the data object
        BankName.setText(Bank.getName());
        BankValueCurrency.setText(String.valueOf(Bank.getCurrency()));
        // Return the completed view to render on screen
        return convertView;
    }

}