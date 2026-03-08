package com.tencent.bugly.proguard;

import android.content.Context;
import android.os.Process;
import com.bumptech.glide.load.Key;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes3.dex */
public final class aa {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean f5582a = true;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static boolean f5583b = true;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static SimpleDateFormat f5584c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static int f5585d = 30720;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static StringBuilder f5586e = null;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static StringBuilder f5587f = null;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static boolean f5588g = false;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static a f5589h = null;
    private static String i = null;
    private static String j = null;
    private static Context k = null;
    private static String l = null;
    private static boolean m = false;
    private static boolean n = false;
    private static ExecutorService o;
    private static int p;
    private static final Object q = new Object();

    static {
        try {
            f5584c = new SimpleDateFormat("MM-dd HH:mm:ss");
        } catch (Throwable th) {
            y.b(th.getCause());
        }
    }

    public static synchronized void a(Context context) {
        if (m || context == null || !f5582a) {
            return;
        }
        try {
            o = Executors.newSingleThreadExecutor();
            f5587f = new StringBuilder(0);
            f5586e = new StringBuilder(0);
            k = context;
            com.tencent.bugly.crashreport.common.info.a aVarA = com.tencent.bugly.crashreport.common.info.a.a(context);
            i = aVarA.f5417d;
            aVarA.getClass();
            j = "";
            l = k.getFilesDir().getPath() + "/buglylog_" + i + "_" + j + ".txt";
            p = Process.myPid();
        } catch (Throwable unused) {
        }
        m = true;
    }

    public static void a(int i2) {
        synchronized (q) {
            f5585d = i2;
            if (i2 < 0) {
                f5585d = 0;
            } else if (i2 > 30720) {
                f5585d = 30720;
            }
        }
    }

    public static void a(String str, String str2, Throwable th) {
        if (th == null) {
            return;
        }
        String message = th.getMessage();
        if (message == null) {
            message = "";
        }
        a(str, str2, message + '\n' + ab.b(th));
    }

    public static synchronized void a(final String str, final String str2, final String str3) {
        if (m && f5582a) {
            try {
                o.execute(new Runnable() { // from class: com.tencent.bugly.proguard.aa.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        aa.c(str, str2, str3);
                    }
                });
            } catch (Exception e2) {
                y.b(e2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static synchronized void c(String str, String str2, String str3) {
        if (f5583b) {
            d(str, str2, str3);
        } else {
            e(str, str2, str3);
        }
    }

    private static synchronized void d(String str, String str2, String str3) {
        String strA = a(str, str2, str3, Process.myTid());
        synchronized (q) {
            try {
                f5587f.append(strA);
                if (f5587f.length() >= f5585d) {
                    f5587f = f5587f.delete(0, f5587f.indexOf("\u0001\r\n") + 1);
                }
            } finally {
            }
        }
    }

    private static synchronized void e(String str, String str2, String str3) {
        String strA = a(str, str2, str3, Process.myTid());
        synchronized (q) {
            try {
                f5587f.append(strA);
                if (f5587f.length() <= f5585d) {
                    return;
                }
                if (f5588g) {
                    return;
                }
                f5588g = true;
                if (f5589h == null) {
                    f5589h = new a(l);
                } else if (f5589h.f5594b == null || f5589h.f5594b.length() + ((long) f5587f.length()) > f5589h.f5597e) {
                    f5589h.a();
                }
                if (f5589h.a(f5587f.toString())) {
                    f5587f.setLength(0);
                    f5588g = false;
                }
            } catch (Throwable unused) {
            }
        }
    }

    private static String a(String str, String str2, String str3, long j2) {
        String string;
        f5586e.setLength(0);
        if (str3.length() > 30720) {
            str3 = str3.substring(str3.length() - 30720, str3.length() - 1);
        }
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = f5584c;
        if (simpleDateFormat != null) {
            string = simpleDateFormat.format(date);
        } else {
            string = date.toString();
        }
        StringBuilder sb = f5586e;
        sb.append(string);
        sb.append(" ");
        sb.append(p);
        sb.append(" ");
        sb.append(j2);
        sb.append(" ");
        sb.append(str);
        sb.append(" ");
        sb.append(str2);
        sb.append(": ");
        sb.append(str3);
        sb.append("\u0001\r\n");
        return f5586e.toString();
    }

    public static byte[] a() {
        if (f5583b) {
            if (f5582a) {
                return ab.a((File) null, f5587f.toString(), "BuglyLog.txt");
            }
            return null;
        }
        return b();
    }

    private static byte[] b() {
        if (!f5582a) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        synchronized (q) {
            if (f5589h != null && f5589h.f5593a && f5589h.f5594b != null && f5589h.f5594b.length() > 0) {
                sb.append(ab.a(f5589h.f5594b, 30720, true));
            }
            if (f5587f != null && f5587f.length() > 0) {
                sb.append(f5587f.toString());
            }
        }
        return ab.a((File) null, sb.toString(), "BuglyLog.txt");
    }

    /* JADX INFO: compiled from: BUGLY */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private boolean f5593a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private File f5594b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private String f5595c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private long f5596d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private long f5597e = 30720;

        public a(String str) {
            if (str == null || str.equals("")) {
                return;
            }
            this.f5595c = str;
            this.f5593a = a();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean a() {
            try {
                this.f5594b = new File(this.f5595c);
                if (this.f5594b.exists() && !this.f5594b.delete()) {
                    this.f5593a = false;
                    return false;
                }
                if (this.f5594b.createNewFile()) {
                    return true;
                }
                this.f5593a = false;
                return false;
            } catch (Throwable th) {
                y.a(th);
                this.f5593a = false;
                return false;
            }
        }

        public final boolean a(String str) {
            FileOutputStream fileOutputStream;
            if (!this.f5593a) {
                return false;
            }
            try {
                fileOutputStream = new FileOutputStream(this.f5594b, true);
            } catch (Throwable th) {
                th = th;
                fileOutputStream = null;
            }
            try {
                byte[] bytes = str.getBytes(Key.STRING_CHARSET_NAME);
                fileOutputStream.write(bytes);
                fileOutputStream.flush();
                fileOutputStream.close();
                this.f5596d += (long) bytes.length;
                this.f5593a = true;
                try {
                    fileOutputStream.close();
                } catch (IOException unused) {
                }
                return true;
            } catch (Throwable th2) {
                th = th2;
                try {
                    y.a(th);
                    this.f5593a = false;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException unused2) {
                        }
                    }
                    return false;
                } catch (Throwable th3) {
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException unused3) {
                        }
                    }
                    throw th3;
                }
            }
        }
    }
}