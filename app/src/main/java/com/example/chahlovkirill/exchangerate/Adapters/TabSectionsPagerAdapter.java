package com.example.chahlovkirill.exchangerate.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.chahlovkirill.exchangerate.Fragments.FragmentsMain.TabBanksFragment;
import com.example.chahlovkirill.exchangerate.Fragments.FragmentsMain.TabCitiesFragment;

/**
 * Created by chahlov.kirill on 23/01/17.
 */

/**
 * A placeholder fragment containing a simple view.
 */
/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class TabSectionsPagerAdapter extends FragmentPagerAdapter {

    private int numOfTabs;

    public TabSectionsPagerAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                TabCitiesFragment tabCitiesFragment = new TabCitiesFragment();
                return tabCitiesFragment;
            case 1:
                TabBanksFragment tabBanksFragment = new TabBanksFragment();
                return tabBanksFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}