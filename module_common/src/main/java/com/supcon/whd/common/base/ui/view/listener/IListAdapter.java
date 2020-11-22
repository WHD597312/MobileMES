package com.supcon.whd.common.base.ui.view.listener;

import java.util.List;

public interface IListAdapter<T> {
    void setList(List<T> list);
    void addData(T t);
    void addList(List<T> list);
    void remove(T t);
    void remove(int i);
    void clear();
    void setData(T t,int position);
    List<T> getList();
    void updateData();
}
