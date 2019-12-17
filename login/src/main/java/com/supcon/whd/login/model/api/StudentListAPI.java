package com.supcon.whd.login.model.api;


import com.supcon.whd.annotation.ContractFactory;
import com.supcon.whd.common.constant.Constant;

@ContractFactory(value = Constant.ModelApi.STUDENTLIST)
public interface StudentListAPI {
    void getStudent(int page);
}
