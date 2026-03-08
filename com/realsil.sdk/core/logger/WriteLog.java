package com.realsil.sdk.core.logger;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import com.bumptech.glide.load.Key;
import com.ido.life.util.DateUtil;
import com.realsil.sdk.core.utility.FileUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
public class WriteLog {
    public static final int RETINTION_DAYS_DEF = 7;
    public static final int RETINTION_DAYS_INFINITE = -1;
    public static String Sb = "/sdcard/btsnoop_hci.log";
    public static final String[] Tb = {"logcat", "-c"};
    public static volatile WriteLog Ub = null;
    public static boolean u = true;
    public String Vb;
    public int Xb;
    public String Yb;
    public String Zb;
    public Context mContext;
    public Process process;
    public final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(DateUtil.DATE_FORMAT_YMD, Locale.US);
    public String Wb = "/saveLog/";

    public WriteLog(Context context, String str, int i) {
        this.Vb = "Realtek";
        this.Xb = -1;
        this.mContext = context;
        this.Vb = str;
        this.Xb = i;
        initialize();
    }

    public static WriteLog getInstance() {
        return Ub;
    }

    public static synchronized void install(Context context) {
        Ub = new WriteLog(context.getApplicationContext(), context.getPackageName(), -1);
    }

    public static synchronized void install(Context context, String str) {
        Ub = new WriteLog(context.getApplicationContext(), str, -1);
    }

    public static synchronized void install(Context context, String str, int i) {
        Ub = new WriteLog(context.getApplicationContext(), str, i);
    }

    public final void a(List<String> list) {
        try {
            String[] strArr = (String[]) list.toArray(new String[list.size()]);
            Runtime.getRuntime().exec(strArr);
            boolean z = u;
            StringBuilder sb = new StringBuilder();
            sb.append("[>_]");
            sb.append(Arrays.toString(strArr));
            ZLogger.i(z, sb.toString());
        } catch (Exception e2) {
            ZLogger.e(u, e2.toString());
        }
    }

    public boolean deleteLog() {
        try {
            return new File(this.Yb).delete();
        } catch (Exception e2) {
            ZLogger.e(e2.toString());
            return false;
        }
    }

    public final void initialize() {
        ZLogger.d("mRetionDays=" + this.Xb);
        ZLogger.d("mRootDirName=" + this.Vb);
        this.Wb = p() + "saveLog/";
        ZLogger.v("mSaveLogDir=" + this.Wb);
        Sb = q();
        ZLogger.v("SYS_BTSNOOP_FILE_NAME=" + Sb);
        n();
    }

    public final void l() {
        if (this.Xb <= -1) {
            ZLogger.d("保留所有日志数据： " + this.Xb);
            return;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.add(5, 0 - this.Xb);
        calendar.set(10, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        File file = new File(this.Wb);
        if (!file.exists()) {
            ZLogger.w(String.format("%s 不存在", file.getAbsolutePath()));
            return;
        }
        if (!file.isDirectory()) {
            ZLogger.w(String.format("%s 不是目录", file.getAbsolutePath()));
            return;
        }
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles == null || fileArrListFiles.length <= 0) {
            ZLogger.w("日志目录为空");
            return;
        }
        for (File file2 : fileArrListFiles) {
            if (!file2.isDirectory()) {
                String name = file2.getName();
                String suffix = FileUtils.getSuffix(file2);
                if (!TextUtils.isEmpty(name) && name.length() >= 10 && !TextUtils.isEmpty(suffix) && ("logcat".equals(suffix) || "cfa".equals(suffix))) {
                    try {
                        if (this.DATE_FORMAT.parse(name.substring(0, 10)).before(calendar.getTime())) {
                            file2.delete();
                        }
                    } catch (ParseException e2) {
                        file2.delete();
                        ZLogger.e(e2.toString());
                    }
                }
            }
        }
    }

    public final void m() {
        try {
            Process processExec = Runtime.getRuntime().exec(Tb);
            boolean z = u;
            StringBuilder sb = new StringBuilder();
            sb.append("[>_]");
            sb.append(Arrays.toString(Tb));
            ZLogger.d(z, sb.toString());
            processExec.destroy();
        } catch (Exception e2) {
            ZLogger.e(u, e2.toString());
        }
    }

    public final boolean n() {
        File file = new File(this.Wb);
        if (file.isDirectory()) {
            return false;
        }
        ZLogger.d(u, "createLogDir start");
        boolean zMkdirs = file.mkdirs();
        ZLogger.d(u, "mkdirs " + zMkdirs);
        return zMkdirs;
    }

    public final void o() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("logcat");
        arrayList.add("-f");
        arrayList.add(this.Yb);
        arrayList.add("-v");
        arrayList.add("time");
        try {
            String[] strArr = (String[]) arrayList.toArray(new String[arrayList.size()]);
            this.process = Runtime.getRuntime().exec(strArr);
            boolean z = u;
            StringBuilder sb = new StringBuilder();
            sb.append("[>_]");
            sb.append(Arrays.toString(strArr));
            ZLogger.i(z, sb.toString());
        } catch (Exception e2) {
            ZLogger.e(u, e2.toString());
        }
    }

    public final String p() {
        StringBuilder sb;
        String packageName;
        if (Environment.getExternalStorageState().equals("mounted")) {
            sb = new StringBuilder();
            packageName = Environment.getExternalStorageDirectory().getAbsolutePath();
        } else {
            sb = new StringBuilder();
            sb.append("/data/data/");
            packageName = this.mContext.getPackageName();
        }
        sb.append(packageName);
        sb.append("/");
        sb.append(this.Vb);
        sb.append("/");
        return sb.toString();
    }

    public final String q() {
        BufferedReader bufferedReader;
        String line;
        try {
            InputStreamReader inputStreamReader = null;
            try {
                inputStreamReader = new InputStreamReader(new FileInputStream(new File("/etc/bluetooth/bt_stack.conf")), Key.STRING_CHARSET_NAME);
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
            }
            bufferedReader = new BufferedReader(inputStreamReader);
        } catch (Exception e3) {
            e3.printStackTrace();
            return "/sdcard/btsnoop_hci.cfa";
        }
        do {
            try {
                line = bufferedReader.readLine();
                if (line == null) {
                    return "/sdcard/btsnoop_hci.cfa";
                }
            } catch (IOException e4) {
                e4.printStackTrace();
                return "/sdcard/btsnoop_hci.cfa";
            }
            e3.printStackTrace();
            return "/sdcard/btsnoop_hci.cfa";
        } while (!line.contains("BtSnoopFileName="));
        boolean z = u;
        StringBuilder sb = new StringBuilder();
        sb.append("line: ");
        sb.append(line);
        ZLogger.v(z, sb.toString());
        return line.substring(16);
    }

    public void restartLog() {
        restartLog(false);
    }

    public void restartLog(boolean z) {
        stopLog();
        if (z) {
            deleteLog();
        }
        startLog();
    }

    public void saveHciLog() {
        n();
        ArrayList arrayList = new ArrayList();
        arrayList.add("cp");
        arrayList.add(Sb);
        arrayList.add(this.Zb);
        a(arrayList);
    }

    public void saveHciLogByTime() {
        n();
        ArrayList arrayList = new ArrayList();
        arrayList.add("cp");
        arrayList.add(Sb);
        arrayList.add(this.Wb + new SimpleDateFormat("MM_dd_HH_mm_ss", Locale.US).format(new Date()) + ".cfa");
        a(arrayList);
    }

    public void setRetentionDays(int i) {
        this.Xb = i;
    }

    public void startLog() {
        n();
        String str = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss", Locale.US).format(new Date());
        this.Yb = this.Wb + str + ".logcat";
        this.Zb = this.Wb + str + "_btsnoop.cfa";
        m();
        o();
        l();
    }

    public void stopLog() {
        Process process = this.process;
        if (process != null) {
            process.destroy();
        }
    }
}