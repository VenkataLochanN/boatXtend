package com.ido.life.syncdownload;

import com.ido.life.database.model.ActiveTimeDayData;
import com.ido.life.database.model.CalorieDayData;
import com.ido.life.database.model.DataDownLoadState;
import com.ido.life.database.model.HealthPressure;
import com.ido.life.database.model.HealthVolumeData;
import com.ido.life.database.model.LifeCycleItemBean;
import com.ido.life.database.model.ServerBloodOxyDayData;
import com.ido.life.database.model.ServerHeartRateDayData;
import com.ido.life.database.model.ServerSleepDayData;
import com.ido.life.database.model.SportDistanceBean;
import com.ido.life.database.model.SportHealth;
import com.ido.life.database.model.StepDayData;
import com.ido.life.database.model.UserMedalInfo;
import com.ido.life.database.model.UserTargetNew;
import com.ido.life.database.model.WalkDayData;
import com.ido.life.database.model.WeightItemBean;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RunTimeUtil;
import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DataDownLoadConfig.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0016\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005\u001a\"\u0010\u0006\u001a\u001e\u0012\f\u0012\n \t*\u0004\u0018\u00010\b0\b\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u000b0\n0\u0007\u001a\u001a\u0010\f\u001a\u0016\u0012\u0004\u0012\u00020\b\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\r0\n0\u0007\u001a*\u0010\u000e\u001a\u001e\u0012\f\u0012\n \t*\u0004\u0018\u00010\b0\b\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u000b0\n0\u00072\u0006\u0010\u0002\u001a\u00020\u0003\u001a\"\u0010\u000f\u001a\u0016\u0012\u0004\u0012\u00020\b\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\r0\n0\u00072\u0006\u0010\u0002\u001a\u00020\u0003¨\u0006\u0010"}, d2 = {"addNewUserDataDownloadState", "", "userId", "", "downloadState", "", "getAllHistoryTaskPropertyList", "", "", "kotlin.jvm.PlatformType", "Ljava/lang/Class;", "Lcom/ido/life/syncdownload/BaseHistoryTaskListener;", "getAllHomeTaskPropertyList", "Lcom/ido/life/syncdownload/BaseDataDownloadTaskListener;", "getHistoryTaskPropertyList", "getHomeTaskPropertyList", "app_release"}, k = 2, mv = {1, 1, 16})
public final class DataDownLoadConfigKt {
    public static final Map<String, Class<? extends BaseDataDownloadTaskListener>> getHomeTaskPropertyList(long j) {
        Map<String, Class<? extends BaseDataDownloadTaskListener>> mapMutableMapOf = MapsKt.mutableMapOf(new Pair(WeightItemBean.class.getSimpleName(), WeightLastestTaskListener.class), new Pair(UserMedalInfo.class.getSimpleName(), UserMedalTaskListener.class), new Pair(ActiveTimeDayData.class.getSimpleName(), ActiveTimeLastestTaskListener.class), new Pair(ServerBloodOxyDayData.class.getSimpleName(), BloodOxyLastestTaskListener.class), new Pair(CalorieDayData.class.getSimpleName(), CategoryLastestTaskListener.class), new Pair(SportDistanceBean.class.getSimpleName(), DistanceLastestTaskListener.class), new Pair(ServerHeartRateDayData.class.getSimpleName(), HeartRateLastestTaskListener.class), new Pair(LifeCycleItemBean.class.getSimpleName(), MensesLatestTaskListener.class), new Pair(HealthPressure.class.getSimpleName(), PressureLastestTaskListener.class), new Pair(ServerSleepDayData.class.getSimpleName(), SleepLastestTaskListener.class), new Pair(SportHealth.class.getSimpleName(), SportRecordLastestTaskListener.class), new Pair(StepDayData.class.getSimpleName(), StepLastestTaskListener.class), new Pair(WalkDayData.class.getSimpleName(), WalkHourLastestTaskListener.class), new Pair(HealthVolumeData.class.getSimpleName(), AmbientVolumeLastestTaskListener.class));
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        if (j != runTimeUtil.getUserId()) {
            mapMutableMapOf.put(UserTargetNew.class.getSimpleName(), UserTargetLastestTaskListener.class);
        }
        return mapMutableMapOf;
    }

    public static final Map<String, Class<? extends BaseDataDownloadTaskListener>> getAllHomeTaskPropertyList() {
        return MapsKt.mutableMapOf(new Pair(UserTargetNew.class.getSimpleName(), UserTargetLastestTaskListener.class), new Pair(WeightItemBean.class.getSimpleName(), WeightLastestTaskListener.class), new Pair(UserMedalInfo.class.getSimpleName(), UserMedalTaskListener.class), new Pair(ActiveTimeDayData.class.getSimpleName(), ActiveTimeLastestTaskListener.class), new Pair(ServerBloodOxyDayData.class.getSimpleName(), BloodOxyLastestTaskListener.class), new Pair(HealthVolumeData.class.getSimpleName(), AmbientVolumeLastestTaskListener.class), new Pair(CalorieDayData.class.getSimpleName(), CategoryLastestTaskListener.class), new Pair(SportDistanceBean.class.getSimpleName(), DistanceLastestTaskListener.class), new Pair(ServerHeartRateDayData.class.getSimpleName(), HeartRateLastestTaskListener.class), new Pair(LifeCycleItemBean.class.getSimpleName(), MensesLatestTaskListener.class), new Pair(HealthPressure.class.getSimpleName(), PressureLastestTaskListener.class), new Pair(ServerSleepDayData.class.getSimpleName(), SleepLastestTaskListener.class), new Pair(SportHealth.class.getSimpleName(), SportRecordLastestTaskListener.class), new Pair(StepDayData.class.getSimpleName(), StepLastestTaskListener.class), new Pair(WalkDayData.class.getSimpleName(), WalkHourLastestTaskListener.class), new Pair(HealthVolumeData.class.getSimpleName(), AmbientVolumeLastestTaskListener.class));
    }

    public static final Map<String, Class<? extends BaseHistoryTaskListener>> getHistoryTaskPropertyList(long j) {
        return MapsKt.mutableMapOf(new Pair(UserTargetNew.class.getSimpleName(), UserTargetTaskListener.class), new Pair(ActiveTimeDayData.class.getSimpleName(), ActiveTimeTaskListener.class), new Pair(ServerBloodOxyDayData.class.getSimpleName(), BloodOxyTaskListener.class), new Pair(CalorieDayData.class.getSimpleName(), CategoryTaskListener.class), new Pair(SportDistanceBean.class.getSimpleName(), DistanceTaskListener.class), new Pair(ServerHeartRateDayData.class.getSimpleName(), HeartRateTaskListener.class), new Pair(HealthPressure.class.getSimpleName(), PressureTaskListener.class), new Pair(ServerSleepDayData.class.getSimpleName(), SleepTaskListener.class), new Pair(SportHealth.class.getSimpleName(), SportRecordTaskListener.class), new Pair(StepDayData.class.getSimpleName(), StepTaskListener.class), new Pair(WalkDayData.class.getSimpleName(), WalkHourTaskListener.class), new Pair(WeightItemBean.class.getSimpleName(), WeightTaskListener.class), new Pair(LifeCycleItemBean.class.getSimpleName(), MensesTaskListener.class), new Pair(HealthVolumeData.class.getSimpleName(), AmbientVolumeTaskListener.class));
    }

    public static final Map<String, Class<? extends BaseHistoryTaskListener>> getAllHistoryTaskPropertyList() {
        return MapsKt.mutableMapOf(new Pair(UserTargetNew.class.getSimpleName(), UserTargetTaskListener.class), new Pair(ActiveTimeDayData.class.getSimpleName(), ActiveTimeTaskListener.class), new Pair(ServerBloodOxyDayData.class.getSimpleName(), BloodOxyTaskListener.class), new Pair(CalorieDayData.class.getSimpleName(), CategoryTaskListener.class), new Pair(SportDistanceBean.class.getSimpleName(), DistanceTaskListener.class), new Pair(ServerHeartRateDayData.class.getSimpleName(), HeartRateTaskListener.class), new Pair(HealthPressure.class.getSimpleName(), PressureTaskListener.class), new Pair(ServerSleepDayData.class.getSimpleName(), SleepTaskListener.class), new Pair(SportHealth.class.getSimpleName(), SportRecordTaskListener.class), new Pair(StepDayData.class.getSimpleName(), StepTaskListener.class), new Pair(WalkDayData.class.getSimpleName(), WalkHourTaskListener.class), new Pair(WeightItemBean.class.getSimpleName(), WeightTaskListener.class), new Pair(LifeCycleItemBean.class.getSimpleName(), MensesTaskListener.class), new Pair(HealthVolumeData.class.getSimpleName(), AmbientVolumeTaskListener.class));
    }

    public static final void addNewUserDataDownloadState(long j, int i) {
        Collection<Class<? extends BaseDataDownloadTaskListener>> collectionValues = getAllHomeTaskPropertyList().values();
        Collection<Class<? extends BaseHistoryTaskListener>> collectionValues2 = getAllHistoryTaskPropertyList().values();
        Iterator<Class<? extends BaseDataDownloadTaskListener>> it = collectionValues.iterator();
        while (it.hasNext()) {
            try {
                Constructor<? extends BaseDataDownloadTaskListener> declaredConstructor = it.next().getDeclaredConstructor(Long.TYPE);
                Intrinsics.checkExpressionValueIsNotNull(declaredConstructor, "taskListener.getDeclared…:class.java\n            )");
                BaseDataDownloadTaskListener baseDataDownloadTaskListenerNewInstance = declaredConstructor.newInstance(Long.valueOf(j));
                if (baseDataDownloadTaskListenerNewInstance == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.ido.life.syncdownload.BaseDataDownloadTaskListener");
                }
                DataDownLoadState defaultTaskDownloadState = baseDataDownloadTaskListenerNewInstance.getDefaultTaskDownloadState();
                defaultTaskDownloadState.setDownloadState(i);
                defaultTaskDownloadState.setTaskId(0);
                GreenDaoUtil.addDataDownLoadStateRecord(defaultTaskDownloadState);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        int iMax = Math.max(4, Math.min(Runtime.getRuntime().availableProcessors() - 1, 4));
        Iterator<Class<? extends BaseHistoryTaskListener>> it2 = collectionValues2.iterator();
        while (it2.hasNext()) {
            try {
                Constructor<? extends BaseHistoryTaskListener> declaredConstructor2 = it2.next().getDeclaredConstructor(Long.TYPE);
                Intrinsics.checkExpressionValueIsNotNull(declaredConstructor2, "taskListener.getDeclared…:class.java\n            )");
                BaseHistoryTaskListener baseHistoryTaskListenerNewInstance = declaredConstructor2.newInstance(Long.valueOf(j));
                if (baseHistoryTaskListenerNewInstance == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.ido.life.syncdownload.BaseHistoryTaskListener");
                }
                BaseHistoryTaskListener baseHistoryTaskListener = baseHistoryTaskListenerNewInstance;
                List<Map<String, String>> requestParams = baseHistoryTaskListener.getRequestParams(iMax);
                if (!requestParams.isEmpty()) {
                    int size = requestParams.size();
                    int i2 = 0;
                    while (i2 < size) {
                        Map<String, String> map = requestParams.get(i2);
                        DataDownLoadState defaultTaskDownloadState2 = baseHistoryTaskListener.getDefaultTaskDownloadState();
                        defaultTaskDownloadState2.setDownloadState(i);
                        i2++;
                        defaultTaskDownloadState2.setTaskId(i2);
                        defaultTaskDownloadState2.setParamsMap(map);
                        GreenDaoUtil.addDataDownLoadStateRecord(defaultTaskDownloadState2);
                    }
                }
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
    }
}