package com.baidu.location.b;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import com.baidu.location.a.l;
import com.baidu.location.g.k;

/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static b f2217a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f2218b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Handler f2219c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private AlarmManager f2220d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private a f2221e = null;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private PendingIntent f2222f = null;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private long f2223g = 0;

    private class a extends BroadcastReceiver {
        private a() {
        }

        /* synthetic */ a(b bVar, c cVar) {
            this();
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (b.this.f2218b && intent.getAction().equals("com.baidu.location.autonotifyloc_7.7.2") && b.this.f2219c != null) {
                b.this.f2222f = null;
                b.this.f2219c.sendEmptyMessage(1);
            }
        }
    }

    private b() {
    }

    public static synchronized b a() {
        if (f2217a == null) {
            f2217a = new b();
        }
        return f2217a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        if (System.currentTimeMillis() - this.f2223g < 1000) {
            return;
        }
        PendingIntent pendingIntent = this.f2222f;
        if (pendingIntent != null) {
            this.f2220d.cancel(pendingIntent);
            this.f2222f = null;
        }
        if (this.f2222f == null) {
            this.f2222f = PendingIntent.getBroadcast(com.baidu.location.f.getServiceContext(), 0, new Intent("com.baidu.location.autonotifyloc_7.7.2"), AMapEngineUtils.HALF_MAX_P20_WIDTH);
            this.f2220d.set(0, System.currentTimeMillis() + ((long) k.X), this.f2222f);
        }
        Message message = new Message();
        message.what = 22;
        if (System.currentTimeMillis() - this.f2223g < k.Y) {
            return;
        }
        this.f2223g = System.currentTimeMillis();
        if (com.baidu.location.e.e.a().j()) {
            return;
        }
        l.c().b(message);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        if (this.f2218b) {
            try {
                if (this.f2222f != null) {
                    this.f2220d.cancel(this.f2222f);
                    this.f2222f = null;
                }
                com.baidu.location.f.getServiceContext().unregisterReceiver(this.f2221e);
            } catch (Exception unused) {
            }
            this.f2220d = null;
            this.f2221e = null;
            this.f2219c = null;
            this.f2218b = false;
        }
    }

    public void b() {
        if (!this.f2218b && k.X >= 10000) {
            if (this.f2219c == null) {
                this.f2219c = new c(this);
            }
            this.f2220d = (AlarmManager) com.baidu.location.f.getServiceContext().getSystemService("alarm");
            this.f2221e = new a(this, null);
            com.baidu.location.f.getServiceContext().registerReceiver(this.f2221e, new IntentFilter("com.baidu.location.autonotifyloc_7.7.2"), "android.permission.ACCESS_FINE_LOCATION", null);
            this.f2222f = PendingIntent.getBroadcast(com.baidu.location.f.getServiceContext(), 0, new Intent("com.baidu.location.autonotifyloc_7.7.2"), AMapEngineUtils.HALF_MAX_P20_WIDTH);
            this.f2220d.set(0, System.currentTimeMillis() + ((long) k.X), this.f2222f);
            this.f2218b = true;
            this.f2223g = System.currentTimeMillis();
        }
    }

    public void c() {
        Handler handler;
        if (this.f2218b && (handler = this.f2219c) != null) {
            handler.sendEmptyMessage(2);
        }
    }

    public void d() {
        Handler handler;
        if (this.f2218b && (handler = this.f2219c) != null) {
            handler.sendEmptyMessage(1);
        }
    }

    public void e() {
        Handler handler;
        if (this.f2218b && (handler = this.f2219c) != null) {
            handler.sendEmptyMessage(1);
        }
    }
}