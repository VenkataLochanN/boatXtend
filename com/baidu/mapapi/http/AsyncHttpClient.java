package com.baidu.mapapi.http;

import android.os.Build;
import com.baidu.mapapi.http.HttpClient;
import com.baidu.mapsdkplatform.comapi.util.PermissionCheck;
import com.tencent.bugly.Bugly;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes.dex */
public class AsyncHttpClient {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f2743a = 10000;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f2744b = 10000;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private ExecutorService f2745c = Executors.newCachedThreadPool();

    /* JADX INFO: Access modifiers changed from: private */
    static abstract class a implements Runnable {
        private a() {
        }

        /* synthetic */ a(com.baidu.mapapi.http.a aVar) {
            this();
        }

        public abstract void a();

        @Override // java.lang.Runnable
        public void run() {
            a();
        }
    }

    static {
        if (Build.VERSION.SDK_INT <= 8) {
            System.setProperty("http.keepAlive", Bugly.SDK_IS_DEV);
        }
    }

    public void get(String str, HttpClient.ProtoResultCallback protoResultCallback) {
        if (str == null) {
            throw new IllegalArgumentException("URI cannot be null");
        }
        this.f2745c.submit(new com.baidu.mapapi.http.a(this, protoResultCallback, str));
    }

    protected boolean isAuthorized() {
        int iPermissionCheck = PermissionCheck.permissionCheck();
        return iPermissionCheck == 0 || iPermissionCheck == 602 || iPermissionCheck == 601;
    }
}