package com.yangchedou.module_personal.BaseInfo;

import android.app.Activity;
import android.content.Context;

/**
 * Created by ADMIN on 2017/11/30.
 */

public interface BaseinfoModel {

    interface onGetBaseinfoListener{

        void success(String baseInfo);

        void failure(String errorInfo);

    }

    interface onUpdateBaseinfoListener{

        void update_success(String resultInfo);

        void update_failure(String errorInfo);

    }

    void getBaseinfo(Context context,onGetBaseinfoListener onGetBaseinfoListener);

    void updateBaseinfo(Context context, String picList,String linkMan,String manageScope,String introduce,String linkAddress,String city, String province,String file0,
                        String company,String cityCode,String detail,String areaCode,String area,String provinceCode,String hours, String name,String linkPhone,onUpdateBaseinfoListener onUpdateBaseinfoListener);
}
