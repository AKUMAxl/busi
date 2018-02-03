package com.yangchedou.lib_common.Base;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;

import com.alibaba.android.arouter.launcher.ARouter;
import com.yangchedou.lib_common.Base.MultiSelect.MultiSelectActivity;
import com.yangchedou.lib_common.R;

/**
 * Created by ADMIN on 2017/11/8.
 */

public class ChangeActivity {

    public static ChangeActivity intance;

    public static ChangeActivity getIntance(){
        if (intance==null){
            synchronized (ChangeActivity.class){
                intance = new ChangeActivity();
            }
        }
        return intance;
    }

    public void ToNextActivity(Activity oThis, String TargetPath, Bundle bundle, boolean toFinish){

        ActivityOptionsCompat compat = ActivityOptionsCompat.makeCustomAnimation(oThis, R.anim.fragment_slide_in_from_right,R.anim.fragment_slide_out_from_left);
        if (Build.VERSION.SDK_INT>15){
            ARouter.getInstance()
                    .build(TargetPath)
                    .with(bundle = (bundle==null?new Bundle():bundle))
                    .withOptionsCompat(compat)
                    .navigation();
        }else {
            ARouter.getInstance()
                    .build(TargetPath)
                    .with(bundle = (bundle==null?new Bundle():bundle))
                    .withTransition(R.anim.fragment_slide_in_from_right,R.anim.fragment_slide_out_from_left)
                    .navigation();
        }
        if (toFinish) {
            ViewManager.getInstance().finishActivity();
        }
    }

    public void ToNextActivityForResult(Activity oThis, String TargetPath, Bundle bundle,int requestCode){
        ActivityOptionsCompat compat = ActivityOptionsCompat.makeCustomAnimation(oThis, R.anim.fragment_slide_in_from_right,R.anim.fragment_slide_out_from_left);
        if (Build.VERSION.SDK_INT>15){
            ARouter.getInstance()
                    .build(TargetPath)
                    .with(bundle = (bundle==null?new Bundle():bundle))
                    .withOptionsCompat(compat)
                    .navigation(oThis,requestCode);
        }else {
            ARouter.getInstance()
                    .build(TargetPath)
                    .with(bundle = (bundle==null?new Bundle():bundle))
                    .withTransition(R.anim.fragment_slide_in_from_right,R.anim.fragment_slide_out_from_left)
                    .navigation(oThis,requestCode);
        }
    }

    public void ToNextActivityForResultByIntent(Activity oThis,Class targetClass,Bundle bundle ,int requestCode){
        Intent intent = new Intent();
        intent.setClass(oThis, targetClass);
        intent.putExtra("bundle",bundle);
        oThis.startActivityForResult(intent,requestCode);
        oThis.overridePendingTransition(R.anim.fragment_slide_in_from_right,R.anim.fragment_slide_out_from_left);
    }

}
