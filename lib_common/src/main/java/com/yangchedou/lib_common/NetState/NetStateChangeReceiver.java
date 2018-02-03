package com.yangchedou.lib_common.NetState;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import com.yangchedou.lib_common.Base.BaseActivity;

/**
 * Created by 27740 on 2017/9/29.
 */

public class NetStateChangeReceiver extends BroadcastReceiver {

    public netStateChangeListener netStateChangeListener = BaseActivity.netStateChangeListener;

    public interface netStateChangeListener{
        void netStateChange(int state);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)){
            int state = NetManagerUtil.getNEtState(context);
            netStateChangeListener.netStateChange(state);
        }
    }
}
