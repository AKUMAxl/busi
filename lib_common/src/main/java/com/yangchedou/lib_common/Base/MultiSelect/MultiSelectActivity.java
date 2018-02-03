package com.yangchedou.lib_common.Base.MultiSelect;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.orhanobut.logger.Logger;
import com.yangchedou.lib_common.AllData;
import com.yangchedou.lib_common.Base.BaseActivity;
import com.yangchedou.lib_common.R;
import com.yangchedou.lib_common.Weight.XlTitle;
import com.yangchedou.lib_common.XLRecyclerView.RefreshRecyclerView;
import com.yangchedou.lib_common.XLRecyclerView.XLRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ADMIN on 2017/12/5.
 */
@Route(path = "/lib_common/multiSelect")
public class MultiSelectActivity extends BaseActivity implements View.OnClickListener,MultSelectView{

    private XlTitle title;
    private RefreshRecyclerView rv;
    private Button btn;

    private MultiSelectAdapter adapter;

    private List<StringAndBoolean> list_sab;
    private List<String> list_str;
    private List<Boolean> list_check;

    private String from;

    private Boolean all_select = false;

    private MultiSelectPersenter multiSelectPersenter;

    @Override
    public void loadView() {
        setContentView(R.layout.activity_multiselect);
    }

    @Override
    public void initData() {

        multiSelectPersenter = new MultiSelectPersenterImpl(getApplicationContext(),this);

        Bundle bundle = getIntent().getBundleExtra("bundle");
        from = bundle.getString("from");

        title = findViewById(R.id.multi_select_title);
        rv = findViewById(R.id.multi_select_rv);
        btn = findViewById(R.id.multi_select_btn);

        if (from.equals("fwfw")){
            title.setText(getResources().getString(R.string.fuwufanwei));
        }
        if (from.equals("jyxm")){
            title.setText(getResources().getString(R.string.jingyingxiangmu));
        }
        title.setleftbg(R.mipmap.actionbar_back_hl);
        title.setleftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oThis.finish();
            }
        });
        title.setrightText(R.string.quanxuan);
        title.setrightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                all_select = !all_select;
                AllSelect(all_select);
            }
        });

        rv.setLayoutManager(new LinearLayoutManager(oThis, OrientationHelper.VERTICAL,false));
        rv.addItemDecoration(new DividerItemDecoration(oThis,LinearLayoutManager.VERTICAL));
        list_sab = new ArrayList<>();
        list_str = new ArrayList<>();
        list_check = new ArrayList<>();
        adapter = new MultiSelectAdapter(R.layout.item_multiselect,list_sab);
        adapter.setFooterView(false,0);
        adapter.setOnItemClickListener(new MultiSelectAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                list_check.set(position,!list_check.get(position));
                boolean currentState = list_check.get(position);
                StringAndBoolean sab = new StringAndBoolean();
                sab.setName(list_str.get(position));
                sab.setChecked(currentState);
                Logger.i("--------------currentState:"+currentState);
                list_sab.set(position,sab);
                adapter.notifyDataSetChanged();
            }
        });

        rv.setAdapter(adapter);


    }

    @Override
    public void firstRequestNet() {
        if (from.equals("fwfw")){
            multiSelectPersenter.getFwfwList();
        }
        if (from.equals("jyxm")){
            multiSelectPersenter.getJyxmList();
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id==R.id.multi_select_btn){
            StringBuilder sb = new StringBuilder();
            for (int i=0;i<list_check.size();i++){
                if (list_check.get(i)){
                    sb.append(list_str.get(i)).append(",");
                }
            }
            Intent intent = new Intent();
            intent.putExtra(from,sb.toString());
            setResult(AllData.SELECT_MULTISELECT_CODE,intent);
        }
    }

    @Override
    public void AllSelect(boolean targetState) {

        if (targetState){
            title.setrightText(R.string.quxiao);
        }else {
            title.setrightText(R.string.quanxuan);
        }
        list_check.clear();
        list_sab.clear();
        for (String str:list_str){
            StringAndBoolean sab = new StringAndBoolean();
            sab.setName(str);
            list_check.add(targetState);
            sab.setChecked(targetState);
            list_sab.add(sab);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void notifyRv(List<StringAndBoolean> list) {
        list_sab.clear();
        list_str.clear();
        list_check.clear();
        for (StringAndBoolean sab:list) {
            list_sab.add(sab);
            list_str.add(sab.getName());
            list_check.add(sab.getChecked());
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });

    }
}
