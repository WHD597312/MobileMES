package com.supcon.whd.common.controller;

import android.content.Context;
import com.jwenfeng.library.pulltorefresh.BaseRefreshListener;
import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;
import com.supcon.whd.common.base.ui.view.listener.IListAdapter;
import com.supcon.whd.common.base.ui.view.listener.IRefreshListener;
import com.supcon.whd.common.base.ui.view.listener.OnRefreshPageListener;
import java.util.List;


public class RefreshController<T> implements IRefreshListener {

    private Context context;
    private IListAdapter<T> listAdapter;
    public PullToRefreshLayout pullToRefreshLayout;
    OnRefreshPageListener mOnRefreshPageListener;
    protected int pageNo=1;
    private boolean refresh=false;
    private boolean loadMore=false;


    public RefreshController(Context context, IListAdapter<T> listAdapter) {
        this.context = context;
        this.listAdapter = listAdapter;
    }

    public void initListener(){
        pullToRefreshLayout.setRefreshListener(new BaseRefreshListener() {
            @Override
            public void refresh() {
                if (mOnRefreshPageListener!=null){
                    loadMore=false;
                    refresh=true;
                    pageNo=1;
                    if (mOnRefreshPageListener!=null){
                        mOnRefreshPageListener.onRefreshPage(pageNo);
                    }
                }else {
                    pullToRefreshLayout.finishRefresh();
                }
            }
            @Override
            public void loadMore() {
                if (mOnRefreshPageListener!=null){
                    loadMore=true;
                    refresh=false;
                    if (mOnRefreshPageListener!=null){
                        mOnRefreshPageListener.onRefreshPage(pageNo);
                    }
                }else {
                    pullToRefreshLayout.finishLoadMore();
                }
            }
        });
    }


    @Override
    public void setOnRefreshPageListener(OnRefreshPageListener onRefreshPageListener) {
        mOnRefreshPageListener=onRefreshPageListener;
        if (mOnRefreshPageListener!=null){
            mOnRefreshPageListener.onRefreshPage(pageNo);
        }
    }


    public void completeRefresh(List<T> list){
        if (listAdapter!=null) {
            if (pageNo==1){
                listAdapter.setList(list);
            }else if (pageNo>1){
                listAdapter.addList(list);
            }
            pageNo++;
            if (loadMore){
                pullToRefreshLayout.finishLoadMore();
            }else if (refresh){
                pullToRefreshLayout.finishRefresh();
            }
        }
    }
    public void completeRefresh(){
        if (loadMore){
            pullToRefreshLayout.finishLoadMore();
        }else if (refresh){
            pullToRefreshLayout.finishRefresh();
        }
    }
    public void refreshBegin(){
        if (mOnRefreshPageListener!=null){
            pageNo=1;
            mOnRefreshPageListener.onRefreshPage(pageNo);
        }
    }

    @Override
    public IListAdapter getListAdapter() {
        return listAdapter;
    }
}
