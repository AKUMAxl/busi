package com.yangchedou.module_work;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.orhanobut.logger.Logger;

import java.util.List;

/**
 * Created by ADMIN on 2017/11/13.
 */

public class WorkItemAdapter extends RecyclerView.Adapter<WorkItemAdapter.ViewHolder> {

    private Context context;
    private List<PicAndTextBean> list_picandtext;
    private LayoutInflater inflater;

    private OnWorkItemClickListener onWorkItemClickListener;

    interface OnWorkItemClickListener{

        void onWorkItemClick(int position);
    }

    public void setOnWorkItemClickListener(OnWorkItemClickListener onWorkItemClickListener){
        this.onWorkItemClickListener = onWorkItemClickListener;
    }

    public WorkItemAdapter(Context context,List<PicAndTextBean> list_picandtext){
        this.context = context;
        this.list_picandtext = list_picandtext;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_work,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder,final int position) {
        PicAndTextBean picAndTextBean = list_picandtext.get(position);
        holder.tv.setText(context.getResources().getString(picAndTextBean.getStrId()));
        Glide.with(context).load(picAndTextBean.getResId()).into(holder.iv);
        holder.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onWorkItemClickListener.onWorkItemClick(position);
            }
        });
        if (picAndTextBean.getUnreadCount()>0){
            holder.tv_count.setVisibility(View.VISIBLE);
            holder.tv_count.setText(String.valueOf(picAndTextBean.getUnreadCount()));
        }else{
            holder.tv_count.setVisibility(View.GONE);
        }


    }

    @Override
    public int getItemCount() {
        return list_picandtext==null?0:list_picandtext.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv;
        TextView tv,tv_count;
        RelativeLayout rl;

        public ViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.item_work_pic);
            tv = itemView.findViewById(R.id.item_work_text);
            rl = itemView.findViewById(R.id.item_work_rl);
            tv_count = itemView.findViewById(R.id.unread_count);
        }
    }

}
