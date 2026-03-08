package com.ido.life.module.home.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.boat.Xtend.two.R;
import com.ido.common.utils.NetworkUtil;
import com.ido.common.utils.ResourceUtil;
import com.ido.life.bean.BarChartPoint;
import com.ido.life.bean.GoalLineInfo;
import com.ido.life.customview.charter.BarChartBar;
import com.ido.life.customview.charter.CustomChatBar;
import com.ido.life.database.model.ActiveTimeDayData;
import com.ido.life.module.home.chartdetail.vertical.BaseDetailCoreFragment;
import com.ido.life.module.home.chartdetail.vertical.BaseDetailViewHolder;
import com.ido.life.module.home.chartdetail.vertical.IChartDetailCallback;
import com.ido.life.util.DateUtil;
import com.ido.life.util.RunTimeUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: ActivityDetailFragment.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u001c\u0018\u0000 <2>\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00012\u00020\u00062\u00020\u0004:\u0001<B\u0005¢\u0006\u0002\u0010\u0007J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u000eH\u0016J\b\u0010\u0015\u001a\u00020\u0012H\u0016J\b\u0010\u0016\u001a\u00020\u0010H\u0014J\b\u0010\u0017\u001a\u00020\u0010H\u0016J\b\u0010\u0018\u001a\u00020\u0005H\u0016J\n\u0010\u0019\u001a\u0004\u0018\u00010\u000eH\u0016J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\u0012\u0010\u001e\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\u0010\u0010\u001f\u001a\u00020\u00122\u0006\u0010 \u001a\u00020!H\u0016J\b\u0010\"\u001a\u00020\u0012H\u0014J\b\u0010#\u001a\u00020\u0012H\u0016J\b\u0010$\u001a\u00020\u0012H\u0016J\u001e\u0010%\u001a\u00020\u00122\u0006\u0010&\u001a\u00020!2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0016J\u001e\u0010(\u001a\u00020\u00122\u0006\u0010&\u001a\u00020!2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0016J\u001e\u0010*\u001a\u00020\u00122\u0006\u0010&\u001a\u00020!2\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0016J\u001e\u0010,\u001a\u00020\u00122\u0006\u0010&\u001a\u00020!2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0016J\b\u0010.\u001a\u00020\u0012H\u0016J\b\u0010/\u001a\u00020\u0012H\u0014J\u0010\u00100\u001a\u00020!2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0012\u00101\u001a\u00020\u00122\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0014J\b\u00102\u001a\u00020\u0012H\u0014J\u0010\u00103\u001a\u00020\u00122\u0006\u00104\u001a\u00020\u0010H\u0016J\u0010\u00105\u001a\u00020\u00122\u0006\u00106\u001a\u00020\u0010H\u0016J\b\u00107\u001a\u00020\u0012H\u0016J\b\u00108\u001a\u00020\u0012H\u0016J\b\u00109\u001a\u00020\u0012H\u0016J\b\u0010:\u001a\u00020\u0012H\u0016J\b\u0010;\u001a\u00020!H\u0014R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006="}, d2 = {"Lcom/ido/life/module/home/activity/ActivityDetailFragment;", "Lcom/ido/life/module/home/chartdetail/vertical/BaseDetailCoreFragment;", "", "Lcom/ido/life/bean/BarChartPoint;", "Lcom/ido/life/module/home/activity/IActivityDetailView;", "Lcom/ido/life/module/home/activity/ActivityDetailPresenter;", "Lcom/ido/life/customview/charter/CustomChatBar$CaluteXGridLineCallback;", "()V", "mGoalLineList", "", "Lcom/ido/life/bean/GoalLineInfo;", "calculateXGridLineValue", "", "target", "Landroid/view/View;", "index", "", "clearCache", "", "clickAction", "view", "dismissLoading", "getLayoutResId", "getPageType", "getPresenter", "getTipLayContent", "getTipViewHolder", "Lcom/ido/life/module/home/chartdetail/vertical/BaseDetailViewHolder;", "context", "Landroid/content/Context;", "getTopView", "hideSelectedUi", "resetDate", "", "initView", "onBottomViewRefresh", "onDetailLoadFailed", "onLoadSuccessByDay", "showChartAnimator", "dayChartList", "onLoadSuccessByMonth", "monthChartList", "onLoadSuccessByWeek", "weekChartList", "onLoadSuccessByYear", "yearChartList", "onTopViewRefresh", "refreshChart", "refreshChartTipView", "refreshTopView", "refreshUiByDateType", "setXMaxValue", "maxValue", "setXMinValue", "minValue", "showLoadFailedView", "showLoadSuccessView", "showLoading", "showLoadingView", "showTopRightLayout", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
public final class ActivityDetailFragment extends BaseDetailCoreFragment<List<? extends BarChartPoint>, List<? extends BarChartPoint>, List<? extends BarChartPoint>, List<? extends BarChartPoint>, IActivityDetailView, ActivityDetailPresenter> implements CustomChatBar.CaluteXGridLineCallback, IActivityDetailView {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = ActivityDetailFragment.class.getSimpleName();
    private HashMap _$_findViewCache;
    private List<GoalLineInfo> mGoalLineList = new ArrayList();

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCoreFragment, com.ido.life.module.home.chartdetail.vertical.BaseDetailFragment
    public void _$_clearFindViewByIdCache() {
        HashMap map = this._$_findViewCache;
        if (map != null) {
            map.clear();
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCoreFragment, com.ido.life.module.home.chartdetail.vertical.BaseDetailFragment
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

    @Override // com.ido.common.base.BaseCoreFragment
    protected int getLayoutResId() {
        return R.layout.fragment_activity_detail_layout;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public int getPageType() {
        return 0;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCoreFragment, com.ido.life.module.home.chartdetail.vertical.BaseDetailFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailFragment, com.ido.common.base.BaseCoreFragment
    protected void initView() {
        super.initView();
        ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).mXGridLineCallback = this;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailFragment
    public ActivityDetailPresenter getPresenter() {
        return new ActivityDetailPresenter(this);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailFragment
    protected void refreshChart() {
        super.refreshChart();
        if (((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) != null) {
            BarChartBar line_chart = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            Intrinsics.checkExpressionValueIsNotNull(line_chart, "line_chart");
            if (line_chart.getVisibility() == 0) {
                ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).refreshChart(true);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCoreFragment
    protected void refreshTopView(Context context) {
        super.refreshTopView(context);
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder topViewHolder = mCallBack != null ? mCallBack.getTopViewHolder(this) : null;
        if (topViewHolder instanceof ActivityDetailTopViewHolder) {
            topViewHolder.refreshLanguage();
            topViewHolder.setDefaultValue();
            ActivityDetailTopViewHolder activityDetailTopViewHolder = (ActivityDetailTopViewHolder) topViewHolder;
            TextView textView = activityDetailTopViewHolder.mTvDate;
            Intrinsics.checkExpressionValueIsNotNull(textView, "topViewHolder.mTvDate");
            textView.setText(((ActivityDetailPresenter) getMPresenter()).getDateText());
            TextView textView2 = activityDetailTopViewHolder.mTvTotalActivityTime;
            Intrinsics.checkExpressionValueIsNotNull(textView2, "topViewHolder.mTvTotalActivityTime");
            textView2.setText(String.valueOf(((ActivityDetailPresenter) getMPresenter()).getMActivityTime()));
            TextView textView3 = activityDetailTopViewHolder.mTvAvgActivityTime;
            Intrinsics.checkExpressionValueIsNotNull(textView3, "topViewHolder.mTvAvgActivityTime");
            textView3.setText(String.valueOf(((ActivityDetailPresenter) getMPresenter()).getMAvgActivityTime()));
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public BaseDetailViewHolder getTopView(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        return new ActivityDetailTopViewHolder(LayoutInflater.from(context).inflate(R.layout.detail_top_activity_layout, (ViewGroup) null));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCoreFragment, com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public boolean refreshChartTipView(int index) {
        BarChartPoint barChartPoint;
        ActiveTimeDayData activeTimeDayData;
        ActiveTimeDayData activeTimeDayData2;
        super.refreshChartTipView(index);
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder tipViewHolder = mCallBack != null ? mCallBack.getTipViewHolder(this) : null;
        if ((tipViewHolder instanceof ActivityDetailTipViewHolder) && ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) != null && index >= 0) {
            BarChartBar line_chart = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            Intrinsics.checkExpressionValueIsNotNull(line_chart, "line_chart");
            List<T> list = line_chart.getList();
            if (list != 0 && list.size() > index && (barChartPoint = (BarChartPoint) list.get(index)) != null) {
                ActivityDetailTipViewHolder activityDetailTipViewHolder = (ActivityDetailTipViewHolder) tipViewHolder;
                TextView mTvTipState = activityDetailTipViewHolder.getMTvTipState();
                if (mTvTipState != null) {
                    mTvTipState.setText(getLanguageText(R.string.min_unit));
                }
                int mDateType = ((ActivityDetailPresenter) getMPresenter()).getMDateType();
                if (mDateType == 1) {
                    IChartDetailCallback mCallBack2 = getMCallBack();
                    if (mCallBack2 != null) {
                        ActivityDetailFragment activityDetailFragment = this;
                        ActivityDetailPresenter activityDetailPresenter = (ActivityDetailPresenter) getMPresenter();
                        mCallBack2.updateSelectDate(activityDetailFragment, activityDetailPresenter != null ? activityDetailPresenter.getMStartDate() : null);
                    }
                    TextView mTvTipTitleAvg = activityDetailTipViewHolder.getMTvTipTitleAvg();
                    if (mTvTipTitleAvg != null) {
                        mTvTipTitleAvg.setVisibility(8);
                    }
                    int iRoundToInt = MathKt.roundToInt(barChartPoint.x) - 1;
                    int i = iRoundToInt + 1;
                    int actualValue = (int) barChartPoint.getActualValue();
                    TextView mTvTipAvg = activityDetailTipViewHolder.getMTvTipAvg();
                    if (mTvTipAvg != null) {
                        mTvTipAvg.setText(String.valueOf(actualValue));
                    }
                    TextView mTvTipDate = activityDetailTipViewHolder.getMTvTipDate();
                    if (mTvTipDate != null) {
                        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                        Object[] objArr = {Integer.valueOf(iRoundToInt), Integer.valueOf(i)};
                        String str = String.format("%02d:00-%02d:00", Arrays.copyOf(objArr, objArr.length));
                        Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(format, *args)");
                        mTvTipDate.setText(str);
                    }
                } else if (mDateType == 2) {
                    TextView mTvTipTitleAvg2 = activityDetailTipViewHolder.getMTvTipTitleAvg();
                    if (mTvTipTitleAvg2 != null) {
                        mTvTipTitleAvg2.setVisibility(8);
                    }
                    if (barChartPoint.hasEffect()) {
                        TextView mTvTipAvg2 = activityDetailTipViewHolder.getMTvTipAvg();
                        if (mTvTipAvg2 != null) {
                            mTvTipAvg2.setText(String.valueOf(MathKt.roundToInt(barChartPoint.getActualValue())));
                        }
                        Calendar calendar = Calendar.getInstance(Locale.getDefault());
                        Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
                        ActivityDetailPresenter activityDetailPresenter2 = (ActivityDetailPresenter) getMPresenter();
                        calendar.setTime(DateUtil.string2Date(activityDetailPresenter2 != null ? activityDetailPresenter2.getMStartDate() : null, DateUtil.DATE_FORMAT_YMD));
                        calendar.add(5, MathKt.roundToInt(barChartPoint.x) - 1);
                        TextView mTvTipDate2 = activityDetailTipViewHolder.getMTvTipDate();
                        if (mTvTipDate2 != null) {
                            mTvTipDate2.setText(DateUtil.format(calendar.getTime(), DateUtil.DATE_FORMAT_DM_1));
                        }
                        IChartDetailCallback mCallBack3 = getMCallBack();
                        if (mCallBack3 != null) {
                            mCallBack3.updateSelectDate(this, DateUtil.format(calendar.getTime(), DateUtil.DATE_FORMAT_YMD));
                        }
                    } else {
                        ActivityDetailPresenter activityDetailPresenter3 = (ActivityDetailPresenter) getMPresenter();
                        List<ActiveTimeDayData> sportList = activityDetailPresenter3 != null ? activityDetailPresenter3.getSportList() : null;
                        if (sportList != null && sportList.size() > barChartPoint.getIndex() && (activeTimeDayData = sportList.get(barChartPoint.getIndex())) != null) {
                            TextView mTvTipAvg3 = activityDetailTipViewHolder.getMTvTipAvg();
                            if (mTvTipAvg3 != null) {
                                mTvTipAvg3.setText(String.valueOf(activeTimeDayData.getTotalSeconds() / 60));
                            }
                            String date = activeTimeDayData.getDate();
                            TextView mTvTipDate3 = activityDetailTipViewHolder.getMTvTipDate();
                            if (mTvTipDate3 != null) {
                                mTvTipDate3.setText(DateUtil.format(DateUtil.string2Date(date, DateUtil.DATE_FORMAT_YMD), DateUtil.DATE_FORMAT_DM_1));
                            }
                            IChartDetailCallback mCallBack4 = getMCallBack();
                            if (mCallBack4 != null) {
                                mCallBack4.updateSelectDate(this, date);
                            }
                        }
                    }
                } else if (mDateType == 3) {
                    TextView mTvTipTitleAvg3 = activityDetailTipViewHolder.getMTvTipTitleAvg();
                    if (mTvTipTitleAvg3 != null) {
                        mTvTipTitleAvg3.setVisibility(8);
                    }
                    if (barChartPoint.hasEffect()) {
                        TextView mTvTipAvg4 = activityDetailTipViewHolder.getMTvTipAvg();
                        if (mTvTipAvg4 != null) {
                            mTvTipAvg4.setText(String.valueOf(MathKt.roundToInt(barChartPoint.getActualValue())));
                        }
                        int iRoundToInt2 = MathKt.roundToInt(barChartPoint.x);
                        Calendar calendar2 = Calendar.getInstance(Locale.getDefault());
                        Intrinsics.checkExpressionValueIsNotNull(calendar2, "calendar");
                        ActivityDetailPresenter activityDetailPresenter4 = (ActivityDetailPresenter) getMPresenter();
                        calendar2.setTime(DateUtil.string2Date(activityDetailPresenter4 != null ? activityDetailPresenter4.getMStartDate() : null, DateUtil.DATE_FORMAT_YMD));
                        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
                        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
                        calendar2.setFirstDayOfWeek(runTimeUtil.getWeekStart());
                        calendar2.set(5, iRoundToInt2);
                        TextView mTvTipDate4 = activityDetailTipViewHolder.getMTvTipDate();
                        if (mTvTipDate4 != null) {
                            mTvTipDate4.setText(DateUtil.format(calendar2.getTime(), DateUtil.DATE_FORMAT_DM_1));
                        }
                        IChartDetailCallback mCallBack5 = getMCallBack();
                        if (mCallBack5 != null) {
                            mCallBack5.updateSelectDate(this, DateUtil.format(calendar2.getTime(), DateUtil.DATE_FORMAT_YMD));
                        }
                    } else {
                        ActivityDetailPresenter activityDetailPresenter5 = (ActivityDetailPresenter) getMPresenter();
                        List<ActiveTimeDayData> sportList2 = activityDetailPresenter5 != null ? activityDetailPresenter5.getSportList() : null;
                        if (sportList2 != null && sportList2.size() > barChartPoint.getIndex() && (activeTimeDayData2 = sportList2.get(barChartPoint.getIndex())) != null) {
                            TextView mTvTipAvg5 = activityDetailTipViewHolder.getMTvTipAvg();
                            if (mTvTipAvg5 != null) {
                                mTvTipAvg5.setText(String.valueOf(activeTimeDayData2.getTotalSeconds() / 60));
                            }
                            String date2 = activeTimeDayData2.getDate();
                            TextView mTvTipDate5 = activityDetailTipViewHolder.getMTvTipDate();
                            if (mTvTipDate5 != null) {
                                mTvTipDate5.setText(DateUtil.format(DateUtil.string2Date(date2, DateUtil.DATE_FORMAT_YMD), DateUtil.DATE_FORMAT_DM_1));
                            }
                            IChartDetailCallback mCallBack6 = getMCallBack();
                            if (mCallBack6 != null) {
                                mCallBack6.updateSelectDate(this, date2);
                            }
                        }
                    }
                } else if (mDateType == 4) {
                    TextView mTvTipTitleAvg4 = activityDetailTipViewHolder.getMTvTipTitleAvg();
                    if (mTvTipTitleAvg4 != null) {
                        mTvTipTitleAvg4.setText(getLanguageText(R.string.detail_average_daily));
                    }
                    if (barChartPoint.hasEffect()) {
                        TextView mTvTipAvg6 = activityDetailTipViewHolder.getMTvTipAvg();
                        if (mTvTipAvg6 != null) {
                            mTvTipAvg6.setText(String.valueOf(MathKt.roundToInt(barChartPoint.getActualValue())));
                        }
                        Calendar calendar3 = Calendar.getInstance(Locale.getDefault());
                        Intrinsics.checkExpressionValueIsNotNull(calendar3, "calendar");
                        ActivityDetailPresenter activityDetailPresenter6 = (ActivityDetailPresenter) getMPresenter();
                        calendar3.setTime(DateUtil.string2Date(activityDetailPresenter6 != null ? activityDetailPresenter6.getMStartDate() : null, DateUtil.DATE_FORMAT_YMD));
                        RunTimeUtil runTimeUtil2 = RunTimeUtil.getInstance();
                        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil2, "RunTimeUtil.getInstance()");
                        calendar3.setFirstDayOfWeek(runTimeUtil2.getWeekStart());
                        int i2 = calendar3.get(1);
                        int iRoundToInt3 = MathKt.roundToInt(barChartPoint.x);
                        TextView mTvTipDate6 = activityDetailTipViewHolder.getMTvTipDate();
                        if (mTvTipDate6 != null) {
                            StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                            Object[] objArr2 = {Integer.valueOf(iRoundToInt3), Integer.valueOf(i2)};
                            String str2 = String.format("%02d/%d", Arrays.copyOf(objArr2, objArr2.length));
                            Intrinsics.checkNotNullExpressionValue(str2, "java.lang.String.format(format, *args)");
                            mTvTipDate6.setText(str2);
                        }
                        IChartDetailCallback mCallBack7 = getMCallBack();
                        if (mCallBack7 != null) {
                            StringCompanionObject stringCompanionObject3 = StringCompanionObject.INSTANCE;
                            Object[] objArr3 = {Integer.valueOf(i2), Integer.valueOf(iRoundToInt3)};
                            String str3 = String.format("%d-%02d-01", Arrays.copyOf(objArr3, objArr3.length));
                            Intrinsics.checkNotNullExpressionValue(str3, "java.lang.String.format(format, *args)");
                            mCallBack7.updateSelectDate(this, str3);
                        }
                    } else {
                        ActivityDetailPresenter activityDetailPresenter7 = (ActivityDetailPresenter) getMPresenter();
                        List<ActiveTimeDayData> sportList3 = activityDetailPresenter7 != null ? activityDetailPresenter7.getSportList() : null;
                        if (sportList3 != null && sportList3.size() > barChartPoint.getIndex()) {
                            ActiveTimeDayData activeTimeDayData3 = sportList3.get(barChartPoint.getIndex());
                            TextView mTvTipAvg7 = activityDetailTipViewHolder.getMTvTipAvg();
                            if (mTvTipAvg7 != null) {
                                mTvTipAvg7.setText(String.valueOf(activeTimeDayData3.getDayAvgSecond()));
                            }
                            String date3 = activeTimeDayData3.getDate();
                            TextView mTvTipDate7 = activityDetailTipViewHolder.getMTvTipDate();
                            if (mTvTipDate7 != null) {
                                mTvTipDate7.setText(DateUtil.format(DateUtil.string2Date(date3, DateUtil.DATE_FORMAT_YMD), DateUtil.DATE_FORMAT_MY));
                            }
                            IChartDetailCallback mCallBack8 = getMCallBack();
                            if (mCallBack8 != null) {
                                mCallBack8.updateSelectDate(this, date3);
                            }
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public BaseDetailViewHolder getTipViewHolder(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.detail_chart_tip_activity_layout, (ViewGroup) null);
        Intrinsics.checkExpressionValueIsNotNull(viewInflate, "LayoutInflater.from(cont…ip_activity_layout, null)");
        return new ActivityDetailTipViewHolder(viewInflate);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void onLoadSuccessByDay(boolean showChartAnimator, List<? extends BarChartPoint> dayChartList) {
        Intrinsics.checkParameterIsNotNull(dayChartList, "dayChartList");
        ActivityDetailPresenter activityDetailPresenter = (ActivityDetailPresenter) getMPresenter();
        if (activityDetailPresenter == null || activityDetailPresenter.getMDateType() != 1 || this.mRootView == null || ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) == null) {
            return;
        }
        BarChartBar barChartBar = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (dayChartList.isEmpty()) {
            dayChartList = null;
        }
        barChartBar.setList(dayChartList);
        BarChartBar barChartBar2 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (getMPresenter() == 0) {
            Intrinsics.throwNpe();
        }
        barChartBar2.mYMinValue = ((ActivityDetailPresenter) r0).getDefaultYMinValue();
        BarChartBar barChartBar3 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        ActivityDetailPresenter activityDetailPresenter2 = (ActivityDetailPresenter) getMPresenter();
        barChartBar3.setLabelYLeftList(activityDetailPresenter2 != null ? activityDetailPresenter2.getDefaultYLabelList() : null);
        Intrinsics.checkExpressionValueIsNotNull(((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).getLabelYLeftList(), "line_chart.getLabelYLeftList()");
        if (!r6.isEmpty()) {
            BarChartBar barChartBar4 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            Intrinsics.checkExpressionValueIsNotNull(((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).getLabelYLeftList().get(((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).getLabelYLeftList().size() - 1), "line_chart.getLabelYLeft…abelYLeftList().size - 1]");
            barChartBar4.mYMaxValue = Integer.parseInt(r0);
        }
        BarChartBar barChartBar5 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        T mPresenter = getMPresenter();
        if (mPresenter == 0) {
            Intrinsics.throwNpe();
        }
        barChartBar5.mXMinValue = ((ActivityDetailPresenter) mPresenter).getMXMinValue();
        BarChartBar barChartBar6 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        T mPresenter2 = getMPresenter();
        if (mPresenter2 == 0) {
            Intrinsics.throwNpe();
        }
        barChartBar6.mXMaxValue = ((ActivityDetailPresenter) mPresenter2).getMXMaxValue();
        ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).refreshChart(showChartAnimator);
        refreshTopView(getMActivity());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void onLoadSuccessByWeek(boolean showChartAnimator, List<? extends BarChartPoint> weekChartList) {
        ActivityDetailPresenter activityDetailPresenter;
        Intrinsics.checkParameterIsNotNull(weekChartList, "weekChartList");
        if (this.mRootView == null || (activityDetailPresenter = (ActivityDetailPresenter) getMPresenter()) == null || activityDetailPresenter.getMDateType() != 2 || ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) == null) {
            return;
        }
        BarChartBar barChartBar = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (weekChartList.isEmpty()) {
            weekChartList = null;
        }
        barChartBar.setList(weekChartList);
        BarChartBar barChartBar2 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        T mPresenter = getMPresenter();
        if (mPresenter == 0) {
            Intrinsics.throwNpe();
        }
        barChartBar2.mXMinValue = ((ActivityDetailPresenter) mPresenter).getMXMinValue();
        BarChartBar barChartBar3 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        T mPresenter2 = getMPresenter();
        if (mPresenter2 == 0) {
            Intrinsics.throwNpe();
        }
        barChartBar3.mXMaxValue = ((ActivityDetailPresenter) mPresenter2).getMXMaxValue();
        T mPresenter3 = getMPresenter();
        if (mPresenter3 == 0) {
            Intrinsics.throwNpe();
        }
        int targetActivityTime = ((ActivityDetailPresenter) mPresenter3).getTargetActivityTime();
        this.mGoalLineList.clear();
        if (targetActivityTime > 0) {
            this.mGoalLineList.add(new GoalLineInfo(0, targetActivityTime, targetActivityTime, String.valueOf(targetActivityTime)));
        }
        ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).mGoalLineColor = ResourceUtil.getColor(R.color.color_65E8CB);
        ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).setGoalLineList(this.mGoalLineList);
        BarChartBar barChartBar4 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        ActivityDetailPresenter activityDetailPresenter2 = (ActivityDetailPresenter) getMPresenter();
        barChartBar4.setLabelYLeftList(activityDetailPresenter2 != null ? activityDetailPresenter2.getDefaultYLabelList() : null);
        List<String> list = ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).mLabelYLeftList;
        if (!(list == null || list.isEmpty())) {
            BarChartBar barChartBar5 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            Intrinsics.checkExpressionValueIsNotNull(((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).getLabelYLeftList().get(((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).getLabelYLeftList().size() - 1), "line_chart.getLabelYLeft…abelYLeftList().size - 1]");
            barChartBar5.mYMaxValue = Integer.parseInt(r0);
        }
        ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).refreshChart(showChartAnimator);
        refreshTopView(getMActivity());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void onLoadSuccessByMonth(boolean showChartAnimator, List<? extends BarChartPoint> monthChartList) {
        Intrinsics.checkParameterIsNotNull(monthChartList, "monthChartList");
        ActivityDetailPresenter activityDetailPresenter = (ActivityDetailPresenter) getMPresenter();
        if (activityDetailPresenter == null || activityDetailPresenter.getMDateType() != 3 || this.mRootView == null || ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) == null) {
            return;
        }
        BarChartBar barChartBar = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (monthChartList.isEmpty()) {
            monthChartList = null;
        }
        barChartBar.setList(monthChartList);
        T mPresenter = getMPresenter();
        if (mPresenter == 0) {
            Intrinsics.throwNpe();
        }
        int targetActivityTime = ((ActivityDetailPresenter) mPresenter).getTargetActivityTime();
        this.mGoalLineList.clear();
        if (targetActivityTime > 0) {
            this.mGoalLineList.add(new GoalLineInfo(0, targetActivityTime, targetActivityTime, String.valueOf(targetActivityTime)));
        }
        ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).mGoalLineColor = ResourceUtil.getColor(R.color.color_65E8CB);
        ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).setGoalLineList(this.mGoalLineList);
        BarChartBar barChartBar2 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        ActivityDetailPresenter activityDetailPresenter2 = (ActivityDetailPresenter) getMPresenter();
        barChartBar2.setLabelYRightList(activityDetailPresenter2 != null ? activityDetailPresenter2.getDefaultYLabelList() : null);
        List<String> list = ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).mLabelYLeftList;
        if (!(list == null || list.isEmpty())) {
            BarChartBar barChartBar3 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            Intrinsics.checkExpressionValueIsNotNull(((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).mLabelYRightList.get(((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).mLabelYRightList.size() - 1), "line_chart.mLabelYRightL…LabelYRightList.size - 1]");
            barChartBar3.mYMaxValue = Integer.parseInt(r0);
        }
        BarChartBar barChartBar4 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        T mPresenter2 = getMPresenter();
        if (mPresenter2 == 0) {
            Intrinsics.throwNpe();
        }
        barChartBar4.mXMinValue = ((ActivityDetailPresenter) mPresenter2).getMXMinValue();
        BarChartBar barChartBar5 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        T mPresenter3 = getMPresenter();
        if (mPresenter3 == 0) {
            Intrinsics.throwNpe();
        }
        barChartBar5.mXMaxValue = ((ActivityDetailPresenter) mPresenter3).getMXMaxValue();
        ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).refreshChart(showChartAnimator);
        refreshTopView(getMActivity());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void onLoadSuccessByYear(boolean showChartAnimator, List<? extends BarChartPoint> yearChartList) {
        Intrinsics.checkParameterIsNotNull(yearChartList, "yearChartList");
        ActivityDetailPresenter activityDetailPresenter = (ActivityDetailPresenter) getMPresenter();
        if (activityDetailPresenter == null || activityDetailPresenter.getMDateType() != 4 || this.mRootView == null || ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) == null) {
            return;
        }
        BarChartBar barChartBar = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (yearChartList.isEmpty()) {
            yearChartList = null;
        }
        barChartBar.setList(yearChartList);
        T mPresenter = getMPresenter();
        if (mPresenter == 0) {
            Intrinsics.throwNpe();
        }
        int targetActivityTime = ((ActivityDetailPresenter) mPresenter).getTargetActivityTime();
        this.mGoalLineList.clear();
        if (targetActivityTime > 0) {
            this.mGoalLineList.add(new GoalLineInfo(0, targetActivityTime, targetActivityTime, String.valueOf(targetActivityTime)));
        }
        ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).mGoalLineColor = ResourceUtil.getColor(R.color.color_65E8CB);
        ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).setGoalLineList(this.mGoalLineList);
        BarChartBar barChartBar2 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        ActivityDetailPresenter activityDetailPresenter2 = (ActivityDetailPresenter) getMPresenter();
        barChartBar2.setLabelYLeftList(activityDetailPresenter2 != null ? activityDetailPresenter2.getDefaultYLabelList() : null);
        Intrinsics.checkExpressionValueIsNotNull(((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).getLabelYLeftList(), "line_chart.getLabelYLeftList()");
        if (!r8.isEmpty()) {
            BarChartBar barChartBar3 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            Intrinsics.checkExpressionValueIsNotNull(((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).getLabelYLeftList().get(((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).getLabelYLeftList().size() - 1), "line_chart.getLabelYLeft…abelYLeftList().size - 1]");
            barChartBar3.mYMaxValue = Integer.parseInt(r0);
        }
        ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).mBottomTitle = (String) null;
        T mPresenter2 = getMPresenter();
        if (mPresenter2 == 0) {
            Intrinsics.throwNpe();
        }
        int iYear = ((ActivityDetailPresenter) mPresenter2).year();
        if (iYear > 0) {
            ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).mBottomTitle = String.valueOf(iYear);
        }
        BarChartBar barChartBar4 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        T mPresenter3 = getMPresenter();
        if (mPresenter3 == 0) {
            Intrinsics.throwNpe();
        }
        barChartBar4.mXMinValue = ((ActivityDetailPresenter) mPresenter3).getMXMinValue();
        BarChartBar barChartBar5 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        T mPresenter4 = getMPresenter();
        if (mPresenter4 == 0) {
            Intrinsics.throwNpe();
        }
        barChartBar5.mXMaxValue = ((ActivityDetailPresenter) mPresenter4).getMXMaxValue();
        ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).refreshChart(showChartAnimator);
        refreshTopView(getMActivity());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void onDetailLoadFailed() {
        if (this.mRootView == null || getMPresenter() == 0 || ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) == null) {
            return;
        }
        ActivityDetailPresenter activityDetailPresenter = (ActivityDetailPresenter) getMPresenter();
        List<BarChartPoint> defaultList = activityDetailPresenter != null ? activityDetailPresenter.getDefaultList() : null;
        BarChartBar barChartBar = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        List<BarChartPoint> list = defaultList;
        if (list == null || list.isEmpty()) {
            defaultList = null;
        } else if (defaultList == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.List<kotlin.Nothing>");
        }
        barChartBar.setList(defaultList);
        ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).mBottomTitle = (String) null;
        if (((ActivityDetailPresenter) getMPresenter()).getMDateType() == 4) {
            T mPresenter = getMPresenter();
            if (mPresenter == 0) {
                Intrinsics.throwNpe();
            }
            int iYear = ((ActivityDetailPresenter) mPresenter).year();
            if (iYear > 0) {
                ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).mBottomTitle = String.valueOf(iYear);
            }
        }
        ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).refreshChart(false);
        refreshTopView(getMActivity());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailFragment
    protected boolean showTopRightLayout() {
        ActivityDetailPresenter activityDetailPresenter = (ActivityDetailPresenter) getMPresenter();
        return activityDetailPresenter == null || activityDetailPresenter.getMDateType() != 1;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCoreFragment, com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public View getTipLayContent() {
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder tipViewHolder = mCallBack != null ? mCallBack.getTipViewHolder(this) : null;
        if (tipViewHolder instanceof ActivityDetailTipViewHolder) {
            return ((ActivityDetailTipViewHolder) tipViewHolder).getMLayTipContent();
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:61:0x017d  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0189  */
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailFragment
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void refreshUiByDateType() {
        /*
            Method dump skipped, instruction units count: 470
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.home.activity.ActivityDetailFragment.refreshUiByDateType():void");
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void setXMaxValue(int maxValue) {
        BarChartBar barChartBar = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (barChartBar != null) {
            barChartBar.mXMaxValue = maxValue;
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void setXMinValue(int minValue) {
        BarChartBar barChartBar = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (barChartBar != null) {
            barChartBar.mXMinValue = minValue;
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void onTopViewRefresh() {
        refreshTopView(getMActivity());
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void onBottomViewRefresh() {
        refreshBottomView(getMActivity());
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void clearCache() {
        if (((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) != null) {
            ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).clearList();
            ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).refreshChart(false);
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void showLoadingView() {
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder topViewHolder = mCallBack != null ? mCallBack.getTopViewHolder(this) : null;
        if (topViewHolder instanceof ActivityDetailTopViewHolder) {
            ((ActivityDetailTopViewHolder) topViewHolder).showLoadingView();
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void showLoadSuccessView() {
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder topViewHolder = mCallBack != null ? mCallBack.getTopViewHolder(this) : null;
        if (topViewHolder instanceof ActivityDetailTopViewHolder) {
            ((ActivityDetailTopViewHolder) topViewHolder).showSuccessView(showTopRightLayout());
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void showLoadFailedView() {
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder topViewHolder = mCallBack != null ? mCallBack.getTopViewHolder(this) : null;
        if (topViewHolder instanceof ActivityDetailTopViewHolder) {
            ((ActivityDetailTopViewHolder) topViewHolder).showLoadFailedView(this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public void hideSelectedUi(boolean resetDate) {
        IChartDetailCallback mCallBack;
        if (resetDate && getMCallBack() != null && (mCallBack = getMCallBack()) != null) {
            mCallBack.updateSelectDate(this, ((ActivityDetailPresenter) getMPresenter()).getMStartDate());
        }
        if (((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) != null) {
            BarChartBar line_chart = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            Intrinsics.checkExpressionValueIsNotNull(line_chart, "line_chart");
            if (line_chart.getVisibility() == 0) {
                ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).refreshChart(false);
            }
        }
    }

    @Override // com.ido.life.customview.charter.CustomChatBar.CaluteXGridLineCallback
    public float calculateXGridLineValue(View target, int index) {
        Intrinsics.checkParameterIsNotNull(target, "target");
        if (((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) == null) {
            return 0.0f;
        }
        BarChartBar line_chart = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        Intrinsics.checkExpressionValueIsNotNull(line_chart, "line_chart");
        List<String> labelYLeftList = line_chart.getLabelYLeftList();
        if (labelYLeftList.size() <= index) {
            return 0.0f;
        }
        Intrinsics.checkExpressionValueIsNotNull(labelYLeftList.get(index), "leftLabelList[index]");
        return Integer.parseInt(r3);
    }

    /* JADX INFO: compiled from: ActivityDetailFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tR\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/ido/life/module/home/activity/ActivityDetailFragment$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "getInstance", "Lcom/ido/life/module/home/activity/ActivityDetailFragment;", "bundle", "Landroid/os/Bundle;", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final ActivityDetailFragment getInstance(Bundle bundle) {
            ActivityDetailFragment activityDetailFragment = new ActivityDetailFragment();
            if (bundle != null) {
                activityDetailFragment.setArguments(bundle);
            }
            return activityDetailFragment;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCoreFragment
    public void clickAction(View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        if (view.getId() != R.id.lay_loading) {
            return;
        }
        if (NetworkUtil.isConnected(getMActivity())) {
            IChartDetailCallback mCallBack = getMCallBack();
            BaseDetailViewHolder topViewHolder = mCallBack != null ? mCallBack.getTopViewHolder(this) : null;
            if (topViewHolder instanceof ActivityDetailTopViewHolder) {
                ((ActivityDetailTopViewHolder) topViewHolder).showLoadingView();
                ActivityDetailPresenter activityDetailPresenter = (ActivityDetailPresenter) getMPresenter();
                if (activityDetailPresenter != null) {
                    activityDetailPresenter.getDetailData();
                    return;
                }
                return;
            }
            return;
        }
        showToast(R.string.public_net_unuse);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void showLoading() {
        showLoadingDialog();
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void dismissLoading() {
        dismissLoadingDialog();
    }
}