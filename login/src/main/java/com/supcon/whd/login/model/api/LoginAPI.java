package com.supcon.whd.login.model.api;


import com.supcon.whd.annotation.ContractFactory;
import com.supcon.whd.common.constant.Constant;
import com.supcon.whd.login.model.bean.LoginEntity;
import com.supcon.whd.login.model.bean.StudentListEntity;


@ContractFactory(entities = {LoginEntity.class})
public interface LoginAPI {
    void login(String username,String password);
}
