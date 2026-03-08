package com.ido.life.module.home.activity;

import android.graphics.Color;
import android.text.TextUtils;
import com.ido.common.utils.GsonUtil;
import com.ido.life.bean.BarChartPoint;
import com.ido.life.constants.Constants;
import com.ido.life.database.LocalHealthDataManager;
import com.ido.life.database.model.ActiveTimeDayData;
import com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter;
import com.ido.life.util.DateUtil;
import com.ido.life.util.HealthDataUtil;
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

/* JADX INFO: compiled from: ActivityDetailPresenter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u000f\u0018\u0000 G28\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0004\u0012\u00020\u00040\u0001:\u0001GB\r\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010\u0006J\u0006\u0010/\u001a\u00020\u0010J\b\u00100\u001a\u000201H\u0014J \u00102\u001a\n\u0012\u0004\u0012\u00020\"\u0018\u00010\u00022\u000e\u00103\u001a\n\u0012\u0004\u0012\u00020\"\u0018\u00010\u0002H\u0002J'\u00104\u001a\u0002012\u0006\u00105\u001a\u0002062\f\u00103\u001a\b\u0012\u0004\u0012\u00020\"0\u0002H\u0082@ø\u0001\u0000¢\u0006\u0002\u00107J \u00108\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u001b2\u000e\u00103\u001a\n\u0012\u0004\u0012\u000209\u0018\u00010\u0002H\u0002J\u0016\u0010:\u001a\u0010\u0012\f\u0012\n ;*\u0004\u0018\u00010\r0\r0\u001bH\u0014J\u0012\u0010<\u001a\u0002012\b\u0010=\u001a\u0004\u0018\u00010\rH\u0014J\u0012\u0010>\u001a\u0002062\b\u0010?\u001a\u0004\u0018\u00010\rH\u0016J\b\u0010@\u001a\u000201H\u0014J\b\u0010A\u001a\u000201H\u0002J\u0019\u0010B\u001a\u0002012\u0006\u00105\u001a\u000206H\u0094@ø\u0001\u0000¢\u0006\u0002\u0010CJ\u0019\u0010D\u001a\u0002012\u0006\u00105\u001a\u000206H\u0094@ø\u0001\u0000¢\u0006\u0002\u0010CJ\u0019\u0010E\u001a\u0002012\u0006\u00105\u001a\u000206H\u0094@ø\u0001\u0000¢\u0006\u0002\u0010CJ\u0019\u0010F\u001a\u0002012\u0006\u00105\u001a\u000206H\u0094@ø\u0001\u0000¢\u0006\u0002\u0010CR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028F¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\tR\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u00028F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\tR\u0011\u0010\u000f\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0012R\u001e\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u0010@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0012R\u001e\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u0010@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0012R\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00030\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028F¢\u0006\u0006\u001a\u0004\b&\u0010\tR\u0017\u0010'\u001a\b\u0012\u0004\u0012\u00020\"0\u00028F¢\u0006\u0006\u001a\u0004\b(\u0010\tR\u0011\u0010)\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b*\u0010\u0012R\u0017\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028F¢\u0006\u0006\u001a\u0004\b,\u0010\tR\u0017\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028F¢\u0006\u0006\u001a\u0004\b.\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006H"}, d2 = {"Lcom/ido/life/module/home/activity/ActivityDetailPresenter;", "Lcom/ido/life/module/home/chartdetail/vertical/BaseDetailPresenter;", "", "Lcom/ido/life/bean/BarChartPoint;", "Lcom/ido/life/module/home/activity/IActivityDetailView;", "view", "(Lcom/ido/life/module/home/activity/IActivityDetailView;)V", "dayChartList", "getDayChartList", "()Ljava/util/List;", "defaultList", "getDefaultList", "defaultYLabelList", "", "getDefaultYLabelList", "defaultYMaxValue", "", "getDefaultYMaxValue", "()I", "defaultYMinValue", "getDefaultYMinValue", "<set-?>", "mActivityTime", "getMActivityTime", "mAvgActivityTime", "getMAvgActivityTime", "mChartList", "", "mDefaultBarColor", "mDefaultBarRadius", "", "mMaxActiviteTime", "mNormalBarColor", "mSportList", "Lcom/ido/life/database/model/ActiveTimeDayData;", "mTargetActivityTime", "mTargetBarColor", "monthChartList", "getMonthChartList", "sportList", "getSportList", "targetActivityTime", "getTargetActivityTime", "weekChartList", "getWeekChartList", "yearChartList", "getYearChartList", "caluteYMaxActiviteTime", "clearData", "", "combineYearList", "list", "convertDataListToUi", "showChartAnimator", "", "(ZLjava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "convertFrom", "", "getDataDownloadType", "kotlin.jvm.PlatformType", "getDetailByDay", "startDate", "hasData", "date", "onTypeChanged", "queryTargetActivityTime", "readLocalDayData", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readLocalMonthData", "readLocalWeekData", "readLocalYearData", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
public final class ActivityDetailPresenter extends BaseDetailPresenter<List<? extends BarChartPoint>, List<? extends BarChartPoint>, List<? extends BarChartPoint>, List<? extends BarChartPoint>, IActivityDetailView> {
    private static final int DEFAULT_TARGET_ACTIVITY_TIME = 30;
    private int mActivityTime;
    private int mAvgActivityTime;
    private List<BarChartPoint> mChartList;
    private final int mDefaultBarColor;
    private final float mDefaultBarRadius;
    private int mMaxActiviteTime;
    private final int mNormalBarColor;
    private final List<ActiveTimeDayData> mSportList;
    private int mTargetActivityTime;
    private final int mTargetBarColor;

    /* JADX INFO: renamed from: com.ido.life.module.home.activity.ActivityDetailPresenter$convertDataListToUi$1, reason: invalid class name */
    /* JADX INFO: compiled from: ActivityDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0082@"}, d2 = {"convertDataListToUi", "", "showChartAnimator", "", "list", "", "Lcom/ido/life/database/model/ActiveTimeDayData;", "continuation", "Lkotlin/coroutines/Continuation;", ""}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.activity.ActivityDetailPresenter", f = "ActivityDetailPresenter.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, l = {290, b.a1, 341, 347}, m = "convertDataListToUi", n = {"this", "showChartAnimator", "list", "startCalendar", "map", "size", "item", Constants.AppPackage.CALENDAR, "activeTime", "dayAvgActivityTime", "count", "index", "weekStart", "dayOfWeek", "yMax", "yDefaultHeight", "i", "this", "showChartAnimator", "list", "startCalendar", "map", "size", "item", Constants.AppPackage.CALENDAR, "activeTime", "dayAvgActivityTime", "count", "index", "weekStart", "dayOfWeek", "yMax", "yDefaultHeight", "i", "this", "showChartAnimator", "list", "startCalendar", "map", "size", "item", Constants.AppPackage.CALENDAR, "activeTime", "dayAvgActivityTime", "count", "index", "weekStart", "dayOfWeek", "yMax", "yDefaultHeight", "i", "this", "showChartAnimator", "list", "startCalendar", "map", "size", "item", Constants.AppPackage.CALENDAR, "activeTime", "dayAvgActivityTime", "count", "index", "weekStart", "dayOfWeek"}, s = {"L$0", "Z$0", "L$1", "L$2", "L$3", "I$0", "L$4", "L$5", "I$1", "I$2", "I$3", "I$4", "I$5", "I$6", "I$7", "F$0", "I$8", "L$0", "Z$0", "L$1", "L$2", "L$3", "I$0", "L$4", "L$5", "I$1", "I$2", "I$3", "I$4", "I$5", "I$6", "I$7", "F$0", "I$8", "L$0", "Z$0", "L$1", "L$2", "L$3", "I$0", "L$4", "L$5", "I$1", "I$2", "I$3", "I$4", "I$5", "I$6", "I$7", "F$0", "I$8", "L$0", "Z$0", "L$1", "L$2", "L$3", "I$0", "L$4", "L$5", "I$1", "I$2", "I$3", "I$4", "I$5", "I$6"})
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
            return ActivityDetailPresenter.this.convertDataListToUi(false, null, this);
        }
    }

    public final int getDefaultYMinValue() {
        return 0;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ActivityDetailPresenter(IActivityDetailView view) {
        super(view);
        Intrinsics.checkParameterIsNotNull(view, "view");
        this.mSportList = new ArrayList();
        this.mChartList = new ArrayList();
        this.mDefaultBarColor = Color.parseColor("#66979797");
        this.mTargetBarColor = Color.parseColor("#65E8CB");
        this.mNormalBarColor = Color.parseColor("#8065E8CB");
        this.mDefaultBarRadius = 0.02f;
    }

    public final int getMActivityTime() {
        return this.mActivityTime;
    }

    public final int getMAvgActivityTime() {
        return this.mAvgActivityTime;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    protected void getDetailByDay(String startDate) {
        super.getDetailByDay(startDate);
    }

    private final List<BarChartPoint> convertFrom(List<int[]> list) {
        if (list == null || list.size() != 24) {
            return null;
        }
        ensureActive();
        ArrayList arrayList = new ArrayList();
        this.mAvgActivityTime = 0;
        this.mActivityTime = this.mAvgActivityTime;
        this.mMaxActiviteTime = 0;
        int size = list.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            ensureActive();
            int[] iArr = list.get(i2);
            if (iArr[1] > 0) {
                int i3 = iArr[1] / 60;
                arrayList.add(new BarChartPoint(iArr[0], iArr[0] + 1, i3));
                this.mActivityTime += i3;
                this.mMaxActiviteTime = Math.max(this.mMaxActiviteTime, i3);
                i++;
            } else {
                arrayList.add(new BarChartPoint(iArr[0], iArr[0] + 1, 0.0f));
            }
        }
        int i4 = this.mActivityTime;
        if (i > 0) {
            i4 /= i;
        }
        this.mAvgActivityTime = i4;
        float fCaluteYMaxActiviteTime = caluteYMaxActiviteTime() * this.mDefaultBarRadius;
        int size2 = arrayList.size();
        for (int i5 = 0; i5 < size2; i5++) {
            ensureActive();
            BarChartPoint barChartPoint = (BarChartPoint) arrayList.get(i5);
            if (barChartPoint.y == 0.0f) {
                barChartPoint.y = fCaluteYMaxActiviteTime;
                barChartPoint.setBarColor(this.mDefaultBarColor);
                barChartPoint.setActualValue(0.0f);
            } else if (barChartPoint.y < fCaluteYMaxActiviteTime) {
                barChartPoint.y = 1 + fCaluteYMaxActiviteTime;
            }
        }
        return arrayList;
    }

    private final List<ActiveTimeDayData> combineYearList(List<? extends ActiveTimeDayData> list) {
        List<? extends ActiveTimeDayData> list2 = list;
        if (list2 == null || list2.isEmpty()) {
            return null;
        }
        ensureActive();
        ArrayList arrayList = new ArrayList();
        this.mActivityTime = 0;
        this.mMaxActiviteTime = 0;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int size = list.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            ensureActive();
            try {
                ActiveTimeDayData activeTimeDayData = list.get(i2);
                String date = activeTimeDayData.getDate();
                if (!(date == null || date.length() == 0) && activeTimeDayData.getTotalSeconds() > 0) {
                    String date2 = activeTimeDayData.getDate();
                    Intrinsics.checkExpressionValueIsNotNull(date2, "itemActive.date");
                    int length = activeTimeDayData.getDate().length() - 3;
                    if (date2 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                    }
                    String strSubstring = date2.substring(0, length);
                    Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    ActiveTimeDayData activeTimeDayData2 = (ActiveTimeDayData) linkedHashMap.get(strSubstring);
                    if (activeTimeDayData2 == null) {
                        ActiveTimeDayData activeTimeDayDataM23clone = activeTimeDayData.m23clone();
                        activeTimeDayDataM23clone.setDayAvgSecond(1);
                        linkedHashMap.put(strSubstring, activeTimeDayDataM23clone);
                    } else {
                        activeTimeDayData2.setDayAvgSecond(activeTimeDayData2.getDayAvgSecond() + 1);
                        activeTimeDayData2.setTotalSeconds(activeTimeDayData2.getTotalSeconds() + activeTimeDayData.getTotalSeconds());
                    }
                    this.mActivityTime += activeTimeDayData.getTotalSeconds();
                    i++;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (!linkedHashMap.isEmpty()) {
            for (ActiveTimeDayData activeTimeDayData3 : linkedHashMap.values()) {
                ensureActive();
                activeTimeDayData3.setDayAvgSecond((activeTimeDayData3.getTotalSeconds() / 60) / activeTimeDayData3.getDayAvgSecond());
                this.mMaxActiviteTime = Math.max(this.mMaxActiviteTime, activeTimeDayData3.getDayAvgSecond());
                arrayList.add(activeTimeDayData3);
            }
            this.mActivityTime /= 60;
            if (i > 0) {
                this.mAvgActivityTime = this.mActivityTime / i;
            }
        }
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x00fe  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final /* synthetic */ java.lang.Object convertDataListToUi(boolean r34, java.util.List<? extends com.ido.life.database.model.ActiveTimeDayData> r35, kotlin.coroutines.Continuation<? super kotlin.Unit> r36) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 1681
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.home.activity.ActivityDetailPresenter.convertDataListToUi(boolean, java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.activity.ActivityDetailPresenter$convertDataListToUi$2, reason: invalid class name */
    /* JADX INFO: compiled from: ActivityDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.activity.ActivityDetailPresenter$convertDataListToUi$2", f = "ActivityDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
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
            AnonymousClass2 anonymousClass2 = ActivityDetailPresenter.this.new AnonymousClass2(this.$showChartAnimator, completion);
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
            ActivityDetailPresenter.this.getMView().onLoadSuccessByWeek(this.$showChartAnimator, ActivityDetailPresenter.this.mChartList);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.activity.ActivityDetailPresenter$convertDataListToUi$3, reason: invalid class name */
    /* JADX INFO: compiled from: ActivityDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.activity.ActivityDetailPresenter$convertDataListToUi$3", f = "ActivityDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
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
            AnonymousClass3 anonymousClass3 = ActivityDetailPresenter.this.new AnonymousClass3(this.$showChartAnimator, completion);
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
            ActivityDetailPresenter.this.getMView().onLoadSuccessByMonth(this.$showChartAnimator, ActivityDetailPresenter.this.mChartList);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.activity.ActivityDetailPresenter$convertDataListToUi$4, reason: invalid class name */
    /* JADX INFO: compiled from: ActivityDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.activity.ActivityDetailPresenter$convertDataListToUi$4", f = "ActivityDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
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
            AnonymousClass4 anonymousClass4 = ActivityDetailPresenter.this.new AnonymousClass4(this.$showChartAnimator, completion);
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
            ActivityDetailPresenter.this.getMView().onLoadSuccessByYear(this.$showChartAnimator, ActivityDetailPresenter.this.mChartList);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.activity.ActivityDetailPresenter$convertDataListToUi$5, reason: invalid class name */
    /* JADX INFO: compiled from: ActivityDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.activity.ActivityDetailPresenter$convertDataListToUi$5", f = "ActivityDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass5 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        AnonymousClass5(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            AnonymousClass5 anonymousClass5 = ActivityDetailPresenter.this.new AnonymousClass5(completion);
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
            ActivityDetailPresenter.this.getMView().onDetailLoadFailed();
            return Unit.INSTANCE;
        }
    }

    public final List<String> getDefaultYLabelList() {
        ArrayList arrayList = new ArrayList();
        int i = this.mMaxActiviteTime;
        int i2 = 0;
        if (i < 10) {
            this.mMaxActiviteTime = ((i / 2) + 1) * 2;
            int i3 = this.mMaxActiviteTime / 2;
            while (i2 < 3) {
                arrayList.add(String.valueOf(i3 * i2));
                i2++;
            }
        } else if (10 <= i && 98 >= i) {
            this.mMaxActiviteTime = ((i / 10) + 1) * 10;
            int i4 = this.mMaxActiviteTime / 2;
            while (i2 < 3) {
                arrayList.add(String.valueOf(i4 * i2));
                i2++;
            }
        } else {
            int i5 = this.mMaxActiviteTime;
            if (100 <= i5 && 998 >= i5) {
                this.mMaxActiviteTime = ((i5 / 100) + 1) * 100;
                int i6 = this.mMaxActiviteTime / 2;
                while (i2 < 3) {
                    arrayList.add(String.valueOf(i6 * i2));
                    i2++;
                }
            } else {
                this.mMaxActiviteTime = ((this.mMaxActiviteTime / 1000) + 1) * 1000;
                int i7 = this.mMaxActiviteTime / 2;
                while (i2 < 3) {
                    arrayList.add(String.valueOf(i7 * i2));
                    i2++;
                }
            }
        }
        return arrayList;
    }

    public final int getTargetActivityTime() {
        if (this.mTargetActivityTime <= 0) {
            queryTargetActivityTime();
        }
        caluteYMaxActiviteTime();
        return this.mTargetActivityTime;
    }

    private final void queryTargetActivityTime() {
        this.mTargetActivityTime = 30;
        int i = this.mMaxActiviteTime;
        int i2 = this.mTargetActivityTime;
        if (i <= i2) {
            this.mMaxActiviteTime = (int) (i2 * 1.1f);
        }
    }

    public final int caluteYMaxActiviteTime() {
        int i = this.mMaxActiviteTime;
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
        return caluteYMaxActiviteTime();
    }

    public final List<ActiveTimeDayData> getSportList() {
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
        float fCaluteYMaxActiviteTime = caluteYMaxActiviteTime() * this.mDefaultBarRadius;
        for (int i = 1; i < actualMaximum; i++) {
            arrayList.add(new BarChartPoint(i - 1, i, fCaluteYMaxActiviteTime, 0.0f, this.mDefaultBarColor));
        }
        return arrayList;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    protected void clearData() {
        this.mChartList.clear();
        this.mSportList.clear();
        this.mTargetActivityTime = 0;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    protected void onTypeChanged() {
        super.onTypeChanged();
        this.mMaxActiviteTime = 0;
        this.mAvgActivityTime = this.mMaxActiviteTime;
        this.mActivityTime = this.mAvgActivityTime;
        this.mTargetActivityTime = 0;
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
        return CollectionsKt.mutableListOf(ActiveTimeDayData.class.getSimpleName());
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    public boolean hasData(String date) {
        if (TextUtils.isEmpty(date)) {
            return false;
        }
        return LocalHealthDataManager.getInstance().hasActiveTimeDayData(date);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    protected Object readLocalDayData(boolean z, Continuation<? super Unit> continuation) {
        LocalHealthDataManager localHealthDataManager = LocalHealthDataManager.getInstance();
        String mStartDate = getMStartDate();
        if (mStartDate == null) {
            Intrinsics.throwNpe();
        }
        ActiveTimeDayData activeTimeDailyDataByDate = localHealthDataManager.getActiveTimeDailyDataByDate(mStartDate);
        if (activeTimeDailyDataByDate != null && !TextUtils.isEmpty(activeTimeDailyDataByDate.getItems())) {
            int[] iArr = (int[]) GsonUtil.fromJson(activeTimeDailyDataByDate.getItems(), int[].class);
            ArrayList arrayList = new ArrayList();
            if (iArr != null) {
                for (int i : iArr) {
                    ensureActive();
                    arrayList.add(Boxing.boxInt(i));
                }
            }
            List<BarChartPoint> listConvertFrom = convertFrom(HealthDataUtil.convertCalorieToHourItem(arrayList, 15));
            clearData();
            List<BarChartPoint> list = listConvertFrom;
            if (!(list == null || list.isEmpty())) {
                this.mChartList.addAll(list);
            }
            Object objWithContext = BuildersKt.withContext(Dispatchers.getMain(), new C02872(z, null), continuation);
            if (objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                return objWithContext;
            }
        } else {
            Object objWithContext2 = BuildersKt.withContext(Dispatchers.getMain(), new C02883(null), continuation);
            if (objWithContext2 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                return objWithContext2;
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.activity.ActivityDetailPresenter$readLocalDayData$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ActivityDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.activity.ActivityDetailPresenter$readLocalDayData$2", f = "ActivityDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C02872 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ boolean $showChartAnimator;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C02872(boolean z, Continuation continuation) {
            super(2, continuation);
            this.$showChartAnimator = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C02872 c02872 = ActivityDetailPresenter.this.new C02872(this.$showChartAnimator, completion);
            c02872.p$ = (CoroutineScope) obj;
            return c02872;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C02872) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            if (!ActivityDetailPresenter.this.mChartList.isEmpty()) {
                ActivityDetailPresenter.this.getMView().onLoadSuccessByDay(this.$showChartAnimator, ActivityDetailPresenter.this.mChartList);
            } else {
                ActivityDetailPresenter.this.getMView().onDetailLoadFailed();
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.activity.ActivityDetailPresenter$readLocalDayData$3, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ActivityDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.activity.ActivityDetailPresenter$readLocalDayData$3", f = "ActivityDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C02883 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        C02883(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C02883 c02883 = ActivityDetailPresenter.this.new C02883(completion);
            c02883.p$ = (CoroutineScope) obj;
            return c02883;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C02883) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            ActivityDetailPresenter.this.getMView().onDetailLoadFailed();
            return Unit.INSTANCE;
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    protected Object readLocalWeekData(boolean z, Continuation<? super Unit> continuation) throws Throwable {
        List<ActiveTimeDayData> activeTimeDailyDataList = LocalHealthDataManager.getInstance().getActiveTimeDailyDataList(getMStartDate(), getMEndDate());
        if (activeTimeDailyDataList != null && activeTimeDailyDataList.size() > 0) {
            Object objConvertDataListToUi = convertDataListToUi(z, activeTimeDailyDataList, continuation);
            if (objConvertDataListToUi == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                return objConvertDataListToUi;
            }
        } else {
            Object objWithContext = BuildersKt.withContext(Dispatchers.getMain(), new C02902(null), continuation);
            if (objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                return objWithContext;
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.activity.ActivityDetailPresenter$readLocalWeekData$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ActivityDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.activity.ActivityDetailPresenter$readLocalWeekData$2", f = "ActivityDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C02902 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        C02902(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C02902 c02902 = ActivityDetailPresenter.this.new C02902(completion);
            c02902.p$ = (CoroutineScope) obj;
            return c02902;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C02902) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            ActivityDetailPresenter.this.getMView().onDetailLoadFailed();
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
        List<ActiveTimeDayData> activeTimeByMonth = LocalHealthDataManager.getInstance().getActiveTimeByMonth(getUserId(), Integer.parseInt((String) listSplit$default.get(0)), Integer.parseInt((String) listSplit$default.get(1)));
        if (activeTimeByMonth != null && activeTimeByMonth.size() > 0) {
            Object objConvertDataListToUi = convertDataListToUi(z, activeTimeByMonth, continuation);
            if (objConvertDataListToUi == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                return objConvertDataListToUi;
            }
        } else {
            Object objWithContext = BuildersKt.withContext(Dispatchers.getMain(), new C02892(null), continuation);
            if (objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                return objWithContext;
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.activity.ActivityDetailPresenter$readLocalMonthData$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ActivityDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.activity.ActivityDetailPresenter$readLocalMonthData$2", f = "ActivityDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C02892 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        C02892(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C02892 c02892 = ActivityDetailPresenter.this.new C02892(completion);
            c02892.p$ = (CoroutineScope) obj;
            return c02892;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C02892) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            ActivityDetailPresenter.this.getMView().onDetailLoadFailed();
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
        List<ActiveTimeDayData> activeTimeByYear = localHealthDataManager.getActiveTimeByYear(userId, Integer.parseInt(strSubstring));
        if (activeTimeByYear != null && activeTimeByYear.size() > 0) {
            List<ActiveTimeDayData> listCombineYearList = combineYearList(activeTimeByYear);
            List<ActiveTimeDayData> list = listCombineYearList;
            if (list == null || list.isEmpty()) {
                Object objWithContext = BuildersKt.withContext(Dispatchers.getMain(), new C02912(null), continuation);
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
            Object objWithContext2 = BuildersKt.withContext(Dispatchers.getMain(), new C02923(null), continuation);
            if (objWithContext2 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                return objWithContext2;
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.activity.ActivityDetailPresenter$readLocalYearData$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ActivityDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.activity.ActivityDetailPresenter$readLocalYearData$2", f = "ActivityDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C02912 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        C02912(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C02912 c02912 = ActivityDetailPresenter.this.new C02912(completion);
            c02912.p$ = (CoroutineScope) obj;
            return c02912;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C02912) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            ActivityDetailPresenter.this.getMView().onDetailLoadFailed();
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.activity.ActivityDetailPresenter$readLocalYearData$3, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ActivityDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.activity.ActivityDetailPresenter$readLocalYearData$3", f = "ActivityDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C02923 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        C02923(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C02923 c02923 = ActivityDetailPresenter.this.new C02923(completion);
            c02923.p$ = (CoroutineScope) obj;
            return c02923;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C02923) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            ActivityDetailPresenter.this.getMView().onDetailLoadFailed();
            return Unit.INSTANCE;
        }
    }
}