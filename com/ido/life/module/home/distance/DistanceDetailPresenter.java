package com.ido.life.module.home.distance;

import android.graphics.Color;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.ido.life.bean.BarChartPoint;
import com.ido.life.constants.Constants;
import com.ido.life.database.model.SportDistanceBean;
import com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter;
import com.ido.life.util.DateUtil;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.UnitUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
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

/* JADX INFO: compiled from: DistanceDetailPresenter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0013\u0018\u000028\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0004\u0012\u00020\u00040\u0001B\r\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010\u0006J\u0006\u0010.\u001a\u00020\rJ\u0006\u0010/\u001a\u00020\rJ\b\u00100\u001a\u000201H\u0014J0\u00102\u001a\n\u0012\u0004\u0012\u00020#\u0018\u00010\u00022\u000e\u00103\u001a\n\u0012\u0004\u0012\u00020#\u0018\u00010\u00152\u000e\u00104\u001a\n\u0012\u0004\u0012\u00020#\u0018\u00010\u0015H\u0002J \u00105\u001a\n\u0012\u0004\u0012\u00020#\u0018\u00010\u00022\u000e\u00106\u001a\n\u0012\u0004\u0012\u00020#\u0018\u00010\u0002H\u0002J)\u00107\u001a\u0002012\u0006\u00108\u001a\u0002092\u000e\u00106\u001a\n\u0012\u0004\u0012\u00020#\u0018\u00010\u0002H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010:J$\u0010;\u001a\b\u0012\u0004\u0012\u00020\u00030\u00152\u0014\u00106\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u0002\u0018\u00010\u0002H\u0002J\u0016\u0010<\u001a\u0010\u0012\f\u0012\n >*\u0004\u0018\u00010=0=0\u0015H\u0014J\u0014\u0010?\u001a\b\u0012\u0004\u0012\u00020=0\u00022\u0006\u0010@\u001a\u00020\rJ\u0012\u0010A\u001a\u0002012\b\u0010B\u001a\u0004\u0018\u00010=H\u0014J\u001c\u0010C\u001a\u0002012\b\u0010D\u001a\u0004\u0018\u00010=2\b\u0010E\u001a\u0004\u0018\u00010=H\u0014J\u001c\u0010F\u001a\u0002012\b\u0010D\u001a\u0004\u0018\u00010=2\b\u0010E\u001a\u0004\u0018\u00010=H\u0014J\u001c\u0010G\u001a\u0002012\b\u0010D\u001a\u0004\u0018\u00010=2\b\u0010E\u001a\u0004\u0018\u00010=H\u0014J\u0012\u0010H\u001a\u0002092\b\u0010I\u001a\u0004\u0018\u00010=H\u0016J\b\u0010J\u001a\u000201H\u0014J\u0019\u0010K\u001a\u0002012\u0006\u00108\u001a\u000209H\u0094@ø\u0001\u0000¢\u0006\u0002\u0010LJ\u0019\u0010M\u001a\u0002012\u0006\u00108\u001a\u000209H\u0094@ø\u0001\u0000¢\u0006\u0002\u0010LJ\u0019\u0010N\u001a\u0002012\u0006\u00108\u001a\u000209H\u0094@ø\u0001\u0000¢\u0006\u0002\u0010LJ\u0019\u0010O\u001a\u0002012\u0006\u00108\u001a\u000209H\u0094@ø\u0001\u0000¢\u0006\u0002\u0010LR\u001b\u0010\u0007\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u00028F¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\f\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0010\u001a\u00020\u00118F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00030\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\rX\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\u0018\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u000f\"\u0004\b\u001a\u0010\u001bR\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010 \u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020\r@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u000fR\u0014\u0010\"\u001a\b\u0012\u0004\u0012\u00020#0\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010$\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020\r@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u000fR\u001b\u0010&\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u00028F¢\u0006\u0006\u001a\u0004\b'\u0010\tR\u0017\u0010(\u001a\b\u0012\u0004\u0012\u00020#0\u00028F¢\u0006\u0006\u001a\u0004\b)\u0010\tR\u001b\u0010*\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u00028F¢\u0006\u0006\u001a\u0004\b+\u0010\tR\u001b\u0010,\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u00028F¢\u0006\u0006\u001a\u0004\b-\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006P"}, d2 = {"Lcom/ido/life/module/home/distance/DistanceDetailPresenter;", "Lcom/ido/life/module/home/chartdetail/vertical/BaseDetailPresenter;", "", "Lcom/ido/life/bean/BarChartPoint;", "Lcom/ido/life/module/home/distance/IDistanceDetailView;", "iView", "(Lcom/ido/life/module/home/distance/IDistanceDetailView;)V", "dayChartList", "getDayChartList", "()Ljava/util/List;", "defaultList", "getDefaultList", "defaultYMaxValue", "", "getDefaultYMaxValue", "()F", "defaultYMinValue", "", "getDefaultYMinValue", "()I", "mChartList", "", "mDefaultBarColor", "mDefaultBarRadius", "mDefaultHeight", "getMDefaultHeight", "setMDefaultHeight", "(F)V", "mGson", "Lcom/google/gson/Gson;", "mMaxDistance", "<set-?>", "mSportAvgDistance", "getMSportAvgDistance", "mSportDistanceList", "Lcom/ido/life/database/model/SportDistanceBean;", "mSportTotalDistance", "getMSportTotalDistance", "monthChartList", "getMonthChartList", "sportList", "getSportList", "weekChartList", "getWeekChartList", "yearChartList", "getYearChartList", "calculateYMaxDistance", "caluteYMaxDistance", "clearData", "", "combineServerAndLocalData", "serverList", "localList", "combineYearList", "list", "convertDataListToUi", "showChartAnimator", "", "(ZLjava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "convertFrom", "getDataDownloadType", "", "kotlin.jvm.PlatformType", "getDefaultYLabelList", "yMaxValue", "getDetailByDay", "startDate", "getDetailByMonth", "start", "end", "getDetailByWeek", "getDetailByYear", "hasData", "date", "onTypeChanged", "readLocalDayData", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readLocalMonthData", "readLocalWeekData", "readLocalYearData", "app_release"}, k = 1, mv = {1, 1, 16})
public final class DistanceDetailPresenter extends BaseDetailPresenter<List<? extends BarChartPoint>, List<? extends BarChartPoint>, List<? extends BarChartPoint>, List<? extends BarChartPoint>, IDistanceDetailView> {
    private List<BarChartPoint> mChartList;
    private final int mDefaultBarColor;
    private final float mDefaultBarRadius;
    private float mDefaultHeight;
    private final Gson mGson;
    private float mMaxDistance;
    private float mSportAvgDistance;
    private final List<SportDistanceBean> mSportDistanceList;
    private float mSportTotalDistance;

    /* JADX INFO: renamed from: com.ido.life.module.home.distance.DistanceDetailPresenter$convertDataListToUi$1, reason: invalid class name */
    /* JADX INFO: compiled from: DistanceDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0082@"}, d2 = {"convertDataListToUi", "", "showChartAnimator", "", "list", "", "Lcom/ido/life/database/model/SportDistanceBean;", "continuation", "Lkotlin/coroutines/Continuation;", ""}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.distance.DistanceDetailPresenter", f = "DistanceDetailPresenter.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, l = {387, Constants.BindErrorCode.BIND_FAILED_DEVICE_ALREADY_IN_BIND_STATE, 443, 450}, m = "convertDataListToUi", n = {"this", "showChartAnimator", "list", "startCalendar", "map", "size", "item", Constants.AppPackage.CALENDAR, "distance", "dayAvgDistance", "unit", "count", "index", "weekStart", "dayOfWeek", "yMax", "yDefaultHeight", "i", "this", "showChartAnimator", "list", "startCalendar", "map", "size", "item", Constants.AppPackage.CALENDAR, "distance", "dayAvgDistance", "unit", "count", "index", "weekStart", "dayOfWeek", "yMax", "yDefaultHeight", "i", "this", "showChartAnimator", "list", "startCalendar", "map", "size", "item", Constants.AppPackage.CALENDAR, "distance", "dayAvgDistance", "unit", "count", "index", "weekStart", "dayOfWeek", "yMax", "yDefaultHeight", "i", "this", "showChartAnimator", "list", "startCalendar", "map", "size", "item", Constants.AppPackage.CALENDAR, "distance", "dayAvgDistance", "unit", "count", "index", "weekStart", "dayOfWeek"}, s = {"L$0", "Z$0", "L$1", "L$2", "L$3", "I$0", "L$4", "L$5", "F$0", "F$1", "I$1", "I$2", "I$3", "I$4", "I$5", "F$2", "F$3", "I$6", "L$0", "Z$0", "L$1", "L$2", "L$3", "I$0", "L$4", "L$5", "F$0", "F$1", "I$1", "I$2", "I$3", "I$4", "I$5", "F$2", "F$3", "I$6", "L$0", "Z$0", "L$1", "L$2", "L$3", "I$0", "L$4", "L$5", "F$0", "F$1", "I$1", "I$2", "I$3", "I$4", "I$5", "F$2", "F$3", "I$6", "L$0", "Z$0", "L$1", "L$2", "L$3", "I$0", "L$4", "L$5", "F$0", "F$1", "I$1", "I$2", "I$3", "I$4", "I$5"})
    static final class AnonymousClass1 extends ContinuationImpl {
        float F$0;
        float F$1;
        float F$2;
        float F$3;
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        int I$4;
        int I$5;
        int I$6;
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
            return DistanceDetailPresenter.this.convertDataListToUi(false, null, this);
        }
    }

    public final int getDefaultYMinValue() {
        return 0;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DistanceDetailPresenter(IDistanceDetailView iView) {
        super(iView);
        Intrinsics.checkParameterIsNotNull(iView, "iView");
        this.mSportDistanceList = new ArrayList();
        this.mChartList = new ArrayList();
        this.mGson = new Gson();
        this.mDefaultBarColor = Color.parseColor("#66979797");
        this.mDefaultBarRadius = 0.02f;
    }

    public final float getMSportTotalDistance() {
        return this.mSportTotalDistance;
    }

    public final float getMSportAvgDistance() {
        return this.mSportAvgDistance;
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

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    protected void getDetailByMonth(String start, String end) {
        super.getDetailByMonth(start, end);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    protected void getDetailByYear(String start, String end) {
        super.getDetailByYear(start, end);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0077  */
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected java.lang.Object readLocalDayData(boolean r5, kotlin.coroutines.Continuation<? super kotlin.Unit> r6) {
        /*
            r4 = this;
            long r0 = r4.getUserId()
            java.lang.String r2 = r4.getMStartDate()
            com.ido.life.database.model.SportDistanceBean r0 = com.ido.life.util.GreenDaoUtil.querySportDistanceByDate(r0, r2)
            r1 = 0
            if (r0 == 0) goto L77
            java.lang.String r2 = r0.getItems()
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            if (r2 == 0) goto L20
            int r2 = r2.length()
            if (r2 != 0) goto L1e
            goto L20
        L1e:
            r2 = 0
            goto L21
        L20:
            r2 = 1
        L21:
            if (r2 != 0) goto L77
            float r2 = r0.getTotalDistance()
            r3 = 1000(0x3e8, float:1.401E-42)
            float r3 = (float) r3
            float r2 = r2 / r3
            r4.mSportTotalDistance = r2
            int r2 = r4.getUnitSet()
            r3 = 2
            if (r2 != r3) goto L44
            float r2 = r4.mSportAvgDistance
            float r2 = com.ido.life.util.UnitUtil.km2mile(r2)
            r4.mSportAvgDistance = r2
            float r2 = r4.mSportTotalDistance
            float r2 = com.ido.life.util.UnitUtil.km2mile(r2)
            r4.mSportTotalDistance = r2
        L44:
            com.google.gson.Gson r2 = r4.mGson
            java.lang.String r0 = r0.getItems()
            com.ido.life.module.home.distance.DistanceDetailPresenter$readLocalDayData$2 r3 = new com.ido.life.module.home.distance.DistanceDetailPresenter$readLocalDayData$2
            r3.<init>()
            java.lang.reflect.Type r3 = r3.getType()
            java.lang.Object r0 = r2.fromJson(r0, r3)
            java.util.List r0 = (java.util.List) r0
            java.util.List r0 = r4.convertFrom(r0)
            r4.mChartList = r0
            kotlinx.coroutines.MainCoroutineDispatcher r0 = kotlinx.coroutines.Dispatchers.getMain()
            kotlin.coroutines.CoroutineContext r0 = (kotlin.coroutines.CoroutineContext) r0
            com.ido.life.module.home.distance.DistanceDetailPresenter$readLocalDayData$3 r2 = new com.ido.life.module.home.distance.DistanceDetailPresenter$readLocalDayData$3
            r2.<init>(r5, r1)
            kotlin.jvm.functions.Function2 r2 = (kotlin.jvm.functions.Function2) r2
            java.lang.Object r5 = kotlinx.coroutines.BuildersKt.withContext(r0, r2, r6)
            java.lang.Object r6 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r5 != r6) goto L8f
            return r5
        L77:
            kotlinx.coroutines.MainCoroutineDispatcher r5 = kotlinx.coroutines.Dispatchers.getMain()
            kotlin.coroutines.CoroutineContext r5 = (kotlin.coroutines.CoroutineContext) r5
            com.ido.life.module.home.distance.DistanceDetailPresenter$readLocalDayData$4 r0 = new com.ido.life.module.home.distance.DistanceDetailPresenter$readLocalDayData$4
            r0.<init>(r1)
            kotlin.jvm.functions.Function2 r0 = (kotlin.jvm.functions.Function2) r0
            java.lang.Object r5 = kotlinx.coroutines.BuildersKt.withContext(r5, r0, r6)
            java.lang.Object r6 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r5 != r6) goto L8f
            return r5
        L8f:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.home.distance.DistanceDetailPresenter.readLocalDayData(boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.distance.DistanceDetailPresenter$readLocalDayData$3, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DistanceDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.distance.DistanceDetailPresenter$readLocalDayData$3", f = "DistanceDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03063 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ boolean $showChartAnimator;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03063(boolean z, Continuation continuation) {
            super(2, continuation);
            this.$showChartAnimator = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03063 c03063 = DistanceDetailPresenter.this.new C03063(this.$showChartAnimator, completion);
            c03063.p$ = (CoroutineScope) obj;
            return c03063;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03063) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            if (!DistanceDetailPresenter.this.mChartList.isEmpty()) {
                DistanceDetailPresenter.this.getMView().onLoadSuccessByDay(this.$showChartAnimator, DistanceDetailPresenter.this.mChartList);
            } else {
                DistanceDetailPresenter.this.getMView().onDetailLoadFailed();
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.distance.DistanceDetailPresenter$readLocalDayData$4, reason: invalid class name */
    /* JADX INFO: compiled from: DistanceDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.distance.DistanceDetailPresenter$readLocalDayData$4", f = "DistanceDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass4 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        AnonymousClass4(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            AnonymousClass4 anonymousClass4 = DistanceDetailPresenter.this.new AnonymousClass4(completion);
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
            DistanceDetailPresenter.this.getMView().onDetailLoadFailed();
            return Unit.INSTANCE;
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    protected Object readLocalWeekData(boolean z, Continuation<? super Unit> continuation) throws Throwable {
        List<SportDistanceBean> listQueryDistanceByDateArea = GreenDaoUtil.queryDistanceByDateArea(getUserId(), getMStartDate(), getMEndDate());
        if (listQueryDistanceByDateArea != null && listQueryDistanceByDateArea.size() > 0) {
            Object objConvertDataListToUi = convertDataListToUi(z, listQueryDistanceByDateArea, continuation);
            if (objConvertDataListToUi == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                return objConvertDataListToUi;
            }
        } else {
            Object objWithContext = BuildersKt.withContext(Dispatchers.getMain(), new C03082(null), continuation);
            if (objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                return objWithContext;
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.distance.DistanceDetailPresenter$readLocalWeekData$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DistanceDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.distance.DistanceDetailPresenter$readLocalWeekData$2", f = "DistanceDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03082 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        C03082(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03082 c03082 = DistanceDetailPresenter.this.new C03082(completion);
            c03082.p$ = (CoroutineScope) obj;
            return c03082;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03082) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            DistanceDetailPresenter.this.getMView().onDetailLoadFailed();
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
        List<SportDistanceBean> listQueryDistanceByMonth = GreenDaoUtil.queryDistanceByMonth(getUserId(), Integer.parseInt((String) listSplit$default.get(0)), Integer.parseInt((String) listSplit$default.get(1)));
        if (listQueryDistanceByMonth != null && listQueryDistanceByMonth.size() > 0) {
            Object objConvertDataListToUi = convertDataListToUi(z, listQueryDistanceByMonth, continuation);
            if (objConvertDataListToUi == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                return objConvertDataListToUi;
            }
        } else {
            Object objWithContext = BuildersKt.withContext(Dispatchers.getMain(), new C03072(null), continuation);
            if (objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                return objWithContext;
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.distance.DistanceDetailPresenter$readLocalMonthData$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DistanceDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.distance.DistanceDetailPresenter$readLocalMonthData$2", f = "DistanceDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03072 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        C03072(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03072 c03072 = DistanceDetailPresenter.this.new C03072(completion);
            c03072.p$ = (CoroutineScope) obj;
            return c03072;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03072) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            DistanceDetailPresenter.this.getMView().onDetailLoadFailed();
            return Unit.INSTANCE;
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    protected Object readLocalYearData(boolean z, Continuation<? super Unit> continuation) throws Throwable {
        String mStartDate = getMStartDate();
        if (mStartDate == null) {
            Intrinsics.throwNpe();
        }
        if (mStartDate == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String strSubstring = mStartDate.substring(0, 4);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        List<SportDistanceBean> listQueryDistanceByYear = GreenDaoUtil.queryDistanceByYear(getUserId(), Integer.parseInt(strSubstring));
        if (listQueryDistanceByYear != null && listQueryDistanceByYear.size() > 0) {
            Object objConvertDataListToUi = convertDataListToUi(z, combineYearList(listQueryDistanceByYear), continuation);
            if (objConvertDataListToUi == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                return objConvertDataListToUi;
            }
        } else {
            Object objWithContext = BuildersKt.withContext(Dispatchers.getMain(), new C03092(null), continuation);
            if (objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                return objWithContext;
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.distance.DistanceDetailPresenter$readLocalYearData$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: DistanceDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.distance.DistanceDetailPresenter$readLocalYearData$2", f = "DistanceDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03092 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        C03092(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03092 c03092 = DistanceDetailPresenter.this.new C03092(completion);
            c03092.p$ = (CoroutineScope) obj;
            return c03092;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03092) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            DistanceDetailPresenter.this.getMView().onDetailLoadFailed();
            return Unit.INSTANCE;
        }
    }

    private final List<BarChartPoint> convertFrom(List<? extends List<Integer>> list) {
        float fFloatValue;
        ArrayList arrayList = new ArrayList();
        ensureActive();
        if (list != null && list.size() == 24) {
            int unitSet = getUnitSet();
            this.mSportAvgDistance = 0.0f;
            this.mMaxDistance = 0.0f;
            int size = list.size();
            int i = 0;
            int i2 = 0;
            while (true) {
                if (i >= size) {
                    break;
                }
                ensureActive();
                List<Integer> list2 = list.get(i);
                if (list2.get(1).intValue() > 0) {
                    if (unitSet == 2) {
                        fFloatValue = UnitUtil.km2mile((list2.get(1).floatValue() * 1.0f) / 1000);
                    } else {
                        fFloatValue = (list2.get(1).floatValue() * 1.0f) / 1000;
                    }
                    float f2 = ((double) fFloatValue) >= 0.005d ? fFloatValue : 0.01f;
                    arrayList.add(new BarChartPoint(list2.get(0).intValue(), list2.get(0).intValue() + 1, f2));
                    this.mMaxDistance = Math.max(this.mMaxDistance, f2);
                    i2++;
                } else {
                    arrayList.add(new BarChartPoint(list2.get(0).intValue(), list2.get(0).intValue() + 1, 0.0f));
                }
                i++;
            }
            float f3 = this.mSportTotalDistance;
            if (i2 > 0) {
                f3 /= i2;
            }
            this.mSportAvgDistance = f3;
            float fCaluteYMaxDistance = caluteYMaxDistance() * this.mDefaultBarRadius;
            this.mDefaultHeight = fCaluteYMaxDistance;
            int size2 = arrayList.size();
            for (int i3 = 0; i3 < size2; i3++) {
                ensureActive();
                BarChartPoint barChartPoint = (BarChartPoint) arrayList.get(i3);
                if (barChartPoint.y == 0.0f) {
                    barChartPoint.y = fCaluteYMaxDistance;
                    barChartPoint.setBarColor(this.mDefaultBarColor);
                    barChartPoint.setActualValue(0.0f);
                } else if (barChartPoint.y < fCaluteYMaxDistance) {
                    barChartPoint.y = fCaluteYMaxDistance + 0.01f;
                }
            }
        }
        return arrayList;
    }

    private final List<SportDistanceBean> combineServerAndLocalData(List<SportDistanceBean> serverList, List<SportDistanceBean> localList) {
        List<SportDistanceBean> list = serverList;
        boolean z = true;
        if (list == null || list.isEmpty()) {
            return localList;
        }
        List<SportDistanceBean> list2 = localList;
        if (list2 != null && !list2.isEmpty()) {
            z = false;
        }
        if (z) {
            return serverList;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        int size = localList.size();
        for (int i = 0; i < size; i++) {
            String date = localList.get(i).getDate();
            Intrinsics.checkExpressionValueIsNotNull(date, "bean.date");
            linkedHashSet.add(date);
        }
        Iterator<SportDistanceBean> it = serverList.iterator();
        while (it.hasNext()) {
            SportDistanceBean next = it.next();
            if (next == null || TextUtils.isEmpty(next.getDate()) || linkedHashSet.contains(next.getDate())) {
                it.remove();
            }
        }
        if (serverList.size() > 0) {
            localList.addAll(list);
        }
        return localList;
    }

    private final List<SportDistanceBean> combineYearList(List<? extends SportDistanceBean> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        ensureActive();
        ArrayList arrayList = new ArrayList();
        this.mSportTotalDistance = 0.0f;
        int size = list.size();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int i = 0;
        int i2 = 0;
        while (true) {
            boolean z = true;
            if (i < size) {
                ensureActive();
                SportDistanceBean sportDistanceBean = list.get(i);
                if (sportDistanceBean != null) {
                    String date = sportDistanceBean.getDate();
                    if (date != null && date.length() != 0) {
                        z = false;
                    }
                    if (!z && sportDistanceBean.getTotalDistance() > 0) {
                        String date2 = sportDistanceBean.getDate();
                        Intrinsics.checkExpressionValueIsNotNull(date2, "distanceItem.date");
                        int length = sportDistanceBean.getDate().length() - 3;
                        if (date2 == null) {
                            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                        }
                        String strSubstring = date2.substring(0, length);
                        Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                        if (linkedHashMap.containsKey(strSubstring)) {
                            SportDistanceBean sportDistanceBean2 = (SportDistanceBean) linkedHashMap.get(strSubstring);
                            if (sportDistanceBean2 == null) {
                                Intrinsics.throwNpe();
                            }
                            sportDistanceBean2.setTotalDistance(sportDistanceBean2.getTotalDistance() + sportDistanceBean.getTotalDistance());
                            sportDistanceBean2.setDayAvgDistance(sportDistanceBean2.getDayAvgDistance() + 1.0f);
                        } else {
                            SportDistanceBean sportDistanceBeanM25clone = sportDistanceBean.m25clone();
                            Intrinsics.checkExpressionValueIsNotNull(sportDistanceBeanM25clone, "distanceItem.clone()");
                            sportDistanceBeanM25clone.setDayAvgDistance(1.0f);
                            linkedHashMap.put(strSubstring, sportDistanceBeanM25clone);
                        }
                        this.mSportTotalDistance += sportDistanceBean.getTotalDistance();
                        i2++;
                    }
                }
                i++;
            } else {
                if (!linkedHashMap.isEmpty()) {
                    for (SportDistanceBean sportDistanceBean3 : linkedHashMap.values()) {
                        ensureActive();
                        if (sportDistanceBean3.getDayAvgDistance() > 0) {
                            sportDistanceBean3.setDayAvgDistance(sportDistanceBean3.getTotalDistance() / sportDistanceBean3.getDayAvgDistance());
                            arrayList.add(sportDistanceBean3);
                        }
                    }
                }
                this.mSportTotalDistance /= 1000;
                if (i2 > 0) {
                    this.mSportAvgDistance = this.mSportTotalDistance / i2;
                }
                if (getUnitSet() == 2) {
                    this.mSportAvgDistance = UnitUtil.km2mile(this.mSportAvgDistance);
                    this.mSportTotalDistance = UnitUtil.km2mile(this.mSportTotalDistance);
                }
                return arrayList;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:139:0x02c7  */
    /* JADX WARN: Removed duplicated region for block: B:250:0x027a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final /* synthetic */ java.lang.Object convertDataListToUi(boolean r35, java.util.List<? extends com.ido.life.database.model.SportDistanceBean> r36, kotlin.coroutines.Continuation<? super kotlin.Unit> r37) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 1623
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.home.distance.DistanceDetailPresenter.convertDataListToUi(boolean, java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.distance.DistanceDetailPresenter$convertDataListToUi$3, reason: invalid class name */
    /* JADX INFO: compiled from: DistanceDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.distance.DistanceDetailPresenter$convertDataListToUi$3", f = "DistanceDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
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
            AnonymousClass3 anonymousClass3 = DistanceDetailPresenter.this.new AnonymousClass3(this.$showChartAnimator, completion);
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
            DistanceDetailPresenter.this.getMView().onLoadSuccessByWeek(this.$showChartAnimator, DistanceDetailPresenter.this.mChartList);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.distance.DistanceDetailPresenter$convertDataListToUi$5, reason: invalid class name */
    /* JADX INFO: compiled from: DistanceDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.distance.DistanceDetailPresenter$convertDataListToUi$5", f = "DistanceDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass5 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ boolean $showChartAnimator;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass5(boolean z, Continuation continuation) {
            super(2, continuation);
            this.$showChartAnimator = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            AnonymousClass5 anonymousClass5 = DistanceDetailPresenter.this.new AnonymousClass5(this.$showChartAnimator, completion);
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
            DistanceDetailPresenter.this.getMView().onLoadSuccessByMonth(this.$showChartAnimator, DistanceDetailPresenter.this.mChartList);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.distance.DistanceDetailPresenter$convertDataListToUi$7, reason: invalid class name */
    /* JADX INFO: compiled from: DistanceDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.distance.DistanceDetailPresenter$convertDataListToUi$7", f = "DistanceDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass7 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ boolean $showChartAnimator;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass7(boolean z, Continuation continuation) {
            super(2, continuation);
            this.$showChartAnimator = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            AnonymousClass7 anonymousClass7 = DistanceDetailPresenter.this.new AnonymousClass7(this.$showChartAnimator, completion);
            anonymousClass7.p$ = (CoroutineScope) obj;
            return anonymousClass7;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass7) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            DistanceDetailPresenter.this.getMView().onLoadSuccessByYear(this.$showChartAnimator, DistanceDetailPresenter.this.mChartList);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.distance.DistanceDetailPresenter$convertDataListToUi$8, reason: invalid class name */
    /* JADX INFO: compiled from: DistanceDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.distance.DistanceDetailPresenter$convertDataListToUi$8", f = "DistanceDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass8 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        AnonymousClass8(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            AnonymousClass8 anonymousClass8 = DistanceDetailPresenter.this.new AnonymousClass8(completion);
            anonymousClass8.p$ = (CoroutineScope) obj;
            return anonymousClass8;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass8) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            DistanceDetailPresenter.this.getMView().onDetailLoadFailed();
            return Unit.INSTANCE;
        }
    }

    public final List<String> getDefaultYLabelList(float yMaxValue) {
        ArrayList arrayList = new ArrayList();
        float f2 = yMaxValue / 4;
        for (int i = 0; i <= 4; i++) {
            arrayList.add(String.valueOf(i * f2));
        }
        return arrayList;
    }

    public final float calculateYMaxDistance() {
        int i;
        float f2 = this.mMaxDistance;
        if (f2 < 1) {
            return 1.0f;
        }
        if (f2 < 4) {
            return (((int) (f2 * 10)) % 10 < 5 ? 0.5f : 1.0f) + (r0 / 10);
        }
        if (f2 < 50) {
            i = (((int) (f2 * 10)) / 10) + 1;
        } else {
            if (f2 < 100) {
                return ((r0 / 10) * 10) + (((int) f2) % 10 > 5 ? 10 : 5);
            }
            i = ((((int) f2) / 10) * 10) + 10;
        }
        return i;
    }

    public final float caluteYMaxDistance() {
        int i;
        float f2 = this.mMaxDistance;
        if (f2 < 1) {
            return 1.0f;
        }
        if (f2 < 4) {
            return (((int) (f2 * 10)) % 10 < 5 ? 0.5f : 1.0f) + (r0 / 10);
        }
        if (f2 < 50) {
            i = (((int) (f2 * 10)) / 10) + 1;
        } else {
            if (f2 < 100) {
                return ((r0 / 10) * 10) + (((int) f2) % 10 > 5 ? 10 : 5);
            }
            i = ((((int) f2) / 10) * 10) + 10;
        }
        return i;
    }

    public final float getDefaultYMaxValue() {
        return caluteYMaxDistance();
    }

    public final List<SportDistanceBean> getSportList() {
        return this.mSportDistanceList;
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
        float fCaluteYMaxDistance = caluteYMaxDistance() * this.mDefaultBarRadius;
        this.mDefaultHeight = fCaluteYMaxDistance;
        for (int i = 1; i < actualMaximum; i++) {
            arrayList.add(new BarChartPoint(i - 1, i, fCaluteYMaxDistance, 0.0f, this.mDefaultBarColor));
        }
        return arrayList;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    protected void clearData() {
        this.mChartList.clear();
        this.mSportDistanceList.clear();
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    protected void onTypeChanged() {
        super.onTypeChanged();
        this.mMaxDistance = 0.0f;
        this.mSportAvgDistance = this.mMaxDistance;
        this.mSportTotalDistance = this.mSportAvgDistance;
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
        return CollectionsKt.mutableListOf(SportDistanceBean.class.getSimpleName());
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    public boolean hasData(String date) {
        if (TextUtils.isEmpty(date)) {
            return false;
        }
        return GreenDaoUtil.hasDistanceData(getUserId(), date);
    }
}