package com.yangchedou.lib_common;

import android.os.Environment;

/**
 * Created by ADMIN on 2017/11/8.
 */

public class AllData {

    //正式
    public static final String url = "http://120.25.152.168:8081/cars/http/channel.action";
    //测试 内网
    //public static final String url = "http://192.168.1.135:8081/cars/http/channel.action";
    //测试 外网
    //public static final String url = "http://120.25.152.168:8088/cars/http/channel.action";

    //public static final String host = url.substring(7,21);
    public static final String uploadPic_host = "192.168.1.121";
    /** 添加服务(表单提交方式) **/
    public static final String ADD_SERVICE = "http://192.168.1.135:8081/cars/upload/service.action";
    /** 添加商品(表单提交方式) **/
    public static final String ADD_GOODS = "http://192.168.1.135:8081/cars/upload/goods.action";
    /** 信息修改 **/
    public static final String INFO_UPDATE = "http://192.168.1.135:8081/cars/upload/business.action";


    public static String BasePath = Environment.getExternalStorageDirectory()+"/ycd_busi";

    public static final int SET_SJJJ_REQUEST_CODE = 1;
    public static final int SET_SJJJ_RESPONSE_CODE = 2;
    public static final int SELECT_FWFW_REQUEST_CODE = 3;
    public static final int SELECT_JYXM_REQUEST_CODE = 4;
    public static final int SELECT_MULTISELECT_CODE = 5;
}
