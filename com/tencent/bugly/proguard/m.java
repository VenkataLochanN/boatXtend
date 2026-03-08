package com.tencent.bugly.proguard;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.ido.life.util.DateUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes3.dex */
public final class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final long f5703a = System.currentTimeMillis();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static m f5704b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Context f5705c;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private SharedPreferences f5708f;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Map<Integer, Map<String, l>> f5707e = new HashMap();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f5706d = com.tencent.bugly.crashreport.common.info.a.b().f5417d;

    static /* synthetic */ boolean a(l lVar, l lVar2) {
        return lVar.f5702g == lVar2.f5702g && lVar.f5697b != null && lVar.f5697b.equalsIgnoreCase(lVar2.f5697b);
    }

    static /* synthetic */ boolean b(l lVar, l lVar2) {
        if (lVar.f5700e == null || lVar.f5700e.equalsIgnoreCase(lVar2.f5700e)) {
            return !(lVar.f5701f == null || lVar.f5701f.equalsIgnoreCase(lVar2.f5701f)) || lVar.f5699d <= 0;
        }
        return true;
    }

    private m(Context context) {
        this.f5705c = context;
        this.f5708f = context.getSharedPreferences("crashrecord", 0);
    }

    public static synchronized m a(Context context) {
        if (f5704b == null) {
            f5704b = new m(context);
        }
        return f5704b;
    }

    public static synchronized m a() {
        return f5704b;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized boolean b(int i) {
        try {
            List<l> listC = c(i);
            if (listC == null) {
                return false;
            }
            long jCurrentTimeMillis = System.currentTimeMillis();
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            for (l lVar : listC) {
                if (lVar.f5697b != null && lVar.f5697b.equalsIgnoreCase(this.f5706d) && lVar.f5699d > 0) {
                    arrayList.add(lVar);
                }
                if (lVar.f5698c + DateUtil.DAY < jCurrentTimeMillis) {
                    arrayList2.add(lVar);
                }
            }
            Collections.sort(arrayList);
            if (arrayList.size() >= 2) {
                if (arrayList.size() <= 0 || ((l) arrayList.get(arrayList.size() - 1)).f5698c + DateUtil.DAY >= jCurrentTimeMillis) {
                    return true;
                }
                listC.clear();
                a(i, listC);
                return false;
            }
            listC.removeAll(arrayList2);
            a(i, listC);
            return false;
        } catch (Exception unused) {
            y.e("isFrequentCrash failed", new Object[0]);
            return false;
        }
    }

    public final void a(int i, final int i2) {
        final int i3 = 1004;
        x.a().a(new Runnable() { // from class: com.tencent.bugly.proguard.m.1
            @Override // java.lang.Runnable
            public final void run() {
                l lVar;
                try {
                    if (TextUtils.isEmpty(m.this.f5706d)) {
                        return;
                    }
                    List<l> listC = m.this.c(i3);
                    if (listC == null) {
                        listC = new ArrayList();
                    }
                    if (m.this.f5707e.get(Integer.valueOf(i3)) == null) {
                        m.this.f5707e.put(Integer.valueOf(i3), new HashMap());
                    }
                    if (((Map) m.this.f5707e.get(Integer.valueOf(i3))).get(m.this.f5706d) != null) {
                        lVar = (l) ((Map) m.this.f5707e.get(Integer.valueOf(i3))).get(m.this.f5706d);
                        lVar.f5699d = i2;
                    } else {
                        lVar = new l();
                        lVar.f5696a = i3;
                        lVar.f5702g = m.f5703a;
                        lVar.f5697b = m.this.f5706d;
                        lVar.f5701f = com.tencent.bugly.crashreport.common.info.a.b().i;
                        lVar.f5700e = com.tencent.bugly.crashreport.common.info.a.b().f5419f;
                        lVar.f5698c = System.currentTimeMillis();
                        lVar.f5699d = i2;
                        ((Map) m.this.f5707e.get(Integer.valueOf(i3))).put(m.this.f5706d, lVar);
                    }
                    ArrayList arrayList = new ArrayList();
                    boolean z = false;
                    for (l lVar2 : listC) {
                        if (m.a(lVar2, lVar)) {
                            z = true;
                            lVar2.f5699d = lVar.f5699d;
                        }
                        if (m.b(lVar2, lVar)) {
                            arrayList.add(lVar2);
                        }
                    }
                    listC.removeAll(arrayList);
                    if (!z) {
                        listC.add(lVar);
                    }
                    m.this.a(i3, listC);
                } catch (Exception unused) {
                    y.e("saveCrashRecord failed", new Object[0]);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0046 A[Catch: all -> 0x005c, Exception -> 0x005e, PHI: r6
  0x0046: PHI (r6v10 java.io.ObjectInputStream) = (r6v9 java.io.ObjectInputStream), (r6v11 java.io.ObjectInputStream) binds: [B:17:0x0044, B:22:0x0052] A[DONT_GENERATE, DONT_INLINE], TRY_ENTER, TRY_LEAVE, TryCatch #1 {Exception -> 0x005e, blocks: (B:4:0x0003, B:10:0x0034, B:18:0x0046, B:26:0x0058, B:27:0x005b), top: B:37:0x0003, outer: #4 }] */
    /* JADX WARN: Type inference failed for: r6v4, types: [boolean] */
    /* JADX WARN: Type inference failed for: r6v5, types: [java.io.ObjectInputStream] */
    /* JADX WARN: Type inference failed for: r6v6 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized <T extends java.util.List<?>> T c(int r6) {
        /*
            r5 = this;
            monitor-enter(r5)
            r0 = 0
            r1 = 0
            java.io.File r2 = new java.io.File     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            android.content.Context r3 = r5.f5705c     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            java.lang.String r4 = "crashrecord"
            java.io.File r3 = r3.getDir(r4, r1)     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            r4.<init>()     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            r4.append(r6)     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            java.lang.String r6 = r4.toString()     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            r2.<init>(r3, r6)     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            boolean r6 = r2.exists()     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            if (r6 != 0) goto L24
            monitor-exit(r5)
            return r0
        L24:
            java.io.ObjectInputStream r6 = new java.io.ObjectInputStream     // Catch: java.lang.Throwable -> L39 java.lang.ClassNotFoundException -> L3c java.io.IOException -> L4a
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L39 java.lang.ClassNotFoundException -> L3c java.io.IOException -> L4a
            r3.<init>(r2)     // Catch: java.lang.Throwable -> L39 java.lang.ClassNotFoundException -> L3c java.io.IOException -> L4a
            r6.<init>(r3)     // Catch: java.lang.Throwable -> L39 java.lang.ClassNotFoundException -> L3c java.io.IOException -> L4a
            java.lang.Object r2 = r6.readObject()     // Catch: java.lang.ClassNotFoundException -> L3d java.io.IOException -> L4b java.lang.Throwable -> L55
            java.util.List r2 = (java.util.List) r2     // Catch: java.lang.ClassNotFoundException -> L3d java.io.IOException -> L4b java.lang.Throwable -> L55
            r6.close()     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            monitor-exit(r5)
            return r2
        L39:
            r2 = move-exception
            r6 = r0
            goto L56
        L3c:
            r6 = r0
        L3d:
            java.lang.String r2 = "get object error"
            java.lang.Object[] r3 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L55
            com.tencent.bugly.proguard.y.a(r2, r3)     // Catch: java.lang.Throwable -> L55
            if (r6 == 0) goto L65
        L46:
            r6.close()     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
            goto L65
        L4a:
            r6 = r0
        L4b:
            java.lang.String r2 = "open record file error"
            java.lang.Object[] r3 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L55
            com.tencent.bugly.proguard.y.a(r2, r3)     // Catch: java.lang.Throwable -> L55
            if (r6 == 0) goto L65
            goto L46
        L55:
            r2 = move-exception
        L56:
            if (r6 == 0) goto L5b
            r6.close()     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
        L5b:
            throw r2     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5e
        L5c:
            r6 = move-exception
            goto L67
        L5e:
            java.lang.String r6 = "readCrashRecord error"
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L5c
            com.tencent.bugly.proguard.y.e(r6, r1)     // Catch: java.lang.Throwable -> L5c
        L65:
            monitor-exit(r5)
            return r0
        L67:
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.m.c(int):java.util.List");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized <T extends List<?>> void a(int i, T t) {
        File dir;
        StringBuilder sb;
        ObjectOutputStream objectOutputStream;
        if (t == null) {
            return;
        }
        try {
            dir = this.f5705c.getDir("crashrecord", 0);
            sb = new StringBuilder();
            sb.append(i);
            objectOutputStream = null;
        } catch (Exception unused) {
            y.e("writeCrashRecord error", new Object[0]);
        }
        try {
            try {
                ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(new FileOutputStream(new File(dir, sb.toString())));
                try {
                    objectOutputStream2.writeObject(t);
                    objectOutputStream2.close();
                } catch (IOException e2) {
                    e = e2;
                    objectOutputStream = objectOutputStream2;
                    e.printStackTrace();
                    y.a("open record file error", new Object[0]);
                    if (objectOutputStream == null) {
                    } else {
                        objectOutputStream.close();
                    }
                } catch (Throwable th) {
                    th = th;
                    objectOutputStream = objectOutputStream2;
                    if (objectOutputStream != null) {
                        objectOutputStream.close();
                    }
                    throw th;
                }
            } catch (IOException e3) {
                e = e3;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public final synchronized boolean a(final int i) {
        boolean z;
        z = true;
        try {
            z = this.f5708f.getBoolean(i + "_" + this.f5706d, true);
            x.a().a(new Runnable() { // from class: com.tencent.bugly.proguard.m.2
                @Override // java.lang.Runnable
                public final void run() {
                    boolean zB = m.this.b(i);
                    m.this.f5708f.edit().putBoolean(i + "_" + m.this.f5706d, !zB).commit();
                }
            });
        } catch (Exception unused) {
            y.e("canInit error", new Object[0]);
            return z;
        }
        return z;
    }
}