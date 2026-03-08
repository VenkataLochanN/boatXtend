package com.ido.common.net;

import java.io.IOException;
import java.net.Proxy;
import java.util.Iterator;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/* JADX INFO: loaded from: classes2.dex */
public class RetrofitService {
    private static NetConfiguration mNetConfiguration;
    private static final RetrofitService mService = new RetrofitService();
    private Retrofit mRetrofit;

    public static RetrofitService getInstance() {
        return mService;
    }

    private RetrofitService() {
    }

    public void init(NetConfiguration netConfiguration) {
        mNetConfiguration = netConfiguration;
        this.mRetrofit = initRetrofit(mNetConfiguration);
    }

    private Retrofit initRetrofit(NetConfiguration netConfiguration) {
        OkHttpClient.Builder builderProxy = new OkHttpClient.Builder().connectTimeout(netConfiguration.getTimeOut(), TimeUnit.MILLISECONDS).readTimeout(netConfiguration.getTimeOut(), TimeUnit.MILLISECONDS).writeTimeout(netConfiguration.getTimeOut(), TimeUnit.MILLISECONDS).sslSocketFactory(SSLSocketFactory.getUnCheckSSLSocketFactory()).proxy(Proxy.NO_PROXY);
        if (netConfiguration.getRequestInterceptorList() != null && netConfiguration.getRequestInterceptorList().size() > 0) {
            Iterator<Interceptor> it = netConfiguration.getRequestInterceptorList().iterator();
            while (it.hasNext()) {
                builderProxy.addInterceptor(it.next());
            }
        }
        if (netConfiguration.getUrlInterceptorList() != null && netConfiguration.getUrlInterceptorList().size() > 0) {
            Iterator<Interceptor> it2 = netConfiguration.getUrlInterceptorList().iterator();
            while (it2.hasNext()) {
                builderProxy.addNetworkInterceptor(it2.next());
            }
        }
        return new Retrofit.Builder().client(builderProxy.build()).baseUrl(netConfiguration.getHost()).addConverterFactory(GsonConverterFactory.create()).build();
    }

    private Interceptor getDownloadInterceptor() {
        return new Interceptor() { // from class: com.ido.common.net.RetrofitService.1
            @Override // okhttp3.Interceptor
            public Response intercept(Interceptor.Chain chain) throws IOException {
                return chain.proceed(chain.request().newBuilder().addHeader("Accept-Encoding", "identity").addHeader("contentType", "text/json").build());
            }
        };
    }

    public static <T> T create(Class<T> cls) {
        return (T) mService.mRetrofit.create(cls);
    }

    public Retrofit initDownloadRetrofit() {
        Retrofit.Builder builderClient = new Retrofit.Builder().callbackExecutor(Executors.newSingleThreadExecutor()).addConverterFactory(GsonConverterFactory.create()).client(new OkHttpClient());
        NetConfiguration netConfiguration = mNetConfiguration;
        if (netConfiguration != null) {
            builderClient.baseUrl(netConfiguration.getHost());
        }
        return builderClient.build();
    }
}