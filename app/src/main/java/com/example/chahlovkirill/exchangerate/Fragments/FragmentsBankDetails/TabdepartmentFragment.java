package com.example.chahlovkirill.exchangerate.Fragments.FragmentsBankDetails;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tab_department, container, false);
        tabDepartmentPresenter = new TabDepartmentPresenter(getContext(),this);
        ListView departmentlistView = (ListView)view.findViewById(R.id.department_listview);
        departmentlistView.setAdapter(tabDepartmentPresenter.getAdapter());
        return view;
    }
}
