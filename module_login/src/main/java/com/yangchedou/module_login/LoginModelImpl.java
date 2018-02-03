package com.yangchedou.module_login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;
import com.yangchedou.lib_common.Base.ChangeActivity;
import com.yangchedou.lib_common.NetWork.NetInterfaceUtil;
import com.yangchedou.lib_common.NetWork.okHttpResponse;
import com.yangchedou.lib_common.utils.SharedPreferencesUtil;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ADMIN on 2017/11/10.
 */

public class LoginModelImpl implements LoginModel {
    @Override
    public void login(final Activity oThis, final String username, final String password, final onLoginFinishedListener onLoginFinishedListener) {

        if (TextUtils.isEmpty(username)){
            onLoginFinishedListener.onUserNameError();
            return;
        }

        if (TextUtils.isEmpty(password)){
            onLoginFinishedListener.onPassWordError();
            return;
        }

        NetInterfaceUtil.getIntance().Login(oThis, "LG001", username, password, new okHttpResponse() {
            @Override
            public void requsetFailure(String failureStr) {
                onLoginFinishedListener.onRequestFailure(failureStr);
            }

            @Override
            public void responseSuccess(String successStr) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    JSONObject object = new JSONObject(successStr);
                    Long id = object.getLong("id");
                    Integer state = object.getInt("state");
                    String path = object.getString("path");
                    SharedPreferencesUtil.getIntance(oThis).SaveData(SharedPreferencesUtil.KEY.USER_ID,String.valueOf(id));
                    SharedPreferencesUtil.getIntance(oThis).SaveData(SharedPreferencesUtil.KEY.BUSINESS_TYPE,String.valueOf(state));
                    SharedPreferencesUtil.getIntance(oThis).SaveData(SharedPreferencesUtil.KEY.PATH,String.valueOf(path));
                    SharedPreferencesUtil.getIntance(oThis).SaveData(SharedPreferencesUtil.KEY.USER_NAME,username);
                    SharedPreferencesUtil.getIntance(oThis).SaveData(SharedPreferencesUtil.KEY.USER_PWD,password);
                    SharedPreferencesUtil.getIntance(oThis).SaveData(SharedPreferencesUtil.KEY.HAS_LOGIN,String.valueOf(true));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                onLoginFinishedListener.onSuccess();
            }

            @Override
            public void dealFailureResult(String failureInfo) {
                onLoginFinishedListener.onResponseError(failureInfo);
            }
        });
    }

    @Override
    public String getUserName(Context context) {
        return SharedPreferencesUtil.getIntance(context).GetData(SharedPreferencesUtil.KEY.USER_NAME);
    }

    @Override
    public String getUserPassword(Context context) {
        return SharedPreferencesUtil.getIntance(context).GetData(SharedPreferencesUtil.KEY.USER_PWD);
    }


}
