package com.supcon.whd.common.base.ui.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.PopupWindow;

import com.supcon.whd.common.R;

import java.lang.reflect.Field;
import java.util.Calendar;

import androidx.core.content.ContextCompat;

public  class DateTimerPicker extends PopupWindow {

    private Context context;
    private boolean onlyTimer;
    private boolean onlyDate;
    private int year,month,day,hour,min;
    private CustomDatePicker datePicker;
    private NumberPicker timerHour;
    private NumberPicker timerMin;
    private View view;


    public DateTimerPicker(Context context, View contentView){
        super(contentView,ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT,true);
        this.view=contentView;
        this.context=context;
    }



    public void initPopTimer(View parent, int color){
        if (this!=null && this.isShowing())
            return;

        this.setFocusable(false);
        this.setOutsideTouchable(true);
        this.update();

        datePicker=view.findViewById(R.id.datePicker);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH));
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        min = calendar.get(Calendar.MINUTE);
        datePicker.setDividerColor(Color.WHITE);
        datePicker.setPickerMargin(1);

        calendar.set(Calendar.YEAR, year + 50);
        datePicker.setMaxDate(calendar.getTimeInMillis());


        timerHour=view.findViewById(R.id.timerHour);
        timerHour.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        timerHour.setMaxValue(23);
        timerHour.setMinValue(0);
        timerHour.setValue(hour);
        setNumberPickerDivider(timerHour,color);

        timerMin=view.findViewById(R.id.timerMin);
        timerMin.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        timerMin.setMaxValue(59);
        timerMin.setMinValue(0);
        timerMin.setValue(min);
        setNumberPickerDivider(timerMin,color);
        this.showAtLocation(parent, Gravity.BOTTOM|Gravity.CENTER,0,0);
    }


    private void setNumberPickerDivider(NumberPicker numberPicker,int color) {
        final int count = numberPicker.getChildCount();
        for (int i = 0; i < count; i++) {
            try {
                Field dividerField = numberPicker.getClass().getDeclaredField("mSelectionDivider");
                dividerField.setAccessible(true);
                ColorDrawable colorDrawable = new ColorDrawable(
                        ContextCompat.getColor(context, color));
                dividerField.set(numberPicker, colorDrawable);
                numberPicker.invalidate();
            } catch (NoSuchFieldException | IllegalAccessException | IllegalArgumentException e) {
                Log.w("setNumberPickerTxtClr", e);
            }
        }
    }

    public void setOnlyTimer(boolean onlyTimer) {
        this.onlyTimer = onlyTimer;
    }

    public void setOnlyDate(boolean onlyDate) {
        this.onlyDate = onlyDate;
    }
}
