package com.supcon.whd.common.base.ui.view.listener;

public interface Lifecycle {
    /**
     * 初始化
     */
    void onInit();
    /**
     * 初始化VIEW
     */
    void initView();



    /**
     * 初始化数据
     */
    void initData();
    void onStart();
    void onStop();
    void onResume() ;
    void onPause();
    /**
     * 撤消
     */
    void onDestroy();
}
