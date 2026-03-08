package com.loc;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.Messenger;
import android.text.TextUtils;

/* JADX INFO: compiled from: ApsServiceCore.java */
/* JADX INFO: loaded from: classes3.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    e f5196a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    Context f5197b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    Messenger f5198c = null;

    public f(Context context) {
        this.f5196a = null;
        this.f5197b = null;
        this.f5197b = context.getApplicationContext();
        this.f5196a = new e(this.f5197b);
    }

    public final IBinder a(Intent intent) {
        e eVar = this.f5196a;
        String stringExtra = intent.getStringExtra("a");
        if (!TextUtils.isEmpty(stringExtra)) {
            l.a(eVar.f5106e, stringExtra);
        }
        eVar.f5102a = intent.getStringExtra("b");
        k.a(eVar.f5102a);
        String stringExtra2 = intent.getStringExtra("d");
        if (!TextUtils.isEmpty(stringExtra2)) {
            n.a(stringExtra2);
        }
        e eVar2 = this.f5196a;
        if ("true".equals(intent.getStringExtra("as")) && eVar2.f5105d != null) {
            eVar2.f5105d.sendEmptyMessageDelayed(9, 100L);
        }
        this.f5198c = new Messenger(this.f5196a.f5105d);
        return this.f5198c.getBinder();
    }

    public final void a() {
        try {
            e.d();
            this.f5196a.j = ep.b();
            this.f5196a.k = ep.a();
            this.f5196a.a();
        } catch (Throwable th) {
            ej.a(th, "ApsServiceCore", "onCreate");
        }
    }

    public final void b() {
        try {
            if (this.f5196a != null) {
                this.f5196a.f5105d.sendEmptyMessage(11);
            }
        } catch (Throwable th) {
            ej.a(th, "ApsServiceCore", "onDestroy");
        }
    }
}