package com.ido.life.syncdownload;

import androidx.core.app.NotificationCompat;
import com.ido.life.syncdownload.NewTask;
import kotlin.Metadata;

/* JADX INFO: compiled from: ITaskExecutedTotalCallBack.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0000\bf\u0018\u00002\u00020\u0001J\u000f\u0010\u0002\u001a\u0004\u0018\u00010\u0003H&¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bH&J\u0010\u0010\f\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bH&J\b\u0010\r\u001a\u00020\u0006H&J\b\u0010\u000e\u001a\u00020\u0006H&J\u0010\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u0011H&¨\u0006\u0012"}, d2 = {"Lcom/ido/life/syncdownload/ITaskExecutedTotalCallBack;", "Lcom/ido/life/syncdownload/IBaseTaskExecutedTotalCallBack;", "getUserId", "", "()Ljava/lang/Long;", "onAllTaskCompleted", "", "homeTask", "", "onTaskExecutedFailed", "taskInfo", "Lcom/ido/life/syncdownload/NewTask$NewTaskInfo;", "onTaskExecutedSuccess", "startHistoryTask", "startHomeTask", "updateTaskProgress", NotificationCompat.CATEGORY_PROGRESS, "", "app_release"}, k = 1, mv = {1, 1, 16})
public interface ITaskExecutedTotalCallBack extends IBaseTaskExecutedTotalCallBack {
    Long getUserId();

    void onAllTaskCompleted(boolean homeTask);

    void onTaskExecutedFailed(NewTask.NewTaskInfo taskInfo);

    void onTaskExecutedSuccess(NewTask.NewTaskInfo taskInfo);

    void startHistoryTask();

    void startHomeTask();

    void updateTaskProgress(float progress);
}