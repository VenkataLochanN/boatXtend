package com.ido.life.net;

import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.life.constants.Constants;
import com.ido.life.data.AuthorizationPreference;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/* JADX INFO: loaded from: classes3.dex */
public class RequestInterceptor implements Interceptor {
    private static final String TAG = "RequestInterceptor";
    private String mAppkey;
    private String mAuthorization;

    public RequestInterceptor(String str, String str2) {
        this.mAppkey = Constants.APP_KEY;
        this.mAppkey = str;
        this.mAuthorization = str2;
    }

    private static class RequestMethod {
        static final String GET = "GET";
        static final String POST = "POST";

        private RequestMethod() {
        }
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        return addPlatform(chain, chain.request());
    }

    private Response addPlatform(Interceptor.Chain chain, Request request) throws IOException {
        this.mAuthorization = AuthorizationPreference.getToken(IdoApp.getAppContext());
        CommonLogUtil.d(TAG, "addPlatform: " + this.mAppkey + AppInfo.DELIM + this.mAuthorization);
        return chain.proceed(request.newBuilder().addHeader("appkey", this.mAppkey).addHeader("Authorization", this.mAuthorization).addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8").addHeader("Accept-Encoding", "").addHeader("Connection", "keep-alive").addHeader("Accept", "*/*").addHeader("X-HB-Client-Type", "ayb-android").build());
    }

    public String getAuthorization() {
        return this.mAuthorization;
    }

    public void setAuthorization(String str) {
        this.mAuthorization = str;
    }

    public String getAppkey() {
        return this.mAppkey;
    }

    public void setAppkey(String str) {
        this.mAppkey = str;
    }
}