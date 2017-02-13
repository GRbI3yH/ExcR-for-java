package com.example.chahlovkirill.exchangerate.Activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

import com.example.chahlovkirill.exchangerate.Cluster.Position;
import com.example.chahlovkirill.exchangerate.Model.BankViewModel;
import com.example.chahlovkirill.exchangerate.Model.Gis2Model.Result;
import com.example.chahlovkirill.exchangerate.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chahlov.kirill on 10/02/17.
 */

public class MapDetailsBankActivity extends FragmentActivity implements OnMapReadyCallback {

    private List<Result> gis2Results;
    private BankViewModel bank;
    private GoogleMap map;
    private float mapZoom = 7;
    private TextView textAddressView;
    private TextView USDBuy;
    private TextView USDSell;
    private TextView EURBuy;
    private TextView EURSell;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_details_bank_activity);
        bank = (BankViewModel)getIntent().getSerializableExtra("bankView");
        gis2Results = getIntent().getParcelableArrayListExtra("gis2Results");
        textAddressView = (TextView)findViewById(R.id.details_address);
        USDBuy = (TextView)findViewById(R.id.Details_USDBuy);
        USDSell = (TextView)findViewById(R.id.Details_USDSell);
        EURBuy = (TextView)findViewById(R.id.Details_EURBuy);
        EURSell = (TextView)findViewById(R.id.Details_EURSell);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapdetails);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        this.map = map;

        ClusterManager<Position> mClusterManager = new ClusterManager<Position>(this,this.map);
        this.map.setOnCameraIdleListener(mClusterManager);
        this.map.setOnMarkerClickListener(mClusterManager);

        textAddressView.setText(gis2Results.get(0).getCity_name()+", "+gis2Results.get(0).getAddress());
        USDBuy.setText(String.format("%.2f",bank.getUSDBuy()));
        USDSell.setText(String.format("%.2f",bank.getUSDSell()));
        EURBuy.setText(String.format("%.2f",bank.getEURBuy()));
        EURSell.setText(String.format("%.2f",bank.getEURSell()));

        if(gis2Results != null & gis2Results.size() != 0){
            List<Position> positions = new ArrayList<Position>();
            for (Result gis2result:gis2Results){
                if(gis2result.getLon()!=null & gis2result.getLat()!=null){
                    positions.add(new Position(Double.valueOf(gis2result.getLat()),Double.valueOf(gis2result.getLon()), gis2result.getName()));
                }
            }
            LatLng latLng = new LatLng(1,1);
            for (Position position: positions) {
                mClusterManager.addItem(position);
                if (position != null) {
                    latLng = position.getPosition();
                }
            }
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,mapZoom));
        }
    }
}
