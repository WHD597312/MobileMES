package com.supcon.whd.login.ui;


import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.supcon.whd.common.annotation.Presenter;
import com.supcon.whd.common.base.ui.activity.BasePresenterActivity;
import com.supcon.whd.common.constant.Constant;
import com.supcon.whd.common.utils.StatusBarUtils;
import com.supcon.whd.login.R;
import com.supcon.whd.login.R2;
import com.supcon.whd.login.model.api.LoginAPI;
import com.supcon.whd.login.model.bean.LoginEntity;
import com.supcon.whd.login.model.contract.ContractLogin;
import com.supcon.whd.login.presenter.LoginPresenter;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;


@Route(path = Constant.Router.LOGIN)
@Presenter(value = {
        LoginPresenter.class
})
public class LoginActivity extends BasePresenterActivity implements ContractLogin.View {

    @BindView(R2.id.loginName)
    EditText loginName;
    @BindView(R2.id.loginPswd)
    EditText loginPswd;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }


    @Override
    public void initView() {
        StatusBarUtils.setWindowStatusBarColor(this,R.color.colorAccent);
    }

    @OnClick({R2.id.loginBtn})
    public void onClick(View view){
        int id=view.getId();
        if (id==R.id.loginBtn){
            String username=loginName.getText().toString().trim();
            String password=loginPswd.getText().toString().trim();
            onLoading("正在登录...");
            Flowable.timer(300, TimeUnit.MILLISECONDS)
                    .subscribe(s->{
                        presenterRouter.create(LoginAPI.class).login(username,password);
                    });
        }
    }



    @Override
    public void doLoginSuccess(LoginEntity loginEntity) {
        Log.i("LoginEntity",loginEntity.toString());
//        Toast.makeText(this,loginEntity.toString(),Toast.LENGTH_SHORT).show();
        onLoadSuccess("登录成功!");
        Flowable.timer(300,TimeUnit.MILLISECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(o->{
                    ARouter.getInstance()
                            .build(Constant.Router.MAIN)
                            .withString("username","whd")
                            .withString("password","123")
                            .navigation();
                });
    }

    @Override
    public void doLoginFailed(String errMsg) {
        Log.i("LoginEntity",errMsg);
//        Toast.makeText(this,errMsg,Toast.LENGTH_SHORT).show();
        onLoadFail(errMsg);
    }

//    @OnClick({R2.id.loginBtn})
//    public void onClick(View view){
//       int id=view.getId();
//       if (id==R.id.loginBtn){
//           String name=loginName.getText().toString();
//           String pswd=loginPswd.getText().toString();
//           Toast.makeText(this,name+"--"+pswd,Toast.LENGTH_SHORT).show();
//       }
//    }
}
