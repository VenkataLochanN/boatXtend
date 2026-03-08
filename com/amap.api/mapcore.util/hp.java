package com.amap.api.mapcore.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import java.util.Arrays;

/* JADX INFO: compiled from: SPUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class hp {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static byte[] f1281a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static byte[] f1282b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f1283c;

    public hp(String str) {
        this.f1283c = gq.b(TextUtils.isDigitsOnly(str) ? "SPUtil" : str);
    }

    public void a(Context context, String str, String str2) {
        if (context == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        try {
            SharedPreferences.Editor editorEdit = context.getSharedPreferences(this.f1283c, 0).edit();
            editorEdit.putString(str, gt.g(a(context, gt.a(str2))));
            b(editorEdit);
        } catch (Throwable unused) {
        }
    }

    public static byte[] a(Context context, byte[] bArr) {
        try {
            return gn.b(a(context), bArr, b(context));
        } catch (Throwable unused) {
            return new byte[0];
        }
    }

    public static byte[] b(Context context, byte[] bArr) {
        try {
            return gn.a(a(context), bArr, b(context));
        } catch (Exception unused) {
            return new byte[0];
        }
    }

    private static byte[] a(Context context) {
        if (context == null) {
            return new byte[0];
        }
        byte[] bArr = f1281a;
        if (bArr != null && bArr.length > 0) {
            return bArr;
        }
        f1281a = gi.f(context).getBytes();
        return f1281a;
    }

    private static byte[] b(Context context) {
        byte[] bArr = f1282b;
        if (bArr != null && bArr.length > 0) {
            return bArr;
        }
        int i = 0;
        if (Build.VERSION.SDK_INT >= 9) {
            f1282b = Arrays.copyOfRange(a(context), 0, a(context).length / 2);
        } else {
            f1282b = new byte[a(context).length / 2];
            while (true) {
                byte[] bArr2 = f1282b;
                if (i >= bArr2.length) {
                    break;
                }
                bArr2[i] = a(context)[i];
                i++;
            }
        }
        return f1282b;
    }

    public String a(Context context, String str) {
        if (context == null) {
            return "";
        }
        try {
            return gt.a(b(context, gt.e(context.getSharedPreferences(this.f1283c, 0).getString(str, ""))));
        } catch (Throwable unused) {
            return "";
        }
    }

    private void b(SharedPreferences.Editor editor) {
        if (editor == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 9) {
            editor.apply();
        } else {
            editor.commit();
        }
    }

    public static SharedPreferences.Editor b(Context context, String str) {
        if (context != null) {
            try {
                if (!TextUtils.isEmpty(str)) {
                    return context.getSharedPreferences(str, 0).edit();
                }
            } catch (Throwable th) {
                hk.a(th, "sp", "ge");
            }
        }
        return null;
    }

    public static void a(SharedPreferences.Editor editor) {
        if (editor == null) {
            return;
        }
        try {
            editor.apply();
        } catch (Throwable th) {
            hk.a(th, "sp", "cm");
        }
    }

    public static void a(SharedPreferences.Editor editor, String str, boolean z) {
        try {
            editor.putBoolean(str, z);
        } catch (Throwable th) {
            hn.c(th, "csp", "setPrefsStr");
        }
    }

    public static void a(SharedPreferences.Editor editor, String str, int i) {
        try {
            editor.putInt(str, i);
        } catch (Throwable th) {
            hn.c(th, "csp", "putPrefsInt");
        }
    }

    public static boolean a(Context context, String str, String str2, boolean z) {
        try {
            return context.getSharedPreferences(str, 0).getBoolean(str2, z);
        } catch (Throwable th) {
            hn.c(th, "csp", "gbv");
            return z;
        }
    }

    public static int a(Context context, String str, String str2, int i) {
        try {
            return context.getSharedPreferences(str, 0).getInt(str2, i);
        } catch (Throwable th) {
            hn.c(th, "csp", "giv");
            return i;
        }
    }

    public static void a(SharedPreferences.Editor editor, String str, long j) {
        if (editor == null || TextUtils.isEmpty(str)) {
            return;
        }
        try {
            editor.putLong(str, j);
        } catch (Throwable th) {
            hn.c(th, "csp", "plv");
        }
    }

    public static void a(SharedPreferences.Editor editor, String str, String str2) {
        if (editor != null) {
            try {
                if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                    editor.putString(str, str2);
                }
            } catch (Throwable th) {
                hk.a(th, "sp", "ps");
            }
        }
    }

    public static String a(Context context, String str, String str2, String str3) {
        if (context == null) {
            return null;
        }
        try {
            return context.getSharedPreferences(str, 0).getString(str2, str3);
        } catch (Throwable th) {
            hn.c(th, "csp", "gsv");
            return str3;
        }
    }

    public static void a(SharedPreferences.Editor editor, String str) {
        if (editor != null) {
            try {
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                editor.remove(str);
            } catch (Throwable th) {
                hk.a(th, "sp", "rk");
            }
        }
    }
}