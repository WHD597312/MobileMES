package com.supcon.whd.common.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.multidex.MultiDex;
import android.view.SurfaceView;
import android.view.View;
import android.webkit.WebView;

import com.supcon.whd.common.utils.DensityUtils;
import com.supcon.whd.common.utils.ScreenUtil;
import com.thejoyrun.router.Router;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class BaseApplication  extends Application {
    public static String baseUrl="http://192.168.90.147:8080/";

    public Stack<Activity> store;
    public static BaseApplication mApp;
    private Set<Class<? extends View>> mProblemViewClassSet = new HashSet<>();
    private static Context mContext;

    public static Context getContext(){
        return mContext;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mContext=this;
        mApp=this;
        store = new Stack<>();
        registerActivityLifecycleCallbacks(new SwitchBackgroundCallbacks());
        MultiDex.install(this);
        Router.init("MobileMES");
        DensityUtils.setDensity(this);
    }
    private class SwitchBackgroundCallbacks implements ActivityLifecycleCallbacks {

        @Override
        public void onActivityCreated(Activity activity, Bundle bundle) {
            store.add(activity);
        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            store.remove(activity);
        }
    }

    /**
     * 获取当前的Activity
     *
     * @return
     */
    public Activity getCurActivity() {
        return store.lastElement();
    }

    /**
     * 获取倒数第二个 Activity
     *
     * @return
     */
    @Nullable
    public Activity getPenultimateActivity(Activity currentActivity) {
        Activity activity = null;
        try {
            if (store.size() > 1) {
                activity = store.get(store.size() - 2);

                if (currentActivity.equals(activity)) {
                    int index = store.indexOf(currentActivity);
                    if (index > 0) {
                        // 处理内存泄漏或最后一个 Activity 正在 finishing 的情况
                        activity = store.get(index - 1);
                    } else if (store.size() == 2) {
                        // 处理屏幕旋转后 mActivityStack 中顺序错乱
                        activity = store.lastElement();
                    }
                }
            }
        } catch (Exception e) {
        }
        return activity;
    }

    public void setProblemViewClassList(List<Class<? extends View>> problemViewClassList) {

        mProblemViewClassSet.add(WebView.class);
        mProblemViewClassSet.add(SurfaceView.class);
        if (problemViewClassList != null) {
            mProblemViewClassSet.addAll(problemViewClassList);
        }
    }


    /**
     * 某个 view 是否会导致滑动返回后立即触摸界面时应用崩溃
     *
     * @param view
     * @return
     */
    public boolean isProblemView(View view) {
        return mProblemViewClassSet.contains(view.getClass());
    }

    /**
     * 滑动返回是否可用
     *
     * @return
     */
    public boolean isSwipeBackEnable() {
        return store.size() > 1;
    }

}
