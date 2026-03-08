package com.ido.common.log;

import android.text.TextUtils;
import android.util.Log;
import androidx.core.app.ActivityCompat;
import com.ido.common.IdoApp;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes2.dex */
class LogService {
    private static final String FILE_NAME_PATTERN = "yyyyMMdd";
    private static final String FILE_TIMESTAMP_PATTERN = "yyyy-MM-dd HH:mm:ss.SSSZ";
    private static final String LOG_FILE_PREFIX_NAME = ".log";
    private static final String TAG = "[IDO_APP] LogService";
    private Thread mLogThread;
    private static final String LINE_SEP = System.getProperty("line.separator");
    private static LogService instance = new LogService();
    private static boolean isPermissionOk = false;
    private ConcurrentLinkedQueue<String[]> mLogQueue = new ConcurrentLinkedQueue<>();
    private volatile boolean mIsStopLog = false;
    private Lock mLock = new ReentrantLock();
    private Condition mCondition = this.mLock.newCondition();
    private Runnable mLooperRunnable = new Runnable() { // from class: com.ido.common.log.LogService.1
        @Override // java.lang.Runnable
        public void run() {
            LogService.this.deleteOutDateLog();
            while (true) {
                if (LogService.this.mIsStopLog) {
                    break;
                }
                LogService.this.mLock.lock();
                try {
                    try {
                        if (LogService.this.mLogQueue.isEmpty()) {
                            LogService.this.mCondition.await();
                        }
                    } catch (InterruptedException e2) {
                        Log.e(LogService.TAG, e2.getMessage(), e2);
                        Thread.currentThread().interrupt();
                    }
                    if (LogService.this.mIsStopLog) {
                        break;
                    }
                    String[] strArr = (String[]) LogService.this.mLogQueue.poll();
                    String str = strArr[0];
                    String str2 = strArr[1];
                    if (LogService.this.createLogFileDir(str)) {
                        LogService.this.writeToFile(str, str2);
                    } else {
                        Log.e(LogService.TAG, "createLogFileDir failed:" + str);
                    }
                    LogService.this.mLock.unlock();
                } finally {
                    LogService.this.mLock.unlock();
                }
            }
            Log.i(LogService.TAG, "exit loop ok!");
        }
    };

    private static void checkPermission() {
        int iCheckSelfPermission = ActivityCompat.checkSelfPermission(IdoApp.getAppContext(), "android.permission.WRITE_EXTERNAL_STORAGE");
        int iCheckSelfPermission2 = ActivityCompat.checkSelfPermission(IdoApp.getAppContext(), "android.permission.READ_EXTERNAL_STORAGE");
        if (iCheckSelfPermission == 0 && iCheckSelfPermission2 == 0) {
            isPermissionOk = true;
        } else {
            isPermissionOk = false;
            Log.e(TAG, "not allowed permission[WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE], LogTool is disabled!");
        }
    }

    public static void init() {
        Log.i(TAG, "init...");
        checkPermission();
        if (isPermissionOk) {
            instance.start();
        }
    }

    public static void destroy() {
        Log.i(TAG, "destroy...");
        if (isPermissionOk) {
            instance.stop();
        }
    }

    private LogService() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean createLogFileDir(String str) {
        File file = new File(str);
        if (file.exists()) {
            return true;
        }
        return file.mkdirs();
    }

    private void start() {
        this.mIsStopLog = false;
        if (this.mLogThread == null) {
            this.mLogThread = new Thread(this.mLooperRunnable);
        }
        if (this.mLogThread.isAlive()) {
            return;
        }
        this.mLogThread.start();
    }

    private void stop() {
        this.mIsStopLog = true;
        Thread thread = this.mLogThread;
        if (thread == null || !thread.isAlive()) {
            return;
        }
        this.mLock.lock();
        this.mCondition.signal();
        this.mLock.unlock();
        this.mLogQueue.clear();
        this.mLogThread = null;
    }

    public static void e(String str, String str2, String str3) {
        instance.writeLogToBuffer(str, "E", str2, str3);
    }

    public static void p(String str, String str2, String str3) {
        instance.writeLogToBuffer(str, "P", str2, str3);
    }

    private void writeLogToBuffer(String str, String str2, String str3, String str4) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3) || TextUtils.isEmpty(str4)) {
            return;
        }
        if (!isPermissionOk) {
            checkPermission();
            return;
        }
        Thread thread = this.mLogThread;
        if (thread == null || !thread.isAlive() || this.mIsStopLog) {
            start();
        }
        if (TextUtils.isEmpty(getLogPathSdcardDir())) {
            Log.e(TAG, "getLogPathSdcardDir or dirPath is null");
            return;
        }
        if (str3.length() < 20) {
            for (int length = str3.length(); length < 20; length++) {
                str3 = str3 + " ";
            }
        }
        String[] strArr = {str, str2 + "    [" + getLogTimeString() + "]        [" + str3 + "]    " + str4 + LINE_SEP};
        this.mLock.lock();
        this.mLogQueue.add(strArr);
        this.mCondition.signal();
        this.mLock.unlock();
    }

    private String getLogPathSdcardDir() {
        return IdoApp.getNormalLogFilePath();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v12 */
    /* JADX WARN: Type inference failed for: r6v13, types: [java.io.BufferedWriter] */
    /* JADX WARN: Type inference failed for: r6v16 */
    /* JADX WARN: Type inference failed for: r6v19 */
    /* JADX WARN: Type inference failed for: r6v25 */
    /* JADX WARN: Type inference failed for: r6v26 */
    /* JADX WARN: Type inference failed for: r6v27 */
    /* JADX WARN: Type inference failed for: r6v28 */
    /* JADX WARN: Type inference failed for: r6v9 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:18:0x0052 -> B:36:0x0071). Please report as a decompilation issue!!! */
    public void writeToFile(String str, String str2) throws Throwable {
        boolean zCreateNewFile;
        File file = new File(str + File.separator + getFileName());
        if (file.exists()) {
            zCreateNewFile = true;
        } else {
            try {
                zCreateNewFile = file.createNewFile();
            } catch (IOException e2) {
                e2.printStackTrace();
                Log.e(TAG, e2.toString());
                zCreateNewFile = true;
            }
        }
        if (!zCreateNewFile) {
            Log.e(TAG, "create log file failed!");
            return;
        }
        ?? r6 = 0;
        r6 = 0;
        BufferedWriter bufferedWriter = null;
        try {
        } catch (IOException e3) {
            String string = e3.toString();
            Log.e(TAG, string);
            r6 = string;
        }
        try {
            try {
                BufferedWriter bufferedWriter2 = new BufferedWriter(new FileWriter(file, true));
                try {
                    bufferedWriter2.write(str2);
                    bufferedWriter2.close();
                } catch (Exception e4) {
                    e = e4;
                    bufferedWriter = bufferedWriter2;
                    Log.e(TAG, e.toString());
                    r6 = bufferedWriter;
                    if (bufferedWriter != null) {
                        bufferedWriter.close();
                        r6 = bufferedWriter;
                    }
                } catch (Throwable th) {
                    th = th;
                    r6 = bufferedWriter2;
                    if (r6 != 0) {
                        try {
                            r6.close();
                        } catch (IOException e5) {
                            Log.e(TAG, e5.toString());
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

    /* JADX INFO: Access modifiers changed from: private */
    public void deleteOutDateLog() {
        List<String> allLogFileDirPathList = IdoApp.getAllLogFileDirPathList();
        if (allLogFileDirPathList == null || allLogFileDirPathList.size() == 0) {
            return;
        }
        Iterator<String> it = allLogFileDirPathList.iterator();
        while (it.hasNext()) {
            File file = new File(it.next());
            if (!file.exists()) {
                return;
            }
            File[] fileArrListFiles = file.listFiles();
            for (File file2 : fileArrListFiles) {
                if (!file2.isDirectory()) {
                    Date dateBefore = getDateBefore();
                    if (file2.getName().endsWith(LOG_FILE_PREFIX_NAME) && getFileDateByStr(file2.getName().replace(LOG_FILE_PREFIX_NAME, "")).before(dateBefore)) {
                        file2.delete();
                    }
                }
            }
        }
    }

    private Date getDateBefore() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(5, calendar.get(5) - IdoApp.getLogFileSaveDays());
        calendar.set(10, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        return calendar.getTime();
    }

    private synchronized Date getFileDateByStr(String str) {
        Date time;
        time = Calendar.getInstance().getTime();
        synchronized (LogService.class) {
            try {
                time = new SimpleDateFormat(FILE_NAME_PATTERN, Locale.getDefault()).parse(str);
            } catch (ParseException e2) {
                Log.e(TAG, e2.getMessage(), e2);
            }
        }
        return time;
    }

    private synchronized String getFileName() {
        String str;
        Date time = Calendar.getInstance().getTime();
        synchronized (LogService.class) {
            str = new SimpleDateFormat(FILE_NAME_PATTERN, Locale.getDefault()).format(time) + LOG_FILE_PREFIX_NAME;
        }
        return str;
    }

    private synchronized String getLogTimeString() {
        String str;
        Date time = Calendar.getInstance().getTime();
        synchronized (LogService.class) {
            str = new SimpleDateFormat(FILE_TIMESTAMP_PATTERN, Locale.getDefault()).format(time);
        }
        return str;
    }
}