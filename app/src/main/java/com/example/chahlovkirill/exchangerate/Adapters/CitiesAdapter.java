package com.example.chahlovkirill.exchangerate.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chahlovkirill.exchangerate.AppSetting.Settings;
import com.example.chahlovkirill.exchangerate.Model.CityModel;
import com.example.chahlovkirill.exchangerate.Presenters.TabCitiesPresenter;
import com.example.chahlovkirill.exchangerate.R;

import java.util.List;

/**
 * Created by chahlov.kirill on 19/01/17.
 */

public class  CitiesAdapter extends ArrayAdapter<CityModel> {
    public CitiesAdapter(Context context, List<CityModel> Cities,TabCitiesPresenter tabCitiesPresenter){
        super(context,0,Cities);
        this.tabCitiesPresenter = tabCitiesPresenter;
    }

    private TabCitiesPresenter tabCitiesPresenter ;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final CityModel city = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.cities_list_item, parent, false);
        }

        TextView cityName = (TextView) convertView.findViewById(R.id.city_name);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);

        convertView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Settings.setselectCityName(city.getName(),getContext());
                tabCitiesPresenter.CityClick(city);
            }

        });
        // Lookup view for data population
        if (city.getSelected()){
            imageView.setVisibility(View.VISIBLE);
        }
        else{
            imageView.setVisibility(View.GONE);
        }
        // Populate the data into the template view using the data object
        cityName.setText(city.getName());
        // Return the completed view to render on screen
        return convertView;
    }

}