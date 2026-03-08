package com.amap.api.mapcore.util;

import android.content.Context;
import com.amap.api.mapcore.util.gs;
import com.amap.api.mapcore.util.hy;

/* JADX INFO: compiled from: MsgProcessorDelegate.java */
/* JADX INFO: loaded from: classes.dex */
public class hw {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Context f1294a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private gs f1295b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f1296c = true;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f1297d = "40C27E38DCAD404B5465362914090908";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private jt f1298e = new jt(this.f1297d);

    public void a(Context context, boolean z, String str, String str2, String str3, String[] strArr) {
        try {
            gs gsVarA = new gs.a(str, str2, str).a(strArr).a(str3).a();
            if (context != null && gsVarA != null) {
                this.f1294a = context.getApplicationContext();
                this.f1295b = gsVarA;
                this.f1296c = z;
                this.f1298e.a(this.f1294a, gsVarA);
            }
        } catch (gh unused) {
        }
    }

    public void a(String str, String str2) {
        hy.a.f1300a.a(this.f1294a, str, str2, this.f1298e.a(this.f1294a), this.f1296c, this.f1295b);
    }
}