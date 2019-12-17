package com.supcon.whd.login.model.bean;


import com.supcon.whd.common.base.model.BaseEntity;
import com.supcon.whd.middle.bean.UserEntity;


public class LoginEntity extends BaseEntity {
    public UserEntity result;
    public boolean success;
    public String errMsg;
}
