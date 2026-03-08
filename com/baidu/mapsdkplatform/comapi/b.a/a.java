package com.baidu.mapsdkplatform.comapi.b.a;

import com.bumptech.glide.load.Key;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread;
import java.net.URLEncoder;

/* JADX INFO: loaded from: classes.dex */
public class a implements Thread.UncaughtExceptionHandler {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static volatile boolean f3465b = false;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f3466a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Thread.UncaughtExceptionHandler f3467c;

    /* JADX INFO: renamed from: com.baidu.mapsdkplatform.comapi.b.a.a$a, reason: collision with other inner class name */
    private static class C0031a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static final a f3468a = new a();
    }

    private a() {
        this.f3466a = "";
        this.f3467c = Thread.getDefaultUncaughtExceptionHandler();
    }

    public static a a() {
        return C0031a.f3468a;
    }

    private void a(Throwable th) {
        if (th == null) {
            return;
        }
        String string = th.toString();
        if (string.isEmpty() || string.contains("BDMapSDKException")) {
            return;
        }
        if (string.contains("com.baidu.platform") || string.contains("com.baidu.mapsdkplatform") || string.contains("com.baidu.mapsdkvi")) {
            try {
                StringWriter stringWriter = new StringWriter();
                PrintWriter printWriter = new PrintWriter(stringWriter);
                th.printStackTrace(printWriter);
                Throwable cause = th.getCause();
                if (cause != null) {
                    cause.printStackTrace(printWriter);
                }
                printWriter.close();
                String string2 = stringWriter.toString();
                if (!string2.isEmpty() && this.f3466a != null && !this.f3466a.isEmpty()) {
                    File file = new File(URLEncoder.encode(this.f3466a + (System.currentTimeMillis() / 1000) + ".txt", Key.STRING_CHARSET_NAME));
                    if (file.exists() || file.createNewFile()) {
                        FileOutputStream fileOutputStream = new FileOutputStream(file);
                        fileOutputStream.write(string2.getBytes());
                        fileOutputStream.close();
                    }
                }
            } catch (Exception unused) {
            }
        }
    }

    protected void a(String str) {
        this.f3466a = str;
        if (Thread.getDefaultUncaughtExceptionHandler() instanceof a) {
            return;
        }
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        if (f3465b) {
            return;
        }
        f3465b = true;
        a(th);
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.f3467c;
        if (uncaughtExceptionHandler != null) {
            uncaughtExceptionHandler.uncaughtException(thread, th);
        }
    }
}