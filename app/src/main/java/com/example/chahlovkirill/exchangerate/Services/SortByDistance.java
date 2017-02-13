package com.example.chahlovkirill.exchangerate.Services;

import android.util.Log;

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
        if (organization != null){
            int i = 0;
            for (Result result:organization) {
                if(result != null){
                    //Log.i("SortByDistance.Sort",String.valueOf(result.getLat())+"\t"+String.valueOf(result.getLon())+"\t"+String.valueOf(i));
                    if (result.getLat() != null & result.getLon() != null){
                        double lat2 = Double.valueOf(result.getLat());
                        double lon2 = Double.valueOf(result.getLon());
                        result.setDistances(Distance(lat1,lan1,lat2,lon2));
                    }
                    i++;
                }
            }
            Collections.sort(organization, Result.ResultModelComparator );
            return organization;
        }
        return organization;
    }//если в возврощаемом 2gis запросе есть нуливые кординаты организации(элемента) мне лучше удалить или пропустить этот элемент

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
/*
Вчера:
1)Длелал переходы на гугл карту по нажатию на отделения или банкоматы
2)Испровлял ошибки
Сегодня:
1)Исправить отображаемые отделения и банки на гугл карте
2)Исправить возможные встречающиеся ошибки
3)Доделать стили
*/