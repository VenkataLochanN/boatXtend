package com.tencent.bugly;

import java.util.Map;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes3.dex */
public class BuglyStrategy {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f5360c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f5361d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f5362e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private long f5363f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private String f5364g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private String f5365h;
    private String i;
    private a u;
    private boolean j = true;
    private boolean k = true;
    private boolean l = false;
    private boolean m = false;
    private boolean n = true;
    private Class<?> o = null;
    private boolean p = true;
    private boolean q = true;
    private boolean r = true;
    private boolean s = true;
    private boolean t = false;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected int f5358a = 31;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected boolean f5359b = false;
    private boolean v = false;

    public synchronized BuglyStrategy setBuglyLogUpload(boolean z) {
        this.p = z;
        return this;
    }

    public synchronized BuglyStrategy setRecordUserInfoOnceADay(boolean z) {
        this.t = z;
        return this;
    }

    public synchronized BuglyStrategy setUploadProcess(boolean z) {
        this.r = z;
        return this;
    }

    public synchronized boolean isUploadProcess() {
        return this.r;
    }

    public synchronized boolean isBuglyLogUpload() {
        return this.p;
    }

    public synchronized boolean recordUserInfoOnceADay() {
        return this.t;
    }

    public boolean isReplaceOldChannel() {
        return this.q;
    }

    public void setReplaceOldChannel(boolean z) {
        this.q = z;
    }

    public synchronized String getAppVersion() {
        if (this.f5360c == null) {
            return com.tencent.bugly.crashreport.common.info.a.b().i;
        }
        return this.f5360c;
    }

    public synchronized BuglyStrategy setAppVersion(String str) {
        this.f5360c = str;
        return this;
    }

    public synchronized BuglyStrategy setUserInfoActivity(Class<?> cls) {
        this.o = cls;
        return this;
    }

    public synchronized Class<?> getUserInfoActivity() {
        return this.o;
    }

    public synchronized String getAppChannel() {
        if (this.f5361d == null) {
            return com.tencent.bugly.crashreport.common.info.a.b().k;
        }
        return this.f5361d;
    }

    public synchronized BuglyStrategy setAppChannel(String str) {
        this.f5361d = str;
        return this;
    }

    public synchronized String getAppPackageName() {
        if (this.f5362e == null) {
            return com.tencent.bugly.crashreport.common.info.a.b().f5416c;
        }
        return this.f5362e;
    }

    public synchronized BuglyStrategy setAppPackageName(String str) {
        this.f5362e = str;
        return this;
    }

    public synchronized long getAppReportDelay() {
        return this.f5363f;
    }

    public synchronized BuglyStrategy setAppReportDelay(long j) {
        this.f5363f = j;
        return this;
    }

    public synchronized String getLibBuglySOFilePath() {
        return this.f5364g;
    }

    public synchronized BuglyStrategy setLibBuglySOFilePath(String str) {
        this.f5364g = str;
        return this;
    }

    public synchronized String getDeviceID() {
        return this.f5365h;
    }

    public synchronized BuglyStrategy setDeviceID(String str) {
        this.f5365h = str;
        return this;
    }

    public synchronized String getDeviceModel() {
        return this.i;
    }

    public synchronized BuglyStrategy setDeviceModel(String str) {
        this.i = str;
        return this;
    }

    public synchronized boolean isEnableNativeCrashMonitor() {
        return this.j;
    }

    public synchronized BuglyStrategy setEnableNativeCrashMonitor(boolean z) {
        this.j = z;
        return this;
    }

    public synchronized BuglyStrategy setEnableUserInfo(boolean z) {
        this.n = z;
        return this;
    }

    public synchronized boolean isEnableUserInfo() {
        return this.n;
    }

    public synchronized boolean isEnableCatchAnrTrace() {
        return this.l;
    }

    public void setEnableCatchAnrTrace(boolean z) {
        this.l = z;
    }

    public void setEnableRecordAnrMainStack(boolean z) {
        this.m = z;
    }

    public boolean isEnableRecordAnrMainStack() {
        return this.m;
    }

    public synchronized boolean isEnableANRCrashMonitor() {
        return this.k;
    }

    public synchronized BuglyStrategy setEnableANRCrashMonitor(boolean z) {
        this.k = z;
        return this;
    }

    public synchronized a getCrashHandleCallback() {
        return this.u;
    }

    public synchronized BuglyStrategy setCrashHandleCallback(a aVar) {
        this.u = aVar;
        return this;
    }

    public synchronized void setCallBackType(int i) {
        this.f5358a = i;
    }

    public synchronized int getCallBackType() {
        return this.f5358a;
    }

    public synchronized void setCloseErrorCallback(boolean z) {
        this.f5359b = z;
    }

    public synchronized boolean getCloseErrorCallback() {
        return this.f5359b;
    }

    public boolean isMerged() {
        return this.v;
    }

    @Deprecated
    public void setMerged(boolean z) {
        this.v = z;
    }

    public synchronized void setUploadSpotCrash(boolean z) {
        this.s = z;
    }

    public synchronized boolean isUploadSpotCrash() {
        return this.s;
    }

    /* JADX INFO: compiled from: BUGLY */
    public static class a {
        public static final int CRASHTYPE_ANR = 4;
        public static final int CRASHTYPE_BLOCK = 7;
        public static final int CRASHTYPE_COCOS2DX_JS = 5;
        public static final int CRASHTYPE_COCOS2DX_LUA = 6;
        public static final int CRASHTYPE_JAVA_CATCH = 1;
        public static final int CRASHTYPE_JAVA_CRASH = 0;
        public static final int CRASHTYPE_NATIVE = 2;
        public static final int CRASHTYPE_U3D = 3;
        public static final int MAX_USERDATA_KEY_LENGTH = 100;
        public static final int MAX_USERDATA_VALUE_LENGTH = 100000;

        public synchronized Map<String, String> onCrashHandleStart(int i, String str, String str2, String str3) {
            return null;
        }

        public synchronized byte[] onCrashHandleStart2GetExtraDatas(int i, String str, String str2, String str3) {
            return null;
        }
    }
}