package com.tencent.bugly;

import android.content.Context;
import android.text.TextUtils;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.crash.BuglyBroadcastReceiver;
import com.tencent.bugly.crashreport.crash.c;
import com.tencent.bugly.crashreport.crash.d;
import com.tencent.bugly.proguard.m;
import com.tencent.bugly.proguard.y;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes3.dex */
public class CrashModule extends a {
    public static final int MODULE_ID = 1004;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static int f5366c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static CrashModule f5367e = new CrashModule();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private long f5368a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private BuglyStrategy.a f5369b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f5370d = false;

    public static CrashModule getInstance() {
        CrashModule crashModule = f5367e;
        crashModule.id = 1004;
        return crashModule;
    }

    public synchronized boolean hasInitialized() {
        return this.f5370d;
    }

    @Override // com.tencent.bugly.a
    public synchronized void init(Context context, boolean z, BuglyStrategy buglyStrategy) {
        if (context != null) {
            if (!this.f5370d) {
                y.a("Initializing crash module.", new Object[0]);
                m mVarA = m.a();
                int i = f5366c + 1;
                f5366c = i;
                mVarA.a(1004, i);
                this.f5370d = true;
                CrashReport.setContext(context);
                a(context, buglyStrategy);
                c cVarA = c.a(1004, context, z, this.f5369b, null, null);
                cVarA.f();
                if (buglyStrategy != null) {
                    cVarA.a(buglyStrategy.getCallBackType());
                    cVarA.a(buglyStrategy.getCloseErrorCallback());
                    c.l = buglyStrategy.isUploadSpotCrash();
                    com.tencent.bugly.crashreport.common.info.a.a(context).b(buglyStrategy.isEnableRecordAnrMainStack());
                }
                if (buglyStrategy != null && buglyStrategy.isEnableCatchAnrTrace()) {
                    cVarA.k();
                }
                cVarA.o();
                if (buglyStrategy == null || buglyStrategy.isEnableNativeCrashMonitor()) {
                    cVarA.h();
                } else {
                    y.a("[crash] Closed native crash monitor!", new Object[0]);
                    cVarA.g();
                }
                if (buglyStrategy == null || buglyStrategy.isEnableANRCrashMonitor()) {
                    cVarA.i();
                } else {
                    y.a("[crash] Closed ANR monitor!", new Object[0]);
                    cVarA.j();
                }
                if (buglyStrategy != null) {
                    c.f5514d = buglyStrategy.isMerged();
                }
                cVarA.a(buglyStrategy != null ? buglyStrategy.getAppReportDelay() : 0L);
                cVarA.n();
                d.a(context);
                BuglyBroadcastReceiver buglyBroadcastReceiver = BuglyBroadcastReceiver.getInstance();
                buglyBroadcastReceiver.addFilter("android.net.conn.CONNECTIVITY_CHANGE");
                buglyBroadcastReceiver.register(context);
                m mVarA2 = m.a();
                int i2 = f5366c - 1;
                f5366c = i2;
                mVarA2.a(1004, i2);
            }
        }
    }

    private synchronized void a(Context context, BuglyStrategy buglyStrategy) {
        if (buglyStrategy == null) {
            return;
        }
        String libBuglySOFilePath = buglyStrategy.getLibBuglySOFilePath();
        if (!TextUtils.isEmpty(libBuglySOFilePath)) {
            com.tencent.bugly.crashreport.common.info.a.a(context).l = libBuglySOFilePath;
            y.a("setted libBugly.so file path :%s", libBuglySOFilePath);
        }
        if (buglyStrategy.getCrashHandleCallback() != null) {
            this.f5369b = buglyStrategy.getCrashHandleCallback();
            y.a("setted CrashHanldeCallback", new Object[0]);
        }
        if (buglyStrategy.getAppReportDelay() > 0) {
            this.f5368a = buglyStrategy.getAppReportDelay();
            y.a("setted delay: %d", Long.valueOf(this.f5368a));
        }
    }

    @Override // com.tencent.bugly.a
    public void onServerStrategyChanged(StrategyBean strategyBean) {
        c cVarA;
        if (strategyBean == null || (cVarA = c.a()) == null) {
            return;
        }
        cVarA.a(strategyBean);
    }

    @Override // com.tencent.bugly.a
    public String[] getTables() {
        return new String[]{"t_cr"};
    }
}