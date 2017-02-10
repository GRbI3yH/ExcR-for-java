package com.example.chahlovkirill.exchangerate.Adapters;

import android.content.Context;
import android.location.Location;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.chahlovkirill.exchangerate.Model.Gis2Model.Result;
import com.example.chahlovkirill.exchangerate.Presenters.PresentersBankDetails.TabCashMachinesPresenter;
import com.example.chahlovkirill.exchangerate.R;
import com.example.chahlovkirill.exchangerate.Services.ListenerLocation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chahlov.kirill on 08/02/17.
 */

public class CashMachinesAdapter extends ArrayAdapter<Result> {
    public CashMachinesAdapter(Context context, List<Result> gis2Results , TabCashMachinesPresenter tabCashMachinesPresenter){
        super(context,0,gis2Results);
        this.tabCashMachinesPresenter = tabCashMachinesPresenter;
    }
    TabCashMachinesPresenter tabCashMachinesPresenter;
    Result gis2Result;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        gis2Result = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.cashmachines_list_item, parent, false);
        }
        TextView nameText = (TextView) convertView.findViewById(R.id.cashmachines_name);
        nameText.setText(gis2Result.getName());
        TextView addressText = (TextView) convertView.findViewById(R.id.cashmachines_address_text);
        addressText.setText(gis2Result.getCity_name()+", "+gis2Result.getAddress());
        TextView distance = (TextView) convertView.findViewById(R.id.cashmachines_distance_text);
        distance.setText(String.format("%.2f",gis2Result.getDistances())+" км");

        convertView.setTag(gis2Result);

        convertView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Result gis2result = (Result)v.getTag();
                List<Result> gis2results = new ArrayList<Result>();
                gis2results.add(gis2result);
                tabCashMachinesPresenter.ClickItem(gis2results);
            }
        });

        return convertView;
    }
}
