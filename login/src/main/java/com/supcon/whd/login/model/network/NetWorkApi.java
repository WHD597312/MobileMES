package com.supcon.whd.login.model.network;

import com.supcon.whd.annotation.ApiFactory;
import com.supcon.whd.login.model.bean.LoginEntity;
import com.supcon.whd.middle.bean.UserEntity;


import io.reactivex.Flowable;
import retrofit2.http.Body;
import retrofit2.http.POST;



@ApiFactory(interfaceName = "NetWorkApi")
public interface NetWorkApi {

    @POST("/qjjc/user/login")
    Flowable<LoginEntity> login(@Body UserEntity userEntity);
}
