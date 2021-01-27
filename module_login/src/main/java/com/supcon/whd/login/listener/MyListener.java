package com.supcon.whd.login.listener;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

public class MyListener implements LifecycleObserver {

    String TAG="MyListener";
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate(LifecycleOwner owner) {

        Log.i(TAG,"-->onCreate");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void start(LifecycleOwner owner) {
        Log.i(TAG,"onStart");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause(LifecycleOwner owner) {
        Log.i(TAG,"-->onPause");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop(LifecycleOwner owner) {
        Log.i(TAG,"-->onStop");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestory(LifecycleOwner owner) {
        Log.i(TAG,"-->onDestory");
    }
}
