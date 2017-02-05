package com.example.chahlovkirill.exchangerate.Activity;

/**
 * Created by chahlov.kirill on 26/01/17.
 */
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.example.chahlovkirill.exchangerate.Cluster.MyItem;
import com.example.chahlovkirill.exchangerate.DataProvider.DataProvider;
import com.example.chahlovkirill.exchangerate.Presenters.MapGooglePresenter;
import com.example.chahlovkirill.exchangerate.R;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterManager;

import java.util.List;

public class MapGoogleActivity extends FragmentActivity implements OnMapReadyCallback {

    private MapGooglePresenter mapGooglePresenter;
    private ClusterManager<MyItem> mClusterManager;
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_google);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mapGooglePresenter = new MapGooglePresenter(this,getIntent().getStringExtra("SelectedBank"));
    }

    @Override
    public void onMapReady(GoogleMap map) {
        this.map = map;

        mClusterManager = new ClusterManager<MyItem>(this,this.map);
        this.map.setOnCameraIdleListener(mClusterManager);
        this.map.setOnMarkerClickListener(mClusterManager);
    }

    public void renderMarkers(){
        List<MyItem> myItem = mapGooglePresenter.getPositionBanks();
        if(myItem != null & myItem.size() != 0){
            LatLng latLng = new LatLng(1,1);
            for (MyItem point: myItem) {
                mClusterManager.addItem(point);
                if (point != null) {
                    latLng = point.getPosition();
                }
            }
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,8));
        }
    }
    @Override
    public void onDestroy() {
        DataProvider.getInstance().removeListener(mapGooglePresenter);
        Log.i("я дистрой от  ","MapGoogleActivity");
        super.onDestroy();
    }
}
