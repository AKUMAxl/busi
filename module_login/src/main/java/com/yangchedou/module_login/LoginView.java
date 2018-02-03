package com.yangchedou.module_login;

/**
 * Created by ADMIN on 2017/11/10.
 */

public interface LoginView {

    void DialogShow(String text);

    void DialogDismiss();

    void setUserName(String userName);

    void setPassword(String Password);

    void ToastShow(String text);

    void ToNextActivity();

}
