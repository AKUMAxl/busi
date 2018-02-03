package com.yangchedou.lib_common.Base.MultiSelect;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yangchedou.lib_common.NetWork.NetInterfaceUtil;
import com.yangchedou.lib_common.NetWork.okHttpResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ADMIN on 2017/12/5.
 */

public class MultiSelectModelImpl implements MultiSelectModel {

    private Context context;

    public MultiSelectModelImpl(Context context){
        this.context = context;
    }


    @Override
    public void getFwfwList(final OnResultResponse onResultResponse) {
        NetInterfaceUtil.getIntance().getFwfwList(context, new okHttpResponse() {
            @Override
            public void requsetFailure(String failureStr) {
                onResultResponse.onFailure(failureStr);
            }

            @Override
            public void responseSuccess(String successStr) {
                List<StringAndBoolean> list_sab = new ArrayList<>();
                MultiSelectStringBean multiSelectStringBean = new Gson().fromJson(successStr,new TypeToken<MultiSelectStringBean>() {}.getType());
                for (MultiSelectStringBean.ListBean bean:multiSelectStringBean.getList()) {
                    StringAndBoolean sab = new StringAndBoolean();
                    sab.setName(bean.getName());
                    sab.setChecked(false);
                    list_sab.add(sab);
                }
                onResultResponse.onSuccess(list_sab);
            }

            @Override
            public void dealFailureResult(String failureInfo) {
                onResultResponse.onFailure(failureInfo);
            }
        });
    }

    @Override
    public void getJyxmList(final OnResultResponse onResultResponse) {
        NetInterfaceUtil.getIntance().getJyxmList(context, new okHttpResponse() {
            @Override
            public void requsetFailure(String failureStr) {
                onResultResponse.onFailure(failureStr);
            }

            @Override
            public void responseSuccess(String successStr) {
                List<StringAndBoolean> list_sab = new ArrayList<>();
                MultiSelectStringBean multiSelectStringBean = new Gson().fromJson(successStr,new TypeToken<MultiSelectStringBean>() {}.getType());
                for (MultiSelectStringBean.ListBean bean:multiSelectStringBean.getList()) {
                    StringAndBoolean sab = new StringAndBoolean();
                    sab.setName(bean.getName());
                    sab.setChecked(false);
                    list_sab.add(sab);
                }
                onResultResponse.onSuccess(list_sab);
            }

            @Override
            public void dealFailureResult(String failureInfo) {
                onResultResponse.onFailure(failureInfo);
            }
        });
    }
}
