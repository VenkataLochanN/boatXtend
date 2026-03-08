package com.ido.life.util.eventbus;

import com.ido.life.base.BaseMessage;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes3.dex */
public class EventBusHelper {
    public static void register(Object obj) {
        unregister(obj);
        EventBus.getDefault().register(obj);
    }

    public static void unregister(Object obj) {
        EventBus.getDefault().unregister(obj);
    }

    public static void post(int i) {
        post(new BaseMessage(i));
    }

    public static void post(BaseMessage baseMessage) {
        EventBus.getDefault().post(baseMessage);
    }

    public static void postSticky(int i) {
        postSticky(new BaseMessage(i));
    }

    public static void postSticky(BaseMessage baseMessage) {
        EventBus.getDefault().postSticky(baseMessage);
    }
}