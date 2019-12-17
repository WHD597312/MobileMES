package com.supcon.whd.common.base.ui.activity;


import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import android.view.View;
import android.view.WindowManager;

import com.supcon.whd.common.base.ui.view.CustomLoad;

import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;


public abstract class BaseActivity  extends AppCompatActivity {

    protected View view;

    Unbinder unbinder;
    Disposable mDisposable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        unbinder= ButterKnife.bind(this);
        onInit();
        initView();
        onListener();
    }
    protected void onListener(){

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
    private void backgroundAlpha(float f) {
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
    public abstract void onInit();
    public abstract void initView();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
