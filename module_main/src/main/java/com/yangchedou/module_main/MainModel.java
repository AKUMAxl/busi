package com.yangchedou.module_main;

import android.content.Context;

/**
 * Created by ADMIN on 2017/11/17.
 */

public interface MainModel {

    interface onCheckVersionResult{

        void onError(String errorInfo);

       void update(String downLoadURI);

       void lastVersion();

    }

    void getVersion(Context context,onCheckVersionResult onCheckVersionResult);

}
