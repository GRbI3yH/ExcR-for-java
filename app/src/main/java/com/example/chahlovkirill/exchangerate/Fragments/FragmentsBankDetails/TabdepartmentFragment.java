package com.example.chahlovkirill.exchangerate.Fragments.FragmentsBankDetails;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.chahlovkirill.exchangerate.DataProvider.DataProvider;
import com.example.chahlovkirill.exchangerate.Model.BankViewModel;
import com.example.chahlovkirill.exchangerate.Presenters.PresentersBankDetails.TabDepartmentPresenter;
import com.example.chahlovkirill.exchangerate.R;

/**
 * Created by chahlov.kirill on 08/02/17.
 */

public class TabDepartmentFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    public TabDepartmentFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static TabDepartmentFragment newInstance(int sectionNumber) {
        TabDepartmentFragment fragment = new TabDepartmentFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    private View view;
    private TabDepartmentPresenter tabDepartmentPresenter;
    private BankViewModel bankView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tab_department, container, false);
        tabDepartmentPresenter = new TabDepartmentPresenter(getContext(),this,bankView);
        ListView departmentlistView = (ListView)view.findViewById(R.id.department_listview);
        departmentlistView.setAdapter(tabDepartmentPresenter.getAdapter());
        TextView TextViewShowAll = (TextView) view.findViewById(R.id.departament_ShowAll);
        TextViewShowAll.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                tabDepartmentPresenter.ClickResultsAll();
            }
        });

        return view;
    }

    @Override
    public void onDestroy() {
        DataProvider.getInstance().removeListener(tabDepartmentPresenter);
        Log.e("onDestroy","TabDepartmentFragment");
        super.onDestroy();
    }
    public void setBank(BankViewModel bankView){
        this.bankView = bankView;
    }
}
