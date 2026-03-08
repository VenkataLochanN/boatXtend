package com.ido.ble.logs;

import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.app.ActivityCompat;
import com.ido.ble.common.e;
import com.ido.ble.common.f;
import com.ido.ble.custom.CustomConfig;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes2.dex */
public class LogTool {
    private static final String i = "yyyyMMdd";
    private static final String j = "yyyy-MM-dd HH:mm:ss.SSSZ";
    private static final String l = "[IDO_BLE_SDK] LogTool";
    private static final int m = 7;
    private static final String n = ".log";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Thread f4627c;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private LogListener f4631g;
    private static final String k = System.getProperty("line.separator");
    private static LogTool o = new LogTool();
    private static boolean p = false;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ConcurrentLinkedQueue<String> f4625a = new ConcurrentLinkedQueue<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private volatile boolean f4626b = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f4628d = "";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Lock f4629e = new ReentrantLock();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Condition f4630f = this.f4629e.newCondition();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private Runnable f4632h = new Runnable() { // from class: com.ido.ble.logs.LogTool.1
        @Override // java.lang.Runnable
        public void run() throws Throwable {
            String str;
            if (!LogTool.this.b()) {
                Log.e(LogTool.l, "createLogFileDir failed");
                return;
            }
            LogTool.this.c();
            while (true) {
                if (LogTool.this.f4626b) {
                    break;
                }
                LogTool.this.f4629e.lock();
                try {
                    try {
                        if (LogTool.this.f4625a.isEmpty()) {
                            LogTool.this.f4630f.await();
                        }
                    } catch (InterruptedException e2) {
                        Log.e(LogTool.l, e2.getMessage(), e2);
                        Thread.currentThread().interrupt();
                        str = "";
                    }
                    if (LogTool.this.f4626b) {
                        break;
                    }
                    str = (String) LogTool.this.f4625a.poll();
                    LogTool.this.f4629e.unlock();
                    LogTool.this.b(str);
                } finally {
                    LogTool.this.f4629e.unlock();
                }
            }
            Log.i(LogTool.l, "exit loop ok!");
        }
    };

    public interface LogListener {
        void onLog(String str);
    }

    private LogTool() {
    }

    private synchronized Date a(String str) {
        Date time;
        time = Calendar.getInstance().getTime();
        synchronized (LogTool.class) {
            try {
                time = new SimpleDateFormat(i, Locale.getDefault()).parse(str);
            } catch (ParseException e2) {
                Log.e(l, e2.getMessage(), e2);
            }
        }
        return time;
    }

    private static void a() {
        p = ActivityCompat.checkSelfPermission(e.a(), "android.permission.WRITE_EXTERNAL_STORAGE") == 0 && ActivityCompat.checkSelfPermission(e.a(), "android.permission.READ_EXTERNAL_STORAGE") == 0;
    }

    public static void a(LogListener logListener) {
        o.f4631g = logListener;
    }

    public static void a(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        Log.d(str, str2);
    }

    private synchronized void a(String str, String str2, String str3) {
        if (!p) {
            a();
            return;
        }
        Thread thread = this.f4627c;
        if (thread == null || !thread.isAlive() || this.f4626b) {
            j();
        }
        if (TextUtils.isEmpty(g())) {
            Log.e(l, "getLogPathSdcardDir is null");
            return;
        }
        if (!TextUtils.isEmpty(str2) && str2.length() < 20) {
            for (int length = str2.length(); length < 20; length++) {
                str2 = str2 + " ";
            }
        }
        String str4 = str + "    [" + h() + "]        [" + str2 + "]    " + str3 + k;
        LogListener logListener = this.f4631g;
        if (logListener != null) {
            logListener.onLog(str3 + k);
        }
        this.f4629e.lock();
        this.f4625a.add(str4);
        this.f4630f.signal();
        this.f4629e.unlock();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:32:0x0075 -> B:40:0x007c). Please report as a decompilation issue!!! */
    public void b(String str) throws Throwable {
        boolean zCreateNewFile;
        if (!CustomConfig.getConfig().isEnableLog()) {
            return;
        }
        BufferedWriter bufferedWriter = null;
        File file = new File(g() + File.separator + f());
        if (file.exists()) {
            zCreateNewFile = true;
        } else {
            try {
                zCreateNewFile = file.createNewFile();
            } catch (IOException e2) {
                Log.e(l, e2.toString());
                zCreateNewFile = true;
            }
        }
        try {
            try {
            } catch (IOException e3) {
                Log.e(l, e3.toString());
            }
            if (!zCreateNewFile) {
                Log.e(l, "create log file failed!");
                return;
            }
            try {
                BufferedWriter bufferedWriter2 = new BufferedWriter(new FileWriter(file, true));
                try {
                    bufferedWriter2.write(str);
                    bufferedWriter2.close();
                } catch (Exception e4) {
                    e = e4;
                    bufferedWriter = bufferedWriter2;
                    Log.e(l, e.toString());
                    if (bufferedWriter != null) {
                        bufferedWriter.close();
                    }
                } catch (Throwable th) {
                    th = th;
                    bufferedWriter = bufferedWriter2;
                    if (bufferedWriter != null) {
                        try {
                            bufferedWriter.close();
                        } catch (IOException e5) {
                            Log.e(l, e5.toString());
                        }
                    }
                    throw th;
                }
            } catch (Exception e6) {
                e = e6;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static void b(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        Log.e(str, str2);
        o.a("E", str, str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b() {
        File file = new File(g());
        if (file.exists()) {
            return true;
        }
        return file.mkdirs();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        File[] fileArrListFiles;
        File file = new File(this.f4628d);
        if (!file.exists() || (fileArrListFiles = file.listFiles()) == null || fileArrListFiles.length == 0) {
            return;
        }
        for (File file2 : fileArrListFiles) {
            if (!file2.isDirectory()) {
                Date dateE = e();
                if (file2.getName().endsWith(n) && a(file2.getName().replace(n, "")).before(dateE)) {
                    file2.delete();
                }
            }
        }
    }

    public static void c(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        Log.i(str, str2);
    }

    public static void d() {
        Log.i(l, "destroy...");
        if (p) {
            o.k();
        }
    }

    public static void d(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        Log.i(str, str2);
        o.a("P", str, str2);
    }

    private Date e() {
        int logSaveDays = CustomConfig.getConfig().getLogSaveDays() <= 0 ? 7 : CustomConfig.getConfig().getLogSaveDays();
        Calendar calendar = Calendar.getInstance();
        calendar.set(5, calendar.get(5) - logSaveDays);
        calendar.set(10, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        return calendar.getTime();
    }

    public static void e(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        Log.v(str, str2);
    }

    private synchronized String f() {
        String str;
        Date time = Calendar.getInstance().getTime();
        synchronized (LogTool.class) {
            str = new SimpleDateFormat(i, Locale.getDefault()).format(time) + n;
        }
        return str;
    }

    public static void f(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        Log.w(str, str2);
    }

    private String g() {
        String logSavePath;
        if (this.f4628d.equals("") && Environment.getExternalStorageState().equals("mounted")) {
            if (TextUtils.isEmpty(CustomConfig.getConfig().getLogSavePath())) {
                logSavePath = f.a() + "Log";
            } else {
                logSavePath = CustomConfig.getConfig().getLogSavePath();
            }
            this.f4628d = logSavePath;
        }
        return this.f4628d;
    }

    private synchronized String h() {
        String str;
        Date time = Calendar.getInstance().getTime();
        synchronized (LogTool.class) {
            str = new SimpleDateFormat(j, Locale.getDefault()).format(time);
        }
        return str;
    }

    public static void i() {
        Log.i(l, "init...");
        a();
        if (p) {
            o.j();
        }
    }

    private void j() {
        this.f4626b = false;
        if (this.f4627c == null) {
            this.f4627c = new Thread(this.f4632h);
        }
        if (this.f4627c.isAlive()) {
            return;
        }
        try {
            this.f4627c.start();
        } catch (IllegalThreadStateException unused) {
        }
    }

    private void k() {
        this.f4626b = true;
        Thread thread = this.f4627c;
        if (thread == null || !thread.isAlive()) {
            return;
        }
        this.f4629e.lock();
        this.f4630f.signal();
        this.f4629e.unlock();
        this.f4625a.clear();
        this.f4627c = null;
    }
}