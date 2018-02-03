package com.yangchedou.lib_common.Base.MultiSelect;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.yangchedou.lib_common.R;
import com.yangchedou.lib_common.XLRecyclerView.XLRecyclerViewAdapter;
import com.yangchedou.lib_common.XLRecyclerView.XLRecyclerViewHolder;

import java.util.List;

/**
 * Created by ADMIN on 2017/12/5.
 */

public class MultiSelectAdapter extends XLRecyclerViewAdapter<StringAndBoolean,XLRecyclerViewHolder> {

    private List<StringAndBoolean> data;

    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener{

        void onItemClick(int position);

    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public MultiSelectAdapter(int layoutResId, @Nullable List data) {
        super(layoutResId, data);
        this.data = data;
    }

    @Override
    public void convert(XLRecyclerViewHolder helper, StringAndBoolean item, int position) {
        helper.setText(R.id.item_multiselect_tv,item.getName());
        helper.setCheckState(R.id.item_multiselect_cb,item.getChecked());
        helper.addOnClickListener(R.id.item_multiselect, position, new XLRecyclerViewHolder.OnClickListener() {
            @Override
            public void onClick(int position) {
                onItemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public void convertHeader(XLRecyclerViewHolder helper, String str, int position) {

    }

    @Override
    public void convertFooter(XLRecyclerViewHolder helper, String str, int position) {

    }

}
