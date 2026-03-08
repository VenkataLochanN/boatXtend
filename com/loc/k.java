package com.loc;

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
/* JADX INFO: loaded from: classes3.dex */
public final class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static String f5236a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static boolean f5237b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static String f5238c = "";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static String f5239d = "";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static String f5240e = "";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static String f5241f = "";

    public static String a(Context context) {
        try {
            return h(context);
        } catch (Throwable th) {
            th.printStackTrace();
            return f5241f;
        }
    }

    static void a(final Context context, final String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        f5241f = str;
        if (context != null) {
            ab.d().submit(new Runnable() { // from class: com.loc.k.1
                @Override // java.lang.Runnable
                public final void run() {
                    FileOutputStream fileOutputStream;
                    FileOutputStream fileOutputStream2 = null;
                    try {
                        File file = new File(z.c(context, "k.store"));
                        if (!file.getParentFile().exists()) {
                            file.getParentFile().mkdirs();
                        }
                        fileOutputStream = new FileOutputStream(file);
                    } catch (Throwable th) {
                        th = th;
                    }
                    try {
                        fileOutputStream.write(u.a(str));
                        try {
                            fileOutputStream.close();
                        } catch (Throwable th2) {
                            th2.printStackTrace();
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        fileOutputStream2 = fileOutputStream;
                        try {
                            y.a(th, "AI", "stf");
                            if (fileOutputStream2 != null) {
                                try {
                                    fileOutputStream2.close();
                                } catch (Throwable th4) {
                                    th4.printStackTrace();
                                }
                            }
                        } catch (Throwable th5) {
                            if (fileOutputStream2 != null) {
                                try {
                                    fileOutputStream2.close();
                                } catch (Throwable th6) {
                                    th6.printStackTrace();
                                }
                            }
                            throw th5;
                        }
                    }
                }
            });
        }
    }

    public static void a(String str) {
        f5239d = str;
    }

    static boolean a() {
        if (f5237b) {
            return true;
        }
        if (b(f5236a)) {
            f5237b = true;
            return true;
        }
        if (!TextUtils.isEmpty(f5236a)) {
            f5237b = false;
            f5236a = null;
            return false;
        }
        if (b(f5239d)) {
            f5237b = true;
            return true;
        }
        if (!TextUtils.isEmpty(f5239d)) {
            f5237b = false;
            f5239d = null;
            return false;
        }
        return true;
    }

    public static String b(Context context) {
        try {
        } catch (Throwable th) {
            y.a(th, "AI", "gAN");
        }
        if (!"".equals(f5238c)) {
            return f5238c;
        }
        PackageManager packageManager = context.getPackageManager();
        f5238c = (String) packageManager.getApplicationLabel(packageManager.getApplicationInfo(context.getPackageName(), 0));
        return f5238c;
    }

    private static boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        str.toCharArray();
        for (char c2 : str.toCharArray()) {
            if (('A' > c2 || c2 > 'z') && (('0' > c2 || c2 > ':') && c2 != '.')) {
                try {
                    ab.b(u.a(), str, "errorPackage");
                } catch (Throwable unused) {
                }
                return false;
            }
        }
        return true;
    }

    public static String c(Context context) {
        try {
        } catch (Throwable th) {
            y.a(th, "AI", "gpck");
        }
        if (f5239d != null && !"".equals(f5239d)) {
            return f5239d;
        }
        String packageName = context.getPackageName();
        f5239d = packageName;
        if (!b(packageName)) {
            f5239d = context.getPackageName();
        }
        return f5239d;
    }

    public static String d(Context context) {
        try {
        } catch (Throwable th) {
            y.a(th, "AI", "gAV");
        }
        if (!"".equals(f5240e)) {
            return f5240e;
        }
        f5240e = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        String str = f5240e;
        return str == null ? "" : str;
    }

    public static String e(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 64);
            byte[] bArrDigest = MessageDigest.getInstance(u.c("IU0hBMQ")).digest(packageInfo.signatures[0].toByteArray());
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
            if (b(strC)) {
                strC = packageInfo.packageName;
            }
            if (!TextUtils.isEmpty(f5239d)) {
                strC = c(context);
            }
            stringBuffer.append(strC);
            String string = stringBuffer.toString();
            f5236a = string;
            return string;
        } catch (Throwable th) {
            y.a(th, "AI", "gsp");
            return f5236a;
        }
    }

    public static String f(Context context) {
        try {
            l.a(context);
        } catch (Throwable unused) {
        }
        try {
            return h(context);
        } catch (Throwable th) {
            y.a(th, "AI", "gKy");
            return f5241f;
        }
    }

    private static String g(Context context) {
        FileInputStream fileInputStream;
        Throwable th;
        File file = new File(z.c(context, "k.store"));
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
            String strA = u.a(bArr);
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
                y.a(th, "AI", "gKe");
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
        String str = f5241f;
        if (str == null || str.equals("")) {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo == null || applicationInfo.metaData == null) {
                return f5241f;
            }
            String string = applicationInfo.metaData.getString("com.amap.api.v2.apikey");
            f5241f = string;
            if (string == null) {
                f5241f = g(context);
            }
        }
        return f5241f;
    }
}