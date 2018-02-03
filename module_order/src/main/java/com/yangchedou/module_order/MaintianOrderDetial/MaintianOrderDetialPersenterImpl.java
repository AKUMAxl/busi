package com.yangchedou.module_order.MaintianOrderDetial;

import android.app.Application;

/**
 * Created by ADMIN on 2018/1/5.
 */

public class MaintianOrderDetialPersenterImpl implements MaintianOrderDetialPersenter,MaintianOrderDetialModel.OnMaintianOrderDetialModelListener {

    private MaintianOrderDetialModel maintianOrderDetialModel;
    private MaintianOrderDetialView maintianOrderDetialView;

    public MaintianOrderDetialPersenterImpl(MaintianOrderDetialView maintianOrderDetialView, Application application){
        maintianOrderDetialModel = new MaintianOrderDetialModelImpl(application);
        this.maintianOrderDetialView = maintianOrderDetialView;
    }

    @Override
    public void getData() {
        maintianOrderDetialModel.getData(1116l,2067l, this);
    }

    @Override
    public void onstart() {

    }

    @Override
    public void onNext() {
        maintianOrderDetialView.showData("123");
    }

    @Override
    public void onComplete() {

    }
}
