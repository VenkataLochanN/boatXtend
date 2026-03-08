package com.ido.life.module.home.fitness;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import com.boat.Xtend.two.R;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPath;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.NetworkUtil;
import com.ido.life.base.BaseActivity;
import com.ido.life.bean.GradientBarPoint;
import com.ido.life.customview.charter.CustomChatBar;
import com.ido.life.customview.charter.GradientBarChartBar;
import com.ido.life.enums.StageInfoEnum;
import com.ido.life.module.device.activity.CommonWebViewActivity;
import com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback;
import com.ido.life.module.home.chartdetail.vertical.BaseDetailFragment;
import com.ido.life.module.home.chartdetail.vertical.BaseDetailViewHolder;
import com.ido.life.module.home.chartdetail.vertical.IChartDetailCallback;
import com.ido.life.module.home.recentsituation.RecentSituationDetailActivity;
import com.ido.life.util.ClickUtilKt;
import com.ido.life.util.RunTimeUtil;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import org.apache.commons.io.IOUtils;

/* JADX INFO: compiled from: FitnessDetailFragment.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000e\n\u0002\bU\n\u0002\u0018\u0002\n\u0002\b \u0018\u0000 \u009c\u000128\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0004\u0012\u00020\u00040\u00012\u00020\u00052\u00020\u00062\u00020\u0007:\u0002\u009c\u0001B\u0005¢\u0006\u0002\u0010\bJ\b\u0010\u000b\u001a\u00020\nH\u0016J\b\u0010\f\u001a\u00020\nH\u0016J\b\u0010\r\u001a\u00020\u000eH\u0002J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u000eH\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0015H\u0016J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u0010\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\b\u0010\u001c\u001a\u00020\u000eH\u0014J\b\u0010\u001d\u001a\u00020\u000eH\u0016J\b\u0010\u001e\u001a\u00020\u0004H\u0016J\n\u0010\u001f\u001a\u0004\u0018\u00010\u0012H\u0016J\u0010\u0010 \u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u0010\u0010!\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u0010\u0010\"\u001a\u00020\u00152\u0006\u0010#\u001a\u00020\nH\u0016J\b\u0010$\u001a\u00020\u0015H\u0014J\u0010\u0010%\u001a\u00020\u00152\u0006\u0010&\u001a\u00020'H\u0016J\u001e\u0010(\u001a\u00020\u00152\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010*\u001a\u00020\nH\u0016J\u001e\u0010+\u001a\u00020\u00152\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010*\u001a\u00020\nH\u0016J\u001e\u0010,\u001a\u00020\u00152\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010*\u001a\u00020\nH\u0016J\u001e\u0010-\u001a\u00020\u00152\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010*\u001a\u00020\nH\u0016J\u0010\u0010.\u001a\u00020\u00152\u0006\u0010&\u001a\u00020'H\u0016J\u001e\u0010/\u001a\u00020\u00152\f\u00100\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010*\u001a\u00020\nH\u0016J\u001e\u00101\u001a\u00020\u00152\f\u00100\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010*\u001a\u00020\nH\u0016J\u001e\u00102\u001a\u00020\u00152\f\u00100\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010*\u001a\u00020\nH\u0016J\u001e\u00103\u001a\u00020\u00152\f\u00100\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010*\u001a\u00020\nH\u0016J\b\u00104\u001a\u00020\u0015H\u0016J\u0012\u00105\u001a\u00020\u00152\b\u00106\u001a\u0004\u0018\u00010\u0012H\u0016J\b\u00107\u001a\u00020\u0015H\u0016J \u00108\u001a\u00020\u00152\u0006\u0010*\u001a\u00020\n2\u000e\u00109\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002H\u0016J \u0010:\u001a\u00020\u00152\u0006\u0010*\u001a\u00020\n2\u000e\u0010;\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002H\u0016J \u0010<\u001a\u00020\u00152\u0006\u0010*\u001a\u00020\n2\u000e\u0010;\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002H\u0016J \u0010=\u001a\u00020\u00152\u0006\u0010*\u001a\u00020\n2\u000e\u0010>\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002H\u0016J\b\u0010?\u001a\u00020\u0015H\u0016J\b\u0010@\u001a\u00020\u0015H\u0014J\u0010\u0010A\u001a\u00020\u00152\u0006\u0010&\u001a\u00020'H\u0016J\u001e\u0010B\u001a\u00020\u00152\f\u0010C\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010*\u001a\u00020\nH\u0016J\u001e\u0010D\u001a\u00020\u00152\f\u0010C\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010*\u001a\u00020\nH\u0016J\u001e\u0010E\u001a\u00020\u00152\f\u0010C\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010*\u001a\u00020\nH\u0016J\u001e\u0010F\u001a\u00020\u00152\f\u0010C\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010*\u001a\u00020\nH\u0016J\u0012\u0010G\u001a\u00020\u00152\b\u0010&\u001a\u0004\u0018\u00010'H\u0002J\u0010\u0010H\u001a\u00020\u00152\u0006\u0010I\u001a\u00020\u000eH\u0016J\u0010\u0010J\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u000eH\u0016J\b\u0010K\u001a\u00020\u0015H\u0016J\u0010\u0010L\u001a\u00020\u00152\u0006\u0010M\u001a\u00020\u000eH\u0016J\u0010\u0010N\u001a\u00020\u00152\u0006\u0010O\u001a\u00020\nH\u0016J\b\u0010P\u001a\u00020\u0015H\u0014J\b\u0010Q\u001a\u00020\u0015H\u0002J\u0010\u0010R\u001a\u00020\u00152\u0006\u0010S\u001a\u00020\u000eH\u0002J\u0018\u0010T\u001a\u00020\u00152\u0006\u0010U\u001a\u00020\u000e2\u0006\u0010V\u001a\u00020\u000eH\u0002J\u0010\u0010W\u001a\u00020\u00152\u0006\u0010X\u001a\u00020\u000eH\u0002J\u0018\u0010Y\u001a\u00020\u00152\u0006\u0010Z\u001a\u00020\u000e2\u0006\u0010[\u001a\u00020\u000eH\u0002J\u0010\u0010\\\u001a\u00020\u00152\u0006\u0010]\u001a\u00020\u000eH\u0002J\u0018\u0010^\u001a\u00020\u00152\u0006\u0010_\u001a\u00020\u000e2\u0006\u0010`\u001a\u00020\u000eH\u0002J\u0018\u0010a\u001a\u00020\u00152\u0006\u0010b\u001a\u00020\u000e2\u0006\u0010c\u001a\u00020\u000eH\u0002J\u0018\u0010d\u001a\u00020\u00152\u0006\u0010b\u001a\u00020\u000e2\u0006\u0010c\u001a\u00020\u000eH\u0002J\u0018\u0010e\u001a\u00020\u00152\u0006\u0010b\u001a\u00020\u000e2\u0006\u0010c\u001a\u00020\u000eH\u0002J\u0018\u0010f\u001a\u00020\u00152\u0006\u0010g\u001a\u00020\u000e2\u0006\u0010h\u001a\u00020\u000eH\u0002J\u0010\u0010i\u001a\u00020\u00152\u0006\u0010j\u001a\u00020\u000eH\u0016J\u0010\u0010k\u001a\u00020\u00152\u0006\u0010l\u001a\u00020\u000eH\u0016J\b\u0010m\u001a\u00020\u0015H\u0016J\b\u0010n\u001a\u00020\u0015H\u0016J\b\u0010o\u001a\u00020\u0015H\u0016J\b\u0010p\u001a\u00020\u0015H\u0016J\b\u0010q\u001a\u00020\nH\u0014J\u0010\u0010r\u001a\u00020\u00152\u0006\u0010s\u001a\u00020\nH\u0016J\b\u0010t\u001a\u00020\u0015H\u0002J\b\u0010u\u001a\u00020\u0015H\u0002J\b\u0010v\u001a\u00020\nH\u0016J\b\u0010w\u001a\u00020\nH\u0016J\u0018\u0010x\u001a\u00020\u00152\u0006\u0010j\u001a\u00020\u000e2\u0006\u0010l\u001a\u00020\u000eH\u0016J\u0010\u0010y\u001a\u00020\u00152\u0006\u0010z\u001a\u00020\u000eH\u0016J \u0010{\u001a\u00020\u00152\u0006\u0010|\u001a\u00020}2\u0006\u0010~\u001a\u00020\u00102\u0006\u0010\u007f\u001a\u00020\u000eH\u0016J\u0019\u0010\u0080\u0001\u001a\u00020\u00152\u0006\u0010j\u001a\u00020\u000e2\u0006\u0010l\u001a\u00020\u000eH\u0016J$\u0010\u0081\u0001\u001a\u00020\u00152\u0007\u0010\u0082\u0001\u001a\u00020\u000e2\u0007\u0010\u0083\u0001\u001a\u00020\u000e2\u0007\u0010\u0084\u0001\u001a\u00020\u000eH\u0016J\u0011\u0010\u0085\u0001\u001a\u00020\u00152\u0006\u0010\u0011\u001a\u00020\u000eH\u0016J$\u0010\u0086\u0001\u001a\u00020\u00152\u0007\u0010\u0082\u0001\u001a\u00020\u000e2\u0007\u0010\u0083\u0001\u001a\u00020\u000e2\u0007\u0010\u0084\u0001\u001a\u00020\u000eH\u0016J\u0011\u0010\u0087\u0001\u001a\u00020\u00152\u0006\u0010\u0011\u001a\u00020\u000eH\u0016J\u0012\u0010\u0088\u0001\u001a\u00020\u00152\u0007\u0010\u0089\u0001\u001a\u00020\u000eH\u0016J!\u0010\u008a\u0001\u001a\u00020\u00152\u0006\u0010|\u001a\u00020}2\u0006\u0010~\u001a\u00020\u00102\u0006\u0010\u007f\u001a\u00020\u000eH\u0016J\u0014\u0010\u008b\u0001\u001a\u00020\u00152\t\u0010\u008c\u0001\u001a\u0004\u0018\u00010'H\u0016J\u001b\u0010\u008d\u0001\u001a\u00020\u00152\u0007\u0010\u008e\u0001\u001a\u00020\u000e2\u0007\u0010\u008f\u0001\u001a\u00020\u000eH\u0016J\u001b\u0010\u0090\u0001\u001a\u00020\u00152\u0007\u0010\u008e\u0001\u001a\u00020\u000e2\u0007\u0010\u008f\u0001\u001a\u00020\u000eH\u0016J\u001b\u0010\u0091\u0001\u001a\u00020\u00152\u0007\u0010\u008e\u0001\u001a\u00020\u000e2\u0007\u0010\u008f\u0001\u001a\u00020\u000eH\u0016J\u001b\u0010\u0092\u0001\u001a\u00020\u00152\u0007\u0010\u0093\u0001\u001a\u00020\u000e2\u0007\u0010\u0094\u0001\u001a\u00020'H\u0016J\u0011\u0010\u0095\u0001\u001a\u00020\u00152\u0006\u0010|\u001a\u00020}H\u0016J\u0012\u0010\u0096\u0001\u001a\u00020\u00152\u0007\u0010\u0097\u0001\u001a\u00020\u000eH\u0016J$\u0010\u0098\u0001\u001a\u00020\u00152\u0007\u0010\u0082\u0001\u001a\u00020\u000e2\u0007\u0010\u0083\u0001\u001a\u00020\u000e2\u0007\u0010\u0084\u0001\u001a\u00020\u000eH\u0016J!\u0010\u0099\u0001\u001a\u00020\u00152\u0006\u0010|\u001a\u00020}2\u0006\u0010~\u001a\u00020\u00102\u0006\u0010\u007f\u001a\u00020\u000eH\u0016J\u0011\u0010\u009a\u0001\u001a\u00020\u00152\u0006\u0010\u0011\u001a\u00020\u000eH\u0016J\u0019\u0010\u009b\u0001\u001a\u00020\u00152\u0006\u0010j\u001a\u00020\u000e2\u0006\u0010l\u001a\u00020\u000eH\u0016R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u009d\u0001"}, d2 = {"Lcom/ido/life/module/home/fitness/FitnessDetailFragment;", "Lcom/ido/life/module/home/chartdetail/vertical/BaseDetailFragment;", "", "Lcom/ido/life/bean/GradientBarPoint;", "Lcom/ido/life/module/home/fitness/FitnessDetailPresenter;", "Lcom/ido/life/customview/charter/CustomChatBar$CaluteXGridLineCallback;", "Lcom/ido/life/module/home/fitness/IFitnessDetailDetailView;", "Lcom/ido/life/module/home/chartdetail/vertical/BaseDetailCallback;", "()V", "mBmr", "", "backgroundNeedUpdate", "bottomViewNeedUpdate", "calculateBottomLabelHeight", "", "calculateXGridLineValue", "", "target", "Landroid/view/View;", "index", "clearCache", "", "dismissLoading", "getBottomView", "Lcom/ido/life/module/home/chartdetail/vertical/BaseDetailViewHolder;", "context", "Landroid/content/Context;", "getChartHeight", "getLayoutResId", "getPageType", "getPresenter", "getTipLayContent", "getTipViewHolder", "getTopView", "hideSelectedUi", "resetDate", "initView", "onActiveCalorieDataLoadFailed", "message", "", "onActiveCalorieDayDataLoadSuccess", "calorieList", "showChartAnimator", "onActiveCalorieMonthDataLoadSuccess", "onActiveCalorieWeekDataLoadSuccess", "onActiveCalorieYearDataLoadSuccess", "onActiveTimeDataLoadFailed", "onActiveTimeDayDataLoadSuccess", "activeList", "onActiveTimeMonthDataLoadSuccess", "onActiveTimeWeekDataLoadSuccess", "onActiveTimeYearDataLoadSuccess", "onBottomViewRefresh", "onClick", "v", "onDetailLoadFailed", "onLoadSuccessByDay", "day", "onLoadSuccessByMonth", "month", "onLoadSuccessByWeek", "onLoadSuccessByYear", "year", "onTopViewRefresh", "onVisible", "onWalkDataLoadFailed", "onWalkDayDataLoadSuccess", "walkList", "onWalkMonthDataLoadSuccess", "onWalkWeekDataLoadSuccess", "onWalkYearDataLoadSuccess", "printAndSave", "refreshCalorieType", "calorieType", "refreshChartTipView", "refreshImageFitness", "refreshRateType", "rateType", "refreshTypeAndOffset", "refreshPage", "refreshUiByDateType", "resetLeftLabelWidth", "setLeftDayActiveCalorieBmrUI", "calorie", "setLeftDayActiveCalorieNormalUI", "calorieAvg", "calorieTarget", "setLeftDayActiveTimeBmrUI", "activeTime", "setLeftDayActiveTimeNormalUI", "timeAvg", "timeTarget", "setLeftDayWalkBmrUI", "sedentary", "setLeftDayWalkNormalUI", "walkAvg", "walkTarget", "setLeftOtherActiveCalorieNormalUI", "targetDataCount", "hasDataDayCount", "setLeftOtherActiveTimeNormalUI", "setLeftOtherWalkNormalUI", "setLeftWeekActiveTimeNormalUI", "targetTimeCount", "timeTargetDayCount", "setXMaxValue", "maxValue", "setXMinValue", "minValue", "showLoadFailedView", "showLoadSuccessView", "showLoading", "showLoadingView", "showTopRightLayout", "showWearTime", "show", "switchToBmrUI", "switchToNormalUI", "tipViewNeedUpdate", "topViewNeedUpdate", "updateActiveCalorieYMaxmin", "updateActivePer", "activeTimePer", "updateActiveRecentSituation", "stage", "Lcom/ido/life/enums/StageInfoEnum;", "value", "compareState", "updateActiveTimeYMaxmin", "updateActivityCalorieProgressMaxmin", "max", "min", NotificationCompat.CATEGORY_PROGRESS, "updateActivityCalorieTarget", "updateActivityTimeProgressMaxmin", "updateActivityTimeTarget", "updateCaloriePer", "perCalorie", "updateCalorieRecentSituation", "updateDate", "dateDesc", "updateLeftActiveTime", "actualValue", "targetValue", "updateLeftActivityCalorie", "updateLeftWalk", "updateRecentScore", "score", "scoreDesc", "updateRecentStage", "updateWalkPer", "walkPer", "updateWalkProgressMaxmin", "updateWalkRecentSituation", "updateWalkTarget", "updateWalkYMaxmin", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
public final class FitnessDetailFragment extends BaseDetailFragment<List<? extends GradientBarPoint>, List<? extends GradientBarPoint>, List<? extends GradientBarPoint>, List<? extends GradientBarPoint>, FitnessDetailPresenter> implements CustomChatBar.CaluteXGridLineCallback, IFitnessDetailDetailView, BaseDetailCallback {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private HashMap _$_findViewCache;
    private boolean mBmr;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[StageInfoEnum.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;

        static {
            $EnumSwitchMapping$0[StageInfoEnum.NODATA.ordinal()] = 1;
            $EnumSwitchMapping$1 = new int[StageInfoEnum.values().length];
            $EnumSwitchMapping$1[StageInfoEnum.NODATA.ordinal()] = 1;
            $EnumSwitchMapping$2 = new int[StageInfoEnum.values().length];
            $EnumSwitchMapping$2[StageInfoEnum.NODATA.ordinal()] = 1;
        }
    }

    @JvmStatic
    public static final FitnessDetailFragment getInstance(Bundle bundle) {
        return INSTANCE.getInstance(bundle);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailFragment
    public void _$_clearFindViewByIdCache() {
        HashMap map = this._$_findViewCache;
        if (map != null) {
            map.clear();
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailFragment
    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View view2 = getView();
        if (view2 == null) {
            return null;
        }
        View viewFindViewById = view2.findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), viewFindViewById);
        return viewFindViewById;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public boolean backgroundNeedUpdate() {
        return false;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public boolean bottomViewNeedUpdate() {
        return false;
    }

    @Override // com.ido.common.base.BaseCoreFragment
    protected int getLayoutResId() {
        return R.layout.fragment_fitness_detail_layout;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public int getPageType() {
        return -1;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void onDetailLoadFailed() {
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void onLoadSuccessByDay(boolean showChartAnimator, List<? extends GradientBarPoint> day) {
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void onLoadSuccessByMonth(boolean showChartAnimator, List<? extends GradientBarPoint> month) {
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void onLoadSuccessByWeek(boolean showChartAnimator, List<? extends GradientBarPoint> month) {
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void onLoadSuccessByYear(boolean showChartAnimator, List<? extends GradientBarPoint> year) {
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public void refreshCalorieType(int calorieType) {
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public void refreshRateType(int rateType) {
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailFragment
    protected boolean showTopRightLayout() {
        return false;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public boolean tipViewNeedUpdate() {
        return false;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public boolean topViewNeedUpdate() {
        return false;
    }

    /* JADX INFO: compiled from: FitnessDetailFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/ido/life/module/home/fitness/FitnessDetailFragment$Companion;", "", "()V", "getInstance", "Lcom/ido/life/module/home/fitness/FitnessDetailFragment;", "bundle", "Landroid/os/Bundle;", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final FitnessDetailFragment getInstance(Bundle bundle) {
            FitnessDetailFragment fitnessDetailFragment = new FitnessDetailFragment();
            fitnessDetailFragment.setArguments(bundle);
            return fitnessDetailFragment;
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailFragment, com.ido.common.base.BaseCoreFragment
    protected void onVisible() {
        super.onVisible();
        if (this.mRootView == null) {
            return;
        }
        refreshLanguage();
        refreshUiByDateType();
        if (getMNeedRefreshPage()) {
            setMNeedRefreshPage(false);
        } else {
            refreshChart();
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailFragment, com.ido.common.base.BaseCoreFragment
    protected void initView() {
        super.initView();
        FitnessDetailFragment fitnessDetailFragment = this;
        ((LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_activity)).setOnClickListener(fitnessDetailFragment);
        ((LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_exercise)).setOnClickListener(fitnessDetailFragment);
        ((LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_walk)).setOnClickListener(fitnessDetailFragment);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailFragment
    public FitnessDetailPresenter getPresenter() {
        return new FitnessDetailPresenter(this);
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0070  */
    @Override // com.ido.life.customview.charter.CustomChatBar.CaluteXGridLineCallback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public float calculateXGridLineValue(android.view.View r4, int r5) {
        /*
            r3 = this;
            java.lang.String r0 = "target"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r4, r0)
            r0 = 0
            r1 = r0
            java.util.List r1 = (java.util.List) r1
            int r2 = com.ido.life.R.id.chart_activity_calorie
            android.view.View r2 = r3._$_findCachedViewById(r2)
            com.ido.life.customview.charter.GradientBarChartBar r2 = (com.ido.life.customview.charter.GradientBarChartBar) r2
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual(r4, r2)
            if (r2 == 0) goto L28
            int r4 = com.ido.life.R.id.chart_activity_calorie
            android.view.View r4 = r3._$_findCachedViewById(r4)
            com.ido.life.customview.charter.GradientBarChartBar r4 = (com.ido.life.customview.charter.GradientBarChartBar) r4
            if (r4 == 0) goto L26
            java.util.List r0 = r4.getLabelYLeftList()
        L26:
            r1 = r0
            goto L62
        L28:
            int r2 = com.ido.life.R.id.chart_activity_time
            android.view.View r2 = r3._$_findCachedViewById(r2)
            com.ido.life.customview.charter.GradientBarChartBar r2 = (com.ido.life.customview.charter.GradientBarChartBar) r2
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual(r4, r2)
            if (r2 == 0) goto L45
            int r4 = com.ido.life.R.id.chart_activity_time
            android.view.View r4 = r3._$_findCachedViewById(r4)
            com.ido.life.customview.charter.GradientBarChartBar r4 = (com.ido.life.customview.charter.GradientBarChartBar) r4
            if (r4 == 0) goto L26
            java.util.List r0 = r4.getLabelYLeftList()
            goto L26
        L45:
            int r2 = com.ido.life.R.id.chart_walk
            android.view.View r2 = r3._$_findCachedViewById(r2)
            com.ido.life.customview.charter.GradientBarChartBar r2 = (com.ido.life.customview.charter.GradientBarChartBar) r2
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual(r4, r2)
            if (r4 == 0) goto L62
            int r4 = com.ido.life.R.id.chart_walk
            android.view.View r4 = r3._$_findCachedViewById(r4)
            com.ido.life.customview.charter.GradientBarChartBar r4 = (com.ido.life.customview.charter.GradientBarChartBar) r4
            if (r4 == 0) goto L26
            java.util.List r0 = r4.getLabelYLeftList()
            goto L26
        L62:
            r4 = r1
            java.util.Collection r4 = (java.util.Collection) r4
            if (r4 == 0) goto L70
            boolean r4 = r4.isEmpty()
            if (r4 == 0) goto L6e
            goto L70
        L6e:
            r4 = 0
            goto L71
        L70:
            r4 = 1
        L71:
            if (r4 != 0) goto L85
            int r4 = r1.size()
            if (r4 > r5) goto L7a
            goto L85
        L7a:
            java.lang.Object r4 = r1.get(r5)
            java.lang.String r4 = (java.lang.String) r4
            float r4 = java.lang.Float.parseFloat(r4)
            goto L86
        L85:
            r4 = 0
        L86:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.home.fitness.FitnessDetailFragment.calculateXGridLineValue(android.view.View, int):float");
    }

    @Override // com.ido.life.module.home.fitness.IFitnessDetailDetailView
    public void onActiveTimeDayDataLoadSuccess(List<? extends GradientBarPoint> activeList, boolean showChartAnimator) {
        Intrinsics.checkParameterIsNotNull(activeList, "activeList");
        if (activeList.isEmpty() || getMPresenter().getMDateType() != 1) {
            return;
        }
        GradientBarChartBar gradientBarChartBar = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_activity_time);
        if (gradientBarChartBar != null) {
            gradientBarChartBar.setList(activeList);
        }
        GradientBarChartBar gradientBarChartBar2 = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_activity_time);
        if (gradientBarChartBar2 != null) {
            gradientBarChartBar2.refreshChart(showChartAnimator);
        }
    }

    @Override // com.ido.life.module.home.fitness.IFitnessDetailDetailView
    public void onActiveTimeWeekDataLoadSuccess(List<? extends GradientBarPoint> activeList, boolean showChartAnimator) {
        Intrinsics.checkParameterIsNotNull(activeList, "activeList");
        if (activeList.isEmpty() || getMPresenter().getMDateType() != 2) {
            return;
        }
        GradientBarChartBar gradientBarChartBar = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_activity_time);
        if (gradientBarChartBar != null) {
            gradientBarChartBar.setList(activeList);
        }
        GradientBarChartBar gradientBarChartBar2 = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_activity_time);
        if (gradientBarChartBar2 != null) {
            gradientBarChartBar2.refreshChart(showChartAnimator);
        }
    }

    @Override // com.ido.life.module.home.fitness.IFitnessDetailDetailView
    public void onActiveTimeMonthDataLoadSuccess(List<? extends GradientBarPoint> activeList, boolean showChartAnimator) {
        Intrinsics.checkParameterIsNotNull(activeList, "activeList");
        if (activeList.isEmpty() || getMPresenter().getMDateType() != 3) {
            return;
        }
        GradientBarChartBar gradientBarChartBar = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_activity_time);
        if (gradientBarChartBar != null) {
            gradientBarChartBar.setList(activeList);
        }
        GradientBarChartBar gradientBarChartBar2 = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_activity_time);
        if (gradientBarChartBar2 != null) {
            gradientBarChartBar2.refreshChart(showChartAnimator);
        }
    }

    @Override // com.ido.life.module.home.fitness.IFitnessDetailDetailView
    public void onActiveTimeYearDataLoadSuccess(List<? extends GradientBarPoint> activeList, boolean showChartAnimator) {
        Intrinsics.checkParameterIsNotNull(activeList, "activeList");
        if (activeList.isEmpty() || getMPresenter().getMDateType() != 4) {
            return;
        }
        GradientBarChartBar gradientBarChartBar = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_activity_time);
        if (gradientBarChartBar != null) {
            gradientBarChartBar.setList(activeList);
        }
        GradientBarChartBar gradientBarChartBar2 = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_activity_time);
        if (gradientBarChartBar2 != null) {
            gradientBarChartBar2.refreshChart(showChartAnimator);
        }
    }

    @Override // com.ido.life.module.home.fitness.IFitnessDetailDetailView
    public void onActiveTimeDataLoadFailed(String message) {
        Intrinsics.checkParameterIsNotNull(message, "message");
        printAndSave(message);
        GradientBarChartBar gradientBarChartBar = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_activity_time);
        if (gradientBarChartBar != null) {
            gradientBarChartBar.clearList();
        }
        GradientBarChartBar gradientBarChartBar2 = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_activity_time);
        if (gradientBarChartBar2 != null) {
            gradientBarChartBar2.refreshChart(false);
        }
    }

    @Override // com.ido.life.module.home.fitness.IFitnessDetailDetailView
    public void onActiveCalorieDayDataLoadSuccess(List<? extends GradientBarPoint> calorieList, boolean showChartAnimator) {
        Intrinsics.checkParameterIsNotNull(calorieList, "calorieList");
        if (calorieList.isEmpty() || getMPresenter().getMDateType() != 1) {
            return;
        }
        GradientBarChartBar gradientBarChartBar = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_activity_calorie);
        if (gradientBarChartBar != null) {
            gradientBarChartBar.setList(calorieList);
        }
        GradientBarChartBar gradientBarChartBar2 = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_activity_calorie);
        if (gradientBarChartBar2 != null) {
            gradientBarChartBar2.refreshChart(showChartAnimator);
        }
    }

    @Override // com.ido.life.module.home.fitness.IFitnessDetailDetailView
    public void onActiveCalorieWeekDataLoadSuccess(List<? extends GradientBarPoint> calorieList, boolean showChartAnimator) {
        Intrinsics.checkParameterIsNotNull(calorieList, "calorieList");
        if (calorieList.isEmpty() || getMPresenter().getMDateType() != 2) {
            return;
        }
        GradientBarChartBar gradientBarChartBar = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_activity_calorie);
        if (gradientBarChartBar != null) {
            gradientBarChartBar.setList(calorieList);
        }
        GradientBarChartBar gradientBarChartBar2 = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_activity_calorie);
        if (gradientBarChartBar2 != null) {
            gradientBarChartBar2.refreshChart(showChartAnimator);
        }
    }

    @Override // com.ido.life.module.home.fitness.IFitnessDetailDetailView
    public void onActiveCalorieMonthDataLoadSuccess(List<? extends GradientBarPoint> calorieList, boolean showChartAnimator) {
        Intrinsics.checkParameterIsNotNull(calorieList, "calorieList");
        if (calorieList.isEmpty() || getMPresenter().getMDateType() != 3) {
            return;
        }
        GradientBarChartBar gradientBarChartBar = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_activity_calorie);
        if (gradientBarChartBar != null) {
            gradientBarChartBar.setList(calorieList);
        }
        GradientBarChartBar gradientBarChartBar2 = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_activity_calorie);
        if (gradientBarChartBar2 != null) {
            gradientBarChartBar2.refreshChart(showChartAnimator);
        }
    }

    @Override // com.ido.life.module.home.fitness.IFitnessDetailDetailView
    public void onActiveCalorieYearDataLoadSuccess(List<? extends GradientBarPoint> calorieList, boolean showChartAnimator) {
        Intrinsics.checkParameterIsNotNull(calorieList, "calorieList");
        if (calorieList.isEmpty() || getMPresenter().getMDateType() != 4) {
            return;
        }
        GradientBarChartBar gradientBarChartBar = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_activity_calorie);
        if (gradientBarChartBar != null) {
            gradientBarChartBar.setList(calorieList);
        }
        GradientBarChartBar gradientBarChartBar2 = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_activity_calorie);
        if (gradientBarChartBar2 != null) {
            gradientBarChartBar2.refreshChart(showChartAnimator);
        }
    }

    @Override // com.ido.life.module.home.fitness.IFitnessDetailDetailView
    public void onActiveCalorieDataLoadFailed(String message) {
        Intrinsics.checkParameterIsNotNull(message, "message");
        printAndSave(message);
        GradientBarChartBar gradientBarChartBar = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_activity_calorie);
        if (gradientBarChartBar != null) {
            gradientBarChartBar.clearList();
        }
        GradientBarChartBar gradientBarChartBar2 = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_activity_calorie);
        if (gradientBarChartBar2 != null) {
            gradientBarChartBar2.refreshChart(false);
        }
    }

    @Override // com.ido.life.module.home.fitness.IFitnessDetailDetailView
    public void onWalkDayDataLoadSuccess(List<? extends GradientBarPoint> walkList, boolean showChartAnimator) {
        Intrinsics.checkParameterIsNotNull(walkList, "walkList");
        if (walkList.isEmpty() || getMPresenter().getMDateType() != 1) {
            return;
        }
        GradientBarChartBar gradientBarChartBar = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_walk);
        if (gradientBarChartBar != null) {
            gradientBarChartBar.setList(walkList);
        }
        GradientBarChartBar gradientBarChartBar2 = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_walk);
        if (gradientBarChartBar2 != null) {
            gradientBarChartBar2.refreshChart(showChartAnimator);
        }
    }

    @Override // com.ido.life.module.home.fitness.IFitnessDetailDetailView
    public void onWalkWeekDataLoadSuccess(List<? extends GradientBarPoint> walkList, boolean showChartAnimator) {
        Intrinsics.checkParameterIsNotNull(walkList, "walkList");
        if (walkList.isEmpty() || getMPresenter().getMDateType() != 2) {
            return;
        }
        GradientBarChartBar gradientBarChartBar = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_walk);
        if (gradientBarChartBar != null) {
            gradientBarChartBar.setList(walkList);
        }
        GradientBarChartBar gradientBarChartBar2 = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_walk);
        if (gradientBarChartBar2 != null) {
            gradientBarChartBar2.refreshChart(showChartAnimator);
        }
    }

    @Override // com.ido.life.module.home.fitness.IFitnessDetailDetailView
    public void onWalkMonthDataLoadSuccess(List<? extends GradientBarPoint> walkList, boolean showChartAnimator) {
        Intrinsics.checkParameterIsNotNull(walkList, "walkList");
        if (walkList.isEmpty() || getMPresenter().getMDateType() != 3) {
            return;
        }
        GradientBarChartBar gradientBarChartBar = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_walk);
        if (gradientBarChartBar != null) {
            gradientBarChartBar.setList(walkList);
        }
        GradientBarChartBar gradientBarChartBar2 = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_walk);
        if (gradientBarChartBar2 != null) {
            gradientBarChartBar2.refreshChart(showChartAnimator);
        }
    }

    @Override // com.ido.life.module.home.fitness.IFitnessDetailDetailView
    public void onWalkYearDataLoadSuccess(List<? extends GradientBarPoint> walkList, boolean showChartAnimator) {
        Intrinsics.checkParameterIsNotNull(walkList, "walkList");
        if (walkList.isEmpty() || getMPresenter().getMDateType() != 4) {
            return;
        }
        GradientBarChartBar gradientBarChartBar = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_walk);
        if (gradientBarChartBar != null) {
            gradientBarChartBar.setList(walkList);
        }
        GradientBarChartBar gradientBarChartBar2 = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_walk);
        if (gradientBarChartBar2 != null) {
            gradientBarChartBar2.refreshChart(showChartAnimator);
        }
    }

    @Override // com.ido.life.module.home.fitness.IFitnessDetailDetailView
    public void onWalkDataLoadFailed(String message) {
        Intrinsics.checkParameterIsNotNull(message, "message");
        printAndSave(message);
        GradientBarChartBar gradientBarChartBar = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_walk);
        if (gradientBarChartBar != null) {
            gradientBarChartBar.clearList();
        }
        GradientBarChartBar gradientBarChartBar2 = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_walk);
        if (gradientBarChartBar2 != null) {
            gradientBarChartBar2.refreshChart(false);
        }
    }

    @Override // com.ido.life.module.home.fitness.IFitnessDetailDetailView
    public void updateCaloriePer(int perCalorie) {
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder topViewHolder = mCallBack != null ? mCallBack.getTopViewHolder(this) : null;
        if (topViewHolder instanceof FitnessDetailTopViewHolder) {
            FitnessDetailTopViewHolder fitnessDetailTopViewHolder = (FitnessDetailTopViewHolder) topViewHolder;
            fitnessDetailTopViewHolder.getMTvActiveCalorieValue().setText(String.valueOf(perCalorie));
            fitnessDetailTopViewHolder.getMFitnessCircle().setInnerProgress(perCalorie);
            fitnessDetailTopViewHolder.getMFitnessCircle().refreshView(false);
        }
    }

    @Override // com.ido.life.module.home.fitness.IFitnessDetailDetailView
    public void updateActivePer(int activeTimePer) {
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder topViewHolder = mCallBack != null ? mCallBack.getTopViewHolder(this) : null;
        if (topViewHolder instanceof FitnessDetailTopViewHolder) {
            FitnessDetailTopViewHolder fitnessDetailTopViewHolder = (FitnessDetailTopViewHolder) topViewHolder;
            fitnessDetailTopViewHolder.getMTvActiveTimeValue().setText(String.valueOf(activeTimePer));
            fitnessDetailTopViewHolder.getMFitnessCircle().setOutTopProgress(activeTimePer);
            fitnessDetailTopViewHolder.getMFitnessCircle().refreshView(false);
        }
    }

    @Override // com.ido.life.module.home.fitness.IFitnessDetailDetailView
    public void updateWalkPer(int walkPer) {
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder topViewHolder = mCallBack != null ? mCallBack.getTopViewHolder(this) : null;
        if (topViewHolder instanceof FitnessDetailTopViewHolder) {
            FitnessDetailTopViewHolder fitnessDetailTopViewHolder = (FitnessDetailTopViewHolder) topViewHolder;
            fitnessDetailTopViewHolder.getMTvWalkingValue().setText(String.valueOf(walkPer));
            fitnessDetailTopViewHolder.getMTvWalkingUnit().setText(getLanguageText(walkPer > 1 ? R.string.public_unit_hrs : R.string.public_unit_hr));
            fitnessDetailTopViewHolder.getMFitnessCircle().setOutBottomProgress(walkPer);
            fitnessDetailTopViewHolder.getMFitnessCircle().refreshView(false);
        }
    }

    @Override // com.ido.life.module.home.fitness.IFitnessDetailDetailView
    public void refreshImageFitness() {
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder topViewHolder = mCallBack != null ? mCallBack.getTopViewHolder(this) : null;
        if (topViewHolder instanceof FitnessDetailTopViewHolder) {
            ((FitnessDetailTopViewHolder) topViewHolder).getMFitnessCircle().refreshView(true);
        }
    }

    @Override // com.ido.life.module.home.fitness.IFitnessDetailDetailView
    public void updateLeftActivityCalorie(int actualValue, int targetValue) {
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder topViewHolder = mCallBack != null ? mCallBack.getTopViewHolder(this) : null;
        if (topViewHolder instanceof FitnessDetailTopViewHolder) {
            FitnessDetailTopViewHolder fitnessDetailTopViewHolder = (FitnessDetailTopViewHolder) topViewHolder;
            fitnessDetailTopViewHolder.getMFitnessCircle().setInnerMaxProgress(targetValue);
            fitnessDetailTopViewHolder.getMFitnessCircle().setInnerProgress(actualValue);
            fitnessDetailTopViewHolder.getMFitnessCircle().refreshView(false);
        }
        if (this.mBmr) {
            return;
        }
        if (getMPresenter().getMDateType() == 1) {
            setLeftDayActiveCalorieNormalUI(actualValue, targetValue);
        } else {
            setLeftOtherActiveCalorieNormalUI(actualValue, targetValue);
        }
    }

    @Override // com.ido.life.module.home.fitness.IFitnessDetailDetailView
    public void updateLeftActiveTime(int actualValue, int targetValue) {
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder topViewHolder = mCallBack != null ? mCallBack.getTopViewHolder(this) : null;
        if (topViewHolder instanceof FitnessDetailTopViewHolder) {
            FitnessDetailTopViewHolder fitnessDetailTopViewHolder = (FitnessDetailTopViewHolder) topViewHolder;
            fitnessDetailTopViewHolder.getMFitnessCircle().setOutTopMaxProgress(targetValue);
            fitnessDetailTopViewHolder.getMFitnessCircle().setOutTopProgress(actualValue);
            fitnessDetailTopViewHolder.getMFitnessCircle().refreshView(false);
        }
        if (this.mBmr) {
            return;
        }
        int mDateType = getMPresenter().getMDateType();
        if (mDateType == 1) {
            setLeftDayActiveTimeNormalUI(actualValue, targetValue);
        } else if (mDateType == 2) {
            setLeftWeekActiveTimeNormalUI(actualValue, targetValue);
        } else {
            setLeftOtherActiveTimeNormalUI(actualValue, targetValue);
        }
    }

    @Override // com.ido.life.module.home.fitness.IFitnessDetailDetailView
    public void updateLeftWalk(int actualValue, int targetValue) {
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder topViewHolder = mCallBack != null ? mCallBack.getTopViewHolder(this) : null;
        if (topViewHolder instanceof FitnessDetailTopViewHolder) {
            FitnessDetailTopViewHolder fitnessDetailTopViewHolder = (FitnessDetailTopViewHolder) topViewHolder;
            fitnessDetailTopViewHolder.getMFitnessCircle().setOutBottomMaxProgress(targetValue);
            fitnessDetailTopViewHolder.getMFitnessCircle().setOutBottomProgress(actualValue);
            fitnessDetailTopViewHolder.getMFitnessCircle().refreshView(false);
        }
        if (this.mBmr) {
            return;
        }
        if (getMPresenter().getMDateType() == 1) {
            setLeftDayWalkNormalUI(actualValue, targetValue);
        } else {
            setLeftOtherWalkNormalUI(actualValue, targetValue);
        }
    }

    @Override // com.ido.life.module.home.fitness.IFitnessDetailDetailView
    public void updateRecentScore(int score, String scoreDesc) {
        Intrinsics.checkParameterIsNotNull(scoreDesc, "scoreDesc");
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder bottomViewHolder = mCallBack != null ? mCallBack.getBottomViewHolder(this) : null;
        if (bottomViewHolder instanceof FitnessDetailBottomViewHolder) {
            if (score == -1) {
                FitnessDetailBottomViewHolder fitnessDetailBottomViewHolder = (FitnessDetailBottomViewHolder) bottomViewHolder;
                fitnessDetailBottomViewHolder.getMTvRecentSituationScore().setText("--");
                fitnessDetailBottomViewHolder.getMTvRecentSituationScoreUnit().setVisibility(8);
            } else {
                FitnessDetailBottomViewHolder fitnessDetailBottomViewHolder2 = (FitnessDetailBottomViewHolder) bottomViewHolder;
                fitnessDetailBottomViewHolder2.getMTvRecentSituationScore().setText(String.valueOf(score));
                fitnessDetailBottomViewHolder2.getMTvRecentSituationScoreUnit().setVisibility(0);
            }
            ((FitnessDetailBottomViewHolder) bottomViewHolder).getMTvRecentSituationDesc().setText(scoreDesc);
        }
    }

    @Override // com.ido.life.module.home.fitness.IFitnessDetailDetailView
    public void updateCalorieRecentSituation(StageInfoEnum stage, float value, int compareState) {
        Intrinsics.checkParameterIsNotNull(stage, "stage");
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder bottomViewHolder = mCallBack != null ? mCallBack.getBottomViewHolder(this) : null;
        if (bottomViewHolder instanceof FitnessDetailBottomViewHolder) {
            FitnessDetailBottomViewHolder fitnessDetailBottomViewHolder = (FitnessDetailBottomViewHolder) bottomViewHolder;
            fitnessDetailBottomViewHolder.getMTvSituationActivityValue().setText(MathKt.roundToInt(value) + RunTimeUtil.getCalorieUnit() + IOUtils.DIR_SEPARATOR_UNIX + LanguageUtil.getLanguageText(R.string.fitness_day_unit));
            if (WhenMappings.$EnumSwitchMapping$0[stage.ordinal()] == 1) {
                fitnessDetailBottomViewHolder.getMImgSituationActivityCompare().setImageResource(R.mipmap.fitness_activity_calorie_flat);
                return;
            }
            if (compareState == FitnessDetailPresenter.INSTANCE.getUP()) {
                fitnessDetailBottomViewHolder.getMImgSituationActivityCompare().setImageResource(R.mipmap.fitness_activity_calorie_up);
            } else if (compareState == FitnessDetailPresenter.INSTANCE.getDOWN()) {
                fitnessDetailBottomViewHolder.getMImgSituationActivityCompare().setImageResource(R.mipmap.fitness_activity_calorie_down);
            } else {
                fitnessDetailBottomViewHolder.getMImgSituationActivityCompare().setImageResource(R.mipmap.fitness_activity_calorie_flat);
            }
        }
    }

    @Override // com.ido.life.module.home.fitness.IFitnessDetailDetailView
    public void updateActiveRecentSituation(StageInfoEnum stage, float value, int compareState) {
        Intrinsics.checkParameterIsNotNull(stage, "stage");
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder bottomViewHolder = mCallBack != null ? mCallBack.getBottomViewHolder(this) : null;
        if (bottomViewHolder instanceof FitnessDetailBottomViewHolder) {
            FitnessDetailBottomViewHolder fitnessDetailBottomViewHolder = (FitnessDetailBottomViewHolder) bottomViewHolder;
            fitnessDetailBottomViewHolder.getMTvSituationExerciseValue().setText(MathKt.roundToInt(value) + getLanguageText(R.string.public_time_minute) + IOUtils.DIR_SEPARATOR_UNIX + LanguageUtil.getLanguageText(R.string.fitness_day_unit));
            if (WhenMappings.$EnumSwitchMapping$1[stage.ordinal()] == 1) {
                fitnessDetailBottomViewHolder.getMImgSituationExerciseCompare().setImageResource(R.mipmap.fitness_activity_time_flat);
                return;
            }
            if (compareState == FitnessDetailPresenter.INSTANCE.getUP()) {
                fitnessDetailBottomViewHolder.getMImgSituationExerciseCompare().setImageResource(R.mipmap.fitness_activity_time_up);
            } else if (compareState == FitnessDetailPresenter.INSTANCE.getDOWN()) {
                fitnessDetailBottomViewHolder.getMImgSituationExerciseCompare().setImageResource(R.mipmap.fitness_activity_time_down);
            } else {
                fitnessDetailBottomViewHolder.getMImgSituationExerciseCompare().setImageResource(R.mipmap.fitness_activity_time_flat);
            }
        }
    }

    @Override // com.ido.life.module.home.fitness.IFitnessDetailDetailView
    public void updateWalkRecentSituation(StageInfoEnum stage, float value, int compareState) {
        Intrinsics.checkParameterIsNotNull(stage, "stage");
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder bottomViewHolder = mCallBack != null ? mCallBack.getBottomViewHolder(this) : null;
        if (bottomViewHolder instanceof FitnessDetailBottomViewHolder) {
            int iRoundToInt = MathKt.roundToInt(value);
            FitnessDetailBottomViewHolder fitnessDetailBottomViewHolder = (FitnessDetailBottomViewHolder) bottomViewHolder;
            TextView mTvSituationWalkValue = fitnessDetailBottomViewHolder.getMTvSituationWalkValue();
            StringBuilder sb = new StringBuilder();
            sb.append(iRoundToInt);
            sb.append(getLanguageText(iRoundToInt > 1 ? R.string.public_unit_hrs : R.string.public_unit_hr));
            sb.append(IOUtils.DIR_SEPARATOR_UNIX);
            sb.append(LanguageUtil.getLanguageText(R.string.fitness_day_unit));
            mTvSituationWalkValue.setText(sb.toString());
            if (WhenMappings.$EnumSwitchMapping$2[stage.ordinal()] == 1) {
                fitnessDetailBottomViewHolder.getMImgSituationWalkCompare().setImageResource(R.mipmap.fitness_walk_flat);
                return;
            }
            if (compareState == FitnessDetailPresenter.INSTANCE.getUP()) {
                fitnessDetailBottomViewHolder.getMImgSituationWalkCompare().setImageResource(R.mipmap.fitness_walk_up);
            } else if (compareState == FitnessDetailPresenter.INSTANCE.getDOWN()) {
                fitnessDetailBottomViewHolder.getMImgSituationWalkCompare().setImageResource(R.mipmap.fitness_walk_down);
            } else {
                fitnessDetailBottomViewHolder.getMImgSituationWalkCompare().setImageResource(R.mipmap.fitness_walk_flat);
            }
        }
    }

    @Override // com.ido.life.module.home.fitness.IFitnessDetailDetailView
    public void updateActivityCalorieTarget(int target) {
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder bottomViewHolder = mCallBack != null ? mCallBack.getBottomViewHolder(this) : null;
        if (bottomViewHolder instanceof FitnessDetailBottomViewHolder) {
            String calorieUnit = RunTimeUtil.getCalorieUnit();
            ((FitnessDetailBottomViewHolder) bottomViewHolder).getMTvSituationActivityTarget().setText(target + calorieUnit);
        }
    }

    @Override // com.ido.life.module.home.fitness.IFitnessDetailDetailView
    public void updateActivityTimeTarget(int target) {
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder bottomViewHolder = mCallBack != null ? mCallBack.getBottomViewHolder(this) : null;
        if (bottomViewHolder instanceof FitnessDetailBottomViewHolder) {
            if (target <= 0) {
                target = 30;
            }
            ((FitnessDetailBottomViewHolder) bottomViewHolder).getMTvSituationExerciseTarget().setText(target + getLanguageText(R.string.min_unit_short));
        }
    }

    @Override // com.ido.life.module.home.fitness.IFitnessDetailDetailView
    public void updateWalkTarget(int target) {
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder bottomViewHolder = mCallBack != null ? mCallBack.getBottomViewHolder(this) : null;
        if (bottomViewHolder instanceof FitnessDetailBottomViewHolder) {
            if (target <= 0) {
                target = 12;
            }
            TextView mTvSituationWalkTarget = ((FitnessDetailBottomViewHolder) bottomViewHolder).getMTvSituationWalkTarget();
            StringBuilder sb = new StringBuilder();
            sb.append(target);
            sb.append(getLanguageText(target > 1 ? R.string.public_unit_hrs : R.string.public_unit_hr));
            mTvSituationWalkTarget.setText(sb.toString());
        }
    }

    @Override // com.ido.life.module.home.fitness.IFitnessDetailDetailView
    public void updateActivityCalorieProgressMaxmin(int max, int min, int progress) {
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder bottomViewHolder = mCallBack != null ? mCallBack.getBottomViewHolder(this) : null;
        if (bottomViewHolder instanceof FitnessDetailBottomViewHolder) {
            FitnessDetailBottomViewHolder fitnessDetailBottomViewHolder = (FitnessDetailBottomViewHolder) bottomViewHolder;
            fitnessDetailBottomViewHolder.getMProgressActivity().setMMaxProgress(max - min);
            fitnessDetailBottomViewHolder.getMProgressActivity().setMCurrentProgress(progress);
            fitnessDetailBottomViewHolder.getMProgressActivity().invalidate();
        }
    }

    @Override // com.ido.life.module.home.fitness.IFitnessDetailDetailView
    public void updateActivityTimeProgressMaxmin(int max, int min, int progress) {
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder bottomViewHolder = mCallBack != null ? mCallBack.getBottomViewHolder(this) : null;
        if (bottomViewHolder instanceof FitnessDetailBottomViewHolder) {
            FitnessDetailBottomViewHolder fitnessDetailBottomViewHolder = (FitnessDetailBottomViewHolder) bottomViewHolder;
            fitnessDetailBottomViewHolder.getMProgressExercise().setMMaxProgress(max - min);
            fitnessDetailBottomViewHolder.getMProgressExercise().setMCurrentProgress(progress);
            fitnessDetailBottomViewHolder.getMProgressExercise().invalidate();
        }
    }

    @Override // com.ido.life.module.home.fitness.IFitnessDetailDetailView
    public void updateWalkProgressMaxmin(int max, int min, int progress) {
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder bottomViewHolder = mCallBack != null ? mCallBack.getBottomViewHolder(this) : null;
        if (bottomViewHolder instanceof FitnessDetailBottomViewHolder) {
            FitnessDetailBottomViewHolder fitnessDetailBottomViewHolder = (FitnessDetailBottomViewHolder) bottomViewHolder;
            fitnessDetailBottomViewHolder.getMProgressWalk().setMMaxProgress(max - min);
            fitnessDetailBottomViewHolder.getMProgressWalk().setMCurrentProgress(progress);
            fitnessDetailBottomViewHolder.getMProgressWalk().invalidate();
        }
    }

    @Override // com.ido.life.module.home.fitness.IFitnessDetailDetailView
    public void updateActiveCalorieYMaxmin(int maxValue, int minValue) {
        GradientBarChartBar gradientBarChartBar = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_activity_calorie);
        if (gradientBarChartBar != null) {
            gradientBarChartBar.mYMinValue = minValue;
        }
        GradientBarChartBar gradientBarChartBar2 = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_activity_calorie);
        if (gradientBarChartBar2 != null) {
            gradientBarChartBar2.mYMaxValue = maxValue;
        }
    }

    @Override // com.ido.life.module.home.fitness.IFitnessDetailDetailView
    public void updateActiveTimeYMaxmin(int maxValue, int minValue) {
        GradientBarChartBar gradientBarChartBar = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_activity_time);
        if (gradientBarChartBar != null) {
            gradientBarChartBar.mYMinValue = minValue;
        }
        GradientBarChartBar gradientBarChartBar2 = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_activity_time);
        if (gradientBarChartBar2 != null) {
            gradientBarChartBar2.mYMaxValue = maxValue;
        }
    }

    @Override // com.ido.life.module.home.fitness.IFitnessDetailDetailView
    public void updateWalkYMaxmin(int maxValue, int minValue) {
        GradientBarChartBar gradientBarChartBar = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_walk);
        if (gradientBarChartBar != null) {
            gradientBarChartBar.mYMinValue = minValue;
        }
        GradientBarChartBar gradientBarChartBar2 = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_walk);
        if (gradientBarChartBar2 != null) {
            gradientBarChartBar2.mYMaxValue = maxValue;
        }
    }

    @Override // com.ido.life.module.home.fitness.IFitnessDetailDetailView
    public void updateDate(String dateDesc) {
        String str = dateDesc;
        if (str == null || str.length() == 0) {
            return;
        }
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder topViewHolder = mCallBack != null ? mCallBack.getTopViewHolder(this) : null;
        if (topViewHolder instanceof FitnessDetailTopViewHolder) {
            ((FitnessDetailTopViewHolder) topViewHolder).getMTvDate().setText(str);
        }
    }

    @Override // com.ido.life.module.home.fitness.IFitnessDetailDetailView
    public void showWearTime(boolean show) {
        if (this.mBmr) {
            if (show) {
                LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_exercise);
                if (linearLayout != null) {
                    linearLayout.setVisibility(0);
                    return;
                }
                return;
            }
            LinearLayout linearLayout2 = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_exercise);
            if (linearLayout2 != null) {
                linearLayout2.setVisibility(4);
            }
        }
    }

    @Override // com.ido.life.module.home.fitness.IFitnessDetailDetailView
    public void updateRecentStage(StageInfoEnum stage) {
        Intrinsics.checkParameterIsNotNull(stage, "stage");
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder bottomViewHolder = mCallBack != null ? mCallBack.getBottomViewHolder(this) : null;
        if (bottomViewHolder instanceof FitnessDetailBottomViewHolder) {
            if (stage == StageInfoEnum.NODATA) {
                TextView mTvStageDateDesc = ((FitnessDetailBottomViewHolder) bottomViewHolder).getMTvStageDateDesc();
                String languageText = getLanguageText(R.string.recent_day_format);
                Object[] objArr = {Integer.valueOf((StageInfoEnum.PRIMARY.getMRecentEndDayCount() - StageInfoEnum.PRIMARY.getMRecentStartDayCount()) + 1)};
                String str = String.format(languageText, Arrays.copyOf(objArr, objArr.length));
                Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(this, *args)");
                mTvStageDateDesc.setText(str);
                return;
            }
            TextView mTvStageDateDesc2 = ((FitnessDetailBottomViewHolder) bottomViewHolder).getMTvStageDateDesc();
            String languageText2 = getLanguageText(R.string.recent_day_format);
            Object[] objArr2 = {Integer.valueOf((stage.getMRecentEndDayCount() - stage.getMRecentStartDayCount()) + 1)};
            String str2 = String.format(languageText2, Arrays.copyOf(objArr2, objArr2.length));
            Intrinsics.checkNotNullExpressionValue(str2, "java.lang.String.format(this, *args)");
            mTvStageDateDesc2.setText(str2);
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void showLoading() {
        showLoadingDialog();
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void dismissLoading() {
        dismissLoadingDialog();
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void setXMaxValue(int maxValue) {
        GradientBarChartBar gradientBarChartBar = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_activity_calorie);
        if (gradientBarChartBar != null) {
            gradientBarChartBar.mXMaxValue = maxValue;
        }
        GradientBarChartBar gradientBarChartBar2 = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_activity_time);
        if (gradientBarChartBar2 != null) {
            gradientBarChartBar2.mXMaxValue = maxValue;
        }
        GradientBarChartBar gradientBarChartBar3 = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_walk);
        if (gradientBarChartBar3 != null) {
            gradientBarChartBar3.mXMaxValue = maxValue;
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void setXMinValue(int minValue) {
        GradientBarChartBar gradientBarChartBar = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_activity_calorie);
        if (gradientBarChartBar != null) {
            gradientBarChartBar.mXMinValue = minValue;
        }
        GradientBarChartBar gradientBarChartBar2 = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_activity_time);
        if (gradientBarChartBar2 != null) {
            gradientBarChartBar2.mXMinValue = minValue;
        }
        GradientBarChartBar gradientBarChartBar3 = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_walk);
        if (gradientBarChartBar3 != null) {
            gradientBarChartBar3.mXMinValue = minValue;
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void onTopViewRefresh() {
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder topViewHolder = mCallBack != null ? mCallBack.getTopViewHolder(this) : null;
        if (topViewHolder instanceof FitnessDetailTopViewHolder) {
            topViewHolder.refreshLanguage();
            ((FitnessDetailTopViewHolder) topViewHolder).getMTvDate().setText(getMPresenter().getDateText());
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void onBottomViewRefresh() {
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder bottomViewHolder = mCallBack != null ? mCallBack.getBottomViewHolder(this) : null;
        if (bottomViewHolder instanceof FitnessDetailBottomViewHolder) {
            bottomViewHolder.refreshLanguage();
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void clearCache() {
        IChartDetailCallback mCallBack;
        if (!isVisible() || (mCallBack = getMCallBack()) == null) {
            return;
        }
        mCallBack.updateSelectDate(this, null);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void showLoadingView() {
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder topViewHolder = mCallBack != null ? mCallBack.getTopViewHolder(this) : null;
        if (topViewHolder instanceof FitnessDetailTopViewHolder) {
            ((FitnessDetailTopViewHolder) topViewHolder).showLoadingView();
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void showLoadSuccessView() {
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder topViewHolder = mCallBack != null ? mCallBack.getTopViewHolder(this) : null;
        if (topViewHolder instanceof FitnessDetailTopViewHolder) {
            ((FitnessDetailTopViewHolder) topViewHolder).showSuccessView(false);
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void showLoadFailedView() {
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder topViewHolder = mCallBack != null ? mCallBack.getTopViewHolder(this) : null;
        if (topViewHolder instanceof FitnessDetailTopViewHolder) {
            ((FitnessDetailTopViewHolder) topViewHolder).showLoadFailedView(this);
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailFragment, android.view.View.OnClickListener
    public void onClick(View v) {
        super.onClick(v);
        if (ClickUtilKt.canClick(v)) {
            Integer numValueOf = v != null ? Integer.valueOf(v.getId()) : null;
            if (numValueOf != null && numValueOf.intValue() == R.id.lay_content) {
                if (NetworkUtil.isConnected(getMActivity())) {
                    IChartDetailCallback mCallBack = getMCallBack();
                    BaseDetailViewHolder topViewHolder = mCallBack != null ? mCallBack.getTopViewHolder(this) : null;
                    if (topViewHolder instanceof FitnessDetailTopViewHolder) {
                        ((FitnessDetailTopViewHolder) topViewHolder).showLoadingView();
                        getMPresenter().getDetailData();
                        return;
                    }
                    return;
                }
                showToast(R.string.public_net_unuse);
                return;
            }
            if (numValueOf != null && numValueOf.intValue() == R.id.lay_recent_situation_activity) {
                RecentSituationDetailActivity.Companion companion = RecentSituationDetailActivity.INSTANCE;
                BaseActivity<?> mActivity = getMActivity();
                if (mActivity == null) {
                    Intrinsics.throwNpe();
                }
                companion.startActivity(mActivity, RecentSituationDetailActivity.INSTANCE.getPAGE_ACTIVITY_CALORIE(), getMPresenter().getUserId());
                return;
            }
            if (numValueOf != null && numValueOf.intValue() == R.id.lay_recent_situation_exercise) {
                RecentSituationDetailActivity.Companion companion2 = RecentSituationDetailActivity.INSTANCE;
                BaseActivity<?> mActivity2 = getMActivity();
                if (mActivity2 == null) {
                    Intrinsics.throwNpe();
                }
                companion2.startActivity(mActivity2, RecentSituationDetailActivity.INSTANCE.getPAGE_ACTIVITY_TIME(), getMPresenter().getUserId());
                return;
            }
            if (numValueOf != null && numValueOf.intValue() == R.id.lay_recent_situation_walk) {
                RecentSituationDetailActivity.Companion companion3 = RecentSituationDetailActivity.INSTANCE;
                BaseActivity<?> mActivity3 = getMActivity();
                if (mActivity3 == null) {
                    Intrinsics.throwNpe();
                }
                companion3.startActivity(mActivity3, RecentSituationDetailActivity.INSTANCE.getPAGE_WALKING(), getMPresenter().getUserId());
                return;
            }
            if ((numValueOf != null && numValueOf.intValue() == R.id.lay_activity) || ((numValueOf != null && numValueOf.intValue() == R.id.lay_exercise) || (numValueOf != null && numValueOf.intValue() == R.id.lay_walk))) {
                if (getMPresenter().getMDateType() != 1) {
                    return;
                }
                this.mBmr = !this.mBmr;
                if (this.mBmr) {
                    switchToBmrUI();
                    return;
                } else {
                    switchToNormalUI();
                    return;
                }
            }
            if (numValueOf != null && numValueOf.intValue() == R.id.lay_what_fitness) {
                Intent intent = new Intent(getMActivity(), (Class<?>) CommonWebViewActivity.class);
                intent.putExtra("type", 9);
                startActivity(intent);
            }
        }
    }

    private final void setLeftDayActiveCalorieNormalUI(int calorieAvg, int calorieTarget) {
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder topViewHolder = mCallBack != null ? mCallBack.getTopViewHolder(this) : null;
        if (topViewHolder instanceof FitnessDetailTopViewHolder) {
            FitnessDetailTopViewHolder fitnessDetailTopViewHolder = (FitnessDetailTopViewHolder) topViewHolder;
            fitnessDetailTopViewHolder.getMFitnessCircle().setInnerProgress(calorieAvg);
            fitnessDetailTopViewHolder.getMFitnessCircle().setInnerMaxProgress(calorieTarget);
            fitnessDetailTopViewHolder.getMFitnessCircle().refreshView(false);
            TextView textView = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_left_active_calorie_value);
            if (textView != null) {
                textView.setText(String.valueOf(calorieAvg));
            }
            TextView textView2 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_left_active_calorie_target);
            if (textView2 != null) {
                StringBuilder sb = new StringBuilder();
                sb.append(IOUtils.DIR_SEPARATOR_UNIX);
                sb.append(calorieTarget);
                textView2.setText(sb.toString());
            }
            TextView textView3 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_left_active_calorie_unit);
            if (textView3 != null) {
                textView3.setText(getLanguageText(R.string.public_heat_calorie));
            }
            resetLeftLabelWidth();
            printAndSave("setLeftDayActiveCalorieNormalUI calorieAvg=" + calorieAvg + ",calorieTarget=" + calorieTarget);
        }
    }

    private final void setLeftOtherActiveCalorieNormalUI(int targetDataCount, int hasDataDayCount) {
        TextView textView = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_left_active_calorie_value);
        if (textView != null) {
            textView.setText(String.valueOf(targetDataCount));
        }
        TextView textView2 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_left_active_calorie_target);
        if (textView2 != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(IOUtils.DIR_SEPARATOR_UNIX);
            sb.append(hasDataDayCount);
            textView2.setText(sb.toString());
        }
        TextView textView3 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_left_active_calorie_unit);
        if (textView3 != null) {
            textView3.setText(getLanguageText(R.string.fitness_detail_day));
        }
        resetLeftLabelWidth();
    }

    private final void setLeftDayActiveTimeNormalUI(int timeAvg, int timeTarget) {
        TextView textView = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_left_active_time_value_two);
        if (textView != null) {
            textView.setText("");
        }
        TextView textView2 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_left_active_time_unit_two);
        if (textView2 != null) {
            textView2.setText("");
        }
        TextView textView3 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_left_active_time_value_one);
        if (textView3 != null) {
            textView3.setText(String.valueOf(timeAvg));
        }
        TextView textView4 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_left_active_time_target);
        if (textView4 != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(IOUtils.DIR_SEPARATOR_UNIX);
            sb.append(timeTarget);
            textView4.setText(sb.toString());
        }
        TextView textView5 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_left_active_time_unit_one);
        if (textView5 != null) {
            textView5.setText(getLanguageText(R.string.min_unit_short));
        }
        resetLeftLabelWidth();
    }

    private final void setLeftWeekActiveTimeNormalUI(int targetTimeCount, int timeTargetDayCount) {
        TextView textView = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_left_active_time_value_two);
        if (textView != null) {
            textView.setText("");
        }
        TextView textView2 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_left_active_time_unit_two);
        if (textView2 != null) {
            textView2.setText("");
        }
        TextView textView3 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_left_active_time_value_one);
        if (textView3 != null) {
            textView3.setText(String.valueOf(targetTimeCount));
        }
        TextView textView4 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_left_active_time_target);
        if (textView4 != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(IOUtils.DIR_SEPARATOR_UNIX);
            sb.append(timeTargetDayCount);
            textView4.setText(sb.toString());
        }
        TextView textView5 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_left_active_time_unit_one);
        if (textView5 != null) {
            textView5.setText(getLanguageText(R.string.min_unit_short));
        }
        resetLeftLabelWidth();
    }

    private final void setLeftOtherActiveTimeNormalUI(int targetDataCount, int hasDataDayCount) {
        TextView textView = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_left_active_time_value_two);
        if (textView != null) {
            textView.setText("");
        }
        TextView textView2 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_left_active_time_unit_two);
        if (textView2 != null) {
            textView2.setText("");
        }
        TextView textView3 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_left_active_time_value_one);
        if (textView3 != null) {
            textView3.setText(String.valueOf(targetDataCount));
        }
        TextView textView4 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_left_active_time_target);
        if (textView4 != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(IOUtils.DIR_SEPARATOR_UNIX);
            sb.append(hasDataDayCount);
            textView4.setText(sb.toString());
        }
        TextView textView5 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_left_active_time_unit_one);
        if (textView5 != null) {
            textView5.setText(getLanguageText(R.string.fitness_detail_day));
        }
        resetLeftLabelWidth();
    }

    private final void setLeftDayWalkNormalUI(int walkAvg, int walkTarget) {
        TextView textView = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_left_walk_value);
        if (textView != null) {
            textView.setText(String.valueOf(walkAvg));
        }
        TextView textView2 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_left_walk_target);
        if (textView2 != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(IOUtils.DIR_SEPARATOR_UNIX);
            sb.append(walkTarget);
            textView2.setText(sb.toString());
        }
        TextView textView3 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_left_walk_unit);
        if (textView3 != null) {
            textView3.setText(getLanguageText(walkAvg > 1 ? R.string.public_unit_hrs : R.string.public_unit_hr));
        }
        resetLeftLabelWidth();
    }

    private final void setLeftOtherWalkNormalUI(int targetDataCount, int hasDataDayCount) {
        TextView textView = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_left_walk_value);
        if (textView != null) {
            textView.setText(String.valueOf(targetDataCount));
        }
        TextView textView2 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_left_walk_target);
        if (textView2 != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(IOUtils.DIR_SEPARATOR_UNIX);
            sb.append(hasDataDayCount);
            textView2.setText(sb.toString());
        }
        TextView textView3 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_left_walk_unit);
        if (textView3 != null) {
            textView3.setText(getLanguageText(R.string.fitness_detail_day));
        }
        resetLeftLabelWidth();
    }

    private final void setLeftDayActiveCalorieBmrUI(int calorie) {
        TextView textView = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_left_active_calorie_target);
        if (textView != null) {
            textView.setText("");
        }
        TextView textView2 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_left_active_calorie_value);
        if (textView2 != null) {
            textView2.setText(String.valueOf(calorie));
        }
        TextView textView3 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_left_active_calorie_unit);
        if (textView3 != null) {
            textView3.setText(getLanguageText(R.string.public_unit_large_calorie));
        }
        resetLeftLabelWidth();
    }

    private final void setLeftDayActiveTimeBmrUI(int activeTime) {
        TextView textView = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_left_active_time_target);
        if (textView != null) {
            textView.setText("");
        }
        int i = activeTime / 60;
        int i2 = activeTime % 60;
        TextView textView2 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_left_active_time_value_one);
        if (textView2 != null) {
            textView2.setText("");
        }
        TextView textView3 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_left_active_time_unit_one);
        if (textView3 != null) {
            textView3.setText("");
        }
        TextView textView4 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_left_active_time_value_two);
        if (textView4 != null) {
            textView4.setText("");
        }
        TextView textView5 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_left_active_time_unit_two);
        if (textView5 != null) {
            textView5.setText("");
        }
        if (i == 0 && i2 == 0) {
            TextView textView6 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_left_active_time_value_two);
            if (textView6 != null) {
                textView6.setText(String.valueOf(i2));
            }
            TextView textView7 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_left_active_time_unit_two);
            if (textView7 != null) {
                textView7.setText(getLanguageText(R.string.min_unit_short));
            }
        } else {
            if (i > 0) {
                TextView textView8 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_left_active_time_value_one);
                if (textView8 != null) {
                    textView8.setText(String.valueOf(i));
                }
                TextView textView9 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_left_active_time_unit_one);
                if (textView9 != null) {
                    textView9.setText(getLanguageText(i > 1 ? R.string.public_unit_hrs : R.string.public_unit_hr));
                }
            }
            if (i2 > 0) {
                TextView textView10 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_left_active_time_value_two);
                if (textView10 != null) {
                    textView10.setText(String.valueOf(i2));
                }
                TextView textView11 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_left_active_time_unit_two);
                if (textView11 != null) {
                    textView11.setText(getLanguageText(R.string.min_unit_short));
                }
            }
        }
        resetLeftLabelWidth();
    }

    private final void setLeftDayWalkBmrUI(int sedentary) {
        TextView textView = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_left_walk_value);
        if (textView != null) {
            textView.setText(String.valueOf(sedentary));
        }
        TextView textView2 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_left_walk_target);
        if (textView2 != null) {
            textView2.setText("");
        }
        TextView textView3 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_left_walk_unit);
        if (textView3 != null) {
            textView3.setText(getLanguageText(sedentary > 1 ? R.string.public_unit_hrs : R.string.public_unit_hr));
        }
        resetLeftLabelWidth();
    }

    private final void switchToNormalUI() {
        LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_exercise);
        if (linearLayout != null) {
            linearLayout.setVisibility(0);
        }
        TextView textView = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_title_activity);
        if (textView != null) {
            textView.setText(getLanguageText(R.string.activity));
        }
        TextView textView2 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_title_exercise);
        if (textView2 != null) {
            textView2.setText(getLanguageText(R.string.exercise));
        }
        TextView textView3 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_title_walk);
        if (textView3 != null) {
            textView3.setText(getLanguageText(R.string.walking));
        }
        String mStartDate = getMPresenter().getMStartDate();
        if (mStartDate == null || mStartDate.length() == 0) {
            return;
        }
        String mEndDate = getMPresenter().getMEndDate();
        if (mEndDate == null || mEndDate.length() == 0) {
            return;
        }
        int mDateType = getMPresenter().getMDateType();
        if (mDateType != 1) {
            if (mDateType == 2) {
                setLeftOtherActiveCalorieNormalUI(getMPresenter().getMActiveCalorieTargetDayCount(), getMPresenter().getMActiveCalorieHasDataDayCount());
                setLeftWeekActiveTimeNormalUI(getMPresenter().getMActiveTimeTotalDuration(), getMPresenter().getMActiveTimeTotalTarget());
                setLeftOtherWalkNormalUI(getMPresenter().getMWalkTargetDayCount(), getMPresenter().getMWalkHasDataDayCount());
                return;
            } else {
                setLeftOtherActiveCalorieNormalUI(getMPresenter().getMActiveCalorieTargetDayCount(), getMPresenter().getMActiveCalorieHasDataDayCount());
                setLeftOtherActiveTimeNormalUI(getMPresenter().getMActiveTimeTargetDayCount(), getMPresenter().getMActiveTimeHasDataDayCount());
                setLeftOtherWalkNormalUI(getMPresenter().getMWalkTargetDayCount(), getMPresenter().getMWalkHasDataDayCount());
                return;
            }
        }
        int mActivityCalorieAvg = getMPresenter().getMActivityCalorieAvg();
        long userId = getMPresenter().getUserId();
        String mEndDate2 = getMPresenter().getMEndDate();
        if (mEndDate2 == null) {
            Intrinsics.throwNpe();
        }
        setLeftDayActiveCalorieNormalUI(mActivityCalorieAvg, FitnessHelperKt.getValidCalorieTarget(userId, mEndDate2));
        int mActivityTimeAvg = getMPresenter().getMActivityTimeAvg();
        long userId2 = getMPresenter().getUserId();
        String mEndDate3 = getMPresenter().getMEndDate();
        if (mEndDate3 == null) {
            Intrinsics.throwNpe();
        }
        setLeftDayActiveTimeNormalUI(mActivityTimeAvg, FitnessHelperKt.getValidTimeTarget(userId2, mEndDate3) / 60);
        int mWalkAvg = getMPresenter().getMWalkAvg();
        long userId3 = getMPresenter().getUserId();
        String mEndDate4 = getMPresenter().getMEndDate();
        if (mEndDate4 == null) {
            Intrinsics.throwNpe();
        }
        setLeftDayWalkNormalUI(mWalkAvg, FitnessHelperKt.getValidWalkTarget(userId3, mEndDate4) / 3600);
    }

    private final void switchToBmrUI() {
        if (getMPresenter().getMDateType() != 1) {
            return;
        }
        TextView textView = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_title_activity);
        if (textView != null) {
            textView.setText(getLanguageText(R.string.all_day));
        }
        TextView textView2 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_title_exercise);
        if (textView2 != null) {
            textView2.setText(getLanguageText(R.string.wear_device));
        }
        TextView textView3 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_title_walk);
        if (textView3 != null) {
            textView3.setText(getLanguageText(R.string.sedentary));
        }
        setLeftDayActiveCalorieBmrUI(getMPresenter().getMAllDayActivityCalorie());
        if (getMPresenter().getMShowWearDuration()) {
            LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_exercise);
            if (linearLayout != null) {
                linearLayout.setVisibility(0);
            }
            setLeftDayActiveTimeBmrUI(getMPresenter().getMValidWearDuration());
        } else {
            LinearLayout linearLayout2 = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_exercise);
            if (linearLayout2 != null) {
                linearLayout2.setVisibility(4);
            }
        }
        setLeftDayWalkBmrUI(getMPresenter().getMSedentary());
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public void refreshTypeAndOffset(boolean refreshPage) {
        this.mBmr = false;
        if (getMCallBack() != null) {
            FitnessDetailPresenter mPresenter = getMPresenter();
            IChartDetailCallback mCallBack = getMCallBack();
            if (mCallBack == null) {
                Intrinsics.throwNpe();
            }
            FitnessDetailFragment fitnessDetailFragment = this;
            int dateType = mCallBack.getDateType(fitnessDetailFragment);
            IChartDetailCallback mCallBack2 = getMCallBack();
            if (mCallBack2 == null) {
                Intrinsics.throwNpe();
            }
            mPresenter.initType(dateType, mCallBack2.getPageOffset(fitnessDetailFragment));
        }
        if (isVisible() && refreshPage) {
            refreshUiByDateType();
        } else {
            setMNeedRefreshPage(true);
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailFragment
    protected void refreshUiByDateType() {
        super.refreshUiByDateType();
        if (getMCallBack() != null) {
            IChartDetailCallback mCallBack = getMCallBack();
            if (mCallBack == null) {
                Intrinsics.throwNpe();
            }
            FitnessDetailFragment fitnessDetailFragment = this;
            if (mCallBack.isShow(fitnessDetailFragment)) {
                IChartDetailCallback mCallBack2 = getMCallBack();
                BaseDetailViewHolder topViewHolder = mCallBack2 != null ? mCallBack2.getTopViewHolder(fitnessDetailFragment) : null;
                IChartDetailCallback mCallBack3 = getMCallBack();
                BaseDetailViewHolder bottomViewHolder = mCallBack3 != null ? mCallBack3.getBottomViewHolder(fitnessDetailFragment) : null;
                if (this.mRootView == null || getContext() == null || !(topViewHolder instanceof FitnessDetailTopViewHolder) || !(bottomViewHolder instanceof FitnessDetailBottomViewHolder)) {
                    return;
                }
                FitnessDetailBottomViewHolder fitnessDetailBottomViewHolder = (FitnessDetailBottomViewHolder) bottomViewHolder;
                fitnessDetailBottomViewHolder.getMProgressActivity().setMDividerPropertyList(getMPresenter().getCaloriePropertyList());
                fitnessDetailBottomViewHolder.getMProgressExercise().setMDividerPropertyList(getMPresenter().getActivityTimePropertyList());
                fitnessDetailBottomViewHolder.getMProgressWalk().setMDividerPropertyList(getMPresenter().getWalkPropertyList());
                GradientBarChartBar gradientBarChartBar = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_walk);
                if (gradientBarChartBar != null) {
                    gradientBarChartBar.setLabelXList(getMPresenter().getBottomLabelList());
                }
                if (getContext() instanceof CustomChatBar.ChartClickListener) {
                    GradientBarChartBar gradientBarChartBar2 = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_activity_calorie);
                    if (gradientBarChartBar2 != null) {
                        Object context = getContext();
                        if (context == null) {
                            throw new TypeCastException("null cannot be cast to non-null type com.ido.life.customview.charter.CustomChatBar.ChartClickListener");
                        }
                        gradientBarChartBar2.setClickListener((CustomChatBar.ChartClickListener) context);
                    }
                    GradientBarChartBar gradientBarChartBar3 = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_activity_time);
                    if (gradientBarChartBar3 != null) {
                        Object context2 = getContext();
                        if (context2 == null) {
                            throw new TypeCastException("null cannot be cast to non-null type com.ido.life.customview.charter.CustomChatBar.ChartClickListener");
                        }
                        gradientBarChartBar3.setClickListener((CustomChatBar.ChartClickListener) context2);
                    }
                    GradientBarChartBar gradientBarChartBar4 = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_walk);
                    if (gradientBarChartBar4 != null) {
                        Object context3 = getContext();
                        if (context3 == null) {
                            throw new TypeCastException("null cannot be cast to non-null type com.ido.life.customview.charter.CustomChatBar.ChartClickListener");
                        }
                        gradientBarChartBar4.setClickListener((CustomChatBar.ChartClickListener) context3);
                    }
                }
                FitnessDetailTopViewHolder fitnessDetailTopViewHolder = (FitnessDetailTopViewHolder) topViewHolder;
                fitnessDetailTopViewHolder.setMDateType(getMPresenter().getMDateType());
                long userId = getMPresenter().getUserId();
                RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
                if (userId == runTimeUtil.getUserId()) {
                    fitnessDetailBottomViewHolder.getMCardRecent().setVisibility(0);
                } else {
                    fitnessDetailBottomViewHolder.getMCardRecent().setVisibility(8);
                }
                boolean z = true;
                fitnessDetailTopViewHolder.updateTopTitle(getMPresenter().getMDateType() == 1);
                int mDateType = getMPresenter().getMDateType();
                if (mDateType == 1) {
                    GradientBarChartBar gradientBarChartBar5 = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_activity_calorie);
                    if (gradientBarChartBar5 != null) {
                        gradientBarChartBar5.mBarSpaceRadius = 0.7f;
                    }
                    GradientBarChartBar gradientBarChartBar6 = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_activity_time);
                    if (gradientBarChartBar6 != null) {
                        gradientBarChartBar6.mBarSpaceRadius = 0.7f;
                    }
                    GradientBarChartBar gradientBarChartBar7 = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_walk);
                    if (gradientBarChartBar7 != null) {
                        gradientBarChartBar7.mBarSpaceRadius = 0.7f;
                    }
                    GradientBarChartBar gradientBarChartBar8 = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_activity_calorie);
                    if (gradientBarChartBar8 != null) {
                        gradientBarChartBar8.mBarSelectedColor = getResources().getColor(R.color.color_E83D1D);
                    }
                    GradientBarChartBar gradientBarChartBar9 = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_activity_time);
                    if (gradientBarChartBar9 != null) {
                        gradientBarChartBar9.mBarSelectedColor = getResources().getColor(R.color.color_00BE47);
                    }
                    GradientBarChartBar gradientBarChartBar10 = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_walk);
                    if (gradientBarChartBar10 != null) {
                        gradientBarChartBar10.mBarSelectedColor = getResources().getColor(R.color.color_079BEC);
                    }
                    fitnessDetailTopViewHolder.getMFitnessCircle().setVisibility(0);
                    if (this.mBmr) {
                        switchToBmrUI();
                    } else {
                        LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_exercise);
                        if (linearLayout != null) {
                            linearLayout.setVisibility(0);
                        }
                        String mEndDate = getMPresenter().getMEndDate();
                        if (mEndDate != null && mEndDate.length() != 0) {
                            z = false;
                        }
                        if (!z) {
                            int mActivityCalorieAvg = getMPresenter().getMActivityCalorieAvg();
                            long userId2 = getMPresenter().getUserId();
                            String mEndDate2 = getMPresenter().getMEndDate();
                            if (mEndDate2 == null) {
                                Intrinsics.throwNpe();
                            }
                            setLeftDayActiveCalorieNormalUI(mActivityCalorieAvg, FitnessHelperKt.getValidCalorieTarget(userId2, mEndDate2));
                            int mActivityTimeAvg = getMPresenter().getMActivityTimeAvg();
                            long userId3 = getMPresenter().getUserId();
                            String mEndDate3 = getMPresenter().getMEndDate();
                            if (mEndDate3 == null) {
                                Intrinsics.throwNpe();
                            }
                            setLeftDayActiveTimeNormalUI(mActivityTimeAvg, FitnessHelperKt.getValidTimeTarget(userId3, mEndDate3) / 60);
                            int mWalkAvg = getMPresenter().getMWalkAvg();
                            long userId4 = getMPresenter().getUserId();
                            String mEndDate4 = getMPresenter().getMEndDate();
                            if (mEndDate4 == null) {
                                Intrinsics.throwNpe();
                            }
                            setLeftDayWalkNormalUI(mWalkAvg, FitnessHelperKt.getValidWalkTarget(userId4, mEndDate4) / 3600);
                        }
                    }
                } else if (mDateType == 2) {
                    fitnessDetailTopViewHolder.getMFitnessCircle().setVisibility(8);
                    setLeftOtherActiveCalorieNormalUI(getMPresenter().getMActiveCalorieTargetDayCount(), getMPresenter().getMActiveCalorieHasDataDayCount());
                    setLeftWeekActiveTimeNormalUI(getMPresenter().getMActiveTimeTotalDuration(), getMPresenter().getMActiveTimeTotalTarget() / 60);
                    setLeftOtherWalkNormalUI(getMPresenter().getMWalkTargetDayCount(), getMPresenter().getMWalkHasDataDayCount());
                    GradientBarChartBar gradientBarChartBar11 = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_activity_calorie);
                    if (gradientBarChartBar11 != null) {
                        gradientBarChartBar11.mBarSpaceRadius = 0.5f;
                    }
                    GradientBarChartBar gradientBarChartBar12 = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_activity_time);
                    if (gradientBarChartBar12 != null) {
                        gradientBarChartBar12.mBarSpaceRadius = 0.5f;
                    }
                    GradientBarChartBar gradientBarChartBar13 = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_walk);
                    if (gradientBarChartBar13 != null) {
                        gradientBarChartBar13.mBarSpaceRadius = 0.5f;
                    }
                } else {
                    GradientBarChartBar gradientBarChartBar14 = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_activity_calorie);
                    if (gradientBarChartBar14 != null) {
                        gradientBarChartBar14.mBarSpaceRadius = 0.5f;
                    }
                    GradientBarChartBar gradientBarChartBar15 = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_activity_time);
                    if (gradientBarChartBar15 != null) {
                        gradientBarChartBar15.mBarSpaceRadius = 0.5f;
                    }
                    GradientBarChartBar gradientBarChartBar16 = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_walk);
                    if (gradientBarChartBar16 != null) {
                        gradientBarChartBar16.mBarSpaceRadius = 0.5f;
                    }
                    fitnessDetailTopViewHolder.getMFitnessCircle().setVisibility(8);
                    setLeftOtherActiveCalorieNormalUI(getMPresenter().getMActiveCalorieTargetDayCount(), getMPresenter().getMActiveCalorieHasDataDayCount());
                    setLeftOtherActiveTimeNormalUI(getMPresenter().getMActiveTimeTargetDayCount(), getMPresenter().getMActiveTimeHasDataDayCount());
                    setLeftOtherWalkNormalUI(getMPresenter().getMWalkTargetDayCount(), getMPresenter().getMWalkHasDataDayCount());
                }
                LinearLayout linearLayout2 = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_activity);
                if (linearLayout2 != null) {
                    GradientBarChartBar gradientBarChartBar17 = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_activity_calorie);
                    Float fValueOf = gradientBarChartBar17 != null ? Float.valueOf(gradientBarChartBar17.getBottomHeight()) : null;
                    if (fValueOf == null) {
                        Intrinsics.throwNpe();
                    }
                    linearLayout2.setPadding(0, 0, 0, MathKt.roundToInt(fValueOf.floatValue()));
                }
                LinearLayout linearLayout3 = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_exercise);
                if (linearLayout3 != null) {
                    GradientBarChartBar gradientBarChartBar18 = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_activity_time);
                    Float fValueOf2 = gradientBarChartBar18 != null ? Float.valueOf(gradientBarChartBar18.getBottomHeight()) : null;
                    if (fValueOf2 == null) {
                        Intrinsics.throwNpe();
                    }
                    linearLayout3.setPadding(0, 0, 0, MathKt.roundToInt(fValueOf2.floatValue()));
                }
                LinearLayout linearLayout4 = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_walk);
                if (linearLayout4 != null) {
                    GradientBarChartBar gradientBarChartBar19 = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_walk);
                    Float fValueOf3 = gradientBarChartBar19 != null ? Float.valueOf(gradientBarChartBar19.getBottomHeight()) : null;
                    if (fValueOf3 == null) {
                        Intrinsics.throwNpe();
                    }
                    linearLayout4.setPadding(0, 0, 0, MathKt.roundToInt(fValueOf3.floatValue()));
                }
                GradientBarChartBar gradientBarChartBar20 = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_activity_calorie);
                if (gradientBarChartBar20 != null) {
                    gradientBarChartBar20.mYMinValue = getMPresenter().getActivityCalorieDefaultYMin();
                }
                GradientBarChartBar gradientBarChartBar21 = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_activity_calorie);
                if (gradientBarChartBar21 != null) {
                    gradientBarChartBar21.mYMaxValue = getMPresenter().getActivityCalorieDefaultYMax();
                }
                GradientBarChartBar gradientBarChartBar22 = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_activity_calorie);
                if (gradientBarChartBar22 != null) {
                    gradientBarChartBar22.mXMinValue = getMPresenter().getActivityCalorieDefaultXMin();
                }
                GradientBarChartBar gradientBarChartBar23 = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_activity_calorie);
                if (gradientBarChartBar23 != null) {
                    gradientBarChartBar23.mXMaxValue = getMPresenter().getActivityCalorieDefaultXMax();
                }
                GradientBarChartBar gradientBarChartBar24 = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_activity_calorie);
                if (gradientBarChartBar24 != null) {
                    gradientBarChartBar24.clearList();
                }
                GradientBarChartBar gradientBarChartBar25 = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_activity_time);
                if (gradientBarChartBar25 != null) {
                    gradientBarChartBar25.mYMinValue = getMPresenter().getActivityTimeDefaultYMin();
                }
                GradientBarChartBar gradientBarChartBar26 = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_activity_time);
                if (gradientBarChartBar26 != null) {
                    gradientBarChartBar26.mYMaxValue = getMPresenter().getActivityTimeDefaultYMax();
                }
                GradientBarChartBar gradientBarChartBar27 = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_activity_time);
                if (gradientBarChartBar27 != null) {
                    gradientBarChartBar27.mXMinValue = getMPresenter().getActivityTimeDefaultXMin();
                }
                GradientBarChartBar gradientBarChartBar28 = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_activity_time);
                if (gradientBarChartBar28 != null) {
                    gradientBarChartBar28.mXMaxValue = getMPresenter().getActivityTimeDefaultXMax();
                }
                GradientBarChartBar gradientBarChartBar29 = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_activity_time);
                if (gradientBarChartBar29 != null) {
                    gradientBarChartBar29.clearList();
                }
                GradientBarChartBar gradientBarChartBar30 = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_walk);
                if (gradientBarChartBar30 != null) {
                    gradientBarChartBar30.mYMinValue = getMPresenter().getWalkDefaultYMin();
                }
                GradientBarChartBar gradientBarChartBar31 = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_walk);
                if (gradientBarChartBar31 != null) {
                    gradientBarChartBar31.mYMaxValue = getMPresenter().getWalkDefaultYMax();
                }
                GradientBarChartBar gradientBarChartBar32 = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_walk);
                if (gradientBarChartBar32 != null) {
                    gradientBarChartBar32.mXMinValue = getMPresenter().getWalkDefaultXMin();
                }
                GradientBarChartBar gradientBarChartBar33 = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_walk);
                if (gradientBarChartBar33 != null) {
                    gradientBarChartBar33.mXMaxValue = getMPresenter().getWalkDefaultXMax();
                }
                GradientBarChartBar gradientBarChartBar34 = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_walk);
                if (gradientBarChartBar34 != null) {
                    gradientBarChartBar34.clearList();
                }
                if (getMPresenter().getMDateType() == 4) {
                    int iYear = getMPresenter().year();
                    if (iYear > 0) {
                        GradientBarChartBar gradientBarChartBar35 = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_activity_calorie);
                        if (gradientBarChartBar35 != null) {
                            gradientBarChartBar35.mBottomTitle = String.valueOf(iYear);
                        }
                        GradientBarChartBar gradientBarChartBar36 = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_activity_time);
                        if (gradientBarChartBar36 != null) {
                            gradientBarChartBar36.mBottomTitle = String.valueOf(iYear);
                        }
                        GradientBarChartBar gradientBarChartBar37 = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_walk);
                        if (gradientBarChartBar37 != null) {
                            gradientBarChartBar37.mBottomTitle = String.valueOf(iYear);
                        }
                    }
                } else {
                    GradientBarChartBar gradientBarChartBar38 = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_activity_calorie);
                    if (gradientBarChartBar38 != null) {
                        gradientBarChartBar38.mBottomTitle = "";
                    }
                    GradientBarChartBar gradientBarChartBar39 = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_activity_time);
                    if (gradientBarChartBar39 != null) {
                        gradientBarChartBar39.mBottomTitle = "";
                    }
                    GradientBarChartBar gradientBarChartBar40 = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_walk);
                    if (gradientBarChartBar40 != null) {
                        gradientBarChartBar40.mBottomTitle = "";
                    }
                }
                GradientBarChartBar gradientBarChartBar41 = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_activity_calorie);
                if (gradientBarChartBar41 != null) {
                    gradientBarChartBar41.refreshChart(false);
                }
                GradientBarChartBar gradientBarChartBar42 = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_activity_time);
                if (gradientBarChartBar42 != null) {
                    gradientBarChartBar42.refreshChart(false);
                }
                GradientBarChartBar gradientBarChartBar43 = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_walk);
                if (gradientBarChartBar43 != null) {
                    gradientBarChartBar43.refreshChart(false);
                }
                fitnessDetailBottomViewHolder.initListener(this);
                getMPresenter().getDetailData();
                IChartDetailCallback mCallBack4 = getMCallBack();
                if (mCallBack4 != null) {
                    mCallBack4.updateSelectDate(fitnessDetailFragment, getMPresenter().getMStartDate());
                }
            }
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public BaseDetailViewHolder getTipViewHolder(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.detail_chart_tip_fitness_layout, (ViewGroup) null);
        Intrinsics.checkExpressionValueIsNotNull(viewInflate, "LayoutInflater.from(cont…tip_fitness_layout, null)");
        return new FitnessDetailTipViewHolder(viewInflate);
    }

    /* JADX WARN: Removed duplicated region for block: B:105:0x0236  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x0310  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x016e  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x01d3  */
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean refreshChartTipView(int r18) {
        /*
            Method dump skipped, instruction units count: 1210
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.home.fitness.FitnessDetailFragment.refreshChartTipView(int):boolean");
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public View getTipLayContent() {
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder tipViewHolder = mCallBack != null ? mCallBack.getTipViewHolder(this) : null;
        if (tipViewHolder instanceof FitnessDetailTipViewHolder) {
            return ((FitnessDetailTipViewHolder) tipViewHolder).getMLayChartContent();
        }
        return null;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public BaseDetailViewHolder getTopView(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.detail_top_fitness_layout, (ViewGroup) null);
        Intrinsics.checkExpressionValueIsNotNull(viewInflate, "LayoutInflater.from(cont…top_fitness_layout, null)");
        return new FitnessDetailTopViewHolder(viewInflate);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public BaseDetailViewHolder getBottomView(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.detail_bottom_fitness_layout, (ViewGroup) null);
        Intrinsics.checkExpressionValueIsNotNull(viewInflate, "LayoutInflater.from(cont…tom_fitness_layout, null)");
        return new FitnessDetailBottomViewHolder(viewInflate);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public void hideSelectedUi(boolean resetDate) {
        IChartDetailCallback mCallBack;
        if (resetDate && (mCallBack = getMCallBack()) != null) {
            mCallBack.updateSelectDate(this, getMPresenter().getMStartDate());
        }
        GradientBarChartBar gradientBarChartBar = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_activity_calorie);
        if (gradientBarChartBar != null) {
            gradientBarChartBar.mSelectedIndex = -1;
        }
        GradientBarChartBar gradientBarChartBar2 = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_activity_time);
        if (gradientBarChartBar2 != null) {
            gradientBarChartBar2.mSelectedIndex = -1;
        }
        GradientBarChartBar gradientBarChartBar3 = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_walk);
        if (gradientBarChartBar3 != null) {
            gradientBarChartBar3.mSelectedIndex = -1;
        }
        GradientBarChartBar gradientBarChartBar4 = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_activity_calorie);
        if (gradientBarChartBar4 != null) {
            gradientBarChartBar4.refreshChart(false);
        }
        GradientBarChartBar gradientBarChartBar5 = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_activity_time);
        if (gradientBarChartBar5 != null) {
            gradientBarChartBar5.refreshChart(false);
        }
        GradientBarChartBar gradientBarChartBar6 = (GradientBarChartBar) _$_findCachedViewById(com.ido.life.R.id.chart_walk);
        if (gradientBarChartBar6 != null) {
            gradientBarChartBar6.refreshChart(false);
        }
    }

    private final void printAndSave(String message) {
        String str = message;
        if (str == null || str.length() == 0) {
            return;
        }
        LogPath logPathImpl = LogPathImpl.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(logPathImpl, "LogPathImpl.getInstance()");
        CommonLogUtil.printAndSave(logPathImpl.getLogPath(), getClass().getSimpleName(), message);
    }

    private final int calculateBottomLabelHeight() {
        Paint paint = new Paint();
        paint.setTextSize(getResources().getDimensionPixelSize(R.dimen.size10sp));
        Paint.FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
        return Math.abs(fontMetricsInt.top) + Math.abs(fontMetricsInt.bottom) + getResources().getDimensionPixelSize(R.dimen.sw_dp_4);
    }

    private final void resetLeftLabelWidth() {
        ((LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_activity)).measure(0, 0);
        ((LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_exercise)).measure(0, 0);
        ((LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_walk)).measure(0, 0);
        LinearLayout lay_activity = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_activity);
        Intrinsics.checkExpressionValueIsNotNull(lay_activity, "lay_activity");
        int measuredWidth = lay_activity.getMeasuredWidth();
        LinearLayout lay_exercise = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_exercise);
        Intrinsics.checkExpressionValueIsNotNull(lay_exercise, "lay_exercise");
        int measuredWidth2 = lay_exercise.getMeasuredWidth();
        LinearLayout lay_walk = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_walk);
        Intrinsics.checkExpressionValueIsNotNull(lay_walk, "lay_walk");
        int iMax = Math.max(measuredWidth, Math.max(measuredWidth2, lay_walk.getMeasuredWidth()));
        LinearLayout lay_activity2 = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_activity);
        Intrinsics.checkExpressionValueIsNotNull(lay_activity2, "lay_activity");
        ViewGroup.LayoutParams layoutParams = lay_activity2.getLayoutParams();
        LinearLayout lay_exercise2 = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_exercise);
        Intrinsics.checkExpressionValueIsNotNull(lay_exercise2, "lay_exercise");
        ViewGroup.LayoutParams layoutParams2 = lay_exercise2.getLayoutParams();
        LinearLayout lay_walk2 = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_walk);
        Intrinsics.checkExpressionValueIsNotNull(lay_walk2, "lay_walk");
        ViewGroup.LayoutParams layoutParams3 = lay_walk2.getLayoutParams();
        layoutParams.width = iMax;
        layoutParams2.width = iMax;
        layoutParams3.width = iMax;
        LinearLayout lay_activity3 = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_activity);
        Intrinsics.checkExpressionValueIsNotNull(lay_activity3, "lay_activity");
        lay_activity3.setLayoutParams(layoutParams);
        LinearLayout lay_exercise3 = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_exercise);
        Intrinsics.checkExpressionValueIsNotNull(lay_exercise3, "lay_exercise");
        lay_exercise3.setLayoutParams(layoutParams2);
        LinearLayout lay_walk3 = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_walk);
        Intrinsics.checkExpressionValueIsNotNull(lay_walk3, "lay_walk");
        lay_walk3.setLayoutParams(layoutParams3);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public int getChartHeight(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        return context.getResources().getDimensionPixelSize(R.dimen.sw_dp_240);
    }
}