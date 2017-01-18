package com.example.chahlovkirill.exchangerate.Model;

/**
 * Created by chahlov.kirill on 18/01/17.
 */
import com.google.gson.annotations.*;

public class BankModel {
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

    private double getEURBuy(){
        return this.EURBuy;
    }
    private void setEURBuy(double EURBuy){
        this.EURBuy = EURBuy;
    }

    private double getEURSell(){
        return this.EURSell;
    }
    private void setEURSell(double EURSell){
        this.EURSell = EURSell;
    }

    private double getUSDBuy(){
        return this.USDBuy;
    }
    private void setUSDBuy(double USDBuy){
        this.USDBuy = USDBuy;
    }

    private double getUSDSell(){
        return this.USDSell;
    }
    private void setUSDSell( double USDSell){
        this.USDSell = USDSell;
    }

    private String getMessage(){
        return this.Message;
    }
    private void setMessage(String Message){
        this.Message = Message;
    }

//    private String getTranslitId(){
//        return this.TranslitId;
//    }
//    private void setTranslitId(String TranslitId){
//        this.TranslitId = TranslitId;
//    }

}
