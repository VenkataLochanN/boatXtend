package com.amap.api.mapcore.util;

import android.content.Context;
import android.text.TextUtils;

/* JADX INFO: compiled from: TimeUpdateStrategy.java */
/* JADX INFO: loaded from: classes.dex */
public class jo extends jp {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected int f1476a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected long f1477b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f1478d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Context f1479e;

    public jo(Context context, int i, String str, jp jpVar) {
        super(jpVar);
        this.f1476a = i;
        this.f1478d = str;
        this.f1479e = context;
    }

    @Override // com.amap.api.mapcore.util.jp
    protected boolean c() {
        if (this.f1477b == 0) {
            this.f1477b = a(this.f1478d);
        }
        return System.currentTimeMillis() - this.f1477b >= ((long) this.f1476a);
    }

    @Override // com.amap.api.mapcore.util.jp
    public void a_(boolean z) {
        super.a_(z);
        if (z) {
            a(this.f1478d, System.currentTimeMillis());
        }
    }

    private void a(String str, long j) {
        this.f1477b = j;
        hl.a(this.f1479e, str, String.valueOf(j));
    }

    private long a(String str) {
        String strA = hl.a(this.f1479e, str);
        if (TextUtils.isEmpty(strA)) {
            return 0L;
        }
        return Long.parseLong(strA);
    }
}