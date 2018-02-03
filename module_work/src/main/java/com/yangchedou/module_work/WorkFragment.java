package com.yangchedou.module_work;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.orhanobut.logger.Logger;
import com.yangchedou.lib_common.Base.BaseFragment;
import com.yangchedou.lib_common.Base.ChangeActivity;
import com.yangchedou.lib_common.Weight.MyProgressDialog;
import com.yangchedou.lib_common.Zxing.android.CaptureActivity;
import com.yangchedou.lib_common.Zxing.common.Constant;
import com.yangchedou.lib_common.utils.SharedPreferencesUtil;
import com.yangchedou.lib_common.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;


import static android.app.Activity.RESULT_OK;

/**
 * Created by ADMIN on 2017/11/11.
 */

public class WorkFragment extends BaseFragment implements WorkView,View.OnClickListener,WorkItemAdapter.OnWorkItemClickListener{

    private ImageView iv;
    private RecyclerView rlv;
    private ToggleButton tb;
    private TextView tv_qrcode,tv_busiName,tv_busiId;
    private MyProgressDialog dialog;

    private List<PicAndTextBean> list_picandtext;
    private WorkItemAdapter workItemAdapter;
    private GridLayoutManager gridManager;

    private int businessType = 0;//0:普通商家   1:物流

    private WorkPersenter workPersenter;

    public WorkFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_work, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    @Override
    public void onResume() {
        super.onResume();
        workPersenter.unreadMessageCount();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        workPersenter.Destory();
    }

    private void init(){
        iv = getActivity().findViewById(R.id.photo);
        rlv = getActivity().findViewById(R.id.work_rlv);
        tv_qrcode = getActivity().findViewById(R.id.qrcode);
        tv_busiName = getActivity().findViewById(R.id.shop_name);
        tv_busiId = getActivity().findViewById(R.id.shop_id);
        tb = getActivity().findViewById(R.id.tb);

        tv_qrcode.setOnClickListener(this);
        tb.setOnClickListener(this);

        Glide.with(getContext()).load(R.mipmap.tab1_profile).into(iv);

        businessType = Integer.valueOf(SharedPreferencesUtil.getIntance(getActivity()).GetData(SharedPreferencesUtil.KEY.BUSINESS_TYPE));

        loadItem();

        dialog = new MyProgressDialog(getContext(),false,getResources().getString(R.string.qingshaodeng));
        workPersenter = new WorkPersenterImpl(getContext(),this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1&&resultCode==RESULT_OK){
            String s = data.getStringExtra(Constant.CODED_CONTENT);
            ToastUtils.showShortToast(s);
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id==R.id.qrcode){
            Intent intent = new Intent(getActivity(), CaptureActivity.class);
            startActivityForResult(intent,1);
        }else if (id==R.id.tb){
            Boolean isChecked = tb.isChecked();
            Logger.i("isChecked:"+isChecked);
            workPersenter.changeShopState(isChecked);
        }
    }

    @Override
    public void onWorkItemClick(int position) {

        switch (position){
            //客户管理 或 待送商品订单
            case 0:
                if (businessType==0){
                    //客户管理
                }else if(businessType==1){
                    //待送商品订单
                }
                break;
            //维修保养订单 或 已送商品订单
            case 1:
                if (businessType==0){
                    //维修保养订单
                    ChangeActivity.getIntance().ToNextActivity(getActivity(),"/module_order/ordermanager",null,false);
                }else if(businessType==1){
                    //已送商品订单
                }
                break;
            //销售分析
            case 2:
                /*Observable<Integer> observable = Observable.create(new Observable.OnSubscribe<Integer>() {
                    @Override
                    public void call(Subscriber<? super Integer> subscriber) {
                        Logger.i("call---");
                        workPersenter.changeShopState(true);
                    }
                });
                Observer observer = new Observer() {
                    @Override
                    public void onCompleted() {
                        Logger.i("onCompleted---");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.i("onError---");
                    }

                    @Override
                    public void onNext(Object o) {
                        Logger.i("onError---"+o);
                    }
                };
                observable.subscribe(observer);*/
                break;
            //洗车订单
            case 3:

                break;
            //财务
            case 4:
                break;
            //查看评论
            case 5:
                break;
            //信息
            case 6:
                workPersenter.clickMessage();
                break;
            //保养券预约记录
            case 7:
                break;
            //卡券消费记录
            case 8:
                break;
            //库存机油查询
            case 9:
                break;
            //礼品核对记录
            case 10:
                break;
        }

    }

    /**
     * 加载工作台各模块
     */
    public void loadItem() {
        gridManager = new GridLayoutManager(getContext(),3);
        rlv.setLayoutManager(gridManager);
        rlv.addItemDecoration(new DividerItemDecoration(getActivity(),LinearLayoutManager.VERTICAL));
        rlv.addItemDecoration(new DividerItemDecoration(getActivity(),LinearLayoutManager.HORIZONTAL));
        list_picandtext = new ArrayList<>();
        if (businessType==0){
            list_picandtext.add(new PicAndTextBean(R.mipmap.item_pic_khgl,R.string.khgl,0));
            list_picandtext.add(new PicAndTextBean(R.mipmap.item_pic_wxbydd,R.string.wxbydd,0));
            list_picandtext.add(new PicAndTextBean(R.mipmap.item_pic_xsfx,R.string.xsfx,0));
            list_picandtext.add(new PicAndTextBean(R.mipmap.item_pic_xcdd,R.string.xcdd,0));
            list_picandtext.add(new PicAndTextBean(R.mipmap.item_pic_cw,R.string.cw,0));
            list_picandtext.add(new PicAndTextBean(R.mipmap.item_pic_ckpl,R.string.ckpl,0));
            list_picandtext.add(new PicAndTextBean(R.mipmap.item_pic_xx,R.string.xx,0));
            list_picandtext.add(new PicAndTextBean(R.mipmap.item_pic_byjyyjl,R.string.byjyydd,0));
            list_picandtext.add(new PicAndTextBean(R.mipmap.item_pic_kqxfjl,R.string.kjxfjl,0));
            list_picandtext.add(new PicAndTextBean(R.mipmap.item_pic_kcjycx,R.string.kcjygl,0));
            list_picandtext.add(new PicAndTextBean(R.mipmap.item_pic_lphdjl,R.string.lphdjl,0));
        }else if (businessType==1){
            list_picandtext.add(new PicAndTextBean(R.mipmap.item_pic_dsspdd,R.string.dsspdd,0));
            list_picandtext.add(new PicAndTextBean(R.mipmap.item_pic_ysspdd,R.string.ysspdd,0));
        }
        workItemAdapter = new WorkItemAdapter(getContext(),list_picandtext);
        workItemAdapter.setOnWorkItemClickListener(this);
        rlv.setAdapter(workItemAdapter);
    }

    @Override
    public void maintainCount(final int unreadCount) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                list_picandtext.set(1,new PicAndTextBean(R.mipmap.item_pic_wxbydd,R.string.wxbydd,unreadCount));
                workItemAdapter.notifyItemChanged(1);
            }
        });

    }

    @Override
    public void washCount(final int unreadCount) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                list_picandtext.set(3,new PicAndTextBean(R.mipmap.item_pic_xcdd,R.string.xcdd,unreadCount));
                workItemAdapter.notifyItemChanged(3);
            }
        });

    }

    @Override
    public void changeShopState(final boolean onBusi) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tb.setChecked(onBusi);
            }
        });
    }

    @Override
    public void showToast(final String text) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtils.showShortToast(text);
            }
        });
    }

    @Override
    public void showDialog(final String text) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                dialog.setText(text);
                dialog.show();
            }
        });
    }

    @Override
    public void dismissDialog() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (dialog.isShowing())
                    dialog.dismiss();
            }
        });
    }

    @Override
    public void loadPhoto(final String url) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Glide.with(getContext())
                        .load(url)
                        .placeholder(R.mipmap.tab1_profile)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .override(100,100)
                        .error(R.mipmap.tab1_profile)
                        .into(iv);
            }
        });
    }

    @Override
    public void setBusiName(final String name) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv_busiName.setText(name);
            }
        });
    }

    @Override
    public void setBusiId(final String id) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv_busiId.setText(id);
            }
        });
    }


}
