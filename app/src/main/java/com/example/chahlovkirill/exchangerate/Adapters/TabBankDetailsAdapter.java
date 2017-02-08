package com.example.chahlovkirill.exchangerate.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.chahlovkirill.exchangerate.Fragments.FragmentsBankDetails.*;
import com.example.chahlovkirill.exchangerate.Model.BankViewModel;


/**
 * Created by chahlov.kirill on 08/02/17.
 */

public class TabBankDetailsAdapter extends FragmentPagerAdapter {

    private int numOfTabs;
    private BankViewModel bankView;

    public TabBankDetailsAdapter(FragmentManager fm, int numOfTabs, BankViewModel bankView) {
        super(fm);
        this.numOfTabs = numOfTabs;
        this.bankView = bankView;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                TabBankDetailsFragment tabBankDetailsFragment = new TabBankDetailsFragment();
                tabBankDetailsFragment.setBank(bankView);
                return tabBankDetailsFragment;
            case 1:
                TabСashMachinesFragment tabСashMachinesFragment = new TabСashMachinesFragment();
                return tabСashMachinesFragment;
            case 2:
                TabDepartmentFragment tabDepartmentFragment = new TabDepartmentFragment();
                return tabDepartmentFragment;
            case 3:
                TabСonverterFragment tabСonverterFragment = new TabСonverterFragment();
                tabСonverterFragment.setBank(bankView);
                return tabСonverterFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
