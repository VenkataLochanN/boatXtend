package com.amap.api.mapcore.util;

import android.content.Context;

/* JADX INFO: compiled from: OfflineLocEntity.java */
/* JADX INFO: loaded from: classes.dex */
public class iy {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Context f1420a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private gs f1421b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f1422c;

    public iy(Context context, gs gsVar, String str) {
        this.f1420a = context.getApplicationContext();
        this.f1421b = gsVar;
        this.f1422c = str;
    }

    byte[] a() {
        return gt.a(a(this.f1420a, this.f1421b, this.f1422c));
    }

    private static String a(Context context, gs gsVar, String str) {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("\"sdkversion\":\"");
            sb.append(gsVar.c());
            sb.append("\",\"product\":\"");
            sb.append(gsVar.a());
            sb.append("\",\"nt\":\"");
            sb.append(gm.e(context));
            sb.append("\",\"details\":");
            sb.append(str);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return sb.toString();
    }
}