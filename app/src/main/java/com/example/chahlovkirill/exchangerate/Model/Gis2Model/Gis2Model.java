package com.example.chahlovkirill.exchangerate.Model.Gis2Model;

import java.util.List;

/**
 * Created by chahlov.kirill on 27/01/17.
 */
import com.google.gson.annotations.*;

import java.io.Serializable;


public class Gis2Model {

    @SerializedName("response_code")
    @Expose
    private String response_code;
    @SerializedName("what")
    @Expose
    private String what;
    @SerializedName("result")
    @Expose
    private List<Result> result;

    public List<Result> getresult() {
        return this.result;
    }
    public void setresult(List<Result> result) {
        this.result = result;
    }

    public String getResponse_code() {
        return this.response_code;
    }
    public void setResponse_code(String response_code) {
        this.response_code = response_code;
    }

    public String getWhat() {
        return what;
    }
    public void setWhat(String what) {
        this.what = what;
    }
}

