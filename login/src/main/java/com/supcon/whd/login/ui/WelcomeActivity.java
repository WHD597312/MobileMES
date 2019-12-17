package com.supcon.whd.login.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.supcon.whd.common.constant.Constant;
import com.supcon.whd.login.R;
import com.thejoyrun.router.ActivityHelper;



public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ActivityHelper.builder(Constant.Router.LOGIN).start(this);
    }
}
