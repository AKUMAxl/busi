package com.yangchedou.lib_common.NetState;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by 27740 on 2017/9/29.
 */

public class NetManagerUtil{

    public static final int NET_NONE = 0;

    public static final int NET_WIFI = 1;

    public static final int NET_MOBILE = 2;

    public static NetManagerUtil intance;

    public static NetManagerUtil getIntance(){
        if (intance==null){
            synchronized (NetManagerUtil.class){
                intance = new NetManagerUtil();
            }
        }
        return intance;
    }

    public static int getNEtState(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo!=null&&networkInfo.isConnected()){
            if (networkInfo.getType()==(ConnectivityManager.TYPE_WIFI)){
                return NET_WIFI;
            }else if(networkInfo.getType()==(ConnectivityManager.TYPE_MOBILE)){
                return NET_MOBILE;
            }else{
                return NET_NONE;
            }
        }
        return NET_NONE;
    }


}
