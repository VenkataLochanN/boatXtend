package com.ido.life.syncdownload;

import androidx.core.app.NotificationCompat;
import com.ido.life.database.model.DataDownLoadState;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;

/* JADX INFO: compiled from: IDataDownLoad.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bH&J\u0016\u0010\t\u001a\u00020\u00032\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u000bH&J\u0018\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H&J\u001e\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000e2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u0012H&J\u0010\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H&J\u0018\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u0016H&J\u0010\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H&J\u0010\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H&J\u0018\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u001a\u001a\u00020\u001bH&J\u0018\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\u001bH&¨\u0006\u001e"}, d2 = {"Lcom/ido/life/syncdownload/IDataDownLoad;", "", "allTaskComplete", "", "clearDataDownloadState", "getDataDownloadState", "Lcom/ido/life/database/model/DataDownLoadState;", "stateId", "", "onGroupTaskComplete", "taskIdSet", "", "submitTask", "taskGroup", "Lcom/ido/life/syncdownload/TaskGroup;", "task", "Lcom/ido/life/syncdownload/NewTask;", "taskList", "", "taskExecutedCancel", "taskExecutedFailed", "errorMsg", "", "taskExecutedStart", "taskExecutedSuccess", "updateDataDownloadState", "state", "", "updateProgress", NotificationCompat.CATEGORY_PROGRESS, "app_release"}, k = 1, mv = {1, 1, 16})
public interface IDataDownLoad {
    void allTaskComplete();

    void clearDataDownloadState();

    DataDownLoadState getDataDownloadState(long stateId);

    void onGroupTaskComplete(Set<Long> taskIdSet);

    void submitTask(TaskGroup taskGroup, NewTask task);

    void submitTask(TaskGroup taskGroup, List<NewTask> taskList);

    void taskExecutedCancel(NewTask task);

    void taskExecutedFailed(NewTask task, String errorMsg);

    void taskExecutedStart(NewTask task);

    void taskExecutedSuccess(NewTask task);

    void updateDataDownloadState(NewTask task, int state);

    void updateProgress(NewTask task, int progress);
}