package com.yangchedou.lib_common.remote;

import android.app.Application;
import android.os.Looper;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by wanglj on 16/7/4.
 */

public class ApiManager {

    private final ApiService apiService;

    private final Application application;


    public ApiManager(ApiService apiService, Application application) {
        this.apiService = apiService;
        this.application = application;
    }

    public void login(String username, String password, SimpleCallback<User> simpleCallback){
        /*apiService.login(username,password)
                .flatMap(new BaseResponseFunc<User>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ExceptionSubscriber<User>(simpleCallback,application));*/

        apiService.getUser(1)
                .concatMap(new BaseResponseFunc<User>())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ExceptionSubscriber<User>(simpleCallback,application));

    }

}
