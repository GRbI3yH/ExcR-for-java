package com.example.chahlovkirill.exchangerate.Presenters;

/**
 * Created by chahlov.kirill on 23/01/17.
 */
//import android.support.annotation.NonNull;
//
//import java.lang.ref.WeakReference;
//
//public abstract class BasePresenter {
//    protected M model;
//    private WeakReference<V> view;
//
//    public void setModel(M model) {
//        resetState();
//        this.model = model;
//        if (setupDone()) {
//            updateView();
//        }
//    }
//
//    protected void resetState() {
//    }
//
//    public void bindView(@NonNull V view) {
//        this.view = new WeakReference<>(view);
//        if (setupDone()) {
//            updateView();
//        }
//    }
//
//    public void unbindView() {
//        this.view = null;
//    }
//
//    protected V view() {
//        if (view == null) {
//            return null;
//        } else {
//            return view.get();
//        }
//    }
//
//    protected abstract void updateView();
//
//    protected boolean setupDone() {
//        return view() != null && model != null;
//    }
//}
