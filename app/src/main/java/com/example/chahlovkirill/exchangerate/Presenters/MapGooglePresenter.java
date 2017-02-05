package com.example.chahlovkirill.exchangerate.Presenters;

import android.content.Context;
import android.util.Log;

import com.example.chahlovkirill.exchangerate.Activity.MapGoogleActivity;
import com.example.chahlovkirill.exchangerate.Cluster.MyItem;
import com.example.chahlovkirill.exchangerate.DataProvider.DataProvider;
import com.example.chahlovkirill.exchangerate.DataProvider.IDataProviderOutput;
import com.example.chahlovkirill.exchangerate.Model.BankModel;
import com.example.chahlovkirill.exchangerate.Model.CityModel;
import com.example.chahlovkirill.exchangerate.Model.EExchangeAction;
import com.example.chahlovkirill.exchangerate.Model.Gis2Model.Gis2Model;
import com.example.chahlovkirill.exchangerate.Model.Gis2Model.Result;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by chahlov.kirill on 27/01/17.
 */
// здесь нужна грамотная фильтрация
public class MapGooglePresenter implements IDataProviderOutput {

    private Gis2Model gis2Model;
    private Context context;
    private String selectedBank;
    private String selectedCity;
    int page = 1;

    public MapGooglePresenter(Context context, String selectedBank){
        this.context = context;
        this.selectedBank = selectedBank;
        DataProvider.getInstance().addListener(this);
        DataProvider.getInstance().getTheSelectedCity();
    }

    public List<MyItem> getPositionBanks(){
        List<MyItem> offsetItem = new ArrayList<MyItem>();
        if (gis2Model.getresult()!=null){
            for (Result gis2Result : gis2Model.getresult() ) {
                if(gis2Result != null & gis2Result.getLat() !=  null & gis2Result.getLon() != null){
                    offsetItem.add(new MyItem(Double.valueOf(gis2Result.getLat()), Double.valueOf(gis2Result.getLon())));
                }
            }
        }
        return offsetItem;
    }

    private void СheckMismatchColumn(){
        if(gis2Model.getresult()!= null){
            Iterator<Result> iterResult = gis2Model.getresult().iterator();
            while(iterResult.hasNext()){
                Result result = iterResult.next();
                boolean rubricsСheck = true;
                Log.i("Gis2ClearTrash","Имя="+result.getName()+"\tRubrics="+ result.getRubrics() );
                if (result.getRubrics() != null){
                    for (String rubric: result.getRubrics()) {
                        if(rubric.equals("Банки")){
                            rubricsСheck = false;
                        }
                    }
                }
                if (rubricsСheck){
                    iterResult.remove();
                    Log.e(result.getName()+" = ","элемент удален за несовподении рубрики");
                }
            }
        }
    }

    private void VerificationDoNotMatchTheName(){//перестал правильно работать а именно удаляет всё подряд. А вроде всё нормально
        if(gis2Model.getresult()!= null){
            String selectedBankUp = selectedBank.toUpperCase();
            Iterator<Result> iterResult = gis2Model.getresult().iterator();
            Log.d("нас = ",String.valueOf(gis2Model.getresult().size()));
            while(iterResult.hasNext()){
                Result result = iterResult.next();
                //Log.d("я = ",iterResult.next().getName());
                String nameBank = result.getName();//Log.d("я = ","ошибка");
                nameBank = nameBank.toUpperCase();
                if (nameBank.contains(selectedBankUp)){
                    Log.e(nameBank,"равен "+selectedBankUp);
                }
                else {
                    Log.e(nameBank,"не равен "+selectedBankUp);
                }
                if (!nameBank.contains(selectedBankUp)){
                    iterResult.remove();
                    Log.e(result.getName()+" = ","элемент удален за не совпадении имени");
                }
            }
        }
    }

    @Override
    public void didReceiveGis2Data(Gis2Model gis2) {
        Log.i("MapGooglePresenter","didReceiveGis2Data");
        this.gis2Model = gis2;
        MapGoogleActivity mapGoogleActivity = (MapGoogleActivity) context;

        if (!gis2Model.getResponse_code().equals("400")){
            if(gis2Model.getresult()!= null){
                if (gis2Model.getresult().size() == 50 &
                        gis2Model.getresult().size() != 0){
                    page++;
                    DataProvider.getInstance().getTheSelectedCity();
//                    DataProvider.getInstance().getGis2Data(selectedBank, Settings.getTheSelectedCityName(context), page);
//                    DataService.getInstance().Gis2DataSearchDownload(selectedBank, Settings.getTheSelectedCityName(context), page);
                }
                СheckMismatchColumn();
                VerificationDoNotMatchTheName();

                for (Result result:gis2Model.getresult()) {
                    for (String rubric: result.getRubrics()) {
                        Log.e("Оставшийся элемент = ",result.getName());
                    }
                }
                mapGoogleActivity.renderMarkers ();
            }
        }
    }

    @Override
    public void didReceiveTheSelectedCity(CityModel city) {
        Log.i("MapGooglePresenter","didReceiveTheSelectedCity");
        DataProvider.getInstance().getGis2Data(
                selectedBank,
                city.getName(),
                page
        );
    }

    @Override
    public void didReceiveCities(List<CityModel> cities) {
        //Log.i("MapGooglePresenter","didReceiveCities");
    }



    @Override
    public void didReceiveBanks(List<BankModel> banks) {
        //Log.i("MapGooglePresenter","didReceiveBanks");
    }

    @Override
    public void didReceiveSelectCurrencyForSorting(EExchangeAction mode) {
        //Log.i("MapGooglePresenter","didReceiveSelectCurrencyForSorting");
    }


}



