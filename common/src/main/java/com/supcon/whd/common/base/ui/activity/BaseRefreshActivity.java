package com.supcon.whd.common.base.ui.activity;


import android.support.v7.widget.RecyclerView;

import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;
import com.supcon.whd.common.R;
import com.supcon.whd.common.base.ui.adapter.BaseListDataRecycleViewAdapter;
import com.supcon.whd.common.base.ui.view.MyHeadRefreshView;
import com.supcon.whd.common.base.ui.view.MyLoadMoreView;
import com.supcon.whd.common.base.ui.view.listener.IListAdapter;
import com.supcon.whd.common.controller.RefreshController;




public abstract class BaseRefreshActivity<T> extends BasePresenterActivity{

    protected PullToRefreshLayout refreshOperate;
    protected RecyclerView contentView;

    protected RefreshController<T> refreshController;

    @Override
    public void onInit() {
        super.onInit();
        refreshController=new RefreshController<>(this,getAdapter());
    }

    @Override
    public void initView() {
        refreshOperate=findViewById(R.id.refreshOperate);
        contentView=findViewById(R.id.contentView);
        refreshController.pullToRefreshLayout=refreshOperate;
        refreshController.initListener();
        refreshOperate.setHeaderView(new MyHeadRefreshView(this));
        refreshOperate.setFooterView(new MyLoadMoreView(this));
        contentView.setAdapter((BaseListDataRecycleViewAdapter<T>)refreshController.getListAdapter());
    }

    @Override
    protected void onListener() {
        super.onListener();
    }

    protected abstract IListAdapter<T> getAdapter();


}
