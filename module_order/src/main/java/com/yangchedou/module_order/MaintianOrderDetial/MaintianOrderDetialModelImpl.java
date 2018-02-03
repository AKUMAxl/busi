package com.yangchedou.module_order.MaintianOrderDetial;

import android.app.Application;

import com.yangchedou.lib_common.remote.ApiManager;
import com.yangchedou.lib_common.remote.ApiService;
import com.yangchedou.lib_common.remote.RetrofitClient;
import com.yangchedou.lib_common.remote.SimpleCallback;
import com.yangchedou.lib_common.remote.User;

/**
 * Created by ADMIN on 2018/1/5.
 */

public class MaintianOrderDetialModelImpl implements MaintianOrderDetialModel {


    private ApiManager apiManager;
    private ApiService apiService;

    public MaintianOrderDetialModelImpl(Application application){

        apiService = RetrofitClient.getIntance().getServiec();
        apiManager = new ApiManager(apiService,application);


    }

    @Override
    public void getData(Long cid, Long oid, final OnMaintianOrderDetialModelListener onMaintianOrderDetialModelListener) {
        apiManager.login("123", "123", new SimpleCallback<User>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onNext(User user) {
                onMaintianOrderDetialModelListener.onNext();
            }

            @Override
            public void onComplete() {

            }
        });

    }

}
