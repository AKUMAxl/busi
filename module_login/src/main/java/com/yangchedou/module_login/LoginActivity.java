package com.yangchedou.module_login;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.orhanobut.logger.Logger;
import com.yangchedou.lib_common.Base.BaseActivity;
import com.yangchedou.lib_common.Base.ChangeActivity;
import com.yangchedou.lib_common.Weight.ClearnEditText;
import com.yangchedou.lib_common.Weight.MyProgressDialog;

/**
 * Created by ADMIN on 2017/11/10.
 */

@Route(path = "/module_login/login")
public class LoginActivity extends BaseActivity implements LoginView,View.OnClickListener{

    private MyProgressDialog mpd;
    private LoginPersenter loginPersenter;
    private TextView tv_login;
    private ClearnEditText cet_username,cet_password;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginPersenter.onDestory();
    }

    @Override
    public void loadView() {
        setContentView(R.layout.activity_login);
    }

    @Override
    public void initData() {

        loginPersenter = new LoginPersenterImpl(oThis,this);

        tv_login = findViewById(R.id.tv_login);
        cet_username = findViewById(R.id.username);
        cet_password = findViewById(R.id.password);

        tv_login.setOnClickListener(this);

        mpd = new MyProgressDialog(oThis,true,getResources().getString(R.string.jiazaizhong));

        loginPersenter.setUserNamePwd(oThis);
    }


    @Override
    public void DialogShow(String text) {
        mpd.setText(text);
        mpd.show();
    }

    @Override
    public void DialogDismiss() {
        mpd.dismiss();
    }

    @Override
    public void setUserName(String userName) {
        cet_username.setText(userName);
    }

    @Override
    public void setPassword(String Password) {
        cet_password.setText(Password);
    }

    @Override
    public void ToastShow(final String text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(oThis,text,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void ToNextActivity() {
        ChangeActivity.getIntance().ToNextActivity(oThis,"/module_main/main",null,true);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id==R.id.tv_login){
            //ToNextActivity();
            //loginPersenter.validataLoginInfo(oThis,"111","000000");
            loginPersenter.validataLoginInfo(oThis,cet_username.getText(),cet_password.getText());
            return;
        }
    }
}
