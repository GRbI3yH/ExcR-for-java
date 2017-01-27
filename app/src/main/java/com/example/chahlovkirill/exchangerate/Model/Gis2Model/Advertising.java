package com.example.chahlovkirill.exchangerate.Model.Gis2Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by chahlov.kirill on 27/01/17.
 */

public class Advertising {
    @SerializedName("firm_id")
    @Expose
    private String firm_id;
    @SerializedName("hash")
    @Expose
    private String hash;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("fas_warning")
    @Expose
    private String fas_warning;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("lon")
    @Expose
    private String lon;
    @SerializedName("lat")
    @Expose
    private String lat;

    public String getFirm_id() {
        return this.firm_id;
    }
    public void setFirm_id(String firm_id) {
        this.firm_id = firm_id;
    }



    public String getHash() {
        return this.hash;
    }
    public void setHash(String hash) {
        this.hash = hash;
    }



    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }



    public String getText() {
        return this.text;
    }
    public void setText(String text) {
        this.text = text;
    }



    public String getFas_warning() {
        return this.fas_warning;
    }
    public void setFas_warning(String fas_warning) {
        this.fas_warning = fas_warning;
    }



    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
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
}
