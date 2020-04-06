package com.supcon.whd.common.base.ui.activity;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.donkingliang.imageselector.utils.ImageSelector;
import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import com.supcon.whd.common.R;

public abstract class BaseScanActivity extends BaseActivity implements  View.OnClickListener{

    protected CaptureManager captureManager;
    protected DecoratedBarcodeView dbv;
    protected boolean isLightOn=false;

    protected RelativeLayout zxing_view_light;
    protected TextView zxing_view_flash_tv;
    protected ImageView zxing_flash_img;
    protected RelativeLayout zxing_view_photo;

    @Override
    public void onInit() {
        dbv=findViewById(R.id.dbv);
        dbv.setStatusText("对准二维码扫一扫");
        captureManager=new CaptureManager(this,dbv);
        Intent intent=getIntent();
        captureManager.initializeFromIntent(intent,intent!=null?intent.getExtras():null);
        captureManager.decode();
    }


    @Override
    public void initView() {
        zxing_view_light=findViewById(R.id.zxing_view_light);
        zxing_view_flash_tv=findViewById(R.id.zxing_view_flash_tv);
        zxing_flash_img=findViewById(R.id.zxing_flash_img);
        zxing_view_photo=findViewById(R.id.zxing_view_photo);
    }

    @Override
    protected void onListener() {
        super.onListener();
        zxing_view_light.setOnClickListener(this);
        zxing_view_photo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id=view.getId();
        if (id==R.id.zxing_view_light){
            if (isLightOn){
                isLightOn=false;
                dbv.setTorchOff();
                zxing_view_flash_tv.setText("关");
                zxing_flash_img.setImageResource(R.mipmap.ic_light_off);
            }else {
                isLightOn=true;
                dbv.setTorchOn();
                zxing_view_flash_tv.setText("开");
                zxing_flash_img.setImageResource(R.mipmap.ic_light_on);
            }
        }else if (id==R.id.zxing_view_photo){
            startGallery();
        }
    }
    protected int requestCode=200;
    private void startGallery(){
        ImageSelector.builder()
                .useCamera(false)//设置是否使用拍照
                .setSingle(true)//设置是否单选
                .setViewImage(false)//设置点击是否放大图片，默认true
                .start(this,requestCode);
    }



    @Override
    protected void onResume() {
        super.onResume();
        captureManager.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        captureManager.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        captureManager.onDestroy();
    }
}
