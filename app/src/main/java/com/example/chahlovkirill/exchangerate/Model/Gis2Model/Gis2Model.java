package com.example.chahlovkirill.exchangerate.Model.Gis2Model;

import java.util.List;

/**
 * Created by chahlov.kirill on 27/01/17.
 */
import com.google.gson.annotations.*;

import java.io.Serializable;


public class Gis2Model {
    @SerializedName("api_version")
    @Expose
    private String api_version;
    @SerializedName("response_code")
    @Expose
    private String response_code;
    @SerializedName("what")
    @Expose
    private String what;
    @SerializedName("where")
    @Expose
    private String where;
    @SerializedName("total")
    @Expose
    private String total;
    @SerializedName("did_you_mean")
    @Expose
    private DidYouMean did_you_mean;
    @SerializedName("advertising")
    @Expose
    private List<Advertising> advertising;
    @SerializedName("result")
    @Expose
    private List<Result> result;

    public String getApi_version() {
        return this.api_version;
    }
    public void setApi_version(String api_version) {
        this.api_version = api_version;
    }



    public String getResponse_code() {
        return this.response_code;
    }
    public void setResponse_code(String response_code) {
        this.response_code = response_code;
    }



    public String getWhat() {
        return this.what;
    }
    public void setWhat(String what) {
        this.what = what;
    }



    public String getWhere() {
        return this.where;
    }
    public void setWhere(String where) {
        this.where = where;
    }



    public String getTotal() {
        return this.total;
    }
    public void setTotal(String total) {
        this.total = total;
    }



    public DidYouMean getDid_you_mean() {
        return this.did_you_mean;
    }
    public void setDid_you_mean(DidYouMean did_you_mean) {
        this.did_you_mean = did_you_mean;
    }



    public List<Advertising> getAdvertising() {
        return this.advertising;
    }
    public void setAdvertising(List<Advertising> advertising) {
        this.advertising = advertising;
    }



    public List<Result> getResult() {
        return this.result;
    }
    public void setResult(List<Result> result) {
        this.result = result;
    }
}

