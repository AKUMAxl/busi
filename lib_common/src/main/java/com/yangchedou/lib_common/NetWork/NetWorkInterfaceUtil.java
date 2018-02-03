package com.yangchedou.lib_common.NetWork;

import android.content.Context;


/**
 * Created by 27740 on 2017/10/28.
 */

public interface NetWorkInterfaceUtil {

    void Login(Context context, String interfaceName, String userName, String password, okHttpResponse okHttpResponse);

    void CUS_Login(Context context, String interfaceName, okHttpResponse okHttpResponse);

    //商户端版本检测
    void getVersion(Context context,okHttpResponse okHttpResponse);

    //工作台页信息
    void getWorkInfo(Context context,okHttpResponse okHttpResponse);

    //改变商家状态
    void changeShopState(Context context,Boolean onBusi,okHttpResponse okHttpResponse);

    //保养订单列表
    void getMaintianList(Context context,String state,int start,int count,okHttpResponse okHttpResponse);

    //获取用户基本信息
    void getBaseinf(Context context,okHttpResponse okHttpResponse);

    //获取服务范围列表
    void getFwfwList(Context context,okHttpResponse okHttpResponse);

    //获取经营项目列表
    void getJyxmList(Context context,okHttpResponse okHttpResponse);

    //更新个人信息
    void updateBaseInfo(Context context,String picList,String linkMan,String manageScope,String introduce,String linkAddress,String city, String province,String file0,
                        String company,String cityCode,String detail,String areaCode,String area,String provinceCode,String hours, String name,String linkPhone, okHttpResponse okHttpResponse);

}
