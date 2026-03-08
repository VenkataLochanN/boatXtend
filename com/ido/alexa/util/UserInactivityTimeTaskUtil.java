package com.ido.alexa.util;

import com.ido.alexa.bean.AvsException;
import com.ido.alexa.callbacks.ImplAsyncCallback;
import com.ido.alexa.data.Event;
import com.ido.alexa.log.AlexaLogUtil;
import com.ido.alexa.manager.AlexaAudioEventManger;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: classes2.dex */
public class UserInactivityTimeTaskUtil {
    private static final long ONE_HOUR = 3600000;
    private static long inactiveTimeInSeconds;
    private static TimerTask task;
    private static Timer timer;

    public static void start() {
        stop();
        AlexaLogUtil.printAndSave("Alexa空闲开始计时开始");
        inactiveTimeInSeconds = 0L;
        timer = new Timer();
        task = new TimerTask() { // from class: com.ido.alexa.util.UserInactivityTimeTaskUtil.1
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                UserInactivityTimeTaskUtil.inactiveTimeInSeconds += 3600;
                UserInactivityTimeTaskUtil.reportEvent();
            }
        };
        timer.schedule(task, 3600000L, 3600000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void reportEvent() {
        AlexaAudioEventManger.getInstance().sendEvent(Event.getUserInactivityReportEvent(inactiveTimeInSeconds), new ImplAsyncCallback<String, AvsException>() { // from class: com.ido.alexa.util.UserInactivityTimeTaskUtil.2
            @Override // com.ido.alexa.callbacks.ImplAsyncCallback, com.ido.alexa.callbacks.AsyncCallback
            public void success(String str) {
                super.success(str);
                AlexaLogUtil.printAndSave("Alexa空闲事件上报成功,inactiveTimeInSeconds=" + UserInactivityTimeTaskUtil.inactiveTimeInSeconds);
            }

            @Override // com.ido.alexa.callbacks.ImplAsyncCallback, com.ido.alexa.callbacks.AsyncCallback
            public void failure(AvsException avsException) {
                super.failure(avsException);
                AlexaLogUtil.printAndSave("Alexa空闲事件上报失败：" + avsException + " ,inactiveTimeInSeconds=" + UserInactivityTimeTaskUtil.inactiveTimeInSeconds);
            }
        });
    }

    public static void stop() {
        Timer timer2 = timer;
        if (timer2 != null) {
            timer2.cancel();
            timer = null;
            TimerTask timerTask = task;
            if (timerTask != null) {
                timerTask.cancel();
                task = null;
            }
            AlexaLogUtil.printAndSave("Alexa空闲事件计时结束");
        }
    }
}