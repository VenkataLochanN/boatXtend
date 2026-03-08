package com.baidu.mapsdkplatform.comapi.synchronization.c;

import android.os.Build;
import com.baidu.mapapi.UIMsg;
import com.tencent.bugly.Bugly;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f3678a = UIMsg.m_AppUI.MSG_APP_SAVESCREEN;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f3679b = UIMsg.m_AppUI.MSG_APP_SAVESCREEN;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private ExecutorService f3680c = Executors.newCachedThreadPool();

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: com.baidu.mapsdkplatform.comapi.synchronization.c.a$a, reason: collision with other inner class name */
    static abstract class AbstractRunnableC0033a implements Runnable {
        private AbstractRunnableC0033a() {
        }

        /* synthetic */ AbstractRunnableC0033a(b bVar) {
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

    public void a(String str, e eVar) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Request URL cannot be null");
        }
        this.f3680c.submit(new b(this, eVar, str));
    }
}