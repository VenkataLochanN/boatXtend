package com.ido.life.module.home.rate.vertical;

import android.text.TextUtils;
import com.ido.life.bean.BaseCharBean;
import com.ido.life.bean.CubicChartBean;
import com.ido.life.bean.FloatBarPoint;
import com.ido.life.database.LocalHealthDataManager;
import com.ido.life.database.model.ServerHeartRateDayData;
import com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter;
import com.ido.life.util.GreenDaoUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
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
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.math.MathKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: compiled from: RateDetailPresenter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u001b\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u000e\b\u0016\u0018\u0000 T28\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0002\u0012\u0004\u0012\u00020\u00050\u0001:\u0002TUB\r\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\u001e\u00100\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u00112\f\u00101\u001a\b\u0012\u0004\u0012\u00020\f0\u0002H\u0002J\u0006\u00102\u001a\u00020\u001aJ\u0006\u00103\u001a\u00020\u001aJ\u001c\u00104\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\f\u00105\u001a\b\u0012\u0004\u0012\u0002060\u0002H\u0002J\b\u00107\u001a\u000208H\u0014J&\u00109\u001a\n\u0012\u0004\u0012\u000206\u0018\u00010\u00022\u000e\u0010:\u001a\n\u0012\u0004\u0012\u000206\u0018\u00010\u00022\u0006\u0010;\u001a\u00020\u001aJ\u0016\u0010<\u001a\u0010\u0012\f\u0012\n =*\u0004\u0018\u00010\u00120\u00120\u0011H\u0014J\u0012\u0010>\u001a\u0002082\b\u0010?\u001a\u0004\u0018\u00010\u0012H\u0014J\u001c\u0010@\u001a\u0002082\b\u0010A\u001a\u0004\u0018\u00010\u00122\b\u0010B\u001a\u0004\u0018\u00010\u0012H\u0014J\u001c\u0010C\u001a\u0002082\b\u0010A\u001a\u0004\u0018\u00010\u00122\b\u0010B\u001a\u0004\u0018\u00010\u0012H\u0014J\u001c\u0010D\u001a\u0002082\b\u0010A\u001a\u0004\u0018\u00010\u00122\b\u0010B\u001a\u0004\u0018\u00010\u0012H\u0014J\u0010\u0010E\u001a\u0004\u0018\u00010\f2\u0006\u0010F\u001a\u00020\u001aJ\u0012\u0010G\u001a\u00020H2\b\u0010I\u001a\u0004\u0018\u00010\u0012H\u0016J\u0012\u0010J\u001a\u00020H2\b\u0010K\u001a\u0004\u0018\u00010\fH\u0002J\u0019\u0010L\u001a\u0002082\u0006\u0010M\u001a\u00020HH\u0094@ø\u0001\u0000¢\u0006\u0002\u0010NJ\u0019\u0010O\u001a\u0002082\u0006\u0010M\u001a\u00020HH\u0094@ø\u0001\u0000¢\u0006\u0002\u0010NJ\u0019\u0010P\u001a\u0002082\u0006\u0010M\u001a\u00020HH\u0094@ø\u0001\u0000¢\u0006\u0002\u0010NJ\u0019\u0010Q\u001a\u0002082\u0006\u0010M\u001a\u00020HH\u0094@ø\u0001\u0000¢\u0006\u0002\u0010NJ\u000e\u0010R\u001a\u0002082\u0006\u0010S\u001a\u00020\u001aR\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028F¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\"\u0010\r\u001a\u0004\u0018\u00010\f2\b\u0010\u000b\u001a\u0004\u0018\u00010\f@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\nR\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\nR\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00040\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00030\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\f0\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u0019\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00028F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\nR\u0011\u0010\u001e\u001a\u00020\u00128F¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u0011\u0010!\u001a\u00020\u001a8F¢\u0006\u0006\u001a\u0004\b\"\u0010#R\u0011\u0010$\u001a\u00020\u001a8F¢\u0006\u0006\u001a\u0004\b%\u0010#R\u0011\u0010&\u001a\u00020\u00128F¢\u0006\u0006\u001a\u0004\b'\u0010 R\u0011\u0010(\u001a\u00020\u00128F¢\u0006\u0006\u001a\u0004\b)\u0010 R\u0011\u0010*\u001a\u00020\u00128F¢\u0006\u0006\u001a\u0004\b+\u0010 R\u0019\u0010,\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00028F¢\u0006\u0006\u001a\u0004\b-\u0010\nR\u0019\u0010.\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00028F¢\u0006\u0006\u001a\u0004\b/\u0010\n\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006V"}, d2 = {"Lcom/ido/life/module/home/rate/vertical/RateDetailPresenter;", "Lcom/ido/life/module/home/chartdetail/vertical/BaseDetailPresenter;", "", "Lcom/ido/life/bean/CubicChartBean;", "Lcom/ido/life/bean/BaseCharBean;", "Lcom/ido/life/module/home/rate/vertical/IRateDetailView;", "iView", "(Lcom/ido/life/module/home/rate/vertical/IRateDetailView;)V", "dayChartList", "getDayChartList", "()Ljava/util/List;", "<set-?>", "Lcom/ido/life/database/model/ServerHeartRateDayData;", "dayHeartRate", "getDayHeartRate", "()Lcom/ido/life/database/model/ServerHeartRateDayData;", "defaultLeftYLabelList", "", "", "getDefaultLeftYLabelList", "leftYLabelList", "getLeftYLabelList", "mChartList", "mDayChartList", "mHeartRateList", "mRateMax", "", "mRateType", "monthChartList", "getMonthChartList", "rateArea", "getRateArea", "()Ljava/lang/String;", "rateMaxValue", "getRateMaxValue", "()I", "rateMinValue", "getRateMinValue", "showAreaRate", "getShowAreaRate", "showSilentRate", "getShowSilentRate", "silentRate", "getSilentRate", "weekChartList", "getWeekChartList", "yearChartList", "getYearChartList", "avgYearHeartDate", "list", "caluteAvgHeartRate", "caluteAvgSilentHeartRate", "caluteDayHeartRate", "dataList", "", "clearData", "", "convertHeartRate", "originalItems", "second", "getDataDownloadType", "kotlin.jvm.PlatformType", "getDetailByDay", "startDate", "getDetailByMonth", "start", "end", "getDetailByWeek", "getDetailByYear", "getItemHeart", "index", "hasData", "", "date", "isvalidHeartRate", "heartRate", "readLocalDayData", "showChartAnimator", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readLocalMonthData", "readLocalWeekData", "readLocalYearData", "setRateType", "rateType", "Companion", "YearHeartRateItem", "app_release"}, k = 1, mv = {1, 1, 16})
public class RateDetailPresenter extends BaseDetailPresenter<List<? extends CubicChartBean>, List<? extends BaseCharBean>, List<? extends BaseCharBean>, List<? extends BaseCharBean>, IRateDetailView> {
    private static final String TAG = RateDetailPresenter.class.getSimpleName();
    private ServerHeartRateDayData dayHeartRate;
    private final List<String> defaultLeftYLabelList;
    private final List<String> leftYLabelList;
    private final List<BaseCharBean> mChartList;
    private final List<CubicChartBean> mDayChartList;
    private List<ServerHeartRateDayData> mHeartRateList;
    private int mRateMax;
    private int mRateType;

    /* JADX INFO: renamed from: com.ido.life.module.home.rate.vertical.RateDetailPresenter$readLocalDayData$1, reason: invalid class name */
    /* JADX INFO: compiled from: RateDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0094@"}, d2 = {"readLocalDayData", "", "showChartAnimator", "", "continuation", "Lkotlin/coroutines/Continuation;", ""}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.rate.vertical.RateDetailPresenter", f = "RateDetailPresenter.kt", i = {0, 0, 0, 1, 1, 1}, l = {217, 223}, m = "readLocalDayData$suspendImpl", n = {"this", "showChartAnimator", "dayData", "this", "showChartAnimator", "dayData"}, s = {"L$0", "Z$0", "L$1", "L$0", "Z$0", "L$1"})
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
            return RateDetailPresenter.readLocalDayData$suspendImpl(RateDetailPresenter.this, false, this);
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.rate.vertical.RateDetailPresenter$readLocalMonthData$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RateDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0094@"}, d2 = {"readLocalMonthData", "", "showChartAnimator", "", "continuation", "Lkotlin/coroutines/Continuation;", ""}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.rate.vertical.RateDetailPresenter", f = "RateDetailPresenter.kt", i = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2}, l = {498, 502, 507}, m = "readLocalMonthData$suspendImpl", n = {"this", "showChartAnimator", "list", "dateList", "item", "size", "this", "showChartAnimator", "list", "dateList", "item", "size", "this", "showChartAnimator", "list", "dateList"}, s = {"L$0", "Z$0", "L$1", "L$2", "L$3", "I$0", "L$0", "Z$0", "L$1", "L$2", "L$3", "I$0", "L$0", "Z$0", "L$1", "L$2"})
    static final class C03771 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        C03771(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return RateDetailPresenter.readLocalMonthData$suspendImpl(RateDetailPresenter.this, false, this);
        }
    }

    public final int getRateMaxValue() {
        return 220;
    }

    public final int getRateMinValue() {
        return 20;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    protected Object readLocalDayData(boolean z, Continuation<? super Unit> continuation) {
        return readLocalDayData$suspendImpl(this, z, continuation);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    protected Object readLocalMonthData(boolean z, Continuation<? super Unit> continuation) {
        return readLocalMonthData$suspendImpl(this, z, continuation);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    protected Object readLocalWeekData(boolean z, Continuation<? super Unit> continuation) {
        return readLocalWeekData$suspendImpl(this, z, continuation);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    protected Object readLocalYearData(boolean z, Continuation<? super Unit> continuation) {
        return readLocalYearData$suspendImpl(this, z, continuation);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RateDetailPresenter(IRateDetailView iView) {
        super(iView);
        Intrinsics.checkParameterIsNotNull(iView, "iView");
        this.mDayChartList = new ArrayList();
        this.mChartList = new ArrayList();
        this.mHeartRateList = new ArrayList();
        this.defaultLeftYLabelList = CollectionsKt.mutableListOf("20", "70", "120", "170");
        this.leftYLabelList = CollectionsKt.mutableListOf("20", "70", "120", "170", "220");
    }

    public final ServerHeartRateDayData getDayHeartRate() {
        return this.dayHeartRate;
    }

    public final void setRateType(int rateType) {
        this.mRateType = rateType;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    protected void getDetailByDay(String startDate) {
        super.getDetailByDay(startDate);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    protected void getDetailByWeek(String start, String end) {
        super.getDetailByWeek(start, end);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    protected void getDetailByMonth(String start, String end) {
        super.getDetailByMonth(start, end);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    protected void getDetailByYear(String start, String end) {
        super.getDetailByYear(start, end);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x017b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static /* synthetic */ java.lang.Object readLocalDayData$suspendImpl(com.ido.life.module.home.rate.vertical.RateDetailPresenter r9, boolean r10, kotlin.coroutines.Continuation r11) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 410
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.home.rate.vertical.RateDetailPresenter.readLocalDayData$suspendImpl(com.ido.life.module.home.rate.vertical.RateDetailPresenter, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.rate.vertical.RateDetailPresenter$readLocalDayData$4, reason: invalid class name */
    /* JADX INFO: compiled from: RateDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.rate.vertical.RateDetailPresenter$readLocalDayData$4", f = "RateDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
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
            AnonymousClass4 anonymousClass4 = RateDetailPresenter.this.new AnonymousClass4(this.$showChartAnimator, completion);
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
            if (!RateDetailPresenter.this.mDayChartList.isEmpty()) {
                RateDetailPresenter.this.getMView().onLoadSuccessByDay(this.$showChartAnimator, RateDetailPresenter.this.mDayChartList);
            } else {
                RateDetailPresenter.this.getMView().onDetailLoadFailed();
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.rate.vertical.RateDetailPresenter$readLocalDayData$5, reason: invalid class name */
    /* JADX INFO: compiled from: RateDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.rate.vertical.RateDetailPresenter$readLocalDayData$5", f = "RateDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass5 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        AnonymousClass5(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            AnonymousClass5 anonymousClass5 = RateDetailPresenter.this.new AnonymousClass5(completion);
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
            RateDetailPresenter.this.getMView().onDetailLoadFailed();
            return Unit.INSTANCE;
        }
    }

    private final List<CubicChartBean> caluteDayHeartRate(List<int[]> dataList) {
        ensureActive();
        ArrayList arrayList = new ArrayList();
        if (dataList.isEmpty()) {
            return arrayList;
        }
        int size = dataList.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            ensureActive();
            int[] iArr = dataList.get(i2);
            if (iArr.length == 2) {
                if (iArr[0] > 0) {
                    if (i >= 6 && (!arrayList.isEmpty())) {
                        arrayList.add(new CubicChartBean(-1, 0.0f, 0.0f));
                    }
                    arrayList.add(new CubicChartBean(-1, (iArr[1] / 300) + 1.0f, iArr[0], iArr[1]));
                    i = 0;
                } else {
                    i++;
                }
            }
        }
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:69:0x0278  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static /* synthetic */ java.lang.Object readLocalWeekData$suspendImpl(com.ido.life.module.home.rate.vertical.RateDetailPresenter r19, boolean r20, kotlin.coroutines.Continuation r21) {
        /*
            Method dump skipped, instruction units count: 660
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.home.rate.vertical.RateDetailPresenter.readLocalWeekData$suspendImpl(com.ido.life.module.home.rate.vertical.RateDetailPresenter, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.rate.vertical.RateDetailPresenter$readLocalWeekData$3, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RateDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.rate.vertical.RateDetailPresenter$readLocalWeekData$3", f = "RateDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03803 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ boolean $showChartAnimator;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03803(boolean z, Continuation continuation) {
            super(2, continuation);
            this.$showChartAnimator = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03803 c03803 = RateDetailPresenter.this.new C03803(this.$showChartAnimator, completion);
            c03803.p$ = (CoroutineScope) obj;
            return c03803;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03803) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            RateDetailPresenter.this.getMView().onLoadSuccessByWeek(this.$showChartAnimator, RateDetailPresenter.this.mChartList);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.rate.vertical.RateDetailPresenter$readLocalWeekData$4, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RateDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.rate.vertical.RateDetailPresenter$readLocalWeekData$4", f = "RateDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03814 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        C03814(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03814 c03814 = RateDetailPresenter.this.new C03814(completion);
            c03814.p$ = (CoroutineScope) obj;
            return c03814;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03814) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            RateDetailPresenter.this.getMView().onDetailLoadFailed();
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.rate.vertical.RateDetailPresenter$readLocalWeekData$5, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RateDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.rate.vertical.RateDetailPresenter$readLocalWeekData$5", f = "RateDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03825 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        C03825(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03825 c03825 = RateDetailPresenter.this.new C03825(completion);
            c03825.p$ = (CoroutineScope) obj;
            return c03825;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03825) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            RateDetailPresenter.this.getMView().onDetailLoadFailed();
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x001a  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x024d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static /* synthetic */ java.lang.Object readLocalMonthData$suspendImpl(com.ido.life.module.home.rate.vertical.RateDetailPresenter r27, boolean r28, kotlin.coroutines.Continuation r29) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 623
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.home.rate.vertical.RateDetailPresenter.readLocalMonthData$suspendImpl(com.ido.life.module.home.rate.vertical.RateDetailPresenter, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.rate.vertical.RateDetailPresenter$readLocalMonthData$3, reason: invalid class name */
    /* JADX INFO: compiled from: RateDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.rate.vertical.RateDetailPresenter$readLocalMonthData$3", f = "RateDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
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
            AnonymousClass3 anonymousClass3 = RateDetailPresenter.this.new AnonymousClass3(this.$showChartAnimator, completion);
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
            RateDetailPresenter.this.getMView().onLoadSuccessByMonth(this.$showChartAnimator, RateDetailPresenter.this.mChartList);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.rate.vertical.RateDetailPresenter$readLocalMonthData$4, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RateDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.rate.vertical.RateDetailPresenter$readLocalMonthData$4", f = "RateDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03784 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        C03784(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03784 c03784 = RateDetailPresenter.this.new C03784(completion);
            c03784.p$ = (CoroutineScope) obj;
            return c03784;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03784) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            RateDetailPresenter.this.getMView().onDetailLoadFailed();
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.rate.vertical.RateDetailPresenter$readLocalMonthData$5, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RateDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.rate.vertical.RateDetailPresenter$readLocalMonthData$5", f = "RateDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03795 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        C03795(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03795 c03795 = RateDetailPresenter.this.new C03795(completion);
            c03795.p$ = (CoroutineScope) obj;
            return c03795;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03795) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            RateDetailPresenter.this.getMView().onDetailLoadFailed();
            return Unit.INSTANCE;
        }
    }

    static /* synthetic */ Object readLocalYearData$suspendImpl(RateDetailPresenter rateDetailPresenter, boolean z, Continuation continuation) {
        ArrayList arrayList = new ArrayList();
        long userId = rateDetailPresenter.getUserId();
        String mStartDate = rateDetailPresenter.getMStartDate();
        if (mStartDate == null) {
            Intrinsics.throwNpe();
        }
        if (mStartDate == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        int i = 0;
        String strSubstring = mStartDate.substring(0, 4);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        List<ServerHeartRateDayData> listQueryHeartRateYearData = GreenDaoUtil.queryHeartRateYearData(userId, Integer.parseInt(strSubstring));
        rateDetailPresenter.mHeartRateList.clear();
        List<ServerHeartRateDayData> list = listQueryHeartRateYearData;
        if (!(list == null || list.isEmpty())) {
            List<ServerHeartRateDayData> listAvgYearHeartDate = rateDetailPresenter.avgYearHeartDate(listQueryHeartRateYearData);
            if (!(listAvgYearHeartDate == null || listAvgYearHeartDate.isEmpty())) {
                arrayList.addAll(listAvgYearHeartDate);
            }
            int size = arrayList.size();
            rateDetailPresenter.mHeartRateList.addAll(arrayList);
            int i2 = rateDetailPresenter.mRateType;
            int i3 = 7;
            int i4 = 5;
            if (i2 == 1) {
                while (i < size) {
                    rateDetailPresenter.ensureActive();
                    ServerHeartRateDayData serverHeartRateDayData = (ServerHeartRateDayData) arrayList.get(i);
                    if (serverHeartRateDayData != null && !TextUtils.isEmpty(serverHeartRateDayData.getDate())) {
                        try {
                            rateDetailPresenter.mRateMax = Math.max(rateDetailPresenter.mRateMax, serverHeartRateDayData.getMaxValue());
                            String date = serverHeartRateDayData.getDate();
                            Intrinsics.checkExpressionValueIsNotNull(date, "item.date");
                            if (date == null) {
                                throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                            }
                            Intrinsics.checkNotNullExpressionValue(date.substring(i4, i3), "(this as java.lang.Strin…ing(startIndex, endIndex)");
                            rateDetailPresenter.mChartList.add(new FloatBarPoint(i, serverHeartRateDayData.getMaxValue(), serverHeartRateDayData.getMinValue(), Integer.parseInt(r13), serverHeartRateDayData.getMaxValue()));
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                    i++;
                    i3 = 7;
                    i4 = 5;
                }
            } else if (i2 == 2) {
                while (i < size) {
                    rateDetailPresenter.ensureActive();
                    ServerHeartRateDayData serverHeartRateDayData2 = (ServerHeartRateDayData) arrayList.get(i);
                    if (serverHeartRateDayData2 != null && !TextUtils.isEmpty(serverHeartRateDayData2.getDate()) && serverHeartRateDayData2.getSilentValue() >= 20 && serverHeartRateDayData2.getSilentValue() <= 220) {
                        try {
                            rateDetailPresenter.mRateMax = Math.max(rateDetailPresenter.mRateMax, serverHeartRateDayData2.getSilentValue());
                            String date2 = serverHeartRateDayData2.getDate();
                            Intrinsics.checkExpressionValueIsNotNull(date2, "item.date");
                            if (date2 == null) {
                                throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                            }
                            Intrinsics.checkNotNullExpressionValue(date2.substring(5, 7), "(this as java.lang.Strin…ing(startIndex, endIndex)");
                            rateDetailPresenter.mChartList.add(new BaseCharBean(i, Integer.parseInt(r13), serverHeartRateDayData2.getSilentValue()));
                        } catch (Exception e3) {
                            e3.printStackTrace();
                        }
                    }
                    i++;
                }
            }
            if (!rateDetailPresenter.mChartList.isEmpty()) {
                List<BaseCharBean> list2 = rateDetailPresenter.mChartList;
                if (list2.size() > 1) {
                    CollectionsKt.sortWith(list2, new Comparator<T>() { // from class: com.ido.life.module.home.rate.vertical.RateDetailPresenter$readLocalYearData$$inlined$sortBy$1
                        /* JADX WARN: Multi-variable type inference failed */
                        @Override // java.util.Comparator
                        public final int compare(T t, T t2) {
                            return ComparisonsKt.compareValues(Float.valueOf(((BaseCharBean) t).x), Float.valueOf(((BaseCharBean) t2).x));
                        }
                    });
                }
                Object objWithContext = BuildersKt.withContext(Dispatchers.getMain(), rateDetailPresenter.new C03833(z, null), continuation);
                if (objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    return objWithContext;
                }
            } else {
                Object objWithContext2 = BuildersKt.withContext(Dispatchers.getMain(), rateDetailPresenter.new C03844(null), continuation);
                if (objWithContext2 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    return objWithContext2;
                }
            }
        } else {
            Object objWithContext3 = BuildersKt.withContext(Dispatchers.getMain(), rateDetailPresenter.new C03855(null), continuation);
            if (objWithContext3 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                return objWithContext3;
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.rate.vertical.RateDetailPresenter$readLocalYearData$3, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RateDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.rate.vertical.RateDetailPresenter$readLocalYearData$3", f = "RateDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03833 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ boolean $showChartAnimator;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03833(boolean z, Continuation continuation) {
            super(2, continuation);
            this.$showChartAnimator = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03833 c03833 = RateDetailPresenter.this.new C03833(this.$showChartAnimator, completion);
            c03833.p$ = (CoroutineScope) obj;
            return c03833;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03833) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            RateDetailPresenter.this.getMView().onLoadSuccessByYear(this.$showChartAnimator, RateDetailPresenter.this.mChartList);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.rate.vertical.RateDetailPresenter$readLocalYearData$4, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RateDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.rate.vertical.RateDetailPresenter$readLocalYearData$4", f = "RateDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03844 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        C03844(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03844 c03844 = RateDetailPresenter.this.new C03844(completion);
            c03844.p$ = (CoroutineScope) obj;
            return c03844;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03844) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            RateDetailPresenter.this.getMView().onDetailLoadFailed();
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.rate.vertical.RateDetailPresenter$readLocalYearData$5, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: RateDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.rate.vertical.RateDetailPresenter$readLocalYearData$5", f = "RateDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03855 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        C03855(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03855 c03855 = RateDetailPresenter.this.new C03855(completion);
            c03855.p$ = (CoroutineScope) obj;
            return c03855;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03855) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            RateDetailPresenter.this.getMView().onDetailLoadFailed();
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: compiled from: RateDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0015\b\u0082\u0004\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003¢\u0006\u0002\u0010\tR\u001a\u0010\u0007\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000b\"\u0004\b\u000f\u0010\rR\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u000b\"\u0004\b\u0011\u0010\rR\u001a\u0010\b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000b\"\u0004\b\u0013\u0010\rR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u000b\"\u0004\b\u0015\u0010\rR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u000b\"\u0004\b\u0017\u0010\r¨\u0006\u0018"}, d2 = {"Lcom/ido/life/module/home/rate/vertical/RateDetailPresenter$YearHeartRateItem;", "", "totalAvgValue", "", "totalSilentValue", "maxRate", "minRate", "count", "silentCount", "(Lcom/ido/life/module/home/rate/vertical/RateDetailPresenter;IIIIII)V", "getCount", "()I", "setCount", "(I)V", "getMaxRate", "setMaxRate", "getMinRate", "setMinRate", "getSilentCount", "setSilentCount", "getTotalAvgValue", "setTotalAvgValue", "getTotalSilentValue", "setTotalSilentValue", "app_release"}, k = 1, mv = {1, 1, 16})
    private final class YearHeartRateItem {
        private int count;
        private int maxRate;
        private int minRate;
        private int silentCount;
        private int totalAvgValue;
        private int totalSilentValue;

        public YearHeartRateItem(int i, int i2, int i3, int i4, int i5, int i6) {
            this.totalAvgValue = i;
            this.totalSilentValue = i2;
            this.maxRate = i3;
            this.minRate = i4;
            this.count = i5;
            this.silentCount = i6;
        }

        public final int getMaxRate() {
            return this.maxRate;
        }

        public final int getTotalAvgValue() {
            return this.totalAvgValue;
        }

        public final int getTotalSilentValue() {
            return this.totalSilentValue;
        }

        public final void setMaxRate(int i) {
            this.maxRate = i;
        }

        public final void setTotalAvgValue(int i) {
            this.totalAvgValue = i;
        }

        public final void setTotalSilentValue(int i) {
            this.totalSilentValue = i;
        }

        public final int getCount() {
            return this.count;
        }

        public final int getMinRate() {
            return this.minRate;
        }

        public final int getSilentCount() {
            return this.silentCount;
        }

        public final void setCount(int i) {
            this.count = i;
        }

        public final void setMinRate(int i) {
            this.minRate = i;
        }

        public final void setSilentCount(int i) {
            this.silentCount = i;
        }
    }

    private final List<ServerHeartRateDayData> avgYearHeartDate(List<? extends ServerHeartRateDayData> list) {
        List<? extends ServerHeartRateDayData> list2 = list;
        if (list2 == null || list2.isEmpty()) {
            return null;
        }
        ensureActive();
        ArrayList arrayList = new ArrayList();
        HashMap map = new HashMap();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            ensureActive();
            ServerHeartRateDayData serverHeartRateDayData = list.get(i);
            if (!TextUtils.isEmpty(serverHeartRateDayData.getDate())) {
                String date = serverHeartRateDayData.getDate();
                Intrinsics.checkExpressionValueIsNotNull(date, "dayData.date");
                if (StringsKt.contains$default((CharSequence) date, (CharSequence) "-", false, 2, (Object) null)) {
                    try {
                        if (serverHeartRateDayData.getSilentValue() != 0 || serverHeartRateDayData.getAvgValue() != 0) {
                            StringBuilder sb = new StringBuilder();
                            String date2 = serverHeartRateDayData.getDate();
                            Intrinsics.checkExpressionValueIsNotNull(date2, "dayData.date");
                            int length = serverHeartRateDayData.getDate().length() - 2;
                            if (date2 == null) {
                                throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                            }
                            String strSubstring = date2.substring(0, length);
                            Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                            sb.append(strSubstring);
                            sb.append("01");
                            String string = sb.toString();
                            if (map.containsKey(string)) {
                                YearHeartRateItem yearHeartRateItem = (YearHeartRateItem) map.get(string);
                                if (serverHeartRateDayData.getAvgValue() > 0) {
                                    if (yearHeartRateItem == null) {
                                        Intrinsics.throwNpe();
                                    }
                                    yearHeartRateItem.setTotalAvgValue(yearHeartRateItem.getTotalAvgValue() + serverHeartRateDayData.getAvgValue());
                                    yearHeartRateItem.setCount(yearHeartRateItem.getCount() + 1);
                                }
                                if (serverHeartRateDayData.getSilentValue() > 0) {
                                    if (yearHeartRateItem == null) {
                                        Intrinsics.throwNpe();
                                    }
                                    yearHeartRateItem.setTotalSilentValue(yearHeartRateItem.getTotalSilentValue() + serverHeartRateDayData.getSilentValue());
                                    yearHeartRateItem.setSilentCount(yearHeartRateItem.getSilentCount() + 1);
                                }
                                if (yearHeartRateItem == null) {
                                    Intrinsics.throwNpe();
                                }
                                yearHeartRateItem.setMaxRate(Math.max(yearHeartRateItem.getMaxRate(), Math.min(getRateMaxValue(), serverHeartRateDayData.getMaxValue())));
                                yearHeartRateItem.setMinRate(Math.min(yearHeartRateItem.getMinRate(), Math.max(getRateMinValue(), serverHeartRateDayData.getMinValue())));
                            } else {
                                map.put(string, new YearHeartRateItem(serverHeartRateDayData.getAvgValue(), serverHeartRateDayData.getSilentValue(), Math.min(getRateMaxValue(), serverHeartRateDayData.getMaxValue()), Math.max(serverHeartRateDayData.getMinValue(), getRateMinValue()), serverHeartRateDayData.getAvgValue() > 0 ? 1 : 0, serverHeartRateDayData.getSilentValue() > 0 ? 1 : 0));
                            }
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                } else {
                    continue;
                }
            }
        }
        if (!map.isEmpty()) {
            for (String str : map.keySet()) {
                ensureActive();
                YearHeartRateItem yearHeartRateItem2 = (YearHeartRateItem) map.get(str);
                if (yearHeartRateItem2 != null) {
                    ServerHeartRateDayData serverHeartRateDayData2 = new ServerHeartRateDayData();
                    serverHeartRateDayData2.setDate(str);
                    serverHeartRateDayData2.setMaxValue(yearHeartRateItem2.getMaxRate());
                    serverHeartRateDayData2.setMinValue(yearHeartRateItem2.getMinRate());
                    if (yearHeartRateItem2.getCount() > 0) {
                        serverHeartRateDayData2.setAvgValue(yearHeartRateItem2.getTotalAvgValue() / yearHeartRateItem2.getCount());
                    }
                    if (yearHeartRateItem2.getSilentCount() > 0) {
                        serverHeartRateDayData2.setSilentValue(yearHeartRateItem2.getTotalSilentValue() / yearHeartRateItem2.getSilentCount());
                    }
                    arrayList.add(serverHeartRateDayData2);
                }
            }
        }
        return arrayList;
    }

    public final List<String> getDefaultLeftYLabelList() {
        return this.defaultLeftYLabelList;
    }

    public final List<String> getLeftYLabelList() {
        return this.leftYLabelList;
    }

    public final ServerHeartRateDayData getItemHeart(int index) {
        if (index < 0 || this.mHeartRateList.size() <= index) {
            return null;
        }
        return this.mHeartRateList.get(index);
    }

    public final int caluteAvgHeartRate() {
        if (this.mHeartRateList.isEmpty()) {
            return 0;
        }
        int size = this.mHeartRateList.size();
        int i = 0;
        int avgValue = 0;
        for (int i2 = 0; i2 < size; i2++) {
            ServerHeartRateDayData serverHeartRateDayData = this.mHeartRateList.get(i2);
            if (serverHeartRateDayData != null && serverHeartRateDayData.getAvgValue() >= 20 && serverHeartRateDayData.getAvgValue() <= 220) {
                i++;
                avgValue += serverHeartRateDayData.getAvgValue();
            }
        }
        if (i > 0) {
            return MathKt.roundToInt((avgValue * 1.0f) / i);
        }
        return 0;
    }

    public final int caluteAvgSilentHeartRate() {
        if (this.mHeartRateList.isEmpty()) {
            return 0;
        }
        int size = this.mHeartRateList.size();
        int i = 0;
        int silentValue = 0;
        for (int i2 = 0; i2 < size; i2++) {
            ServerHeartRateDayData serverHeartRateDayData = this.mHeartRateList.get(i2);
            if (serverHeartRateDayData != null && serverHeartRateDayData.getAvgValue() >= 20 && serverHeartRateDayData.getAvgValue() <= 220 && serverHeartRateDayData.getSilentValue() >= 20 && serverHeartRateDayData.getSilentValue() <= 220) {
                i++;
                silentValue += serverHeartRateDayData.getSilentValue();
            }
        }
        if (i > 0) {
            return MathKt.roundToInt((silentValue * 1.0f) / i);
        }
        return 0;
    }

    public final String getShowAreaRate() {
        ServerHeartRateDayData serverHeartRateDayData;
        if (getMDateType() != 1 || (serverHeartRateDayData = this.dayHeartRate) == null) {
            return String.valueOf(caluteAvgHeartRate());
        }
        if (serverHeartRateDayData == null) {
            Intrinsics.throwNpe();
        }
        return String.valueOf(serverHeartRateDayData.getAvgValue());
    }

    public final String getShowSilentRate() {
        ServerHeartRateDayData serverHeartRateDayData;
        if (getMDateType() != 1 || (serverHeartRateDayData = this.dayHeartRate) == null) {
            return String.valueOf(caluteAvgSilentHeartRate());
        }
        if (serverHeartRateDayData == null) {
            Intrinsics.throwNpe();
        }
        return String.valueOf(serverHeartRateDayData.getSilentValue());
    }

    public final String getRateArea() {
        if (getMDateType() == 1) {
            if (this.dayHeartRate != null) {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                Object[] objArr = new Object[2];
                ServerHeartRateDayData serverHeartRateDayData = this.dayHeartRate;
                if (serverHeartRateDayData == null) {
                    Intrinsics.throwNpe();
                }
                objArr[0] = Integer.valueOf(serverHeartRateDayData.getMinValue());
                ServerHeartRateDayData serverHeartRateDayData2 = this.dayHeartRate;
                if (serverHeartRateDayData2 == null) {
                    Intrinsics.throwNpe();
                }
                objArr[1] = Integer.valueOf(serverHeartRateDayData2.getMaxValue());
                String str = String.format("%d-%d", Arrays.copyOf(objArr, objArr.length));
                Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(format, *args)");
                return str;
            }
        } else if (this.mHeartRateList.size() > 0) {
            int rateMaxValue = getRateMaxValue();
            int rateMinValue = getRateMinValue();
            int size = this.mHeartRateList.size();
            int iMin = rateMaxValue;
            for (int i = 0; i < size; i++) {
                ServerHeartRateDayData serverHeartRateDayData3 = this.mHeartRateList.get(i);
                iMin = Math.min(iMin, serverHeartRateDayData3.getMinValue());
                rateMinValue = Math.max(rateMinValue, serverHeartRateDayData3.getMaxValue());
            }
            if (rateMinValue > 0) {
                StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                Object[] objArr2 = {Integer.valueOf(iMin), Integer.valueOf(rateMinValue)};
                String str2 = String.format("%s-%s", Arrays.copyOf(objArr2, objArr2.length));
                Intrinsics.checkNotNullExpressionValue(str2, "java.lang.String.format(format, *args)");
                return str2;
            }
        }
        return "";
    }

    public final String getSilentRate() {
        boolean z = true;
        if (getMDateType() == 1) {
            ServerHeartRateDayData serverHeartRateDayData = this.dayHeartRate;
            if (serverHeartRateDayData != null) {
                if (serverHeartRateDayData == null) {
                    Intrinsics.throwNpe();
                }
                if (serverHeartRateDayData.getSilentValue() > 0) {
                    ServerHeartRateDayData serverHeartRateDayData2 = this.dayHeartRate;
                    if (serverHeartRateDayData2 == null) {
                        Intrinsics.throwNpe();
                    }
                    return String.valueOf(serverHeartRateDayData2.getSilentValue());
                }
            }
        } else {
            List<ServerHeartRateDayData> list = this.mHeartRateList;
            if (list != null && !list.isEmpty()) {
                z = false;
            }
            if (!z) {
                int size = this.mHeartRateList.size();
                int silentValue = 0;
                for (int i = 0; i < size; i++) {
                    silentValue += this.mHeartRateList.get(i).getSilentValue();
                }
                int iRoundToInt = MathKt.roundToInt((silentValue * 1.0f) / size);
                if (iRoundToInt > 0) {
                    return String.valueOf(iRoundToInt);
                }
            }
        }
        return "";
    }

    private final boolean isvalidHeartRate(ServerHeartRateDayData heartRate) {
        if (heartRate == null || TextUtils.isEmpty(heartRate.getDate()) || heartRate.getMaxValue() > getRateMaxValue() || heartRate.getMaxValue() < getRateMinValue() || heartRate.getMinValue() > getRateMaxValue() || heartRate.getMinValue() < getRateMinValue()) {
            return false;
        }
        int rateMinValue = getRateMinValue();
        int rateMaxValue = getRateMaxValue();
        int avgValue = heartRate.getAvgValue();
        return rateMinValue <= avgValue && rateMaxValue >= avgValue;
    }

    public final List<int[]> convertHeartRate(List<int[]> originalItems, int second) {
        int i;
        List<int[]> list = originalItems;
        if (list == null || originalItems.isEmpty() || second <= 255) {
            return originalItems;
        }
        int size = originalItems.size();
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        int i11 = 0;
        int i12 = 0;
        while (true) {
            if (i2 >= size) {
                break;
            }
            int i13 = size;
            int[] iArr = list.get(i2);
            if (iArr.length != 2) {
                i = i2;
            } else {
                int i14 = iArr[1];
                int i15 = iArr[0];
                StringBuilder sb = new StringBuilder();
                i = i2;
                sb.append("offset = ");
                sb.append(i14);
                sb.append(", value = ");
                sb.append(i15);
                sb.append(", newSecond = ");
                sb.append(i3);
                sb.append(", preSecond = ");
                sb.append(i4);
                printAndSaveLog(sb.toString());
                int i16 = i3 + i14;
                if (i16 - i4 >= second) {
                    if (i5 <= 0 || i6 <= 0) {
                        arrayList.add(new int[]{0, 0});
                    } else {
                        if (i7 == 0 && i8 == 0) {
                            arrayList.add(new int[]{i9 / i10, i3});
                        } else if (i5 > i7 && i6 < i8) {
                            arrayList.add(new int[]{i5, i11});
                        } else if (i5 > i7) {
                            arrayList.add(new int[]{i5, i11});
                            i6 = i8;
                        } else if (i6 < i8) {
                            arrayList.add(new int[]{i6, i12});
                            i5 = i7;
                        } else {
                            arrayList.add(new int[]{i9 / i10, i3});
                        }
                        i4 = i16;
                        i7 = i5;
                        i8 = i6;
                        i5 = 0;
                        i6 = 0;
                        i9 = 0;
                        i10 = 0;
                        i11 = 0;
                        i12 = 0;
                    }
                    i5 = i7;
                    i6 = i8;
                    i4 = i16;
                    i7 = i5;
                    i8 = i6;
                    i5 = 0;
                    i6 = 0;
                    i9 = 0;
                    i10 = 0;
                    i11 = 0;
                    i12 = 0;
                }
                if ((i16 / 60) / 60 >= 24) {
                    i3 = i16 - i14;
                    break;
                }
                if (20 <= i15 && 220 >= i15) {
                    if (i15 > i5) {
                        i5 = i15;
                        i11 = i16;
                    }
                    if (i15 < i6 || i6 == 0) {
                        i6 = i15;
                        i12 = i16;
                    }
                    i9 += i15;
                    i10++;
                }
                i3 = i16;
            }
            i2 = i + 1;
            list = originalItems;
            size = i13;
        }
        if (i5 > 0 && i6 > 0) {
            if (i7 == 0 && i8 == 0) {
                arrayList.add(new int[]{i9 / i10, i3});
            } else if ((i5 > i7 && i6 < i8) || i5 > i7) {
                arrayList.add(new int[]{i5, i11});
            } else if (i6 < i8) {
                arrayList.add(new int[]{i6, i12});
            } else {
                arrayList.add(new int[]{i9 / i10, i3});
            }
        }
        return arrayList;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    protected void clearData() {
        this.mChartList.clear();
        this.mHeartRateList.clear();
        this.mDayChartList.clear();
        this.dayHeartRate = (ServerHeartRateDayData) null;
        this.mRateMax = 0;
    }

    public final List<CubicChartBean> getDayChartList() {
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
    public boolean hasData(String date) {
        if (TextUtils.isEmpty(date)) {
            return false;
        }
        return LocalHealthDataManager.getInstance().hasHeartRate(date);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    protected List<String> getDataDownloadType() {
        return CollectionsKt.mutableListOf(ServerHeartRateDayData.class.getSimpleName());
    }
}