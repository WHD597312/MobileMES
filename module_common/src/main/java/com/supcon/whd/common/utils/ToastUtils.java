package com.supcon.whd.common.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {
    public static void showShort(Context context,String str){
        Toast.makeText(context,str,Toast.LENGTH_SHORT).show();
    }
    public static void showShort(Context context,String str,int gravity){
        Toast toast=Toast.makeText(context, str,Toast.LENGTH_SHORT);
        toast.setGravity(gravity,0,0);
        toast.show();
    }
    public static void showLong(Context context,String str){
        Toast.makeText(context,str,Toast.LENGTH_LONG).show();
    }
    public static void showLong(Context context,String str,int gravity){
        Toast toast=Toast.makeText(context, str,Toast.LENGTH_SHORT);
        toast.setGravity(gravity,0,0);
        toast.show();
    }
}
