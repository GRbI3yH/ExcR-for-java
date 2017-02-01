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

       //map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(51.0,-0.1),10));
        map.setOnCameraIdleListener(mClusterManager);
        map.setOnMarkerClickListener(mClusterManager);

        List<MyItem> myItem = mapGooglePresenter.getPosition();
        if(myItem != null & myItem.size() != 0){
            for (MyItem points: myItem) {
                mClusterManager.addItem(points);
            }
//            CameraPosition cameraPosition =new CameraPosition.Builder().target(
//                    pointPositionModels.get(0)
//                            .getLatLng())
//                    .zoom(9)
//                    .build();
//            CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
//            map.animateCamera(cameraUpdate);
        }
//        if(pointPositionModels != null & pointPositionModels.size() != 0){
//            for (PointItemModel points: pointPositionModels) {
//                map.addMarker(new MarkerOptions().position(points.getLatLng()).title(points.getNameBank()));
//            }
//            CameraPosition cameraPosition =new CameraPosition.Builder().target(
//                    pointPositionModels.get(0)
//                    .getLatLng())
//                    .zoom(9)
//                    .build();
//            CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
//            map.animateCamera(cameraUpdate);
//        }
    }
    // Declare a variable for the cluster manager.
//    private void addItems() {
//
//        // Set some lat/lng coordinates to start with.
//        double lat = 51.5145160;
//        double lng = -0.1270060;
//
//        // Add ten cluster items in close proximity, for purposes of this example.
//        for (int i = 0; i < 10; i++) {
//            double offset = i / 60d;
//            lat = lat + offset;
//            lng = lng + offset;
//            MyItem offsetItem = new MyItem(lat, lng);
//            mClusterManager.addItem(offsetItem);
//        }
//    }
//    private void setUpClusterer() {
//        // Position the map.
//        getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(51.503186, -0.126446), 10));
//
//        // Initialize the manager with the context and the map.
//        // (Activity extends context, so we can pass 'this' in the constructor.)
//        mClusterManager = new ClusterManager<MyItem>(this, getMap());
//
//        // Point the map's listeners at the listeners implemented by the cluster
//        // manager.
//        getMap().setOnCameraIdleListener(mClusterManager);
//        getMap().setOnMarkerClickListener(mClusterManager);
//
//        // Add cluster items (markers) to the cluster manager.
//        addItems();
//    }
}
// extends AppCompatActivity
//        implements OnMapReadyCallback {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        // Retrieve the content view that renders the map.
//        setContentView(R.layout.map_google);
//        // Get the SupportMapFragment and request notification
//        // when the map is ready to be used.
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);
//    }
//
//    /**
//     * Manipulates the map when it's available.
//     * The API invokes this callback when the map is ready to be used.
//     * This is where we can add markers or lines, add listeners or move the camera. In this case,
//     * we just add a marker near Sydney, Australia.
//     * If Google Play services is not installed on the device, the user receives a prompt to install
//     * Play services inside the SupportMapFragment. The API invokes this method after the user has
//     * installed Google Play services and returned to the app.
//     */
//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        // Add a marker in Sydney, Australia,
//        // and move the map's camera to the same location.
//        LatLng sydney = new LatLng(-33.852, 151.211);
//        googleMap.addMarker(new MarkerOptions().position(sydney)
//                .title("Marker in Sydney"));
//        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//    }
//}
