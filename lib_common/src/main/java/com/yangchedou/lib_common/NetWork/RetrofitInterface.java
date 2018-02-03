package com.yangchedou.lib_common.NetWork;

import com.yangchedou.lib_common.Area;
import com.yangchedou.lib_common.MaintianOrderDetialBean;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.adapter.rxjava2.Result;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


/**
 * Created by ADMIN on 2017/11/4.
 */

public interface RetrofitInterface {

/*    .header("Content-Type","application/json")
                .addHeader("app_code","business")
                .addHeader("service_code","LG001")
                .addHeader("screen_code","LG001")
                .addHeader("os",os)
                .addHeader("device",device)
                .addHeader("imei",imei)
                .addHeader("user_id","")
                .addHeader("city_code","")*/

    @Headers({
            "Content-Type:application/json"
            , "app_code:business"
            ,"service_code:LG001"
            ,"screen_code:LG001"})
    @POST("/{paramsStr}")
    Call<Result<Area>> Login(@Path("paramsStr") String paramsStr, @Header("os") String os, @Header("device") String device, @Header("imei") String imei);

    @GET("user/login" )
    Observable<Result<Area>> login(
            @Query("username") String username,
            @Query("password") String password
    );

    @Headers({
            "Content-Type:application/json"
            , "app_code:business"
            ,"service_code:LG001"
            ,"screen_code:LG001"
            ,"os:android"
            ,"device:YQ"
            ,"imei:123"})
    Observable<Result<MaintianOrderDetialBean>> getMaintianOrderDetialBean(@Query("customerId") Long customerId,@Query("orderId") Long orderId);

}
