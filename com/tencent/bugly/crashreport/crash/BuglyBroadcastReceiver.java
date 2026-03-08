package com.tencent.bugly.crashreport.crash;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.tencent.bugly.proguard.ab;
import com.tencent.bugly.proguard.v;
import com.tencent.bugly.proguard.y;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes3.dex */
public class BuglyBroadcastReceiver extends BroadcastReceiver {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static BuglyBroadcastReceiver f5442d;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Context f5444b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f5445c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f5446e = true;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private IntentFilter f5443a = new IntentFilter();

    public static synchronized BuglyBroadcastReceiver getInstance() {
        if (f5442d == null) {
            f5442d = new BuglyBroadcastReceiver();
        }
        return f5442d;
    }

    public synchronized void addFilter(String str) {
        if (!this.f5443a.hasAction(str)) {
            this.f5443a.addAction(str);
        }
        y.c("add action %s", str);
    }

    public synchronized void register(Context context) {
        this.f5444b = context;
        ab.a(new Runnable() { // from class: com.tencent.bugly.crashreport.crash.BuglyBroadcastReceiver.1
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    y.a(BuglyBroadcastReceiver.f5442d.getClass(), "Register broadcast receiver of Bugly.", new Object[0]);
                    synchronized (this) {
                        BuglyBroadcastReceiver.this.f5444b.registerReceiver(BuglyBroadcastReceiver.f5442d, BuglyBroadcastReceiver.this.f5443a, "com.tencent.bugly.BuglyBroadcastReceiver.permission", null);
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        });
    }

    public synchronized void unregister(Context context) {
        try {
            y.a(getClass(), "Unregister broadcast receiver of Bugly.", new Object[0]);
            context.unregisterReceiver(this);
            this.f5444b = context;
        } catch (Throwable th) {
            if (y.a(th)) {
                return;
            }
            th.printStackTrace();
        }
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        try {
            a(context, intent);
        } catch (Throwable th) {
            if (y.a(th)) {
                return;
            }
            th.printStackTrace();
        }
    }

    private synchronized boolean a(Context context, Intent intent) {
        if (context != null && intent != null) {
            if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                if (this.f5446e) {
                    this.f5446e = false;
                    return true;
                }
                String strB = com.tencent.bugly.crashreport.common.info.b.b(this.f5444b);
                y.c("is Connect BC " + strB, new Object[0]);
                y.a("network %s changed to %s", this.f5445c, strB);
                if (strB == null) {
                    this.f5445c = null;
                    return true;
                }
                String str = this.f5445c;
                this.f5445c = strB;
                long jCurrentTimeMillis = System.currentTimeMillis();
                com.tencent.bugly.crashreport.common.strategy.a aVarA = com.tencent.bugly.crashreport.common.strategy.a.a();
                v vVarA = v.a();
                com.tencent.bugly.crashreport.common.info.a aVarA2 = com.tencent.bugly.crashreport.common.info.a.a(context);
                if (aVarA != null && vVarA != null && aVarA2 != null) {
                    if (!strB.equals(str) && jCurrentTimeMillis - vVarA.a(c.f5511a) > 30000) {
                        y.a("try to upload crash on network changed.", new Object[0]);
                        c cVarA = c.a();
                        if (cVarA != null) {
                            cVarA.a(0L);
                        }
                        y.a("try to upload userinfo on network changed.", new Object[0]);
                        com.tencent.bugly.crashreport.biz.b.f5400a.b();
                    }
                    return true;
                }
                y.d("not inited BC not work", new Object[0]);
                return true;
            }
        }
        return false;
    }
}