package com.yangchedou.module_work;

import android.content.Context;

/**
 * Created by ADMIN on 2017/11/17.
 */

public interface WorkModel {

    interface onInfoListener{

        void shopState(boolean onBusi);

        void changeSateSuccess();

        void changeStateFalure(boolean currentState);

        void Refresh(int maintainCount,int washCount);

        void setBaseInfo(String photo_url,String busiName,String busiId);

        void setMessageInfo(boolean isFours,int con,String carBrand);

        void onError(String errorinfo);

    }

    void getWorkInfo(Context context,onInfoListener onInfoListener);


    void changeShopState(Context context,Boolean onBusi,onInfoListener onInfoListener);

}
