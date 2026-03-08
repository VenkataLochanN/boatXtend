package com.loc;

import android.content.Context;
import android.text.TextUtils;

/* JADX INFO: compiled from: TimeUpdateStrategy.java */
/* JADX INFO: loaded from: classes3.dex */
public final class bp extends bq {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected int f4874a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected long f4875b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f4876d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Context f4877e;

    public bp(Context context, int i, String str, bq bqVar) {
        super(bqVar);
        this.f4874a = i;
        this.f4876d = str;
        this.f4877e = context;
    }

    @Override // com.loc.bq
    public final void a(boolean z) {
        super.a(z);
        if (z) {
            String str = this.f4876d;
            long jCurrentTimeMillis = System.currentTimeMillis();
            this.f4875b = jCurrentTimeMillis;
            z.a(this.f4877e, str, String.valueOf(jCurrentTimeMillis));
        }
    }

    @Override // com.loc.bq
    protected final boolean a() {
        if (this.f4875b == 0) {
            String strA = z.a(this.f4877e, this.f4876d);
            this.f4875b = TextUtils.isEmpty(strA) ? 0L : Long.parseLong(strA);
        }
        return System.currentTimeMillis() - this.f4875b >= ((long) this.f4874a);
    }
}