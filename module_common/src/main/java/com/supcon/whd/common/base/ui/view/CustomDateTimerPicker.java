package com.supcon.whd.common.base.ui.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import android.view.View;

import android.widget.ImageView;
import com.supcon.whd.common.R;
import com.weigan.loopview.LoopView;
import com.weigan.loopview.OnItemSelectedListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import androidx.annotation.NonNull;


public class CustomDateTimerPicker extends Dialog {
    private Context context;
//    private View view;
    private List<String> years, months, hours, mins, seconds;
    private List<String> days, days2;
    private List<String> nonleapYearFeb, leapYearFeb;
    LoopView loopYear, loopMonth, loopDay, loopHour, loopMin, loopSecond;
    private int year, month, day, hour, min, second;
    private String type = "yyyy-MM-dd HH:mm:ss";
    private int dayIndex=0;

    public CustomDateTimerPicker(@NonNull Context context) {
        super(context, R.style.MyDialog);
        this.context=context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ly_date_timer2);
        loopYear =findViewById(R.id.loopYear);
        loopMonth = findViewById(R.id.loopMonth);
        loopDay = findViewById(R.id.loopDay);

        loopHour = findViewById(R.id.loopHour);

        loopMin = findViewById(R.id.loopMin);
        loopSecond = findViewById(R.id.loopSecond);
        setType(type);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initPopTimer();

    }

    public void initPopTimer() {
        if (this != null && this.isShowing())
            return;
        ImageView imgEnsure = findViewById(R.id.imgEnsure);
        ImageView imgCancel = findViewById(R.id.imgCancel);


        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        min = calendar.get(Calendar.MINUTE);
        second = calendar.get(Calendar.SECOND);
        if (needYear) {
            loopYear.setItems(years);
            loopYear.setItemsVisibleCount(7);
            loopYear.setCenterTextColor(Color.parseColor("#ff37d39e"));
            loopYear.setTextSize(18);
            loopYear.setLineSpacingMultiplier(2.5f);
            loopYear.setInitPosition(years.indexOf("" + year));
        }
        if (needMonth)
        if (needHour) {
            loopHour.setItems(hours);{
                loopMonth.setItems(months);
                loopMonth.setItemsVisibleCount(7);
                loopMonth.setCenterTextColor(Color.parseColor("#ff37d39e"));
                loopMonth.setTextSize(18);
                loopMonth.setLineSpacingMultiplier(2.5f);
                int monthPosition;
                if (month<10)
                    monthPosition=months.indexOf("0" + month);
                else
                    monthPosition=months.indexOf(month);
                loopMonth.setInitPosition(monthPosition);
            }
            loopHour.setItemsVisibleCount(7);
            loopHour.setCenterTextColor(Color.parseColor("#ff37d39e"));
            loopHour.setTextSize(18);
            loopHour.setLineSpacingMultiplier(2.5f);
            if (hour < 10) {
                loopHour.setInitPosition(hours.indexOf("0" + hour));
            } else {
                loopHour.setInitPosition(hours.indexOf("" + hour));
            }
        }

        if (needMin) {
            loopMin.setItems(mins);
            loopMin.setItemsVisibleCount(7);
            loopMin.setCenterTextColor(Color.parseColor("#ff37d39e"));
            loopMin.setTextSize(18);
            loopMin.setLineSpacingMultiplier(2.5f);
            if (min < 10) {
                loopMin.setInitPosition(mins.indexOf("0" + min));
            } else {
                loopMin.setInitPosition(mins.indexOf("" + min));
            }
        }

        if (needSecond) {
            loopSecond.setItems(seconds);
            loopSecond.setItemsVisibleCount(7);
            loopSecond.setCenterTextColor(Color.parseColor("#ff37d39e"));
            loopSecond.setTextSize(18);
            loopSecond.setLineSpacingMultiplier(2.5f);
        }

        if ("yyyy-MM-dd HH:mm".equals(type)) {
            loopSecond.setVisibility(View.GONE);
        } else if ("HH:mm:ss".equals(type)) {
            loopYear.setVisibility(View.GONE);
            loopMonth.setVisibility(View.GONE);
            loopDay.setVisibility(View.GONE);
        } else if ("HH:mm".equals(type)) {
            loopYear.setVisibility(View.GONE);
            loopMonth.setVisibility(View.GONE);
            loopDay.setVisibility(View.GONE);
            loopSecond.setVisibility(View.GONE);
        }

        if (needDay) {
            loopDay.setItemsVisibleCount(7);
            loopDay.setCenterTextColor(Color.parseColor("#ff37d39e"));
            loopDay.setTextSize(18);
            loopDay.setLineSpacingMultiplier(2.5f);
            if (month != 2) {
                if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
                    loopDay.setItems(days);
                    if (day < 10) {
                        loopDay.setInitPosition(days.indexOf("0" + day));
                    } else {
                        loopDay.setInitPosition(days.indexOf("" + day));
                    }
                } else {
                    loopDay.setItems(days2);
                    if (day < 10) {
                        loopDay.setInitPosition(days2.indexOf("0" + day));
                    } else {
                        loopDay.setInitPosition(days2.indexOf("" + day));
                    }
                }
            } else {
                if (isLeapYear(year)) {
                    loopDay.setItems(leapYearFeb);
                    if (day < 10) {
                        loopDay.setInitPosition(leapYearFeb.indexOf("0" + day));
                    } else {
                        loopDay.setInitPosition(leapYearFeb.indexOf("" + day));
                    }
                } else {
                    loopDay.setItems(nonleapYearFeb);
                    if (day < 10) {
                        loopDay.setInitPosition(nonleapYearFeb.indexOf("0" + day));
                    } else {
                        loopDay.setInitPosition(nonleapYearFeb.indexOf("" + day));
                    }
                }
            }
            dayIndex=day-1;
        }

        loopYear.setListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                year = Integer.parseInt(years.get(index));
                if (needDay) {
                    if (month != 2) {
                        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
                            loopDay.setItems(days);
                            loopDay.setCurrentPosition(dayIndex);
                            day = Integer.parseInt(days.get(dayIndex));
                        } else {
                            loopDay.setItems(days2);
                            if (loopDay.getSelectedItem() == 30) {
                                loopDay.setCurrentPosition(29);
                                day = Integer.parseInt(days2.get(29));
                            } else {
                                day = Integer.parseInt(days2.get(dayIndex));
                            }
                        }
                    } else {
                        if (isLeapYear(year)) {
                            loopDay.setItems(leapYearFeb);
                            if (dayIndex > 28) {
                                loopDay.setCurrentPosition(28);
                                day = Integer.parseInt(leapYearFeb.get(28));
                            } else {
                                day = Integer.parseInt(leapYearFeb.get(dayIndex));
                            }
                        } else {
                            loopDay.setItems(nonleapYearFeb);
                            if (dayIndex > 27) {
                                loopDay.setCurrentPosition(27);
                                day = Integer.parseInt(nonleapYearFeb.get(27));
                            } else {
                                day = Integer.parseInt(nonleapYearFeb.get(dayIndex));
                            }
                        }
                    }
                    dayIndex=day-1;
                }
            }
        });


        loopMonth.setListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                month = Integer.parseInt(months.get(index));
                if (month != 2) {
                    if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
                        loopDay.setItems(days);
                        day = Integer.parseInt(days.get(dayIndex));
                        loopDay.setCurrentPosition(dayIndex);
                    } else {
                        loopDay.setItems(days2);
                        if (dayIndex==30) {
                            loopDay.setCurrentPosition(29);
                            day = Integer.parseInt(days2.get(29));
                        } else {
                            day = Integer.parseInt(days2.get(dayIndex));
                        }
                    }
                } else {
                    if (isLeapYear(year)) {
                        loopDay.setItems(leapYearFeb);
                        if (dayIndex > 28) {
                            loopDay.setCurrentPosition(28);
                            day = Integer.parseInt(leapYearFeb.get(28));
                        } else {
                            day = Integer.parseInt(leapYearFeb.get(dayIndex));
                        }
                    } else {
                        loopDay.setItems(nonleapYearFeb);
                        if (dayIndex > 27) {
                            loopDay.setCurrentPosition(27);
                            day = Integer.parseInt(nonleapYearFeb.get(27));
                        } else {
                            day = Integer.parseInt(nonleapYearFeb.get(dayIndex));
                        }
                    }
                }
                dayIndex=day-1;
            }
        });


        loopDay.setListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                Log.i("dayIndex","dayIndex="+index);
                dayIndex=index;
                if (month == 2) {
                    if (isLeapYear(year)) {
                        day = Integer.parseInt(leapYearFeb.get(index));
                    } else {
                        day = Integer.parseInt(nonleapYearFeb.get(index));
                    }
                } else {
                    if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
                        loopDay.setItems(days);
                        day = Integer.parseInt(days.get(index));
                    } else {
                        loopDay.setItems(days2);
                        day = Integer.parseInt(days2.get(index));
                    }
                }
            }
        });


        loopHour.setListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                hour = Integer.parseInt(hours.get(index));
            }
        });


        loopMin.setListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                min = Integer.parseInt(mins.get(index));
            }
        });


        loopSecond.setListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                second = Integer.parseInt(seconds.get(index));
            }
        });


        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = view.getId();
                if (id == R.id.imgCancel) {
                    dismiss();
                } else {
                    Log.i("selectedItem", "selectedItemYear=" + year + ",selectedItemMonth=" + month +
                            ",selectedItemDay=" + day + ",selectedItemHour=" + hour + ",selectedItemMin=" + min
                            + ",selectedItemSecond=" + second);

                    if (onDateTimerPicker != null) {
                        onDateTimerPicker.getDateTimePicker(year, month, day, hour, min, second);
                        dismiss();
                    }
                }
            }
        };
        imgCancel.setOnClickListener(onClickListener);
        imgEnsure.setOnClickListener(onClickListener);
//        this.showAtLocation(parent, Gravity.BOTTOM | Gravity.CENTER, 0, 0);
    }


    private boolean needYear = true, needMonth = true, needDay = true, needHour = true, needMin = true, needSecond = true;
    public CustomDateTimerPicker setType(String type) {
        this.type = type;
        if ("yyyy-MM-dd HH:mm:ss".equals(type)) {
            getYears();
            getMonths();
            getLeapYearFeb();
            getNonleapYearFeb();
            getDays();
            getDays2();
            getHours();
            getMins();
            getSeconds();
        }
        if ("yyyy-MM-dd HH:mm".equals(type)) {
            getYears();
            getMonths();
            getLeapYearFeb();
            getNonleapYearFeb();
            getDays();
            getDays2();
            getHours();
            getMins();
            needSecond = false;
        } else if ("HH:mm:ss".equals(type)) {
            needYear = false;
            needHour = false;
            needDay = false;
            getHours();
            getMins();
            getSeconds();
        } else if ("HH:mm".equals(type)) {
            needYear = false;
            needHour = false;
            needDay = false;
            needSecond = false;
            getHours();
            getMins();

        }
        return this;
    }

    public String getDateTime() {
        return year + "-" + month + "-" + day + " " + hour + ":" + min + ":" + second;
    }

    public String getDate() {
        return year + "-" + month + "-" + day;
    }

    public String getDateTimeHourMin() {
        return year + "-" + month + "-" + day + " " + hour + ":" + min + ":";
    }

    public String getHourMin() {
        return hour + ":" + min + ":";
    }

    public String getHourMinSecond() {
        return hour + ":" + min + ":" + second;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public int getMin() {
        return min;
    }

    public int getSecond() {
        return second;
    }

    private boolean isLeapYear(int year) {
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
            return true;
        }
        return false;
    }

    private List<String> getYears() {
        if (years != null && !years.isEmpty())
            return years;
        years = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        for (int i = year - 50; i <= year + 50; i++) {
            years.add(i + "");
        }
        return years;
    }

    private List<String> getMonths() {
        if (months != null && !months.isEmpty())
            return months;
        months = new ArrayList<>();
        for (int i = 1; i < 13; i++) {
            if (i < 10) {
                months.add("0" + i);
            } else {
                months.add(i + "");
            }
        }
        return months;
    }

    private List<String> getNonleapYearFeb() {
        nonleapYearFeb = new ArrayList<>();
        for (int i = 1; i < 29; i++) {
            if (i < 10) {
                nonleapYearFeb.add("0" + i);
            } else {
                nonleapYearFeb.add(i + "");
            }
        }
        return nonleapYearFeb;
    }

    private List<String> getLeapYearFeb() {
        leapYearFeb = new ArrayList<>();
        for (int i = 1; i < 30; i++) {
            if (i < 10) {
                leapYearFeb.add("0" + i);
            } else {
                leapYearFeb.add(i + "");
            }
        }
        return leapYearFeb;
    }

    private List<String> getDays() {
        if (days != null && !days.isEmpty())
            return days;
        days = new ArrayList<>();
        for (int i = 1; i < 32; i++) {
            if (i < 10) {
                days.add("0" + i);
            } else {
                days.add(i + "");
            }
        }
        return days;
    }

    private List<String> getDays2() {
        if (days2 != null && !days2.isEmpty())
            return days2;
        days2 = new ArrayList<>();
        for (int i = 1; i < 31; i++) {
            if (i < 10) {
                days2.add("0" + i);
            } else {
                days2.add(i + "");
            }
        }
        return days2;
    }

    private List<String> getHours() {
        if (hours != null && !hours.isEmpty())
            return hours;
        hours = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            if (i < 10) {
                hours.add("0" + i);
            } else {
                hours.add(i + "");
            }
        }
        return hours;
    }

    private List<String> getMins() {
        if (mins != null && !mins.isEmpty())
            return mins;
        mins = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            if (i < 10) {
                mins.add("0" + i);
            } else {
                mins.add(i + "");
            }
        }
        return mins;
    }

    private List<String> getSeconds() {
        if (seconds != null && seconds.isEmpty()) {
            return seconds;
        }
        seconds = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            if (i < 10) {
                seconds.add("0" + i);
            } else {
                seconds.add(i + "");
            }
        }
        return seconds;
    }

    public interface Type{
        String YEAR_MONTH_DAY_HOUR_MIN_SCCOND="yyyy-MM-dd HH:mm:ss";
        String YEAR_MONTH_DAY_HOUR_MIN="yyyy-MM-dd HH:mm";
        String HOUR_MIN_SECOND="HH:mm:ss";
        String HOUR_MIN="HH:mm";
    }
    public interface OnDateTimerPicker {
        void getDateTimePicker(int year, int month, int day, int hour, int min, int second);
    }

    private OnDateTimerPicker onDateTimerPicker;

    public void setOnDateTimerPicker(OnDateTimerPicker onDateTimerPicker) {
        this.onDateTimerPicker = onDateTimerPicker;
    }
}
