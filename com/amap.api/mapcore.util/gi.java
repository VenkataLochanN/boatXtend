package com.amap.api.mapcore.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.ido.alexa.AlexaCustomSkillConstant;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.MessageDigest;
import java.util.Locale;
import kotlin.UByte;

/* JADX INFO: compiled from: AppInfo.java */
/* JADX INFO: loaded from: classes.dex */
public class gi {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static String f1048a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static boolean f1049b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static String f1050c = "";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static String f1051d = "";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static String f1052e = "";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static String f1053f = "";

    private static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        str.toCharArray();
        for (char c2 : str.toCharArray()) {
            if (('A' > c2 || c2 > 'z') && (('0' > c2 || c2 > ':') && c2 != '.')) {
                try {
                    hn.b(gt.a(), str, "errorPackage");
                } catch (Throwable unused) {
                }
                return false;
            }
        }
        return true;
    }

    static boolean a() {
        if (f1049b) {
            return true;
        }
        if (a(f1048a)) {
            f1049b = true;
            return true;
        }
        if (!TextUtils.isEmpty(f1048a)) {
            f1049b = false;
            f1048a = null;
            return false;
        }
        if (a(f1051d)) {
            f1049b = true;
            return true;
        }
        if (!TextUtils.isEmpty(f1051d)) {
            f1049b = false;
            f1051d = null;
            return false;
        }
        return true;
    }

    public static String a(Context context) {
        try {
            return h(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return f1053f;
        }
    }

    public static String b(Context context) {
        try {
        } catch (Throwable th) {
            hk.a(th, "AI", "gAN");
        }
        if (!"".equals(f1050c)) {
            return f1050c;
        }
        PackageManager packageManager = context.getPackageManager();
        f1050c = (String) packageManager.getApplicationLabel(packageManager.getApplicationInfo(context.getPackageName(), 0));
        return f1050c;
    }

    public static String c(Context context) {
        try {
        } catch (Throwable th) {
            hk.a(th, "AI", "gpck");
        }
        if (f1051d != null && !"".equals(f1051d)) {
            return f1051d;
        }
        f1051d = context.getPackageName();
        if (!a(f1051d)) {
            f1051d = context.getPackageName();
        }
        return f1051d;
    }

    public static String d(Context context) {
        try {
        } catch (Throwable th) {
            hk.a(th, "AI", "gAV");
        }
        if (!"".equals(f1052e)) {
            return f1052e;
        }
        f1052e = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        String str = f1052e;
        return str == null ? "" : str;
    }

    public static String e(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 64);
            byte[] bArrDigest = MessageDigest.getInstance(gt.c("IU0hBMQ")).digest(packageInfo.signatures[0].toByteArray());
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b2 : bArrDigest) {
                String upperCase = Integer.toHexString(b2 & UByte.MAX_VALUE).toUpperCase(Locale.US);
                if (upperCase.length() == 1) {
                    stringBuffer.append(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
                }
                stringBuffer.append(upperCase);
                stringBuffer.append(":");
            }
            String strC = packageInfo.packageName;
            if (a(strC)) {
                strC = packageInfo.packageName;
            }
            if (!TextUtils.isEmpty(f1051d)) {
                strC = c(context);
            }
            stringBuffer.append(strC);
            f1048a = stringBuffer.toString();
            return f1048a;
        } catch (Throwable th) {
            hk.a(th, "AI", "gsp");
            return f1048a;
        }
    }

    static void a(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        f1053f = str;
        if (context != null) {
            b(context, str);
        }
    }

    public static String f(Context context) {
        try {
            gj.a(context);
        } catch (Throwable unused) {
        }
        try {
            return h(context);
        } catch (Throwable th) {
            hk.a(th, "AI", "gKy");
            return f1053f;
        }
    }

    private static void b(final Context context, final String str) {
        hn.d().submit(new Runnable() { // from class: com.amap.api.mapcore.util.gi.1
            @Override // java.lang.Runnable
            public void run() {
                FileOutputStream fileOutputStream;
                FileOutputStream fileOutputStream2 = null;
                try {
                    try {
                        File file = new File(hl.c(context, "k.store"));
                        if (!file.getParentFile().exists()) {
                            file.getParentFile().mkdirs();
                        }
                        fileOutputStream = new FileOutputStream(file);
                    } catch (Throwable th) {
                        th = th;
                    }
                    try {
                        fileOutputStream.write(gt.a(str));
                        fileOutputStream.close();
                    } catch (Throwable th2) {
                        th = th2;
                        fileOutputStream2 = fileOutputStream;
                        try {
                            hk.a(th, "AI", "stf");
                            if (fileOutputStream2 == null) {
                            } else {
                                fileOutputStream2.close();
                            }
                        } catch (Throwable th3) {
                            if (fileOutputStream2 != null) {
                                try {
                                    fileOutputStream2.close();
                                } catch (Throwable th4) {
                                    th4.printStackTrace();
                                }
                            }
                            throw th3;
                        }
                    }
                } catch (Throwable th5) {
                    th5.printStackTrace();
                }
            }
        });
    }

    private static String g(Context context) {
        FileInputStream fileInputStream;
        Throwable th;
        File file = new File(hl.c(context, "k.store"));
        if (!file.exists()) {
            return "";
        }
        try {
            fileInputStream = new FileInputStream(file);
        } catch (Throwable th2) {
            fileInputStream = null;
            th = th2;
        }
        try {
            byte[] bArr = new byte[fileInputStream.available()];
            fileInputStream.read(bArr);
            String strA = gt.a(bArr);
            if (strA.length() != 32) {
                strA = "";
            }
            try {
                fileInputStream.close();
            } catch (Throwable th3) {
                th3.printStackTrace();
            }
            return strA;
        } catch (Throwable th4) {
            th = th4;
            try {
                hk.a(th, "AI", "gKe");
                try {
                    if (file.exists()) {
                        file.delete();
                    }
                } catch (Throwable th5) {
                    th5.printStackTrace();
                }
                return "";
            } finally {
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (Throwable th6) {
                        th6.printStackTrace();
                    }
                }
            }
        }
    }

    private static String h(Context context) throws PackageManager.NameNotFoundException {
        String str = f1053f;
        if (str == null || str.equals("")) {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo == null || applicationInfo.metaData == null) {
                return f1053f;
            }
            f1053f = applicationInfo.metaData.getString("com.amap.api.v2.apikey");
            if (f1053f == null) {
                f1053f = g(context);
            }
        }
        return f1053f;
    }
}