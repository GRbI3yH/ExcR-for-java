package com.example.chahlovkirill.exchangerate.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chahlovkirill.exchangerate.AppSetting.Setting;
import com.example.chahlovkirill.exchangerate.Model.BankModel;
import com.example.chahlovkirill.exchangerate.Model.CityModel;
import com.example.chahlovkirill.exchangerate.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;

/**
 * Created by chahlov.kirill on 19/01/17.
 */

public class  CitiesAdapter extends ArrayAdapter<CityModel> {
    public CitiesAdapter(Context context, List<CityModel> Cities){
        super(context,0,Cities);
        this.Cities = Cities;
    }
    private List<CityModel> Cities = new ArrayList<>();

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final CityModel City = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.cities_list_item, parent, false);
        }
        convertView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //CitiesAdapter.this.Cities;
                for (CityModel cityModel: CitiesAdapter.this.Cities){
                    if (cityModel.getSelected()){
                        cityModel.setSelected(false);
                    }
                }
                City.setSelected(true);
                Setting.setselectCity(String.valueOf(City.getId()),getContext());
                CitiesAdapter.this.notifyDataSetChanged();
            }
        });
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.city_name);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);

        if (City.getSelected()){
            imageView.setVisibility(View.VISIBLE);
        }
        else{
            imageView.setVisibility(View.GONE);
        }
        //TextView tvHome = (TextView) convertView.findViewById(R.id.tvHome);
        // Populate the data into the template view using the data object
        tvName.setText(City.getName());
        //tvHome.setText(user.hometown);
        // Return the completed view to render on screen
        return convertView;
    }

}