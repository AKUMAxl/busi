package com.yangchedou.module_personal.BaseInfo;

import android.app.Activity;
import android.content.Context;

import com.yangchedou.lib_common.NetWork.NetInterfaceUtil;
import com.yangchedou.lib_common.NetWork.okHttpResponse;
import com.yangchedou.lib_common.NetWork.okHttpResponseImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by ADMIN on 2017/11/30.
 */

public class BaseinfoModelImpl implements BaseinfoModel {

    @Override
    public void getBaseinfo(Context context, final onGetBaseinfoListener onGetBaseinfoListener) {
        NetInterfaceUtil.getIntance().getBaseinf(context, new okHttpResponse() {

            @Override
            public void requsetFailure(String failureStr) {
                onGetBaseinfoListener.failure(failureStr);
            }

            @Override
            public void responseSuccess(String successStr) {
                onGetBaseinfoListener.success(successStr);
            }

            @Override
            public void dealFailureResult(String failureInfo) {
                onGetBaseinfoListener.failure(failureInfo);
            }
        });
    }

    @Override
    public void updateBaseinfo(Context context, String picList,String linkMan,String manageScope,String introduce,String linkAddress,String city, String province,String file0,
                               String company,String cityCode,String detail,String areaCode,String area,String provinceCode,String hours, String name,String linkPhone,onUpdateBaseinfoListener onUpdateBaseinfoListener) {

        NetInterfaceUtil.getIntance().updateBaseInfo(context, picList, linkMan, manageScope, introduce, linkAddress, city, province, file0, company, cityCode, detail, areaCode, area, provinceCode, hours, name, linkPhone, new okHttpResponse() {
            @Override
            public void requsetFailure(String failureStr) {

            }

            @Override
            public void responseSuccess(String successStr) {

            }

            @Override
            public void dealFailureResult(String failureInfo) {

            }
        });
    }


}
