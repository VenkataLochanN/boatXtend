package com.ido.life.module.home.chartdetail.vertical;

import android.text.TextUtils;
import android.util.Pair;
import com.amazon.identity.auth.device.authorization.AuthorizationResponseParser;
import com.boat.Xtend.two.R;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPath;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.ResourceUtil;
import com.ido.life.base.BaseMessage;
import com.ido.life.constants.Constants;
import com.ido.life.database.model.TimeSet;
import com.ido.life.module.home.HomeHelperKt;
import com.ido.life.module.home.chartdetail.vertical.IBaseDetailView;
import com.ido.life.syncdownload.DataDownLoadService;
import com.ido.life.syncdownload.ITaskExecutedCallBack;
import com.ido.life.syncdownload.NewTask;
import com.ido.life.util.DateUtil;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RunTimeUtil;
import com.ido.life.util.TimeUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;

/* JADX INFO: compiled from: BaseDetailPresenter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0010!\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0010\b&\u0018\u0000 |*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u0002*\u0004\b\u0002\u0010\u0003*\u0004\b\u0003\u0010\u0004* \b\u0004\u0010\u0005*\u001a\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u00040\u00062\u00020\u0007:\u0001|B\r\u0012\u0006\u0010\b\u001a\u00028\u0004¢\u0006\u0002\u0010\tJ\b\u0010H\u001a\u00020IH\u0004J\b\u0010J\u001a\u00020IH\u0002J\b\u0010K\u001a\u00020IH$J\u0010\u0010L\u001a\u0004\u0018\u00010M2\u0006\u0010N\u001a\u00020\u000bJ\u0006\u0010O\u001a\u00020IJ\u000e\u0010P\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0010H$J\u000e\u0010Q\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0010H\u0002J\u0012\u0010R\u001a\u00020I2\b\u0010S\u001a\u0004\u0018\u00010\u000bH\u0014J\u001c\u0010T\u001a\u00020I2\b\u0010U\u001a\u0004\u0018\u00010\u000b2\b\u0010V\u001a\u0004\u0018\u00010\u000bH\u0014J\u001c\u0010W\u001a\u00020I2\b\u0010U\u001a\u0004\u0018\u00010\u000b2\b\u0010V\u001a\u0004\u0018\u00010\u000bH\u0014J\u001c\u0010X\u001a\u00020I2\b\u0010U\u001a\u0004\u0018\u00010\u000b2\b\u0010V\u001a\u0004\u0018\u00010\u000bH\u0014J\b\u0010Y\u001a\u00020IH\u0016J\u0006\u0010Z\u001a\u00020\u000bJ\u000e\u0010[\u001a\b\u0012\u0004\u0012\u00020(0'H\u0016J\u0006\u0010\\\u001a\u00020\u000bJ\u0006\u0010]\u001a\u00020(J\u0012\u0010^\u001a\u00020-2\b\u0010N\u001a\u0004\u0018\u00010\u000bH&J\u0006\u0010_\u001a\u00020-J\u0016\u0010`\u001a\u00020I2\u0006\u0010a\u001a\u00020\u00142\u0006\u0010b\u001a\u00020\u0014J\b\u0010c\u001a\u00020IH\u0016J\u001a\u0010d\u001a\u00020I2\u0006\u0010e\u001a\u00020f2\b\u0010g\u001a\u0004\u0018\u00010\u000bH\u0016J\u0010\u0010h\u001a\u00020I2\u0006\u0010e\u001a\u00020fH\u0016J\b\u0010i\u001a\u00020IH\u0014J\u0010\u0010j\u001a\u00020I2\u0006\u0010k\u001a\u00020\u000bH\u0004J\u0016\u0010l\u001a\u00020I2\f\u0010k\u001a\b\u0012\u0002\b\u0003\u0018\u00010mH\u0016J\u0010\u0010n\u001a\u00020I2\u0006\u0010o\u001a\u00020-H\u0004J\u0011\u0010p\u001a\u00020IH\u0094@ø\u0001\u0000¢\u0006\u0002\u0010qJ\u0019\u0010r\u001a\u00020I2\u0006\u0010o\u001a\u00020-H¤@ø\u0001\u0000¢\u0006\u0002\u0010sJ\u0019\u0010t\u001a\u00020I2\u0006\u0010o\u001a\u00020-H¤@ø\u0001\u0000¢\u0006\u0002\u0010sJ\u0019\u0010u\u001a\u00020I2\u0006\u0010o\u001a\u00020-H¤@ø\u0001\u0000¢\u0006\u0002\u0010sJ\u0019\u0010v\u001a\u00020I2\u0006\u0010o\u001a\u00020-H¤@ø\u0001\u0000¢\u0006\u0002\u0010sJ\b\u0010w\u001a\u00020-H\u0014J\b\u0010x\u001a\u00020IH\u0016J\u0010\u0010y\u001a\u00020I2\u0006\u0010o\u001a\u00020-H\u0002J\u0014\u0010z\u001a\u0004\u0018\u00010\u000b2\b\u0010N\u001a\u0004\u0018\u00010\u000bH\u0002J\b\u0010{\u001a\u00020\u0014H\u0016R\u0019\u0010\n\u001a\n \f*\u0004\u0018\u00010\u000b0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00108F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R$\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u0014@DX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u0011\u0010\u001a\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u000eR\u001a\u0010\u001c\u001a\u00020\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0017\"\u0004\b\u001e\u0010\u0019R\u001a\u0010\u001f\u001a\u00020\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0017\"\u0004\b!\u0010\u0019R(\u0010\"\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0013\u001a\u0004\u0018\u00010\u000b@DX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u000e\"\u0004\b$\u0010%R \u0010&\u001a\b\u0012\u0004\u0012\u00020(0'X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\u0012\"\u0004\b*\u0010+R\u000e\u0010,\u001a\u00020-X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010.\u001a\u0004\u0018\u00010/X\u0082\u000e¢\u0006\u0002\n\u0000R(\u00100\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0013\u001a\u0004\u0018\u00010\u000b@DX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010\u000e\"\u0004\b2\u0010%R\u001c\u0010\b\u001a\u00028\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u00106\u001a\u0004\b3\u00104\"\u0004\b5\u0010\tR\u001a\u00107\u001a\u00020\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u0010\u0017\"\u0004\b9\u0010\u0019R\u001a\u0010:\u001a\u00020\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010\u0017\"\u0004\b<\u0010\u0019R\u001a\u0010=\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00108BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b>\u0010\u0012R\u0011\u0010?\u001a\u00020\u00148F¢\u0006\u0006\u001a\u0004\b@\u0010\u0017R\u0011\u0010A\u001a\u00020\u00148F¢\u0006\u0006\u001a\u0004\bB\u0010\u0017R\u0011\u0010C\u001a\u00020\u00148F¢\u0006\u0006\u001a\u0004\bD\u0010\u0017R\u001a\u0010E\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00108DX\u0084\u0004¢\u0006\u0006\u001a\u0004\bF\u0010\u0012R\u0014\u0010G\u001a\b\u0012\u0004\u0012\u00020\u000b0'X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006}"}, d2 = {"Lcom/ido/life/module/home/chartdetail/vertical/BaseDetailPresenter;", "Day", "Week", "Month", "Year", "T", "Lcom/ido/life/module/home/chartdetail/vertical/IBaseDetailView;", "Lcom/ido/life/syncdownload/ITaskExecutedCallBack;", "mView", "(Lcom/ido/life/module/home/chartdetail/vertical/IBaseDetailView;)V", "TAG", "", "kotlin.jvm.PlatformType", "getTAG", "()Ljava/lang/String;", "bottomLabelList", "", "getBottomLabelList", "()Ljava/util/List;", "<set-?>", "", "dataDownloadState", "getDataDownloadState", "()I", "setDataDownloadState", "(I)V", "dateText", "getDateText", "mDateOffset", "getMDateOffset", "setMDateOffset", "mDateType", "getMDateType", "setMDateType", "mEndDate", "getMEndDate", "setMEndDate", "(Ljava/lang/String;)V", "mFocusList", "", "", "getMFocusList", "setMFocusList", "(Ljava/util/List;)V", "mHasCancled", "", "mJob", "Lkotlinx/coroutines/Job;", "mStartDate", "getMStartDate", "setMStartDate", "getMView", "()Lcom/ido/life/module/home/chartdetail/vertical/IBaseDetailView;", "setMView", "Lcom/ido/life/module/home/chartdetail/vertical/IBaseDetailView;", "mXMaxValue", "getMXMaxValue", "setMXMaxValue", "mXMinValue", "getMXMinValue", "setMXMinValue", "monthBottomLabelList", "getMonthBottomLabelList", "timeFormat", "getTimeFormat", "unitSet", "getUnitSet", "weekStart", "getWeekStart", "yearAndMonthList", "getYearAndMonthList", "yearBottomLabelList", "calculateDate", "", "cancelJob", "clearData", "convertDateToIntArray", "", "date", "ensureActive", "getDataDownloadType", "getDayBottomLabel", "getDetailByDay", "startDate", "getDetailByMonth", "start", "end", "getDetailByWeek", "getDetailByYear", "getDetailData", "getEndDayMonth", "getFocusTaskList", "getStartDayMonth", "getUserId", "hasData", "hasLogin", "initType", "dateType", "dateOffset", "onTaskComplete", "onTaskExecutedFailed", "taskInfo", "Lcom/ido/life/syncdownload/NewTask$NewTaskInfo;", AuthorizationResponseParser.ERROR, "onTaskExecutedSuccess", "onTypeChanged", "printAndSaveLog", "message", "processEventBusMessage", "Lcom/ido/life/base/BaseMessage;", "readDataFromLocal", "showChartAnimator", "readDataOnlyOnce", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readLocalDayData", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readLocalMonthData", "readLocalWeekData", "readLocalYearData", "readOnlyFromLocal", "requestPullData", "startJob", "transformDateString", "year", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
public abstract class BaseDetailPresenter<Day, Week, Month, Year, T extends IBaseDetailView<Day, Week, Month, Year>> implements ITaskExecutedCallBack {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static AtomicBoolean mHasLoadOnce = new AtomicBoolean(false);
    private final String TAG;
    private int dataDownloadState;
    private int mDateOffset;
    private int mDateType;
    private String mEndDate;
    private List<Long> mFocusList;
    private boolean mHasCancled;
    private Job mJob;
    private String mStartDate;
    private T mView;
    private int mXMaxValue;
    private int mXMinValue;
    private final List<String> yearBottomLabelList;

    protected abstract void clearData();

    protected abstract List<String> getDataDownloadType();

    public abstract boolean hasData(String date);

    protected Object readDataOnlyOnce(Continuation<? super Unit> continuation) {
        return readDataOnlyOnce$suspendImpl(this, continuation);
    }

    protected abstract Object readLocalDayData(boolean z, Continuation<? super Unit> continuation);

    protected abstract Object readLocalMonthData(boolean z, Continuation<? super Unit> continuation);

    protected abstract Object readLocalWeekData(boolean z, Continuation<? super Unit> continuation);

    protected abstract Object readLocalYearData(boolean z, Continuation<? super Unit> continuation);

    protected boolean readOnlyFromLocal() {
        return true;
    }

    public BaseDetailPresenter(T mView) {
        Intrinsics.checkParameterIsNotNull(mView, "mView");
        this.mView = mView;
        this.TAG = getClass().getSimpleName();
        this.dataDownloadState = -1;
        this.mFocusList = new ArrayList();
        DataDownLoadService.INSTANCE.addTaskExecutedCallback(this);
        String languageText = LanguageUtil.getLanguageText(R.string.chart_detail_month_short_jan);
        Intrinsics.checkExpressionValueIsNotNull(languageText, "LanguageUtil.getLanguage…t_detail_month_short_jan)");
        String languageText2 = LanguageUtil.getLanguageText(R.string.chart_detail_month_short_feb);
        Intrinsics.checkExpressionValueIsNotNull(languageText2, "LanguageUtil.getLanguage…t_detail_month_short_feb)");
        String languageText3 = LanguageUtil.getLanguageText(R.string.chart_detail_month_short_mar);
        Intrinsics.checkExpressionValueIsNotNull(languageText3, "LanguageUtil.getLanguage…t_detail_month_short_mar)");
        String languageText4 = LanguageUtil.getLanguageText(R.string.chart_detail_month_short_apr);
        Intrinsics.checkExpressionValueIsNotNull(languageText4, "LanguageUtil.getLanguage…t_detail_month_short_apr)");
        String languageText5 = LanguageUtil.getLanguageText(R.string.chart_detail_month_short_may);
        Intrinsics.checkExpressionValueIsNotNull(languageText5, "LanguageUtil.getLanguage…t_detail_month_short_may)");
        String languageText6 = LanguageUtil.getLanguageText(R.string.chart_detail_month_short_jun);
        Intrinsics.checkExpressionValueIsNotNull(languageText6, "LanguageUtil.getLanguage…t_detail_month_short_jun)");
        String languageText7 = LanguageUtil.getLanguageText(R.string.chart_detail_month_short_jul);
        Intrinsics.checkExpressionValueIsNotNull(languageText7, "LanguageUtil.getLanguage…t_detail_month_short_jul)");
        String languageText8 = LanguageUtil.getLanguageText(R.string.chart_detail_month_short_aug);
        Intrinsics.checkExpressionValueIsNotNull(languageText8, "LanguageUtil.getLanguage…t_detail_month_short_aug)");
        String languageText9 = LanguageUtil.getLanguageText(R.string.chart_detail_month_short_sep);
        Intrinsics.checkExpressionValueIsNotNull(languageText9, "LanguageUtil.getLanguage…t_detail_month_short_sep)");
        String languageText10 = LanguageUtil.getLanguageText(R.string.chart_detail_month_short_oct);
        Intrinsics.checkExpressionValueIsNotNull(languageText10, "LanguageUtil.getLanguage…t_detail_month_short_oct)");
        String languageText11 = LanguageUtil.getLanguageText(R.string.chart_detail_month_short_nov);
        Intrinsics.checkExpressionValueIsNotNull(languageText11, "LanguageUtil.getLanguage…t_detail_month_short_nov)");
        String languageText12 = LanguageUtil.getLanguageText(R.string.chart_detail_month_short_dec);
        Intrinsics.checkExpressionValueIsNotNull(languageText12, "LanguageUtil.getLanguage…t_detail_month_short_dec)");
        this.yearBottomLabelList = CollectionsKt.mutableListOf(languageText, languageText2, languageText3, languageText4, languageText5, languageText6, languageText7, languageText8, languageText9, languageText10, languageText11, languageText12);
    }

    public final T getMView() {
        return this.mView;
    }

    public final void setMView(T t) {
        Intrinsics.checkParameterIsNotNull(t, "<set-?>");
        this.mView = t;
    }

    public final String getTAG() {
        return this.TAG;
    }

    public final int getMDateType() {
        return this.mDateType;
    }

    public final void setMDateType(int i) {
        this.mDateType = i;
    }

    public final int getMDateOffset() {
        return this.mDateOffset;
    }

    public final void setMDateOffset(int i) {
        this.mDateOffset = i;
    }

    public final String getMStartDate() {
        return this.mStartDate;
    }

    protected final void setMStartDate(String str) {
        this.mStartDate = str;
    }

    public final String getMEndDate() {
        return this.mEndDate;
    }

    protected final void setMEndDate(String str) {
        this.mEndDate = str;
    }

    public final int getMXMinValue() {
        return this.mXMinValue;
    }

    public final void setMXMinValue(int i) {
        this.mXMinValue = i;
    }

    public final int getMXMaxValue() {
        return this.mXMaxValue;
    }

    public final void setMXMaxValue(int i) {
        this.mXMaxValue = i;
    }

    public final int getDataDownloadState() {
        return this.dataDownloadState;
    }

    protected final void setDataDownloadState(int i) {
        this.dataDownloadState = i;
    }

    protected final List<Long> getMFocusList() {
        return this.mFocusList;
    }

    protected final void setMFocusList(List<Long> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.mFocusList = list;
    }

    public final void initType(int dateType, int dateOffset) {
        if (this.mDateType != dateType) {
            mHasLoadOnce.set(false);
        }
        this.mDateType = dateType;
        this.mDateOffset = dateOffset;
        this.mEndDate = (String) null;
        this.mStartDate = this.mEndDate;
        onTypeChanged();
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void getDetailData() {
        /*
            Method dump skipped, instruction units count: 418
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter.getDetailData():void");
    }

    @Override // com.ido.life.syncdownload.ITaskExecutedCallBack
    public void onTaskExecutedFailed(NewTask.NewTaskInfo taskInfo, String error) {
        Intrinsics.checkParameterIsNotNull(taskInfo, "taskInfo");
        HomeHelperKt.printAndSave("任务 " + taskInfo + " 执行失败", this.TAG);
        if (this.dataDownloadState == 4) {
            return;
        }
        this.dataDownloadState = 4;
        this.mView.showLoadFailedView();
    }

    @Override // com.ido.life.syncdownload.ITaskExecutedCallBack
    public void onTaskExecutedSuccess(NewTask.NewTaskInfo taskInfo) {
        Intrinsics.checkParameterIsNotNull(taskInfo, "taskInfo");
        HomeHelperKt.printAndSave("item任务执行成功", this.TAG);
        if (this.dataDownloadState == 3) {
            return;
        }
        mHasLoadOnce.set(false);
        readDataFromLocal(false);
    }

    @Override // com.ido.life.syncdownload.ITaskExecutedCallBack
    public void onTaskComplete() {
        int i = this.dataDownloadState;
        if (i == 3 || i == 4) {
            return;
        }
        HomeHelperKt.printAndSave("所有任务执行完成", this.TAG);
        this.dataDownloadState = 3;
        this.mView.showLoadSuccessView();
        mHasLoadOnce.set(false);
        readDataFromLocal(false);
    }

    public synchronized void processEventBusMessage(BaseMessage<?> message) {
    }

    private final List<String> getMonthBottomLabelList() {
        ArrayList arrayList = new ArrayList();
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        int weekStart = runTimeUtil.getWeekStart();
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
        calendar.setFirstDayOfWeek(weekStart);
        calendar.set(7, weekStart);
        for (int i = 0; i <= 6; i++) {
            switch (calendar.get(7)) {
                case 1:
                    String languageText = LanguageUtil.getLanguageText(R.string.public_time_sunday_short);
                    Intrinsics.checkExpressionValueIsNotNull(languageText, "LanguageUtil.getLanguage…public_time_sunday_short)");
                    arrayList.add(languageText);
                    break;
                case 2:
                    String languageText2 = LanguageUtil.getLanguageText(R.string.public_time_monday_short);
                    Intrinsics.checkExpressionValueIsNotNull(languageText2, "LanguageUtil.getLanguage…public_time_monday_short)");
                    arrayList.add(languageText2);
                    break;
                case 3:
                    String languageText3 = LanguageUtil.getLanguageText(R.string.public_time_tuesday_short);
                    Intrinsics.checkExpressionValueIsNotNull(languageText3, "LanguageUtil.getLanguage…ublic_time_tuesday_short)");
                    arrayList.add(languageText3);
                    break;
                case 4:
                    String languageText4 = LanguageUtil.getLanguageText(R.string.public_time_wednesday_short);
                    Intrinsics.checkExpressionValueIsNotNull(languageText4, "LanguageUtil.getLanguage…lic_time_wednesday_short)");
                    arrayList.add(languageText4);
                    break;
                case 5:
                    String languageText5 = LanguageUtil.getLanguageText(R.string.public_time_thursday_short);
                    Intrinsics.checkExpressionValueIsNotNull(languageText5, "LanguageUtil.getLanguage…blic_time_thursday_short)");
                    arrayList.add(languageText5);
                    break;
                case 6:
                    String languageText6 = LanguageUtil.getLanguageText(R.string.public_time_friday_short);
                    Intrinsics.checkExpressionValueIsNotNull(languageText6, "LanguageUtil.getLanguage…public_time_friday_short)");
                    arrayList.add(languageText6);
                    break;
                case 7:
                    String languageText7 = LanguageUtil.getLanguageText(R.string.public_time_saturday_short);
                    Intrinsics.checkExpressionValueIsNotNull(languageText7, "LanguageUtil.getLanguage…blic_time_saturday_short)");
                    arrayList.add(languageText7);
                    break;
            }
            calendar.add(5, 1);
        }
        return arrayList;
    }

    public final List<String> getBottomLabelList() {
        ArrayList arrayList = new ArrayList();
        int i = this.mDateType;
        if (i == 1) {
            arrayList.add("00:00");
            arrayList.add("06:00");
            arrayList.add("12:00");
            arrayList.add("18:00");
            arrayList.add("24:00");
        } else if (i == 2) {
            arrayList.addAll(getMonthBottomLabelList());
        } else if (i == 3) {
            Calendar calendar = Calendar.getInstance(Locale.getDefault());
            calendar.add(2, this.mDateOffset);
            int actualMaximum = calendar.getActualMaximum(5);
            calendar.add(2, 1);
            int i2 = calendar.get(2);
            if (i2 == 0) {
                i2 = 12;
            }
            int i3 = actualMaximum / 4;
            for (int i4 = 0; i4 < 4; i4++) {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                Object[] objArr = {Integer.valueOf((i3 * i4) + 1), Integer.valueOf(i2)};
                String str = String.format("%d/%d", Arrays.copyOf(objArr, objArr.length));
                Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(format, *args)");
                arrayList.add(str);
            }
            StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
            Object[] objArr2 = {Integer.valueOf(actualMaximum), Integer.valueOf(i2)};
            String str2 = String.format("%d/%d", Arrays.copyOf(objArr2, objArr2.length));
            Intrinsics.checkNotNullExpressionValue(str2, "java.lang.String.format(format, *args)");
            arrayList.add(str2);
        } else if (i == 4) {
            for (int i5 = 1; i5 < 13; i5++) {
                arrayList.add(String.valueOf(i5));
            }
        }
        return arrayList;
    }

    private final List<String> getDayBottomLabel() {
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        int timeFormat = runTimeUtil.getTimeFormat();
        if (timeFormat == 1) {
            String[] stringArray = ResourceUtil.getResources().getStringArray(R.array.chart_detail_bottom_label_24);
            Intrinsics.checkExpressionValueIsNotNull(stringArray, "ResourceUtil.getResource…t_detail_bottom_label_24)");
            return ArraysKt.toList(stringArray);
        }
        if (timeFormat == 2) {
            String[] stringArray2 = ResourceUtil.getResources().getStringArray(R.array.chart_detail_bottom_label_12);
            Intrinsics.checkExpressionValueIsNotNull(stringArray2, "ResourceUtil.getResource…t_detail_bottom_label_12)");
            return ArraysKt.toList(stringArray2);
        }
        if (TimeUtil.is24Hour()) {
            String[] stringArray3 = ResourceUtil.getResources().getStringArray(R.array.chart_detail_bottom_label_24);
            Intrinsics.checkExpressionValueIsNotNull(stringArray3, "ResourceUtil.getResource…t_detail_bottom_label_24)");
            return ArraysKt.toList(stringArray3);
        }
        String[] stringArray4 = ResourceUtil.getResources().getStringArray(R.array.chart_detail_bottom_label_12);
        Intrinsics.checkExpressionValueIsNotNull(stringArray4, "ResourceUtil.getResource…t_detail_bottom_label_12)");
        return ArraysKt.toList(stringArray4);
    }

    public final String getDateText() {
        String strTransformDateString = transformDateString(this.mStartDate);
        String strTransformDateString2 = transformDateString(this.mEndDate);
        int i = this.mDateType;
        if (i == 1) {
            String str = strTransformDateString;
            if (!TextUtils.isEmpty(str)) {
                if (strTransformDateString == null) {
                    Intrinsics.throwNpe();
                }
                return new Regex("-").replace(str, "/");
            }
        } else if (i == 2 || i == 3) {
            String str2 = strTransformDateString;
            if (!TextUtils.isEmpty(str2)) {
                String str3 = strTransformDateString2;
                if (!TextUtils.isEmpty(str3)) {
                    if (strTransformDateString == null) {
                        Intrinsics.throwNpe();
                    }
                    String strReplace = new Regex("-").replace(str2, "/");
                    if (strTransformDateString2 == null) {
                        Intrinsics.throwNpe();
                    }
                    String strReplace2 = new Regex("-").replace(str3, "/");
                    int iLastIndexOf$default = StringsKt.lastIndexOf$default((CharSequence) strReplace, "/", 0, false, 6, (Object) null);
                    if (strReplace2 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                    }
                    String strSubstring = strReplace2.substring(0, iLastIndexOf$default);
                    Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    return strReplace + '-' + strSubstring;
                }
            }
        } else if (i == 4) {
            String str4 = strTransformDateString;
            if (!TextUtils.isEmpty(str4)) {
                String str5 = strTransformDateString2;
                if (!TextUtils.isEmpty(str5)) {
                    if (strTransformDateString == null) {
                        Intrinsics.throwNpe();
                    }
                    String strReplace3 = new Regex("-").replace(str4, "/");
                    if (strTransformDateString2 == null) {
                        Intrinsics.throwNpe();
                    }
                    String strReplace4 = new Regex("-").replace(str5, "/");
                    String strReplace5 = new Regex("-").replace(str4, "/");
                    int iIndexOf$default = StringsKt.indexOf$default((CharSequence) strReplace3, "/", 0, false, 6, (Object) null) + 1;
                    int length = strReplace3.length();
                    if (strReplace5 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                    }
                    String strSubstring2 = strReplace5.substring(iIndexOf$default, length);
                    Intrinsics.checkNotNullExpressionValue(strSubstring2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    String strReplace6 = new Regex("-").replace(str5, "/");
                    int iIndexOf$default2 = StringsKt.indexOf$default((CharSequence) strReplace4, "/", 0, false, 6, (Object) null) + 1;
                    int length2 = strReplace4.length();
                    if (strReplace6 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                    }
                    String strSubstring3 = strReplace6.substring(iIndexOf$default2, length2);
                    Intrinsics.checkNotNullExpressionValue(strSubstring3, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    return strSubstring2 + '-' + strSubstring3;
                }
            }
        }
        return "";
    }

    private final String transformDateString(String date) {
        String str = date;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (date == null) {
            Intrinsics.throwNpe();
        }
        List listSplit$default = StringsKt.split$default((CharSequence) str, new String[]{"-"}, false, 0, 6, (Object) null);
        if (listSplit$default.size() != 3) {
            return date;
        }
        return ((String) listSplit$default.get(2)) + "-" + ((String) listSplit$default.get(1)) + "-" + ((String) listSplit$default.get(0));
    }

    private final void cancelJob() {
        if (this.mJob != null) {
            CommonLogUtil.d(this.TAG, "开始取消任务");
            Job job = this.mJob;
            if (job != null) {
                job.invokeOnCompletion(new Function1<Throwable, Unit>() { // from class: com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter.cancelJob.1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                        invoke2(th);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Throwable th) {
                        BaseDetailPresenter.this.mJob = (Job) null;
                        BaseDetailPresenter.this.mHasCancled = false;
                    }
                });
            }
            Job job2 = this.mJob;
            if (job2 != null) {
                job2.cancel(new CancellationException("任务取消"));
            }
        }
    }

    protected final void readDataFromLocal(final boolean showChartAnimator) {
        if (this.mJob != null) {
            if (this.mHasCancled) {
                return;
            }
            this.mHasCancled = true;
            CommonLogUtil.d(this.TAG, "(readDataFromLocal)开始取消任务,准备计算(" + this.mStartDate + '-' + this.mEndDate + ')');
            Job job = this.mJob;
            if (job != null) {
                job.invokeOnCompletion(new Function1<Throwable, Unit>() { // from class: com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter.readDataFromLocal.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                        invoke2(th);
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter$readDataFromLocal$1$1, reason: invalid class name and collision with other inner class name */
                    /* JADX INFO: compiled from: BaseDetailPresenter.kt */
                    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0004\"\u0004\b\u0003\u0010\u0005\" \b\u0004\u0010\u0006*\u001a\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0007*\u00020\bH\u008a@¢\u0006\u0004\b\t\u0010\n"}, d2 = {"<anonymous>", "", "Day", "Week", "Month", "Year", "T", "Lcom/ido/life/module/home/chartdetail/vertical/IBaseDetailView;", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
                    @DebugMetadata(c = "com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter$readDataFromLocal$1$1", f = "BaseDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                    static final class C01111 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        int label;
                        private CoroutineScope p$;

                        C01111(Continuation continuation) {
                            super(2, continuation);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                            Intrinsics.checkParameterIsNotNull(completion, "completion");
                            C01111 c01111 = C03041.this.new C01111(completion);
                            c01111.p$ = (CoroutineScope) obj;
                            return c01111;
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                            return ((C01111) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object obj) throws Throwable {
                            IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            if (this.label != 0) {
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                            }
                            ResultKt.throwOnFailure(obj);
                            CoroutineScope coroutineScope = this.p$;
                            BaseDetailPresenter.this.mHasCancled = false;
                            if (BaseDetailPresenter.this.mJob != null) {
                                BaseDetailPresenter.this.mJob = (Job) null;
                                CommonLogUtil.d(BaseDetailPresenter.this.getTAG(), "任务取消后开始执行新的任务,准备计算(" + BaseDetailPresenter.this.getMStartDate() + '-' + BaseDetailPresenter.this.getMEndDate() + ')');
                                BaseDetailPresenter.this.startJob(showChartAnimator);
                            }
                            return Unit.INSTANCE;
                        }
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Throwable th) {
                        CommonLogUtil.d(BaseDetailPresenter.this.getTAG(), "任务取消成功");
                        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C01111(null), 2, null);
                    }
                });
            }
            Job job2 = this.mJob;
            if (job2 != null) {
                job2.cancel(new CancellationException("任务取消"));
                return;
            }
            return;
        }
        CommonLogUtil.d(this.TAG, "开始执行新的任务,准备计算(" + this.mStartDate + '-' + this.mEndDate + ')');
        startJob(showChartAnimator);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startJob(boolean showChartAnimator) {
        CommonLogUtil.d(this.TAG, "startJob,mStartDate=" + this.mStartDate + ",mEndDate=" + this.mEndDate + ",view=" + this.mView);
        if (TextUtils.isEmpty(this.mStartDate) || TextUtils.isEmpty(this.mEndDate)) {
            return;
        }
        this.mJob = BuildersKt.launch(GlobalScope.INSTANCE, Dispatchers.getDefault(), CoroutineStart.LAZY, new C03051(showChartAnimator, null));
        Job job = this.mJob;
        if (job != null) {
            job.start();
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter$startJob$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: BaseDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0004\"\u0004\b\u0003\u0010\u0005\" \b\u0004\u0010\u0006*\u001a\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0007*\u00020\bH\u008a@¢\u0006\u0004\b\t\u0010\n"}, d2 = {"<anonymous>", "", "Day", "Week", "Month", "Year", "T", "Lcom/ido/life/module/home/chartdetail/vertical/IBaseDetailView;", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter$startJob$1", f = "BaseDetailPresenter.kt", i = {0, 0, 1, 1, 2, 2, 3, 3, 4, 4}, l = {413, 414, Constants.BindErrorCode.BIND_FAILED_DEVICE_ALREADY_IN_BIND_STATE, Constants.BindErrorCode.BIND_FAILED_ERROR_ENCRYPTED, 425}, m = "invokeSuspend", n = {"$this$launch", "start$iv", "$this$launch", "start$iv", "$this$launch", "start$iv", "$this$launch", "start$iv", "$this$launch", "userTimeMillios"}, s = {"L$0", "J$0", "L$0", "J$0", "L$0", "J$0", "L$0", "J$0", "L$0", "J$0"})
    static final class C03051 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ boolean $showChartAnimator;
        long J$0;
        Object L$0;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03051(boolean z, Continuation continuation) {
            super(2, continuation);
            this.$showChartAnimator = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03051 c03051 = new C03051(this.$showChartAnimator, completion);
            c03051.p$ = (CoroutineScope) obj;
            return c03051;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03051) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            CoroutineScope coroutineScope;
            long jCurrentTimeMillis;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                coroutineScope = this.p$;
                BaseDetailPresenter.this.clearData();
                jCurrentTimeMillis = System.currentTimeMillis();
                int mDateType = BaseDetailPresenter.this.getMDateType();
                if (mDateType == 1) {
                    BaseDetailPresenter baseDetailPresenter = BaseDetailPresenter.this;
                    boolean z = this.$showChartAnimator;
                    this.L$0 = coroutineScope;
                    this.J$0 = jCurrentTimeMillis;
                    this.label = 1;
                    if (baseDetailPresenter.readLocalDayData(z, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else if (mDateType == 2) {
                    BaseDetailPresenter baseDetailPresenter2 = BaseDetailPresenter.this;
                    boolean z2 = this.$showChartAnimator;
                    this.L$0 = coroutineScope;
                    this.J$0 = jCurrentTimeMillis;
                    this.label = 2;
                    if (baseDetailPresenter2.readLocalWeekData(z2, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else if (mDateType == 3) {
                    BaseDetailPresenter baseDetailPresenter3 = BaseDetailPresenter.this;
                    boolean z3 = this.$showChartAnimator;
                    this.L$0 = coroutineScope;
                    this.J$0 = jCurrentTimeMillis;
                    this.label = 3;
                    if (baseDetailPresenter3.readLocalMonthData(z3, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else if (mDateType == 4) {
                    BaseDetailPresenter baseDetailPresenter4 = BaseDetailPresenter.this;
                    boolean z4 = this.$showChartAnimator;
                    this.L$0 = coroutineScope;
                    this.J$0 = jCurrentTimeMillis;
                    this.label = 4;
                    if (baseDetailPresenter4.readLocalYearData(z4, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
            } else {
                if (i != 1 && i != 2 && i != 3 && i != 4) {
                    if (i == 5) {
                        long j = this.J$0;
                        ResultKt.throwOnFailure(obj);
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                long j2 = this.J$0;
                coroutineScope = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                jCurrentTimeMillis = j2;
            }
            long jCurrentTimeMillis2 = System.currentTimeMillis() - jCurrentTimeMillis;
            CommonLogUtil.d(BaseDetailPresenter.this.getTAG(), '(' + BaseDetailPresenter.this.getMStartDate() + '-' + BaseDetailPresenter.this.getMEndDate() + ")数据计算用时userTimeMillios=" + jCurrentTimeMillis2);
            if (!BaseDetailPresenter.INSTANCE.getMHasLoadOnce().get()) {
                BaseDetailPresenter.INSTANCE.getMHasLoadOnce().set(true);
                BaseDetailPresenter baseDetailPresenter5 = BaseDetailPresenter.this;
                this.L$0 = coroutineScope;
                this.J$0 = jCurrentTimeMillis2;
                this.label = 5;
                if (baseDetailPresenter5.readDataOnlyOnce(this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            return Unit.INSTANCE;
        }
    }

    protected final void calculateDate() {
        Calendar startCalendar = Calendar.getInstance(Locale.getDefault());
        int i = this.mDateType;
        if (i == 1) {
            this.mXMinValue = 1;
            this.mXMaxValue = 24;
            startCalendar.add(5, this.mDateOffset);
            Intrinsics.checkExpressionValueIsNotNull(startCalendar, "startCalendar");
            this.mEndDate = DateUtil.format(startCalendar.getTime(), DateUtil.DATE_FORMAT_YMD);
            this.mStartDate = this.mEndDate;
        } else if (i == 2) {
            Intrinsics.checkExpressionValueIsNotNull(startCalendar, "startCalendar");
            startCalendar.setFirstDayOfWeek(getWeekStart());
            startCalendar.set(7, startCalendar.getFirstDayOfWeek());
            startCalendar.add(4, this.mDateOffset);
            this.mStartDate = DateUtil.format(startCalendar.getTime(), DateUtil.DATE_FORMAT_YMD);
            startCalendar.add(5, 6);
            this.mEndDate = DateUtil.format(startCalendar.getTime(), DateUtil.DATE_FORMAT_YMD);
            this.mXMinValue = 1;
            this.mXMaxValue = 7;
        } else if (i == 3) {
            startCalendar.add(2, this.mDateOffset);
            startCalendar.set(5, 1);
            Intrinsics.checkExpressionValueIsNotNull(startCalendar, "startCalendar");
            this.mStartDate = DateUtil.format(startCalendar.getTime(), DateUtil.DATE_FORMAT_YMD);
            int actualMaximum = startCalendar.getActualMaximum(5);
            startCalendar.set(5, actualMaximum);
            this.mEndDate = DateUtil.format(startCalendar.getTime(), DateUtil.DATE_FORMAT_YMD);
            this.mXMinValue = 1;
            this.mXMaxValue = actualMaximum;
        } else if (i == 4) {
            startCalendar.add(1, this.mDateOffset);
            startCalendar.set(2, startCalendar.getActualMinimum(2));
            startCalendar.set(5, startCalendar.getActualMinimum(5));
            Intrinsics.checkExpressionValueIsNotNull(startCalendar, "startCalendar");
            this.mStartDate = DateUtil.format(startCalendar.getTime(), DateUtil.DATE_FORMAT_YMD);
            startCalendar.set(2, startCalendar.getActualMaximum(2));
            startCalendar.set(5, startCalendar.getActualMaximum(5));
            this.mEndDate = DateUtil.format(startCalendar.getTime(), DateUtil.DATE_FORMAT_YMD);
            this.mXMinValue = 1;
            this.mXMaxValue = 12;
        }
        this.mView.setXMaxValue(this.mXMaxValue);
        this.mView.setXMinValue(this.mXMinValue);
        this.mView.onBottomViewRefresh();
        this.mView.onTopViewRefresh();
        CommonLogUtil.d(this.TAG, "mStartDate=" + this.mStartDate + ",mEndDate=" + this.mEndDate);
    }

    public int year() {
        if (TextUtils.isEmpty(this.mStartDate)) {
            return 0;
        }
        try {
            Calendar calendar = Calendar.getInstance(Locale.getDefault());
            Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
            calendar.setTime(DateUtil.string2Date(this.mStartDate, DateUtil.DATE_FORMAT_YMD));
            return calendar.get(1);
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    protected void getDetailByDay(String startDate) {
        HomeHelperKt.printAndSave("开始请求日详情数据 startDate=" + startDate + ",mDateType=" + this.mDateType, this.TAG);
    }

    protected void getDetailByWeek(String start, String end) {
        CommonLogUtil.d(this.TAG, "getDetailByWeek start=" + start + ",end=" + end);
    }

    protected void getDetailByMonth(String start, String end) {
        CommonLogUtil.d(this.TAG, "getDetailByMonth( start=" + start + ",end=" + end);
    }

    protected void getDetailByYear(String start, String end) {
        CommonLogUtil.d(this.TAG, "getDetailByYear( start=" + start + ",end=" + end);
    }

    public final int[] convertDateToIntArray(String date) {
        Intrinsics.checkParameterIsNotNull(date, "date");
        String str = date;
        if (!TextUtils.isEmpty(str)) {
            if (StringsKt.contains$default((CharSequence) str, (CharSequence) "-", false, 2, (Object) null)) {
                Object[] array = StringsKt.split$default((CharSequence) str, new String[]{"-"}, false, 0, 6, (Object) null).toArray(new String[0]);
                if (array != null) {
                    String[] strArr = (String[]) array;
                    int[] iArr = (int[]) null;
                    int length = strArr.length;
                    if (strArr == null || length <= 0) {
                        return iArr;
                    }
                    int[] iArr2 = new int[length];
                    for (int i = 0; i < length; i++) {
                        try {
                            iArr2[i] = Integer.parseInt(strArr[i]);
                        } catch (Exception e2) {
                            e2.printStackTrace();
                            return iArr;
                        }
                    }
                    return iArr2;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
            }
        }
        return null;
    }

    protected final List<String> getYearAndMonthList() {
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        try {
            calendar.set(1, DateUtil.yearMonthDay(this.mStartDate)[0]);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        int i = calendar.get(1);
        ArrayList arrayList = new ArrayList();
        for (int i2 = 1; i2 <= 12; i2++) {
            if (i2 < 10) {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                Object[] objArr = {Integer.valueOf(i), Integer.valueOf(i2)};
                String str = String.format("%d-0%d", Arrays.copyOf(objArr, objArr.length));
                Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(format, *args)");
                arrayList.add(str);
            } else {
                StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                Object[] objArr2 = {Integer.valueOf(i), Integer.valueOf(i2)};
                String str2 = String.format("%d-%d", Arrays.copyOf(objArr2, objArr2.length));
                Intrinsics.checkNotNullExpressionValue(str2, "java.lang.String.format(format, *args)");
                arrayList.add(str2);
            }
        }
        return arrayList;
    }

    protected void onTypeChanged() {
        clearData();
        this.mView.clearCache();
    }

    public final int getTimeFormat() {
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        TimeSet timeSetQueryTimeSet = GreenDaoUtil.queryTimeSet(runTimeUtil.getUserId());
        if (timeSetQueryTimeSet != null) {
            return timeSetQueryTimeSet.getTimeFormat();
        }
        return 1;
    }

    public final int getWeekStart() {
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        return runTimeUtil.getWeekStart();
    }

    public final int getUnitSet() {
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        return runTimeUtil.getUnitSet();
    }

    public final long getUserId() {
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        return runTimeUtil.getUserId();
    }

    static /* synthetic */ Object readDataOnlyOnce$suspendImpl(BaseDetailPresenter baseDetailPresenter, Continuation continuation) {
        baseDetailPresenter.printAndSaveLog("readDataOnlyOnce");
        return Unit.INSTANCE;
    }

    public void requestPullData() {
        Pair<List<Long>, List<Long>> pairQueryLoadingOrFailedTask = GreenDaoUtil.queryLoadingOrFailedTask(getUserId(), getDataDownloadType(), this.mStartDate, this.mEndDate, DateUtil.DATE_FORMAT_YMD);
        if (pairQueryLoadingOrFailedTask == null || (((List) pairQueryLoadingOrFailedTask.first).isEmpty() && ((List) pairQueryLoadingOrFailedTask.second).isEmpty())) {
            this.mView.showLoadSuccessView();
            return;
        }
        this.mFocusList.clear();
        Intrinsics.checkExpressionValueIsNotNull(pairQueryLoadingOrFailedTask.first, "result.first");
        if (!((Collection) r1).isEmpty()) {
            List<Long> list = this.mFocusList;
            Object obj = pairQueryLoadingOrFailedTask.first;
            Intrinsics.checkExpressionValueIsNotNull(obj, "result.first");
            list.addAll((Collection) obj);
        }
        Intrinsics.checkExpressionValueIsNotNull(pairQueryLoadingOrFailedTask.second, "result.second");
        if (!((Collection) r1).isEmpty()) {
            List<Long> list2 = this.mFocusList;
            Object obj2 = pairQueryLoadingOrFailedTask.second;
            Intrinsics.checkExpressionValueIsNotNull(obj2, "result.second");
            list2.addAll((Collection) obj2);
        }
        this.dataDownloadState = 2;
        Intrinsics.checkExpressionValueIsNotNull(pairQueryLoadingOrFailedTask.second, "result.second");
        if (!((Collection) r1).isEmpty()) {
            DataDownLoadService.Companion companion = DataDownLoadService.INSTANCE;
            Object obj3 = pairQueryLoadingOrFailedTask.second;
            Intrinsics.checkExpressionValueIsNotNull(obj3, "result.second");
            companion.requestPullData((List) obj3);
        }
        this.mView.showLoadingView();
    }

    public final boolean hasLogin() {
        return RunTimeUtil.getInstance().hasLogin();
    }

    /* JADX INFO: compiled from: BaseDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/ido/life/module/home/chartdetail/vertical/BaseDetailPresenter$Companion;", "", "()V", "mHasLoadOnce", "Ljava/util/concurrent/atomic/AtomicBoolean;", "getMHasLoadOnce", "()Ljava/util/concurrent/atomic/AtomicBoolean;", "setMHasLoadOnce", "(Ljava/util/concurrent/atomic/AtomicBoolean;)V", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final AtomicBoolean getMHasLoadOnce() {
            return BaseDetailPresenter.mHasLoadOnce;
        }

        public final void setMHasLoadOnce(AtomicBoolean atomicBoolean) {
            Intrinsics.checkParameterIsNotNull(atomicBoolean, "<set-?>");
            BaseDetailPresenter.mHasLoadOnce = atomicBoolean;
        }
    }

    public final synchronized void ensureActive() {
        if (this.mJob == null) {
            throw new CancellationException("取消任务");
        }
        Job job = this.mJob;
        if (job != null) {
            JobKt.ensureActive(job);
        }
    }

    public final String getStartDayMonth() {
        String str = this.mStartDate;
        List listSplit$default = str != null ? StringsKt.split$default((CharSequence) str, new String[]{"-"}, false, 0, 6, (Object) null) : null;
        if (listSplit$default == null || listSplit$default.size() != 3) {
            return "";
        }
        return ((String) listSplit$default.get(2)) + "/" + ((String) listSplit$default.get(1));
    }

    public final String getEndDayMonth() {
        String str = this.mEndDate;
        List listSplit$default = str != null ? StringsKt.split$default((CharSequence) str, new String[]{"-"}, false, 0, 6, (Object) null) : null;
        if (listSplit$default == null || listSplit$default.size() != 3) {
            return "";
        }
        return ((String) listSplit$default.get(2)) + "/" + ((String) listSplit$default.get(1));
    }

    @Override // com.ido.life.syncdownload.ITaskExecutedCallBack
    public List<Long> getFocusTaskList() {
        return this.mFocusList;
    }

    protected final void printAndSaveLog(String message) {
        Intrinsics.checkParameterIsNotNull(message, "message");
        if (message.length() == 0) {
            return;
        }
        CommonLogUtil.d(this.TAG, message);
        LogPath logPathImpl = LogPathImpl.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
        CommonLogUtil.printAndSave(logPathImpl.getLogPath(), this.TAG, message);
    }
}