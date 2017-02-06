package com.example.chahlovkirill.exchangerate.Model;

import retrofit2.http.GET;
import com.google.gson.annotations.*;

import java.io.Serializable;

/**
 * Created by chahlov.kirill on 18/01/17.
 */

public class CityModel implements Serializable {

    @SerializedName("Id")
    @Expose
    private int Id ;
    @SerializedName("Name")
    @Expose
    private String Name ;

    private boolean Selected;

//    private String TranslitId ;

    public int getId() {
        return this.Id;
    }
    public void setId(int Id) {
        this.Id = Id;
    }

    public String getName() {
        return this.Name;
    }
    public void setName(String Name) {
        this.Name = Name;
    }

    public boolean getSelected(){
        return this.Selected;
    }
    public void setSelected(boolean Selected){
        this.Selected = Selected;
    }

    public CityModel(){}

    public CityModel(int id, String name,Boolean selected){
        Id = id;
        Name = name;
        Selected = selected;

    }


//    public String getTranslitId() {
//        return this.TranslitId;
//    }
//    public void setTranslitId(String TranslitId) {
//        this.TranslitId = TranslitId;
//    }
}
