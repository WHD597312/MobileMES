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

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class CustomDialog extends Dialog {


    Context context;
    Unbinder unbinder;
    public CustomDialog(@NonNull Context context) {
        super(context, R.style.MyDialog);
        this.context=context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayout());
        unbinder=ButterKnife.bind(this);
    }
    public abstract int getLayout();

    @Override
    protected void onStop() {
        super.onStop();
        unbinder.unbind();
    }
}
