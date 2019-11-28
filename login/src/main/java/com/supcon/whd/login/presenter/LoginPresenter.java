package com.supcon.whd.login.presenter;

import com.supcon.whd.login.model.bean.LoginEntity;
import com.supcon.whd.login.model.contract.ContractLogin;
import com.supcon.whd.login.model.network.HttpClient;
import com.supcon.whd.middle.bean.UserEntity;


public class LoginPresenter extends ContractLogin.Presenter {

    @Override
    public void login(String phone, String password) {
        UserEntity userEntity=new UserEntity();
        userEntity.phone=phone;
        userEntity.password=password;
        mCompositeSubscription.add(
                HttpClient
                        .login(userEntity)
                        .onErrorReturn(throwable -> {
                            LoginEntity loginEntity = new LoginEntity();
                            loginEntity.returnCode = -200;
                            loginEntity.returnMsg = throwable.toString();
                            return loginEntity;
                        })
                        .subscribe(loginEntity -> {
                            if (-200==loginEntity.returnCode) {
                                getView().doLoginFailed(loginEntity.returnMsg);
                            } else {
                                getView().doLoginSuccess(loginEntity);
                            }
                        })
        );
    }
}
