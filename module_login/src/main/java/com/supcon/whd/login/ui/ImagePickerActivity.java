package com.supcon.whd.login.ui;

import android.content.Intent;
import android.util.Log;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.donkingliang.imageselector.utils.ImageSelector;
import com.jakewharton.rxbinding2.view.RxView;
import com.leaf.library.StatusBarUtil;
import com.supcon.whd.common.base.ui.activity.BaseActivity;
import com.supcon.whd.common.constant.Constant;
import com.supcon.whd.login.R;
import com.supcon.whd.login.R2;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import androidx.annotation.Nullable;
import butterknife.BindView;

@Route(path = Constant.Router.IMAGEPICKER)
public class ImagePickerActivity extends BaseActivity {
    @BindView(R2.id.btnImagePicker)
    Button btnImagePicker;
    int REQUEST_CODE=100;
    @Override
    public int getLayoutId() {
        return R.layout.ac_image_picker;
    }

    private ArrayList<String> selectedList=new ArrayList<>();
    @Override
    public void onInit() {

    }

    @Override
    protected void initView() {
        super.initView();
        StatusBarUtil.setColor(this,getBaseColor(R.color.blue));
    }

    @Override
    protected void onListener() {
        super.onListener();
        RxView.clicks(btnImagePicker)
                .throttleFirst(2000, TimeUnit.MICROSECONDS)
                .subscribe(o -> {
                //限数量的多选(最多9张)
                    ImageSelector.builder()
                            .useCamera(false) // 设置是否使用拍照
                            .setSingle(false)  //设置是否单选
                            .setMaxSelectCount(9) // 图片的最大选择数量，小于等于0时，不限数量。
                            .setSelected(selectedList) // 把已选的图片传入默认选中。
                            .start(this, REQUEST_CODE); // 打开相册
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && data != null) {
            //获取选择器返回的数据
            ArrayList<String> images = data.getStringArrayListExtra(ImageSelector.SELECT_RESULT);
            Log.i("images",images.toString());
        }
    }
}
