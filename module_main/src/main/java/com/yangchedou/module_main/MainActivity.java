package com.yangchedou.module_main;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.orhanobut.logger.Logger;
import com.yangchedou.lib_common.Base.BaseActivity;
import com.yangchedou.lib_common.Base.BaseFragment;
import com.yangchedou.lib_common.Base.ViewManager;
import com.yangchedou.lib_common.Weight.NoScrollViewPager;
import com.yangchedou.lib_common.utils.ToastUtils;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by ADMIN on 2017/11/6.
 */
@Route(path = "/module_main/main")
public class MainActivity extends BaseActivity implements View.OnClickListener,MainView{

    private long mExitTime = 0;
    private BottomNavigationView bottomNavigationView;
    private NoScrollViewPager mPager;
    private List<BaseFragment> mFragments;
    private FragmentAdapter mAdapter;

    private MainPersenter mainPersenter;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int i = item.getItemId();
            if (i == R.id.navigation_home_tab1) {
                mPager.setCurrentItem(0);
                return true;
            } else if (i == R.id.navigation_home_tab2) {
                mPager.setCurrentItem(1);
                return true;
            } else if (i == R.id.navigation_home_tab3) {
                mPager.setCurrentItem(2);
                return true;
            }
            return false;
        }

    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPersenter.Destory();
    }

    @Override
    public void loadView() {
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    @Override
    public void initData() {

        mainPersenter = new MainPersenterImpl(oThis,this);
        mainPersenter.checkUpdate();

        mFragments = ViewManager.getInstance().getAllFragment();

        mPager = (NoScrollViewPager) findViewById(R.id.no_scroll_viewpager);
        mAdapter = new FragmentAdapter(getSupportFragmentManager(), mFragments);
        mPager.setPagerEnabled(false);
        mPager.setAdapter(mAdapter);
    }

    @Override
    public void firstRequestNet() {

    }

    @Override
    public void fresh() {

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            //两秒之内按返回键就会退出
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                //ToastUtils.showShortToast(getString(R.string.app_exit_hint));
                Toast.makeText(oThis,"再次点击退出程序",Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                ViewManager.getInstance().exitApp(oThis);
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void showToast(final String text) {
        /*oThis.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtils.showShortToast(text);
            }
        });*/
    }

    @Override
    public void downLoadDialog(final String downLoadURI) {
        oThis.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //new AlertDialog.Builder(oThis).setTitle(downLoadURI).show();
                new AlertDialog.Builder(oThis)
                        .setTitle(getResources().getString(R.string.dqyxbbqsj))
                        .setNegativeButton(getResources().getString(R.string.sj), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                Uri uri = Uri.parse(downLoadURI);
                                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                startActivity(intent);

                            }
                        })
                        .setNeutralButton(getResources().getString(R.string.qx), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ToastUtils.showShortToast("cancel");
                                dialogInterface.dismiss();
                            }
                        }).show();
            }
        });
    }




}
