package com.yangchedou.module_personal.BaseInfo;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by ADMIN on 2017/11/30.
 */

public interface BaseinfoView {

    void showDataPacker();

    void toSelectArea();

    void toSelectTime();

    void initUI(String id,String picList,String name,String csmc,String province,String city,String area,String provinceCode,String cityCode,String areaCode,String address,String sjjs,String yyxm,String fwfw,String time,String lxr,String kfdh,ArrayList<String> list_businessPics);
}
