package com.example.chahlovkirill.exchangerate.WebService;

/**
 * Created by chahlov.kirill on 18/01/17.
 */
import com.example.chahlovkirill.exchangerate.Model.CitiyModel;
import java.util.List;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.converter.gson.GsonConverterFactory;


public class WebDownloadJson {

    private final String BASE_URL = "http://wsv3.cash2cash.ru/ExRatesJson.svc";

    public static void getData(){
        try {
            String urlmod = BASE_URL + METHOD + parametrs + value;
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        catch (Exception e){

        }
    }

}