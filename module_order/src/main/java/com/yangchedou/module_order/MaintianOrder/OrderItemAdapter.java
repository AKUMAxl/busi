package com.yangchedou.module_order.MaintianOrder;

import android.support.annotation.Nullable;
import android.view.View;

import com.orhanobut.logger.Logger;
import com.yangchedou.lib_common.Base.ChangeActivity;
import com.yangchedou.lib_common.XLRecyclerView.XLRecyclerViewAdapter;
import com.yangchedou.lib_common.XLRecyclerView.XLRecyclerViewHolder;
import com.yangchedou.lib_common.XLRecyclerView.onItemPartClickListener;
import com.yangchedou.module_order.MaintianOrder.Bean.MianTainOrderBean;
import com.yangchedou.module_order.R;

import java.util.List;

/**
 * Created by ADMIN on 2017/11/20.
 */
 public class OrderItemAdapter extends XLRecyclerViewAdapter<MianTainOrderBean,XLRecyclerViewHolder> {

   private List<MianTainOrderBean> data;

   private onItemPartClickListener onItemPartClickListener;

    public OrderItemAdapter(int layoutResId, @Nullable List<MianTainOrderBean> data) {
        super(layoutResId, data);
        this.data = data;
    }

    @Override
    public void convert(final XLRecyclerViewHolder helper, MianTainOrderBean item, int position) {
        helper.setText(R.id.content_ddbh,item.getOrderCode())
                .setText(R.id.content_pay_way,item.getPayWay())
                .setText(R.id.content_pay_state,item.getPaystateName())
                .setText(R.id.content_fwnr,item.getOrderName())
                .setText(R.id.content_phone,item.getRegisterPhone())
                .setText(R.id.content_ddje,String.valueOf(item.getOrderPrice()))
                .setText(R.id.content_sjzfje,String.valueOf(item.getUseticketPrice()))
                .setText(R.id.content_date,item.getCreateDate())
                .addOnClickListener(R.id.image_toRight, position, new XLRecyclerViewHolder.OnClickListener() {
                    @Override
                    public void onClick(int position) {
                        Logger.i("onClick:"+data.get(position).getCarNo());
                        onItemPartClickListener.toNetxActivity(position);
                    }
                });
    }

    public void setOnItemPartClickListener(onItemPartClickListener onItemPartClickListener){
        this.onItemPartClickListener = onItemPartClickListener;
    }

    @Override
    public void convertHeader(XLRecyclerViewHolder helper, String str,int position) {
        helper.setText(R.id.tv_text,str);
    }

    @Override
    public void convertFooter(XLRecyclerViewHolder helper, String str,int position) {
        helper.setText(R.id.tv_text,str);
    }
}
