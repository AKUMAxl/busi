package com.yangchedou.module_work;

import android.content.Context;

import com.orhanobut.logger.Logger;
import com.yangchedou.lib_common.NetWork.NetInterfaceUtil;
import com.yangchedou.lib_common.NetWork.okHttpResponse;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ADMIN on 2017/11/17.
 */

public class WorkModelImpl implements WorkModel {

    @Override
    public void getWorkInfo(Context context, final onInfoListener onInfoListener) {

        NetInterfaceUtil.getIntance().getWorkInfo(context, new okHttpResponse() {
            @Override
            public void requsetFailure(String failureStr) {
                onInfoListener.onError(failureStr);
            }

            @Override
            public void responseSuccess(String successStr) {
                int maintainCount = 0;
                int washCount = 0;
                Boolean onBusi = false;
                String photo_url = "";
                String busiName = "";
                String busiId = "";
                boolean isFours = false;
                int con = 0;
                String carBrand = "";
                try {
                    JSONObject object = new JSONObject(successStr);
                    maintainCount = object.getInt("untreatedOrderCounts");
                    washCount = object.getInt("untreatedWashCounts");
                    photo_url = object.getString("logo");
                    busiId = String.valueOf(object.getLong("id"));
                    busiName = object.getString("name");
                    if (object.getInt("locked")==0){
                        onBusi = true;
                    }
                    if (object.getInt("fours")==0){
                        isFours = true;
                    }
                    con = object.getInt("con");
                    carBrand = object.getString("carbrand");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                onInfoListener.setMessageInfo(isFours,con,carBrand);
                onInfoListener.setBaseInfo(photo_url,busiName,busiId);
                onInfoListener.Refresh(maintainCount,washCount);
                onInfoListener.shopState(onBusi);
            }

            @Override
            public void dealFailureResult(String failureInfo) {
                onInfoListener.onError(failureInfo);
            }
        });
    }

    @Override
    public void changeShopState(Context context, final Boolean onBusi, final onInfoListener onInfoListener) {
        NetInterfaceUtil.getIntance().changeShopState(context, onBusi, new okHttpResponse() {
            @Override
            public void requsetFailure(String failureStr) {
                onInfoListener.changeStateFalure(!onBusi);
            }

            @Override
            public void responseSuccess(String successStr) {
                Boolean success = false;
                try {
                    JSONObject object = new JSONObject(successStr);
                    success = object.getBoolean("success");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (success){
                    onInfoListener.changeSateSuccess();
                } else {
                    onInfoListener.changeStateFalure(!onBusi);
                }
            }

            @Override
            public void dealFailureResult(String failureInfo) {
                onInfoListener.changeStateFalure(!onBusi);
            }
        });
    }


}
