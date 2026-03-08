package com.ido.life.syncdownload;

import android.text.TextUtils;
import com.google.gson.JsonElement;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPath;
import com.ido.common.log.LogPathImpl;
import com.ido.life.syncdownload.Task;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: BaseTaskListener.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u001a\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\b&\u0018\u00002\u00020\u00012\u00020\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0006\u0010\u001d\u001a\u00020\u001eJ\b\u0010\u001f\u001a\u00020\u000eH\u0016J\u0012\u0010 \u001a\u00020\u000e2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0004J\u0010\u0010#\u001a\u00020\u000e2\u0006\u0010$\u001a\u00020\tH\u0004J\b\u0010%\u001a\u00020\u001eH\u0016J\u0012\u0010&\u001a\u00020\u001e2\b\u0010'\u001a\u0004\u0018\u00010(H\u0016J\u0010\u0010)\u001a\u00020\u001e2\u0006\u0010*\u001a\u00020\tH\u0004J\u0018\u0010)\u001a\u00020\u001e2\u0006\u0010+\u001a\u00020\t2\u0006\u0010*\u001a\u00020\tH\u0004J\u0006\u0010,\u001a\u00020\u000eR\u001c\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\tX\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u0014X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001c¨\u0006-"}, d2 = {"Lcom/ido/life/syncdownload/BaseTaskListener;", "Lcom/ido/life/syncdownload/Task$Listenter;", "", "userId", "", "taskCount", "", "(JI)V", "TAG", "", "kotlin.jvm.PlatformType", "getTAG", "()Ljava/lang/String;", "mHasStop", "", "getMHasStop", "()Z", "setMHasStop", "(Z)V", "mTaskProgress", "Ljava/util/concurrent/atomic/AtomicInteger;", "getMTaskProgress", "()Ljava/util/concurrent/atomic/AtomicInteger;", "setMTaskProgress", "(Ljava/util/concurrent/atomic/AtomicInteger;)V", "getTaskCount", "()I", "setTaskCount", "(I)V", "increaseTaskProgress", "", "interrupted", "isNullJsonElement", "jsonElement", "Lcom/google/gson/JsonElement;", "isNullString", "value", "onAllTaskComplete", "onSingleTaskFailed", "taskInfo", "Lcom/ido/life/syncdownload/Task$TaskInfo;", "printAndSaveLog", "message", "tag", "taskHasComplete", "app_release"}, k = 1, mv = {1, 1, 16})
public abstract class BaseTaskListener extends Task.Listenter implements Cloneable {
    private final String TAG;
    private boolean mHasStop;
    private AtomicInteger mTaskProgress;
    private int taskCount;

    public boolean interrupted() {
        return true;
    }

    @Override // com.ido.life.syncdownload.Task.Listenter
    public void onAllTaskComplete() {
    }

    @Override // com.ido.life.syncdownload.Task.Listenter
    public void onSingleTaskFailed(Task.TaskInfo taskInfo) {
    }

    public BaseTaskListener(long j, int i) {
        super(j);
        this.taskCount = i;
        this.TAG = BaseTaskListener.class.getSimpleName();
        this.mTaskProgress = new AtomicInteger(0);
    }

    public Object clone() {
        return super.clone();
    }

    public final int getTaskCount() {
        return this.taskCount;
    }

    public final void setTaskCount(int i) {
        this.taskCount = i;
    }

    protected String getTAG() {
        return this.TAG;
    }

    protected final AtomicInteger getMTaskProgress() {
        return this.mTaskProgress;
    }

    protected final void setMTaskProgress(AtomicInteger atomicInteger) {
        Intrinsics.checkParameterIsNotNull(atomicInteger, "<set-?>");
        this.mTaskProgress = atomicInteger;
    }

    public final boolean getMHasStop() {
        return this.mHasStop;
    }

    public final void setMHasStop(boolean z) {
        this.mHasStop = z;
    }

    protected final void printAndSaveLog(String message) {
        Intrinsics.checkParameterIsNotNull(message, "message");
        if (message.length() == 0) {
            return;
        }
        LogPath logPathImpl = LogPathImpl.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
        CommonLogUtil.printAndSave(logPathImpl.getServerLogPath(), getTAG(), message);
    }

    protected final void printAndSaveLog(String tag, String message) {
        Intrinsics.checkParameterIsNotNull(tag, "tag");
        Intrinsics.checkParameterIsNotNull(message, "message");
        if (message.length() == 0) {
            return;
        }
        LogPath logPathImpl = LogPathImpl.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
        CommonLogUtil.printAndSave(logPathImpl.getServerLogPath(), tag, message);
    }

    protected final boolean isNullJsonElement(JsonElement jsonElement) {
        return jsonElement == null || jsonElement.isJsonNull();
    }

    protected final boolean isNullString(String value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        if (!StringsKt.isBlank(value)) {
            String lowerCase = value.toLowerCase();
            Intrinsics.checkNotNullExpressionValue(lowerCase, "(this as java.lang.String).toLowerCase()");
            if (!TextUtils.equals(lowerCase, "null")) {
                return false;
            }
        }
        return true;
    }

    public final void increaseTaskProgress() {
        this.mTaskProgress.incrementAndGet();
    }

    public final boolean taskHasComplete() {
        return this.mTaskProgress.get() >= this.taskCount;
    }
}