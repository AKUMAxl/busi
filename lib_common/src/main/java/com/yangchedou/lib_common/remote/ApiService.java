package com.yangchedou.lib_common.remote;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.adapter.rxjava2.Result;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;


/**
 * Created by wanglj on 16/7/4.
 */

public interface ApiService {

    String SERVER_URL = "http://192.168.104.104:3000/";

    @GET("users")
    Observable<BaseResponse<User>> login(@Query("username") String username, @Query("password") String password);


    @GET("topics")
    Observable<BaseResponse<List<Topic>>> topics(@Query("job_code") String job_code, @Query("password") String password);
    @Headers(
            {"imei:sdfkj","name:xl"})
    @POST("getUserById")
    Observable<BaseResponse<User>> getUser(@Query("id") int id);
}
