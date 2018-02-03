package com.yangchedou.module_money;


import com.yangchedou.lib_common.Base.ApplicationDelegate;
import com.yangchedou.lib_common.Base.ViewManager;

public class MyDelegate implements ApplicationDelegate {

    @Override
    public void onCreate() {
        ViewManager.getInstance().addFragment(1, new MoneyFragment());
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
