package com.yangchedou.module_order.MaintianOrder;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;
import com.yangchedou.lib_common.NetWork.NetInterfaceUtil;
import com.yangchedou.lib_common.NetWork.okHttpResponse;
import com.yangchedou.module_order.MaintianOrder.Bean.MianTainOrderBean;
import com.yangchedou.module_order.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ADMIN on 2017/11/18.
 */

public class OrderModelImpl implements OrderMedel {

    private Context context;
    private OrderMedel.onGetMainTainListener onGetMainTainListener;

    public OrderModelImpl(Context context,OrderMedel.onGetMainTainListener onGetMainTainListener){
        this.context = context;
        this.onGetMainTainListener = onGetMainTainListener;
    }

    @Override
    public void getMainTainOrderList(String state,int startIndex,int queryCount) {
        NetInterfaceUtil.getIntance().getMaintianList(context, state, startIndex,queryCount,new okHttpResponse() {
            @Override
            public void requsetFailure(String failureStr) {
                onGetMainTainListener.onFalure(failureStr);
            }

            @Override
            public void responseSuccess(String successStr) {
                try {
                    List<MianTainOrderBean> list = new ArrayList<>();
                    JSONObject object = new JSONObject(successStr);
                    String count  = String.valueOf(object.getInt("orderCount"));
                    String allprice = String.valueOf(object.getDouble("allprice"));
                    Logger.i(count+"----"+allprice);
                    if (Integer.valueOf(count)!=0){
                        String resultList = object.getJSONArray("orderList").toString();
                        list = new Gson().fromJson(resultList,new TypeToken<List<MianTainOrderBean>>(){}.getType());
                    }
                    onGetMainTainListener.onSuccess(list,count,allprice);
                } catch (JSONException e) {
                    e.printStackTrace();
                    onGetMainTainListener.onFalure(context.getResources().getString(R.string.shibai));
                }
            }

            @Override
            public void dealFailureResult(String failureInfo) {
                onGetMainTainListener.onFalure(failureInfo);
            }
        });
    }
}
