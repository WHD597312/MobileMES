package com.supcon.whd.login.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jakewharton.rxbinding2.view.RxView;
import com.suke.widget.SwitchButton;
import com.supcon.whd.common.base.ui.activity.BaseActivity;
import com.supcon.whd.common.base.ui.view.CustomDatePicker;
import com.supcon.whd.common.base.ui.view.CustomDateTimerPicker;
import com.supcon.whd.common.base.ui.view.DateTimerPicker;
import com.supcon.whd.common.constant.Constant;
import com.supcon.whd.common.utils.DensityUtils;
import com.supcon.whd.common.utils.ScreenUtil;
import com.supcon.whd.login.R;
import com.supcon.whd.login.R2;


import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = Constant.Router.CUSTOM)
public class CustomViewActivity extends BaseActivity {
    @BindView(R2.id.btnDatePicker)
    Button btnDatePicker;
    @BindView(R2.id.switch_button)
    SwitchButton switch_button;


    @Override
    protected String getOrientation() {
        return ScreenUtil.HEIGHT;
    }

    @Override
    public int getLayoutId() {
        return R.layout.ac_custom_view;
    }

    @Override
    public void onInit() {

    }

    @Override
    protected void onListener() {
        super.onListener();
        switch_button.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                Log.i("CheckedChangeListener","-->"+isChecked);
            }
        });

    }

    @OnClick({R2.id.btnDatePicker})
    public void onClick(View view){
        int id=view.getId();
        if (id==R.id.btnDatePicker){
//            View contentView=View.inflate(this, R.layout.pop_date_timer,null);
//            DateTimerPicker dateTimerPicker=new DateTimerPicker(this,contentView);
//            dateTimerPicker.initPopTimer(btnDatePicker,R.color.white);
            CustomDateTimerPicker customDateTimerPicker=new CustomDateTimerPicker(this);
//            customDateTimerPicker.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            customDateTimerPicker.setType(CustomDateTimerPicker.Type.YEAR_MONTH_DAY_HOUR_MIN_SCCOND);
            customDateTimerPicker.setCanceledOnTouchOutside(true);
            customDateTimerPicker.getWindow().setGravity(Gravity.BOTTOM);
            customDateTimerPicker.setOnDateTimerPicker(new CustomDateTimerPicker.OnDateTimerPicker() {
                        @Override
                        public void getDateTimePicker(int year, int month, int day, int hour, int min, int second) {
                        }
                    });
            customDateTimerPicker.show();

        }
    }
    @Override
    public void initView() {
        setSwipeBackEnable(false);
    }
}
