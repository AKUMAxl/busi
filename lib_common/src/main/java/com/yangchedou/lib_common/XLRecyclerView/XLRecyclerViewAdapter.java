package com.yangchedou.lib_common.XLRecyclerView;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.yangchedou.lib_common.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ADMIN on 2017/11/20.
 */

public abstract class XLRecyclerViewAdapter<T,K extends XLRecyclerViewHolder> extends RecyclerView.Adapter<K> {

    private List<T> mData;
    private int mLayoutResId;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    private final int HEADVIEW = 0;
    private final int BODYVIEW = 1;
    private final int FOOTVIEW = 2;

    private boolean hasHeader = false;
    private boolean hasFooter = false;

    private int HeaderViewResId = 0;
    private int FooterViewResId = 0;

    private View HeaderView;
    private View FooterView;

    private OnLoadMoreListener onLoadMoreListener;

    public interface OnLoadMoreListener{

        void onLoadMore();
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener){
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public XLRecyclerViewAdapter(@LayoutRes int layoutResId, @Nullable List<T> data) {
        this.mData = data == null ? new ArrayList<T>() : data;
        if (layoutResId != 0) {
            this.mLayoutResId = layoutResId;
        }
    }

    public abstract void convert(K helper, T item,int position);

    public abstract void convertHeader(K helper,String str,int position);

    public abstract void convertFooter(K helper,String str,int position);

    @Override
    public int getItemViewType(int position) {
        int currentView = BODYVIEW;
        if (hasHeader&&hasFooter&&mData!=null){
            if (position==0){
                currentView = HEADVIEW;
                return currentView;
            }else if (position == mData.size()+1){
                currentView = FOOTVIEW;
                return currentView;
            }else {
                currentView = BODYVIEW;
                return currentView;
            }
        }else if ((hasHeader||hasFooter)&&mData!=null){
            if (hasHeader){
                if (position==0){
                    currentView = HEADVIEW;
                    return currentView;
                }else {
                    currentView = BODYVIEW;
                    return currentView;
                }
            }
            if (hasFooter){
                if (position==mData.size()){
                    currentView = FOOTVIEW;
                    return currentView;
                }else {
                    currentView = BODYVIEW;
                    return currentView;
                }
            }
            return currentView;
        }else {
            return currentView;
        }
    }

    @Override
    public K onCreateViewHolder(ViewGroup parent, int viewType) {
        K XLRecyclerViewHolder = null;
        View view = null;
        this.mContext = parent.getContext();
        this.mLayoutInflater = LayoutInflater.from(mContext);
        switch (viewType){
            case HEADVIEW:
                if (HeaderViewResId==0){
                    view = mLayoutInflater.inflate(R.layout.item_base_footer,parent,false);
                }else {
                    view = mLayoutInflater.inflate(HeaderViewResId,parent,false);
                }
                HeaderView = view;
                HeaderView.setVisibility(View.GONE);
                break;
            case BODYVIEW:
                view = mLayoutInflater.inflate(mLayoutResId,parent,false);
                break;
            case FOOTVIEW:
                if (FooterViewResId==0){
                    view = mLayoutInflater.inflate(R.layout.item_base_footer,parent,false);
                }else {
                    view = mLayoutInflater.inflate(FooterViewResId,parent,false);
                }
                FooterView = view;
                FooterView.setVisibility(View.GONE);

                break;
        }
        XLRecyclerViewHolder = (K) new XLRecyclerViewHolder(mContext,view);
        return XLRecyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(K holder, int position) {
        int viewType = holder.getItemViewType();
        switch (viewType){
            case HEADVIEW:
                    convertHeader(holder,"header",0);
                break;
            case BODYVIEW:
                if (mData!=null&&mData.size()!=0){
                    if (hasHeader){
                        convert(holder,getItem(position),position-1);
                    }else {
                        convert(holder,getItem(position),position);
                    }
                }
                break;
            case FOOTVIEW:
                convertFooter(holder,mContext.getResources().getString(R.string.yijiazaiquanbu),mData.size()==0?1:mData.size()+1);
                if (mData.size()<4){
                    FooterView.setVisibility(View.GONE);
                }
                break;
        }
    }

    @Override
    public int getItemCount() {
        int itemCount = 0;
        if (hasFooter&&hasHeader){
            itemCount = (mData==null?0:(mData.size()+2));
        }else if (hasHeader||hasFooter){
            itemCount = (mData==null?0:(mData.size()+1));
        }else {
            itemCount = (mData==null?0:mData.size());
        }
        if (mData.size()<4){
            itemCount--;
        }
        return itemCount;
    }

    private T getItem(int position){
        if (hasHeader&&mData!=null&&mData.size()!=0){
            return mData.get(position-1);
        }else if ((!hasHeader)&&mData!=null&&mData.size()!=0){
            return mData.get(position);
        }else {
            return null;
        }
    }

    public void setHeaderView(boolean hasHeader,int HeaderViewResId){
        this.hasHeader = hasHeader;
        if (hasHeader){
            this.HeaderViewResId = HeaderViewResId;
        }
    }

    public void setFooterView(boolean hasFooter,int FooterViewResId){
        this.hasFooter = hasFooter;
        if (hasFooter){
            this.FooterViewResId = FooterViewResId;
        }
    }

    public int showHeaderView(){
        int h = 0;
        if (HeaderView!=null){
            //HeaderView.
            RecyclerView.LayoutParams p = (RecyclerView.LayoutParams) HeaderView.getLayoutParams();
            h = p.height;
            ObjectAnimator animator1 = ObjectAnimator.ofFloat(HeaderView,"y",0,h);
            animator1.setDuration(500);
            animator1.start();
            HeaderView.setVisibility(View.VISIBLE);
        }
        return h;
    }

    public int dismessHeaderView(){
        int h = 0;
        if (HeaderView!=null){
            RecyclerView.LayoutParams p = (RecyclerView.LayoutParams) HeaderView.getLayoutParams();
            h = p.height;
            ObjectAnimator animator1 = ObjectAnimator.ofFloat(HeaderView,"y",0,-h);
            animator1.setDuration(500);
            animator1.start();
            HeaderView.setVisibility(View.GONE);
        }
        return h;
    }

    public void showFooterView(){
        if (FooterView!=null){
            FooterView.setVisibility(View.VISIBLE);
            ( (TextView)FooterView.findViewById(R.id.tv_text)).setText(mContext.getResources().getString(R.string.jiazaizhong));
            ((ProgressBar)FooterView.findViewById(R.id.pb)).setVisibility(View.VISIBLE);
            onLoadMoreListener.onLoadMore();
        }
    }

    public void setFooterText(String text){
        if (FooterView!=null){
            ( (TextView)FooterView.findViewById(R.id.tv_text)).setText(text);

        }
    }

    public void loadOver(){
        if (FooterView!=null){
            ((ProgressBar)FooterView.findViewById(R.id.pb)).setVisibility(View.GONE);
            ( (TextView)FooterView.findViewById(R.id.tv_text)).setText(mContext.getResources().getString(R.string.yijiazaiquanbu));
        }
    }

    public void loadSuccess(){
        if (FooterView!=null){
            ( (TextView)FooterView.findViewById(R.id.tv_text)).setText(mContext.getResources().getString(R.string.jiazaizhong));
            ((ProgressBar)FooterView.findViewById(R.id.pb)).setVisibility(View.GONE);
        }
    }

    public void loadFailure(){
        if (FooterView!=null){
            ( (TextView)FooterView.findViewById(R.id.tv_text)).setText(mContext.getResources().getString(R.string.jiazaishibai));
            ((ProgressBar)FooterView.findViewById(R.id.pb)).setVisibility(View.GONE);
        }
    }
}
