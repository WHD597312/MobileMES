package com.supcon.whd.login.model.network;

import com.supcon.whd.annotation.ApiFactory;
import com.supcon.whd.login.model.bean.LoginEntity;
import java.util.Map;
import io.reactivex.Flowable;

import retrofit2.http.GET;

import retrofit2.http.Query;
import retrofit2.http.QueryMap;


@ApiFactory(interfaceName = "NetWorkApi")
public interface NetWorkApi {

    /**
     * 登陆
     *
     * @param username 用户名
     * @param password 密码
     * @param map      默认参数
     * @return
     */
    @GET("/cas/mobile/logon")
    Flowable<LoginEntity> login(@Query("username") String username, @Query("password") String password, @QueryMap Map<String, Object> map);

}
