package com.yangchedou.module_personal.BaseInfo;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.orhanobut.logger.Logger;
import com.yangchedou.lib_common.AllData;
import com.yangchedou.lib_common.Base.BaseActivity;
import com.yangchedou.lib_common.Base.ChangeActivity;
import com.yangchedou.lib_common.Base.MultiSelect.MultiSelectActivity;
import com.yangchedou.lib_common.MobileInfo;
import com.yangchedou.lib_common.PickerHelper;
import com.yangchedou.lib_common.Weight.MyProgressDialog;
import com.yangchedou.lib_common.Weight.SelectAreaDialog;
import com.yangchedou.lib_common.Weight.XlTitle;
import com.yangchedou.lib_common.utils.SharedPreferencesUtil;
import com.yangchedou.module_personal.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import cn.qqtheme.framework.entity.City;
import cn.qqtheme.framework.entity.County;
import cn.qqtheme.framework.entity.Province;
import cn.qqtheme.framework.picker.AddressPicker;
import cn.qqtheme.framework.picker.DoublePicker;
import cn.qqtheme.framework.picker.TimePicker;
import cn.qqtheme.framework.util.ConvertUtils;
import rx.Observable;


/**
 * Created by ADMIN on 2017/11/24.
 */

@Route(path = "/module_personal/baseInfo")
public class BaseinfoActivity extends BaseActivity implements View.OnClickListener,BaseinfoView{

    private XlTitle title;
    private TextView tv_sjjj;
    private TextView tv_businessId,tv_szdq,tv_jyfw,tv_fwfw,tv_yysj;
    private EditText et_gsmc,et_csmc,et_xxdz,et_lxr,et_kfdh;

    private MyProgressDialog mpd;

    private SelectAreaDialog selectAreaDialog;

    private BaseinfoPersenter baseinfoPersenter;

    private ArrayList<String> list_businessPics;

    private AddressPicker picker_address;

    private TimePicker picker__time;

    private Boolean isFirstSelectTime = true;

    private String picList,linkMan,manageScope,introduce,linkAddress,city,file0,province,company,cityCode,detail,areaCode,area,provinceCode,hours,name,linkPhone;


    @Override
    public void loadView() {
        setContentView(R.layout.activity_baseinfo);
    }

    @Override
    public void initData() {

        baseinfoPersenter = new BaseinfoPersenterImpl(getApplicationContext(),this);

        title = findViewById(R.id.baseinfo_title);
        tv_sjjj = findViewById(R.id.baseinfo_tv_content_sjjj);
        tv_businessId = findViewById(R.id.baseinfo_tv_content_sjid);
        tv_szdq = findViewById(R.id.baseinfo_tv_content_szdq);
        tv_jyfw = findViewById(R.id.baseinfo_tv_content_jyxm);
        tv_fwfw = findViewById(R.id.baseinfo_tv_content_fwfw);
        tv_yysj = findViewById(R.id.baseinfo_tv_content_yysj);
        et_gsmc = findViewById(R.id.baseinfo_et_content_gsmc);
        et_csmc = findViewById(R.id.baseinfo_et_content_csmc);
        et_xxdz = findViewById(R.id.baseinfo_et_content_xxdz);
        et_lxr = findViewById(R.id.baseinfo_et_content_lxr);
        et_kfdh = findViewById(R.id.baseinfo_et_content_kfdh);

        tv_yysj.setOnClickListener(this);
        tv_fwfw.setOnClickListener(this);
        tv_jyfw.setOnClickListener(this);
        tv_szdq.setOnClickListener(this);
        tv_sjjj.setOnClickListener(this);


        picker__time = new TimePicker(oThis,TimePicker.HOUR_24);
        picker__time = PickerHelper.getIntance(getApplicationContext()).settingTimePicker(picker__time);
        picker__time.setOnTimePickListener(new TimePicker.OnTimePickListener() {
            @Override
            public void onTimePicked(String hour, String minute) {
                String[] arr_time = tv_yysj.getText().toString().split("-");
                if (isFirstSelectTime){
                    arr_time[0] = hour+":"+minute;
                }else {
                    arr_time[1] = hour+":"+minute;
                }
                isFirstSelectTime = !isFirstSelectTime;

                tv_yysj.setText(arr_time[0]+"-"+arr_time[1]);
                hours = arr_time[0]+"-"+arr_time[1];
            }
        });
        picker__time.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                if (!isFirstSelectTime){
                    picker__time.setTitleText("闭店时间");
                    picker__time.show();
                }
            }
        });

        title.setText(getResources().getString(R.string.jibenxinxi));
        title.setleftbg(R.mipmap.actionbar_back_hl);
        title.setrightText(R.string.baocun);
        title.setleftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oThis.finish();
            }
        });
        title.setrightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                private String picList,linkMan,manageScope,introduce,linkAddress,city,file0,province,company,cityCode,detail,areaCode,area,provinceCode,hours,name,linkPhone;

                baseinfoPersenter.updateBaseinfo(picList,et_lxr.getText().toString(),manageScope,introduce,et_xxdz.getText().toString(),city,province,file0,et_gsmc.getText().toString(),cityCode,detail,areaCode,area,provinceCode,hours,et_csmc.getText().toString(),et_kfdh.getText().toString());
            }
        });
    }

    @Override
    public void firstRequestNet() {
        baseinfoPersenter.getBaseinfo();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mpd!=null&&mpd.isShowing()){
            mpd.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        baseinfoPersenter.onDestory();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        //商家简介
        if (id==R.id.baseinfo_tv_content_sjjj){
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("pic_list", list_businessPics);
            bundle.putString("content",introduce);
            ChangeActivity.getIntance().ToNextActivityForResult(oThis,"/module_persional/businessBrief",bundle, AllData.SET_SJJJ_REQUEST_CODE);
        }
        //所在地区
        if (id==R.id.baseinfo_tv_content_szdq){
            baseinfoPersenter.getAreainfo();
        }
        //营业时间
        if (id==R.id.baseinfo_tv_content_yysj){
            toSelectTime();
            /*Intent intent = new Intent();
            intent.setData(Uri.parse("ycd://cus"));
            startActivity(intent);*/
        }
        //服务范围
        if (id==R.id.baseinfo_tv_content_fwfw){
            Intent intent = new Intent();
            intent.setClass(oThis, MultiSelectActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("from","fwfw");
            ChangeActivity.getIntance().ToNextActivityForResultByIntent(oThis,MultiSelectActivity.class,bundle,AllData.SELECT_FWFW_REQUEST_CODE);
        }
        //经营项目
        if (id==R.id.baseinfo_tv_content_jyxm){
            Intent intent = new Intent();
            intent.setClass(oThis, MultiSelectActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("from","jyxm");
            ChangeActivity.getIntance().ToNextActivityForResultByIntent(oThis,MultiSelectActivity.class,bundle,AllData.SELECT_JYXM_REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==AllData.SET_SJJJ_REQUEST_CODE&&resultCode==AllData.SET_SJJJ_RESPONSE_CODE){
            Bundle bundle = data.getExtras();
            list_businessPics = bundle.getStringArrayList("pic_list");
            introduce = bundle.getString("content","what");

            tv_sjjj.setText(introduce.toString());
            StringBuilder sb_net = new StringBuilder();
            StringBuilder sb_phone = new StringBuilder();
            for (String str:list_businessPics){
                Logger.i("---------"+str);
                if (str.contains(AllData.uploadPic_host)){
                    str = str.substring(7);
                    str = "http://"+str;
                    sb_net.append(str).append("|");
                }else {
                    sb_phone.append(str).append("|");
                }
            }
            picList = sb_net.toString();
            file0 = sb_phone.toString();
            if (picList.length()>0)
                picList = sb_net.substring(0,sb_net.length()-1);
            if (file0.length()>0)
                file0= sb_phone.substring(0,sb_phone.length()-1);
            Logger.i("NET______"+picList.toString());
            Logger.i("TEL______"+file0.toString());
        }
        if (requestCode==AllData.SELECT_FWFW_REQUEST_CODE&&resultCode==AllData.SELECT_MULTISELECT_CODE){
            detail = data.getStringExtra("fwfw");
            tv_fwfw.setText(detail);
        }
        if (requestCode==AllData.SELECT_JYXM_REQUEST_CODE&&resultCode==AllData.SELECT_MULTISELECT_CODE){
            manageScope = data.getStringExtra("jyxm");
            tv_jyfw.setText(manageScope);
        }
    }

    @Override
    public void showDataPacker() {

    }

    @Override
    public void toSelectArea() {
        mpd = new MyProgressDialog(oThis,true,getResources().getString(R.string.jiazaizhong));
        if (picker_address==null){
            picker_address = new AddressPicker(oThis,PickerHelper.getIntance(getApplicationContext()).getList_province());
            picker_address = PickerHelper.getIntance(getApplicationContext()).settingAddressPicker(picker_address);
            picker_address.setOnAddressPickListener(new AddressPicker.OnAddressPickListener() {
                @Override
                public void onAddressPicked(Province province1, City city1, County county1) {
                    String provinceName = province1.getName();
                    String cityName = "";
                    if (city1 != null) {
                        cityName = city1.getName();
                        //忽略直辖市的二级名称
                        if (cityName.equals("市辖区") || cityName.equals("市") || cityName.equals("县")) {
                            cityName = "";
                        }
                    }
                    String countyName = "";
                    if (county1 != null) {
                        countyName = county1.getName();
                    }
                    Logger.i("地区选择："+province1.getName()+"--"+city1.getName()+"--"+county1.getName());
                    Logger.i("地区选择："+province1.getAreaId()+"--"+city1.getAreaId()+"--"+county1.getAreaId());
                    province = province1.getName();
                    city = city1.getName();
                    area = county1.getName();
                    provinceCode = province1.getAreaId();
                    city = city1.getAreaId();
                    areaCode = county1.getAreaId();
                    tv_szdq.setText(province1.getName()+"  "+city1.getName()+"  "+county1.getName());
                }
            });
        }

        mpd.dismiss();
        picker_address.show();
    }

    @Override
    public void toSelectTime() {
        picker__time.setTitleText("开店时间");
        picker__time.show();
    }

    @Override
    public void initUI(final String id,final String picList ,final String name, final String csmc, final String province, final String city, final String area, final String provinceCode, String cityCode, String areaCode, final String address, final String sjjs, final String yyxm, final String fwfw, final String time, final String lxr, final String kfdh, ArrayList<String> list_businessPics) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv_businessId.setText(id);
                et_gsmc.setText(name);
                et_csmc.setText(csmc);
                tv_szdq.setText(province+" "+city+" "+area);
                et_xxdz.setText(address);
                tv_sjjj.setText(sjjs);
                tv_jyfw.setText(yyxm);
                tv_fwfw.setText(fwfw);
                tv_yysj.setText(time);
                et_lxr.setText(lxr);
                et_kfdh.setText(kfdh);
            }
        });
        this.list_businessPics = list_businessPics;
        this.picList = picList;
        this.linkMan = lxr;
        this.manageScope = yyxm;
        this.introduce = sjjs;
        this.linkAddress = address;
        this.city = city;
        this.province = province;
        this.area = area;
        this.company = name;
        this.detail = fwfw;
        this.hours = time;
        this.name = csmc;
        this.linkPhone = kfdh;
        this.provinceCode = provinceCode;
        this.cityCode = cityCode;
        this.areaCode = areaCode;


    }
}
