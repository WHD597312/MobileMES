package com.supcon.whd.common.base.ui.fragment;



import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;
import com.supcon.whd.common.R;
import com.supcon.whd.common.base.ui.adapter.BaseListDataRecycleViewAdapter;
import com.supcon.whd.common.base.ui.view.MyHeadRefreshView;
import com.supcon.whd.common.base.ui.view.MyLoadMoreView;
import com.supcon.whd.common.base.ui.view.listener.IListAdapter;
import com.supcon.whd.common.controller.RefreshController;

import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseRefreshFragment<T> extends BasePresenterFragment {
    protected PullToRefreshLayout refreshOperate;
    protected RecyclerView contentView;
    protected RefreshController<T> refreshController;

    @Override
    protected void onInit() {
        super.onInit();
        refreshController=new RefreshController<>(getActivity(),getAdapter());
    }

    @Override
    protected void initView() {
        super.initView();
        refreshOperate=view.findViewById(R.id.refreshOperate);
        contentView=view.findViewById(R.id.contentView);
        refreshController.pullToRefreshLayout=refreshOperate;
        refreshController.initListener();
        refreshOperate.setHeaderView(new MyHeadRefreshView(getActivity()));
        refreshOperate.setFooterView(new MyLoadMoreView(getActivity()));
        contentView.setAdapter((BaseListDataRecycleViewAdapter<T>)refreshController.getListAdapter());
    }

    protected abstract IListAdapter<T> getAdapter();
}
