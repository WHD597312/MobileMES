package com.supcon.whd.login.model.api;


import com.supcon.whd.annotation.ContractFactory;
import com.supcon.whd.common.constant.Constant;


@ContractFactory(value = Constant.ModelApi.LOGIN)
public interface LoginAPI {
    void login(String username,String password);
}
