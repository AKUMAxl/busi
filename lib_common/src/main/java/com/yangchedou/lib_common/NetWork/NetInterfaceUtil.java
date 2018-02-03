package com.yangchedou.lib_common.NetWork;

import android.content.Context;
import android.drm.DrmStore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.orhanobut.logger.Logger;
import com.yangchedou.lib_common.AllData;
import com.yangchedou.lib_common.Area;
import com.yangchedou.lib_common.MobileInfo;
import com.yangchedou.lib_common.utils.SharedPreferencesUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.reactivex.Scheduler;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import retrofit2.adapter.rxjava2.Result;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;


/**
 * Created by 27740 on 2017/9/29.
 */

public class NetInterfaceUtil implements NetWorkInterfaceUtil{

    public static volatile NetInterfaceUtil intance;

    public static NetInterfaceUtil getIntance(){
        if (intance==null){
            synchronized (NetInterfaceUtil.class){
                if (intance==null){
                    intance = new NetInterfaceUtil();
                }
            }
        }
        return intance;
    }

    //商户端登录
    @Override
    public void Login(Context context, String interfaceName, String userName, String password, okHttpResponse okHttpResponse){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("loginId",userName);
            jsonObject.put("password",password);
            jsonObject.put("channelid","");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtil.getIntance().getOKHttpResultByPostJson(context,"LG001",jsonObject,okHttpResponse);
    }

    //客户端登录
    @Override
    public void CUS_Login(Context context, String interfaceName, okHttpResponse okHttpResponse){

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("phone","111");
            jsonObject.put("password","111111");
            jsonObject.put("city","沈阳");
            jsonObject.put("channelId","0");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtil.getIntance().getOKHttpResultByPostJson(context,interfaceName,jsonObject,okHttpResponse);
    }

    //商户端版本检测
    @Override
    public void getVersion(Context context,okHttpResponse okHttpResponse){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("appCode", "business");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtil.getIntance().getOKHttpResultByPostJson(context,"AP004",jsonObject,okHttpResponse);
    }

    //工作台页信息
    @Override
    public void getWorkInfo(Context context,okHttpResponse okHttpResponse){
        JSONObject jsonObject = new JSONObject();
        String user_id = SharedPreferencesUtil.getIntance(context).GetData(SharedPreferencesUtil.KEY.USER_ID);
        try {
            jsonObject.put("businessId",Long.parseLong(user_id));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtil.getIntance().getOKHttpResultByPostJson(context,"HP001",jsonObject,okHttpResponse);
    }


    //改变商家状态
    @Override
    public void changeShopState(Context context,Boolean onBusi,okHttpResponse okHttpResponse){
        JSONObject jsonObject = new JSONObject();
        String user_id = SharedPreferencesUtil.getIntance(context).GetData(SharedPreferencesUtil.KEY.USER_ID);
        try {
            jsonObject.put("businessId",Long.parseLong(user_id));
            jsonObject.put("locked",onBusi ? 0 : 1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtil.getIntance().getOKHttpResultByPostJson(context,"HP002",jsonObject,okHttpResponse);
    }

    //获取保养订单列表
    @Override
    public void getMaintianList(Context context, String state,int start,int count, okHttpResponse okHttpResponse) {
        JSONObject jsonObject = new JSONObject();
        String user_id = SharedPreferencesUtil.getIntance(context).GetData(SharedPreferencesUtil.KEY.USER_ID);
        try {
            jsonObject.put("businessId",Long.parseLong(user_id));
            jsonObject.put("queryStart",start);
            jsonObject.put("queryNumber",count);
            jsonObject.put("state",Integer.valueOf(state));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtil.getIntance().getOKHttpResultByPostJson(context,"HP007",jsonObject,okHttpResponse);
    }

    //获取用户基本信息
    @Override
    public void getBaseinf(Context context,okHttpResponse okHttpResponse) {
        JSONObject jsonObject = new JSONObject();
        String user_id = SharedPreferencesUtil.getIntance(context).GetData(SharedPreferencesUtil.KEY.USER_ID);
        try {
            jsonObject.put("businessId",Long.parseLong(user_id));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtil.getIntance().getOKHttpResultByPostJson(context,"SE001",jsonObject,okHttpResponse);
    }

    //获取服务范围列表
    @Override
    public void getFwfwList(Context context, okHttpResponse okHttpResponse) {
        JSONObject jsonObject = new JSONObject();
        String user_id = SharedPreferencesUtil.getIntance(context).GetData(SharedPreferencesUtil.KEY.USER_ID);
        try {
            jsonObject.put("businessId",Long.parseLong(user_id));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtil.getIntance().getOKHttpResultByPostJson(context,"SE002",jsonObject,okHttpResponse);
    }


    //获取经营项目列表
    @Override
    public void getJyxmList(Context context, okHttpResponse okHttpResponse) {
        JSONObject jsonObject = new JSONObject();
        String user_id = SharedPreferencesUtil.getIntance(context).GetData(SharedPreferencesUtil.KEY.USER_ID);
        try {
            jsonObject.put("businessId",Long.parseLong(user_id));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtil.getIntance().getOKHttpResultByPostJson(context,"SE003",jsonObject,okHttpResponse);
    }

    @Override
    public void updateBaseInfo(Context context,String picList,String linkMan,String manageScope,String introduce,String linkAddress,String city, String province,String file0,
                               String company,String cityCode,String detail,String areaCode,String area,String provinceCode,String hours, String name,String linkPhone, okHttpResponse okHttpResponse) {
        JSONObject jsonObject = new JSONObject();
        String user_id = SharedPreferencesUtil.getIntance(context).GetData(SharedPreferencesUtil.KEY.USER_ID);

        try {
            jsonObject.put("picList",picList);
            jsonObject.put("linkMan",linkMan);
            jsonObject.put("manageScope",manageScope);
            jsonObject.put("introduce","456");
            jsonObject.put("linkAddress",linkAddress);
            jsonObject.put("city",city);
            jsonObject.put("businessId",Long.parseLong(user_id));
            jsonObject.put("province",province);
            jsonObject.put("file0",file0);
            jsonObject.put("company",company);
            jsonObject.put("cityCode",cityCode);
            jsonObject.put("detail",detail);
            jsonObject.put("areaCode",areaCode);
            jsonObject.put("area",area);
            jsonObject.put("provinceCode",provinceCode);
            jsonObject.put("hours",hours);
            jsonObject.put("name",name);
            jsonObject.put("linkPhone",linkPhone);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Logger.i("更新内容："+jsonObject.toString());
        OkHttpUtil.getIntance().getOKHttpResultByPostJsonWithourHeader(context,jsonObject,introduce,okHttpResponse);
    }

}
