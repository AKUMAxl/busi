package com.yangchedou.lib_common.NetWork;

import android.util.Log;


/**
 * Created by ADMIN on 2017/11/4.
 */

public class okHttpResponseImpl implements okHttpResponse {

    @Override
    public void requsetFailure(String failureStr) {

    }

    @Override
    public void responseSuccess(String successStr) {

    }

    @Override
    public void dealFailureResult(String failureInfo) {
        Log.i("xl","IMPL"+failureInfo);
    }
}
