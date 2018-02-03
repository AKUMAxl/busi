package com.yangchedou.lib_common;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;

import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cn.qqtheme.framework.entity.City;
import cn.qqtheme.framework.entity.County;
import cn.qqtheme.framework.entity.Province;
import cn.qqtheme.framework.picker.AddressPicker;
import cn.qqtheme.framework.picker.DoublePicker;
import cn.qqtheme.framework.picker.LinkagePicker;
import cn.qqtheme.framework.picker.TimePicker;
import cn.qqtheme.framework.util.ConvertUtils;


/**
 * Created by ADMIN on 2017/12/4.
 */

public class PickerHelper {

    private ArrayList<Province> list_province;

    private Context context;

    public static PickerHelper intance;

    public static PickerHelper getIntance(Context context){
        if (intance==null){
            synchronized (PickerHelper.class){
                if (intance==null){
                    intance = new PickerHelper(context);
                }
            }
        }
        return intance;
    }

    public PickerHelper(Context context){
        this.context = context;
    }

    public ArrayList<Province> getList_province(){
        String data = null;
        try {
            data = ConvertUtils.toString(context.getAssets().open("city.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (list_province != null && list_province.size() > 0) {
            return list_province;
        }
        list_province = new ArrayList<>();
        String[] fullCodeAndNames = data.split(";");
        for (String fullCodeAndName : fullCodeAndNames) {
            String[] codeAndName = fullCodeAndName.split(",");
            if (codeAndName.length != 2) {
                continue;
            }
            String code = codeAndName[0];
            String name = codeAndName[1];
            if (code.substring(2, 6).equals("0000")) {
                //省份
                Province province= new Province();
                province.setAreaId(code);
                province.setAreaName(name);
                province.setCities(new ArrayList<City>());
                list_province.add(province);
            } else if (code.substring(4, 6).equals("00")) {
                //地市
                Province province = findProvinceByCode(code.substring(0, 2));
                if (province != null) {
                    City city = new City();
                    city.setAreaId(code);
                    city.setAreaName(name);
                    city.setCounties(new ArrayList<County>());
                    province.getCities().add(city);
                }
            } else {
                //区县
                City city = findCityByCode(code.substring(0, 2), code.substring(2, 4));
                if (city != null) {
                    County county = new County();
                    county.setAreaId(code);
                    county.setAreaName(name);
                    city.getCounties().add(county);
                }
            }
        }
        return list_province;
    }

    private Province findProvinceByCode(String provinceCode) {
        for (Province province : list_province) {
            if (province.getAreaId().substring(0, 2).equals(provinceCode)) {
                return province;
            }
        }
        return null;
    }

    private City findCityByCode(String provinceCode, String cityCode) {
        for (Province province : list_province) {
            List<City> cities = province.getCities();
            for (City city : cities) {
                if (province.getAreaId().substring(0, 2).equals(provinceCode) &&
                        city.getAreaId().substring(2, 4).equals(cityCode)) {
                    return city;
                }
            }
        }
        return null;
    }

    public AddressPicker settingAddressPicker(AddressPicker picker){

        picker.setHeight(MobileInfo.getScreenWH(context)[1]/4);
        picker.setTextSize(14);
        picker.setDividerColor(context.getResources().getColor(R.color.colorPrimary));
        picker.setTextColor(context.getResources().getColor(R.color.colorPrimary));
        picker.setCancelTextColor(context.getResources().getColor(R.color.colorPrimary));
        picker.setPressedTextColor(context.getResources().getColor(R.color.colorPrimary));
        picker.setSubmitTextColor(context.getResources().getColor(R.color.colorPrimary));
        picker.setTopLineColor(context.getResources().getColor(R.color.colorPrimary));
        picker.setCanceledOnTouchOutside(false);
        picker.setHideProvince(false);
        picker.setHideCounty(false);
        if (false) {
            picker.setColumnWeight(1 / 3.0f, 2 / 3.0f);//将屏幕分为3份，省级和地级的比例为1:2
        } else {
            picker.setColumnWeight(2 / 8.0f, 3 / 8.0f, 3 / 8.0f);//省级、地级和县级的比例为2:3:3
        }
        picker.setSelectedItem("辽宁", "沈阳", "和平区");
        return picker;
    }

    public TimePicker settingTimePicker(TimePicker picker){
        picker.setUseWeight(false);
        picker.setCycleDisable(false);
        picker.setCanceledOnTouchOutside(false);
        picker.setDividerColor(context.getResources().getColor(R.color.colorPrimary));
        picker.setTextColor(context.getResources().getColor(R.color.colorPrimary));
        picker.setCancelTextColor(context.getResources().getColor(R.color.colorPrimary));
        picker.setPressedTextColor(context.getResources().getColor(R.color.colorPrimary));
        picker.setSubmitTextColor(context.getResources().getColor(R.color.colorPrimary));
        picker.setRangeStart(0, 0);//00:00
        picker.setRangeEnd(23, 59);//23:59
        int currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int currentMinute = Calendar.getInstance().get(Calendar.MINUTE);
        picker.setSelectedItem(currentHour, currentMinute);
        picker.setTopLineVisible(false);
        picker.setHeight(MobileInfo.getScreenWH(context)[1]/4);
        picker.setTextSize(14);
        picker.setContentPadding(0,0);
        //picker.setTextPadding(ConvertUtils.toPx(this, 15));

        return picker;
    }

    public DoublePicker settingDoublePicker(DoublePicker picker){
        picker.setDividerColor(context.getResources().getColor(R.color.colorPrimary));
        picker.setTextColor(context.getResources().getColor(R.color.colorPrimary));
        picker.setCancelTextColor(context.getResources().getColor(R.color.colorPrimary));
        picker.setPressedTextColor(context.getResources().getColor(R.color.colorPrimary));
        picker.setSubmitTextColor(context.getResources().getColor(R.color.colorPrimary));
        picker.setDividerVisible(true);
        picker.setCycleDisable(false);
        picker.setSelectedIndex(0, 0);
        picker.setSecondLabel("-  ", null);
        picker.setTextSize(12);
        picker.setContentPadding(15, 10);

        return picker;
    }

    public SparseArray getTime_HMList(){
        SparseArray sparseArray = new SparseArray();
        List<String> list_hour = new ArrayList<>();
        List<String> list_minute = new ArrayList<>();
        for (int i=0;i<24;i++){
            if (i<10){
                list_hour.add("0"+i);
            }else {
                list_hour.add(i+"");
            }
        }
        for (int i=0;i<60;i++){
            if (i<10){
                list_minute.add("0"+i);
            }else {
                list_minute.add(i+"");
            }
        }
        sparseArray.put(0,list_hour);
        sparseArray.put(1,list_minute);
        return sparseArray;
    }

}
