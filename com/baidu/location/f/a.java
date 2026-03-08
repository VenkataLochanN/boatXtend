package com.baidu.location.f;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Process;
import android.util.Log;
import com.baidu.location.LLSInterface;
import com.baidu.location.a.h;
import com.baidu.location.a.j;
import com.baidu.location.a.l;
import com.baidu.location.a.o;
import com.baidu.location.a.u;
import com.baidu.location.a.v;
import com.baidu.location.a.w;
import com.baidu.location.a.x;
import com.baidu.location.b.d;
import com.baidu.location.b.e;
import com.baidu.location.e.i;
import com.baidu.location.f;
import com.baidu.location.g.k;
import com.baidu.location.indoor.g;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public class a extends Service implements LLSInterface {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static HandlerC0022a f2441a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static long f2442c;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static long f2443g;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    Messenger f2444b = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Looper f2445d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private HandlerThread f2446e = null;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f2447f = false;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int f2448h = 0;

    /* JADX INFO: renamed from: com.baidu.location.f.a$a, reason: collision with other inner class name */
    public static class HandlerC0022a extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final WeakReference<a> f2449a;

        public HandlerC0022a(Looper looper, a aVar) {
            super(looper);
            this.f2449a = new WeakReference<>(aVar);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            a aVar = this.f2449a.get();
            if (aVar == null) {
                return;
            }
            if (f.isServing) {
                int i = message.what;
                if (i != 11) {
                    if (i != 12) {
                        if (i != 15) {
                            if (i != 22) {
                                if (i != 28) {
                                    if (i != 41) {
                                        if (i != 705) {
                                            if (i != 406) {
                                                if (i != 407 && i != 802 && i != 803) {
                                                    switch (i) {
                                                        case 110:
                                                            g.a().c();
                                                            break;
                                                        case 111:
                                                            g.a().d();
                                                            break;
                                                        case 112:
                                                            g.a().b();
                                                            break;
                                                        case 113:
                                                            Object obj = message.obj;
                                                            if (obj != null) {
                                                                g.a().a((Bundle) obj);
                                                            }
                                                            break;
                                                        case 114:
                                                            Object obj2 = message.obj;
                                                            if (obj2 != null) {
                                                                g.a().b((Bundle) obj2);
                                                            }
                                                            break;
                                                        default:
                                                            switch (i) {
                                                                case 401:
                                                                    try {
                                                                        message.getData();
                                                                    } catch (Exception unused) {
                                                                    }
                                                                    break;
                                                            }
                                                            break;
                                                    }
                                                }
                                            } else {
                                                h.a().e();
                                            }
                                        } else {
                                            com.baidu.location.a.a.a().a(message.getData().getBoolean("foreground"));
                                        }
                                    } else {
                                        l.c().i();
                                    }
                                } else {
                                    l.c().a(true, true);
                                }
                            } else {
                                l.c().b(message);
                            }
                        } else {
                            aVar.c(message);
                        }
                    } else {
                        aVar.b(message);
                    }
                } else {
                    aVar.a(message);
                }
            }
            if (message.what == 1) {
                aVar.d();
            }
            if (message.what == 0) {
                aVar.c();
            }
            super.handleMessage(message);
        }
    }

    public static Handler a() {
        return f2441a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Message message) {
        Log.d("baidu_location_service", "baidu location service register ...");
        com.baidu.location.a.a.a().a(message);
        com.baidu.location.d.h.a();
        e.a().d();
        if (k.b()) {
            return;
        }
        o.b().c();
    }

    public static long b() {
        return f2443g;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Message message) {
        com.baidu.location.a.a.a().b(message);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        j.a().a(f.getServiceContext());
        e.a().b();
        com.baidu.location.g.b.a();
        x.a().e();
        h.a().b();
        com.baidu.location.e.e.a().b();
        com.baidu.location.e.b.a().b();
        l.c().d();
        com.baidu.location.d.a.a().c();
        d.a().b();
        com.baidu.location.b.g.a().b();
        com.baidu.location.b.a.a().b();
        i.a().c();
        this.f2448h = 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(Message message) {
        com.baidu.location.a.a.a().c(message);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        com.baidu.location.e.e.a().e();
        i.a().e();
        com.baidu.location.d.h.a().n();
        x.a().f();
        e.a().c();
        d.a().c();
        com.baidu.location.b.b.a().c();
        com.baidu.location.b.a.a().c();
        com.baidu.location.a.b.a().b();
        com.baidu.location.e.b.a().c();
        l.c().e();
        g.a().d();
        h.a().c();
        w.d();
        com.baidu.location.a.a.a().b();
        v.a().d();
        this.f2448h = 4;
        Log.d("baidu_location_service", "baidu location service has stoped ...");
        if (this.f2447f) {
            return;
        }
        Process.killProcess(Process.myPid());
    }

    @Override // com.baidu.location.LLSInterface
    public double getVersion() {
        return 7.71999979019165d;
    }

    @Override // android.app.Service, com.baidu.location.LLSInterface
    public IBinder onBind(Intent intent) {
        boolean z;
        Bundle extras = intent.getExtras();
        if (extras != null) {
            com.baidu.location.g.b.f2463h = extras.getString("key");
            com.baidu.location.g.b.f2462g = extras.getString("sign");
            this.f2447f = extras.getBoolean("kill_process");
            z = extras.getBoolean("cache_exception");
        } else {
            z = false;
        }
        if (!z) {
            Thread.setDefaultUncaughtExceptionHandler(com.baidu.location.b.g.a());
        }
        return this.f2444b.getBinder();
    }

    @Override // com.baidu.location.LLSInterface
    public void onCreate(Context context) {
        try {
            k.ax = context.getPackageName();
        } catch (Exception unused) {
        }
        f2443g = System.currentTimeMillis();
        this.f2446e = u.a();
        HandlerThread handlerThread = this.f2446e;
        if (handlerThread != null) {
            this.f2445d = handlerThread.getLooper();
        }
        Looper looper = this.f2445d;
        if (looper == null) {
            f2441a = new HandlerC0022a(Looper.getMainLooper(), this);
        } else {
            f2441a = new HandlerC0022a(looper, this);
        }
        f2442c = System.currentTimeMillis();
        this.f2444b = new Messenger(f2441a);
        f2441a.sendEmptyMessage(0);
        this.f2448h = 1;
        Log.d("baidu_location_service", "baidu location service start1 ...20190509..." + Process.myPid());
    }

    @Override // android.app.Service, com.baidu.location.LLSInterface
    public void onDestroy() {
        try {
            f2441a.sendEmptyMessage(1);
        } catch (Exception unused) {
            Log.d("baidu_location_service", "baidu location service stop exception...");
            d();
            Process.killProcess(Process.myPid());
        }
        this.f2448h = 3;
        new Handler(Looper.getMainLooper()).postDelayed(new b(this, new WeakReference(this)), 1000L);
        Log.d("baidu_location_service", "baidu location service stop ...");
    }

    @Override // android.app.Service, com.baidu.location.LLSInterface
    public int onStartCommand(Intent intent, int i, int i2) {
        return 1;
    }

    @Override // android.app.Service, com.baidu.location.LLSInterface
    public void onTaskRemoved(Intent intent) {
        Log.d("baidu_location_service", "baidu location service remove task...");
    }

    @Override // com.baidu.location.LLSInterface
    public boolean onUnBind(Intent intent) {
        return false;
    }
}