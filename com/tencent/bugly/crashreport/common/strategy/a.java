package com.tencent.bugly.crashreport.common.strategy;

import android.content.Context;
import com.tencent.bugly.crashreport.biz.b;
import com.tencent.bugly.proguard.ab;
import com.tencent.bugly.proguard.aq;
import com.tencent.bugly.proguard.n;
import com.tencent.bugly.proguard.o;
import com.tencent.bugly.proguard.q;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.y;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes3.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f5433a = 1000;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static a f5434b;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static String f5435h;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final List<com.tencent.bugly.a> f5436c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final x f5437d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final StrategyBean f5438e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private StrategyBean f5439f = null;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private Context f5440g;

    private a(Context context, List<com.tencent.bugly.a> list) {
        String str;
        this.f5440g = context;
        if (com.tencent.bugly.crashreport.common.info.a.a(context) != null) {
            String str2 = com.tencent.bugly.crashreport.common.info.a.a(context).x;
            if (!"oversea".equals(str2)) {
                str = "na_https".equals(str2) ? "https://astat.bugly.cros.wr.pvp.net/:8180/rqd/async" : "https://astat.bugly.qcloud.com/rqd/async";
            }
            StrategyBean.f5425a = str;
            StrategyBean.f5426b = str;
        }
        this.f5438e = new StrategyBean();
        this.f5436c = list;
        this.f5437d = x.a();
    }

    public static synchronized a a(Context context, List<com.tencent.bugly.a> list) {
        if (f5434b == null) {
            f5434b = new a(context, list);
        }
        return f5434b;
    }

    public final void a(long j) {
        this.f5437d.a(new Thread() { // from class: com.tencent.bugly.crashreport.common.strategy.a.1
            @Override // java.lang.Thread, java.lang.Runnable
            public final void run() {
                try {
                    Map<String, byte[]> mapA = o.a().a(a.f5433a, (n) null, true);
                    if (mapA != null) {
                        byte[] bArr = mapA.get("device");
                        byte[] bArr2 = mapA.get("gateway");
                        if (bArr != null) {
                            com.tencent.bugly.crashreport.common.info.a.a(a.this.f5440g).f(new String(bArr));
                        }
                        if (bArr2 != null) {
                            com.tencent.bugly.crashreport.common.info.a.a(a.this.f5440g).e(new String(bArr2));
                        }
                    }
                    a aVar = a.this;
                    a aVar2 = a.this;
                    aVar.f5439f = a.d();
                    if (a.this.f5439f != null) {
                        if (ab.a(a.f5435h) || !ab.c(a.f5435h)) {
                            a.this.f5439f.p = StrategyBean.f5425a;
                            a.this.f5439f.q = StrategyBean.f5426b;
                        } else {
                            a.this.f5439f.p = a.f5435h;
                            a.this.f5439f.q = a.f5435h;
                        }
                    }
                } catch (Throwable th) {
                    if (!y.a(th)) {
                        th.printStackTrace();
                    }
                }
                a aVar3 = a.this;
                aVar3.a(aVar3.f5439f, false);
            }
        }, j);
    }

    public static synchronized a a() {
        return f5434b;
    }

    public final synchronized boolean b() {
        return this.f5439f != null;
    }

    public final StrategyBean c() {
        StrategyBean strategyBean = this.f5439f;
        if (strategyBean != null) {
            if (!ab.c(strategyBean.p)) {
                this.f5439f.p = StrategyBean.f5425a;
            }
            if (!ab.c(this.f5439f.q)) {
                this.f5439f.q = StrategyBean.f5426b;
            }
            return this.f5439f;
        }
        if (!ab.a(f5435h) && ab.c(f5435h)) {
            StrategyBean strategyBean2 = this.f5438e;
            String str = f5435h;
            strategyBean2.p = str;
            strategyBean2.q = str;
        }
        return this.f5438e;
    }

    protected final void a(StrategyBean strategyBean, boolean z) {
        y.c("[Strategy] Notify %s", b.class.getName());
        b.a(strategyBean, z);
        for (com.tencent.bugly.a aVar : this.f5436c) {
            try {
                y.c("[Strategy] Notify %s", aVar.getClass().getName());
                aVar.onServerStrategyChanged(strategyBean);
            } catch (Throwable th) {
                if (!y.a(th)) {
                    th.printStackTrace();
                }
            }
        }
    }

    public static void a(String str) {
        if (ab.a(str) || !ab.c(str)) {
            y.d("URL user set is invalid.", new Object[0]);
        } else {
            f5435h = str;
        }
    }

    public final void a(aq aqVar) {
        if (aqVar == null) {
            return;
        }
        if (this.f5439f == null || aqVar.f5659h != this.f5439f.n) {
            StrategyBean strategyBean = new StrategyBean();
            strategyBean.f5429e = aqVar.f5652a;
            strategyBean.f5431g = aqVar.f5654c;
            strategyBean.f5430f = aqVar.f5653b;
            if (ab.a(f5435h) || !ab.c(f5435h)) {
                if (ab.c(aqVar.f5655d)) {
                    y.c("[Strategy] Upload url changes to %s", aqVar.f5655d);
                    strategyBean.p = aqVar.f5655d;
                }
                if (ab.c(aqVar.f5656e)) {
                    y.c("[Strategy] Exception upload url changes to %s", aqVar.f5656e);
                    strategyBean.q = aqVar.f5656e;
                }
            }
            if (aqVar.f5657f != null && !ab.a(aqVar.f5657f.f5650a)) {
                strategyBean.r = aqVar.f5657f.f5650a;
            }
            if (aqVar.f5659h != 0) {
                strategyBean.n = aqVar.f5659h;
            }
            if (aqVar != null && aqVar.f5658g != null && aqVar.f5658g.size() > 0) {
                strategyBean.s = aqVar.f5658g;
                String str = aqVar.f5658g.get("B11");
                strategyBean.f5432h = str != null && str.equals("1");
                String str2 = aqVar.f5658g.get("B3");
                if (str2 != null) {
                    strategyBean.v = Long.parseLong(str2);
                }
                strategyBean.o = aqVar.i;
                strategyBean.u = aqVar.i;
                String str3 = aqVar.f5658g.get("B27");
                if (str3 != null && str3.length() > 0) {
                    try {
                        int i = Integer.parseInt(str3);
                        if (i > 0) {
                            strategyBean.t = i;
                        }
                    } catch (Exception e2) {
                        if (!y.a(e2)) {
                            e2.printStackTrace();
                        }
                    }
                }
                String str4 = aqVar.f5658g.get("B25");
                strategyBean.j = str4 != null && str4.equals("1");
            }
            y.a("[Strategy] enableCrashReport:%b, enableQuery:%b, enableUserInfo:%b, enableAnr:%b, enableBlock:%b, enableSession:%b, enableSessionTimer:%b, sessionOverTime:%d, enableCocos:%b, strategyLastUpdateTime:%d", Boolean.valueOf(strategyBean.f5429e), Boolean.valueOf(strategyBean.f5431g), Boolean.valueOf(strategyBean.f5430f), Boolean.valueOf(strategyBean.f5432h), Boolean.valueOf(strategyBean.i), Boolean.valueOf(strategyBean.l), Boolean.valueOf(strategyBean.m), Long.valueOf(strategyBean.o), Boolean.valueOf(strategyBean.j), Long.valueOf(strategyBean.n));
            this.f5439f = strategyBean;
            if (!ab.c(aqVar.f5655d)) {
                y.c("[Strategy] download url is null", new Object[0]);
                this.f5439f.p = "";
            }
            if (!ab.c(aqVar.f5656e)) {
                y.c("[Strategy] download crashurl is null", new Object[0]);
                this.f5439f.q = "";
            }
            o.a().b(2);
            q qVar = new q();
            qVar.f5730b = 2;
            qVar.f5729a = strategyBean.f5427c;
            qVar.f5733e = strategyBean.f5428d;
            qVar.f5735g = ab.a(strategyBean);
            o.a().a(qVar);
            a(strategyBean, true);
        }
    }

    public static StrategyBean d() {
        List<q> listA = o.a().a(2);
        if (listA == null || listA.size() <= 0) {
            return null;
        }
        q qVar = listA.get(0);
        if (qVar.f5735g != null) {
            return (StrategyBean) ab.a(qVar.f5735g, StrategyBean.CREATOR);
        }
        return null;
    }
}