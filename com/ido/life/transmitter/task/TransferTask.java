package com.ido.life.transmitter.task;

import com.google.android.gms.fitness.FitnessActivities;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPath;
import com.ido.common.log.LogPathImpl;
import com.ido.life.transmitter.ModuleType;
import com.ido.life.transmitter.callback.OnFileTransferCallback;
import com.ido.life.util.DateUtil;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TransferTask.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b&\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B+\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0003\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0011\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u001f\u001a\u00020\u0000H\u0096\u0002J\u0013\u0010 \u001a\u00020!2\b\u0010\u001f\u001a\u0004\u0018\u00010\"H\u0096\u0002J\b\u0010#\u001a\u00020\u0003H\u0016J\b\u0010$\u001a\u00020%H$J\b\u0010&\u001a\u00020%H$J\u0016\u0010'\u001a\u00020%2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020%0(H\u0005J\u0010\u0010)\u001a\u00020%2\u0006\u0010*\u001a\u00020\nH\u0004J\u000e\u0010+\u001a\u00020%2\u0006\u0010,\u001a\u00020\rJ\u0006\u0010-\u001a\u00020%J\b\u0010.\u001a\u00020%H\u0007J\u0006\u0010/\u001a\u00020%J\b\u00100\u001a\u00020\nH\u0016R\u0016\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0013\"\u0004\b\u0017\u0010\u0015R\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0013\"\u0004\b\u0019\u0010\u0015R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001d¨\u00061"}, d2 = {"Lcom/ido/life/transmitter/task/TransferTask;", "", "moduleType", "", "dataType", "priority", "timeout", "", "(IIIJ)V", "TAG", "", "kotlin.jvm.PlatformType", "callback", "Lcom/ido/life/transmitter/callback/OnFileTransferCallback;", "getCallback", "()Lcom/ido/life/transmitter/callback/OnFileTransferCallback;", "setCallback", "(Lcom/ido/life/transmitter/callback/OnFileTransferCallback;)V", "getDataType", "()I", "setDataType", "(I)V", "getModuleType", "setModuleType", "getPriority", "setPriority", "getTimeout", "()J", "setTimeout", "(J)V", "compareTo", FitnessActivities.OTHER, "equals", "", "", "hashCode", "onTransferStart", "", "onTransferStop", "onTransferStopAsync", "Lkotlin/Function0;", "printAndSave", "msg", "startTransfer", "onFileTransferCallback", "stopTransfer", "stopTransferAsync", "stopTransferSilently", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
public abstract class TransferTask implements Comparable<TransferTask> {
    private final String TAG;
    private OnFileTransferCallback callback;

    /* JADX INFO: renamed from: dataType, reason: from kotlin metadata and from toString */
    private int childType;
    private int moduleType;
    private int priority;
    private long timeout;

    protected abstract void onTransferStart();

    protected abstract void onTransferStop();

    protected final void onTransferStopAsync(Function0<Unit> callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
    }

    public TransferTask(@ModuleType int i, int i2, int i3, long j) {
        this.moduleType = i;
        this.childType = i2;
        this.priority = i3;
        this.timeout = j;
        this.TAG = getClass().getSimpleName();
    }

    public final int getModuleType() {
        return this.moduleType;
    }

    public final void setModuleType(int i) {
        this.moduleType = i;
    }

    /* JADX INFO: renamed from: getDataType, reason: from getter */
    public final int getChildType() {
        return this.childType;
    }

    public final void setDataType(int i) {
        this.childType = i;
    }

    public final int getPriority() {
        return this.priority;
    }

    public final void setPriority(int i) {
        this.priority = i;
    }

    public /* synthetic */ TransferTask(int i, int i2, int i3, long j, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, i2, (i4 & 4) != 0 ? 0 : i3, (i4 & 8) != 0 ? DateUtil.MINUTE : j);
    }

    public final long getTimeout() {
        return this.timeout;
    }

    public final void setTimeout(long j) {
        this.timeout = j;
    }

    protected final OnFileTransferCallback getCallback() {
        return this.callback;
    }

    protected final void setCallback(OnFileTransferCallback onFileTransferCallback) {
        this.callback = onFileTransferCallback;
    }

    public final void startTransfer(OnFileTransferCallback onFileTransferCallback) {
        Intrinsics.checkParameterIsNotNull(onFileTransferCallback, "onFileTransferCallback");
        this.callback = onFileTransferCallback;
        printAndSave("startTransfer");
        onTransferStart();
    }

    public final void stopTransfer() {
        printAndSave("stopTransfer");
        onTransferStop();
        printAndSave("onTransferStop");
        OnFileTransferCallback onFileTransferCallback = this.callback;
        if (onFileTransferCallback != null) {
            onFileTransferCallback.onCancel(this);
        }
    }

    public final void stopTransferSilently() {
        printAndSave("stopTransferSilently");
        onTransferStop();
        printAndSave("onTransferStopSilently");
    }

    public final void stopTransferAsync() {
        printAndSave("stopTransferAsync");
        onTransferStopAsync(new Function0<Unit>() { // from class: com.ido.life.transmitter.task.TransferTask.stopTransferAsync.1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                TransferTask.this.printAndSave("onTransferStopAsync");
                OnFileTransferCallback callback = TransferTask.this.getCallback();
                if (callback != null) {
                    callback.onCancel(TransferTask.this);
                }
            }
        });
    }

    protected final void printAndSave(String msg) {
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        LogPath logPathImpl = LogPathImpl.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
        CommonLogUtil.printAndSave(logPathImpl.getLogPath(), this.TAG, (char) 12304 + this.moduleType + "】【" + this.childType + "】：" + msg);
    }

    @Override // java.lang.Comparable
    public int compareTo(TransferTask other) {
        Intrinsics.checkParameterIsNotNull(other, "other");
        return other.priority - this.priority;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(getClass(), other != null ? other.getClass() : null)) {
            return false;
        }
        if (other != null) {
            TransferTask transferTask = (TransferTask) other;
            return this.moduleType == transferTask.moduleType && this.childType == transferTask.childType;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.ido.life.transmitter.task.TransferTask");
    }

    public int hashCode() {
        return (this.moduleType * 31) + Integer.valueOf(this.childType).hashCode();
    }

    public String toString() {
        return "TransferTask(moduleType=" + this.moduleType + ", childType=" + this.childType + ", priority=" + this.priority + ')';
    }
}