package com.yangchedou.module_main;

import android.content.Context;

/**
 * Created by ADMIN on 2017/11/17.
 */

public class MainPersenterImpl implements MainPersenter,MainModel.onCheckVersionResult{

    private MainView mainView;
    private Context context;
    private MainModel mainModel;


    public MainPersenterImpl(Context context,MainView mainView){
        this.context = context;
        this.mainView = mainView;
        mainModel = new MainModelImpl();
    }

    @Override
    public void checkUpdate() {
        mainModel.getVersion(context,this);
    }

    @Override
    public void Destory() {
        if (mainView!=null){
            mainView = null;
        }
    }

    @Override
    public void update(String downLoadURI) {
        if (mainView!=null){
            mainView.downLoadDialog(downLoadURI);
        }
    }

    @Override
    public void lastVersion() {
        if (mainView!=null){
            mainView.showToast("lastVersion");
        }
    }

    @Override
    public void onError(String errorInfo){
        if (mainView!=null){
            mainView.showToast("lastVersion");
        }
    }
}
