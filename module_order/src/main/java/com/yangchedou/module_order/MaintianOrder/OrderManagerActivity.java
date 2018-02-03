package com.yangchedou.module_order.MaintianOrder;

import android.support.design.widget.TabLayout.Tab;


import android.support.design.widget.TabLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.view.View;
import android.widget.TextView;


import com.alibaba.android.arouter.facade.annotation.Route;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yangchedou.lib_common.Base.BaseActivity;

import com.yangchedou.lib_common.Base.ChangeActivity;
import com.yangchedou.lib_common.Weight.XlTitle;
import com.yangchedou.lib_common.XLRecyclerView.RefreshRecyclerView;
import com.yangchedou.lib_common.XLRecyclerView.XLRecyclerViewAdapter;
import com.yangchedou.lib_common.XLRecyclerView.onItemPartClickListener;
import com.yangchedou.lib_common.utils.ToastUtils;
import com.yangchedou.module_order.MaintianOrder.Bean.MianTainOrderBean;
import com.yangchedou.module_order.MaintianOrder.OrderItemAdapter;
import com.yangchedou.module_order.MaintianOrder.OrderPersenter;
import com.yangchedou.module_order.MaintianOrder.OrderPersenterImpl;
import com.yangchedou.module_order.MaintianOrder.OrderView;
import com.yangchedou.module_order.R;


import java.util.ArrayList;
import java.util.List;


/**
 * Created by ADMIN on 2017/11/15.
 */
@Route(path = "/module_order/ordermanager")
public class OrderManagerActivity extends BaseActivity implements OrderView,onItemPartClickListener {

    private XlTitle title;
    private TabLayout tab_layout;
    private RefreshRecyclerView rv;
    //private RecyclerView rv;
    //private SwipeRefreshLayout srl;
    private RefreshLayout refreshLayout;
    private TextView tv_count,tv_price;

    private OrderItemAdapter adapter;
    private LinearLayoutManager manager;

    private List<MianTainOrderBean> list_miantainOrderBean;
    private OrderPersenter orderPersenter;

    private int current_state = 0;
    private int start_index = 0;
    private int last_index = 0;
    private int queryCount = 20;

    private int[] colors = {R.color.bottom_icon,R.color.colorGreen};

    @Override
    public void loadView() {
        setContentView(R.layout.activity_ordermanager);
    }

    @Override
    public void initData() {
        title = findViewById(R.id.order_title);

        tab_layout = findViewById(R.id.tab_layout);
        rv = findViewById(R.id.rv);
        tv_count = findViewById(R.id.tv_count);
        tv_price = findViewById(R.id.tv_price);
        refreshLayout = findViewById(R.id.ordermanager_sfl);
        /*srl = findViewById(R.id.srl);
        srl.setColorSchemeResources(colors);*/
//R.color.bottom_icon
        if (refreshLayout==null){
            Logger.i("refreshLayout==null");
            return;
        }
        refreshLayout.setPrimaryColorsId(R.color.bottom_icon, R.color.colorWhite);
        refreshLayout.setEnableLoadmore(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                Logger.i("--------刷新");
                orderPersenter.getMinatainOrderList(String.valueOf(current_state),0,20);
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });

        /*refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                Logger.i("--------加载");
                orderPersenter.getMinatainOrderList(String.valueOf(current_state),0,20);
                refreshlayout.finishLoadmore(2000*//*,false*//*);//传入false表示加载失败
            }
        });*/

        title.setText(getResources().getString(R.string.dingdanguanli));
        title.setleftbg(R.mipmap.actionbar_back_hl);
        title.setRightVisibility(false);

        title.setleftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oThis.finish();
            }
        });

        orderPersenter = new OrderPersenterImpl(getApplicationContext(),this);

        tab_layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(Tab tab) {
                switch (tab.getText().toString()){
                    case "待确认":
                        Logger.i("待确认");
                        current_state = 0;
                        orderPersenter.getMinatainOrderList("0",0,20);
                        break;
                    case "待服务":
                        Logger.i("待服务");
                        current_state = 1;
                        orderPersenter.getMinatainOrderList("1",0,20);
                        break;
                    case "待完成":
                        Logger.i("待完成");
                        current_state = 2;
                        orderPersenter.getMinatainOrderList("2",0,20);
                        break;
                    case "待关闭":
                        Logger.i("待关闭");
                        current_state = 3;
                        orderPersenter.getMinatainOrderList("3",0,20);
                        break;
                }
            }

            @Override
            public void onTabUnselected(Tab tab) {

            }

            @Override
            public void onTabReselected(Tab tab) {

            }
        });

        list_miantainOrderBean = new ArrayList<>();
        manager = new LinearLayoutManager(oThis,OrientationHelper.VERTICAL,false);

        rv.addItemDecoration(new DividerItemDecoration(oThis,LinearLayoutManager.VERTICAL));
        rv.setLayoutManager(manager);

        adapter = new OrderItemAdapter(R.layout.item_order,list_miantainOrderBean);
        adapter.setOnItemPartClickListener(this);
        adapter.setFooterView(true,0);
        adapter.setOnLoadMoreListener(new XLRecyclerViewAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                orderPersenter.getMinatainOrderList(String.valueOf(current_state),0,20);
            }
        });

        rv.setAdapter(adapter);
        /*srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srl.setRefreshing(true);
                orderPersenter.getMinatainOrderList(String.valueOf(current_state),0,20);
            }
        });*/

    }

    @Override
    public void firstRequestNet() {
        orderPersenter.getMinatainOrderList("0",0,20);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        orderPersenter.onDestory();
    }


    @Override
    public void loadMaintainOrderList(final List<MianTainOrderBean> list_miantainOrderBean1, final String count, final String price) {
        oThis.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv_count.setText(count);
                tv_price.setText(price);
                list_miantainOrderBean.clear();
                list_miantainOrderBean.addAll(list_miantainOrderBean1);
                adapter.notifyDataSetChanged();
                adapter.loadSuccess();
            }
        });

    }

    @Override
    public void showToast(final String text) {
        oThis.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtils.showShortToast(text);
            }
        });
    }

    @Override
    public void dismissHeaderFooter() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //srl.setRefreshing(false);
            }
        });
    }


    @Override
    public void toNetxActivity(int position) {

        ChangeActivity.getIntance().ToNextActivity(oThis,"/module_order/maintianOrderDetial",null,false);
    }
}
