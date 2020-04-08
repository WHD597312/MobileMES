package com.supcon.whd.login.ui;



import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.supcon.whd.common.base.ui.activity.BaseActivity;
import com.supcon.whd.common.base.ui.view.listener.OnItemViewClickListener;
import com.supcon.whd.common.constant.Constant;
import com.supcon.whd.common.utils.StatusBarUtils;
import com.supcon.whd.login.R;
import com.supcon.whd.login.R2;
import com.supcon.whd.login.model.bean.WorkEntity;
import com.supcon.whd.login.ui.adapter.WorkAdapter;
import com.supcon.whd.login.ui.fragment.MineFragment;
import com.thejoyrun.router.ActivityHelper;
import com.thejoyrun.router.RouterActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

@RouterActivity(value = Constant.Router.MAIN)
public class MainActivity extends BaseActivity {

    @BindView(R2.id.contentView)
    RecyclerView contentView;
    @BindView(R2.id.drawLayout)
    DrawerLayout drawLayout;
    @BindView(R2.id.imgMenu)
    ImageView imgMenu;
    @BindView(R2.id.left)
    LinearLayout left;
    @BindView(R2.id.right)
    LinearLayout right;
    WorkAdapter adapter;
    List<WorkEntity> list=new ArrayList<>();
    MineFragment mineFragment;
    FragmentManager fragmentManager;


    @Override
    public int getLayoutId() {
        return R.layout.ac_main;
    }

    @Override
    public void onInit() {
        list.add(new WorkEntity(R.drawable.ic_list,"列表",Constant.Router.STUDENTLIST));
        list.add(new WorkEntity(R.drawable.ic_qrcode,"二维码",Constant.Router.QRSCAN));
        list.add(new WorkEntity(R.drawable.ic_custom,"自定义",Constant.Router.CUSTOM));
    }

    DisplayMetrics outMetrics;
    @Override
    public void initView() {
        StatusBarUtils.setWindowStatusBarColor(this,R.color.colorAccent);
        setSwipeBackEnable(false);
        adapter=new WorkAdapter(this,list);
        contentView.setLayoutManager(new GridLayoutManager(this,3));
        contentView.setAdapter(adapter);

        mineFragment=new MineFragment();
        fragmentManager=getSupportFragmentManager();
        outMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container,mineFragment).commit();
        drawLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View view, float v) {

                //设置右面的布局位置  根据左面菜单的right作为右面布局的left   左面的right+屏幕的宽度（或者right的宽度这里是相等的）为右面布局的right
                right.layout(left.getRight(), 0, left.getRight() + outMetrics.widthPixels, outMetrics.heightPixels);
            }

            @Override
            public void onDrawerOpened(@NonNull View view) {

            }

            @Override
            public void onDrawerClosed(@NonNull View view) {

            }

            @Override
            public void onDrawerStateChanged(int i) {

            }
        });
    }

    @Override
    protected void onListener() {
        super.onListener();
        adapter.setOnItemViewClickListener(new OnItemViewClickListener() {
            @Override
            public void onItemViewClick(View view, int position) {
                WorkEntity workEntity=adapter.getItemEntity(position);
                if (Constant.Router.STUDENTLIST.equals(workEntity.router)){
                    ActivityHelper.builder(Constant.Router.STUDENTLIST).start(MainActivity.this);
                }else if (Constant.Router.CUSTOM.equals(workEntity.router)){
                    ActivityHelper.builder(Constant.Router.CUSTOM).start(MainActivity.this);
                }else if (Constant.Router.QRSCAN.equals(workEntity.router)) {
                    new IntentIntegrator(MainActivity.this)
                            .setOrientationLocked(true)
                            .setCaptureActivity(QRScanActivity.class)
                            .setRequestCode(100)
                            .initiateScan();
                }
            }
        });
    }
    @OnClick({R2.id.imgMenu})
    public void onClick(View view){
        int id=view.getId();
        if (id==R.id.imgMenu){
            drawLayout.openDrawer(Gravity.LEFT);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data!=null && requestCode==100){
            String scanResult=data.getStringExtra("SCAN_RESULT");
            Toast.makeText(this,scanResult,Toast.LENGTH_LONG).show();
        }
    }
}
