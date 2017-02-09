package com.example.chahlovkirill.exchangerate.Services;

import android.util.Log;

import com.example.chahlovkirill.exchangerate.Model.Gis2Model.Gis2Model;
import com.example.chahlovkirill.exchangerate.Model.Gis2Model.Result;

import java.util.Iterator;

/**
 * Created by chahlov.kirill on 09/02/17.
 */

public class Operations2GISModel {
    public static Gis2Model CheckMismatchRubric(Gis2Model gis2,String whatRubric ){
        if(gis2.getresult()!= null){
            Iterator<Result> iterResult = gis2.getresult().iterator();
            while(iterResult.hasNext()){
                Result result = iterResult.next();
                boolean rubricsСheck = true;
                if (result.getRubrics() != null){
                    for (String rubric: result.getRubrics()) {
                        if(rubric.equals(whatRubric)){
                            rubricsСheck = false;
                        }
                    }
                }
                if (rubricsСheck){
                    iterResult.remove();
                    Log.e(result.getName()+" = ","элемент удален за несовподением рубрики");
                }
            }
        }
        return gis2;
    }

    public static Gis2Model VerificationDoNotMatchTheName(Gis2Model gis2, String bankName){
        if(gis2.getresult()!= null){
            String selectedBankUp = bankName.toUpperCase();
            Iterator<Result> iterResult = gis2.getresult().iterator();
            Log.d("нас = ",String.valueOf(gis2.getresult().size()));
            while(iterResult.hasNext()){
                Result result = iterResult.next();
                String nameBank = result.getName().toUpperCase();
                if (!nameBank.contains(selectedBankUp)){
                    iterResult.remove();
                    Log.e(result.getName()+" = ","элемент удален за несовпадением имени");
                }
            }
        }
        return gis2;
    }
}
