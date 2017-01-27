package com.example.chahlovkirill.exchangerate.Model.Gis2Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by chahlov.kirill on 27/01/17.
 */

public class Rubric {
    @SerializedName("name")
    @Expose
    private String name;

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
