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
import com.example.chahlovkirill.exchangerate.DataProvider.DataProvider;
import com.example.chahlovkirill.exchangerate.Model.CityModel;
import com.example.chahlovkirill.exchangerate.Presenters.TabCitiesPresenter;
import com.example.chahlovkirill.exchangerate.R;

import java.util.List;

/**
 * Created by chahlov.kirill on 19/01/17.
 */

public class  CitiesAdapter extends ArrayAdapter<CityModel> {
    public CitiesAdapter(Context context, List<CityModel> cities,TabCitiesPresenter tabCitiesPresenter){
        super(context,0,cities);
        this.tabCitiesPresenter = tabCitiesPresenter;
    }

    private TabCitiesPresenter tabCitiesPresenter ;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final CityModel city = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.cities_list_item, parent, false);
        }

        TextView cityName = (TextView) convertView.findViewById(R.id.city_name);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);

        convertView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                tabCitiesPresenter.ClickCity(city);
            }

        });
        if (city.getSelected()){
            imageView.setVisibility(View.VISIBLE);
        }
        else{
            imageView.setVisibility(View.GONE);
        }
        cityName.setText(city.getName());
        return convertView;
    }

}