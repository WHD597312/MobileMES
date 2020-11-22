package com.supcon.whd.common.base.network;
import android.text.TextUtils;
import com.supcon.whd.common.constant.MBapConstant;
import com.supcon.whd.common.utils.XmlUtil;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.List;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Response;
import okio.Buffer;
import okio.BufferedSource;

/**
 * Created by wangshizhan on 2017/8/9.
 */

public abstract class BaseInterceptor implements Interceptor {

    private static final Charset UTF8 = Charset.forName("UTF-8");

    protected Buffer getBuffer(Response response) throws IOException{
        BufferedSource source = response.body().source();
        source.request(Long.MAX_VALUE); // Buffer the entire body.
        return source.buffer();
    }

    protected String readContent(Response response, Buffer buffer) throws UnsupportedCharsetException{
        Charset charset = UTF8;
        MediaType contentType = response.body().contentType();
        if (contentType != null) {
            charset = contentType.charset(UTF8);
        }

        return buffer.clone().readString(charset);
    }

    protected void getHeaders(Response response, String content) {

        Headers headers = response.headers();

        String CASTGCStr = null;
        String jsessionidStr = null;
        String supOSticket = null;

        jsessionidStr = XmlUtil.getStringByTag(content, "JSEESIONID");
        supOSticket = XmlUtil.getStringByTag(content, "supOSticket");

        List<String> cookies = headers.values("Set-Cookie");

        for(int i = 0; i < cookies.size(); i++){

            String cookie = cookies.get(i);

/*            if(cookie.contains(Constant.SPKey.JSESSIONID) && cookie.contains(";") && cookie.contains("=")){

                String tmp[] = cookie.split(";");
                if(tmp[0].contains("Constant.SPKey.JSESSIONID")){
                    jsessionidStr = tmp[0].split("=")[1];
                }

//                jsessionidStr = cookie.split("=")[1];
            }
            else */if(cookie.contains(MBapConstant.SPKey.CASTGC)){

                String temp = cookie.split(";")[0];
                if(!TextUtils.isEmpty(temp) && temp.contains(MBapConstant.SPKey.CASTGC) && temp.contains("=")){
                    CASTGCStr = temp.split("=")[1];
                }

            }

        }



    }

}
