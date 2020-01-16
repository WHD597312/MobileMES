package com.supcon.whd.login.presenter;
import android.text.TextUtils;

import com.supcon.whd.login.model.bean.LoginEntity;
import com.supcon.whd.login.model.contract.ContractLogin;
import com.supcon.whd.login.model.network.HttpClient;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class LoginPresenter extends ContractLogin.Presenter {

    Map<String, Object> queryParams = new HashMap<>();

    @Override
    public void login(String username, String password) {
//        queryParams.put("machineId", 11111111);
//        queryParams.put("clientType", "android");
//        queryParams.put("clientVersion", "2.1");
//        queryParams.put("timestamp", new Date().getTime());
//        mCompositeSubscription.add(
//                HttpClient
//                        .login(username, password, queryParams)
//                        .onErrorReturn(error -> {
//                            LoginEntity loginEntity = new LoginEntity();
//                            loginEntity.errMsg = error.toString();
//                            loginEntity.success = false;
//                            return loginEntity;
//                        })
//                        .subscribe(loginEntity -> {
//                            if (loginEntity.result!=null){
//                                getView().doLoginSuccess(loginEntity);
//                            }else {
//                                getView().doLoginFailed(loginEntity.errMsg);
//                            }
//                        })
//        );
        Flowable.timer(500, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(o->{
                    if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)){
                        LoginEntity loginEntity = new LoginEntity();
                        getView().doLoginSuccess(loginEntity);
                    }else if (TextUtils.isEmpty(username)){
                        getView().doLoginFailed("用户名为空");
                    }else if (TextUtils.isEmpty(username)){
                        getView().doLoginFailed("密码为空");
                    }
                });

    }
}
