package com.yangchedou.module_order.MaintianOrder;

import com.yangchedou.module_order.MaintianOrder.Bean.MianTainOrderBean;

import java.util.List;

/**
 * Created by ADMIN on 2017/11/18.
 */

public interface OrderView {

    void loadMaintainOrderList(List<MianTainOrderBean> list_miantainOrderBean,String count,String price);

    void showToast(String text);

    void dismissHeaderFooter();
}
