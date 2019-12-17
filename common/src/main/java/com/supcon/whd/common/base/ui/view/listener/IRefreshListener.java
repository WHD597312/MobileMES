package com.supcon.whd.common.base.ui.view.listener;

public interface IRefreshListener<T> {
    void setOnRefreshPageListener(OnRefreshPageListener onRefreshPageListener);
    IListAdapter<T> getListAdapter();
}
