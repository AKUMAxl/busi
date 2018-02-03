package com.yangchedou.module_personal.BusinessBrief;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CheckableImageButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.orhanobut.logger.Logger;
import com.yangchedou.lib_common.AllData;
import com.yangchedou.lib_common.MobileInfo;
import com.yangchedou.module_personal.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ADMIN on 2017/11/27.
 */

public class BusinessBriefAdapter extends RecyclerView.Adapter<BusinessBriefAdapter.ViewHolder> {

    private Context context;
    private List<File> list_file = new ArrayList<>();
    private LayoutInflater inflater;

    private int ViewType = 0;  //0:正常item 1:footer

    private onBusinesPicClickListener onBusinesPicClickListener;

    public interface onBusinesPicClickListener{

        void ClickPic(int position);

    }

    public void setOnBusinesPicClickListener(onBusinesPicClickListener onBusinesPicClickListener){
        this.onBusinesPicClickListener = onBusinesPicClickListener;
    }

    public BusinessBriefAdapter(Context context,List<File> list_file){
        this.context = context.getApplicationContext();
        this.list_file = list_file;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        if (position==0&&list_file.size()==0){
            ViewType = 1;
        }else if (position==list_file.size()){
            ViewType = 1;
        }else {
            ViewType = 0;
        }
        return ViewType;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (viewType==1){
            view = inflater.inflate(R.layout.item_picture,null);
        }else {
            view = inflater.inflate(R.layout.item_picture,null);
        }
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        switch (holder.getItemViewType()){
            case 0:
                if (list_file.get(position).getAbsolutePath().contains(AllData.uploadPic_host)){
                    String url = list_file.get(position).getAbsolutePath();
                    url = url.substring(7);
                    Glide.with(context)
                            .load("http://"+url)
                            .fitCenter()
                            .into(holder.cib);
                }else {
                    Glide.with(context)
                            .load(list_file.get(position))
                            .fitCenter()
                            .into(holder.cib);
                }
                break;
            case 1:

                break;
        }
        holder.cib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBusinesPicClickListener.ClickPic(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list_file.size()==9?9:list_file.size()+1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CheckableImageButton cib;

        public ViewHolder(View itemView) {
            super(itemView);
            cib = itemView.findViewById(R.id.item_picture_cib);

            int[] arr_wh = MobileInfo.getScreenWH(context);
            ConstraintLayout.LayoutParams p = (ConstraintLayout.LayoutParams) cib.getLayoutParams();
            p.width = (arr_wh[0]-90)/3;
            p.height = (arr_wh[0]-90)/3-20;
            p.setMargins(20,20,20,20);
            cib.setLayoutParams(p);

            if (ViewType==1){
                Glide.with(context)
                        .load(R.mipmap.btn_addimage)
                        .fitCenter()
                        .into(cib);
            }
        }


    }
}
