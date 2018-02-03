package com.yangchedou.lib_common.NetWork;

/**
 * Created by 27740 on 2017/9/26.
 */

public interface okHttpResponse {

    /**
     * 请求失败  进入okhttp的onFailure回调中
     */
    void requsetFailure(String failureStr);

    /**
     * 请求成功 进入okhttp的onSuccess中    返回的数据正确  code==0
     * @param successStr    返回的json数据
     */
    void responseSuccess(String successStr);

    /**
     * 请求成功 进入okhttp的onSuccess中     返回的数据不正确    code!=0
     * @param failureInfo
     */
    void dealFailureResult(String failureInfo);

}
