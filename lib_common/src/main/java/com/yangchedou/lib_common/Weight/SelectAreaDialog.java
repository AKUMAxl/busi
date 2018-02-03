package com.yangchedou.lib_common.Weight;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.orhanobut.logger.Logger;
import com.yangchedou.lib_common.R;


/**
 * Created by ADMIN on 2017/12/1.
 */

public class SelectAreaDialog extends Dialog {

    private Context context;
    private TabLayout tabLayout;
    private RecyclerView rv_sheng,rv_shi,rv_qu;


    public SelectAreaDialog(Context context) {
        super(context,R.style.MyDialog1);
        this.context = context;
    }

    public SelectAreaDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    protected void init() {
        //以view来引入布局
        //View view = LayoutInflater.from(context).inflate(R.layout.wait_dialog_d,null);
        setContentView(R.layout.layout_selecta_area);
        //设置dialog大小
        Window dialogWindow = getWindow();
        WindowManager manager = ((AppCompatActivity) context).getWindowManager();
        WindowManager.LayoutParams params = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        dialogWindow.setGravity(Gravity.BOTTOM);
        Display d = manager.getDefaultDisplay(); // 获取屏幕宽、高度
        params.width = (int) (d.getWidth()); // 宽度设置为屏幕的0.65，根据实际情况调整
        params.alpha = 1f;
        params.windowAnimations = R.anim.fragment_slide_in_from_left;
        dialogWindow.setAttributes(params);

        tabLayout = findViewById(R.id.select_area_tab);

        final TabLayout.Tab tab1 = tabLayout.newTab().setText("111");
        final TabLayout.Tab tab2 = tabLayout.newTab().setText("222");
        tabLayout.addTab(tab1);
        tabLayout.addTab(tab2);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:

                        break;
                    case 1:
                        //ti3.setVisibility(View.VISIBLE);
                        break;
                    case 2:

                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        /*TabLayout.Tab tab1 = tabLayout.newTab().setText("111");
        TabLayout.Tab tab2 = tabLayout.newTab().setText("222");
        TabLayout.Tab tab3 = tabLayout.newTab().setText("333");
        tabLayout.addTab(tab1);
        tabLayout.addTab(tab2);
        tabLayout.addTab(tab3);*/
    }


}

