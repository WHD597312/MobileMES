package com.supcon.whd.login.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.util.Log;

import com.donkingliang.imageselector.utils.ImageSelector;
import com.supcon.whd.common.base.ui.activity.BaseScanActivity;
import com.supcon.whd.common.utils.ZXingUtils;
import com.supcon.whd.login.R;

import java.util.ArrayList;

public class QRScanActivity extends BaseScanActivity {

    @Override
    public int getLayoutId() {
        return R.layout.ac_qrscan;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==QRScanActivity.this.requestCode && data!=null){
            ArrayList<String> images= data.getStringArrayListExtra(ImageSelector.SELECT_RESULT);
            if (images!=null && !images.isEmpty()){
                String path=images.get(0);
                Bitmap bitmap= BitmapFactory.decodeFile(path);
                String result= ZXingUtils.parseQRcode(bitmap);
                Log.i("ParseImage","-->"+result);
            }
        }
    }
}
