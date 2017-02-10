package com.example.chahlovkirill.exchangerate.Model.Gis2Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

/**
 * Created by chahlov.kirill on 27/01/17.
 */

public class Result implements Comparable<Result>,Serializable,Parcelable {

    private Double distances;

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("lon")
    @Expose
    private String lon;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("rubrics")
    @Expose
    private List<String> rubrics;
    @SerializedName("city_name")
    @Expose
    private String city_name;

    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }


    public String getLon() {
        return this.lon;
    }
    public void setLon(String lon) {
        this.lon = lon;
    }


    public String getLat() {
        return this.lat;
    }
    public void setLat(String lat) {
        this.lat = lat;
    }


    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }


    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }


    public List<String> getRubrics() {
        return this.rubrics;
    }
    public void setRubrics(List<String> rubrics) {
        this.rubrics = rubrics;
    }

    public String getCity_name() {
        return city_name;
    }
    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public void setDistances(Double distances) {
        this.distances = distances;
    }
    public Double getDistances() {
        return distances;
    }

    @Override
    public int compareTo(Result o) {
        if (this.getDistances()<o.getDistances()){
            return -1;
        }
        else if(this.getDistances()>o.getDistances()){
            return 1;
        }
        return 0;
    }
    public static Comparator<Result> ResultModelComparator = new Comparator<Result>() {
        @Override
        public int compare(Result o1, Result o2) {
            Double C1 = o1.getDistances();
            Double C2 = o2.getDistances();
            return C1.compareTo(C2);
        }
    };

    @Override
    public int describeContents() {
        return this.hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(lon);
        dest.writeString(lat);
        dest.writeString(name);
        dest.writeString(address);
        dest.writeStringList(rubrics);
        dest.writeString(city_name);
    }
    protected Result(Parcel in) {
        id = in.readString();
        lon = in.readString();
        lat = in.readString();
        name = in.readString();
        address = in.readString();
        rubrics = in.createStringArrayList();
        city_name = in.readString();
    }

    public static final Creator<Result> CREATOR = new Creator<Result>() {
        @Override
        public Result createFromParcel(Parcel in) {
            return new Result(in);
        }

        @Override
        public Result[] newArray(int size) {
            return new Result[size];
        }
    };
}
