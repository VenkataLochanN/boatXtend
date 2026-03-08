package com.baidu.mapsdkplatform.comapi.util;

import android.content.Context;
import android.os.Environment;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public final class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final boolean f3836a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f3837b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f3838c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final String f3839d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final String f3840e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final String f3841f;

    g(Context context) {
        this.f3836a = false;
        this.f3837b = Environment.getExternalStorageDirectory().getAbsolutePath();
        this.f3838c = this.f3837b + File.separator + "BaiduMapSDKNew";
        this.f3839d = context.getCacheDir().getAbsolutePath();
        this.f3840e = "";
        this.f3841f = "";
    }

    g(String str, boolean z, String str2, Context context) {
        this.f3836a = z;
        this.f3837b = str;
        this.f3838c = this.f3837b + File.separator + "BaiduMapSDKNew";
        this.f3839d = this.f3838c + File.separator + "cache";
        this.f3840e = context.getCacheDir().getAbsolutePath();
        this.f3841f = str2;
    }

    public String a() {
        return this.f3837b;
    }

    public String b() {
        return this.f3837b + File.separator + "BaiduMapSDKNew";
    }

    public String c() {
        return this.f3839d;
    }

    public String d() {
        return this.f3840e;
    }

    public boolean equals(Object obj) {
        if (obj == null || !g.class.isInstance(obj)) {
            return false;
        }
        return this.f3837b.equals(((g) obj).f3837b);
    }
}