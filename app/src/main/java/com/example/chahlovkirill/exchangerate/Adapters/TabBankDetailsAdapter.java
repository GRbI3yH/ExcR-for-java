package com.example.chahlovkirill.exchangerate.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.chahlovkirill.exchangerate.Fragments.FragmentsBankDetails.*;
import com.example.chahlovkirill.exchangerate.Model.BankViewModel;
import com.example.chahlovkirill.exchangerate.Model.CityModel;


/**
 * Created by chahlov.kirill on 08/02/17.
 */

public class TabBankDetailsAdapter extends FragmentPagerAdapter {

    private int numOfTabs;
    private BankViewModel bank;
    private CityModel city;

    public TabBankDetailsAdapter(FragmentManager fm, int numOfTabs, BankViewModel bank, CityModel city) {
        super(fm);
        this.numOfTabs = numOfTabs;
        this.bank = bank;
        this.city = city;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                TabBankDetailsFragment tabBankDetailsFragment = new TabBankDetailsFragment();
                tabBankDetailsFragment.setBank(bank);
                return tabBankDetailsFragment;
            case 1:
                TabBankDetailsSearchFragment BankDetailsAtmFragment = new TabBankDetailsSearchFragment();
                BankDetailsAtmFragment.setBankDetailsArgument (bank,city,"Банкоматы","Банкоматы");
                return BankDetailsAtmFragment;
            case 2:
                TabBankDetailsSearchFragment BankDetailsDepartmentFragment = new TabBankDetailsSearchFragment();
                BankDetailsDepartmentFragment.setBankDetailsArgument (bank,city,"Отделения","Банки");
                return BankDetailsDepartmentFragment;
            case 3:
                TabСonverterFragment tabСonverterFragment = new TabСonverterFragment();
                tabСonverterFragment.setBank(bank);
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
