package com.ido.life.module.home.step;

import android.graphics.Color;
import android.text.TextUtils;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.ido.alexa.AlexaCustomSkillConstant;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.GsonUtil;
import com.ido.life.bean.BarChartPoint;
import com.ido.life.constants.Constants;
import com.ido.life.database.LocalHealthDataManager;
import com.ido.life.database.model.StepDayData;
import com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter;
import com.ido.life.util.DateUtil;
import com.ido.life.util.HealthDataUtil;
import com.ido.life.util.RunTimeUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
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
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: compiled from: StepDetailPresenter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0013\u0018\u0000 P28\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0004\u0012\u00020\u00040\u0001:\u0001PB\r\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010\u0006J\u0006\u00104\u001a\u00020\u0010J\b\u00105\u001a\u000206H\u0014J \u00107\u001a\n\u0012\u0004\u0012\u00020%\u0018\u00010\u00022\u000e\u00108\u001a\n\u0012\u0004\u0012\u00020%\u0018\u00010\u0002H\u0002J'\u00109\u001a\u0002062\u0006\u0010:\u001a\u00020;2\f\u00108\u001a\b\u0012\u0004\u0012\u00020%0\u0002H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010<J \u0010=\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00162\u000e\u00108\u001a\n\u0012\u0004\u0012\u00020>\u0018\u00010\u0002H\u0002J\u0016\u0010?\u001a\u0010\u0012\f\u0012\n @*\u0004\u0018\u00010\r0\r0\u0016H\u0014J\u0012\u0010A\u001a\u0002062\b\u0010B\u001a\u0004\u0018\u00010\rH\u0014J\u001c\u0010C\u001a\u0002062\b\u0010D\u001a\u0004\u0018\u00010\r2\b\u0010E\u001a\u0004\u0018\u00010\rH\u0014J\u001c\u0010F\u001a\u0002062\b\u0010D\u001a\u0004\u0018\u00010\r2\b\u0010E\u001a\u0004\u0018\u00010\rH\u0014J\u0012\u0010G\u001a\u00020;2\b\u0010H\u001a\u0004\u0018\u00010\rH\u0016J\b\u0010I\u001a\u000206H\u0014J\b\u0010J\u001a\u000206H\u0002J\u0019\u0010K\u001a\u0002062\u0006\u0010:\u001a\u00020;H\u0094@ø\u0001\u0000¢\u0006\u0002\u0010LJ\u0019\u0010M\u001a\u0002062\u0006\u0010:\u001a\u00020;H\u0094@ø\u0001\u0000¢\u0006\u0002\u0010LJ\u0019\u0010N\u001a\u0002062\u0006\u0010:\u001a\u00020;H\u0094@ø\u0001\u0000¢\u0006\u0002\u0010LJ\u0019\u0010O\u001a\u0002062\u0006\u0010:\u001a\u00020;H\u0094@ø\u0001\u0000¢\u0006\u0002\u0010LR\u001b\u0010\u0007\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u00028F¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\tR\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u00028F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\tR\u0011\u0010\u000f\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0012R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00030\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\u001a\u001a\u00020\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u000e\u0010\u001f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\"\u001a\u00020\u00102\u0006\u0010!\u001a\u00020\u0010@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0012R\u0014\u0010$\u001a\b\u0012\u0004\u0012\u00020%0\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010&\u001a\u00020\u00102\u0006\u0010!\u001a\u00020\u0010@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u0012R\u000e\u0010(\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010*\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u00028F¢\u0006\u0006\u001a\u0004\b+\u0010\tR\u0019\u0010,\u001a\n\u0012\u0004\u0012\u00020%\u0018\u00010\u00028F¢\u0006\u0006\u001a\u0004\b-\u0010\tR\u0011\u0010.\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b/\u0010\u0012R\u001b\u00100\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u00028F¢\u0006\u0006\u001a\u0004\b1\u0010\tR\u001b\u00102\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u00028F¢\u0006\u0006\u001a\u0004\b3\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006Q"}, d2 = {"Lcom/ido/life/module/home/step/StepDetailPresenter;", "Lcom/ido/life/module/home/chartdetail/vertical/BaseDetailPresenter;", "", "Lcom/ido/life/bean/BarChartPoint;", "Lcom/ido/life/module/home/step/IStepDetailView;", "iView", "(Lcom/ido/life/module/home/step/IStepDetailView;)V", "dayChartList", "getDayChartList", "()Ljava/util/List;", "defaultList", "getDefaultList", "defaultYLabelList", "", "getDefaultYLabelList", "defaultYMaxValue", "", "getDefaultYMaxValue", "()I", "defaultYMinValue", "getDefaultYMinValue", "mChartList", "", "mDefaultBarColor", "mDefaultBarRadius", "", "mDefaultHeight", "getMDefaultHeight", "()F", "setMDefaultHeight", "(F)V", "mMaxStep", "mNormalBarColor", "<set-?>", "mSportAvgStep", "getMSportAvgStep", "mSportList", "Lcom/ido/life/database/model/StepDayData;", "mSportTotalStep", "getMSportTotalStep", "mTargetBarColor", "mTargetStep", "monthChartList", "getMonthChartList", "sportList", "getSportList", "targetStep", "getTargetStep", "weekChartList", "getWeekChartList", "yearChartList", "getYearChartList", "caluteYMaxStep", "clearData", "", "combineYearList", "list", "convertDataListToUi", "showChartAnimator", "", "(ZLjava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "convertFrom", "", "getDataDownloadType", "kotlin.jvm.PlatformType", "getDetailByDay", "startDate", "getDetailByMonth", "start", "end", "getDetailByYear", "hasData", "date", "onTypeChanged", "queryTargetStep", "readLocalDayData", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readLocalMonthData", "readLocalWeekData", "readLocalYearData", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
public final class StepDetailPresenter extends BaseDetailPresenter<List<? extends BarChartPoint>, List<? extends BarChartPoint>, List<? extends BarChartPoint>, List<? extends BarChartPoint>, IStepDetailView> {
    private static final String TAG = "StepDetailPresenter";
    private List<BarChartPoint> mChartList;
    private final int mDefaultBarColor;
    private final float mDefaultBarRadius;
    private float mDefaultHeight;
    private int mMaxStep;
    private final int mNormalBarColor;
    private int mSportAvgStep;
    private final List<StepDayData> mSportList;
    private int mSportTotalStep;
    private final int mTargetBarColor;
    private int mTargetStep;

    /* JADX INFO: renamed from: com.ido.life.module.home.step.StepDetailPresenter$convertDataListToUi$1, reason: invalid class name */
    /* JADX INFO: compiled from: StepDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0082@"}, d2 = {"convertDataListToUi", "", "showChartAnimator", "", "list", "", "Lcom/ido/life/database/model/StepDayData;", "continuation", "Lkotlin/coroutines/Continuation;", ""}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.step.StepDetailPresenter", f = "StepDetailPresenter.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, l = {378, 413, 447, 453}, m = "convertDataListToUi", n = {"this", "showChartAnimator", "list", "startCalendar", "map", "size", "item", Constants.AppPackage.CALENDAR, "numStep", "dayAvgnumStep", "count", "index", "weekStart", "dayOfWeek", "yMax", "yDefaultHeight", "i", "this", "showChartAnimator", "list", "startCalendar", "map", "size", "item", Constants.AppPackage.CALENDAR, "numStep", "dayAvgnumStep", "count", "index", "weekStart", "dayOfWeek", "yMax", "yDefaultHeight", "i", "this", "showChartAnimator", "list", "startCalendar", "map", "size", "item", Constants.AppPackage.CALENDAR, "numStep", "dayAvgnumStep", "count", "index", "weekStart", "dayOfWeek", "yMax", "yDefaultHeight", "i", "this", "showChartAnimator", "list", "startCalendar", "map", "size", "item", Constants.AppPackage.CALENDAR, "numStep", "dayAvgnumStep", "count", "index", "weekStart", "dayOfWeek"}, s = {"L$0", "Z$0", "L$1", "L$2", "L$3", "I$0", "L$4", "L$5", "I$1", "I$2", "I$3", "I$4", "I$5", "I$6", "I$7", "F$0", "I$8", "L$0", "Z$0", "L$1", "L$2", "L$3", "I$0", "L$4", "L$5", "I$1", "I$2", "I$3", "I$4", "I$5", "I$6", "I$7", "F$0", "I$8", "L$0", "Z$0", "L$1", "L$2", "L$3", "I$0", "L$4", "L$5", "I$1", "I$2", "I$3", "I$4", "I$5", "I$6", "I$7", "F$0", "I$8", "L$0", "Z$0", "L$1", "L$2", "L$3", "I$0", "L$4", "L$5", "I$1", "I$2", "I$3", "I$4", "I$5", "I$6"})
    static final class AnonymousClass1 extends ContinuationImpl {
        float F$0;
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        int I$4;
        int I$5;
        int I$6;
        int I$7;
        int I$8;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
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
            return StepDetailPresenter.this.convertDataListToUi(false, null, this);
        }
    }

    public final int getDefaultYMinValue() {
        return 0;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    protected void getDetailByDay(String startDate) {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StepDetailPresenter(IStepDetailView iView) {
        super(iView);
        Intrinsics.checkParameterIsNotNull(iView, "iView");
        this.mSportList = new ArrayList();
        this.mChartList = new ArrayList();
        this.mDefaultBarColor = Color.parseColor("#66979797");
        this.mTargetBarColor = Color.parseColor("#FF4A00");
        this.mNormalBarColor = Color.parseColor("#80FF4A00");
        this.mDefaultBarRadius = 0.02f;
    }

    public final int getMSportTotalStep() {
        return this.mSportTotalStep;
    }

    public final int getMSportAvgStep() {
        return this.mSportAvgStep;
    }

    public final float getMDefaultHeight() {
        return this.mDefaultHeight;
    }

    public final void setMDefaultHeight(float f2) {
        this.mDefaultHeight = f2;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    protected void getDetailByMonth(String start, String end) {
        super.getDetailByMonth(start, end);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    protected void getDetailByYear(String start, String end) {
        super.getDetailByYear(start, end);
    }

    private final List<BarChartPoint> convertFrom(List<int[]> list) {
        if (list == null || list.size() != 24) {
            return null;
        }
        ensureActive();
        ArrayList arrayList = new ArrayList();
        this.mMaxStep = 0;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            ensureActive();
            int[] iArr = list.get(i);
            if (iArr[1] > 0) {
                int i2 = iArr[1];
                arrayList.add(new BarChartPoint(iArr[0], iArr[0] + 1, i2));
                this.mMaxStep = Math.max(this.mMaxStep, i2);
            } else {
                arrayList.add(new BarChartPoint(iArr[0], iArr[0] + 1, 0.0f));
            }
        }
        int iCaluteYMaxStep = caluteYMaxStep();
        float f2 = iCaluteYMaxStep * this.mDefaultBarRadius;
        this.mDefaultHeight = f2;
        int size2 = arrayList.size();
        for (int i3 = 0; i3 < size2; i3++) {
            ensureActive();
            BarChartPoint barChartPoint = (BarChartPoint) arrayList.get(i3);
            String tag = getTAG();
            StringBuilder sb = new StringBuilder();
            sb.append("convertFrom: ");
            RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
            sb.append(runTimeUtil.getStepTarget());
            sb.append(AppInfo.DELIM);
            sb.append(iCaluteYMaxStep);
            CommonLogUtil.d(tag, sb.toString());
            if (barChartPoint == null) {
                Intrinsics.throwNpe();
            }
            if (barChartPoint.y == 0.0f) {
                barChartPoint.y = f2;
                barChartPoint.setBarColor(this.mDefaultBarColor);
                barChartPoint.setActualValue(0.0f);
            } else if (barChartPoint.y < f2) {
                barChartPoint.y = 1 + f2;
            }
        }
        CommonLogUtil.d(getTAG(), "convertFrom: + mMaxStep" + this.mMaxStep);
        return arrayList;
    }

    private final List<StepDayData> combineYearList(List<? extends StepDayData> list) {
        List<? extends StepDayData> list2 = list;
        if (list2 == null || list2.isEmpty()) {
            return null;
        }
        ensureActive();
        ArrayList arrayList = new ArrayList();
        this.mSportTotalStep = 0;
        this.mMaxStep = 0;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int size = list.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            ensureActive();
            StepDayData stepDayData = list.get(i2);
            if (stepDayData != null) {
                String date = stepDayData.getDate();
                if (!(date == null || date.length() == 0) && stepDayData.getNumSteps() > 0) {
                    String date2 = stepDayData.getDate();
                    Intrinsics.checkExpressionValueIsNotNull(date2, "stepItem.date");
                    int length = stepDayData.getDate().length() - 3;
                    if (date2 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                    }
                    String strSubstring = date2.substring(0, length);
                    Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    StepDayData stepDayData2 = (StepDayData) linkedHashMap.get(strSubstring);
                    if (stepDayData2 != null) {
                        stepDayData2.setDayAvgStep(stepDayData2.getDayAvgStep() + 1);
                        stepDayData2.setNumSteps(stepDayData2.getNumSteps() + stepDayData.getNumSteps());
                    } else {
                        StepDayData stepDayDataM26clone = stepDayData.m26clone();
                        stepDayDataM26clone.setDayAvgStep(1);
                        linkedHashMap.put(strSubstring, stepDayDataM26clone);
                    }
                    this.mSportTotalStep += stepDayData.getNumSteps();
                    i++;
                }
            }
        }
        if (!linkedHashMap.isEmpty()) {
            for (StepDayData stepDayData3 : linkedHashMap.values()) {
                ensureActive();
                stepDayData3.setDayAvgStep(stepDayData3.getNumSteps() / stepDayData3.getDayAvgStep());
                arrayList.add(stepDayData3);
                this.mMaxStep = Math.max(this.mMaxStep, stepDayData3.getDayAvgStep());
            }
        }
        if (i > 0) {
            this.mSportAvgStep = this.mSportTotalStep / i;
        }
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:138:0x03a4 A[Catch: Exception -> 0x03c2, TRY_LEAVE, TryCatch #14 {Exception -> 0x03c2, blocks: (B:136:0x039d, B:138:0x03a4), top: B:279:0x039d }] */
    /* JADX WARN: Removed duplicated region for block: B:144:0x03b8  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final /* synthetic */ java.lang.Object convertDataListToUi(boolean r36, java.util.List<? extends com.ido.life.database.model.StepDayData> r37, kotlin.coroutines.Continuation<? super kotlin.Unit> r38) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 1862
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.home.step.StepDetailPresenter.convertDataListToUi(boolean, java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.step.StepDetailPresenter$convertDataListToUi$2, reason: invalid class name */
    /* JADX INFO: compiled from: StepDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.step.StepDetailPresenter$convertDataListToUi$2", f = "StepDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ boolean $showChartAnimator;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(boolean z, Continuation continuation) {
            super(2, continuation);
            this.$showChartAnimator = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            AnonymousClass2 anonymousClass2 = StepDetailPresenter.this.new AnonymousClass2(this.$showChartAnimator, completion);
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
            StepDetailPresenter.this.getMView().onLoadSuccessByWeek(this.$showChartAnimator, StepDetailPresenter.this.mChartList);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.step.StepDetailPresenter$convertDataListToUi$3, reason: invalid class name */
    /* JADX INFO: compiled from: StepDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.step.StepDetailPresenter$convertDataListToUi$3", f = "StepDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ boolean $showChartAnimator;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(boolean z, Continuation continuation) {
            super(2, continuation);
            this.$showChartAnimator = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            AnonymousClass3 anonymousClass3 = StepDetailPresenter.this.new AnonymousClass3(this.$showChartAnimator, completion);
            anonymousClass3.p$ = (CoroutineScope) obj;
            return anonymousClass3;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            StepDetailPresenter.this.getMView().onLoadSuccessByMonth(this.$showChartAnimator, StepDetailPresenter.this.mChartList);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.step.StepDetailPresenter$convertDataListToUi$4, reason: invalid class name */
    /* JADX INFO: compiled from: StepDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.step.StepDetailPresenter$convertDataListToUi$4", f = "StepDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass4 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ boolean $showChartAnimator;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass4(boolean z, Continuation continuation) {
            super(2, continuation);
            this.$showChartAnimator = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            AnonymousClass4 anonymousClass4 = StepDetailPresenter.this.new AnonymousClass4(this.$showChartAnimator, completion);
            anonymousClass4.p$ = (CoroutineScope) obj;
            return anonymousClass4;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass4) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            StepDetailPresenter.this.getMView().onLoadSuccessByYear(this.$showChartAnimator, StepDetailPresenter.this.mChartList);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.step.StepDetailPresenter$convertDataListToUi$5, reason: invalid class name */
    /* JADX INFO: compiled from: StepDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.step.StepDetailPresenter$convertDataListToUi$5", f = "StepDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass5 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        AnonymousClass5(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            AnonymousClass5 anonymousClass5 = StepDetailPresenter.this.new AnonymousClass5(completion);
            anonymousClass5.p$ = (CoroutineScope) obj;
            return anonymousClass5;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass5) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            StepDetailPresenter.this.getMView().onDetailLoadFailed();
            return Unit.INSTANCE;
        }
    }

    public final List<String> getDefaultYLabelList() {
        ArrayList arrayList = new ArrayList();
        int i = this.mMaxStep;
        if (i < 4) {
            arrayList.add(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
            arrayList.add("1");
            arrayList.add("2");
            arrayList.add(Constants.DIALDEFNED_VERSION_CONNECT);
            arrayList.add(AlexaCustomSkillConstant.EVENT_START_SPORT);
        } else if (i < 10) {
            arrayList.add("2");
            arrayList.add(AlexaCustomSkillConstant.EVENT_START_SPORT);
            arrayList.add("6");
            arrayList.add(AlexaCustomSkillConstant.EVENT_BRIGHTNESS);
            arrayList.add("10");
        } else {
            int i2 = 0;
            if (10 <= i && 98 >= i) {
                this.mMaxStep = ((i / 10) + 1) * 10;
                int i3 = this.mMaxStep / 4;
                while (i2 < 5) {
                    arrayList.add(String.valueOf(i3 * i2));
                    i2++;
                }
            } else {
                int i4 = this.mMaxStep;
                if (100 <= i4 && 998 >= i4) {
                    this.mMaxStep = ((i4 / 100) + 1) * 100;
                    int i5 = this.mMaxStep / 4;
                    while (i2 < 5) {
                        arrayList.add(String.valueOf(i5 * i2));
                        i2++;
                    }
                } else {
                    this.mMaxStep = ((this.mMaxStep / 1000) + 1) * 1000;
                    int i6 = this.mMaxStep / 4;
                    while (i2 < 5) {
                        arrayList.add(String.valueOf(i6 * i2));
                        i2++;
                    }
                }
            }
        }
        return arrayList;
    }

    public final int caluteYMaxStep() {
        int i = this.mMaxStep;
        if (i > i) {
            i++;
        }
        if (i < 4) {
            return 4;
        }
        if (i < 10) {
            return 10;
        }
        if (10 <= i && 98 >= i) {
            return ((i / 10) + 1) * 10;
        }
        return (100 <= i && 998 >= i) ? ((i / 100) + 1) * 100 : ((i / 1000) + 1) * 1000;
    }

    public final int getTargetStep() {
        if (this.mTargetStep <= 0) {
            queryTargetStep();
        }
        caluteYMaxStep();
        return this.mTargetStep;
    }

    private final void queryTargetStep() {
        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
        this.mTargetStep = runTimeUtil.getStepTarget();
        int i = this.mMaxStep;
        int i2 = this.mTargetStep;
        if (i <= i2) {
            this.mMaxStep = (int) (i2 * 1.1f);
        }
    }

    public final int getDefaultYMaxValue() {
        return caluteYMaxStep();
    }

    public final List<StepDayData> getSportList() {
        return this.mSportList;
    }

    public final List<BarChartPoint> getDefaultList() {
        int actualMaximum;
        int mDateType = getMDateType();
        if (mDateType == 1) {
            actualMaximum = 25;
        } else if (mDateType == 2) {
            actualMaximum = 8;
        } else if (mDateType != 3) {
            actualMaximum = mDateType != 4 ? 0 : 13;
        } else if (TextUtils.isEmpty(getMStartDate())) {
            actualMaximum = 31;
        } else {
            Calendar calendar = Calendar.getInstance(Locale.getDefault());
            Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
            calendar.setTime(DateUtil.string2Date(getMStartDate(), DateUtil.DATE_FORMAT_YMD));
            actualMaximum = calendar.getActualMaximum(5) + 1;
        }
        ArrayList arrayList = new ArrayList();
        float fCaluteYMaxStep = caluteYMaxStep() * this.mDefaultBarRadius;
        this.mDefaultHeight = fCaluteYMaxStep;
        for (int i = 1; i < actualMaximum; i++) {
            arrayList.add(new BarChartPoint(i - 1, i, fCaluteYMaxStep, 0.0f, this.mDefaultBarColor));
        }
        return arrayList;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    protected void onTypeChanged() {
        super.onTypeChanged();
        this.mMaxStep = 0;
        this.mSportAvgStep = this.mMaxStep;
        this.mSportTotalStep = this.mSportAvgStep;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    protected void clearData() {
        this.mChartList.clear();
        this.mSportList.clear();
        this.mTargetStep = 0;
    }

    public final List<BarChartPoint> getDayChartList() {
        return this.mChartList;
    }

    public final List<BarChartPoint> getWeekChartList() {
        return this.mChartList;
    }

    public final List<BarChartPoint> getMonthChartList() {
        return this.mChartList;
    }

    public final List<BarChartPoint> getYearChartList() {
        return this.mChartList;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    protected List<String> getDataDownloadType() {
        return CollectionsKt.mutableListOf(StepDayData.class.getSimpleName());
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    public boolean hasData(String date) {
        if (TextUtils.isEmpty(date)) {
            return false;
        }
        return LocalHealthDataManager.getInstance().hasStepData(date);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    protected Object readLocalDayData(boolean z, Continuation<? super Unit> continuation) {
        LocalHealthDataManager localHealthDataManager = LocalHealthDataManager.getInstance();
        String mStartDate = getMStartDate();
        if (mStartDate == null) {
            Intrinsics.throwNpe();
        }
        StepDayData stepDailyDataByDate = localHealthDataManager.getStepDailyDataByDate(mStartDate);
        if (stepDailyDataByDate != null && !TextUtils.isEmpty(stepDailyDataByDate.getItems())) {
            int[] iArr = (int[]) GsonUtil.fromJson(stepDailyDataByDate.getItems(), int[].class);
            ArrayList arrayList = new ArrayList();
            if (iArr != null) {
                for (int i : iArr) {
                    ensureActive();
                    arrayList.add(Boxing.boxInt(i));
                }
            }
            List<BarChartPoint> listConvertFrom = convertFrom(HealthDataUtil.convertCalorieToHourItem(arrayList, 15));
            this.mSportTotalStep = stepDailyDataByDate.getNumSteps();
            this.mSportAvgStep = 0;
            List<BarChartPoint> list = listConvertFrom;
            if (!(list == null || list.isEmpty())) {
                List<BarChartPoint> list2 = this.mChartList;
                if (listConvertFrom == null) {
                    Intrinsics.throwNpe();
                }
                list2.addAll(list);
            }
            Object objWithContext = BuildersKt.withContext(Dispatchers.getMain(), new C04072(z, null), continuation);
            if (objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                return objWithContext;
            }
        } else {
            Object objWithContext2 = BuildersKt.withContext(Dispatchers.getMain(), new C04083(null), continuation);
            if (objWithContext2 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                return objWithContext2;
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.step.StepDetailPresenter$readLocalDayData$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: StepDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.step.StepDetailPresenter$readLocalDayData$2", f = "StepDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C04072 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ boolean $showChartAnimator;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C04072(boolean z, Continuation continuation) {
            super(2, continuation);
            this.$showChartAnimator = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C04072 c04072 = StepDetailPresenter.this.new C04072(this.$showChartAnimator, completion);
            c04072.p$ = (CoroutineScope) obj;
            return c04072;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C04072) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            if (!StepDetailPresenter.this.mChartList.isEmpty()) {
                StepDetailPresenter.this.getMView().onLoadSuccessByDay(this.$showChartAnimator, StepDetailPresenter.this.mChartList);
            } else {
                StepDetailPresenter.this.getMView().onDetailLoadFailed();
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.step.StepDetailPresenter$readLocalDayData$3, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: StepDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.step.StepDetailPresenter$readLocalDayData$3", f = "StepDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C04083 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        C04083(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C04083 c04083 = StepDetailPresenter.this.new C04083(completion);
            c04083.p$ = (CoroutineScope) obj;
            return c04083;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C04083) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            StepDetailPresenter.this.getMView().onDetailLoadFailed();
            return Unit.INSTANCE;
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    protected Object readLocalWeekData(boolean z, Continuation<? super Unit> continuation) throws Throwable {
        List<StepDayData> stepDailyDataList = LocalHealthDataManager.getInstance().getStepDailyDataList(getMStartDate(), getMEndDate());
        if (stepDailyDataList != null && stepDailyDataList.size() > 0) {
            Object objConvertDataListToUi = convertDataListToUi(z, stepDailyDataList, continuation);
            if (objConvertDataListToUi == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                return objConvertDataListToUi;
            }
        } else {
            Object objWithContext = BuildersKt.withContext(Dispatchers.getMain(), new C04102(null), continuation);
            if (objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                return objWithContext;
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.step.StepDetailPresenter$readLocalWeekData$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: StepDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.step.StepDetailPresenter$readLocalWeekData$2", f = "StepDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C04102 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        C04102(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C04102 c04102 = StepDetailPresenter.this.new C04102(completion);
            c04102.p$ = (CoroutineScope) obj;
            return c04102;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C04102) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            StepDetailPresenter.this.getMView().onDetailLoadFailed();
            return Unit.INSTANCE;
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    protected Object readLocalMonthData(boolean z, Continuation<? super Unit> continuation) throws Throwable {
        String mStartDate = getMStartDate();
        if (mStartDate == null) {
            Intrinsics.throwNpe();
        }
        List listSplit$default = StringsKt.split$default((CharSequence) mStartDate, new String[]{"-"}, false, 0, 6, (Object) null);
        List<StepDayData> stepMonthData = LocalHealthDataManager.getInstance().getStepMonthData(getUserId(), Integer.parseInt((String) listSplit$default.get(0)), Integer.parseInt((String) listSplit$default.get(1)));
        if (stepMonthData != null && stepMonthData.size() > 0) {
            Object objConvertDataListToUi = convertDataListToUi(z, stepMonthData, continuation);
            if (objConvertDataListToUi == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                return objConvertDataListToUi;
            }
        } else {
            Object objWithContext = BuildersKt.withContext(Dispatchers.getMain(), new C04092(null), continuation);
            if (objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                return objWithContext;
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.step.StepDetailPresenter$readLocalMonthData$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: StepDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.step.StepDetailPresenter$readLocalMonthData$2", f = "StepDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C04092 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        C04092(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C04092 c04092 = StepDetailPresenter.this.new C04092(completion);
            c04092.p$ = (CoroutineScope) obj;
            return c04092;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C04092) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            StepDetailPresenter.this.getMView().onDetailLoadFailed();
            return Unit.INSTANCE;
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    protected Object readLocalYearData(boolean z, Continuation<? super Unit> continuation) throws Throwable {
        LocalHealthDataManager localHealthDataManager = LocalHealthDataManager.getInstance();
        long userId = getUserId();
        String mStartDate = getMStartDate();
        if (mStartDate == null) {
            Intrinsics.throwNpe();
        }
        if (mStartDate == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String strSubstring = mStartDate.substring(0, 4);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        List<StepDayData> stepYearData = localHealthDataManager.getStepYearData(userId, Integer.parseInt(strSubstring));
        if (stepYearData != null && stepYearData.size() > 0) {
            List<StepDayData> listCombineYearList = combineYearList(stepYearData);
            List<StepDayData> list = listCombineYearList;
            if (list == null || list.isEmpty()) {
                Object objWithContext = BuildersKt.withContext(Dispatchers.getMain(), new C04112(null), continuation);
                if (objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    return objWithContext;
                }
            } else {
                Object objConvertDataListToUi = convertDataListToUi(z, listCombineYearList, continuation);
                if (objConvertDataListToUi == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    return objConvertDataListToUi;
                }
            }
        } else {
            Object objWithContext2 = BuildersKt.withContext(Dispatchers.getMain(), new C04123(null), continuation);
            if (objWithContext2 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                return objWithContext2;
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.step.StepDetailPresenter$readLocalYearData$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: StepDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.step.StepDetailPresenter$readLocalYearData$2", f = "StepDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C04112 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        C04112(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C04112 c04112 = StepDetailPresenter.this.new C04112(completion);
            c04112.p$ = (CoroutineScope) obj;
            return c04112;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C04112) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            StepDetailPresenter.this.getMView().onDetailLoadFailed();
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.step.StepDetailPresenter$readLocalYearData$3, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: StepDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.step.StepDetailPresenter$readLocalYearData$3", f = "StepDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C04123 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        C04123(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C04123 c04123 = StepDetailPresenter.this.new C04123(completion);
            c04123.p$ = (CoroutineScope) obj;
            return c04123;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C04123) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            StepDetailPresenter.this.getMView().onDetailLoadFailed();
            return Unit.INSTANCE;
        }
    }
}