package com.example.chahlovkirill.exchangerate.Services;

import com.example.chahlovkirill.exchangerate.Model.Gis2Model.Result;
import com.google.android.gms.maps.model.LatLng;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by chahlov.kirill on 09/02/17.
 */

public class SortByDistance {

    public static List<Result> Sort(Double lat1, Double lan1, List<Result> organization){

        for (Result result:organization) {
            double lat2 = Double.valueOf(result.getLat());
            double lon2 = Double.valueOf(result.getLon());
            result.setDistances(Distance(lat1,lan1,lat2,lon2));
        }
        Collections.sort(organization, Result.ResultModelComparator );
        return organization;
    }

    private static double Distance(Double lat1, Double lan1, Double lat2, Double lan2){
//        double x1 = Math.toRadians(lat1);
//        double y1 = Math.toRadians(lan1);
//        double x2 = Math.toRadians(lat2);
//        double y2 = Math.toRadians(lan2);
        double x1 = lat1;
        double y1 = lan1;
        double x2 = lat2;
        double y2 = lan2;
        double distance = 6371.01 *
                Math.acos( Math.sin(Math.toRadians(x1)) * Math.sin(Math.toRadians(x2)) +
                        Math.cos(Math.toRadians(x1)) * Math.cos(Math.toRadians(x2)) *
                                Math.cos(Math.toRadians(y1 - y2)));
        return distance;
    }
}
