package com.supcon.whd.login.listener;



import androidx.lifecycle.LifecycleOwner;

public interface DefaultLifecycleObserver {
    void onCreate(LifecycleOwner owner);
    void start(LifecycleOwner owner);
    void onPause(LifecycleOwner owner);
    void onStop(LifecycleOwner owner);
    void onDestory(LifecycleOwner owner);
}
