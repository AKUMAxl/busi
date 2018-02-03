package com.yangchedou.module_personal.BaseInfo;

import android.content.Context;

/**
 * Created by ADMIN on 2017/11/30.
 */

public interface BaseinfoPersenter {

    void getBaseinfo();

    void getAreainfo();

    void updateBaseinfo(String picList,String linkMan,String manageScope,String introduce,String linkAddress,String city, String province,String file0,
                        String company,String cityCode,String detail,String areaCode,String area,String provinceCode,String hours, String name,String linkPhone);

    void onDestory();

}
