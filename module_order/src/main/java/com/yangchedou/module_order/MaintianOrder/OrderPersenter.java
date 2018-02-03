package com.yangchedou.module_order.MaintianOrder;

/**
 * Created by ADMIN on 2017/11/18.
 */

public interface OrderPersenter {

    void getMinatainOrderList(String state,int startIndex,int queryCount);

    void onDestory();

}
