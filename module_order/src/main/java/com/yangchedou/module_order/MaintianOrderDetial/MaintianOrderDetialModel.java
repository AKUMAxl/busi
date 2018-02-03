package com.yangchedou.module_order.MaintianOrderDetial;

/**
 * Created by ADMIN on 2018/1/5.
 */

public interface MaintianOrderDetialModel {

    interface OnMaintianOrderDetialModelListener{

        void onstart();

        void onNext();

        void onComplete();
    }

    void getData(Long cid,Long oid,OnMaintianOrderDetialModelListener listener);

}
