package com.yangchedou.lib_common.Base.MultiSelect;

import android.content.Context;

import java.util.List;

/**
 * Created by ADMIN on 2017/12/5.
 */

public class MultiSelectPersenterImpl implements MultiSelectPersenter,MultiSelectModel.OnResultResponse {

    private Context context;
    private MultiSelectModel multiSelectModel;
    private MultSelectView multSelectView;

    public MultiSelectPersenterImpl(Context context,MultSelectView multSelectView){
        this.multiSelectModel = new MultiSelectModelImpl(context);
        this.multSelectView = multSelectView;
    }

    @Override
    public void getFwfwList() {
        multiSelectModel.getFwfwList(this);
    }

    @Override
    public void getJyxmList() {
        multiSelectModel.getJyxmList(this);
    }

    @Override
    public void onSuccess(List<StringAndBoolean> list_sab) {
        multSelectView.notifyRv(list_sab);
    }

    @Override
    public void onFailure(String errorinfo) {

    }
}
