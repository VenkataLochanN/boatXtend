package com.ido.life.data.cache;

import android.text.TextUtils;
import com.ido.ble.BLEManager;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPath;
import com.ido.common.log.LogPathImpl;
import com.ido.life.base.BaseMessage;
import com.ido.life.ble.BaseConnCallback;
import com.ido.life.util.eventbus.EventBusHelper;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: compiled from: AbsDataCacheManager.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\b&\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u0005H\u0014J\u0010\u0010#\u001a\u00020!2\u0006\u0010\"\u001a\u00020\u0005H\u0014J\u0012\u0010$\u001a\u00020!2\b\u0010%\u001a\u0004\u0018\u00010\u0005H\u0016J\u0016\u0010&\u001a\u00020!2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00050(H\u0007J\b\u0010)\u001a\u00020!H\u0016J\b\u0010*\u001a\u00020!H\u0016J\u0012\u0010+\u001a\u00020!2\b\u0010%\u001a\u0004\u0018\u00010\u0005H\u0016J\u0010\u0010,\u001a\u00020!2\u0006\u0010%\u001a\u00020\u0005H\u0016J\u0016\u0010-\u001a\u00020!2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00050(H\u0007R\"\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\fX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u000e\"\u0004\b\u0019\u0010\u0010R\u001c\u0010\u001a\u001a\u0004\u0018\u00010\u0005X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\b\"\u0004\b\u001c\u0010\nR\u001a\u0010\u001d\u001a\u00020\u0005X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\b\"\u0004\b\u001f\u0010\n¨\u0006."}, d2 = {"Lcom/ido/life/data/cache/AbsDataCacheManager;", "T", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "getTAG", "()Ljava/lang/String;", "setTAG", "(Ljava/lang/String;)V", "mBind", "Ljava/util/concurrent/atomic/AtomicBoolean;", "getMBind", "()Ljava/util/concurrent/atomic/AtomicBoolean;", "setMBind", "(Ljava/util/concurrent/atomic/AtomicBoolean;)V", "mConnCallback", "Lcom/ido/life/ble/BaseConnCallback;", "getMConnCallback", "()Lcom/ido/life/ble/BaseConnCallback;", "setMConnCallback", "(Lcom/ido/life/ble/BaseConnCallback;)V", "mConnected", "getMConnected", "setMConnected", "mCurrentMacAddress", "getMCurrentMacAddress", "setMCurrentMacAddress", "mLogPath", "getMLogPath", "setMLogPath", "logD", "", "msg", "logP", "onBind", "macAddress", "onBindEvent", "event", "Lcom/ido/life/base/BaseMessage;", "onClear", "onConnectBreak", "onSdkInitComplete", "onUnBind", "onUnBindEvent", "app_release"}, k = 1, mv = {1, 1, 16})
public abstract class AbsDataCacheManager<T> {
    private String TAG = getClass().getSimpleName();
    private AtomicBoolean mBind;
    private BaseConnCallback mConnCallback;
    private AtomicBoolean mConnected;
    private String mCurrentMacAddress;
    private String mLogPath;

    public void onBind(String macAddress) {
    }

    public void onClear() {
    }

    public void onConnectBreak() {
    }

    public void onSdkInitComplete(String macAddress) {
    }

    public void onUnBind(String macAddress) {
        Intrinsics.checkParameterIsNotNull(macAddress, "macAddress");
    }

    public AbsDataCacheManager() {
        LogPath logPathImpl = LogPathImpl.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
        String logPath = logPathImpl.getLogPath();
        Intrinsics.checkExpressionValueIsNotNull(logPath, "LogPathImpl.getInstance().logPath");
        this.mLogPath = logPath;
        this.mConnected = new AtomicBoolean(false);
        this.mBind = new AtomicBoolean(false);
        this.mConnCallback = new BaseConnCallback() { // from class: com.ido.life.data.cache.AbsDataCacheManager$mConnCallback$1
            @Override // com.ido.life.ble.BaseConnCallback, com.ido.ble.callback.ConnectCallBack.ICallBack
            public void onConnectSuccess(String macAddress) {
                this.this$0.logP("onConnectSuccess isBind = " + BLEManager.isBind());
            }

            @Override // com.ido.life.ble.BaseConnCallback, com.ido.ble.callback.ConnectCallBack.ICallBack
            public void onConnectBreak(String macAddress) {
                super.onConnectBreak(macAddress);
                this.this$0.logP("onConnectBreak：" + macAddress);
                this.this$0.getMConnected().set(false);
                this.this$0.onClear();
                this.this$0.onConnectBreak();
            }

            @Override // com.ido.life.ble.BaseConnCallback, com.ido.ble.callback.ConnectCallBack.ICallBack
            public void onInitCompleted(String macAddress) {
                super.onInitCompleted(macAddress);
                this.this$0.logP("onInitCompleted isBind = " + BLEManager.isBind());
                this.this$0.setMCurrentMacAddress(macAddress);
                if (!BLEManager.isBind()) {
                    this.this$0.logP("onInitCompleted not bind return");
                    return;
                }
                if (this.this$0.getMConnected().get()) {
                    this.this$0.logP("onInitCompleted callback repeated!!!");
                    return;
                }
                this.this$0.getMConnected().set(true);
                this.this$0.logP("onInitCompleted：" + macAddress);
                this.this$0.onSdkInitComplete(macAddress);
            }
        };
        BLEManager.registerConnectCallBack(this.mConnCallback);
        EventBusHelper.register(this);
    }

    protected final String getTAG() {
        return this.TAG;
    }

    protected final void setTAG(String str) {
        this.TAG = str;
    }

    protected final String getMLogPath() {
        return this.mLogPath;
    }

    protected final void setMLogPath(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.mLogPath = str;
    }

    protected final String getMCurrentMacAddress() {
        return this.mCurrentMacAddress;
    }

    protected final void setMCurrentMacAddress(String str) {
        this.mCurrentMacAddress = str;
    }

    protected final AtomicBoolean getMConnected() {
        return this.mConnected;
    }

    protected final void setMConnected(AtomicBoolean atomicBoolean) {
        Intrinsics.checkParameterIsNotNull(atomicBoolean, "<set-?>");
        this.mConnected = atomicBoolean;
    }

    protected final AtomicBoolean getMBind() {
        return this.mBind;
    }

    protected final void setMBind(AtomicBoolean atomicBoolean) {
        Intrinsics.checkParameterIsNotNull(atomicBoolean, "<set-?>");
        this.mBind = atomicBoolean;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void logP(String msg) {
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        String str = this.mLogPath;
        String str2 = this.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append((char) 12304);
        Thread threadCurrentThread = Thread.currentThread();
        Intrinsics.checkExpressionValueIsNotNull(threadCurrentThread, "Thread.currentThread()");
        sb.append(threadCurrentThread.getName());
        sb.append((char) 12305);
        sb.append(msg);
        CommonLogUtil.printAndSave(str, str2, sb.toString());
    }

    protected void logD(String msg) {
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        CommonLogUtil.d(this.TAG, msg);
    }

    public final BaseConnCallback getMConnCallback() {
        return this.mConnCallback;
    }

    public final void setMConnCallback(BaseConnCallback baseConnCallback) {
        Intrinsics.checkParameterIsNotNull(baseConnCallback, "<set-?>");
        this.mConnCallback = baseConnCallback;
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public final void onUnBindEvent(BaseMessage<String> event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        if (event.getType() != 829 || TextUtils.isEmpty(event.getData())) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("onUnBindEvent：");
        sb.append(event.getData());
        sb.append(", length = ");
        String data = event.getData();
        sb.append(data != null ? Integer.valueOf(data.length()) : null);
        logP(sb.toString());
        this.mBind.set(false);
        String data2 = event.getData();
        Intrinsics.checkExpressionValueIsNotNull(data2, "event.data");
        onUnBind(data2);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public final void onBindEvent(BaseMessage<String> event) {
        BaseConnCallback baseConnCallback;
        Intrinsics.checkParameterIsNotNull(event, "event");
        if (event.getType() == 103 && BLEManager.isConnected()) {
            if (this.mBind.get()) {
                logP("onBindEvent callback repeated!!!");
                return;
            }
            this.mBind.set(true);
            StringBuilder sb = new StringBuilder();
            sb.append("onBindEvent：");
            sb.append(event.getData());
            sb.append(", length = ");
            String data = event.getData();
            sb.append(data != null ? Integer.valueOf(data.length()) : null);
            logP(sb.toString());
            onBind(event.getData());
            if (this.mConnected.get() || (baseConnCallback = this.mConnCallback) == null) {
                return;
            }
            baseConnCallback.onInitCompleted(this.mCurrentMacAddress);
        }
    }
}