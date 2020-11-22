package com.supcon.whd.common.base.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.supcon.whd.common.base.ui.adapter.viewholder.BaseRecyclerViewHolder;
import com.supcon.whd.common.base.ui.view.listener.IListAdapter;
import com.supcon.whd.common.base.ui.view.listener.OnItemViewClickListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseListDataRecycleViewAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewHolder<T>> implements IListAdapter<T> {
    protected Context context;
    protected List<T> list;
    protected OnItemViewClickListener onItemViewClickListener;

    public BaseListDataRecycleViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public BaseRecyclerViewHolder<T> onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=View.inflate(context,getLayoutId(),null);
        BaseRecyclerViewHolder<T> viewHolder=getViewHolder(view);
        viewHolder.setOnItemViewClickListener(onItemViewClickListener);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull BaseRecyclerViewHolder<T> holder, int i) {
        holder.bind(getItemEntity(i));
    }

    @Override
    public int getItemCount() {
        if (list!=null){
            return list.size();
        }
        return 0;
    }

    public T getItemEntity(int i){
        if (list!=null && !list.isEmpty()){
            return list.get(i);
        }
        return null;
    }

    public abstract BaseRecyclerViewHolder<T> getViewHolder(View view);
    public abstract int getLayoutId();

    public void setOnItemViewClickListener(OnItemViewClickListener onItemViewClickListener) {
        this.onItemViewClickListener = onItemViewClickListener;
    }


    @Override
    public void setList(List<T> mList) {
        if (list==null){
            list=new ArrayList<>();
        }
        list=mList;
        notifyDataSetChanged();
    }

    @Override
    public void addData(T t) {
        if (t==null)
            return;

        if (list==null)
            list=new ArrayList<>();
        if (t!=null){
            list.add(t);
            notifyDataSetChanged();
        }
    }

    @Override
    public void addList(List<T> list) {
        if (this.list==null) {
            setList(list);
            notifyDataSetChanged();
        }
        else if (list!=null && !list.isEmpty()){
            this.list.addAll(list);
            notifyDataSetChanged();
        }
    }

    @Override
    public void updateData() {
        notifyDataSetChanged();
    }

    @Override
    public void remove(T t) {
        if (list!=null && list.size()>0 && list.contains(t)){
            list.remove(t);
            notifyDataSetChanged();
        }
    }

    @Override
    public void remove(int i) {
        if(list!=null && list.size()>0 && list.get(i)!=null){
            list.remove(i);
            notifyDataSetChanged();
        }
    }

    @Override
    public void clear() {
        if (list!=null && !list.isEmpty()){
            list.clear();
            notifyDataSetChanged();
        }
    }

    @Override
    public void setData(T t, int position) {
        if (list!=null && !list.isEmpty() && list.get(position)!=null){
            list.set(position,t);
            notifyDataSetChanged();
        }
    }

    @Override
    public List<T> getList() {
        return list;
    }

}
