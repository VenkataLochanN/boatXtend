package com.ido.life.module.home.menstrualcycle;

import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.style.TextAppearanceSpan;
import android.util.Pair;
import com.amazon.identity.auth.device.authorization.AuthorizationResponseParser;
import com.boat.Xtend.two.R;
import com.google.android.gms.fitness.FitnessActivities;
import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.callback.OtherProtocolCallBack;
import com.ido.ble.callback.SettingCallBack;
import com.ido.ble.protocol.model.MenstruationHistoricalData;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.NetworkUtil;
import com.ido.life.base.BaseMessage;
import com.ido.life.base.BasePresenter;
import com.ido.life.customview.MenstruationCalendar;
import com.ido.life.database.model.DataDownLoadState;
import com.ido.life.database.model.LifeCycleItemBean;
import com.ido.life.database.model.MenstruationConfig;
import com.ido.life.database.model.WeekStartSetting;
import com.ido.life.module.home.HomeHelperKt;
import com.ido.life.module.home.WholeLifeCycleInfo;
import com.ido.life.module.home.menstrualcycle.dialog.MenstrulationSettingPresenter;
import com.ido.life.syncdownload.DataDownLoadService;
import com.ido.life.syncdownload.ITaskExecutedCallBack;
import com.ido.life.syncdownload.NewTask;
import com.ido.life.util.DateUtil;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RunTimeUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.math.MathKt;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: MenstrualCycleDetailPresenter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0094\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010%\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0010\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u00042\u00020\u0005:\u0002bcB\u0005¢\u0006\u0002\u0010\u0006J\b\u00102\u001a\u000203H\u0002J\b\u00104\u001a\u000203H\u0002J\b\u00105\u001a\u000203H\u0002J\u0016\u00106\u001a\u00020\b2\u0006\u00107\u001a\u00020\b2\u0006\u00108\u001a\u00020\bJ\u000e\u00109\u001a\b\u0012\u0004\u0012\u00020\u00100\u0019H\u0002J\u000e\u0010:\u001a\b\u0012\u0004\u0012\u00020\u001c0\u0019H\u0016J\u0018\u0010;\u001a\n\u0012\u0004\u0012\u00020!\u0018\u00010\u00192\u0006\u0010<\u001a\u00020\bH\u0002J\u0018\u0010=\u001a\u00020\r2\u0006\u00107\u001a\u00020\b2\u0006\u00108\u001a\u00020\bH\u0002J\u001c\u0010>\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\u0006\u00107\u001a\u00020\b2\u0006\u00108\u001a\u00020\bJ\b\u0010?\u001a\u000203H\u0002J\b\u0010@\u001a\u000203H\u0002J\b\u0010A\u001a\u00020BH\u0002J\u0012\u0010C\u001a\u0002032\b\u0010D\u001a\u0004\u0018\u00010EH\u0016J\u0012\u0010C\u001a\u0002032\b\u0010D\u001a\u0004\u0018\u00010FH\u0016J\u0012\u0010G\u001a\u0002032\b\u0010D\u001a\u0004\u0018\u00010EH\u0016J\u001c\u0010G\u001a\u0002032\b\u0010D\u001a\u0004\u0018\u00010F2\b\u0010H\u001a\u0004\u0018\u00010IH\u0016J\b\u0010J\u001a\u000203H\u0016J\u001a\u0010K\u001a\u0002032\u0006\u0010L\u001a\u00020M2\b\u0010N\u001a\u0004\u0018\u00010\rH\u0016J\u0010\u0010O\u001a\u0002032\u0006\u0010L\u001a\u00020MH\u0016J\u0014\u0010P\u001a\u0002032\f\u0010Q\u001a\b\u0012\u0002\b\u0003\u0018\u00010RJ\u0010\u0010S\u001a\n\u0012\u0004\u0012\u00020\u001c\u0018\u00010TH\u0002J\u0006\u0010U\u001a\u000203J\u0006\u0010V\u001a\u000203J\b\u0010W\u001a\u00020BH\u0002J\b\u0010X\u001a\u000203H\u0002J\b\u0010Y\u001a\u000203H\u0002J\u0006\u0010Z\u001a\u000203J&\u0010[\u001a\u0002032\u0006\u00107\u001a\u00020\b2\u0006\u00108\u001a\u00020\b2\f\u0010\\\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019H\u0002J\u0006\u0010]\u001a\u000203J\u000e\u0010^\u001a\u0002032\u0006\u0010_\u001a\u00020\bJ&\u0010`\u001a\u0002032\u0006\u00107\u001a\u00020\b2\u0006\u00108\u001a\u00020\b2\f\u0010a\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019H\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082D¢\u0006\u0002\n\u0000R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R \u0010\u0017\u001a\u0014\u0012\u0004\u0012\u00020\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u00190\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0012R\u000e\u0010&\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010(\u001a\u00020\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u001a\u0010-\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010/\"\u0004\b0\u00101¨\u0006d"}, d2 = {"Lcom/ido/life/module/home/menstrualcycle/MenstrualCycleDetailPresenter;", "Lcom/ido/life/base/BasePresenter;", "Lcom/ido/life/module/home/menstrualcycle/IMenstrualCycleDetailView;", "Lcom/ido/ble/callback/SettingCallBack$ICallBack;", "Lcom/ido/life/syncdownload/ITaskExecutedCallBack;", "Lcom/ido/ble/callback/OtherProtocolCallBack$ICallBack;", "()V", "FAILED", "", "MENS_HISTORY_SHOW_MAX_COUNT", "SUCCESS", "SYNCING", "TAG", "", "mChartDataList", "Ljava/util/LinkedList;", "Lcom/ido/life/module/home/menstrualcycle/dialog/MenstrulationSettingPresenter$SettingBean;", "getMChartDataList", "()Ljava/util/LinkedList;", "mCurrentDay", "mCurrentMonth", "mCurrentYear", "mDataDownloadState", "mDateListMap", "", "", "Lcom/ido/life/customview/MenstruationCalendar$DateInfo;", "mFocusTaskList", "", "mLatestMensInfo", "Lcom/ido/life/module/home/menstrualcycle/MenstrualCycleDetailPresenter$MensInfo;", "mMensCycle", "mMensHistoryList", "Lcom/ido/life/module/home/menstrualcycle/MenstrualCycleDetailPresenter$HistoryMenstruationItemBean;", "mMensLength", "mMonthOffset", "mPageList", "getMPageList", "mSyncHistoryState", "mSyncMensConfigState", "mUserId", "getMUserId", "()J", "setMUserId", "(J)V", "mWeekStart", "getMWeekStart", "()I", "setMWeekStart", "(I)V", "calculateBottomData", "", "calculateLatestMensInfo", "calculateMensDesc", "getDataDownloadState", "year", "month", "getDataList", "getFocusTaskList", "getHistoryMensRecord", "dataCount", "getKey", "getMenstruationData", "getPageData", "initConfigData", "isSupportSetMenstrualNotifyFlag", "", "onFailed", "settingType", "Lcom/ido/ble/callback/OtherProtocolCallBack$SettingType;", "Lcom/ido/ble/callback/SettingCallBack$SettingType;", "onSuccess", "p1", "", "onTaskComplete", "onTaskExecutedFailed", "taskInfo", "Lcom/ido/life/syncdownload/NewTask$NewTaskInfo;", AuthorizationResponseParser.ERROR, "onTaskExecutedSuccess", "processBusMessage", "message", "Lcom/ido/life/base/BaseMessage;", "queryUnSuccessTaskId", "", "retryLoadData", "startLoadData", "supportHistoryMensConfig", "syncHistoryMens", "syncMensConfig", "syncMensToDevice", "updateDateState", "list", "updateMensInfo", "updateMonthOffset", "offset", "updatePredictCycle", "dataInfoList", "HistoryMenstruationItemBean", "MensInfo", "app_release"}, k = 1, mv = {1, 1, 16})
public final class MenstrualCycleDetailPresenter extends BasePresenter<IMenstrualCycleDetailView> implements SettingCallBack.ICallBack, ITaskExecutedCallBack, OtherProtocolCallBack.ICallBack {
    private final int FAILED;
    private final int SUCCESS;
    private final int SYNCING;
    private final LinkedList<MenstrulationSettingPresenter.SettingBean> mChartDataList;
    private final int mCurrentDay;
    private final int mCurrentMonth;
    private final int mCurrentYear;
    private int mDataDownloadState;
    private final Map<String, List<MenstruationCalendar.DateInfo>> mDateListMap;
    private final List<Long> mFocusTaskList;
    private MensInfo mLatestMensInfo;
    private int mMensCycle;
    private List<HistoryMenstruationItemBean> mMensHistoryList;
    private int mMensLength;
    private int mMonthOffset;
    private final LinkedList<MenstrulationSettingPresenter.SettingBean> mPageList;
    private int mSyncHistoryState;
    private int mSyncMensConfigState;
    private long mUserId;
    private int mWeekStart;
    private final String TAG = "MenstrualCycleDetail";
    private final int MENS_HISTORY_SHOW_MAX_COUNT = 5;

    public MenstrualCycleDetailPresenter() {
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        this.mUserId = runTimeUtil.getUserId();
        this.mFocusTaskList = new ArrayList();
        this.mChartDataList = new LinkedList<>();
        this.mDateListMap = new LinkedHashMap();
        this.mMensHistoryList = new ArrayList();
        this.mDataDownloadState = 3;
        this.mMensCycle = 28;
        this.mWeekStart = -1;
        this.mMensLength = 7;
        this.mLatestMensInfo = new MensInfo(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2047, null);
        this.mPageList = new LinkedList<>();
        this.FAILED = 1;
        this.SYNCING = -1;
        int i = this.SYNCING;
        this.mSyncHistoryState = i;
        this.mSyncMensConfigState = i;
        Calendar calendar = Calendar.getInstance();
        this.mCurrentYear = calendar.get(1);
        this.mCurrentMonth = calendar.get(2);
        this.mCurrentDay = calendar.get(5);
    }

    public final long getMUserId() {
        return this.mUserId;
    }

    public final void setMUserId(long j) {
        this.mUserId = j;
    }

    public final LinkedList<MenstrulationSettingPresenter.SettingBean> getMChartDataList() {
        return this.mChartDataList;
    }

    public final int getMWeekStart() {
        return this.mWeekStart;
    }

    public final void setMWeekStart(int i) {
        this.mWeekStart = i;
    }

    public final LinkedList<MenstrulationSettingPresenter.SettingBean> getMPageList() {
        return this.mPageList;
    }

    private final List<Long> queryUnSuccessTaskId() {
        this.mFocusTaskList.clear();
        if (!RunTimeUtil.getInstance().hasLogin()) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        String str = DateUtil.format(calendar, DateUtil.DATE_FORMAT_YMD);
        calendar.add(1, -100);
        Pair<List<Long>, List<Long>> pairQueryLoadingOrFailedTask = GreenDaoUtil.queryLoadingOrFailedTask(this.mUserId, CollectionsKt.mutableListOf(LifeCycleItemBean.class.getSimpleName()), DateUtil.format(calendar, DateUtil.DATE_FORMAT_YMD), str, DateUtil.DATE_FORMAT_YMD);
        if (pairQueryLoadingOrFailedTask == null) {
            return null;
        }
        Intrinsics.checkExpressionValueIsNotNull(pairQueryLoadingOrFailedTask.first, "stateList.first");
        if (!((Collection) r1).isEmpty()) {
            List<Long> list = this.mFocusTaskList;
            Object obj = pairQueryLoadingOrFailedTask.first;
            Intrinsics.checkExpressionValueIsNotNull(obj, "stateList.first");
            list.addAll((Collection) obj);
        }
        Intrinsics.checkExpressionValueIsNotNull(pairQueryLoadingOrFailedTask.second, "stateList.second");
        if (!((Collection) r1).isEmpty()) {
            List<Long> list2 = this.mFocusTaskList;
            Object obj2 = pairQueryLoadingOrFailedTask.second;
            Intrinsics.checkExpressionValueIsNotNull(obj2, "stateList.second");
            list2.addAll((Collection) obj2);
        }
        return (List) pairQueryLoadingOrFailedTask.second;
    }

    public final void startLoadData() {
        retryLoadData();
    }

    private final void getPageData() {
        initConfigData();
        calculateLatestMensInfo();
        calculateBottomData();
        calculateMensDesc();
        this.mChartDataList.clear();
        this.mDateListMap.clear();
        this.mChartDataList.addAll(getDataList());
        IMenstrualCycleDetailView view = getView();
        if (view != null) {
            view.onGetDataSuccess(this.mChartDataList);
        }
        IMenstrualCycleDetailView view2 = getView();
        if (view2 != null) {
            view2.updateDeviceConnectState(BLEManager.isConnected());
        }
        this.mMensHistoryList.clear();
        List<HistoryMenstruationItemBean> historyMensRecord = getHistoryMensRecord(20);
        if (!(historyMensRecord == null || historyMensRecord.isEmpty())) {
            this.mMensHistoryList.addAll(historyMensRecord);
        }
        IMenstrualCycleDetailView view3 = getView();
        if (view3 != null) {
            view3.setHistoryMenstruation(this.mMensHistoryList);
        }
    }

    public final void syncMensToDevice() {
        int i;
        MenstrualCycleDetailPresenter menstrualCycleDetailPresenter = this;
        BLEManager.unregisterOtherProtocolCallBack(menstrualCycleDetailPresenter);
        MenstrualCycleDetailPresenter menstrualCycleDetailPresenter2 = this;
        BLEManager.unregisterSettingCallBack(menstrualCycleDetailPresenter2);
        if (!BLEManager.isConnected()) {
            HomeHelperKt.printAndSave("准备同步经期数据到设备，但是此时设备没有连接.", this.TAG);
            IMenstrualCycleDetailView view = getView();
            if (view != null) {
                view.syncToDeviceDisconnect();
            }
            IMenstrualCycleDetailView view2 = getView();
            if (view2 != null) {
                view2.updateDeviceConnectState(false);
                return;
            }
            return;
        }
        int i2 = this.SYNCING;
        this.mSyncHistoryState = i2;
        this.mSyncMensConfigState = i2;
        IMenstrualCycleDetailView view3 = getView();
        if (view3 != null) {
            view3.startSyncToDevice();
        }
        BLEManager.unregisterSettingCallBack(menstrualCycleDetailPresenter2);
        BLEManager.unregisterOtherProtocolCallBack(menstrualCycleDetailPresenter);
        syncHistoryMens();
        syncMensConfig();
        int i3 = this.mSyncHistoryState;
        int i4 = this.SYNCING;
        if (i3 == i4 || (i = this.mSyncMensConfigState) == i4) {
            return;
        }
        int i5 = this.SUCCESS;
        if (i3 == i5 && i == i5) {
            IMenstrualCycleDetailView view4 = getView();
            if (view4 != null) {
                view4.syncToDeviceSuccess();
                return;
            }
            return;
        }
        IMenstrualCycleDetailView view5 = getView();
        if (view5 != null) {
            view5.syncToDeviceFailed();
        }
    }

    private final void syncHistoryMens() {
        int i;
        int i2;
        int mensesCycle;
        int i3;
        int i4;
        List<LifeCycleItemBean> list;
        int i5;
        if (supportHistoryMensConfig()) {
            int i6 = 5;
            List<LifeCycleItemBean> listQueryLatestLifeCycle = HomeHelperKt.queryLatestLifeCycle(this.mUserId, 5);
            List<LifeCycleItemBean> list2 = listQueryLatestLifeCycle;
            int i7 = 1;
            int i8 = 0;
            if (list2 == null || list2.isEmpty()) {
                HomeHelperKt.printAndSave("准备同步生理周期数据到设备端，但是用户没有记录数据", this.TAG);
                this.mSyncHistoryState = this.FAILED;
                return;
            }
            MenstruationConfig mensConfig = GreenDaoUtil.queryMenstruationConfig(this.mUserId);
            HomeHelperKt.printAndSave("当前设备支持历史生理周期数据下发", this.TAG);
            List<LifeCycleItemBean> list3 = listQueryLatestLifeCycle;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list3, 10));
            for (LifeCycleItemBean lifeCycleItemBean : list3) {
                List<List<Integer>> itemList = lifeCycleItemBean.getItemList();
                if (!(itemList == null || itemList.isEmpty())) {
                    List<List<Integer>> itemList2 = lifeCycleItemBean.getItemList();
                    Intrinsics.checkExpressionValueIsNotNull(itemList2, "it.itemList");
                    if (itemList2.size() > 1) {
                        CollectionsKt.sortWith(itemList2, new Comparator<T>() { // from class: com.ido.life.module.home.menstrualcycle.MenstrualCycleDetailPresenter$$special$$inlined$sortByDescending$1
                            @Override // java.util.Comparator
                            public final int compare(T t, T t2) {
                                List it = (List) t2;
                                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                                Integer num = (Integer) CollectionsKt.first(it);
                                List it2 = (List) t;
                                Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                                return ComparisonsKt.compareValues(num, (Integer) CollectionsKt.first(it2));
                            }
                        });
                    }
                }
                arrayList.add(Unit.INSTANCE);
            }
            MenstruationHistoricalData menstruationHistoricalData = new MenstruationHistoricalData();
            ArrayList arrayList2 = new ArrayList();
            menstruationHistoricalData.items = arrayList2;
            int size = listQueryLatestLifeCycle.size() - 1;
            if (size >= 0) {
                int i9 = 0;
                int i10 = 0;
                mensesCycle = 0;
                i3 = 0;
                int i11 = 0;
                while (true) {
                    LifeCycleItemBean lifeCycleItemBean2 = listQueryLatestLifeCycle.get(i10);
                    List<List<Integer>> itemList3 = lifeCycleItemBean2.getItemList();
                    if (((itemList3 == null || itemList3.isEmpty()) ? i7 : i8) != 0) {
                        list = listQueryLatestLifeCycle;
                        i5 = i6;
                    } else {
                        i4 = i9 + 1;
                        mensesCycle += lifeCycleItemBean2.getMensesCycle();
                        try {
                            String month = lifeCycleItemBean2.getMonth();
                            try {
                                Intrinsics.checkExpressionValueIsNotNull(month, "itemLifeCycle.month");
                                List listSplit$default = StringsKt.split$default((CharSequence) month, new String[]{"-"}, false, 0, 6, (Object) null);
                                int i12 = Integer.parseInt((String) listSplit$default.get(i8));
                                int i13 = Integer.parseInt((String) listSplit$default.get(i7));
                                Iterator<List<Integer>> it = lifeCycleItemBean2.getItemList().iterator();
                                while (true) {
                                    if (!it.hasNext()) {
                                        list = listQueryLatestLifeCycle;
                                        i2 = i11;
                                        i5 = 5;
                                        break;
                                    }
                                    List<Integer> itemList4 = it.next();
                                    i11++;
                                    MenstruationHistoricalData.MenstruationItem menstruationItem = new MenstruationHistoricalData.MenstruationItem();
                                    menstruationItem.year = i12;
                                    menstruationItem.mon = i13;
                                    int i14 = i13;
                                    Intrinsics.checkExpressionValueIsNotNull(itemList4, "itemList");
                                    Object objFirst = CollectionsKt.first((List<? extends Object>) itemList4);
                                    list = listQueryLatestLifeCycle;
                                    try {
                                        Intrinsics.checkExpressionValueIsNotNull(objFirst, "itemList.first()");
                                        menstruationItem.day = ((Number) objFirst).intValue();
                                        if (arrayList2.isEmpty()) {
                                            Intrinsics.checkExpressionValueIsNotNull(mensConfig, "mensConfig");
                                            menstruationItem.menstrual_day = mensConfig.getMensLength();
                                        } else {
                                            menstruationItem.menstrual_day = itemList4.size();
                                        }
                                        i3 += menstruationItem.menstrual_day;
                                        menstruationItem.cycle_day = lifeCycleItemBean2.getMensesCycle();
                                        arrayList2.add(menstruationItem);
                                        i5 = 5;
                                        if (i11 == 5) {
                                            i2 = i11;
                                            break;
                                        } else {
                                            i13 = i14;
                                            listQueryLatestLifeCycle = list;
                                        }
                                    } catch (Exception e2) {
                                        e = e2;
                                        i5 = 5;
                                        e.printStackTrace();
                                    }
                                }
                            } catch (Exception e3) {
                                e = e3;
                                list = listQueryLatestLifeCycle;
                            }
                        } catch (Exception e4) {
                            e = e4;
                            list = listQueryLatestLifeCycle;
                            i5 = i6;
                        }
                        if (i2 == i5) {
                            break;
                        }
                        i11 = i2;
                        i9 = i4;
                    }
                    if (i10 == size) {
                        i4 = i9;
                        i2 = i11;
                        break;
                    } else {
                        i10++;
                        i6 = i5;
                        listQueryLatestLifeCycle = list;
                        i7 = 1;
                        i8 = 0;
                    }
                }
                i = 1;
            } else {
                i = 1;
                i2 = 0;
                mensesCycle = 0;
                i3 = 0;
                i4 = 0;
            }
            menstruationHistoricalData.version = i;
            menstruationHistoricalData.avg_cycle_day = i4 > 0 ? MathKt.roundToInt(mensesCycle / i4) : 0;
            menstruationHistoricalData.avg_menstrual_day = i2 > 0 ? MathKt.roundToInt(i3 / i2) : 0;
            menstruationHistoricalData.items_len = arrayList2.size();
            BLEManager.registerSettingCallBack(this);
            BLEManager.setMenstruationHistoricalData(menstruationHistoricalData);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0135  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void syncMensConfig() {
        /*
            Method dump skipped, instruction units count: 430
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.home.menstrualcycle.MenstrualCycleDetailPresenter.syncMensConfig():void");
    }

    private final boolean isSupportSetMenstrualNotifyFlag() {
        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
        return supportFunctionInfo != null && supportFunctionInfo.V3_menstrual_add_notify_flag;
    }

    private final boolean supportHistoryMensConfig() {
        SupportFunctionInfo supportFunctionInfo;
        return BLEManager.isConnected() && (supportFunctionInfo = LocalDataManager.getSupportFunctionInfo()) != null && supportFunctionInfo.V3_v3_33_3D_historical_menstruation_01_create;
    }

    private final void initConfigData() {
        updateMensInfo();
        WeekStartSetting weekStartSetting = GreenDaoUtil.queryWeekStart(this.mUserId);
        if (weekStartSetting == null) {
            weekStartSetting = RunTimeUtil.generateDefaultWeekSetting(this.mUserId);
        }
        Intrinsics.checkExpressionValueIsNotNull(weekStartSetting, "weekStartSetting");
        this.mWeekStart = weekStartSetting.getWeekStart();
    }

    public final void updateMensInfo() {
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        MenstruationConfig menstruationConfigQueryMenstruationConfig = GreenDaoUtil.queryMenstruationConfig(runTimeUtil.getUserId());
        if (menstruationConfigQueryMenstruationConfig != null) {
            this.mMensLength = menstruationConfigQueryMenstruationConfig.getMensLength();
            this.mMensCycle = menstruationConfigQueryMenstruationConfig.getMensCycle();
            return;
        }
        RunTimeUtil runTimeUtil2 = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil2, "RunTimeUtil.getInstance()");
        LifeCycleItemBean lifeCycleItemBeanQueryLatestLifeCycle = HomeHelperKt.queryLatestLifeCycle(runTimeUtil2.getUserId());
        if (lifeCycleItemBeanQueryLatestLifeCycle != null) {
            this.mMensLength = lifeCycleItemBeanQueryLatestLifeCycle.getMensesDays();
            this.mMensCycle = lifeCycleItemBeanQueryLatestLifeCycle.getMensesCycle();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x0178  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void calculateLatestMensInfo() {
        /*
            Method dump skipped, instruction units count: 491
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.home.menstrualcycle.MenstrualCycleDetailPresenter.calculateLatestMensInfo():void");
    }

    private final List<MenstrulationSettingPresenter.SettingBean> getDataList() {
        if (!this.mPageList.isEmpty()) {
            return this.mPageList;
        }
        Calendar calendar = Calendar.getInstance();
        int i = this.mMonthOffset;
        if (i != 0) {
            calendar.add(2, i);
        }
        calendar.add(2, -1);
        for (int i2 = 1; i2 <= 3; i2++) {
            this.mPageList.add(new MenstrulationSettingPresenter.SettingBean(calendar.get(1), calendar.get(2)));
            calendar.add(2, 1);
        }
        return this.mPageList;
    }

    public final void updateMonthOffset(int offset) {
        this.mMonthOffset += offset;
        Calendar calendar = Calendar.getInstance();
        int i = this.mMonthOffset;
        if (i != 0) {
            calendar.add(2, i);
        }
        calendar.add(2, -1);
        for (int i2 = 0; i2 <= 2; i2++) {
            MenstrulationSettingPresenter.SettingBean settingBean = this.mPageList.get(i2);
            Intrinsics.checkExpressionValueIsNotNull(settingBean, "mPageList[i]");
            MenstrulationSettingPresenter.SettingBean settingBean2 = settingBean;
            settingBean2.setYear(calendar.get(1));
            settingBean2.setMonth(calendar.get(2));
            calendar.add(2, 1);
        }
    }

    public final List<MenstruationCalendar.DateInfo> getMenstruationData(int year, int month) {
        String key = getKey(year, month);
        List<MenstruationCalendar.DateInfo> list = this.mDateListMap.get(key);
        List<MenstruationCalendar.DateInfo> list2 = list;
        if (!(list2 == null || list2.isEmpty())) {
            return list;
        }
        List<MenstruationCalendar.DateInfo> listGenerateCalendarData = MenstrualUtilKt.generateCalendarData(year, month, true, this.mWeekStart);
        this.mDateListMap.put(key, listGenerateCalendarData);
        updateDateState(year, month, listGenerateCalendarData);
        return listGenerateCalendarData;
    }

    private final void updateDateState(int year, int month, List<MenstruationCalendar.DateInfo> list) {
        updatePredictCycle(year, month, list);
    }

    /* JADX WARN: Code restructure failed: missing block: B:110:0x02fe, code lost:
    
        r6 = 2;
     */
    /* JADX WARN: Removed duplicated region for block: B:102:0x02e0  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x02eb  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x03a1  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x03ac  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x03c4  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x0418  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void updatePredictCycle(int r35, int r36, java.util.List<com.ido.life.customview.MenstruationCalendar.DateInfo> r37) {
        /*
            Method dump skipped, instruction units count: 1396
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.home.menstrualcycle.MenstrualCycleDetailPresenter.updatePredictCycle(int, int, java.util.List):void");
    }

    private final void calculateMensDesc() {
        int i;
        int color;
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        WholeLifeCycleInfo wholeLifeCycleInfoQueryLatestWholeLifeCycle = HomeHelperKt.queryLatestWholeLifeCycle(runTimeUtil.getUserId());
        if (wholeLifeCycleInfoQueryLatestWholeLifeCycle == null) {
            IMenstrualCycleDetailView view = getView();
            if (view != null) {
                view.updateMensDesc(-1, null);
                return;
            }
            return;
        }
        int[] iArrCalculateMensState = HomeHelperKt.calculateMensState(wholeLifeCycleInfoQueryLatestWholeLifeCycle.getMensesCycle(), wholeLifeCycleInfoQueryLatestWholeLifeCycle.getMensesDays(), wholeLifeCycleInfoQueryLatestWholeLifeCycle.getMensStartDate());
        int i2 = iArrCalculateMensState[1];
        int i3 = iArrCalculateMensState[0];
        if (i3 == 1) {
            i = R.string.menstrual_reminder;
            color = Color.parseColor("#FE6F75");
        } else if (i3 == 2) {
            i = R.string.end_of_menstruation;
            color = Color.parseColor("#DED5FF");
        } else if (i3 == 3) {
            i = R.string.start_of_menstruation;
            color = Color.parseColor("#DED5FF");
        } else {
            i = R.string.end_of_easy_pregnancy;
            color = Color.parseColor("#FE6F75");
        }
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String languageText = LanguageUtil.getLanguageText(i);
        Intrinsics.checkExpressionValueIsNotNull(languageText, "LanguageUtil.getLanguageText(formatResId)");
        Object[] objArr = {Integer.valueOf(i2)};
        String str = String.format(languageText, Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(format, *args)");
        String str2 = str;
        int iIndexOf$default = StringsKt.indexOf$default((CharSequence) str2, String.valueOf(i2), 0, false, 6, (Object) null);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str2);
        spannableStringBuilder.setSpan(new TextAppearanceSpan(IdoApp.getAppContext(), R.style.home_life_cycle_info), iIndexOf$default, String.valueOf(i2).length() + iIndexOf$default, 17);
        IMenstrualCycleDetailView view2 = getView();
        if (view2 != null) {
            view2.updateMensDesc(color, spannableStringBuilder);
        }
    }

    private final void calculateBottomData() {
        List<LifeCycleItemBean> listQueryLatestLifeCycle = HomeHelperKt.queryLatestLifeCycle(this.mUserId, 5);
        List<LifeCycleItemBean> list = listQueryLatestLifeCycle;
        if (list == null || list.isEmpty()) {
            IMenstrualCycleDetailView view = getView();
            if (view != null) {
                view.menstruationAvgLength(0);
            }
            IMenstrualCycleDetailView view2 = getView();
            if (view2 != null) {
                view2.menstruationCycleAvg(0);
                return;
            }
            return;
        }
        Calendar calendar = Calendar.getInstance();
        int i = 0;
        int mensesCycle = 0;
        int i2 = 0;
        int size = 0;
        for (LifeCycleItemBean lifeCycleItemBean : listQueryLatestLifeCycle) {
            List<List<Integer>> itemList = lifeCycleItemBean.getItemList();
            if (!(itemList == null || itemList.isEmpty())) {
                i++;
                mensesCycle += lifeCycleItemBean.getMensesCycle();
                if (i2 < 5) {
                    List<List<Integer>> itemList2 = lifeCycleItemBean.getItemList();
                    Intrinsics.checkExpressionValueIsNotNull(itemList2, "cycleItemBean.itemList");
                    if (itemList2.size() > 1) {
                        CollectionsKt.sortWith(itemList2, new Comparator<T>() { // from class: com.ido.life.module.home.menstrualcycle.MenstrualCycleDetailPresenter$calculateBottomData$$inlined$sortByDescending$1
                            @Override // java.util.Comparator
                            public final int compare(T t, T t2) {
                                List it = (List) t2;
                                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                                Integer numValueOf = Integer.valueOf(((Number) CollectionsKt.first(it)).intValue());
                                List it2 = (List) t;
                                Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                                return ComparisonsKt.compareValues(numValueOf, Integer.valueOf(((Number) CollectionsKt.first(it2)).intValue()));
                            }
                        });
                    }
                    Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
                    calendar.setTime(DateUtil.string2Date(lifeCycleItemBean.getMonth(), DateUtil.DATE_FORMAT_YM_3));
                    for (List<Integer> itemList3 : lifeCycleItemBean.getItemList()) {
                        i2++;
                        size += itemList3.size();
                        Intrinsics.checkExpressionValueIsNotNull(itemList3, "itemList");
                        Object objFirst = CollectionsKt.first((List<? extends Object>) itemList3);
                        Intrinsics.checkExpressionValueIsNotNull(objFirst, "itemList.first()");
                        calendar.set(5, ((Number) objFirst).intValue());
                        Object objLast = CollectionsKt.last((List<? extends Object>) itemList3);
                        Intrinsics.checkExpressionValueIsNotNull(objLast, "itemList.last()");
                        calendar.set(5, ((Number) objLast).intValue());
                        if (i2 == 5) {
                            break;
                        }
                    }
                }
                if (i == 5) {
                    break;
                }
            }
        }
        CommonLogUtil.printAndSave("calculateBottomData,totalMensLengthCount=" + i2 + ", totalMensLength=" + size);
        IMenstrualCycleDetailView view3 = getView();
        if (view3 != null) {
            view3.menstruationCycleAvg(i > 0 ? MathKt.roundToInt(mensesCycle / i) : 0);
        }
    }

    private final List<HistoryMenstruationItemBean> getHistoryMensRecord(int dataCount) {
        String str;
        String str2;
        int menstruationLength;
        List<LifeCycleItemBean> list;
        int i;
        int i2;
        int i3;
        LifeCycleItemBean lifeCycleItemBean;
        int i4;
        int i5;
        List<LifeCycleItemBean> listQueryLatestLifeCycle = HomeHelperKt.queryLatestLifeCycle(this.mUserId, dataCount);
        List<LifeCycleItemBean> list2 = listQueryLatestLifeCycle;
        String str3 = "RunTimeUtil.getInstance()";
        String str4 = "mensConfig";
        if (list2 == null || list2.isEmpty()) {
            RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
            MenstruationConfig mensConfig = GreenDaoUtil.queryMenstruationConfig(runTimeUtil.getUserId());
            IMenstrualCycleDetailView view = getView();
            if (view != null) {
                Intrinsics.checkExpressionValueIsNotNull(mensConfig, "mensConfig");
                view.menstruationAvgLength(mensConfig.getMensLength());
            }
            IMenstrualCycleDetailView view2 = getView();
            if (view2 == null) {
                return null;
            }
            Intrinsics.checkExpressionValueIsNotNull(mensConfig, "mensConfig");
            view2.menstruationCycleAvg(mensConfig.getMensCycle());
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int size = listQueryLatestLifeCycle.size();
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        int i6 = 0;
        int i7 = 0;
        while (true) {
            if (i6 >= size) {
                str = str3;
                str2 = str4;
                menstruationLength = 0;
                break;
            }
            LifeCycleItemBean lifeCycleItemBean2 = listQueryLatestLifeCycle.get(i6);
            List<List<Integer>> itemList = lifeCycleItemBean2.getItemList();
            if (itemList == null || itemList.isEmpty()) {
                list = listQueryLatestLifeCycle;
                str = str3;
                str2 = str4;
                i = size;
                i2 = i6;
            } else {
                List<List<Integer>> itemList2 = lifeCycleItemBean2.getItemList();
                Intrinsics.checkExpressionValueIsNotNull(itemList2, "lifeCycleItem.itemList");
                CollectionsKt.sortWith(itemList2, new Comparator<List<? extends Integer>>() { // from class: com.ido.life.module.home.menstrualcycle.MenstrualCycleDetailPresenter.getHistoryMensRecord.1
                    @Override // java.util.Comparator
                    public /* bridge */ /* synthetic */ int compare(List<? extends Integer> list3, List<? extends Integer> list4) {
                        return compare2((List<Integer>) list3, (List<Integer>) list4);
                    }

                    /* JADX INFO: renamed from: compare, reason: avoid collision after fix types in other method */
                    public final int compare2(List<Integer> list3, List<Integer> list4) {
                        return list3.get(0).intValue() > list4.get(0).intValue() ? -1 : 1;
                    }
                });
                Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
                calendar.setTime(DateUtil.string2Date(lifeCycleItemBean2.getMonth(), DateUtil.DATE_FORMAT_YM_3));
                int size2 = lifeCycleItemBean2.getItemList().size();
                int i8 = i7;
                int i9 = 0;
                while (true) {
                    if (i9 >= size2) {
                        list = listQueryLatestLifeCycle;
                        str = str3;
                        str2 = str4;
                        i = size;
                        i2 = i6;
                        menstruationLength = 0;
                        break;
                    }
                    List<Integer> list3 = lifeCycleItemBean2.getItemList().get(i9);
                    List<Integer> list4 = list3;
                    if (!(list4 == null || list4.isEmpty())) {
                        Integer startDay = (Integer) CollectionsKt.first((List) list3);
                        Intrinsics.checkExpressionValueIsNotNull(startDay, "startDay");
                        i3 = size2;
                        calendar.set(5, startDay.intValue());
                        String startDate = DateUtil.format(calendar, DateUtil.DATE_FORMAT_YMD);
                        Object objLast = CollectionsKt.last((List<? extends Object>) list3);
                        str2 = str4;
                        Intrinsics.checkExpressionValueIsNotNull(objLast, "item.last()");
                        calendar.set(5, ((Number) objLast).intValue());
                        String endDate = DateUtil.format(calendar, DateUtil.DATE_FORMAT_YMD);
                        str = str3;
                        int mensesCycle = lifeCycleItemBean2.getMensesCycle();
                        lifeCycleItemBean = lifeCycleItemBean2;
                        Intrinsics.checkExpressionValueIsNotNull(startDate, "startDate");
                        i2 = i6;
                        Intrinsics.checkExpressionValueIsNotNull(endDate, "endDate");
                        HistoryMenstruationItemBean historyMenstruationItemBean = new HistoryMenstruationItemBean(mensesCycle, startDate, endDate, MenstrualUtilKt.getDays(startDate, endDate));
                        arrayList.add(historyMenstruationItemBean);
                        i8++;
                        int i10 = 1;
                        if (startDay.intValue() == 1) {
                            int i11 = i2;
                            while (true) {
                                int i12 = i11 + i10;
                                if (i12 >= size) {
                                    list = listQueryLatestLifeCycle;
                                    i = size;
                                    menstruationLength = 0;
                                    break;
                                }
                                LifeCycleItemBean lifeCycleItemBean3 = listQueryLatestLifeCycle.get(i12);
                                List<List<Integer>> itemList3 = lifeCycleItemBean3.getItemList();
                                if (!(itemList3 == null || itemList3.isEmpty())) {
                                    List<List<Integer>> itemList4 = lifeCycleItemBean3.getItemList();
                                    list = listQueryLatestLifeCycle;
                                    Intrinsics.checkExpressionValueIsNotNull(itemList4, "previousItem.itemList");
                                    i4 = i12;
                                    CollectionsKt.sortWith(itemList4, new Comparator<List<? extends Integer>>() { // from class: com.ido.life.module.home.menstrualcycle.MenstrualCycleDetailPresenter.getHistoryMensRecord.2
                                        @Override // java.util.Comparator
                                        public /* bridge */ /* synthetic */ int compare(List<? extends Integer> list5, List<? extends Integer> list6) {
                                            return compare2((List<Integer>) list5, (List<Integer>) list6);
                                        }

                                        /* JADX INFO: renamed from: compare, reason: avoid collision after fix types in other method */
                                        public final int compare2(List<Integer> list5, List<Integer> list6) {
                                            return list5.get(0).intValue() > list6.get(0).intValue() ? -1 : 1;
                                        }
                                    });
                                    List<List<Integer>> itemList5 = lifeCycleItemBean3.getItemList();
                                    Intrinsics.checkExpressionValueIsNotNull(itemList5, "previousItem.itemList");
                                    ArrayList arrayList2 = new ArrayList();
                                    for (Object obj : itemList5) {
                                        int i13 = size;
                                        List list5 = (List) obj;
                                        if (!(list5 == null || list5.isEmpty())) {
                                            arrayList2.add(obj);
                                        }
                                        size = i13;
                                    }
                                    i = size;
                                    i5 = 1;
                                    calendar.set(5, startDay.intValue());
                                    calendar.add(5, -1);
                                    int i14 = calendar.get(5);
                                    List<List<Integer>> itemList6 = lifeCycleItemBean3.getItemList();
                                    Intrinsics.checkExpressionValueIsNotNull(itemList6, "previousItem.itemList");
                                    Object objFirst = CollectionsKt.first((List<? extends Object>) itemList6);
                                    Intrinsics.checkExpressionValueIsNotNull(objFirst, "previousItem.itemList.first()");
                                    Integer num = (Integer) CollectionsKt.last((List) objFirst);
                                    if (num != null && i14 == num.intValue() && Intrinsics.areEqual(lifeCycleItemBean3.getMonth(), DateUtil.format(calendar, DateUtil.DATE_FORMAT_YM_3))) {
                                        List<List<Integer>> itemList7 = lifeCycleItemBean3.getItemList();
                                        Intrinsics.checkExpressionValueIsNotNull(itemList7, "previousItem.itemList");
                                        Object obj2 = ((List) CollectionsKt.first((List) itemList7)).get(0);
                                        Intrinsics.checkExpressionValueIsNotNull(obj2, "previousItem.itemList.first()[0]");
                                        calendar.set(5, ((Number) obj2).intValue());
                                        String startDate2 = DateUtil.format(calendar, DateUtil.DATE_FORMAT_YMD);
                                        Intrinsics.checkExpressionValueIsNotNull(startDate2, "startDate");
                                        historyMenstruationItemBean.setStartDate(startDate2);
                                        historyMenstruationItemBean.setMenstruationLength(MenstrualUtilKt.getDays(startDate2, endDate));
                                        menstruationLength = 0;
                                        lifeCycleItemBean3.getItemList().remove(0);
                                        break;
                                    }
                                } else {
                                    list = listQueryLatestLifeCycle;
                                    i4 = i12;
                                    i = size;
                                    i5 = 1;
                                }
                                i10 = i5;
                                listQueryLatestLifeCycle = list;
                                size = i;
                                i11 = i4;
                            }
                        } else {
                            list = listQueryLatestLifeCycle;
                            i = size;
                            menstruationLength = 0;
                        }
                        if (i8 == this.MENS_HISTORY_SHOW_MAX_COUNT) {
                            break;
                        }
                    } else {
                        list = listQueryLatestLifeCycle;
                        str = str3;
                        str2 = str4;
                        i = size;
                        i2 = i6;
                        lifeCycleItemBean = lifeCycleItemBean2;
                        i3 = size2;
                    }
                    i9++;
                    size2 = i3;
                    str4 = str2;
                    str3 = str;
                    lifeCycleItemBean2 = lifeCycleItemBean;
                    i6 = i2;
                    listQueryLatestLifeCycle = list;
                    size = i;
                }
                if (i8 == this.MENS_HISTORY_SHOW_MAX_COUNT) {
                    break;
                }
                i7 = i8;
            }
            i6 = i2 + 1;
            str4 = str2;
            str3 = str;
            listQueryLatestLifeCycle = list;
            size = i;
        }
        if (!arrayList.isEmpty()) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                menstruationLength += ((HistoryMenstruationItemBean) it.next()).getMenstruationLength();
            }
            IMenstrualCycleDetailView view3 = getView();
            if (view3 != null) {
                view3.menstruationAvgLength(MathKt.roundToInt((menstruationLength * 1.0f) / arrayList.size()));
            }
        } else {
            RunTimeUtil runTimeUtil2 = RunTimeUtil.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(runTimeUtil2, str);
            MenstruationConfig menstruationConfigQueryMenstruationConfig = GreenDaoUtil.queryMenstruationConfig(runTimeUtil2.getUserId());
            IMenstrualCycleDetailView view4 = getView();
            if (view4 != null) {
                Intrinsics.checkExpressionValueIsNotNull(menstruationConfigQueryMenstruationConfig, str2);
                view4.menstruationAvgLength(menstruationConfigQueryMenstruationConfig.getMensLength());
            }
        }
        return arrayList;
    }

    private final String getKey(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1, year);
        calendar.set(2, month);
        String str = DateUtil.format(calendar, DateUtil.DATE_FORMAT_YM_3);
        Intrinsics.checkExpressionValueIsNotNull(str, "DateUtil.format(calendar…ateUtil.DATE_FORMAT_YM_3)");
        return str;
    }

    public final int getDataDownloadState(int year, int month) {
        if (GreenDaoUtil.queryLifeCycleItemBeanByDate(this.mUserId, getKey(year, month)) != null) {
            return 3;
        }
        List<DataDownLoadState> historyDataDownloadState = GreenDaoUtil.getHistoryDataDownloadState(this.mUserId, LifeCycleItemBean.class.getSimpleName());
        List<DataDownLoadState> list = historyDataDownloadState;
        boolean z = false;
        if (list == null || list.isEmpty()) {
            return 1;
        }
        Iterator<DataDownLoadState> it = historyDataDownloadState.iterator();
        boolean z2 = false;
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            DataDownLoadState dataDownLoadState = it.next();
            Intrinsics.checkExpressionValueIsNotNull(dataDownLoadState, "dataDownLoadState");
            if (dataDownLoadState.getDownloadState() == 4) {
                z = true;
                break;
            }
            if (dataDownLoadState.getDownloadState() == 1 || dataDownLoadState.getDownloadState() == 2) {
                z2 = true;
            }
        }
        if (z) {
            return 4;
        }
        return z2 ? 2 : 3;
    }

    /* JADX INFO: compiled from: MenstrualCycleDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003¢\u0006\u0002\u0010\bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J1\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001e\u001a\u00020\u0005HÖ\u0001R\u001a\u0010\u0006\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0007\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u000e\"\u0004\b\u0012\u0010\u0010R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\n\"\u0004\b\u0014\u0010\f¨\u0006\u001f"}, d2 = {"Lcom/ido/life/module/home/menstrualcycle/MenstrualCycleDetailPresenter$HistoryMenstruationItemBean;", "", "menstruationCycle", "", "startDate", "", "endDate", "menstruationLength", "(ILjava/lang/String;Ljava/lang/String;I)V", "getEndDate", "()Ljava/lang/String;", "setEndDate", "(Ljava/lang/String;)V", "getMenstruationCycle", "()I", "setMenstruationCycle", "(I)V", "getMenstruationLength", "setMenstruationLength", "getStartDate", "setStartDate", "component1", "component2", "component3", "component4", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final /* data */ class HistoryMenstruationItemBean {
        private String endDate;
        private int menstruationCycle;
        private int menstruationLength;
        private String startDate;

        public HistoryMenstruationItemBean() {
            this(0, null, null, 0, 15, null);
        }

        public static /* synthetic */ HistoryMenstruationItemBean copy$default(HistoryMenstruationItemBean historyMenstruationItemBean, int i, String str, String str2, int i2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                i = historyMenstruationItemBean.menstruationCycle;
            }
            if ((i3 & 2) != 0) {
                str = historyMenstruationItemBean.startDate;
            }
            if ((i3 & 4) != 0) {
                str2 = historyMenstruationItemBean.endDate;
            }
            if ((i3 & 8) != 0) {
                i2 = historyMenstruationItemBean.menstruationLength;
            }
            return historyMenstruationItemBean.copy(i, str, str2, i2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getMenstruationCycle() {
            return this.menstruationCycle;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getStartDate() {
            return this.startDate;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getEndDate() {
            return this.endDate;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final int getMenstruationLength() {
            return this.menstruationLength;
        }

        public final HistoryMenstruationItemBean copy(int menstruationCycle, String startDate, String endDate, int menstruationLength) {
            Intrinsics.checkParameterIsNotNull(startDate, "startDate");
            Intrinsics.checkParameterIsNotNull(endDate, "endDate");
            return new HistoryMenstruationItemBean(menstruationCycle, startDate, endDate, menstruationLength);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof HistoryMenstruationItemBean)) {
                return false;
            }
            HistoryMenstruationItemBean historyMenstruationItemBean = (HistoryMenstruationItemBean) other;
            return this.menstruationCycle == historyMenstruationItemBean.menstruationCycle && Intrinsics.areEqual(this.startDate, historyMenstruationItemBean.startDate) && Intrinsics.areEqual(this.endDate, historyMenstruationItemBean.endDate) && this.menstruationLength == historyMenstruationItemBean.menstruationLength;
        }

        public int hashCode() {
            int iHashCode = Integer.valueOf(this.menstruationCycle).hashCode() * 31;
            String str = this.startDate;
            int iHashCode2 = (iHashCode + (str != null ? str.hashCode() : 0)) * 31;
            String str2 = this.endDate;
            return ((iHashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31) + Integer.valueOf(this.menstruationLength).hashCode();
        }

        public String toString() {
            return "HistoryMenstruationItemBean(menstruationCycle=" + this.menstruationCycle + ", startDate=" + this.startDate + ", endDate=" + this.endDate + ", menstruationLength=" + this.menstruationLength + ")";
        }

        public HistoryMenstruationItemBean(int i, String startDate, String endDate, int i2) {
            Intrinsics.checkParameterIsNotNull(startDate, "startDate");
            Intrinsics.checkParameterIsNotNull(endDate, "endDate");
            this.menstruationCycle = i;
            this.startDate = startDate;
            this.endDate = endDate;
            this.menstruationLength = i2;
        }

        public final int getMenstruationCycle() {
            return this.menstruationCycle;
        }

        public final String getStartDate() {
            return this.startDate;
        }

        public final void setMenstruationCycle(int i) {
            this.menstruationCycle = i;
        }

        public final void setStartDate(String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            this.startDate = str;
        }

        public /* synthetic */ HistoryMenstruationItemBean(int i, String str, String str2, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this((i3 & 1) != 0 ? 0 : i, (i3 & 2) != 0 ? "" : str, (i3 & 4) != 0 ? "" : str2, (i3 & 8) != 0 ? 0 : i2);
        }

        public final String getEndDate() {
            return this.endDate;
        }

        public final int getMenstruationLength() {
            return this.menstruationLength;
        }

        public final void setEndDate(String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            this.endDate = str;
        }

        public final void setMenstruationLength(int i) {
            this.menstruationLength = i;
        }
    }

    /* JADX INFO: compiled from: MenstrualCycleDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b0\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001Bs\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\r\u001a\u00020\u0003¢\u0006\u0002\u0010\u000eJ\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0003HÆ\u0003J\t\u0010)\u001a\u00020\u0003HÆ\u0003J\t\u0010*\u001a\u00020\u0003HÆ\u0003J\t\u0010+\u001a\u00020\u0003HÆ\u0003J\t\u0010,\u001a\u00020\u0003HÆ\u0003J\t\u0010-\u001a\u00020\u0003HÆ\u0003J\t\u0010.\u001a\u00020\u0003HÆ\u0003J\t\u0010/\u001a\u00020\u0003HÆ\u0003J\t\u00100\u001a\u00020\u0003HÆ\u0003J\t\u00101\u001a\u00020\u0003HÆ\u0003Jw\u00102\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u0003HÆ\u0001J\u0013\u00103\u001a\u0002042\b\u00105\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00106\u001a\u00020\u0003HÖ\u0001J\u0006\u00107\u001a\u000208J\t\u00109\u001a\u00020:HÖ\u0001R\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0010\"\u0004\b\u0014\u0010\u0012R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0010\"\u0004\b\u0016\u0010\u0012R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0010\"\u0004\b\u0018\u0010\u0012R\u001a\u0010\f\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0010\"\u0004\b\u001a\u0010\u0012R\u001a\u0010\u000b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0010\"\u0004\b\u001c\u0010\u0012R\u001a\u0010\n\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0010\"\u0004\b\u001e\u0010\u0012R\u001a\u0010\r\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0010\"\u0004\b \u0010\u0012R\u001a\u0010\t\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0010\"\u0004\b\"\u0010\u0012R\u001a\u0010\b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0010\"\u0004\b$\u0010\u0012R\u001a\u0010\u0007\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0010\"\u0004\b&\u0010\u0012¨\u0006;"}, d2 = {"Lcom/ido/life/module/home/menstrualcycle/MenstrualCycleDetailPresenter$MensInfo;", "", "cycleStartYear", "", "cycleStartMonth", "cycleEndYear", "cycleEndMonth", "mensStartYear", "mensStartMonth", "mensStartDay", "mensEndYear", "mensEndMonth", "mensEndDay", "mensLength", "(IIIIIIIIIII)V", "getCycleEndMonth", "()I", "setCycleEndMonth", "(I)V", "getCycleEndYear", "setCycleEndYear", "getCycleStartMonth", "setCycleStartMonth", "getCycleStartYear", "setCycleStartYear", "getMensEndDay", "setMensEndDay", "getMensEndMonth", "setMensEndMonth", "getMensEndYear", "setMensEndYear", "getMensLength", "setMensLength", "getMensStartDay", "setMensStartDay", "getMensStartMonth", "setMensStartMonth", "getMensStartYear", "setMensStartYear", "component1", "component10", "component11", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "reset", "", "toString", "", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final /* data */ class MensInfo {
        private int cycleEndMonth;
        private int cycleEndYear;
        private int cycleStartMonth;
        private int cycleStartYear;
        private int mensEndDay;
        private int mensEndMonth;
        private int mensEndYear;
        private int mensLength;
        private int mensStartDay;
        private int mensStartMonth;
        private int mensStartYear;

        public MensInfo() {
            this(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2047, null);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getCycleStartYear() {
            return this.cycleStartYear;
        }

        /* JADX INFO: renamed from: component10, reason: from getter */
        public final int getMensEndDay() {
            return this.mensEndDay;
        }

        /* JADX INFO: renamed from: component11, reason: from getter */
        public final int getMensLength() {
            return this.mensLength;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getCycleStartMonth() {
            return this.cycleStartMonth;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final int getCycleEndYear() {
            return this.cycleEndYear;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final int getCycleEndMonth() {
            return this.cycleEndMonth;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final int getMensStartYear() {
            return this.mensStartYear;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final int getMensStartMonth() {
            return this.mensStartMonth;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final int getMensStartDay() {
            return this.mensStartDay;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final int getMensEndYear() {
            return this.mensEndYear;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final int getMensEndMonth() {
            return this.mensEndMonth;
        }

        public final MensInfo copy(int cycleStartYear, int cycleStartMonth, int cycleEndYear, int cycleEndMonth, int mensStartYear, int mensStartMonth, int mensStartDay, int mensEndYear, int mensEndMonth, int mensEndDay, int mensLength) {
            return new MensInfo(cycleStartYear, cycleStartMonth, cycleEndYear, cycleEndMonth, mensStartYear, mensStartMonth, mensStartDay, mensEndYear, mensEndMonth, mensEndDay, mensLength);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof MensInfo)) {
                return false;
            }
            MensInfo mensInfo = (MensInfo) other;
            return this.cycleStartYear == mensInfo.cycleStartYear && this.cycleStartMonth == mensInfo.cycleStartMonth && this.cycleEndYear == mensInfo.cycleEndYear && this.cycleEndMonth == mensInfo.cycleEndMonth && this.mensStartYear == mensInfo.mensStartYear && this.mensStartMonth == mensInfo.mensStartMonth && this.mensStartDay == mensInfo.mensStartDay && this.mensEndYear == mensInfo.mensEndYear && this.mensEndMonth == mensInfo.mensEndMonth && this.mensEndDay == mensInfo.mensEndDay && this.mensLength == mensInfo.mensLength;
        }

        public int hashCode() {
            return (((((((((((((((((((Integer.valueOf(this.cycleStartYear).hashCode() * 31) + Integer.valueOf(this.cycleStartMonth).hashCode()) * 31) + Integer.valueOf(this.cycleEndYear).hashCode()) * 31) + Integer.valueOf(this.cycleEndMonth).hashCode()) * 31) + Integer.valueOf(this.mensStartYear).hashCode()) * 31) + Integer.valueOf(this.mensStartMonth).hashCode()) * 31) + Integer.valueOf(this.mensStartDay).hashCode()) * 31) + Integer.valueOf(this.mensEndYear).hashCode()) * 31) + Integer.valueOf(this.mensEndMonth).hashCode()) * 31) + Integer.valueOf(this.mensEndDay).hashCode()) * 31) + Integer.valueOf(this.mensLength).hashCode();
        }

        public String toString() {
            return "MensInfo(cycleStartYear=" + this.cycleStartYear + ", cycleStartMonth=" + this.cycleStartMonth + ", cycleEndYear=" + this.cycleEndYear + ", cycleEndMonth=" + this.cycleEndMonth + ", mensStartYear=" + this.mensStartYear + ", mensStartMonth=" + this.mensStartMonth + ", mensStartDay=" + this.mensStartDay + ", mensEndYear=" + this.mensEndYear + ", mensEndMonth=" + this.mensEndMonth + ", mensEndDay=" + this.mensEndDay + ", mensLength=" + this.mensLength + ")";
        }

        public MensInfo(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11) {
            this.cycleStartYear = i;
            this.cycleStartMonth = i2;
            this.cycleEndYear = i3;
            this.cycleEndMonth = i4;
            this.mensStartYear = i5;
            this.mensStartMonth = i6;
            this.mensStartDay = i7;
            this.mensEndYear = i8;
            this.mensEndMonth = i9;
            this.mensEndDay = i10;
            this.mensLength = i11;
        }

        public final int getCycleStartYear() {
            return this.cycleStartYear;
        }

        public final void setCycleStartYear(int i) {
            this.cycleStartYear = i;
        }

        public final int getCycleStartMonth() {
            return this.cycleStartMonth;
        }

        public final void setCycleStartMonth(int i) {
            this.cycleStartMonth = i;
        }

        public final int getCycleEndYear() {
            return this.cycleEndYear;
        }

        public final void setCycleEndYear(int i) {
            this.cycleEndYear = i;
        }

        public final int getCycleEndMonth() {
            return this.cycleEndMonth;
        }

        public final void setCycleEndMonth(int i) {
            this.cycleEndMonth = i;
        }

        public final int getMensStartYear() {
            return this.mensStartYear;
        }

        public final void setMensStartYear(int i) {
            this.mensStartYear = i;
        }

        public final int getMensStartMonth() {
            return this.mensStartMonth;
        }

        public final void setMensStartMonth(int i) {
            this.mensStartMonth = i;
        }

        public final int getMensStartDay() {
            return this.mensStartDay;
        }

        public final void setMensStartDay(int i) {
            this.mensStartDay = i;
        }

        public final int getMensEndYear() {
            return this.mensEndYear;
        }

        public final void setMensEndYear(int i) {
            this.mensEndYear = i;
        }

        public final int getMensEndMonth() {
            return this.mensEndMonth;
        }

        public final void setMensEndMonth(int i) {
            this.mensEndMonth = i;
        }

        public final int getMensEndDay() {
            return this.mensEndDay;
        }

        public final void setMensEndDay(int i) {
            this.mensEndDay = i;
        }

        public /* synthetic */ MensInfo(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, DefaultConstructorMarker defaultConstructorMarker) {
            this((i12 & 1) != 0 ? 0 : i, (i12 & 2) != 0 ? 0 : i2, (i12 & 4) != 0 ? 0 : i3, (i12 & 8) != 0 ? 0 : i4, (i12 & 16) != 0 ? 0 : i5, (i12 & 32) != 0 ? 0 : i6, (i12 & 64) != 0 ? 0 : i7, (i12 & 128) != 0 ? 0 : i8, (i12 & 256) != 0 ? 0 : i9, (i12 & 512) != 0 ? 0 : i10, (i12 & 1024) == 0 ? i11 : 0);
        }

        public final int getMensLength() {
            return this.mensLength;
        }

        public final void setMensLength(int i) {
            this.mensLength = i;
        }

        public final void reset() {
            this.cycleStartYear = 0;
            this.cycleStartMonth = 0;
            this.cycleEndYear = 0;
            this.cycleEndMonth = 0;
            this.mensStartYear = 0;
            this.mensStartMonth = 0;
            this.mensStartDay = 0;
            this.mensEndYear = 0;
            this.mensEndMonth = 0;
            this.mensEndDay = 0;
        }
    }

    @Override // com.ido.ble.callback.SettingCallBack.ICallBack
    public void onSuccess(SettingCallBack.SettingType settingType, Object p1) {
        IMenstrualCycleDetailView view;
        if (settingType == SettingCallBack.SettingType.MENSTRUATION_HISTORICAL_DATA) {
            HomeHelperKt.printAndSave("经期历史数据同步到固件成功", this.TAG);
            int i = this.SUCCESS;
            this.mSyncHistoryState = i;
            int i2 = this.mSyncMensConfigState;
            if (i2 == this.FAILED) {
                IMenstrualCycleDetailView view2 = getView();
                if (view2 != null) {
                    view2.syncToDeviceFailed();
                    return;
                }
                return;
            }
            if (i2 != i || (view = getView()) == null) {
                return;
            }
            view.syncToDeviceSuccess();
        }
    }

    @Override // com.ido.ble.callback.SettingCallBack.ICallBack
    public void onFailed(SettingCallBack.SettingType settingType) {
        IMenstrualCycleDetailView view;
        if (settingType == SettingCallBack.SettingType.MENSTRUATION_HISTORICAL_DATA) {
            HomeHelperKt.printAndSave("经期历史数据同步到固件失败", this.TAG);
            this.mSyncHistoryState = this.FAILED;
            if (this.mSyncMensConfigState == this.SYNCING || (view = getView()) == null) {
                return;
            }
            view.syncToDeviceFailed();
        }
    }

    @Override // com.ido.life.syncdownload.ITaskExecutedCallBack
    public void onTaskComplete() {
        HomeHelperKt.printAndSave("所有生理周期数据加载完成", this.TAG);
        if (this.mDataDownloadState == 3) {
            IMenstrualCycleDetailView view = getView();
            if (view != null) {
                view.dataLoadSuccess();
            }
            IMenstrualCycleDetailView view2 = getView();
            if (view2 != null) {
                view2.updateDeviceConnectState(BLEManager.isConnected());
            }
        } else {
            IMenstrualCycleDetailView view3 = getView();
            if (view3 != null) {
                view3.dataLoadFailed();
            }
        }
        getPageData();
    }

    @Override // com.ido.life.syncdownload.ITaskExecutedCallBack
    public void onTaskExecutedFailed(NewTask.NewTaskInfo taskInfo, String error) {
        Intrinsics.checkParameterIsNotNull(taskInfo, "taskInfo");
        HomeHelperKt.printAndSave("生理周期数据下拉失败", this.TAG);
        this.mDataDownloadState = 4;
        IMenstrualCycleDetailView view = getView();
        if (view != null) {
            view.dataLoadFailed();
        }
        getPageData();
    }

    @Override // com.ido.life.syncdownload.ITaskExecutedCallBack
    public void onTaskExecutedSuccess(NewTask.NewTaskInfo taskInfo) {
        Intrinsics.checkParameterIsNotNull(taskInfo, "taskInfo");
        HomeHelperKt.printAndSave("生理周期数据下拉成功", this.TAG);
        getPageData();
    }

    public final void retryLoadData() {
        List<Long> listQueryUnSuccessTaskId = queryUnSuccessTaskId();
        if (this.mFocusTaskList.isEmpty()) {
            this.mDataDownloadState = 3;
            IMenstrualCycleDetailView view = getView();
            if (view != null) {
                view.dataLoadSuccess();
            }
            getPageData();
            return;
        }
        DataDownLoadService.INSTANCE.addTaskExecutedCallback(this);
        List<Long> list = listQueryUnSuccessTaskId;
        if (!(list == null || list.isEmpty())) {
            if (NetworkUtil.isConnected(IdoApp.getAppContext())) {
                HomeHelperKt.printAndSave("进入生理周期详情页面，发现有加载失败的任务并且此时有网络，就主动从网络重新加载失败了的任务", this.TAG);
                IMenstrualCycleDetailView view2 = getView();
                if (view2 != null) {
                    view2.dataLoading();
                }
                this.mDataDownloadState = 2;
                DataDownLoadService.INSTANCE.requestPullData(listQueryUnSuccessTaskId);
                return;
            }
            HomeHelperKt.printAndSave("进入生理周期详情页面，发现有加载失败了的任务，但是此时没有网络，就主动从网络重新加载数据。", this.TAG);
            this.mDataDownloadState = 4;
            IMenstrualCycleDetailView view3 = getView();
            if (view3 != null) {
                view3.dataLoadFailed();
                return;
            }
            return;
        }
        this.mDataDownloadState = 2;
        IMenstrualCycleDetailView view4 = getView();
        if (view4 != null) {
            view4.dataLoading();
        }
    }

    @Override // com.ido.life.syncdownload.ITaskExecutedCallBack
    public List<Long> getFocusTaskList() {
        return this.mFocusTaskList;
    }

    @Override // com.ido.ble.callback.OtherProtocolCallBack.ICallBack
    public void onSuccess(OtherProtocolCallBack.SettingType settingType) {
        IMenstrualCycleDetailView view;
        if (settingType == OtherProtocolCallBack.SettingType.MENSTRUAL) {
            HomeHelperKt.printAndSave("老设备生理周期数据下发成功", this.TAG);
            this.mSyncMensConfigState = this.SUCCESS;
            BLEManager.unregisterOtherProtocolCallBack(this);
            int i = this.mSyncHistoryState;
            if (i == this.FAILED) {
                IMenstrualCycleDetailView view2 = getView();
                if (view2 != null) {
                    view2.syncToDeviceFailed();
                    return;
                }
                return;
            }
            if (i != this.SUCCESS || (view = getView()) == null) {
                return;
            }
            view.syncToDeviceSuccess();
        }
    }

    @Override // com.ido.ble.callback.OtherProtocolCallBack.ICallBack
    public void onFailed(OtherProtocolCallBack.SettingType settingType) {
        IMenstrualCycleDetailView view;
        if (settingType == OtherProtocolCallBack.SettingType.MENSTRUAL) {
            HomeHelperKt.printAndSave("老设备生理周期数据下拉失败", this.TAG);
            this.mSyncMensConfigState = this.FAILED;
            BLEManager.unregisterOtherProtocolCallBack(this);
            if (this.mSyncHistoryState == this.SYNCING || (view = getView()) == null) {
                return;
            }
            view.syncToDeviceFailed();
        }
    }

    public final void processBusMessage(BaseMessage<?> message) {
        Integer numValueOf = message != null ? Integer.valueOf(message.getType()) : null;
        if (numValueOf != null && numValueOf.intValue() == 101) {
            HomeHelperKt.printAndSave("收到设备连接成功通知", this.TAG);
            IMenstrualCycleDetailView view = getView();
            if (view != null) {
                view.updateDeviceConnectState(BLEManager.isConnected());
                return;
            }
            return;
        }
        if ((numValueOf != null && numValueOf.intValue() == 102) || ((numValueOf != null && numValueOf.intValue() == -100) || (numValueOf != null && numValueOf.intValue() == -101))) {
            HomeHelperKt.printAndSave("收到设备断开连接通知", this.TAG);
            IMenstrualCycleDetailView view2 = getView();
            if (view2 != null) {
                view2.updateDeviceConnectState(BLEManager.isConnected());
            }
        }
    }
}