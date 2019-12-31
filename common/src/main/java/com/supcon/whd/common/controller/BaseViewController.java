package com.supcon.whd.common.controller;

import android.view.View;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class BaseViewController extends BasePresenterController {


    Unbinder unbinder;
    public BaseViewController(View view){
        unbinder=ButterKnife.bind(this,view);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
