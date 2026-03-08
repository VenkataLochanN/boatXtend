package com.ido.ble.event.stat.one;

import android.content.Context;

/* JADX INFO: loaded from: classes2.dex */
class h extends com.ido.ble.common.d {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final String f4409c = "LOG_STAT_PARAS";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final String f4410d = "last_ip";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static h f4411e;

    h() {
    }

    public static final h c() {
        if (f4411e == null) {
            f4411e = new h();
            f4411e.a(com.ido.ble.common.e.a());
        }
        return f4411e;
    }

    public void a(Context context) {
        super.a(context, f4409c);
    }

    public String b() {
        return a(f4410d, "");
    }

    public void c(String str) {
        b(f4410d, str);
    }
}