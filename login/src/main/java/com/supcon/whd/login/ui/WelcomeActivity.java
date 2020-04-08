package com.supcon.whd.login.ui;


import android.os.Bundle;

import com.supcon.whd.common.constant.Constant;
import com.supcon.whd.login.R;
import com.thejoyrun.router.ActivityHelper;

import androidx.appcompat.app.AppCompatActivity;


public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ActivityHelper.builder(Constant.Router.LOGIN).start(this);
    }
}
