package com.tencent.bugly.crashreport.crash;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import android.util.Pair;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.ido.life.constants.Constants;
import com.ido.life.util.DateUtil;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.crashreport.common.info.PlugInBean;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.proguard.ab;
import com.tencent.bugly.proguard.ai;
import com.tencent.bugly.proguard.ak;
import com.tencent.bugly.proguard.al;
import com.tencent.bugly.proguard.am;
import com.tencent.bugly.proguard.an;
import com.tencent.bugly.proguard.j;
import com.tencent.bugly.proguard.n;
import com.tencent.bugly.proguard.o;
import com.tencent.bugly.proguard.t;
import com.tencent.bugly.proguard.u;
import com.tencent.bugly.proguard.v;
import com.tencent.bugly.proguard.y;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.apache.commons.io.IOUtils;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes3.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static int f5498a;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static final Map<Integer, Pair<String, String>> f5499g = new HashMap<Integer, Pair<String, String>>() { // from class: com.tencent.bugly.crashreport.crash.b.1
        {
            put(3, new Pair("203", "103"));
            put(7, new Pair("208", "108"));
            put(0, new Pair("200", "100"));
            put(1, new Pair("201", "101"));
            put(2, new Pair("202", "102"));
            put(4, new Pair("204", "104"));
            put(6, new Pair("206", "106"));
            put(5, new Pair("207", "107"));
        }
    };

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static final ArrayList<a> f5500h = new ArrayList<a>() { // from class: com.tencent.bugly.crashreport.crash.b.2
        {
            byte b2 = 0;
            add(new C0138b(b2));
            add(new c(b2));
            add(new d(b2));
            add(new e(b2));
            add(new h(b2));
            add(new i(b2));
            add(new f(b2));
            add(new g(b2));
        }
    };
    private static final Map<Integer, Integer> i = new HashMap<Integer, Integer>() { // from class: com.tencent.bugly.crashreport.crash.b.3
        {
            put(3, 4);
            put(7, 7);
            put(2, 1);
            put(0, 0);
            put(1, 2);
            put(4, 3);
            put(5, 5);
            put(6, 6);
        }
    };
    private static final Map<Integer, String> j = new HashMap<Integer, String>() { // from class: com.tencent.bugly.crashreport.crash.b.4
        {
            put(3, "BuglyAnrCrash");
            put(0, "BuglyJavaCrash");
            put(1, "BuglyNativeCrash");
        }
    };
    private static final Map<Integer, String> k = new HashMap<Integer, String>() { // from class: com.tencent.bugly.crashreport.crash.b.5
        {
            put(3, "BuglyAnrCrashReport");
            put(0, "BuglyJavaCrashReport");
            put(1, "BuglyNativeCrashReport");
        }
    };

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Context f5501b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private v f5502c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private com.tencent.bugly.crashreport.common.strategy.a f5503d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private n f5504e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private BuglyStrategy.a f5505f;

    static /* synthetic */ void a(b bVar, List list, boolean z, long j2, String str, String str2) {
        if (list == null || list.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            CrashDetailBean crashDetailBean = (CrashDetailBean) it.next();
            String str3 = k.get(Integer.valueOf(crashDetailBean.f5450b));
            if (!TextUtils.isEmpty(str3)) {
                arrayList.add(new t.c(crashDetailBean.f5451c, str3, crashDetailBean.r, z, j2, str, str2));
            }
        }
        t.a().a(arrayList);
    }

    public b(int i2, Context context, v vVar, o oVar, com.tencent.bugly.crashreport.common.strategy.a aVar, BuglyStrategy.a aVar2, n nVar) {
        f5498a = i2;
        this.f5501b = context;
        this.f5502c = vVar;
        this.f5503d = aVar;
        this.f5505f = aVar2;
        this.f5504e = nVar;
    }

    private static List<com.tencent.bugly.crashreport.crash.a> a(List<com.tencent.bugly.crashreport.crash.a> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        ArrayList arrayList = new ArrayList();
        for (com.tencent.bugly.crashreport.crash.a aVar : list) {
            if (aVar.f5460d && aVar.f5458b <= jCurrentTimeMillis - DateUtil.DAY) {
                arrayList.add(aVar);
            }
        }
        return arrayList;
    }

    private CrashDetailBean a(List<com.tencent.bugly.crashreport.crash.a> list, CrashDetailBean crashDetailBean) {
        List<CrashDetailBean> listC;
        if (list == null || list.isEmpty()) {
            return crashDetailBean;
        }
        CrashDetailBean crashDetailBean2 = null;
        ArrayList arrayList = new ArrayList(10);
        for (com.tencent.bugly.crashreport.crash.a aVar : list) {
            if (aVar.f5461e) {
                arrayList.add(aVar);
            }
        }
        if (!arrayList.isEmpty() && (listC = c(arrayList)) != null && !listC.isEmpty()) {
            Collections.sort(listC);
            crashDetailBean2 = listC.get(0);
            a(crashDetailBean2, listC);
        }
        if (crashDetailBean2 == null) {
            crashDetailBean.j = true;
            crashDetailBean.t = 0;
            crashDetailBean.s = "";
            crashDetailBean2 = crashDetailBean;
        }
        b(crashDetailBean2, list);
        if (crashDetailBean2.r != crashDetailBean.r) {
            String str = crashDetailBean2.s;
            StringBuilder sb = new StringBuilder();
            sb.append(crashDetailBean.r);
            if (!str.contains(sb.toString())) {
                crashDetailBean2.t++;
                crashDetailBean2.s += crashDetailBean.r + IOUtils.LINE_SEPARATOR_UNIX;
            }
        }
        return crashDetailBean2;
    }

    private static void a(CrashDetailBean crashDetailBean, List<CrashDetailBean> list) {
        String[] strArrSplit;
        StringBuilder sb = new StringBuilder(128);
        for (int i2 = 1; i2 < list.size(); i2++) {
            CrashDetailBean crashDetailBean2 = list.get(i2);
            if (crashDetailBean2.s != null && (strArrSplit = crashDetailBean2.s.split(IOUtils.LINE_SEPARATOR_UNIX)) != null) {
                for (String str : strArrSplit) {
                    if (!crashDetailBean.s.contains(str)) {
                        crashDetailBean.t++;
                        sb.append(str);
                        sb.append(IOUtils.LINE_SEPARATOR_UNIX);
                    }
                }
            }
        }
        crashDetailBean.s += sb.toString();
    }

    private static void b(CrashDetailBean crashDetailBean, List<com.tencent.bugly.crashreport.crash.a> list) {
        StringBuilder sb = new StringBuilder(64);
        for (com.tencent.bugly.crashreport.crash.a aVar : list) {
            if (!aVar.f5461e && !aVar.f5460d) {
                String str = crashDetailBean.s;
                StringBuilder sb2 = new StringBuilder();
                sb2.append(aVar.f5458b);
                if (!str.contains(sb2.toString())) {
                    crashDetailBean.t++;
                    sb.append(aVar.f5458b);
                    sb.append(IOUtils.LINE_SEPARATOR_UNIX);
                }
            }
        }
        crashDetailBean.s += sb.toString();
    }

    public final boolean a(CrashDetailBean crashDetailBean) {
        return b(crashDetailBean);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0038  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean b(com.tencent.bugly.crashreport.crash.CrashDetailBean r18) {
        /*
            Method dump skipped, instruction units count: 320
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.b.b(com.tencent.bugly.crashreport.crash.CrashDetailBean):boolean");
    }

    private static boolean a(String str) {
        if (com.tencent.bugly.crashreport.crash.c.o != null && !com.tencent.bugly.crashreport.crash.c.o.isEmpty()) {
            try {
                y.c("Crash regular filter for crash stack is: %s", com.tencent.bugly.crashreport.crash.c.o);
                if (Pattern.compile(com.tencent.bugly.crashreport.crash.c.o).matcher(str).find()) {
                    y.d("This crash matches the regular filter string set. It will not be record and upload.", new Object[0]);
                    return true;
                }
            } catch (Exception e2) {
                y.a(e2);
                y.d("Failed to compile " + com.tencent.bugly.crashreport.crash.c.o, new Object[0]);
            }
        }
        return false;
    }

    private static boolean a(CrashDetailBean crashDetailBean, List<com.tencent.bugly.crashreport.crash.a> list, List<com.tencent.bugly.crashreport.crash.a> list2) {
        boolean z = false;
        for (com.tencent.bugly.crashreport.crash.a aVar : list) {
            if (crashDetailBean.u.equals(aVar.f5459c)) {
                if (aVar.f5461e) {
                    z = true;
                }
                list2.add(aVar);
            }
        }
        return z;
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0036, code lost:
    
        if (r0.size() >= com.tencent.bugly.crashreport.crash.c.f5513c) goto L25;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean b(com.tencent.bugly.crashreport.crash.CrashDetailBean r8, java.util.List<com.tencent.bugly.crashreport.crash.a> r9, java.util.List<com.tencent.bugly.crashreport.crash.a> r10) {
        /*
            r7 = this;
            int r0 = r8.f5450b
            r1 = 1
            r2 = 0
            if (r0 == 0) goto Lb
            if (r0 != r1) goto L9
            goto Lb
        L9:
            r3 = r2
            goto Lc
        Lb:
            r3 = r1
        Lc:
            r4 = 3
            if (r0 != r4) goto L11
            r0 = r1
            goto L12
        L11:
            r0 = r2
        L12:
            boolean r4 = com.tencent.bugly.b.f5373c
            if (r4 != 0) goto L1f
            if (r0 != 0) goto L1c
            if (r3 != 0) goto L1c
            r0 = r1
            goto L20
        L1c:
            boolean r0 = com.tencent.bugly.crashreport.crash.c.f5514d
            goto L20
        L1f:
            r0 = r2
        L20:
            if (r0 != 0) goto L23
            return r2
        L23:
            java.util.ArrayList r0 = new java.util.ArrayList
            r3 = 10
            r0.<init>(r3)
            boolean r9 = a(r8, r9, r0)
            if (r9 != 0) goto L38
            int r9 = r0.size()     // Catch: java.lang.Exception -> L6d
            int r3 = com.tencent.bugly.crashreport.crash.c.f5513c     // Catch: java.lang.Exception -> L6d
            if (r9 < r3) goto L78
        L38:
            java.lang.String r9 = "same crash occur too much do merged!"
            java.lang.Object[] r3 = new java.lang.Object[r2]     // Catch: java.lang.Exception -> L6d
            com.tencent.bugly.proguard.y.a(r9, r3)     // Catch: java.lang.Exception -> L6d
            com.tencent.bugly.crashreport.crash.CrashDetailBean r8 = r7.a(r0, r8)     // Catch: java.lang.Exception -> L6d
            java.util.Iterator r9 = r0.iterator()     // Catch: java.lang.Exception -> L6d
        L47:
            boolean r0 = r9.hasNext()     // Catch: java.lang.Exception -> L6d
            if (r0 == 0) goto L5f
            java.lang.Object r0 = r9.next()     // Catch: java.lang.Exception -> L6d
            com.tencent.bugly.crashreport.crash.a r0 = (com.tencent.bugly.crashreport.crash.a) r0     // Catch: java.lang.Exception -> L6d
            long r3 = r0.f5457a     // Catch: java.lang.Exception -> L6d
            long r5 = r8.f5449a     // Catch: java.lang.Exception -> L6d
            int r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r3 == 0) goto L47
            r10.add(r0)     // Catch: java.lang.Exception -> L6d
            goto L47
        L5f:
            r7.e(r8)     // Catch: java.lang.Exception -> L6d
            d(r10)     // Catch: java.lang.Exception -> L6d
            java.lang.String r8 = "[crash] save crash success. For this device crash many times, it will not upload crashes immediately"
            java.lang.Object[] r9 = new java.lang.Object[r2]     // Catch: java.lang.Exception -> L6d
            com.tencent.bugly.proguard.y.b(r8, r9)     // Catch: java.lang.Exception -> L6d
            return r1
        L6d:
            r8 = move-exception
            com.tencent.bugly.proguard.y.a(r8)
            java.lang.Object[] r8 = new java.lang.Object[r2]
            java.lang.String r9 = "Failed to merge crash."
            com.tencent.bugly.proguard.y.d(r9, r8)
        L78:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.b.b(com.tencent.bugly.crashreport.crash.CrashDetailBean, java.util.List, java.util.List):boolean");
    }

    public final List<CrashDetailBean> a() {
        StrategyBean strategyBeanC = com.tencent.bugly.crashreport.common.strategy.a.a().c();
        if (strategyBeanC == null) {
            y.d("have not synced remote!", new Object[0]);
            return null;
        }
        if (!strategyBeanC.f5429e) {
            y.d("Crashreport remote closed, please check your APP ID correct and Version available, then uninstall and reinstall your app.", new Object[0]);
            y.b("[init] WARNING! Crashreport closed by server, please check your APP ID correct and Version available, then uninstall and reinstall your app.", new Object[0]);
            return null;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        long jB = ab.b();
        List<com.tencent.bugly.crashreport.crash.a> listB = b();
        y.c("Size of crash list loaded from DB: %s", Integer.valueOf(listB.size()));
        if (listB == null || listB.size() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        List<com.tencent.bugly.crashreport.crash.a> arrayList2 = new ArrayList<>();
        arrayList.addAll(a(listB));
        listB.removeAll(arrayList);
        Iterator<com.tencent.bugly.crashreport.crash.a> it = listB.iterator();
        while (it.hasNext()) {
            com.tencent.bugly.crashreport.crash.a next = it.next();
            if (next.f5458b < jB - com.tencent.bugly.crashreport.crash.c.f5517g) {
                arrayList2.add(next);
                it.remove();
                arrayList.add(next);
            } else if (next.f5460d) {
                if (next.f5458b >= jCurrentTimeMillis - DateUtil.DAY) {
                    it.remove();
                } else if (!next.f5461e) {
                    it.remove();
                    arrayList.add(next);
                }
            } else if (next.f5462f >= 3 && next.f5458b < jCurrentTimeMillis - DateUtil.DAY) {
                it.remove();
                arrayList.add(next);
            }
        }
        b(arrayList2);
        if (arrayList.size() > 0) {
            d(arrayList);
        }
        ArrayList arrayList3 = new ArrayList();
        List<CrashDetailBean> listC = c(listB);
        if (listC != null && listC.size() > 0) {
            String str = com.tencent.bugly.crashreport.common.info.a.b().i;
            Iterator<CrashDetailBean> it2 = listC.iterator();
            while (it2.hasNext()) {
                CrashDetailBean next2 = it2.next();
                if (!str.equals(next2.f5454f)) {
                    it2.remove();
                    arrayList3.add(next2);
                }
            }
        }
        if (arrayList3.size() > 0) {
            e(arrayList3);
        }
        return listC;
    }

    private void b(List<com.tencent.bugly.crashreport.crash.a> list) {
        List<CrashDetailBean> listC = c(list);
        if (listC == null || listC.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (CrashDetailBean crashDetailBean : listC) {
            String str = k.get(Integer.valueOf(crashDetailBean.f5450b));
            if (!TextUtils.isEmpty(str)) {
                y.c("find expired data,crashId:%s eventType:%s", crashDetailBean.f5451c, str);
                arrayList.add(new t.c(crashDetailBean.f5451c, str, crashDetailBean.r, false, 0L, "expired", null));
            }
        }
        t.a().a(arrayList);
    }

    public final void c(CrashDetailBean crashDetailBean) {
        int i2 = crashDetailBean.f5450b;
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 == 3 && !com.tencent.bugly.crashreport.crash.c.a().r()) {
                    return;
                }
            } else if (!com.tencent.bugly.crashreport.crash.c.a().q()) {
                return;
            }
        } else if (!com.tencent.bugly.crashreport.crash.c.a().q()) {
            return;
        }
        if (this.f5504e != null) {
            y.c("Calling 'onCrashHandleEnd' of RQD crash listener.", new Object[0]);
            n nVar = this.f5504e;
            int i3 = crashDetailBean.f5450b;
        }
    }

    public final void a(CrashDetailBean crashDetailBean, long j2, boolean z) {
        if (com.tencent.bugly.crashreport.crash.c.l) {
            y.a("try to upload right now", new Object[0]);
            ArrayList arrayList = new ArrayList();
            arrayList.add(crashDetailBean);
            a(arrayList, 3000L, z, crashDetailBean.f5450b == 7, z);
            return;
        }
        y.a("do not upload spot crash right now, crash would be uploaded when app next start", new Object[0]);
    }

    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v9, types: [com.tencent.bugly.proguard.ai, com.tencent.bugly.proguard.aj] */
    public final void a(final List<CrashDetailBean> list, long j2, final boolean z, boolean z2, boolean z3) {
        int i2;
        int i3;
        am amVar;
        am amVar2;
        al alVar;
        int i4 = 0;
        if (!com.tencent.bugly.crashreport.common.info.a.a(this.f5501b).f5418e) {
            y.d("warn: not upload process", new Object[0]);
            return;
        }
        v vVar = this.f5502c;
        if (vVar == null) {
            y.d("warn: upload manager is null", new Object[0]);
            return;
        }
        if (!z3 && !vVar.b(com.tencent.bugly.crashreport.crash.c.f5511a)) {
            y.d("warn: not crashHappen or not should upload", new Object[0]);
            return;
        }
        StrategyBean strategyBeanC = this.f5503d.c();
        if (!strategyBeanC.f5429e) {
            y.d("remote report is disable!", new Object[0]);
            y.b("[crash] server closed bugly in this app. please check your appid if is correct, and re-install it", new Object[0]);
            return;
        }
        if (list == null || list.size() == 0) {
            y.d("warn: crashList is null or crashList num is 0", new Object[0]);
            return;
        }
        int i5 = 1;
        try {
            String str = strategyBeanC.q;
            String str2 = StrategyBean.f5426b;
            Context context = this.f5501b;
            com.tencent.bugly.crashreport.common.info.a aVarB = com.tencent.bugly.crashreport.common.info.a.b();
            ?? r2 = 0;
            try {
                if (context == null || list == null || list.size() == 0 || aVarB == null) {
                    i3 = 0;
                    y.d("enEXPPkg args == null!", new Object[0]);
                    amVar = null;
                } else {
                    am amVar3 = new am();
                    amVar3.f5633a = new ArrayList<>();
                    for (CrashDetailBean crashDetailBean : list) {
                        ArrayList<al> arrayList = amVar3.f5633a;
                        if (context == null || crashDetailBean == null || aVarB == null) {
                            amVar2 = amVar3;
                            y.d("enExp args == null", new Object[0]);
                            alVar = null;
                        } else {
                            al alVar2 = new al();
                            alVar2.f5624a = g(crashDetailBean);
                            alVar2.f5625b = crashDetailBean.r;
                            alVar2.f5626c = crashDetailBean.n;
                            alVar2.f5627d = crashDetailBean.o;
                            alVar2.f5628e = crashDetailBean.p;
                            alVar2.f5630g = crashDetailBean.q;
                            alVar2.f5631h = crashDetailBean.z;
                            alVar2.i = crashDetailBean.f5451c;
                            alVar2.j = r2;
                            alVar2.l = crashDetailBean.m;
                            alVar2.m = crashDetailBean.f5453e;
                            alVar2.f5629f = crashDetailBean.B;
                            alVar2.n = r2;
                            alVar2.p = h(crashDetailBean);
                            Object[] objArr = new Object[i5];
                            objArr[i4] = alVar2.o;
                            y.c("libInfo %s", objArr);
                            ArrayList<ak> arrayList2 = new ArrayList<>(20);
                            a(arrayList2, crashDetailBean);
                            a(arrayList2, crashDetailBean.w);
                            b(arrayList2, crashDetailBean.x);
                            c(arrayList2, crashDetailBean.Y);
                            a(arrayList2, crashDetailBean.Z, context);
                            a(arrayList2, crashDetailBean.y);
                            a(arrayList2, crashDetailBean, context);
                            b(arrayList2, crashDetailBean, context);
                            a(arrayList2, aVarB.B);
                            b(arrayList2, crashDetailBean.X);
                            alVar2.q = arrayList2;
                            if (crashDetailBean.j) {
                                alVar2.k = crashDetailBean.t;
                            }
                            alVar2.r = a(crashDetailBean, aVarB);
                            alVar2.s = new HashMap();
                            if (crashDetailBean.R != null && crashDetailBean.R.size() > 0) {
                                alVar2.s.putAll(crashDetailBean.R);
                                Object[] objArr2 = new Object[i5];
                                objArr2[i4] = Integer.valueOf(alVar2.s.size());
                                y.a("setted message size %d", objArr2);
                            }
                            Map<String, String> map = alVar2.s;
                            StringBuilder sb = new StringBuilder("pss:");
                            amVar2 = amVar3;
                            sb.append(crashDetailBean.I);
                            sb.append(" vss:");
                            sb.append(crashDetailBean.J);
                            sb.append(" javaHeap:");
                            sb.append(crashDetailBean.K);
                            y.c(sb.toString(), new Object[i4]);
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append(crashDetailBean.I);
                            map.put("SDK_UPLOAD_U1", sb2.toString());
                            StringBuilder sb3 = new StringBuilder();
                            sb3.append(crashDetailBean.J);
                            map.put("SDK_UPLOAD_U2", sb3.toString());
                            StringBuilder sb4 = new StringBuilder();
                            sb4.append(crashDetailBean.K);
                            map.put("SDK_UPLOAD_U3", sb4.toString());
                            Object[] objArr3 = new Object[12];
                            objArr3[i4] = crashDetailBean.n;
                            try {
                                objArr3[1] = crashDetailBean.f5451c;
                                objArr3[2] = aVarB.e();
                                objArr3[3] = Long.valueOf((crashDetailBean.r - crashDetailBean.P) / 1000);
                                objArr3[4] = Boolean.valueOf(crashDetailBean.k);
                                objArr3[5] = Boolean.valueOf(crashDetailBean.Q);
                                objArr3[6] = Boolean.valueOf(crashDetailBean.j);
                                objArr3[7] = Boolean.valueOf(crashDetailBean.f5450b == 1);
                                objArr3[8] = Integer.valueOf(crashDetailBean.t);
                                objArr3[9] = crashDetailBean.s;
                                objArr3[10] = Boolean.valueOf(crashDetailBean.f5452d);
                                alVar = alVar2;
                                objArr3[11] = Integer.valueOf(alVar.r.size());
                                y.c("%s rid:%s sess:%s ls:%ds isR:%b isF:%b isM:%b isN:%b mc:%d ,%s ,isUp:%b ,vm:%d", objArr3);
                            } catch (Throwable th) {
                                th = th;
                                i2 = 1;
                                Object[] objArr4 = new Object[i2];
                                objArr4[0] = th.toString();
                                y.e("req cr error %s", objArr4);
                                if (y.b(th)) {
                                    return;
                                }
                                th.printStackTrace();
                                return;
                            }
                        }
                        arrayList.add(alVar);
                        amVar3 = amVar2;
                        r2 = 0;
                        i4 = 0;
                        i5 = 1;
                    }
                    amVar = amVar3;
                    i3 = i4;
                }
                if (amVar == null) {
                    y.d("create eupPkg fail!", new Object[i3]);
                    return;
                }
                byte[] bArrA = com.tencent.bugly.proguard.a.a((j) amVar);
                if (bArrA == null) {
                    y.d("send encode fail!", new Object[0]);
                    return;
                }
                an anVarA = com.tencent.bugly.proguard.a.a(this.f5501b, Constants.EventConstants.EVENT_CHECKED_NEW_FLASH, bArrA);
                if (anVarA == null) {
                    y.d("request package is null.", new Object[0]);
                    return;
                }
                final long jCurrentTimeMillis = System.currentTimeMillis();
                u uVar = new u() { // from class: com.tencent.bugly.crashreport.crash.b.6
                    @Override // com.tencent.bugly.proguard.u
                    public final void a(boolean z4, String str3) {
                        b.a(b.this, list, z4, System.currentTimeMillis() - jCurrentTimeMillis, z ? "realtime" : "cache", str3);
                        b bVar = b.this;
                        b.a(z4, (List<CrashDetailBean>) list);
                    }
                };
                if (z) {
                    this.f5502c.a(f5498a, anVarA, str, str2, uVar, j2, z2);
                } else {
                    this.f5502c.a(f5498a, anVarA, str, str2, uVar, false);
                }
            } catch (Throwable th2) {
                th = th2;
                i2 = 1;
            }
        } catch (Throwable th3) {
            th = th3;
            i2 = i5;
        }
    }

    public static void a(boolean z, List<CrashDetailBean> list) {
        if (list != null && list.size() > 0) {
            y.c("up finish update state %b", Boolean.valueOf(z));
            for (CrashDetailBean crashDetailBean : list) {
                y.c("pre uid:%s uc:%d re:%b me:%b", crashDetailBean.f5451c, Integer.valueOf(crashDetailBean.l), Boolean.valueOf(crashDetailBean.f5452d), Boolean.valueOf(crashDetailBean.j));
                crashDetailBean.l++;
                crashDetailBean.f5452d = z;
                y.c("set uid:%s uc:%d re:%b me:%b", crashDetailBean.f5451c, Integer.valueOf(crashDetailBean.l), Boolean.valueOf(crashDetailBean.f5452d), Boolean.valueOf(crashDetailBean.j));
            }
            Iterator<CrashDetailBean> it = list.iterator();
            while (it.hasNext()) {
                com.tencent.bugly.crashreport.crash.c.a().a(it.next());
            }
            y.c("update state size %d", Integer.valueOf(list.size()));
        }
        if (z) {
            return;
        }
        y.b("[crash] upload fail.", new Object[0]);
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x00b7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void d(com.tencent.bugly.crashreport.crash.CrashDetailBean r9) {
        /*
            Method dump skipped, instruction units count: 378
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.b.d(com.tencent.bugly.crashreport.crash.CrashDetailBean):void");
    }

    private static ContentValues f(CrashDetailBean crashDetailBean) {
        if (crashDetailBean == null) {
            return null;
        }
        try {
            ContentValues contentValues = new ContentValues();
            if (crashDetailBean.f5449a > 0) {
                contentValues.put("_id", Long.valueOf(crashDetailBean.f5449a));
            }
            contentValues.put("_tm", Long.valueOf(crashDetailBean.r));
            contentValues.put("_s1", crashDetailBean.u);
            int i2 = 1;
            contentValues.put("_up", Integer.valueOf(crashDetailBean.f5452d ? 1 : 0));
            if (!crashDetailBean.j) {
                i2 = 0;
            }
            contentValues.put("_me", Integer.valueOf(i2));
            contentValues.put("_uc", Integer.valueOf(crashDetailBean.l));
            contentValues.put("_dt", ab.a(crashDetailBean));
            return contentValues;
        } catch (Throwable th) {
            if (!y.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    private static CrashDetailBean a(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            byte[] blob = cursor.getBlob(cursor.getColumnIndex("_dt"));
            if (blob == null) {
                return null;
            }
            long j2 = cursor.getLong(cursor.getColumnIndex("_id"));
            CrashDetailBean crashDetailBean = (CrashDetailBean) ab.a(blob, CrashDetailBean.CREATOR);
            if (crashDetailBean != null) {
                crashDetailBean.f5449a = j2;
            }
            return crashDetailBean;
        } catch (Throwable th) {
            if (!y.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public final void e(CrashDetailBean crashDetailBean) {
        ContentValues contentValuesF;
        if (crashDetailBean == null || (contentValuesF = f(crashDetailBean)) == null) {
            return;
        }
        long jA = o.a().a("t_cr", contentValuesF, (n) null, true);
        if (jA >= 0) {
            y.c("insert %s success!", "t_cr");
            crashDetailBean.f5449a = jA;
        }
    }

    private List<CrashDetailBean> c(List<com.tencent.bugly.crashreport.crash.a> list) {
        Cursor cursorA;
        if (list == null || list.size() == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("_id in ");
        sb.append("(");
        Iterator<com.tencent.bugly.crashreport.crash.a> it = list.iterator();
        while (it.hasNext()) {
            sb.append(it.next().f5457a);
            sb.append(AppInfo.DELIM);
        }
        StringBuilder sb2 = sb.toString().contains(AppInfo.DELIM) ? new StringBuilder(sb.substring(0, sb.lastIndexOf(AppInfo.DELIM))) : sb;
        sb2.append(")");
        String string = sb2.toString();
        sb2.setLength(0);
        try {
            cursorA = o.a().a("t_cr", null, string, null, null, true);
            if (cursorA == null) {
                return null;
            }
            try {
                ArrayList arrayList = new ArrayList();
                sb2.append("_id in ");
                sb2.append("(");
                int i2 = 0;
                while (cursorA.moveToNext()) {
                    CrashDetailBean crashDetailBeanA = a(cursorA);
                    if (crashDetailBeanA != null) {
                        arrayList.add(crashDetailBeanA);
                    } else {
                        try {
                            sb2.append(cursorA.getLong(cursorA.getColumnIndex("_id")));
                            sb2.append(AppInfo.DELIM);
                            i2++;
                        } catch (Throwable unused) {
                            y.d("unknown id!", new Object[0]);
                        }
                    }
                }
                if (sb2.toString().contains(AppInfo.DELIM)) {
                    sb2 = new StringBuilder(sb2.substring(0, sb2.lastIndexOf(AppInfo.DELIM)));
                }
                sb2.append(")");
                String string2 = sb2.toString();
                if (i2 > 0) {
                    y.d("deleted %s illegal data %d", "t_cr", Integer.valueOf(o.a().a("t_cr", string2, (String[]) null, (n) null, true)));
                }
                if (cursorA != null) {
                    cursorA.close();
                }
                return arrayList;
            } catch (Throwable th) {
                th = th;
                try {
                    if (!y.a(th)) {
                        th.printStackTrace();
                    }
                    if (cursorA != null) {
                        cursorA.close();
                    }
                    return null;
                } finally {
                    if (cursorA != null) {
                        cursorA.close();
                    }
                }
            }
        } catch (Throwable th2) {
            th = th2;
            cursorA = null;
        }
    }

    private static com.tencent.bugly.crashreport.crash.a b(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            com.tencent.bugly.crashreport.crash.a aVar = new com.tencent.bugly.crashreport.crash.a();
            aVar.f5457a = cursor.getLong(cursor.getColumnIndex("_id"));
            aVar.f5458b = cursor.getLong(cursor.getColumnIndex("_tm"));
            aVar.f5459c = cursor.getString(cursor.getColumnIndex("_s1"));
            aVar.f5460d = cursor.getInt(cursor.getColumnIndex("_up")) == 1;
            aVar.f5461e = cursor.getInt(cursor.getColumnIndex("_me")) == 1;
            aVar.f5462f = cursor.getInt(cursor.getColumnIndex("_uc"));
            return aVar;
        } catch (Throwable th) {
            if (!y.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    private List<com.tencent.bugly.crashreport.crash.a> b() {
        Cursor cursorA;
        ArrayList arrayList = new ArrayList();
        try {
            cursorA = o.a().a("t_cr", new String[]{"_id", "_tm", "_s1", "_up", "_me", "_uc"}, null, null, null, true);
            if (cursorA == null) {
                return null;
            }
            try {
                if (cursorA.getCount() <= 0) {
                    if (cursorA != null) {
                        cursorA.close();
                    }
                    return arrayList;
                }
                StringBuilder sb = new StringBuilder();
                sb.append("_id in ");
                sb.append("(");
                int i2 = 0;
                while (cursorA.moveToNext()) {
                    com.tencent.bugly.crashreport.crash.a aVarB = b(cursorA);
                    if (aVarB != null) {
                        arrayList.add(aVarB);
                    } else {
                        try {
                            sb.append(cursorA.getLong(cursorA.getColumnIndex("_id")));
                            sb.append(AppInfo.DELIM);
                            i2++;
                        } catch (Throwable unused) {
                            y.d("unknown id!", new Object[0]);
                        }
                    }
                }
                if (sb.toString().contains(AppInfo.DELIM)) {
                    sb = new StringBuilder(sb.substring(0, sb.lastIndexOf(AppInfo.DELIM)));
                }
                sb.append(")");
                String string = sb.toString();
                sb.setLength(0);
                if (i2 > 0) {
                    y.d("deleted %s illegal data %d", "t_cr", Integer.valueOf(o.a().a("t_cr", string, (String[]) null, (n) null, true)));
                }
                if (cursorA != null) {
                    cursorA.close();
                }
                return arrayList;
            } catch (Throwable th) {
                th = th;
                try {
                    if (!y.a(th)) {
                        th.printStackTrace();
                    }
                    if (cursorA != null) {
                        cursorA.close();
                    }
                    return arrayList;
                } finally {
                    if (cursorA != null) {
                        cursorA.close();
                    }
                }
            }
        } catch (Throwable th2) {
            th = th2;
            cursorA = null;
        }
    }

    private static void d(List<com.tencent.bugly.crashreport.crash.a> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("_id in ");
        sb.append("(");
        Iterator<com.tencent.bugly.crashreport.crash.a> it = list.iterator();
        while (it.hasNext()) {
            sb.append(it.next().f5457a);
            sb.append(AppInfo.DELIM);
        }
        StringBuilder sb2 = new StringBuilder(sb.substring(0, sb.lastIndexOf(AppInfo.DELIM)));
        sb2.append(")");
        String string = sb2.toString();
        sb2.setLength(0);
        try {
            y.c("deleted %s data %d", "t_cr", Integer.valueOf(o.a().a("t_cr", string, (String[]) null, (n) null, true)));
        } catch (Throwable th) {
            if (y.a(th)) {
                return;
            }
            th.printStackTrace();
        }
    }

    private static void e(List<CrashDetailBean> list) {
        if (list != null) {
            try {
                if (list.size() == 0) {
                    return;
                }
                StringBuilder sb = new StringBuilder();
                for (CrashDetailBean crashDetailBean : list) {
                    sb.append(" or _id");
                    sb.append(" = ");
                    sb.append(crashDetailBean.f5449a);
                }
                String string = sb.toString();
                if (string.length() > 0) {
                    string = string.substring(4);
                }
                sb.setLength(0);
                y.c("deleted %s data %d", "t_cr", Integer.valueOf(o.a().a("t_cr", string, (String[]) null, (n) null, true)));
            } catch (Throwable th) {
                if (y.a(th)) {
                    return;
                }
                th.printStackTrace();
            }
        }
    }

    private static ak a(String str, Context context, String str2) {
        FileInputStream fileInputStream;
        if (str2 == null || context == null) {
            y.d("rqdp{  createZipAttachment sourcePath == null || context == null ,pls check}", new Object[0]);
            return null;
        }
        y.c("zip %s", str2);
        File file = new File(str2);
        File file2 = new File(context.getCacheDir(), str);
        if (!ab.a(file, file2, 5000)) {
            y.d("zip fail!", new Object[0]);
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            fileInputStream = new FileInputStream(file2);
        } catch (Throwable th) {
            th = th;
            fileInputStream = null;
        }
        try {
            byte[] bArr = new byte[4096];
            while (true) {
                int i2 = fileInputStream.read(bArr);
                if (i2 <= 0) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, i2);
                byteArrayOutputStream.flush();
            }
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            y.c("read bytes :%d", Integer.valueOf(byteArray.length));
            ak akVar = new ak((byte) 2, file2.getName(), byteArray);
            try {
                fileInputStream.close();
            } catch (IOException e2) {
                if (!y.a(e2)) {
                    e2.printStackTrace();
                }
            }
            if (file2.exists()) {
                y.c("del tmp", new Object[0]);
                file2.delete();
            }
            return akVar;
        } catch (Throwable th2) {
            th = th2;
            try {
                if (!y.a(th)) {
                    th.printStackTrace();
                }
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e3) {
                        if (!y.a(e3)) {
                            e3.printStackTrace();
                        }
                    }
                }
                if (file2.exists()) {
                    y.c("del tmp", new Object[0]);
                    file2.delete();
                }
                return null;
            } catch (Throwable th3) {
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e4) {
                        if (!y.a(e4)) {
                            e4.printStackTrace();
                        }
                    }
                }
                if (file2.exists()) {
                    y.c("del tmp", new Object[0]);
                    file2.delete();
                }
                throw th3;
            }
        }
    }

    public static void a(String str, String str2, String str3, String str4, String str5, CrashDetailBean crashDetailBean) {
        String str6;
        com.tencent.bugly.crashreport.common.info.a aVarB = com.tencent.bugly.crashreport.common.info.a.b();
        if (aVarB == null) {
            return;
        }
        y.e("#++++++++++Record By Bugly++++++++++#", new Object[0]);
        y.e("# You can use Bugly(http:\\\\bugly.qq.com) to get more Crash Detail!", new Object[0]);
        y.e("# PKG NAME: %s", aVarB.f5416c);
        y.e("# APP VER: %s", aVarB.i);
        y.e("# SDK VER: %s", aVarB.f5419f);
        y.e("# LAUNCH TIME: %s", ab.a(new Date(com.tencent.bugly.crashreport.common.info.a.b().f5414a)));
        y.e("# CRASH TYPE: %s", str);
        y.e("# CRASH TIME: %s", str2);
        y.e("# CRASH PROCESS: %s", str3);
        y.e("# CRASH FOREGROUND: %s", Boolean.valueOf(aVarB.a()));
        y.e("# CRASH THREAD: %s", str4);
        if (crashDetailBean != null) {
            y.e("# REPORT ID: %s", crashDetailBean.f5451c);
            Object[] objArr = new Object[2];
            objArr[0] = aVarB.l();
            objArr[1] = aVarB.v().booleanValue() ? "ROOTED" : "UNROOT";
            y.e("# CRASH DEVICE: %s %s", objArr);
            y.e("# RUNTIME AVAIL RAM:%d ROM:%d SD:%d", Long.valueOf(crashDetailBean.C), Long.valueOf(crashDetailBean.D), Long.valueOf(crashDetailBean.E));
            y.e("# RUNTIME TOTAL RAM:%d ROM:%d SD:%d", Long.valueOf(crashDetailBean.F), Long.valueOf(crashDetailBean.G), Long.valueOf(crashDetailBean.H));
            if (!ab.a(crashDetailBean.N)) {
                y.e("# EXCEPTION FIRED BY %s %s", crashDetailBean.N, crashDetailBean.M);
            } else if (crashDetailBean.f5450b == 3) {
                Object[] objArr2 = new Object[1];
                if (crashDetailBean.S == null) {
                    str6 = "null";
                } else {
                    str6 = crashDetailBean.S.get("BUGLY_CR_01");
                }
                objArr2[0] = str6;
                y.e("# EXCEPTION ANR MESSAGE:\n %s", objArr2);
            }
        }
        if (!ab.a(str5)) {
            y.e("# CRASH STACK: ", new Object[0]);
            y.e(str5, new Object[0]);
        }
        y.e("#++++++++++++++++++++++++++++++++++++++++++#", new Object[0]);
    }

    private static void a(CrashDetailBean crashDetailBean, Map<String, String> map) {
        String value;
        if (map == null || map.isEmpty()) {
            y.d("extra map is empty. CrashBean won't have userDatas.", new Object[0]);
            return;
        }
        crashDetailBean.R = new LinkedHashMap(map.size());
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (!ab.a(entry.getKey())) {
                String key = entry.getKey();
                if (key.length() > 100) {
                    key = key.substring(0, 100);
                    y.d("setted key length is over limit %d substring to %s", 100, key);
                }
                if (!ab.a(entry.getValue()) && entry.getValue().length() > 100000) {
                    value = entry.getValue().substring(entry.getValue().length() - BuglyStrategy.a.MAX_USERDATA_VALUE_LENGTH);
                    y.d("setted %s value length is over limit %d substring", key, Integer.valueOf(BuglyStrategy.a.MAX_USERDATA_VALUE_LENGTH));
                } else {
                    value = entry.getValue();
                }
                crashDetailBean.R.put(key, value);
                y.a("add setted key %s value size:%d", key, Integer.valueOf(value.length()));
            }
        }
    }

    private static String g(CrashDetailBean crashDetailBean) {
        try {
            Pair<String, String> pair = f5499g.get(Integer.valueOf(crashDetailBean.f5450b));
            if (pair == null) {
                y.e("crash type error! %d", Integer.valueOf(crashDetailBean.f5450b));
                return "";
            }
            if (crashDetailBean.j) {
                return (String) pair.first;
            }
            return (String) pair.second;
        } catch (Exception e2) {
            y.a(e2);
            return "";
        }
    }

    private static ArrayList<ai> h(CrashDetailBean crashDetailBean) {
        if (crashDetailBean.f5456h == null || crashDetailBean.f5456h.isEmpty()) {
            return null;
        }
        ArrayList<ai> arrayList = new ArrayList<>(crashDetailBean.f5456h.size());
        for (Map.Entry<String, PlugInBean> entry : crashDetailBean.f5456h.entrySet()) {
            ai aiVar = new ai();
            aiVar.f5612a = entry.getValue().f5411a;
            aiVar.f5613b = entry.getValue().f5413c;
            aiVar.f5614c = entry.getValue().f5412b;
            arrayList.add(aiVar);
        }
        return arrayList;
    }

    private static void a(ArrayList<ak> arrayList, CrashDetailBean crashDetailBean) {
        if (crashDetailBean.j && crashDetailBean.s != null && crashDetailBean.s.length() > 0) {
            try {
                arrayList.add(new ak((byte) 1, "alltimes.txt", crashDetailBean.s.getBytes("utf-8")));
            } catch (Exception e2) {
                e2.printStackTrace();
                y.a(e2);
            }
        }
    }

    private static void a(ArrayList<ak> arrayList, String str) {
        if (str != null) {
            try {
                arrayList.add(new ak((byte) 1, "log.txt", str.getBytes("utf-8")));
            } catch (Exception e2) {
                e2.printStackTrace();
                y.a(e2);
            }
        }
    }

    private static void b(ArrayList<ak> arrayList, String str) {
        if (str != null) {
            try {
                arrayList.add(new ak((byte) 1, "jniLog.txt", str.getBytes("utf-8")));
            } catch (Exception e2) {
                e2.printStackTrace();
                y.a(e2);
            }
        }
    }

    private static void c(ArrayList<ak> arrayList, String str) {
        if (ab.a(str)) {
            return;
        }
        try {
            ak akVar = new ak((byte) 1, "crashInfos.txt", str.getBytes("utf-8"));
            y.c("attach crash infos", new Object[0]);
            arrayList.add(akVar);
        } catch (Exception e2) {
            e2.printStackTrace();
            y.a(e2);
        }
    }

    private static void a(ArrayList<ak> arrayList, String str, Context context) {
        if (str != null) {
            try {
                ak akVarA = a("backupRecord.zip", context, str);
                if (akVarA != null) {
                    y.c("attach backup record", new Object[0]);
                    arrayList.add(akVarA);
                }
            } catch (Exception e2) {
                y.a(e2);
            }
        }
    }

    private static void a(ArrayList<ak> arrayList, byte[] bArr) {
        if (bArr == null || bArr.length <= 0) {
            return;
        }
        try {
            ak akVar = new ak((byte) 2, "buglylog.zip", bArr);
            y.c("attach user log", new Object[0]);
            arrayList.add(akVar);
        } catch (Exception e2) {
            y.a(e2);
        }
    }

    private static void a(ArrayList<ak> arrayList, CrashDetailBean crashDetailBean, Context context) {
        ak akVarA;
        if (crashDetailBean.f5450b != 3) {
            return;
        }
        y.c("crashBean.anrMessages:%s", crashDetailBean.S);
        try {
            if (crashDetailBean.S != null && crashDetailBean.S.containsKey("BUGLY_CR_01")) {
                if (!TextUtils.isEmpty(crashDetailBean.S.get("BUGLY_CR_01"))) {
                    arrayList.add(new ak((byte) 1, "anrMessage.txt", crashDetailBean.S.get("BUGLY_CR_01").getBytes("utf-8")));
                    y.c("attach anr message", new Object[0]);
                }
                crashDetailBean.S.remove("BUGLY_CR_01");
            }
            if (crashDetailBean.v == null || (akVarA = a("trace.zip", context, crashDetailBean.v)) == null) {
                return;
            }
            y.c("attach traces", new Object[0]);
            arrayList.add(akVarA);
        } catch (Exception e2) {
            e2.printStackTrace();
            y.a(e2);
        }
    }

    private static void b(ArrayList<ak> arrayList, CrashDetailBean crashDetailBean, Context context) {
        if (crashDetailBean.f5450b == 1 && crashDetailBean.v != null) {
            try {
                ak akVarA = a("tomb.zip", context, crashDetailBean.v);
                if (akVarA != null) {
                    y.c("attach tombs", new Object[0]);
                    arrayList.add(akVarA);
                }
            } catch (Exception e2) {
                y.a(e2);
            }
        }
    }

    private static void a(ArrayList<ak> arrayList, List<String> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
        }
        try {
            arrayList.add(new ak((byte) 1, "martianlog.txt", sb.toString().getBytes("utf-8")));
            y.c("attach pageTracingList", new Object[0]);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private static void b(ArrayList<ak> arrayList, byte[] bArr) {
        if (bArr == null || bArr.length <= 0) {
            return;
        }
        try {
            arrayList.add(new ak((byte) 1, "userExtraByteData", bArr));
            y.c("attach extraData", new Object[0]);
        } catch (Exception e2) {
            y.a(e2);
        }
    }

    private static Map<String, String> a(CrashDetailBean crashDetailBean, com.tencent.bugly.crashreport.common.info.a aVar) {
        HashMap map = new HashMap(30);
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(crashDetailBean.C);
            map.put("A9", sb.toString());
            StringBuilder sb2 = new StringBuilder();
            sb2.append(crashDetailBean.D);
            map.put("A11", sb2.toString());
            StringBuilder sb3 = new StringBuilder();
            sb3.append(crashDetailBean.E);
            map.put("A10", sb3.toString());
            map.put("A23", crashDetailBean.f5454f);
            StringBuilder sb4 = new StringBuilder();
            aVar.getClass();
            map.put("A7", sb4.toString());
            map.put("A6", com.tencent.bugly.crashreport.common.info.a.r());
            map.put("A5", aVar.q());
            map.put("A22", aVar.k());
            StringBuilder sb5 = new StringBuilder();
            sb5.append(crashDetailBean.G);
            map.put("A2", sb5.toString());
            StringBuilder sb6 = new StringBuilder();
            sb6.append(crashDetailBean.F);
            map.put("A1", sb6.toString());
            map.put("A24", aVar.f5420g);
            StringBuilder sb7 = new StringBuilder();
            sb7.append(crashDetailBean.H);
            map.put("A17", sb7.toString());
            map.put("A25", aVar.k());
            map.put("A15", aVar.u());
            StringBuilder sb8 = new StringBuilder();
            sb8.append(aVar.v());
            map.put("A13", sb8.toString());
            map.put("A34", crashDetailBean.A);
            if (aVar.w != null) {
                map.put("productIdentify", aVar.w);
            }
            map.put("A26", URLEncoder.encode(crashDetailBean.L, "utf-8"));
            boolean z = true;
            if (crashDetailBean.f5450b == 1) {
                map.put("A27", crashDetailBean.N);
                map.put("A28", crashDetailBean.M);
                StringBuilder sb9 = new StringBuilder();
                sb9.append(crashDetailBean.k);
                map.put("A29", sb9.toString());
            }
            map.put("A30", crashDetailBean.O);
            StringBuilder sb10 = new StringBuilder();
            sb10.append(crashDetailBean.P);
            map.put("A18", sb10.toString());
            StringBuilder sb11 = new StringBuilder();
            if (crashDetailBean.Q) {
                z = false;
            }
            sb11.append(z);
            map.put("A36", sb11.toString());
            StringBuilder sb12 = new StringBuilder();
            sb12.append(aVar.p);
            map.put("F02", sb12.toString());
            StringBuilder sb13 = new StringBuilder();
            sb13.append(aVar.q);
            map.put("F03", sb13.toString());
            map.put("F04", aVar.e());
            StringBuilder sb14 = new StringBuilder();
            sb14.append(aVar.r);
            map.put("F05", sb14.toString());
            map.put("F06", aVar.o);
            map.put("F08", aVar.u);
            map.put("F09", aVar.v);
            StringBuilder sb15 = new StringBuilder();
            sb15.append(aVar.s);
            map.put("F10", sb15.toString());
            a(map, crashDetailBean);
        } catch (Exception e2) {
            e2.printStackTrace();
            y.a(e2);
        }
        return map;
    }

    private static void a(Map<String, String> map, CrashDetailBean crashDetailBean) {
        if (crashDetailBean.T >= 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(crashDetailBean.T);
            map.put("C01", sb.toString());
        }
        if (crashDetailBean.U >= 0) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(crashDetailBean.U);
            map.put("C02", sb2.toString());
        }
        if (crashDetailBean.V != null && crashDetailBean.V.size() > 0) {
            for (Map.Entry<String, String> entry : crashDetailBean.V.entrySet()) {
                map.put("C03_" + entry.getKey(), entry.getValue());
            }
        }
        if (crashDetailBean.W == null || crashDetailBean.W.size() <= 0) {
            return;
        }
        for (Map.Entry<String, String> entry2 : crashDetailBean.W.entrySet()) {
            map.put("C04_" + entry2.getKey(), entry2.getValue());
        }
    }

    /* JADX INFO: compiled from: BUGLY */
    static abstract class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final int f5510a;

        abstract boolean a();

        /* synthetic */ a(int i, byte b2) {
            this(i);
        }

        static /* synthetic */ boolean a(a aVar, CrashDetailBean crashDetailBean) {
            return aVar.f5510a == crashDetailBean.f5450b;
        }

        private a(int i) {
            this.f5510a = i;
        }
    }

    /* JADX INFO: renamed from: com.tencent.bugly.crashreport.crash.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: BUGLY */
    static class C0138b extends a {
        /* synthetic */ C0138b(byte b2) {
            this();
        }

        private C0138b() {
            super(3, (byte) 0);
        }

        @Override // com.tencent.bugly.crashreport.crash.b.a
        final boolean a() {
            return com.tencent.bugly.crashreport.crash.c.a().r();
        }
    }

    /* JADX INFO: compiled from: BUGLY */
    static class c extends a {
        @Override // com.tencent.bugly.crashreport.crash.b.a
        final boolean a() {
            return true;
        }

        /* synthetic */ c(byte b2) {
            this();
        }

        private c() {
            super(7, (byte) 0);
        }
    }

    /* JADX INFO: compiled from: BUGLY */
    static class d extends a {
        @Override // com.tencent.bugly.crashreport.crash.b.a
        final boolean a() {
            return true;
        }

        /* synthetic */ d(byte b2) {
            this();
        }

        private d() {
            super(2, (byte) 0);
        }
    }

    /* JADX INFO: compiled from: BUGLY */
    static class e extends a {
        /* synthetic */ e(byte b2) {
            this();
        }

        /* JADX WARN: Illegal instructions before constructor call */
        private e() {
            byte b2 = 0;
            super(b2, b2);
        }

        @Override // com.tencent.bugly.crashreport.crash.b.a
        final boolean a() {
            return com.tencent.bugly.crashreport.crash.c.a().q();
        }
    }

    /* JADX INFO: compiled from: BUGLY */
    static class h extends a {
        /* synthetic */ h(byte b2) {
            this();
        }

        private h() {
            super(1, (byte) 0);
        }

        @Override // com.tencent.bugly.crashreport.crash.b.a
        final boolean a() {
            return com.tencent.bugly.crashreport.crash.c.a().q();
        }
    }

    /* JADX INFO: compiled from: BUGLY */
    static class i extends a {
        /* synthetic */ i(byte b2) {
            this();
        }

        private i() {
            super(4, (byte) 0);
        }

        @Override // com.tencent.bugly.crashreport.crash.b.a
        final boolean a() {
            return com.tencent.bugly.crashreport.crash.c.a().s();
        }
    }

    /* JADX INFO: compiled from: BUGLY */
    static class f extends a {
        /* synthetic */ f(byte b2) {
            this();
        }

        private f() {
            super(5, (byte) 0);
        }

        @Override // com.tencent.bugly.crashreport.crash.b.a
        final boolean a() {
            return com.tencent.bugly.crashreport.crash.c.a().t();
        }
    }

    /* JADX INFO: compiled from: BUGLY */
    static class g extends a {
        /* synthetic */ g(byte b2) {
            this();
        }

        private g() {
            super(6, (byte) 0);
        }

        @Override // com.tencent.bugly.crashreport.crash.b.a
        final boolean a() {
            return com.tencent.bugly.crashreport.crash.c.a().u();
        }
    }
}