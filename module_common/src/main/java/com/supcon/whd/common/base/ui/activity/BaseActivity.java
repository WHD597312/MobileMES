package com.supcon.whd.common.base.ui.activity;


import android.content.Context;
import android.os.Bundle;

import android.text.TextUtils;

import android.view.View;
import android.view.WindowManager;

import com.alibaba.android.arouter.launcher.ARouter;
import com.supcon.whd.common.base.ui.view.CustomLoad;
import com.supcon.whd.common.swipelayout.SwipeBackActivityBase;
import com.supcon.whd.common.swipelayout.SwipeBackActivityHelper;
import com.supcon.whd.common.swipelayout.SwipeBackLayout;
import com.supcon.whd.common.swipelayout.Utils;
import com.supcon.whd.common.utils.DensityUtils;
import com.supcon.whd.common.utils.ScreenUtil;

import java.util.concurrent.TimeUnit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;


public abstract class BaseActivity  extends AppCompatActivity implements SwipeBackActivityBase {

    protected View view;
    protected Context context;
    Unbinder unbinder;
    Disposable mDisposable;
    private SwipeBackActivityHelper mHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);
        mHelper = new SwipeBackActivityHelper(this);
        mHelper.onActivityCreate();
        this.context=this;
        DensityUtils.setOrientation(this,getOrientation());
        setContentView(getLayoutId());
        unbinder= ButterKnife.bind(this);
        onInit();
        initView();
        onListener();
       // new ViewModelProvider(this,)
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mHelper.onPostCreate();
    }


    @Override
    public SwipeBackLayout getSwipeBackLayout() {
        return mHelper.getSwipeBackLayout();
    }

    @Override
    public void setSwipeBackEnable(boolean enable) {
        getSwipeBackLayout().setEnableGesture(enable);
    }

    @Override
    public void scrollToFinishActivity() {
        Utils.convertActivityToTranslucent(this);
        getSwipeBackLayout().scrollToFinishActivity();
    }

    protected String getOrientation(){
        return ScreenUtil.WIDTH;
    }


    protected void onListener(){

    }

    public int getBaseColor(int color){
        return getResources().getColor(color);
    }
    protected int getDimen(int dimen){
        return (int) getResources().getDimension(dimen);
    }
    CustomLoad dialogLoad;
    protected void onLoading(String load) {

        if (dialogLoad != null && dialogLoad.isShowing()) {
            return;
        }
        dialogLoad = new CustomLoad(this);
        dialogLoad.setCanceledOnTouchOutside(false);
        if (!TextUtils.isEmpty(load)){
            dialogLoad.setLoad(load);
        }else {
            dialogLoad.setLoad("正在加载...");
        }
        backgroundAlpha(0.6f);
        dialogLoad.setOnCancelListener(dialogInterface->{
            backgroundAlpha(1.0f);
        });
        if (dialogLoad!=null){
            dialogLoad.show();
        }
    }
    protected void back(){
        finish();
    }
    //设置蒙版
    protected void backgroundAlpha(float f) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = f;
        getWindow().setAttributes(lp);
    }
    protected void onLoadSuccess(String load){
        if (dialogLoad!=null && dialogLoad.isShowing()){
            dialogLoad.onLoadSuccess(load);
            backgroundAlpha(1.0f);

            mDisposable=Flowable.timer(1500, TimeUnit.MILLISECONDS)
                    .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe(s->{
                dialogLoad.dismiss();
                mDisposable.dispose();
            });
        }
    }


    public void onLoadFail(String load){
        if (dialogLoad!=null && dialogLoad.isShowing()){
            dialogLoad.onLoadFail(load);
            backgroundAlpha(1.0f);

            mDisposable=Flowable.timer(1500, TimeUnit.MILLISECONDS)
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .subscribe(s->{
                        dialogLoad.dismiss();
                        mDisposable.dispose();
                    });
        }
    }

    public abstract int getLayoutId();
    public  void onInit(){
        ARouter.getInstance().inject(this);
    }
    protected  void initView(){

    }


    @Override
    public void onBackPressed() {
        back();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
