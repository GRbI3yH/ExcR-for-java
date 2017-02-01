package com.example.chahlovkirill.exchangerate.Activity;

/**
 * Created by chahlov.kirill on 26/01/17.
 */
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.chahlovkirill.exchangerate.Cluster.MyItem;
import com.example.chahlovkirill.exchangerate.Model.BankModel;
import com.example.chahlovkirill.exchangerate.Model.PointItemModel;
import com.example.chahlovkirill.exchangerate.Presenters.MapGooglePresenter;
import com.example.chahlovkirill.exchangerate.R;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterManager;

import java.util.List;
import java.util.Map;

public class MapGoogleActivity extends FragmentActivity implements OnMapReadyCallback {

    private MapGooglePresenter mapGooglePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_google);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mapGooglePresenter = new MapGooglePresenter(this);
    }

    GoogleMap map ;
    @Override
    public void onMapReady(GoogleMap map) {
        // Add a marker in Sydney, Australia, and move the camera.
        mapGooglePresenter.DownloadModelOfServices();
        this.map = map;

    }

    private ClusterManager<MyItem> mClusterManager;

    public void renderMarkers(List<PointItemModel> pointPositionModels){

        mClusterManager = new ClusterManager<MyItem>(this,map);

        map.setOnCameraIdleListener(mClusterManager);
        map.setOnMarkerClickListener(mClusterManager);

        List<MyItem> myItem = mapGooglePresenter.getPosition();
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
