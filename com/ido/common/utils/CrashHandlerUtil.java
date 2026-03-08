package com.ido.common.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.content.ContextCompat;
import com.ido.common.IdoApp;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.IOUtils;

/* JADX INFO: loaded from: classes2.dex */
public class CrashHandlerUtil implements Thread.UncaughtExceptionHandler {
    private static CrashHandlerUtil INSTANCE = new CrashHandlerUtil();
    public static final String TAG = "CrashHandlerUtil";
    private String dir;
    private Context mContext;
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    public String path;
    private Map<String, String> infos = new HashMap();
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

    private CrashHandlerUtil() {
    }

    public static CrashHandlerUtil getInstance() {
        return INSTANCE;
    }

    public void init(Context context) {
        this.mContext = context;
        this.mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    public void setCrashDir(String str) {
        this.dir = str;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        File file = new File(str);
        if (file.exists()) {
            return;
        }
        file.mkdirs();
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler;
        if (!handleException(th) && (uncaughtExceptionHandler = this.mDefaultHandler) != null) {
            uncaughtExceptionHandler.uncaughtException(thread, th);
            return;
        }
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e2) {
            Log.e(TAG, "error : ", e2);
        }
        Process.killProcess(Process.myPid());
        System.exit(1);
    }

    private boolean handleException(Throwable th) {
        if (th == null) {
            return false;
        }
        collectDeviceInfo(this.mContext);
        saveCrashInfo2File(th);
        return true;
    }

    public void collectDeviceInfo(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 1);
            if (packageInfo != null) {
                String str = packageInfo.versionName == null ? "null" : packageInfo.versionName;
                String str2 = packageInfo.versionCode + "";
                this.infos.put("versionName", str);
                this.infos.put("versionCode", str2);
            }
        } catch (PackageManager.NameNotFoundException e2) {
            Log.e(TAG, "an error occured when collect package info", e2);
        }
        for (Field field : Build.class.getDeclaredFields()) {
            try {
                field.setAccessible(true);
                this.infos.put(field.getName(), field.get(null).toString());
                Log.d(TAG, field.getName() + " : " + field.get(null));
            } catch (Exception e3) {
                Log.e(TAG, "an error occured when collect crash info", e3);
            }
        }
    }

    private String saveCrashInfo2File(Throwable th) {
        if (ContextCompat.checkSelfPermission(IdoApp.getAppContext(), "android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (Map.Entry<String, String> entry : this.infos.entrySet()) {
            stringBuffer.append(entry.getKey() + "=" + entry.getValue() + IOUtils.LINE_SEPARATOR_UNIX);
        }
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        for (Throwable cause = th.getCause(); cause != null; cause = cause.getCause()) {
            cause.printStackTrace(printWriter);
        }
        printWriter.close();
        stringBuffer.append(stringWriter.toString());
        try {
            String str = "crash_log_" + this.formatter.format(new Date()) + ".txt";
            if (Environment.getExternalStorageState().equals("mounted")) {
                if (TextUtils.isEmpty(this.dir)) {
                    this.dir = Environment.getExternalStorageDirectory().getAbsolutePath();
                } else {
                    deleteOldFile(this.dir);
                }
                File file = new File(this.dir);
                if (!file.exists()) {
                    file.mkdirs();
                }
                FileOutputStream fileOutputStream = new FileOutputStream(new File(this.dir, str));
                fileOutputStream.write(stringBuffer.toString().getBytes());
                fileOutputStream.close();
            }
            return str;
        } catch (Exception e2) {
            Log.e(TAG, "an error occured while writing url...", e2);
            return null;
        }
    }

    public static int daysOfTwo(Date date) {
        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(6);
        calendar.setTime(date);
        return i - calendar.get(6);
    }

    public static int daysOfTwo(long j) {
        return daysOfTwo(new Date(j));
    }

    private static void deleteOldFile(String str) {
        File[] fileArrListFiles;
        File file = new File(str);
        if (file.exists() && (fileArrListFiles = file.listFiles()) != null && file.length() > 10) {
            for (File file2 : fileArrListFiles) {
                if (daysOfTwo(file2.lastModified()) > 10) {
                    file2.delete();
                }
            }
        }
    }
}