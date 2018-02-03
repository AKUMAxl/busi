package com.yangchedou.lib_common.Base.MultiSelect;

import java.util.List;

/**
 * Created by ADMIN on 2017/12/5.
 */

public interface MultiSelectModel {

    public interface OnResultResponse{

        void onSuccess(List<StringAndBoolean> list_str);

        void onFailure(String errorinfo);

    }

    void getFwfwList(OnResultResponse onResultResponse);

    void getJyxmList(OnResultResponse onResultResponse);
}
