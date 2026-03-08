package com.baidu.location.g;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import com.baidu.android.bbalbs.common.util.CommonParam;

/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static String f2460e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static String f2461f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static String f2462g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static String f2463h;
    public static int i;
    private static b j;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f2464a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f2465b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f2466c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f2467d = null;
    private boolean k = false;

    private b() {
        if (com.baidu.location.f.getServiceContext() != null) {
            a(com.baidu.location.f.getServiceContext());
        }
    }

    public static b a() {
        if (j == null) {
            j = new b();
        }
        return j;
    }

    public String a(boolean z) {
        return a(z, (String) null);
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0118  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String a(boolean r4, java.lang.String r5) {
        /*
            Method dump skipped, instruction units count: 309
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.g.b.a(boolean, java.lang.String):java.lang.String");
    }

    public void a(Context context) {
        String serial;
        if (context == null || this.k) {
            return;
        }
        try {
            this.f2464a = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
        } catch (Exception unused) {
            this.f2464a = "NULL";
        }
        try {
            this.f2467d = Settings.Secure.getString(context.getContentResolver(), "android_id");
        } catch (Throwable unused2) {
            this.f2467d = null;
        }
        try {
            this.f2466c = CommonParam.a(context);
        } catch (Exception unused3) {
            this.f2466c = null;
        }
        try {
            f2460e = context.getPackageName();
        } catch (Exception unused4) {
            f2460e = null;
        }
        if (Build.VERSION.SDK_INT >= 26) {
            if (k.a(com.baidu.location.f.getServiceContext(), "android.permission.READ_PHONE_STATE") == 1) {
                serial = Build.getSerial();
            }
            k.o = "" + this.f2466c;
            this.k = true;
        }
        serial = Build.SERIAL;
        this.f2465b = serial;
        k.o = "" + this.f2466c;
        this.k = true;
    }

    public void a(String str, String str2) {
        f2461f = str;
        f2460e = str2;
    }

    public String b() {
        StringBuilder sb;
        String str;
        if (this.f2466c != null) {
            sb = new StringBuilder();
            sb.append("v7.72|");
            str = this.f2466c;
        } else {
            sb = new StringBuilder();
            sb.append("v7.72|");
            str = this.f2464a;
        }
        sb.append(str);
        sb.append("|");
        sb.append(Build.MODEL);
        return sb.toString();
    }

    public String c() {
        String str;
        StringBuffer stringBuffer = new StringBuffer(200);
        if (this.f2466c != null) {
            stringBuffer.append("&cu=");
            str = this.f2466c;
        } else {
            stringBuffer.append("&im=");
            str = this.f2464a;
        }
        stringBuffer.append(str);
        try {
            stringBuffer.append("&mb=");
            stringBuffer.append(Build.MODEL);
        } catch (Exception unused) {
        }
        stringBuffer.append("&pack=");
        try {
            stringBuffer.append(f2460e);
        } catch (Exception unused2) {
        }
        stringBuffer.append("&sdk=");
        stringBuffer.append(7.72f);
        return stringBuffer.toString();
    }

    public String d() {
        String str;
        StringBuffer stringBuffer = new StringBuffer();
        if (this.f2466c == null) {
            stringBuffer.append("&im=");
            str = this.f2464a;
        } else {
            stringBuffer.append("&cu=");
            str = this.f2466c;
        }
        stringBuffer.append(str);
        stringBuffer.append("&sdk=");
        stringBuffer.append(7.72f);
        stringBuffer.append("&mb=");
        stringBuffer.append(Build.MODEL);
        stringBuffer.append("&stp=1");
        stringBuffer.append("&os=A");
        stringBuffer.append(Build.VERSION.SDK);
        stringBuffer.append("&prod=");
        stringBuffer.append(f2461f + ":" + f2460e);
        stringBuffer.append(k.e(com.baidu.location.f.getServiceContext()));
        stringBuffer.append("&resid=");
        stringBuffer.append("12");
        return stringBuffer.toString();
    }
}