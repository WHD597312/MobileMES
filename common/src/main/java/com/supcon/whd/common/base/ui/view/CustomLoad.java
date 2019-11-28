package com.supcon.whd.common.base.ui.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.supcon.whd.common.R;
import com.wang.avi.AVLoadingIndicatorView;




public class CustomLoad extends Dialog {

    private String load;
    Context context;
    AVLoadingIndicatorView avi;
    ImageView imgLoadRestult;
    TextView tv_load;

    public CustomLoad(@NonNull Context context) {
        super(context, R.style.MyDialog);
        this.context=context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loading);
        avi=findViewById(R.id.avi);
        imgLoadRestult=findViewById(R.id.img_load);
        tv_load=findViewById(R.id.tv_load);
    }

    //    AnimationDrawable animationDrawable;
    @Override
    protected void onStart() {
        super.onStart();
        startAnim();
//        img_load.setImageResource(R.drawable.load);
//        animationDrawable = (AnimationDrawable) img_load.getDrawable();
//        animationDrawable.start();
        if (!TextUtils.isEmpty(load)){
            tv_load.setText(load);
        }
    }



    @Override
    protected void onStop() {
        super.onStop();
        stopAnim();
    }

    public void setLoad(String load) {
        this.load = load;
    }

    public String getLoad() {
        return load;
    }
    private boolean loading=false;
     void startAnim(){
        avi.show();
        loading=true;
        // or avi.smoothToShow();
    }

     void stopAnim(){
        avi.hide();
    }
    public void onLoadSuccess(String load){
        if (loading){
            loading=false;
            stopAnim();
        }
        if (TextUtils.isEmpty(load))
            load="加载成功！";

        tv_load.setText(load);
        imgLoadRestult.setVisibility(View.VISIBLE);
        imgLoadRestult.setImageResource(R.drawable.ic_load_success);
    }
    public void onLoadFail(String load){
        if (loading){
            loading=false;
            stopAnim();
        }
        if (TextUtils.isEmpty(load))
            load="加载失败！";
        tv_load.setText(load);
        imgLoadRestult.setVisibility(View.VISIBLE);
        imgLoadRestult.setImageResource(R.drawable.ic_load_fail);
    }
}
