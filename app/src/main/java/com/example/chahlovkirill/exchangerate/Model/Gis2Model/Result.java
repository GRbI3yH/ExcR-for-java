package com.example.chahlovkirill.exchangerate.Model.Gis2Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by chahlov.kirill on 27/01/17.
 */

public class Result {
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
}
