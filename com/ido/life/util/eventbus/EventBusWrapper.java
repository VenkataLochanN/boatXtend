package com.ido.life.util.eventbus;

import com.ido.life.base.BaseMessage;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes3.dex */
public class EventBusWrapper {
    public IHandlerEventBus iHandlerEventBus;

    public void register(IHandlerEventBus iHandlerEventBus) {
        this.iHandlerEventBus = iHandlerEventBus;
        EventBusHelper.register(this);
    }

    public void unregister() {
        this.iHandlerEventBus = null;
        EventBusHelper.unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleMessageEvent(BaseMessage baseMessage) {
        IHandlerEventBus iHandlerEventBus = this.iHandlerEventBus;
        if (iHandlerEventBus != null) {
            iHandlerEventBus.handleMessage(baseMessage);
        }
    }
}