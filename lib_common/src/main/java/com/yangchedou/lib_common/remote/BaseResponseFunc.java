package com.yangchedou.lib_common.remote;


import io.reactivex.Observable;
import io.reactivex.functions.Function;


/**
 * Created by wanglj on 16/7/4.
 */

public class BaseResponseFunc<T> implements Function<BaseResponse<T>, Observable<T>> {

    @Override
    public Observable<T> apply(BaseResponse<T> tBaseResponse) throws Exception {
        //遇到非200错误统一处理,将BaseResponse转换成您想要的对象
        if (tBaseResponse.getCode() != 200) {
            return Observable.error(new Throwable(tBaseResponse.getMessage()));
        }else{
            return Observable.just(tBaseResponse.getResult());
        }
    }
}
