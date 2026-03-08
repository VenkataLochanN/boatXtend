package com.ido.life.syncdownload;

import com.amazon.identity.auth.device.authorization.AuthorizationResponseParser;
import com.ido.life.syncdownload.NewTask;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: ITaskExecutedCallBack.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&J\b\u0010\u0005\u001a\u00020\u0006H&J\u001a\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH&J\u0010\u0010\f\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH&¨\u0006\r"}, d2 = {"Lcom/ido/life/syncdownload/ITaskExecutedCallBack;", "Lcom/ido/life/syncdownload/IBaseTaskExecutedTotalCallBack;", "getFocusTaskList", "", "", "onTaskComplete", "", "onTaskExecutedFailed", "taskInfo", "Lcom/ido/life/syncdownload/NewTask$NewTaskInfo;", AuthorizationResponseParser.ERROR, "", "onTaskExecutedSuccess", "app_release"}, k = 1, mv = {1, 1, 16})
public interface ITaskExecutedCallBack extends IBaseTaskExecutedTotalCallBack {
    List<Long> getFocusTaskList();

    void onTaskComplete();

    void onTaskExecutedFailed(NewTask.NewTaskInfo taskInfo, String error);

    void onTaskExecutedSuccess(NewTask.NewTaskInfo taskInfo);
}