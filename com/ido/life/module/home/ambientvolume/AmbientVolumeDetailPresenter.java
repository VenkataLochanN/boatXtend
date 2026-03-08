package com.ido.life.module.home.ambientvolume;

import android.util.Pair;
import com.boat.Xtend.two.R;
import com.ido.alexa.AlexaCustomSkillConstant;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.GsonUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.constants.Constants;
import com.ido.life.customview.AmbientVolumePassedChartView;
import com.ido.life.customview.charter.FloatLineChartBar;
import com.ido.life.database.model.HealthVolumeData;
import com.ido.life.database.model.NoticeItem;
import com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter;
import com.ido.life.util.DateUtil;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RunTimeUtil;
import com.ido.life.util.TimeUtil;
import com.veryfit.multi.nativeprotocol.b;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.math.MathKt;
import kotlin.random.Random;
import kotlin.random.RandomKt;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: compiled from: AmbientVolumeDetailPresenter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u001f\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010%\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u0000 t28\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0004\u0012\u00020\u00040\u0001:\u0001tB\r\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010\u0006J\b\u0010N\u001a\u00020OH\u0002J\b\u0010P\u001a\u00020OH\u0014J\b\u0010Q\u001a\u00020OH\u0002J\u001c\u0010R\u001a\b\u0012\u0004\u0012\u00020\u00030\u00102\f\u0010S\u001a\b\u0012\u0004\u0012\u00020T0\u0002H\u0002J\u0018\u0010U\u001a\b\u0012\u0004\u0012\u00020V0\u00022\b\u0010W\u001a\u0004\u0018\u00010\u0011H\u0002J\u000e\u0010X\u001a\b\u0012\u0004\u0012\u00020\u00030\u0010H\u0002J\u000e\u0010Y\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0002J\u0016\u0010Z\u001a\u0010\u0012\f\u0012\n [*\u0004\u0018\u00010\u00110\u00110\u0010H\u0014J\b\u0010\\\u001a\u00020OH\u0016J\u0010\u0010]\u001a\u00020\b2\u0006\u0010^\u001a\u00020\bH\u0002J\b\u0010_\u001a\u00020OH\u0002J\u0010\u0010`\u001a\u00020\b2\u0006\u0010^\u001a\u00020\bH\u0002J\u000e\u0010a\u001a\b\u0012\u0004\u0012\u0002050\u0010H\u0002J\b\u0010b\u001a\u00020OH\u0002JJ\u0010c\u001a\u00020O2\u0014\u0010d\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\b0e2\u0014\u0010f\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\u00030e2\f\u0010g\u001a\b\u0012\u0004\u0012\u00020\u00030\u00102\u0006\u0010h\u001a\u00020iH\u0002J\u001c\u0010j\u001a\b\u0012\u0004\u0012\u00020\u00030\u00102\f\u0010k\u001a\b\u0012\u0004\u0012\u00020V0\u0002H\u0002J\u0012\u0010l\u001a\u00020\u00152\b\u0010m\u001a\u0004\u0018\u00010\u0011H\u0016J\u0019\u0010n\u001a\u00020O2\u0006\u0010o\u001a\u00020\u0015H\u0094@ø\u0001\u0000¢\u0006\u0002\u0010pJ\u0019\u0010q\u001a\u00020O2\u0006\u0010o\u001a\u00020\u0015H\u0094@ø\u0001\u0000¢\u0006\u0002\u0010pJ\u0019\u0010r\u001a\u00020O2\u0006\u0010o\u001a\u00020\u0015H\u0094@ø\u0001\u0000¢\u0006\u0002\u0010pJ\u0019\u0010s\u001a\u00020O2\u0006\u0010o\u001a\u00020\u0015H\u0094@ø\u0001\u0000¢\u0006\u0002\u0010pR\u0014\u0010\u0007\u001a\u00020\bX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\bX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0014\u0010\r\u001a\u00020\bX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\nR\u001a\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00108BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u0015X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00030\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0013R\u001a\u0010\u001b\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\n\"\u0004\b\u001d\u0010\u001eR\u001a\u0010\u001f\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\n\"\u0004\b!\u0010\u001eR\u001a\u0010\"\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\n\"\u0004\b$\u0010\u001eR\u001a\u0010%\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\n\"\u0004\b'\u0010\u001eR\u001a\u0010(\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\n\"\u0004\b*\u0010\u001eR\u001a\u0010+\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\n\"\u0004\b-\u0010\u001eR\u001a\u0010.\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010\n\"\u0004\b0\u0010\u001eR\u001a\u00101\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010\n\"\u0004\b3\u0010\u001eR \u00104\u001a\b\u0012\u0004\u0012\u0002050\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u0010\u0013\"\u0004\b7\u00108R\u001a\u00109\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010\n\"\u0004\b;\u0010\u001eR\u001a\u0010<\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010\n\"\u0004\b>\u0010\u001eR\u001a\u0010?\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b@\u0010\n\"\u0004\bA\u0010\u001eR\u001a\u0010B\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u0010\n\"\u0004\bD\u0010\u001eR\u001a\u0010E\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u0010\n\"\u0004\bG\u0010\u001eR\u001a\u0010H\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bI\u0010\n\"\u0004\bJ\u0010\u001eR\u001a\u0010K\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bL\u0010\n\"\u0004\bM\u0010\u001e\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006u"}, d2 = {"Lcom/ido/life/module/home/ambientvolume/AmbientVolumeDetailPresenter;", "Lcom/ido/life/module/home/chartdetail/vertical/BaseDetailPresenter;", "", "Lcom/ido/life/customview/charter/FloatLineChartBar$ChartBean;", "Lcom/ido/life/module/home/ambientvolume/IAmbientVolumeDetailView;", "iView", "(Lcom/ido/life/module/home/ambientvolume/IAmbientVolumeDetailView;)V", "EXPOSURE_HIGH_MAX_DURATION", "", "getEXPOSURE_HIGH_MAX_DURATION", "()I", "EXPOSURE_MEDIUM_MAX_DURATION", "getEXPOSURE_MEDIUM_MAX_DURATION", "EXPOSURE_NORMAL_MAX_DURATION", "getEXPOSURE_NORMAL_MAX_DURATION", "getPassedChartXLabelList", "", "", "getGetPassedChartXLabelList", "()Ljava/util/List;", "isGetData", "", "()Z", "setGetData", "(Z)V", "mChartList", "getMChartList", "mDayCompareTodayVolumeAvg", "getMDayCompareTodayVolumeAvg", "setMDayCompareTodayVolumeAvg", "(I)V", "mDayCompareYesterdayVolumeAvg", "getMDayCompareYesterdayVolumeAvg", "setMDayCompareYesterdayVolumeAvg", "mExporesuHighDuration", "getMExporesuHighDuration", "setMExporesuHighDuration", "mExporesuMediumDuration", "getMExporesuMediumDuration", "setMExporesuMediumDuration", "mExporesuNormalDuration", "getMExporesuNormalDuration", "setMExporesuNormalDuration", "mExposureState", "getMExposureState", "setMExposureState", "mMaxVolumeAvg", "getMMaxVolumeAvg", "setMMaxVolumeAvg", "mMinVolumeAvg", "getMMinVolumeAvg", "setMMinVolumeAvg", "mRecentSevenDaysChartList", "Lcom/ido/life/customview/AmbientVolumePassedChartView$ChartBean;", "getMRecentSevenDaysChartList", "setMRecentSevenDaysChartList", "(Ljava/util/List;)V", "mRecentSevenDaysVolumeAvg", "getMRecentSevenDaysVolumeAvg", "setMRecentSevenDaysVolumeAvg", "mRecentSevenDaysVolumeDuration", "getMRecentSevenDaysVolumeDuration", "setMRecentSevenDaysVolumeDuration", "mRecentSevenDaysVolumeState", "getMRecentSevenDaysVolumeState", "setMRecentSevenDaysVolumeState", "mVolumeAvg", "getMVolumeAvg", "setMVolumeAvg", "mVolumeAvgDuration", "getMVolumeAvgDuration", "setMVolumeAvgDuration", "mWeekCompareCurrVolumeAvg", "getMWeekCompareCurrVolumeAvg", "setMWeekCompareCurrVolumeAvg", "mWeekComparePreVolumeAvg", "getMWeekComparePreVolumeAvg", "setMWeekComparePreVolumeAvg", "calcuteCommonData", "", "clearData", "comparedPastTwoDays", "convertNoiseInfoToLineChart", "datalist", "Lcom/ido/life/database/model/HealthVolumeData;", "formatNoiseItems", "Lcom/ido/life/database/model/NoticeItem;", "items", "generateTestChartData", "getChartYLabelList", "getDataDownloadType", "kotlin.jvm.PlatformType", "getDetailData", "getHour", "totalSecond", "getLastWeekNoiseData", "getMinute", "getPassedChartList", "getPassedSevenDaysChartList", "getVolumeListData", "yearAvgMap", "", "map", "list", Constants.AppPackage.CALENDAR, "Ljava/util/Calendar;", "handleDayChartData", "itemList", "hasData", "date", "readLocalDayData", "showChartAnimator", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readLocalMonthData", "readLocalWeekData", "readLocalYearData", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
public final class AmbientVolumeDetailPresenter extends BaseDetailPresenter<List<? extends FloatLineChartBar.ChartBean>, List<? extends FloatLineChartBar.ChartBean>, List<? extends FloatLineChartBar.ChartBean>, List<? extends FloatLineChartBar.ChartBean>, IAmbientVolumeDetailView> {
    public static final int EXPOSURE_HIGH = 1;
    public static final int EXPOSURE_NORMAL = 0;
    public static final int EXPOSURE_NO_DATA = -1;
    public static final String TAG = "AmbientVolumeDetailPresenter";
    private final int EXPOSURE_HIGH_MAX_DURATION;
    private final int EXPOSURE_MEDIUM_MAX_DURATION;
    private final int EXPOSURE_NORMAL_MAX_DURATION;
    private boolean isGetData;
    private final List<FloatLineChartBar.ChartBean> mChartList;
    private int mDayCompareTodayVolumeAvg;
    private int mDayCompareYesterdayVolumeAvg;
    private int mExporesuHighDuration;
    private int mExporesuMediumDuration;
    private int mExporesuNormalDuration;
    private int mExposureState;
    private int mMaxVolumeAvg;
    private int mMinVolumeAvg;
    private List<AmbientVolumePassedChartView.ChartBean> mRecentSevenDaysChartList;
    private int mRecentSevenDaysVolumeAvg;
    private int mRecentSevenDaysVolumeDuration;
    private int mRecentSevenDaysVolumeState;
    private int mVolumeAvg;
    private int mVolumeAvgDuration;
    private int mWeekCompareCurrVolumeAvg;
    private int mWeekComparePreVolumeAvg;

    /* JADX INFO: renamed from: com.ido.life.module.home.ambientvolume.AmbientVolumeDetailPresenter$readLocalDayData$1, reason: invalid class name */
    /* JADX INFO: compiled from: AmbientVolumeDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0094@"}, d2 = {"readLocalDayData", "", "showChartAnimator", "", "continuation", "Lkotlin/coroutines/Continuation;", ""}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.ambientvolume.AmbientVolumeDetailPresenter", f = "AmbientVolumeDetailPresenter.kt", i = {0, 0, 0}, l = {b.a1}, m = "readLocalDayData", n = {"this", "showChartAnimator", "noiseData"}, s = {"L$0", "Z$0", "L$1"})
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return AmbientVolumeDetailPresenter.this.readLocalDayData(false, this);
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    protected void clearData() {
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    public boolean hasData(String date) {
        return true;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AmbientVolumeDetailPresenter(IAmbientVolumeDetailView iView) {
        super(iView);
        Intrinsics.checkParameterIsNotNull(iView, "iView");
        this.mChartList = new ArrayList();
        this.mRecentSevenDaysChartList = new ArrayList();
        this.EXPOSURE_HIGH_MAX_DURATION = 4;
        this.EXPOSURE_MEDIUM_MAX_DURATION = 40;
        this.EXPOSURE_NORMAL_MAX_DURATION = 127;
    }

    public final int getMExposureState() {
        return this.mExposureState;
    }

    public final void setMExposureState(int i) {
        this.mExposureState = i;
    }

    public final int getMVolumeAvg() {
        return this.mVolumeAvg;
    }

    public final void setMVolumeAvg(int i) {
        this.mVolumeAvg = i;
    }

    public final int getMVolumeAvgDuration() {
        return this.mVolumeAvgDuration;
    }

    public final void setMVolumeAvgDuration(int i) {
        this.mVolumeAvgDuration = i;
    }

    public final List<FloatLineChartBar.ChartBean> getMChartList() {
        return this.mChartList;
    }

    public final int getMMaxVolumeAvg() {
        return this.mMaxVolumeAvg;
    }

    public final void setMMaxVolumeAvg(int i) {
        this.mMaxVolumeAvg = i;
    }

    public final int getMMinVolumeAvg() {
        return this.mMinVolumeAvg;
    }

    public final void setMMinVolumeAvg(int i) {
        this.mMinVolumeAvg = i;
    }

    public final List<AmbientVolumePassedChartView.ChartBean> getMRecentSevenDaysChartList() {
        return this.mRecentSevenDaysChartList;
    }

    public final void setMRecentSevenDaysChartList(List<AmbientVolumePassedChartView.ChartBean> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.mRecentSevenDaysChartList = list;
    }

    public final int getMRecentSevenDaysVolumeState() {
        return this.mRecentSevenDaysVolumeState;
    }

    public final void setMRecentSevenDaysVolumeState(int i) {
        this.mRecentSevenDaysVolumeState = i;
    }

    public final int getMRecentSevenDaysVolumeAvg() {
        return this.mRecentSevenDaysVolumeAvg;
    }

    public final void setMRecentSevenDaysVolumeAvg(int i) {
        this.mRecentSevenDaysVolumeAvg = i;
    }

    public final int getMRecentSevenDaysVolumeDuration() {
        return this.mRecentSevenDaysVolumeDuration;
    }

    public final void setMRecentSevenDaysVolumeDuration(int i) {
        this.mRecentSevenDaysVolumeDuration = i;
    }

    public final int getEXPOSURE_HIGH_MAX_DURATION() {
        return this.EXPOSURE_HIGH_MAX_DURATION;
    }

    public final int getEXPOSURE_MEDIUM_MAX_DURATION() {
        return this.EXPOSURE_MEDIUM_MAX_DURATION;
    }

    public final int getEXPOSURE_NORMAL_MAX_DURATION() {
        return this.EXPOSURE_NORMAL_MAX_DURATION;
    }

    public final int getMExporesuHighDuration() {
        return this.mExporesuHighDuration;
    }

    public final void setMExporesuHighDuration(int i) {
        this.mExporesuHighDuration = i;
    }

    public final int getMExporesuMediumDuration() {
        return this.mExporesuMediumDuration;
    }

    public final void setMExporesuMediumDuration(int i) {
        this.mExporesuMediumDuration = i;
    }

    public final int getMExporesuNormalDuration() {
        return this.mExporesuNormalDuration;
    }

    public final void setMExporesuNormalDuration(int i) {
        this.mExporesuNormalDuration = i;
    }

    public final int getMDayCompareTodayVolumeAvg() {
        return this.mDayCompareTodayVolumeAvg;
    }

    public final void setMDayCompareTodayVolumeAvg(int i) {
        this.mDayCompareTodayVolumeAvg = i;
    }

    public final int getMDayCompareYesterdayVolumeAvg() {
        return this.mDayCompareYesterdayVolumeAvg;
    }

    public final void setMDayCompareYesterdayVolumeAvg(int i) {
        this.mDayCompareYesterdayVolumeAvg = i;
    }

    public final int getMWeekCompareCurrVolumeAvg() {
        return this.mWeekCompareCurrVolumeAvg;
    }

    public final void setMWeekCompareCurrVolumeAvg(int i) {
        this.mWeekCompareCurrVolumeAvg = i;
    }

    public final int getMWeekComparePreVolumeAvg() {
        return this.mWeekComparePreVolumeAvg;
    }

    public final void setMWeekComparePreVolumeAvg(int i) {
        this.mWeekComparePreVolumeAvg = i;
    }

    /* JADX INFO: renamed from: isGetData, reason: from getter */
    public final boolean getIsGetData() {
        return this.isGetData;
    }

    public final void setGetData(boolean z) {
        this.isGetData = z;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    public void getDetailData() {
        CommonLogUtil.d("getDetailData");
        super.getDetailData();
        if (this.isGetData) {
            return;
        }
        this.isGetData = true;
        getPassedSevenDaysChartList();
        getLastWeekNoiseData();
        comparedPastTwoDays();
    }

    private final void comparedPastTwoDays() {
        String currentDateBefore = TimeUtil.getCurrentDateBefore(1);
        String currentDateBefore2 = TimeUtil.getCurrentDateBefore(2);
        HealthVolumeData healthVolumeDataQueryHealthVolumeData = GreenDaoUtil.queryHealthVolumeData(getUserId(), currentDateBefore);
        HealthVolumeData healthVolumeDataQueryHealthVolumeData2 = GreenDaoUtil.queryHealthVolumeData(getUserId(), currentDateBefore2);
        if (healthVolumeDataQueryHealthVolumeData != null) {
            this.mDayCompareTodayVolumeAvg = healthVolumeDataQueryHealthVolumeData.getAvgValue();
        }
        if (healthVolumeDataQueryHealthVolumeData2 != null) {
            this.mDayCompareYesterdayVolumeAvg = healthVolumeDataQueryHealthVolumeData2.getAvgValue();
        }
        CommonLogUtil.printAndSave("comparedPastTwoDays mDayCompareTodayVolumeAvg = " + this.mDayCompareTodayVolumeAvg + ", mDayCompareYesterdayVolumeAvg = " + this.mDayCompareYesterdayVolumeAvg);
    }

    private final void getLastWeekNoiseData() {
        Pair<String, String> lastWeekDateRange = TimeUtil.getLastWeekDateRange();
        String str = (String) lastWeekDateRange.first;
        String str2 = (String) lastWeekDateRange.second;
        String str3 = (String) TimeUtil.getWeekDateRange().first;
        String endWeek = TimeUtil.getCurrentDateBefore(1);
        List<HealthVolumeData> listQueryHealthVolumeDataArea = GreenDaoUtil.queryHealthVolumeDataArea(getUserId(), str, str2);
        List<HealthVolumeData> listQueryHealthVolumeDataArea2 = GreenDaoUtil.queryHealthVolumeDataArea(getUserId(), str3, endWeek);
        StringBuilder sb = new StringBuilder();
        sb.append("getLastWeekNoiseData, weekStart = ");
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        sb.append(runTimeUtil.getWeekStart());
        CommonLogUtil.printAndSave(sb.toString());
        CommonLogUtil.printAndSave("getLastWeekNoiseData, startWeek = " + str3 + ", endWeek = " + endWeek + ", lastStartDate = " + str + ", lastEndDate = " + str2);
        Intrinsics.checkExpressionValueIsNotNull(endWeek, "endWeek");
        if (str3.compareTo(endWeek) > 0) {
            CommonLogUtil.printAndSave("getLastWeekNoiseData, 本周第一天，不做周对比");
            return;
        }
        List<HealthVolumeData> list = listQueryHealthVolumeDataArea2;
        if (list == null || list.isEmpty()) {
            CommonLogUtil.printAndSave("getLastWeekNoiseData, 本周无数据，不做周对比");
            return;
        }
        this.mWeekCompareCurrVolumeAvg = 0;
        int size = listQueryHealthVolumeDataArea2.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                break;
            }
            HealthVolumeData healthVolumeData = listQueryHealthVolumeDataArea2.get(i);
            if (healthVolumeData != null) {
                int i2 = this.mWeekCompareCurrVolumeAvg;
                Integer numValueOf = healthVolumeData != null ? Integer.valueOf(healthVolumeData.getAvgValue()) : null;
                if (numValueOf == null) {
                    Intrinsics.throwNpe();
                }
                this.mWeekCompareCurrVolumeAvg = i2 + numValueOf.intValue();
                if (i == listQueryHealthVolumeDataArea2.size() - 1) {
                    this.mWeekCompareCurrVolumeAvg /= i + 1;
                }
            }
            i++;
        }
        List<HealthVolumeData> list2 = listQueryHealthVolumeDataArea;
        if (list2 == null || list2.isEmpty()) {
            CommonLogUtil.printAndSave("getLastWeekNoiseData, 上周无数据，不做周对比");
            return;
        }
        this.mWeekComparePreVolumeAvg = 0;
        int size2 = listQueryHealthVolumeDataArea.size();
        for (int i3 = 0; i3 < size2; i3++) {
            HealthVolumeData healthVolumeData2 = listQueryHealthVolumeDataArea.get(i3);
            if (healthVolumeData2 != null) {
                int i4 = this.mWeekComparePreVolumeAvg;
                Integer numValueOf2 = healthVolumeData2 != null ? Integer.valueOf(healthVolumeData2.getAvgValue()) : null;
                if (numValueOf2 == null) {
                    Intrinsics.throwNpe();
                }
                this.mWeekComparePreVolumeAvg = i4 + numValueOf2.intValue();
                if (i3 == listQueryHealthVolumeDataArea.size() - 1) {
                    this.mWeekComparePreVolumeAvg /= i3 + 1;
                }
            }
        }
    }

    private final void getPassedSevenDaysChartList() {
        String currentDateBefore = TimeUtil.getCurrentDateBefore(7);
        String currentDateBefore2 = TimeUtil.getCurrentDateBefore(1);
        List<HealthVolumeData> listQueryHealthVolumeDataArea = GreenDaoUtil.queryHealthVolumeDataArea(getUserId(), currentDateBefore, currentDateBefore2);
        this.mRecentSevenDaysChartList.clear();
        HashMap map = new HashMap();
        CommonLogUtil.printAndSave("getPassedSevenDaysChartList startDate = " + currentDateBefore + ", endDate = " + currentDateBefore2 + ", dataList = " + listQueryHealthVolumeDataArea);
        this.mDayCompareTodayVolumeAvg = 0;
        this.mDayCompareYesterdayVolumeAvg = 0;
        this.mRecentSevenDaysVolumeAvg = 0;
        this.mRecentSevenDaysVolumeDuration = 0;
        List<HealthVolumeData> list = listQueryHealthVolumeDataArea;
        if ((list == null || list.isEmpty()) || listQueryHealthVolumeDataArea.size() < 3) {
            return;
        }
        int size = listQueryHealthVolumeDataArea.size();
        for (int i = 0; i < size; i++) {
            HealthVolumeData data = listQueryHealthVolumeDataArea.get(i);
            Intrinsics.checkExpressionValueIsNotNull(data, "data");
            int iDaysBetween = TimeUtil.daysBetween(data.getDate());
            String items = data.getItems();
            if (!(items == null || items.length() == 0)) {
                this.mRecentSevenDaysVolumeAvg += data.getAvgValue();
                if (i == listQueryHealthVolumeDataArea.size() - 1) {
                    this.mRecentSevenDaysVolumeAvg /= i + 1;
                }
                map.put(Integer.valueOf(8 - iDaysBetween), new AmbientVolumePassedChartView.ChartBean(data.getMaxValue(), data.getMinValue()));
                this.mExporesuHighDuration += data.getSuperHighLevelSeconds();
                this.mExporesuMediumDuration += data.getHighLevelSeconds();
                this.mExporesuNormalDuration += data.getNormalLevelSeconds();
                this.mRecentSevenDaysVolumeDuration += data.getTotalSeconds();
            }
        }
        this.mRecentSevenDaysVolumeState = this.mRecentSevenDaysVolumeAvg >= 80 ? 1 : 0;
        for (int i2 = 1; i2 < 8; i2++) {
            AmbientVolumePassedChartView.ChartBean chartBean = (AmbientVolumePassedChartView.ChartBean) map.get(Integer.valueOf(i2));
            if (chartBean != null) {
                this.mRecentSevenDaysChartList.add(chartBean);
            } else {
                this.mRecentSevenDaysChartList.add(new AmbientVolumePassedChartView.ChartBean(0.0f, 0.0f));
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected java.lang.Object readLocalDayData(boolean r7, kotlin.coroutines.Continuation<? super kotlin.Unit> r8) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 238
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.home.ambientvolume.AmbientVolumeDetailPresenter.readLocalDayData(boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.ambientvolume.AmbientVolumeDetailPresenter$readLocalDayData$2, reason: invalid class name */
    /* JADX INFO: compiled from: AmbientVolumeDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.ambientvolume.AmbientVolumeDetailPresenter$readLocalDayData$2", f = "AmbientVolumeDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        AnonymousClass2(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            AnonymousClass2 anonymousClass2 = AmbientVolumeDetailPresenter.this.new AnonymousClass2(completion);
            anonymousClass2.p$ = (CoroutineScope) obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            AmbientVolumeDetailPresenter ambientVolumeDetailPresenter = AmbientVolumeDetailPresenter.this;
            ambientVolumeDetailPresenter.setMVolumeAvgDuration(Math.max(ambientVolumeDetailPresenter.getMVolumeAvgDuration(), 60));
            AmbientVolumeDetailPresenter.this.getMView().setExposureState(AmbientVolumeDetailPresenter.this.getMExposureState());
            AmbientVolumeDetailPresenter.this.getMView().setDate(AmbientVolumeDetailPresenter.this.getDateText());
            IAmbientVolumeDetailView mView = AmbientVolumeDetailPresenter.this.getMView();
            AmbientVolumeDetailPresenter ambientVolumeDetailPresenter2 = AmbientVolumeDetailPresenter.this;
            int hour = ambientVolumeDetailPresenter2.getHour(ambientVolumeDetailPresenter2.getMVolumeAvgDuration());
            AmbientVolumeDetailPresenter ambientVolumeDetailPresenter3 = AmbientVolumeDetailPresenter.this;
            mView.setExposureDuration(hour, ambientVolumeDetailPresenter3.getMinute(ambientVolumeDetailPresenter3.getMVolumeAvgDuration()));
            AmbientVolumeDetailPresenter.this.getMView().setExposureAvg(AmbientVolumeDetailPresenter.this.getMVolumeAvg());
            AmbientVolumeDetailPresenter.this.getMView().setPerHourVolumeMaxmin(AmbientVolumeDetailPresenter.this.getMMinVolumeAvg(), AmbientVolumeDetailPresenter.this.getMMaxVolumeAvg());
            AmbientVolumeDetailPresenter.this.getMView().setChartXLabelList(AmbientVolumeDetailPresenter.this.getBottomLabelList());
            AmbientVolumeDetailPresenter.this.getMView().setChartYLabelList(AmbientVolumeDetailPresenter.this.getChartYLabelList());
            AmbientVolumeDetailPresenter.this.getMView().onLoadSuccessByDay(true, AmbientVolumeDetailPresenter.this.getMChartList());
            AmbientVolumeDetailPresenter.this.calcuteCommonData();
            return Unit.INSTANCE;
        }
    }

    private final List<FloatLineChartBar.ChartBean> handleDayChartData(List<? extends NoticeItem> itemList) {
        int i;
        int i2;
        int i3;
        int i4;
        List<? extends NoticeItem> list = itemList;
        ArrayList arrayList = new ArrayList();
        if (itemList.isEmpty()) {
            return arrayList;
        }
        int size = list.size();
        int i5 = 0;
        int iMax = 0;
        int offset = 0;
        int offset2 = 0;
        int iMin = 120;
        int i6 = 0;
        int i7 = 0;
        while (i5 < size) {
            NoticeItem noticeItem = list.get(i5);
            if (noticeItem.getOffset() + offset > 3600) {
                if (iMax > 0) {
                    i3 = iMin;
                    i4 = i6;
                    arrayList.add(new FloatLineChartBar.ChartBean(i6, 1.0f + i6, (iMin + iMax) / 2.0f, iMax, iMin, TimeUtil.second2Hour(offset2), TimeUtil.second2Min(offset2)));
                } else {
                    i3 = iMin;
                    i4 = i6;
                }
                i2 = offset - 3600;
                if (iMax > 0) {
                    i7++;
                    this.mMaxVolumeAvg += iMax;
                    this.mMinVolumeAvg += i3;
                    offset2 = i2;
                } else {
                    offset2 = 0;
                }
                i6 = i4 + 1;
                i = 120;
                iMax = 0;
            } else {
                i = iMin;
                i2 = offset;
            }
            if (noticeItem.getValue() > 0) {
                this.mVolumeAvgDuration += noticeItem.getOffset();
            }
            offset2 += noticeItem.getOffset();
            int value = noticeItem.getValue();
            if (1 <= value && 120 >= value) {
                iMin = Math.min(i, noticeItem.getValue());
                iMax = Math.max(iMax, noticeItem.getValue());
            } else {
                iMin = i;
            }
            offset = i2 + noticeItem.getOffset();
            i5++;
            list = itemList;
        }
        int i8 = 1;
        int i9 = iMin;
        int i10 = i6;
        if (iMax > 0) {
            arrayList.add(new FloatLineChartBar.ChartBean(i10, 1.0f + i10, (i9 + iMax) / 2.0f, iMax + 0.0f, i9 + 0.0f, TimeUtil.second2Hour(offset2), TimeUtil.second2Min(offset2)));
            i7++;
            this.mMaxVolumeAvg += iMax;
            this.mMinVolumeAvg += i9;
        }
        int i11 = i7;
        if (i10 != 0) {
            float f2 = i11;
            this.mMaxVolumeAvg = MathKt.roundToInt(this.mMaxVolumeAvg / f2);
            this.mMinVolumeAvg = MathKt.roundToInt(this.mMinVolumeAvg / f2);
        }
        if (this.mVolumeAvg <= 80) {
            i8 = 0;
        }
        this.mExposureState = i8;
        return arrayList;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    protected Object readLocalWeekData(boolean z, Continuation<? super Unit> continuation) {
        CommonLogUtil.d("readLocalWeekData " + getMStartDate() + " -> " + getMEndDate());
        List<HealthVolumeData> noiseData = GreenDaoUtil.queryHealthVolumeDataArea(getUserId(), getMStartDate(), getMEndDate());
        Intrinsics.checkExpressionValueIsNotNull(noiseData, "noiseData");
        List<FloatLineChartBar.ChartBean> listConvertNoiseInfoToLineChart = convertNoiseInfoToLineChart(noiseData);
        this.mChartList.clear();
        List<FloatLineChartBar.ChartBean> list = listConvertNoiseInfoToLineChart;
        if (!list.isEmpty()) {
            this.mChartList.addAll(list);
        }
        Object objWithContext = BuildersKt.withContext(Dispatchers.getMain(), new C02942(null), continuation);
        return objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContext : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.ambientvolume.AmbientVolumeDetailPresenter$readLocalWeekData$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AmbientVolumeDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.ambientvolume.AmbientVolumeDetailPresenter$readLocalWeekData$2", f = "AmbientVolumeDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C02942 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        C02942(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C02942 c02942 = AmbientVolumeDetailPresenter.this.new C02942(completion);
            c02942.p$ = (CoroutineScope) obj;
            return c02942;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C02942) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            AmbientVolumeDetailPresenter.this.getMView().setExposureState(AmbientVolumeDetailPresenter.this.getMExposureState());
            AmbientVolumeDetailPresenter.this.getMView().setDate(AmbientVolumeDetailPresenter.this.getDateText());
            AmbientVolumeDetailPresenter.this.getMView().setExposureDuration(TimeUtil.second2Hour(AmbientVolumeDetailPresenter.this.getMVolumeAvgDuration()), TimeUtil.second2Min(AmbientVolumeDetailPresenter.this.getMVolumeAvgDuration()));
            AmbientVolumeDetailPresenter.this.getMView().setExposureAvg(AmbientVolumeDetailPresenter.this.getMVolumeAvg());
            AmbientVolumeDetailPresenter.this.getMView().setPerHourVolumeMaxmin(AmbientVolumeDetailPresenter.this.getMMinVolumeAvg(), AmbientVolumeDetailPresenter.this.getMMaxVolumeAvg());
            AmbientVolumeDetailPresenter.this.getMView().setChartXLabelList(AmbientVolumeDetailPresenter.this.getBottomLabelList());
            AmbientVolumeDetailPresenter.this.getMView().setChartYLabelList(AmbientVolumeDetailPresenter.this.getChartYLabelList());
            AmbientVolumeDetailPresenter.this.getMView().onLoadSuccessByWeek(true, AmbientVolumeDetailPresenter.this.getMChartList());
            AmbientVolumeDetailPresenter.this.calcuteCommonData();
            return Unit.INSTANCE;
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    protected Object readLocalMonthData(boolean z, Continuation<? super Unit> continuation) {
        CommonLogUtil.d("readLocalWeekData " + getMStartDate() + " -> " + getMEndDate());
        List<HealthVolumeData> noiseData = GreenDaoUtil.queryHealthVolumeDataArea(getUserId(), getMStartDate(), getMEndDate());
        Intrinsics.checkExpressionValueIsNotNull(noiseData, "noiseData");
        List<FloatLineChartBar.ChartBean> listConvertNoiseInfoToLineChart = convertNoiseInfoToLineChart(noiseData);
        this.mChartList.clear();
        List<FloatLineChartBar.ChartBean> list = listConvertNoiseInfoToLineChart;
        if (!list.isEmpty()) {
            this.mChartList.addAll(list);
        }
        Object objWithContext = BuildersKt.withContext(Dispatchers.getMain(), new C02932(null), continuation);
        return objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContext : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.ambientvolume.AmbientVolumeDetailPresenter$readLocalMonthData$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AmbientVolumeDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.ambientvolume.AmbientVolumeDetailPresenter$readLocalMonthData$2", f = "AmbientVolumeDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C02932 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        C02932(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C02932 c02932 = AmbientVolumeDetailPresenter.this.new C02932(completion);
            c02932.p$ = (CoroutineScope) obj;
            return c02932;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C02932) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            AmbientVolumeDetailPresenter.this.getMView().setExposureState(AmbientVolumeDetailPresenter.this.getMExposureState());
            AmbientVolumeDetailPresenter.this.getMView().setDate(AmbientVolumeDetailPresenter.this.getDateText());
            AmbientVolumeDetailPresenter.this.getMView().setExposureDuration(TimeUtil.second2Hour(AmbientVolumeDetailPresenter.this.getMVolumeAvgDuration()), TimeUtil.second2Min(AmbientVolumeDetailPresenter.this.getMVolumeAvgDuration()));
            AmbientVolumeDetailPresenter.this.getMView().setExposureAvg(AmbientVolumeDetailPresenter.this.getMVolumeAvg());
            AmbientVolumeDetailPresenter.this.getMView().setPerHourVolumeMaxmin(AmbientVolumeDetailPresenter.this.getMMinVolumeAvg(), AmbientVolumeDetailPresenter.this.getMMaxVolumeAvg());
            AmbientVolumeDetailPresenter.this.getMView().setChartXLabelList(AmbientVolumeDetailPresenter.this.getBottomLabelList());
            AmbientVolumeDetailPresenter.this.getMView().setChartYLabelList(AmbientVolumeDetailPresenter.this.getChartYLabelList());
            AmbientVolumeDetailPresenter.this.getMView().onLoadSuccessByMonth(true, AmbientVolumeDetailPresenter.this.getMChartList());
            AmbientVolumeDetailPresenter.this.calcuteCommonData();
            return Unit.INSTANCE;
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    protected Object readLocalYearData(boolean z, Continuation<? super Unit> continuation) {
        CommonLogUtil.d("readLocalWeekData " + getMStartDate() + " -> " + getMEndDate());
        List<HealthVolumeData> noiseData = GreenDaoUtil.queryHealthVolumeDataArea(getUserId(), getMStartDate(), getMEndDate());
        Intrinsics.checkExpressionValueIsNotNull(noiseData, "noiseData");
        List<FloatLineChartBar.ChartBean> listConvertNoiseInfoToLineChart = convertNoiseInfoToLineChart(noiseData);
        this.mChartList.clear();
        List<FloatLineChartBar.ChartBean> list = listConvertNoiseInfoToLineChart;
        if (!list.isEmpty()) {
            this.mChartList.addAll(list);
        }
        Object objWithContext = BuildersKt.withContext(Dispatchers.getMain(), new C02952(null), continuation);
        return objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContext : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.ambientvolume.AmbientVolumeDetailPresenter$readLocalYearData$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: AmbientVolumeDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.ambientvolume.AmbientVolumeDetailPresenter$readLocalYearData$2", f = "AmbientVolumeDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C02952 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        C02952(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C02952 c02952 = AmbientVolumeDetailPresenter.this.new C02952(completion);
            c02952.p$ = (CoroutineScope) obj;
            return c02952;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C02952) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            AmbientVolumeDetailPresenter.this.getMView().setExposureState(AmbientVolumeDetailPresenter.this.getMExposureState());
            AmbientVolumeDetailPresenter.this.getMView().setDate(AmbientVolumeDetailPresenter.this.getDateText());
            AmbientVolumeDetailPresenter.this.getMView().setExposureDuration(TimeUtil.second2Hour(AmbientVolumeDetailPresenter.this.getMVolumeAvgDuration()), TimeUtil.second2Min(AmbientVolumeDetailPresenter.this.getMVolumeAvgDuration()));
            AmbientVolumeDetailPresenter.this.getMView().setExposureAvg(AmbientVolumeDetailPresenter.this.getMVolumeAvg());
            AmbientVolumeDetailPresenter.this.getMView().setPerHourVolumeMaxmin(AmbientVolumeDetailPresenter.this.getMMinVolumeAvg(), AmbientVolumeDetailPresenter.this.getMMaxVolumeAvg());
            AmbientVolumeDetailPresenter.this.getMView().setChartXLabelList(AmbientVolumeDetailPresenter.this.getBottomLabelList());
            AmbientVolumeDetailPresenter.this.getMView().setChartYLabelList(AmbientVolumeDetailPresenter.this.getChartYLabelList());
            AmbientVolumeDetailPresenter.this.getMView().onLoadSuccessByYear(true, AmbientVolumeDetailPresenter.this.getMChartList());
            AmbientVolumeDetailPresenter.this.calcuteCommonData();
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void calcuteCommonData() {
        int i;
        List<AmbientVolumePassedChartView.ChartBean> list = this.mRecentSevenDaysChartList;
        if (list == null || list.isEmpty()) {
            i = 0;
        } else {
            i = 0;
            for (AmbientVolumePassedChartView.ChartBean chartBean : this.mRecentSevenDaysChartList) {
                float f2 = 0;
                if (chartBean.getMaxValue() > f2 && chartBean.getMinValue() > f2) {
                    i++;
                }
            }
        }
        printAndSaveLog(i >= 3 ? "显示过去7天噪音UI" : "不显示过去7天噪音UI");
        printAndSaveLog(i >= 3 ? "显示过去7天音量暴露UI" : "不显示过去7天音量暴露UI");
        getMView().setPassedSevenDaysChartVisibility(i >= 3);
        getMView().setVolumeLevelExposureVisibility(i >= 3);
        getMView().setPassedSevenDaysXLabelList(getGetPassedChartXLabelList());
        getMView().setPassedSevenDaysVolumeState(this.mRecentSevenDaysVolumeState);
        getMView().setPassedSevenDaysVolumeValue(this.mRecentSevenDaysVolumeAvg, TimeUtil.second2Hour(this.mRecentSevenDaysVolumeDuration), TimeUtil.second2Min(this.mRecentSevenDaysVolumeDuration));
        getMView().setPassedSevenDaysChartList(this.mRecentSevenDaysVolumeAvg, this.mRecentSevenDaysChartList);
        IAmbientVolumeDetailView mView = getMView();
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String languageText = LanguageUtil.getLanguageText(R.string.volume_hour_day);
        Intrinsics.checkExpressionValueIsNotNull(languageText, "LanguageUtil.getLanguage…R.string.volume_hour_day)");
        Object[] objArr = {"≥90dB", 4, 7};
        String str = String.format(languageText, Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(format, *args)");
        mView.setVolumeHighLevelExposureInfo(str, this.mExporesuHighDuration, this.EXPOSURE_HIGH_MAX_DURATION);
        IAmbientVolumeDetailView mView2 = getMView();
        StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
        String languageText2 = LanguageUtil.getLanguageText(R.string.volume_hour_day);
        Intrinsics.checkExpressionValueIsNotNull(languageText2, "LanguageUtil.getLanguage…R.string.volume_hour_day)");
        Object[] objArr2 = {"81-90dB", 40, 7};
        String str2 = String.format(languageText2, Arrays.copyOf(objArr2, objArr2.length));
        Intrinsics.checkNotNullExpressionValue(str2, "java.lang.String.format(format, *args)");
        mView2.setVolumeMediumLevelExposureInfo(str2, this.mExporesuMediumDuration, this.EXPOSURE_MEDIUM_MAX_DURATION);
        IAmbientVolumeDetailView mView3 = getMView();
        StringCompanionObject stringCompanionObject3 = StringCompanionObject.INSTANCE;
        String languageText3 = LanguageUtil.getLanguageText(R.string.volume_hour_day);
        Intrinsics.checkExpressionValueIsNotNull(languageText3, "LanguageUtil.getLanguage…R.string.volume_hour_day)");
        Object[] objArr3 = {"75-80dB", 127, 7};
        String str3 = String.format(languageText3, Arrays.copyOf(objArr3, objArr3.length));
        Intrinsics.checkNotNullExpressionValue(str3, "java.lang.String.format(format, *args)");
        mView3.setVolumeNormalLevelExposureInfo(str3, this.mExporesuNormalDuration, this.EXPOSURE_NORMAL_MAX_DURATION);
        getMView().setDayVolumeCompareUIVisibility((this.mDayCompareYesterdayVolumeAvg == 0 || this.mDayCompareTodayVolumeAvg == 0) ? false : true);
        getMView().setDayVolumeCompare(this.mDayCompareTodayVolumeAvg, this.mDayCompareYesterdayVolumeAvg);
        getMView().setWeekVolumeCompareUIVisibility((this.mWeekCompareCurrVolumeAvg == 0 || this.mWeekComparePreVolumeAvg == 0) ? false : true);
        getMView().setWeekVolumeCompare(this.mWeekCompareCurrVolumeAvg, this.mWeekComparePreVolumeAvg);
        IAmbientVolumeDetailView mView4 = getMView();
        List<FloatLineChartBar.ChartBean> list2 = this.mChartList;
        mView4.setAboutAmbientVolumeUIVisibility(true ^ (list2 == null || list2.isEmpty()));
        if (this.mExposureState == -1) {
            getMView().onDetailLoadFailed();
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    protected List<String> getDataDownloadType() {
        return CollectionsKt.mutableListOf(HealthVolumeData.class.getSimpleName());
    }

    private final List<FloatLineChartBar.ChartBean> generateTestChartData() {
        ArrayList arrayList = new ArrayList();
        int mXMaxValue = (getMXMaxValue() - getMXMinValue()) + 1;
        Random Random = RandomKt.Random(1);
        for (int i = 0; i < mXMaxValue; i++) {
            float f2 = 120;
            float fNextFloat = Random.nextFloat() * f2;
            float fNextFloat2 = Random.nextFloat() * f2;
            arrayList.add(new FloatLineChartBar.ChartBean(i, 1.0f + i, (fNextFloat + fNextFloat2) / 2, Math.max(fNextFloat, fNextFloat2), Math.min(fNextFloat, fNextFloat2), 1, 1));
        }
        return arrayList;
    }

    private final List<FloatLineChartBar.ChartBean> convertNoiseInfoToLineChart(List<? extends HealthVolumeData> datalist) {
        ArrayList arrayList = new ArrayList();
        this.mVolumeAvg = 0;
        this.mVolumeAvgDuration = 0;
        this.mMinVolumeAvg = 0;
        this.mMaxVolumeAvg = 0;
        this.mExposureState = -1;
        List<? extends HealthVolumeData> list = datalist;
        if (list == null || list.isEmpty()) {
            return arrayList;
        }
        int size = datalist.size();
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        HashMap map = new HashMap();
        HashMap map2 = new HashMap();
        for (int i = 0; i < size; i++) {
            HealthVolumeData healthVolumeData = datalist.get(i);
            this.mVolumeAvgDuration += healthVolumeData.getTotalSeconds();
            int mDateType = getMDateType();
            if (mDateType == 2) {
                Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
                calendar.setTime(DateUtil.string2Date(healthVolumeData.getDate(), DateUtil.DATE_FORMAT_YMD));
                calendar.setFirstDayOfWeek(getWeekStart());
                int i2 = calendar.get(7);
                int weekStart = getWeekStart();
                if (weekStart == 1) {
                    map.put(Integer.valueOf(i2), new FloatLineChartBar.ChartBean(i2 - 1, i2, healthVolumeData.getAvgValue(), healthVolumeData.getMaxValue(), healthVolumeData.getMinValue(), TimeUtil.second2Hour(healthVolumeData.getTotalSeconds()), TimeUtil.second2Min(healthVolumeData.getTotalSeconds())));
                } else if (weekStart != 2) {
                    if (weekStart == 7) {
                        if (i2 == 7) {
                            map.put(1, new FloatLineChartBar.ChartBean(0, 1.0f, healthVolumeData.getAvgValue(), healthVolumeData.getMaxValue(), healthVolumeData.getMinValue(), TimeUtil.second2Hour(healthVolumeData.getTotalSeconds()), TimeUtil.second2Min(healthVolumeData.getTotalSeconds())));
                        } else {
                            map.put(Integer.valueOf(i2 + 1), new FloatLineChartBar.ChartBean(i2, i2 + 1.0f, healthVolumeData.getAvgValue(), healthVolumeData.getMaxValue(), healthVolumeData.getMinValue(), TimeUtil.second2Hour(healthVolumeData.getTotalSeconds()), TimeUtil.second2Min(healthVolumeData.getTotalSeconds())));
                        }
                    }
                } else if (i2 == 1) {
                    map.put(7, new FloatLineChartBar.ChartBean(6, 7.0f, healthVolumeData.getAvgValue(), healthVolumeData.getMaxValue(), healthVolumeData.getMinValue(), TimeUtil.second2Hour(healthVolumeData.getTotalSeconds()), TimeUtil.second2Min(healthVolumeData.getTotalSeconds())));
                } else {
                    int i3 = i2 - 1;
                    map.put(Integer.valueOf(i3), new FloatLineChartBar.ChartBean(i2 - 2, i3, healthVolumeData.getAvgValue(), healthVolumeData.getMaxValue(), healthVolumeData.getMinValue(), TimeUtil.second2Hour(healthVolumeData.getTotalSeconds()), TimeUtil.second2Min(healthVolumeData.getTotalSeconds())));
                }
            } else if (mDateType == 3) {
                Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
                calendar.setTime(DateUtil.string2Date(healthVolumeData.getDate(), DateUtil.DATE_FORMAT_YMD));
                int i4 = calendar.get(5);
                map.put(Integer.valueOf(i4), new FloatLineChartBar.ChartBean(i4 - 1, i4, healthVolumeData.getAvgValue(), healthVolumeData.getMaxValue(), healthVolumeData.getMinValue(), TimeUtil.second2Hour(healthVolumeData.getTotalSeconds()), TimeUtil.second2Min(healthVolumeData.getTotalSeconds())));
            } else if (mDateType == 4) {
                Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
                calendar.setTime(DateUtil.string2Date(healthVolumeData.getDate(), DateUtil.DATE_FORMAT_YMD));
                int i5 = calendar.get(2);
                CommonLogUtil.d("index=====" + i5);
                int i6 = i5 + 1;
                if (map.get(Integer.valueOf(i6)) == null) {
                    map2.put(Integer.valueOf(i6), 1);
                    map.put(Integer.valueOf(i6), new FloatLineChartBar.ChartBean(i5, i5 + 1.0f, healthVolumeData.getAvgValue(), healthVolumeData.getMaxValue(), healthVolumeData.getMinValue(), healthVolumeData.getTotalSeconds(), healthVolumeData.getTotalSeconds()));
                } else {
                    FloatLineChartBar.ChartBean chartBean = map.get(Integer.valueOf(i6));
                    Integer num = map2.get(Integer.valueOf(i6));
                    if (num != null) {
                        map2.put(Integer.valueOf(i6), Integer.valueOf(num.intValue() + 1));
                    }
                    Integer numValueOf = Integer.valueOf(i6);
                    float f2 = i5 + 1.0f;
                    float avgValue = healthVolumeData.getAvgValue();
                    Float fValueOf = chartBean != null ? Float.valueOf(chartBean.getAvgValue()) : null;
                    if (fValueOf == null) {
                        Intrinsics.throwNpe();
                    }
                    map.put(numValueOf, new FloatLineChartBar.ChartBean(i5, f2, avgValue + fValueOf.floatValue(), RangesKt.coerceAtLeast(healthVolumeData.getMaxValue(), chartBean.getMaxValue()), RangesKt.coerceAtMost(healthVolumeData.getMinValue(), chartBean.getMinValue()), healthVolumeData.getTotalSeconds() + chartBean.getDurHour(), healthVolumeData.getTotalSeconds() + chartBean.getDurMinute()));
                }
            }
            this.mVolumeAvg += healthVolumeData.getAvgValue();
            this.mMinVolumeAvg += healthVolumeData.getMinValue();
            this.mMaxVolumeAvg += healthVolumeData.getMaxValue();
        }
        if (!map.isEmpty()) {
            Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
            getVolumeListData(map2, map, arrayList, calendar);
            this.mVolumeAvg /= size;
            this.mMinVolumeAvg /= size;
            this.mMaxVolumeAvg /= size;
        }
        this.mExposureState = this.mVolumeAvg > 80 ? 1 : 0;
        return arrayList;
    }

    private final void getVolumeListData(Map<Integer, Integer> yearAvgMap, Map<Integer, FloatLineChartBar.ChartBean> map, List<FloatLineChartBar.ChartBean> list, Calendar calendar) {
        FloatLineChartBar.ChartBean chartBean;
        int mDateType = getMDateType();
        int i = 1;
        if (mDateType == 2) {
            while (i < 8) {
                if (map.containsKey(Integer.valueOf(i)) && map.get(Integer.valueOf(i)) != null && (chartBean = map.get(Integer.valueOf(i))) != null && chartBean.getMaxValue() != 0.0f) {
                    list.add(chartBean);
                }
                i++;
            }
            return;
        }
        if (mDateType == 3) {
            int actualMaximum = calendar.getActualMaximum(5) + 1;
            while (i < actualMaximum) {
                FloatLineChartBar.ChartBean chartBean2 = map.get(Integer.valueOf(i));
                if (chartBean2 != null && chartBean2.getMaxValue() != 0.0f) {
                    list.add(chartBean2);
                }
                i++;
            }
            return;
        }
        if (mDateType != 4) {
            return;
        }
        while (i < 13) {
            if (map.containsKey(Integer.valueOf(i)) && map.get(Integer.valueOf(i)) != null) {
                FloatLineChartBar.ChartBean chartBean3 = map.get(Integer.valueOf(i));
                Integer num = yearAvgMap.get(Integer.valueOf(i));
                if (chartBean3 != null && chartBean3.getMaxValue() != 0.0f) {
                    chartBean3.setDurMinute(TimeUtil.second2Min(chartBean3.getDurMinute()));
                    chartBean3.setDurHour(TimeUtil.second2Hour(chartBean3.getDurHour()));
                    chartBean3.setAvgValue((num == null || num.intValue() <= 0) ? 0.0f : chartBean3.getAvgValue() / num.intValue());
                    list.add(chartBean3);
                }
            }
            i++;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<String> getChartYLabelList() {
        return CollectionsKt.mutableListOf(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE, "40", "80", "120");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getHour(int totalSecond) {
        return totalSecond / 3600;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getMinute(int totalSecond) {
        int i = (totalSecond % 3600) / 60;
        return totalSecond % 60 == 0 ? i : i + 1;
    }

    private final List<NoticeItem> formatNoiseItems(String items) {
        ArrayList arrayList = new ArrayList();
        String str = items;
        if (str == null || str.length() == 0) {
            return arrayList;
        }
        List<int[]> listAnalysisJsonArrayToList = GsonUtil.analysisJsonArrayToList(items, int[][].class);
        List list = listAnalysisJsonArrayToList;
        if (list == null || list.isEmpty()) {
            return arrayList;
        }
        for (int[] iArr : listAnalysisJsonArrayToList) {
            if (iArr != null && iArr.length == 2) {
                NoticeItem noticeItem = new NoticeItem();
                noticeItem.setOffset(iArr[0]);
                noticeItem.setValue(iArr[1]);
                arrayList.add(noticeItem);
            }
        }
        return arrayList;
    }

    private final List<AmbientVolumePassedChartView.ChartBean> getPassedChartList() {
        ArrayList arrayList = new ArrayList();
        Random Random = RandomKt.Random(1);
        for (int i = 0; i < 7; i++) {
            float f2 = 120;
            float fNextFloat = Random.nextFloat() * f2;
            float fNextFloat2 = Random.nextFloat() * f2;
            arrayList.add(new AmbientVolumePassedChartView.ChartBean(Math.max(fNextFloat, fNextFloat2), Math.min(fNextFloat, fNextFloat2)));
        }
        return arrayList;
    }

    private final List<String> getGetPassedChartXLabelList() {
        ArrayList arrayList = new ArrayList();
        int iDayForWeek = TimeUtil.dayForWeek(TimeUtil.getCurrentDateBefore(-1));
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
        calendar.setFirstDayOfWeek(iDayForWeek);
        calendar.set(7, iDayForWeek);
        for (int i = 0; i <= 6; i++) {
            switch (calendar.get(7)) {
                case 1:
                    String languageText = LanguageUtil.getLanguageText(R.string.device_week_sun_android);
                    Intrinsics.checkExpressionValueIsNotNull(languageText, "LanguageUtil.getLanguage….device_week_sun_android)");
                    arrayList.add(languageText);
                    break;
                case 2:
                    String languageText2 = LanguageUtil.getLanguageText(R.string.device_week_mon_android);
                    Intrinsics.checkExpressionValueIsNotNull(languageText2, "LanguageUtil.getLanguage….device_week_mon_android)");
                    arrayList.add(languageText2);
                    break;
                case 3:
                    String languageText3 = LanguageUtil.getLanguageText(R.string.device_week_tue_android);
                    Intrinsics.checkExpressionValueIsNotNull(languageText3, "LanguageUtil.getLanguage….device_week_tue_android)");
                    arrayList.add(languageText3);
                    break;
                case 4:
                    String languageText4 = LanguageUtil.getLanguageText(R.string.device_week_wed_android);
                    Intrinsics.checkExpressionValueIsNotNull(languageText4, "LanguageUtil.getLanguage….device_week_wed_android)");
                    arrayList.add(languageText4);
                    break;
                case 5:
                    String languageText5 = LanguageUtil.getLanguageText(R.string.device_week_thu_android);
                    Intrinsics.checkExpressionValueIsNotNull(languageText5, "LanguageUtil.getLanguage….device_week_thu_android)");
                    arrayList.add(languageText5);
                    break;
                case 6:
                    String languageText6 = LanguageUtil.getLanguageText(R.string.device_week_fri_android);
                    Intrinsics.checkExpressionValueIsNotNull(languageText6, "LanguageUtil.getLanguage….device_week_fri_android)");
                    arrayList.add(languageText6);
                    break;
                case 7:
                    String languageText7 = LanguageUtil.getLanguageText(R.string.device_week_sat_android);
                    Intrinsics.checkExpressionValueIsNotNull(languageText7, "LanguageUtil.getLanguage….device_week_sat_android)");
                    arrayList.add(languageText7);
                    break;
            }
            calendar.add(5, 1);
        }
        return arrayList;
    }
}