package com.example.chahlovkirill.exchangerate.Activity;

/**
 * Created by chahlov.kirill on 26/01/17.
 */
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.chahlovkirill.exchangerate.Cluster.MyItem;
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
        mapGooglePresenter = new MapGooglePresenter(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        // Add a marker in Sydney, Australia, and move the camera.
        mapGooglePresenter.DownloadOfServices();
        this.map = map;

    }

    public void renderMarkers(){
        mClusterManager = new ClusterManager<MyItem>(this,map);
        map.setOnCameraIdleListener(mClusterManager);
        map.setOnMarkerClickListener(mClusterManager);

        List<MyItem> myItem = mapGooglePresenter.getPositionBanks();
        if(myItem != null & myItem.size() != 0){
            LatLng latLng = new LatLng(1,1);
            for (MyItem points: myItem) {
                mClusterManager.addItem(points);
                if (points != null) {
                    latLng = points.getPosition();
                }
            }
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,9));
        }
    }
}
