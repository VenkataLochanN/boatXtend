package com.ido.life.module.home.pressure.detail.vertical;

import android.graphics.Color;
import android.text.TextUtils;
import com.boat.Xtend.two.R;
import com.ido.alexa.AlexaCustomSkillConstant;
import com.ido.common.utils.GsonUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.bean.BaseCharBean;
import com.ido.life.bean.PressureBarChartPoint;
import com.ido.life.database.model.HealthPressure;
import com.ido.life.enums.PressureEnum;
import com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter;
import com.ido.life.util.DateUtil;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RunTimeUtil;
import com.veryfit.multi.nativeprotocol.b;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TreeSet;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.math.MathKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: compiled from: PresureDetailPresenter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u000e\u0018\u0000 S28\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0002\u0012\u0004\u0012\u00020\u00050\u0001:\u0002STB\r\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\u0010\u0010/\u001a\u00020\u00112\u0006\u00100\u001a\u00020\u000eH\u0002J\b\u00101\u001a\u000202H\u0014J*\u00103\u001a\b\u0012\u0004\u0012\u00020&0\u00162\f\u00104\u001a\b\u0012\u0004\u0012\u00020&0\u00162\f\u00105\u001a\b\u0012\u0004\u0012\u00020&0\u0016H\u0002J \u00106\u001a\n\u0012\u0004\u0012\u00020&\u0018\u00010\u00162\u000e\u00107\u001a\n\u0012\u0004\u0012\u00020&\u0018\u00010\u0002H\u0002J\u0016\u00108\u001a\u0010\u0012\f\u0012\n 9*\u0004\u0018\u00010\u000e0\u000e0\u0016H\u0014J\u0018\u0010:\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00022\u0006\u0010;\u001a\u00020&H\u0002J\u0012\u0010<\u001a\u0002022\b\u0010=\u001a\u0004\u0018\u00010\u000eH\u0014J\u001c\u0010>\u001a\u0002022\b\u0010?\u001a\u0004\u0018\u00010\u000e2\b\u0010@\u001a\u0004\u0018\u00010\u000eH\u0014J\u001c\u0010A\u001a\u0002022\b\u0010?\u001a\u0004\u0018\u00010\u000e2\b\u0010@\u001a\u0004\u0018\u00010\u000eH\u0014J\u001c\u0010B\u001a\u0002022\b\u0010?\u001a\u0004\u0018\u00010\u000e2\b\u0010@\u001a\u0004\u0018\u00010\u000eH\u0014J\u0010\u0010C\u001a\u0004\u0018\u00010&2\u0006\u0010D\u001a\u00020\u0011J\"\u0010E\u001a\u00020\u000e2\u0006\u0010F\u001a\u00020G2\b\u0010=\u001a\u0004\u0018\u00010\u000e2\b\u0010H\u001a\u0004\u0018\u00010\u000eJ\u0012\u0010I\u001a\u00020G2\b\u0010J\u001a\u0004\u0018\u00010\u000eH\u0016J\u0019\u0010K\u001a\u0002022\u0006\u0010L\u001a\u00020GH\u0094@ø\u0001\u0000¢\u0006\u0002\u0010MJ\u0019\u0010N\u001a\u0002022\u0006\u0010L\u001a\u00020GH\u0094@ø\u0001\u0000¢\u0006\u0002\u0010MJ\u0019\u0010O\u001a\u0002022\u0006\u0010L\u001a\u00020GH\u0094@ø\u0001\u0000¢\u0006\u0002\u0010MJ\u0019\u0010P\u001a\u0002022\u0006\u0010L\u001a\u00020GH\u0094@ø\u0001\u0000¢\u0006\u0002\u0010MJ\u0012\u0010Q\u001a\u00020G2\b\u0010R\u001a\u0004\u0018\u00010&H\u0002R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028F¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028F¢\u0006\u0006\u001a\u0004\b\f\u0010\nR\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00028F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\nR\u001e\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u0011@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00040\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00030\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082D¢\u0006\u0002\n\u0000R\u001e\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u0011@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0014R\u001e\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u0011@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0014R\u001e\u0010\u001f\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u0011@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0014R\u001e\u0010!\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u0011@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0014R\u001e\u0010#\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u0011@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0014R\u0014\u0010%\u001a\b\u0012\u0004\u0012\u00020&0\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010'\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u0011@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u0014R\u0019\u0010)\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u00028F¢\u0006\u0006\u001a\u0004\b*\u0010\nR\u0019\u0010+\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u00028F¢\u0006\u0006\u001a\u0004\b,\u0010\nR\u0019\u0010-\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u00028F¢\u0006\u0006\u001a\u0004\b.\u0010\n\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006U"}, d2 = {"Lcom/ido/life/module/home/pressure/detail/vertical/PresureDetailPresenter;", "Lcom/ido/life/module/home/chartdetail/vertical/BaseDetailPresenter;", "", "Lcom/ido/life/bean/PressureBarChartPoint;", "Lcom/ido/life/bean/BaseCharBean;", "Lcom/ido/life/module/home/pressure/detail/vertical/IPresureDetailView;", "iView", "(Lcom/ido/life/module/home/pressure/detail/vertical/IPresureDetailView;)V", "dayChartList", "getDayChartList", "()Ljava/util/List;", "dayDefaultList", "getDayDefaultList", "leftLabelList", "", "getLeftLabelList", "<set-?>", "", "mAvgPressure", "getMAvgPressure", "()I", "mChartList", "", "mDayChartList", "mDefaultBarColor", "mDefaultBarRadius", "", "mHigherRadio", "getMHigherRadio", "mMaxPressure", "getMMaxPressure", "mMediumRadio", "getMMediumRadio", "mMinPressure", "getMMinPressure", "mNormalRadio", "getMNormalRadio", "mPressureDateArea", "Lcom/ido/life/database/model/HealthPressure;", "mRelaxRadio", "getMRelaxRadio", "monthChartList", "getMonthChartList", "weekChartList", "getWeekChartList", "yearChartList", "getYearChartList", "calutePressureAvg", "items", "clearData", "", "combineServerAndLocal", "serverList", "localList", "combineYearData", "list", "getDataDownloadType", "kotlin.jvm.PlatformType", "getDayPresure", "healthPressure", "getDetailByDay", "startDate", "getDetailByMonth", "start", "end", "getDetailByWeek", "getDetailByYear", "getPressureItem", "index", "getPressureTipResId", "week", "", "endDate", "hasData", "date", "readLocalDayData", "showChartAnimator", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readLocalMonthData", "readLocalWeekData", "readLocalYearData", "validPressureData", "bean", "Companion", "PressureItem", "app_release"}, k = 1, mv = {1, 1, 16})
public final class PresureDetailPresenter extends BaseDetailPresenter<List<? extends PressureBarChartPoint>, List<? extends BaseCharBean>, List<? extends BaseCharBean>, List<? extends BaseCharBean>, IPresureDetailView> {
    private static final int PRESSURE_MAX = PressureEnum.HIGH.Max;
    private static final int PRESSURE_MIN = PressureEnum.RELAX.Min;
    private int mAvgPressure;
    private final List<BaseCharBean> mChartList;
    private final List<PressureBarChartPoint> mDayChartList;
    private final int mDefaultBarColor;
    private final float mDefaultBarRadius;
    private int mHigherRadio;
    private int mMaxPressure;
    private int mMediumRadio;
    private int mMinPressure;
    private int mNormalRadio;
    private final List<HealthPressure> mPressureDateArea;
    private int mRelaxRadio;

    /* JADX INFO: renamed from: com.ido.life.module.home.pressure.detail.vertical.PresureDetailPresenter$readLocalMonthData$1, reason: invalid class name */
    /* JADX INFO: compiled from: PresureDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0094@"}, d2 = {"readLocalMonthData", "", "showChartAnimator", "", "continuation", "Lkotlin/coroutines/Continuation;", ""}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.pressure.detail.vertical.PresureDetailPresenter", f = "PresureDetailPresenter.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2}, l = {295, 299, 304}, m = "readLocalMonthData", n = {"this", "showChartAnimator", "dateList", "list", "totalHigherCount", "totalMediumCount", "totalNormalCount", "totalRelaxCount", "size", "pressureDay", "total", "max", "min", "totalCount", "this", "showChartAnimator", "dateList", "list", "totalHigherCount", "totalMediumCount", "totalNormalCount", "totalRelaxCount", "size", "pressureDay", "total", "max", "min", "totalCount", "this", "showChartAnimator", "dateList", "list", "totalHigherCount", "totalMediumCount", "totalNormalCount", "totalRelaxCount"}, s = {"L$0", "Z$0", "L$1", "L$2", "I$0", "I$1", "I$2", "I$3", "I$4", "L$3", "I$5", "I$6", "I$7", "I$8", "L$0", "Z$0", "L$1", "L$2", "I$0", "I$1", "I$2", "I$3", "I$4", "L$3", "I$5", "I$6", "I$7", "I$8", "L$0", "Z$0", "L$1", "L$2", "I$0", "I$1", "I$2", "I$3"})
    static final class AnonymousClass1 extends ContinuationImpl {
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
            return PresureDetailPresenter.this.readLocalMonthData(false, this);
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.pressure.detail.vertical.PresureDetailPresenter$readLocalYearData$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PresureDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0094@"}, d2 = {"readLocalYearData", "", "showChartAnimator", "", "continuation", "Lkotlin/coroutines/Continuation;", ""}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.pressure.detail.vertical.PresureDetailPresenter", f = "PresureDetailPresenter.kt", i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2}, l = {b.h1, 330, b.n1}, m = "readLocalYearData", n = {"this", "showChartAnimator", "list", "size", "pressureDay", "this", "showChartAnimator", "list", "size", "pressureDay", "this", "showChartAnimator", "list"}, s = {"L$0", "Z$0", "L$1", "I$0", "L$2", "L$0", "Z$0", "L$1", "I$0", "L$2", "L$0", "Z$0", "L$1"})
    static final class C03731 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        C03731(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PresureDetailPresenter.this.readLocalYearData(false, this);
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    protected void getDetailByDay(String startDate) {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PresureDetailPresenter(IPresureDetailView iView) {
        super(iView);
        Intrinsics.checkParameterIsNotNull(iView, "iView");
        this.mPressureDateArea = new ArrayList();
        this.mDayChartList = new ArrayList();
        this.mChartList = new ArrayList();
        this.mRelaxRadio = 100;
        this.mDefaultBarColor = Color.parseColor("#66979797");
        this.mDefaultBarRadius = 0.02f;
    }

    public final int getMRelaxRadio() {
        return this.mRelaxRadio;
    }

    public final int getMNormalRadio() {
        return this.mNormalRadio;
    }

    public final int getMMediumRadio() {
        return this.mMediumRadio;
    }

    public final int getMHigherRadio() {
        return this.mHigherRadio;
    }

    public final int getMAvgPressure() {
        return this.mAvgPressure;
    }

    public final int getMMaxPressure() {
        return this.mMaxPressure;
    }

    public final int getMMinPressure() {
        return this.mMinPressure;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    protected void getDetailByWeek(String start, String end) {
        super.getDetailByWeek(start, end);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    protected void getDetailByMonth(String start, String end) {
        super.getDetailByMonth(start, end);
    }

    private final List<HealthPressure> combineServerAndLocal(List<HealthPressure> serverList, List<HealthPressure> localList) {
        if (serverList.isEmpty()) {
            return localList;
        }
        if (localList.isEmpty()) {
            return serverList;
        }
        TreeSet treeSet = new TreeSet();
        int size = localList.size();
        for (int i = 0; i < size; i++) {
            ensureActive();
            String date = localList.get(i).getDate();
            Intrinsics.checkExpressionValueIsNotNull(date, "healthPressure.date");
            treeSet.add(date);
        }
        Iterator<HealthPressure> it = serverList.iterator();
        while (it.hasNext()) {
            HealthPressure next = it.next();
            Iterator it2 = treeSet.iterator();
            while (true) {
                if (it2.hasNext()) {
                    if (TextUtils.equals(next.getDate(), (String) it2.next())) {
                        it.remove();
                        break;
                    }
                }
            }
        }
        if (serverList.size() > 0) {
            localList.addAll(serverList);
        }
        return localList;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    protected void getDetailByYear(String start, String end) {
        super.getDetailByYear(start, end);
    }

    /* JADX WARN: Type inference failed for: r3v0, types: [T, java.util.List] */
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    protected Object readLocalDayData(boolean z, Continuation<? super Unit> continuation) {
        HealthPressure healthPressureDetailByDay = GreenDaoUtil.getHealthPressureDetailByDay(getUserId(), getMStartDate());
        if (healthPressureDetailByDay != null) {
            Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = getDayPresure(healthPressureDetailByDay);
            if (((List) objectRef.element) != null && (!((List) objectRef.element).isEmpty())) {
                this.mDayChartList.addAll((List) objectRef.element);
                this.mMinPressure = Math.max(PRESSURE_MIN, healthPressureDetailByDay.getMinPressure());
                this.mMaxPressure = Math.min(PRESSURE_MAX, healthPressureDetailByDay.getMaxPressure());
                this.mAvgPressure = healthPressureDetailByDay.getAvgPressure();
                Object objWithContext = BuildersKt.withContext(Dispatchers.getMain(), new AnonymousClass2(z, objectRef, null), continuation);
                if (objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    return objWithContext;
                }
            } else {
                Object objWithContext2 = BuildersKt.withContext(Dispatchers.getMain(), new AnonymousClass3(null), continuation);
                if (objWithContext2 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    return objWithContext2;
                }
            }
        } else {
            Object objWithContext3 = BuildersKt.withContext(Dispatchers.getMain(), new AnonymousClass4(null), continuation);
            if (objWithContext3 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                return objWithContext3;
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.pressure.detail.vertical.PresureDetailPresenter$readLocalDayData$2, reason: invalid class name */
    /* JADX INFO: compiled from: PresureDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.pressure.detail.vertical.PresureDetailPresenter$readLocalDayData$2", f = "PresureDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.ObjectRef $barList;
        final /* synthetic */ boolean $showChartAnimator;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(boolean z, Ref.ObjectRef objectRef, Continuation continuation) {
            super(2, continuation);
            this.$showChartAnimator = z;
            this.$barList = objectRef;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            AnonymousClass2 anonymousClass2 = PresureDetailPresenter.this.new AnonymousClass2(this.$showChartAnimator, this.$barList, completion);
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
            PresureDetailPresenter.this.getMView().onLoadSuccessByDay(this.$showChartAnimator, (List) this.$barList.element);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.pressure.detail.vertical.PresureDetailPresenter$readLocalDayData$3, reason: invalid class name */
    /* JADX INFO: compiled from: PresureDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.pressure.detail.vertical.PresureDetailPresenter$readLocalDayData$3", f = "PresureDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        AnonymousClass3(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            AnonymousClass3 anonymousClass3 = PresureDetailPresenter.this.new AnonymousClass3(completion);
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
            PresureDetailPresenter.this.getMView().onDetailLoadFailed();
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.pressure.detail.vertical.PresureDetailPresenter$readLocalDayData$4, reason: invalid class name */
    /* JADX INFO: compiled from: PresureDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.pressure.detail.vertical.PresureDetailPresenter$readLocalDayData$4", f = "PresureDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass4 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        AnonymousClass4(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            AnonymousClass4 anonymousClass4 = PresureDetailPresenter.this.new AnonymousClass4(completion);
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
            PresureDetailPresenter.this.getMView().onDetailLoadFailed();
            return Unit.INSTANCE;
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    protected Object readLocalWeekData(boolean z, Continuation<? super Unit> continuation) {
        Calendar calendar;
        int i;
        List<HealthPressure> listQueryHealthPressureByDateArea = GreenDaoUtil.queryHealthPressureByDateArea(getUserId(), getMStartDate(), getMEndDate());
        List<HealthPressure> list = listQueryHealthPressureByDateArea;
        if (!(list == null || list.isEmpty())) {
            int size = listQueryHealthPressureByDateArea.size();
            this.mPressureDateArea.addAll(list);
            Calendar calendar2 = Calendar.getInstance(Locale.getDefault());
            Intrinsics.checkExpressionValueIsNotNull(calendar2, "calendar");
            RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
            calendar2.setFirstDayOfWeek(runTimeUtil.getWeekStart());
            int i2 = PressureEnum.RELAX.Min;
            int i3 = PressureEnum.HIGH.Max;
            int weekStart = getWeekStart();
            int iMax = i2;
            int iMin = i3;
            int i4 = 0;
            int higherCount = 0;
            int mediumCount = 0;
            int normalCount = 0;
            int relaxCount = 0;
            int avgPressure = 0;
            while (i4 < size) {
                ensureActive();
                HealthPressure pressureDay = listQueryHealthPressureByDateArea.get(i4);
                Intrinsics.checkExpressionValueIsNotNull(pressureDay, "pressureDay");
                List<HealthPressure> list2 = listQueryHealthPressureByDateArea;
                calendar2.setTime(DateUtil.string2Date(pressureDay.getDate(), DateUtil.DATE_FORMAT_YMD));
                calendar2.setFirstDayOfWeek(weekStart);
                int i5 = calendar2.get(7);
                if (weekStart == 1) {
                    calendar = calendar2;
                    i = weekStart;
                    this.mChartList.add(new BaseCharBean(i4, i5, pressureDay.getAvgPressure()));
                } else if (weekStart == 2) {
                    calendar = calendar2;
                    i = weekStart;
                    if (i5 == 1) {
                        this.mChartList.add(new BaseCharBean(i4, 7.0f, pressureDay.getAvgPressure()));
                    } else {
                        this.mChartList.add(new BaseCharBean(i4, i5 - 1, pressureDay.getAvgPressure()));
                    }
                } else if (weekStart != 7) {
                    calendar = calendar2;
                    i = weekStart;
                } else if (i5 == 7) {
                    calendar = calendar2;
                    i = weekStart;
                    this.mChartList.add(new BaseCharBean(i4, 1.0f, pressureDay.getAvgPressure()));
                } else {
                    calendar = calendar2;
                    i = weekStart;
                    this.mChartList.add(new BaseCharBean(i4, i5 + 1, pressureDay.getAvgPressure()));
                }
                higherCount += pressureDay.getHigherCount();
                mediumCount += pressureDay.getMediumCount();
                normalCount += pressureDay.getNormalCount();
                relaxCount += pressureDay.getRelaxCount();
                avgPressure += pressureDay.getAvgPressure();
                iMax = Math.max(iMax, pressureDay.getMaxPressure());
                iMin = Math.min(iMin, pressureDay.getMinPressure());
                i4++;
                listQueryHealthPressureByDateArea = list2;
                calendar2 = calendar;
                weekStart = i;
            }
            int i6 = higherCount + mediumCount + normalCount + relaxCount;
            if (i6 > 0) {
                float f2 = i6;
                this.mHigherRadio = MathKt.roundToInt((higherCount * 100.0f) / f2);
                this.mMediumRadio = MathKt.roundToInt((mediumCount * 100.0f) / f2);
                this.mNormalRadio = MathKt.roundToInt((normalCount * 100.0f) / f2);
                this.mRelaxRadio = ((100 - this.mHigherRadio) - this.mMediumRadio) - this.mNormalRadio;
            } else {
                this.mHigherRadio = 0;
                this.mMediumRadio = 0;
                this.mNormalRadio = 0;
                this.mRelaxRadio = 100;
            }
            this.mAvgPressure = MathKt.roundToInt((avgPressure * 1.0f) / size);
            this.mMaxPressure = Math.min(iMax, PRESSURE_MAX);
            this.mMinPressure = Math.max(PRESSURE_MIN, iMin);
            if (size > 0) {
                List<BaseCharBean> list3 = this.mChartList;
                if (list3.size() > 1) {
                    CollectionsKt.sortWith(list3, new Comparator<T>() { // from class: com.ido.life.module.home.pressure.detail.vertical.PresureDetailPresenter$readLocalWeekData$$inlined$sortBy$1
                        /* JADX WARN: Multi-variable type inference failed */
                        @Override // java.util.Comparator
                        public final int compare(T t, T t2) {
                            BaseCharBean baseCharBean = (BaseCharBean) t;
                            BaseCharBean baseCharBean2 = (BaseCharBean) t2;
                            return ComparisonsKt.compareValues(Float.valueOf((baseCharBean != null ? Float.valueOf(baseCharBean.x) : null).floatValue()), Float.valueOf((baseCharBean2 != null ? Float.valueOf(baseCharBean2.x) : null).floatValue()));
                        }
                    });
                }
                Object objWithContext = BuildersKt.withContext(Dispatchers.getMain(), new C03703(z, null), continuation);
                if (objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    return objWithContext;
                }
            } else {
                Object objWithContext2 = BuildersKt.withContext(Dispatchers.getMain(), new C03714(null), continuation);
                if (objWithContext2 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    return objWithContext2;
                }
            }
        } else {
            Object objWithContext3 = BuildersKt.withContext(Dispatchers.getMain(), new C03725(null), continuation);
            if (objWithContext3 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                return objWithContext3;
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.pressure.detail.vertical.PresureDetailPresenter$readLocalWeekData$3, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PresureDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.pressure.detail.vertical.PresureDetailPresenter$readLocalWeekData$3", f = "PresureDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03703 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ boolean $showChartAnimator;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03703(boolean z, Continuation continuation) {
            super(2, continuation);
            this.$showChartAnimator = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03703 c03703 = PresureDetailPresenter.this.new C03703(this.$showChartAnimator, completion);
            c03703.p$ = (CoroutineScope) obj;
            return c03703;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03703) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            PresureDetailPresenter.this.getMView().onLoadSuccessByWeek(this.$showChartAnimator, PresureDetailPresenter.this.mChartList);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.pressure.detail.vertical.PresureDetailPresenter$readLocalWeekData$4, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PresureDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.pressure.detail.vertical.PresureDetailPresenter$readLocalWeekData$4", f = "PresureDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03714 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        C03714(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03714 c03714 = PresureDetailPresenter.this.new C03714(completion);
            c03714.p$ = (CoroutineScope) obj;
            return c03714;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03714) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            PresureDetailPresenter.this.getMView().onDetailLoadFailed();
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.pressure.detail.vertical.PresureDetailPresenter$readLocalWeekData$5, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PresureDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.pressure.detail.vertical.PresureDetailPresenter$readLocalWeekData$5", f = "PresureDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03725 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        C03725(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03725 c03725 = PresureDetailPresenter.this.new C03725(completion);
            c03725.p$ = (CoroutineScope) obj;
            return c03725;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03725) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            PresureDetailPresenter.this.getMView().onDetailLoadFailed();
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x001a  */
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected java.lang.Object readLocalMonthData(boolean r24, kotlin.coroutines.Continuation<? super kotlin.Unit> r25) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 655
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.home.pressure.detail.vertical.PresureDetailPresenter.readLocalMonthData(boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.pressure.detail.vertical.PresureDetailPresenter$readLocalMonthData$3, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PresureDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.pressure.detail.vertical.PresureDetailPresenter$readLocalMonthData$3", f = "PresureDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03683 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ boolean $showChartAnimator;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03683(boolean z, Continuation continuation) {
            super(2, continuation);
            this.$showChartAnimator = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03683 c03683 = PresureDetailPresenter.this.new C03683(this.$showChartAnimator, completion);
            c03683.p$ = (CoroutineScope) obj;
            return c03683;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03683) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            PresureDetailPresenter.this.getMView().onLoadSuccessByMonth(this.$showChartAnimator, PresureDetailPresenter.this.mChartList);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.pressure.detail.vertical.PresureDetailPresenter$readLocalMonthData$4, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PresureDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.pressure.detail.vertical.PresureDetailPresenter$readLocalMonthData$4", f = "PresureDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03694 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        C03694(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03694 c03694 = PresureDetailPresenter.this.new C03694(completion);
            c03694.p$ = (CoroutineScope) obj;
            return c03694;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03694) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            PresureDetailPresenter.this.getMView().onDetailLoadFailed();
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.pressure.detail.vertical.PresureDetailPresenter$readLocalMonthData$5, reason: invalid class name */
    /* JADX INFO: compiled from: PresureDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.pressure.detail.vertical.PresureDetailPresenter$readLocalMonthData$5", f = "PresureDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass5 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        AnonymousClass5(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            AnonymousClass5 anonymousClass5 = PresureDetailPresenter.this.new AnonymousClass5(completion);
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
            PresureDetailPresenter.this.getMView().onDetailLoadFailed();
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x001a  */
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected java.lang.Object readLocalYearData(boolean r17, kotlin.coroutines.Continuation<? super kotlin.Unit> r18) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 346
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.home.pressure.detail.vertical.PresureDetailPresenter.readLocalYearData(boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.pressure.detail.vertical.PresureDetailPresenter$readLocalYearData$3, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PresureDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.pressure.detail.vertical.PresureDetailPresenter$readLocalYearData$3", f = "PresureDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03743 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ boolean $showChartAnimator;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03743(boolean z, Continuation continuation) {
            super(2, continuation);
            this.$showChartAnimator = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03743 c03743 = PresureDetailPresenter.this.new C03743(this.$showChartAnimator, completion);
            c03743.p$ = (CoroutineScope) obj;
            return c03743;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03743) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            PresureDetailPresenter.this.getMView().onLoadSuccessByYear(this.$showChartAnimator, PresureDetailPresenter.this.mChartList);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.pressure.detail.vertical.PresureDetailPresenter$readLocalYearData$4, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PresureDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.pressure.detail.vertical.PresureDetailPresenter$readLocalYearData$4", f = "PresureDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03754 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        C03754(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03754 c03754 = PresureDetailPresenter.this.new C03754(completion);
            c03754.p$ = (CoroutineScope) obj;
            return c03754;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03754) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            PresureDetailPresenter.this.getMView().onDetailLoadFailed();
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.pressure.detail.vertical.PresureDetailPresenter$readLocalYearData$5, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PresureDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.pressure.detail.vertical.PresureDetailPresenter$readLocalYearData$5", f = "PresureDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03765 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        C03765(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03765 c03765 = PresureDetailPresenter.this.new C03765(completion);
            c03765.p$ = (CoroutineScope) obj;
            return c03765;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03765) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            PresureDetailPresenter.this.getMView().onDetailLoadFailed();
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: compiled from: PresureDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\b\u0082\u0004\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007R\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\t\"\u0004\b\r\u0010\u000bR\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\t\"\u0004\b\u000f\u0010\u000bR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\t\"\u0004\b\u0011\u0010\u000b¨\u0006\u0012"}, d2 = {"Lcom/ido/life/module/home/pressure/detail/vertical/PresureDetailPresenter$PressureItem;", "", "totalPressure", "", "maxPressure", "minPressure", "count", "(Lcom/ido/life/module/home/pressure/detail/vertical/PresureDetailPresenter;IIII)V", "getCount", "()I", "setCount", "(I)V", "getMaxPressure", "setMaxPressure", "getMinPressure", "setMinPressure", "getTotalPressure", "setTotalPressure", "app_release"}, k = 1, mv = {1, 1, 16})
    private final class PressureItem {
        private int count;
        private int maxPressure;
        private int minPressure;
        private int totalPressure;

        public PressureItem(int i, int i2, int i3, int i4) {
            this.totalPressure = i;
            this.maxPressure = i2;
            this.minPressure = i3;
            this.count = i4;
        }

        public final int getCount() {
            return this.count;
        }

        public final int getMaxPressure() {
            return this.maxPressure;
        }

        public final int getMinPressure() {
            return this.minPressure;
        }

        public final int getTotalPressure() {
            return this.totalPressure;
        }

        public final void setCount(int i) {
            this.count = i;
        }

        public final void setMaxPressure(int i) {
            this.maxPressure = i;
        }

        public final void setMinPressure(int i) {
            this.minPressure = i;
        }

        public final void setTotalPressure(int i) {
            this.totalPressure = i;
        }
    }

    private final List<HealthPressure> combineYearData(List<? extends HealthPressure> list) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        StringBuilder sb;
        String date;
        int length;
        int i6;
        int i7;
        List<? extends HealthPressure> list2 = list;
        List<? extends HealthPressure> list3 = list2;
        int i8 = 0;
        if (list3 == null || list3.isEmpty()) {
            return null;
        }
        ensureActive();
        ArrayList arrayList = new ArrayList();
        this.mHigherRadio = 0;
        this.mMediumRadio = 0;
        this.mNormalRadio = 0;
        this.mRelaxRadio = 0;
        this.mAvgPressure = 0;
        this.mMaxPressure = PressureEnum.RELAX.Min;
        this.mMinPressure = PressureEnum.HIGH.Max;
        HashMap map = new HashMap();
        int size = list.size();
        int normalCount = 0;
        int mediumCount = 0;
        int higherCount = 0;
        int avgPressure = 0;
        int i9 = 0;
        int i10 = 0;
        int relaxCount = 0;
        while (i9 < size) {
            ensureActive();
            HealthPressure healthPressure = list2.get(i9);
            if (TextUtils.isEmpty(healthPressure.getDate()) || healthPressure.getAvgPressure() <= 0) {
                i = normalCount;
                i2 = mediumCount;
                i3 = higherCount;
                i4 = size;
                i5 = avgPressure;
            } else {
                try {
                    sb = new StringBuilder();
                    date = healthPressure.getDate();
                    Intrinsics.checkExpressionValueIsNotNull(date, "item.date");
                    length = healthPressure.getDate().length() - 2;
                } catch (Exception e2) {
                    e = e2;
                    i = normalCount;
                    i2 = mediumCount;
                    i3 = higherCount;
                    i4 = size;
                    i5 = avgPressure;
                }
                if (date == null) {
                    i = normalCount;
                    i2 = mediumCount;
                    i3 = higherCount;
                    i4 = size;
                    i5 = avgPressure;
                    try {
                        throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                    } catch (Exception e3) {
                        e = e3;
                    }
                } else {
                    String strSubstring = date.substring(i8, length);
                    Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    sb.append(strSubstring);
                    sb.append("01");
                    String string = sb.toString();
                    if (map.containsKey(string)) {
                        PressureItem pressureItem = (PressureItem) map.get(string);
                        if (pressureItem == null) {
                            Intrinsics.throwNpe();
                        }
                        pressureItem.setTotalPressure(pressureItem.getTotalPressure() + healthPressure.getAvgPressure());
                        pressureItem.setMaxPressure(Math.max(pressureItem.getMaxPressure(), healthPressure.getMaxPressure()));
                        pressureItem.setMinPressure(Math.min(pressureItem.getMinPressure(), healthPressure.getMinPressure()));
                        pressureItem.setCount(pressureItem.getCount() + 1);
                        i6 = normalCount;
                        i7 = mediumCount;
                        i3 = higherCount;
                        i4 = size;
                        i5 = avgPressure;
                    } else {
                        i6 = normalCount;
                        i7 = mediumCount;
                        i3 = higherCount;
                        i4 = size;
                        i5 = avgPressure;
                        try {
                            map.put(string, new PressureItem(healthPressure.getAvgPressure(), healthPressure.getMaxPressure(), healthPressure.getMinPressure(), 1));
                        } catch (Exception e4) {
                            e = e4;
                            i = i6;
                            i2 = i7;
                        }
                    }
                    this.mMaxPressure = Math.max(this.mMaxPressure, healthPressure.getMaxPressure());
                    this.mMinPressure = Math.min(this.mMinPressure, healthPressure.getMinPressure());
                    higherCount = i3 + healthPressure.getHigherCount();
                    try {
                        mediumCount = i7 + healthPressure.getMediumCount();
                        try {
                            normalCount = i6 + healthPressure.getNormalCount();
                        } catch (Exception e5) {
                            e = e5;
                            i = i6;
                        }
                    } catch (Exception e6) {
                        e = e6;
                        i = i6;
                        i2 = i7;
                    }
                    try {
                        relaxCount += healthPressure.getRelaxCount();
                        avgPressure = i5 + healthPressure.getAvgPressure();
                        i10++;
                    } catch (Exception e7) {
                        e = e7;
                        i = normalCount;
                        i2 = mediumCount;
                        i3 = higherCount;
                        e.printStackTrace();
                        normalCount = i;
                        higherCount = i3;
                        mediumCount = i2;
                        avgPressure = i5;
                    }
                    i9++;
                    list2 = list;
                    size = i4;
                    i8 = 0;
                }
                e.printStackTrace();
            }
            normalCount = i;
            higherCount = i3;
            mediumCount = i2;
            avgPressure = i5;
            i9++;
            list2 = list;
            size = i4;
            i8 = 0;
        }
        int i11 = normalCount;
        int i12 = mediumCount;
        int i13 = higherCount;
        int i14 = avgPressure;
        if (!map.isEmpty()) {
            for (String str : map.keySet()) {
                ensureActive();
                PressureItem pressureItem2 = (PressureItem) map.get(str);
                HealthPressure healthPressure2 = new HealthPressure();
                if (pressureItem2 == null) {
                    Intrinsics.throwNpe();
                }
                healthPressure2.setAvgPressure(MathKt.roundToInt((pressureItem2.getTotalPressure() * 1.0f) / pressureItem2.getCount()));
                healthPressure2.setDate(str);
                healthPressure2.setMaxPressure(pressureItem2.getMaxPressure());
                healthPressure2.setMinPressure(pressureItem2.getMinPressure());
                arrayList.add(healthPressure2);
            }
        }
        if (i10 > 0) {
            this.mAvgPressure = MathKt.roundToInt((i14 * 1.0f) / i10);
            int i15 = i13 + i12 + i11 + relaxCount;
            if (i15 > 0) {
                float f2 = i15;
                this.mHigherRadio = MathKt.roundToInt((i13 * 100.0f) / f2);
                this.mMediumRadio = MathKt.roundToInt((i12 * 100.0f) / f2);
                this.mNormalRadio = MathKt.roundToInt((i11 * 100.0f) / f2);
                this.mRelaxRadio = ((100 - this.mHigherRadio) - this.mMediumRadio) - this.mNormalRadio;
            }
        }
        return arrayList;
    }

    public final List<String> getLeftLabelList() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
        arrayList.add("29");
        arrayList.add("59");
        arrayList.add("79");
        arrayList.add("99");
        return arrayList;
    }

    private final List<PressureBarChartPoint> getDayPresure(HealthPressure healthPressure) {
        int[] iArr;
        ArrayList arrayList = null;
        if (healthPressure != null && !TextUtils.isEmpty(healthPressure.getItems())) {
            ensureActive();
            List listAnalysisJsonArrayToList = GsonUtil.analysisJsonArrayToList(healthPressure.getItems(), int[][].class);
            HashMap map = new HashMap();
            arrayList = (List) null;
            if (listAnalysisJsonArrayToList != null && listAnalysisJsonArrayToList.size() > 0) {
                int size = listAnalysisJsonArrayToList.size();
                int i = 0;
                int i2 = 0;
                int i3 = 0;
                int i4 = 0;
                int i5 = 0;
                int i6 = 0;
                while (true) {
                    if (i2 >= size) {
                        break;
                    }
                    ensureActive();
                    int[] iArr2 = (int[]) listAnalysisJsonArrayToList.get(i2);
                    if (iArr2 == null) {
                        Intrinsics.throwNpe();
                    }
                    i3 += iArr2[i];
                    if (i3 / 60 >= 24) {
                        i3 -= iArr2[i];
                        break;
                    }
                    if (iArr2.length == 2) {
                        if (i3 > i5 * 30) {
                            int i7 = ((i3 - iArr2[i]) / 30) + ((i3 - iArr2[i]) % 30 == 0 ? i : 1);
                            if (i4 > 0) {
                                iArr = iArr2;
                                map.put(Integer.valueOf(i7), new PressureBarChartPoint(i3, i7, i4, PressureEnum.getPressureEnumByValue(i4).Color, i6, i6));
                                i4 = 0;
                            } else {
                                iArr = iArr2;
                            }
                            i5 = (i3 / 30) + 1;
                            i6 = 0;
                        } else {
                            iArr = iArr2;
                        }
                        if (i4 < iArr[1]) {
                            i4 = iArr[1];
                            i6 = i3;
                        }
                    }
                    i2++;
                    i = 0;
                }
                if (i3 / 60 < 24 && i4 > 0) {
                    map.put(Integer.valueOf(i5), new PressureBarChartPoint(Math.min(i3 + 30, 1440), i5, i4, PressureEnum.getPressureEnumByValue(i4).Color, i6, i6));
                }
                if (!map.isEmpty()) {
                    arrayList = new ArrayList();
                    float f2 = PressureEnum.HIGH.Max * this.mDefaultBarRadius;
                    for (int i8 = 1; i8 < 49; i8++) {
                        ensureActive();
                        if (map.containsKey(Integer.valueOf(i8))) {
                            PressureBarChartPoint pressureBarChartPoint = (PressureBarChartPoint) map.get(Integer.valueOf(i8));
                            if (pressureBarChartPoint == null) {
                                Intrinsics.throwNpe();
                            }
                            arrayList.add(pressureBarChartPoint);
                            if (pressureBarChartPoint.y < f2) {
                                pressureBarChartPoint.setActualValue(pressureBarChartPoint.y);
                                pressureBarChartPoint.y = f2;
                            }
                        } else {
                            int i9 = i8 * 30;
                            arrayList.add(new PressureBarChartPoint(i9, i8, f2, 0.0f, this.mDefaultBarColor, i9, i9));
                        }
                    }
                }
                this.mHigherRadio = healthPressure.getHigherRatio();
                this.mMediumRadio = healthPressure.getMediumRatio();
                this.mNormalRadio = healthPressure.getNormalRatio();
                this.mRelaxRadio = ((100 - this.mHigherRadio) - this.mMediumRadio) - this.mNormalRadio;
            }
        }
        return arrayList;
    }

    private final int calutePressureAvg(String items) {
        if (TextUtils.isEmpty(items)) {
            return 0;
        }
        List listAnalysisJsonArrayToList = GsonUtil.analysisJsonArrayToList(items, int[][].class);
        List list = listAnalysisJsonArrayToList;
        if (list == null || list.isEmpty()) {
            return 0;
        }
        int size = listAnalysisJsonArrayToList.size();
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            int[] iArr = (int[]) listAnalysisJsonArrayToList.get(i3);
            if (iArr != null && iArr.length == 2) {
                i++;
                i2 += iArr[1];
            }
        }
        return i > 0 ? MathKt.roundToInt((i2 * 1.0f) / i) : i2;
    }

    private final boolean validPressureData(HealthPressure bean) {
        return (bean == null || TextUtils.isEmpty(bean.getItems()) || TextUtils.isEmpty(bean.getDate()) || bean.getMaxPressure() < bean.getMinPressure()) ? false : true;
    }

    public final String getPressureTipResId(boolean week, String startDate, String endDate) {
        int iMax = Math.max(this.mHigherRadio, Math.max(this.mMediumRadio, Math.max(this.mNormalRadio, this.mRelaxRadio)));
        if (this.mHigherRadio == iMax) {
            if (week) {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String languageText = LanguageUtil.getLanguageText(R.string.pressure_health_tip_higher_week);
                Intrinsics.checkExpressionValueIsNotNull(languageText, "LanguageUtil.getLanguage…e_health_tip_higher_week)");
                Object[] objArr = {startDate, endDate, LanguageUtil.getLanguageText(R.string.pressure_high_state_desc)};
                String str = String.format(languageText, Arrays.copyOf(objArr, objArr.length));
                Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(format, *args)");
                return str;
            }
            StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
            String languageText2 = LanguageUtil.getLanguageText(R.string.pressure_health_tip_higher_month);
            Intrinsics.checkExpressionValueIsNotNull(languageText2, "LanguageUtil.getLanguage…_health_tip_higher_month)");
            Object[] objArr2 = {startDate, endDate, LanguageUtil.getLanguageText(R.string.pressure_high_state_desc)};
            String str2 = String.format(languageText2, Arrays.copyOf(objArr2, objArr2.length));
            Intrinsics.checkNotNullExpressionValue(str2, "java.lang.String.format(format, *args)");
            return str2;
        }
        if (this.mMediumRadio == iMax) {
            if (week) {
                StringCompanionObject stringCompanionObject3 = StringCompanionObject.INSTANCE;
                String languageText3 = LanguageUtil.getLanguageText(R.string.pressure_health_tip_medium_week);
                Intrinsics.checkExpressionValueIsNotNull(languageText3, "LanguageUtil.getLanguage…e_health_tip_medium_week)");
                Object[] objArr3 = {startDate, endDate, LanguageUtil.getLanguageText(R.string.pressure_medium_state_desc)};
                String str3 = String.format(languageText3, Arrays.copyOf(objArr3, objArr3.length));
                Intrinsics.checkNotNullExpressionValue(str3, "java.lang.String.format(format, *args)");
                return str3;
            }
            StringCompanionObject stringCompanionObject4 = StringCompanionObject.INSTANCE;
            String languageText4 = LanguageUtil.getLanguageText(R.string.pressure_health_tip_medium_month);
            Intrinsics.checkExpressionValueIsNotNull(languageText4, "LanguageUtil.getLanguage…_health_tip_medium_month)");
            Object[] objArr4 = {startDate, endDate, LanguageUtil.getLanguageText(R.string.pressure_medium_state_desc)};
            String str4 = String.format(languageText4, Arrays.copyOf(objArr4, objArr4.length));
            Intrinsics.checkNotNullExpressionValue(str4, "java.lang.String.format(format, *args)");
            return str4;
        }
        if (this.mNormalRadio == iMax) {
            if (week) {
                StringCompanionObject stringCompanionObject5 = StringCompanionObject.INSTANCE;
                String languageText5 = LanguageUtil.getLanguageText(R.string.pressure_health_tip_normal_week);
                Intrinsics.checkExpressionValueIsNotNull(languageText5, "LanguageUtil.getLanguage…e_health_tip_normal_week)");
                Object[] objArr5 = {startDate, endDate, LanguageUtil.getLanguageText(R.string.pressure_normal_state_desc)};
                String str5 = String.format(languageText5, Arrays.copyOf(objArr5, objArr5.length));
                Intrinsics.checkNotNullExpressionValue(str5, "java.lang.String.format(format, *args)");
                return str5;
            }
            StringCompanionObject stringCompanionObject6 = StringCompanionObject.INSTANCE;
            String languageText6 = LanguageUtil.getLanguageText(R.string.pressure_health_tip_normal_month);
            Intrinsics.checkExpressionValueIsNotNull(languageText6, "LanguageUtil.getLanguage…_health_tip_normal_month)");
            Object[] objArr6 = {startDate, endDate, LanguageUtil.getLanguageText(R.string.pressure_normal_state_desc)};
            String str6 = String.format(languageText6, Arrays.copyOf(objArr6, objArr6.length));
            Intrinsics.checkNotNullExpressionValue(str6, "java.lang.String.format(format, *args)");
            return str6;
        }
        if (week) {
            StringCompanionObject stringCompanionObject7 = StringCompanionObject.INSTANCE;
            String languageText7 = LanguageUtil.getLanguageText(R.string.pressure_health_tip_relax_week);
            Intrinsics.checkExpressionValueIsNotNull(languageText7, "LanguageUtil.getLanguage…re_health_tip_relax_week)");
            Object[] objArr7 = {startDate, endDate, LanguageUtil.getLanguageText(R.string.pressure_relax_state_desc)};
            String str7 = String.format(languageText7, Arrays.copyOf(objArr7, objArr7.length));
            Intrinsics.checkNotNullExpressionValue(str7, "java.lang.String.format(format, *args)");
            return str7;
        }
        StringCompanionObject stringCompanionObject8 = StringCompanionObject.INSTANCE;
        String languageText8 = LanguageUtil.getLanguageText(R.string.pressure_health_tip_relax_month);
        Intrinsics.checkExpressionValueIsNotNull(languageText8, "LanguageUtil.getLanguage…e_health_tip_relax_month)");
        Object[] objArr8 = {startDate, endDate, LanguageUtil.getLanguageText(R.string.pressure_relax_state_desc)};
        String str8 = String.format(languageText8, Arrays.copyOf(objArr8, objArr8.length));
        Intrinsics.checkNotNullExpressionValue(str8, "java.lang.String.format(format, *args)");
        return str8;
    }

    public final HealthPressure getPressureItem(int index) {
        if (this.mPressureDateArea.size() <= index) {
            return null;
        }
        return this.mPressureDateArea.get(index);
    }

    public final List<PressureBarChartPoint> getDayDefaultList() {
        ArrayList arrayList = new ArrayList();
        float f2 = PressureEnum.HIGH.Max * this.mDefaultBarRadius;
        for (int i = 1; i < 49; i++) {
            int i2 = i * 30;
            arrayList.add(new PressureBarChartPoint(i, i, f2, 0.0f, this.mDefaultBarColor, i2, i2));
        }
        return arrayList;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    protected void clearData() {
        this.mPressureDateArea.clear();
        this.mDayChartList.clear();
        this.mChartList.clear();
        this.mMaxPressure = 0;
        this.mMinPressure = 0;
        this.mAvgPressure = 0;
        this.mHigherRadio = 0;
        this.mMediumRadio = this.mHigherRadio;
        this.mNormalRadio = this.mMediumRadio;
        this.mRelaxRadio = this.mNormalRadio;
    }

    public final List<PressureBarChartPoint> getDayChartList() {
        return this.mDayChartList;
    }

    public final List<BaseCharBean> getWeekChartList() {
        return this.mChartList;
    }

    public final List<BaseCharBean> getMonthChartList() {
        return this.mChartList;
    }

    public final List<BaseCharBean> getYearChartList() {
        return this.mChartList;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    protected List<String> getDataDownloadType() {
        return CollectionsKt.mutableListOf(HealthPressure.class.getSimpleName());
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    public boolean hasData(String date) {
        if (TextUtils.isEmpty(date)) {
            return false;
        }
        return GreenDaoUtil.hasPressure(getUserId(), date);
    }
}