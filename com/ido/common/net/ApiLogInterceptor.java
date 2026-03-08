package com.ido.common.net;

import com.ido.common.log.CommonLogUtil;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* JADX INFO: loaded from: classes2.dex */
public class ApiLogInterceptor implements Interceptor {
    private static final String TAG = ApiLogInterceptor.class.getSimpleName();

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws Exception {
        Request request = chain.request();
        Response responseProceed = chain.proceed(chain.request());
        MediaType mediaTypeContentType = responseProceed.body().contentType();
        String strString = responseProceed.body().string();
        CommonLogUtil.d(TAG, request.toString());
        CommonLogUtil.d(TAG, strString);
        if (responseProceed.body() == null) {
            return responseProceed;
        }
        return responseProceed.newBuilder().body(ResponseBody.create(mediaTypeContentType, strString)).build();
    }
}