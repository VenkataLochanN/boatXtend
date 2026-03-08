package com.ido.ntf.exception;

import android.content.IntentFilter;
import com.ido.ntf.Utils.TimeUtil;
import com.ido.ntf.callback.INotificationExceptionListener;

/* JADX INFO: loaded from: classes3.dex */
public class ExceptionHandle {
    INotificationExceptionListener mNotificationExceptionListener;

    private static class SingletonHolder {
        private static final ExceptionHandle INSTANCE = new ExceptionHandle();

        private SingletonHolder() {
        }
    }

    private ExceptionHandle() {
    }

    public static final ExceptionHandle getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void initExceptionListener(int i, INotificationExceptionListener iNotificationExceptionListener) {
        TimeUtil.setExceptionTime(i);
        this.mNotificationExceptionListener = iNotificationExceptionListener;
        new IntentFilter().addAction("android.ido.ntf.notification_exception");
    }
}