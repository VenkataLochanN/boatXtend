package com.baidu.lbsapi.auth;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import com.baidu.android.bbalbs.common.util.CommonParam;
import com.ido.alexa.AlexaCustomSkillConstant;
import com.ido.life.util.DateUtil;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class LBSAuthManager {
    public static final int CODE_AUTHENTICATE_SUCC = 0;
    public static final int CODE_AUTHENTICATING = 602;
    public static final int CODE_INNER_ERROR = -1;
    public static final int CODE_KEY_NOT_EXIST = 101;
    public static final int CODE_NETWORK_FAILED = -11;
    public static final int CODE_NETWORK_INVALID = -10;
    public static final int CODE_UNAUTHENTICATE = 601;
    public static final String VERSION = "1.0.22";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static Context f1981a;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static m f1982d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static int f1983e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static Hashtable<String, LBSAuthManagerListener> f1984f = new Hashtable<>();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static LBSAuthManager f1985g;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private c f1986b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private e f1987c = null;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final Handler f1988h = new i(this, Looper.getMainLooper());

    private LBSAuthManager(Context context) {
        f1981a = context;
        m mVar = f1982d;
        if (mVar != null && !mVar.isAlive()) {
            f1982d = null;
        }
        a.b("BaiduApiAuth SDK Version:1.0.22");
        d();
    }

    private int a(String str) {
        int i = -1;
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.has(NotificationCompat.CATEGORY_STATUS)) {
                jSONObject.put(NotificationCompat.CATEGORY_STATUS, -1);
            }
            i = jSONObject.getInt(NotificationCompat.CATEGORY_STATUS);
            if (jSONObject.has("current") && i == 0) {
                long j = jSONObject.getLong("current");
                long jCurrentTimeMillis = System.currentTimeMillis();
                if ((jCurrentTimeMillis - j) / 3600000.0d >= 24.0d) {
                    i = 601;
                } else {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtil.DATE_FORMAT_YMD);
                    if (!simpleDateFormat.format(Long.valueOf(jCurrentTimeMillis)).equals(simpleDateFormat.format(Long.valueOf(j)))) {
                        i = 601;
                    }
                }
            }
            if (jSONObject.has("current") && i == 602) {
                if ((System.currentTimeMillis() - jSONObject.getLong("current")) / 1000 > 180.0d) {
                    return 601;
                }
            }
            return i;
        } catch (JSONException e2) {
            int i2 = i;
            e2.printStackTrace();
            return i2;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:31:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0035 A[PHI: r0 r6
  0x0035: PHI (r0v5 java.lang.String) = (r0v14 java.lang.String), (r0v16 java.lang.String), (r0v10 java.lang.String) binds: [B:34:0x0072, B:42:0x0082, B:7:0x002f] A[DONT_GENERATE, DONT_INLINE]
  0x0035: PHI (r6v9 java.io.FileInputStream) = (r6v7 java.io.FileInputStream), (r6v8 java.io.FileInputStream), (r6v12 java.io.FileInputStream) binds: [B:34:0x0072, B:42:0x0082, B:7:0x002f] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String a(int r6) throws java.lang.Throwable {
        /*
            r5 = this;
            r0 = 0
            java.io.File r1 = new java.io.File     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L65 java.io.FileNotFoundException -> L75
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L65 java.io.FileNotFoundException -> L75
            r2.<init>()     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L65 java.io.FileNotFoundException -> L75
            java.lang.String r3 = "/proc/"
            r2.append(r3)     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L65 java.io.FileNotFoundException -> L75
            r2.append(r6)     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L65 java.io.FileNotFoundException -> L75
            java.lang.String r6 = "/cmdline"
            r2.append(r6)     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L65 java.io.FileNotFoundException -> L75
            java.lang.String r6 = r2.toString()     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L65 java.io.FileNotFoundException -> L75
            r1.<init>(r6)     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L65 java.io.FileNotFoundException -> L75
            java.io.FileInputStream r6 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L65 java.io.FileNotFoundException -> L75
            r6.<init>(r1)     // Catch: java.lang.Throwable -> L52 java.io.IOException -> L65 java.io.FileNotFoundException -> L75
            java.io.InputStreamReader r1 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L49 java.io.IOException -> L4e java.io.FileNotFoundException -> L50
            r1.<init>(r6)     // Catch: java.lang.Throwable -> L49 java.io.IOException -> L4e java.io.FileNotFoundException -> L50
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L40 java.io.IOException -> L45 java.io.FileNotFoundException -> L47
            r2.<init>(r1)     // Catch: java.lang.Throwable -> L40 java.io.IOException -> L45 java.io.FileNotFoundException -> L47
            java.lang.String r0 = r2.readLine()     // Catch: java.lang.Throwable -> L3a java.io.IOException -> L68 java.io.FileNotFoundException -> L78
            r2.close()
            r1.close()
        L35:
            r6.close()
            goto L85
        L3a:
            r0 = move-exception
            r4 = r2
            r2 = r6
            r6 = r0
            r0 = r4
            goto L55
        L40:
            r2 = move-exception
            r4 = r2
            r2 = r6
            r6 = r4
            goto L55
        L45:
            r2 = r0
            goto L68
        L47:
            r2 = r0
            goto L78
        L49:
            r1 = move-exception
            r2 = r6
            r6 = r1
            r1 = r0
            goto L55
        L4e:
            r1 = r0
            goto L67
        L50:
            r1 = r0
            goto L77
        L52:
            r6 = move-exception
            r1 = r0
            r2 = r1
        L55:
            if (r0 == 0) goto L5a
            r0.close()
        L5a:
            if (r1 == 0) goto L5f
            r1.close()
        L5f:
            if (r2 == 0) goto L64
            r2.close()
        L64:
            throw r6
        L65:
            r6 = r0
            r1 = r6
        L67:
            r2 = r1
        L68:
            if (r2 == 0) goto L6d
            r2.close()
        L6d:
            if (r1 == 0) goto L72
            r1.close()
        L72:
            if (r6 == 0) goto L85
            goto L35
        L75:
            r6 = r0
            r1 = r6
        L77:
            r2 = r1
        L78:
            if (r2 == 0) goto L7d
            r2.close()
        L7d:
            if (r1 == 0) goto L82
            r1.close()
        L82:
            if (r6 == 0) goto L85
            goto L35
        L85:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.lbsapi.auth.LBSAuthManager.a(int):java.lang.String");
    }

    private String a(Context context) throws Throwable {
        int iMyPid = Process.myPid();
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses != null) {
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.pid == iMyPid) {
                    return runningAppProcessInfo.processName;
                }
            }
        }
        String strA = null;
        try {
            strA = a(iMyPid);
        } catch (IOException unused) {
        }
        return strA != null ? strA : f1981a.getPackageName();
    }

    private String a(Context context, String str) {
        String string;
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo.metaData == null) {
                LBSAuthManagerListener lBSAuthManagerListener = f1984f.get(str);
                if (lBSAuthManagerListener != null) {
                    lBSAuthManagerListener.onAuthResult(101, ErrorMessage.a(101, "AndroidManifest.xml的application中没有meta-data标签"));
                }
                return "";
            }
            string = applicationInfo.metaData.getString("com.baidu.lbsapi.API_KEY");
            if (string != null) {
                try {
                    if (!string.equals("")) {
                        return string;
                    }
                } catch (PackageManager.NameNotFoundException unused) {
                    LBSAuthManagerListener lBSAuthManagerListener2 = f1984f.get(str);
                    if (lBSAuthManagerListener2 == null) {
                        return string;
                    }
                    lBSAuthManagerListener2.onAuthResult(101, ErrorMessage.a(101, "无法在AndroidManifest.xml中获取com.baidu.android.lbs.API_KEY的值"));
                    return string;
                }
            }
            LBSAuthManagerListener lBSAuthManagerListener3 = f1984f.get(str);
            if (lBSAuthManagerListener3 == null) {
                return string;
            }
            lBSAuthManagerListener3.onAuthResult(101, ErrorMessage.a(101, "无法在AndroidManifest.xml中获取com.baidu.android.lbs.API_KEY的值"));
            return string;
        } catch (PackageManager.NameNotFoundException unused2) {
            string = "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0028 A[Catch: JSONException -> 0x0066, all -> 0x00c0, TryCatch #1 {JSONException -> 0x0066, blocks: (B:7:0x000e, B:9:0x001b, B:10:0x0020, B:12:0x0028, B:13:0x0031, B:15:0x0040, B:16:0x0045), top: B:38:0x000e, outer: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0040 A[Catch: JSONException -> 0x0066, all -> 0x00c0, TryCatch #1 {JSONException -> 0x0066, blocks: (B:7:0x000e, B:9:0x001b, B:10:0x0020, B:12:0x0028, B:13:0x0031, B:15:0x0040, B:16:0x0045), top: B:38:0x000e, outer: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0089 A[Catch: all -> 0x00c0, TryCatch #0 {, blocks: (B:4:0x0003, B:5:0x0007, B:7:0x000e, B:9:0x001b, B:10:0x0020, B:12:0x0028, B:13:0x0031, B:15:0x0040, B:16:0x0045, B:20:0x0085, B:22:0x0089, B:23:0x008e, B:25:0x0098, B:26:0x00ae, B:28:0x00b2, B:30:0x00b6, B:19:0x0067), top: B:36:0x0003, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0098 A[Catch: all -> 0x00c0, TryCatch #0 {, blocks: (B:4:0x0003, B:5:0x0007, B:7:0x000e, B:9:0x001b, B:10:0x0020, B:12:0x0028, B:13:0x0031, B:15:0x0040, B:16:0x0045, B:20:0x0085, B:22:0x0089, B:23:0x008e, B:25:0x0098, B:26:0x00ae, B:28:0x00b2, B:30:0x00b6, B:19:0x0067), top: B:36:0x0003, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x001b A[Catch: JSONException -> 0x0066, all -> 0x00c0, TryCatch #1 {JSONException -> 0x0066, blocks: (B:7:0x000e, B:9:0x001b, B:10:0x0020, B:12:0x0028, B:13:0x0031, B:15:0x0040, B:16:0x0045), top: B:38:0x000e, outer: #0 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized void a(java.lang.String r6, java.lang.String r7) {
        /*
            r5 = this;
            monitor-enter(r5)
            if (r6 != 0) goto L7
            java.lang.String r6 = r5.e()     // Catch: java.lang.Throwable -> Lc0
        L7:
            android.os.Handler r0 = r5.f1988h     // Catch: java.lang.Throwable -> Lc0
            android.os.Message r0 = r0.obtainMessage()     // Catch: java.lang.Throwable -> Lc0
            r1 = -1
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lc0
            r2.<init>(r6)     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lc0
            java.lang.String r6 = "status"
            boolean r6 = r2.has(r6)     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lc0
            if (r6 != 0) goto L20
            java.lang.String r6 = "status"
            r2.put(r6, r1)     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lc0
        L20:
            java.lang.String r6 = "current"
            boolean r6 = r2.has(r6)     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lc0
            if (r6 != 0) goto L31
            java.lang.String r6 = "current"
            long r3 = java.lang.System.currentTimeMillis()     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lc0
            r2.put(r6, r3)     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lc0
        L31:
            java.lang.String r6 = r2.toString()     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lc0
            r5.c(r6)     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lc0
            java.lang.String r6 = "current"
            boolean r6 = r2.has(r6)     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lc0
            if (r6 == 0) goto L45
            java.lang.String r6 = "current"
            r2.remove(r6)     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lc0
        L45:
            java.lang.String r6 = "status"
            int r1 = r2.getInt(r6)     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lc0
            r0.what = r1     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lc0
            java.lang.String r6 = r2.toString()     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lc0
            r0.obj = r6     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lc0
            android.os.Bundle r6 = new android.os.Bundle     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lc0
            r6.<init>()     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lc0
            java.lang.String r2 = "listenerKey"
            r6.putString(r2, r7)     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lc0
            r0.setData(r6)     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lc0
            android.os.Handler r6 = r5.f1988h     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lc0
            r6.sendMessage(r0)     // Catch: org.json.JSONException -> L66 java.lang.Throwable -> Lc0
            goto L85
        L66:
            r6 = move-exception
            r6.printStackTrace()     // Catch: java.lang.Throwable -> Lc0
            r0.what = r1     // Catch: java.lang.Throwable -> Lc0
            org.json.JSONObject r6 = new org.json.JSONObject     // Catch: java.lang.Throwable -> Lc0
            r6.<init>()     // Catch: java.lang.Throwable -> Lc0
            r0.obj = r6     // Catch: java.lang.Throwable -> Lc0
            android.os.Bundle r6 = new android.os.Bundle     // Catch: java.lang.Throwable -> Lc0
            r6.<init>()     // Catch: java.lang.Throwable -> Lc0
            java.lang.String r1 = "listenerKey"
            r6.putString(r1, r7)     // Catch: java.lang.Throwable -> Lc0
            r0.setData(r6)     // Catch: java.lang.Throwable -> Lc0
            android.os.Handler r6 = r5.f1988h     // Catch: java.lang.Throwable -> Lc0
            r6.sendMessage(r0)     // Catch: java.lang.Throwable -> Lc0
        L85:
            com.baidu.lbsapi.auth.m r6 = com.baidu.lbsapi.auth.LBSAuthManager.f1982d     // Catch: java.lang.Throwable -> Lc0
            if (r6 == 0) goto L8e
            com.baidu.lbsapi.auth.m r6 = com.baidu.lbsapi.auth.LBSAuthManager.f1982d     // Catch: java.lang.Throwable -> Lc0
            r6.c()     // Catch: java.lang.Throwable -> Lc0
        L8e:
            int r6 = com.baidu.lbsapi.auth.LBSAuthManager.f1983e     // Catch: java.lang.Throwable -> Lc0
            int r6 = r6 + (-1)
            com.baidu.lbsapi.auth.LBSAuthManager.f1983e = r6     // Catch: java.lang.Throwable -> Lc0
            boolean r6 = com.baidu.lbsapi.auth.a.f1989a     // Catch: java.lang.Throwable -> Lc0
            if (r6 == 0) goto Lae
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lc0
            r6.<init>()     // Catch: java.lang.Throwable -> Lc0
            java.lang.String r7 = "httpRequest called mAuthCounter-- = "
            r6.append(r7)     // Catch: java.lang.Throwable -> Lc0
            int r7 = com.baidu.lbsapi.auth.LBSAuthManager.f1983e     // Catch: java.lang.Throwable -> Lc0
            r6.append(r7)     // Catch: java.lang.Throwable -> Lc0
            java.lang.String r6 = r6.toString()     // Catch: java.lang.Throwable -> Lc0
            com.baidu.lbsapi.auth.a.a(r6)     // Catch: java.lang.Throwable -> Lc0
        Lae:
            int r6 = com.baidu.lbsapi.auth.LBSAuthManager.f1983e     // Catch: java.lang.Throwable -> Lc0
            if (r6 != 0) goto Lbe
            com.baidu.lbsapi.auth.m r6 = com.baidu.lbsapi.auth.LBSAuthManager.f1982d     // Catch: java.lang.Throwable -> Lc0
            if (r6 == 0) goto Lbe
            com.baidu.lbsapi.auth.m r6 = com.baidu.lbsapi.auth.LBSAuthManager.f1982d     // Catch: java.lang.Throwable -> Lc0
            r6.a()     // Catch: java.lang.Throwable -> Lc0
            r6 = 0
            com.baidu.lbsapi.auth.LBSAuthManager.f1982d = r6     // Catch: java.lang.Throwable -> Lc0
        Lbe:
            monitor-exit(r5)
            return
        Lc0:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.lbsapi.auth.LBSAuthManager.a(java.lang.String, java.lang.String):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z, String str, Hashtable<String, String> hashtable, String str2) {
        String strA;
        String strC;
        String strA2;
        String strA3 = a(f1981a, str2);
        if (strA3 == null || strA3.equals("")) {
            return;
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("url", "https://api.map.baidu.com/sdkcs/verify");
        a.a("url:https://api.map.baidu.com/sdkcs/verify");
        map.put("output", "json");
        map.put("ak", strA3);
        a.a("ak:" + strA3);
        map.put("mcode", b.a(f1981a));
        map.put("from", "lbs_yunsdk");
        if (hashtable != null && hashtable.size() > 0) {
            for (Map.Entry<String, String> entry : hashtable.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (!TextUtils.isEmpty(key) && !TextUtils.isEmpty(value)) {
                    map.put(key, value);
                }
            }
        }
        try {
            strA = CommonParam.a(f1981a);
        } catch (Exception unused) {
            strA = "";
        }
        a.a("cuid:" + strA);
        if (TextUtils.isEmpty(strA)) {
            map.put("cuid", "");
        } else {
            map.put("cuid", strA);
        }
        map.put("pcn", f1981a.getPackageName());
        map.put("version", VERSION);
        try {
            strC = b.c(f1981a);
        } catch (Exception unused2) {
            strC = "";
        }
        if (TextUtils.isEmpty(strC)) {
            map.put("macaddr", "");
        } else {
            map.put("macaddr", strC);
        }
        try {
            strA2 = b.a();
        } catch (Exception unused3) {
            strA2 = "";
        }
        if (TextUtils.isEmpty(strA2)) {
            map.put("language", "");
        } else {
            map.put("language", strA2);
        }
        if (z) {
            map.put("force", z ? "1" : AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
        }
        if (str == null) {
            map.put("from_service", "");
        } else {
            map.put("from_service", str);
        }
        this.f1986b = new c(f1981a);
        this.f1986b.a(map, new k(this, str2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z, String str, Hashtable<String, String> hashtable, String[] strArr, String str2) {
        String strA;
        String strC;
        String strA2;
        String strA3 = a(f1981a, str2);
        if (strA3 == null || strA3.equals("")) {
            return;
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("url", "https://api.map.baidu.com/sdkcs/verify");
        map.put("output", "json");
        map.put("ak", strA3);
        map.put("from", "lbs_yunsdk");
        if (hashtable != null && hashtable.size() > 0) {
            for (Map.Entry<String, String> entry : hashtable.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (!TextUtils.isEmpty(key) && !TextUtils.isEmpty(value)) {
                    map.put(key, value);
                }
            }
        }
        try {
            strA = CommonParam.a(f1981a);
        } catch (Exception unused) {
            strA = "";
        }
        if (TextUtils.isEmpty(strA)) {
            map.put("cuid", "");
        } else {
            map.put("cuid", strA);
        }
        map.put("pcn", f1981a.getPackageName());
        map.put("version", VERSION);
        try {
            strC = b.c(f1981a);
        } catch (Exception unused2) {
            strC = "";
        }
        if (TextUtils.isEmpty(strC)) {
            map.put("macaddr", "");
        } else {
            map.put("macaddr", strC);
        }
        try {
            strA2 = b.a();
        } catch (Exception unused3) {
            strA2 = "";
        }
        if (TextUtils.isEmpty(strA2)) {
            map.put("language", "");
        } else {
            map.put("language", strA2);
        }
        if (z) {
            map.put("force", z ? "1" : AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
        }
        if (str == null) {
            map.put("from_service", "");
        } else {
            map.put("from_service", str);
        }
        this.f1987c = new e(f1981a);
        this.f1987c.a(map, strArr, new l(this, str2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b(String str) {
        String string;
        String strA = a(f1981a, str);
        try {
            JSONObject jSONObject = new JSONObject(e());
            if (!jSONObject.has("ak")) {
                return true;
            }
            string = jSONObject.getString("ak");
        } catch (JSONException e2) {
            e2.printStackTrace();
            string = "";
        }
        return (strA == null || string == null || strA.equals(string)) ? false : true;
    }

    private void c(String str) {
        f1981a.getSharedPreferences("authStatus_" + a(f1981a), 0).edit().putString(NotificationCompat.CATEGORY_STATUS, str).commit();
    }

    private void d() {
        synchronized (LBSAuthManager.class) {
            if (f1982d == null) {
                f1982d = new m("auth");
                f1982d.start();
                while (f1982d.f2015a == null) {
                    try {
                        if (a.f1989a) {
                            a.a("wait for create auth thread.");
                        }
                        Thread.sleep(3L);
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }
    }

    private String e() {
        return f1981a.getSharedPreferences("authStatus_" + a(f1981a), 0).getString(NotificationCompat.CATEGORY_STATUS, "{\"status\":601}");
    }

    public static LBSAuthManager getInstance(Context context) {
        if (f1985g == null) {
            synchronized (LBSAuthManager.class) {
                if (f1985g == null) {
                    f1985g = new LBSAuthManager(context);
                }
            }
        } else if (context != null) {
            f1981a = context;
        } else if (a.f1989a) {
            a.c("input context is null");
            new RuntimeException("here").printStackTrace();
        }
        return f1985g;
    }

    public int authenticate(boolean z, String str, Hashtable<String, String> hashtable, LBSAuthManagerListener lBSAuthManagerListener) {
        synchronized (LBSAuthManager.class) {
            String str2 = System.currentTimeMillis() + "";
            if (lBSAuthManagerListener != null) {
                f1984f.put(str2, lBSAuthManagerListener);
            }
            String strA = a(f1981a, str2);
            if (strA != null && !strA.equals("")) {
                f1983e++;
                if (a.f1989a) {
                    a.a(" mAuthCounter  ++ = " + f1983e);
                }
                String strE = e();
                if (a.f1989a) {
                    a.a("getAuthMessage from cache:" + strE);
                }
                int iA = a(strE);
                if (iA == 601) {
                    try {
                        c(new JSONObject().put(NotificationCompat.CATEGORY_STATUS, 602).toString());
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                }
                d();
                if (f1982d != null && f1982d.f2015a != null) {
                    if (a.f1989a) {
                        a.a("mThreadLooper.mHandler = " + f1982d.f2015a);
                    }
                    f1982d.f2015a.post(new j(this, iA, z, str2, str, hashtable));
                    return iA;
                }
                return -1;
            }
            return 101;
        }
    }

    public String getCUID() {
        Context context = f1981a;
        if (context == null) {
            return "";
        }
        try {
            return CommonParam.a(context);
        } catch (Exception e2) {
            if (!a.f1989a) {
                return "";
            }
            e2.printStackTrace();
            return "";
        }
    }

    public String getKey() {
        Context context = f1981a;
        if (context == null) {
            return "";
        }
        try {
            return getPublicKey(context);
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public String getMCode() {
        Context context = f1981a;
        return context == null ? "" : b.a(context);
    }

    public String getPublicKey(Context context) throws PackageManager.NameNotFoundException {
        return context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getString("com.baidu.lbsapi.API_KEY");
    }
}