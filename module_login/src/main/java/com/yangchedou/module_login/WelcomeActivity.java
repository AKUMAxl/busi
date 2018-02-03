package com.yangchedou.module_login;

import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;

import com.yangchedou.lib_common.Base.BaseActivity;
import com.yangchedou.lib_common.Base.ChangeActivity;
import com.yangchedou.lib_common.Weight.RoundProgressBar;
import com.yangchedou.lib_common.utils.SharedPreferencesUtil;

/**
 * Created by ADMIN on 2017/11/8.
 */

public class WelcomeActivity extends BaseActivity implements View.OnClickListener{

    private int count = 100;

    private RoundProgressBar rpb;

    private Boolean toMain = true;

    @Override
    public void loadView() {
        setContentView(R.layout.activity_welcome);
    }

    @Override
    public void initData() {
        rpb = findViewById(R.id.rpb);
        rpb.setOnClickListener(this);

        toMain = Boolean.valueOf(SharedPreferencesUtil.getIntance(oThis).GetData(SharedPreferencesUtil.KEY.HAS_LOGIN));
    }

    @Override
    public void firstRequestNet() {

    }

    @Override
    public void fresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                timer.start();
            }
        },500);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id==R.id.rpb){
            timer.cancel();
            ToNextActivity();
        }
    }

    private CountDownTimer timer = new CountDownTimer(3000,3000/360) {
        @Override
        public void onTick(long millisUntilFinished) {
            count--;
            synchronized (this){
                if (rpb!=null&&count>=0){
                    rpb.setProgress(count);
                }
            }
        }

        @Override
        public void onFinish() {
            ToNextActivity();
        }
    };

    private void ToNextActivity(){
        if (toMain)
            //ChangeActivity.getIntance().ToNextActivity(oThis,"/module_login/login",null,true);
            ChangeActivity.getIntance().ToNextActivity(oThis,"/module_main/main",null,true);
        else
            ChangeActivity.getIntance().ToNextActivity(oThis,"/module_login/login",null,true);

    }


}
