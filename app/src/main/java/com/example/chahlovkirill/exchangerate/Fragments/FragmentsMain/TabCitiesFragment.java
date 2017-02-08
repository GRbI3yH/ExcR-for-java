package com.example.chahlovkirill.exchangerate.Fragments.FragmentsMain;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.chahlovkirill.exchangerate.DataProvider.DataProvider;
import com.example.chahlovkirill.exchangerate.Presenters.TabCitiesPresenter;
import com.example.chahlovkirill.exchangerate.R;

/**
 * Created by chahlov.kirill on 23/01/17.
 */

public class TabCitiesFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    public TabCitiesFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static TabCitiesFragment newInstance(int sectionNumber) {
        TabCitiesFragment fragment = new TabCitiesFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    private View view;
    private ListView listViewCities;
    private TabCitiesPresenter tabCitiesPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.cities_tab_exc_rate, container, false);
        listViewCities = (ListView)view.findViewById(R.id.lvCityM);
        tabCitiesPresenter = new TabCitiesPresenter(getContext());
        listViewCities.setAdapter(tabCitiesPresenter.getAdapter());

        return view;
    }
    @Override
    public void onDestroy() {
        DataProvider.getInstance().removeListener(tabCitiesPresenter);
        Log.i("onDestroy","TabCitiesFragment");
        super.onDestroy();
    }
}