package com.yangchedou.module_login;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;


/**
 * Created by ADMIN on 2017/11/10.
 */

public class LoginPersenterImpl implements LoginPersenter,LoginModel.onLoginFinishedListener{

    private Activity oThis;
    private LoginView loginView;
    private LoginModel loginModel;

    public LoginPersenterImpl(Activity oThis,LoginView loginView){
        this.oThis = oThis;
        this.loginView = loginView;
        this.loginModel = new LoginModelImpl();
    }

    @Override
    public void validataLoginInfo(Activity oThis,String username, String password) {
        if (loginView!=null){
            loginView.DialogShow(null);
        }
        loginModel.login(oThis,username,password,this);
    }

    @Override
    public void setUserNamePwd(Context context) {
        String username = loginModel.getUserName(context);
        String password = loginModel.getUserPassword(context);
        if (!TextUtils.isEmpty(username)){
            loginView.setUserName(username);
        }
        if (!TextUtils.isEmpty(password)){
            loginView.setPassword(password);
        }
    }

    @Override
    public void onDestory() {
        loginView = null;
    }

    @Override
    public void onUserNameError() {
        if (loginView!=null){
            loginView.DialogDismiss();
            loginView.ToastShow(oThis.getResources().getString(R.string.yonghumingbunengweikong));
        }
    }

    @Override
    public void onPassWordError() {
        if (loginView!=null){
            loginView.DialogDismiss();
            loginView.ToastShow(oThis.getResources().getString(R.string.mimabunengweikong));
        }
    }

    @Override
    public void onRequestFailure(String info) {
        if (loginView!=null){
            loginView.DialogDismiss();
            loginView.ToastShow(info);
        }
    }

    @Override
    public void onResponseError(String info) {
        if (loginView!=null){
            loginView.DialogDismiss();
            loginView.ToastShow(info);
        }
    }

    @Override
    public void onSuccess() {
        //SharedPreferencesUtil.getIntance().SaveData("user_id",);
        if (loginView!=null){
            loginView.DialogDismiss();
            loginView.ToNextActivity();
        }
    }
}
