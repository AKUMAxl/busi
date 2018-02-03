package com.yangchedou.module_order.MaintianOrder;

import com.yangchedou.module_order.MaintianOrder.Bean.MianTainOrderBean;

import java.util.List;

/**
 * Created by ADMIN on 2017/11/18.
 */

public interface OrderMedel {

    interface onGetMainTainListener{

        void onSuccess(List<MianTainOrderBean> list_maintainOrderBean,String count,String price);

        void onFalure(String errorInfo);
    }

    void getMainTainOrderList(String state,int startIndex,int queryCount);

}
