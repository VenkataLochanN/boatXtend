package com.loc;

import android.content.Context;
import android.text.TextUtils;

/* JADX INFO: compiled from: MobileUpdateStrategy.java */
/* JADX INFO: loaded from: classes3.dex */
public final class bn extends bq {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Context f4868b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f4869d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f4870e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f4871f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f4867a = "iKey";

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f4872g = 0;

    public bn(Context context, boolean z, int i, int i2, String str) {
        a(context, z, i, i2, str, 0);
    }

    public bn(Context context, boolean z, int i, int i2, String str, int i3) {
        a(context, z, i, i2, str, i3);
    }

    private void a(Context context, boolean z, int i, int i2, String str, int i3) {
        this.f4868b = context;
        this.f4869d = z;
        this.f4870e = i;
        this.f4871f = i2;
        this.f4867a = str;
        this.f4872g = i3;
    }

    @Override // com.loc.bq
    public final void a(int i) {
        if (n.q(this.f4868b) == 1) {
            return;
        }
        String strA = u.a(System.currentTimeMillis(), "yyyyMMdd");
        String strA2 = z.a(this.f4868b, this.f4867a);
        if (!TextUtils.isEmpty(strA2)) {
            String[] strArrSplit = strA2.split("\\|");
            if (strArrSplit == null || strArrSplit.length < 2) {
                z.b(this.f4868b, this.f4867a);
            } else if (strA.equals(strArrSplit[0])) {
                i += Integer.parseInt(strArrSplit[1]);
            }
        }
        z.a(this.f4868b, this.f4867a, strA + "|" + i);
    }

    @Override // com.loc.bq
    protected final boolean a() {
        if (n.q(this.f4868b) == 1) {
            return true;
        }
        if (!this.f4869d) {
            return false;
        }
        String strA = z.a(this.f4868b, this.f4867a);
        if (TextUtils.isEmpty(strA)) {
            return true;
        }
        String[] strArrSplit = strA.split("\\|");
        if (strArrSplit != null && strArrSplit.length >= 2) {
            return !u.a(System.currentTimeMillis(), "yyyyMMdd").equals(strArrSplit[0]) || Integer.parseInt(strArrSplit[1]) < this.f4871f;
        }
        z.b(this.f4868b, this.f4867a);
        return true;
    }

    @Override // com.loc.bq
    public final int b() {
        int i;
        if ((n.q(this.f4868b) == 1 || (i = this.f4870e) <= 0) && ((i = this.f4872g) <= 0 || i >= Integer.MAX_VALUE)) {
            i = Integer.MAX_VALUE;
        }
        return this.f4878c != null ? Math.max(i, this.f4878c.b()) : i;
    }
}