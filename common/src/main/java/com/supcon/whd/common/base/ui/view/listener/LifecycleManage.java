package com.supcon.whd.common.base.ui.view.listener;

import java.util.HashMap;
import java.util.Map;

public class LifecycleManage implements Lifecycle {

    private Map<String,Lifecycle> lifecycleMap;
    public LifecycleManage(){
        lifecycleMap=new HashMap<>();
    }

    public void register(String key, Lifecycle lifecycle) {
        lifecycleMap.put(key, lifecycle);
    }

    public void unregister(String key) {
        lifecycleMap.remove(key);
    }
    @Override
    public void onInit() {
        for (Map.Entry<String, Lifecycle> entry : lifecycleMap.entrySet()) {
            entry.getValue().onInit();
        }
    }

    @Override
    public void initView() {
        for (Map.Entry<String, Lifecycle> entry : lifecycleMap.entrySet()) {
            entry.getValue().initView();
        }
    }

    @Override
    public void initData() {
        for (Map.Entry<String, Lifecycle> entry : lifecycleMap.entrySet()) {
            entry.getValue().initData();
        }
    }

    @Override
    public void onStart() {
        for (Map.Entry<String, Lifecycle> entry : lifecycleMap.entrySet()) {
            entry.getValue().onStart();
        }
    }

    @Override
    public void onStop() {
        for (Map.Entry<String, Lifecycle> entry : lifecycleMap.entrySet()) {
            entry.getValue().onStop();
        }
    }

    @Override
    public void onResume() {
        for (Map.Entry<String, Lifecycle> entry : lifecycleMap.entrySet()) {
            entry.getValue().onResume();
        }
    }

    @Override
    public void onPause() {
        for (Map.Entry<String, Lifecycle> entry : lifecycleMap.entrySet()) {
            entry.getValue().onPause();
        }
    }


    public Lifecycle get(String key) {
        return lifecycleMap.get(key);
    }
    @Override
    public void onDestroy() {
        for (Map.Entry<String, Lifecycle> entry : lifecycleMap.entrySet()) {
            entry.getValue().onStop();
        }
    }
}
