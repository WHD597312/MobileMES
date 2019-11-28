package com.supcon.whd.common.base;

import android.app.Application;
import android.content.Context;

import com.thejoyrun.router.Router;

public class BaseApplication  extends Application {
    public static String baseUrl="http://47.111.101.184:8095/qjjc/";

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
