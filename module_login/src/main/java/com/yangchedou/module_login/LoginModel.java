package com.yangchedou.module_login;

import android.app.Activity;
import android.content.Context;

/**
 * Created by ADMIN on 2017/11/10.
 */

public interface LoginModel {

    interface onLoginFinishedListener{

        void onUserNameError();

        void onPassWordError();

        void onRequestFailure(String info);

        void onResponseError(String info);

        void onSuccess();

    }

    void login(Activity oThis, String username, String password, onLoginFinishedListener onLoginFinishedListener);

    String getUserName(Context context);

    String getUserPassword(Context context);
}
