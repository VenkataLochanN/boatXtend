package com.amap.api.mapcore.util;

import android.content.Context;

/* JADX INFO: compiled from: AdiuManager.java */
/* JADX INFO: loaded from: classes.dex */
public class gv {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static gv f1145a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Context f1146b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f1147c = hc.a(gt.c("RYW1hcF9kZXZpY2VfYWRpdQ"));

    public gv(Context context) {
        this.f1146b = context.getApplicationContext();
    }

    public static gv a(Context context) {
        if (f1145a == null) {
            synchronized (gv.class) {
                if (f1145a == null) {
                    f1145a = new gv(context);
                }
            }
        }
        return f1145a;
    }

    public void a(String str) {
        gw.a(this.f1146b).a(this.f1147c);
        gw.a(this.f1146b).b(str);
    }
}