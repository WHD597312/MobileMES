package com.supcon.whd.login.ui;


import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.alibaba.android.arouter.launcher.ARouter;
import com.supcon.whd.common.constant.Constant;
import com.supcon.whd.login.R;

import androidx.appcompat.app.AppCompatActivity;


public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ARouter.getInstance().inject(this);
        ARouter.getInstance().build(Constant.Router.LOGIN).navigation();
    }
}
