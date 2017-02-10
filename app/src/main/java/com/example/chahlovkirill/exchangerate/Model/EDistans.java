package com.example.chahlovkirill.exchangerate.Model;

/**
 * Created by chahlov.kirill on 10/02/17.
 */

public enum EDistans{
   all(0.0), km1(1.0), km3(3.0), km5(5.0);

    private final Double value;

    private EDistans(Double value){
        this.value = value;
    }
}
