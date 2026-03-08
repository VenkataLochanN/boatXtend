package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.amap.api.mapcore.util.j;
import com.autonavi.base.amap.api.mapcore.IAMapDelegate;
import com.autonavi.base.amap.mapcore.MapConfig;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: AuthProTask.java */
/* JADX INFO: loaded from: classes.dex */
public class i extends Thread {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static int f1302c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static int f1303d = 3;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static long f1304e = 30000;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static boolean f1305g = false;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private WeakReference<Context> f1306a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private IAMapDelegate f1307b;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private a f1308f = null;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private Handler f1309h = new Handler(Looper.getMainLooper()) { // from class: com.amap.api.mapcore.util.i.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (i.f1305g) {
                return;
            }
            if (i.this.f1308f == null) {
                i iVar = i.this;
                iVar.f1308f = new a(iVar.f1307b, i.this.f1306a == null ? null : (Context) i.this.f1306a.get());
            }
            eq.a().a(i.this.f1308f);
        }
    };

    static /* synthetic */ int c() {
        int i = f1302c;
        f1302c = i + 1;
        return i;
    }

    public i(Context context, IAMapDelegate iAMapDelegate) {
        this.f1306a = null;
        if (context != null) {
            this.f1306a = new WeakReference<>(context);
        }
        this.f1307b = iAMapDelegate;
        a();
    }

    public static void a() {
        f1302c = 0;
        f1305g = false;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        try {
            f();
        } catch (Throwable th) {
            hn.c(th, "AMapDelegateImpGLSurfaceView", "mVerfy");
            th.printStackTrace();
            ey.b(ex.f798e, "auth pro exception " + th.getMessage());
        }
    }

    private void f() {
        if (f1305g) {
            return;
        }
        int i = 0;
        while (i <= f1303d) {
            i++;
            this.f1309h.sendEmptyMessageDelayed(0, ((long) i) * f1304e);
        }
    }

    @Override // java.lang.Thread
    public void interrupt() {
        this.f1307b = null;
        this.f1306a = null;
        Handler handler = this.f1309h;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        this.f1309h = null;
        this.f1308f = null;
        a();
        super.interrupt();
    }

    /* JADX INFO: compiled from: AuthProTask.java */
    static class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private WeakReference<IAMapDelegate> f1311a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private WeakReference<Context> f1312b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private j f1313c;

        public a(IAMapDelegate iAMapDelegate, Context context) {
            this.f1311a = null;
            this.f1312b = null;
            this.f1311a = new WeakReference<>(iAMapDelegate);
            if (context != null) {
                this.f1312b = new WeakReference<>(context);
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            j.a aVarE;
            try {
                if (i.f1305g) {
                    return;
                }
                if (this.f1313c == null && this.f1312b != null && this.f1312b.get() != null) {
                    this.f1313c = new j(this.f1312b.get(), "");
                }
                i.c();
                if (i.f1302c > i.f1303d) {
                    boolean unused = i.f1305g = true;
                    a();
                } else {
                    if (this.f1313c == null || (aVarE = this.f1313c.e()) == null) {
                        return;
                    }
                    if (!aVarE.f1436d) {
                        a();
                    }
                    boolean unused2 = i.f1305g = true;
                }
            } catch (Throwable th) {
                hn.c(th, "authForPro", "loadConfigData_uploadException");
                ey.b(ex.f798e, "auth exception get data " + th.getMessage());
            }
        }

        private void a() {
            final IAMapDelegate iAMapDelegate;
            WeakReference<IAMapDelegate> weakReference = this.f1311a;
            if (weakReference == null || weakReference.get() == null || (iAMapDelegate = this.f1311a.get()) == null || iAMapDelegate.getMapConfig() == null) {
                return;
            }
            iAMapDelegate.queueEvent(new Runnable() { // from class: com.amap.api.mapcore.util.i.a.1
                @Override // java.lang.Runnable
                public void run() {
                    IAMapDelegate iAMapDelegate2 = iAMapDelegate;
                    if (iAMapDelegate2 == null || iAMapDelegate2.getMapConfig() == null) {
                        return;
                    }
                    MapConfig mapConfig = iAMapDelegate.getMapConfig();
                    mapConfig.setProFunctionAuthEnable(false);
                    if (mapConfig.isUseProFunction()) {
                        iAMapDelegate.setMapCustomEnable(mapConfig.isCustomStyleEnable(), true);
                        iAMapDelegate.reloadMapCustomStyle();
                        ds.a(a.this.f1312b == null ? null : (Context) a.this.f1312b.get(), "鉴权失败，当前key没有自定义纹理的使用权限，自定义纹理相关内容，将不会呈现！");
                    }
                }
            });
        }
    }
}