package com.tencent.bugly.crashreport;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.CrashModule;
import com.tencent.bugly.b;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.crash.BuglyBroadcastReceiver;
import com.tencent.bugly.crashreport.crash.c;
import com.tencent.bugly.crashreport.crash.d;
import com.tencent.bugly.crashreport.crash.h5.H5JavaScriptInterface;
import com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler;
import com.tencent.bugly.proguard.ab;
import com.tencent.bugly.proguard.p;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.y;
import java.net.InetAddress;
import java.net.Proxy;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes3.dex */
public class CrashReport {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static Context f5376a;

    /* JADX INFO: compiled from: BUGLY */
    public static class CrashHandleCallback extends BuglyStrategy.a {
    }

    /* JADX INFO: compiled from: BUGLY */
    public interface WebViewInterface {
        void addJavascriptInterface(H5JavaScriptInterface h5JavaScriptInterface, String str);

        CharSequence getContentDescription();

        String getUrl();

        void loadUrl(String str);

        void setJavaScriptEnabled(boolean z);
    }

    public static void enableBugly(boolean z) {
        b.f5371a = z;
    }

    public static void initCrashReport(Context context) {
        if (context == null) {
            return;
        }
        f5376a = context;
        b.a(CrashModule.getInstance());
        b.a(context);
    }

    public static void initCrashReport(Context context, UserStrategy userStrategy) {
        if (context == null) {
            return;
        }
        f5376a = context;
        b.a(CrashModule.getInstance());
        b.a(context, userStrategy);
    }

    public static void initCrashReport(Context context, String str, boolean z) {
        if (context != null) {
            f5376a = context;
            b.a(CrashModule.getInstance());
            b.a(context, str, z, null);
        }
    }

    public static void initCrashReport(Context context, String str, boolean z, UserStrategy userStrategy) {
        if (context == null) {
            return;
        }
        f5376a = context;
        b.a(CrashModule.getInstance());
        b.a(context, str, z, userStrategy);
    }

    public static Context getContext() {
        return f5376a;
    }

    public static String getBuglyVersion(Context context) {
        if (context == null) {
            y.d("Please call with context.", new Object[0]);
            return "unknown";
        }
        return com.tencent.bugly.crashreport.common.info.a.a(context).c();
    }

    public static void testJavaCrash() {
        if (!b.f5371a) {
            Log.w(y.f5777a, "Can not test Java crash because bugly is disable.");
        } else {
            if (!CrashModule.getInstance().hasInitialized()) {
                Log.e(y.f5777a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
                return;
            }
            com.tencent.bugly.crashreport.common.info.a aVarB = com.tencent.bugly.crashreport.common.info.a.b();
            if (aVarB != null) {
                aVarB.b(24096);
            }
            throw new RuntimeException("This Crash create for Test! You can go to Bugly see more detail!");
        }
    }

    public static void testNativeCrash() {
        testNativeCrash(false, false, false);
    }

    public static void testNativeCrash(boolean z, boolean z2, boolean z3) {
        if (!b.f5371a) {
            Log.w(y.f5777a, "Can not test native crash because bugly is disable.");
        } else if (!CrashModule.getInstance().hasInitialized()) {
            Log.e(y.f5777a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        } else {
            y.a("start to create a native crash for test!", new Object[0]);
            c.a().a(z, z2, z3);
        }
    }

    public static void testANRCrash() {
        if (!b.f5371a) {
            Log.w(y.f5777a, "Can not test ANR crash because bugly is disable.");
        } else if (!CrashModule.getInstance().hasInitialized()) {
            Log.e(y.f5777a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        } else {
            y.a("start to create a anr crash for test!", new Object[0]);
            c.a().l();
        }
    }

    public static void postException(Thread thread, int i, String str, String str2, String str3, Map<String, String> map) {
        if (!b.f5371a) {
            Log.w(y.f5777a, "Can not post crash caught because bugly is disable.");
        } else if (!CrashModule.getInstance().hasInitialized()) {
            Log.e(y.f5777a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        } else {
            d.a(thread, i, str, str2, str3, map);
        }
    }

    public static void postException(int i, String str, String str2, String str3, Map<String, String> map) {
        postException(Thread.currentThread(), i, str, str2, str3, map);
    }

    public static void postCatchedException(Throwable th) {
        postCatchedException(th, Thread.currentThread(), false);
    }

    public static void postCatchedException(Throwable th, Thread thread) {
        postCatchedException(th, thread, false);
    }

    public static void postCatchedException(Throwable th, Thread thread, boolean z) {
        if (!b.f5371a) {
            Log.w(y.f5777a, "Can not post crash caught because bugly is disable.");
            return;
        }
        if (!CrashModule.getInstance().hasInitialized()) {
            Log.e(y.f5777a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return;
        }
        if (th == null) {
            y.d("throwable is null, just return", new Object[0]);
            return;
        }
        if (thread == null) {
            thread = Thread.currentThread();
        }
        c.a().a(thread, th, false, null, null, z, true);
    }

    public static void setAllThreadStackEnable(Context context, boolean z, boolean z2) {
        com.tencent.bugly.crashreport.common.info.a.a(context).a(z, z2);
    }

    public static void closeNativeReport() {
        if (!b.f5371a) {
            Log.w(y.f5777a, "Can not close native report because bugly is disable.");
        } else if (!CrashModule.getInstance().hasInitialized()) {
            Log.e(y.f5777a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        } else {
            c.a().g();
        }
    }

    public static void startCrashReport() {
        if (!b.f5371a) {
            Log.w(y.f5777a, "Can not start crash report because bugly is disable.");
        } else if (!CrashModule.getInstance().hasInitialized()) {
            Log.w(y.f5777a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        } else {
            c.a().c();
        }
    }

    public static void closeCrashReport() {
        if (!b.f5371a) {
            Log.w(y.f5777a, "Can not close crash report because bugly is disable.");
        } else if (!CrashModule.getInstance().hasInitialized()) {
            Log.w(y.f5777a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        } else {
            c.a().d();
        }
    }

    public static void closeBugly() {
        if (!b.f5371a) {
            Log.w(y.f5777a, "Can not close bugly because bugly is disable.");
            return;
        }
        if (!CrashModule.getInstance().hasInitialized()) {
            Log.w(y.f5777a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return;
        }
        if (f5376a == null) {
            return;
        }
        BuglyBroadcastReceiver buglyBroadcastReceiver = BuglyBroadcastReceiver.getInstance();
        if (buglyBroadcastReceiver != null) {
            buglyBroadcastReceiver.unregister(f5376a);
        }
        closeCrashReport();
        com.tencent.bugly.crashreport.biz.b.a(f5376a);
        x xVarA = x.a();
        if (xVarA != null) {
            xVarA.b();
        }
    }

    public static void setUserSceneTag(Context context, int i) {
        if (!b.f5371a) {
            Log.w(y.f5777a, "Can not set tag caught because bugly is disable.");
            return;
        }
        if (context == null) {
            Log.e(y.f5777a, "setTag args context should not be null");
            return;
        }
        if (i <= 0) {
            y.d("setTag args tagId should > 0", new Object[0]);
        }
        com.tencent.bugly.crashreport.common.info.a.a(context).a(i);
        y.b("[param] set user scene tag: %d", Integer.valueOf(i));
    }

    public static int getUserSceneTagId(Context context) {
        if (!b.f5371a) {
            Log.w(y.f5777a, "Can not get user scene tag because bugly is disable.");
            return -1;
        }
        if (context == null) {
            Log.e(y.f5777a, "getUserSceneTagId args context should not be null");
            return -1;
        }
        return com.tencent.bugly.crashreport.common.info.a.a(context).D();
    }

    public static String getUserData(Context context, String str) {
        if (!b.f5371a) {
            Log.w(y.f5777a, "Can not get user data because bugly is disable.");
            return "unknown";
        }
        if (context == null) {
            Log.e(y.f5777a, "getUserDataValue args context should not be null");
            return "unknown";
        }
        if (ab.a(str)) {
            return null;
        }
        return com.tencent.bugly.crashreport.common.info.a.a(context).i(str);
    }

    public static void putUserData(Context context, String str, String str2) {
        if (!b.f5371a) {
            Log.w(y.f5777a, "Can not put user data because bugly is disable.");
            return;
        }
        if (context == null) {
            Log.w(y.f5777a, "putUserData args context should not be null");
            return;
        }
        if (str == null) {
            String str3 = str;
            y.d("putUserData args key should not be null or empty", new Object[0]);
            return;
        }
        if (str2 == null) {
            String str4 = str2;
            y.d("putUserData args value should not be null", new Object[0]);
            return;
        }
        if (str2.length() > 200) {
            y.d("user data value length over limit %d, it will be cutted!", 200);
            str2 = str2.substring(0, 200);
        }
        com.tencent.bugly.crashreport.common.info.a aVarA = com.tencent.bugly.crashreport.common.info.a.a(context);
        if (aVarA.A().contains(str)) {
            NativeCrashHandler nativeCrashHandler = NativeCrashHandler.getInstance();
            if (nativeCrashHandler != null) {
                nativeCrashHandler.putKeyValueToNative(str, str2);
            }
            com.tencent.bugly.crashreport.common.info.a.a(context).b(str, str2);
            y.c("replace KV %s %s", str, str2);
            return;
        }
        if (aVarA.z() >= 50) {
            y.d("user data size is over limit %d, it will be cutted!", 50);
            return;
        }
        if (str.length() > 50) {
            y.d("user data key length over limit %d , will drop this new key %s", 50, str);
            str = str.substring(0, 50);
        }
        NativeCrashHandler nativeCrashHandler2 = NativeCrashHandler.getInstance();
        if (nativeCrashHandler2 != null) {
            nativeCrashHandler2.putKeyValueToNative(str, str2);
        }
        com.tencent.bugly.crashreport.common.info.a.a(context).b(str, str2);
        y.b("[param] set user data: %s - %s", str, str2);
    }

    public static String removeUserData(Context context, String str) {
        if (!b.f5371a) {
            Log.w(y.f5777a, "Can not remove user data because bugly is disable.");
            return "unknown";
        }
        if (context == null) {
            Log.e(y.f5777a, "removeUserData args context should not be null");
            return "unknown";
        }
        if (ab.a(str)) {
            return null;
        }
        y.b("[param] remove user data: %s", str);
        return com.tencent.bugly.crashreport.common.info.a.a(context).h(str);
    }

    public static Set<String> getAllUserDataKeys(Context context) {
        if (!b.f5371a) {
            Log.w(y.f5777a, "Can not get all keys of user data because bugly is disable.");
            return new HashSet();
        }
        if (context == null) {
            Log.e(y.f5777a, "getAllUserDataKeys args context should not be null");
            return new HashSet();
        }
        return com.tencent.bugly.crashreport.common.info.a.a(context).A();
    }

    public static int getUserDatasSize(Context context) {
        if (!b.f5371a) {
            Log.w(y.f5777a, "Can not get size of user data because bugly is disable.");
            return -1;
        }
        if (context == null) {
            Log.e(y.f5777a, "getUserDatasSize args context should not be null");
            return -1;
        }
        return com.tencent.bugly.crashreport.common.info.a.a(context).z();
    }

    public static String getAppID() {
        if (!b.f5371a) {
            Log.w(y.f5777a, "Can not get App ID because bugly is disable.");
            return "unknown";
        }
        if (!CrashModule.getInstance().hasInitialized()) {
            Log.e(y.f5777a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return "unknown";
        }
        return com.tencent.bugly.crashreport.common.info.a.a(f5376a).f();
    }

    public static void setUserId(String str) {
        if (!b.f5371a) {
            Log.w(y.f5777a, "Can not set user ID because bugly is disable.");
        } else if (!CrashModule.getInstance().hasInitialized()) {
            Log.e(y.f5777a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
        } else {
            setUserId(f5376a, str);
        }
    }

    public static void setUserId(Context context, String str) {
        if (!b.f5371a) {
            Log.w(y.f5777a, "Can not set user ID because bugly is disable.");
            return;
        }
        if (context == null) {
            Log.e(y.f5777a, "Context should not be null when bugly has not been initialed!");
            return;
        }
        if (TextUtils.isEmpty(str)) {
            y.d("userId should not be null", new Object[0]);
            return;
        }
        if (str.length() > 100) {
            String strSubstring = str.substring(0, 100);
            y.d("userId %s length is over limit %d substring to %s", str, 100, strSubstring);
            str = strSubstring;
        }
        if (str.equals(com.tencent.bugly.crashreport.common.info.a.a(context).g())) {
            return;
        }
        com.tencent.bugly.crashreport.common.info.a.a(context).b(str);
        y.b("[user] set userId : %s", str);
        NativeCrashHandler nativeCrashHandler = NativeCrashHandler.getInstance();
        if (nativeCrashHandler != null) {
            nativeCrashHandler.setNativeUserId(str);
        }
        if (CrashModule.getInstance().hasInitialized()) {
            com.tencent.bugly.crashreport.biz.b.a();
        }
    }

    public static String getUserId() {
        if (!b.f5371a) {
            Log.w(y.f5777a, "Can not get user ID because bugly is disable.");
            return "unknown";
        }
        if (!CrashModule.getInstance().hasInitialized()) {
            Log.e(y.f5777a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return "unknown";
        }
        return com.tencent.bugly.crashreport.common.info.a.a(f5376a).g();
    }

    public static void setDeviceId(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return;
        }
        com.tencent.bugly.crashreport.common.info.a.a(context).c(str);
    }

    public static void setDeviceModel(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return;
        }
        com.tencent.bugly.crashreport.common.info.a.a(context).d(str);
    }

    public static String getDeviceID(Context context) {
        return com.tencent.bugly.crashreport.common.info.a.a(context).k();
    }

    public static String getAppVer() {
        if (!b.f5371a) {
            Log.w(y.f5777a, "Can not get app version because bugly is disable.");
            return "unknown";
        }
        if (!CrashModule.getInstance().hasInitialized()) {
            Log.e(y.f5777a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return "unknown";
        }
        return com.tencent.bugly.crashreport.common.info.a.a(f5376a).i;
    }

    public static String getAppChannel() {
        if (!b.f5371a) {
            Log.w(y.f5777a, "Can not get App channel because bugly is disable.");
            return "unknown";
        }
        if (!CrashModule.getInstance().hasInitialized()) {
            Log.e(y.f5777a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return "unknown";
        }
        return com.tencent.bugly.crashreport.common.info.a.a(f5376a).k;
    }

    public static void setContext(Context context) {
        f5376a = context;
    }

    public static boolean isLastSessionCrash() {
        if (!b.f5371a) {
            Log.w(y.f5777a, "The info 'isLastSessionCrash' is not accurate because bugly is disable.");
            return false;
        }
        if (!CrashModule.getInstance().hasInitialized()) {
            Log.e(y.f5777a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return false;
        }
        return c.a().b();
    }

    public static void setSdkExtraData(Context context, String str, String str2) {
        if (!b.f5371a) {
            Log.w(y.f5777a, "Can not put SDK extra data because bugly is disable.");
        } else {
            if (context == null || ab.a(str) || ab.a(str2)) {
                return;
            }
            com.tencent.bugly.crashreport.common.info.a.a(context).a(str, str2);
        }
    }

    public static Map<String, String> getSdkExtraData() {
        if (!b.f5371a) {
            Log.w(y.f5777a, "Can not get SDK extra data because bugly is disable.");
            return new HashMap();
        }
        if (!CrashModule.getInstance().hasInitialized()) {
            Log.e(y.f5777a, "CrashReport has not been initialed! pls to call method 'initCrashReport' first!");
            return null;
        }
        return com.tencent.bugly.crashreport.common.info.a.a(f5376a).A;
    }

    public static Map<String, String> getSdkExtraData(Context context) {
        if (!b.f5371a) {
            Log.w(y.f5777a, "Can not get SDK extra data because bugly is disable.");
            return new HashMap();
        }
        if (context == null) {
            y.d("Context should not be null.", new Object[0]);
            return null;
        }
        return com.tencent.bugly.crashreport.common.info.a.a(context).A;
    }

    private static void putSdkData(Context context, String str, String str2) {
        if (context == null || ab.a(str) || ab.a(str2)) {
            return;
        }
        String strReplace = str.replace("[a-zA-Z[0-9]]+", "");
        if (strReplace.length() > 100) {
            Log.w(y.f5777a, String.format("putSdkData key length over limit %d, will be cutted.", 50));
            strReplace = strReplace.substring(0, 50);
        }
        if (str2.length() > 500) {
            Log.w(y.f5777a, String.format("putSdkData value length over limit %d, will be cutted!", 200));
            str2 = str2.substring(0, 200);
        }
        com.tencent.bugly.crashreport.common.info.a.a(context).c(strReplace, str2);
        y.b(String.format("[param] putSdkData data: %s - %s", strReplace, str2), new Object[0]);
    }

    @Deprecated
    public static void setIsAppForeground(Context context, boolean z) {
        y.a("App fore and back status are no longer supported", new Object[0]);
    }

    public static void setIsDevelopmentDevice(Context context, boolean z) {
        if (!b.f5371a) {
            Log.w(y.f5777a, "Can not set 'isDevelopmentDevice' because bugly is disable.");
            return;
        }
        if (context == null) {
            y.d("Context should not be null.", new Object[0]);
            return;
        }
        if (z) {
            y.c("This is a development device.", new Object[0]);
        } else {
            y.c("This is not a development device.", new Object[0]);
        }
        com.tencent.bugly.crashreport.common.info.a.a(context).y = z;
    }

    public static void setSessionIntervalMills(long j) {
        if (!b.f5371a) {
            Log.w(y.f5777a, "Can not set 'SessionIntervalMills' because bugly is disable.");
        } else {
            com.tencent.bugly.crashreport.biz.b.a(j);
        }
    }

    public static void setAppVersion(Context context, String str) {
        if (!b.f5371a) {
            Log.w(y.f5777a, "Can not set App version because bugly is disable.");
            return;
        }
        if (context == null) {
            Log.w(y.f5777a, "setAppVersion args context should not be null");
            return;
        }
        if (str == null) {
            Log.w(y.f5777a, "App version is null, will not set");
            return;
        }
        com.tencent.bugly.crashreport.common.info.a.a(context).i = str;
        NativeCrashHandler nativeCrashHandler = NativeCrashHandler.getInstance();
        if (nativeCrashHandler != null) {
            nativeCrashHandler.setNativeAppVersion(str);
        }
    }

    public static void setAppChannel(Context context, String str) {
        if (!b.f5371a) {
            Log.w(y.f5777a, "Can not set App channel because Bugly is disable.");
            return;
        }
        if (context == null) {
            Log.w(y.f5777a, "setAppChannel args context should not be null");
            return;
        }
        if (str == null) {
            Log.w(y.f5777a, "App channel is null, will not set");
            return;
        }
        com.tencent.bugly.crashreport.common.info.a.a(context).k = str;
        NativeCrashHandler nativeCrashHandler = NativeCrashHandler.getInstance();
        if (nativeCrashHandler != null) {
            nativeCrashHandler.setNativeAppChannel(str);
        }
    }

    public static void setAppPackage(Context context, String str) {
        if (!b.f5371a) {
            Log.w(y.f5777a, "Can not set App package because bugly is disable.");
            return;
        }
        if (context == null) {
            Log.w(y.f5777a, "setAppPackage args context should not be null");
            return;
        }
        if (str == null) {
            Log.w(y.f5777a, "App package is null, will not set");
            return;
        }
        com.tencent.bugly.crashreport.common.info.a.a(context).f5416c = str;
        NativeCrashHandler nativeCrashHandler = NativeCrashHandler.getInstance();
        if (nativeCrashHandler != null) {
            nativeCrashHandler.setNativeAppPackage(str);
        }
    }

    public static void setCrashFilter(String str) {
        if (!b.f5371a) {
            Log.w(y.f5777a, "Can not set App package because bugly is disable.");
            return;
        }
        Log.i(y.f5777a, "Set crash stack filter: " + str);
        c.n = str;
    }

    public static void setCrashRegularFilter(String str) {
        if (!b.f5371a) {
            Log.w(y.f5777a, "Can not set App package because bugly is disable.");
            return;
        }
        Log.i(y.f5777a, "Set crash stack filter: " + str);
        c.o = str;
    }

    public static void setHandleNativeCrashInJava(boolean z) {
        if (!b.f5371a) {
            Log.w(y.f5777a, "Can not set App package because bugly is disable.");
            return;
        }
        Log.i(y.f5777a, "Should handle native crash in Java profile after handled in native profile: " + z);
        NativeCrashHandler.setShouldHandleInJava(z);
    }

    public static void setBuglyDbName(String str) {
        if (!b.f5371a) {
            Log.w(y.f5777a, "Can not set DB name because bugly is disable.");
            return;
        }
        Log.i(y.f5777a, "Set Bugly DB name: " + str);
        p.f5725a = str;
    }

    public static void enableObtainId(Context context, boolean z) {
        setCollectPrivacyInfo(context, z);
    }

    public static void setCollectPrivacyInfo(Context context, boolean z) {
        if (!b.f5371a) {
            Log.w(y.f5777a, "Can not set collect privacy info enable because bugly is disable.");
            return;
        }
        if (context == null) {
            Log.w(y.f5777a, "setCollectPrivacyInfo args context should not be null");
            return;
        }
        Log.i(y.f5777a, "setCollectPrivacyInfo: " + z);
        com.tencent.bugly.crashreport.common.info.a.a(context).a(z);
    }

    public static void setServerUrl(String str) {
        if (ab.a(str) || !ab.c(str)) {
            Log.i(y.f5777a, "URL is invalid.");
            return;
        }
        com.tencent.bugly.crashreport.common.strategy.a.a(str);
        StrategyBean.f5425a = str;
        StrategyBean.f5426b = str;
    }

    public static void uploadUserInfo() {
        if (!b.f5371a) {
            Log.w(y.f5777a, "Can not upload user info because bugly is disable.");
        } else if (com.tencent.bugly.crashreport.biz.b.f5400a == null) {
            Log.w(y.f5777a, "Can not upload user info because bugly is not init.");
        } else {
            com.tencent.bugly.crashreport.biz.b.f5400a.b();
        }
    }

    public static boolean setJavascriptMonitor(WebView webView, boolean z) {
        return setJavascriptMonitor(webView, z, false);
    }

    public static boolean setJavascriptMonitor(final WebView webView, boolean z, boolean z2) {
        if (webView == null) {
            Log.w(y.f5777a, "WebView is null.");
            return false;
        }
        webView.getSettings().setSavePassword(false);
        webView.getSettings().setAllowFileAccess(false);
        return setJavascriptMonitor(new WebViewInterface() { // from class: com.tencent.bugly.crashreport.CrashReport.1
            @Override // com.tencent.bugly.crashreport.CrashReport.WebViewInterface
            public final String getUrl() {
                return webView.getUrl();
            }

            @Override // com.tencent.bugly.crashreport.CrashReport.WebViewInterface
            public final void setJavaScriptEnabled(boolean z3) {
                WebSettings settings = webView.getSettings();
                if (settings.getJavaScriptEnabled()) {
                    return;
                }
                settings.setJavaScriptEnabled(true);
            }

            @Override // com.tencent.bugly.crashreport.CrashReport.WebViewInterface
            public final void loadUrl(String str) {
                webView.loadUrl(str);
            }

            @Override // com.tencent.bugly.crashreport.CrashReport.WebViewInterface
            public final void addJavascriptInterface(H5JavaScriptInterface h5JavaScriptInterface, String str) {
                webView.addJavascriptInterface(h5JavaScriptInterface, str);
            }

            @Override // com.tencent.bugly.crashreport.CrashReport.WebViewInterface
            public final CharSequence getContentDescription() {
                return webView.getContentDescription();
            }
        }, z, z2);
    }

    public static boolean setJavascriptMonitor(WebViewInterface webViewInterface, boolean z) {
        return setJavascriptMonitor(webViewInterface, z, false);
    }

    public static boolean setJavascriptMonitor(WebViewInterface webViewInterface, boolean z, boolean z2) {
        if (webViewInterface == null) {
            Log.w(y.f5777a, "WebViewInterface is null.");
            return false;
        }
        if (!CrashModule.getInstance().hasInitialized()) {
            y.e("CrashReport has not been initialed! please to call method 'initCrashReport' first!", new Object[0]);
            return false;
        }
        y.a("Set Javascript exception monitor of webview.", new Object[0]);
        if (!b.f5371a) {
            Log.w(y.f5777a, "Can not set JavaScript monitor because bugly is disable.");
            return false;
        }
        y.c("URL of webview is %s", webViewInterface.getUrl());
        if (!z2 && Build.VERSION.SDK_INT < 19) {
            y.e("This interface is only available for Android 4.4 or later.", new Object[0]);
            return false;
        }
        y.a("Enable the javascript needed by webview monitor.", new Object[0]);
        webViewInterface.setJavaScriptEnabled(true);
        H5JavaScriptInterface h5JavaScriptInterface = H5JavaScriptInterface.getInstance(webViewInterface);
        if (h5JavaScriptInterface != null) {
            y.a("Add a secure javascript interface to the webview.", new Object[0]);
            webViewInterface.addJavascriptInterface(h5JavaScriptInterface, "exceptionUploader");
        }
        if (z) {
            y.a("Inject bugly.js(v%s) to the webview.", com.tencent.bugly.crashreport.crash.h5.b.b());
            String strA = com.tencent.bugly.crashreport.crash.h5.b.a();
            if (strA == null) {
                y.e("Failed to inject Bugly.js.", com.tencent.bugly.crashreport.crash.h5.b.b());
                return false;
            }
            webViewInterface.loadUrl(BridgeUtil.JAVASCRIPT_STR + strA);
        }
        return true;
    }

    public static void setHttpProxy(String str, int i) {
        com.tencent.bugly.proguard.a.a(str, i);
    }

    public static void setHttpProxy(InetAddress inetAddress, int i) {
        com.tencent.bugly.proguard.a.a(inetAddress, i);
    }

    public static Proxy getHttpProxy() {
        return com.tencent.bugly.proguard.a.b();
    }

    /* JADX INFO: compiled from: BUGLY */
    public static class UserStrategy extends BuglyStrategy {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private CrashHandleCallback f5378c;

        public UserStrategy(Context context) {
        }

        @Override // com.tencent.bugly.BuglyStrategy
        public synchronized void setCallBackType(int i) {
            this.f5358a = i;
        }

        @Override // com.tencent.bugly.BuglyStrategy
        public synchronized int getCallBackType() {
            return this.f5358a;
        }

        @Override // com.tencent.bugly.BuglyStrategy
        public synchronized void setCloseErrorCallback(boolean z) {
            this.f5359b = z;
        }

        @Override // com.tencent.bugly.BuglyStrategy
        public synchronized boolean getCloseErrorCallback() {
            return this.f5359b;
        }

        @Override // com.tencent.bugly.BuglyStrategy
        public synchronized CrashHandleCallback getCrashHandleCallback() {
            return this.f5378c;
        }

        public synchronized void setCrashHandleCallback(CrashHandleCallback crashHandleCallback) {
            this.f5378c = crashHandleCallback;
        }
    }
}