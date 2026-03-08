package com.ido.alexa.util;

import android.util.Log;
import com.ido.alexa.net.HttpLoggingInterceptor;
import java.util.concurrent.TimeUnit;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;

/* JADX INFO: loaded from: classes2.dex */
public class ClientUtil {
    private static final long CONNECTION_POOL_TIMEOUT_MILLISECONDS = 3600000;
    private static long TIME_OUT = 30000;
    static HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() { // from class: com.ido.alexa.util.ClientUtil.1
        @Override // com.ido.alexa.net.HttpLoggingInterceptor.Logger
        public void log(String str) {
            Log.d("AlexaHttpLogInfo", str);
        }
    }).setLevel(HttpLoggingInterceptor.Level.BODY);
    private static OkHttpClient mClient;
    private static OkHttpClient okHttpClient;

    public static OkHttpClient getTLS12OkHttpClient() {
        if (mClient == null) {
            mClient = new OkHttpClient.Builder().connectTimeout(0L, TimeUnit.MILLISECONDS).readTimeout(0L, TimeUnit.MILLISECONDS).writeTimeout(30L, TimeUnit.SECONDS).connectionPool(new ConnectionPool(10, 3600000L, TimeUnit.MILLISECONDS)).build();
        }
        return mClient;
    }

    public static OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient.Builder().connectTimeout(TIME_OUT, TimeUnit.SECONDS).readTimeout(TIME_OUT, TimeUnit.SECONDS).writeTimeout(TIME_OUT, TimeUnit.SECONDS).build();
        }
        return okHttpClient;
    }
}