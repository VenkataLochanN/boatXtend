package com.ido.life.module.home.oxygen;

import android.graphics.Color;
import android.graphics.Point;
import android.text.TextUtils;
import com.boat.Xtend.two.R;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPath;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.ResourceUtil;
import com.ido.life.bean.BaseCharBean;
import com.ido.life.constants.Constants;
import com.ido.life.customview.TrainingEffectProgressView;
import com.ido.life.database.model.HealthPressure;
import com.ido.life.database.model.SportHealth;
import com.ido.life.database.model.UserInfo;
import com.ido.life.log.SportLogHelper;
import com.ido.life.module.home.HomeHelperKt;
import com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter;
import com.ido.life.module.sport.bean.OxyGenProgressBean;
import com.ido.life.util.DateUtil;
import com.ido.life.util.GreenDaoUtil;
import com.realsil.sdk.dfu.DfuException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
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
import kotlin.math.MathKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: OxygenDetailPresenter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010!\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u0007\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000b\u0018\u000028\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0004\u0012\u00020\u00040\u0001:\u0001QB\r\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010\u0006J\b\u0010/\u001a\u000200H\u0014J\u001c\u00101\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00162\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0002H\u0002J \u00102\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u00162\u000e\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0002H\u0002J\u0010\u00103\u001a\u0004\u0018\u0001042\u0006\u00105\u001a\u00020\bJ\u0006\u00106\u001a\u00020\bJ\u000e\u00107\u001a\b\u0012\u0004\u0012\u0002080\u0016H\u0002J\u0016\u00109\u001a\u0010\u0012\f\u0012\n :*\u0004\u0018\u00010\b0\b0\u0016H\u0014J\u000e\u0010;\u001a\b\u0012\u0004\u0012\u0002080\u0016H\u0002J\u0006\u0010<\u001a\u000200J\f\u0010=\u001a\b\u0012\u0004\u0012\u00020>0\u0016J\u0016\u0010?\u001a\u00020\b2\u0006\u0010@\u001a\u00020\u00172\u0006\u0010A\u001a\u00020\u0017J\u0016\u0010B\u001a\u0012\u0012\u0004\u0012\u00020\u00170\rj\b\u0012\u0004\u0012\u00020\u0017`CJ\u0010\u0010D\u001a\u0004\u0018\u00010\u000e2\u0006\u0010E\u001a\u00020\u0017J\u0012\u0010F\u001a\u00020G2\b\u00105\u001a\u0004\u0018\u00010\bH\u0016J\u000e\u0010H\u001a\u0002002\u0006\u0010@\u001a\u00020\u0017J \u0010I\u001a\u0002002\u0006\u0010J\u001a\u00020\u00172\u0006\u0010A\u001a\u00020\u00172\u0006\u0010@\u001a\u00020\u0017H\u0002J\u0019\u0010K\u001a\u0002002\u0006\u0010L\u001a\u00020GH\u0094@ø\u0001\u0000¢\u0006\u0002\u0010MJ\u0019\u0010N\u001a\u0002002\u0006\u0010L\u001a\u00020GH\u0094@ø\u0001\u0000¢\u0006\u0002\u0010MJ\u0019\u0010O\u001a\u0002002\u0006\u0010L\u001a\u00020GH\u0094@ø\u0001\u0000¢\u0006\u0002\u0010MJ\u0019\u0010P\u001a\u0002002\u0006\u0010L\u001a\u00020GH\u0094@ø\u0001\u0000¢\u0006\u0002\u0010MR\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR \u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\b0\u00028F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u000bR\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R \u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0002X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u000b\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001c\u001a\u00020\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u0014\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00030\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00030\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010#\u001a\u00020$X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u0014\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00170\u0002X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010*\u001a\u00020\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\u001e\"\u0004\b,\u0010 R\u0014\u0010-\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006R"}, d2 = {"Lcom/ido/life/module/home/oxygen/OxygenDetailPresenter;", "Lcom/ido/life/module/home/chartdetail/vertical/BaseDetailPresenter;", "", "Lcom/ido/life/bean/BaseCharBean;", "Lcom/ido/life/module/home/oxygen/IOxygenDetailView;", "iView", "(Lcom/ido/life/module/home/oxygen/IOxygenDetailView;)V", "ageIn", "", "dayChartList", "getDayChartList", "()Ljava/util/List;", "lastTwoDays", "Ljava/util/ArrayList;", "Lcom/ido/life/database/model/SportHealth;", "getLastTwoDays", "()Ljava/util/ArrayList;", "setLastTwoDays", "(Ljava/util/ArrayList;)V", "leftLabelList", "getLeftLabelList", "leftLists", "", "", "list", "getList", "setList", "(Ljava/util/List;)V", "mAvgOxygen", "getMAvgOxygen", "()I", "setMAvgOxygen", "(I)V", "mChartList", "mDayChartList", "mDayLastOxygen", "", "getMDayLastOxygen", "()F", "setMDayLastOxygen", "(F)V", "mListOxygen", "mPosition", "getMPosition", "setMPosition", "mSportHealthDateArea", "vo2Total", "clearData", "", "combineDayData", "combineYearData", "convertOxygenDateToIntArray", "", "date", "getAgeIns", "getBoyOxygenDataList", "Lcom/ido/life/module/sport/bean/OxyGenProgressBean;", "getDataDownloadType", "kotlin.jvm.PlatformType", "getGirlOxygenDataList", "getLatelyDayData", "getOxygenList", "Lcom/ido/life/customview/TrainingEffectProgressView$DividerProperty;", "getOxygenProgress", "vo2max", "gender", "getOxygenates", "Lkotlin/collections/ArrayList;", "getSportHealthItem", "index", "hasData", "", "oxygenAction", "oxygenDetail", "position", "readLocalDayData", "showChartAnimator", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readLocalMonthData", "readLocalWeekData", "readLocalYearData", "SportHealthItem", "app_release"}, k = 1, mv = {1, 1, 16})
public final class OxygenDetailPresenter extends BaseDetailPresenter<List<? extends BaseCharBean>, List<? extends BaseCharBean>, List<? extends BaseCharBean>, List<? extends BaseCharBean>, IOxygenDetailView> {
    private String ageIn;
    private ArrayList<SportHealth> lastTwoDays;
    private List<Integer> leftLists;
    private List<? extends SportHealth> list;
    private int mAvgOxygen;
    private final List<BaseCharBean> mChartList;
    private final List<BaseCharBean> mDayChartList;
    private float mDayLastOxygen;
    private List<Integer> mListOxygen;
    private int mPosition;
    private final List<SportHealth> mSportHealthDateArea;
    private int vo2Total;

    /* JADX INFO: renamed from: com.ido.life.module.home.oxygen.OxygenDetailPresenter$readLocalDayData$1, reason: invalid class name */
    /* JADX INFO: compiled from: OxygenDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0094@"}, d2 = {"readLocalDayData", "", "showChartAnimator", "", "continuation", "Lkotlin/coroutines/Continuation;", ""}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.oxygen.OxygenDetailPresenter", f = "OxygenDetailPresenter.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1}, l = {DfuException.ERROR_REQUEST_MTU_NO_CALLBACK, 287}, m = "readLocalDayData", n = {"this", "showChartAnimator", "list", "dayList", "size", "totalTime", "totalOxygen", "sportHealth", "total", "this", "showChartAnimator", "list", "dayList"}, s = {"L$0", "Z$0", "L$1", "L$2", "L$3", "J$0", "J$1", "L$4", "I$0", "L$0", "Z$0", "L$1", "L$2"})
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        long J$0;
        long J$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
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
            return OxygenDetailPresenter.this.readLocalDayData(false, this);
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.oxygen.OxygenDetailPresenter$readLocalMonthData$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: OxygenDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0094@"}, d2 = {"readLocalMonthData", "", "showChartAnimator", "", "continuation", "Lkotlin/coroutines/Continuation;", ""}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.oxygen.OxygenDetailPresenter", f = "OxygenDetailPresenter.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1}, l = {407, 413}, m = "readLocalMonthData", n = {"this", "showChartAnimator", "dateList", "list", "combineDayData", "size", "sportHealthDay", "total", "this", "showChartAnimator", "dateList", "list"}, s = {"L$0", "Z$0", "L$1", "L$2", "L$3", "I$0", "L$4", "I$1", "L$0", "Z$0", "L$1", "L$2"})
    static final class C03601 extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        C03601(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return OxygenDetailPresenter.this.readLocalMonthData(false, this);
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.oxygen.OxygenDetailPresenter$readLocalWeekData$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: OxygenDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0094@"}, d2 = {"readLocalWeekData", "", "showChartAnimator", "", "continuation", "Lkotlin/coroutines/Continuation;", ""}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.oxygen.OxygenDetailPresenter", f = "OxygenDetailPresenter.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1}, l = {355, 362}, m = "readLocalWeekData", n = {"this", "showChartAnimator", "list", "combineDayData", "size", "sportHealth", Constants.AppPackage.CALENDAR, "total", "weekStart", "dayOfWeek", "this", "showChartAnimator", "list"}, s = {"L$0", "Z$0", "L$1", "L$2", "I$0", "L$3", "L$4", "I$1", "I$2", "I$3", "L$0", "Z$0", "L$1"})
    static final class C03621 extends ContinuationImpl {
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

        C03621(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return OxygenDetailPresenter.this.readLocalWeekData(false, this);
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.oxygen.OxygenDetailPresenter$readLocalYearData$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: OxygenDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0094@"}, d2 = {"readLocalYearData", "", "showChartAnimator", "", "continuation", "Lkotlin/coroutines/Continuation;", ""}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.oxygen.OxygenDetailPresenter", f = "OxygenDetailPresenter.kt", i = {0, 0, 0, 0, 0, 0, 1, 1, 1}, l = {450, 456}, m = "readLocalYearData", n = {"this", "showChartAnimator", "list", "size", "total", "sportHealthDay", "this", "showChartAnimator", "list"}, s = {"L$0", "Z$0", "L$1", "I$0", "I$1", "L$2", "L$0", "Z$0", "L$1"})
    static final class C03651 extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        C03651(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return OxygenDetailPresenter.this.readLocalYearData(false, this);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OxygenDetailPresenter(IOxygenDetailView iView) {
        super(iView);
        Intrinsics.checkParameterIsNotNull(iView, "iView");
        this.mSportHealthDateArea = new ArrayList();
        this.lastTwoDays = new ArrayList<>();
        this.list = new ArrayList();
        this.mDayChartList = new ArrayList();
        this.mChartList = new ArrayList();
        this.ageIn = "";
        this.mListOxygen = CollectionsKt.emptyList();
        this.leftLists = new ArrayList();
    }

    public final ArrayList<SportHealth> getLastTwoDays() {
        return this.lastTwoDays;
    }

    public final void setLastTwoDays(ArrayList<SportHealth> arrayList) {
        Intrinsics.checkParameterIsNotNull(arrayList, "<set-?>");
        this.lastTwoDays = arrayList;
    }

    public final List<SportHealth> getList() {
        return this.list;
    }

    public final void setList(List<? extends SportHealth> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.list = list;
    }

    public final int getMAvgOxygen() {
        return this.mAvgOxygen;
    }

    public final void setMAvgOxygen(int i) {
        this.mAvgOxygen = i;
    }

    public final float getMDayLastOxygen() {
        return this.mDayLastOxygen;
    }

    public final void setMDayLastOxygen(float f2) {
        this.mDayLastOxygen = f2;
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00da A[LOOP:0: B:45:0x00d4->B:47:0x00da, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.util.ArrayList<java.lang.Integer> getOxygenates() {
        /*
            Method dump skipped, instruction units count: 237
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.home.oxygen.OxygenDetailPresenter.getOxygenates():java.util.ArrayList");
    }

    /* JADX INFO: renamed from: getAgeIns, reason: from getter */
    public final String getAgeIn() {
        return this.ageIn;
    }

    public final void getLatelyDayData() {
        String strReplace$default;
        String strReplace$default2;
        String strReplace$default3;
        if (!this.lastTwoDays.isEmpty()) {
            return;
        }
        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(1);
        calendar.add(5, 1);
        String endDate = DateUtil.format(calendar, DateUtil.DATE_FORMAT_YMD);
        calendar.add(2, -3);
        String startDate = DateUtil.format(calendar, DateUtil.DATE_FORMAT_YMD);
        long userId = getUserId();
        Intrinsics.checkExpressionValueIsNotNull(startDate, "startDate");
        Intrinsics.checkExpressionValueIsNotNull(endDate, "endDate");
        List<SportHealth> listQueryOxygenByDateArea = HomeHelperKt.queryOxygenByDateArea(userId, startDate, endDate);
        List<SportHealth> list = listQueryOxygenByDateArea;
        if (list == null || list.isEmpty()) {
            return;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (SportHealth sportHealth : listQueryOxygenByDateArea) {
            String dateTime = sportHealth.getDateTime();
            Intrinsics.checkExpressionValueIsNotNull(dateTime, "itemSport.dateTime");
            if (dateTime == null) {
                throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }
            String strSubstring = dateTime.substring(0, 10);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            Pair pair = (Pair) linkedHashMap.get(strSubstring);
            if (pair == null) {
                if (linkedHashMap.size() == 2) {
                    break;
                }
                pair = new Pair(Integer.valueOf(linkedHashMap.size()), new Point(0, 0));
                linkedHashMap.put(strSubstring, pair);
            }
            ((Point) pair.getSecond()).x += sportHealth.getVo2max();
            ((Point) pair.getSecond()).y++;
        }
        if (!linkedHashMap.isEmpty()) {
            if (linkedHashMap.size() == 1) {
                Pair pair2 = (Pair) CollectionsKt.first(linkedHashMap.values());
                SportHealth sportHealth2 = new SportHealth();
                String str = (String) CollectionsKt.first(linkedHashMap.keySet());
                String str2 = str;
                int iIndexOf$default = StringsKt.indexOf$default((CharSequence) str2, "-", 0, false, 6, (Object) null);
                if (str == null) {
                    throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                }
                String strSubstring2 = str.substring(0, iIndexOf$default);
                Intrinsics.checkNotNullExpressionValue(strSubstring2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                if (Integer.parseInt(strSubstring2) == i) {
                    int iIndexOf$default2 = StringsKt.indexOf$default((CharSequence) str2, "-", 0, false, 6, (Object) null) + 1;
                    if (str == null) {
                        throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                    }
                    String strSubstring3 = str.substring(iIndexOf$default2);
                    Intrinsics.checkNotNullExpressionValue(strSubstring3, "(this as java.lang.String).substring(startIndex)");
                    strReplace$default3 = StringsKt.replace$default(strSubstring3, "-", "/", false, 4, (Object) null);
                } else {
                    strReplace$default3 = StringsKt.replace$default(str, "-", "/", false, 4, (Object) null);
                }
                sportHealth2.setDateTime(strReplace$default3);
                sportHealth2.setVo2max(MathKt.roundToInt(((Point) pair2.getSecond()).x / ((Point) pair2.getSecond()).y));
                this.lastTwoDays.add(sportHealth2);
                return;
            }
            this.lastTwoDays.add(new SportHealth());
            this.lastTwoDays.add(new SportHealth());
            for (Map.Entry entry : linkedHashMap.entrySet()) {
                SportHealth sportHealth3 = (SportHealth) (((Number) ((Pair) entry.getValue()).getFirst()).intValue() == 0 ? CollectionsKt.first((List) this.lastTwoDays) : CollectionsKt.last((List) this.lastTwoDays));
                sportHealth3.setDateTime((String) entry.getKey());
                sportHealth3.setVo2max(MathKt.roundToInt(((Point) ((Pair) entry.getValue()).getSecond()).x / ((Point) ((Pair) entry.getValue()).getSecond()).y));
            }
            SportHealth sportHealth4 = (SportHealth) CollectionsKt.first((List) this.lastTwoDays);
            SportHealth sportHealth5 = (SportHealth) CollectionsKt.last((List) this.lastTwoDays);
            String dateTime2 = sportHealth4.getDateTime();
            Intrinsics.checkExpressionValueIsNotNull(dateTime2, "firstSportHealth.dateTime");
            String dateTime3 = sportHealth4.getDateTime();
            Intrinsics.checkExpressionValueIsNotNull(dateTime3, "firstSportHealth.dateTime");
            int iIndexOf$default3 = StringsKt.indexOf$default((CharSequence) dateTime3, "-", 0, false, 6, (Object) null);
            if (dateTime2 == null) {
                throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }
            String strSubstring4 = dateTime2.substring(0, iIndexOf$default3);
            Intrinsics.checkNotNullExpressionValue(strSubstring4, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            int i2 = Integer.parseInt(strSubstring4);
            String dateTime4 = sportHealth5.getDateTime();
            Intrinsics.checkExpressionValueIsNotNull(dateTime4, "lastSportHealth.dateTime");
            String dateTime5 = sportHealth5.getDateTime();
            Intrinsics.checkExpressionValueIsNotNull(dateTime5, "lastSportHealth.dateTime");
            int iIndexOf$default4 = StringsKt.indexOf$default((CharSequence) dateTime5, "-", 0, false, 6, (Object) null);
            if (dateTime4 == null) {
                throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }
            String strSubstring5 = dateTime4.substring(0, iIndexOf$default4);
            Intrinsics.checkNotNullExpressionValue(strSubstring5, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            int i3 = Integer.parseInt(strSubstring5);
            if (i == i2) {
                String dateTime6 = sportHealth4.getDateTime();
                Intrinsics.checkExpressionValueIsNotNull(dateTime6, "firstSportHealth.dateTime");
                String dateTime7 = sportHealth4.getDateTime();
                Intrinsics.checkExpressionValueIsNotNull(dateTime7, "firstSportHealth.dateTime");
                int iIndexOf$default5 = StringsKt.indexOf$default((CharSequence) dateTime7, "-", 0, false, 6, (Object) null) + 1;
                if (dateTime6 == null) {
                    throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                }
                String strSubstring6 = dateTime6.substring(iIndexOf$default5);
                Intrinsics.checkNotNullExpressionValue(strSubstring6, "(this as java.lang.String).substring(startIndex)");
                strReplace$default = StringsKt.replace$default(strSubstring6, "-", "/", false, 4, (Object) null);
            } else {
                String dateTime8 = sportHealth4.getDateTime();
                Intrinsics.checkExpressionValueIsNotNull(dateTime8, "firstSportHealth.dateTime");
                strReplace$default = StringsKt.replace$default(dateTime8, "-", "/", false, 4, (Object) null);
            }
            sportHealth4.setDateTime(strReplace$default);
            if (i == i3) {
                String dateTime9 = sportHealth5.getDateTime();
                Intrinsics.checkExpressionValueIsNotNull(dateTime9, "lastSportHealth.dateTime");
                String dateTime10 = sportHealth5.getDateTime();
                Intrinsics.checkExpressionValueIsNotNull(dateTime10, "lastSportHealth.dateTime");
                int iIndexOf$default6 = StringsKt.indexOf$default((CharSequence) dateTime10, "-", 0, false, 6, (Object) null) + 1;
                if (dateTime9 == null) {
                    throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                }
                String strSubstring7 = dateTime9.substring(iIndexOf$default6);
                Intrinsics.checkNotNullExpressionValue(strSubstring7, "(this as java.lang.String).substring(startIndex)");
                strReplace$default2 = StringsKt.replace$default(strSubstring7, "-", "/", false, 4, (Object) null);
            } else {
                String dateTime11 = sportHealth5.getDateTime();
                Intrinsics.checkExpressionValueIsNotNull(dateTime11, "lastSportHealth.dateTime");
                strReplace$default2 = StringsKt.replace$default(dateTime11, "-", "/", false, 4, (Object) null);
            }
            sportHealth5.setDateTime(strReplace$default2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x001a  */
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected java.lang.Object readLocalDayData(boolean r27, kotlin.coroutines.Continuation<? super kotlin.Unit> r28) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 746
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.home.oxygen.OxygenDetailPresenter.readLocalDayData(boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.oxygen.OxygenDetailPresenter$readLocalDayData$3, reason: invalid class name */
    /* JADX INFO: compiled from: OxygenDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.oxygen.OxygenDetailPresenter$readLocalDayData$3", f = "OxygenDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ boolean $showChartAnimator;
        final /* synthetic */ Ref.IntRef $size;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(Ref.IntRef intRef, boolean z, Continuation continuation) {
            super(2, continuation);
            this.$size = intRef;
            this.$showChartAnimator = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            AnonymousClass3 anonymousClass3 = OxygenDetailPresenter.this.new AnonymousClass3(this.$size, this.$showChartAnimator, completion);
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
            if (this.$size.element > 0) {
                LogPath logPathImpl = LogPathImpl.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
                CommonLogUtil.printAndSave(logPathImpl.getLogPath(), OxygenDetailPresenter.this.getTAG(), "onLoadSuccessBydate");
                List list = OxygenDetailPresenter.this.mDayChartList;
                if (list.size() > 1) {
                    CollectionsKt.sortWith(list, new Comparator<T>() { // from class: com.ido.life.module.home.oxygen.OxygenDetailPresenter$readLocalDayData$3$invokeSuspend$$inlined$sortBy$1
                        /* JADX WARN: Multi-variable type inference failed */
                        @Override // java.util.Comparator
                        public final int compare(T t, T t2) {
                            return ComparisonsKt.compareValues(Float.valueOf(((BaseCharBean) t).x), Float.valueOf(((BaseCharBean) t2).x));
                        }
                    });
                }
                OxygenDetailPresenter oxygenDetailPresenter = OxygenDetailPresenter.this;
                oxygenDetailPresenter.setMDayLastOxygen(((BaseCharBean) oxygenDetailPresenter.mDayChartList.get(OxygenDetailPresenter.this.mDayChartList.size() - 1)).y);
                OxygenDetailPresenter.this.getMView().onLoadSuccessByDay(this.$showChartAnimator, OxygenDetailPresenter.this.mDayChartList);
            } else {
                OxygenDetailPresenter.this.getMView().onDetailLoadFailed();
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.oxygen.OxygenDetailPresenter$readLocalDayData$4, reason: invalid class name */
    /* JADX INFO: compiled from: OxygenDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.oxygen.OxygenDetailPresenter$readLocalDayData$4", f = "OxygenDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass4 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        AnonymousClass4(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            AnonymousClass4 anonymousClass4 = OxygenDetailPresenter.this.new AnonymousClass4(completion);
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
            OxygenDetailPresenter.this.getMView().onDetailLoadFailed();
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x001a  */
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected java.lang.Object readLocalWeekData(boolean r18, kotlin.coroutines.Continuation<? super kotlin.Unit> r19) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 450
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.home.oxygen.OxygenDetailPresenter.readLocalWeekData(boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.oxygen.OxygenDetailPresenter$readLocalWeekData$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: OxygenDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.oxygen.OxygenDetailPresenter$readLocalWeekData$2", f = "OxygenDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03632 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ boolean $showChartAnimator;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03632(boolean z, Continuation continuation) {
            super(2, continuation);
            this.$showChartAnimator = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03632 c03632 = OxygenDetailPresenter.this.new C03632(this.$showChartAnimator, completion);
            c03632.p$ = (CoroutineScope) obj;
            return c03632;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03632) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            List list = OxygenDetailPresenter.this.mChartList;
            if (list.size() > 1) {
                CollectionsKt.sortWith(list, new Comparator<T>() { // from class: com.ido.life.module.home.oxygen.OxygenDetailPresenter$readLocalWeekData$2$invokeSuspend$$inlined$sortBy$1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // java.util.Comparator
                    public final int compare(T t, T t2) {
                        return ComparisonsKt.compareValues(Float.valueOf(((BaseCharBean) t).x), Float.valueOf(((BaseCharBean) t2).x));
                    }
                });
            }
            SportLogHelper.saveSportLog(OxygenDetailPresenter.this.getTAG(), "week: onLoadSuccessByWeek");
            OxygenDetailPresenter.this.getMView().onLoadSuccessByWeek(this.$showChartAnimator, OxygenDetailPresenter.this.mChartList);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.oxygen.OxygenDetailPresenter$readLocalWeekData$3, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: OxygenDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.oxygen.OxygenDetailPresenter$readLocalWeekData$3", f = "OxygenDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03643 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        C03643(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03643 c03643 = OxygenDetailPresenter.this.new C03643(completion);
            c03643.p$ = (CoroutineScope) obj;
            return c03643;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03643) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            OxygenDetailPresenter.this.getMView().onDetailLoadFailed();
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x001a  */
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected java.lang.Object readLocalMonthData(boolean r21, kotlin.coroutines.Continuation<? super kotlin.Unit> r22) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 497
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.home.oxygen.OxygenDetailPresenter.readLocalMonthData(boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.oxygen.OxygenDetailPresenter$readLocalMonthData$2, reason: invalid class name */
    /* JADX INFO: compiled from: OxygenDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.oxygen.OxygenDetailPresenter$readLocalMonthData$2", f = "OxygenDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
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
            AnonymousClass2 anonymousClass2 = OxygenDetailPresenter.this.new AnonymousClass2(this.$showChartAnimator, completion);
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
            List list = OxygenDetailPresenter.this.mChartList;
            if (list.size() > 1) {
                CollectionsKt.sortWith(list, new Comparator<T>() { // from class: com.ido.life.module.home.oxygen.OxygenDetailPresenter$readLocalMonthData$2$invokeSuspend$$inlined$sortBy$1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // java.util.Comparator
                    public final int compare(T t, T t2) {
                        return ComparisonsKt.compareValues(Float.valueOf(((BaseCharBean) t).x), Float.valueOf(((BaseCharBean) t2).x));
                    }
                });
            }
            OxygenDetailPresenter.this.getMView().onLoadSuccessByMonth(this.$showChartAnimator, OxygenDetailPresenter.this.mChartList);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.oxygen.OxygenDetailPresenter$readLocalMonthData$3, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: OxygenDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.oxygen.OxygenDetailPresenter$readLocalMonthData$3", f = "OxygenDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03613 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        C03613(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03613 c03613 = OxygenDetailPresenter.this.new C03613(completion);
            c03613.p$ = (CoroutineScope) obj;
            return c03613;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03613) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            OxygenDetailPresenter.this.getMView().onDetailLoadFailed();
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x001a  */
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected java.lang.Object readLocalYearData(boolean r19, kotlin.coroutines.Continuation<? super kotlin.Unit> r20) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 414
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.home.oxygen.OxygenDetailPresenter.readLocalYearData(boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.oxygen.OxygenDetailPresenter$readLocalYearData$2, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: OxygenDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.oxygen.OxygenDetailPresenter$readLocalYearData$2", f = "OxygenDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03662 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ boolean $showChartAnimator;
        int label;
        private CoroutineScope p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C03662(boolean z, Continuation continuation) {
            super(2, continuation);
            this.$showChartAnimator = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03662 c03662 = OxygenDetailPresenter.this.new C03662(this.$showChartAnimator, completion);
            c03662.p$ = (CoroutineScope) obj;
            return c03662;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03662) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            List list = OxygenDetailPresenter.this.mChartList;
            if (list.size() > 1) {
                CollectionsKt.sortWith(list, new Comparator<T>() { // from class: com.ido.life.module.home.oxygen.OxygenDetailPresenter$readLocalYearData$2$invokeSuspend$$inlined$sortBy$1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // java.util.Comparator
                    public final int compare(T t, T t2) {
                        return ComparisonsKt.compareValues(Float.valueOf(((BaseCharBean) t).x), Float.valueOf(((BaseCharBean) t2).x));
                    }
                });
            }
            OxygenDetailPresenter.this.getMView().onLoadSuccessByYear(this.$showChartAnimator, OxygenDetailPresenter.this.mChartList);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: com.ido.life.module.home.oxygen.OxygenDetailPresenter$readLocalYearData$3, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: OxygenDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.ido.life.module.home.oxygen.OxygenDetailPresenter$readLocalYearData$3", f = "OxygenDetailPresenter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03673 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        C03673(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03673 c03673 = OxygenDetailPresenter.this.new C03673(completion);
            c03673.p$ = (CoroutineScope) obj;
            return c03673;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03673) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            OxygenDetailPresenter.this.getMView().onDetailLoadFailed();
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: compiled from: OxygenDetailPresenter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\t\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0007\"\u0004\b\u000b\u0010\t¨\u0006\f"}, d2 = {"Lcom/ido/life/module/home/oxygen/OxygenDetailPresenter$SportHealthItem;", "", "totalOxygen", "", "count", "(Lcom/ido/life/module/home/oxygen/OxygenDetailPresenter;II)V", "getCount", "()I", "setCount", "(I)V", "getTotalOxygen", "setTotalOxygen", "app_release"}, k = 1, mv = {1, 1, 16})
    private final class SportHealthItem {
        private int count;
        private int totalOxygen;

        public SportHealthItem(int i, int i2) {
            this.totalOxygen = i;
            this.count = i2;
        }

        public final int getTotalOxygen() {
            return this.totalOxygen;
        }

        public final void setTotalOxygen(int i) {
            this.totalOxygen = i;
        }

        public final int getCount() {
            return this.count;
        }

        public final void setCount(int i) {
            this.count = i;
        }
    }

    private final List<SportHealth> combineDayData(List<? extends SportHealth> list) {
        List<? extends SportHealth> list2 = list;
        if (list2 == null || list2.isEmpty()) {
            return new ArrayList();
        }
        ensureActive();
        ArrayList arrayList = new ArrayList();
        HashMap map = new HashMap();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            ensureActive();
            SportHealth sportHealth = list.get(i);
            if (!TextUtils.isEmpty(sportHealth.getDateTime()) && sportHealth.getVo2max() > 0) {
                try {
                    String dateTime = sportHealth.getDateTime();
                    Intrinsics.checkExpressionValueIsNotNull(dateTime, "item.dateTime");
                    int length = sportHealth.getDateTime().length() - 8;
                    if (dateTime == null) {
                        throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                    }
                    String strSubstring = dateTime.substring(0, length);
                    Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    SportLogHelper.saveSportLog("TAG", strSubstring);
                    if (!map.containsKey(strSubstring)) {
                        map.put(strSubstring, new SportHealthItem(sportHealth.getVo2max(), 1));
                    } else {
                        SportHealthItem sportHealthItem = (SportHealthItem) map.get(strSubstring);
                        if (sportHealthItem == null) {
                            Intrinsics.throwNpe();
                        }
                        sportHealthItem.setTotalOxygen(sportHealthItem.getTotalOxygen() + sportHealth.getVo2max());
                        sportHealthItem.setCount(sportHealthItem.getCount() + 1);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        if (!map.isEmpty()) {
            for (String str : map.keySet()) {
                ensureActive();
                SportHealthItem sportHealthItem2 = (SportHealthItem) map.get(str);
                SportHealth sportHealth2 = new SportHealth();
                if (sportHealthItem2 == null) {
                    Intrinsics.throwNpe();
                }
                sportHealth2.setVo2max(MathKt.roundToInt((sportHealthItem2.getTotalOxygen() * 1.0f) / sportHealthItem2.getCount()));
                if (str != null) {
                    sportHealth2.setDateTime(StringsKt.trim((CharSequence) str).toString());
                    arrayList.add(sportHealth2);
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.CharSequence");
                }
            }
        }
        return arrayList;
    }

    private final List<SportHealth> combineYearData(List<? extends SportHealth> list) {
        List<? extends SportHealth> list2 = list;
        if (list2 == null || list2.isEmpty()) {
            return null;
        }
        ensureActive();
        ArrayList arrayList = new ArrayList();
        HashMap map = new HashMap();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            ensureActive();
            SportHealth sportHealth = list.get(i);
            if (!TextUtils.isEmpty(sportHealth.getDateTime()) && sportHealth.getVo2max() > 0) {
                try {
                    StringBuilder sb = new StringBuilder();
                    String dateTime = sportHealth.getDateTime();
                    Intrinsics.checkExpressionValueIsNotNull(dateTime, "item.dateTime");
                    int length = sportHealth.getDateTime().length() - 11;
                    if (dateTime == null) {
                        throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                    }
                    String strSubstring = dateTime.substring(0, length);
                    Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    sb.append(strSubstring);
                    sb.append("01");
                    String string = sb.toString();
                    LogPath logPathImpl = LogPathImpl.getInstance();
                    Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
                    CommonLogUtil.printAndSave(logPathImpl.getLogPath(), getTAG(), string);
                    if (!map.containsKey(string)) {
                        map.put(string, new SportHealthItem(sportHealth.getVo2max(), 1));
                    } else {
                        SportHealthItem sportHealthItem = (SportHealthItem) map.get(string);
                        if (sportHealthItem == null) {
                            Intrinsics.throwNpe();
                        }
                        sportHealthItem.setTotalOxygen(sportHealthItem.getTotalOxygen() + sportHealth.getVo2max());
                        sportHealthItem.setCount(sportHealthItem.getCount() + 1);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        if (!map.isEmpty()) {
            for (String str : map.keySet()) {
                ensureActive();
                SportHealthItem sportHealthItem2 = (SportHealthItem) map.get(str);
                SportHealth sportHealth2 = new SportHealth();
                if (sportHealthItem2 == null) {
                    Intrinsics.throwNpe();
                }
                sportHealth2.setVo2max(MathKt.roundToInt((sportHealthItem2.getTotalOxygen() * 1.0f) / sportHealthItem2.getCount()));
                sportHealth2.setDateTime(str);
                arrayList.add(sportHealth2);
            }
        }
        return arrayList;
    }

    public final List<String> getLeftLabelList() {
        this.leftLists.clear();
        ArrayList arrayList = new ArrayList();
        this.vo2Total = 0;
        if (this.mSportHealthDateArea.size() > 0) {
            for (SportHealth sportHealth : this.mSportHealthDateArea) {
                if (sportHealth.getVo2max() != 0) {
                    this.leftLists.add(Integer.valueOf(sportHealth.getVo2max()));
                }
            }
            CollectionsKt.sort(this.leftLists);
            int iIntValue = ((this.leftLists.get(0).intValue() / 10) * 10) - 10;
            int iIntValue2 = ((this.leftLists.get(r5.size() - 1).intValue() / 10) * 10) + 10;
            if (iIntValue2 - this.leftLists.get(r6.size() - 1).intValue() < 10) {
                iIntValue2 += 10;
            }
            if (iIntValue <= iIntValue2) {
                while (true) {
                    if (iIntValue % 10 == 0) {
                        arrayList.add("" + iIntValue);
                    }
                    if (iIntValue == iIntValue2) {
                        break;
                    }
                    iIntValue++;
                }
            }
            LogPath logPathImpl = LogPathImpl.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
            CommonLogUtil.printAndSave(logPathImpl.getLogPath(), getTAG(), "11" + arrayList.size());
        } else {
            arrayList.add("30");
            arrayList.add("40");
            arrayList.add("50");
            arrayList.add("60");
        }
        LogPath logPathImpl2 = LogPathImpl.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(logPathImpl2, "LogPathImpl.getInstance()");
        CommonLogUtil.printAndSave(logPathImpl2.getLogPath(), getTAG(), "" + arrayList.size());
        return arrayList;
    }

    public final SportHealth getSportHealthItem(int index) {
        if (this.mSportHealthDateArea.size() <= index) {
            return null;
        }
        return this.mSportHealthDateArea.get(index);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailPresenter
    protected void clearData() {
        this.mDayChartList.clear();
        this.mChartList.clear();
    }

    public final List<BaseCharBean> getDayChartList() {
        return this.mDayChartList;
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

    public final void oxygenAction(int vo2max) {
        int ageByBirthday;
        UserInfo userInfo = GreenDaoUtil.queryUserInfo(getUserId());
        try {
            Intrinsics.checkExpressionValueIsNotNull(userInfo, "userInfo");
            ageByBirthday = DateUtil.getAgeByBirthday(userInfo.getBirthday(), DateUtil.DATE_FORMAT_YMD);
        } catch (Exception e2) {
            e2.printStackTrace();
            LogPath logPathImpl = LogPathImpl.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
            CommonLogUtil.printAndSave(logPathImpl.getLogPath(), getTAG(), "年龄异常");
            ageByBirthday = 30;
        }
        if (20 <= ageByBirthday && 29 >= ageByBirthday) {
            Intrinsics.checkExpressionValueIsNotNull(userInfo, "userInfo");
            oxygenDetail(0, userInfo.getGender(), vo2max);
            this.ageIn = "20 - 29";
            return;
        }
        if (30 <= ageByBirthday && 39 >= ageByBirthday) {
            Intrinsics.checkExpressionValueIsNotNull(userInfo, "userInfo");
            oxygenDetail(1, userInfo.getGender(), vo2max);
            this.ageIn = "30 - 39";
            return;
        }
        if (40 <= ageByBirthday && 49 >= ageByBirthday) {
            Intrinsics.checkExpressionValueIsNotNull(userInfo, "userInfo");
            oxygenDetail(2, userInfo.getGender(), vo2max);
            this.ageIn = "40 - 49";
            return;
        }
        if (50 <= ageByBirthday && 59 >= ageByBirthday) {
            Intrinsics.checkExpressionValueIsNotNull(userInfo, "userInfo");
            oxygenDetail(3, userInfo.getGender(), vo2max);
            this.ageIn = "50 - 59";
        } else if (60 <= ageByBirthday && 69 >= ageByBirthday) {
            Intrinsics.checkExpressionValueIsNotNull(userInfo, "userInfo");
            oxygenDetail(4, userInfo.getGender(), vo2max);
            this.ageIn = "60 - 69";
        } else if (70 <= ageByBirthday && 79 >= ageByBirthday) {
            Intrinsics.checkExpressionValueIsNotNull(userInfo, "userInfo");
            oxygenDetail(5, userInfo.getGender(), vo2max);
            this.ageIn = "70 - 79";
        }
    }

    private final void oxygenDetail(int position, int gender, int vo2max) {
        if (gender == 1) {
            List<Integer> list_oxygen = getBoyOxygenDataList().get(position).getList_oxygen();
            Intrinsics.checkExpressionValueIsNotNull(list_oxygen, "getBoyOxygenDataList()[position].list_oxygen");
            this.mListOxygen = list_oxygen;
            getOxygenProgress(vo2max, gender);
            return;
        }
        if (gender != 2) {
            return;
        }
        List<Integer> list_oxygen2 = getGirlOxygenDataList().get(position).getList_oxygen();
        Intrinsics.checkExpressionValueIsNotNull(list_oxygen2, "getGirlOxygenDataList()[position].list_oxygen");
        this.mListOxygen = list_oxygen2;
        getOxygenProgress(vo2max, gender);
    }

    public final int getMPosition() {
        return this.mPosition;
    }

    public final void setMPosition(int i) {
        this.mPosition = i;
    }

    public final String getOxygenProgress(int vo2max, int gender) {
        Iterator<T> it = this.mListOxygen.iterator();
        int i = 0;
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            if (vo2max < ((Number) it.next()).intValue()) {
                this.mPosition = i - 1;
                break;
            }
            i++;
        }
        if (this.mPosition == -1) {
            this.mPosition = 0;
        }
        if (vo2max == 0) {
            this.mPosition = -1;
            String languageText = LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_man_tip_no);
            Intrinsics.checkExpressionValueIsNotNull(languageText, "LanguageUtil.getLanguage…_oxygen_level_man_tip_no)");
            return languageText;
        }
        if (gender == 1) {
            int i2 = this.mPosition;
            if (i2 == 0) {
                String languageText2 = LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_man_tip_one);
                Intrinsics.checkExpressionValueIsNotNull(languageText2, "LanguageUtil.getLanguage…oxygen_level_man_tip_one)");
                return languageText2;
            }
            if (i2 == 1) {
                String languageText3 = LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_man_tip_two);
                Intrinsics.checkExpressionValueIsNotNull(languageText3, "LanguageUtil.getLanguage…oxygen_level_man_tip_two)");
                return languageText3;
            }
            if (i2 == 2) {
                String languageText4 = LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_man_ip_three);
                Intrinsics.checkExpressionValueIsNotNull(languageText4, "LanguageUtil.getLanguage…xygen_level_man_ip_three)");
                return languageText4;
            }
            if (i2 == 3) {
                String languageText5 = LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_man_tip_four);
                Intrinsics.checkExpressionValueIsNotNull(languageText5, "LanguageUtil.getLanguage…xygen_level_man_tip_four)");
                return languageText5;
            }
            if (i2 == 4) {
                String languageText6 = LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_man_tip_five);
                Intrinsics.checkExpressionValueIsNotNull(languageText6, "LanguageUtil.getLanguage…xygen_level_man_tip_five)");
                return languageText6;
            }
            if (i2 == 5) {
                String languageText7 = LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_man_tip_six);
                Intrinsics.checkExpressionValueIsNotNull(languageText7, "LanguageUtil.getLanguage…oxygen_level_man_tip_six)");
                return languageText7;
            }
        } else {
            int i3 = this.mPosition;
            if (i3 == 0) {
                String languageText8 = LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_woman_tip_one);
                Intrinsics.checkExpressionValueIsNotNull(languageText8, "LanguageUtil.getLanguage…ygen_level_woman_tip_one)");
                return languageText8;
            }
            if (i3 == 1) {
                String languageText9 = LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_woman_tip_two);
                Intrinsics.checkExpressionValueIsNotNull(languageText9, "LanguageUtil.getLanguage…ygen_level_woman_tip_two)");
                return languageText9;
            }
            if (i3 == 2) {
                String languageText10 = LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_woman_tip_three);
                Intrinsics.checkExpressionValueIsNotNull(languageText10, "LanguageUtil.getLanguage…en_level_woman_tip_three)");
                return languageText10;
            }
            if (i3 == 3) {
                String languageText11 = LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_woman_tip_four);
                Intrinsics.checkExpressionValueIsNotNull(languageText11, "LanguageUtil.getLanguage…gen_level_woman_tip_four)");
                return languageText11;
            }
            if (i3 == 4) {
                String languageText12 = LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_woman_tip_five);
                Intrinsics.checkExpressionValueIsNotNull(languageText12, "LanguageUtil.getLanguage…gen_level_woman_tip_five)");
                return languageText12;
            }
            if (i3 == 5) {
                String languageText13 = LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_woman_tip_six);
                Intrinsics.checkExpressionValueIsNotNull(languageText13, "LanguageUtil.getLanguage…ygen_level_woman_tip_six)");
                return languageText13;
            }
        }
        String languageText14 = LanguageUtil.getLanguageText(R.string.oxygen_detail_bottom_no_data);
        Intrinsics.checkExpressionValueIsNotNull(languageText14, "LanguageUtil.getLanguage…en_detail_bottom_no_data)");
        return languageText14;
    }

    private final List<OxyGenProgressBean> getBoyOxygenDataList() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new OxyGenProgressBean(1, 20, 29, CollectionsKt.listOf((Object[]) new Integer[]{26, 37, 41, 44, 50, 54, 70})));
        arrayList.add(new OxyGenProgressBean(1, 30, 39, CollectionsKt.listOf((Object[]) new Integer[]{26, 35, 39, 43, 47, 53, 58})));
        arrayList.add(new OxyGenProgressBean(1, 40, 49, CollectionsKt.listOf((Object[]) new Integer[]{25, 33, 37, 41, 45, 51, 56})));
        arrayList.add(new OxyGenProgressBean(1, 50, 59, CollectionsKt.listOf((Object[]) new Integer[]{22, 31, 34, 38, 42, 48, 54})));
        arrayList.add(new OxyGenProgressBean(1, 60, 69, CollectionsKt.listOf((Object[]) new Integer[]{19, 27, 31, 34, 38, 44, 51})));
        arrayList.add(new OxyGenProgressBean(1, 70, 79, CollectionsKt.listOf((Object[]) new Integer[]{18, 24, 28, 31, 35, 42, 49})));
        return arrayList;
    }

    private final List<OxyGenProgressBean> getGirlOxygenDataList() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new OxyGenProgressBean(2, 20, 29, CollectionsKt.listOf((Object[]) new Integer[]{23, 31, 35, 38, 42, 48, 54})));
        arrayList.add(new OxyGenProgressBean(2, 30, 39, CollectionsKt.listOf((Object[]) new Integer[]{22, 29, 33, 36, 41, 46, 52})));
        arrayList.add(new OxyGenProgressBean(2, 40, 49, CollectionsKt.listOf((Object[]) new Integer[]{22, 28, 32, 35, 38, 44, 51})));
        arrayList.add(new OxyGenProgressBean(2, 50, 59, CollectionsKt.listOf((Object[]) new Integer[]{20, 25, 29, 32, 35, 40, 46})));
        arrayList.add(new OxyGenProgressBean(2, 60, 69, CollectionsKt.listOf((Object[]) new Integer[]{19, 23, 26, 29, 32, 36, 42})));
        arrayList.add(new OxyGenProgressBean(2, 70, 79, CollectionsKt.listOf((Object[]) new Integer[]{16, 22, 25, 28, 30, 36, 42})));
        return arrayList;
    }

    public final List<TrainingEffectProgressView.DividerProperty> getOxygenList() {
        ArrayList arrayList = new ArrayList();
        int color = Color.parseColor("#E64C8B");
        float dimens = ResourceUtil.getDimens(R.dimen.sw_dp_3);
        float dimens2 = ResourceUtil.getDimens(R.dimen.sw_dp_1);
        String languageText = LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_one);
        Intrinsics.checkExpressionValueIsNotNull(languageText, "LanguageUtil.getLanguage…l_train_oxygen_level_one)");
        arrayList.add(new TrainingEffectProgressView.DividerProperty(color, dimens, dimens2, languageText));
        int color2 = Color.parseColor("#9067F2");
        float dimens3 = ResourceUtil.getDimens(R.dimen.sw_dp_3);
        float dimens4 = ResourceUtil.getDimens(R.dimen.sw_dp_1);
        String languageText2 = LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_two);
        Intrinsics.checkExpressionValueIsNotNull(languageText2, "LanguageUtil.getLanguage…l_train_oxygen_level_two)");
        arrayList.add(new TrainingEffectProgressView.DividerProperty(color2, dimens3, dimens4, languageText2));
        int color3 = Color.parseColor("#598EFF");
        float dimens5 = ResourceUtil.getDimens(R.dimen.sw_dp_3);
        float dimens6 = ResourceUtil.getDimens(R.dimen.sw_dp_1);
        String languageText3 = LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_three);
        Intrinsics.checkExpressionValueIsNotNull(languageText3, "LanguageUtil.getLanguage…train_oxygen_level_three)");
        arrayList.add(new TrainingEffectProgressView.DividerProperty(color3, dimens5, dimens6, languageText3));
        int color4 = Color.parseColor("#01B3FE");
        float dimens7 = ResourceUtil.getDimens(R.dimen.sw_dp_3);
        float dimens8 = ResourceUtil.getDimens(R.dimen.sw_dp_1);
        String languageText4 = LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_four);
        Intrinsics.checkExpressionValueIsNotNull(languageText4, "LanguageUtil.getLanguage…_train_oxygen_level_four)");
        arrayList.add(new TrainingEffectProgressView.DividerProperty(color4, dimens7, dimens8, languageText4));
        int color5 = Color.parseColor("#01CEFE");
        float dimens9 = ResourceUtil.getDimens(R.dimen.sw_dp_3);
        float dimens10 = ResourceUtil.getDimens(R.dimen.sw_dp_1);
        String languageText5 = LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_five);
        Intrinsics.checkExpressionValueIsNotNull(languageText5, "LanguageUtil.getLanguage…_train_oxygen_level_five)");
        arrayList.add(new TrainingEffectProgressView.DividerProperty(color5, dimens9, dimens10, languageText5));
        int color6 = Color.parseColor("#00F2FF");
        float dimens11 = ResourceUtil.getDimens(R.dimen.sw_dp_3);
        float dimens12 = ResourceUtil.getDimens(R.dimen.sw_dp_1);
        String languageText6 = LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_six);
        Intrinsics.checkExpressionValueIsNotNull(languageText6, "LanguageUtil.getLanguage…l_train_oxygen_level_six)");
        arrayList.add(new TrainingEffectProgressView.DividerProperty(color6, dimens11, dimens12, languageText6));
        return arrayList;
    }

    public final int[] convertOxygenDateToIntArray(String date) {
        Intrinsics.checkParameterIsNotNull(date, "date");
        String str = date;
        if (!TextUtils.isEmpty(str)) {
            if (StringsKt.contains$default((CharSequence) str, (CharSequence) "-", false, 2, (Object) null)) {
                String strSubstring = date.substring(0, 10);
                Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                Object[] array = StringsKt.split$default((CharSequence) strSubstring, new String[]{"-"}, false, 0, 6, (Object) null).toArray(new String[0]);
                if (array != null) {
                    String[] strArr = (String[]) array;
                    int[] iArr = (int[]) null;
                    int length = strArr.length;
                    if (length <= 0) {
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
}