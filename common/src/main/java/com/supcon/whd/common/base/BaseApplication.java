package com.supcon.whd.common.base;

import android.app.Application;
import android.content.Context;

import com.thejoyrun.router.Router;

public class BaseApplication  extends Application {
    public static String baseUrl="http://192.168.90.147:8080/";

    private static Context mContext;
    public static Context getContext(){
        return mContext;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mContext=this;
        Router.init("MobileMES");
    }
}
