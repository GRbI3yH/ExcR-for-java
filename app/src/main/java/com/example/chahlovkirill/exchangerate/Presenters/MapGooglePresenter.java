package com.example.chahlovkirill.exchangerate.Presenters;

import android.content.Context;
import android.util.Log;

import com.example.chahlovkirill.exchangerate.Activity.MapGoogleActivity;
import com.example.chahlovkirill.exchangerate.Cluster.Position;
import com.example.chahlovkirill.exchangerate.DataProvider.DataProvider;
import com.example.chahlovkirill.exchangerate.DataProvider.IDataProviderOutput;
import com.example.chahlovkirill.exchangerate.Model.BankModel;
import com.example.chahlovkirill.exchangerate.Model.BankViewModel;
import com.example.chahlovkirill.exchangerate.Model.CityModel;
import com.example.chahlovkirill.exchangerate.Model.EExchangeAction;
import com.example.chahlovkirill.exchangerate.Model.Gis2Model.Gis2Model;
import com.example.chahlovkirill.exchangerate.Model.Gis2Model.Result;
import com.example.chahlovkirill.exchangerate.Services.Operations2GISModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chahlov.kirill on 27/01/17.
 */

public class MapGooglePresenter implements IDataProviderOutput {

    private Gis2Model gis2Model;
    private Context context;
    private BankViewModel bankView;
    private int page = 1;

    public MapGooglePresenter(Context context, BankViewModel bankView){
        this.context = context;
        this.bankView = bankView;
        DataProvider.getInstance().addListener(this);
        DataProvider.getInstance().getTheSelectedCity();
    }

    public List<Position> getPositionBanks(){
        List<Position> offsetItem = new ArrayList<Position>();
        if (gis2Model.getresult()!=null){
            for (Result gis2Result : gis2Model.getresult() ) {
                if(gis2Result != null & gis2Result.getLat() !=  null & gis2Result.getLon() != null){
                    offsetItem.add(new Position(Double.valueOf(gis2Result.getLat()), Double.valueOf(gis2Result.getLon()), gis2Result.getName()));
                }
            }
        }
        return offsetItem;
    }

//    private void CheckMismatchRubric(){
//        if(gis2Model.getresult()!= null){
//            Iterator<Result> iterResult = gis2Model.getresult().iterator();
//            while(iterResult.hasNext()){
//                Result result = iterResult.next();
//                boolean rubricsСheck = true;
//                if (result.getRubrics() != null){
//                    for (String rubric: result.getRubrics()) {
//                        if(rubric.equals("Банки")){
//                            rubricsСheck = false;
//                        }
//                    }
//                }
//                if (rubricsСheck){
//                    iterResult.remove();
//                    Log.e(result.getName()+" = ","элемент удален за несовподением рубрики");
//                }
//            }
//        }
//    }
//
//    private void VerificationDoNotMatchTheName(){
//        if(gis2Model.getresult()!= null){
//            String selectedBankUp = bankView.getName().toUpperCase();
//            Iterator<Result> iterResult = gis2Model.getresult().iterator();
//            Log.d("нас = ",String.valueOf(gis2Model.getresult().size()));
//            while(iterResult.hasNext()){
//                Result result = iterResult.next();
//                String nameBank = result.getName().toUpperCase();
//                if (!nameBank.contains(selectedBankUp)){
//                    iterResult.remove();
//                    Log.e(result.getName()+" = ","элемент удален за несовпадением имени");
//                }
//            }
//        }
//    }

    @Override
    public void didReceiveGis2Data(Gis2Model gis2) {
        Log.i("MapGooglePresenter","didReceiveGis2Data");
        //this.gis2Model = gis2;
        MapGoogleActivity mapGoogleActivity = (MapGoogleActivity) context;
        if (!gis2.getResponse_code().equals("400")){
            if(gis2.getresult()!= null){
                int sizeResult = gis2.getresult().size();
                if (sizeResult == 50 & sizeResult != 0){
                    page++;
                    DataProvider.getInstance().getTheSelectedCity();
                }
                gis2Model = Operations2GISModel.VerificationDoNotMatchTheName(
                        Operations2GISModel.CheckMismatchRubric(gis2,"Банки"),bankView.getName());
                for (Result result:gis2Model.getresult()) {
                    Log.e("Оставшийся элемент = ",result.getName());
                }
                mapGoogleActivity.renderMarkers();
            }
        }
    }

    @Override
    public void didReceiveTheSelectedCity(CityModel city) {
        Log.i("MapGooglePresenter","didReceiveTheSelectedCity");
        DataProvider.getInstance().getGis2Data(
                "отделения_"+bankView.getName(),
                city.getName(),
                page
        );
    }

    @Override
    public void didReceiveCities(List<CityModel> cities) {}

    @Override
    public void didReceiveBanks(List<BankModel> banks) {}

    @Override
    public void didReceiveSelectedCurrencyForSorting(EExchangeAction mode) {}


}



