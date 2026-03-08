package com.ido.life.data.cache;

import com.ido.ble.event.stat.one.d;
import com.ido.common.log.CommonLogUtil;
import com.ido.life.base.BaseMessage;
import com.ido.life.transmitter.FileTransmitter;
import com.ido.life.util.WallpaperDialManager;
import com.ido.life.util.eventbus.EventBusHelper;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: compiled from: DataManagerService.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \b2\u00020\u0001:\u0002\b\tB\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u0016\u0010\u0005\u001a\u00020\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u0007H\u0007¨\u0006\n"}, d2 = {"Lcom/ido/life/data/cache/DataManagerService;", "", "()V", d.m, "", "onSyncEvent", "event", "Lcom/ido/life/base/BaseMessage;", "Companion", "SingleInstanceHolder", "app_release"}, k = 1, mv = {1, 1, 16})
public final class DataManagerService {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final DataManagerService instance = SingleInstanceHolder.INSTANCE.getINSTANCE();

    /* JADX INFO: compiled from: DataManagerService.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/ido/life/data/cache/DataManagerService$Companion;", "", "()V", "instance", "Lcom/ido/life/data/cache/DataManagerService;", "getInstance", "()Lcom/ido/life/data/cache/DataManagerService;", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final DataManagerService getInstance() {
            return DataManagerService.instance;
        }
    }

    /* JADX INFO: compiled from: DataManagerService.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/ido/life/data/cache/DataManagerService$SingleInstanceHolder;", "", "()V", "INSTANCE", "Lcom/ido/life/data/cache/DataManagerService;", "getINSTANCE", "()Lcom/ido/life/data/cache/DataManagerService;", "app_release"}, k = 1, mv = {1, 1, 16})
    private static final class SingleInstanceHolder {
        public static final SingleInstanceHolder INSTANCE = new SingleInstanceHolder();
        private static final DataManagerService INSTANCE = new DataManagerService();

        private SingleInstanceHolder() {
        }

        public final DataManagerService getINSTANCE() {
            return INSTANCE;
        }
    }

    public final void init() {
        CommonLogUtil.printAndSave("初始化数据管理器，app版本：2.2.1 18");
        EventBusHelper.register(this);
        AppNameLanguageManager.INSTANCE.getInstance();
        MotionTypeManager.INSTANCE.getInstance();
        RemindDataManager.INSTANCE.getInstance();
        ShortcutAppManager.INSTANCE.getInstance();
        WorldTimeCityManager.INSTANCE.getInstance();
        WallpaperDialManager.INSTANCE.init();
        HealthMonitoringManager.INSTANCE.getInstance();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onSyncEvent(BaseMessage<Object> event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        if (event.getType() == 904 || event.getType() == 816) {
            FileTransmitter.INSTANCE.getInstance().printAndSave(event.getType() == 904 ? "开始同步设备数据，暂停文件传输..." : "开始发送Alexa语言，暂停文件传输...");
            FileTransmitter.INSTANCE.getInstance().pause();
        } else if (event.getType() == 905 || event.getType() == 817) {
            FileTransmitter.INSTANCE.getInstance().printAndSave(event.getType() == 905 ? "同步设备数据结束，恢复文件传输..." : "发送Alexa语音结束，恢复文件传输...");
            FileTransmitter.INSTANCE.getInstance().resume();
        }
    }
}