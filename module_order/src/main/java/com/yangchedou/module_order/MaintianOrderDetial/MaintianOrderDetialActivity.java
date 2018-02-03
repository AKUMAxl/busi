package com.yangchedou.module_order.MaintianOrderDetial;

import android.os.Bundle;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yangchedou.lib_common.Area;
import com.yangchedou.lib_common.Base.BaseActivity;
import com.yangchedou.lib_common.NetWork.NetInterfaceUtil;
import com.yangchedou.lib_common.NetWork.OkHttpUtil;
import com.yangchedou.lib_common.utils.ToastUtils;
import com.yangchedou.lib_common.utils.XlThreadPoolUtil;
import com.yangchedou.module_order.R;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava2.Result;

/**
 * Created by ADMIN on 2017/12/12.
 */
@Route(path = "/module_order/maintianOrderDetial")
public class MaintianOrderDetialActivity extends BaseActivity implements MaintianOrderDetialView{


    private MaintianOrderDetialPersenter maintianOrderDetialPersenter;

    @Override
    public void loadView() {
        setContentView(R.layout.activity_maintiandetial);
    }

    @Override
    public void initData() {
        RefreshLayout maintiandetialSfl = (RefreshLayout) findViewById(R.id.maintiandetial_sfl);
        maintiandetialSfl.setPrimaryColorsId(R.color.bottom_icon, android.R.color.white);
        maintiandetialSfl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000, false);//传入false表示刷新失败
            }
        });
        maintiandetialSfl.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadmore(2000, false);//传入false表示加载失败
            }
        });

        maintianOrderDetialPersenter = new MaintianOrderDetialPersenterImpl(this,getApplication());
        maintianOrderDetialPersenter.getData();

        Thread thread = new Thread(new MyThread());
        //thread.start();

    }

    @Override
    public void showData(String info) {
        ToastUtils.showShortToast("isOk "+info);
    }

    class MyThread implements Runnable{

        @Override
        public void run() {
            try {
                int coreCount = Runtime.getRuntime().availableProcessors();
                //XlThreadPoolUtil.getIntance().getScheduledThreadPool(coreCount).execute();
                OkHttpClient mOkHttpClient=new OkHttpClient();
                FormBody formBody = new FormBody.Builder().add("id","1").build();
                Request.Builder requestBuilder = new Request.Builder().url("http://192.168.1.102:8080/user/getUserById");
                //可以省略，默认是GET请求
                requestBuilder.method("GET",formBody);
                Request request = requestBuilder.build();
                Call mcall= mOkHttpClient.newCall(request);
                Response response = mcall.execute();
                Logger.i("相应："+response.body().string());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
