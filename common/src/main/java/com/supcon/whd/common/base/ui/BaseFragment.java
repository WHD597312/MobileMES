package com.supcon.whd.common.base.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.supcon.whd.common.base.ui.view.CustomLoad;
import com.supcon.whd.common.base.ui.view.listener.CallBack;

import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public abstract class BaseFragment extends Fragment {

    Unbinder unbinder;
    Disposable mDisposable;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=View.inflate(getActivity(),getLayoutId(),null);
        unbinder=ButterKnife.bind(this,view);
        initView();
        initData();
        return view;
    }

    protected abstract int getLayoutId();
    protected  void initView(){}
    protected void initData(){}

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
    protected int getDimen(int dimen){
        return (int) getResources().getDimension(dimen);
    }
    CustomLoad dialogLoad;
    protected void onLoading(String load) {
        if (dialogLoad != null && dialogLoad.isShowing()) {
            return;
        }
        dialogLoad = new CustomLoad(getActivity());
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

    //设置蒙版
    private void backgroundAlpha(float f) {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = f;
        getActivity().getWindow().setAttributes(lp);
    }
    protected void onLoadSuccess(String load){
        if (dialogLoad!=null && dialogLoad.isShowing()){
            dialogLoad.onLoadSuccess(load);
            backgroundAlpha(1.0f);

            mDisposable= Flowable.timer(1500, TimeUnit.MILLISECONDS)
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .subscribe(s->{
                        dialogLoad.dismiss();
                        mDisposable.dispose();
                    });
        }
    }


    protected void onLoadFail(String load){
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
}
