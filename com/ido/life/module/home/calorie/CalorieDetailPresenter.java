package com.ido.life.module.home.calorie;

import android.graphics.Color;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.ido.alexa.AlexaCustomSkillConstant;
import com.ido.common.log.CommonLogUtil;
import com.ido.life.bean.BarChartPoint;
import com.ido.life.constants.Constants;
import com.ido.life.database.LocalHealthDataManager;
import com.ido.life.database.model.CalorieDayData;
import com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter;
import com.ido.life.util.DateUtil;
import com.veryfit.multi.nativeprotocol.b;
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
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: compiled from: CalorieDetailPresenter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0018\u0018\u0000 O28\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0004\u0012\u00020\u00040\u0001:\u0001OB\r\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010\u0006J\u0006\u0010+\u001a\u00020\u000eJ\b\u0010,\u001a\u00020-H\u0014J\u001e\u0010.\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00022\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\b0\u0002H\u0002J'\u00100\u001a\u00020-2\u0006\u00101\u001a\u0002022\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\b0\u0002H\u0082@ø\u0001\u0000¢\u0006\u0002\u00103J\u001e\u00104\u001a\b\u0012\u0004\u0012\u00020\u00030\u001b2\u000e\u0010/\u001a\n\u0012\u0004\u0012\u000205\u0018\u00010\u0002H\u0002J\f\u00106\u001a\b\u0012\u0004\u0012\u00020\u00030\u001bJ\f\u00107\u001a\b\u0012\u0004\u0012\u0002080\u0002J\u0016\u00109\u001a\u0010\u0012\f\u0012\n :*\u0004\u0018\u000108080\u001bH\u0014J\u0012\u0010;\u001a\u00020-2\b\u0010<\u001a\u0004\u0018\u000108H\u0014J\u001c\u0010=\u001a\u00020-2\b\u0010>\u001a\u0004\u0018\u0001082\b\u0010?\u001a\u0004\u0018\u000108H\u0014J\u001c\u0010@\u001a\u00020-2\b\u0010>\u001a\u0004\u0018\u0001082\b\u0010?\u001a\u0004\u0018\u000108H\u0014J\u001c\u0010A\u001a\u00020-2\b\u0010>\u001a\u0004\u0018\u0001082\b\u0010?\u001a\u0004\u0018\u000108H\u0014J\u0012\u0010B\u001a\u0002022\b\u0010C\u001a\u0004\u0018\u000108H\u0016J\f\u0010D\u001a\b\u0012\u0004\u0012\u00020\u00030\u001bJ\b\u0010E\u001a\u00020-H\u0014J\u0019\u0010F\u001a\u00020-2\u0006\u00101\u001a\u000202H\u0094@ø\u0001\u0000¢\u0006\u0002\u0010GJ\u0019\u0010H\u001a\u00020-2\u0006\u00101\u001a\u000202H\u0094@ø\u0001\u0000¢\u0006\u0002\u0010GJ\u0019\u0010I\u001a\u00020-2\u0006\u00101\u001a\u000202H\u0094@ø\u0001\u0000¢\u0006\u0002\u0010GJ\u0019\u0010J\u001a\u00020-2\u0006\u00101\u001a\u000202H\u0094@ø\u0001\u0000¢\u0006\u0002\u0010GJ\u000e\u0010K\u001a\u00020-2\u0006\u0010L\u001a\u00020\u000eJ\f\u0010M\u001a\b\u0012\u0004\u0012\u00020\u00030\u001bJ\f\u0010N\u001a\b\u0012\u0004\u0012\u00020\u00030\u001bR\u0019\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00028F¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028F¢\u0006\u0006\u001a\u0004\b\f\u0010\nR\u0011\u0010\r\u001a\u00020\u000e8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\u000e8F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0010R\u001e\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u000e@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0010R\u001e\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u000e@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0010R\u001e\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u000e@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0010R\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\b0\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00030\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010!\u001a\u00020 X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u000e\u0010&\u001a\u00020'X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010)\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u000e@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u0010\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006P"}, d2 = {"Lcom/ido/life/module/home/calorie/CalorieDetailPresenter;", "Lcom/ido/life/module/home/chartdetail/vertical/BaseDetailPresenter;", "", "Lcom/ido/life/bean/BarChartPoint;", "Lcom/ido/life/module/home/calorie/ICalorieDetailView;", "iView", "(Lcom/ido/life/module/home/calorie/ICalorieDetailView;)V", "calorieList", "Lcom/ido/life/database/model/CalorieDayData;", "getCalorieList", "()Ljava/util/List;", "defaultList", "getDefaultList", "defaultYMaxValue", "", "getDefaultYMaxValue", "()I", "defaultYMinValue", "getDefaultYMinValue", "<set-?>", "mActivityAvgCalorie", "getMActivityAvgCalorie", "mActivityCalorie", "getMActivityCalorie", "mAvgCalorie", "getMAvgCalorie", "mCalorieList", "", "mCalorieType", "mChartList", "mDefaultBarColor", "mDefaultBarRadius", "", "mDefaultHeight", "getMDefaultHeight", "()F", "setMDefaultHeight", "(F)V", "mGson", "Lcom/google/gson/Gson;", "mMaxCalorie", "mTotalCalorie", "getMTotalCalorie", "caluteYMaxCalorie", "clearData", "", "combineYearList", "list", "convertDataListToUi", "showChartAnimator", "", "(ZLjava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "convertFrom", "", "dayChartList", "defaultYLabelList", "", "getDataDownloadType", "kotlin.jvm.PlatformType", "getDetailByDay", "startDate", "getDetailByMonth", "start", "end", "getDetailByWeek", "getDetailByYear", "hasData", "date", "monthChartList", "onTypeChanged", "readLocalDayData", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readLocalMonthData", "readLocalWeekData", "readLocalYearData", "setRateType", "rateType", "weekChartList", "yearChartList", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
public final class CalorieDetailPresenter extends BaseDetailPresenter<List<? extends BarChartPoint>, List<? extends BarChartPoint>, List<? extends BarChartPoint>, List<? extends BarChartPoint>, ICalorieDetailView> {
    private static final String TAG = "CalorieDetailPresenter";
    private int mActivityAvgCalorie;
    private int mActivityCalorie;
    private int mAvgCalorie;
    private final List<CalorieDayData> mCalorieList;
    private int mCalorieType;
    private List<BarChartPoint> mChartList;
    private final int mDefaultBarColor;
    private final float mDefaultBarRadius;
    private float mDefaultHeight;
    private final Gson mGson;
    private int mMaxCalorie;
    private int mTotalCalorie;

    /* JADX INFO: renamed from: com.ido.life.module.home.calorie.CalorieDetailPresenter$convertDataListToUi$1, reason: invalid class name */
    /* JADX INFO: compiled from: CalorieDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0082@"}, d2 = {"convertDataListToUi", "", "showChartAnimator", "", "list", "", "Lcom/ido/life/database/model/CalorieDayData;", "continuation", "Lkotlin/coroutines/Continuation;", ""}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.calorie.CalorieDetailPresenter", f = "CalorieDetailPresenter.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}, l = {472, 507, 541}, m = "convertDataListToUi", n = {"this", "showChartAnimator", "list", "startCalendar", "map", "size", "item", Constants.AppPackage.CALENDAR, "calorie", "dayAvgCalorie", "calorieActivity", "dayAvgActivityCalorie", "count", "itemCount", "index", "weekStart", "dayOfWeek", "yMax", "yDefaultHeight", "i", "this", "showChartAnimator", "list", "startCalendar", "map", "size", "item", Constants.AppPackage.CALENDAR, "calorie", "dayAvgCalorie", "calorieActivity", "dayAvgActivityCalorie", "count", "itemCount", "index", "weekStart", "dayOfWeek", "yMax", "yDefaultHeight", "i", "this", "showChartAnimator", "list", "startCalendar", "map", "size", "item", Constants.AppPackage.CALENDAR, "calorie", "dayAvgCalorie", "calorieActivity", "dayAvgActivityCalorie", "count", "itemCount", "index", "weekStart", "dayOfWeek", "yMax", "yDefaultHeight", "i"}, s = {"L$0", "Z$0", "L$1", "L$2", "L$3", "I$0", "L$4", "L$5", "I$1", "I$2", "I$3", "I$4", "I$5", "I$6", "I$7", "I$8", "I$9", "I$10", "F$0", "I$11", "L$0", "Z$0", "L$1", "L$2", "L$3", "I$0", "L$4", "L$5", "I$1", "I$2", "I$3", "I$4", "I$5", "I$6", "I$7", "I$8", "I$9", "I$10", "F$0", "I$11", "L$0", "Z$0", "L$1", "L$2", "L$3", "I$0", "L$4", "L$5", "I$1", "I$2", "I$3", "I$4", "I$5", "I$6", "I$7", "I$8", "I$9", "I$10", "F$0", "I$11"})
    static final class AnonymousClass1 extends ContinuationImpl {
        float F$0;
        int I$0;
        int I$1;
        int I$10;
        int I$11;
        int I$2;
        int I$3;
        int I$4;
        int I$5;
        int I$6;
        int I$7;
        int I$8;
        int I$9;
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
            return CalorieDetailPresenter.this.convertDataListToUi(false, null, this);
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.calorie.CalorieDetailPresenter$readLocalDayData$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: CalorieDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0094@"}, d2 = {"readLocalDayData", "", "showChartAnimator", "", "continuation", "Lkotlin/coroutines/Continuation;", ""}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.calorie.CalorieDetailPresenter", f = "CalorieDetailPresenter.kt", i = {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3}, l = {b.p2, 588, 610, b.I2}, m = "readLocalDayData", n = {"this", "showChartAnimator", "calorieDayData", "list", "originalItem", "listCalorie", "chartList", "this", "showChartAnimator", "calorieDayData", "this", "showChartAnimator", "calorieDayData", "list", "originalItem", "listCalorie", "chartList", "this", "showChartAnimator", "calorieDayData"}, s = {"L$0", "Z$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "Z$0", "L$1", "L$0", "Z$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "Z$0", "L$1"})
    static final class C02961 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        C02961(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CalorieDetailPresenter.this.readLocalDayData(false, this);
        }
    }

    public final int getDefaultYMinValue() {
        return 0;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CalorieDetailPresenter(ICalorieDetailView iView) {
        super(iView);
        Intrinsics.checkParameterIsNotNull(iView, "iView");
        this.mCalorieList = new ArrayList();
        this.mChartList = new ArrayList();
        this.mGson = new Gson();
        this.mDefaultBarColor = Color.parseColor("#66979797");
        this.mDefaultBarRadius = 0.02f;
    }

    public final int getMTotalCalorie() {
        return this.mTotalCalorie;
    }

    public final int getMAvgCalorie() {
        return this.mAvgCalorie;
    }

    public final int getMActivityCalorie() {
        return this.mActivityCalorie;
    }

    public final int getMActivityAvgCalorie() {
        return this.mActivityAvgCalorie;
    }

    public final float getMDefaultHeight() {
        return this.mDefaultHeight;
    }

    public final void setMDefaultHeight(float f2) {
        this.mDefaultHeight = f2;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    protected void getDetailByDay(String startDate) {
        super.getDetailByDay(startDate);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    protected void getDetailByWeek(String start, String end) {
        super.getDetailByWeek(start, end);
    }

    public final void setRateType(int rateType) {
        this.mCalorieType = rateType;
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
            return new ArrayList();
        }
        ensureActive();
        ArrayList arrayList = new ArrayList();
        this.mMaxCalorie = 0;
        int size = list.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            ensureActive();
            int[] iArr = list.get(i2);
            if (iArr[1] > 0) {
                int i3 = iArr[1];
                arrayList.add(new BarChartPoint(iArr[0], iArr[0] + 1, i3));
                this.mMaxCalorie = Math.max(this.mMaxCalorie, i3);
                i++;
            } else {
                arrayList.add(new BarChartPoint(iArr[0], iArr[0] + 1, 0.0f));
            }
        }
        if (this.mCalorieType == 1) {
            int i4 = this.mTotalCalorie;
            if (i > 0) {
                i4 /= i;
            }
            this.mAvgCalorie = i4;
        } else if (i > 0) {
            this.mActivityAvgCalorie = this.mActivityCalorie / i;
        } else {
            this.mAvgCalorie = this.mTotalCalorie;
        }
        float fCaluteYMaxCalorie = caluteYMaxCalorie() * this.mDefaultBarRadius;
        this.mDefaultHeight = fCaluteYMaxCalorie;
        int size2 = arrayList.size();
        for (int i5 = 0; i5 < size2; i5++) {
            ensureActive();
            BarChartPoint barChartPoint = (BarChartPoint) arrayList.get(i5);
            if (barChartPoint == null) {
                Intrinsics.throwNpe();
            }
            if (barChartPoint.y == 0.0f) {
                barChartPoint.y = fCaluteYMaxCalorie;
                barChartPoint.setBarColor(this.mDefaultBarColor);
                barChartPoint.setActualValue(0.0f);
            } else if (barChartPoint.y < fCaluteYMaxCalorie) {
                barChartPoint.y = 1 + fCaluteYMaxCalorie;
            }
        }
        return arrayList;
    }

    private final List<CalorieDayData> combineYearList(List<? extends CalorieDayData> list) {
        int dayAvgActivityCalorie;
        int i;
        int i2;
        List<? extends CalorieDayData> list2 = list;
        int dayAvgCalorie = 0;
        if (list2 == null || list2.isEmpty()) {
            return null;
        }
        ensureActive();
        ArrayList arrayList = new ArrayList();
        int size = list.size();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (int i3 = 0; i3 < size; i3++) {
            ensureActive();
            CalorieDayData calorieDayData = list.get(i3);
            if (calorieDayData != null && !TextUtils.isEmpty(calorieDayData.getDate())) {
                String date = calorieDayData.getDate();
                Intrinsics.checkExpressionValueIsNotNull(date, "item.date");
                if (StringsKt.contains$default((CharSequence) date, (CharSequence) "-", false, 2, (Object) null) && (calorieDayData.getTotalCalorie() != 0 || calorieDayData.getActivityCalorie() != 0)) {
                    String date2 = calorieDayData.getDate();
                    Intrinsics.checkExpressionValueIsNotNull(date2, "item.date");
                    int length = calorieDayData.getDate().length() - 3;
                    if (date2 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                    }
                    String strSubstring = date2.substring(0, length);
                    Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    CalorieDayData calorieDayData2 = (CalorieDayData) linkedHashMap.get(strSubstring);
                    if (calorieDayData2 != null) {
                        if (calorieDayData.getTotalCalorie() > 0) {
                            calorieDayData2.setTotalCalorie(calorieDayData2.getTotalCalorie() + calorieDayData.getTotalCalorie());
                            calorieDayData2.setDayAvgCalorie(calorieDayData2.getDayAvgCalorie() + 1);
                        }
                        if (calorieDayData.getActivityCalorie() > 0) {
                            calorieDayData2.setActivityCalorie(calorieDayData2.getActivityCalorie() + calorieDayData.getActivityCalorie());
                            calorieDayData2.setDayAvgActivityCalorie(calorieDayData2.getDayAvgActivityCalorie() + 1);
                        }
                    } else {
                        CalorieDayData calorieDayDataM24clone = calorieDayData.m24clone();
                        if (calorieDayDataM24clone.getActivityCalorie() > 0) {
                            calorieDayDataM24clone.setDayAvgActivityCalorie(1);
                        } else {
                            calorieDayDataM24clone.setDayAvgActivityCalorie(0);
                        }
                        if (calorieDayDataM24clone.getTotalCalorie() > 0) {
                            calorieDayDataM24clone.setDayAvgCalorie(1);
                        } else {
                            calorieDayDataM24clone.setDayAvgCalorie(0);
                        }
                        linkedHashMap.put(strSubstring, calorieDayDataM24clone);
                    }
                }
            }
        }
        this.mTotalCalorie = 0;
        this.mMaxCalorie = 0;
        this.mActivityCalorie = 0;
        this.mAvgCalorie = 0;
        this.mActivityAvgCalorie = 0;
        if (!linkedHashMap.isEmpty()) {
            dayAvgActivityCalorie = 0;
            for (CalorieDayData calorieDayData3 : linkedHashMap.values()) {
                ensureActive();
                String tag = getTAG();
                StringBuilder sb = new StringBuilder();
                sb.append(calorieDayData3.getDayAvgCalorie());
                sb.append('-');
                sb.append(calorieDayData3.getDayAvgActivityCalorie());
                CommonLogUtil.d(tag, sb.toString());
                if (calorieDayData3.getDayAvgCalorie() > 0) {
                    dayAvgCalorie += calorieDayData3.getDayAvgCalorie();
                    this.mTotalCalorie += calorieDayData3.getTotalCalorie();
                    calorieDayData3.setDayAvgCalorie(calorieDayData3.getTotalCalorie() / calorieDayData3.getDayAvgCalorie());
                    if (this.mCalorieType == 1) {
                        this.mMaxCalorie = Math.max(this.mMaxCalorie, calorieDayData3.getDayAvgCalorie());
                    }
                }
                if (calorieDayData3.getDayAvgActivityCalorie() > 0) {
                    dayAvgActivityCalorie += calorieDayData3.getDayAvgActivityCalorie();
                    this.mActivityCalorie += calorieDayData3.getActivityCalorie();
                    if (this.mCalorieType != 1) {
                        this.mMaxCalorie = Math.max(this.mMaxCalorie, calorieDayData3.getActivityCalorie() / calorieDayData3.getDayAvgActivityCalorie());
                    }
                    calorieDayData3.setDayAvgActivityCalorie(calorieDayData3.getActivityCalorie() / calorieDayData3.getDayAvgActivityCalorie());
                }
                arrayList.add(calorieDayData3);
            }
        } else {
            dayAvgActivityCalorie = 0;
        }
        if (dayAvgCalorie > 0) {
            i = this.mTotalCalorie / dayAvgCalorie;
        } else {
            i = this.mTotalCalorie;
        }
        this.mAvgCalorie = i;
        if (dayAvgActivityCalorie > 0) {
            i2 = this.mActivityCalorie / dayAvgActivityCalorie;
        } else {
            i2 = this.mActivityCalorie;
        }
        this.mActivityAvgCalorie = i2;
        return arrayList;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(10:54|(6:56|(7:260|132|133|(1:153)(5:(3:262|136|137)(1:140)|(3:258|142|143)(1:146)|147|(1:149)(1:150)|151)|176|177|285)(3:278|59|(4:61|276|62|(1:(1:65))(1:(1:67)))(3:282|71|72))|175|176|177|285)(2:73|(3:75|76|(1:(1:79))(1:(1:81)))(3:283|83|84))|68|260|132|133|(0)(0)|176|177|285) */
    /* JADX WARN: Code restructure failed: missing block: B:154:0x0349, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x011b, code lost:
    
        if (r26.getActivityCalorie() == 0) goto L178;
     */
    /* JADX WARN: Removed duplicated region for block: B:135:0x030b  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x0344  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final /* synthetic */ java.lang.Object convertDataListToUi(boolean r46, java.util.List<? extends com.ido.life.database.model.CalorieDayData> r47, kotlin.coroutines.Continuation<? super kotlin.Unit> r48) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 1707
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.home.calorie.CalorieDetailPresenter.convertDataListToUi(boolean, java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.calorie.CalorieDetailPresenter$convertDataListToUi$2, reason: invalid class name */
    /* JADX INFO: compiled from: CalorieDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.calorie.CalorieDetailPresenter$convertDataListToUi$2", f = "CalorieDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
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
            AnonymousClass2 anonymousClass2 = CalorieDetailPresenter.this.new AnonymousClass2(this.$showChartAnimator, completion);
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
            CalorieDetailPresenter.this.getMView().onLoadSuccessByWeek(this.$showChartAnimator, CalorieDetailPresenter.this.mChartList);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.calorie.CalorieDetailPresenter$convertDataListToUi$3, reason: invalid class name */
    /* JADX INFO: compiled from: CalorieDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.calorie.CalorieDetailPresenter$convertDataListToUi$3", f = "CalorieDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
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
            AnonymousClass3 anonymousClass3 = CalorieDetailPresenter.this.new AnonymousClass3(this.$showChartAnimator, completion);
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
            CalorieDetailPresenter.this.getMView().onLoadSuccessByMonth(this.$showChartAnimator, CalorieDetailPresenter.this.mChartList);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.calorie.CalorieDetailPresenter$convertDataListToUi$4, reason: invalid class name */
    /* JADX INFO: compiled from: CalorieDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.calorie.CalorieDetailPresenter$convertDataListToUi$4", f = "CalorieDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
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
            AnonymousClass4 anonymousClass4 = CalorieDetailPresenter.this.new AnonymousClass4(this.$showChartAnimator, completion);
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
            CalorieDetailPresenter.this.getMView().onLoadSuccessByYear(this.$showChartAnimator, CalorieDetailPresenter.this.mChartList);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0016  */
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected java.lang.Object readLocalDayData(boolean r13, kotlin.coroutines.Continuation<? super kotlin.Unit> r14) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 431
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.home.calorie.CalorieDetailPresenter.readLocalDayData(boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.calorie.CalorieDetailPresenter$readLocalDayData$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: CalorieDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.calorie.CalorieDetailPresenter$readLocalDayData$2", f = "CalorieDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C02972 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ boolean $showChartAnimator;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02972(boolean z, Continuation continuation) {
            super(2, continuation);
            this.$showChartAnimator = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C02972 c02972 = CalorieDetailPresenter.this.new C02972(this.$showChartAnimator, completion);
            c02972.p$ = (CoroutineScope) obj;
            return c02972;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C02972) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            if (!CalorieDetailPresenter.this.mChartList.isEmpty()) {
                CalorieDetailPresenter.this.getMView().onLoadSuccessByDay(this.$showChartAnimator, CalorieDetailPresenter.this.mChartList);
            } else {
                CalorieDetailPresenter.this.getMView().onDetailLoadFailed();
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.calorie.CalorieDetailPresenter$readLocalDayData$3, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: CalorieDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.calorie.CalorieDetailPresenter$readLocalDayData$3", f = "CalorieDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C02983 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        C02983(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C02983 c02983 = CalorieDetailPresenter.this.new C02983(completion);
            c02983.p$ = (CoroutineScope) obj;
            return c02983;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C02983) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            CalorieDetailPresenter.this.getMView().onDetailLoadFailed();
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.calorie.CalorieDetailPresenter$readLocalDayData$4, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: CalorieDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.calorie.CalorieDetailPresenter$readLocalDayData$4", f = "CalorieDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C02994 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ boolean $showChartAnimator;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02994(boolean z, Continuation continuation) {
            super(2, continuation);
            this.$showChartAnimator = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C02994 c02994 = CalorieDetailPresenter.this.new C02994(this.$showChartAnimator, completion);
            c02994.p$ = (CoroutineScope) obj;
            return c02994;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C02994) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            if (!CalorieDetailPresenter.this.mChartList.isEmpty()) {
                CalorieDetailPresenter.this.getMView().onLoadSuccessByDay(this.$showChartAnimator, CalorieDetailPresenter.this.mChartList);
            } else {
                CalorieDetailPresenter.this.getMView().onDetailLoadFailed();
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.calorie.CalorieDetailPresenter$readLocalDayData$5, reason: invalid class name */
    /* JADX INFO: compiled from: CalorieDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.calorie.CalorieDetailPresenter$readLocalDayData$5", f = "CalorieDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass5 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        AnonymousClass5(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            AnonymousClass5 anonymousClass5 = CalorieDetailPresenter.this.new AnonymousClass5(completion);
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
            CalorieDetailPresenter.this.getMView().onDetailLoadFailed();
            return Unit.INSTANCE;
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    protected Object readLocalWeekData(boolean z, Continuation<? super Unit> continuation) throws Throwable {
        List<CalorieDayData> calorieDailyDataList = LocalHealthDataManager.getInstance().getCalorieDailyDataList(getMStartDate(), getMEndDate());
        List<CalorieDayData> list = calorieDailyDataList;
        if (!(list == null || list.isEmpty())) {
            Object objConvertDataListToUi = convertDataListToUi(z, calorieDailyDataList, continuation);
            if (objConvertDataListToUi == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                return objConvertDataListToUi;
            }
        } else {
            Object objWithContext = BuildersKt.withContext(Dispatchers.getMain(), new C03012(null), continuation);
            if (objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                return objWithContext;
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.calorie.CalorieDetailPresenter$readLocalWeekData$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: CalorieDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.calorie.CalorieDetailPresenter$readLocalWeekData$2", f = "CalorieDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03012 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        C03012(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03012 c03012 = CalorieDetailPresenter.this.new C03012(completion);
            c03012.p$ = (CoroutineScope) obj;
            return c03012;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03012) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            CalorieDetailPresenter.this.getMView().onDetailLoadFailed();
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
        List<CalorieDayData> calorieMonthData = LocalHealthDataManager.getInstance().getCalorieMonthData(getUserId(), Integer.parseInt((String) listSplit$default.get(0)), Integer.parseInt((String) listSplit$default.get(1)));
        List<CalorieDayData> list = calorieMonthData;
        if (!(list == null || list.isEmpty())) {
            Object objConvertDataListToUi = convertDataListToUi(z, calorieMonthData, continuation);
            if (objConvertDataListToUi == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                return objConvertDataListToUi;
            }
        } else {
            Object objWithContext = BuildersKt.withContext(Dispatchers.getMain(), new C03002(null), continuation);
            if (objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                return objWithContext;
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.calorie.CalorieDetailPresenter$readLocalMonthData$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: CalorieDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.calorie.CalorieDetailPresenter$readLocalMonthData$2", f = "CalorieDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03002 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        C03002(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03002 c03002 = CalorieDetailPresenter.this.new C03002(completion);
            c03002.p$ = (CoroutineScope) obj;
            return c03002;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03002) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            CalorieDetailPresenter.this.getMView().onDetailLoadFailed();
            return Unit.INSTANCE;
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    protected Object readLocalYearData(boolean z, Continuation<? super Unit> continuation) throws Throwable {
        String mStartDate = getMStartDate();
        boolean z2 = true;
        if (mStartDate == null || mStartDate.length() == 0) {
            return Unit.INSTANCE;
        }
        ensureActive();
        LocalHealthDataManager localHealthDataManager = LocalHealthDataManager.getInstance();
        long userId = getUserId();
        String mStartDate2 = getMStartDate();
        if (mStartDate2 == null) {
            Intrinsics.throwNpe();
        }
        if (mStartDate2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String strSubstring = mStartDate2.substring(0, 4);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        List<CalorieDayData> calorieYearData = localHealthDataManager.getCalorieYearData(userId, Integer.parseInt(strSubstring));
        if (calorieYearData != null && calorieYearData.size() > 0) {
            List<CalorieDayData> listCombineYearList = combineYearList(calorieYearData);
            List<CalorieDayData> list = listCombineYearList;
            if (list != null && !list.isEmpty()) {
                z2 = false;
            }
            if (z2) {
                Object objWithContext = BuildersKt.withContext(Dispatchers.getMain(), new C03022(null), continuation);
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
            Object objWithContext2 = BuildersKt.withContext(Dispatchers.getMain(), new C03033(null), continuation);
            if (objWithContext2 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                return objWithContext2;
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.calorie.CalorieDetailPresenter$readLocalYearData$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: CalorieDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.calorie.CalorieDetailPresenter$readLocalYearData$2", f = "CalorieDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03022 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        C03022(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03022 c03022 = CalorieDetailPresenter.this.new C03022(completion);
            c03022.p$ = (CoroutineScope) obj;
            return c03022;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03022) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            CalorieDetailPresenter.this.getMView().onDetailLoadFailed();
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.calorie.CalorieDetailPresenter$readLocalYearData$3, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: CalorieDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.calorie.CalorieDetailPresenter$readLocalYearData$3", f = "CalorieDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03033 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        C03033(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03033 c03033 = CalorieDetailPresenter.this.new C03033(completion);
            c03033.p$ = (CoroutineScope) obj;
            return c03033;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03033) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            CalorieDetailPresenter.this.getMView().onDetailLoadFailed();
            return Unit.INSTANCE;
        }
    }

    public final List<String> defaultYLabelList() {
        ArrayList arrayList = new ArrayList();
        int i = this.mMaxCalorie;
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
                this.mMaxCalorie = ((i / 10) + 1) * 10;
                int i3 = this.mMaxCalorie / 4;
                while (i2 < 5) {
                    arrayList.add(String.valueOf(i3 * i2));
                    i2++;
                }
            } else {
                int i4 = this.mMaxCalorie;
                if (100 <= i4 && 998 >= i4) {
                    this.mMaxCalorie = ((i4 / 100) + 1) * 100;
                    int i5 = this.mMaxCalorie / 4;
                    while (i2 < 5) {
                        arrayList.add(String.valueOf(i5 * i2));
                        i2++;
                    }
                } else {
                    this.mMaxCalorie = ((this.mMaxCalorie / 1000) + 1) * 1000;
                    int i6 = this.mMaxCalorie / 4;
                    while (i2 < 5) {
                        arrayList.add(String.valueOf(i6 * i2));
                        i2++;
                    }
                }
            }
        }
        return arrayList;
    }

    public final int caluteYMaxCalorie() {
        int i = this.mMaxCalorie;
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

    public final int getDefaultYMaxValue() {
        return caluteYMaxCalorie();
    }

    public final List<CalorieDayData> getCalorieList() {
        return this.mCalorieList;
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
        float fCaluteYMaxCalorie = caluteYMaxCalorie() * this.mDefaultBarRadius;
        this.mDefaultHeight = fCaluteYMaxCalorie;
        for (int i = 1; i < actualMaximum; i++) {
            arrayList.add(new BarChartPoint(i - 1, i, fCaluteYMaxCalorie, 0.0f, this.mDefaultBarColor));
        }
        return arrayList;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    protected void clearData() {
        this.mChartList.clear();
        this.mCalorieList.clear();
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    protected void onTypeChanged() {
        super.onTypeChanged();
        this.mMaxCalorie = 0;
        this.mAvgCalorie = 0;
        this.mTotalCalorie = 0;
        this.mActivityAvgCalorie = 0;
        this.mActivityCalorie = 0;
    }

    public final List<BarChartPoint> dayChartList() {
        return this.mChartList;
    }

    public final List<BarChartPoint> weekChartList() {
        return this.mChartList;
    }

    public final List<BarChartPoint> monthChartList() {
        return this.mChartList;
    }

    public final List<BarChartPoint> yearChartList() {
        return this.mChartList;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    protected List<String> getDataDownloadType() {
        return CollectionsKt.mutableListOf(CalorieDayData.class.getSimpleName());
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    public boolean hasData(String date) {
        if (TextUtils.isEmpty(date)) {
            return false;
        }
        return LocalHealthDataManager.getInstance().hasCalorieDayData(date);
    }
}