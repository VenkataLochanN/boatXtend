package com.ido.life.syncdownload;

import androidx.core.app.NotificationCompat;
import com.google.android.gms.fitness.FitnessActivities;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPath;
import com.ido.common.log.LogPathImpl;
import com.ido.common.net.ApiLogInterceptor;
import com.ido.common.net.SSLSocketFactory;
import com.ido.life.constants.Constants;
import com.ido.life.net.BaseUrlInterceptor;
import com.ido.life.net.RequestInterceptor;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* JADX INFO: compiled from: Task.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u0016\u0018\u0000 -2\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002:\u0003-./B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0011\u0010\u001c\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\u0000H\u0096\u0002J\b\u0010\u001e\u001a\u00020\u001fH\u0016J\u0018\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0016J\u0018\u0010%\u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\"2\u0006\u0010&\u001a\u00020'H\u0016J\u0010\u0010(\u001a\u00020\u001f2\u0006\u0010)\u001a\u00020\u000bH\u0004J\u0010\u0010*\u001a\u00020\u001f2\u0006\u0010)\u001a\u00020\u000bH\u0004J\b\u0010+\u001a\u00020\u001fH\u0016J\b\u0010,\u001a\u00020\u001fH\u0016R\u0016\u0010\n\u001a\n \f*\u0004\u0018\u00010\u000b0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001b¨\u00060"}, d2 = {"Lcom/ido/life/syncdownload/Task;", "Lokhttp3/Callback;", "", "taskInfo", "Lcom/ido/life/syncdownload/Task$TaskInfo;", "listenter", "Lcom/ido/life/syncdownload/Task$Listenter;", "priority", "", "(Lcom/ido/life/syncdownload/Task$TaskInfo;Lcom/ido/life/syncdownload/Task$Listenter;I)V", "TAG", "", "kotlin.jvm.PlatformType", "getListenter", "()Lcom/ido/life/syncdownload/Task$Listenter;", "setListenter", "(Lcom/ido/life/syncdownload/Task$Listenter;)V", "mHasStop", "", "mTaskRetryCount", "getPriority", "()I", "setPriority", "(I)V", "getTaskInfo", "()Lcom/ido/life/syncdownload/Task$TaskInfo;", "setTaskInfo", "(Lcom/ido/life/syncdownload/Task$TaskInfo;)V", "compareTo", FitnessActivities.OTHER, "failed", "", "onFailure", NotificationCompat.CATEGORY_CALL, "Lokhttp3/Call;", "t", "Ljava/io/IOException;", "onResponse", "response", "Lokhttp3/Response;", "printAndSave", "message", "printAndSaveError", "startExecute", "stopExecute", "Companion", "Listenter", "TaskInfo", "app_release"}, k = 1, mv = {1, 1, 16})
public class Task implements Callback, Comparable<Task> {
    private static final int KEEP_ALIVE_SECONDS = 10;
    private static final int MAX_RETRY_COUNT = 3;
    private static OkHttpClient mHttpClient;
    private final String TAG;
    private Listenter listenter;
    private boolean mHasStop;
    private int mTaskRetryCount;
    private int priority;
    private TaskInfo taskInfo;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE = Math.max(2, Math.min(CPU_COUNT - 1, 4));
    private static final int MAXIMUM_POOL_SIZ = (CPU_COUNT * 2) + 1;

    public Task(TaskInfo taskInfo, Listenter listenter, int i) {
        Intrinsics.checkParameterIsNotNull(taskInfo, "taskInfo");
        Intrinsics.checkParameterIsNotNull(listenter, "listenter");
        this.taskInfo = taskInfo;
        this.listenter = listenter;
        this.priority = i;
        this.TAG = Task.class.getSimpleName();
    }

    public /* synthetic */ Task(TaskInfo taskInfo, Listenter listenter, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(taskInfo, listenter, (i2 & 4) != 0 ? 1 : i);
    }

    public final Listenter getListenter() {
        return this.listenter;
    }

    public final int getPriority() {
        return this.priority;
    }

    public final TaskInfo getTaskInfo() {
        return this.taskInfo;
    }

    public final void setListenter(Listenter listenter) {
        Intrinsics.checkParameterIsNotNull(listenter, "<set-?>");
        this.listenter = listenter;
    }

    public final void setPriority(int i) {
        this.priority = i;
    }

    public final void setTaskInfo(TaskInfo taskInfo) {
        Intrinsics.checkParameterIsNotNull(taskInfo, "<set-?>");
        this.taskInfo = taskInfo;
    }

    /* JADX INFO: compiled from: Task.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0018\u0010\t\u001a\u00020\n8\u0002@\u0002X\u0083\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u000b\u0010\u0002¨\u0006\f"}, d2 = {"Lcom/ido/life/syncdownload/Task$Companion;", "", "()V", "CORE_POOL_SIZE", "", "CPU_COUNT", "KEEP_ALIVE_SECONDS", "MAXIMUM_POOL_SIZ", "MAX_RETRY_COUNT", "mHttpClient", "Lokhttp3/OkHttpClient;", "mHttpClient$annotations", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        @JvmStatic
        private static /* synthetic */ void mHttpClient$annotations() {
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    static {
        OkHttpClient okHttpClientBuild = new OkHttpClient.Builder().addInterceptor(new BaseUrlInterceptor()).addInterceptor(new RequestInterceptor(Constants.APP_KEY, "")).addNetworkInterceptor(new ApiLogInterceptor()).connectTimeout(5L, TimeUnit.MINUTES).callTimeout(5L, TimeUnit.MINUTES).readTimeout(5L, TimeUnit.MINUTES).writeTimeout(5L, TimeUnit.MINUTES).retryOnConnectionFailure(true).sslSocketFactory(SSLSocketFactory.getUnCheckSSLSocketFactory()).dispatcher(new Dispatcher(new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZ, 10, TimeUnit.SECONDS, new LinkedBlockingDeque()))).build();
        Intrinsics.checkExpressionValueIsNotNull(okHttpClientBuild, "OkHttpClient.Builder()\n …   )\n            .build()");
        mHttpClient = okHttpClientBuild;
    }

    @Override // okhttp3.Callback
    public void onResponse(Call call, Response response) {
        Intrinsics.checkParameterIsNotNull(call, "call");
        Intrinsics.checkParameterIsNotNull(response, "response");
        Thread threadCurrentThread = Thread.currentThread();
        Intrinsics.checkExpressionValueIsNotNull(threadCurrentThread, "Thread.currentThread()");
        String name = threadCurrentThread.getName();
        Intrinsics.checkExpressionValueIsNotNull(name, "Thread.currentThread().name");
        printAndSave(name);
        if (this.mHasStop) {
            printAndSave("任务已经取消");
            return;
        }
        if (!response.isSuccessful()) {
            failed();
            return;
        }
        ResponseBody responseBodyBody = response.body();
        this.listenter.onSingleTaskSuccess(this.taskInfo, responseBodyBody != null ? responseBodyBody.string() : null);
        Listenter listenter = this.listenter;
        if (listenter instanceof BaseTaskListener) {
            if (listenter != null) {
                BaseTaskListener baseTaskListener = (BaseTaskListener) listenter;
                baseTaskListener.increaseTaskProgress();
                if (baseTaskListener.taskHasComplete()) {
                    baseTaskListener.onAllTaskComplete();
                    return;
                }
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.ido.life.syncdownload.BaseTaskListener");
        }
    }

    @Override // okhttp3.Callback
    public void onFailure(Call call, IOException t) {
        Intrinsics.checkParameterIsNotNull(call, "call");
        Intrinsics.checkParameterIsNotNull(t, "t");
        Thread threadCurrentThread = Thread.currentThread();
        Intrinsics.checkExpressionValueIsNotNull(threadCurrentThread, "Thread.currentThread()");
        String name = threadCurrentThread.getName();
        Intrinsics.checkExpressionValueIsNotNull(name, "Thread.currentThread().name");
        printAndSave(name);
        String localizedMessage = t.getLocalizedMessage();
        Intrinsics.checkExpressionValueIsNotNull(localizedMessage, "t?.localizedMessage");
        printAndSave(localizedMessage);
        failed();
    }

    public void failed() {
        if (this.mHasStop) {
            printAndSaveError("任务已经取消");
            return;
        }
        this.mTaskRetryCount++;
        if (this.mTaskRetryCount > 3) {
            printAndSaveError("任务执行失败,失败后重试次数:" + this.mTaskRetryCount);
            this.listenter.onSingleTaskFailed(this.taskInfo);
            Listenter listenter = this.listenter;
            if (listenter instanceof BaseTaskListener) {
                if (listenter != null) {
                    BaseTaskListener baseTaskListener = (BaseTaskListener) listenter;
                    baseTaskListener.increaseTaskProgress();
                    if (baseTaskListener.taskHasComplete()) {
                        this.listenter.onAllTaskComplete();
                        return;
                    }
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type com.ido.life.syncdownload.BaseTaskListener");
            }
            return;
        }
        printAndSaveError("任务执行失败,重新执行.失败后重试次数:" + this.mTaskRetryCount);
        startExecute();
    }

    public void startExecute() {
        if (this.mHasStop || this.taskInfo.getRequest() == null) {
            return;
        }
        printAndSave("开始执行任务");
        mHttpClient.newCall(this.taskInfo.getRequest()).enqueue(this);
    }

    public void stopExecute() {
        if (this.mHasStop) {
            return;
        }
        this.mHasStop = true;
        printAndSave("停止执行任务mHasStop=" + this.mHasStop);
        mHttpClient.dispatcher().cancelAll();
    }

    /* JADX INFO: compiled from: Task.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\u000f\u001a\u00020\u0010H\u0016R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u0004¨\u0006\u0011"}, d2 = {"Lcom/ido/life/syncdownload/Task$TaskInfo;", "", "taskId", "", "(I)V", "request", "Lokhttp3/Request;", "(ILokhttp3/Request;)V", "getRequest", "()Lokhttp3/Request;", "setRequest", "(Lokhttp3/Request;)V", "getTaskId", "()I", "setTaskId", "toString", "", "app_release"}, k = 1, mv = {1, 1, 16})
    public static class TaskInfo {
        private Request request;
        private int taskId;

        public TaskInfo(int i, Request request) {
            this.taskId = i;
            this.request = request;
        }

        public final Request getRequest() {
            return this.request;
        }

        public final int getTaskId() {
            return this.taskId;
        }

        public final void setRequest(Request request) {
            this.request = request;
        }

        public final void setTaskId(int i) {
            this.taskId = i;
        }

        public TaskInfo(int i) {
            this(i, null);
            this.taskId = i;
        }

        public String toString() {
            return "taskId=" + this.taskId + ",task=" + this.request;
        }
    }

    /* JADX INFO: compiled from: Task.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\b&\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\b\u001a\u00020\tH&J\u0012\u0010\n\u001a\u00020\t2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH&J\u001c\u0010\r\u001a\u00020\u000e2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H&R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\u0011"}, d2 = {"Lcom/ido/life/syncdownload/Task$Listenter;", "", "userId", "", "(J)V", "getUserId", "()J", "setUserId", "onAllTaskComplete", "", "onSingleTaskFailed", "taskInfo", "Lcom/ido/life/syncdownload/Task$TaskInfo;", "onSingleTaskSuccess", "", "response", "", "app_release"}, k = 1, mv = {1, 1, 16})
    public static abstract class Listenter {
        private long userId;

        public abstract void onAllTaskComplete();

        public abstract void onSingleTaskFailed(TaskInfo taskInfo);

        public abstract boolean onSingleTaskSuccess(TaskInfo taskInfo, String response);

        public Listenter(long j) {
            this.userId = j;
        }

        public final long getUserId() {
            return this.userId;
        }

        public final void setUserId(long j) {
            this.userId = j;
        }
    }

    protected final void printAndSave(String message) {
        Intrinsics.checkParameterIsNotNull(message, "message");
        if (message.length() == 0) {
            return;
        }
        CommonLogUtil.d(this.TAG, message);
        LogPath logPathImpl = LogPathImpl.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
        CommonLogUtil.printAndSave(logPathImpl.getServerLogPath(), message);
    }

    protected final void printAndSaveError(String message) {
        Intrinsics.checkParameterIsNotNull(message, "message");
        if (message.length() == 0) {
            return;
        }
        CommonLogUtil.e(this.TAG, message);
        LogPath logPathImpl = LogPathImpl.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
        CommonLogUtil.printAndSave(logPathImpl.getServerLogPath(), message);
    }

    @Override // java.lang.Comparable
    public int compareTo(Task other) {
        Intrinsics.checkParameterIsNotNull(other, "other");
        TaskInfo taskInfo = this.taskInfo;
        if (taskInfo == null) {
            return 1;
        }
        if (other.taskInfo == null) {
            return -1;
        }
        if (taskInfo.getTaskId() == other.taskInfo.getTaskId()) {
            return this.priority > other.priority ? -1 : 1;
        }
        if (this.taskInfo.getTaskId() > other.taskInfo.getTaskId()) {
            return 1;
        }
        return this.taskInfo.getTaskId() < other.taskInfo.getTaskId() ? -1 : 0;
    }
}