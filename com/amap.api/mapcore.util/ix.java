package com.amap.api.mapcore.util;

import android.content.Context;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: MarkInfoManager.java */
/* JADX INFO: loaded from: classes.dex */
public class ix {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static WeakReference<iv> f1417a;

    public static void a(final String str, final Context context) {
        hn.d().submit(new Runnable() { // from class: com.amap.api.mapcore.util.ix.1
            @Override // java.lang.Runnable
            public void run() {
                synchronized (ix.class) {
                    try {
                        String strA = gq.a(gt.a(str));
                        iv ivVarA = jc.a(ix.f1417a);
                        jc.a(context, ivVarA, hl.j, 50, 102400, "10");
                        if (ivVarA.f1413e == null) {
                            ivVarA.f1413e = new ic(new Cif(new ie()));
                        }
                        iw.a(strA, gt.a(" \"timestamp\":\"" + gt.a(System.currentTimeMillis(), "yyyyMMdd HH:mm:ss") + "\",\"details\":" + str), ivVarA);
                    } finally {
                    }
                }
            }
        });
    }
}