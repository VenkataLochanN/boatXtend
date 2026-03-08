package com.amap.api.mapcore.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Looper;
import android.text.TextUtils;
import com.ido.life.util.DateUtil;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import org.apache.commons.io.IOUtils;

/* JADX INFO: compiled from: Log.java */
/* JADX INFO: loaded from: classes.dex */
public class hl {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f1241a = "/a/";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final String f1242b = "b";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static final String f1243c = "c";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    static final String f1244d = "d";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static String f1245e = "s";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final String f1246f = "g";

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final String f1247g = "h";

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final String f1248h = "e";
    public static final String i = "f";
    public static final String j = "j";
    public static final String k = "k";
    private static long l;
    private static Vector<gs> m = new Vector<>();

    public static String a(Context context, String str) {
        return context.getSharedPreferences("AMSKLG_CFG", 0).getString(str, "");
    }

    public static void a(Context context, String str, String str2) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("AMSKLG_CFG", 0).edit();
        editorEdit.putString(str, str2);
        editorEdit.apply();
    }

    public static void b(Context context, String str) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("AMSKLG_CFG", 0).edit();
        editorEdit.remove(str);
        editorEdit.apply();
    }

    public static String c(Context context, String str) {
        return context.getFilesDir().getAbsolutePath() + f1241a + str;
    }

    public static void a(final Context context) {
        try {
            if (System.currentTimeMillis() - l < DateUtil.MINUTE) {
                return;
            }
            l = System.currentTimeMillis();
            ExecutorService executorServiceD = hn.d();
            if (executorServiceD != null && !executorServiceD.isShutdown()) {
                executorServiceD.submit(new Runnable() { // from class: com.amap.api.mapcore.util.hl.1
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            ho.b(context);
                            ho.d(context);
                            ho.c(context);
                            jb.a(context);
                            iz.a(context);
                        } catch (RejectedExecutionException unused) {
                        } catch (Throwable th) {
                            hn.c(th, "Lg", "proL");
                        }
                    }
                });
            }
        } catch (Throwable th) {
            hn.c(th, "Lg", "proL");
        }
    }

    public static void a(gs gsVar) {
        try {
            synchronized (Looper.getMainLooper()) {
                if (gsVar == null) {
                    return;
                }
                if (m.contains(gsVar)) {
                    return;
                }
                m.add(gsVar);
            }
        } catch (Throwable unused) {
        }
    }

    static List<gs> b(Context context) {
        Vector<gs> vector;
        try {
            synchronized (Looper.getMainLooper()) {
                vector = m;
            }
            return vector;
        } catch (Throwable th) {
            th.printStackTrace();
            return m;
        }
    }

    static boolean a(String[] strArr, String str) {
        if (strArr != null && str != null) {
            try {
                String[] strArrSplit = str.split(IOUtils.LINE_SEPARATOR_UNIX);
                for (String str2 : strArrSplit) {
                    if (a(str2.trim())) {
                        return false;
                    }
                }
                for (String str3 : strArrSplit) {
                    if (b(strArr, str3.trim())) {
                        return true;
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return false;
    }

    static boolean a(String str) {
        return !TextUtils.isEmpty(str) && str.startsWith("at ") && str.contains("uncaughtException");
    }

    static boolean b(String[] strArr, String str) {
        if (strArr != null && str != null) {
            try {
                String strTrim = str;
                for (String str2 : strArr) {
                    strTrim = strTrim.trim();
                    if (strTrim.startsWith("at ")) {
                        if (strTrim.contains(str2 + ".") && strTrim.endsWith(")") && !strTrim.contains("uncaughtException")) {
                            return true;
                        }
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return false;
    }
}