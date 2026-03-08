package com.ido.life.syncdownload;

import com.ido.life.database.model.DataDownLoadState;
import com.ido.life.net.BaseUrlInterceptor;
import com.ido.life.syncdownload.NewTask;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BaseDataDownloadTaskListener.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0007\b&\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\r\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u000e\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nJ\b\u0010\u000f\u001a\u00020\u0010H\u0016JL\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u00032\u0014\u0010\u0015\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u00162\u0014\u0010\u0017\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u00162\u0006\u0010\u0018\u001a\u00020\u0005H\u0016J\b\u0010\u0019\u001a\u00020\nH&J\b\u0010\u001a\u001a\u00020\nH&J\b\u0010\u001b\u001a\u00020\nH&J\b\u0010\u001c\u001a\u00020\u0000H&¨\u0006\u001d"}, d2 = {"Lcom/ido/life/syncdownload/BaseDataDownloadTaskListener;", "Lcom/ido/life/syncdownload/BaseTaskListener;", "userId", "", "taskCount", "", "(JI)V", "convertToDouble", "", "value", "", "convertToFloat", "", "convertToInt", "convertToLong", "getDefaultTaskDownloadState", "Lcom/ido/life/database/model/DataDownLoadState;", "getTask", "Lcom/ido/life/syncdownload/NewTask;", "groupId", "stateId", "params", "", "headerMap", "taskPriority", "getTaskTag", "getTaskType", "getTaskUrlPath", "newInstance", "app_release"}, k = 1, mv = {1, 1, 16})
public abstract class BaseDataDownloadTaskListener extends BaseTaskListener {
    public abstract String getTaskTag();

    public abstract String getTaskType();

    public abstract String getTaskUrlPath();

    public abstract BaseDataDownloadTaskListener newInstance();

    public /* synthetic */ BaseDataDownloadTaskListener(long j, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, (i2 & 2) != 0 ? 1 : i);
    }

    public BaseDataDownloadTaskListener(long j, int i) {
        super(j, i);
    }

    public DataDownLoadState getDefaultTaskDownloadState() {
        DataDownLoadState dataDownLoadState = new DataDownLoadState();
        dataDownLoadState.setUserId(getUserId());
        dataDownLoadState.setDataName(getTaskTag());
        dataDownLoadState.setParamsMap(new LinkedHashMap());
        dataDownLoadState.setUrl(BaseUrlInterceptor.formatBasUrl(getTaskType()) + getTaskUrlPath());
        dataDownLoadState.setDownloadTimeStamp(System.currentTimeMillis());
        return dataDownLoadState;
    }

    public NewTask getTask(int groupId, long stateId, Map<String, String> params, Map<String, String> headerMap, int taskPriority) {
        return new NewTask(new NewTask.NewTaskInfo(new NewTask.Builder(BaseUrlInterceptor.formatBasUrl(getTaskType()) + getTaskUrlPath(), getTaskTag()).stateId(stateId).groupId(groupId).headers(headerMap).addHeader(new Pair<>("Accept-Encoding", "gzip,deflate,br")).post().bodys(params).addArgs(BaseUrlInterceptor.getPublicParams())), this, taskPriority);
    }

    public final int convertToInt(String value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        if (value.length() == 0) {
            return 0;
        }
        try {
            return Integer.parseInt(value);
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    public final long convertToLong(String value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        if (value.length() == 0) {
            return 0L;
        }
        try {
            return Long.parseLong(value);
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0L;
        }
    }

    public final float convertToFloat(String value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        if (value.length() == 0) {
            return 0.0f;
        }
        try {
            return Float.parseFloat(value);
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0.0f;
        }
    }

    public final double convertToDouble(String value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        if (value.length() == 0) {
            return 0.0d;
        }
        try {
            return Double.parseDouble(value);
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0.0d;
        }
    }
}