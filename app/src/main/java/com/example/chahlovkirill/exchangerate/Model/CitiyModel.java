package com.example.chahlovkirill.exchangerate.Model;

import retrofit2.http.GET;
import com.google.gson.annotations.*;
/**
 * Created by chahlov.kirill on 18/01/17.
 */

public class CitiyModel {

    @SerializedName("Id")
    @Expose
    private int Id ;
    @SerializedName("Name")
    @Expose
    private String Name ;
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

//    public String getTranslitId() {
//        return this.TranslitId;
//    }
//    public void setTranslitId(String TranslitId) {
//        this.TranslitId = TranslitId;
//    }
}
