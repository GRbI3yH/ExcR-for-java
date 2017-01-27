package com.example.chahlovkirill.exchangerate.Model.Gis2Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by chahlov.kirill on 27/01/17.
 */

public class FirmGroup {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("count")
    @Expose
    private String count;

    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }



    public String getCount() {
        return this.count;
    }
    public void setCount(String count) {
        this.count = count;
    }
}
