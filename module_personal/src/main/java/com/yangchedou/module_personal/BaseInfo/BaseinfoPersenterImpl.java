package com.yangchedou.module_personal.BaseInfo;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;
import com.yangchedou.lib_common.Area;
import com.yangchedou.lib_common.utils.SharedPreferencesUtil;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by ADMIN on 2017/11/30.
 */

public class BaseinfoPersenterImpl implements BaseinfoPersenter,BaseinfoModel.onGetBaseinfoListener,BaseinfoModel.onUpdateBaseinfoListener {

    private Context context;
    private BaseinfoModel baseinfoModel;
    private BaseinfoView baseinfoView;

    public BaseinfoPersenterImpl(Context context,BaseinfoView baseinfoView){
        this.baseinfoModel = new BaseinfoModelImpl();
        this.baseinfoView = baseinfoView;
        this.context = context.getApplicationContext();
    }

    @Override
    public void getBaseinfo() {
        baseinfoModel.getBaseinfo(context,this);
    }

    @Override
    public void getAreainfo() {
        baseinfoView.toSelectArea();
    }

    @Override
    public void updateBaseinfo(String picList,String linkMan,String manageScope,String introduce,String linkAddress,String city, String province,String file0,
                               String company,String cityCode,String detail,String areaCode,String area,String provinceCode,String hours, String name,String linkPhone) {
        baseinfoModel.updateBaseinfo(context,picList,linkMan,manageScope,introduce,linkAddress,city,province,file0,company,cityCode,detail,areaCode,area,provinceCode,hours,name,linkPhone,this);
    }

    @Override
    public void onDestory() {
        baseinfoView = null;
    }

    @Override
    public void success(String successInfo) {

        Gson gson =  new Gson();
        BaseinfoBean baseinfoBean = gson.fromJson(successInfo,BaseinfoBean.class);

        String[] arr_pic =  baseinfoBean.getPicList().split("\\|");

        ArrayList<String> list_pic = new ArrayList<>();
        for (String pic:Arrays.asList(arr_pic)) {
            list_pic.add(SharedPreferencesUtil.getIntance(context).GetData(SharedPreferencesUtil.KEY.PATH)+pic);
        }
        baseinfoView.initUI(SharedPreferencesUtil.getIntance(context).GetData(SharedPreferencesUtil.KEY.USER_ID),
                baseinfoBean.getPicList(),
                baseinfoBean.getCompany(),
                baseinfoBean.getName(),
                baseinfoBean.getProvince(),
                baseinfoBean.getCity(),
                baseinfoBean.getArea(),
                baseinfoBean.getProvinceCode(),
                baseinfoBean.getCityCode(),
                baseinfoBean.getAreaCode(),
                baseinfoBean.getLinkAddress(),
                baseinfoBean.getIntroduce(),
                baseinfoBean.getManageScope(),
                baseinfoBean.getDetail(),
                baseinfoBean.getHours(),
                baseinfoBean.getLinkMan(),
                baseinfoBean.getLinkPhone(),
                list_pic);

    }

    @Override
    public void failure(String errorInfo) {

    }

    @Override
    public void update_success(String resultInfo) {

    }

    @Override
    public void update_failure(String errorInfo) {

    }
}
