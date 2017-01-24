package com.example.chahlovkirill.exchangerate.Services;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chahlov.kirill on 24/01/17.
 */

public class ListenersRegistrator {

    private List listeners = new ArrayList();

    public void  addListener(ControlListener listener){
        this.listeners.add(listener);
    }

    private void fireAction(int x){
        for (int i = 0; i < listeners.size();i++){
            ControlListener listener = (ControlListener)listeners.get(i);
            listener.onDataChanged(x);
        }
    }
}
