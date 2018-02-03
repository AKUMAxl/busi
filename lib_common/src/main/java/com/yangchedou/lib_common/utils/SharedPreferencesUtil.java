package com.yangchedou.lib_common.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.Menu;

/**
 * Created by ADMIN on 2017/11/16.
 */

public class SharedPreferencesUtil {

    public static SharedPreferencesUtil intance;
    private Context context;

    public enum KEY{
        TOKEN,
        USER_ID,//用户ID
        BUSINESS_TYPE,//商户类型
        USER_NAME,//用户名
        USER_PWD,//用户密码
        HAS_LOGIN,//是否已经登录
        PATH,//路径
    }

    public SharedPreferencesUtil(Context context){
        this.context = context.getApplicationContext();
    }

    public static SharedPreferencesUtil getIntance(Context context){
        if (intance==null){
            synchronized (SharedPreferencesUtil.class){
                intance = new SharedPreferencesUtil(context);
            }
        }
        return intance;
    }

    public void SaveData(KEY key,String value){
        SharedPreferences sp = context.getSharedPreferences("ycd_sharedPreferences",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(String.valueOf(key),value);
        editor.commit();
    }

    public String GetData(KEY key){
        SharedPreferences sp = context.getSharedPreferences("ycd_sharedPreferences",Context.MODE_PRIVATE);
        return sp.getString(String.valueOf(key),"");
    }
}
