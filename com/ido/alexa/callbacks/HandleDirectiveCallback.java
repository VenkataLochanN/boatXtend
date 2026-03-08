package com.ido.alexa.callbacks;

import java.util.Date;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface HandleDirectiveCallback {
    void cancelAlarm(String str);

    void cancelAlarms(List<String> list);

    void eauthorizerAmazon();

    void handlerAlarm(String str, Date date, long j);

    void handlerNotification(boolean z);

    void handlerRangeControlerResult();

    void handlerReminder(String str, Date date, long j, String str2);

    void handlerTimer(String str, int i);

    void handlerToggleControlerResult(boolean z);

    void stopRecord(String str);
}