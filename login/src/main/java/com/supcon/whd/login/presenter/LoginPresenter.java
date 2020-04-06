package com.supcon.whd.login.presenter;

import com.supcon.whd.common.base.network.RxSchedulers;
import com.supcon.whd.login.model.bean.LoginEntity;
import com.supcon.whd.login.model.contract.ContractLogin;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenter extends ContractLogin.Presenter {
    @Override
    public void login(String username, String password) {
        mCompositeSubscription.add(
                Flowable
                        .timer(2000, TimeUnit.MICROSECONDS)
                        .compose(RxSchedulers.io_main())
                        .subscribe(o -> {
                            if ("whd".equals(username) && "123".equals(password)){
                                getView().doLoginSuccess(new LoginEntity());
                            }else {
                                getView().doLoginFailed("登录失败！");
                            }
                        })

        );
    }
}
