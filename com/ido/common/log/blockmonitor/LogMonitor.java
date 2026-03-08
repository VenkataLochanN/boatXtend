package com.ido.common.log.blockmonitor;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.text.TextUtils;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import org.apache.commons.io.IOUtils;

/* JADX INFO: loaded from: classes2.dex */
public class LogMonitor {
    private static final long TIME_BLOCK = 2000;
    private Handler mIoHandler;
    private HandlerThread mLogThread = new HandlerThread("log");
    private static LogMonitor sInstance = new LogMonitor();
    private static final String TAG = LogMonitor.class.getSimpleName();
    private static Runnable mLogRunnable = new Runnable() { // from class: com.ido.common.log.blockmonitor.LogMonitor.1
        @Override // java.lang.Runnable
        public void run() {
            StringBuilder sb = new StringBuilder();
            for (StackTraceElement stackTraceElement : Looper.getMainLooper().getThread().getStackTrace()) {
                sb.append(stackTraceElement.toString() + IOUtils.LINE_SEPARATOR_UNIX);
            }
            CommonLogUtil.e("TAG卡顿日志", sb.toString());
            LogMonitor.printLog(sb.toString());
        }
    };

    public boolean isMonitor() {
        return true;
    }

    private LogMonitor() {
        this.mLogThread.start();
        this.mIoHandler = new Handler(this.mLogThread.getLooper());
    }

    public static LogMonitor getInstance() {
        return sInstance;
    }

    public void startMonitor() {
        this.mIoHandler.postDelayed(mLogRunnable, 2000L);
    }

    public void removeMonitor() {
        this.mIoHandler.removeCallbacks(mLogRunnable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void printLog(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getBugLogPath(), TAG, str);
    }
}