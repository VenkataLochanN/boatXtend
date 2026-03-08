package com.ido.life.module.home.weight;

import android.text.TextUtils;
import com.ido.life.bean.BaseCharBean;
import com.ido.life.constants.Constants;
import com.ido.life.database.model.UserInfo;
import com.ido.life.database.model.UserTargetNew;
import com.ido.life.database.model.WeightItemBean;
import com.ido.life.enums.WeightBmiEnum;
import com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter;
import com.ido.life.util.DateUtil;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RunTimeUtil;
import com.ido.life.util.UnitUtil;
import com.veryfit.multi.nativeprotocol.b;
import java.util.ArrayList;
import java.util.Calendar;
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
import kotlin.math.MathKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: compiled from: WeightDetailPresenter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0014\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u001d\u0018\u0000 J24\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0004\u0012\u00020\u00050\u0001:\u0001JB\r\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\u0006\u0010*\u001a\u00020\u0011J'\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.2\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00020\u0003H\u0082@ø\u0001\u0000¢\u0006\u0002\u00100J\b\u00101\u001a\u00020,H\u0002J\u0010\u00102\u001a\u00020.2\u0006\u00103\u001a\u00020\u0011H\u0002J\b\u00104\u001a\u00020,H\u0014J\u0019\u00105\u001a\u00020,2\u0006\u0010-\u001a\u00020.H\u0082@ø\u0001\u0000¢\u0006\u0002\u00106J\u0016\u00107\u001a\u0010\u0012\f\u0012\n 8*\u0004\u0018\u00010&0&0\tH\u0014J\u0012\u00109\u001a\u00020,2\b\u0010:\u001a\u0004\u0018\u00010&H\u0014J\u001c\u0010;\u001a\u00020,2\b\u0010<\u001a\u0004\u0018\u00010&2\b\u0010=\u001a\u0004\u0018\u00010&H\u0014J\u001c\u0010>\u001a\u00020,2\b\u0010<\u001a\u0004\u0018\u00010&2\b\u0010=\u001a\u0004\u0018\u00010&H\u0014J\u001c\u0010?\u001a\u00020,2\b\u0010<\u001a\u0004\u0018\u00010&2\b\u0010=\u001a\u0004\u0018\u00010&H\u0014J\u0010\u0010@\u001a\u0004\u0018\u00010\u00022\u0006\u0010A\u001a\u00020\u000bJ\u0012\u0010B\u001a\u00020.2\b\u0010C\u001a\u0004\u0018\u00010&H\u0016J\b\u0010D\u001a\u00020,H\u0014J\b\u0010E\u001a\u00020,H\u0002J\u0019\u0010F\u001a\u00020,2\u0006\u0010-\u001a\u00020.H\u0094@ø\u0001\u0000¢\u0006\u0002\u00106J\u0019\u0010G\u001a\u00020,2\u0006\u0010-\u001a\u00020.H\u0094@ø\u0001\u0000¢\u0006\u0002\u00106J\u0019\u0010H\u001a\u00020,2\u0006\u0010-\u001a\u00020.H\u0094@ø\u0001\u0000¢\u0006\u0002\u00106J\u0019\u0010I\u001a\u00020,2\u0006\u0010-\u001a\u00020.H\u0094@ø\u0001\u0000¢\u0006\u0002\u00106R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\r\u001a\u0004\u0018\u00010\u00022\b\u0010\f\u001a\u0004\u0018\u00010\u0002@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0012\u001a\u00020\u00112\u0006\u0010\f\u001a\u00020\u0011@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u001e\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0018\u001a\u00020\u000bX\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0017R\u0017\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u001d\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u0017R\u0013\u0010\u001f\u001a\u0004\u0018\u00010\u00028F¢\u0006\u0006\u001a\u0004\b \u0010\u000fR\u0017\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038F¢\u0006\u0006\u001a\u0004\b\"\u0010\u001cR\u0019\u0010#\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00038F¢\u0006\u0006\u001a\u0004\b$\u0010\u001cR\u0017\u0010%\u001a\b\u0012\u0004\u0012\u00020&0\u00038F¢\u0006\u0006\u001a\u0004\b'\u0010\u001cR\u0017\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038F¢\u0006\u0006\u001a\u0004\b)\u0010\u001c\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006K"}, d2 = {"Lcom/ido/life/module/home/weight/WeightDetailPresenter;", "Lcom/ido/life/module/home/chartdetail/vertical/BaseDetailPresenter;", "Lcom/ido/life/database/model/WeightItemBean;", "", "Lcom/ido/life/bean/BaseCharBean;", "Lcom/ido/life/module/home/weight/IWeightDetailView;", "iView", "(Lcom/ido/life/module/home/weight/IWeightDetailView;)V", "mCharList", "", "mTargetWeight", "", "<set-?>", "mWeightDay", "getMWeightDay", "()Lcom/ido/life/database/model/WeightItemBean;", "mWeightList", "", "mWeightMax", "getMWeightMax", "()F", "mYMax", "getMYMax", "()I", "mYMin", "getMYMin", "monthChartList", "getMonthChartList", "()Ljava/util/List;", "targetWeight", "getTargetWeight", "todayWeightRecord", "getTodayWeightRecord", "weekChartList", "getWeekChartList", "weightList", "getWeightList", "yLabelList", "", "getYLabelList", "yearChartList", "getYearChartList", "caluteAvgWeight", "calutePerByYear", "", "showChartAnimator", "", "dataList", "(ZLjava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "caluteYMaxinValue", "checkWeightValid", "weight", "clearData", "convertWeightInfoToLineChart", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getDataDownloadType", "kotlin.jvm.PlatformType", "getDetailByDay", "startDate", "getDetailByMonth", "start", "end", "getDetailByWeek", "getDetailByYear", "getWeightItemByIndex", "index", "hasData", "date", "onTypeChanged", "queryTargetWeight", "readLocalDayData", "readLocalMonthData", "readLocalWeekData", "readLocalYearData", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
public final class WeightDetailPresenter extends BaseDetailPresenter<WeightItemBean, List<? extends BaseCharBean>, List<? extends BaseCharBean>, List<? extends BaseCharBean>, IWeightDetailView> {
    private static final int LABEL_Y_COUNT = 3;
    private List<BaseCharBean> mCharList;
    private int mTargetWeight;
    private WeightItemBean mWeightDay;
    private List<WeightItemBean> mWeightList;
    private float mWeightMax;
    private int mYMax;
    private final int mYMin;

    /* JADX INFO: renamed from: com.ido.life.module.home.weight.WeightDetailPresenter$calutePerByYear$1, reason: invalid class name */
    /* JADX INFO: compiled from: WeightDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0082@"}, d2 = {"calutePerByYear", "", "showChartAnimator", "", "dataList", "", "Lcom/ido/life/database/model/WeightItemBean;", "continuation", "Lkotlin/coroutines/Continuation;", ""}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.weight.WeightDetailPresenter", f = "WeightDetailPresenter.kt", i = {0, 0, 0, 0, 0, 0}, l = {b.Y}, m = "calutePerByYear", n = {"this", "showChartAnimator", "dataList", "map", "size", "dateStr"}, s = {"L$0", "Z$0", "L$1", "L$2", "I$0", "L$3"})
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
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
            return WeightDetailPresenter.this.calutePerByYear(false, null, this);
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.weight.WeightDetailPresenter$convertWeightInfoToLineChart$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: WeightDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0082@"}, d2 = {"convertWeightInfoToLineChart", "", "showChartAnimator", "", "continuation", "Lkotlin/coroutines/Continuation;", ""}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.weight.WeightDetailPresenter", f = "WeightDetailPresenter.kt", i = {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, l = {158, 359}, m = "convertWeightInfoToLineChart", n = {"this", "showChartAnimator", "this", "showChartAnimator", "size", "itemBean", Constants.AppPackage.CALENDAR, "unitSetting", "weekStart", "dayOfWeek", "iterator", "bean"}, s = {"L$0", "Z$0", "L$0", "Z$0", "I$0", "L$1", "L$2", "I$1", "I$2", "I$3", "L$3", "L$4"})
    static final class C04131 extends ContinuationImpl {
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        C04131(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return WeightDetailPresenter.this.convertWeightInfoToLineChart(false, this);
        }
    }

    private final boolean checkWeightValid(float weight) {
        return weight >= ((float) 10) && weight <= ((float) 250);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WeightDetailPresenter(IWeightDetailView iView) {
        super(iView);
        Intrinsics.checkParameterIsNotNull(iView, "iView");
        this.mWeightList = new ArrayList();
        this.mCharList = new ArrayList();
    }

    public final WeightItemBean getMWeightDay() {
        return this.mWeightDay;
    }

    public final int getMYMax() {
        return this.mYMax;
    }

    public final int getMYMin() {
        return this.mYMin;
    }

    public final float getMWeightMax() {
        return this.mWeightMax;
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

    public final WeightItemBean getTodayWeightRecord() {
        long userId = getUserId();
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        Intrinsics.checkExpressionValueIsNotNull(calendar, "Calendar.getInstance(Locale.CHINA)");
        return GreenDaoUtil.queryWeightByDate(userId, DateUtil.format(calendar.getTime(), DateUtil.DATE_FORMAT_YMD));
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final /* synthetic */ java.lang.Object calutePerByYear(boolean r12, java.util.List<? extends com.ido.life.database.model.WeightItemBean> r13, kotlin.coroutines.Continuation<? super kotlin.Unit> r14) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 305
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.home.weight.WeightDetailPresenter.calutePerByYear(boolean, java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x001a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final /* synthetic */ java.lang.Object convertWeightInfoToLineChart(boolean r21, kotlin.coroutines.Continuation<? super kotlin.Unit> r22) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 1120
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.home.weight.WeightDetailPresenter.convertWeightInfoToLineChart(boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.weight.WeightDetailPresenter$convertWeightInfoToLineChart$2, reason: invalid class name */
    /* JADX INFO: compiled from: WeightDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.weight.WeightDetailPresenter$convertWeightInfoToLineChart$2", f = "WeightDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        AnonymousClass2(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            AnonymousClass2 anonymousClass2 = WeightDetailPresenter.this.new AnonymousClass2(completion);
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
            WeightDetailPresenter.this.getMView().onDetailLoadFailed();
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.weight.WeightDetailPresenter$convertWeightInfoToLineChart$4, reason: invalid class name */
    /* JADX INFO: compiled from: WeightDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.weight.WeightDetailPresenter$convertWeightInfoToLineChart$4", f = "WeightDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
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
            AnonymousClass4 anonymousClass4 = WeightDetailPresenter.this.new AnonymousClass4(this.$showChartAnimator, completion);
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
            int mDateType = WeightDetailPresenter.this.getMDateType();
            if (mDateType == 2) {
                WeightDetailPresenter.this.getMView().onLoadSuccessByWeek(this.$showChartAnimator, WeightDetailPresenter.this.mCharList);
            } else if (mDateType == 3) {
                WeightDetailPresenter.this.getMView().onLoadSuccessByMonth(this.$showChartAnimator, WeightDetailPresenter.this.mCharList);
            } else if (mDateType == 4) {
                WeightDetailPresenter.this.getMView().onLoadSuccessByYear(this.$showChartAnimator, WeightDetailPresenter.this.mCharList);
            }
            return Unit.INSTANCE;
        }
    }

    private final void caluteYMaxinValue() {
        int iMax = Math.max((int) this.mWeightMax, this.mTargetWeight);
        int i = 2;
        if (iMax > 0) {
            if (iMax < 10) {
                i = iMax % 2 == 0 ? 2 + iMax : iMax + 1;
            } else {
                i = ((iMax / 10) + 1) * 10;
            }
        }
        this.mYMax = i;
    }

    public final List<String> getYLabelList() {
        ArrayList arrayList = new ArrayList();
        int i = (this.mYMax - this.mYMin) / 2;
        int i2 = 0;
        while (i2 < 3) {
            arrayList.add(i2 == 0 ? "" : String.valueOf(this.mYMin + (i2 * i)));
            i2++;
        }
        return arrayList;
    }

    public final float caluteAvgWeight() {
        float bmi;
        float totalWeight;
        if (getMDateType() == 1 || this.mWeightList.size() == 0) {
            return 0.0f;
        }
        if (getMDateType() == 4) {
            int size = this.mWeightList.size();
            bmi = 0.0f;
            totalWeight = 0.0f;
            for (int i = 0; i < size; i++) {
                WeightItemBean weightItemBean = this.mWeightList.get(i);
                if (weightItemBean.getTotalWeight() > 0) {
                    bmi += weightItemBean.getBmi();
                    totalWeight += weightItemBean.getTotalWeight();
                }
            }
        } else {
            int size2 = this.mWeightList.size();
            float f2 = 0.0f;
            float totalWeight2 = 0.0f;
            for (int i2 = 0; i2 < size2; i2++) {
                WeightItemBean weightItemBean2 = this.mWeightList.get(i2);
                if (weightItemBean2.getTotalWeight() > 0) {
                    f2++;
                    totalWeight2 += weightItemBean2.getTotalWeight();
                }
            }
            bmi = f2;
            totalWeight = totalWeight2;
        }
        return bmi > ((float) 0) ? (totalWeight * 1.0f) / bmi : totalWeight;
    }

    public final List<WeightItemBean> getWeightList() {
        return this.mWeightList;
    }

    public final WeightItemBean getWeightItemByIndex(int index) {
        if (this.mWeightList.size() == 0 || index < 0 || this.mWeightList.size() <= index) {
            return null;
        }
        return this.mWeightList.get(index);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    protected void onTypeChanged() {
        super.onTypeChanged();
        queryTargetWeight();
        caluteYMaxinValue();
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    protected Object readLocalDayData(boolean z, Continuation<? super Unit> continuation) {
        UserInfo userInfoQueryUserInfo;
        ensureActive();
        this.mWeightDay = GreenDaoUtil.queryNewestWeightRecord(getUserId());
        if (this.mWeightDay == null && (userInfoQueryUserInfo = GreenDaoUtil.queryUserInfo(getUserId())) != null) {
            this.mWeightDay = new WeightItemBean();
            WeightItemBean weightItemBean = this.mWeightDay;
            if (weightItemBean != null) {
                weightItemBean.setUserId(getUserId());
            }
            Calendar calendar = Calendar.getInstance(Locale.getDefault());
            Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
            calendar.setTimeInMillis(userInfoQueryUserInfo.getUpdateTime());
            WeightItemBean weightItemBean2 = this.mWeightDay;
            if (weightItemBean2 == null) {
                Intrinsics.throwNpe();
            }
            weightItemBean2.setDate(DateUtil.format(calendar.getTime(), DateUtil.DATE_FORMAT_YMD));
            if (userInfoQueryUserInfo.getWeightUnit() == 2) {
                WeightItemBean weightItemBean3 = this.mWeightDay;
                if (weightItemBean3 == null) {
                    Intrinsics.throwNpe();
                }
                weightItemBean3.setTotalWeight(UnitUtil.getPound2Kg(userInfoQueryUserInfo.getWeight()));
            } else {
                WeightItemBean weightItemBean4 = this.mWeightDay;
                if (weightItemBean4 == null) {
                    Intrinsics.throwNpe();
                }
                weightItemBean4.setTotalWeight(userInfoQueryUserInfo.getWeight());
            }
            WeightItemBean weightItemBean5 = this.mWeightDay;
            if (weightItemBean5 == null) {
                Intrinsics.throwNpe();
            }
            WeightItemBean weightItemBean6 = this.mWeightDay;
            if (weightItemBean6 == null) {
                Intrinsics.throwNpe();
            }
            weightItemBean5.setBmi(WeightBmiEnum.caluteBmi(weightItemBean6.getTotalWeight(), userInfoQueryUserInfo.getHeightCm()));
            GreenDaoUtil.addWeight(this.mWeightDay);
        }
        Object objWithContext = BuildersKt.withContext(Dispatchers.getMain(), new C04142(z, null), continuation);
        return objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContext : Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.weight.WeightDetailPresenter$readLocalDayData$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: WeightDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.weight.WeightDetailPresenter$readLocalDayData$2", f = "WeightDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C04142 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ boolean $showChartAnimator;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C04142(boolean z, Continuation continuation) {
            super(2, continuation);
            this.$showChartAnimator = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C04142 c04142 = WeightDetailPresenter.this.new C04142(this.$showChartAnimator, completion);
            c04142.p$ = (CoroutineScope) obj;
            return c04142;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C04142) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            if (WeightDetailPresenter.this.getMWeightDay() != null) {
                WeightDetailPresenter.this.getMView().onLoadSuccessByDay(this.$showChartAnimator, WeightDetailPresenter.this.getMWeightDay());
            } else {
                WeightDetailPresenter.this.getMView().onDetailLoadFailed();
            }
            return Unit.INSTANCE;
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    protected Object readLocalWeekData(boolean z, Continuation<? super Unit> continuation) throws Throwable {
        List<WeightItemBean> listQueryWeightListByDateArea = GreenDaoUtil.queryWeightListByDateArea(getMStartDate(), getMEndDate(), getUserId());
        if (listQueryWeightListByDateArea != null && listQueryWeightListByDateArea.size() > 0) {
            this.mWeightList.addAll(listQueryWeightListByDateArea);
            Object objConvertWeightInfoToLineChart = convertWeightInfoToLineChart(z, continuation);
            if (objConvertWeightInfoToLineChart == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                return objConvertWeightInfoToLineChart;
            }
        } else {
            Object objWithContext = BuildersKt.withContext(Dispatchers.getMain(), new C04162(null), continuation);
            if (objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                return objWithContext;
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.weight.WeightDetailPresenter$readLocalWeekData$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: WeightDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.weight.WeightDetailPresenter$readLocalWeekData$2", f = "WeightDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C04162 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        C04162(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C04162 c04162 = WeightDetailPresenter.this.new C04162(completion);
            c04162.p$ = (CoroutineScope) obj;
            return c04162;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C04162) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            WeightDetailPresenter.this.getMView().onDetailLoadFailed();
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
        List<WeightItemBean> listQueryWeightRecordByMonth = GreenDaoUtil.queryWeightRecordByMonth(getUserId(), Integer.parseInt((String) listSplit$default.get(0)), Integer.parseInt((String) listSplit$default.get(1)));
        if (listQueryWeightRecordByMonth != null && listQueryWeightRecordByMonth.size() > 0) {
            this.mWeightList.addAll(listQueryWeightRecordByMonth);
            Object objConvertWeightInfoToLineChart = convertWeightInfoToLineChart(z, continuation);
            if (objConvertWeightInfoToLineChart == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                return objConvertWeightInfoToLineChart;
            }
        } else {
            Object objWithContext = BuildersKt.withContext(Dispatchers.getMain(), new C04152(null), continuation);
            if (objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                return objWithContext;
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.weight.WeightDetailPresenter$readLocalMonthData$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: WeightDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.weight.WeightDetailPresenter$readLocalMonthData$2", f = "WeightDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C04152 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        C04152(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C04152 c04152 = WeightDetailPresenter.this.new C04152(completion);
            c04152.p$ = (CoroutineScope) obj;
            return c04152;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C04152) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            WeightDetailPresenter.this.getMView().onDetailLoadFailed();
            return Unit.INSTANCE;
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    protected Object readLocalYearData(boolean z, Continuation<? super Unit> continuation) throws Throwable {
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
        List<WeightItemBean> listQueryWeightRecordByYear = GreenDaoUtil.queryWeightRecordByYear(userId, Integer.parseInt(strSubstring));
        List<WeightItemBean> list = listQueryWeightRecordByYear;
        if (!(list == null || list.isEmpty())) {
            Object objCalutePerByYear = calutePerByYear(z, listQueryWeightRecordByYear, continuation);
            if (objCalutePerByYear == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                return objCalutePerByYear;
            }
        } else {
            Object objWithContext = BuildersKt.withContext(Dispatchers.getMain(), new C04172(null), continuation);
            if (objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                return objWithContext;
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.weight.WeightDetailPresenter$readLocalYearData$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: WeightDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.weight.WeightDetailPresenter$readLocalYearData$2", f = "WeightDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C04172 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        C04172(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C04172 c04172 = WeightDetailPresenter.this.new C04172(completion);
            c04172.p$ = (CoroutineScope) obj;
            return c04172;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C04172) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            WeightDetailPresenter.this.getMView().onDetailLoadFailed();
            return Unit.INSTANCE;
        }
    }

    private final void queryTargetWeight() {
        int iRoundToInt;
        int iRoundToInt2;
        UserTargetNew userTargetNewQueryUserLatestTarget = GreenDaoUtil.queryUserLatestTarget(RunTimeUtil.getInstance().getUserId());
        if (userTargetNewQueryUserLatestTarget != null) {
            int unitSet = getUnitSet();
            if (unitSet == 1) {
                if (userTargetNewQueryUserLatestTarget.getWeightUnit() == unitSet) {
                    iRoundToInt = userTargetNewQueryUserLatestTarget.getWeightInt();
                } else {
                    iRoundToInt = MathKt.roundToInt(UnitUtil.getPound2Kg(userTargetNewQueryUserLatestTarget.getWeight()));
                }
                this.mTargetWeight = iRoundToInt;
                return;
            }
            if (unitSet != 2) {
                return;
            }
            if (userTargetNewQueryUserLatestTarget.getWeightUnit() == unitSet) {
                iRoundToInt2 = userTargetNewQueryUserLatestTarget.getWeightInt();
            } else {
                iRoundToInt2 = MathKt.roundToInt(UnitUtil.getKg2Pound(userTargetNewQueryUserLatestTarget.getWeight()));
            }
            this.mTargetWeight = iRoundToInt2;
        }
    }

    public final int getTargetWeight() {
        if (this.mTargetWeight <= 0) {
            caluteYMaxinValue();
        }
        queryTargetWeight();
        return this.mTargetWeight;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    protected void clearData() {
        this.mWeightList.clear();
        this.mCharList.clear();
        this.mWeightDay = (WeightItemBean) null;
        this.mWeightMax = 0.0f;
    }

    public final List<BaseCharBean> getWeekChartList() {
        return this.mCharList;
    }

    public final List<BaseCharBean> getMonthChartList() {
        return this.mCharList;
    }

    public final List<BaseCharBean> getYearChartList() {
        return this.mCharList;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    protected List<String> getDataDownloadType() {
        return CollectionsKt.mutableListOf(WeightItemBean.class.getSimpleName());
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    public boolean hasData(String date) {
        if (TextUtils.isEmpty(date)) {
            return false;
        }
        return GreenDaoUtil.hasWeightData(getUserId(), date);
    }
}