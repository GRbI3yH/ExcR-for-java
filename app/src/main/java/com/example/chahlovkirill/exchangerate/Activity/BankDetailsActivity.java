package com.example.chahlovkirill.exchangerate.Activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.chahlovkirill.exchangerate.Adapters.TabBankDetailsAdapter;
import com.example.chahlovkirill.exchangerate.Model.BankViewModel;
import com.example.chahlovkirill.exchangerate.R;
import com.example.chahlovkirill.exchangerate.Services.ListenerLocation;

public class BankDetailsActivity extends FragmentActivity {

    private TabBankDetailsAdapter mTabBankDetailsAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_bank_details);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout_BankDetails);
        tabLayout.addTab(tabLayout.newTab().setText("Банк"));
        tabLayout.addTab(tabLayout.newTab().setText("Банкоматы"));
        tabLayout.addTab(tabLayout.newTab().setText("Отделения"));
        tabLayout.addTab(tabLayout.newTab().setText("Конвертер"));

        ListenerLocation.SetUpLocationListener(this);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager_BankDetails);
        BankViewModel bankView = (BankViewModel)getIntent().getSerializableExtra("SelectedBank");
        final TabBankDetailsAdapter adapter = new TabBankDetailsAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount(), bankView);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
