package com.ido.life.module.home.detail;

import android.text.TextUtils;
import android.util.Pair;
import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.callback.SettingCallBack;
import com.ido.ble.data.manage.database.HealthSleep;
import com.ido.ble.data.manage.database.HealthSleepItem;
import com.ido.ble.protocol.model.HeartRateMeasureModeV3;
import com.ido.ble.protocol.model.SleepMonitoringPara;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import com.ido.ble.protocol.model.WalkReminder;
import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.GsonUtil;
import com.ido.common.utils.NetworkUtil;
import com.ido.life.base.BaseMessage;
import com.ido.life.base.BasePresenter;
import com.ido.life.bean.ValueRangePair;
import com.ido.life.data.health.remote.HealthDataManager;
import com.ido.life.database.LocalHealthDataManager;
import com.ido.life.database.model.ServerBloodOxyDayData;
import com.ido.life.database.model.ServerBloodOxyMonthData;
import com.ido.life.database.model.ServerDaysBloodOxyData;
import com.ido.life.database.model.ServerDaysSleepData;
import com.ido.life.database.model.ServerMultiDaysBloodOxy;
import com.ido.life.database.model.ServerMultiDaysSleep;
import com.ido.life.database.model.ServerMultiMonthBloodOxy;
import com.ido.life.database.model.ServerMultiMonthBloodOxyTotalData;
import com.ido.life.database.model.ServerMultiMonthSleep;
import com.ido.life.database.model.ServerMultiMonthSleepTotalData;
import com.ido.life.database.model.ServerSleepDayData;
import com.ido.life.database.model.ServerSleepMonthData;
import com.ido.life.database.model.SleepDetailModel;
import com.ido.life.database.model.StepMonthData;
import com.ido.life.database.model.WalkDayData;
import com.ido.life.database.model.WalkMonthTotalData;
import com.ido.life.syncdownload.DataDownLoadService;
import com.ido.life.syncdownload.ITaskExecutedCallBack;
import com.ido.life.syncdownload.NewTask;
import com.ido.life.util.AsyncTaskUtil;
import com.ido.life.util.DateUtil;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.HealthDataUtil;
import com.ido.life.util.RunTimeUtil;
import com.ido.life.util.SPHelper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.TreeSet;

/* JADX INFO: loaded from: classes2.dex */
public class DetailPresenter extends BasePresenter<IDetailView> implements ITaskExecutedCallBack {
    private static final String TAG = "DetailPresenter";
    private int mDaysOfMonth;
    private String mEndDate;
    private List<ServerSleepDayData> mSleepDayDataList;
    private String mStartDate;
    private long mUserId;
    private int dataType = 0;
    private int timeType = 0;
    private int mDownloadState = -1;
    private List<Long> mFocusTaskList = new ArrayList();
    private final SettingCallBack.ICallBack mSettingCallback = new SettingCallBack.ICallBack() { // from class: com.ido.life.module.home.detail.DetailPresenter.13
        @Override // com.ido.ble.callback.SettingCallBack.ICallBack
        public void onSuccess(SettingCallBack.SettingType settingType, Object obj) {
            SupportFunctionInfo supportFunctionInfo;
            HeartRateMeasureModeV3 heartRateMeasureModeV3;
            List<Integer> secModeArray;
            boolean z;
            boolean z2;
            if (!DetailPresenter.this.isAttachView() || settingType != SettingCallBack.SettingType.HEART_RATE_MEASURE_MODE_V3 || (supportFunctionInfo = LocalDataManager.getSupportFunctionInfo()) == null || !supportFunctionInfo.V3_support_scientific_sleep || (heartRateMeasureModeV3 = (HeartRateMeasureModeV3) obj) == null || (secModeArray = heartRateMeasureModeV3.getSecModeArray()) == null || secModeArray.isEmpty()) {
                return;
            }
            Iterator<Integer> it = secModeArray.iterator();
            while (true) {
                z = true;
                if (!it.hasNext()) {
                    z2 = false;
                    break;
                } else if (it.next().intValue() <= 5) {
                    z2 = true;
                    break;
                }
            }
            if (z2) {
                IDetailView iDetailView = (IDetailView) DetailPresenter.this.getView();
                if (heartRateMeasureModeV3.measurementInterval > 0 && heartRateMeasureModeV3.measurementInterval <= 5 && (heartRateMeasureModeV3.mode == 187 || heartRateMeasureModeV3.mode == 204)) {
                    z = false;
                }
                iDetailView.onNeedTurnedOnHeartRateMeasurement(z);
            }
        }

        @Override // com.ido.ble.callback.SettingCallBack.ICallBack
        public void onFailed(SettingCallBack.SettingType settingType) {
            CommonLogUtil.d("onFailed ：" + settingType);
        }
    };

    public boolean isConnected() {
        return BLEManager.isConnected();
    }

    public void setDataType(int i) {
        this.dataType = i;
    }

    @Override // com.ido.life.base.BasePresenter
    public void attachView(IDetailView iDetailView) {
        super.attachView(iDetailView);
        this.mUserId = RunTimeUtil.getInstance().getUserId();
    }

    public boolean supportBloodDetection() {
        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
        return supportFunctionInfo != null && supportFunctionInfo.V3_support_set_spo2_all_day_on_off;
    }

    public boolean isSupportScienceSleep() {
        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
        return supportFunctionInfo != null && supportFunctionInfo.V3_support_set_scientific_sleep_switch;
    }

    public boolean isScienceSleepOpen() {
        SleepMonitoringPara sleepMonitoringPara = LocalDataManager.getSleepMonitoringPara();
        return sleepMonitoringPara == null || sleepMonitoringPara.mode == 170;
    }

    public boolean isSupportWalkReminder() {
        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
        return supportFunctionInfo != null && supportFunctionInfo.walk_reminder;
    }

    public void setTimeType(int i) {
        this.timeType = i;
    }

    public void removeListener() {
        CommonLogUtil.d(TAG, "移除数据加载监听");
        DataDownLoadService.removeTaskExecutedCallback(this);
        this.mFocusTaskList.clear();
    }

    public void addListener() {
        CommonLogUtil.d(TAG, "添加数据加载监听");
        DataDownLoadService.addTaskExecutedCallback(this);
    }

    public Date getStartDateOfWeek(Date date) {
        int weekStart = RunTimeUtil.getInstance().getWeekStart();
        if (weekStart == 2) {
            return DateUtil.getMondayOfWeek(date);
        }
        if (weekStart == 7) {
            return DateUtil.getSaturdayOfLastWeek(date);
        }
        return DateUtil.getSundayOfWeek(date);
    }

    public void requestDayData(Date date) {
        if (isAttachView()) {
            String formatDate = DateUtil.getFormatDate(DateUtil.DATE_FORMAT_YMD, date);
            this.mEndDate = formatDate;
            this.mStartDate = formatDate;
            String simpleName = null;
            int i = this.dataType;
            if (i == 4) {
                simpleName = WalkDayData.class.getSimpleName();
                queryLocalDayWalkData(this.mStartDate);
                if (this.timeType == 0) {
                    zHasWalkData = LocalHealthDataManager.getInstance().hasWalkData(this.mStartDate);
                }
            } else if (i == 7) {
                simpleName = ServerSleepDayData.class.getSimpleName();
                zHasWalkData = this.timeType == 0 ? LocalHealthDataManager.getInstance().hasSleepData(this.mStartDate) : false;
                queryLocalDaySleep(this.mStartDate);
            } else if (i == 13) {
                getView().onGetDayData(13, getTestData(24, 10));
            } else if (i == 9) {
                getView().onGetDayData(9, getTestValuePairs(24, 60, 30));
            } else if (i == 10) {
                simpleName = ServerBloodOxyDayData.class.getSimpleName();
                queryLocalDayBloodOxygen(this.mStartDate);
                if (this.timeType == 0) {
                    zHasWalkData = LocalHealthDataManager.getInstance().hasBloodDayData(this.mStartDate);
                }
            }
            processRequestDownloadState(simpleName, zHasWalkData);
        }
    }

    private void processRequestDownloadState(String str, boolean z) {
        if (isAttachView()) {
            if (TextUtils.isEmpty(str)) {
                this.mDownloadState = 3;
                getView().showLoadSuccessUI();
                return;
            }
            this.mFocusTaskList.clear();
            ArrayList arrayList = new ArrayList();
            arrayList.add(str);
            Pair<List<Long>, List<Long>> pairQueryLoadingOrFailedTask = GreenDaoUtil.queryLoadingOrFailedTask(this.mUserId, arrayList, this.mStartDate, this.mEndDate, DateUtil.DATE_FORMAT_YMD);
            if (pairQueryLoadingOrFailedTask == null || ((List) pairQueryLoadingOrFailedTask.first).size() + ((List) pairQueryLoadingOrFailedTask.second).size() == 0) {
                this.mDownloadState = 3;
                getView().showLoadSuccessUI();
                return;
            }
            if (this.timeType == 0 && z) {
                this.mDownloadState = 3;
                getView().showLoadSuccessUI();
                return;
            }
            if (((List) pairQueryLoadingOrFailedTask.second).size() > 0) {
                if (NetworkUtil.isConnected(IdoApp.getAppContext())) {
                    this.mDownloadState = 2;
                    getView().showLoadingUI();
                    new TreeSet();
                    if (((List) pairQueryLoadingOrFailedTask.first).size() > 0) {
                        this.mFocusTaskList.addAll((Collection) pairQueryLoadingOrFailedTask.first);
                    }
                    this.mFocusTaskList.addAll((Collection) pairQueryLoadingOrFailedTask.second);
                    addListener();
                    DataDownLoadService.requestPullData((List) pairQueryLoadingOrFailedTask.second);
                    return;
                }
                this.mDownloadState = 4;
                getView().showLoadFailedUI();
                return;
            }
            if (((List) pairQueryLoadingOrFailedTask.first).size() > 0) {
                this.mFocusTaskList.addAll((Collection) pairQueryLoadingOrFailedTask.first);
            }
            this.mDownloadState = 2;
            getView().showLoadingUI();
            addListener();
        }
    }

    public void retryPullData() {
        String simpleName;
        int i = this.dataType;
        if (i == 4) {
            simpleName = WalkDayData.class.getSimpleName();
        } else if (i == 7) {
            simpleName = ServerSleepDayData.class.getSimpleName();
        } else {
            simpleName = i != 10 ? null : ServerBloodOxyDayData.class.getSimpleName();
        }
        processRequestDownloadState(simpleName, false);
    }

    private void requestWalkDayData(final String str) {
        if (!SPHelper.isLogin()) {
            queryLocalDayWalkData(str);
        } else if (DateUtil.getFormatDate(DateUtil.DATE_FORMAT_YMD, DateUtil.getTodayDate()).equals(str)) {
            queryLocalDayWalkData(str);
        } else {
            HealthDataManager.requestDayWalkData(str, new HealthDataManager.OnHealthDataCallback<WalkDayData>() { // from class: com.ido.life.module.home.detail.DetailPresenter.1
                @Override // com.ido.life.data.health.remote.HealthDataManager.OnHealthDataCallback
                public void onSuccess(WalkDayData walkDayData) {
                    DetailPresenter.this.saveWalkData(walkDayData);
                    DetailPresenter.this.queryLocalDayWalkData(str);
                }

                @Override // com.ido.life.data.health.remote.HealthDataManager.OnHealthDataCallback
                public void onFailed(int i, String str2) {
                    DetailPresenter.this.queryLocalDayWalkData(str);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveWalkData(WalkDayData walkDayData) {
        if (walkDayData != null) {
            WalkDayData walkDayData2 = LocalHealthDataManager.getInstance().getWalkDayData(walkDayData.getDate());
            if (walkDayData2 == null || walkDayData2.getItems() == null || !walkDayData2.getItems().equals(walkDayData.getItems())) {
                walkDayData.setUploaded(true);
                LocalHealthDataManager.getInstance().saveWalkDayData(walkDayData);
                HealthDataUtil.calculateWalkData(DateUtil.string2Date(walkDayData.getDate(), DateUtil.DATE_FORMAT_YMD));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void queryLocalDayWalkData(String str) {
        if (isAttachView()) {
            String formatDate = DateUtil.getFormatDate(DateUtil.DATE_FORMAT_YMD, DateUtil.getTodayDate());
            WalkDayData walkDayData = LocalHealthDataManager.getInstance().getWalkDayData(str);
            getView().onGetDayWalkData(walkDayData, HealthDataUtil.formatWalkItems(walkDayData, formatDate.equals(str)));
        }
    }

    private void requestBloodOxygenDayData(final String str) {
        if (!SPHelper.isLogin()) {
            queryLocalDayBloodOxygen(str);
        } else if (DateUtil.getFormatDate(DateUtil.DATE_FORMAT_YMD, DateUtil.getTodayDate()).equals(str)) {
            queryLocalDayBloodOxygen(str);
        } else {
            HealthDataManager.requestDayBloodOxygen(str, new HealthDataManager.OnHealthDataCallback<ServerBloodOxyDayData>() { // from class: com.ido.life.module.home.detail.DetailPresenter.2
                @Override // com.ido.life.data.health.remote.HealthDataManager.OnHealthDataCallback
                public void onSuccess(ServerBloodOxyDayData serverBloodOxyDayData) {
                    DetailPresenter.this.saveBloodOxygenData(serverBloodOxyDayData);
                    DetailPresenter.this.queryLocalDayBloodOxygen(str);
                }

                @Override // com.ido.life.data.health.remote.HealthDataManager.OnHealthDataCallback
                public void onFailed(int i, String str2) {
                    DetailPresenter.this.queryLocalDayBloodOxygen(str);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveBloodOxygenData(ServerBloodOxyDayData serverBloodOxyDayData) {
        if (serverBloodOxyDayData != null) {
            ServerBloodOxyDayData bloodOxyDailyDataByDate = LocalHealthDataManager.getInstance().getBloodOxyDailyDataByDate(serverBloodOxyDayData.getDate());
            if (bloodOxyDailyDataByDate == null || bloodOxyDailyDataByDate.getItems() == null || !bloodOxyDailyDataByDate.getItems().equals(serverBloodOxyDayData.getItems())) {
                serverBloodOxyDayData.setUploaded(true);
                LocalHealthDataManager.getInstance().saveBloodOxyDayData(serverBloodOxyDayData);
                HealthDataUtil.calculateBloodOxyData(DateUtil.string2Date(serverBloodOxyDayData.getDate(), DateUtil.DATE_FORMAT_YMD));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void queryLocalDayBloodOxygen(String str) {
        if (isAttachView()) {
            ServerBloodOxyDayData bloodOxyDailyDataByDate = LocalHealthDataManager.getInstance().getBloodOxyDailyDataByDate(str);
            getView().onGetDayBloodOxygen(bloodOxyDailyDataByDate, HealthDataUtil.formatBloodOxyItems(bloodOxyDailyDataByDate == null ? null : bloodOxyDailyDataByDate.getItems()));
        }
    }

    private void requestSleepDayData(final String str) {
        if (!SPHelper.isLogin()) {
            queryLocalDaySleep(str);
        } else if (DateUtil.getFormatDate(DateUtil.DATE_FORMAT_YMD, DateUtil.getTodayDate()).equals(str)) {
            queryLocalDaySleep(str);
        } else {
            HealthDataManager.requestDaySleep(str, new HealthDataManager.OnHealthDataCallback<ServerSleepDayData>() { // from class: com.ido.life.module.home.detail.DetailPresenter.3
                @Override // com.ido.life.data.health.remote.HealthDataManager.OnHealthDataCallback
                public void onSuccess(ServerSleepDayData serverSleepDayData) {
                    DetailPresenter.this.saveSleepData(serverSleepDayData);
                    DetailPresenter.this.queryLocalDaySleep(str);
                }

                @Override // com.ido.life.data.health.remote.HealthDataManager.OnHealthDataCallback
                public void onFailed(int i, String str2) {
                    DetailPresenter.this.queryLocalDaySleep(str);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveSleepData(ServerSleepDayData serverSleepDayData) {
        if (serverSleepDayData != null) {
            ServerSleepDayData sleepDailyDataByDate = LocalHealthDataManager.getInstance().getSleepDailyDataByDate(serverSleepDayData.getDate(), serverSleepDayData.getStartTime(), serverSleepDayData.getEndTime());
            if (sleepDailyDataByDate == null || sleepDailyDataByDate.getTotalSeconds() != serverSleepDayData.getTotalSeconds() || sleepDailyDataByDate.getItems() == null || !sleepDailyDataByDate.getItems().equals(serverSleepDayData.getItems())) {
                serverSleepDayData.setUploaded(true);
                Date dateString2Date = DateUtil.string2Date(serverSleepDayData.getStartTime(), "yyyy-MM-dd HH:mm:ss");
                serverSleepDayData.setStartTimeMinuteOffset((dateString2Date.getHours() * 60) + dateString2Date.getMinutes());
                Date dateString2Date2 = DateUtil.string2Date(serverSleepDayData.getEndTime(), "yyyy-MM-dd HH:mm:ss");
                serverSleepDayData.setEndTimeMinuteOffset((dateString2Date2.getHours() * 60) + dateString2Date2.getMinutes());
                LocalHealthDataManager.getInstance().saveSleepDayData(serverSleepDayData);
                HealthDataUtil.calculateSleepData(DateUtil.string2Date(serverSleepDayData.getDate(), DateUtil.DATE_FORMAT_YMD));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void queryLocalDaySleep(String str) {
        if (isAttachView()) {
            this.mSleepDayDataList = LocalHealthDataManager.getInstance().getSleepListByDate(str);
            List<ServerSleepDayData> list = this.mSleepDayDataList;
            if (list != null && list.size() > 3) {
                ArrayList arrayList = new ArrayList();
                for (int size = this.mSleepDayDataList.size() - 1; size >= 0; size--) {
                    arrayList.add(0, this.mSleepDayDataList.get(size));
                    if (arrayList.size() == 3) {
                        break;
                    }
                }
                this.mSleepDayDataList.clear();
                this.mSleepDayDataList.addAll(arrayList);
            }
            getView().onGetDaySleepList(this.mSleepDayDataList);
            List<ServerSleepDayData> list2 = this.mSleepDayDataList;
            if (list2 == null || list2.isEmpty()) {
                getView().onGetDaySleepData(createDefaultSleepData(str), null, null);
            } else {
                switchSleepTime(0);
            }
        }
    }

    public void switchSleepTime(int i) {
        HealthSleep sleepData;
        List<ServerSleepDayData> list = this.mSleepDayDataList;
        if (list == null || i >= list.size()) {
            return;
        }
        ServerSleepDayData serverSleepDayData = this.mSleepDayDataList.get(i);
        List<HealthSleepItem> sleepItems = null;
        if (serverSleepDayData == null) {
            sleepData = createDefaultSleepData(this.mStartDate);
        } else {
            sleepData = HealthDataUtil.formatSleepData(serverSleepDayData);
            sleepItems = HealthDataUtil.formatSleepItems(serverSleepDayData.getItems());
        }
        if (isAttachView()) {
            getView().onGetDaySleepData(sleepData, sleepItems, serverSleepDayData);
        }
    }

    private HealthSleep createDefaultSleepData(String str) {
        HealthSleep healthSleep = new HealthSleep();
        healthSleep.sleepEndedTimeH = 24;
        healthSleep.totalSleepMinutes = 0;
        int[] iArrYearMonthDay = DateUtil.yearMonthDay(str);
        healthSleep.year = iArrYearMonthDay[0];
        healthSleep.month = iArrYearMonthDay[1];
        healthSleep.day = iArrYearMonthDay[2];
        return healthSleep;
    }

    public void requestWeekData(Date date) {
        String simpleName;
        if (isAttachView()) {
            this.mStartDate = DateUtil.getFormatDate(DateUtil.DATE_FORMAT_YMD, date);
            this.mEndDate = DateUtil.getFormatDate(DateUtil.DATE_FORMAT_YMD, DateUtil.getSpecifiedDayBefore(date, -6));
            int i = this.dataType;
            if (i == 4) {
                simpleName = WalkDayData.class.getSimpleName();
                queryLocalMultiDaysWalkData(this.mStartDate, this.mEndDate, false, true);
            } else if (i != 7) {
                if (i == 13) {
                    getView().onGetWeekData(13, getTestData(7, 10));
                } else if (i == 9) {
                    getView().onGetWeekData(9, getTestValuePairs(7, 60, 30));
                } else if (i == 10) {
                    simpleName = ServerBloodOxyDayData.class.getSimpleName();
                    queryLocalMultiDaysBloodOxygenTotalData(this.mStartDate, this.mEndDate, false, true);
                }
                simpleName = "";
            } else {
                simpleName = ServerSleepDayData.class.getSimpleName();
                queryLocalMultiDaySleepTotalData(this.mStartDate, this.mEndDate, false, true);
            }
            processRequestDownloadState(simpleName, false);
        }
    }

    private void queryLocalMultiDaysWalkData(final String str, final String str2, final boolean z, final boolean z2) {
        if (isAttachView()) {
            new AsyncTaskUtil(new AsyncTaskUtil.IAsyncTaskCallBack() { // from class: com.ido.life.module.home.detail.DetailPresenter.4
                @Override // com.ido.life.util.AsyncTaskUtil.IAsyncTaskCallBack
                public Object doInBackground(String... strArr) {
                    HealthDataUtil.calculateMultiDaysWalkData(str, str2, z);
                    Date todayDate = DateUtil.getTodayDate();
                    List<String> dates = DateUtil.getDates(str, str2);
                    int i = 0;
                    if (LocalHealthDataManager.getInstance().getMultiDaysWalkTotalData(str, str2) == null) {
                        float[] fArr = DetailPresenter.this.timeType != 1 ? new float[DetailPresenter.this.mDaysOfMonth] : new float[7];
                        while (i < fArr.length) {
                            if (i < dates.size()) {
                                if (DateUtil.string2Date(dates.get(i), DateUtil.DATE_FORMAT_YMD).after(todayDate)) {
                                    fArr[i] = -2.0f;
                                } else {
                                    fArr[i] = -1.0f;
                                }
                            } else {
                                fArr[i] = -2.0f;
                            }
                            i++;
                        }
                        return fArr;
                    }
                    List<WalkDayData> walkDayDataList = LocalHealthDataManager.getInstance().getWalkDayDataList(str, str2, z);
                    float[] fArr2 = new float[walkDayDataList.size()];
                    while (i < walkDayDataList.size()) {
                        if (walkDayDataList.get(i) != null) {
                            fArr2[i] = ((r7.getReachSeconds() * 1.0f) / 60.0f) / 60.0f;
                        } else if (i < dates.size()) {
                            if (DateUtil.string2Date(dates.get(i), DateUtil.DATE_FORMAT_YMD).after(todayDate)) {
                                fArr2[i] = -2.0f;
                            } else {
                                fArr2[i] = -1.0f;
                            }
                        } else {
                            fArr2[i] = -2.0f;
                        }
                        i++;
                    }
                    return fArr2;
                }

                @Override // com.ido.life.util.AsyncTaskUtil.IAsyncTaskCallBack
                public void onPostExecute(Object obj) {
                    if (DetailPresenter.this.isAttachView()) {
                        ((IDetailView) DetailPresenter.this.getView()).onGetMultiDaysWalkData(LocalHealthDataManager.getInstance().getMultiDaysWalkTotalData(str, str2), (float[]) obj, z2);
                    }
                }
            }).execute("");
        }
    }

    private void requestMultiDaysBloodOxygenData(final String str, final String str2, final boolean z, final boolean z2) {
        if (!SPHelper.isLogin()) {
            queryLocalMultiDaysBloodOxygenTotalData(str, str2, z, z2);
        } else {
            HealthDataManager.requestMultiDayBloodOxygen(str, str2, new HealthDataManager.OnHealthDataCallback<ServerMultiDaysBloodOxy>() { // from class: com.ido.life.module.home.detail.DetailPresenter.5
                @Override // com.ido.life.data.health.remote.HealthDataManager.OnHealthDataCallback
                public void onSuccess(ServerMultiDaysBloodOxy serverMultiDaysBloodOxy) {
                    if (serverMultiDaysBloodOxy != null) {
                        LocalHealthDataManager.getInstance().saveMultiDaysBloodOxyTotalData((ServerDaysBloodOxyData) GsonUtil.fromJson(GsonUtil.toJson(serverMultiDaysBloodOxy), ServerDaysBloodOxyData.class));
                        LocalHealthDataManager.getInstance().saveMultiDaysBloodOxyData(serverMultiDaysBloodOxy.getDatas(), true);
                    }
                    DetailPresenter.this.queryLocalMultiDaysBloodOxygenTotalData(str, str2, z, z2);
                }

                @Override // com.ido.life.data.health.remote.HealthDataManager.OnHealthDataCallback
                public void onFailed(int i, String str3) {
                    DetailPresenter.this.queryLocalMultiDaysBloodOxygenTotalData(str, str2, z, z2);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void queryLocalMultiDaysBloodOxygenTotalData(final String str, final String str2, final boolean z, final boolean z2) {
        if (isAttachView()) {
            new AsyncTaskUtil(new AsyncTaskUtil.IAsyncTaskCallBack() { // from class: com.ido.life.module.home.detail.DetailPresenter.6
                @Override // com.ido.life.util.AsyncTaskUtil.IAsyncTaskCallBack
                public Object doInBackground(String... strArr) {
                    HealthDataUtil.calculateMultiDaysBloodOxyData(str, str2, z);
                    if (LocalHealthDataManager.getInstance().getBloodOxyMultiDaysTotalData(str, str2) == null) {
                        return DetailPresenter.this.timeType != 1 ? new ValueRangePair[DetailPresenter.this.mDaysOfMonth] : new ValueRangePair[7];
                    }
                    List<ServerBloodOxyDayData> bloodOxyDailyDataList = LocalHealthDataManager.getInstance().getBloodOxyDailyDataList(str, str2, z);
                    ValueRangePair[] valueRangePairArr = new ValueRangePair[bloodOxyDailyDataList.size()];
                    for (int i = 0; i < bloodOxyDailyDataList.size(); i++) {
                        if (bloodOxyDailyDataList.get(i) != null) {
                            valueRangePairArr[i] = new ValueRangePair(r2.getMaxValue(), r2.getMinValue());
                        }
                    }
                    return valueRangePairArr;
                }

                @Override // com.ido.life.util.AsyncTaskUtil.IAsyncTaskCallBack
                public void onPostExecute(Object obj) {
                    if (DetailPresenter.this.isAttachView()) {
                        ((IDetailView) DetailPresenter.this.getView()).onGetMultiDaysBloodOxygen(LocalHealthDataManager.getInstance().getBloodOxyMultiDaysTotalData(str, str2), new ArrayList(Arrays.asList((ValueRangePair[]) obj)), z2);
                    }
                }
            }).execute("");
        }
    }

    private void requestMultiDaysSleepData(final String str, final String str2, final boolean z, final boolean z2) {
        if (!SPHelper.isLogin()) {
            queryLocalMultiDaySleepTotalData(str, str2, z, z2);
        } else {
            HealthDataManager.requestMultiDaySleep(str, str2, new HealthDataManager.OnHealthDataCallback<ServerMultiDaysSleep>() { // from class: com.ido.life.module.home.detail.DetailPresenter.7
                @Override // com.ido.life.data.health.remote.HealthDataManager.OnHealthDataCallback
                public void onSuccess(ServerMultiDaysSleep serverMultiDaysSleep) {
                    if (serverMultiDaysSleep != null) {
                        LocalHealthDataManager.getInstance().saveMultiDaysSleepTotalData((ServerDaysSleepData) GsonUtil.fromJson(GsonUtil.toJson(serverMultiDaysSleep), ServerDaysSleepData.class));
                        LocalHealthDataManager.getInstance().saveMultiDaysSleepData(serverMultiDaysSleep.getDatas(), true);
                    }
                    DetailPresenter.this.queryLocalMultiDaySleepTotalData(str, str2, z, z2);
                }

                @Override // com.ido.life.data.health.remote.HealthDataManager.OnHealthDataCallback
                public void onFailed(int i, String str3) {
                    DetailPresenter.this.queryLocalMultiDaySleepTotalData(str, str2, z, z2);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:116:0x02ba  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x020e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void queryLocalMultiDaySleepTotalData(java.lang.String r32, java.lang.String r33, boolean r34, boolean r35) {
        /*
            Method dump skipped, instruction units count: 721
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.home.detail.DetailPresenter.queryLocalMultiDaySleepTotalData(java.lang.String, java.lang.String, boolean, boolean):void");
    }

    public void requestMonthData(Date date) {
        String simpleName;
        if (isAttachView()) {
            this.mDaysOfMonth = DateUtil.getDaysOfMonth(date);
            this.mStartDate = DateUtil.getFormatDate(DateUtil.DATE_FORMAT_YMD, DateUtil.getFirstDayOfMonth(date));
            this.mEndDate = DateUtil.getFormatDate(DateUtil.DATE_FORMAT_YMD, DateUtil.getLastDayOfMonth(date));
            int i = this.dataType;
            if (i == 4) {
                queryLocalMultiDaysWalkData(this.mStartDate, this.mEndDate, false, true);
                simpleName = WalkDayData.class.getSimpleName();
            } else if (i != 7) {
                if (i == 13) {
                    getView().onGetMonthData(13, getTestData(this.mDaysOfMonth, 10));
                } else if (i == 9) {
                    getView().onGetMonthData(9, getTestValuePairs(this.mDaysOfMonth, 60, 30));
                } else if (i == 10) {
                    queryLocalMultiDaysBloodOxygenTotalData(this.mStartDate, this.mEndDate, false, true);
                    simpleName = ServerBloodOxyDayData.class.getSimpleName();
                }
                simpleName = "";
            } else {
                queryLocalMultiDaySleepTotalData(this.mStartDate, this.mEndDate, false, true);
                simpleName = ServerSleepDayData.class.getSimpleName();
            }
            processRequestDownloadState(simpleName, false);
        }
    }

    public void requestYearData(int i) {
        String simpleName;
        if (isAttachView()) {
            Calendar calendar = Calendar.getInstance(Locale.CHINA);
            calendar.set(1, i);
            calendar.set(2, calendar.getActualMinimum(2));
            calendar.set(5, calendar.getActualMinimum(5));
            this.mStartDate = DateUtil.format(calendar, DateUtil.DATE_FORMAT_YMD);
            calendar.set(2, calendar.getActualMaximum(2));
            calendar.set(5, calendar.getActualMaximum(5));
            this.mEndDate = DateUtil.format(calendar, DateUtil.DATE_FORMAT_YMD);
            int i2 = this.dataType;
            if (i2 == 0) {
                requestYearStepData(i);
            } else {
                if (i2 == 4) {
                    queryLocalYearWalkData(i, true);
                    simpleName = WalkDayData.class.getSimpleName();
                } else if (i2 == 7) {
                    queryLocalYearSleep(i, true);
                    simpleName = ServerSleepDayData.class.getSimpleName();
                } else if (i2 == 13) {
                    getView().onGetYearData(13, getTestData(12, 10));
                } else if (i2 == 9) {
                    getView().onGetYearData(9, getTestValuePairs(12, 60, 30));
                } else if (i2 == 10) {
                    queryLocalYearBloodOxy(i, true);
                    simpleName = ServerBloodOxyDayData.class.getSimpleName();
                }
                processRequestDownloadState(simpleName, false);
            }
            simpleName = "";
            processRequestDownloadState(simpleName, false);
        }
    }

    private void queryLocalYearWalkData(final int i, final boolean z) {
        if (isAttachView()) {
            new AsyncTaskUtil(new AsyncTaskUtil.IAsyncTaskCallBack() { // from class: com.ido.life.module.home.detail.DetailPresenter.8
                @Override // com.ido.life.util.AsyncTaskUtil.IAsyncTaskCallBack
                public Object doInBackground(String... strArr) {
                    HealthDataUtil.calculateWalkYearData(i);
                    List<WalkMonthTotalData> monthWalkAnnual = LocalHealthDataManager.getInstance().getMonthWalkAnnual(i);
                    float[] defaultValues = HealthDataUtil.getDefaultValues(12);
                    for (int i2 = 0; i2 < Math.min(defaultValues.length, monthWalkAnnual.size()); i2++) {
                        WalkMonthTotalData walkMonthTotalData = monthWalkAnnual.get(i2);
                        if (walkMonthTotalData == null) {
                            defaultValues[i2] = -1.0f;
                        } else {
                            defaultValues[i2] = walkMonthTotalData.getAvgDayHour();
                        }
                    }
                    return defaultValues;
                }

                @Override // com.ido.life.util.AsyncTaskUtil.IAsyncTaskCallBack
                public void onPostExecute(Object obj) {
                    if (DetailPresenter.this.isAttachView()) {
                        ((IDetailView) DetailPresenter.this.getView()).onGetYearWalkData(LocalHealthDataManager.getInstance().getWalkYearTotalData(i), (float[]) obj, z);
                    }
                }
            }).execute("");
        }
    }

    private void requestYearBloodOxyData(final int i, final boolean z) {
        if (!SPHelper.isLogin()) {
            queryLocalYearBloodOxy(i, z);
            return;
        }
        HealthDataManager.requestMultiMonthBloodOxy(i + LocalHealthDataManager.YEAR_START_DATE, i + LocalHealthDataManager.YEAR_END_DATE, new HealthDataManager.OnHealthDataCallback<ServerMultiMonthBloodOxy>() { // from class: com.ido.life.module.home.detail.DetailPresenter.9
            @Override // com.ido.life.data.health.remote.HealthDataManager.OnHealthDataCallback
            public void onSuccess(ServerMultiMonthBloodOxy serverMultiMonthBloodOxy) {
                if (serverMultiMonthBloodOxy != null) {
                    LocalHealthDataManager.getInstance().saveYearBloodOxy((ServerMultiMonthBloodOxyTotalData) GsonUtil.fromJson(GsonUtil.toJson(serverMultiMonthBloodOxy), ServerMultiMonthBloodOxyTotalData.class));
                    LocalHealthDataManager.getInstance().saveMultiMonthBloodOxyData(serverMultiMonthBloodOxy.getDatas());
                }
                DetailPresenter.this.queryLocalYearBloodOxy(i, z);
            }

            @Override // com.ido.life.data.health.remote.HealthDataManager.OnHealthDataCallback
            public void onFailed(int i2, String str) {
                DetailPresenter.this.queryLocalYearBloodOxy(i, z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void queryLocalYearBloodOxy(final int i, final boolean z) {
        new AsyncTaskUtil(new AsyncTaskUtil.IAsyncTaskCallBack() { // from class: com.ido.life.module.home.detail.DetailPresenter.10
            @Override // com.ido.life.util.AsyncTaskUtil.IAsyncTaskCallBack
            public Object doInBackground(String... strArr) {
                HealthDataUtil.calculateBloodOxyYearData(i);
                List<ServerBloodOxyMonthData> monthBloodOxyAnnual = LocalHealthDataManager.getInstance().getMonthBloodOxyAnnual(i);
                ArrayList arrayList = new ArrayList();
                for (int i2 = 0; i2 < monthBloodOxyAnnual.size(); i2++) {
                    if (monthBloodOxyAnnual.get(i2) == null) {
                        arrayList.add(new ValueRangePair());
                    } else {
                        arrayList.add(new ValueRangePair(r3.getMaxValue(), r3.getMinValue()));
                    }
                }
                int size = arrayList.size();
                if (size < 12) {
                    for (int i3 = 0; i3 < 12 - size; i3++) {
                        arrayList.add(new ValueRangePair());
                    }
                }
                return arrayList;
            }

            @Override // com.ido.life.util.AsyncTaskUtil.IAsyncTaskCallBack
            public void onPostExecute(Object obj) {
                if (DetailPresenter.this.isAttachView()) {
                    ((IDetailView) DetailPresenter.this.getView()).onGetYearBloodOxy(LocalHealthDataManager.getInstance().getYearBloodOxy(i), (List) obj, z);
                }
            }
        }).execute("");
    }

    private void requestYearSleepData(final int i, final boolean z) {
        if (!SPHelper.isLogin()) {
            queryLocalYearSleep(i, z);
            return;
        }
        HealthDataManager.requestMultiMonthSleep(i + LocalHealthDataManager.YEAR_START_DATE, i + LocalHealthDataManager.YEAR_END_DATE, new HealthDataManager.OnHealthDataCallback<ServerMultiMonthSleep>() { // from class: com.ido.life.module.home.detail.DetailPresenter.11
            @Override // com.ido.life.data.health.remote.HealthDataManager.OnHealthDataCallback
            public void onSuccess(ServerMultiMonthSleep serverMultiMonthSleep) {
                if (serverMultiMonthSleep != null) {
                    LocalHealthDataManager.getInstance().saveYearSleep((ServerMultiMonthSleepTotalData) GsonUtil.fromJson(GsonUtil.toJson(serverMultiMonthSleep), ServerMultiMonthSleepTotalData.class));
                    LocalHealthDataManager.getInstance().saveMultiMonthSleepData(serverMultiMonthSleep.getDatas());
                }
                DetailPresenter.this.queryLocalYearSleep(i, z);
            }

            @Override // com.ido.life.data.health.remote.HealthDataManager.OnHealthDataCallback
            public void onFailed(int i2, String str) {
                DetailPresenter.this.queryLocalYearSleep(i, z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void queryLocalYearSleep(final int i, final boolean z) {
        if (isAttachView()) {
            new AsyncTaskUtil(new AsyncTaskUtil.IAsyncTaskCallBack() { // from class: com.ido.life.module.home.detail.DetailPresenter.12
                @Override // com.ido.life.util.AsyncTaskUtil.IAsyncTaskCallBack
                public Object doInBackground(String... strArr) {
                    HealthDataUtil.calculateSleepYearData(i);
                    List<ServerSleepMonthData> monthSleepAnnual = LocalHealthDataManager.getInstance().getMonthSleepAnnual(i);
                    ArrayList arrayList = new ArrayList();
                    for (int i2 = 0; i2 < monthSleepAnnual.size(); i2++) {
                        ServerSleepMonthData serverSleepMonthData = monthSleepAnnual.get(i2);
                        if (serverSleepMonthData == null) {
                            serverSleepMonthData = new ServerSleepMonthData();
                        }
                        SleepDetailModel sleepDetailModel = new SleepDetailModel();
                        sleepDetailModel.totalSleepMinutes = serverSleepMonthData.getAvgTotalSeconds() / 60;
                        sleepDetailModel.lightSleepMinutes = serverSleepMonthData.getAvgLightlySeconds() / 60;
                        sleepDetailModel.deepSleepMinutes = serverSleepMonthData.getAvgDeeplySeconds() / 60;
                        sleepDetailModel.awakeSleepMinutes = serverSleepMonthData.getAvgAwakeSeconds() / 60;
                        sleepDetailModel.eyeMovementSleepMinutes = serverSleepMonthData.getAvgEyeMovementSeconds() / 60;
                        arrayList.add(sleepDetailModel);
                    }
                    int size = arrayList.size();
                    if (size < 12) {
                        for (int i3 = 0; i3 < 12 - size; i3++) {
                            arrayList.add(new SleepDetailModel());
                        }
                    }
                    return arrayList;
                }

                @Override // com.ido.life.util.AsyncTaskUtil.IAsyncTaskCallBack
                public void onPostExecute(Object obj) {
                    if (DetailPresenter.this.isAttachView()) {
                        ((IDetailView) DetailPresenter.this.getView()).onGetYearSleepData(LocalHealthDataManager.getInstance().getYearSleep(i), (List) obj, z);
                    }
                }
            }).execute("");
        }
    }

    private void requestYearStepData(int i) {
        queryLocalYearStep(i);
    }

    private void queryLocalYearStep(int i) {
        if (isAttachView()) {
            List<StepMonthData> monthStepDataAnnual = LocalHealthDataManager.getInstance().getMonthStepDataAnnual(i);
            float[] fArr = new float[12];
            int i2 = 0;
            int avgSteps = 0;
            int totalSteps = 0;
            for (int i3 = 0; i3 < Math.min(fArr.length, monthStepDataAnnual.size()); i3++) {
                StepMonthData stepMonthData = monthStepDataAnnual.get(i3);
                if (stepMonthData != null) {
                    fArr[i3] = stepMonthData.getAvgSteps();
                    avgSteps += stepMonthData.getAvgSteps();
                    totalSteps += stepMonthData.getTotalSteps();
                    if (stepMonthData.getAvgSteps() > 0) {
                        i2++;
                    }
                }
            }
            if (i2 > 0) {
                avgSteps /= i2;
            }
            getView().onGetYearStepData(totalSteps, avgSteps, fArr);
        }
    }

    public String[] getMonthLabels(Date date) {
        int daysOfMonth = DateUtil.getDaysOfMonth(date) / 4;
        String[] strArr = new String[5];
        strArr[strArr.length - 1] = DateUtil.getFormatDate(DateUtil.DATE_FORMAT_DM, DateUtil.getLastDayOfMonth(date));
        for (int i = 0; i < strArr.length - 1; i++) {
            strArr[i] = DateUtil.getFormatDate(DateUtil.DATE_FORMAT_DM, DateUtil.getDateOfMonthBySpecifiedDay(date, (i * daysOfMonth) + 1));
        }
        return strArr;
    }

    public int getWalkTargetHour() {
        WalkReminder walkReminder = LocalDataManager.getWalkReminder();
        if (walkReminder == null) {
            walkReminder = new WalkReminder();
            walkReminder.setWeeks(new boolean[]{true, true, true, true, true, true, true});
            walkReminder.setStartHour(9);
            walkReminder.setStartMinute(0);
            walkReminder.setEndHour(21);
            walkReminder.setEndMinute(0);
            walkReminder.setGoalStep(100);
        }
        int endHour = walkReminder.getEndHour() - walkReminder.getStartHour();
        return endHour <= 0 ? endHour + 24 : endHour;
    }

    public void getHeartRateMode() {
        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
        if (supportFunctionInfo != null && supportFunctionInfo.ex_main4_v3_hr_data && BLEManager.isConnected()) {
            HeartRateMeasureModeV3 heartRateMeasureModeV3 = new HeartRateMeasureModeV3();
            heartRateMeasureModeV3.updateTime = 0;
            BLEManager.unregisterSettingCallBack(this.mSettingCallback);
            BLEManager.registerSettingCallBack(this.mSettingCallback);
            BLEManager.setHeartRateMeasureModeV3(heartRateMeasureModeV3);
        }
    }

    public ServerSleepDayData getLastSleepData() {
        return LocalHealthDataManager.getInstance().getNearestSleep();
    }

    public ServerBloodOxyDayData getLastBloodOxyDailyData() {
        return LocalHealthDataManager.getInstance().getNearBloodOxyDailyData();
    }

    private List<ValueRangePair> getTestValuePairs(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        Random random = new Random();
        for (int i4 = 0; i4 < i; i4++) {
            arrayList.add(new ValueRangePair(random.nextInt(i3) + i2, random.nextInt(i3) + i3));
        }
        return arrayList;
    }

    private float[] getTestData(int i, int i2) {
        float[] fArr = new float[i];
        Random random = new Random();
        for (int i3 = 0; i3 < i; i3++) {
            fArr[i3] = random.nextInt(i2);
        }
        return fArr;
    }

    private void getTestDaySleepData() {
        Random random = new Random();
        HealthSleep healthSleep = new HealthSleep();
        healthSleep.setTotalSleepMinutes(random.nextInt(100) + 450);
        healthSleep.setSleepEndedTimeH(random.nextInt(3) + 7);
        healthSleep.setSleepEndedTimeM(random.nextInt(60));
        healthSleep.setDeepSleepMinutes(random.nextInt(100) + 200);
        healthSleep.setLightSleepMinutes(random.nextInt(50) + 100);
        ArrayList arrayList = new ArrayList();
        int offsetMinute = 0;
        for (int i = 0; i < 10; i++) {
            HealthSleepItem healthSleepItem = new HealthSleepItem();
            healthSleepItem.setSleepStatus(random.nextInt(3) + 1);
            healthSleepItem.setOffsetMinute(random.nextInt(20) + 30);
            offsetMinute += healthSleepItem.getOffsetMinute();
            arrayList.add(healthSleepItem);
        }
        if (offsetMinute < healthSleep.getTotalSleepMinutes()) {
            HealthSleepItem healthSleepItem2 = new HealthSleepItem();
            healthSleepItem2.setSleepStatus(random.nextInt(3) + 1);
            healthSleepItem2.setOffsetMinute(healthSleep.getTotalSleepMinutes() - offsetMinute);
            arrayList.add(healthSleepItem2);
        }
        getView().onGetDaySleepData(healthSleep, arrayList, new ServerSleepDayData());
    }

    private List<SleepDetailModel> getTestRecordSleepData(int i) {
        Random random = new Random();
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < i; i2++) {
            SleepDetailModel sleepDetailModel = new SleepDetailModel();
            sleepDetailModel.setTotalSleepMinutes(random.nextInt(100) + 450);
            sleepDetailModel.setDeepSleepMinutes(random.nextInt(200) + 50);
            sleepDetailModel.setLightSleepMinutes(random.nextInt(100) + 50);
            sleepDetailModel.eyeMovementSleepMinutes = random.nextInt(50) + 50;
            sleepDetailModel.awakeSleepMinutes = random.nextInt(100) + 50;
            arrayList.add(sleepDetailModel);
        }
        return arrayList;
    }

    private int[] getTestDayBloodOxygenData(int i) {
        Random random = new Random();
        int[] iArr = new int[i];
        for (int i2 = 0; i2 < iArr.length; i2++) {
            iArr[i2] = random.nextInt(20) + 80;
        }
        return iArr;
    }

    private List<ValueRangePair> getTestBloodOxygenData(int i) {
        ArrayList arrayList = new ArrayList();
        Random random = new Random();
        for (int i2 = 0; i2 < i; i2++) {
            arrayList.add(new ValueRangePair(random.nextInt(5) + 95, random.nextInt(35) + 60));
        }
        return arrayList;
    }

    @Override // com.ido.life.base.BasePresenter
    public void detachView() {
        super.detachView();
        BLEManager.unregisterSettingCallBack(this.mSettingCallback);
    }

    public void processEventMessage(BaseMessage baseMessage) {
        if (baseMessage.getType() == 101 && this.dataType == 7) {
            getHeartRateMode();
        }
    }

    @Override // com.ido.life.syncdownload.ITaskExecutedCallBack
    public void onTaskExecutedSuccess(NewTask.NewTaskInfo newTaskInfo) {
        if (!isAttachView() || this.mDownloadState == 3) {
            return;
        }
        refreshPage(false);
    }

    @Override // com.ido.life.syncdownload.ITaskExecutedCallBack
    public void onTaskExecutedFailed(NewTask.NewTaskInfo newTaskInfo, String str) {
        if (!isAttachView() || this.mDownloadState == 4) {
            return;
        }
        this.mDownloadState = 4;
        getView().showLoadFailedUI();
    }

    @Override // com.ido.life.syncdownload.ITaskExecutedCallBack
    public void onTaskComplete() {
        int i = this.mDownloadState;
        if (i == 3 || i == 4) {
            return;
        }
        this.mDownloadState = 3;
        if (isAttachView()) {
            getView().showLoadSuccessUI();
        }
    }

    private void refreshPage(boolean z) {
        int i;
        try {
            i = DateUtil.yearMonthDay(this.mStartDate)[0];
        } catch (Exception e2) {
            e2.printStackTrace();
            i = -1;
        }
        if (i == -1) {
            return;
        }
        int i2 = this.dataType;
        if (i2 == 4) {
            int i3 = this.timeType;
            if (i3 == 0) {
                queryLocalDayWalkData(this.mStartDate);
                return;
            } else if (i3 == 3) {
                queryLocalYearWalkData(i, z);
                return;
            } else {
                queryLocalMultiDaysWalkData(this.mStartDate, this.mEndDate, false, z);
                return;
            }
        }
        if (i2 == 7) {
            int i4 = this.timeType;
            if (i4 == 0) {
                queryLocalDaySleep(this.mStartDate);
                return;
            } else if (i4 == 3) {
                queryLocalYearSleep(i, z);
                return;
            } else {
                queryLocalMultiDaySleepTotalData(this.mStartDate, this.mEndDate, false, z);
                return;
            }
        }
        if (i2 != 10) {
            return;
        }
        int i5 = this.timeType;
        if (i5 == 0) {
            queryLocalDayBloodOxygen(this.mStartDate);
        } else if (i5 == 3) {
            queryLocalYearBloodOxy(i, z);
        } else {
            queryLocalMultiDaysBloodOxygenTotalData(this.mStartDate, this.mEndDate, false, z);
        }
    }

    @Override // com.ido.life.syncdownload.ITaskExecutedCallBack
    public List<Long> getFocusTaskList() {
        return this.mFocusTaskList;
    }
}