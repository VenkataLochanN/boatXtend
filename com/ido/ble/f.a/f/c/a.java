package com.ido.ble.f.a.f.c;

import android.content.Context;
import com.ido.ble.common.d;
import com.ido.ble.common.e;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
class a extends d {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final String f4457c = "BLE_CONNECT_CONFIG";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final String f4458d = "bind_device_address";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final String f4459e = "is_bind";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final String f4460f = "bind_auth";

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static a f4461g;

    private a() {
    }

    public static final a e() {
        if (f4461g == null) {
            f4461g = new a();
            f4461g.a(e.a());
        }
        return f4461g;
    }

    public void a(Context context) {
        super.a(context, f4457c);
    }

    public void a(boolean z) {
        b(f4459e, z);
    }

    public String b() {
        return a(f4460f, "");
    }

    public String c() {
        return a(f4458d, "");
    }

    public void c(String str) {
        b(f4460f, str);
    }

    public void d(String str) {
        b(f4458d, str);
    }

    public boolean d() {
        return a(f4459e, false);
    }
}