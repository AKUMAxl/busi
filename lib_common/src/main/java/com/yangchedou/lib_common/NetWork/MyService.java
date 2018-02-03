package com.yangchedou.lib_common.NetWork;

import com.yangchedou.lib_common.Area;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ADMIN on 2017/12/23.
 */

public interface MyService {
    @GET("user/login" )
    Observable<Area> login(
            @Query("username") String username,
            @Query("password") String password
    );
}
