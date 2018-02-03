package com.yangchedou.lib_common.remote;

import com.yangchedou.lib_common.NetWork.OkHttpUtil;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ADMIN on 2018/1/5.
 */

public class RetrofitClient {

    public static volatile RetrofitClient intance;

    private ApiService service;
    private Retrofit retrofit;

    public static RetrofitClient getIntance() {
        synchronized (RetrofitClient.class) {
            if (intance == null) {
                synchronized (RetrofitClient.class) {
                    intance = new RetrofitClient();
                }
            }
        }
        return intance;
    }

    public ApiService getServiec(){

        if (service==null){
            retrofit = new Retrofit.Builder()
                    .client(OkHttpUtil.getOKHttpClient())
                    .baseUrl("http://192.168.1.102:8080/user/")
                    .addConverterFactory(GsonConverterFactory.create())
                    //.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    // 针对rxjava2.x
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            service = retrofit.create(ApiService.class);
        }
        return service;
    }

}
