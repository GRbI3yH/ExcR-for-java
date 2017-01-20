package com.example.chahlovkirill.exchangerate.Model;

/**
 * Created by chahlov.kirill on 18/01/17.
 */
import com.google.gson.annotations.*;

import java.io.Serializable;

public class BankModel implements Serializable , Comparable<BankModel> {
    @SerializedName("BankId")
    @Expose
    private String BankId;
    @SerializedName("Name")
    @Expose
    private String Name;
    @SerializedName("EURBuy")
    @Expose
    private double EURBuy;
    @SerializedName("EURSell")
    @Expose
    private double EURSell;
    @SerializedName("USDBuy")
    @Expose
    private double USDBuy;
    @SerializedName("USDSell")
    @Expose
    private double USDSell;
    @SerializedName("Message")
    @Expose
    private String Message;
//    private String TranslitId;

    public String getBankId(){
        return this.BankId;
    }
    public void setBankId(String BankId){
        this.BankId = BankId;
    }

    public String getName(){
        return this.Name;
    }
    public void setName(String Name){
        this.Name = Name;
    }

    public double getEURBuy(){
        return this.EURBuy;
    }
    public void setEURBuy(double EURBuy){
        this.EURBuy = EURBuy;
    }

    public double getEURSell(){
        return this.EURSell;
    }
    public void setEURSell(double EURSell){
        this.EURSell = EURSell;
    }

    public double getUSDBuy(){
        return this.USDBuy;
    }
    public void setUSDBuy(double USDBuy){
        this.USDBuy = USDBuy;
    }

    public double getUSDSell(){
        return this.USDSell;
    }
    public void setUSDSell( double USDSell){
        this.USDSell = USDSell;
    }

    public String getMessage(){
        return this.Message;
    }
    public void setMessage(String Message){
        this.Message = Message;
    }

    @Override
    public int compareTo(BankModel o) {
        int result;
        result = Double.compare(this.EURBuy ,    o.getEURBuy());
        if(result != 0) return result;

        result = Double.compare(this.EURSell,    o.getEURSell());
        if(result != 0) return result;

        result = Double.compare(this.USDBuy,     o.getUSDBuy());
        if(result != 0) return result;

        result = Double.compare(this.USDSell,    o.getUSDSell());
        return result;
    }

//    private String getTranslitId(){
//        return this.TranslitId;
//    }
//    private void setTranslitId(String TranslitId){
//        this.TranslitId = TranslitId;
//    }

}
