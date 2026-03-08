package com.amap.api.mapcore.util;

import android.content.Context;
import android.text.TextUtils;

/* JADX INFO: compiled from: MobileUpdateStrategy.java */
/* JADX INFO: loaded from: classes.dex */
public class jm extends jp {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Context f1470b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f1471d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f1472e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f1473f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f1469a = "iKey";

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f1474g = 0;

    public jm(Context context, boolean z, int i, int i2, String str, int i3) {
        a(context, z, i, i2, str, i3);
    }

    private void a(Context context, boolean z, int i, int i2, String str, int i3) {
        this.f1470b = context;
        this.f1471d = z;
        this.f1472e = i;
        this.f1473f = i2;
        this.f1469a = str;
        this.f1474g = i3;
    }

    @Override // com.amap.api.mapcore.util.jp
    protected boolean c() {
        if (gm.r(this.f1470b) == 1) {
            return true;
        }
        if (!this.f1471d) {
            return false;
        }
        String strA = hl.a(this.f1470b, this.f1469a);
        if (TextUtils.isEmpty(strA)) {
            return true;
        }
        String[] strArrSplit = strA.split("\\|");
        if (strArrSplit != null && strArrSplit.length >= 2) {
            return !gt.a(System.currentTimeMillis(), "yyyyMMdd").equals(strArrSplit[0]) || Integer.parseInt(strArrSplit[1]) < this.f1473f;
        }
        hl.b(this.f1470b, this.f1469a);
        return true;
    }

    @Override // com.amap.api.mapcore.util.jp
    public int a() {
        int i;
        if ((gm.r(this.f1470b) == 1 || (i = this.f1472e) <= 0) && ((i = this.f1474g) <= 0 || i >= Integer.MAX_VALUE)) {
            i = Integer.MAX_VALUE;
        }
        return this.f1480c != null ? Math.max(i, this.f1480c.a()) : i;
    }

    @Override // com.amap.api.mapcore.util.jp
    public void a_(int i) {
        if (gm.r(this.f1470b) == 1) {
            return;
        }
        String strA = gt.a(System.currentTimeMillis(), "yyyyMMdd");
        String strA2 = hl.a(this.f1470b, this.f1469a);
        if (!TextUtils.isEmpty(strA2)) {
            String[] strArrSplit = strA2.split("\\|");
            if (strArrSplit == null || strArrSplit.length < 2) {
                hl.b(this.f1470b, this.f1469a);
            } else if (strA.equals(strArrSplit[0])) {
                i += Integer.parseInt(strArrSplit[1]);
            }
        }
        hl.a(this.f1470b, this.f1469a, strA + "|" + i);
    }
}