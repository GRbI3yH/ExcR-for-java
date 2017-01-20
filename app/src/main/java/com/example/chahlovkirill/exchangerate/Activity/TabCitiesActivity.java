package com.example.chahlovkirill.exchangerate.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.chahlovkirill.exchangerate.Adapters.CitiesAdapter;
import com.example.chahlovkirill.exchangerate.AppSetting.Setting;
import com.example.chahlovkirill.exchangerate.Model.CityModel;
import com.example.chahlovkirill.exchangerate.R;
import com.example.chahlovkirill.exchangerate.Services.cash2cashAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by chahlov.kirill on 20/01/17.
 */

public class TabCitiesActivity extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    public TabCitiesActivity() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static TabCitiesActivity newInstance(int sectionNumber) {
        TabCitiesActivity fragment = new TabCitiesActivity();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.cities_tab_exc_rate, container, false);
        //final TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
        //TextView CM = (TextView)rootView.findViewById(R.id.section_label);


        ListView LVCityM = (ListView)rootView.findViewById(R.id.lvCityM);
        CitiesAdapter adapter = new  CitiesAdapter( getContext() , Setting.getCities(getContext()));
        if (adapter.equals(null)) LVCityM.setAdapter(adapter);

        cash2cashAPI.getCallCitiyModel(new Callback<List<CityModel>>(){
            @Override
            public void onResponse(Call<List<CityModel>> call, Response<List<CityModel>> response) {
                if (response.isSuccessful() && response.body() != null){
                    List<CityModel> citiymodel = response.body() ;

                    Setting.setCities(citiymodel,getContext());

                    ListView LVCityM = (ListView)rootView.findViewById(R.id.lvCityM);

                    CitiesAdapter adapter = new  CitiesAdapter( getContext() , citiymodel);

                    LVCityM.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<CityModel>> call, Throwable t) {
                //TextView CM = (TextView)rootView.findViewById(R.id.section_label);
                //CM.setText("Something went wrong: " + t.getMessage());
            }
        });
        return rootView;
    }
}

//public static class PlaceholderFragment extends Fragment {
//    /**
//     * The fragment argument representing the section number for this
//     * fragment.
//     */
//    private static final String ARG_SECTION_NUMBER = "section_number";
//
//    public PlaceholderFragment() {
//    }
//
//    /**
//     * Returns a new instance of this fragment for the given section
//     * number.
//     */
//    public static PlaceholderFragment newInstance(int sectionNumber) {
//        PlaceholderFragment fragment = new PlaceholderFragment();
//        Bundle args = new Bundle();
//        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        final View rootView = inflater.inflate(R.layout.fragment_tab_exc_rate, container, false);
//        //final TextView textView = (TextView) rootView.findViewById(R.id.section_label);
//        //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
//        //TextView CM = (TextView)rootView.findViewById(R.id.section_label);
//
//        Setting setting = new Setting();
//        ListView LVCityM = (ListView)rootView.findViewById(R.id.lvCityM);
//        CitiesAdapter adapter = new  CitiesAdapter( getContext() , setting.getCities(getContext()));
//        if (adapter.equals(null)) LVCityM.setAdapter(adapter);
//
//        cash2cashAPI.getCallCitiyModel(new Callback<List<CityModel>>(){
//            @Override
//            public void onResponse(Call<List<CityModel>> call, Response<List<CityModel>> response) {
//                if (response.isSuccessful() && response.body() != null){
//                    List<CityModel> citiymodel = response.body() ;
//                    Setting setting = new Setting();
//                    setting.setCities(citiymodel,getContext());
//
//                    ListView LVCityM = (ListView)rootView.findViewById(R.id.lvCityM);
//
//                    CitiesAdapter adapter = new  CitiesAdapter( getContext() , citiymodel);
//
//                    LVCityM.setAdapter(adapter);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<CityModel>> call, Throwable t) {
//                //TextView CM = (TextView)rootView.findViewById(R.id.section_label);
//                //CM.setText("Something went wrong: " + t.getMessage());
//            }
//        });
//        return rootView;
//    }
//}