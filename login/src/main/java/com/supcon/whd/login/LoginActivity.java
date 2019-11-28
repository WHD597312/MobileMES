package com.supcon.whd.login;


import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.supcon.whd.common.annotation.Presenter;
import com.supcon.whd.common.base.ui.activity.BasePresenterActivity;
import com.supcon.whd.common.constant.Constant;
import com.supcon.whd.common.utils.StatusBarUtils;
import com.supcon.whd.login.model.api.LoginAPI;
import com.supcon.whd.login.model.bean.LoginEntity;
import com.supcon.whd.login.model.contract.ContractLogin;
import com.supcon.whd.login.presenter.LoginPresenter;
import com.thejoyrun.router.RouterActivity;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Flowable;


@RouterActivity(Constant.Router.LOGIN)
@Presenter(value = {
        LoginPresenter.class
})
public class LoginActivity extends BasePresenterActivity implements ContractLogin.View {

    @BindView(R2.id.loginName)
    EditText loginName;
    @BindView(R2.id.loginPswd)
    EditText loginPswd;
    @Override
    public int getLayoutID() {
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
            String phone=loginName.getText().toString();
            String password=loginPswd.getText().toString();
            onLoading("正在加载...");
            Flowable.timer(300, TimeUnit.MILLISECONDS)
                    .subscribe(s->{
                        presenterRouter.create(LoginAPI.class).login(phone,password);
                    });

        }
    }
    @Override
    public void doLoginSuccess(LoginEntity loginEntity) {
        Log.i("LoginEntity",loginEntity.toString());
//        Toast.makeText(this,loginEntity.toString(),Toast.LENGTH_SHORT).show();
        onLoadSuccess("登录成功!");
    }

    @Override
    public void doLoginFailed(String errMsg) {
        Log.i("LoginEntity",errMsg);
//        Toast.makeText(this,errMsg,Toast.LENGTH_SHORT).show();
        onLoadFail("登录失败!");
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
