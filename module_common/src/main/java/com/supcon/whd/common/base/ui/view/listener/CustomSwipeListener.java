package com.supcon.whd.common.base.ui.view.listener;

import android.util.Log;

import com.daimajia.swipe.SwipeLayout;

public class CustomSwipeListener implements SwipeLayout.SwipeListener {

    private boolean openSwipe=false;

    public boolean isOpenSwipe() {
        return openSwipe;
    }

    @Override
    public void onStartOpen(SwipeLayout layout) {
        Log.i("CustomSwipeListener","onStartOpen");
    }

    @Override
    public void onOpen(SwipeLayout layout) {
        Log.i("CustomSwipeListener","onOpen");
        openSwipe=true;
    }

    @Override
    public void onStartClose(SwipeLayout layout) {
        Log.i("CustomSwipeListener","onStartClose");
    }

    @Override
    public void onClose(SwipeLayout layout) {
        Log.i("CustomSwipeListener","onClose");
        openSwipe=false;
    }

    @Override
    public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {

    }

    @Override
    public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {

    }

}
