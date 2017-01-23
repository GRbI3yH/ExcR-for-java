package com.example.chahlovkirill.exchangerate.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.chahlovkirill.exchangerate.Fragments.TabBanksFragment;
import com.example.chahlovkirill.exchangerate.Fragments.TabCitiesFragment;

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

    private int NumOfTabs;

    public TabSectionsPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.NumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        switch (position){
            case 0:
                TabCitiesFragment TCitiesA = new TabCitiesFragment();
                return TCitiesA;
            case 1:
                TabBanksFragment TBanksA = new TabBanksFragment();
                return TBanksA;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NumOfTabs;
    }
}