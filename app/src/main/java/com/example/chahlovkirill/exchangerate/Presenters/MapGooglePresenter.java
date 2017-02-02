package com.example.chahlovkirill.exchangerate.Presenters;

import android.content.Context;
import android.util.Log;

import com.example.chahlovkirill.exchangerate.Activity.MapGoogleActivity;
import com.example.chahlovkirill.exchangerate.AppSetting.Settings;
import com.example.chahlovkirill.exchangerate.Cluster.MyItem;
import com.example.chahlovkirill.exchangerate.Model.BankModel;
import com.example.chahlovkirill.exchangerate.Model.CityModel;
import com.example.chahlovkirill.exchangerate.Model.Gis2Model.Gis2Model;
import com.example.chahlovkirill.exchangerate.Model.Gis2Model.Result;
import com.example.chahlovkirill.exchangerate.Services.DataService;
import com.example.chahlovkirill.exchangerate.Services.IControlListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by chahlov.kirill on 27/01/17.
 */

public class MapGooglePresenter implements IControlListener {

    private Gis2Model gis2Model;
    private Context context;
    private String selectedBank;

    public MapGooglePresenter(Context context, String selectedBank){
        this.context = context;
        this.selectedBank = selectedBank;
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

    public void DownloadOfServices(){
        DataService.getInstance().addListener(this);
        DataService.getInstance().Gis2DataSearchDownload(
                selectedBank,
                Settings.getToSelectedCityName(context),
                1
        );
    }

    int page = 1;
    int inquiry = 1;
    @Override
    public void onGis2DataSearchDownload(Gis2Model Gis2) {
        this.gis2Model = Gis2;
        MapGoogleActivity mapGoogleActivity = (MapGoogleActivity) context;

        if (!gis2Model.getResponse_code().equals("400")){
            if(gis2Model.getresult()!= null){
                if (gis2Model.getresult().size() == 50 &
                        gis2Model.getresult().size() != 0){
                    page++;
                    inquiry++;
                    DataService.getInstance().Gis2DataSearchDownload(selectedBank, Settings.getToSelectedCityName(context), page);
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

    private void VerificationDoNotMatchTheName(){//перестал правильно работать а именно удаляет всё подряд
        if(gis2Model.getresult()!= null){
            String selectedBankUp = selectedBank.toUpperCase();
            Iterator<Result> iterResult = gis2Model.getresult().iterator();
            Log.d("нас = ",String.valueOf(gis2Model.getresult().size()));
            while(iterResult.hasNext()){
                Result result = iterResult.next();
                //Log.d("я = ",iterResult.next().getName());
                String nameBank = result.getName();//Log.d("я = ","ошибка");
                nameBank = nameBank.toUpperCase();
                if (!nameBank.contains(selectedBankUp)){
                    iterResult.remove();
                    Log.e(result.getName()+" = ","элемент удален за не совпадении имени");
                }
            }
        }
    }

    @Override
    public void onCitiesDownloaded(List<CityModel> cities) {

    }

    @Override
    public void onBanksDownloaded(List<BankModel> banks) {

    }
}