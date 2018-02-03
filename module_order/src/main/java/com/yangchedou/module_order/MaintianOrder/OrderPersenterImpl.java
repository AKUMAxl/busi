package com.yangchedou.module_order.MaintianOrder;

import android.content.Context;

import com.yangchedou.module_order.MaintianOrder.Bean.MianTainOrderBean;

import java.util.List;

/**
 * Created by ADMIN on 2017/11/18.
 */

public class OrderPersenterImpl implements OrderPersenter,OrderMedel.onGetMainTainListener {

    private Context context;
    private OrderView orderView;
    private OrderMedel orderMedel;

    public OrderPersenterImpl(Context context,OrderView orderView){
        this.context = context;
        this.orderView = orderView;
        this.orderMedel = new OrderModelImpl(context,this);
    }

    @Override
    public void getMinatainOrderList(String state,int startIndex,int queryCount) {
        orderMedel.getMainTainOrderList(state,startIndex,queryCount);
    }

    @Override
    public void onDestory() {
        if (orderView!=null){
            orderView = null;
        }
    }

    @Override
    public void onSuccess(List<MianTainOrderBean> list_miantainOrderBean,String count,String price) {
        if (orderView!=null){
            orderView.loadMaintainOrderList(list_miantainOrderBean,count,price);
            orderView.dismissHeaderFooter();
        }
    }

    @Override
    public void onFalure(String errorInfo) {
        if (orderView!=null){
            orderView.showToast(errorInfo);
        }
    }
}
