package com.baidu.location.a;

import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static Object f2062a = new Object();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static b f2063b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f2064c = -1;

    public static b a() {
        b bVar;
        synchronized (f2062a) {
            if (f2063b == null) {
                f2063b = new b();
            }
            bVar = f2063b;
        }
        return bVar;
    }

    public void a(int i, int i2, String str) {
        if (i2 != this.f2064c) {
            this.f2064c = i2;
            Bundle bundle = new Bundle();
            bundle.putInt("loctype", i);
            bundle.putInt("diagtype", i2);
            bundle.putByteArray("diagmessage", str.getBytes());
            a.a().a(bundle, 303);
        }
    }

    public void b() {
        this.f2064c = -1;
    }
}