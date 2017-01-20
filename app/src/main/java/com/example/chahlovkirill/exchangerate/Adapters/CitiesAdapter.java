package com.example.chahlovkirill.exchangerate.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.chahlovkirill.exchangerate.Model.CityModel;
import com.example.chahlovkirill.exchangerate.R;

import java.util.List;

import retrofit2.Callback;

/**
 * Created by chahlov.kirill on 19/01/17.
 */

public class  CitiesAdapter extends ArrayAdapter<CityModel> {
    public CitiesAdapter(Context context, List<CityModel> City){
        super(context,0,City);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        CityModel City = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.cities_list_item, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.city_name);
        //TextView tvHome = (TextView) convertView.findViewById(R.id.tvHome);
        // Populate the data into the template view using the data object
        tvName.setText(City.getName());
        //tvHome.setText(user.hometown);
        // Return the completed view to render on screen
        return convertView;
    }

}