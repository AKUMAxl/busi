package com.yangchedou.module_work;


import com.yangchedou.lib_common.Base.ApplicationDelegate;
import com.yangchedou.lib_common.Base.ViewManager;

public class MyDelegate implements ApplicationDelegate {

    @Override
    public void onCreate() {
        ViewManager.getInstance().addFragment(0, new WorkFragment());
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
