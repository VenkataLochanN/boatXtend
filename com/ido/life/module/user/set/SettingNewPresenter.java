package com.ido.life.module.user.set;

import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import com.ido.common.log.CommonLogUtil;
import com.ido.life.base.BasePresenter;
import com.ido.life.database.model.DataDownLoadState;
import com.ido.life.database.model.DataPullConfigInfo;
import com.ido.life.database.model.MapEngine;
import com.ido.life.database.model.MessageEntity;
import com.ido.life.database.model.TimeSet;
import com.ido.life.database.model.UnitSetting;
import com.ido.life.database.model.WeekStartSetting;
import com.ido.life.syncdownload.DataDownLoadService;
import com.ido.life.syncdownload.ITaskExecutedTotalCallBack;
import com.ido.life.syncdownload.NewTask;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RunTimeUtil;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: SettingNewPresenter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0006\u0010\u000f\u001a\u00020\u0010J\u0006\u0010\u0011\u001a\u00020\u000bJ\u0006\u0010\u0012\u001a\u00020\u0006J\u0006\u0010\u0013\u001a\u00020\u0010J\u0006\u0010\u0014\u001a\u00020\u0010J\u0006\u0010\u0015\u001a\u00020\u0010J\u0006\u0010\u0016\u001a\u00020\u0010J\r\u0010\u0017\u001a\u00020\u0018H\u0016¢\u0006\u0002\u0010\u0019J\u0006\u0010\u001a\u001a\u00020\u0010J\u0006\u0010\u001b\u001a\u00020\u0010J\u0010\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u0010\u0010\u001f\u001a\u00020\u00102\u0006\u0010 \u001a\u00020!H\u0016J\u0010\u0010\"\u001a\u00020\u00102\u0006\u0010 \u001a\u00020!H\u0016J\b\u0010#\u001a\u00020\u000bH\u0002J\b\u0010$\u001a\u00020\u0010H\u0016J\b\u0010%\u001a\u00020\u0010H\u0016J\u0006\u0010&\u001a\u00020\u0010J\u0010\u0010'\u001a\u00020\u00102\u0006\u0010(\u001a\u00020\rH\u0016R\u0019\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lcom/ido/life/module/user/set/SettingNewPresenter;", "Lcom/ido/life/base/BasePresenter;", "Lcom/ido/life/module/user/set/ISettingNewView;", "Lcom/ido/life/syncdownload/ITaskExecutedTotalCallBack;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "getTAG", "()Ljava/lang/String;", "mDataDownloadState", "", "mDownTotalSize", "", "mHasDownloadSise", "getCacheData", "", "getHistoryDownloadProgress", "getHistoryDownloadSizeShowText", "getMapEngine", "getPageData", "getTimeFormat", "getUnit", "getUserId", "", "()Ljava/lang/Long;", "getWeekStart", "initConfig", "onAllTaskCompleted", "homeTask", "", "onTaskExecutedFailed", "taskInfo", "Lcom/ido/life/syncdownload/NewTask$NewTaskInfo;", "onTaskExecutedSuccess", "queryDataDownloadState", "startHistoryTask", "startHomeTask", "startSuccessWithDataPullService", "updateTaskProgress", NotificationCompat.CATEGORY_PROGRESS, "app_release"}, k = 1, mv = {1, 1, 16})
public final class SettingNewPresenter extends BasePresenter<ISettingNewView> implements ITaskExecutedTotalCallBack {
    private final String TAG = SettingNewPresenter.class.getSimpleName();
    private int mDataDownloadState;
    private float mDownTotalSize;
    private float mHasDownloadSise;

    @Override // com.ido.life.syncdownload.ITaskExecutedTotalCallBack
    public void onTaskExecutedFailed(NewTask.NewTaskInfo taskInfo) {
        Intrinsics.checkParameterIsNotNull(taskInfo, "taskInfo");
    }

    @Override // com.ido.life.syncdownload.ITaskExecutedTotalCallBack
    public void onTaskExecutedSuccess(NewTask.NewTaskInfo taskInfo) {
        Intrinsics.checkParameterIsNotNull(taskInfo, "taskInfo");
    }

    @Override // com.ido.life.syncdownload.ITaskExecutedTotalCallBack
    public void startHomeTask() {
    }

    public final String getTAG() {
        return this.TAG;
    }

    public final void initConfig() {
        CommonLogUtil.d(this.TAG, "开始初始化配置。");
        int iQueryDataDownloadState = queryDataDownloadState();
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        DataPullConfigInfo dataPullConfigInfoQueryDataPullConfigInfo = GreenDaoUtil.queryDataPullConfigInfo(runTimeUtil.getUserId());
        if (dataPullConfigInfoQueryDataPullConfigInfo != null) {
            this.mHasDownloadSise = dataPullConfigInfoQueryDataPullConfigInfo.getHasDownloadCount().floatValue();
            this.mDownTotalSize = dataPullConfigInfoQueryDataPullConfigInfo.getDataTotalCount();
        }
        if (iQueryDataDownloadState == 3) {
            this.mDataDownloadState = 3;
            ISettingNewView view = getView();
            if (view != null) {
                view.onHistoryDataDownloadSuccess();
                return;
            }
            return;
        }
        if (iQueryDataDownloadState == 4) {
            this.mDataDownloadState = 4;
            ISettingNewView view2 = getView();
            if (view2 != null) {
                view2.onHistoryDataDownloadFailed();
            }
            DataDownLoadService.INSTANCE.addAllTaskExecutedCallback(this);
            return;
        }
        this.mDataDownloadState = 2;
        ISettingNewView view3 = getView();
        if (view3 != null) {
            view3.onHistoryDataDownloading();
        }
        DataDownLoadService.INSTANCE.addAllTaskExecutedCallback(this);
    }

    public final void startSuccessWithDataPullService() {
        CommonLogUtil.d(this.TAG, "数据下拉服务启动成功");
        this.mDataDownloadState = 2;
        ISettingNewView view = getView();
        if (view != null) {
            view.onHistoryDataDownloading();
        }
        DataDownLoadService.INSTANCE.addAllTaskExecutedCallback(this);
    }

    public final String getHistoryDownloadSizeShowText() {
        if (this.mDownTotalSize <= 0) {
            return "100%";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(Math.min(MathKt.roundToInt((this.mHasDownloadSise * 100) / this.mDownTotalSize), 100));
        sb.append('%');
        return sb.toString();
    }

    public final int getHistoryDownloadProgress() {
        float f2 = this.mDownTotalSize;
        if (f2 <= 0) {
            return 100;
        }
        return MathKt.roundToInt((this.mHasDownloadSise * 100) / f2);
    }

    public final void getPageData() {
        getTimeFormat();
        getUnit();
        getWeekStart();
        getMapEngine();
        getCacheData();
    }

    public final void getTimeFormat() {
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        TimeSet timeSetQueryTimeSet = GreenDaoUtil.queryTimeSet(runTimeUtil.getUserId());
        if (timeSetQueryTimeSet != null) {
            ISettingNewView view = getView();
            if (view != null) {
                view.onGetTimeFormatSuccess(timeSetQueryTimeSet.getTimeFormat());
                return;
            }
            return;
        }
        ISettingNewView view2 = getView();
        if (view2 != null) {
            view2.onGetTimeFormatSuccess(1);
        }
    }

    public final void getUnit() {
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        UnitSetting unitSettingQueryUnitSetting = GreenDaoUtil.queryUnitSetting(runTimeUtil.getUserId());
        if (unitSettingQueryUnitSetting != null) {
            ISettingNewView view = getView();
            if (view != null) {
                view.onGetUnitSuccess(unitSettingQueryUnitSetting.getUnit());
                return;
            }
            return;
        }
        ISettingNewView view2 = getView();
        if (view2 != null) {
            view2.onGetUnitSuccess(1);
        }
    }

    public final void getWeekStart() {
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        WeekStartSetting weekStartSettingQueryWeekStart = GreenDaoUtil.queryWeekStart(runTimeUtil.getUserId());
        if (weekStartSettingQueryWeekStart != null) {
            ISettingNewView view = getView();
            if (view != null) {
                view.onGetWeekStartSuccess(weekStartSettingQueryWeekStart.getWeekStart());
                return;
            }
            return;
        }
        ISettingNewView view2 = getView();
        if (view2 != null) {
            view2.onGetWeekStartSuccess(2);
        }
    }

    public final void getMapEngine() {
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        MapEngine mapEngineQueryMapEngine = GreenDaoUtil.queryMapEngine(runTimeUtil.getUserId());
        if (mapEngineQueryMapEngine != null) {
            ISettingNewView view = getView();
            if (view != null) {
                view.onGetMapEngineSuccess(mapEngineQueryMapEngine.getMapEngine());
                return;
            }
            return;
        }
        ISettingNewView view2 = getView();
        if (view2 != null) {
            view2.onGetMapEngineSuccess(2);
        }
    }

    public final void getCacheData() {
        ISettingNewView view = getView();
        if (view != null) {
            view.onGetCacleData(20 * ((float) Math.random()));
        }
    }

    private final int queryDataDownloadState() {
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        List<DataDownLoadState> historyDataDownloadState = GreenDaoUtil.getHistoryDataDownloadState(runTimeUtil.getUserId());
        List<DataDownLoadState> list = historyDataDownloadState;
        if (list == null || list.isEmpty()) {
            CommonLogUtil.d(this.TAG, "数据下拉状态列表为空,历史数据下拉失败。");
            return 2;
        }
        int size = historyDataDownloadState.size();
        boolean z = false;
        boolean z2 = false;
        for (int i = 0; i < size; i++) {
            DataDownLoadState dataDownLoadState = historyDataDownloadState.get(i);
            if (dataDownLoadState != null) {
                String simpleName = MessageEntity.class.getSimpleName();
                if (dataDownLoadState == null) {
                    Intrinsics.throwNpe();
                }
                if (!TextUtils.equals(simpleName, dataDownLoadState.getDataName())) {
                    if (dataDownLoadState.getDownloadState() == 4) {
                        z = true;
                    } else if (dataDownLoadState.getDownloadState() == 2 || dataDownLoadState.getDownloadState() == 1) {
                        z2 = true;
                    }
                    if (z && z2) {
                        break;
                    }
                } else {
                    continue;
                }
            }
        }
        CommonLogUtil.d(this.TAG, "hasExecutingTask=" + z2 + ",hasFailedTask=" + z);
        if (z2) {
            return 2;
        }
        return z ? 4 : 3;
    }

    @Override // com.ido.life.syncdownload.ITaskExecutedTotalCallBack
    public void onAllTaskCompleted(boolean homeTask) {
        CommonLogUtil.d(this.TAG, "历史数据下拉完成");
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        List<DataDownLoadState> historyDataDownloadState = GreenDaoUtil.getHistoryDataDownloadState(runTimeUtil.getUserId());
        List<DataDownLoadState> list = historyDataDownloadState;
        boolean z = false;
        if (!(list == null || list.isEmpty())) {
            int size = historyDataDownloadState.size();
            int i = 0;
            while (true) {
                if (i >= size) {
                    z = true;
                    break;
                }
                DataDownLoadState item = historyDataDownloadState.get(i);
                Intrinsics.checkExpressionValueIsNotNull(item, "item");
                if (!TextUtils.equals(item.getDataName(), MessageEntity.class.getSimpleName()) && item.getDownloadState() != 3) {
                    break;
                } else {
                    i++;
                }
            }
        }
        if (z) {
            ISettingNewView view = getView();
            if (view != null) {
                view.onHistoryDataDownloadSuccess();
                return;
            }
            return;
        }
        ISettingNewView view2 = getView();
        if (view2 != null) {
            view2.onHistoryDataDownloadFailed();
        }
    }

    @Override // com.ido.life.syncdownload.ITaskExecutedTotalCallBack
    public void updateTaskProgress(float progress) {
        CommonLogUtil.d(this.TAG, "更新历史数据下拉进度progress=" + progress);
        if (this.mDataDownloadState != 2) {
            return;
        }
        if (progress >= this.mHasDownloadSise) {
            this.mHasDownloadSise = progress;
        }
        ISettingNewView view = getView();
        if (view != null) {
            view.onHistoryDataDownloading();
        }
    }

    @Override // com.ido.life.syncdownload.ITaskExecutedTotalCallBack
    public void startHistoryTask() {
        if (RunTimeUtil.getInstance().hasLogin()) {
            startSuccessWithDataPullService();
        }
    }

    @Override // com.ido.life.syncdownload.ITaskExecutedTotalCallBack
    public Long getUserId() {
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        return Long.valueOf(runTimeUtil.getUserId());
    }
}