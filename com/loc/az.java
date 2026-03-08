package com.loc;

import android.content.Context;

/* JADX INFO: compiled from: OfflineLocEntity.java */
/* JADX INFO: loaded from: classes3.dex */
public final class az {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Context f4822a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private t f4823b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f4824c;

    public az(Context context, t tVar, String str) {
        this.f4822a = context.getApplicationContext();
        this.f4823b = tVar;
        this.f4824c = str;
    }

    private static String a(Context context, t tVar, String str) {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("\"sdkversion\":\"");
            sb.append(tVar.c());
            sb.append("\",\"product\":\"");
            sb.append(tVar.a());
            sb.append("\",\"nt\":\"");
            sb.append(n.d(context));
            sb.append("\",\"details\":");
            sb.append(str);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return sb.toString();
    }

    final byte[] a() {
        return u.a(a(this.f4822a, this.f4823b, this.f4824c));
    }
}