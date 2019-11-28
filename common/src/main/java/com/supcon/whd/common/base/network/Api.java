package com.supcon.whd.common.base.network;

import android.util.Log;

import com.supcon.whd.common.base.BaseApplication;
import com.supcon.whd.common.utils.NetWorkUtil;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {

    public Retrofit retrofit;
//    public HttpService service;
    private boolean isDebug = true;
    private Api(){
        build();
    }

    private static class SinletonHolder{
        private static final Api INSTANCE=new Api();
    }
    public static Api  getInstance(){
        return SinletonHolder.INSTANCE;
    }
    Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {

            Request request = chain.request();
            //网上很多示例代码都对在request请求前对其进行无网的判断，其实无需判断，无网自动访问缓存
//            if(!NetworkUtil.getInstance().isConnected()){
//                request = request.newBuilder()
//                        .cacheControl(CacheControl.FORCE_CACHE)//只访问缓存
//                        .build();
//            }
            Response response = chain.proceed(request);

            if (NetWorkUtil.isConn(BaseApplication.getContext())) {
                int maxAge = 0;//缓存失效时间，单位为秒
                return response.newBuilder()
                        .removeHeader("Pragma")//清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                        .header("Cache-Control", "public ,max-age=" + maxAge)
                        .build();
            } else {
//                NetWorkUtil.showNoNetWorkDlg(MyApplication.getContext());
//                这段代码设置无效
//                int maxStale = 60 * 60 * 24 * 28; // 无网络时，设置超时为4周
//                return response.newBuilder()
//                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
//                        .removeHeader("Pragma")
//                        .build();
            }
            return response;
        }
    };
    private void build(){
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i("HttpClient",message);
            }
        });
        logInterceptor.setLevel(isDebug?HttpLoggingInterceptor.Level.BODY:HttpLoggingInterceptor.Level.NONE);
        String path=BaseApplication.baseUrl;
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(path)
                .client(new OkHttpClient.Builder()
                        .connectTimeout(5, TimeUnit.SECONDS)//设置连接超时
                        .readTimeout(5, TimeUnit.SECONDS)//读取超时
                        .writeTimeout(5, TimeUnit.SECONDS)//写入超时
                        .addNetworkInterceptor(logInterceptor)//添加自定义缓存拦截器（后面讲解），注意这里需要使用.addNetworkInterceptor
//                            .cache(cache)//把缓存添加进来

                        .build())
                .build();
//        service=retrofit.create(HttpService.class);
    }
}
