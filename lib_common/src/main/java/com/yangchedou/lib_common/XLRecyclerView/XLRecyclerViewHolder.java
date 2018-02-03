package com.yangchedou.lib_common.XLRecyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * Created by ADMIN on 2017/11/20.
 */

public class XLRecyclerViewHolder extends RecyclerView.ViewHolder {

    private final SparseArray<View> views;
    private final Context context;
    private View convertView;

    public OnClickListener onClickListener;

    public interface OnClickListener{
        void onClick(int position);
    }

    protected XLRecyclerViewHolder(Context context,View view){
        super(view);
        this.context = context;
        this.views = new SparseArray<View>();
        convertView = view;
    }

    protected <T extends View> T retrieveView(int ViewId){
        View view = views.get(ViewId);
        if (view==null){
            view = convertView.findViewById(ViewId);
            views.put(ViewId,view);
        }
        return (T)view;
    }

    public XLRecyclerViewHolder setText(int viewId,CharSequence value){
        TextView view = retrieveView(viewId);
        view.setText(value);
        return this;
    }

    public XLRecyclerViewHolder setBg(int viewId,int resourceId){
        TextView view = retrieveView(viewId);
        view.setBackgroundResource(resourceId);
        return this;
    }

    public XLRecyclerViewHolder setCheckState(int viewId,Boolean targetState){
        CheckBox view = retrieveView(viewId);
        view.setChecked(targetState);
        return this;
    }


    public void addOnClickListener(int viewId, final int position,final OnClickListener onClickListener){
        this.onClickListener = onClickListener;
        View view = retrieveView(viewId);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.onClick(position);
            }
        });
    }


}
