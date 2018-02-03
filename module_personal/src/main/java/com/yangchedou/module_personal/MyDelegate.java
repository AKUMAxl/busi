package com.yangchedou.module_personal;


import android.util.Log;

import com.orhanobut.logger.Logger;
import com.yangchedou.lib_common.Base.ApplicationDelegate;
import com.yangchedou.lib_common.Base.ViewManager;

public class MyDelegate implements ApplicationDelegate {

    @Override
    public void onCreate() {
        ViewManager.getInstance().addFragment(2, new PersonalFragment());
    }

    @Override
    public void onTerminate() {

    }

    @Override
    public void onLowMemory() {

    }

    @Override
    public void onTrimMemory(int level) {

    }
}
