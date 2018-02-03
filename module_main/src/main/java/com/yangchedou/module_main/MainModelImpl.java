package com.yangchedou.module_main;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.orhanobut.logger.Logger;
import com.yangchedou.lib_common.NetWork.NetInterfaceUtil;
import com.yangchedou.lib_common.NetWork.okHttpResponse;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ADMIN on 2017/11/17.
 */

public class MainModelImpl implements MainModel {

    @Override
    public void getVersion(final Context context, final onCheckVersionResult onCheckVersionResult) {
        NetInterfaceUtil.getIntance().getVersion(context, new okHttpResponse() {
            @Override
            public void requsetFailure(String failureStr) {
                Logger.i("getVersion requsetFailure:"+failureStr);
                onCheckVersionResult.onError(failureStr);
            }

            @Override
            public void responseSuccess(String successStr) {
                Logger.i("getVersion responseSuccess:"+successStr);
                PackageManager manager = context.getPackageManager();
                PackageInfo info = null;
                try {
                    info = manager.getPackageInfo(context.getPackageName(),0);
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
                int current_versionCode = info.versionCode;
                int last_versionCode = 0;
                String downLoadURI = "";
                try {
                    JSONObject object = new JSONObject(successStr);
                    last_versionCode = object.getInt("number");
                    downLoadURI = object.getString("appPath");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Logger.i(" last and current "+ last_versionCode +"--"+current_versionCode);
                if (last_versionCode>current_versionCode){
                    onCheckVersionResult.update(downLoadURI);
                }else{
                    onCheckVersionResult.lastVersion();
                }
            }

            @Override
            public void dealFailureResult(String failureInfo) {
                Logger.i("getVersion dealFailureResult:"+failureInfo);
                onCheckVersionResult.onError(failureInfo);
            }
        });
    }
}
