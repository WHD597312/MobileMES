package com.supcon.whd.common.base.ui.adapter;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.supcon.whd.common.base.ui.adapter.viewholder.BaseRecyclerViewHolder;
import com.supcon.whd.common.base.ui.view.listener.OnItemViewClickListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public  abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewHolder<T>>{
    protected Context context;
    protected List<T> list;

    public BaseRecyclerViewAdapter(){}

    public BaseRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public BaseRecyclerViewAdapter(Context context, List<T> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getItemCount() {
        if (list!=null)
            return list.size();
        return 0;
    }


    @NonNull
    @Override
    public BaseRecyclerViewHolder<T> onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=View.inflate(context,getLayoutId(),null);
        BaseRecyclerViewHolder<T> viewHolder=getViewHolder(view);
        viewHolder.setOnItemViewClickListener(onItemViewClickListener);
        return viewHolder;
    }

    public T getItemEntity(int i){
        if (list!=null && !list.isEmpty()){
            return list.get(i);
        }
        return null;
    }
    @Override
    public void onBindViewHolder(@NonNull BaseRecyclerViewHolder<T> holder, int i) {
        holder.bind(getItemEntity(i));
    }

    public abstract BaseRecyclerViewHolder<T> getViewHolder(View view);
    public abstract int getLayoutId();


    protected OnItemViewClickListener onItemViewClickListener;

    public void setOnItemViewClickListener(OnItemViewClickListener onItemViewClickListener) {
        this.onItemViewClickListener = onItemViewClickListener;
    }

    public void remove(int i){
        if (list!=null && !list.isEmpty())
            list.remove(i);
    }
    public void remove(T t){
        if (list!=null && !list.isEmpty() && list.contains(t))
            list.remove(t);
    }
    public void add(T t){
        if (list==null)
            list=new ArrayList<>();
        list.add(t);
    }
    public void addList(List<T> list){
        if (list==null)
            return;
        if (this.list==null)
            this.list=list;
        else {
            this.list.addAll(list);
        }
    }


    public void setList(List<T> list){
        this.list=list;
    }




}
