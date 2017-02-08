package com.example.chahlovkirill.exchangerate.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.chahlovkirill.exchangerate.Model.Gis2Model.Result;
import com.example.chahlovkirill.exchangerate.Presenters.PresentersBankDetails.TabDepartmentPresenter;
import com.example.chahlovkirill.exchangerate.R;

import java.util.List;

/**
 * Created by chahlov.kirill on 08/02/17.
 */

public class DepartmentAdapter extends ArrayAdapter<Result> {
    public DepartmentAdapter(Context context, List<Result> gis2Results , TabDepartmentPresenter tabDepartmentPresenter){
        super(context,0,gis2Results);
        this.tabDepartmentPresenter = tabDepartmentPresenter;
    }
    TabDepartmentPresenter tabDepartmentPresenter;
    Result gis2Result;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        gis2Result = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.department_list_item, parent, false);
        }
        TextView nameText = (TextView) convertView.findViewById(R.id.department_name);
        nameText.setText(gis2Result.getName());
        TextView addressText = (TextView) convertView.findViewById(R.id.department_address_text);
        addressText.setText(gis2Result.getCity_name()+", "+gis2Result.getAddress());
        return convertView;
    }
}
