package com.ido.common.net;

import com.ido.life.net.BaseUrl;
import java.util.ArrayList;
import java.util.List;
import okhttp3.Interceptor;

/* JADX INFO: loaded from: classes2.dex */
public class NetConfiguration {
    private String mAppkey;
    private String mAuthorization;
    private String mHost;
    private List<Interceptor> mRequestInterceptorList;
    private long mTimeOut;
    private List<Interceptor> mUrlInterceptorList;

    private NetConfiguration(long j, String str, List<Interceptor> list, List<Interceptor> list2, String str2, String str3) {
        this.mTimeOut = j;
        this.mHost = str;
        this.mRequestInterceptorList = list;
        this.mUrlInterceptorList = list2;
        this.mAppkey = str2;
        this.mAuthorization = str3;
    }

    public long getTimeOut() {
        return this.mTimeOut;
    }

    public String getHost() {
        return this.mHost;
    }

    public List<Interceptor> getRequestInterceptorList() {
        return this.mRequestInterceptorList;
    }

    public List<Interceptor> getUrlInterceptorList() {
        return this.mUrlInterceptorList;
    }

    public String getAppkey() {
        return this.mAppkey;
    }

    public String getAuthorization() {
        return this.mAuthorization;
    }

    public static class Builder {
        private String Appkey;
        private String Authorization;
        private String Host;
        private long TimeOut = 30000;
        private List<Interceptor> RequestInterceptorList = new ArrayList();
        private List<Interceptor> UrlInterceptorList = new ArrayList();

        public Builder(String str) {
            this.Host = BaseUrl.DEFAULT_HOST;
            this.Host = str;
        }

        public Builder RequestInterceptorList(List<Interceptor> list) {
            if (list != null && list.size() > 0) {
                this.RequestInterceptorList.addAll(list);
            }
            return this;
        }

        public Builder UrlInterceptorList(List<Interceptor> list) {
            if (list != null && list.size() > 0) {
                this.UrlInterceptorList.addAll(list);
            }
            return this;
        }

        public Builder addRequestInterceptorList(Interceptor interceptor) {
            if (interceptor != null) {
                this.RequestInterceptorList.add(interceptor);
            }
            return this;
        }

        public Builder addUrlInterceptorList(Interceptor interceptor) {
            if (interceptor != null) {
                this.UrlInterceptorList.add(interceptor);
            }
            return this;
        }

        public Builder Appkey(String str) {
            this.Appkey = str;
            return this;
        }

        public Builder Authorization(String str) {
            this.Authorization = str;
            return this;
        }

        public NetConfiguration build() {
            return new NetConfiguration(this.TimeOut, this.Host, this.RequestInterceptorList, this.UrlInterceptorList, this.Appkey, this.Authorization);
        }
    }
}