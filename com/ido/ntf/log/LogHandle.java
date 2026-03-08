package com.ido.ntf.log;

/* JADX INFO: loaded from: classes3.dex */
public class LogHandle {
    LogBack logBack;

    private static class SingletonHolder {
        private static final LogHandle INSTANCE = new LogHandle();

        private SingletonHolder() {
        }
    }

    private LogHandle() {
    }

    public static final LogHandle getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void initLogListener(LogBack logBack) {
        this.logBack = logBack;
    }

    public LogBack getLogBack() {
        return this.logBack;
    }

    public void printLog(String str) {
        LogBack logBack = this.logBack;
        if (logBack == null) {
            return;
        }
        logBack.printLog(str);
    }
}