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

import com.example.chahlovkirill.exchangerate.Model.BankModel;
import com.example.chahlovkirill.exchangerate.Model.PointItemModel;
import com.example.chahlovkirill.exchangerate.Presenters.MapGooglePresenter;
import com.example.chahlovkirill.exchangerate.R;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

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

    public void renderMarkers(List<PointItemModel> pointPositionModels){

        if(pointPositionModels != null & pointPositionModels.size() != 0){
            for (PointItemModel points: pointPositionModels) {
                map.addMarker(new MarkerOptions().position(points.getLatLng()).title(points.getNameBank()));
            }
            CameraPosition cameraPosition =new CameraPosition.Builder().target(
                    pointPositionModels.get(0)
                    .getLatLng())
                    .zoom(9)
                    .build();
            CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
            map.animateCamera(cameraUpdate);
        }
//        if(latLngs != null){
//            if (latLngs.size() != 0){
//                for (LatLng BanksPosition : latLngs){
//                    map.addMarker(new MarkerOptions().position(BanksPosition).title(bank));
//
//                }
//                CameraPosition cameraPosition =new CameraPosition.Builder().target(latLngs.get(0)).zoom(9).build();
//                CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
//                map.animateCamera(cameraUpdate);
//            }
//        }
    }
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
