package com.supcon.whd.login.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.supcon.whd.common.base.ui.activity.BaseActivity;
import com.supcon.whd.common.base.ui.view.CustomDatePicker;
import com.supcon.whd.common.base.ui.view.CustomDateTimerPicker;
import com.supcon.whd.common.base.ui.view.DateTimerPicker;
import com.supcon.whd.common.constant.Constant;
import com.supcon.whd.common.utils.DensityUtils;
import com.supcon.whd.common.utils.ScreenUtil;
import com.supcon.whd.login.R;
import com.supcon.whd.login.R2;
import com.thejoyrun.router.RouterActivity;

import butterknife.BindView;
import butterknife.OnClick;

@RouterActivity(Constant.Router.CUSTOM)
public class CustomViewActivity extends BaseActivity {
    @BindView(R2.id.btnDatePicker)
    Button btnDatePicker;


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

    @OnClick({R2.id.btnDatePicker})
    public void onClick(View view){
        int id=view.getId();
        if (id==R.id.btnDatePicker){
//            View contentView=View.inflate(this, R.layout.pop_date_timer,null);
//            DateTimerPicker dateTimerPicker=new DateTimerPicker(this,contentView);
//            dateTimerPicker.initPopTimer(btnDatePicker,R.color.white);
            CustomDateTimerPicker customDateTimerPicker=new CustomDateTimerPicker(this);
            customDateTimerPicker
                    .setType(CustomDateTimerPicker.Type.YEAR_MONTH_DAY_HOUR_MIN_SCCOND)
                    .initPopTimer(btnDatePicker)
                    .setOnDateTimerPicker(new CustomDateTimerPicker.OnDateTimerPicker() {
                        @Override
                        public void getDateTimePicker(int year, int month, int day, int hour, int min, int second) {
                            backgroundAlpha(1.0f);
                        }
                    });
            customDateTimerPicker.setOnDismissListener(()->{
                backgroundAlpha(1.0f);
            });
            backgroundAlpha(0.6f);

        }
    }
    @Override
    public void initView() {

    }
}
