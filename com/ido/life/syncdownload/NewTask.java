package com.ido.life.syncdownload;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.android.gms.fitness.FitnessActivities;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.net.SSLSocketFactory;
import com.ido.common.utils.NetworkUtil;
import com.ido.life.constants.Constants;
import com.ido.life.data.AuthorizationPreference;
import com.ido.life.syncdownload.Task;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import javax.net.ssl.HttpsURLConnection;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import kotlin.text.Typography;

/* JADX INFO: compiled from: NewTask.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0002\b\u0013\b\u0016\u0018\u0000 82\u00020\u00012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00000\u0002:\u0004789:B'\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u000e\u0010'\u001a\u00020(2\u0006\u0010\u0005\u001a\u00020\u0019J\u0013\u0010)\u001a\u00020\b2\b\u0010*\u001a\u0004\u0018\u00010\u0000H\u0096\u0002J\u0006\u0010+\u001a\u00020\bJ\u0010\u0010,\u001a\u00020(2\b\u0010-\u001a\u0004\u0018\u00010\u000bJ\u000e\u0010.\u001a\u00020(2\u0006\u0010/\u001a\u00020\u000bJ\u000e\u00100\u001a\u00020(2\u0006\u00101\u001a\u00020\u000bJ\u0010\u00102\u001a\u00020(2\u0006\u00101\u001a\u00020\u000bH\u0004J\u000e\u00103\u001a\u00020(2\u0006\u0010\u0005\u001a\u00020\u0019J\b\u00104\u001a\u00020(H\u0016J\u0006\u00105\u001a\u00020(J\b\u00106\u001a\u00020\u000bH\u0016R\u0016\u0010\n\u001a\n \f*\u0004\u0018\u00010\u000b0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0012X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R \u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u000e\u0010\u001e\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&¨\u0006;"}, d2 = {"Lcom/ido/life/syncdownload/NewTask;", "Ljava/lang/Runnable;", "", "taskInfo", "Lcom/ido/life/syncdownload/NewTask$NewTaskInfo;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/ido/life/syncdownload/Task$Listenter;", "taskPriority", "", "(Lcom/ido/life/syncdownload/NewTask$NewTaskInfo;Lcom/ido/life/syncdownload/Task$Listenter;I)V", "TAG", "", "kotlin.jvm.PlatformType", "getListener", "()Lcom/ido/life/syncdownload/Task$Listenter;", "setListener", "(Lcom/ido/life/syncdownload/Task$Listenter;)V", "mHasStop", "", "getMHasStop", "()Z", "setMHasStop", "(Z)V", "mTaskListenerList", "", "Lcom/ido/life/syncdownload/NewTask$TaskListener;", "getMTaskListenerList", "()Ljava/util/List;", "setMTaskListenerList", "(Ljava/util/List;)V", "mTaskRetryCount", "getTaskInfo", "()Lcom/ido/life/syncdownload/NewTask$NewTaskInfo;", "setTaskInfo", "(Lcom/ido/life/syncdownload/NewTask$NewTaskInfo;)V", "getTaskPriority", "()I", "setTaskPriority", "(I)V", "addTaskListener", "", "compareTo", FitnessActivities.OTHER, "getTaskRetryCount", "onFailure", "errorMessage", "onSuccess", "response", "printAndSave", "message", "printAndSaveError", "removeTaskListener", "run", "stopExecute", "toString", "Builder", "Companion", "NewTaskInfo", "TaskListener", "app_release"}, k = 1, mv = {1, 1, 16})
public class NewTask implements Runnable, Comparable<NewTask> {
    private final String TAG;
    private Task.Listenter listener;
    private boolean mHasStop;
    private List<TaskListener> mTaskListenerList;
    private int mTaskRetryCount;
    private NewTaskInfo taskInfo;
    private int taskPriority;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String POST = "POST";
    private static final String GET = "GET";

    /* JADX INFO: compiled from: NewTask.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u000b"}, d2 = {"Lcom/ido/life/syncdownload/NewTask$TaskListener;", "", "onTaskCanceled", "", "task", "Lcom/ido/life/syncdownload/NewTask;", "onTaskFailed", "errorMsg", "", "onTaskStart", "onTaskSuccess", "app_release"}, k = 1, mv = {1, 1, 16})
    public interface TaskListener {
        void onTaskCanceled(NewTask task);

        void onTaskFailed(NewTask task, String errorMsg);

        void onTaskStart(NewTask task);

        void onTaskSuccess(NewTask task);
    }

    public NewTask() {
        this(null, null, 0, 7, null);
    }

    public static final String getGET() {
        Companion companion = INSTANCE;
        return GET;
    }

    public static final String getPOST() {
        Companion companion = INSTANCE;
        return POST;
    }

    public NewTask(NewTaskInfo newTaskInfo, Task.Listenter listenter, int i) {
        this.taskInfo = newTaskInfo;
        this.listener = listenter;
        this.taskPriority = i;
        this.TAG = NewTask.class.getSimpleName();
        this.mTaskListenerList = new ArrayList();
    }

    public /* synthetic */ NewTask(NewTaskInfo newTaskInfo, Task.Listenter listenter, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? (NewTaskInfo) null : newTaskInfo, (i2 & 2) != 0 ? (Task.Listenter) null : listenter, (i2 & 4) != 0 ? 0 : i);
    }

    public final NewTaskInfo getTaskInfo() {
        return this.taskInfo;
    }

    public final void setTaskInfo(NewTaskInfo newTaskInfo) {
        this.taskInfo = newTaskInfo;
    }

    public final Task.Listenter getListener() {
        return this.listener;
    }

    public final void setListener(Task.Listenter listenter) {
        this.listener = listenter;
    }

    public final int getTaskPriority() {
        return this.taskPriority;
    }

    public final void setTaskPriority(int i) {
        this.taskPriority = i;
    }

    protected final boolean getMHasStop() {
        return this.mHasStop;
    }

    protected final void setMHasStop(boolean z) {
        this.mHasStop = z;
    }

    protected final List<TaskListener> getMTaskListenerList() {
        return this.mTaskListenerList;
    }

    protected final void setMTaskListenerList(List<TaskListener> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.mTaskListenerList = list;
    }

    /* JADX INFO: compiled from: NewTask.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u00020\u00048\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007R\u001c\u0010\b\u001a\u00020\u00048\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\t\u0010\u0002\u001a\u0004\b\n\u0010\u0007¨\u0006\u000b"}, d2 = {"Lcom/ido/life/syncdownload/NewTask$Companion;", "", "()V", "GET", "", "GET$annotations", "getGET", "()Ljava/lang/String;", "POST", "POST$annotations", "getPOST", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        @JvmStatic
        public static /* synthetic */ void GET$annotations() {
        }

        @JvmStatic
        public static /* synthetic */ void POST$annotations() {
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String getPOST() {
            return NewTask.POST;
        }

        public final String getGET() {
            return NewTask.GET;
        }
    }

    public final void addTaskListener(TaskListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        if (this.mTaskListenerList.contains(listener)) {
            return;
        }
        this.mTaskListenerList.add(listener);
    }

    public final void removeTaskListener(TaskListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        if (this.mTaskListenerList.contains(listener)) {
            this.mTaskListenerList.remove(listener);
        }
    }

    public final void onSuccess(String response) {
        Task.Listenter listenter;
        Intrinsics.checkParameterIsNotNull(response, "response");
        if (this.mHasStop || (listenter = this.listener) == null || (listenter != null && listenter.getUserId() == -1)) {
            if (!this.mTaskListenerList.isEmpty()) {
                Iterator<T> it = this.mTaskListenerList.iterator();
                while (it.hasNext()) {
                    ((TaskListener) it.next()).onTaskCanceled(this);
                }
            }
            printAndSave("任务已经取消");
            return;
        }
        printAndSave("newTask 任务执行成功 taskInfo=" + this);
        Task.Listenter listenter2 = this.listener;
        if (listenter2 != null) {
            listenter2.onSingleTaskSuccess(this.taskInfo, response);
        }
        Task.Listenter listenter3 = this.listener;
        if (listenter3 instanceof BaseTaskListener) {
            if (listenter3 != null) {
                BaseTaskListener baseTaskListener = (BaseTaskListener) listenter3;
                baseTaskListener.increaseTaskProgress();
                if (baseTaskListener.taskHasComplete()) {
                    baseTaskListener.onAllTaskComplete();
                }
            } else {
                throw new TypeCastException("null cannot be cast to non-null type com.ido.life.syncdownload.BaseTaskListener");
            }
        }
        if (!this.mTaskListenerList.isEmpty()) {
            Iterator<T> it2 = this.mTaskListenerList.iterator();
            while (it2.hasNext()) {
                ((TaskListener) it2.next()).onTaskSuccess(this);
            }
        }
    }

    public final void onFailure(String errorMessage) {
        Builder builder;
        Builder builder2;
        if (this.mHasStop) {
            if (!this.mTaskListenerList.isEmpty()) {
                Iterator<T> it = this.mTaskListenerList.iterator();
                while (it.hasNext()) {
                    ((TaskListener) it.next()).onTaskCanceled(this);
                }
            }
            printAndSaveError("任务已经取消");
            return;
        }
        this.mTaskRetryCount++;
        if (this.taskInfo != null) {
            Integer numValueOf = null;
            if (!NetworkUtil.isConnected(IdoApp.getAppContext())) {
                NewTaskInfo newTaskInfo = this.taskInfo;
                Integer numValueOf2 = (newTaskInfo == null || (builder2 = newTaskInfo.getBuilder()) == null) ? null : Integer.valueOf(builder2.getFailedRetryCount());
                if (numValueOf2 == null) {
                    Intrinsics.throwNpe();
                }
                this.mTaskRetryCount = numValueOf2.intValue() + 1;
            }
            int i = this.mTaskRetryCount;
            NewTaskInfo newTaskInfo2 = this.taskInfo;
            if (newTaskInfo2 != null && (builder = newTaskInfo2.getBuilder()) != null) {
                numValueOf = Integer.valueOf(builder.getFailedRetryCount());
            }
            if (numValueOf == null) {
                Intrinsics.throwNpe();
            }
            if (i > numValueOf.intValue()) {
                StringBuilder sb = new StringBuilder();
                sb.append("任务执行失败,失败后重试次数:");
                sb.append(this.mTaskRetryCount - 1);
                printAndSaveError(sb.toString());
                Task.Listenter listenter = this.listener;
                if (listenter != null) {
                    NewTaskInfo newTaskInfo3 = this.taskInfo;
                    if (newTaskInfo3 == null) {
                        Intrinsics.throwNpe();
                    }
                    listenter.onSingleTaskFailed(newTaskInfo3);
                }
                Task.Listenter listenter2 = this.listener;
                if (listenter2 instanceof BaseTaskListener) {
                    if (listenter2 != null) {
                        BaseTaskListener baseTaskListener = (BaseTaskListener) listenter2;
                        baseTaskListener.increaseTaskProgress();
                        if (baseTaskListener.taskHasComplete()) {
                            baseTaskListener.onAllTaskComplete();
                        }
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type com.ido.life.syncdownload.BaseTaskListener");
                    }
                }
            } else {
                printAndSaveError("任务执行失败,重新执行errorMessage=" + errorMessage + ".失败后重试次数:" + this.mTaskRetryCount);
            }
        }
        if (!this.mTaskListenerList.isEmpty()) {
            Iterator<T> it2 = this.mTaskListenerList.iterator();
            while (it2.hasNext()) {
                ((TaskListener) it2.next()).onTaskFailed(this, "任务执行失败 errorMessage=" + errorMessage);
            }
        }
    }

    /* JADX INFO: renamed from: getTaskRetryCount, reason: from getter */
    public final int getMTaskRetryCount() {
        return this.mTaskRetryCount;
    }

    public final void stopExecute() {
        if (this.mHasStop) {
            return;
        }
        Task.Listenter listenter = this.listener;
        if (listenter instanceof BaseTaskListener) {
            if (listenter != null) {
                if (!((BaseTaskListener) listenter).interrupted()) {
                    return;
                }
                Task.Listenter listenter2 = this.listener;
                if (listenter2 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.ido.life.syncdownload.BaseTaskListener");
                }
                ((BaseTaskListener) listenter2).setMHasStop(true);
            } else {
                throw new TypeCastException("null cannot be cast to non-null type com.ido.life.syncdownload.BaseTaskListener");
            }
        }
        this.mHasStop = true;
        printAndSave("停止执行任务mHasStop=" + this.mHasStop);
    }

    /* JADX INFO: compiled from: NewTask.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\b\u001a\u00020\tH\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\n"}, d2 = {"Lcom/ido/life/syncdownload/NewTask$NewTaskInfo;", "Lcom/ido/life/syncdownload/Task$TaskInfo;", "builder", "Lcom/ido/life/syncdownload/NewTask$Builder;", "(Lcom/ido/life/syncdownload/NewTask$Builder;)V", "getBuilder", "()Lcom/ido/life/syncdownload/NewTask$Builder;", "setBuilder", "toString", "", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class NewTaskInfo extends Task.TaskInfo {
        private Builder builder;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public NewTaskInfo(Builder builder) {
            super(builder.getGroupId());
            Intrinsics.checkParameterIsNotNull(builder, "builder");
            this.builder = builder;
        }

        public final Builder getBuilder() {
            return this.builder;
        }

        public final void setBuilder(Builder builder) {
            Intrinsics.checkParameterIsNotNull(builder, "<set-?>");
            this.builder = builder;
        }

        @Override // com.ido.life.syncdownload.Task.TaskInfo
        public String toString() {
            return "taskId=" + getTaskId() + ",builder=" + this.builder;
        }
    }

    /* JADX INFO: compiled from: NewTask.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\u001a\u00103\u001a\u00020\u00002\u0012\u00104\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u000305J\u001c\u00106\u001a\u00020\u00002\u0014\u00107\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u000108J\u001a\u00109\u001a\u00020\u00002\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u000305J\u001a\u0010:\u001a\u00020\u00002\u0012\u0010#\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u000305J\u001a\u0010;\u001a\u00020\u00002\u0012\u0010;\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u000308J\u001c\u0010<\u001a\u00020\u00002\u0014\u0010<\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u000108J\u0006\u0010=\u001a\u00020\u0000J\u000e\u0010 \u001a\u00020\u00002\u0006\u0010 \u001a\u00020\u0013J\u001c\u0010>\u001a\u00020\u00002\u0014\u0010>\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u000108J\u0006\u0010?\u001a\u00020\u0000J\u000e\u0010,\u001a\u00020\u00002\u0006\u0010,\u001a\u00020\rJ\b\u0010@\u001a\u00020\u0003H\u0016R&\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001a\u0010\u001d\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0015\"\u0004\b\u001f\u0010\u0017R\u001a\u0010 \u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0015\"\u0004\b\"\u0010\u0017R&\u0010#\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\t\"\u0004\b%\u0010\u000bR\u001c\u0010&\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\u001a\"\u0004\b(\u0010\u001cR&\u0010)\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\t\"\u0004\b+\u0010\u000bR\u001a\u0010,\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010\u000f\"\u0004\b.\u0010\u0011R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010\u001a\"\u0004\b0\u0010\u001cR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010\u001a\"\u0004\b2\u0010\u001c¨\u0006A"}, d2 = {"Lcom/ido/life/syncdownload/NewTask$Builder;", "", "url", "", "taskTag", "(Ljava/lang/String;Ljava/lang/String;)V", "body", "", "getBody", "()Ljava/util/Map;", "setBody", "(Ljava/util/Map;)V", "contentLength", "", "getContentLength", "()J", "setContentLength", "(J)V", "dataTotalCount", "", "getDataTotalCount", "()I", "setDataTotalCount", "(I)V", "errorMsg", "getErrorMsg", "()Ljava/lang/String;", "setErrorMsg", "(Ljava/lang/String;)V", "failedRetryCount", "getFailedRetryCount", "setFailedRetryCount", "groupId", "getGroupId", "setGroupId", "header", "getHeader", "setHeader", FirebaseAnalytics.Param.METHOD, "getMethod", "setMethod", "pathArg", "getPathArg", "setPathArg", "stateId", "getStateId", "setStateId", "getTaskTag", "setTaskTag", "getUrl", "setUrl", "addArg", "arg", "Lkotlin/Pair;", "addArgs", "argMap", "", "addBody", "addHeader", "args", "bodys", "get", "headers", "post", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class Builder {
        private Map<String, String> body;
        private long contentLength;
        private int dataTotalCount;
        private String errorMsg;
        private int failedRetryCount;
        private int groupId;
        private Map<String, String> header;
        private String method;
        private Map<String, String> pathArg;
        private long stateId;
        private String taskTag;
        private String url;

        public Builder(String url, String taskTag) {
            Intrinsics.checkParameterIsNotNull(url, "url");
            Intrinsics.checkParameterIsNotNull(taskTag, "taskTag");
            this.url = url;
            this.taskTag = taskTag;
            this.pathArg = new LinkedHashMap();
            this.header = new LinkedHashMap();
            this.body = new LinkedHashMap();
            this.failedRetryCount = 3;
            this.header.put("appKey", Constants.APP_KEY);
            Map<String, String> map = this.header;
            String token = AuthorizationPreference.getToken(IdoApp.getAppContext());
            Intrinsics.checkExpressionValueIsNotNull(token, "AuthorizationPreference.…n(IdoApp.getAppContext())");
            map.put("Authorization", token);
            this.header.put("Content-Type", "application/json");
            this.header.put("Connection", "Keep-Alive");
            this.header.put("X-HB-Client-Type", "ayb-android");
        }

        public /* synthetic */ Builder(String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i & 2) != 0 ? "DefaultTag" : str2);
        }

        public final String getTaskTag() {
            return this.taskTag;
        }

        public final String getUrl() {
            return this.url;
        }

        public final void setTaskTag(String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            this.taskTag = str;
        }

        public final void setUrl(String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            this.url = str;
        }

        public final int getGroupId() {
            return this.groupId;
        }

        public final void setGroupId(int i) {
            this.groupId = i;
        }

        public final long getStateId() {
            return this.stateId;
        }

        public final void setStateId(long j) {
            this.stateId = j;
        }

        public final Map<String, String> getPathArg() {
            return this.pathArg;
        }

        public final void setPathArg(Map<String, String> map) {
            Intrinsics.checkParameterIsNotNull(map, "<set-?>");
            this.pathArg = map;
        }

        public final Map<String, String> getHeader() {
            return this.header;
        }

        public final void setHeader(Map<String, String> map) {
            Intrinsics.checkParameterIsNotNull(map, "<set-?>");
            this.header = map;
        }

        public final Map<String, String> getBody() {
            return this.body;
        }

        public final void setBody(Map<String, String> map) {
            Intrinsics.checkParameterIsNotNull(map, "<set-?>");
            this.body = map;
        }

        public final String getMethod() {
            return this.method;
        }

        public final void setMethod(String str) {
            this.method = str;
        }

        public final int getFailedRetryCount() {
            return this.failedRetryCount;
        }

        public final void setFailedRetryCount(int i) {
            this.failedRetryCount = i;
        }

        public final String getErrorMsg() {
            return this.errorMsg;
        }

        public final void setErrorMsg(String str) {
            this.errorMsg = str;
        }

        public final long getContentLength() {
            return this.contentLength;
        }

        public final void setContentLength(long j) {
            this.contentLength = j;
        }

        public final int getDataTotalCount() {
            return this.dataTotalCount;
        }

        public final void setDataTotalCount(int i) {
            this.dataTotalCount = i;
        }

        public String toString() {
            return "{url=" + this.url + ",,pathArg=" + this.pathArg + ",header=" + this.header + ",body=" + this.body + ",method=" + this.method + ",failedRetryCount=" + this.failedRetryCount + ",taskTag=" + this.taskTag + ",errorMsg=" + this.errorMsg + ",contentLength=" + this.contentLength + ",dataTotalCount=" + this.dataTotalCount + '}';
        }

        public final Builder args(Map<String, String> args) {
            Intrinsics.checkParameterIsNotNull(args, "args");
            if (!args.isEmpty()) {
                this.pathArg.putAll(args);
            }
            return this;
        }

        public final Builder addArg(Pair<String, String> arg) {
            Intrinsics.checkParameterIsNotNull(arg, "arg");
            this.pathArg.put(arg.getFirst(), arg.getSecond());
            return this;
        }

        public final Builder addArgs(Map<String, String> argMap) {
            if (!(argMap == null || argMap.isEmpty())) {
                this.pathArg.putAll(argMap);
            }
            return this;
        }

        public final Builder headers(Map<String, String> headers) {
            if (!(headers == null || headers.isEmpty())) {
                this.header.putAll(headers);
            }
            return this;
        }

        public final Builder addHeader(Pair<String, String> header) {
            Intrinsics.checkParameterIsNotNull(header, "header");
            this.header.put(header.getFirst(), header.getSecond());
            return this;
        }

        public final Builder bodys(Map<String, String> bodys) {
            if (!(bodys == null || bodys.isEmpty())) {
                this.body.putAll(bodys);
            }
            return this;
        }

        public final Builder addBody(Pair<String, String> body) {
            Intrinsics.checkParameterIsNotNull(body, "body");
            this.body.put(body.getFirst(), body.getSecond());
            return this;
        }

        public final Builder groupId(int groupId) {
            this.groupId = groupId;
            return this;
        }

        public final Builder stateId(long stateId) {
            this.stateId = stateId;
            return this;
        }

        public final Builder post() {
            this.method = NewTask.INSTANCE.getPOST();
            return this;
        }

        public final Builder get() {
            this.method = NewTask.INSTANCE.getGET();
            return this;
        }
    }

    public final void printAndSave(String message) {
        Intrinsics.checkParameterIsNotNull(message, "message");
        if (message.length() == 0) {
            return;
        }
        CommonLogUtil.d(this.TAG, message);
    }

    protected final void printAndSaveError(String message) {
        Intrinsics.checkParameterIsNotNull(message, "message");
        if (message.length() == 0) {
            return;
        }
        CommonLogUtil.e(this.TAG, message);
    }

    @Override // java.lang.Runnable
    public void run() {
        String line;
        try {
            boolean z = true;
            if (!this.mTaskListenerList.isEmpty()) {
                Iterator<T> it = this.mTaskListenerList.iterator();
                while (it.hasNext()) {
                    ((TaskListener) it.next()).onTaskStart(this);
                }
            }
            NewTaskInfo newTaskInfo = this.taskInfo;
            Builder builder = newTaskInfo != null ? newTaskInfo.getBuilder() : null;
            if (builder == null) {
                Intrinsics.throwNpe();
            }
            String url = builder.getUrl();
            if (!builder.getPathArg().isEmpty()) {
                String str = url + "?";
                for (Map.Entry<String, String> entry : builder.getPathArg().entrySet()) {
                    str = str + entry.getKey() + '=' + entry.getValue() + Typography.amp;
                }
                url = StringsKt.dropLast(str, 1);
            }
            URLConnection uRLConnectionOpenConnection = new URL(url).openConnection();
            if (uRLConnectionOpenConnection == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.net.HttpURLConnection");
            }
            HttpURLConnection httpURLConnection = (HttpURLConnection) uRLConnectionOpenConnection;
            if (httpURLConnection instanceof HttpsURLConnection) {
                ((HttpsURLConnection) httpURLConnection).setSSLSocketFactory(SSLSocketFactory.getUnCheckSSLSocketFactory());
            }
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestMethod(builder.getMethod());
            httpURLConnection.setConnectTimeout(20000);
            httpURLConnection.setReadTimeout(300000);
            for (Map.Entry<String, String> entry2 : builder.getHeader().entrySet()) {
                httpURLConnection.addRequestProperty(entry2.getKey(), entry2.getValue());
            }
            httpURLConnection.connect();
            OutputStream outputStream = httpURLConnection.getOutputStream();
            Map<String, String> body = builder.getBody();
            if (!body.isEmpty()) {
                String str2 = "";
                for (Map.Entry<String, String> entry3 : body.entrySet()) {
                    str2 = str2 + Typography.quote + entry3.getKey() + "\":\"" + entry3.getValue() + "\",";
                }
                String str3 = '{' + StringsKt.dropLast(str2, 1) + '}';
                printAndSave(str3);
                Charset charset = Charsets.UTF_8;
                if (str3 == null) {
                    throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                }
                byte[] bytes = str3.getBytes(charset);
                Intrinsics.checkNotNullExpressionValue(bytes, "(this as java.lang.String).getBytes(charset)");
                outputStream.write(bytes);
                outputStream.flush();
                outputStream.close();
            }
            if (httpURLConnection.getResponseCode() == 200) {
                builder.setContentLength(0L);
                String headerField = httpURLConnection.getHeaderField("Content-Encoding");
                String str4 = headerField;
                BufferedReader bufferedReader = ((str4 == null || str4.length() == 0) || !StringsKt.contains$default((CharSequence) headerField, (CharSequence) "gzip", false, 2, (Object) null)) ? new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream())) : new BufferedReader(new InputStreamReader(new GZIPInputStream(httpURLConnection.getInputStream())));
                StringBuilder sb = new StringBuilder();
                while (!this.mHasStop && (line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }
                if (sb.length() <= 0) {
                    z = false;
                }
                if (!z) {
                    onFailure(httpURLConnection.getResponseMessage());
                    return;
                }
                String string = sb.toString();
                Intrinsics.checkExpressionValueIsNotNull(string, "response.toString()");
                onSuccess(string);
                return;
            }
            onFailure(httpURLConnection.getResponseMessage());
        } catch (Exception e2) {
            e2.printStackTrace();
            onFailure(e2.getLocalizedMessage());
        }
    }

    @Override // java.lang.Comparable
    public int compareTo(NewTask other) {
        if (this.taskInfo == null) {
            return 1;
        }
        if ((other != null ? other.taskInfo : null) == null) {
            return -1;
        }
        NewTaskInfo newTaskInfo = this.taskInfo;
        Integer numValueOf = newTaskInfo != null ? Integer.valueOf(newTaskInfo.getTaskId()) : null;
        NewTaskInfo newTaskInfo2 = other.taskInfo;
        if (Intrinsics.areEqual(numValueOf, newTaskInfo2 != null ? Integer.valueOf(newTaskInfo2.getTaskId()) : null)) {
            int i = this.taskPriority;
            int i2 = other.taskPriority;
            if (i > i2) {
                return -1;
            }
            return i < i2 ? 1 : 0;
        }
        NewTaskInfo newTaskInfo3 = this.taskInfo;
        Integer numValueOf2 = newTaskInfo3 != null ? Integer.valueOf(newTaskInfo3.getTaskId()) : null;
        if (numValueOf2 == null) {
            Intrinsics.throwNpe();
        }
        int iIntValue = numValueOf2.intValue();
        NewTaskInfo newTaskInfo4 = other.taskInfo;
        Integer numValueOf3 = newTaskInfo4 != null ? Integer.valueOf(newTaskInfo4.getTaskId()) : null;
        if (numValueOf3 == null) {
            Intrinsics.throwNpe();
        }
        if (iIntValue > numValueOf3.intValue()) {
            return 1;
        }
        NewTaskInfo newTaskInfo5 = this.taskInfo;
        Integer numValueOf4 = newTaskInfo5 != null ? Integer.valueOf(newTaskInfo5.getTaskId()) : null;
        if (numValueOf4 == null) {
            Intrinsics.throwNpe();
        }
        int iIntValue2 = numValueOf4.intValue();
        NewTaskInfo newTaskInfo6 = other.taskInfo;
        Integer numValueOf5 = newTaskInfo6 != null ? Integer.valueOf(newTaskInfo6.getTaskId()) : null;
        if (numValueOf5 == null) {
            Intrinsics.throwNpe();
        }
        return iIntValue2 < numValueOf5.intValue() ? -1 : 0;
    }

    public String toString() {
        return "NewTask(taskInfo=" + this.taskInfo + ", listener=" + this.listener + ", taskPriority=" + this.taskPriority + ", mTaskRetryCount=" + this.mTaskRetryCount + ", mHasStop=" + this.mHasStop + ')';
    }
}