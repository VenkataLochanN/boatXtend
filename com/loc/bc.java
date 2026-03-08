package com.loc;

import android.content.Context;
import android.text.TextUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Random;

/* JADX INFO: compiled from: StatisticsManager.java */
/* JADX INFO: loaded from: classes3.dex */
public class bc {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static boolean f4840a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static int f4841b = 20;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static int f4842c = 20;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static WeakReference<ax> f4843d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static int f4844e;

    /* JADX INFO: compiled from: StatisticsManager.java */
    static class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static int f4845a = 1;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        static int f4846b = 2;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        static int f4847c = 3;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private Context f4848d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private bb f4849e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private int f4850f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private List<bb> f4851g;

        a(Context context, int i) {
            this.f4848d = context;
            this.f4850f = i;
        }

        a(Context context, int i, bb bbVar) {
            this(context, i);
            this.f4849e = bbVar;
        }

        a(Context context, int i, List<bb> list) {
            this(context, i);
            this.f4851g = list;
        }

        @Override // java.lang.Runnable
        public final void run() {
            String str;
            String str2;
            ByteArrayOutputStream byteArrayOutputStream;
            Throwable th;
            int i = this.f4850f;
            if (i == 1) {
                try {
                    if (this.f4848d != null && this.f4849e != null) {
                        synchronized (bc.class) {
                            if (this.f4848d != null && this.f4849e != null) {
                                bc.a(this.f4848d, this.f4849e.a());
                                return;
                            }
                            return;
                        }
                    }
                    return;
                } catch (Throwable th2) {
                    th = th2;
                    str = "stm";
                    str2 = "as";
                }
            } else {
                if (i != 2) {
                    if (i == 3) {
                        try {
                            if (this.f4848d == null) {
                                return;
                            }
                            ax axVarA = bd.a(bc.f4843d);
                            bd.a(this.f4848d, axVarA, z.f5350h, 1000, 307200, "2");
                            if (axVarA.f4820g == null) {
                                axVarA.f4820g = new be(new bi(this.f4848d, new bf(new bj(new bl()))));
                            }
                            axVarA.f4821h = 3600000;
                            if (TextUtils.isEmpty(axVarA.i)) {
                                axVarA.i = "cKey";
                            }
                            if (axVarA.f4819f == null) {
                                axVarA.f4819f = new bp(this.f4848d, axVarA.f4821h, axVarA.i, new bm(axVarA.f4814a, new bn(this.f4848d, bc.f4840a, bc.f4842c * 1024, bc.f4841b * 1024, "staticUpdate", bc.f4844e * 1024)));
                            }
                            ay.a(axVarA);
                            return;
                        } catch (Throwable th3) {
                            ab.b(th3, "stm", "usd");
                            return;
                        }
                    }
                    return;
                }
                try {
                    synchronized (bc.class) {
                        if (this.f4851g != null && this.f4848d != null) {
                            byte[] byteArray = new byte[0];
                            try {
                                byteArrayOutputStream = new ByteArrayOutputStream();
                            } catch (Throwable th4) {
                                th = th4;
                                byteArrayOutputStream = null;
                            }
                            try {
                                for (bb bbVar : this.f4851g) {
                                    if (bbVar != null) {
                                        byteArrayOutputStream.write(bbVar.a());
                                    }
                                }
                                byteArray = byteArrayOutputStream.toByteArray();
                                try {
                                    byteArrayOutputStream.close();
                                } catch (Throwable th5) {
                                    th = th5;
                                    th.printStackTrace();
                                }
                            } catch (Throwable th6) {
                                th = th6;
                                try {
                                    ab.b(th, "stm", "aStB");
                                    if (byteArrayOutputStream != null) {
                                        try {
                                            byteArrayOutputStream.close();
                                        } catch (Throwable th7) {
                                            th = th7;
                                            th.printStackTrace();
                                        }
                                    }
                                } finally {
                                }
                            }
                            bc.a(this.f4848d, byteArray);
                            return;
                        }
                        return;
                    }
                } catch (Throwable th8) {
                    th = th8;
                    str = "stm";
                    str2 = "apb";
                }
            }
            ab.b(th, str, str2);
        }
    }

    public static void a(Context context) {
        ab.d().submit(new a(context, a.f4847c));
    }

    static /* synthetic */ void a(Context context, byte[] bArr) throws IOException {
        ax axVarA = bd.a(f4843d);
        bd.a(context, axVarA, z.f5350h, 1000, 307200, "2");
        if (axVarA.f4818e == null) {
            axVarA.f4818e = new am();
        }
        try {
            ay.a(Integer.toString(new Random().nextInt(100)) + Long.toString(System.nanoTime()), bArr, axVarA);
        } catch (Throwable th) {
            ab.b(th, "stm", "wts");
        }
    }

    public static synchronized void a(bb bbVar, Context context) {
        ab.d().submit(new a(context, a.f4845a, bbVar));
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x0009, code lost:
    
        if (r4.size() == 0) goto L14;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static synchronized void a(java.util.List<com.loc.bb> r4, android.content.Context r5) {
        /*
            java.lang.Class<com.loc.bc> r0 = com.loc.bc.class
            monitor-enter(r0)
            if (r4 == 0) goto L1f
            int r1 = r4.size()     // Catch: java.lang.Throwable -> Lc
            if (r1 != 0) goto Lc
            goto L1f
        Lc:
            java.util.concurrent.ExecutorService r1 = com.loc.ab.d()     // Catch: java.lang.Throwable -> L1c
            com.loc.bc$a r2 = new com.loc.bc$a     // Catch: java.lang.Throwable -> L1c
            int r3 = com.loc.bc.a.f4846b     // Catch: java.lang.Throwable -> L1c
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
        throw new UnsupportedOperationException("Method not decompiled: com.loc.bc.a(java.util.List, android.content.Context):void");
    }

    public static synchronized void a(boolean z, int i) {
        f4840a = z;
        f4844e = Math.max(0, i);
    }

    public static synchronized void b(List<bb> list, Context context) {
        try {
            List<bb> listB = as.b();
            if (listB != null && listB.size() > 0) {
                list.addAll(listB);
            }
        } catch (Throwable unused) {
        }
        a(list, context);
    }
}