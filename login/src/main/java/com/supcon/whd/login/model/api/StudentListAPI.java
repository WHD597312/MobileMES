package com.supcon.whd.login.model.api;


import com.supcon.whd.annotation.ContractFactory;
import com.supcon.whd.common.constant.Constant;
import com.supcon.whd.login.model.bean.StudentEntity;
import com.supcon.whd.login.model.bean.StudentListEntity;

@ContractFactory(entities = StudentListEntity.class)
public interface StudentListAPI {
    void getStudent(int page);
}
