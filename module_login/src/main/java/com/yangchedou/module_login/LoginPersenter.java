package com.yangchedou.module_login;

import android.app.Activity;
import android.content.Context;

/**
 * Created by ADMIN on 2017/11/10.
 */

public interface LoginPersenter {

    void validataLoginInfo(Activity oThis, String username, String password);

    void setUserNamePwd(Context context);

    void onDestory();

}
