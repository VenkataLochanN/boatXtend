package com.amap.api.mapcore.util;

import android.content.Context;
import android.text.TextUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Random;

/* JADX INFO: compiled from: StatisticsManager.java */
/* JADX INFO: loaded from: classes.dex */
public class jb {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static boolean f1442a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static int f1443b = 20;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static int f1444c = 20;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static WeakReference<iv> f1445d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static int f1446e;

    /* JADX INFO: compiled from: StatisticsManager.java */
    static class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static int f1447a = 1;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        static int f1448b = 2;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        static int f1449c = 3;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private Context f1450d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private ja f1451e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private int f1452f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private List<ja> f1453g;

        a(Context context, int i) {
            this.f1450d = context;
            this.f1452f = i;
        }

        a(Context context, int i, List<ja> list) {
            this(context, i);
            this.f1453g = list;
        }

        a(Context context, int i, ja jaVar) {
            this(context, i);
            this.f1451e = jaVar;
        }

        private void a() {
            try {
                if (this.f1450d != null && this.f1451e != null) {
                    synchronized (jb.class) {
                        if (this.f1450d != null && this.f1451e != null) {
                            jb.b(this.f1450d, this.f1451e.a());
                        }
                    }
                }
            } catch (Throwable th) {
                hn.c(th, "stm", "as");
            }
        }

        private void b() {
            ByteArrayOutputStream byteArrayOutputStream;
            Throwable th;
            try {
                synchronized (jb.class) {
                    if (this.f1453g != null && this.f1450d != null) {
                        byte[] byteArray = new byte[0];
                        try {
                            byteArrayOutputStream = new ByteArrayOutputStream();
                            try {
                                for (ja jaVar : this.f1453g) {
                                    if (jaVar != null) {
                                        byteArrayOutputStream.write(jaVar.a());
                                    }
                                }
                                byteArray = byteArrayOutputStream.toByteArray();
                                try {
                                    byteArrayOutputStream.close();
                                } catch (Throwable th2) {
                                    th = th2;
                                    th.printStackTrace();
                                }
                            } catch (Throwable th3) {
                                th = th3;
                                try {
                                    hn.c(th, "stm", "aStB");
                                    if (byteArrayOutputStream != null) {
                                        try {
                                            byteArrayOutputStream.close();
                                        } catch (Throwable th4) {
                                            th = th4;
                                            th.printStackTrace();
                                        }
                                    }
                                } finally {
                                }
                            }
                        } catch (Throwable th5) {
                            byteArrayOutputStream = null;
                            th = th5;
                        }
                        jb.b(this.f1450d, byteArray);
                    }
                }
            } catch (Throwable th6) {
                hn.c(th6, "stm", "apb");
            }
        }

        private void c() {
            try {
                if (this.f1450d == null) {
                    return;
                }
                iv ivVarA = jc.a(jb.f1445d);
                jc.a(this.f1450d, ivVarA, hl.f1248h, 1000, 307200, "2");
                if (ivVarA.f1415g == null) {
                    ivVarA.f1415g = new jd(new jh(this.f1450d, new je(new ji(new jk()))));
                }
                ivVarA.f1416h = 3600000;
                if (TextUtils.isEmpty(ivVarA.i)) {
                    ivVarA.i = "cKey";
                }
                if (ivVarA.f1414f == null) {
                    ivVarA.f1414f = new jo(this.f1450d, ivVarA.f1416h, ivVarA.i, new jl(30, ivVarA.f1409a, new jm(this.f1450d, jb.f1442a, jb.f1444c * 1024, jb.f1443b * 1024, "staticUpdate", jb.f1446e * 1024)));
                }
                iw.a(ivVarA);
            } catch (Throwable th) {
                hn.c(th, "stm", "usd");
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            int i = this.f1452f;
            if (i == 1) {
                a();
            } else if (i == 2) {
                b();
            } else if (i == 3) {
                c();
            }
        }
    }

    public static synchronized void a(ja jaVar, Context context) {
        hn.d().submit(new a(context, a.f1447a, jaVar));
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x0009, code lost:
    
        if (r4.size() == 0) goto L14;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static synchronized void a(java.util.List<com.amap.api.mapcore.util.ja> r4, android.content.Context r5) {
        /*
            java.lang.Class<com.amap.api.mapcore.util.jb> r0 = com.amap.api.mapcore.util.jb.class
            monitor-enter(r0)
            if (r4 == 0) goto L1f
            int r1 = r4.size()     // Catch: java.lang.Throwable -> Lc
            if (r1 != 0) goto Lc
            goto L1f
        Lc:
            java.util.concurrent.ExecutorService r1 = com.amap.api.mapcore.util.hn.d()     // Catch: java.lang.Throwable -> L1c
            com.amap.api.mapcore.util.jb$a r2 = new com.amap.api.mapcore.util.jb$a     // Catch: java.lang.Throwable -> L1c
            int r3 = com.amap.api.mapcore.util.jb.a.f1448b     // Catch: java.lang.Throwable -> L1c
            r2.<init>(r5, r3, r4)     // Catch: java.lang.Throwable -> L1c
            r1.submit(r2)     // Catch: java.lang.Throwable -> L1c
            monitor-exit(r0)
            return
        L1c:
            r4 = move-exception
            monitor-exit(r0)
            throw r4
        L1f:
            monitor-exit(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.jb.a(java.util.List, android.content.Context):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(Context context, byte[] bArr) throws IOException {
        iv ivVarA = jc.a(f1445d);
        jc.a(context, ivVarA, hl.f1248h, 1000, 307200, "2");
        if (ivVarA.f1413e == null) {
            ivVarA.f1413e = new ia();
        }
        try {
            iw.a(Integer.toString(new Random().nextInt(100)) + Long.toString(System.nanoTime()), bArr, ivVarA);
        } catch (Throwable th) {
            hn.c(th, "stm", "wts");
        }
    }

    public static void a(Context context) {
        hn.d().submit(new a(context, a.f1449c));
    }
}