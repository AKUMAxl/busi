package com.yangchedou.module_work;

import android.content.Context;

/**
 * Created by ADMIN on 2017/11/17.
 */

public class WorkPersenterImpl implements WorkPersenter,WorkModelImpl.onInfoListener {

    private Context context;
    private WorkView workView;
    private WorkModel workModel;

    private boolean isFours;
    private int con;
    private String carBrand;

    public WorkPersenterImpl(Context context,WorkView workView){
        this.context = context;
        this.workView = workView;
        this.workModel = new WorkModelImpl();
    }


    @Override
    public void unreadMessageCount() {
        if (workModel!=null){
            workModel.getWorkInfo(context,this);
        }
    }

    @Override
    public void changeShopState(boolean onbusi) {
        if (workView!=null){
            workModel.changeShopState(context,onbusi,this);
        }
    }

    @Override
    public void clickMessage() {
        String text = "";
        if (workView!=null&&isFours){
            text = "您有 "+con+" 单 "+carBrand+" 保养预购券待服务！";
        }else if (workView!=null&&(!isFours)){
            text = "您没有保养预购券服务！";
        }
        workView.showToast(text);
    }

    @Override
    public void Destory() {
        if (workView!=null){
            workView = null;
        }
    }


    @Override
    public void shopState(boolean onBusi) {
        if (workView!=null){
            workView.dismissDialog();
            workView.changeShopState(onBusi);
        }
    }

    @Override
    public void changeSateSuccess() {
        if (workView!=null){
            workView.showToast(context.getResources().getString(R.string.ztxgcg));
        }
    }

    @Override
    public void changeStateFalure(boolean currentState) {
        if (workView!=null){
            workView.changeShopState(currentState);
            workView.showToast(context.getResources().getString(R.string.ztxgsb));
        }
    }

    @Override
    public void Refresh(int maintainCount,int washCount) {
        if (workView!=null){
            workView.maintainCount(maintainCount);
            workView.washCount(washCount);
        }
    }

    @Override
    public void setBaseInfo(String photo_url, String busiName, String busiId) {
        if (workView!=null){
            workView.loadPhoto(photo_url);
            workView.setBusiId(busiId);
            workView.setBusiName(busiName);
        }
    }

    @Override
    public void setMessageInfo(boolean isFours, int con, String carBrand) {
        this.isFours = isFours;
        this.con = con;
        this.carBrand = carBrand;
    }

    @Override
    public void onError(String errorinfo) {
        if (workView!=null){
            workView.showToast(errorinfo);
        }
    }
}
