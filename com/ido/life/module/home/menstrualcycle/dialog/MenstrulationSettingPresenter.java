package com.ido.life.module.home.menstrualcycle.dialog;

import android.text.TextUtils;
import com.amazon.identity.auth.device.authorization.AuthorizationResponseParser;
import com.boat.Xtend.two.R;
import com.google.android.gms.fitness.FitnessActivities;
import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.protocol.model.MenstrualRemind;
import com.ido.common.IdoApp;
import com.ido.common.net.BaseEntityNew;
import com.ido.common.utils.GsonUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.NetworkUtil;
import com.ido.life.base.BasePresenter;
import com.ido.life.bean.ServerBean;
import com.ido.life.customview.MenstruationCalendar;
import com.ido.life.data.health.remote.HealthManager;
import com.ido.life.database.model.DataDownLoadState;
import com.ido.life.database.model.LifeCycleItemBean;
import com.ido.life.database.model.MenstruationConfig;
import com.ido.life.module.home.HomeHelperKt;
import com.ido.life.module.home.menstrualcycle.MenstrualUtilKt;
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
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeSet;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MenstrulationSettingPresenter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010%\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 N2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0002NOB\u0005¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u0006J\u0016\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u0006J4\u0010\u001d\u001a\u00020\u00192\u0014\u0010\u001e\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u001f\u0018\u00010\u001f2\u0014\u0010 \u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u001f\u0018\u00010\u001fH\u0002J\u0006\u0010!\u001a\u00020\u0019J,\u0010\"\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010#2\u0006\u0010$\u001a\u00020\b2\u0006\u0010%\u001a\u00020\b2\u0006\u0010&\u001a\u00020\bJ,\u0010'\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010#2\u0006\u0010$\u001a\u00020\b2\u0006\u0010%\u001a\u00020\b2\u0006\u0010&\u001a\u00020\bJ\u0016\u0010(\u001a\u00020\b2\u0006\u0010$\u001a\u00020\b2\u0006\u0010%\u001a\u00020\bJ\u000e\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00170\u0012H\u0002J\u0016\u0010*\u001a\u00020\b2\u0006\u0010$\u001a\u00020\b2\u0006\u0010%\u001a\u00020\bJ\u0018\u0010+\u001a\u00020\u00062\u0006\u0010$\u001a\u00020\b2\u0006\u0010%\u001a\u00020\bH\u0002J\u001c\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u0010$\u001a\u00020\b2\u0006\u0010%\u001a\u00020\bJ\u0014\u0010-\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060#H\u0002J\u000e\u0010.\u001a\u00020\u00062\u0006\u0010/\u001a\u00020\u0015J\u000e\u00100\u001a\b\u0012\u0004\u0012\u00020\u00150\u0012H\u0016J\u000e\u00101\u001a\u00020\u00062\u0006\u0010/\u001a\u00020\u0015J\u000e\u00102\u001a\u00020\b2\u0006\u00103\u001a\u00020\u0006J\u000e\u00104\u001a\u00020\u00062\u0006\u00103\u001a\u00020\u0006J\u0010\u00105\u001a\u00020\u00062\u0006\u00103\u001a\u00020\u0006H\u0002J\b\u00106\u001a\u000207H\u0002J\u0006\u00108\u001a\u00020\bJ\f\u00109\u001a\b\u0012\u0004\u0012\u00020\u00170\u0012J\f\u0010:\u001a\b\u0012\u0004\u0012\u00020\u00060\u0012J\u0006\u0010;\u001a\u00020\bJ\b\u0010<\u001a\u00020=H\u0016J\u001a\u0010>\u001a\u00020=2\u0006\u0010?\u001a\u00020@2\b\u0010A\u001a\u0004\u0018\u00010\u0006H\u0016J\u0010\u0010B\u001a\u00020=2\u0006\u0010?\u001a\u00020@H\u0016J\u0010\u0010C\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u001fH\u0002J\u0006\u0010D\u001a\u00020=J\u0006\u0010E\u001a\u00020=J\u001c\u0010F\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u0006J\b\u0010G\u001a\u00020=H\u0002J&\u0010H\u001a\u00020=2\u0006\u0010$\u001a\u00020\b2\u0006\u0010%\u001a\u00020\b2\f\u0010I\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H\u0002J\u0016\u0010J\u001a\u00020=2\f\u0010K\u001a\b\u0012\u0004\u0012\u00020L0\u0012H\u0002J\u0016\u0010M\u001a\u00020=2\f\u0010K\u001a\b\u0012\u0004\u0012\u00020L0\u0012H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0010\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u0012X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006P"}, d2 = {"Lcom/ido/life/module/home/menstrualcycle/dialog/MenstrulationSettingPresenter;", "Lcom/ido/life/base/BasePresenter;", "Lcom/ido/life/module/home/menstrualcycle/dialog/IMenstrulationSettingView;", "Lcom/ido/life/syncdownload/ITaskExecutedCallBack;", "()V", "TAG", "", "mCurrentCalendarPosition", "", "getMCurrentCalendarPosition", "()I", "setMCurrentCalendarPosition", "(I)V", "mCurrentDay", "mCurrentMonth", "mCurrentYear", "mDateMap", "", "", "Lcom/ido/life/customview/MenstruationCalendar$DateInfo;", "mFocusTaskList", "", "mSettingList", "Lcom/ido/life/module/home/menstrualcycle/dialog/MenstrulationSettingPresenter$SettingBean;", "checkHasCancledDate", "", "startDate", "endDate", "checkHasDateDay", "compareListEqual", "previousList", "", "nextList", "dataHasChanged", "findAfterMensFirstStartDate", "Lkotlin/Pair;", "year", "month", "startIndex", "findPastMensFirstEndDate", "findPositionInSettingList", "generateSettingList", "getDataDownloadState", "getDateKey", "getDateList", "getDeviceInfo", "getFirstDay", "timeStamp", "getFocusTaskList", "getLastDay", "getMensDayCount", "date", "getMensEndDate", "getMensStartDate", "getMenstrualRemind", "Lcom/ido/ble/protocol/model/MenstrualRemind;", "getMenstrulationLength", "getSettingList", "getWeekList", "getWeekStart", "onTaskComplete", "", "onTaskExecutedFailed", "taskInfo", "Lcom/ido/life/syncdownload/NewTask$NewTaskInfo;", AuthorizationResponseParser.ERROR, "onTaskExecutedSuccess", "queryUnSuccessTaskId", "retryLoadData", "saveMenstruation", "setMenstruationDate", "startLoadData", "updateDataState", "list", "uploadMainUserLifeCycle", "lifecycleList", "Lcom/ido/life/database/model/LifeCycleItemBean;", "uploadToServer", "Companion", "SettingBean", "app_release"}, k = 1, mv = {1, 1, 16})
public final class MenstrulationSettingPresenter extends BasePresenter<IMenstrulationSettingView> implements ITaskExecutedCallBack {
    private final int mCurrentDay;
    private final int mCurrentMonth;
    private final int mCurrentYear;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int SETTING_MAX_VALUE = 14;
    private static final int AUTO_LINK_MAX_DAY = 5;
    private final String TAG = "MenstrulationSettingPresenter";
    private final List<Long> mFocusTaskList = new ArrayList();
    private final List<SettingBean> mSettingList = new ArrayList();
    private int mCurrentCalendarPosition = -1;
    private final Map<String, List<MenstruationCalendar.DateInfo>> mDateMap = new LinkedHashMap();

    public static final int getAUTO_LINK_MAX_DAY() {
        Companion companion = INSTANCE;
        return AUTO_LINK_MAX_DAY;
    }

    public static final int getSETTING_MAX_VALUE() {
        Companion companion = INSTANCE;
        return SETTING_MAX_VALUE;
    }

    public final int getMenstrulationLength() {
        return 8;
    }

    public MenstrulationSettingPresenter() {
        Calendar calendar = Calendar.getInstance();
        this.mCurrentYear = calendar.get(1);
        this.mCurrentMonth = calendar.get(2);
        this.mCurrentDay = calendar.get(5);
        startLoadData();
    }

    public static final /* synthetic */ IMenstrulationSettingView access$getView(MenstrulationSettingPresenter menstrulationSettingPresenter) {
        return menstrulationSettingPresenter.getView();
    }

    /* JADX INFO: compiled from: MenstrulationSettingPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u00020\u00048\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007R\u001c\u0010\b\u001a\u00020\u00048\u0006X\u0087D¢\u0006\u000e\n\u0000\u0012\u0004\b\t\u0010\u0002\u001a\u0004\b\n\u0010\u0007¨\u0006\u000b"}, d2 = {"Lcom/ido/life/module/home/menstrualcycle/dialog/MenstrulationSettingPresenter$Companion;", "", "()V", "AUTO_LINK_MAX_DAY", "", "AUTO_LINK_MAX_DAY$annotations", "getAUTO_LINK_MAX_DAY", "()I", "SETTING_MAX_VALUE", "SETTING_MAX_VALUE$annotations", "getSETTING_MAX_VALUE", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        @JvmStatic
        public static /* synthetic */ void AUTO_LINK_MAX_DAY$annotations() {
        }

        @JvmStatic
        public static /* synthetic */ void SETTING_MAX_VALUE$annotations() {
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final int getSETTING_MAX_VALUE() {
            return MenstrulationSettingPresenter.SETTING_MAX_VALUE;
        }

        public final int getAUTO_LINK_MAX_DAY() {
            return MenstrulationSettingPresenter.AUTO_LINK_MAX_DAY;
        }
    }

    public final int getMCurrentCalendarPosition() {
        return this.mCurrentCalendarPosition;
    }

    public final void setMCurrentCalendarPosition(int i) {
        this.mCurrentCalendarPosition = i;
    }

    public final List<SettingBean> getSettingList() {
        if (this.mSettingList.isEmpty()) {
            this.mSettingList.addAll(generateSettingList());
        }
        return this.mSettingList;
    }

    private final List<SettingBean> generateSettingList() {
        ArrayList arrayList = new ArrayList();
        Calendar startCalendar = Calendar.getInstance(Locale.CHINA);
        int i = startCalendar.get(1);
        int i2 = startCalendar.get(2);
        Calendar endCalendar = Calendar.getInstance(Locale.CHINA);
        Intrinsics.checkExpressionValueIsNotNull(startCalendar, "startCalendar");
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        startCalendar.setTime(DateUtil.string2Date(MenstrualUtilKt.getMensSettingStartDate(runTimeUtil.getUserId()), DateUtil.DATE_FORMAT_YMD));
        Intrinsics.checkExpressionValueIsNotNull(endCalendar, "endCalendar");
        RunTimeUtil runTimeUtil2 = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil2, "RunTimeUtil.getInstance()");
        endCalendar.setTime(DateUtil.string2Date(MenstrualUtilKt.getMensSettingEndDate(runTimeUtil2.getUserId()), DateUtil.DATE_FORMAT_YMD));
        int i3 = endCalendar.get(1);
        int i4 = endCalendar.get(2);
        while (true) {
            int i5 = startCalendar.get(1);
            int i6 = startCalendar.get(2);
            arrayList.add(new SettingBean(i5, i6));
            if (i5 == i && i6 == i2) {
                this.mCurrentCalendarPosition = arrayList.size() - 1;
            }
            if (i5 == i3 && i6 == i4) {
                return arrayList;
            }
            startCalendar.add(2, 1);
        }
    }

    public final List<String> getWeekList() {
        ArrayList arrayList = new ArrayList();
        int weekStart = getWeekStart();
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.set(7, weekStart);
        for (int i = 0; i < 7; i++) {
            switch (calendar.get(7)) {
                case 2:
                    String languageText = LanguageUtil.getLanguageText(R.string.device_week_mon_android);
                    Intrinsics.checkExpressionValueIsNotNull(languageText, "LanguageUtil.getLanguage….device_week_mon_android)");
                    arrayList.add(languageText);
                    break;
                case 3:
                    String languageText2 = LanguageUtil.getLanguageText(R.string.device_week_tue_android);
                    Intrinsics.checkExpressionValueIsNotNull(languageText2, "LanguageUtil.getLanguage….device_week_tue_android)");
                    arrayList.add(languageText2);
                    break;
                case 4:
                    String languageText3 = LanguageUtil.getLanguageText(R.string.device_week_wed_android);
                    Intrinsics.checkExpressionValueIsNotNull(languageText3, "LanguageUtil.getLanguage….device_week_wed_android)");
                    arrayList.add(languageText3);
                    break;
                case 5:
                    String languageText4 = LanguageUtil.getLanguageText(R.string.device_week_thu_android);
                    Intrinsics.checkExpressionValueIsNotNull(languageText4, "LanguageUtil.getLanguage….device_week_thu_android)");
                    arrayList.add(languageText4);
                    break;
                case 6:
                    String languageText5 = LanguageUtil.getLanguageText(R.string.device_week_fri_android);
                    Intrinsics.checkExpressionValueIsNotNull(languageText5, "LanguageUtil.getLanguage….device_week_fri_android)");
                    arrayList.add(languageText5);
                    break;
                case 7:
                    String languageText6 = LanguageUtil.getLanguageText(R.string.device_week_sat_android);
                    Intrinsics.checkExpressionValueIsNotNull(languageText6, "LanguageUtil.getLanguage….device_week_sat_android)");
                    arrayList.add(languageText6);
                    break;
                default:
                    String languageText7 = LanguageUtil.getLanguageText(R.string.device_week_sun_android);
                    Intrinsics.checkExpressionValueIsNotNull(languageText7, "LanguageUtil.getLanguage….device_week_sun_android)");
                    arrayList.add(languageText7);
                    break;
            }
            calendar.add(5, 1);
        }
        return arrayList;
    }

    public final List<MenstruationCalendar.DateInfo> getDateList(int year, int month) {
        String dateKey = getDateKey(year, month);
        List<MenstruationCalendar.DateInfo> list = this.mDateMap.get(dateKey);
        List<MenstruationCalendar.DateInfo> list2 = list;
        if (!(list2 == null || list2.isEmpty())) {
            return list;
        }
        List<MenstruationCalendar.DateInfo> listGenerateCalendarData = MenstrualUtilKt.generateCalendarData(year, month, false, getWeekStart());
        updateDataState(year, month, listGenerateCalendarData);
        this.mDateMap.put(dateKey, listGenerateCalendarData);
        return listGenerateCalendarData;
    }

    public final boolean checkHasDateDay(String startDate, String endDate) {
        Intrinsics.checkParameterIsNotNull(startDate, "startDate");
        Intrinsics.checkParameterIsNotNull(endDate, "endDate");
        Calendar startCalendar = Calendar.getInstance(Locale.CHINA);
        Calendar endCalendar = Calendar.getInstance(Locale.CHINA);
        Intrinsics.checkExpressionValueIsNotNull(startCalendar, "startCalendar");
        startCalendar.setTime(DateUtil.string2Date(startDate, DateUtil.DATE_FORMAT_YMD));
        Intrinsics.checkExpressionValueIsNotNull(endCalendar, "endCalendar");
        endCalendar.setTime(DateUtil.string2Date(endDate, DateUtil.DATE_FORMAT_YMD));
        Date time = endCalendar.getTime();
        Intrinsics.checkExpressionValueIsNotNull(time, "endCalendar.time");
        long time2 = time.getTime();
        Date time3 = startCalendar.getTime();
        Intrinsics.checkExpressionValueIsNotNull(time3, "startCalendar.time");
        return time2 - time3.getTime() < ((long) 86400000);
    }

    public final int getDataDownloadState(int year, int month) {
        if (!RunTimeUtil.getInstance().hasLogin()) {
            return 3;
        }
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        if (GreenDaoUtil.queryLifeCycleItemBeanByDate(runTimeUtil.getUserId(), getDateKey(year, month)) != null) {
            return 3;
        }
        RunTimeUtil runTimeUtil2 = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil2, "RunTimeUtil.getInstance()");
        List<DataDownLoadState> listQueryHistoryDataDownloadState = GreenDaoUtil.queryHistoryDataDownloadState(runTimeUtil2.getUserId(), LifeCycleItemBean.class.getSimpleName());
        List<DataDownLoadState> list = listQueryHistoryDataDownloadState;
        if (list == null || list.isEmpty()) {
            return 3;
        }
        DataDownLoadState firstDownloadState = (DataDownLoadState) CollectionsKt.first((List) listQueryHistoryDataDownloadState);
        Intrinsics.checkExpressionValueIsNotNull(firstDownloadState, "firstDownloadState");
        return firstDownloadState.getDownloadState();
    }

    private final void updateDataState(int year, int month, List<MenstruationCalendar.DateInfo> list) {
        if (list.isEmpty()) {
            return;
        }
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        LifeCycleItemBean lifeCycleItemBeanQueryLifeCycleItemBeanByDate = GreenDaoUtil.queryLifeCycleItemBeanByDate(runTimeUtil.getUserId(), getDateKey(year, month));
        ArrayList arrayList = new ArrayList();
        if (lifeCycleItemBeanQueryLifeCycleItemBeanByDate != null) {
            List<List<Integer>> itemList = lifeCycleItemBeanQueryLifeCycleItemBeanByDate.getItemList();
            if (!(itemList == null || itemList.isEmpty())) {
                List<List<Integer>> itemList2 = lifeCycleItemBeanQueryLifeCycleItemBeanByDate.getItemList();
                Intrinsics.checkExpressionValueIsNotNull(itemList2, "localLifeCycle.itemList");
                List<List<Integer>> list2 = itemList2;
                ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
                Iterator<T> it = list2.iterator();
                while (it.hasNext()) {
                    List list3 = (List) it.next();
                    if (!(list3 == null || list3.isEmpty())) {
                        arrayList.addAll(list3);
                    }
                    arrayList2.add(Unit.INSTANCE);
                }
            }
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            MenstruationCalendar.DateInfo dateInfo = list.get(i);
            if (dateInfo.getType() == MenstruationCalendar.Type.NORMAL) {
                if (arrayList.contains(Integer.valueOf(dateInfo.getValue()))) {
                    dateInfo.setType(MenstruationCalendar.Type.MENSTRUATION);
                    dateInfo.setOriginalType(MenstruationCalendar.Type.MENSTRUATION);
                } else {
                    int i2 = this.mCurrentYear;
                    if (year > i2 || ((year == i2 && month > this.mCurrentMonth) || (year == this.mCurrentYear && month == this.mCurrentMonth && dateInfo.getValue() > this.mCurrentDay))) {
                        dateInfo.setType(MenstruationCalendar.Type.DISABLE);
                    }
                }
            }
        }
    }

    public final Pair<String, String> findPastMensFirstEndDate(int year, int month, int startIndex) {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.set(1, year);
        calendar.set(2, month);
        int i = calendar.get(1);
        int i2 = calendar.get(2);
        String date = "";
        String str = "";
        while (true) {
            int i3 = calendar.get(1);
            int i4 = calendar.get(2);
            List<MenstruationCalendar.DateInfo> list = this.mDateMap.get(getDateKey(i3, i4));
            List<MenstruationCalendar.DateInfo> list2 = list;
            if (list2 == null || list2.isEmpty()) {
                if (date.length() == 0) {
                    return null;
                }
                return new Pair<>(date, str);
            }
            if (i3 == i && i4 == i2 && startIndex < 0) {
                calendar.add(2, -1);
            } else {
                for (int size = (i3 == i && i4 == i2) ? startIndex : list.size() - 1; size >= 0; size--) {
                    MenstruationCalendar.DateInfo dateInfo = list.get(size);
                    if (dateInfo.getType() == MenstruationCalendar.Type.MENSTRUATION) {
                        calendar.set(5, dateInfo.getValue());
                        date = DateUtil.format(calendar, DateUtil.DATE_FORMAT_YMD);
                        if (str.length() == 0) {
                            Intrinsics.checkExpressionValueIsNotNull(date, "date");
                            str = date;
                        }
                        Intrinsics.checkExpressionValueIsNotNull(date, "date");
                    } else if (dateInfo.getType() != MenstruationCalendar.Type.NORMAL) {
                        continue;
                    } else {
                        if (date.length() > 0) {
                            return new Pair<>(date, str);
                        }
                    }
                }
                calendar.add(2, -1);
            }
        }
    }

    public final Pair<String, String> findAfterMensFirstStartDate(int year, int month, int startIndex) {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.set(1, year);
        calendar.set(2, month);
        int i = calendar.get(1);
        int i2 = calendar.get(2);
        String str = "";
        String date = "";
        while (true) {
            int i3 = calendar.get(1);
            int i4 = calendar.get(2);
            List<MenstruationCalendar.DateInfo> list = this.mDateMap.get(getDateKey(i3, i4));
            List<MenstruationCalendar.DateInfo> list2 = list;
            if (list2 == null || list2.isEmpty()) {
                if (str.length() == 0) {
                    return null;
                }
                return new Pair<>(str, date);
            }
            int i5 = (i3 == i && i4 == i2 && startIndex > -1) ? startIndex : 0;
            int size = list.size() - 1;
            if (i5 <= size) {
                while (true) {
                    MenstruationCalendar.DateInfo dateInfo = list.get(i5);
                    if (dateInfo.getType() == MenstruationCalendar.Type.MENSTRUATION) {
                        calendar.set(5, dateInfo.getValue());
                        date = DateUtil.format(calendar, DateUtil.DATE_FORMAT_YMD);
                        if (str.length() == 0) {
                            Intrinsics.checkExpressionValueIsNotNull(date, "date");
                            str = date;
                        }
                        Intrinsics.checkExpressionValueIsNotNull(date, "date");
                    } else if (dateInfo.getType() == MenstruationCalendar.Type.NORMAL) {
                        if (str.length() > 0) {
                            return new Pair<>(str, date);
                        }
                    }
                    if (i5 != size) {
                        i5++;
                    }
                }
            }
            calendar.add(2, 1);
        }
    }

    public final boolean checkHasCancledDate(String startDate, String endDate) {
        int i;
        Intrinsics.checkParameterIsNotNull(startDate, "startDate");
        Intrinsics.checkParameterIsNotNull(endDate, "endDate");
        Calendar startCalendar = Calendar.getInstance(Locale.CHINA);
        Calendar endCalendar = Calendar.getInstance(Locale.CHINA);
        Intrinsics.checkExpressionValueIsNotNull(startCalendar, "startCalendar");
        startCalendar.setTime(DateUtil.string2Date(startDate, DateUtil.DATE_FORMAT_YMD));
        Intrinsics.checkExpressionValueIsNotNull(endCalendar, "endCalendar");
        endCalendar.setTime(DateUtil.string2Date(endDate, DateUtil.DATE_FORMAT_YMD));
        int i2 = 5;
        int i3 = 1;
        startCalendar.add(5, 1);
        startCalendar.set(11, 0);
        startCalendar.set(12, 0);
        startCalendar.set(13, 0);
        startCalendar.set(14, 0);
        endCalendar.set(11, 0);
        endCalendar.set(12, 0);
        endCalendar.set(13, 0);
        endCalendar.set(14, 0);
        if (startCalendar.after(endCalendar)) {
            return false;
        }
        int i4 = startCalendar.get(1);
        int i5 = 2;
        int i6 = startCalendar.get(2);
        int i7 = startCalendar.get(5);
        int i8 = endCalendar.get(1);
        int i9 = endCalendar.get(2);
        int i10 = endCalendar.get(5);
        int i11 = startCalendar.get(1);
        int i12 = startCalendar.get(2);
        boolean z = false;
        while (true) {
            int i13 = (i11 == i4 && i12 == i6) ? i7 : i3;
            int actualMaximum = (i11 == i8 && i12 == i9) ? i10 : startCalendar.getActualMaximum(i2);
            List<MenstruationCalendar.DateInfo> list = this.mDateMap.get(getDateKey(i11, i12));
            List<MenstruationCalendar.DateInfo> list2 = list;
            if (((list2 == null || list2.isEmpty()) ? i3 : 0) == 0) {
                int size = list.size();
                int i14 = 0;
                while (true) {
                    if (i14 >= size) {
                        i = i5;
                        break;
                    }
                    MenstruationCalendar.DateInfo dateInfo = list.get(i14);
                    int value = dateInfo.getValue();
                    if (i13 <= value && actualMaximum >= value && dateInfo.getType() == MenstruationCalendar.Type.NORMAL && !dateInfo.getAutoLink()) {
                        i = 2;
                        i3 = 1;
                        z = true;
                        break;
                    }
                    i14++;
                    i3 = 1;
                    i5 = 2;
                }
                startCalendar.add(i, i3);
                i11 = startCalendar.get(i3);
                i12 = startCalendar.get(i);
                if (i11 >= i8 && (i11 != i8 || i12 > i9)) {
                    break;
                }
                i5 = i;
                i2 = 5;
            } else {
                break;
            }
        }
        return z;
    }

    public final int getMensDayCount(String date) {
        Intrinsics.checkParameterIsNotNull(date, "date");
        if (date.length() == 0) {
            return 0;
        }
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
        calendar.setTime(DateUtil.string2Date(date, DateUtil.DATE_FORMAT_YMD));
        String mensStartDate = getMensStartDate(date);
        String mensEndDate = getMensEndDate(date);
        if (TextUtils.equals(mensStartDate, mensEndDate)) {
            return 1;
        }
        HomeHelperKt.printAndSave("查询到" + date + " 所处的生理周期日期范围是" + mensStartDate + '~' + mensEndDate, this.TAG);
        return MenstrualUtilKt.getDays(mensStartDate, mensEndDate);
    }

    private final String getMensStartDate(String date) {
        MenstrulationSettingPresenter menstrulationSettingPresenter = this;
        int i = 1;
        if (date.length() == 0) {
            return date;
        }
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
        calendar.setTime(DateUtil.string2Date(date, DateUtil.DATE_FORMAT_YMD));
        int i2 = calendar.get(1);
        int i3 = 2;
        int i4 = calendar.get(2);
        int i5 = 5;
        int i6 = calendar.get(5);
        int i7 = i2;
        int i8 = i4;
        int value = i6;
        while (true) {
            int i9 = calendar.get(i);
            int i10 = calendar.get(i3);
            int actualMaximum = (i9 == i2 && i10 == i4) ? i6 - 1 : calendar.getActualMaximum(i5);
            List<MenstruationCalendar.DateInfo> list = menstrulationSettingPresenter.mDateMap.get(menstrulationSettingPresenter.getDateKey(i9, i10));
            List<MenstruationCalendar.DateInfo> list2 = list;
            if (((list2 == null || list2.isEmpty()) ? i : 0) == 0) {
                for (int size = list.size() - i; size >= 0; size--) {
                    MenstruationCalendar.DateInfo dateInfo = list.get(size);
                    if ((dateInfo.getType() == MenstruationCalendar.Type.MENSTRUATION || dateInfo.getType() == MenstruationCalendar.Type.NORMAL) && dateInfo.getValue() <= actualMaximum) {
                        if (dateInfo.getType() == MenstruationCalendar.Type.MENSTRUATION) {
                            value = dateInfo.getValue();
                            i7 = i9;
                            i8 = i10;
                        } else {
                            Calendar calendar2 = Calendar.getInstance(Locale.CHINA);
                            calendar2.set(1, i7);
                            calendar2.set(2, i8);
                            calendar2.set(5, value);
                            String str = DateUtil.format(calendar2, DateUtil.DATE_FORMAT_YMD);
                            Intrinsics.checkExpressionValueIsNotNull(str, "DateUtil.format(result, DateUtil.DATE_FORMAT_YMD)");
                            return str;
                        }
                    }
                    i = 1;
                }
                calendar.add(2, -1);
                i5 = 5;
                i3 = 2;
                menstrulationSettingPresenter = this;
            } else {
                Calendar calendar3 = Calendar.getInstance(Locale.CHINA);
                calendar3.set(i, i7);
                calendar3.set(2, i8);
                calendar3.set(5, value);
                String str2 = DateUtil.format(calendar3, DateUtil.DATE_FORMAT_YMD);
                Intrinsics.checkExpressionValueIsNotNull(str2, "DateUtil.format(result, DateUtil.DATE_FORMAT_YMD)");
                return str2;
            }
        }
    }

    public final String getMensEndDate(String date) {
        char c2;
        int i;
        MenstrulationSettingPresenter menstrulationSettingPresenter = this;
        Intrinsics.checkParameterIsNotNull(date, "date");
        int i2 = 1;
        if (date.length() == 0) {
            return date;
        }
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
        calendar.setTime(DateUtil.string2Date(date, DateUtil.DATE_FORMAT_YMD));
        int i3 = calendar.get(1);
        int i4 = 2;
        int i5 = calendar.get(2);
        int i6 = calendar.get(5);
        int i7 = i3;
        int i8 = i5;
        int i9 = i6;
        while (true) {
            int i10 = calendar.get(i2);
            int i11 = calendar.get(i4);
            int i12 = (i10 == i3 && i11 == i5) ? i6 + 1 : i2;
            List<MenstruationCalendar.DateInfo> list = menstrulationSettingPresenter.mDateMap.get(menstrulationSettingPresenter.getDateKey(i10, i11));
            List<MenstruationCalendar.DateInfo> list2 = list;
            if (((list2 == null || list2.isEmpty()) ? i2 : 0) == 0) {
                int size = list.size() - i2;
                if (size >= 0) {
                    int value = i9;
                    int i13 = i8;
                    int i14 = i7;
                    int i15 = 0;
                    while (true) {
                        MenstruationCalendar.DateInfo dateInfo = list.get(i15);
                        if ((dateInfo.getType() != MenstruationCalendar.Type.MENSTRUATION && dateInfo.getType() != MenstruationCalendar.Type.NORMAL) || dateInfo.getValue() < i12) {
                            c2 = 5;
                            value = value;
                        } else if (dateInfo.getType() == MenstruationCalendar.Type.MENSTRUATION) {
                            value = dateInfo.getValue();
                            i14 = i10;
                            i13 = i11;
                            c2 = 5;
                        } else {
                            Calendar calendar2 = Calendar.getInstance(Locale.CHINA);
                            calendar2.set(1, i14);
                            calendar2.set(2, i13);
                            calendar2.set(5, value);
                            String str = DateUtil.format(calendar2, DateUtil.DATE_FORMAT_YMD);
                            Intrinsics.checkExpressionValueIsNotNull(str, "DateUtil.format(result, DateUtil.DATE_FORMAT_YMD)");
                            return str;
                        }
                        if (i15 == size) {
                            i7 = i14;
                            i8 = i13;
                            i9 = value;
                            i = 2;
                            i2 = 1;
                            break;
                        }
                        i15++;
                    }
                } else {
                    c2 = 5;
                    i = 2;
                }
                calendar.add(i, i2);
                i4 = i;
                menstrulationSettingPresenter = this;
            } else {
                Calendar calendar3 = Calendar.getInstance(Locale.CHINA);
                calendar3.set(i2, i7);
                calendar3.set(2, i8);
                calendar3.set(5, i9);
                String str2 = DateUtil.format(calendar3, DateUtil.DATE_FORMAT_YMD);
                Intrinsics.checkExpressionValueIsNotNull(str2, "DateUtil.format(result, DateUtil.DATE_FORMAT_YMD)");
                return str2;
            }
        }
    }

    public final List<MenstruationCalendar.DateInfo> setMenstruationDate(String startDate, String endDate) {
        MenstrulationSettingPresenter menstrulationSettingPresenter = this;
        Intrinsics.checkParameterIsNotNull(startDate, "startDate");
        Intrinsics.checkParameterIsNotNull(endDate, "endDate");
        ArrayList arrayList = new ArrayList();
        Calendar startCalendar = Calendar.getInstance(Locale.CHINA);
        Calendar endCalendar = Calendar.getInstance(Locale.CHINA);
        Intrinsics.checkExpressionValueIsNotNull(startCalendar, "startCalendar");
        startCalendar.setTime(DateUtil.string2Date(startDate, DateUtil.DATE_FORMAT_YMD));
        Intrinsics.checkExpressionValueIsNotNull(endCalendar, "endCalendar");
        endCalendar.setTime(DateUtil.string2Date(endDate, DateUtil.DATE_FORMAT_YMD));
        int i = 5;
        int i2 = 1;
        startCalendar.add(5, 1);
        startCalendar.set(11, 0);
        startCalendar.set(12, 0);
        startCalendar.set(13, 0);
        startCalendar.set(14, 0);
        endCalendar.set(11, 0);
        endCalendar.set(12, 0);
        endCalendar.set(13, 0);
        endCalendar.set(14, 0);
        if (startCalendar.after(endCalendar)) {
            return arrayList;
        }
        int i3 = startCalendar.get(1);
        int i4 = 2;
        int i5 = startCalendar.get(2);
        int i6 = startCalendar.get(5);
        int i7 = endCalendar.get(1);
        int i8 = endCalendar.get(2);
        int i9 = endCalendar.get(5);
        int i10 = startCalendar.get(1);
        int i11 = startCalendar.get(2);
        while (true) {
            int i12 = (i10 == i3 && i11 == i5) ? i6 : i2;
            int actualMaximum = (i10 == i7 && i11 == i8) ? i9 : startCalendar.getActualMaximum(i);
            List<MenstruationCalendar.DateInfo> list = menstrulationSettingPresenter.mDateMap.get(menstrulationSettingPresenter.getDateKey(i10, i11));
            List<MenstruationCalendar.DateInfo> list2 = list;
            if (((list2 == null || list2.isEmpty()) ? i2 : 0) == 0) {
                int size = list.size();
                int i13 = 0;
                while (i13 < size) {
                    MenstruationCalendar.DateInfo dateInfo = list.get(i13);
                    int value = dateInfo.getValue();
                    if (i12 <= value && actualMaximum >= value && dateInfo.getType() == MenstruationCalendar.Type.NORMAL) {
                        dateInfo.setType(MenstruationCalendar.Type.MENSTRUATION);
                        arrayList.add(dateInfo);
                    }
                    i13++;
                    i2 = 1;
                    i4 = 2;
                }
                int i14 = i4;
                startCalendar.add(i14, i2);
                i10 = startCalendar.get(i2);
                i11 = startCalendar.get(i14);
                if (i10 >= i7 && (i10 != i7 || i11 > i8)) {
                    break;
                }
                i = 5;
                i4 = i14;
                menstrulationSettingPresenter = this;
            } else {
                break;
            }
        }
        return arrayList;
    }

    private final String getDateKey(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1, year);
        calendar.set(2, month);
        String str = DateUtil.format(calendar, DateUtil.DATE_FORMAT_YM_3);
        Intrinsics.checkExpressionValueIsNotNull(str, "DateUtil.format(calendar…ateUtil.DATE_FORMAT_YM_3)");
        return str;
    }

    public final int findPositionInSettingList(int year, int month) {
        int size = this.mSettingList.size();
        for (int i = 0; i < size; i++) {
            SettingBean settingBean = this.mSettingList.get(i);
            if (settingBean.getYear() == year && settingBean.getMonth() == month) {
                return i;
            }
        }
        return -1;
    }

    public final String getLastDay(long timeStamp) {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
        calendar.setTimeInMillis(timeStamp);
        calendar.set(5, calendar.getActualMaximum(5));
        String str = DateUtil.format(calendar, DateUtil.DATE_FORMAT_YMD);
        Intrinsics.checkExpressionValueIsNotNull(str, "DateUtil.format(calendar…DateUtil.DATE_FORMAT_YMD)");
        return str;
    }

    public final String getFirstDay(long timeStamp) {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
        calendar.setTimeInMillis(timeStamp);
        calendar.set(5, 1);
        String str = DateUtil.format(calendar, DateUtil.DATE_FORMAT_YMD);
        Intrinsics.checkExpressionValueIsNotNull(str, "DateUtil.format(calendar…DateUtil.DATE_FORMAT_YMD)");
        return str;
    }

    private final MenstrualRemind getMenstrualRemind() {
        BLEDevice currentDeviceInfo;
        MenstrualRemind menstrualRemind = LocalDataManager.getMenstrualRemind();
        if (menstrualRemind == null) {
            menstrualRemind = new MenstrualRemind();
            menstrualRemind.start_day = 3;
            menstrualRemind.ovulation_day = 3;
            menstrualRemind.hour = 20;
            menstrualRemind.minute = 0;
            menstrualRemind.pregnancy_day_before_remind = 3;
        }
        if (menstrualRemind.pregnancy_day_before_remind <= 0 && BLEManager.isBind() && (currentDeviceInfo = LocalDataManager.getCurrentDeviceInfo()) != null && HomeHelperKt.deviceSupportPregnancyRemind(currentDeviceInfo.mDeviceAddress)) {
            menstrualRemind.pregnancy_day_before_remind = 1;
        }
        return menstrualRemind;
    }

    private final Pair<String, String> getDeviceInfo() {
        BLEDevice currentDeviceInfo;
        if (BLEManager.isBind() && (currentDeviceInfo = LocalDataManager.getCurrentDeviceInfo()) != null) {
            return new Pair<>(currentDeviceInfo.mDeviceName, currentDeviceInfo.mDeviceAddress);
        }
        return new Pair<>("", "");
    }

    public final void saveMenstruation() {
        IMenstrulationSettingView view = getView();
        if (view != null) {
            view.showLoadingDialog();
        }
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        MenstruationConfig lifecycleConfig = GreenDaoUtil.queryMenstruationConfig(runTimeUtil.getUserId());
        MenstrualRemind menstrualRemind = getMenstrualRemind();
        Pair<String, String> deviceInfo = getDeviceInfo();
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, List<MenstruationCalendar.DateInfo>> entry : this.mDateMap.entrySet()) {
            ArrayList arrayList2 = new ArrayList();
            int size = entry.getValue().size() - 1;
            List list = (List) null;
            if (size >= 0) {
                ArrayList arrayList3 = list;
                int i = 0;
                while (true) {
                    MenstruationCalendar.DateInfo dateInfo = entry.getValue().get(i);
                    if (dateInfo.getType() == MenstruationCalendar.Type.MENSTRUATION) {
                        if (arrayList3 == null) {
                            arrayList3 = new ArrayList();
                        }
                        arrayList3.add(Integer.valueOf(dateInfo.getValue()));
                    } else {
                        List list2 = arrayList3;
                        if (!(list2 == null || list2.isEmpty())) {
                            arrayList2.add(arrayList3);
                        }
                        arrayList3 = list;
                    }
                    if (i == size) {
                        break;
                    } else {
                        i++;
                    }
                }
            }
            RunTimeUtil runTimeUtil2 = RunTimeUtil.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(runTimeUtil2, "RunTimeUtil.getInstance()");
            LifeCycleItemBean lifeCycleItemBeanQueryLifeCycleItemBeanByDate = GreenDaoUtil.queryLifeCycleItemBeanByDate(runTimeUtil2.getUserId(), entry.getKey());
            if (lifeCycleItemBeanQueryLifeCycleItemBeanByDate != null && compareListEqual(lifeCycleItemBeanQueryLifeCycleItemBeanByDate.getItemList(), arrayList2)) {
                HomeHelperKt.printAndSave(entry.getKey() + "生理周期数据没有发生变化,不需要更新", this.TAG);
            } else if (arrayList2.isEmpty() && lifeCycleItemBeanQueryLifeCycleItemBeanByDate == null) {
                HomeHelperKt.printAndSave(entry.getKey() + " 检测到用户之前和现在都没有设置该月的生理周期数据，跳过", this.TAG);
            } else {
                if (lifeCycleItemBeanQueryLifeCycleItemBeanByDate != null) {
                    lifeCycleItemBeanQueryLifeCycleItemBeanByDate.delete();
                }
                LifeCycleItemBean lifeCycleItemBean = new LifeCycleItemBean();
                RunTimeUtil runTimeUtil3 = RunTimeUtil.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(runTimeUtil3, "RunTimeUtil.getInstance()");
                lifeCycleItemBean.setUserId(runTimeUtil3.getUserId());
                lifeCycleItemBean.setMonth(entry.getKey());
                Intrinsics.checkExpressionValueIsNotNull(lifecycleConfig, "lifecycleConfig");
                lifeCycleItemBean.setMensesCycle(lifecycleConfig.getMensCycle());
                lifeCycleItemBean.setMensesDays(lifecycleConfig.getMensLength());
                lifeCycleItemBean.setMensesStartDay(menstrualRemind.start_day);
                lifeCycleItemBean.setOvulationDay(menstrualRemind.ovulation_day);
                lifeCycleItemBean.setPregnancyDayBeforeRemind(menstrualRemind.pregnancy_day_before_remind);
                Object[] objArr = {Integer.valueOf(menstrualRemind.hour), Integer.valueOf(menstrualRemind.minute)};
                String str = String.format("%02d:%02d", Arrays.copyOf(objArr, objArr.length));
                Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(this, *args)");
                lifeCycleItemBean.setReminderTime(str);
                lifeCycleItemBean.setSourceMac(deviceInfo.getSecond());
                lifeCycleItemBean.setDeviceName(deviceInfo.getFirst());
                lifeCycleItemBean.setTimeStamp(System.currentTimeMillis());
                lifeCycleItemBean.setItemList(arrayList2);
                lifeCycleItemBean.setUpload(false);
                arrayList.add(lifeCycleItemBean);
            }
        }
        boolean z = true;
        if (!arrayList.isEmpty()) {
            GreenDaoUtil.insertLifeCycle(arrayList);
        }
        if (RunTimeUtil.getInstance().hasLogin() && NetworkUtil.isConnected(IdoApp.getAppContext())) {
            RunTimeUtil runTimeUtil4 = RunTimeUtil.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(runTimeUtil4, "RunTimeUtil.getInstance()");
            List<LifeCycleItemBean> allUploadLifeCycleBean = GreenDaoUtil.getAllUploadLifeCycleBean(runTimeUtil4.getUserId());
            List<LifeCycleItemBean> list3 = allUploadLifeCycleBean;
            if (list3 != null && !list3.isEmpty()) {
                z = false;
            }
            if (!z) {
                uploadToServer(allUploadLifeCycleBean);
                return;
            }
            IMenstrulationSettingView view2 = getView();
            if (view2 != null) {
                view2.dismissLoadingDialog();
            }
            IMenstrulationSettingView view3 = getView();
            if (view3 != null) {
                view3.saveSuccess();
                return;
            }
            return;
        }
        HomeHelperKt.printAndSave("检测都用户当前没有连接网络，生理周期数据暂时保存到本地。", this.TAG);
        IMenstrulationSettingView view4 = getView();
        if (view4 != null) {
            view4.dismissLoadingDialog();
        }
        IMenstrulationSettingView view5 = getView();
        if (view5 != null) {
            view5.saveSuccess();
        }
    }

    private final boolean compareListEqual(List<? extends List<Integer>> previousList, List<? extends List<Integer>> nextList) {
        List<? extends List<Integer>> list = previousList;
        boolean z = true;
        if (list == null || list.isEmpty()) {
            List<? extends List<Integer>> list2 = nextList;
            if (list2 == null || list2.isEmpty()) {
                return true;
            }
        }
        if (list == null || list.isEmpty()) {
            List<? extends List<Integer>> list3 = nextList;
            if (!(list3 == null || list3.isEmpty())) {
                return false;
            }
        }
        if (!(list == null || list.isEmpty())) {
            List<? extends List<Integer>> list4 = nextList;
            if (list4 != null && !list4.isEmpty()) {
                z = false;
            }
            if (z) {
                return false;
            }
        }
        if (previousList == null) {
            Intrinsics.throwNpe();
        }
        int size = previousList.size();
        if (nextList == null) {
            Intrinsics.throwNpe();
        }
        if (size != nextList.size()) {
            return false;
        }
        int size2 = previousList.size();
        TreeSet treeSet = new TreeSet();
        TreeSet treeSet2 = new TreeSet();
        for (int i = 0; i < size2; i++) {
            List<Integer> list5 = previousList.get(i);
            List<Integer> list6 = nextList.get(i);
            List<Integer> list7 = list5;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list7, 10));
            Iterator<T> it = list7.iterator();
            while (it.hasNext()) {
                arrayList.add(Boolean.valueOf(treeSet.add(Integer.valueOf(((Number) it.next()).intValue()))));
            }
            List<Integer> list8 = list6;
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list8, 10));
            Iterator<T> it2 = list8.iterator();
            while (it2.hasNext()) {
                arrayList2.add(Boolean.valueOf(treeSet2.add(Integer.valueOf(((Number) it2.next()).intValue()))));
            }
        }
        return Intrinsics.areEqual(GsonUtil.toJson(treeSet), GsonUtil.toJson(treeSet2));
    }

    private final void uploadToServer(List<LifeCycleItemBean> lifecycleList) {
        IMenstrulationSettingView view = getView();
        if (view != null) {
            view.showLoadingDialog();
        }
        uploadMainUserLifeCycle(lifecycleList);
    }

    private final void uploadMainUserLifeCycle(final List<LifeCycleItemBean> lifecycleList) {
        HealthManager.uploadLifeCycle(new ServerBean.LifeCycleUploadBean(lifecycleList), new HealthManager.CommonApiCallback<BaseEntityNew<Boolean>>() { // from class: com.ido.life.module.home.menstrualcycle.dialog.MenstrulationSettingPresenter.uploadMainUserLifeCycle.1
            @Override // com.ido.life.data.health.remote.HealthManager.CommonApiCallback
            public void onSuccess(BaseEntityNew<Boolean> t) {
                HomeHelperKt.printAndSave("主账号生理周期数据上传到服务器成功", MenstrulationSettingPresenter.this.TAG);
                List<LifeCycleItemBean> list = lifecycleList;
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                for (LifeCycleItemBean lifeCycleItemBean : list) {
                    lifeCycleItemBean.setUpload(true);
                    lifeCycleItemBean.update();
                    arrayList.add(Unit.INSTANCE);
                }
                IMenstrulationSettingView iMenstrulationSettingViewAccess$getView = MenstrulationSettingPresenter.access$getView(MenstrulationSettingPresenter.this);
                if (iMenstrulationSettingViewAccess$getView != null) {
                    iMenstrulationSettingViewAccess$getView.dismissLoadingDialog();
                }
                IMenstrulationSettingView iMenstrulationSettingViewAccess$getView2 = MenstrulationSettingPresenter.access$getView(MenstrulationSettingPresenter.this);
                if (iMenstrulationSettingViewAccess$getView2 != null) {
                    iMenstrulationSettingViewAccess$getView2.saveSuccess();
                }
            }

            @Override // com.ido.life.data.health.remote.HealthManager.CommonApiCallback
            public void onFailed(String message) {
                HomeHelperKt.printAndSave("主账号生理周期数据上传失败,message=" + message, MenstrulationSettingPresenter.this.TAG);
                IMenstrulationSettingView iMenstrulationSettingViewAccess$getView = MenstrulationSettingPresenter.access$getView(MenstrulationSettingPresenter.this);
                if (iMenstrulationSettingViewAccess$getView != null) {
                    iMenstrulationSettingViewAccess$getView.dismissLoadingDialog();
                }
                IMenstrulationSettingView iMenstrulationSettingViewAccess$getView2 = MenstrulationSettingPresenter.access$getView(MenstrulationSettingPresenter.this);
                if (iMenstrulationSettingViewAccess$getView2 != null) {
                    if (message == null) {
                        message = "";
                    }
                    iMenstrulationSettingViewAccess$getView2.saveFailed(message);
                }
            }
        });
    }

    private final void startLoadData() {
        MenstrulationSettingPresenter menstrulationSettingPresenter = this;
        DataDownLoadService.INSTANCE.removeTaskExecutedCallback(menstrulationSettingPresenter);
        List<Long> listQueryUnSuccessTaskId = queryUnSuccessTaskId();
        boolean z = true;
        if (!this.mFocusTaskList.isEmpty()) {
            List<Long> list = listQueryUnSuccessTaskId;
            if (list != null && !list.isEmpty()) {
                z = false;
            }
            if (!z) {
                DataDownLoadService.INSTANCE.requestPullData(listQueryUnSuccessTaskId);
            }
            DataDownLoadService.INSTANCE.addTaskExecutedCallback(menstrulationSettingPresenter);
        }
    }

    public final void retryLoadData() {
        this.mDateMap.clear();
        startLoadData();
        IMenstrulationSettingView view = getView();
        if (view != null) {
            view.refreshData();
        }
    }

    private final List<Long> queryUnSuccessTaskId() {
        this.mFocusTaskList.clear();
        Calendar calendar = Calendar.getInstance();
        String str = DateUtil.format(calendar, DateUtil.DATE_FORMAT_YMD);
        calendar.add(1, -100);
        String str2 = DateUtil.format(calendar, DateUtil.DATE_FORMAT_YMD);
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        android.util.Pair<List<Long>, List<Long>> pairQueryLoadingOrFailedTask = GreenDaoUtil.queryLoadingOrFailedTask(runTimeUtil.getUserId(), CollectionsKt.mutableListOf(LifeCycleItemBean.class.getSimpleName()), str2, str, DateUtil.DATE_FORMAT_YMD);
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

    public final int getWeekStart() {
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        return runTimeUtil.getWeekStart();
    }

    public final boolean dataHasChanged() {
        Iterator<List<MenstruationCalendar.DateInfo>> it = this.mDateMap.values().iterator();
        while (it.hasNext()) {
            for (MenstruationCalendar.DateInfo dateInfo : it.next()) {
                if (dateInfo.getType() == MenstruationCalendar.Type.MENSTRUATION || dateInfo.getType() == MenstruationCalendar.Type.NORMAL) {
                    if (dateInfo.getType() != dateInfo.getOriginalType()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /* JADX INFO: compiled from: MenstrulationSettingPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0007\"\u0004\b\u000b\u0010\t¨\u0006\u0015"}, d2 = {"Lcom/ido/life/module/home/menstrualcycle/dialog/MenstrulationSettingPresenter$SettingBean;", "", "year", "", "month", "(II)V", "getMonth", "()I", "setMonth", "(I)V", "getYear", "setYear", "component1", "component2", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final /* data */ class SettingBean {
        private int month;
        private int year;

        /* JADX WARN: Illegal instructions before constructor call */
        public SettingBean() {
            int i = 0;
            this(i, i, 3, null);
        }

        public static /* synthetic */ SettingBean copy$default(SettingBean settingBean, int i, int i2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                i = settingBean.year;
            }
            if ((i3 & 2) != 0) {
                i2 = settingBean.month;
            }
            return settingBean.copy(i, i2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getYear() {
            return this.year;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getMonth() {
            return this.month;
        }

        public final SettingBean copy(int year, int month) {
            return new SettingBean(year, month);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SettingBean)) {
                return false;
            }
            SettingBean settingBean = (SettingBean) other;
            return this.year == settingBean.year && this.month == settingBean.month;
        }

        public int hashCode() {
            return (Integer.valueOf(this.year).hashCode() * 31) + Integer.valueOf(this.month).hashCode();
        }

        public String toString() {
            return "SettingBean(year=" + this.year + ", month=" + this.month + ")";
        }

        public SettingBean(int i, int i2) {
            this.year = i;
            this.month = i2;
        }

        public final int getYear() {
            return this.year;
        }

        public final void setYear(int i) {
            this.year = i;
        }

        public /* synthetic */ SettingBean(int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this((i3 & 1) != 0 ? 0 : i, (i3 & 2) != 0 ? 0 : i2);
        }

        public final int getMonth() {
            return this.month;
        }

        public final void setMonth(int i) {
            this.month = i;
        }
    }

    @Override // com.ido.life.syncdownload.ITaskExecutedCallBack
    public void onTaskComplete() {
        this.mDateMap.clear();
        IMenstrulationSettingView view = getView();
        if (view != null) {
            view.refreshData();
        }
    }

    @Override // com.ido.life.syncdownload.ITaskExecutedCallBack
    public void onTaskExecutedFailed(NewTask.NewTaskInfo taskInfo, String error) {
        Intrinsics.checkParameterIsNotNull(taskInfo, "taskInfo");
        this.mDateMap.clear();
        IMenstrulationSettingView view = getView();
        if (view != null) {
            view.refreshData();
        }
    }

    @Override // com.ido.life.syncdownload.ITaskExecutedCallBack
    public void onTaskExecutedSuccess(NewTask.NewTaskInfo taskInfo) {
        Intrinsics.checkParameterIsNotNull(taskInfo, "taskInfo");
        this.mDateMap.clear();
        IMenstrulationSettingView view = getView();
        if (view != null) {
            view.refreshData();
        }
    }

    @Override // com.ido.life.syncdownload.ITaskExecutedCallBack
    public List<Long> getFocusTaskList() {
        return this.mFocusTaskList;
    }
}