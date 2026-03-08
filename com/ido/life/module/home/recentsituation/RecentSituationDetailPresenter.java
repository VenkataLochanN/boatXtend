package com.ido.life.module.home.recentsituation;

import android.graphics.Color;
import android.text.TextUtils;
import com.boat.Xtend.two.R;
import com.google.android.gms.fitness.FitnessActivities;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.callback.OtherProtocolCallBack;
import com.ido.ble.event.stat.one.d;
import com.ido.ble.protocol.model.CalorieAndDistanceGoal;
import com.ido.ble.protocol.model.Sport100Type;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.ResourceUtil;
import com.ido.life.base.BasePresenter;
import com.ido.life.bean.GradientBarPoint;
import com.ido.life.constants.Constants;
import com.ido.life.customview.RecentSituationProgressView;
import com.ido.life.customview.charter.RecentSituationWeekChart;
import com.ido.life.database.model.ActiveTimeDayData;
import com.ido.life.database.model.CalorieDayData;
import com.ido.life.database.model.UserTargetNew;
import com.ido.life.database.model.WalkDayData;
import com.ido.life.enums.StageInfoEnum;
import com.ido.life.module.home.HomeHelperKt;
import com.ido.life.module.home.fitness.FitnessDetailPresenter;
import com.ido.life.module.home.fitness.FitnessHelperKt;
import com.ido.life.util.DateUtil;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RunTimeUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.math.MathKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.MainCoroutineDispatcher;

/* JADX INFO: compiled from: RecentSituationDetailPresenter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\b!\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0006\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0003pqrB\u0005¢\u0006\u0002\u0010\u0004J\u0010\u0010(\u001a\u00020\b2\u0006\u0010)\u001a\u00020\bH\u0002J\u0010\u0010*\u001a\u00020\b2\u0006\u0010)\u001a\u00020\bH\u0002J\u0019\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\bH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010.J\u0019\u0010/\u001a\u00020,2\u0006\u0010-\u001a\u00020\bH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010.J\u0011\u00100\u001a\u00020,H\u0082@ø\u0001\u0000¢\u0006\u0002\u00101J\u0019\u00102\u001a\u00020,2\u0006\u0010-\u001a\u00020\bH\u0082@ø\u0001\u0000¢\u0006\u0002\u0010.J\u0011\u00103\u001a\u00020,H\u0082@ø\u0001\u0000¢\u0006\u0002\u00101J\u0010\u00104\u001a\u00020,2\u0006\u00105\u001a\u00020\u0006H\u0002J\b\u00106\u001a\u00020,H\u0002J\b\u00107\u001a\u00020,H\u0002J\b\u00108\u001a\u00020,H\u0002J\b\u00109\u001a\u00020,H\u0002J\b\u0010:\u001a\u00020,H\u0002J\b\u0010;\u001a\u00020,H\u0002J\b\u0010<\u001a\u00020,H\u0002J\b\u0010=\u001a\u00020,H\u0002J\b\u0010>\u001a\u00020,H\u0002J\b\u0010?\u001a\u00020,H\u0002J\b\u0010@\u001a\u00020,H\u0002J\b\u0010A\u001a\u00020,H\u0002J\b\u0010B\u001a\u00020,H\u0002J\b\u0010C\u001a\u00020,H\u0002J\b\u0010D\u001a\u00020,H\u0002J\b\u0010E\u001a\u00020,H\u0002J\b\u0010F\u001a\u00020,H\u0002J\b\u0010G\u001a\u00020,H\u0002J\u0011\u0010H\u001a\u00020,H\u0082@ø\u0001\u0000¢\u0006\u0002\u00101J\b\u0010I\u001a\u00020,H\u0002J\u001e\u0010J\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\u0006\u0010K\u001a\u00020\b2\u0006\u0010L\u001a\u00020\bH\u0002J\f\u0010M\u001a\b\u0012\u0004\u0012\u00020N0\u0019J\u0011\u0010O\u001a\u00020,H\u0082@ø\u0001\u0000¢\u0006\u0002\u00101J\b\u0010P\u001a\u00020,H\u0002J\f\u0010Q\u001a\b\u0012\u0004\u0012\u00020N0\u0019J\u0011\u0010R\u001a\u00020,H\u0082@ø\u0001\u0000¢\u0006\u0002\u00101J\b\u0010S\u001a\u00020,H\u0002J\b\u0010T\u001a\u00020UH\u0002J\u0006\u0010V\u001a\u00020,J\u0018\u0010W\u001a\u00020\u00062\u0006\u0010X\u001a\u00020Y2\u0006\u0010Z\u001a\u00020YH\u0002J\u0018\u0010W\u001a\u00020\u00062\u0006\u0010[\u001a\u00020\u00062\u0006\u0010\\\u001a\u00020\u0006H\u0002J\b\u0010]\u001a\u00020\bH\u0002J\b\u0010^\u001a\u00020\bH\u0002J\b\u0010_\u001a\u00020`H\u0002J\u0011\u0010a\u001a\u00020,H\u0082@ø\u0001\u0000¢\u0006\u0002\u00101J\b\u0010b\u001a\u00020,H\u0002J\f\u0010c\u001a\b\u0012\u0004\u0012\u00020N0\u0019J\u0012\u0010d\u001a\u00020,2\b\u0010e\u001a\u0004\u0018\u00010fH\u0016J\u0012\u0010g\u001a\u00020,2\b\u0010e\u001a\u0004\u0018\u00010fH\u0016J\u0011\u0010h\u001a\u00020,H\u0082@ø\u0001\u0000¢\u0006\u0002\u00101J\u0016\u0010i\u001a\u00020,2\u0006\u0010j\u001a\u00020\b2\u0006\u0010k\u001a\u00020\bJ\u0006\u0010l\u001a\u00020mJ\u0006\u0010n\u001a\u00020mJ\u0006\u0010o\u001a\u00020mR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\f\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\rR\u000e\u0010\u000e\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082D¢\u0006\u0002\n\u0000R\u0012\u0010\u0011\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\rR\u001a\u0010\u0012\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010!\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0010X\u0082D¢\u0006\u0002\n\u0000R\u0012\u0010&\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\rR\u0012\u0010'\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\r\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006s"}, d2 = {"Lcom/ido/life/module/home/recentsituation/RecentSituationDetailPresenter;", "Lcom/ido/life/base/BasePresenter;", "Lcom/ido/life/module/home/recentsituation/IRecentSituationDetailView;", "Lcom/ido/ble/callback/OtherProtocolCallBack$ICallBack;", "()V", "TAG", "", "Y_MAX_ACTIVITY_CALORIE", "", "Y_MAX_ACTIVITY_TIME", "Y_MAX_WALK", "mBarYMax", "mCalorieTarget", "Ljava/lang/Integer;", "mDefaultBarColor", "mDefaultBarRadius", "", "mExerciseTimeTarget", "mPageType", "getMPageType", "()I", "setMPageType", "(I)V", "mPastAvg", "mPastChartData", "", "Lcom/ido/life/bean/GradientBarPoint;", "mPastEndDate", "mPastHasDataDayCount", "mPastStartDate", "mReallyStage", "Lcom/ido/life/enums/StageInfoEnum;", "mRecentAvg", "mRecentChartData", "mRecentEndDate", "mRecentHasDataDayCount", "mRecentStartDate", "mRoundAnglePercent", "mWalkStep", "mWalkTimeTarget", "ajustCalorieMax", "calorie", "ajustCalorieMin", "calculateActivityCalorieLineChart", "", "weekCount", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "calculateWalkLineChart", "caluteActiveTimeAndCompareState", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "caluteActivityTimeLineChart", "caluteCalorieAvgAndCompareState", "caluteDate", "taskTag", "calutePastActivityCalorieChartData", "calutePastActivityCalorieChartDataByDay", "calutePastActivityCalorieChartDataByWeek", "calutePastActivityTimeChartData", "calutePastActivityTimeChartDataByDay", "calutePastActivityTimeChartDataByWeek", "calutePastWalkChartData", "calutePastWalkChartDataByDay", "calutePastWalkChartDataByWeek", "caluteRecentActivityCalorieChartData", "caluteRecentActivityCalorieChartDataByDay", "caluteRecentActivityCalorieChartDataByWeek", "caluteRecentActivityTimeChartData", "caluteRecentActivityTimeChartDataByDay", "caluteRecentActivityTimeChartDataByWeek", "caluteRecentWalkChartData", "caluteRecentWalkChartDataByDay", "caluteRecentWalkChartDataByWeek", "caluteWalkAndCompareState", "clearData", "generateDefaultDayChartData", "dayCount", "yMax", "getActivityCalorie", "Lcom/ido/life/customview/RecentSituationProgressView$DividerProperty;", "getActivityCaloriePageData", "getActivityCalorieTarget", "getActivityTime", "getActivityTimePageData", "getActivityTimeTarget", "getLastestUserTarget", "Lcom/ido/life/database/model/UserTargetNew;", "getPageData", "getPastDateDesc", "startCalendar", "Ljava/util/Calendar;", "endCalendar", "startDate", "endDate", "getPastDayCount", "getRecentDayCount", "getUserId", "", "getWalkPageData", "getWalkTarget", "getWalking", "onFailed", "type", "Lcom/ido/ble/callback/OtherProtocolCallBack$SettingType;", "onSuccess", "processBar", "saveUserTarget", "fTarget", "sTarget", "supportActiveCalorieTarget", "", "supportActiveTimeTarget", "supportWalkTarget", "RecommandActiceBean", "RecommandActive", "RecommandActiveDesc", "app_release"}, k = 1, mv = {1, 1, 16})
public final class RecentSituationDetailPresenter extends BasePresenter<IRecentSituationDetailView> implements OtherProtocolCallBack.ICallBack {
    private int mBarYMax;
    private Integer mCalorieTarget;
    private Integer mExerciseTimeTarget;
    private float mPastAvg;
    private String mPastEndDate;
    private int mPastHasDataDayCount;
    private String mPastStartDate;
    private float mRecentAvg;
    private String mRecentEndDate;
    private int mRecentHasDataDayCount;
    private String mRecentStartDate;
    private Integer mWalkStep;
    private Integer mWalkTimeTarget;
    private final String TAG = "RecentSituationDetailPresenter";
    private final int Y_MAX_ACTIVITY_CALORIE = 500;
    private final int Y_MAX_ACTIVITY_TIME = 30;
    private final int Y_MAX_WALK = 12;
    private int mPageType = RecentSituationDetailActivity.INSTANCE.getPAGE_ACTIVITY_CALORIE();
    private final float mDefaultBarRadius = 0.05f;
    private final int mDefaultBarColor = Color.parseColor("#F6F7F9");
    private StageInfoEnum mReallyStage = StageInfoEnum.NODATA;
    private List<GradientBarPoint> mRecentChartData = new ArrayList();
    private List<GradientBarPoint> mPastChartData = new ArrayList();
    private final float mRoundAnglePercent = 0.5f;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[StageInfoEnum.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;
        public static final /* synthetic */ int[] $EnumSwitchMapping$3;
        public static final /* synthetic */ int[] $EnumSwitchMapping$4;
        public static final /* synthetic */ int[] $EnumSwitchMapping$5;
        public static final /* synthetic */ int[] $EnumSwitchMapping$6;
        public static final /* synthetic */ int[] $EnumSwitchMapping$7;
        public static final /* synthetic */ int[] $EnumSwitchMapping$8;
        public static final /* synthetic */ int[] $EnumSwitchMapping$9;

        static {
            $EnumSwitchMapping$0[StageInfoEnum.THIRD.ordinal()] = 1;
            $EnumSwitchMapping$0[StageInfoEnum.TERMINAL.ordinal()] = 2;
            $EnumSwitchMapping$1 = new int[StageInfoEnum.values().length];
            $EnumSwitchMapping$1[StageInfoEnum.NODATA.ordinal()] = 1;
            $EnumSwitchMapping$1[StageInfoEnum.PRIMARY.ordinal()] = 2;
            $EnumSwitchMapping$1[StageInfoEnum.SECOND.ordinal()] = 3;
            $EnumSwitchMapping$2 = new int[StageInfoEnum.values().length];
            $EnumSwitchMapping$2[StageInfoEnum.NODATA.ordinal()] = 1;
            $EnumSwitchMapping$2[StageInfoEnum.PRIMARY.ordinal()] = 2;
            $EnumSwitchMapping$2[StageInfoEnum.SECOND.ordinal()] = 3;
            $EnumSwitchMapping$3 = new int[StageInfoEnum.values().length];
            $EnumSwitchMapping$3[StageInfoEnum.NODATA.ordinal()] = 1;
            $EnumSwitchMapping$3[StageInfoEnum.PRIMARY.ordinal()] = 2;
            $EnumSwitchMapping$3[StageInfoEnum.SECOND.ordinal()] = 3;
            $EnumSwitchMapping$4 = new int[StageInfoEnum.values().length];
            $EnumSwitchMapping$4[StageInfoEnum.NODATA.ordinal()] = 1;
            $EnumSwitchMapping$5 = new int[StageInfoEnum.values().length];
            $EnumSwitchMapping$5[StageInfoEnum.NODATA.ordinal()] = 1;
            $EnumSwitchMapping$6 = new int[StageInfoEnum.values().length];
            $EnumSwitchMapping$6[StageInfoEnum.NODATA.ordinal()] = 1;
            $EnumSwitchMapping$7 = new int[StageInfoEnum.values().length];
            $EnumSwitchMapping$7[StageInfoEnum.NODATA.ordinal()] = 1;
            $EnumSwitchMapping$7[StageInfoEnum.PRIMARY.ordinal()] = 2;
            $EnumSwitchMapping$7[StageInfoEnum.SECOND.ordinal()] = 3;
            $EnumSwitchMapping$8 = new int[StageInfoEnum.values().length];
            $EnumSwitchMapping$8[StageInfoEnum.NODATA.ordinal()] = 1;
            $EnumSwitchMapping$8[StageInfoEnum.PRIMARY.ordinal()] = 2;
            $EnumSwitchMapping$8[StageInfoEnum.SECOND.ordinal()] = 3;
            $EnumSwitchMapping$9 = new int[StageInfoEnum.values().length];
            $EnumSwitchMapping$9[StageInfoEnum.NODATA.ordinal()] = 1;
            $EnumSwitchMapping$9[StageInfoEnum.PRIMARY.ordinal()] = 2;
            $EnumSwitchMapping$9[StageInfoEnum.SECOND.ordinal()] = 3;
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.recentsituation.RecentSituationDetailPresenter$calculateActivityCalorieLineChart$1, reason: invalid class name */
    /* JADX INFO: compiled from: RecentSituationDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0082@"}, d2 = {"calculateActivityCalorieLineChart", "", "weekCount", "", "continuation", "Lkotlin/coroutines/Continuation;", ""}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.recentsituation.RecentSituationDetailPresenter", f = "RecentSituationDetailPresenter.kt", i = {0, 0, 0, 0, 0, 0, 0}, l = {1847}, m = "calculateActivityCalorieLineChart", n = {"this", "weekCount", "lineChartList", "weekStart", Constants.AppPackage.CALENDAR, "xLabelList", "maxCalorie"}, s = {"L$0", "I$0", "L$1", "I$1", "L$2", "L$3", "L$4"})
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return RecentSituationDetailPresenter.this.calculateActivityCalorieLineChart(0, this);
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.recentsituation.RecentSituationDetailPresenter$getActivityCaloriePageData$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RecentSituationDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0082@"}, d2 = {"getActivityCaloriePageData", "", "continuation", "Lkotlin/coroutines/Continuation;", ""}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.recentsituation.RecentSituationDetailPresenter", f = "RecentSituationDetailPresenter.kt", i = {0, 0, 0, 0, 0, 0, 1, 2, 3, 3, 4, 4}, l = {167, 206, 214, 246, 247}, m = "getActivityCaloriePageData", n = {"this", "bmr", "calorieMax", "calorieMin", "startCalendar", "endCalendar", "this", "this", "this", "todayCalorie", "this", "todayCalorie"}, s = {"L$0", "I$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$0", "L$0", "L$1", "L$0", "L$1"})
    static final class C03951 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        C03951(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return RecentSituationDetailPresenter.this.getActivityCaloriePageData(this);
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.recentsituation.RecentSituationDetailPresenter$getActivityTimePageData$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RecentSituationDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0082@"}, d2 = {"getActivityTimePageData", "", "continuation", "Lkotlin/coroutines/Continuation;", ""}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.recentsituation.RecentSituationDetailPresenter", f = "RecentSituationDetailPresenter.kt", i = {0, 0, 0, 1, 2, 3, 3, 4, 4}, l = {260, 298, 306, 340, 341}, m = "getActivityTimePageData", n = {"this", "startCalendar", "endCalendar", "this", "this", "this", "todayTimeDayData", "this", "todayTimeDayData"}, s = {"L$0", "L$1", "L$2", "L$0", "L$0", "L$0", "L$1", "L$0", "L$1"})
    static final class C03981 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C03981(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return RecentSituationDetailPresenter.this.getActivityTimePageData(this);
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.recentsituation.RecentSituationDetailPresenter$getWalkPageData$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RecentSituationDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0082@"}, d2 = {"getWalkPageData", "", "continuation", "Lkotlin/coroutines/Continuation;", ""}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.recentsituation.RecentSituationDetailPresenter", f = "RecentSituationDetailPresenter.kt", i = {0, 0, 0, 1, 2, 3, 3, 4, 4}, l = {354, 386, 394, 426, 427}, m = "getWalkPageData", n = {"this", "startCalendar", "endCalendar", "this", "this", "this", "todayWalkDayData", "this", "todayWalkDayData"}, s = {"L$0", "L$1", "L$2", "L$0", "L$0", "L$0", "L$1", "L$0", "L$1"})
    static final class C04021 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C04021(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return RecentSituationDetailPresenter.this.getWalkPageData(this);
        }
    }

    public static final /* synthetic */ IRecentSituationDetailView access$getView(RecentSituationDetailPresenter recentSituationDetailPresenter) {
        return recentSituationDetailPresenter.getView();
    }

    public final int getMPageType() {
        return this.mPageType;
    }

    public final void setMPageType(int i) {
        this.mPageType = i;
    }

    public final void getPageData() {
        clearData();
        IRecentSituationDetailView view = getView();
        if (view != null) {
            view.showDialog();
        }
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C04011(null), 3, null);
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.recentsituation.RecentSituationDetailPresenter$getPageData$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RecentSituationDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.recentsituation.RecentSituationDetailPresenter$getPageData$1", f = "RecentSituationDetailPresenter.kt", i = {0, 1, 2, 3}, l = {127, Sport100Type.SPORT_TYPE_MOUNTAINEERING_MACHINE, 133, 136}, m = "invokeSuspend", n = {"$this$launch", "$this$launch", "$this$launch", "$this$launch"}, s = {"L$0", "L$0", "L$0", "L$0"})
    static final class C04011 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        int label;
        private CoroutineScope p$;

        C04011(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C04011 c04011 = RecentSituationDetailPresenter.this.new C04011(completion);
            c04011.p$ = (CoroutineScope) obj;
            return c04011;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C04011) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            CoroutineScope coroutineScope;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                coroutineScope = this.p$;
                int mPageType = RecentSituationDetailPresenter.this.getMPageType();
                if (mPageType == RecentSituationDetailActivity.INSTANCE.getPAGE_ACTIVITY_CALORIE()) {
                    RecentSituationDetailPresenter recentSituationDetailPresenter = RecentSituationDetailPresenter.this;
                    this.L$0 = coroutineScope;
                    this.label = 1;
                    if (recentSituationDetailPresenter.getActivityCaloriePageData(this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else if (mPageType == RecentSituationDetailActivity.INSTANCE.getPAGE_ACTIVITY_TIME()) {
                    RecentSituationDetailPresenter recentSituationDetailPresenter2 = RecentSituationDetailPresenter.this;
                    this.L$0 = coroutineScope;
                    this.label = 2;
                    if (recentSituationDetailPresenter2.getActivityTimePageData(this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    RecentSituationDetailPresenter recentSituationDetailPresenter3 = RecentSituationDetailPresenter.this;
                    this.L$0 = coroutineScope;
                    this.label = 3;
                    if (recentSituationDetailPresenter3.getWalkPageData(this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
            } else {
                if (i != 1 && i != 2 && i != 3) {
                    if (i == 4) {
                        ResultKt.throwOnFailure(obj);
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                coroutineScope = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            MainCoroutineDispatcher main = Dispatchers.getMain();
            C01121 c01121 = new C01121(null);
            this.L$0 = coroutineScope;
            this.label = 4;
            if (BuildersKt.withContext(main, c01121, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: com.ido.life.module.home.recentsituation.RecentSituationDetailPresenter$getPageData$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: RecentSituationDetailPresenter.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
        @DebugMetadata(c = "com.ido.life.module.home.recentsituation.RecentSituationDetailPresenter$getPageData$1$1", f = "RecentSituationDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class C01121 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            private CoroutineScope p$;

            C01121(Continuation continuation) {
                super(2, continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                Intrinsics.checkParameterIsNotNull(completion, "completion");
                C01121 c01121 = C04011.this.new C01121(completion);
                c01121.p$ = (CoroutineScope) obj;
                return c01121;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C01121) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.p$;
                IRecentSituationDetailView iRecentSituationDetailViewAccess$getView = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                if (iRecentSituationDetailViewAccess$getView != null) {
                    iRecentSituationDetailViewAccess$getView.dismissDialog();
                }
                IRecentSituationDetailView iRecentSituationDetailViewAccess$getView2 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                if (iRecentSituationDetailViewAccess$getView2 != null) {
                    iRecentSituationDetailViewAccess$getView2.refreshPastChart(true);
                }
                IRecentSituationDetailView iRecentSituationDetailViewAccess$getView3 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                if (iRecentSituationDetailViewAccess$getView3 != null) {
                    iRecentSituationDetailViewAccess$getView3.refreshRecentChart(true);
                }
                IRecentSituationDetailView iRecentSituationDetailViewAccess$getView4 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                if (iRecentSituationDetailViewAccess$getView4 == null) {
                    return null;
                }
                iRecentSituationDetailViewAccess$getView4.refreshLineChart(true);
                return Unit.INSTANCE;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x01d0  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0236  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0260 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:88:0x026e A[RETURN] */
    /* JADX WARN: Type inference failed for: r0v26, types: [T, java.util.Calendar] */
    /* JADX WARN: Type inference failed for: r0v28, types: [T, java.util.Calendar] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final /* synthetic */ java.lang.Object getActivityCaloriePageData(kotlin.coroutines.Continuation<? super kotlin.Unit> r20) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 626
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.home.recentsituation.RecentSituationDetailPresenter.getActivityCaloriePageData(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.recentsituation.RecentSituationDetailPresenter$getActivityCaloriePageData$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RecentSituationDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.recentsituation.RecentSituationDetailPresenter$getActivityCaloriePageData$2", f = "RecentSituationDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03962 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.IntRef $calorieMax;
        final /* synthetic */ Ref.IntRef $calorieMin;
        final /* synthetic */ Ref.ObjectRef $endCalendar;
        final /* synthetic */ Ref.ObjectRef $startCalendar;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03962(Ref.IntRef intRef, Ref.IntRef intRef2, Ref.ObjectRef objectRef, Ref.ObjectRef objectRef2, Continuation continuation) {
            super(2, continuation);
            this.$calorieMax = intRef;
            this.$calorieMin = intRef2;
            this.$startCalendar = objectRef;
            this.$endCalendar = objectRef2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03962 c03962 = RecentSituationDetailPresenter.this.new C03962(this.$calorieMax, this.$calorieMin, this.$startCalendar, this.$endCalendar, completion);
            c03962.p$ = (CoroutineScope) obj;
            return c03962;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03962) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.p$;
                IRecentSituationDetailView iRecentSituationDetailViewAccess$getView = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                if (iRecentSituationDetailViewAccess$getView != null) {
                    iRecentSituationDetailViewAccess$getView.updateProgressMaxmin(this.$calorieMax.element, this.$calorieMin.element);
                }
                IRecentSituationDetailView iRecentSituationDetailViewAccess$getView2 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                if (iRecentSituationDetailViewAccess$getView2 != null) {
                    iRecentSituationDetailViewAccess$getView2.updateRecentAvg(0.0f);
                }
                IRecentSituationDetailView iRecentSituationDetailViewAccess$getView3 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                if (iRecentSituationDetailViewAccess$getView3 != null) {
                    iRecentSituationDetailViewAccess$getView3.updateProgress(0);
                }
                IRecentSituationDetailView iRecentSituationDetailViewAccess$getView4 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                if (iRecentSituationDetailViewAccess$getView4 != null) {
                    iRecentSituationDetailViewAccess$getView4.updateProgress(0);
                }
                IRecentSituationDetailView iRecentSituationDetailViewAccess$getView5 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                if (iRecentSituationDetailViewAccess$getView5 != null) {
                    iRecentSituationDetailViewAccess$getView5.updateRecentChartAvg(-1.0f);
                }
                IRecentSituationDetailView iRecentSituationDetailViewAccess$getView6 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                if (iRecentSituationDetailViewAccess$getView6 != null) {
                    iRecentSituationDetailViewAccess$getView6.updatePastChartAvg(-1.0f);
                }
                IRecentSituationDetailView iRecentSituationDetailViewAccess$getView7 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                if (iRecentSituationDetailViewAccess$getView7 != null) {
                    RecentSituationDetailPresenter recentSituationDetailPresenter = RecentSituationDetailPresenter.this;
                    Calendar startCalendar = (Calendar) this.$startCalendar.element;
                    Intrinsics.checkExpressionValueIsNotNull(startCalendar, "startCalendar");
                    Calendar endCalendar = (Calendar) this.$endCalendar.element;
                    Intrinsics.checkExpressionValueIsNotNull(endCalendar, "endCalendar");
                    iRecentSituationDetailViewAccess$getView7.updatePastChartDesc(recentSituationDetailPresenter.getPastDateDesc(startCalendar, endCalendar));
                }
                IRecentSituationDetailView iRecentSituationDetailViewAccess$getView8 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                if (iRecentSituationDetailViewAccess$getView8 != null) {
                    String languageText = LanguageUtil.getLanguageText(R.string.recent_day_format);
                    Intrinsics.checkExpressionValueIsNotNull(languageText, "LanguageUtil.getLanguage…string.recent_day_format)");
                    Object[] objArr = {Boxing.boxInt((StageInfoEnum.PRIMARY.getMRecentEndDayCount() - StageInfoEnum.PRIMARY.getMRecentStartDayCount()) + 1)};
                    String str = String.format(languageText, Arrays.copyOf(objArr, objArr.length));
                    Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(this, *args)");
                    iRecentSituationDetailViewAccess$getView8.updateRecentChartDesc(str);
                }
                IRecentSituationDetailView iRecentSituationDetailViewAccess$getView9 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                if (iRecentSituationDetailViewAccess$getView9 != null) {
                    iRecentSituationDetailViewAccess$getView9.updatePassDayCount((StageInfoEnum.PRIMARY.getMPastEndDayCount() - StageInfoEnum.PRIMARY.getMPastStartDayCount()) + 1, 0);
                }
                IRecentSituationDetailView iRecentSituationDetailViewAccess$getView10 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                if (iRecentSituationDetailViewAccess$getView10 != null) {
                    iRecentSituationDetailViewAccess$getView10.updateRecentDayCount((StageInfoEnum.PRIMARY.getMRecentEndDayCount() - StageInfoEnum.PRIMARY.getMRecentStartDayCount()) + 1, 0);
                }
                IRecentSituationDetailView iRecentSituationDetailViewAccess$getView11 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                if (iRecentSituationDetailViewAccess$getView11 != null) {
                    iRecentSituationDetailViewAccess$getView11.updateRecentBarXMaxmin(RecentSituationDetailPresenter.this.getRecentDayCount() + 1, 1);
                }
                IRecentSituationDetailView iRecentSituationDetailViewAccess$getView12 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                if (iRecentSituationDetailViewAccess$getView12 != null) {
                    iRecentSituationDetailViewAccess$getView12.updatePastBarXMaxmin(RecentSituationDetailPresenter.this.getPastDayCount(), 1);
                }
                IRecentSituationDetailView iRecentSituationDetailViewAccess$getView13 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                if (iRecentSituationDetailViewAccess$getView13 != null) {
                    iRecentSituationDetailViewAccess$getView13.updateBarYmaxmin(RecentSituationDetailPresenter.this.Y_MAX_ACTIVITY_CALORIE, 0);
                }
                IRecentSituationDetailView iRecentSituationDetailViewAccess$getView14 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                if (iRecentSituationDetailViewAccess$getView14 != null) {
                    iRecentSituationDetailViewAccess$getView14.updatePastChartData(RecentSituationDetailPresenter.this.generateDefaultDayChartData((StageInfoEnum.PRIMARY.getMPastEndDayCount() - StageInfoEnum.PRIMARY.getMPastStartDayCount()) + 1, RecentSituationDetailPresenter.this.Y_MAX_ACTIVITY_CALORIE));
                }
                IRecentSituationDetailView iRecentSituationDetailViewAccess$getView15 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                if (iRecentSituationDetailViewAccess$getView15 == null) {
                    return null;
                }
                iRecentSituationDetailViewAccess$getView15.updateRecentChartData(RecentSituationDetailPresenter.this.generateDefaultDayChartData((StageInfoEnum.PRIMARY.getMRecentEndDayCount() - StageInfoEnum.PRIMARY.getMRecentStartDayCount()) + 1, RecentSituationDetailPresenter.this.Y_MAX_ACTIVITY_CALORIE));
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.recentsituation.RecentSituationDetailPresenter$getActivityCaloriePageData$3, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RecentSituationDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.recentsituation.RecentSituationDetailPresenter$getActivityCaloriePageData$3", f = "RecentSituationDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03973 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        C03973(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03973 c03973 = RecentSituationDetailPresenter.this.new C03973(completion);
            c03973.p$ = (CoroutineScope) obj;
            return c03973;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03973) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.p$;
                IRecentSituationDetailView iRecentSituationDetailViewAccess$getView = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                if (iRecentSituationDetailViewAccess$getView != null) {
                    RecentSituationDetailPresenter recentSituationDetailPresenter = RecentSituationDetailPresenter.this;
                    String str = recentSituationDetailPresenter.mPastStartDate;
                    if (str == null) {
                        Intrinsics.throwNpe();
                    }
                    String str2 = RecentSituationDetailPresenter.this.mPastEndDate;
                    if (str2 == null) {
                        Intrinsics.throwNpe();
                    }
                    iRecentSituationDetailViewAccess$getView.updatePastChartDesc(recentSituationDetailPresenter.getPastDateDesc(str, str2));
                }
                IRecentSituationDetailView iRecentSituationDetailViewAccess$getView2 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                if (iRecentSituationDetailViewAccess$getView2 == null) {
                    return null;
                }
                String languageText = LanguageUtil.getLanguageText(R.string.recent_day_format);
                Intrinsics.checkExpressionValueIsNotNull(languageText, "LanguageUtil.getLanguage…string.recent_day_format)");
                Object[] objArr = {Boxing.boxInt(RecentSituationDetailPresenter.this.getRecentDayCount())};
                String str3 = String.format(languageText, Arrays.copyOf(objArr, objArr.length));
                Intrinsics.checkNotNullExpressionValue(str3, "java.lang.String.format(this, *args)");
                iRecentSituationDetailViewAccess$getView2.updateRecentChartDesc(str3);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0155  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x01c3  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x01f4 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0201 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    /* JADX WARN: Type inference failed for: r4v4, types: [T, java.util.Calendar] */
    /* JADX WARN: Type inference failed for: r7v2, types: [T, java.util.Calendar] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final /* synthetic */ java.lang.Object getActivityTimePageData(kotlin.coroutines.Continuation<? super kotlin.Unit> r19) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 517
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.home.recentsituation.RecentSituationDetailPresenter.getActivityTimePageData(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.recentsituation.RecentSituationDetailPresenter$getActivityTimePageData$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RecentSituationDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.recentsituation.RecentSituationDetailPresenter$getActivityTimePageData$2", f = "RecentSituationDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03992 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.ObjectRef $endCalendar;
        final /* synthetic */ Ref.ObjectRef $startCalendar;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03992(Ref.ObjectRef objectRef, Ref.ObjectRef objectRef2, Continuation continuation) {
            super(2, continuation);
            this.$startCalendar = objectRef;
            this.$endCalendar = objectRef2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03992 c03992 = RecentSituationDetailPresenter.this.new C03992(this.$startCalendar, this.$endCalendar, completion);
            c03992.p$ = (CoroutineScope) obj;
            return c03992;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03992) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.p$;
                IRecentSituationDetailView iRecentSituationDetailViewAccess$getView = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                if (iRecentSituationDetailViewAccess$getView != null) {
                    iRecentSituationDetailViewAccess$getView.updateRecentAvg(0.0f);
                }
                IRecentSituationDetailView iRecentSituationDetailViewAccess$getView2 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                if (iRecentSituationDetailViewAccess$getView2 != null) {
                    iRecentSituationDetailViewAccess$getView2.updateProgressMaxmin(60, 5);
                }
                IRecentSituationDetailView iRecentSituationDetailViewAccess$getView3 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                if (iRecentSituationDetailViewAccess$getView3 != null) {
                    iRecentSituationDetailViewAccess$getView3.updateProgress(0);
                }
                IRecentSituationDetailView iRecentSituationDetailViewAccess$getView4 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                if (iRecentSituationDetailViewAccess$getView4 != null) {
                    iRecentSituationDetailViewAccess$getView4.updateRecentChartAvg(-1.0f);
                }
                IRecentSituationDetailView iRecentSituationDetailViewAccess$getView5 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                if (iRecentSituationDetailViewAccess$getView5 != null) {
                    iRecentSituationDetailViewAccess$getView5.updatePastChartAvg(-1.0f);
                }
                IRecentSituationDetailView iRecentSituationDetailViewAccess$getView6 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                if (iRecentSituationDetailViewAccess$getView6 != null) {
                    RecentSituationDetailPresenter recentSituationDetailPresenter = RecentSituationDetailPresenter.this;
                    Calendar startCalendar = (Calendar) this.$startCalendar.element;
                    Intrinsics.checkExpressionValueIsNotNull(startCalendar, "startCalendar");
                    Calendar endCalendar = (Calendar) this.$endCalendar.element;
                    Intrinsics.checkExpressionValueIsNotNull(endCalendar, "endCalendar");
                    iRecentSituationDetailViewAccess$getView6.updatePastChartDesc(recentSituationDetailPresenter.getPastDateDesc(startCalendar, endCalendar));
                }
                IRecentSituationDetailView iRecentSituationDetailViewAccess$getView7 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                if (iRecentSituationDetailViewAccess$getView7 != null) {
                    String languageText = LanguageUtil.getLanguageText(R.string.recent_day_format);
                    Intrinsics.checkExpressionValueIsNotNull(languageText, "LanguageUtil.getLanguage…string.recent_day_format)");
                    Object[] objArr = {Boxing.boxInt((StageInfoEnum.PRIMARY.getMRecentEndDayCount() - StageInfoEnum.PRIMARY.getMRecentStartDayCount()) + 1)};
                    String str = String.format(languageText, Arrays.copyOf(objArr, objArr.length));
                    Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(this, *args)");
                    iRecentSituationDetailViewAccess$getView7.updateRecentChartDesc(str);
                }
                IRecentSituationDetailView iRecentSituationDetailViewAccess$getView8 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                if (iRecentSituationDetailViewAccess$getView8 != null) {
                    iRecentSituationDetailViewAccess$getView8.updatePassDayCount((StageInfoEnum.PRIMARY.getMPastEndDayCount() - StageInfoEnum.PRIMARY.getMPastStartDayCount()) + 1, 0);
                }
                IRecentSituationDetailView iRecentSituationDetailViewAccess$getView9 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                if (iRecentSituationDetailViewAccess$getView9 != null) {
                    iRecentSituationDetailViewAccess$getView9.updateRecentDayCount((StageInfoEnum.PRIMARY.getMRecentEndDayCount() - StageInfoEnum.PRIMARY.getMRecentStartDayCount()) + 1, 0);
                }
                IRecentSituationDetailView iRecentSituationDetailViewAccess$getView10 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                if (iRecentSituationDetailViewAccess$getView10 != null) {
                    iRecentSituationDetailViewAccess$getView10.updateRecentBarXMaxmin(RecentSituationDetailPresenter.this.getRecentDayCount() + 1, 1);
                }
                IRecentSituationDetailView iRecentSituationDetailViewAccess$getView11 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                if (iRecentSituationDetailViewAccess$getView11 != null) {
                    iRecentSituationDetailViewAccess$getView11.updatePastBarXMaxmin(RecentSituationDetailPresenter.this.getPastDayCount(), 1);
                }
                IRecentSituationDetailView iRecentSituationDetailViewAccess$getView12 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                if (iRecentSituationDetailViewAccess$getView12 != null) {
                    iRecentSituationDetailViewAccess$getView12.updateBarYmaxmin(RecentSituationDetailPresenter.this.Y_MAX_ACTIVITY_TIME, 0);
                }
                IRecentSituationDetailView iRecentSituationDetailViewAccess$getView13 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                if (iRecentSituationDetailViewAccess$getView13 != null) {
                    iRecentSituationDetailViewAccess$getView13.updatePastChartData(RecentSituationDetailPresenter.this.generateDefaultDayChartData((StageInfoEnum.PRIMARY.getMPastEndDayCount() - StageInfoEnum.PRIMARY.getMPastStartDayCount()) + 1, RecentSituationDetailPresenter.this.Y_MAX_ACTIVITY_TIME));
                }
                IRecentSituationDetailView iRecentSituationDetailViewAccess$getView14 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                if (iRecentSituationDetailViewAccess$getView14 == null) {
                    return null;
                }
                iRecentSituationDetailViewAccess$getView14.updateRecentChartData(RecentSituationDetailPresenter.this.generateDefaultDayChartData((StageInfoEnum.PRIMARY.getMRecentEndDayCount() - StageInfoEnum.PRIMARY.getMRecentStartDayCount()) + 1, RecentSituationDetailPresenter.this.Y_MAX_ACTIVITY_TIME));
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.recentsituation.RecentSituationDetailPresenter$getActivityTimePageData$3, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RecentSituationDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.recentsituation.RecentSituationDetailPresenter$getActivityTimePageData$3", f = "RecentSituationDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C04003 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        C04003(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C04003 c04003 = RecentSituationDetailPresenter.this.new C04003(completion);
            c04003.p$ = (CoroutineScope) obj;
            return c04003;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C04003) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.p$;
                IRecentSituationDetailView iRecentSituationDetailViewAccess$getView = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                if (iRecentSituationDetailViewAccess$getView != null) {
                    RecentSituationDetailPresenter recentSituationDetailPresenter = RecentSituationDetailPresenter.this;
                    String str = recentSituationDetailPresenter.mPastStartDate;
                    if (str == null) {
                        Intrinsics.throwNpe();
                    }
                    String str2 = RecentSituationDetailPresenter.this.mPastEndDate;
                    if (str2 == null) {
                        Intrinsics.throwNpe();
                    }
                    iRecentSituationDetailViewAccess$getView.updatePastChartDesc(recentSituationDetailPresenter.getPastDateDesc(str, str2));
                }
                IRecentSituationDetailView iRecentSituationDetailViewAccess$getView2 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                if (iRecentSituationDetailViewAccess$getView2 == null) {
                    return null;
                }
                String languageText = LanguageUtil.getLanguageText(R.string.recent_day_format);
                Intrinsics.checkExpressionValueIsNotNull(languageText, "LanguageUtil.getLanguage…string.recent_day_format)");
                Object[] objArr = {Boxing.boxInt(RecentSituationDetailPresenter.this.getRecentDayCount())};
                String str3 = String.format(languageText, Arrays.copyOf(objArr, objArr.length));
                Intrinsics.checkNotNullExpressionValue(str3, "java.lang.String.format(this, *args)");
                iRecentSituationDetailViewAccess$getView2.updateRecentChartDesc(str3);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0151  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x01c1  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x01e8 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x01f5 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /* JADX WARN: Type inference failed for: r2v4, types: [T, java.util.Calendar] */
    /* JADX WARN: Type inference failed for: r5v5, types: [T, java.util.Calendar] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final /* synthetic */ java.lang.Object getWalkPageData(kotlin.coroutines.Continuation<? super kotlin.Unit> r15) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 505
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.home.recentsituation.RecentSituationDetailPresenter.getWalkPageData(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.recentsituation.RecentSituationDetailPresenter$getWalkPageData$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RecentSituationDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.recentsituation.RecentSituationDetailPresenter$getWalkPageData$2", f = "RecentSituationDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C04032 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.ObjectRef $endCalendar;
        final /* synthetic */ Ref.ObjectRef $startCalendar;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C04032(Ref.ObjectRef objectRef, Ref.ObjectRef objectRef2, Continuation continuation) {
            super(2, continuation);
            this.$startCalendar = objectRef;
            this.$endCalendar = objectRef2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C04032 c04032 = RecentSituationDetailPresenter.this.new C04032(this.$startCalendar, this.$endCalendar, completion);
            c04032.p$ = (CoroutineScope) obj;
            return c04032;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C04032) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.p$;
                IRecentSituationDetailView iRecentSituationDetailViewAccess$getView = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                if (iRecentSituationDetailViewAccess$getView != null) {
                    iRecentSituationDetailViewAccess$getView.updateRecentAvg(0.0f);
                }
                IRecentSituationDetailView iRecentSituationDetailViewAccess$getView2 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                if (iRecentSituationDetailViewAccess$getView2 != null) {
                    iRecentSituationDetailViewAccess$getView2.updateProgressMaxmin(14, 6);
                }
                IRecentSituationDetailView iRecentSituationDetailViewAccess$getView3 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                if (iRecentSituationDetailViewAccess$getView3 != null) {
                    iRecentSituationDetailViewAccess$getView3.updateProgress(0);
                }
                IRecentSituationDetailView iRecentSituationDetailViewAccess$getView4 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                if (iRecentSituationDetailViewAccess$getView4 != null) {
                    iRecentSituationDetailViewAccess$getView4.updateRecentChartAvg(-1.0f);
                }
                IRecentSituationDetailView iRecentSituationDetailViewAccess$getView5 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                if (iRecentSituationDetailViewAccess$getView5 != null) {
                    iRecentSituationDetailViewAccess$getView5.updatePastChartAvg(-1.0f);
                }
                IRecentSituationDetailView iRecentSituationDetailViewAccess$getView6 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                if (iRecentSituationDetailViewAccess$getView6 != null) {
                    RecentSituationDetailPresenter recentSituationDetailPresenter = RecentSituationDetailPresenter.this;
                    Calendar startCalendar = (Calendar) this.$startCalendar.element;
                    Intrinsics.checkExpressionValueIsNotNull(startCalendar, "startCalendar");
                    Calendar endCalendar = (Calendar) this.$endCalendar.element;
                    Intrinsics.checkExpressionValueIsNotNull(endCalendar, "endCalendar");
                    iRecentSituationDetailViewAccess$getView6.updatePastChartDesc(recentSituationDetailPresenter.getPastDateDesc(startCalendar, endCalendar));
                }
                IRecentSituationDetailView iRecentSituationDetailViewAccess$getView7 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                if (iRecentSituationDetailViewAccess$getView7 != null) {
                    String languageText = LanguageUtil.getLanguageText(R.string.recent_day_format);
                    Intrinsics.checkExpressionValueIsNotNull(languageText, "LanguageUtil.getLanguage…string.recent_day_format)");
                    Object[] objArr = {Boxing.boxInt((StageInfoEnum.PRIMARY.getMRecentEndDayCount() - StageInfoEnum.PRIMARY.getMRecentStartDayCount()) + 1)};
                    String str = String.format(languageText, Arrays.copyOf(objArr, objArr.length));
                    Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(this, *args)");
                    iRecentSituationDetailViewAccess$getView7.updateRecentChartDesc(str);
                }
                IRecentSituationDetailView iRecentSituationDetailViewAccess$getView8 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                if (iRecentSituationDetailViewAccess$getView8 != null) {
                    iRecentSituationDetailViewAccess$getView8.updatePassDayCount(0, 0);
                }
                IRecentSituationDetailView iRecentSituationDetailViewAccess$getView9 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                if (iRecentSituationDetailViewAccess$getView9 != null) {
                    iRecentSituationDetailViewAccess$getView9.updateRecentDayCount(0, 0);
                }
                IRecentSituationDetailView iRecentSituationDetailViewAccess$getView10 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                if (iRecentSituationDetailViewAccess$getView10 != null) {
                    iRecentSituationDetailViewAccess$getView10.updateRecentBarXMaxmin(RecentSituationDetailPresenter.this.getRecentDayCount() + 1, 1);
                }
                IRecentSituationDetailView iRecentSituationDetailViewAccess$getView11 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                if (iRecentSituationDetailViewAccess$getView11 != null) {
                    iRecentSituationDetailViewAccess$getView11.updatePastBarXMaxmin(RecentSituationDetailPresenter.this.getPastDayCount(), 1);
                }
                IRecentSituationDetailView iRecentSituationDetailViewAccess$getView12 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                if (iRecentSituationDetailViewAccess$getView12 != null) {
                    iRecentSituationDetailViewAccess$getView12.updateBarYmaxmin(RecentSituationDetailPresenter.this.Y_MAX_WALK, 0);
                }
                IRecentSituationDetailView iRecentSituationDetailViewAccess$getView13 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                if (iRecentSituationDetailViewAccess$getView13 != null) {
                    iRecentSituationDetailViewAccess$getView13.updatePastChartData(RecentSituationDetailPresenter.this.generateDefaultDayChartData((StageInfoEnum.PRIMARY.getMPastEndDayCount() - StageInfoEnum.PRIMARY.getMPastStartDayCount()) + 1, RecentSituationDetailPresenter.this.Y_MAX_WALK));
                }
                IRecentSituationDetailView iRecentSituationDetailViewAccess$getView14 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                if (iRecentSituationDetailViewAccess$getView14 == null) {
                    return null;
                }
                iRecentSituationDetailViewAccess$getView14.updateRecentChartData(RecentSituationDetailPresenter.this.generateDefaultDayChartData((StageInfoEnum.PRIMARY.getMRecentEndDayCount() - StageInfoEnum.PRIMARY.getMRecentStartDayCount()) + 1, RecentSituationDetailPresenter.this.Y_MAX_WALK));
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.recentsituation.RecentSituationDetailPresenter$getWalkPageData$3, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RecentSituationDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.recentsituation.RecentSituationDetailPresenter$getWalkPageData$3", f = "RecentSituationDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C04043 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        C04043(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C04043 c04043 = RecentSituationDetailPresenter.this.new C04043(completion);
            c04043.p$ = (CoroutineScope) obj;
            return c04043;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C04043) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.p$;
                IRecentSituationDetailView iRecentSituationDetailViewAccess$getView = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                if (iRecentSituationDetailViewAccess$getView != null) {
                    RecentSituationDetailPresenter recentSituationDetailPresenter = RecentSituationDetailPresenter.this;
                    String str = recentSituationDetailPresenter.mPastStartDate;
                    if (str == null) {
                        Intrinsics.throwNpe();
                    }
                    String str2 = RecentSituationDetailPresenter.this.mPastEndDate;
                    if (str2 == null) {
                        Intrinsics.throwNpe();
                    }
                    iRecentSituationDetailViewAccess$getView.updatePastChartDesc(recentSituationDetailPresenter.getPastDateDesc(str, str2));
                }
                IRecentSituationDetailView iRecentSituationDetailViewAccess$getView2 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                if (iRecentSituationDetailViewAccess$getView2 == null) {
                    return null;
                }
                String languageText = LanguageUtil.getLanguageText(R.string.recent_day_format);
                Intrinsics.checkExpressionValueIsNotNull(languageText, "LanguageUtil.getLanguage…string.recent_day_format)");
                Object[] objArr = {Boxing.boxInt(RecentSituationDetailPresenter.this.getRecentDayCount())};
                String str3 = String.format(languageText, Arrays.copyOf(objArr, objArr.length));
                Intrinsics.checkNotNullExpressionValue(str3, "java.lang.String.format(this, *args)");
                iRecentSituationDetailViewAccess$getView2.updateRecentChartDesc(str3);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    final /* synthetic */ Object processBar(Continuation<? super Unit> continuation) {
        int i;
        if (this.mBarYMax <= 0) {
            int i2 = this.mPageType;
            if (i2 == RecentSituationDetailActivity.INSTANCE.getPAGE_ACTIVITY_CALORIE()) {
                i = this.Y_MAX_ACTIVITY_CALORIE;
            } else if (i2 == RecentSituationDetailActivity.INSTANCE.getPAGE_ACTIVITY_TIME()) {
                i = this.Y_MAX_ACTIVITY_TIME;
            } else {
                i = this.Y_MAX_WALK;
            }
            this.mBarYMax = i;
        }
        Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = getPastDayCount();
        Ref.IntRef intRef2 = new Ref.IntRef();
        intRef2.element = getRecentDayCount();
        float f2 = this.mBarYMax * this.mDefaultBarRadius;
        if (!this.mPastChartData.isEmpty()) {
            int size = this.mPastChartData.size();
            for (int i3 = 0; i3 < size; i3++) {
                GradientBarPoint gradientBarPoint = this.mPastChartData.get(i3);
                gradientBarPoint.y = Math.max(f2, gradientBarPoint.y);
            }
        } else {
            this.mPastChartData.addAll(generateDefaultDayChartData(intRef.element, this.mBarYMax));
        }
        int size2 = this.mRecentChartData.size();
        for (int i4 = 0; i4 < size2; i4++) {
            GradientBarPoint gradientBarPoint2 = this.mRecentChartData.get(i4);
            gradientBarPoint2.y = Math.max(gradientBarPoint2.y, f2);
        }
        Object objWithContext = BuildersKt.withContext(Dispatchers.getMain(), new C04052(intRef, intRef2, null), continuation);
        return objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContext : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.recentsituation.RecentSituationDetailPresenter$processBar$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RecentSituationDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.recentsituation.RecentSituationDetailPresenter$processBar$2", f = "RecentSituationDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C04052 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.IntRef $pastDayCount;
        final /* synthetic */ Ref.IntRef $recentDayCount;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C04052(Ref.IntRef intRef, Ref.IntRef intRef2, Continuation continuation) {
            super(2, continuation);
            this.$pastDayCount = intRef;
            this.$recentDayCount = intRef2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C04052 c04052 = RecentSituationDetailPresenter.this.new C04052(this.$pastDayCount, this.$recentDayCount, completion);
            c04052.p$ = (CoroutineScope) obj;
            return c04052;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C04052) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.p$;
                if (RecentSituationDetailPresenter.this.mRecentHasDataDayCount > 0) {
                    IRecentSituationDetailView iRecentSituationDetailViewAccess$getView = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                    if (iRecentSituationDetailViewAccess$getView != null) {
                        iRecentSituationDetailViewAccess$getView.updateRecentChartAvg(RecentSituationDetailPresenter.this.mRecentAvg);
                    }
                } else {
                    IRecentSituationDetailView iRecentSituationDetailViewAccess$getView2 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                    if (iRecentSituationDetailViewAccess$getView2 != null) {
                        iRecentSituationDetailViewAccess$getView2.updateRecentChartAvg(-1.0f);
                    }
                }
                if (RecentSituationDetailPresenter.this.mPastHasDataDayCount > 0) {
                    IRecentSituationDetailView iRecentSituationDetailViewAccess$getView3 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                    if (iRecentSituationDetailViewAccess$getView3 != null) {
                        iRecentSituationDetailViewAccess$getView3.updatePastChartAvg(RecentSituationDetailPresenter.this.mPastAvg);
                    }
                } else {
                    IRecentSituationDetailView iRecentSituationDetailViewAccess$getView4 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                    if (iRecentSituationDetailViewAccess$getView4 != null) {
                        iRecentSituationDetailViewAccess$getView4.updatePastChartAvg(-1.0f);
                    }
                }
                if (RecentSituationDetailPresenter.this.mReallyStage == StageInfoEnum.NODATA) {
                    IRecentSituationDetailView iRecentSituationDetailViewAccess$getView5 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                    if (iRecentSituationDetailViewAccess$getView5 != null) {
                        iRecentSituationDetailViewAccess$getView5.updatePassDayCount(this.$pastDayCount.element, RecentSituationDetailPresenter.this.mPastHasDataDayCount);
                    }
                    IRecentSituationDetailView iRecentSituationDetailViewAccess$getView6 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                    if (iRecentSituationDetailViewAccess$getView6 != null) {
                        iRecentSituationDetailViewAccess$getView6.updateRecentDayCount(this.$recentDayCount.element, RecentSituationDetailPresenter.this.mPastHasDataDayCount);
                    }
                } else {
                    IRecentSituationDetailView iRecentSituationDetailViewAccess$getView7 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                    if (iRecentSituationDetailViewAccess$getView7 != null) {
                        iRecentSituationDetailViewAccess$getView7.updatePassDayCount(this.$pastDayCount.element, RecentSituationDetailPresenter.this.mPastHasDataDayCount);
                    }
                    IRecentSituationDetailView iRecentSituationDetailViewAccess$getView8 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                    if (iRecentSituationDetailViewAccess$getView8 != null) {
                        iRecentSituationDetailViewAccess$getView8.updateRecentDayCount(this.$recentDayCount.element, RecentSituationDetailPresenter.this.mRecentHasDataDayCount);
                    }
                }
                IRecentSituationDetailView iRecentSituationDetailViewAccess$getView9 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                if (iRecentSituationDetailViewAccess$getView9 != null) {
                    iRecentSituationDetailViewAccess$getView9.updateBarYmaxmin(RecentSituationDetailPresenter.this.mBarYMax, 0);
                }
                IRecentSituationDetailView iRecentSituationDetailViewAccess$getView10 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                if (iRecentSituationDetailViewAccess$getView10 != null) {
                    iRecentSituationDetailViewAccess$getView10.updateRecentChartData(RecentSituationDetailPresenter.this.mRecentChartData);
                }
                IRecentSituationDetailView iRecentSituationDetailViewAccess$getView11 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                if (iRecentSituationDetailViewAccess$getView11 != null) {
                    iRecentSituationDetailViewAccess$getView11.updatePastChartData(RecentSituationDetailPresenter.this.mPastChartData);
                }
                int i = WhenMappings.$EnumSwitchMapping$0[RecentSituationDetailPresenter.this.mReallyStage.ordinal()];
                if (i == 1 || i == 2) {
                    IRecentSituationDetailView iRecentSituationDetailViewAccess$getView12 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                    if (iRecentSituationDetailViewAccess$getView12 != null) {
                        iRecentSituationDetailViewAccess$getView12.updateRecentBarXMaxmin(((this.$recentDayCount.element + 1) / 7) + ((this.$recentDayCount.element + 1) % 7 == 0 ? 0 : 1), 1);
                    }
                    IRecentSituationDetailView iRecentSituationDetailViewAccess$getView13 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                    if (iRecentSituationDetailViewAccess$getView13 == null) {
                        return null;
                    }
                    iRecentSituationDetailViewAccess$getView13.updatePastBarXMaxmin((this.$pastDayCount.element / 7) + (this.$pastDayCount.element % 7 != 0 ? 1 : 0), 1);
                    return Unit.INSTANCE;
                }
                IRecentSituationDetailView iRecentSituationDetailViewAccess$getView14 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                if (iRecentSituationDetailViewAccess$getView14 != null) {
                    iRecentSituationDetailViewAccess$getView14.updateRecentBarXMaxmin(this.$recentDayCount.element + 1, 1);
                }
                IRecentSituationDetailView iRecentSituationDetailViewAccess$getView15 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                if (iRecentSituationDetailViewAccess$getView15 == null) {
                    return null;
                }
                iRecentSituationDetailViewAccess$getView15.updatePastBarXMaxmin(this.$pastDayCount.element, 1);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    private final void caluteDate(String taskTag) {
        String str = (String) null;
        this.mRecentStartDate = str;
        this.mRecentEndDate = str;
        this.mPastStartDate = str;
        this.mPastEndDate = str;
        StageInfoEnum stageInfoEnum = StageInfoEnum.NODATA;
        for (StageInfoEnum stageInfoEnum2 : FitnessHelperKt.getSiturationStageList()) {
            if (FitnessHelperKt.recentSatisfyPrimaryStage(getUserId(), taskTag, stageInfoEnum2)) {
                stageInfoEnum = stageInfoEnum2;
            }
        }
        this.mReallyStage = stageInfoEnum;
        if (stageInfoEnum == StageInfoEnum.NODATA) {
            stageInfoEnum = StageInfoEnum.PRIMARY;
        }
        Pair<String, String> pairCaluteStageDate = FitnessHelperKt.caluteStageDate(stageInfoEnum.getMRecentStartDayCount(), stageInfoEnum.getMRecentEndDayCount() - stageInfoEnum.getMRecentStartDayCount());
        Pair<String, String> pairCaluteStageDate2 = FitnessHelperKt.caluteStageDate(stageInfoEnum.getMPastStartDayCount(), stageInfoEnum.getMPastEndDayCount() - stageInfoEnum.getMPastStartDayCount());
        this.mRecentStartDate = pairCaluteStageDate.getFirst();
        this.mRecentEndDate = pairCaluteStageDate.getSecond();
        this.mPastStartDate = pairCaluteStageDate2.getFirst();
        this.mPastEndDate = pairCaluteStageDate2.getSecond();
    }

    private final void caluteRecentActivityCalorieChartData() {
        int i = WhenMappings.$EnumSwitchMapping$1[this.mReallyStage.ordinal()];
        if (i == 1 || i == 2 || i == 3) {
            caluteRecentActivityCalorieChartDataByDay();
        } else {
            caluteRecentActivityCalorieChartDataByWeek();
        }
    }

    private final void caluteRecentActivityTimeChartData() {
        int i = WhenMappings.$EnumSwitchMapping$2[this.mReallyStage.ordinal()];
        if (i == 1 || i == 2 || i == 3) {
            caluteRecentActivityTimeChartDataByDay();
        } else {
            caluteRecentActivityTimeChartDataByWeek();
        }
    }

    private final void caluteRecentWalkChartData() {
        int i = WhenMappings.$EnumSwitchMapping$3[this.mReallyStage.ordinal()];
        if (i == 1 || i == 2 || i == 3) {
            caluteRecentWalkChartDataByDay();
        } else {
            caluteRecentWalkChartDataByWeek();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v12, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v14, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v17, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v19, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v30, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v33, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v35, types: [T, java.lang.Object, java.lang.String] */
    final /* synthetic */ Object caluteCalorieAvgAndCompareState(Continuation<? super Unit> continuation) {
        Ref.FloatRef floatRef = new Ref.FloatRef();
        long userId = getUserId();
        String str = this.mRecentStartDate;
        if (str == null) {
            Intrinsics.throwNpe();
        }
        String str2 = this.mRecentEndDate;
        if (str2 == null) {
            Intrinsics.throwNpe();
        }
        floatRef.element = FitnessHelperKt.calumetRetentivityActiveCalorieAvg(userId, str, str2);
        int flat = FitnessDetailPresenter.INSTANCE.getFLAT();
        if (this.mReallyStage != StageInfoEnum.NODATA) {
            float f2 = floatRef.element;
            long userId2 = getUserId();
            String str3 = this.mPastStartDate;
            if (str3 == null) {
                Intrinsics.throwNpe();
            }
            String str4 = this.mPastEndDate;
            if (str4 == null) {
                Intrinsics.throwNpe();
            }
            float fCalumetRetentivityActiveCalorieAvg = f2 - FitnessHelperKt.calumetRetentivityActiveCalorieAvg(userId2, str3, str4);
            if (fCalumetRetentivityActiveCalorieAvg >= 1) {
                flat = FitnessDetailPresenter.INSTANCE.getUP();
            } else if (fCalumetRetentivityActiveCalorieAvg <= -1) {
                flat = FitnessDetailPresenter.INSTANCE.getDOWN();
            }
        }
        int iCaluteBMR = FitnessHelperKt.caluteBMR(getUserId());
        Ref.IntRef intRef = new Ref.IntRef();
        float f3 = iCaluteBMR;
        intRef.element = MathKt.roundToInt(0.8f * f3);
        Ref.IntRef intRef2 = new Ref.IntRef();
        intRef2.element = MathKt.roundToInt(f3 * 0.15f);
        intRef2.element = Math.max(10, (intRef2.element / 50) * 50);
        intRef.element = intRef.element % 50 == 0 ? intRef.element : ((intRef.element / 50) + 1) * 50;
        intRef.element = Math.max(50, intRef.element);
        if (WhenMappings.$EnumSwitchMapping$4[this.mReallyStage.ordinal()] == 1) {
            Ref.IntRef intRef3 = new Ref.IntRef();
            intRef3.element = FitnessHelperKt.getCalorieTargetStageLeft(getUserId(), StageInfoEnum.PRIMARY);
            Object objWithContext = BuildersKt.withContext(Dispatchers.getMain(), new C03912(intRef3, floatRef, intRef, intRef2, null), continuation);
            if (objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                return objWithContext;
            }
        } else {
            Ref.IntRef intRef4 = new Ref.IntRef();
            Ref.ObjectRef objectRef = new Ref.ObjectRef();
            Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
            if (flat == FitnessDetailPresenter.INSTANCE.getUP()) {
                intRef4.element = R.mipmap.fitness_activity_calorie_up;
                ?? languageText = LanguageUtil.getLanguageText(R.string.fitness_detail_compare_up);
                Intrinsics.checkExpressionValueIsNotNull(languageText, "LanguageUtil.getLanguage…itness_detail_compare_up)");
                objectRef.element = languageText;
                ?? languageText2 = LanguageUtil.getLanguageText(R.string.fitness_detail_compare_desc_up);
                Intrinsics.checkExpressionValueIsNotNull(languageText2, "LanguageUtil.getLanguage…s_detail_compare_desc_up)");
                objectRef2.element = languageText2;
            } else if (flat == FitnessDetailPresenter.INSTANCE.getDOWN()) {
                intRef4.element = R.mipmap.fitness_activity_calorie_down;
                ?? languageText3 = LanguageUtil.getLanguageText(R.string.fitness_detail_compare_down);
                Intrinsics.checkExpressionValueIsNotNull(languageText3, "LanguageUtil.getLanguage…ness_detail_compare_down)");
                objectRef.element = languageText3;
                ?? languageText4 = LanguageUtil.getLanguageText(R.string.fitness_detail_active_calorie_compare_desc_down);
                Intrinsics.checkExpressionValueIsNotNull(languageText4, "LanguageUtil.getLanguage…alorie_compare_desc_down)");
                objectRef2.element = languageText4;
            } else {
                intRef4.element = R.mipmap.fitness_activity_calorie_flat;
                ?? languageText5 = LanguageUtil.getLanguageText(R.string.fitness_detail_compare_flat);
                Intrinsics.checkExpressionValueIsNotNull(languageText5, "LanguageUtil.getLanguage…ness_detail_compare_flat)");
                objectRef.element = languageText5;
                ?? languageText6 = LanguageUtil.getLanguageText(R.string.fitness_detail_compare_desc_flat);
                Intrinsics.checkExpressionValueIsNotNull(languageText6, "LanguageUtil.getLanguage…detail_compare_desc_flat)");
                objectRef2.element = languageText6;
            }
            StageInfoEnum nextStage = this.mReallyStage.getNextStage();
            if (nextStage != null && FitnessHelperKt.getCalorieTargetStageLeft(getUserId(), nextStage) == 14) {
                objectRef2.element = ((String) objectRef2.element) + LanguageUtil.getLanguageText(R.string.fitness_next_stage_14_left);
            }
            Object objWithContext2 = BuildersKt.withContext(Dispatchers.getMain(), new C03923(floatRef, intRef, intRef2, intRef4, objectRef, objectRef2, null), continuation);
            if (objWithContext2 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                return objWithContext2;
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.recentsituation.RecentSituationDetailPresenter$caluteCalorieAvgAndCompareState$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RecentSituationDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.recentsituation.RecentSituationDetailPresenter$caluteCalorieAvgAndCompareState$2", f = "RecentSituationDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03912 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.FloatRef $calorieAvg;
        final /* synthetic */ Ref.IntRef $calorieMax;
        final /* synthetic */ Ref.IntRef $calorieMin;
        final /* synthetic */ Ref.IntRef $leftDayCount;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03912(Ref.IntRef intRef, Ref.FloatRef floatRef, Ref.IntRef intRef2, Ref.IntRef intRef3, Continuation continuation) {
            super(2, continuation);
            this.$leftDayCount = intRef;
            this.$calorieAvg = floatRef;
            this.$calorieMax = intRef2;
            this.$calorieMin = intRef3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03912 c03912 = RecentSituationDetailPresenter.this.new C03912(this.$leftDayCount, this.$calorieAvg, this.$calorieMax, this.$calorieMin, completion);
            c03912.p$ = (CoroutineScope) obj;
            return c03912;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03912) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            IRecentSituationDetailView iRecentSituationDetailViewAccess$getView = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
            if (iRecentSituationDetailViewAccess$getView != null) {
                iRecentSituationDetailViewAccess$getView.updateCompareStateIcon(R.mipmap.fitness_activity_calorie_flat);
            }
            IRecentSituationDetailView iRecentSituationDetailViewAccess$getView2 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
            if (iRecentSituationDetailViewAccess$getView2 != null) {
                String languageText = LanguageUtil.getLanguageText(R.string.fitness_detail_compare_flat_nodata);
                Intrinsics.checkExpressionValueIsNotNull(languageText, "LanguageUtil.getLanguage…tail_compare_flat_nodata)");
                iRecentSituationDetailViewAccess$getView2.updateCompareStateTitle(languageText);
            }
            IRecentSituationDetailView iRecentSituationDetailViewAccess$getView3 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
            if (iRecentSituationDetailViewAccess$getView3 != null) {
                String languageText2 = LanguageUtil.getLanguageText(R.string.fitness_detail_compare_calorie_desc_flat_nodata);
                Intrinsics.checkExpressionValueIsNotNull(languageText2, "LanguageUtil.getLanguage…calorie_desc_flat_nodata)");
                Object[] objArr = {Boxing.boxInt(this.$leftDayCount.element)};
                String str = String.format(languageText2, Arrays.copyOf(objArr, objArr.length));
                Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(this, *args)");
                iRecentSituationDetailViewAccess$getView3.updateCompareStateDesc(str);
            }
            IRecentSituationDetailView iRecentSituationDetailViewAccess$getView4 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
            if (iRecentSituationDetailViewAccess$getView4 != null) {
                iRecentSituationDetailViewAccess$getView4.updateRecentAvg(this.$calorieAvg.element);
            }
            IRecentSituationDetailView iRecentSituationDetailViewAccess$getView5 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
            if (iRecentSituationDetailViewAccess$getView5 != null) {
                iRecentSituationDetailViewAccess$getView5.updateProgressMaxmin(this.$calorieMax.element, this.$calorieMin.element);
            }
            IRecentSituationDetailView iRecentSituationDetailViewAccess$getView6 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
            if (iRecentSituationDetailViewAccess$getView6 == null) {
                return null;
            }
            iRecentSituationDetailViewAccess$getView6.updateProgress(MathKt.roundToInt(this.$calorieAvg.element));
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.recentsituation.RecentSituationDetailPresenter$caluteCalorieAvgAndCompareState$3, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RecentSituationDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.recentsituation.RecentSituationDetailPresenter$caluteCalorieAvgAndCompareState$3", f = "RecentSituationDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03923 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.FloatRef $calorieAvg;
        final /* synthetic */ Ref.IntRef $calorieMax;
        final /* synthetic */ Ref.IntRef $calorieMin;
        final /* synthetic */ Ref.IntRef $iconResId;
        final /* synthetic */ Ref.ObjectRef $stageDesc;
        final /* synthetic */ Ref.ObjectRef $stageTitle;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03923(Ref.FloatRef floatRef, Ref.IntRef intRef, Ref.IntRef intRef2, Ref.IntRef intRef3, Ref.ObjectRef objectRef, Ref.ObjectRef objectRef2, Continuation continuation) {
            super(2, continuation);
            this.$calorieAvg = floatRef;
            this.$calorieMax = intRef;
            this.$calorieMin = intRef2;
            this.$iconResId = intRef3;
            this.$stageTitle = objectRef;
            this.$stageDesc = objectRef2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03923 c03923 = RecentSituationDetailPresenter.this.new C03923(this.$calorieAvg, this.$calorieMax, this.$calorieMin, this.$iconResId, this.$stageTitle, this.$stageDesc, completion);
            c03923.p$ = (CoroutineScope) obj;
            return c03923;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03923) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            IRecentSituationDetailView iRecentSituationDetailViewAccess$getView = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
            if (iRecentSituationDetailViewAccess$getView != null) {
                iRecentSituationDetailViewAccess$getView.updateRecentAvg(this.$calorieAvg.element);
            }
            IRecentSituationDetailView iRecentSituationDetailViewAccess$getView2 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
            if (iRecentSituationDetailViewAccess$getView2 != null) {
                iRecentSituationDetailViewAccess$getView2.updateProgressMaxmin(this.$calorieMax.element, this.$calorieMin.element);
            }
            IRecentSituationDetailView iRecentSituationDetailViewAccess$getView3 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
            if (iRecentSituationDetailViewAccess$getView3 != null) {
                iRecentSituationDetailViewAccess$getView3.updateProgress(MathKt.roundToInt(this.$calorieAvg.element));
            }
            IRecentSituationDetailView iRecentSituationDetailViewAccess$getView4 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
            if (iRecentSituationDetailViewAccess$getView4 != null) {
                iRecentSituationDetailViewAccess$getView4.updateCompareStateIcon(this.$iconResId.element);
            }
            IRecentSituationDetailView iRecentSituationDetailViewAccess$getView5 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
            if (iRecentSituationDetailViewAccess$getView5 != null) {
                iRecentSituationDetailViewAccess$getView5.updateCompareStateTitle((String) this.$stageTitle.element);
            }
            IRecentSituationDetailView iRecentSituationDetailViewAccess$getView6 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
            if (iRecentSituationDetailViewAccess$getView6 == null) {
                return null;
            }
            iRecentSituationDetailViewAccess$getView6.updateCompareStateDesc((String) this.$stageDesc.element);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v11, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v14, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v16, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v27, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v30, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v32, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v9, types: [T, java.lang.Object, java.lang.String] */
    final /* synthetic */ Object caluteActiveTimeAndCompareState(Continuation<? super Unit> continuation) {
        Ref.FloatRef floatRef = new Ref.FloatRef();
        long userId = getUserId();
        String str = this.mRecentStartDate;
        if (str == null) {
            Intrinsics.throwNpe();
        }
        String str2 = this.mRecentEndDate;
        if (str2 == null) {
            Intrinsics.throwNpe();
        }
        floatRef.element = FitnessHelperKt.caluteRecentSituationActiveTimeAvg(userId, str, str2);
        int flat = FitnessDetailPresenter.INSTANCE.getFLAT();
        if (this.mReallyStage != StageInfoEnum.NODATA) {
            long userId2 = getUserId();
            String str3 = this.mRecentStartDate;
            if (str3 == null) {
                Intrinsics.throwNpe();
            }
            String str4 = this.mRecentEndDate;
            if (str4 == null) {
                Intrinsics.throwNpe();
            }
            float fCaluteRecentSituationActiveTimeAvg = FitnessHelperKt.caluteRecentSituationActiveTimeAvg(userId2, str3, str4);
            long userId3 = getUserId();
            String str5 = this.mPastStartDate;
            if (str5 == null) {
                Intrinsics.throwNpe();
            }
            String str6 = this.mPastEndDate;
            if (str6 == null) {
                Intrinsics.throwNpe();
            }
            float fCaluteRecentSituationActiveTimeAvg2 = fCaluteRecentSituationActiveTimeAvg - FitnessHelperKt.caluteRecentSituationActiveTimeAvg(userId3, str5, str6);
            if (fCaluteRecentSituationActiveTimeAvg2 >= 1) {
                flat = FitnessDetailPresenter.INSTANCE.getUP();
            } else if (fCaluteRecentSituationActiveTimeAvg2 <= -1) {
                flat = FitnessDetailPresenter.INSTANCE.getDOWN();
            }
        }
        if (WhenMappings.$EnumSwitchMapping$5[this.mReallyStage.ordinal()] == 1) {
            Ref.IntRef intRef = new Ref.IntRef();
            intRef.element = FitnessHelperKt.getActiveTimeTargetStageLeft(getUserId(), StageInfoEnum.PRIMARY);
            Object objWithContext = BuildersKt.withContext(Dispatchers.getMain(), new C03892(floatRef, intRef, null), continuation);
            if (objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                return objWithContext;
            }
        } else {
            Ref.IntRef intRef2 = new Ref.IntRef();
            Ref.ObjectRef objectRef = new Ref.ObjectRef();
            Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
            if (flat == FitnessDetailPresenter.INSTANCE.getUP()) {
                intRef2.element = R.mipmap.fitness_activity_time_up;
                ?? languageText = LanguageUtil.getLanguageText(R.string.fitness_detail_compare_up);
                Intrinsics.checkExpressionValueIsNotNull(languageText, "LanguageUtil.getLanguage…itness_detail_compare_up)");
                objectRef.element = languageText;
                ?? languageText2 = LanguageUtil.getLanguageText(R.string.fitness_detail_compare_desc_up);
                Intrinsics.checkExpressionValueIsNotNull(languageText2, "LanguageUtil.getLanguage…s_detail_compare_desc_up)");
                objectRef2.element = languageText2;
            } else if (flat == FitnessDetailPresenter.INSTANCE.getDOWN()) {
                intRef2.element = R.mipmap.fitness_activity_time_down;
                ?? languageText3 = LanguageUtil.getLanguageText(R.string.fitness_detail_compare_down);
                Intrinsics.checkExpressionValueIsNotNull(languageText3, "LanguageUtil.getLanguage…ness_detail_compare_down)");
                objectRef.element = languageText3;
                ?? languageText4 = LanguageUtil.getLanguageText(R.string.fitness_detail_active_time_compare_desc_down);
                Intrinsics.checkExpressionValueIsNotNull(languageText4, "LanguageUtil.getLanguage…e_time_compare_desc_down)");
                objectRef2.element = languageText4;
            } else {
                intRef2.element = R.mipmap.fitness_activity_time_flat;
                ?? languageText5 = LanguageUtil.getLanguageText(R.string.fitness_detail_compare_flat);
                Intrinsics.checkExpressionValueIsNotNull(languageText5, "LanguageUtil.getLanguage…ness_detail_compare_flat)");
                objectRef.element = languageText5;
                ?? languageText6 = LanguageUtil.getLanguageText(R.string.fitness_detail_compare_desc_flat);
                Intrinsics.checkExpressionValueIsNotNull(languageText6, "LanguageUtil.getLanguage…detail_compare_desc_flat)");
                objectRef2.element = languageText6;
            }
            StageInfoEnum nextStage = this.mReallyStage.getNextStage();
            if (nextStage != null && FitnessHelperKt.getActiveTimeTargetStageLeft(getUserId(), nextStage) == 14) {
                objectRef2.element = ((String) objectRef2.element) + LanguageUtil.getLanguageText(R.string.fitness_next_stage_14_left);
            }
            Object objWithContext2 = BuildersKt.withContext(Dispatchers.getMain(), new AnonymousClass3(floatRef, intRef2, objectRef, objectRef2, null), continuation);
            if (objWithContext2 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                return objWithContext2;
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.recentsituation.RecentSituationDetailPresenter$caluteActiveTimeAndCompareState$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RecentSituationDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.recentsituation.RecentSituationDetailPresenter$caluteActiveTimeAndCompareState$2", f = "RecentSituationDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03892 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.FloatRef $activeTimeAvg;
        final /* synthetic */ Ref.IntRef $leftDayCount;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03892(Ref.FloatRef floatRef, Ref.IntRef intRef, Continuation continuation) {
            super(2, continuation);
            this.$activeTimeAvg = floatRef;
            this.$leftDayCount = intRef;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03892 c03892 = RecentSituationDetailPresenter.this.new C03892(this.$activeTimeAvg, this.$leftDayCount, completion);
            c03892.p$ = (CoroutineScope) obj;
            return c03892;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03892) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.p$;
                IRecentSituationDetailView iRecentSituationDetailViewAccess$getView = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                if (iRecentSituationDetailViewAccess$getView != null) {
                    iRecentSituationDetailViewAccess$getView.updateRecentAvg(this.$activeTimeAvg.element);
                }
                IRecentSituationDetailView iRecentSituationDetailViewAccess$getView2 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                if (iRecentSituationDetailViewAccess$getView2 != null) {
                    iRecentSituationDetailViewAccess$getView2.updateProgressMaxmin(60, 5);
                }
                IRecentSituationDetailView iRecentSituationDetailViewAccess$getView3 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                if (iRecentSituationDetailViewAccess$getView3 != null) {
                    iRecentSituationDetailViewAccess$getView3.updateProgress(MathKt.roundToInt(this.$activeTimeAvg.element));
                }
                IRecentSituationDetailView iRecentSituationDetailViewAccess$getView4 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                if (iRecentSituationDetailViewAccess$getView4 != null) {
                    iRecentSituationDetailViewAccess$getView4.updateCompareStateIcon(R.mipmap.fitness_activity_time_flat);
                }
                IRecentSituationDetailView iRecentSituationDetailViewAccess$getView5 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                if (iRecentSituationDetailViewAccess$getView5 != null) {
                    String languageText = LanguageUtil.getLanguageText(R.string.fitness_detail_compare_flat_nodata);
                    Intrinsics.checkExpressionValueIsNotNull(languageText, "LanguageUtil.getLanguage…tail_compare_flat_nodata)");
                    iRecentSituationDetailViewAccess$getView5.updateCompareStateTitle(languageText);
                }
                IRecentSituationDetailView iRecentSituationDetailViewAccess$getView6 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                if (iRecentSituationDetailViewAccess$getView6 == null) {
                    return null;
                }
                String languageText2 = LanguageUtil.getLanguageText(R.string.fitness_detail_compare_time_desc_flat_nodata);
                Intrinsics.checkExpressionValueIsNotNull(languageText2, "LanguageUtil.getLanguage…re_time_desc_flat_nodata)");
                Object[] objArr = {Boxing.boxInt(this.$leftDayCount.element)};
                String str = String.format(languageText2, Arrays.copyOf(objArr, objArr.length));
                Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(this, *args)");
                iRecentSituationDetailViewAccess$getView6.updateCompareStateDesc(str);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.recentsituation.RecentSituationDetailPresenter$caluteActiveTimeAndCompareState$3, reason: invalid class name */
    /* JADX INFO: compiled from: RecentSituationDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.recentsituation.RecentSituationDetailPresenter$caluteActiveTimeAndCompareState$3", f = "RecentSituationDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.FloatRef $activeTimeAvg;
        final /* synthetic */ Ref.IntRef $iconResId;
        final /* synthetic */ Ref.ObjectRef $stageDesc;
        final /* synthetic */ Ref.ObjectRef $stageTitle;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(Ref.FloatRef floatRef, Ref.IntRef intRef, Ref.ObjectRef objectRef, Ref.ObjectRef objectRef2, Continuation continuation) {
            super(2, continuation);
            this.$activeTimeAvg = floatRef;
            this.$iconResId = intRef;
            this.$stageTitle = objectRef;
            this.$stageDesc = objectRef2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            AnonymousClass3 anonymousClass3 = RecentSituationDetailPresenter.this.new AnonymousClass3(this.$activeTimeAvg, this.$iconResId, this.$stageTitle, this.$stageDesc, completion);
            anonymousClass3.p$ = (CoroutineScope) obj;
            return anonymousClass3;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            IRecentSituationDetailView iRecentSituationDetailViewAccess$getView = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
            if (iRecentSituationDetailViewAccess$getView != null) {
                iRecentSituationDetailViewAccess$getView.updateRecentAvg(this.$activeTimeAvg.element);
            }
            IRecentSituationDetailView iRecentSituationDetailViewAccess$getView2 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
            if (iRecentSituationDetailViewAccess$getView2 != null) {
                iRecentSituationDetailViewAccess$getView2.updateProgressMaxmin(60, 5);
            }
            IRecentSituationDetailView iRecentSituationDetailViewAccess$getView3 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
            if (iRecentSituationDetailViewAccess$getView3 != null) {
                iRecentSituationDetailViewAccess$getView3.updateProgress(MathKt.roundToInt(this.$activeTimeAvg.element));
            }
            IRecentSituationDetailView iRecentSituationDetailViewAccess$getView4 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
            if (iRecentSituationDetailViewAccess$getView4 != null) {
                iRecentSituationDetailViewAccess$getView4.updateCompareStateIcon(this.$iconResId.element);
            }
            IRecentSituationDetailView iRecentSituationDetailViewAccess$getView5 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
            if (iRecentSituationDetailViewAccess$getView5 != null) {
                iRecentSituationDetailViewAccess$getView5.updateCompareStateTitle((String) this.$stageTitle.element);
            }
            IRecentSituationDetailView iRecentSituationDetailViewAccess$getView6 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
            if (iRecentSituationDetailViewAccess$getView6 == null) {
                return null;
            }
            iRecentSituationDetailViewAccess$getView6.updateCompareStateDesc((String) this.$stageDesc.element);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v11, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v14, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v16, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v27, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v30, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v32, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v9, types: [T, java.lang.Object, java.lang.String] */
    final /* synthetic */ Object caluteWalkAndCompareState(Continuation<? super Unit> continuation) {
        Ref.FloatRef floatRef = new Ref.FloatRef();
        long userId = getUserId();
        String str = this.mRecentStartDate;
        if (str == null) {
            Intrinsics.throwNpe();
        }
        String str2 = this.mRecentEndDate;
        if (str2 == null) {
            Intrinsics.throwNpe();
        }
        floatRef.element = FitnessHelperKt.caluteRecentSituationWalkAvg(userId, str, str2);
        int flat = FitnessDetailPresenter.INSTANCE.getFLAT();
        if (this.mReallyStage != StageInfoEnum.NODATA) {
            long userId2 = getUserId();
            String str3 = this.mRecentStartDate;
            if (str3 == null) {
                Intrinsics.throwNpe();
            }
            String str4 = this.mRecentEndDate;
            if (str4 == null) {
                Intrinsics.throwNpe();
            }
            float fCaluteRecentSituationWalkAvg = FitnessHelperKt.caluteRecentSituationWalkAvg(userId2, str3, str4);
            long userId3 = getUserId();
            String str5 = this.mPastStartDate;
            if (str5 == null) {
                Intrinsics.throwNpe();
            }
            String str6 = this.mPastEndDate;
            if (str6 == null) {
                Intrinsics.throwNpe();
            }
            float fCaluteRecentSituationWalkAvg2 = fCaluteRecentSituationWalkAvg - FitnessHelperKt.caluteRecentSituationWalkAvg(userId3, str5, str6);
            float f2 = 0;
            if (fCaluteRecentSituationWalkAvg2 - 0.5f >= f2) {
                flat = FitnessDetailPresenter.INSTANCE.getUP();
            } else if (fCaluteRecentSituationWalkAvg2 + 0.5f <= f2) {
                flat = FitnessDetailPresenter.INSTANCE.getDOWN();
            }
        }
        if (WhenMappings.$EnumSwitchMapping$6[this.mReallyStage.ordinal()] == 1) {
            Ref.IntRef intRef = new Ref.IntRef();
            intRef.element = FitnessHelperKt.getWalkTargetStageLeft(getUserId(), StageInfoEnum.PRIMARY);
            Object objWithContext = BuildersKt.withContext(Dispatchers.getMain(), new C03932(floatRef, intRef, null), continuation);
            if (objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                return objWithContext;
            }
        } else {
            Ref.IntRef intRef2 = new Ref.IntRef();
            Ref.ObjectRef objectRef = new Ref.ObjectRef();
            Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
            if (flat == FitnessDetailPresenter.INSTANCE.getUP()) {
                intRef2.element = R.mipmap.fitness_walk_up;
                ?? languageText = LanguageUtil.getLanguageText(R.string.fitness_detail_compare_up);
                Intrinsics.checkExpressionValueIsNotNull(languageText, "LanguageUtil.getLanguage…itness_detail_compare_up)");
                objectRef.element = languageText;
                ?? languageText2 = LanguageUtil.getLanguageText(R.string.fitness_detail_compare_desc_up);
                Intrinsics.checkExpressionValueIsNotNull(languageText2, "LanguageUtil.getLanguage…s_detail_compare_desc_up)");
                objectRef2.element = languageText2;
            } else if (flat == FitnessDetailPresenter.INSTANCE.getDOWN()) {
                intRef2.element = R.mipmap.fitness_walk_down;
                ?? languageText3 = LanguageUtil.getLanguageText(R.string.fitness_detail_compare_down);
                Intrinsics.checkExpressionValueIsNotNull(languageText3, "LanguageUtil.getLanguage…ness_detail_compare_down)");
                objectRef.element = languageText3;
                ?? languageText4 = LanguageUtil.getLanguageText(R.string.fitness_detail_walk_compare_desc_down);
                Intrinsics.checkExpressionValueIsNotNull(languageText4, "LanguageUtil.getLanguage…l_walk_compare_desc_down)");
                objectRef2.element = languageText4;
            } else {
                intRef2.element = R.mipmap.fitness_walk_flat;
                ?? languageText5 = LanguageUtil.getLanguageText(R.string.fitness_detail_compare_flat);
                Intrinsics.checkExpressionValueIsNotNull(languageText5, "LanguageUtil.getLanguage…ness_detail_compare_flat)");
                objectRef.element = languageText5;
                ?? languageText6 = LanguageUtil.getLanguageText(R.string.fitness_detail_compare_desc_flat);
                Intrinsics.checkExpressionValueIsNotNull(languageText6, "LanguageUtil.getLanguage…detail_compare_desc_flat)");
                objectRef2.element = languageText6;
            }
            StageInfoEnum nextStage = this.mReallyStage.getNextStage();
            if (nextStage != null && FitnessHelperKt.getWalkTargetStageLeft(getUserId(), nextStage) == 14) {
                objectRef2.element = ((String) objectRef2.element) + LanguageUtil.getLanguageText(R.string.fitness_next_stage_14_left);
            }
            Object objWithContext2 = BuildersKt.withContext(Dispatchers.getMain(), new C03943(floatRef, intRef2, objectRef, objectRef2, null), continuation);
            if (objWithContext2 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                return objWithContext2;
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.recentsituation.RecentSituationDetailPresenter$caluteWalkAndCompareState$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RecentSituationDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.recentsituation.RecentSituationDetailPresenter$caluteWalkAndCompareState$2", f = "RecentSituationDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03932 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.IntRef $leftDayCount;
        final /* synthetic */ Ref.FloatRef $walkAvg;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03932(Ref.FloatRef floatRef, Ref.IntRef intRef, Continuation continuation) {
            super(2, continuation);
            this.$walkAvg = floatRef;
            this.$leftDayCount = intRef;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03932 c03932 = RecentSituationDetailPresenter.this.new C03932(this.$walkAvg, this.$leftDayCount, completion);
            c03932.p$ = (CoroutineScope) obj;
            return c03932;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03932) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.p$;
                IRecentSituationDetailView iRecentSituationDetailViewAccess$getView = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                if (iRecentSituationDetailViewAccess$getView != null) {
                    iRecentSituationDetailViewAccess$getView.updateRecentAvg(this.$walkAvg.element);
                }
                IRecentSituationDetailView iRecentSituationDetailViewAccess$getView2 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                if (iRecentSituationDetailViewAccess$getView2 != null) {
                    iRecentSituationDetailViewAccess$getView2.updateProgressMaxmin(14, 6);
                }
                IRecentSituationDetailView iRecentSituationDetailViewAccess$getView3 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                if (iRecentSituationDetailViewAccess$getView3 != null) {
                    iRecentSituationDetailViewAccess$getView3.updateProgress(MathKt.roundToInt(this.$walkAvg.element));
                }
                IRecentSituationDetailView iRecentSituationDetailViewAccess$getView4 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                if (iRecentSituationDetailViewAccess$getView4 != null) {
                    iRecentSituationDetailViewAccess$getView4.updateCompareStateIcon(R.mipmap.fitness_walk_flat);
                }
                IRecentSituationDetailView iRecentSituationDetailViewAccess$getView5 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                if (iRecentSituationDetailViewAccess$getView5 != null) {
                    String languageText = LanguageUtil.getLanguageText(R.string.fitness_detail_compare_flat_nodata);
                    Intrinsics.checkExpressionValueIsNotNull(languageText, "LanguageUtil.getLanguage…tail_compare_flat_nodata)");
                    iRecentSituationDetailViewAccess$getView5.updateCompareStateTitle(languageText);
                }
                IRecentSituationDetailView iRecentSituationDetailViewAccess$getView6 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
                if (iRecentSituationDetailViewAccess$getView6 == null) {
                    return null;
                }
                String languageText2 = LanguageUtil.getLanguageText(R.string.fitness_detail_compare_walk_desc_flat_nodata);
                Intrinsics.checkExpressionValueIsNotNull(languageText2, "LanguageUtil.getLanguage…re_walk_desc_flat_nodata)");
                Object[] objArr = {Boxing.boxInt(this.$leftDayCount.element)};
                String str = String.format(languageText2, Arrays.copyOf(objArr, objArr.length));
                Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(this, *args)");
                iRecentSituationDetailViewAccess$getView6.updateCompareStateDesc(str);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.recentsituation.RecentSituationDetailPresenter$caluteWalkAndCompareState$3, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RecentSituationDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.recentsituation.RecentSituationDetailPresenter$caluteWalkAndCompareState$3", f = "RecentSituationDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03943 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.IntRef $iconResId;
        final /* synthetic */ Ref.ObjectRef $stageDesc;
        final /* synthetic */ Ref.ObjectRef $stageTitle;
        final /* synthetic */ Ref.FloatRef $walkAvg;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03943(Ref.FloatRef floatRef, Ref.IntRef intRef, Ref.ObjectRef objectRef, Ref.ObjectRef objectRef2, Continuation continuation) {
            super(2, continuation);
            this.$walkAvg = floatRef;
            this.$iconResId = intRef;
            this.$stageTitle = objectRef;
            this.$stageDesc = objectRef2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03943 c03943 = RecentSituationDetailPresenter.this.new C03943(this.$walkAvg, this.$iconResId, this.$stageTitle, this.$stageDesc, completion);
            c03943.p$ = (CoroutineScope) obj;
            return c03943;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03943) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            IRecentSituationDetailView iRecentSituationDetailViewAccess$getView = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
            if (iRecentSituationDetailViewAccess$getView != null) {
                iRecentSituationDetailViewAccess$getView.updateRecentAvg(this.$walkAvg.element);
            }
            IRecentSituationDetailView iRecentSituationDetailViewAccess$getView2 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
            if (iRecentSituationDetailViewAccess$getView2 != null) {
                iRecentSituationDetailViewAccess$getView2.updateProgressMaxmin(14, 6);
            }
            IRecentSituationDetailView iRecentSituationDetailViewAccess$getView3 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
            if (iRecentSituationDetailViewAccess$getView3 != null) {
                iRecentSituationDetailViewAccess$getView3.updateProgress(MathKt.roundToInt(this.$walkAvg.element));
            }
            IRecentSituationDetailView iRecentSituationDetailViewAccess$getView4 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
            if (iRecentSituationDetailViewAccess$getView4 != null) {
                iRecentSituationDetailViewAccess$getView4.updateCompareStateIcon(this.$iconResId.element);
            }
            IRecentSituationDetailView iRecentSituationDetailViewAccess$getView5 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
            if (iRecentSituationDetailViewAccess$getView5 != null) {
                iRecentSituationDetailViewAccess$getView5.updateCompareStateTitle((String) this.$stageTitle.element);
            }
            IRecentSituationDetailView iRecentSituationDetailViewAccess$getView6 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
            if (iRecentSituationDetailViewAccess$getView6 == null) {
                return null;
            }
            iRecentSituationDetailViewAccess$getView6.updateCompareStateDesc((String) this.$stageDesc.element);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void getActivityCalorieTarget() {
        IRecentSituationDetailView view = getView();
        if (view != null) {
            view.updateTarget(getLastestUserTarget().getCalories());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void getActivityTimeTarget() {
        IRecentSituationDetailView view = getView();
        if (view != null) {
            view.updateTarget(getLastestUserTarget().getActivityTime() / 60);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void getWalkTarget() {
        IRecentSituationDetailView view = getView();
        if (view != null) {
            view.updateTarget(getLastestUserTarget().getWalk() / 3600);
        }
    }

    private final UserTargetNew getLastestUserTarget() {
        UserTargetNew target = GreenDaoUtil.queryUserLatestTarget(getUserId());
        if (target == null) {
            target = RunTimeUtil.generateDefaultUserTargetNew(getUserId());
        }
        Intrinsics.checkExpressionValueIsNotNull(target, "target");
        return target;
    }

    private final void calutePastActivityCalorieChartData() {
        int i = WhenMappings.$EnumSwitchMapping$7[this.mReallyStage.ordinal()];
        if (i == 1 || i == 2 || i == 3) {
            calutePastActivityCalorieChartDataByDay();
        } else {
            calutePastActivityCalorieChartDataByWeek();
        }
    }

    private final void calutePastActivityTimeChartData() {
        int i = WhenMappings.$EnumSwitchMapping$8[this.mReallyStage.ordinal()];
        if (i == 1 || i == 2 || i == 3) {
            calutePastActivityTimeChartDataByDay();
        } else {
            calutePastActivityTimeChartDataByWeek();
        }
    }

    private final void calutePastWalkChartData() {
        int i = WhenMappings.$EnumSwitchMapping$9[this.mReallyStage.ordinal()];
        if (i == 1 || i == 2 || i == 3) {
            calutePastWalkChartDataByDay();
        } else {
            calutePastWalkChartDataByWeek();
        }
    }

    private final void caluteRecentActivityCalorieChartDataByDay() {
        int i = 0;
        List<CalorieDayData> validCalorieData = FitnessHelperKt.getValidCalorieData(getUserId(), this.mRecentStartDate, this.mRecentEndDate, false);
        List<CalorieDayData> list = validCalorieData;
        int i2 = 1;
        if (list == null || list.isEmpty()) {
            return;
        }
        int size = validCalorieData.size();
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
        calendar.setTime(DateUtil.string2Date(this.mRecentStartDate, DateUtil.DATE_FORMAT_YMD));
        int recentDayCount = getRecentDayCount();
        int i3 = 0;
        int activityCalorie = 0;
        while (i < recentDayCount) {
            String str = DateUtil.format(calendar, DateUtil.DATE_FORMAT_YMD);
            CalorieDayData calorieDayData = (CalorieDayData) null;
            if (size > i3) {
                CalorieDayData calorieDayData2 = validCalorieData.get(i3);
                if (TextUtils.equals(str, calorieDayData2.getDate())) {
                    i3++;
                    calorieDayData = calorieDayData2;
                }
            }
            if (calorieDayData != null) {
                this.mBarYMax = Math.max(calorieDayData.getActivityCalorie(), this.mBarYMax);
                this.mRecentHasDataDayCount += i2;
                activityCalorie += calorieDayData.getActivityCalorie();
                this.mRecentChartData.add(new GradientBarPoint(-1, 1.0f + i, calorieDayData.getActivityCalorie(), 0.0f, this.mRoundAnglePercent));
            } else {
                this.mRecentChartData.add(new GradientBarPoint(-1, i + 1.0f, 0.0f, 0.0f, this.mDefaultBarColor, this.mRoundAnglePercent));
            }
            calendar.add(5, 1);
            i++;
            i2 = 1;
        }
        int i4 = this.mRecentHasDataDayCount;
        if (i4 > 0) {
            this.mRecentAvg = (activityCalorie * 1.0f) / i4;
        }
    }

    private final void caluteRecentActivityCalorieChartDataByWeek() {
        List<CalorieDayData> validCalorieData = FitnessHelperKt.getValidCalorieData(getUserId(), this.mRecentStartDate, this.mRecentEndDate, false);
        List<CalorieDayData> list = validCalorieData;
        if (list == null || list.isEmpty()) {
            return;
        }
        int size = validCalorieData.size();
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
        calendar.setTime(DateUtil.string2Date(this.mRecentStartDate, DateUtil.DATE_FORMAT_YMD));
        int recentDayCount = getRecentDayCount();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int activityCalorie = 0;
        int i4 = 0;
        int activityCalorie2 = 0;
        while (i < recentDayCount) {
            String str = DateUtil.format(calendar, DateUtil.DATE_FORMAT_YMD);
            if (size > i3) {
                CalorieDayData calorieDayData = validCalorieData.get(i3);
                if (TextUtils.equals(str, calorieDayData.getDate())) {
                    i4++;
                    activityCalorie2 += calorieDayData.getActivityCalorie();
                    i2++;
                    activityCalorie += calorieDayData.getActivityCalorie();
                    i3++;
                }
            }
            i++;
            if (i % 7 == 0) {
                if (i2 > 0) {
                    int i5 = activityCalorie / i2;
                    this.mRecentChartData.add(new GradientBarPoint(-1, r5.size() + 1.0f, i5, 0.0f, this.mRoundAnglePercent));
                    this.mBarYMax = Math.max(this.mBarYMax, i5);
                } else {
                    this.mRecentChartData.add(new GradientBarPoint(-1, r3.size() + 1.0f, 0.0f, 0.0f, this.mRoundAnglePercent));
                }
                i2 = 0;
                activityCalorie = 0;
            }
            calendar.add(5, 1);
        }
        if (i2 > 0) {
            int i6 = activityCalorie / i2;
            this.mRecentChartData.add(new GradientBarPoint(-1, r1.size() + 1, i6, 0.0f, this.mRoundAnglePercent));
            this.mBarYMax = Math.max(this.mBarYMax, i6);
        } else {
            this.mRecentChartData.add(new GradientBarPoint(-1, r1.size() + 1, 0.0f, 0.0f, this.mRoundAnglePercent));
        }
        this.mRecentHasDataDayCount = i4;
        if (i4 > 0) {
            this.mRecentAvg = (activityCalorie2 * 1.0f) / i4;
        }
    }

    private final void calutePastActivityCalorieChartDataByDay() {
        int i = 0;
        List<CalorieDayData> validCalorieData = FitnessHelperKt.getValidCalorieData(getUserId(), this.mPastStartDate, this.mPastEndDate, false);
        List<CalorieDayData> list = validCalorieData;
        int i2 = 1;
        if (list == null || list.isEmpty()) {
            return;
        }
        int size = validCalorieData.size();
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
        calendar.setTime(DateUtil.string2Date(this.mPastStartDate, DateUtil.DATE_FORMAT_YMD));
        int pastDayCount = getPastDayCount();
        int i3 = 0;
        int activityCalorie = 0;
        while (i < pastDayCount) {
            String str = DateUtil.format(calendar, DateUtil.DATE_FORMAT_YMD);
            CalorieDayData calorieDayData = (CalorieDayData) null;
            if (size > i3) {
                CalorieDayData calorieDayData2 = validCalorieData.get(i3);
                if (TextUtils.equals(str, calorieDayData2.getDate())) {
                    i3++;
                    calorieDayData = calorieDayData2;
                }
            }
            if (calorieDayData != null) {
                this.mBarYMax = Math.max(calorieDayData.getActivityCalorie(), this.mBarYMax);
                activityCalorie += calorieDayData.getActivityCalorie();
                this.mPastHasDataDayCount += i2;
                this.mPastChartData.add(new GradientBarPoint(-1, 1.0f + i, calorieDayData.getActivityCalorie(), 0.0f, this.mRoundAnglePercent));
            } else {
                this.mPastChartData.add(new GradientBarPoint(-1, i + 1.0f, 0.0f, 0.0f, this.mDefaultBarColor, this.mRoundAnglePercent));
            }
            calendar.add(5, 1);
            i++;
            i2 = 1;
        }
        int i4 = this.mPastHasDataDayCount;
        if (i4 > 0) {
            this.mPastAvg = (activityCalorie * 1.0f) / i4;
        }
    }

    private final void calutePastActivityCalorieChartDataByWeek() {
        List<CalorieDayData> validCalorieData = FitnessHelperKt.getValidCalorieData(getUserId(), this.mPastStartDate, this.mPastEndDate, false);
        List<CalorieDayData> list = validCalorieData;
        if (list == null || list.isEmpty()) {
            return;
        }
        int size = validCalorieData.size();
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
        calendar.setTime(DateUtil.string2Date(this.mPastStartDate, DateUtil.DATE_FORMAT_YMD));
        int pastDayCount = getPastDayCount();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int activityCalorie = 0;
        int i4 = 0;
        int activityCalorie2 = 0;
        while (i < pastDayCount) {
            String str = DateUtil.format(calendar, DateUtil.DATE_FORMAT_YMD);
            if (size > i3) {
                CalorieDayData calorieDayData = validCalorieData.get(i3);
                if (TextUtils.equals(str, calorieDayData.getDate())) {
                    i4++;
                    activityCalorie2 += calorieDayData.getActivityCalorie();
                    i2++;
                    activityCalorie += calorieDayData.getActivityCalorie();
                    i3++;
                }
            }
            i++;
            if (i % 7 == 0) {
                if (i2 > 0) {
                    int i5 = activityCalorie / i2;
                    this.mPastChartData.add(new GradientBarPoint(-1, r5.size() + 1.0f, i5, 0.0f, this.mRoundAnglePercent));
                    this.mBarYMax = Math.max(this.mBarYMax, i5);
                } else {
                    this.mPastChartData.add(new GradientBarPoint(-1, r3.size() + 1.0f, 0.0f, 0.0f, this.mRoundAnglePercent));
                }
                i2 = 0;
                activityCalorie = 0;
            }
            calendar.add(5, 1);
        }
        if (i2 > 0) {
            int i6 = activityCalorie / i2;
            this.mPastChartData.add(new GradientBarPoint(-1, r1.size() + 1, i6, 0.0f, this.mRoundAnglePercent));
            this.mBarYMax = Math.max(this.mBarYMax, i6);
        } else {
            this.mPastChartData.add(new GradientBarPoint(-1, r1.size() + 1, 0.0f, 0.0f, this.mRoundAnglePercent));
        }
        this.mPastHasDataDayCount = i4;
        if (i4 > 0) {
            this.mPastAvg = (activityCalorie2 * 1.0f) / i4;
        }
    }

    private final void caluteRecentActivityTimeChartDataByDay() {
        List<ActiveTimeDayData> validActiveTimeData = FitnessHelperKt.getValidActiveTimeData(getUserId(), this.mRecentStartDate, this.mRecentEndDate);
        List<ActiveTimeDayData> list = validActiveTimeData;
        int i = 0;
        int i2 = 1;
        if (list == null || list.isEmpty()) {
            return;
        }
        int size = validActiveTimeData.size();
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
        calendar.setTime(DateUtil.string2Date(this.mRecentStartDate, DateUtil.DATE_FORMAT_YMD));
        int recentDayCount = getRecentDayCount();
        int i3 = 0;
        int mediumOrHigherSeconds = 0;
        while (i < recentDayCount) {
            ActiveTimeDayData activeTimeDayData = (ActiveTimeDayData) null;
            String str = DateUtil.format(calendar, DateUtil.DATE_FORMAT_YMD);
            if (size > i3) {
                ActiveTimeDayData activeTimeDayData2 = validActiveTimeData.get(i3);
                if (TextUtils.equals(str, activeTimeDayData2.getDate())) {
                    i3++;
                    activeTimeDayData = activeTimeDayData2;
                }
            }
            if (activeTimeDayData != null) {
                this.mBarYMax = Math.max(activeTimeDayData.getMediumOrHigherSeconds() / 60, this.mBarYMax);
                mediumOrHigherSeconds += activeTimeDayData.getMediumOrHigherSeconds();
                this.mRecentHasDataDayCount += i2;
                this.mRecentChartData.add(new GradientBarPoint(-1, 1.0f + i, activeTimeDayData.getMediumOrHigherSeconds() / 60.0f, 0.0f, this.mRoundAnglePercent));
            } else {
                this.mRecentChartData.add(new GradientBarPoint(-1, i + 1.0f, 0.0f, 0.0f, this.mDefaultBarColor, this.mRoundAnglePercent));
            }
            calendar.add(5, 1);
            i++;
            i2 = 1;
        }
        if (this.mRecentHasDataDayCount > 0) {
            this.mRecentAvg = (mediumOrHigherSeconds * 1.0f) / (r1 * 60);
        }
    }

    private final void caluteRecentActivityTimeChartDataByWeek() {
        List<ActiveTimeDayData> validActiveTimeData = FitnessHelperKt.getValidActiveTimeData(getUserId(), this.mRecentStartDate, this.mRecentEndDate);
        List<ActiveTimeDayData> list = validActiveTimeData;
        if (list == null || list.isEmpty()) {
            return;
        }
        int size = validActiveTimeData.size();
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
        calendar.setTime(DateUtil.string2Date(this.mRecentStartDate, DateUtil.DATE_FORMAT_YMD));
        int recentDayCount = getRecentDayCount();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int mediumOrHigherSeconds = 0;
        int i4 = 0;
        int mediumOrHigherSeconds2 = 0;
        while (i < recentDayCount) {
            String str = DateUtil.format(calendar, DateUtil.DATE_FORMAT_YMD);
            if (size > i3) {
                ActiveTimeDayData activeTimeDayData = validActiveTimeData.get(i3);
                if (TextUtils.equals(str, activeTimeDayData.getDate())) {
                    i4++;
                    mediumOrHigherSeconds2 += activeTimeDayData.getMediumOrHigherSeconds();
                    i2++;
                    mediumOrHigherSeconds += activeTimeDayData.getMediumOrHigherSeconds();
                    i3++;
                }
            }
            i++;
            if (i % 7 == 0) {
                if (i2 > 0) {
                    this.mRecentChartData.add(new GradientBarPoint(-1, r3.size() + 1.0f, mediumOrHigherSeconds / (i2 * 60.0f), 0.0f, this.mRoundAnglePercent));
                    this.mBarYMax = Math.max(this.mBarYMax, mediumOrHigherSeconds / (i2 * 60));
                } else {
                    this.mRecentChartData.add(new GradientBarPoint(-1, r3.size() + 1.0f, 0.0f, 0.0f, this.mRoundAnglePercent));
                }
                i2 = 0;
                mediumOrHigherSeconds = 0;
            }
            calendar.add(5, 1);
        }
        if (i2 > 0) {
            this.mRecentChartData.add(new GradientBarPoint(-1, r1.size() + 1, mediumOrHigherSeconds / (i2 * 60.0f), 0.0f, this.mRoundAnglePercent));
            this.mBarYMax = Math.max(this.mBarYMax, mediumOrHigherSeconds / (i2 * 60));
        } else {
            this.mRecentChartData.add(new GradientBarPoint(-1, r1.size() + 1, 0.0f, 0.0f, this.mRoundAnglePercent));
        }
        this.mRecentHasDataDayCount = i4;
        if (i4 > 0) {
            this.mRecentAvg = (mediumOrHigherSeconds2 * 1.0f) / (i4 * 60);
        }
    }

    private final void calutePastActivityTimeChartDataByDay() {
        List<ActiveTimeDayData> validActiveTimeData = FitnessHelperKt.getValidActiveTimeData(getUserId(), this.mPastStartDate, this.mPastEndDate);
        List<ActiveTimeDayData> list = validActiveTimeData;
        int i = 0;
        int i2 = 1;
        if (list == null || list.isEmpty()) {
            return;
        }
        int size = validActiveTimeData.size();
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
        calendar.setTime(DateUtil.string2Date(this.mPastStartDate, DateUtil.DATE_FORMAT_YMD));
        int pastDayCount = getPastDayCount();
        int i3 = 0;
        int mediumOrHigherSeconds = 0;
        while (i < pastDayCount) {
            String str = DateUtil.format(calendar, DateUtil.DATE_FORMAT_YMD);
            ActiveTimeDayData activeTimeDayData = (ActiveTimeDayData) null;
            if (size > i3) {
                ActiveTimeDayData activeTimeDayData2 = validActiveTimeData.get(i3);
                if (TextUtils.equals(str, activeTimeDayData2.getDate())) {
                    i3++;
                    activeTimeDayData = activeTimeDayData2;
                }
            }
            if (activeTimeDayData != null) {
                this.mBarYMax = Math.max(activeTimeDayData.getMediumOrHigherSeconds() / 60, this.mBarYMax);
                mediumOrHigherSeconds += activeTimeDayData.getMediumOrHigherSeconds();
                this.mPastHasDataDayCount += i2;
                this.mPastChartData.add(new GradientBarPoint(-1, 1.0f + i, activeTimeDayData.getMediumOrHigherSeconds() / 60.0f, 0.0f, this.mRoundAnglePercent));
                this.mBarYMax = Math.max(this.mBarYMax, activeTimeDayData.getMediumOrHigherSeconds() / 60);
            } else {
                this.mPastChartData.add(new GradientBarPoint(-1, i + 1.0f, 0.0f, 0.0f, this.mDefaultBarColor, this.mRoundAnglePercent));
            }
            calendar.add(5, 1);
            i++;
            i2 = 1;
        }
        if (this.mPastHasDataDayCount > 0) {
            this.mPastAvg = (mediumOrHigherSeconds * 1.0f) / (r1 * 60);
        }
    }

    private final void calutePastActivityTimeChartDataByWeek() {
        List<ActiveTimeDayData> validActiveTimeData = FitnessHelperKt.getValidActiveTimeData(getUserId(), this.mPastStartDate, this.mPastEndDate);
        List<ActiveTimeDayData> list = validActiveTimeData;
        int i = 1;
        if (list == null || list.isEmpty()) {
            return;
        }
        int size = validActiveTimeData.size();
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
        calendar.setTime(DateUtil.string2Date(this.mPastStartDate, DateUtil.DATE_FORMAT_YMD));
        int pastDayCount = getPastDayCount();
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int mediumOrHigherSeconds = 0;
        int i5 = 0;
        int mediumOrHigherSeconds2 = 0;
        while (i2 < pastDayCount) {
            String str = DateUtil.format(calendar, DateUtil.DATE_FORMAT_YMD);
            if (size > i4) {
                ActiveTimeDayData activeTimeDayData = validActiveTimeData.get(i4);
                if (TextUtils.equals(str, activeTimeDayData.getDate())) {
                    i5++;
                    mediumOrHigherSeconds2 += activeTimeDayData.getMediumOrHigherSeconds();
                    i3++;
                    mediumOrHigherSeconds += activeTimeDayData.getMediumOrHigherSeconds();
                    i4++;
                }
            }
            calendar.add(5, i);
            i2++;
            if (i2 % 7 == 0) {
                if (i3 > 0) {
                    this.mPastChartData.add(new GradientBarPoint(-1, r4.size() + 1.0f, mediumOrHigherSeconds / (i3 * 60.0f), 0.0f, this.mRoundAnglePercent));
                    this.mBarYMax = Math.max(mediumOrHigherSeconds / (i3 * 60), this.mBarYMax);
                } else {
                    this.mPastChartData.add(new GradientBarPoint(-1, r3.size() + 1.0f, 0.0f, 0.0f, this.mRoundAnglePercent));
                }
                i3 = 0;
                mediumOrHigherSeconds = 0;
            }
            i = 1;
        }
        if (i3 > 0) {
            this.mPastChartData.add(new GradientBarPoint(-1, r1.size() + 1, mediumOrHigherSeconds / (i3 * 60.0f), 0.0f, this.mRoundAnglePercent));
            this.mBarYMax = Math.max(this.mBarYMax, mediumOrHigherSeconds / (i3 * 60));
        } else {
            this.mPastChartData.add(new GradientBarPoint(-1, r1.size() + 1, 0.0f, 0.0f, this.mRoundAnglePercent));
        }
        this.mPastHasDataDayCount = i5;
        if (i5 > 0) {
            this.mPastAvg = (mediumOrHigherSeconds2 * 1.0f) / (i5 * 60);
        }
    }

    private final void caluteRecentWalkChartDataByDay() {
        List<WalkDayData> validWalkData = FitnessHelperKt.getValidWalkData(getUserId(), this.mRecentStartDate, this.mRecentEndDate);
        List<WalkDayData> list = validWalkData;
        int i = 0;
        int i2 = 1;
        if (list == null || list.isEmpty()) {
            return;
        }
        int size = validWalkData.size();
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
        calendar.setTime(DateUtil.string2Date(this.mRecentStartDate, DateUtil.DATE_FORMAT_YMD));
        int recentDayCount = getRecentDayCount();
        int i3 = 0;
        int reachSeconds = 0;
        while (i < recentDayCount) {
            String str = DateUtil.format(calendar, DateUtil.DATE_FORMAT_YMD);
            WalkDayData walkDayData = (WalkDayData) null;
            if (size > i3) {
                WalkDayData walkDayData2 = validWalkData.get(i3);
                if (TextUtils.equals(str, walkDayData2.getDate())) {
                    i3++;
                    walkDayData = walkDayData2;
                }
            }
            if (walkDayData != null) {
                this.mBarYMax = Math.max(walkDayData.getReachSeconds() / 3600, this.mBarYMax);
                this.mRecentHasDataDayCount += i2;
                reachSeconds += walkDayData.getReachSeconds();
                this.mRecentChartData.add(new GradientBarPoint(-1, 1.0f + i, walkDayData.getReachSeconds() / 3600.0f, 0.0f, this.mRoundAnglePercent));
            } else {
                this.mRecentChartData.add(new GradientBarPoint(-1, i + 1.0f, 0.0f, 0.0f, this.mDefaultBarColor, this.mRoundAnglePercent));
            }
            calendar.add(5, 1);
            i++;
            i2 = 1;
        }
        int i4 = this.mRecentHasDataDayCount;
        if (i4 > 0) {
            this.mRecentAvg = (reachSeconds * 1.0f) / (i4 * 3600.0f);
        }
    }

    private final void caluteRecentWalkChartDataByWeek() {
        List<WalkDayData> validWalkData = FitnessHelperKt.getValidWalkData(getUserId(), this.mRecentStartDate, this.mRecentEndDate);
        List<WalkDayData> list = validWalkData;
        if (list == null || list.isEmpty()) {
            return;
        }
        int size = validWalkData.size();
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
        calendar.setTime(DateUtil.string2Date(this.mRecentStartDate, DateUtil.DATE_FORMAT_YMD));
        int recentDayCount = getRecentDayCount();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int reachSeconds = 0;
        int i4 = 0;
        int reachSeconds2 = 0;
        while (i < recentDayCount) {
            String str = DateUtil.format(calendar, DateUtil.DATE_FORMAT_YMD);
            if (size > i3) {
                WalkDayData walkDayData = validWalkData.get(i3);
                if (TextUtils.equals(str, walkDayData.getDate())) {
                    i4++;
                    reachSeconds2 += walkDayData.getReachSeconds();
                    i2++;
                    reachSeconds += walkDayData.getReachSeconds();
                    i3++;
                }
            }
            i++;
            if (i % 7 == 0) {
                if (i2 > 0) {
                    float f2 = reachSeconds / (i2 * 3600.0f);
                    this.mRecentChartData.add(new GradientBarPoint(-1, r3.size() + 1.0f, f2, 0.0f, this.mRoundAnglePercent));
                    this.mBarYMax = Math.max(this.mBarYMax, MathKt.roundToInt(f2));
                } else {
                    this.mRecentChartData.add(new GradientBarPoint(-1, r3.size() + 1.0f, 0.0f, 0.0f, this.mRoundAnglePercent));
                }
                i2 = 0;
                reachSeconds = 0;
            }
            calendar.add(5, 1);
        }
        if (i2 > 0) {
            float f3 = reachSeconds / (i2 * 3600.0f);
            this.mRecentChartData.add(new GradientBarPoint(-1, r1.size() + 1, f3, 0.0f, this.mRoundAnglePercent));
            this.mBarYMax = Math.max(this.mBarYMax, MathKt.roundToInt(f3));
        } else {
            this.mRecentChartData.add(new GradientBarPoint(-1, r1.size() + 1, 0.0f, 0.0f, this.mRoundAnglePercent));
        }
        this.mRecentHasDataDayCount = i4;
        if (i4 > 0) {
            this.mRecentAvg = (reachSeconds2 * 1.0f) / (i4 * 3600.0f);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x006a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void calutePastWalkChartDataByDay() {
        /*
            Method dump skipped, instruction units count: 221
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.home.recentsituation.RecentSituationDetailPresenter.calutePastWalkChartDataByDay():void");
    }

    private final void calutePastWalkChartDataByWeek() {
        List<WalkDayData> validWalkData = FitnessHelperKt.getValidWalkData(getUserId(), this.mPastStartDate, this.mPastEndDate);
        List<WalkDayData> list = validWalkData;
        if (list == null || list.isEmpty()) {
            return;
        }
        int size = validWalkData.size();
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
        calendar.setTime(DateUtil.string2Date(this.mPastStartDate, DateUtil.DATE_FORMAT_YMD));
        int pastDayCount = getPastDayCount();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int reachSeconds = 0;
        int i4 = 0;
        int reachSeconds2 = 0;
        while (i < pastDayCount) {
            String str = DateUtil.format(calendar, DateUtil.DATE_FORMAT_YMD);
            if (size > i3) {
                WalkDayData walkDayData = validWalkData.get(i3);
                if (TextUtils.equals(str, walkDayData.getDate())) {
                    i4++;
                    reachSeconds2 += walkDayData.getReachSeconds();
                    i2++;
                    reachSeconds += walkDayData.getReachSeconds();
                    i3++;
                }
            }
            i++;
            if (i % 7 == 0) {
                if (i2 > 0) {
                    float f2 = reachSeconds / (i2 * 3600.0f);
                    this.mPastChartData.add(new GradientBarPoint(-1, r3.size() + 1.0f, f2, 0.0f, this.mRoundAnglePercent));
                    this.mBarYMax = Math.max(this.mBarYMax, MathKt.roundToInt(f2));
                } else {
                    this.mPastChartData.add(new GradientBarPoint(-1, r3.size() + 1.0f, 0.0f, 0.0f, this.mRoundAnglePercent));
                }
                i2 = 0;
                reachSeconds = 0;
            }
            calendar.add(5, 1);
        }
        if (i2 > 0) {
            float f3 = reachSeconds / (i2 * 3600.0f);
            this.mPastChartData.add(new GradientBarPoint(-1, r1.size() + 1, f3, 0.0f, this.mRoundAnglePercent));
            this.mBarYMax = Math.max(this.mBarYMax, MathKt.roundToInt(f3));
        } else {
            this.mPastChartData.add(new GradientBarPoint(-1, r1.size() + 1, 0.0f, 0.0f, this.mRoundAnglePercent));
        }
        this.mPastHasDataDayCount = i4;
        if (i4 > 0) {
            this.mPastAvg = (reachSeconds2 * 1.0f) / (i4 * 3600.0f);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x001a  */
    /* JADX WARN: Type inference failed for: r0v10, types: [T, java.util.List] */
    /* JADX WARN: Type inference failed for: r0v3, types: [T, java.util.List] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final /* synthetic */ java.lang.Object calculateActivityCalorieLineChart(int r26, kotlin.coroutines.Continuation<? super kotlin.Unit> r27) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 375
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.home.recentsituation.RecentSituationDetailPresenter.calculateActivityCalorieLineChart(int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.recentsituation.RecentSituationDetailPresenter$calculateActivityCalorieLineChart$2, reason: invalid class name */
    /* JADX INFO: compiled from: RecentSituationDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.recentsituation.RecentSituationDetailPresenter$calculateActivityCalorieLineChart$2", f = "RecentSituationDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.ObjectRef $lineChartList;
        final /* synthetic */ Ref.IntRef $maxCalorie;
        final /* synthetic */ int $weekCount;
        final /* synthetic */ Ref.ObjectRef $xLabelList;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(int i, Ref.IntRef intRef, Ref.ObjectRef objectRef, Ref.ObjectRef objectRef2, Continuation continuation) {
            super(2, continuation);
            this.$weekCount = i;
            this.$maxCalorie = intRef;
            this.$xLabelList = objectRef;
            this.$lineChartList = objectRef2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            AnonymousClass2 anonymousClass2 = RecentSituationDetailPresenter.this.new AnonymousClass2(this.$weekCount, this.$maxCalorie, this.$xLabelList, this.$lineChartList, completion);
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
            IRecentSituationDetailView iRecentSituationDetailViewAccess$getView = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
            if (iRecentSituationDetailViewAccess$getView != null) {
                iRecentSituationDetailViewAccess$getView.updateLineXMaxmin(this.$weekCount - 1, 1);
            }
            IRecentSituationDetailView iRecentSituationDetailViewAccess$getView2 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
            if (iRecentSituationDetailViewAccess$getView2 != null) {
                iRecentSituationDetailViewAccess$getView2.updateLineYMaxmin(this.$maxCalorie.element, 0);
            }
            IRecentSituationDetailView iRecentSituationDetailViewAccess$getView3 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
            if (iRecentSituationDetailViewAccess$getView3 != null) {
                iRecentSituationDetailViewAccess$getView3.updateLineXLabel((List) this.$xLabelList.element);
            }
            IRecentSituationDetailView iRecentSituationDetailViewAccess$getView4 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
            if (iRecentSituationDetailViewAccess$getView4 != null) {
                iRecentSituationDetailViewAccess$getView4.updateLineYLabel(CollectionsKt.mutableListOf("", String.valueOf(MathKt.roundToInt(this.$maxCalorie.element / 2.0f)), String.valueOf(this.$maxCalorie.element)));
            }
            IRecentSituationDetailView iRecentSituationDetailViewAccess$getView5 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
            if (iRecentSituationDetailViewAccess$getView5 != null) {
                iRecentSituationDetailViewAccess$getView5.updateLineChartData((List) this.$lineChartList.element);
            }
            RecentSituationDetailPresenter.this.getActivityCalorieTarget();
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [T, java.util.List] */
    /* JADX WARN: Type inference failed for: r0v7, types: [T, java.util.List] */
    final /* synthetic */ Object caluteActivityTimeLineChart(int i, Continuation<? super Unit> continuation) {
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = new ArrayList();
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        int weekStart = runTimeUtil.getWeekStart();
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
        calendar.setFirstDayOfWeek(weekStart);
        calendar.set(7, weekStart);
        calendar.add(4, (-i) + 1);
        Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = new ArrayList();
        Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = 0;
        int i2 = i - 1;
        for (int i3 = 0; i3 < i2; i3++) {
            String str = DateUtil.format(calendar, DateUtil.DATE_FORMAT_YMD);
            List list = (List) objectRef2.element;
            String str2 = DateUtil.format(calendar, DateUtil.DATE_FORMAT_MD_2);
            Intrinsics.checkExpressionValueIsNotNull(str2, "DateUtil.format(calendar…ateUtil.DATE_FORMAT_MD_2)");
            list.add(str2);
            calendar.add(5, 6);
            String str3 = DateUtil.format(calendar, DateUtil.DATE_FORMAT_YMD);
            if (FitnessHelperKt.getValidActiveTimeCount(getUserId(), str, str3) > 0) {
                float fCaluteRecentSituationActiveTimeAvg = FitnessHelperKt.caluteRecentSituationActiveTimeAvg(getUserId(), str, str3);
                intRef.element = Math.max(intRef.element, MathKt.roundToInt(fCaluteRecentSituationActiveTimeAvg));
                ((List) objectRef.element).add(new RecentSituationWeekChart.ChartBean(0, false, i3 + 1, fCaluteRecentSituationActiveTimeAvg, 3, null));
            } else {
                ((List) objectRef.element).add(new RecentSituationWeekChart.ChartBean(this.mDefaultBarColor, false, i3 + 1, 0.0f));
            }
            calendar.add(5, 1);
        }
        if (intRef.element < 2) {
            intRef.element = 2;
        }
        Object objWithContext = BuildersKt.withContext(Dispatchers.getMain(), new C03902(i, intRef, objectRef2, objectRef, null), continuation);
        return objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContext : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.recentsituation.RecentSituationDetailPresenter$caluteActivityTimeLineChart$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RecentSituationDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.recentsituation.RecentSituationDetailPresenter$caluteActivityTimeLineChart$2", f = "RecentSituationDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03902 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.ObjectRef $lineChartList;
        final /* synthetic */ Ref.IntRef $maxActiveTime;
        final /* synthetic */ int $weekCount;
        final /* synthetic */ Ref.ObjectRef $xLabelList;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03902(int i, Ref.IntRef intRef, Ref.ObjectRef objectRef, Ref.ObjectRef objectRef2, Continuation continuation) {
            super(2, continuation);
            this.$weekCount = i;
            this.$maxActiveTime = intRef;
            this.$xLabelList = objectRef;
            this.$lineChartList = objectRef2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03902 c03902 = RecentSituationDetailPresenter.this.new C03902(this.$weekCount, this.$maxActiveTime, this.$xLabelList, this.$lineChartList, completion);
            c03902.p$ = (CoroutineScope) obj;
            return c03902;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03902) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            RecentSituationDetailPresenter.this.getActivityTimeTarget();
            IRecentSituationDetailView iRecentSituationDetailViewAccess$getView = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
            if (iRecentSituationDetailViewAccess$getView != null) {
                iRecentSituationDetailViewAccess$getView.updateLineXMaxmin(this.$weekCount - 1, 1);
            }
            IRecentSituationDetailView iRecentSituationDetailViewAccess$getView2 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
            if (iRecentSituationDetailViewAccess$getView2 != null) {
                iRecentSituationDetailViewAccess$getView2.updateLineYMaxmin(this.$maxActiveTime.element, 0);
            }
            IRecentSituationDetailView iRecentSituationDetailViewAccess$getView3 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
            if (iRecentSituationDetailViewAccess$getView3 != null) {
                iRecentSituationDetailViewAccess$getView3.updateLineXLabel((List) this.$xLabelList.element);
            }
            IRecentSituationDetailView iRecentSituationDetailViewAccess$getView4 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
            if (iRecentSituationDetailViewAccess$getView4 != null) {
                iRecentSituationDetailViewAccess$getView4.updateLineYLabel(CollectionsKt.mutableListOf("", String.valueOf(MathKt.roundToInt(this.$maxActiveTime.element / 2.0f)), String.valueOf(this.$maxActiveTime.element)));
            }
            IRecentSituationDetailView iRecentSituationDetailViewAccess$getView5 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
            if (iRecentSituationDetailViewAccess$getView5 == null) {
                return null;
            }
            iRecentSituationDetailViewAccess$getView5.updateLineChartData((List) this.$lineChartList.element);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [T, java.util.List] */
    /* JADX WARN: Type inference failed for: r0v7, types: [T, java.util.List] */
    final /* synthetic */ Object calculateWalkLineChart(int i, Continuation<? super Unit> continuation) {
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = new ArrayList();
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        int weekStart = runTimeUtil.getWeekStart();
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
        calendar.setFirstDayOfWeek(weekStart);
        calendar.set(7, weekStart);
        calendar.add(4, (-i) + 1);
        Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = new ArrayList();
        Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = 0;
        int i2 = i - 1;
        for (int i3 = 0; i3 < i2; i3++) {
            String startDate = DateUtil.format(calendar, DateUtil.DATE_FORMAT_YMD);
            List list = (List) objectRef2.element;
            String str = DateUtil.format(calendar, DateUtil.DATE_FORMAT_MD_2);
            Intrinsics.checkExpressionValueIsNotNull(str, "DateUtil.format(calendar…ateUtil.DATE_FORMAT_MD_2)");
            list.add(str);
            calendar.add(5, 6);
            String endDate = DateUtil.format(calendar, DateUtil.DATE_FORMAT_YMD);
            if (FitnessHelperKt.getValidWalkCount(getUserId(), startDate, endDate) > 0) {
                long userId = getUserId();
                Intrinsics.checkExpressionValueIsNotNull(startDate, "startDate");
                Intrinsics.checkExpressionValueIsNotNull(endDate, "endDate");
                float fCaluteRecentSituationWalkAvg = FitnessHelperKt.caluteRecentSituationWalkAvg(userId, startDate, endDate);
                intRef.element = Math.max(intRef.element, MathKt.roundToInt(fCaluteRecentSituationWalkAvg));
                ((List) objectRef.element).add(new RecentSituationWeekChart.ChartBean(0, false, i3 + 1, fCaluteRecentSituationWalkAvg, 3, null));
            } else {
                ((List) objectRef.element).add(new RecentSituationWeekChart.ChartBean(this.mDefaultBarColor, false, i3 + 1, 0.0f));
            }
            calendar.add(5, 1);
        }
        if (intRef.element < 2) {
            intRef.element = 2;
        }
        Object objWithContext = BuildersKt.withContext(Dispatchers.getMain(), new C03882(i, intRef, objectRef2, objectRef, null), continuation);
        return objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContext : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.recentsituation.RecentSituationDetailPresenter$calculateWalkLineChart$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RecentSituationDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.recentsituation.RecentSituationDetailPresenter$calculateWalkLineChart$2", f = "RecentSituationDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03882 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.ObjectRef $lineChartList;
        final /* synthetic */ Ref.IntRef $maxWalk;
        final /* synthetic */ int $weekCount;
        final /* synthetic */ Ref.ObjectRef $xLabelList;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03882(int i, Ref.IntRef intRef, Ref.ObjectRef objectRef, Ref.ObjectRef objectRef2, Continuation continuation) {
            super(2, continuation);
            this.$weekCount = i;
            this.$maxWalk = intRef;
            this.$xLabelList = objectRef;
            this.$lineChartList = objectRef2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03882 c03882 = RecentSituationDetailPresenter.this.new C03882(this.$weekCount, this.$maxWalk, this.$xLabelList, this.$lineChartList, completion);
            c03882.p$ = (CoroutineScope) obj;
            return c03882;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03882) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            RecentSituationDetailPresenter.this.getWalkTarget();
            IRecentSituationDetailView iRecentSituationDetailViewAccess$getView = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
            if (iRecentSituationDetailViewAccess$getView != null) {
                iRecentSituationDetailViewAccess$getView.updateLineXMaxmin(this.$weekCount - 1, 1);
            }
            IRecentSituationDetailView iRecentSituationDetailViewAccess$getView2 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
            if (iRecentSituationDetailViewAccess$getView2 != null) {
                iRecentSituationDetailViewAccess$getView2.updateLineYMaxmin(this.$maxWalk.element, 0);
            }
            IRecentSituationDetailView iRecentSituationDetailViewAccess$getView3 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
            if (iRecentSituationDetailViewAccess$getView3 != null) {
                iRecentSituationDetailViewAccess$getView3.updateLineXLabel((List) this.$xLabelList.element);
            }
            IRecentSituationDetailView iRecentSituationDetailViewAccess$getView4 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
            if (iRecentSituationDetailViewAccess$getView4 != null) {
                iRecentSituationDetailViewAccess$getView4.updateLineYLabel(CollectionsKt.mutableListOf("", String.valueOf(MathKt.roundToInt(this.$maxWalk.element / 2.0f)), String.valueOf(this.$maxWalk.element)));
            }
            IRecentSituationDetailView iRecentSituationDetailViewAccess$getView5 = RecentSituationDetailPresenter.access$getView(RecentSituationDetailPresenter.this);
            if (iRecentSituationDetailViewAccess$getView5 == null) {
                return null;
            }
            iRecentSituationDetailViewAccess$getView5.updateLineChartData((List) this.$lineChartList.element);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<GradientBarPoint> generateDefaultDayChartData(int dayCount, int yMax) {
        ArrayList arrayList = new ArrayList();
        float f2 = yMax * this.mDefaultBarRadius;
        int i = 0;
        while (i < dayCount) {
            i++;
            arrayList.add(new GradientBarPoint(-1, i, f2, 0.0f, this.mDefaultBarColor, this.mRoundAnglePercent));
        }
        return arrayList;
    }

    private final void clearData() {
        this.mRecentChartData.clear();
        this.mPastChartData.clear();
        this.mRecentAvg = 0.0f;
        this.mPastAvg = 0.0f;
        this.mRecentHasDataDayCount = 0;
        this.mPastHasDataDayCount = 0;
    }

    private final long getUserId() {
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        return runTimeUtil.getUserId();
    }

    public final void saveUserTarget(int fTarget, int sTarget) {
        int i;
        Integer num = (Integer) null;
        this.mCalorieTarget = num;
        this.mExerciseTimeTarget = num;
        this.mWalkTimeTarget = num;
        this.mWalkStep = num;
        if (BLEManager.isConnected()) {
            IRecentSituationDetailView view = getView();
            if (view != null) {
                view.showDialog();
            }
            HomeHelperKt.printAndSave("用户设备已经连接，准备设置三环目标", this.TAG);
            UserTargetNew userTarget = GreenDaoUtil.queryUserLatestTarget(getUserId());
            if (userTarget == null) {
                userTarget = RunTimeUtil.generateDefaultUserTargetNew(getUserId());
            }
            int iCaluteBMR = FitnessHelperKt.caluteBMR(getUserId());
            int iAjustCalorieMax = 50;
            if (MathKt.roundToInt(((double) iCaluteBMR) * 0.8d) < 50) {
                i = 10;
            } else {
                float f2 = iCaluteBMR;
                int iAjustCalorieMin = ajustCalorieMin(MathKt.roundToInt(0.15f * f2));
                iAjustCalorieMax = ajustCalorieMax(MathKt.roundToInt(f2 * 0.8f));
                i = iAjustCalorieMin;
            }
            CalorieAndDistanceGoal calorieAndDistanceGoal = LocalDataManager.getCalorieAndDistanceGoal();
            if (calorieAndDistanceGoal == null) {
                calorieAndDistanceGoal = new CalorieAndDistanceGoal();
            }
            Intrinsics.checkExpressionValueIsNotNull(userTarget, "userTarget");
            calorieAndDistanceGoal.calorie = userTarget.getCalories() > 0 ? userTarget.getCalories() : 500;
            calorieAndDistanceGoal.distance = userTarget.getDistance() > 0 ? userTarget.getDistance() : 5000;
            calorieAndDistanceGoal.mid_high_time_goal = userTarget.getActivityTime() > 0 ? userTarget.getActivityTime() : 1800L;
            calorieAndDistanceGoal.walk_goal_time = userTarget.getWalk() >= 3600 ? userTarget.getWalk() / 3600 : 12;
            calorieAndDistanceGoal.calorie_min = i;
            calorieAndDistanceGoal.calorie_max = iAjustCalorieMax;
            int i2 = this.mPageType;
            if (i2 == RecentSituationDetailActivity.INSTANCE.getPAGE_ACTIVITY_CALORIE()) {
                this.mCalorieTarget = Integer.valueOf(fTarget);
                calorieAndDistanceGoal.calorie = fTarget;
            } else if (i2 == RecentSituationDetailActivity.INSTANCE.getPAGE_ACTIVITY_TIME()) {
                this.mExerciseTimeTarget = Integer.valueOf(fTarget * 60);
                calorieAndDistanceGoal.mid_high_time_goal = ((long) fTarget) * 60;
            } else if (i2 == RecentSituationDetailActivity.INSTANCE.getPAGE_WALKING()) {
                this.mWalkTimeTarget = Integer.valueOf(fTarget * 3600);
                this.mWalkStep = Integer.valueOf(sTarget);
                calorieAndDistanceGoal.walk_goal_time = fTarget;
            }
            RecentSituationDetailPresenter recentSituationDetailPresenter = this;
            BLEManager.unregisterOtherProtocolCallBack(recentSituationDetailPresenter);
            BLEManager.registerOtherProtocolCallBack(recentSituationDetailPresenter);
            HomeHelperKt.printAndSave$default("开始将数据三环目标设置数据发送到设备端calorieGoal=" + calorieAndDistanceGoal, null, 2, null);
            BLEManager.setCalorieAndDistanceGoal(calorieAndDistanceGoal);
            return;
        }
        HomeHelperKt.printAndSave("用户的设备没有连接，三环目标设置失败", this.TAG);
        IRecentSituationDetailView view2 = getView();
        if (view2 != null) {
            view2.onTargetSettingFailed();
        }
    }

    public final boolean supportActiveCalorieTarget() {
        SupportFunctionInfo supportFunctionInfo;
        return BLEManager.isConnected() && (supportFunctionInfo = LocalDataManager.getSupportFunctionInfo()) != null && supportFunctionInfo.ex_main3_calorie_goal;
    }

    public final boolean supportActiveTimeTarget() {
        SupportFunctionInfo supportFunctionInfo;
        return BLEManager.isConnected() && (supportFunctionInfo = LocalDataManager.getSupportFunctionInfo()) != null && supportFunctionInfo.V3_set_mid_high_time_goal;
    }

    public final boolean supportWalkTarget() {
        SupportFunctionInfo supportFunctionInfo;
        if (BLEManager.isConnected() && (supportFunctionInfo = LocalDataManager.getSupportFunctionInfo()) != null) {
            return supportFunctionInfo.V3_set_walk_reminder_goal_time || supportFunctionInfo.support_set_fitness_guidance;
        }
        return false;
    }

    public final List<RecentSituationProgressView.DividerProperty> getActivityCalorie() {
        int iCaluteBMR = FitnessHelperKt.caluteBMR(getUserId());
        ArrayList arrayList = new ArrayList();
        if (MathKt.roundToInt(((double) iCaluteBMR) * 0.8d) < 50) {
            int color = Color.parseColor("#FFE451");
            float dimens = ResourceUtil.getDimens(R.dimen.sw_dp_3);
            float dimens2 = ResourceUtil.getDimens(R.dimen.sw_dp_1);
            String languageText = LanguageUtil.getLanguageText(R.string.fitness_detail_less);
            Intrinsics.checkExpressionValueIsNotNull(languageText, "LanguageUtil.getLanguage…ring.fitness_detail_less)");
            arrayList.add(new RecentSituationProgressView.DividerProperty(color, dimens, dimens2, languageText, 10.0f, 20.0f));
            int color2 = Color.parseColor("#FFAE6A");
            float dimens3 = ResourceUtil.getDimens(R.dimen.sw_dp_3);
            float dimens4 = ResourceUtil.getDimens(R.dimen.sw_dp_1);
            String languageText2 = LanguageUtil.getLanguageText(R.string.fitness_detail_medium);
            Intrinsics.checkExpressionValueIsNotNull(languageText2, "LanguageUtil.getLanguage…ng.fitness_detail_medium)");
            arrayList.add(new RecentSituationProgressView.DividerProperty(color2, dimens3, dimens4, languageText2, 20.0f, 30.0f));
            int color3 = Color.parseColor("#FF7550");
            float dimens5 = ResourceUtil.getDimens(R.dimen.sw_dp_3);
            float dimens6 = ResourceUtil.getDimens(R.dimen.sw_dp_1);
            String languageText3 = LanguageUtil.getLanguageText(R.string.fitness_detail_much);
            Intrinsics.checkExpressionValueIsNotNull(languageText3, "LanguageUtil.getLanguage…ring.fitness_detail_much)");
            arrayList.add(new RecentSituationProgressView.DividerProperty(color3, dimens5, dimens6, languageText3, 30.0f, 40.0f));
            int color4 = Color.parseColor("#FF3519");
            float dimens7 = ResourceUtil.getDimens(R.dimen.sw_dp_3);
            float dimens8 = ResourceUtil.getDimens(R.dimen.sw_dp_1);
            String languageText4 = LanguageUtil.getLanguageText(R.string.fitness_detail_more);
            Intrinsics.checkExpressionValueIsNotNull(languageText4, "LanguageUtil.getLanguage…ring.fitness_detail_more)");
            arrayList.add(new RecentSituationProgressView.DividerProperty(color4, dimens7, dimens8, languageText4, 40.0f, 50.0f));
        } else {
            int color5 = Color.parseColor("#FFE451");
            float dimens9 = ResourceUtil.getDimens(R.dimen.sw_dp_3);
            float dimens10 = ResourceUtil.getDimens(R.dimen.sw_dp_1);
            String languageText5 = LanguageUtil.getLanguageText(R.string.fitness_detail_less);
            Intrinsics.checkExpressionValueIsNotNull(languageText5, "LanguageUtil.getLanguage…ring.fitness_detail_less)");
            float f2 = iCaluteBMR;
            float f3 = f2 * 0.25f;
            arrayList.add(new RecentSituationProgressView.DividerProperty(color5, dimens9, dimens10, languageText5, ajustCalorieMin(MathKt.roundToInt(0.15f * f2)), f3));
            int color6 = Color.parseColor("#FFAE6A");
            float dimens11 = ResourceUtil.getDimens(R.dimen.sw_dp_3);
            float dimens12 = ResourceUtil.getDimens(R.dimen.sw_dp_1);
            String languageText6 = LanguageUtil.getLanguageText(R.string.fitness_detail_medium);
            Intrinsics.checkExpressionValueIsNotNull(languageText6, "LanguageUtil.getLanguage…ng.fitness_detail_medium)");
            float f4 = f2 * 0.35f;
            arrayList.add(new RecentSituationProgressView.DividerProperty(color6, dimens11, dimens12, languageText6, f3, f4));
            int color7 = Color.parseColor("#FF7550");
            float dimens13 = ResourceUtil.getDimens(R.dimen.sw_dp_3);
            float dimens14 = ResourceUtil.getDimens(R.dimen.sw_dp_1);
            String languageText7 = LanguageUtil.getLanguageText(R.string.fitness_detail_much);
            Intrinsics.checkExpressionValueIsNotNull(languageText7, "LanguageUtil.getLanguage…ring.fitness_detail_much)");
            float f5 = f2 * 0.55f;
            arrayList.add(new RecentSituationProgressView.DividerProperty(color7, dimens13, dimens14, languageText7, f4, f5));
            int color8 = Color.parseColor("#FF3519");
            float dimens15 = ResourceUtil.getDimens(R.dimen.sw_dp_3);
            float dimens16 = ResourceUtil.getDimens(R.dimen.sw_dp_1);
            String languageText8 = LanguageUtil.getLanguageText(R.string.fitness_detail_more);
            Intrinsics.checkExpressionValueIsNotNull(languageText8, "LanguageUtil.getLanguage…ring.fitness_detail_more)");
            arrayList.add(new RecentSituationProgressView.DividerProperty(color8, dimens15, dimens16, languageText8, f5, ajustCalorieMax(MathKt.roundToInt(f2 * 0.8f))));
        }
        return arrayList;
    }

    public final List<RecentSituationProgressView.DividerProperty> getActivityTime() {
        ArrayList arrayList = new ArrayList();
        int color = Color.parseColor("#D1F3A7");
        float dimens = ResourceUtil.getDimens(R.dimen.sw_dp_3);
        float dimens2 = ResourceUtil.getDimens(R.dimen.sw_dp_1);
        String languageText = LanguageUtil.getLanguageText(R.string.fitness_detail_less);
        Intrinsics.checkExpressionValueIsNotNull(languageText, "LanguageUtil.getLanguage…ring.fitness_detail_less)");
        arrayList.add(new RecentSituationProgressView.DividerProperty(color, dimens, dimens2, languageText, 5.0f, 10.0f));
        int color2 = Color.parseColor("#81ED74");
        float dimens3 = ResourceUtil.getDimens(R.dimen.sw_dp_3);
        float dimens4 = ResourceUtil.getDimens(R.dimen.sw_dp_1);
        String languageText2 = LanguageUtil.getLanguageText(R.string.fitness_detail_medium);
        Intrinsics.checkExpressionValueIsNotNull(languageText2, "LanguageUtil.getLanguage…ng.fitness_detail_medium)");
        arrayList.add(new RecentSituationProgressView.DividerProperty(color2, dimens3, dimens4, languageText2, 10.0f, 20.0f));
        int color3 = Color.parseColor("#30E146");
        float dimens5 = ResourceUtil.getDimens(R.dimen.sw_dp_3);
        float dimens6 = ResourceUtil.getDimens(R.dimen.sw_dp_1);
        String languageText3 = LanguageUtil.getLanguageText(R.string.fitness_detail_much);
        Intrinsics.checkExpressionValueIsNotNull(languageText3, "LanguageUtil.getLanguage…ring.fitness_detail_much)");
        arrayList.add(new RecentSituationProgressView.DividerProperty(color3, dimens5, dimens6, languageText3, 20.0f, 40.0f));
        int color4 = Color.parseColor("#00BE47");
        float dimens7 = ResourceUtil.getDimens(R.dimen.sw_dp_3);
        float dimens8 = ResourceUtil.getDimens(R.dimen.sw_dp_1);
        String languageText4 = LanguageUtil.getLanguageText(R.string.fitness_detail_more);
        Intrinsics.checkExpressionValueIsNotNull(languageText4, "LanguageUtil.getLanguage…ring.fitness_detail_more)");
        arrayList.add(new RecentSituationProgressView.DividerProperty(color4, dimens7, dimens8, languageText4, 40.0f, 60.0f));
        return arrayList;
    }

    public final List<RecentSituationProgressView.DividerProperty> getWalking() {
        ArrayList arrayList = new ArrayList();
        int color = Color.parseColor("#9AE4F4");
        float dimens = ResourceUtil.getDimens(R.dimen.sw_dp_3);
        float dimens2 = ResourceUtil.getDimens(R.dimen.sw_dp_1);
        String languageText = LanguageUtil.getLanguageText(R.string.fitness_detail_less);
        Intrinsics.checkExpressionValueIsNotNull(languageText, "LanguageUtil.getLanguage…ring.fitness_detail_less)");
        arrayList.add(new RecentSituationProgressView.DividerProperty(color, dimens, dimens2, languageText, 6.0f, 8.0f));
        int color2 = Color.parseColor("#67DAF4");
        float dimens3 = ResourceUtil.getDimens(R.dimen.sw_dp_3);
        float dimens4 = ResourceUtil.getDimens(R.dimen.sw_dp_1);
        String languageText2 = LanguageUtil.getLanguageText(R.string.fitness_detail_medium);
        Intrinsics.checkExpressionValueIsNotNull(languageText2, "LanguageUtil.getLanguage…ng.fitness_detail_medium)");
        arrayList.add(new RecentSituationProgressView.DividerProperty(color2, dimens3, dimens4, languageText2, 8.0f, 10.0f));
        int color3 = Color.parseColor("#3DB6F1");
        float dimens5 = ResourceUtil.getDimens(R.dimen.sw_dp_3);
        float dimens6 = ResourceUtil.getDimens(R.dimen.sw_dp_1);
        String languageText3 = LanguageUtil.getLanguageText(R.string.fitness_detail_much);
        Intrinsics.checkExpressionValueIsNotNull(languageText3, "LanguageUtil.getLanguage…ring.fitness_detail_much)");
        arrayList.add(new RecentSituationProgressView.DividerProperty(color3, dimens5, dimens6, languageText3, 10.0f, 12.0f));
        int color4 = Color.parseColor("#079BEC");
        float dimens7 = ResourceUtil.getDimens(R.dimen.sw_dp_3);
        float dimens8 = ResourceUtil.getDimens(R.dimen.sw_dp_1);
        String languageText4 = LanguageUtil.getLanguageText(R.string.fitness_detail_more);
        Intrinsics.checkExpressionValueIsNotNull(languageText4, "LanguageUtil.getLanguage…ring.fitness_detail_more)");
        arrayList.add(new RecentSituationProgressView.DividerProperty(color4, dimens7, dimens8, languageText4, 12.0f, 14.0f));
        return arrayList;
    }

    private final int ajustCalorieMin(int calorie) {
        return calorie % 50 != 0 ? (calorie / 50) * 50 : calorie;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getPastDateDesc(Calendar startCalendar, Calendar endCalendar) {
        if (startCalendar.get(1) == endCalendar.get(1)) {
            return DateUtil.format(startCalendar, DateUtil.DATE_FORMAT_YMD_2) + '-' + DateUtil.format(endCalendar, DateUtil.DATE_FORMAT_MD_2);
        }
        return DateUtil.format(startCalendar, DateUtil.DATE_FORMAT_YMD_2) + '-' + DateUtil.format(endCalendar, DateUtil.DATE_FORMAT_YMD_2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getPastDateDesc(String startDate, String endDate) {
        try {
            Calendar startCalendar = Calendar.getInstance(Locale.CHINA);
            Calendar endCalendar = Calendar.getInstance(Locale.CHINA);
            Intrinsics.checkExpressionValueIsNotNull(startCalendar, "startCalendar");
            startCalendar.setTime(DateUtil.string2Date(startDate, DateUtil.DATE_FORMAT_YMD));
            Intrinsics.checkExpressionValueIsNotNull(endCalendar, "endCalendar");
            endCalendar.setTime(DateUtil.string2Date(endDate, DateUtil.DATE_FORMAT_YMD));
            return getPastDateDesc(startCalendar, endCalendar);
        } catch (Exception unused) {
            return "";
        }
    }

    private final int ajustCalorieMax(int calorie) {
        return calorie % 50 != 0 ? ((calorie / 50) + 1) * 50 : calorie;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getRecentDayCount() {
        StageInfoEnum stageInfoEnum = this.mReallyStage == StageInfoEnum.NODATA ? StageInfoEnum.PRIMARY : this.mReallyStage;
        return (stageInfoEnum.getMRecentEndDayCount() - stageInfoEnum.getMRecentStartDayCount()) + 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getPastDayCount() {
        StageInfoEnum stageInfoEnum = this.mReallyStage == StageInfoEnum.NODATA ? StageInfoEnum.PRIMARY : this.mReallyStage;
        return (stageInfoEnum.getMPastEndDayCount() - stageInfoEnum.getMPastStartDayCount()) + 1;
    }

    /* JADX INFO: compiled from: RecentSituationDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0002\u0010\fJ\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0005HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\bHÆ\u0003J\u000f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u000b0\nHÆ\u0003JA\u0010$\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nHÆ\u0001J\u0013\u0010%\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010(\u001a\u00020\u0003HÖ\u0001J\t\u0010)\u001a\u00020\u0005HÖ\u0001R \u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0012\"\u0004\b\u001e\u0010\u0014¨\u0006*"}, d2 = {"Lcom/ido/life/module/home/recentsituation/RecentSituationDetailPresenter$RecommandActive;", "", "type", "", "activeName", "", "activeIcon", "metValue", "", "activeDesc", "", "Lcom/ido/life/module/home/recentsituation/RecentSituationDetailPresenter$RecommandActiveDesc;", "(ILjava/lang/String;IFLjava/util/List;)V", "getActiveDesc", "()Ljava/util/List;", "setActiveDesc", "(Ljava/util/List;)V", "getActiveIcon", "()I", "setActiveIcon", "(I)V", "getActiveName", "()Ljava/lang/String;", "setActiveName", "(Ljava/lang/String;)V", "getMetValue", "()F", "setMetValue", "(F)V", "getType", "setType", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final /* data */ class RecommandActive {
        private List<RecommandActiveDesc> activeDesc;
        private int activeIcon;
        private String activeName;
        private float metValue;
        private int type;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ RecommandActive copy$default(RecommandActive recommandActive, int i, String str, int i2, float f2, List list, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                i = recommandActive.type;
            }
            if ((i3 & 2) != 0) {
                str = recommandActive.activeName;
            }
            String str2 = str;
            if ((i3 & 4) != 0) {
                i2 = recommandActive.activeIcon;
            }
            int i4 = i2;
            if ((i3 & 8) != 0) {
                f2 = recommandActive.metValue;
            }
            float f3 = f2;
            if ((i3 & 16) != 0) {
                list = recommandActive.activeDesc;
            }
            return recommandActive.copy(i, str2, i4, f3, list);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getType() {
            return this.type;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getActiveName() {
            return this.activeName;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final int getActiveIcon() {
            return this.activeIcon;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final float getMetValue() {
            return this.metValue;
        }

        public final List<RecommandActiveDesc> component5() {
            return this.activeDesc;
        }

        public final RecommandActive copy(int type, String activeName, int activeIcon, float metValue, List<RecommandActiveDesc> activeDesc) {
            Intrinsics.checkParameterIsNotNull(activeName, "activeName");
            Intrinsics.checkParameterIsNotNull(activeDesc, "activeDesc");
            return new RecommandActive(type, activeName, activeIcon, metValue, activeDesc);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof RecommandActive)) {
                return false;
            }
            RecommandActive recommandActive = (RecommandActive) other;
            return this.type == recommandActive.type && Intrinsics.areEqual(this.activeName, recommandActive.activeName) && this.activeIcon == recommandActive.activeIcon && Float.compare(this.metValue, recommandActive.metValue) == 0 && Intrinsics.areEqual(this.activeDesc, recommandActive.activeDesc);
        }

        public int hashCode() {
            int iHashCode = Integer.valueOf(this.type).hashCode() * 31;
            String str = this.activeName;
            int iHashCode2 = (((((iHashCode + (str != null ? str.hashCode() : 0)) * 31) + Integer.valueOf(this.activeIcon).hashCode()) * 31) + Float.valueOf(this.metValue).hashCode()) * 31;
            List<RecommandActiveDesc> list = this.activeDesc;
            return iHashCode2 + (list != null ? list.hashCode() : 0);
        }

        public String toString() {
            return "RecommandActive(type=" + this.type + ", activeName=" + this.activeName + ", activeIcon=" + this.activeIcon + ", metValue=" + this.metValue + ", activeDesc=" + this.activeDesc + ")";
        }

        public RecommandActive(int i, String activeName, int i2, float f2, List<RecommandActiveDesc> activeDesc) {
            Intrinsics.checkParameterIsNotNull(activeName, "activeName");
            Intrinsics.checkParameterIsNotNull(activeDesc, "activeDesc");
            this.type = i;
            this.activeName = activeName;
            this.activeIcon = i2;
            this.metValue = f2;
            this.activeDesc = activeDesc;
        }

        public final int getType() {
            return this.type;
        }

        public final void setType(int i) {
            this.type = i;
        }

        public final String getActiveName() {
            return this.activeName;
        }

        public final void setActiveName(String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            this.activeName = str;
        }

        public final int getActiveIcon() {
            return this.activeIcon;
        }

        public final void setActiveIcon(int i) {
            this.activeIcon = i;
        }

        public final float getMetValue() {
            return this.metValue;
        }

        public final void setMetValue(float f2) {
            this.metValue = f2;
        }

        public final List<RecommandActiveDesc> getActiveDesc() {
            return this.activeDesc;
        }

        public final void setActiveDesc(List<RecommandActiveDesc> list) {
            Intrinsics.checkParameterIsNotNull(list, "<set-?>");
            this.activeDesc = list;
        }
    }

    /* JADX INFO: compiled from: RecentSituationDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0006HÆ\u0003J'\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0006HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\t\"\u0004\b\u0011\u0010\u000b¨\u0006\u001b"}, d2 = {"Lcom/ido/life/module/home/recentsituation/RecentSituationDetailPresenter$RecommandActiveDesc;", "", d.C, "", "kCal", "durationDesc", "", "(IILjava/lang/String;)V", "getDuration", "()I", "setDuration", "(I)V", "getDurationDesc", "()Ljava/lang/String;", "setDurationDesc", "(Ljava/lang/String;)V", "getKCal", "setKCal", "component1", "component2", "component3", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final /* data */ class RecommandActiveDesc {
        private int duration;
        private String durationDesc;
        private int kCal;

        public static /* synthetic */ RecommandActiveDesc copy$default(RecommandActiveDesc recommandActiveDesc, int i, int i2, String str, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                i = recommandActiveDesc.duration;
            }
            if ((i3 & 2) != 0) {
                i2 = recommandActiveDesc.kCal;
            }
            if ((i3 & 4) != 0) {
                str = recommandActiveDesc.durationDesc;
            }
            return recommandActiveDesc.copy(i, i2, str);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getDuration() {
            return this.duration;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getKCal() {
            return this.kCal;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getDurationDesc() {
            return this.durationDesc;
        }

        public final RecommandActiveDesc copy(int duration, int kCal, String durationDesc) {
            Intrinsics.checkParameterIsNotNull(durationDesc, "durationDesc");
            return new RecommandActiveDesc(duration, kCal, durationDesc);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof RecommandActiveDesc)) {
                return false;
            }
            RecommandActiveDesc recommandActiveDesc = (RecommandActiveDesc) other;
            return this.duration == recommandActiveDesc.duration && this.kCal == recommandActiveDesc.kCal && Intrinsics.areEqual(this.durationDesc, recommandActiveDesc.durationDesc);
        }

        public int hashCode() {
            int iHashCode = ((Integer.valueOf(this.duration).hashCode() * 31) + Integer.valueOf(this.kCal).hashCode()) * 31;
            String str = this.durationDesc;
            return iHashCode + (str != null ? str.hashCode() : 0);
        }

        public String toString() {
            return "RecommandActiveDesc(duration=" + this.duration + ", kCal=" + this.kCal + ", durationDesc=" + this.durationDesc + ")";
        }

        public RecommandActiveDesc(int i, int i2, String durationDesc) {
            Intrinsics.checkParameterIsNotNull(durationDesc, "durationDesc");
            this.duration = i;
            this.kCal = i2;
            this.durationDesc = durationDesc;
        }

        public final int getDuration() {
            return this.duration;
        }

        public final String getDurationDesc() {
            return this.durationDesc;
        }

        public final int getKCal() {
            return this.kCal;
        }

        public final void setDuration(int i) {
            this.duration = i;
        }

        public final void setDurationDesc(String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            this.durationDesc = str;
        }

        public final void setKCal(int i) {
            this.kCal = i;
        }
    }

    /* JADX INFO: compiled from: RecentSituationDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003¢\u0006\u0002\u0010\tJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J;\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010!\u001a\u00020\u0003HÖ\u0001J\t\u0010\"\u001a\u00020\u0006HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000b\"\u0004\b\u000f\u0010\rR\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u000b\"\u0004\b\u0015\u0010\rR\u001a\u0010\u0007\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0011\"\u0004\b\u0017\u0010\u0013¨\u0006#"}, d2 = {"Lcom/ido/life/module/home/recentsituation/RecentSituationDetailPresenter$RecommandActiceBean;", "", "type", "", "iconResId", AppMeasurementSdk.ConditionalUserProperty.NAME, "", "valueDesc", "kcal", "(IILjava/lang/String;Ljava/lang/String;I)V", "getIconResId", "()I", "setIconResId", "(I)V", "getKcal", "setKcal", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "getType", "setType", "getValueDesc", "setValueDesc", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final /* data */ class RecommandActiceBean {
        private int iconResId;
        private int kcal;
        private String name;
        private int type;
        private String valueDesc;

        public static /* synthetic */ RecommandActiceBean copy$default(RecommandActiceBean recommandActiceBean, int i, int i2, String str, String str2, int i3, int i4, Object obj) {
            if ((i4 & 1) != 0) {
                i = recommandActiceBean.type;
            }
            if ((i4 & 2) != 0) {
                i2 = recommandActiceBean.iconResId;
            }
            int i5 = i2;
            if ((i4 & 4) != 0) {
                str = recommandActiceBean.name;
            }
            String str3 = str;
            if ((i4 & 8) != 0) {
                str2 = recommandActiceBean.valueDesc;
            }
            String str4 = str2;
            if ((i4 & 16) != 0) {
                i3 = recommandActiceBean.kcal;
            }
            return recommandActiceBean.copy(i, i5, str3, str4, i3);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getType() {
            return this.type;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getIconResId() {
            return this.iconResId;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getValueDesc() {
            return this.valueDesc;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final int getKcal() {
            return this.kcal;
        }

        public final RecommandActiceBean copy(int type, int iconResId, String name, String valueDesc, int kcal) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            Intrinsics.checkParameterIsNotNull(valueDesc, "valueDesc");
            return new RecommandActiceBean(type, iconResId, name, valueDesc, kcal);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof RecommandActiceBean)) {
                return false;
            }
            RecommandActiceBean recommandActiceBean = (RecommandActiceBean) other;
            return this.type == recommandActiceBean.type && this.iconResId == recommandActiceBean.iconResId && Intrinsics.areEqual(this.name, recommandActiceBean.name) && Intrinsics.areEqual(this.valueDesc, recommandActiceBean.valueDesc) && this.kcal == recommandActiceBean.kcal;
        }

        public int hashCode() {
            int iHashCode = ((Integer.valueOf(this.type).hashCode() * 31) + Integer.valueOf(this.iconResId).hashCode()) * 31;
            String str = this.name;
            int iHashCode2 = (iHashCode + (str != null ? str.hashCode() : 0)) * 31;
            String str2 = this.valueDesc;
            return ((iHashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31) + Integer.valueOf(this.kcal).hashCode();
        }

        public String toString() {
            return "RecommandActiceBean(type=" + this.type + ", iconResId=" + this.iconResId + ", name=" + this.name + ", valueDesc=" + this.valueDesc + ", kcal=" + this.kcal + ")";
        }

        public RecommandActiceBean(int i, int i2, String name, String valueDesc, int i3) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            Intrinsics.checkParameterIsNotNull(valueDesc, "valueDesc");
            this.type = i;
            this.iconResId = i2;
            this.name = name;
            this.valueDesc = valueDesc;
            this.kcal = i3;
        }

        public final int getType() {
            return this.type;
        }

        public final void setType(int i) {
            this.type = i;
        }

        public final int getIconResId() {
            return this.iconResId;
        }

        public final void setIconResId(int i) {
            this.iconResId = i;
        }

        public final String getName() {
            return this.name;
        }

        public final void setName(String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            this.name = str;
        }

        public final String getValueDesc() {
            return this.valueDesc;
        }

        public final void setValueDesc(String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            this.valueDesc = str;
        }

        public /* synthetic */ RecommandActiceBean(int i, int i2, String str, String str2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
            this(i, i2, str, (i4 & 8) != 0 ? "" : str2, (i4 & 16) != 0 ? 0 : i3);
        }

        public final int getKcal() {
            return this.kcal;
        }

        public final void setKcal(int i) {
            this.kcal = i;
        }
    }

    @Override // com.ido.ble.callback.OtherProtocolCallBack.ICallBack
    public void onSuccess(OtherProtocolCallBack.SettingType type) {
        BLEManager.unregisterOtherProtocolCallBack(this);
        if (type == OtherProtocolCallBack.SettingType.CALORIE_DISTANCE_GOAL) {
            HomeHelperKt.printAndSave("目标设置成功", this.TAG);
            String today = FitnessHelperKt.getToday();
            UserTargetNew userTarget = GreenDaoUtil.queryUserTarget(getUserId(), today);
            if (userTarget == null) {
                UserTargetNew userTargetNewQueryUserLatestTarget = GreenDaoUtil.queryUserLatestTarget(getUserId());
                if (userTargetNewQueryUserLatestTarget == null) {
                    userTarget = RunTimeUtil.generateDefaultUserTargetNew(getUserId());
                } else {
                    userTarget = userTargetNewQueryUserLatestTarget.m28clone();
                }
                Intrinsics.checkExpressionValueIsNotNull(userTarget, "userTarget");
                userTarget.setCreateTime(System.currentTimeMillis());
                userTarget.setUserId(getUserId());
                userTarget.setId((Long) null);
                userTarget.setDate(today);
            }
            Integer num = this.mCalorieTarget;
            if (num != null) {
                if (num == null) {
                    Intrinsics.throwNpe();
                }
                userTarget.setCalories(num.intValue());
            }
            Integer num2 = this.mExerciseTimeTarget;
            if (num2 != null) {
                if (num2 == null) {
                    Intrinsics.throwNpe();
                }
                userTarget.setActivityTime(num2.intValue());
            }
            Integer num3 = this.mWalkTimeTarget;
            if (num3 != null) {
                if (num3 == null) {
                    Intrinsics.throwNpe();
                }
                userTarget.setWalk(num3.intValue());
            }
            Integer num4 = this.mWalkStep;
            if (num4 != null) {
                if (num4 == null) {
                    Intrinsics.throwNpe();
                }
                if (num4.intValue() > 0) {
                    Integer num5 = this.mWalkStep;
                    if (num5 == null) {
                        Intrinsics.throwNpe();
                    }
                    userTarget.setSportStep(num5.intValue());
                }
            }
            userTarget.setUpdateTime(System.currentTimeMillis());
            userTarget.setHasUpload(false);
            GreenDaoUtil.addUserTarget(userTarget);
            IRecentSituationDetailView view = getView();
            if (view != null) {
                view.dismissDialog();
            }
            IRecentSituationDetailView view2 = getView();
            if (view2 != null) {
                view2.onTargetSettingSuccess();
            }
        }
    }

    @Override // com.ido.ble.callback.OtherProtocolCallBack.ICallBack
    public void onFailed(OtherProtocolCallBack.SettingType type) {
        if (type == OtherProtocolCallBack.SettingType.CALORIE_DISTANCE_GOAL) {
            HomeHelperKt.printAndSave("目标设置失败", this.TAG);
            IRecentSituationDetailView view = getView();
            if (view != null) {
                view.dismissDialog();
            }
            IRecentSituationDetailView view2 = getView();
            if (view2 != null) {
                view2.onTargetSettingFailed();
            }
        }
    }
}