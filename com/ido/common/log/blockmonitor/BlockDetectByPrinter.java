package com.ido.common.log.blockmonitor;

import android.os.Looper;
import android.util.Printer;

/* JADX INFO: loaded from: classes2.dex */
public class BlockDetectByPrinter {
    public static void start() {
        Looper.getMainLooper().setMessageLogging(new Printer() { // from class: com.ido.common.log.blockmonitor.BlockDetectByPrinter.1
            private static final String END = "<<<<< Finished";
            private static final String START = ">>>>> Dispatching";

            @Override // android.util.Printer
            public void println(String str) {
                if (str.startsWith(START)) {
                    LogMonitor.getInstance().startMonitor();
                }
                if (str.startsWith(END)) {
                    LogMonitor.getInstance().removeMonitor();
                }
            }
        });
    }
}