package com.supcon.whd.common.base.ui.adapter.viewholder;
import android.support.v7.widget.RecyclerView;

import android.view.View;
import android.widget.AdapterView;


import com.supcon.whd.common.base.ui.view.listener.OnItemViewClickListener;

import butterknife.ButterKnife;

public abstract class BaseRecyclerViewHolder<T> extends RecyclerView.ViewHolder {


    public BaseRecyclerViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
        initListener();
    }
    public abstract void bind(T t);

    public void initListener(){

    }
    private OnItemViewClickListener onItemViewClickListener;

    public void setOnItemViewClickListener(OnItemViewClickListener onItemViewClickListener) {
        this.onItemViewClickListener = onItemViewClickListener;
    }

    public void onItemViewClick(View view, int position){
        if (onItemViewClickListener!=null){
            onItemViewClickListener.onItemViewClick(view, position);
        }

    }
}
