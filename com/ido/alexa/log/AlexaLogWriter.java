package com.ido.alexa.log;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.app.ActivityCompat;
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
class AlexaLogWriter {
    private static final String FILE_NAME_PATTERN = "yyyyMMdd";
    private static final String FILE_TIMESTAMP_PATTERN = "yyyy-MM-dd HH:mm:ss.SSSZ";
    private static final String LOG_FILE_PREFIX_NAME = ".log";
    public static final String MP3_FILE_PREFIX_NAME = ".mp3";
    public static final String MP3_NAME_PATTERN = "yyyyMMddHHmmss";
    public static final String PCM_FILE_PREFIX_NAME = ".pcm";
    public static final String PCM_NAME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final String TAG = "[Alexa_SDK] LogService";
    private static Context mContext;
    private Thread mLogThread;
    private static final String LINE_SEP = System.getProperty("line.separator");
    private static AlexaLogWriter instance = new AlexaLogWriter();
    private static boolean isPermissionOk = false;
    private ConcurrentLinkedQueue<String[]> mLogQueue = new ConcurrentLinkedQueue<>();
    private volatile boolean mIsStopLog = false;
    private Lock mLock = new ReentrantLock();
    private Condition mCondition = this.mLock.newCondition();
    private Runnable mLooperRunnable = new Runnable() { // from class: com.ido.alexa.log.AlexaLogWriter.1
        @Override // java.lang.Runnable
        public void run() {
            AlexaLogWriter.this.deleteOutDateLog();
            AlexaLogWriter.this.deleteOutDatePCMLog();
            AlexaLogWriter.this.deleteOutDateMP3Log();
            while (true) {
                if (AlexaLogWriter.this.mIsStopLog) {
                    break;
                }
                AlexaLogWriter.this.mLock.lock();
                try {
                    try {
                        if (AlexaLogWriter.this.mLogQueue.isEmpty()) {
                            AlexaLogWriter.this.mCondition.await();
                        }
                    } catch (InterruptedException e2) {
                        Log.e(AlexaLogWriter.TAG, e2.getMessage(), e2);
                        Thread.currentThread().interrupt();
                    }
                    if (AlexaLogWriter.this.mIsStopLog) {
                        break;
                    }
                    String[] strArr = (String[]) AlexaLogWriter.this.mLogQueue.poll();
                    String str = strArr[0];
                    String str2 = strArr[1];
                    if (AlexaLogWriter.this.createLogFileDir(str)) {
                        AlexaLogWriter.this.writeToFile(str, str2);
                    } else {
                        Log.e(AlexaLogWriter.TAG, "createLogFileDir failed:" + str);
                    }
                    AlexaLogWriter.this.mLock.unlock();
                } finally {
                    AlexaLogWriter.this.mLock.unlock();
                }
            }
            Log.i(AlexaLogWriter.TAG, "exit loop ok!");
        }
    };
    int mLogFileSaveDays = 2;

    private static void checkPermission() {
        int iCheckSelfPermission = ActivityCompat.checkSelfPermission(mContext, "android.permission.WRITE_EXTERNAL_STORAGE");
        int iCheckSelfPermission2 = ActivityCompat.checkSelfPermission(mContext, "android.permission.READ_EXTERNAL_STORAGE");
        if (iCheckSelfPermission == 0 && iCheckSelfPermission2 == 0) {
            isPermissionOk = true;
        } else {
            isPermissionOk = false;
            Log.e(TAG, "not allowed permission[WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE], LogTool is disabled!");
        }
    }

    public static void init(Context context) {
        Log.i(TAG, "init...");
        mContext = context;
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

    private AlexaLogWriter() {
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

    /* JADX INFO: Access modifiers changed from: private */
    public void writeToFile(String str, String str2) throws Throwable {
        boolean zCreateNewFile;
        File file = new File(str + File.separator + getFileName());
        if (file.exists()) {
            zCreateNewFile = true;
        } else {
            try {
                zCreateNewFile = file.createNewFile();
            } catch (IOException e2) {
                Log.e(TAG, e2.toString());
                zCreateNewFile = true;
            }
        }
        if (!zCreateNewFile) {
            Log.e(TAG, "create log file failed!");
            return;
        }
        BufferedWriter bufferedWriter = null;
        try {
            try {
                try {
                    BufferedWriter bufferedWriter2 = new BufferedWriter(new FileWriter(file, true));
                    try {
                        bufferedWriter2.write(str2);
                        bufferedWriter2.close();
                    } catch (Exception e3) {
                        e = e3;
                        bufferedWriter = bufferedWriter2;
                        Log.e(TAG, e.toString());
                        if (bufferedWriter == null) {
                        } else {
                            bufferedWriter.close();
                        }
                    } catch (Throwable th) {
                        th = th;
                        bufferedWriter = bufferedWriter2;
                        if (bufferedWriter != null) {
                            try {
                                bufferedWriter.close();
                            } catch (IOException e4) {
                                Log.e(TAG, e4.toString());
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Exception e5) {
                e = e5;
            }
        } catch (IOException e6) {
            Log.e(TAG, e6.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deleteOutDateLog() {
        String alexaPath = AlexaLogPathImpl.getInstance().getAlexaPath();
        if (TextUtils.isEmpty(alexaPath)) {
            return;
        }
        File file = new File(alexaPath);
        if (file.exists()) {
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

    /* JADX INFO: Access modifiers changed from: private */
    public void deleteOutDatePCMLog() {
        String alexaPCMPath = AlexaLogPathImpl.getInstance().getAlexaPCMPath();
        if (TextUtils.isEmpty(alexaPCMPath)) {
            return;
        }
        File file = new File(alexaPCMPath);
        if (file.exists()) {
            File[] fileArrListFiles = file.listFiles();
            for (File file2 : fileArrListFiles) {
                if (!file2.isDirectory()) {
                    Date dateBefore = getDateBefore();
                    if (file2.getName().endsWith(PCM_FILE_PREFIX_NAME) && getPCMFileDateByStr(file2.getName().replace(PCM_FILE_PREFIX_NAME, "")).before(dateBefore)) {
                        file2.delete();
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deleteOutDateMP3Log() {
        String alexaPCMPath = AlexaLogPathImpl.getInstance().getAlexaPCMPath();
        if (TextUtils.isEmpty(alexaPCMPath)) {
            return;
        }
        File file = new File(alexaPCMPath);
        if (file.exists()) {
            File[] fileArrListFiles = file.listFiles();
            for (File file2 : fileArrListFiles) {
                if (!file2.isDirectory()) {
                    Date dateBefore = getDateBefore();
                    if (file2.getName().endsWith(MP3_FILE_PREFIX_NAME) && getMP3FileDateByStr(file2.getName().replace(MP3_FILE_PREFIX_NAME, "")).before(dateBefore)) {
                        file2.delete();
                    }
                }
            }
        }
    }

    private Date getDateBefore() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(5, calendar.get(5) - this.mLogFileSaveDays);
        calendar.set(10, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        return calendar.getTime();
    }

    private synchronized Date getFileDateByStr(String str) {
        Date time;
        time = Calendar.getInstance().getTime();
        synchronized (AlexaLogWriter.class) {
            try {
                time = new SimpleDateFormat(FILE_NAME_PATTERN, Locale.getDefault()).parse(str);
            } catch (ParseException e2) {
                Log.e(TAG, e2.getMessage(), e2);
            }
        }
        return time;
    }

    private synchronized Date getPCMFileDateByStr(String str) {
        Date time;
        time = Calendar.getInstance().getTime();
        synchronized (AlexaLogWriter.class) {
            try {
                time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA).parse(str);
            } catch (ParseException e2) {
                Log.e(TAG, e2.getMessage(), e2);
            }
        }
        return time;
    }

    private synchronized Date getMP3FileDateByStr(String str) {
        Date time;
        time = Calendar.getInstance().getTime();
        synchronized (AlexaLogWriter.class) {
            try {
                time = new SimpleDateFormat(MP3_NAME_PATTERN, Locale.CHINA).parse(str);
            } catch (ParseException e2) {
                Log.e(TAG, e2.getMessage(), e2);
            }
        }
        return time;
    }

    private synchronized String getFileName() {
        String str;
        Date time = Calendar.getInstance().getTime();
        synchronized (AlexaLogWriter.class) {
            str = new SimpleDateFormat(FILE_NAME_PATTERN, Locale.getDefault()).format(time) + LOG_FILE_PREFIX_NAME;
        }
        return str;
    }

    private synchronized String getLogTimeString() {
        String str;
        Date time = Calendar.getInstance().getTime();
        synchronized (AlexaLogWriter.class) {
            str = new SimpleDateFormat(FILE_TIMESTAMP_PATTERN, Locale.getDefault()).format(time);
        }
        return str;
    }
}