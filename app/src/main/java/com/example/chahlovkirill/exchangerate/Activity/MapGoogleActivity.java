package com.example.chahlovkirill.exchangerate.Activity;

/**
 * Created by chahlov.kirill on 26/01/17.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.example.chahlovkirill.exchangerate.Cluster.Position;
import com.example.chahlovkirill.exchangerate.DataProvider.DataProvider;
import com.example.chahlovkirill.exchangerate.Model.BankViewModel;
import com.example.chahlovkirill.exchangerate.Presenters.MapGooglePresenter;
import com.example.chahlovkirill.exchangerate.R;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterManager;

import java.util.List;

public class MapGoogleActivity extends FragmentActivity implements OnMapReadyCallback {

    private MapGooglePresenter mapGooglePresenter;
    private ClusterManager<Position> mClusterManager;
    private GoogleMap map;
    private float mapZoom = 7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_google);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mapGooglePresenter = new MapGooglePresenter(this,(BankViewModel)getIntent().getSerializableExtra("SelectedBank"));
    }

    @Override
    public void onMapReady(GoogleMap map) {
        this.map = map;

        mClusterManager = new ClusterManager<Position>(this,this.map);
        this.map.setOnCameraIdleListener(mClusterManager);
        this.map.setOnMarkerClickListener(mClusterManager);
    }

    public void renderMarkers(){
        List<Position> positions = mapGooglePresenter.getPositionBanks();
        if(positions != null & positions.size() != 0){
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
    @Override
    public void onDestroy() {
        DataProvider.getInstance().removeListener(mapGooglePresenter);
        Log.i("onDestroy","MapGoogleActivity");
        super.onDestroy();
    }
}
