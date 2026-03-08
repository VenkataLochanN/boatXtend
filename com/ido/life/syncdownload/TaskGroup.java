package com.ido.life.syncdownload;

import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPath;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.NetworkUtil;
import com.ido.life.syncdownload.NewTask;
import com.ido.life.syncdownload.Task;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TaskGroup.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010#\n\u0002\u0010\t\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u0000 F2\u00020\u0001:\u0001FB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\"\u001a\u00020\u00002\u0006\u0010#\u001a\u00020\u0000J\u000e\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u001cJ\u000e\u0010'\u001a\u00020%2\u0006\u0010#\u001a\u00020\u0000J\u000e\u0010(\u001a\u00020%2\u0006\u0010#\u001a\u00020\u0000J\u0006\u0010)\u001a\u00020\u0000J\u0006\u0010*\u001a\u00020\u0000J\u0006\u0010+\u001a\u00020\u0000J\u0010\u0010,\u001a\u0004\u0018\u00010\u001c2\u0006\u0010-\u001a\u00020\u0019J\u0006\u0010.\u001a\u00020\u0000J\u0006\u0010/\u001a\u00020\u0000J\b\u00100\u001a\u0004\u0018\u00010\u0000J\u0006\u00101\u001a\u00020\fJ\u0006\u00102\u001a\u00020\fJ\u0010\u00103\u001a\u00020%2\u0006\u0010&\u001a\u00020\u001cH\u0016J\u0018\u00104\u001a\u00020%2\u0006\u0010&\u001a\u00020\u001c2\u0006\u00105\u001a\u00020\u0006H\u0016J\u0010\u00106\u001a\u00020%2\u0006\u0010&\u001a\u00020\u001cH\u0016J\u0010\u00107\u001a\u00020%2\u0006\u0010&\u001a\u00020\u001cH\u0016J\u0010\u00108\u001a\u00020%2\u0006\u00109\u001a\u00020\u0006H\u0002J\u000e\u0010:\u001a\u00020%2\u0006\u0010;\u001a\u00020<J\u0014\u0010=\u001a\u00020%2\f\u0010>\u001a\b\u0012\u0004\u0012\u00020\u00190\u001bJ\u000e\u0010?\u001a\u00020%2\u0006\u0010-\u001a\u00020\u0019J\u0014\u0010@\u001a\u00020%2\f\u0010>\u001a\b\u0012\u0004\u0012\u00020\u00190\u001bJ\b\u0010A\u001a\u00020%H\u0002J\b\u0010B\u001a\u00020%H\u0002J\u000e\u0010C\u001a\u00020%2\u0006\u0010D\u001a\u00020\u000eJ\u0006\u0010E\u001a\u00020%R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u0004R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0000X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u0000X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0011\"\u0004\b\u0016\u0010\u0013R\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R>\u0010\u001a\u001a&\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u001c0\u001c \u0007*\u0012\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u001c0\u001c\u0018\u00010\u001d0\u001bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!¨\u0006G"}, d2 = {"Lcom/ido/life/syncdownload/TaskGroup;", "Lcom/ido/life/syncdownload/NewTask$TaskListener;", "callBack", "Lcom/ido/life/syncdownload/IDataDownLoad;", "(Lcom/ido/life/syncdownload/IDataDownLoad;)V", "TAG", "", "kotlin.jvm.PlatformType", "getCallBack", "()Lcom/ido/life/syncdownload/IDataDownLoad;", "setCallBack", "mAllExecuted", "", "mExecutedTaskFlag", "", "mNextTaskGroup", "getMNextTaskGroup", "()Lcom/ido/life/syncdownload/TaskGroup;", "setMNextTaskGroup", "(Lcom/ido/life/syncdownload/TaskGroup;)V", "mPreTaskGroup", "getMPreTaskGroup", "setMPreTaskGroup", "mTaskIdSet", "", "", "mTaskList", "", "Lcom/ido/life/syncdownload/NewTask;", "", "getMTaskList", "()Ljava/util/List;", "setMTaskList", "(Ljava/util/List;)V", "addNextTaskGroup", "taskGroup", "addTask", "", "task", "addToFirstTaskGroup", "addToLastTaskGroup", "findExecutingTaskGroup", "findFirstTaskGroup", "findLastTaskGroup", "findTask", "taskId", "getFirstTaskGroup", "getLastTaskGroup", "getPreTaskGroup", "isAllComplete", "isComplete", "onTaskCanceled", "onTaskFailed", "errorMsg", "onTaskStart", "onTaskSuccess", "printAndSave", "message", "printTaskGraph", "builder", "Ljava/lang/StringBuilder;", "removeOtherTask", "taskIdList", "removeSingleTask", "removeTaskList", "startAllTask", "startNextTask", "startTask", "taskFlag", "stopAllTask", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
public final class TaskGroup implements NewTask.TaskListener {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int EXECUTED_ONE_BY_ONE = 1;
    private static final int EXECUTED_ONE_SHOT = 2;
    private String TAG;
    private IDataDownLoad callBack;
    private boolean mAllExecuted;
    private int mExecutedTaskFlag;
    private TaskGroup mNextTaskGroup;
    private TaskGroup mPreTaskGroup;
    private Set<Long> mTaskIdSet;
    private List<NewTask> mTaskList;

    public static final int getEXECUTED_ONE_BY_ONE() {
        Companion companion = INSTANCE;
        return EXECUTED_ONE_BY_ONE;
    }

    public static final int getEXECUTED_ONE_SHOT() {
        Companion companion = INSTANCE;
        return EXECUTED_ONE_SHOT;
    }

    /* JADX INFO: compiled from: TaskGroup.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u00020\u00048\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007R\u001c\u0010\b\u001a\u00020\u00048\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\t\u0010\u0002\u001a\u0004\b\n\u0010\u0007¨\u0006\u000b"}, d2 = {"Lcom/ido/life/syncdownload/TaskGroup$Companion;", "", "()V", "EXECUTED_ONE_BY_ONE", "", "EXECUTED_ONE_BY_ONE$annotations", "getEXECUTED_ONE_BY_ONE", "()I", "EXECUTED_ONE_SHOT", "EXECUTED_ONE_SHOT$annotations", "getEXECUTED_ONE_SHOT", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        @JvmStatic
        public static /* synthetic */ void EXECUTED_ONE_BY_ONE$annotations() {
        }

        @JvmStatic
        public static /* synthetic */ void EXECUTED_ONE_SHOT$annotations() {
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final int getEXECUTED_ONE_BY_ONE() {
            return TaskGroup.EXECUTED_ONE_BY_ONE;
        }

        public final int getEXECUTED_ONE_SHOT() {
            return TaskGroup.EXECUTED_ONE_SHOT;
        }
    }

    public TaskGroup(IDataDownLoad callBack) {
        Intrinsics.checkParameterIsNotNull(callBack, "callBack");
        this.callBack = callBack;
        this.TAG = TaskGroup.class.getSimpleName();
        this.mTaskList = Collections.synchronizedList(new ArrayList());
        this.mExecutedTaskFlag = -1;
        this.mTaskIdSet = new LinkedHashSet();
    }

    public final IDataDownLoad getCallBack() {
        return this.callBack;
    }

    public final void setCallBack(IDataDownLoad iDataDownLoad) {
        Intrinsics.checkParameterIsNotNull(iDataDownLoad, "<set-?>");
        this.callBack = iDataDownLoad;
    }

    public final List<NewTask> getMTaskList() {
        return this.mTaskList;
    }

    public final void setMTaskList(List<NewTask> list) {
        this.mTaskList = list;
    }

    public final TaskGroup getMPreTaskGroup() {
        return this.mPreTaskGroup;
    }

    public final void setMPreTaskGroup(TaskGroup taskGroup) {
        this.mPreTaskGroup = taskGroup;
    }

    public final TaskGroup getMNextTaskGroup() {
        return this.mNextTaskGroup;
    }

    public final void setMNextTaskGroup(TaskGroup taskGroup) {
        this.mNextTaskGroup = taskGroup;
    }

    public final void startTask(int taskFlag) {
        int i = EXECUTED_ONE_SHOT;
        if (taskFlag == i) {
            this.mExecutedTaskFlag = i;
            startAllTask();
        } else {
            this.mExecutedTaskFlag = EXECUTED_ONE_BY_ONE;
            startNextTask();
        }
    }

    private final void startAllTask() {
        synchronized (Boolean.valueOf(this.mAllExecuted)) {
            List<NewTask> mTaskList = this.mTaskList;
            Intrinsics.checkExpressionValueIsNotNull(mTaskList, "mTaskList");
            if (!mTaskList.isEmpty()) {
                if (!this.mAllExecuted) {
                    this.mAllExecuted = true;
                    int size = this.mTaskList.size();
                    for (int i = 0; i < size; i++) {
                        NewTask item = this.mTaskList.get(i);
                        item.addTaskListener(this);
                        IDataDownLoad iDataDownLoad = this.callBack;
                        Intrinsics.checkExpressionValueIsNotNull(item, "item");
                        iDataDownLoad.submitTask(this, item);
                    }
                    Unit unit = Unit.INSTANCE;
                } else {
                    printAndSave("该任务组任务已经全部执行，不需要重复添加。");
                    Unit unit2 = Unit.INSTANCE;
                }
            } else if (this.mNextTaskGroup == null) {
                this.callBack.allTaskComplete();
                Unit unit3 = Unit.INSTANCE;
            } else {
                TaskGroup taskGroup = this.mNextTaskGroup;
                if (taskGroup != null) {
                    taskGroup.startTask(this.mExecutedTaskFlag);
                    Unit unit4 = Unit.INSTANCE;
                }
            }
        }
    }

    private final void startNextTask() {
        List<NewTask> mTaskList = this.mTaskList;
        Intrinsics.checkExpressionValueIsNotNull(mTaskList, "mTaskList");
        synchronized (mTaskList) {
            List<NewTask> mTaskList2 = this.mTaskList;
            Intrinsics.checkExpressionValueIsNotNull(mTaskList2, "mTaskList");
            if (!mTaskList2.isEmpty()) {
                List<NewTask> mTaskList3 = this.mTaskList;
                Intrinsics.checkExpressionValueIsNotNull(mTaskList3, "mTaskList");
                NewTask firstTask = (NewTask) CollectionsKt.first((List) mTaskList3);
                firstTask.addTaskListener(this);
                IDataDownLoad iDataDownLoad = this.callBack;
                Intrinsics.checkExpressionValueIsNotNull(firstTask, "firstTask");
                iDataDownLoad.submitTask(this, firstTask);
                Unit unit = Unit.INSTANCE;
            } else if (this.mNextTaskGroup == null) {
                this.callBack.allTaskComplete();
                Unit unit2 = Unit.INSTANCE;
            } else {
                TaskGroup taskGroup = this.mNextTaskGroup;
                if (taskGroup != null) {
                    taskGroup.startTask(this.mExecutedTaskFlag);
                    Unit unit3 = Unit.INSTANCE;
                }
            }
        }
    }

    public final void addTask(NewTask task) {
        Intrinsics.checkParameterIsNotNull(task, "task");
        this.mTaskList.add(task);
    }

    public final TaskGroup addNextTaskGroup(TaskGroup taskGroup) {
        Intrinsics.checkParameterIsNotNull(taskGroup, "taskGroup");
        taskGroup.findLastTaskGroup().mNextTaskGroup = this.mNextTaskGroup;
        taskGroup.findFirstTaskGroup().mPreTaskGroup = this;
        this.mNextTaskGroup = taskGroup;
        return taskGroup;
    }

    public final void addToLastTaskGroup(TaskGroup taskGroup) {
        Intrinsics.checkParameterIsNotNull(taskGroup, "taskGroup");
        TaskGroup taskGroupFindLastTaskGroup = findLastTaskGroup();
        taskGroupFindLastTaskGroup.mNextTaskGroup = taskGroup;
        taskGroup.findFirstTaskGroup().mPreTaskGroup = taskGroupFindLastTaskGroup;
    }

    public final void addToFirstTaskGroup(TaskGroup taskGroup) {
        Intrinsics.checkParameterIsNotNull(taskGroup, "taskGroup");
        TaskGroup taskGroup2 = (TaskGroup) null;
        taskGroup.mPreTaskGroup = taskGroup2;
        taskGroup.mNextTaskGroup = taskGroup2;
        TaskGroup taskGroupFindFirstTaskGroup = findFirstTaskGroup();
        TaskGroup taskGroupFindLastTaskGroup = taskGroup.findLastTaskGroup();
        taskGroupFindFirstTaskGroup.mPreTaskGroup = taskGroupFindLastTaskGroup;
        taskGroupFindLastTaskGroup.mNextTaskGroup = taskGroupFindFirstTaskGroup;
    }

    public final void stopAllTask() {
        printAndSave("停止所有任务。");
        List<NewTask> mTaskList = this.mTaskList;
        Intrinsics.checkExpressionValueIsNotNull(mTaskList, "mTaskList");
        if (!mTaskList.isEmpty()) {
            int size = this.mTaskList.size();
            for (int i = 0; i < size; i++) {
                try {
                    NewTask item = this.mTaskList.get(i);
                    item.stopExecute();
                    IDataDownLoad iDataDownLoad = this.callBack;
                    Intrinsics.checkExpressionValueIsNotNull(item, "item");
                    iDataDownLoad.taskExecutedCancel(item);
                } catch (Exception unused) {
                }
            }
        }
        this.mTaskList.clear();
        TaskGroup taskGroup = this.mNextTaskGroup;
        if (taskGroup != null) {
            taskGroup.stopAllTask();
        }
    }

    @Override // com.ido.life.syncdownload.NewTask.TaskListener
    public void onTaskSuccess(NewTask task) {
        NewTask.Builder builder;
        Intrinsics.checkParameterIsNotNull(task, "task");
        StringBuilder sb = new StringBuilder();
        sb.append("任务执行成功userId=");
        Task.Listenter listener = task.getListener();
        sb.append(listener != null ? Long.valueOf(listener.getUserId()) : null);
        sb.append(",taskTag=");
        NewTask.NewTaskInfo taskInfo = task.getTaskInfo();
        sb.append((taskInfo == null || (builder = taskInfo.getBuilder()) == null) ? null : builder.getTaskTag());
        sb.append(",taskId=");
        NewTask.NewTaskInfo taskInfo2 = task.getTaskInfo();
        sb.append(taskInfo2 != null ? Integer.valueOf(taskInfo2.getTaskId()) : null);
        printAndSave(sb.toString());
        if (task.getTaskInfo() != null) {
            Set<Long> set = this.mTaskIdSet;
            NewTask.NewTaskInfo taskInfo3 = task.getTaskInfo();
            if (taskInfo3 == null) {
                Intrinsics.throwNpe();
            }
            set.add(Long.valueOf(taskInfo3.getBuilder().getStateId()));
        }
        this.mTaskList.remove(task);
        this.callBack.taskExecutedSuccess(task);
        if (task.getListener() instanceof BaseDataDownloadProgressTaskListener) {
            IDataDownLoad iDataDownLoad = this.callBack;
            Task.Listenter listener2 = task.getListener();
            if (listener2 != null) {
                iDataDownLoad.updateProgress(task, ((BaseDataDownloadProgressTaskListener) listener2).getMCurrProgress());
            } else {
                throw new TypeCastException("null cannot be cast to non-null type com.ido.life.syncdownload.BaseDataDownloadProgressTaskListener");
            }
        }
        if (NetworkUtil.isConnected(IdoApp.getAppContext())) {
            if (isComplete()) {
                if (this.mNextTaskGroup == null) {
                    this.callBack.allTaskComplete();
                    return;
                }
                this.callBack.onGroupTaskComplete(this.mTaskIdSet);
                TaskGroup taskGroup = this.mNextTaskGroup;
                if (taskGroup != null) {
                    taskGroup.startTask(this.mExecutedTaskFlag);
                    return;
                }
                return;
            }
            if (this.mExecutedTaskFlag == EXECUTED_ONE_BY_ONE) {
                startNextTask();
                return;
            }
            return;
        }
        printAndSave("检测不到网络，停止所有任务。");
        stopAllTask();
        this.callBack.allTaskComplete();
    }

    @Override // com.ido.life.syncdownload.NewTask.TaskListener
    public void onTaskFailed(NewTask task, String errorMsg) {
        NewTask.Builder builder;
        Intrinsics.checkParameterIsNotNull(task, "task");
        Intrinsics.checkParameterIsNotNull(errorMsg, "errorMsg");
        StringBuilder sb = new StringBuilder();
        sb.append("任务执行失败userId=");
        Task.Listenter listener = task.getListener();
        sb.append(listener != null ? Long.valueOf(listener.getUserId()) : null);
        sb.append(",taskTag=");
        NewTask.NewTaskInfo taskInfo = task.getTaskInfo();
        sb.append((taskInfo == null || (builder = taskInfo.getBuilder()) == null) ? null : builder.getTaskTag());
        sb.append(",taskId=");
        NewTask.NewTaskInfo taskInfo2 = task.getTaskInfo();
        sb.append(taskInfo2 != null ? Integer.valueOf(taskInfo2.getTaskId()) : null);
        sb.append(",errorMsg=");
        sb.append(errorMsg);
        printAndSave(sb.toString());
        if (task.getTaskInfo() != null) {
            Set<Long> set = this.mTaskIdSet;
            NewTask.NewTaskInfo taskInfo3 = task.getTaskInfo();
            if (taskInfo3 == null) {
                Intrinsics.throwNpe();
            }
            set.add(Long.valueOf(taskInfo3.getBuilder().getStateId()));
        }
        this.mTaskList.remove(task);
        this.callBack.taskExecutedFailed(task, errorMsg);
        if (NetworkUtil.isConnected(IdoApp.getAppContext())) {
            if (isComplete()) {
                if (this.mNextTaskGroup == null) {
                    this.callBack.allTaskComplete();
                    return;
                }
                this.callBack.onGroupTaskComplete(this.mTaskIdSet);
                TaskGroup taskGroup = this.mNextTaskGroup;
                if (taskGroup != null) {
                    taskGroup.startTask(this.mExecutedTaskFlag);
                    return;
                }
                return;
            }
            if (this.mExecutedTaskFlag == EXECUTED_ONE_BY_ONE) {
                startNextTask();
                return;
            }
            return;
        }
        stopAllTask();
        this.callBack.allTaskComplete();
    }

    @Override // com.ido.life.syncdownload.NewTask.TaskListener
    public void onTaskCanceled(NewTask task) {
        NewTask.Builder builder;
        Intrinsics.checkParameterIsNotNull(task, "task");
        if (task.getTaskInfo() != null) {
            Set<Long> set = this.mTaskIdSet;
            NewTask.NewTaskInfo taskInfo = task.getTaskInfo();
            if (taskInfo == null) {
                Intrinsics.throwNpe();
            }
            set.add(Long.valueOf(taskInfo.getBuilder().getStateId()));
        }
        StringBuilder sb = new StringBuilder();
        sb.append("任务取消执行userId=");
        Task.Listenter listener = task.getListener();
        sb.append(listener != null ? Long.valueOf(listener.getUserId()) : null);
        sb.append(",taskTag=");
        NewTask.NewTaskInfo taskInfo2 = task.getTaskInfo();
        sb.append((taskInfo2 == null || (builder = taskInfo2.getBuilder()) == null) ? null : builder.getTaskTag());
        sb.append(",taskId=");
        NewTask.NewTaskInfo taskInfo3 = task.getTaskInfo();
        sb.append(taskInfo3 != null ? Integer.valueOf(taskInfo3.getTaskId()) : null);
        printAndSave(sb.toString());
        this.mTaskList.remove(task);
        this.callBack.taskExecutedCancel(task);
        if (NetworkUtil.isConnected(IdoApp.getAppContext())) {
            if (isComplete()) {
                if (this.mNextTaskGroup == null) {
                    this.callBack.allTaskComplete();
                    return;
                }
                this.callBack.onGroupTaskComplete(this.mTaskIdSet);
                TaskGroup taskGroup = this.mNextTaskGroup;
                if (taskGroup != null) {
                    taskGroup.startTask(this.mExecutedTaskFlag);
                    return;
                }
                return;
            }
            if (this.mExecutedTaskFlag == EXECUTED_ONE_BY_ONE) {
                startNextTask();
                return;
            }
            return;
        }
        stopAllTask();
        this.callBack.allTaskComplete();
    }

    @Override // com.ido.life.syncdownload.NewTask.TaskListener
    public void onTaskStart(NewTask task) {
        NewTask.Builder builder;
        Intrinsics.checkParameterIsNotNull(task, "task");
        StringBuilder sb = new StringBuilder();
        sb.append("任务开始执行userId=");
        Task.Listenter listener = task.getListener();
        sb.append(listener != null ? Long.valueOf(listener.getUserId()) : null);
        sb.append(",taskTag=");
        NewTask.NewTaskInfo taskInfo = task.getTaskInfo();
        sb.append((taskInfo == null || (builder = taskInfo.getBuilder()) == null) ? null : builder.getTaskTag());
        sb.append(",taskId=");
        NewTask.NewTaskInfo taskInfo2 = task.getTaskInfo();
        sb.append(taskInfo2 != null ? Integer.valueOf(taskInfo2.getTaskId()) : null);
        printAndSave(sb.toString());
        this.callBack.taskExecutedStart(task);
    }

    public final TaskGroup getPreTaskGroup() {
        return this.mPreTaskGroup;
    }

    public final boolean isComplete() {
        return this.mTaskList.isEmpty();
    }

    public final boolean isAllComplete() {
        List<NewTask> mTaskList = this.mTaskList;
        Intrinsics.checkExpressionValueIsNotNull(mTaskList, "mTaskList");
        if (!mTaskList.isEmpty()) {
            return false;
        }
        TaskGroup taskGroup = this.mNextTaskGroup;
        if (taskGroup == null) {
            return true;
        }
        if (taskGroup == null) {
            Intrinsics.throwNpe();
        }
        return taskGroup.isAllComplete();
    }

    public final TaskGroup findLastTaskGroup() {
        TaskGroup taskGroup = this.mNextTaskGroup;
        if (taskGroup == null) {
            return this;
        }
        if (taskGroup == null) {
            Intrinsics.throwNpe();
        }
        return taskGroup.findLastTaskGroup();
    }

    public final TaskGroup findFirstTaskGroup() {
        TaskGroup taskGroup = this.mPreTaskGroup;
        if (taskGroup == null) {
            return this;
        }
        if (taskGroup == null) {
            Intrinsics.throwNpe();
        }
        return taskGroup.findFirstTaskGroup();
    }

    public final TaskGroup findExecutingTaskGroup() {
        TaskGroup taskGroup;
        List<NewTask> mTaskList = this.mTaskList;
        Intrinsics.checkExpressionValueIsNotNull(mTaskList, "mTaskList");
        if ((!mTaskList.isEmpty()) || (taskGroup = this.mNextTaskGroup) == null) {
            return this;
        }
        if (taskGroup == null) {
            Intrinsics.throwNpe();
        }
        return taskGroup.findExecutingTaskGroup();
    }

    public final NewTask findTask(long taskId) {
        NewTask newTask;
        NewTask.NewTaskInfo taskInfo;
        NewTask.Builder builder;
        NewTask newTask2 = (NewTask) null;
        List<NewTask> mTaskList = this.mTaskList;
        Intrinsics.checkExpressionValueIsNotNull(mTaskList, "mTaskList");
        if (!mTaskList.isEmpty()) {
            int size = this.mTaskList.size();
            int i = 0;
            while (true) {
                if (i >= size) {
                    break;
                }
                try {
                    newTask = this.mTaskList.get(i);
                    taskInfo = newTask.getTaskInfo();
                } catch (Exception unused) {
                }
                if (taskInfo != null && (builder = taskInfo.getBuilder()) != null && builder.getStateId() == taskId) {
                    newTask2 = newTask;
                    break;
                }
                i++;
            }
        }
        if (newTask2 != null) {
            return newTask2;
        }
        TaskGroup taskGroup = this.mNextTaskGroup;
        return taskGroup != null ? taskGroup.findTask(taskId) : null;
    }

    public final void printTaskGraph(StringBuilder builder) {
        NewTask.Builder builder2;
        Intrinsics.checkParameterIsNotNull(builder, "builder");
        if (this.mTaskList.isEmpty()) {
            builder.append("任务列表为空。\n");
        } else {
            builder.append("当前节点任务列表信息:\n");
            int size = this.mTaskList.size();
            for (int i = 0; i < size; i++) {
                NewTask newTask = this.mTaskList.get(i);
                StringBuilder sb = new StringBuilder();
                sb.append("               userId=");
                Task.Listenter listener = newTask.getListener();
                Integer numValueOf = null;
                sb.append(listener != null ? Long.valueOf(listener.getUserId()) : null);
                sb.append(",taskTag=");
                NewTask.NewTaskInfo taskInfo = newTask.getTaskInfo();
                sb.append((taskInfo == null || (builder2 = taskInfo.getBuilder()) == null) ? null : builder2.getTaskTag());
                sb.append(",taskId=");
                NewTask.NewTaskInfo taskInfo2 = newTask.getTaskInfo();
                if (taskInfo2 != null) {
                    numValueOf = Integer.valueOf(taskInfo2.getTaskId());
                }
                sb.append(numValueOf);
                sb.append("\n;");
                builder.append(sb.toString());
            }
        }
        TaskGroup taskGroup = this.mNextTaskGroup;
        if (taskGroup == null || taskGroup == null) {
            return;
        }
        taskGroup.printTaskGraph(builder);
    }

    public final void removeTaskList(List<Long> taskIdList) {
        TaskGroup taskGroup;
        NewTask itemTask;
        Intrinsics.checkParameterIsNotNull(taskIdList, "taskIdList");
        if (taskIdList.isEmpty()) {
            return;
        }
        List<NewTask> mTaskList = this.mTaskList;
        Intrinsics.checkExpressionValueIsNotNull(mTaskList, "mTaskList");
        synchronized (mTaskList) {
            List<NewTask> mTaskList2 = this.mTaskList;
            Intrinsics.checkExpressionValueIsNotNull(mTaskList2, "mTaskList");
            if (!mTaskList2.isEmpty()) {
                int size = this.mTaskList.size();
                int i = 0;
                while (i < size) {
                    try {
                        itemTask = this.mTaskList.get(i);
                    } catch (Exception unused) {
                    }
                    if (itemTask.getTaskInfo() != null) {
                        NewTask.NewTaskInfo taskInfo = itemTask.getTaskInfo();
                        if (taskInfo == null) {
                            Intrinsics.throwNpe();
                        }
                        int iIndexOf = taskIdList.indexOf(Long.valueOf(taskInfo.getBuilder().getStateId()));
                        if (iIndexOf != -1) {
                            taskIdList.remove(iIndexOf);
                            itemTask.stopExecute();
                            IDataDownLoad iDataDownLoad = this.callBack;
                            Intrinsics.checkExpressionValueIsNotNull(itemTask, "itemTask");
                            iDataDownLoad.taskExecutedCancel(itemTask);
                            this.mTaskList.remove(i);
                            size--;
                        }
                    }
                    i++;
                }
            }
            if ((!taskIdList.isEmpty()) && (taskGroup = this.mNextTaskGroup) != null) {
                taskGroup.removeTaskList(taskIdList);
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void removeOtherTask(List<Long> taskIdList) {
        Intrinsics.checkParameterIsNotNull(taskIdList, "taskIdList");
        List<NewTask> mTaskList = this.mTaskList;
        Intrinsics.checkExpressionValueIsNotNull(mTaskList, "mTaskList");
        synchronized (mTaskList) {
            List<NewTask> mTaskList2 = this.mTaskList;
            Intrinsics.checkExpressionValueIsNotNull(mTaskList2, "mTaskList");
            if (!mTaskList2.isEmpty()) {
                int size = this.mTaskList.size();
                int i = 0;
                while (i < size) {
                    NewTask itemTask = this.mTaskList.get(i);
                    if (itemTask.getTaskInfo() != null) {
                        NewTask.NewTaskInfo taskInfo = itemTask.getTaskInfo();
                        if (taskInfo == null) {
                            Intrinsics.throwNpe();
                        }
                        if (taskIdList.indexOf(Long.valueOf(taskInfo.getBuilder().getStateId())) <= -1) {
                            this.mTaskList.remove(i);
                            itemTask.stopExecute();
                            IDataDownLoad iDataDownLoad = this.callBack;
                            Intrinsics.checkExpressionValueIsNotNull(itemTask, "itemTask");
                            iDataDownLoad.taskExecutedCancel(itemTask);
                        }
                    }
                    i++;
                }
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void removeSingleTask(long taskId) {
        TaskGroup taskGroup;
        if (this.mTaskList.isEmpty()) {
            TaskGroup taskGroup2 = this.mNextTaskGroup;
            if (taskGroup2 != null) {
                taskGroup2.removeSingleTask(taskId);
                return;
            }
            return;
        }
        int size = this.mTaskList.size();
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= size) {
                break;
            }
            try {
                NewTask newTask = this.mTaskList.get(i);
                NewTask.NewTaskInfo taskInfo = newTask.getTaskInfo();
                if (taskInfo == null) {
                    Intrinsics.throwNpe();
                }
                if (taskInfo.getBuilder().getStateId() == taskId) {
                    newTask.stopExecute();
                    this.mTaskList.remove(i);
                    z = true;
                    break;
                }
                continue;
            } catch (Exception unused) {
            }
            i++;
        }
        if (z || (taskGroup = this.mNextTaskGroup) == null) {
            return;
        }
        taskGroup.removeSingleTask(taskId);
    }

    public final TaskGroup getFirstTaskGroup() {
        TaskGroup taskGroup = this;
        while (true) {
            TaskGroup taskGroup2 = taskGroup.mPreTaskGroup;
            if (taskGroup2 == null) {
                return taskGroup;
            }
            if (taskGroup2 == null) {
                Intrinsics.throwNpe();
            }
            taskGroup = taskGroup2;
        }
    }

    public final TaskGroup getLastTaskGroup() {
        TaskGroup taskGroup = this;
        while (true) {
            TaskGroup taskGroup2 = taskGroup.mNextTaskGroup;
            if (taskGroup2 == null) {
                return taskGroup;
            }
            if (taskGroup2 == null) {
                Intrinsics.throwNpe();
            }
            taskGroup = taskGroup2;
        }
    }

    private final void printAndSave(String message) {
        if (message.length() == 0) {
            return;
        }
        LogPath logPathImpl = LogPathImpl.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
        CommonLogUtil.printAndSave(logPathImpl.getServerLogPath(), this.TAG, message);
    }
}