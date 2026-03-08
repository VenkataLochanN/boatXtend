package com.ido.life.module.home.step;

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
import com.ido.life.database.model.StepDayData;
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
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: StepDetailFragment.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u001c\u0018\u0000 =2>\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00012\u00020\u00062\u00020\u00072\u00020\u0004:\u0001=B\u0005¢\u0006\u0002\u0010\bJ\u001a\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\u0010\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u000fH\u0016J\b\u0010\u0016\u001a\u00020\u0013H\u0016J\b\u0010\u0017\u001a\u00020\u0011H\u0014J\b\u0010\u0018\u001a\u00020\u0011H\u0016J\b\u0010\u0019\u001a\u00020\u0005H\u0016J\n\u0010\u001a\u001a\u0004\u0018\u00010\u000fH\u0016J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u0012\u0010\u001f\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u0010\u0010 \u001a\u00020\u00132\u0006\u0010!\u001a\u00020\"H\u0016J\b\u0010#\u001a\u00020\u0013H\u0014J\b\u0010$\u001a\u00020\"H\u0016J\b\u0010%\u001a\u00020\u0013H\u0016J\b\u0010&\u001a\u00020\u0013H\u0016J \u0010'\u001a\u00020\u00132\u0006\u0010(\u001a\u00020\"2\u000e\u0010)\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002H\u0016J \u0010*\u001a\u00020\u00132\u0006\u0010(\u001a\u00020\"2\u000e\u0010+\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002H\u0016J \u0010,\u001a\u00020\u00132\u0006\u0010(\u001a\u00020\"2\u000e\u0010+\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002H\u0016J \u0010-\u001a\u00020\u00132\u0006\u0010(\u001a\u00020\"2\u000e\u0010.\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002H\u0016J\b\u0010/\u001a\u00020\u0013H\u0016J\b\u00100\u001a\u00020\u0013H\u0014J\u0010\u00101\u001a\u00020\"2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0012\u00102\u001a\u00020\u00132\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0014J\b\u00103\u001a\u00020\u0013H\u0014J\u0010\u00104\u001a\u00020\u00132\u0006\u00105\u001a\u00020\u0011H\u0016J\u0010\u00106\u001a\u00020\u00132\u0006\u00107\u001a\u00020\u0011H\u0016J\b\u00108\u001a\u00020\u0013H\u0016J\b\u00109\u001a\u00020\u0013H\u0016J\b\u0010:\u001a\u00020\u0013H\u0016J\b\u0010;\u001a\u00020\u0013H\u0016J\b\u0010<\u001a\u00020\"H\u0014R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006>"}, d2 = {"Lcom/ido/life/module/home/step/StepDetailFragment;", "Lcom/ido/life/module/home/chartdetail/vertical/BaseDetailCoreFragment;", "", "Lcom/ido/life/bean/BarChartPoint;", "Lcom/ido/life/module/home/step/IStepDetailView;", "Lcom/ido/life/module/home/step/StepDetailPresenter;", "Lcom/ido/life/customview/charter/CustomChatBar$CaluteXGridLineCallback;", "Landroid/view/View$OnClickListener;", "()V", "mGoalLineList", "", "Lcom/ido/life/bean/GoalLineInfo;", "calculateXGridLineValue", "", "target", "Landroid/view/View;", "index", "", "clearCache", "", "clickAction", "view", "dismissLoading", "getLayoutResId", "getPageType", "getPresenter", "getTipLayContent", "getTipViewHolder", "Lcom/ido/life/module/home/chartdetail/vertical/BaseDetailViewHolder;", "context", "Landroid/content/Context;", "getTopView", "hideSelectedUi", "resetDate", "", "initView", "needEventBus", "onBottomViewRefresh", "onDetailLoadFailed", "onLoadSuccessByDay", "showChartAnimator", "day", "onLoadSuccessByMonth", "month", "onLoadSuccessByWeek", "onLoadSuccessByYear", "year", "onTopViewRefresh", "refreshChart", "refreshChartTipView", "refreshTopView", "refreshUiByDateType", "setXMaxValue", "maxValue", "setXMinValue", "minValue", "showLoadFailedView", "showLoadSuccessView", "showLoading", "showLoadingView", "showTopRightLayout", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
public final class StepDetailFragment extends BaseDetailCoreFragment<List<? extends BarChartPoint>, List<? extends BarChartPoint>, List<? extends BarChartPoint>, List<? extends BarChartPoint>, IStepDetailView, StepDetailPresenter> implements CustomChatBar.CaluteXGridLineCallback, View.OnClickListener, IStepDetailView {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = StepDetailFragment.class.getSimpleName();
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
        return R.layout.fragment_step_detail_layout;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public int getPageType() {
        return 0;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailFragment
    public boolean needEventBus() {
        return true;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCoreFragment, com.ido.life.module.home.chartdetail.vertical.BaseDetailFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailFragment, com.ido.common.base.BaseCoreFragment
    protected void initView() {
        super.initView();
        BarChartBar line_chart = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        Intrinsics.checkExpressionValueIsNotNull(line_chart, "line_chart");
        line_chart.setXGridLineCallback(this);
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

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public BaseDetailViewHolder getTipViewHolder(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.detail_chart_tip_step_layout, (ViewGroup) null);
        Intrinsics.checkExpressionValueIsNotNull(viewInflate, "LayoutInflater.from(cont…rt_tip_step_layout, null)");
        return new StepDetailTipViewHolder(viewInflate);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public BaseDetailViewHolder getTopView(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        return new StepDetailTopViewHolder(LayoutInflater.from(context).inflate(R.layout.detail_top_step_layout, (ViewGroup) null));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCoreFragment
    protected void refreshTopView(Context context) {
        super.refreshTopView(context);
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder topViewHolder = mCallBack != null ? mCallBack.getTopViewHolder(this) : null;
        if (topViewHolder instanceof StepDetailTopViewHolder) {
            topViewHolder.refreshLanguage();
            topViewHolder.setDefaultValue();
            StepDetailTopViewHolder stepDetailTopViewHolder = (StepDetailTopViewHolder) topViewHolder;
            TextView textView = stepDetailTopViewHolder.mTvDate;
            Intrinsics.checkExpressionValueIsNotNull(textView, "topViewHolder.mTvDate");
            T mPresenter = getMPresenter();
            if (mPresenter == 0) {
                Intrinsics.throwNpe();
            }
            textView.setText(((StepDetailPresenter) mPresenter).getDateText());
            TextView textView2 = stepDetailTopViewHolder.mTvTotalStep;
            Intrinsics.checkExpressionValueIsNotNull(textView2, "topViewHolder.mTvTotalStep");
            T mPresenter2 = getMPresenter();
            if (mPresenter2 == 0) {
                Intrinsics.throwNpe();
            }
            textView2.setText(String.valueOf(((StepDetailPresenter) mPresenter2).getMSportTotalStep()));
            TextView textView3 = stepDetailTopViewHolder.mTvAvgStep;
            Intrinsics.checkExpressionValueIsNotNull(textView3, "topViewHolder.mTvAvgStep");
            T mPresenter3 = getMPresenter();
            if (mPresenter3 == 0) {
                Intrinsics.throwNpe();
            }
            textView3.setText(String.valueOf(((StepDetailPresenter) mPresenter3).getMSportAvgStep()));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCoreFragment, com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public boolean refreshChartTipView(int index) {
        BarChartPoint barChartPoint;
        StepDayData stepDayData;
        StepDayData stepDayData2;
        super.refreshChartTipView(index);
        if (index < 0) {
            return false;
        }
        BarChartBar line_chart = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        Intrinsics.checkExpressionValueIsNotNull(line_chart, "line_chart");
        List<T> list = line_chart.getList();
        if (list == 0 || list.size() <= index || (barChartPoint = (BarChartPoint) list.get(index)) == null) {
            return false;
        }
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder tipViewHolder = mCallBack != null ? mCallBack.getTipViewHolder(this) : null;
        if (!(tipViewHolder instanceof StepDetailTipViewHolder)) {
            return false;
        }
        StepDetailTipViewHolder stepDetailTipViewHolder = (StepDetailTipViewHolder) tipViewHolder;
        TextView mTvTipState = stepDetailTipViewHolder.getMTvTipState();
        if (mTvTipState == null) {
            Intrinsics.throwNpe();
        }
        mTvTipState.setText(getLanguageText(R.string.public_sport_step));
        int mDateType = ((StepDetailPresenter) getMPresenter()).getMDateType();
        if (mDateType == 1) {
            IChartDetailCallback mCallBack2 = getMCallBack();
            if (mCallBack2 != null) {
                StepDetailFragment stepDetailFragment = this;
                T mPresenter = getMPresenter();
                if (mPresenter == 0) {
                    Intrinsics.throwNpe();
                }
                mCallBack2.updateSelectDate(stepDetailFragment, ((StepDetailPresenter) mPresenter).getMStartDate());
            }
            TextView mTvTipTitleAvg = stepDetailTipViewHolder.getMTvTipTitleAvg();
            if (mTvTipTitleAvg != null) {
                mTvTipTitleAvg.setVisibility(8);
            }
            int iRoundToInt = MathKt.roundToInt(barChartPoint.x) - 1;
            int i = iRoundToInt + 1;
            int actualValue = (int) barChartPoint.getActualValue();
            TextView mTvTipAvg = stepDetailTipViewHolder.getMTvTipAvg();
            if (mTvTipAvg != null) {
                mTvTipAvg.setText(String.valueOf(actualValue));
            }
            TextView mTvTipDate = stepDetailTipViewHolder.getMTvTipDate();
            if (mTvTipDate != null) {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                Object[] objArr = {Integer.valueOf(iRoundToInt), Integer.valueOf(i)};
                String str = String.format("%02d:00-%02d:00", Arrays.copyOf(objArr, objArr.length));
                Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(format, *args)");
                mTvTipDate.setText(str);
            }
        } else if (mDateType == 2) {
            TextView mTvTipTitleAvg2 = stepDetailTipViewHolder.getMTvTipTitleAvg();
            if (mTvTipTitleAvg2 != null) {
                mTvTipTitleAvg2.setVisibility(8);
            }
            if (barChartPoint.hasEffect()) {
                TextView mTvTipAvg2 = stepDetailTipViewHolder.getMTvTipAvg();
                if (mTvTipAvg2 != null) {
                    mTvTipAvg2.setText(String.valueOf(MathKt.roundToInt(barChartPoint.getActualValue())));
                }
                Calendar calendar = Calendar.getInstance(Locale.getDefault());
                Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
                T mPresenter2 = getMPresenter();
                if (mPresenter2 == 0) {
                    Intrinsics.throwNpe();
                }
                calendar.setTime(DateUtil.string2Date(((StepDetailPresenter) mPresenter2).getMStartDate(), DateUtil.DATE_FORMAT_YMD));
                calendar.add(5, MathKt.roundToInt(barChartPoint.x) - 1);
                TextView mTvTipDate2 = stepDetailTipViewHolder.getMTvTipDate();
                if (mTvTipDate2 != null) {
                    mTvTipDate2.setText(DateUtil.format(calendar.getTime(), DateUtil.DATE_FORMAT_DM_1));
                }
                IChartDetailCallback mCallBack3 = getMCallBack();
                if (mCallBack3 != null) {
                    mCallBack3.updateSelectDate(this, DateUtil.format(calendar.getTime(), DateUtil.DATE_FORMAT_YMD));
                }
            } else {
                T mPresenter3 = getMPresenter();
                if (mPresenter3 == 0) {
                    Intrinsics.throwNpe();
                }
                List<StepDayData> sportList = ((StepDetailPresenter) mPresenter3).getSportList();
                if (sportList != null && sportList.size() > barChartPoint.getIndex() && (stepDayData = sportList.get(barChartPoint.getIndex())) != null) {
                    TextView mTvTipAvg3 = stepDetailTipViewHolder.getMTvTipAvg();
                    if (mTvTipAvg3 != null) {
                        mTvTipAvg3.setText(String.valueOf(stepDayData.getNumSteps()));
                    }
                    String date = stepDayData.getDate();
                    TextView mTvTipDate3 = stepDetailTipViewHolder.getMTvTipDate();
                    if (mTvTipDate3 != null) {
                        mTvTipDate3.setText(DateUtil.format(DateUtil.string2Date(stepDayData.getDate(), DateUtil.DATE_FORMAT_YMD), DateUtil.DATE_FORMAT_DM_1));
                    }
                    IChartDetailCallback mCallBack4 = getMCallBack();
                    if (mCallBack4 != null) {
                        mCallBack4.updateSelectDate(this, date);
                    }
                }
            }
        } else if (mDateType == 3) {
            TextView mTvTipTitleAvg3 = stepDetailTipViewHolder.getMTvTipTitleAvg();
            if (mTvTipTitleAvg3 != null) {
                mTvTipTitleAvg3.setVisibility(8);
            }
            if (barChartPoint.hasEffect()) {
                TextView mTvTipAvg4 = stepDetailTipViewHolder.getMTvTipAvg();
                if (mTvTipAvg4 != null) {
                    mTvTipAvg4.setText(String.valueOf(MathKt.roundToInt(barChartPoint.getActualValue())));
                }
                int iRoundToInt2 = MathKt.roundToInt(barChartPoint.x);
                Calendar calendar2 = Calendar.getInstance(Locale.getDefault());
                Intrinsics.checkExpressionValueIsNotNull(calendar2, "calendar");
                T mPresenter4 = getMPresenter();
                if (mPresenter4 == 0) {
                    Intrinsics.throwNpe();
                }
                calendar2.setTime(DateUtil.string2Date(((StepDetailPresenter) mPresenter4).getMStartDate(), DateUtil.DATE_FORMAT_YMD));
                RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
                calendar2.setFirstDayOfWeek(runTimeUtil.getWeekStart());
                calendar2.set(5, iRoundToInt2);
                TextView mTvTipDate4 = stepDetailTipViewHolder.getMTvTipDate();
                if (mTvTipDate4 != null) {
                    mTvTipDate4.setText(DateUtil.format(calendar2.getTime(), DateUtil.DATE_FORMAT_DM_1));
                }
                IChartDetailCallback mCallBack5 = getMCallBack();
                if (mCallBack5 != null) {
                    mCallBack5.updateSelectDate(this, DateUtil.format(calendar2.getTime(), DateUtil.DATE_FORMAT_YMD));
                }
            } else {
                T mPresenter5 = getMPresenter();
                if (mPresenter5 == 0) {
                    Intrinsics.throwNpe();
                }
                List<StepDayData> sportList2 = ((StepDetailPresenter) mPresenter5).getSportList();
                if (sportList2 != null && sportList2.size() > barChartPoint.getIndex() && (stepDayData2 = sportList2.get(barChartPoint.getIndex())) != null) {
                    TextView mTvTipAvg5 = stepDetailTipViewHolder.getMTvTipAvg();
                    if (mTvTipAvg5 != null) {
                        mTvTipAvg5.setText(String.valueOf(stepDayData2.getNumSteps()));
                    }
                    String date2 = stepDayData2.getDate();
                    TextView mTvTipDate5 = stepDetailTipViewHolder.getMTvTipDate();
                    if (mTvTipDate5 != null) {
                        mTvTipDate5.setText(DateUtil.format(DateUtil.string2Date(stepDayData2.getDate(), DateUtil.DATE_FORMAT_YMD), DateUtil.DATE_FORMAT_DM_1));
                    }
                    IChartDetailCallback mCallBack6 = getMCallBack();
                    if (mCallBack6 != null) {
                        mCallBack6.updateSelectDate(this, date2);
                    }
                }
            }
        } else if (mDateType == 4) {
            TextView mTvTipTitleAvg4 = stepDetailTipViewHolder.getMTvTipTitleAvg();
            if (mTvTipTitleAvg4 != null) {
                mTvTipTitleAvg4.setText(getLanguageText(R.string.detail_average_daily));
            }
            if (barChartPoint.hasEffect()) {
                TextView mTvTipAvg6 = stepDetailTipViewHolder.getMTvTipAvg();
                if (mTvTipAvg6 != null) {
                    mTvTipAvg6.setText(String.valueOf(MathKt.roundToInt(barChartPoint.getActualValue())));
                }
                Calendar calendar3 = Calendar.getInstance(Locale.getDefault());
                Intrinsics.checkExpressionValueIsNotNull(calendar3, "calendar");
                T mPresenter6 = getMPresenter();
                if (mPresenter6 == 0) {
                    Intrinsics.throwNpe();
                }
                calendar3.setTime(DateUtil.string2Date(((StepDetailPresenter) mPresenter6).getMStartDate(), DateUtil.DATE_FORMAT_YMD));
                RunTimeUtil runTimeUtil2 = RunTimeUtil.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(runTimeUtil2, "RunTimeUtil.getInstance()");
                calendar3.setFirstDayOfWeek(runTimeUtil2.getWeekStart());
                int i2 = calendar3.get(1);
                int iRoundToInt3 = MathKt.roundToInt(barChartPoint.x);
                TextView mTvTipDate6 = stepDetailTipViewHolder.getMTvTipDate();
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
                T mPresenter7 = getMPresenter();
                if (mPresenter7 == 0) {
                    Intrinsics.throwNpe();
                }
                List<StepDayData> sportList3 = ((StepDetailPresenter) mPresenter7).getSportList();
                if (sportList3 != null && sportList3.size() > barChartPoint.getIndex()) {
                    StepDayData stepDayData3 = sportList3.get(barChartPoint.getIndex());
                    TextView mTvTipAvg7 = stepDetailTipViewHolder.getMTvTipAvg();
                    if (mTvTipAvg7 != null) {
                        mTvTipAvg7.setText(String.valueOf(stepDayData3.getDayAvgStep()));
                    }
                    String date3 = stepDayData3.getDate();
                    TextView mTvTipDate7 = stepDetailTipViewHolder.getMTvTipDate();
                    if (mTvTipDate7 != null) {
                        mTvTipDate7.setText(DateUtil.format(DateUtil.string2Date(stepDayData3.getDate(), DateUtil.DATE_FORMAT_YMD), DateUtil.DATE_FORMAT_MY));
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

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailFragment
    protected boolean showTopRightLayout() {
        return ((StepDetailPresenter) getMPresenter()).getMDateType() != 1;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCoreFragment, com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public View getTipLayContent() {
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder tipViewHolder = mCallBack != null ? mCallBack.getTipViewHolder(this) : null;
        if (tipViewHolder instanceof StepDetailTipViewHolder) {
            return ((StepDetailTipViewHolder) tipViewHolder).getMLayTipContent();
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0146  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0152  */
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailFragment
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void refreshUiByDateType() {
        /*
            Method dump skipped, instruction units count: 402
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.home.step.StepDetailFragment.refreshUiByDateType():void");
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void showLoading() {
        showLoadingDialog();
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void dismissLoading() {
        dismissLoadingDialog();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void onDetailLoadFailed() {
        if (((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) != null) {
            BarChartBar line_chart = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            Intrinsics.checkExpressionValueIsNotNull(line_chart, "line_chart");
            T mPresenter = getMPresenter();
            if (mPresenter == 0) {
                Intrinsics.throwNpe();
            }
            line_chart.setList(((StepDetailPresenter) mPresenter).getDefaultList());
            ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).setBottomTitle(null);
            if (((StepDetailPresenter) getMPresenter()).getMDateType() == 4) {
                T mPresenter2 = getMPresenter();
                if (mPresenter2 == 0) {
                    Intrinsics.throwNpe();
                }
                int iYear = ((StepDetailPresenter) mPresenter2).year();
                if (iYear > 0) {
                    ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).setBottomTitle(String.valueOf(iYear));
                }
            }
            BarChartBar barChartBar = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            T mPresenter3 = getMPresenter();
            if (mPresenter3 == 0) {
                Intrinsics.throwNpe();
            }
            barChartBar.setDefaultHeight(((StepDetailPresenter) mPresenter3).getMDefaultHeight());
            ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).refreshChart(false);
            refreshTopView(getMActivity());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void onLoadSuccessByDay(boolean showChartAnimator, List<? extends BarChartPoint> day) {
        if (((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) != null) {
            T mPresenter = getMPresenter();
            if (mPresenter == 0) {
                Intrinsics.throwNpe();
            }
            List<BarChartPoint> dayChartList = ((StepDetailPresenter) mPresenter).getDayChartList();
            BarChartBar line_chart = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            Intrinsics.checkExpressionValueIsNotNull(line_chart, "line_chart");
            line_chart.setList(dayChartList);
            BarChartBar line_chart2 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            Intrinsics.checkExpressionValueIsNotNull(line_chart2, "line_chart");
            T mPresenter2 = getMPresenter();
            if (mPresenter2 == 0) {
                Intrinsics.throwNpe();
            }
            line_chart2.setLabelYLeftList(((StepDetailPresenter) mPresenter2).getDefaultYLabelList());
            BarChartBar barChartBar = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (getMPresenter() == 0) {
                Intrinsics.throwNpe();
            }
            barChartBar.setYMinValue(((StepDetailPresenter) r5).getDefaultYMinValue());
            BarChartBar line_chart3 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            Intrinsics.checkExpressionValueIsNotNull(line_chart3, "line_chart");
            List<String> labelYLeftList = line_chart3.getLabelYLeftList();
            if (!(labelYLeftList == null || labelYLeftList.isEmpty())) {
                BarChartBar barChartBar2 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                BarChartBar line_chart4 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                Intrinsics.checkExpressionValueIsNotNull(line_chart4, "line_chart");
                List<String> labelYLeftList2 = line_chart4.getLabelYLeftList();
                BarChartBar line_chart5 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                Intrinsics.checkExpressionValueIsNotNull(line_chart5, "line_chart");
                Intrinsics.checkExpressionValueIsNotNull(labelYLeftList2.get(line_chart5.getLabelYLeftList().size() - 1), "line_chart.labelYLeftLis…rt.labelYLeftList.size-1]");
                barChartBar2.setYMaxValue(Integer.parseInt(r0));
            }
            BarChartBar barChartBar3 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            T mPresenter3 = getMPresenter();
            if (mPresenter3 == 0) {
                Intrinsics.throwNpe();
            }
            barChartBar3.setXMinValue(((StepDetailPresenter) mPresenter3).getMXMinValue());
            BarChartBar barChartBar4 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            T mPresenter4 = getMPresenter();
            if (mPresenter4 == 0) {
                Intrinsics.throwNpe();
            }
            barChartBar4.setXMaxValue(((StepDetailPresenter) mPresenter4).getMXMaxValue());
            BarChartBar barChartBar5 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            T mPresenter5 = getMPresenter();
            if (mPresenter5 == 0) {
                Intrinsics.throwNpe();
            }
            barChartBar5.setDefaultHeight(((StepDetailPresenter) mPresenter5).getMDefaultHeight());
            ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).refreshChart(true);
            refreshTopView(getMActivity());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void onLoadSuccessByWeek(boolean showChartAnimator, List<? extends BarChartPoint> month) {
        if (((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) != null) {
            T mPresenter = getMPresenter();
            if (mPresenter == 0) {
                Intrinsics.throwNpe();
            }
            List<BarChartPoint> weekChartList = ((StepDetailPresenter) mPresenter).getWeekChartList();
            BarChartBar line_chart = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            Intrinsics.checkExpressionValueIsNotNull(line_chart, "line_chart");
            line_chart.setList(weekChartList);
            BarChartBar barChartBar = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            T mPresenter2 = getMPresenter();
            if (mPresenter2 == 0) {
                Intrinsics.throwNpe();
            }
            barChartBar.setXMinValue(((StepDetailPresenter) mPresenter2).getMXMinValue());
            BarChartBar barChartBar2 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            T mPresenter3 = getMPresenter();
            if (mPresenter3 == 0) {
                Intrinsics.throwNpe();
            }
            barChartBar2.setXMaxValue(((StepDetailPresenter) mPresenter3).getMXMaxValue());
            T mPresenter4 = getMPresenter();
            if (mPresenter4 == 0) {
                Intrinsics.throwNpe();
            }
            int targetStep = ((StepDetailPresenter) mPresenter4).getTargetStep();
            this.mGoalLineList.clear();
            if (targetStep > 0) {
                this.mGoalLineList.add(new GoalLineInfo(0, targetStep, targetStep, String.valueOf(targetStep)));
            }
            ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).setGoalLineColor(ResourceUtil.getColor(R.color.color_FF4A00));
            ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).setGoalLineList(this.mGoalLineList);
            BarChartBar line_chart2 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            Intrinsics.checkExpressionValueIsNotNull(line_chart2, "line_chart");
            T mPresenter5 = getMPresenter();
            if (mPresenter5 == 0) {
                Intrinsics.throwNpe();
            }
            line_chart2.setLabelYLeftList(((StepDetailPresenter) mPresenter5).getDefaultYLabelList());
            BarChartBar line_chart3 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            Intrinsics.checkExpressionValueIsNotNull(line_chart3, "line_chart");
            List<String> labelYLeftList = line_chart3.getLabelYLeftList();
            if (!(labelYLeftList == null || labelYLeftList.isEmpty())) {
                BarChartBar barChartBar3 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                BarChartBar line_chart4 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                Intrinsics.checkExpressionValueIsNotNull(line_chart4, "line_chart");
                List<String> labelYLeftList2 = line_chart4.getLabelYLeftList();
                BarChartBar line_chart5 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                Intrinsics.checkExpressionValueIsNotNull(line_chart5, "line_chart");
                Intrinsics.checkExpressionValueIsNotNull(labelYLeftList2.get(line_chart5.getLabelYLeftList().size() - 1), "line_chart.labelYLeftLis…rt.labelYLeftList.size-1]");
                barChartBar3.setYMaxValue(Integer.parseInt(r7));
            }
            BarChartBar barChartBar4 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            T mPresenter6 = getMPresenter();
            if (mPresenter6 == 0) {
                Intrinsics.throwNpe();
            }
            barChartBar4.setDefaultHeight(((StepDetailPresenter) mPresenter6).getMDefaultHeight());
            ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).refreshChart(true);
            refreshTopView(getMActivity());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void onLoadSuccessByMonth(boolean showChartAnimator, List<? extends BarChartPoint> month) {
        if (getMPresenter() == 0 || ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) == null) {
            return;
        }
        T mPresenter = getMPresenter();
        if (mPresenter == 0) {
            Intrinsics.throwNpe();
        }
        List<BarChartPoint> monthChartList = ((StepDetailPresenter) mPresenter).getMonthChartList();
        BarChartBar line_chart = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        Intrinsics.checkExpressionValueIsNotNull(line_chart, "line_chart");
        line_chart.setList(monthChartList);
        BarChartBar barChartBar = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        T mPresenter2 = getMPresenter();
        if (mPresenter2 == 0) {
            Intrinsics.throwNpe();
        }
        barChartBar.setXMinValue(((StepDetailPresenter) mPresenter2).getMXMinValue());
        BarChartBar barChartBar2 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        T mPresenter3 = getMPresenter();
        if (mPresenter3 == 0) {
            Intrinsics.throwNpe();
        }
        barChartBar2.setXMaxValue(((StepDetailPresenter) mPresenter3).getMXMaxValue());
        T mPresenter4 = getMPresenter();
        if (mPresenter4 == 0) {
            Intrinsics.throwNpe();
        }
        int targetStep = ((StepDetailPresenter) mPresenter4).getTargetStep();
        this.mGoalLineList.clear();
        if (targetStep > 0) {
            this.mGoalLineList.add(new GoalLineInfo(0, targetStep, targetStep, String.valueOf(targetStep)));
        }
        ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).setGoalLineColor(ResourceUtil.getColor(R.color.color_FF4A00));
        ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).setGoalLineList(this.mGoalLineList);
        BarChartBar line_chart2 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        Intrinsics.checkExpressionValueIsNotNull(line_chart2, "line_chart");
        T mPresenter5 = getMPresenter();
        if (mPresenter5 == 0) {
            Intrinsics.throwNpe();
        }
        line_chart2.setLabelYLeftList(((StepDetailPresenter) mPresenter5).getDefaultYLabelList());
        BarChartBar line_chart3 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        Intrinsics.checkExpressionValueIsNotNull(line_chart3, "line_chart");
        List<String> labelYLeftList = line_chart3.getLabelYLeftList();
        if (!(labelYLeftList == null || labelYLeftList.isEmpty())) {
            BarChartBar barChartBar3 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            BarChartBar line_chart4 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            Intrinsics.checkExpressionValueIsNotNull(line_chart4, "line_chart");
            List<String> labelYLeftList2 = line_chart4.getLabelYLeftList();
            BarChartBar line_chart5 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            Intrinsics.checkExpressionValueIsNotNull(line_chart5, "line_chart");
            Intrinsics.checkExpressionValueIsNotNull(labelYLeftList2.get(line_chart5.getLabelYLeftList().size() - 1), "line_chart.labelYLeftLis…rt.labelYLeftList.size-1]");
            barChartBar3.setYMaxValue(Integer.parseInt(r7));
        }
        BarChartBar barChartBar4 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        T mPresenter6 = getMPresenter();
        if (mPresenter6 == 0) {
            Intrinsics.throwNpe();
        }
        barChartBar4.setDefaultHeight(((StepDetailPresenter) mPresenter6).getMDefaultHeight());
        ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).refreshChart(true);
        refreshTopView(getMActivity());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void onLoadSuccessByYear(boolean showChartAnimator, List<? extends BarChartPoint> year) {
        if (getMPresenter() == 0 || ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) == null) {
            return;
        }
        T mPresenter = getMPresenter();
        if (mPresenter == 0) {
            Intrinsics.throwNpe();
        }
        List<BarChartPoint> yearChartList = ((StepDetailPresenter) mPresenter).getYearChartList();
        BarChartBar line_chart = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        Intrinsics.checkExpressionValueIsNotNull(line_chart, "line_chart");
        line_chart.setList(yearChartList);
        ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).setBottomTitle(null);
        T mPresenter2 = getMPresenter();
        if (mPresenter2 == 0) {
            Intrinsics.throwNpe();
        }
        int iYear = ((StepDetailPresenter) mPresenter2).year();
        if (iYear > 0) {
            ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).setBottomTitle(String.valueOf(iYear));
        }
        BarChartBar barChartBar = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        T mPresenter3 = getMPresenter();
        if (mPresenter3 == 0) {
            Intrinsics.throwNpe();
        }
        barChartBar.setXMinValue(((StepDetailPresenter) mPresenter3).getMXMinValue());
        BarChartBar barChartBar2 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        T mPresenter4 = getMPresenter();
        if (mPresenter4 == 0) {
            Intrinsics.throwNpe();
        }
        barChartBar2.setXMaxValue(((StepDetailPresenter) mPresenter4).getMXMaxValue());
        T mPresenter5 = getMPresenter();
        if (mPresenter5 == 0) {
            Intrinsics.throwNpe();
        }
        int targetStep = ((StepDetailPresenter) mPresenter5).getTargetStep();
        this.mGoalLineList.clear();
        if (targetStep > 0) {
            this.mGoalLineList.add(new GoalLineInfo(0, targetStep, targetStep, String.valueOf(targetStep)));
        }
        ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).setGoalLineColor(ResourceUtil.getColor(R.color.color_FF4A00));
        ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).setGoalLineList(this.mGoalLineList);
        BarChartBar line_chart2 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        Intrinsics.checkExpressionValueIsNotNull(line_chart2, "line_chart");
        T mPresenter6 = getMPresenter();
        if (mPresenter6 == 0) {
            Intrinsics.throwNpe();
        }
        line_chart2.setLabelYLeftList(((StepDetailPresenter) mPresenter6).getDefaultYLabelList());
        BarChartBar line_chart3 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        Intrinsics.checkExpressionValueIsNotNull(line_chart3, "line_chart");
        List<String> labelYLeftList = line_chart3.getLabelYLeftList();
        if (!(labelYLeftList == null || labelYLeftList.isEmpty())) {
            BarChartBar barChartBar3 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            BarChartBar line_chart4 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            Intrinsics.checkExpressionValueIsNotNull(line_chart4, "line_chart");
            List<String> labelYLeftList2 = line_chart4.getLabelYLeftList();
            BarChartBar line_chart5 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            Intrinsics.checkExpressionValueIsNotNull(line_chart5, "line_chart");
            Intrinsics.checkExpressionValueIsNotNull(labelYLeftList2.get(line_chart5.getLabelYLeftList().size() - 1), "line_chart.labelYLeftLis…rt.labelYLeftList.size-1]");
            barChartBar3.setYMaxValue(Integer.parseInt(r7));
        }
        BarChartBar barChartBar4 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        T mPresenter7 = getMPresenter();
        if (mPresenter7 == 0) {
            Intrinsics.throwNpe();
        }
        barChartBar4.setDefaultHeight(((StepDetailPresenter) mPresenter7).getMDefaultHeight());
        ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).refreshChart(true);
        refreshTopView(getMActivity());
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void setXMaxValue(int maxValue) {
        if (((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) != null) {
            ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).setXMaxValue(maxValue);
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void setXMinValue(int minValue) {
        if (((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) != null) {
            ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).setXMinValue(minValue);
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
        if (topViewHolder instanceof StepDetailTopViewHolder) {
            ((StepDetailTopViewHolder) topViewHolder).showLoadingView();
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void showLoadSuccessView() {
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder topViewHolder = mCallBack != null ? mCallBack.getTopViewHolder(this) : null;
        if (topViewHolder instanceof StepDetailTopViewHolder) {
            ((StepDetailTopViewHolder) topViewHolder).showSuccessView(showTopRightLayout());
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void showLoadFailedView() {
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder topViewHolder = mCallBack != null ? mCallBack.getTopViewHolder(this) : null;
        if (topViewHolder instanceof StepDetailTopViewHolder) {
            ((StepDetailTopViewHolder) topViewHolder).showLoadFailedView(this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public void hideSelectedUi(boolean resetDate) {
        IChartDetailCallback mCallBack;
        if (resetDate && (mCallBack = getMCallBack()) != null) {
            mCallBack.updateSelectDate(this, ((StepDetailPresenter) getMPresenter()).getMStartDate());
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
        if (((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) == null) {
            return 0.0f;
        }
        BarChartBar line_chart = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        Intrinsics.checkExpressionValueIsNotNull(line_chart, "line_chart");
        List<String> labelYLeftList = line_chart.getLabelYLeftList();
        if (labelYLeftList == null || labelYLeftList.size() <= index) {
            return 0.0f;
        }
        Intrinsics.checkExpressionValueIsNotNull(labelYLeftList.get(index), "leftLabelList[index]");
        return Integer.parseInt(r3);
    }

    /* JADX INFO: compiled from: StepDetailFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tR\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/ido/life/module/home/step/StepDetailFragment$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "getInstance", "Lcom/ido/life/module/home/step/StepDetailFragment;", "bundle", "Landroid/os/Bundle;", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final StepDetailFragment getInstance(Bundle bundle) {
            StepDetailFragment stepDetailFragment = new StepDetailFragment();
            if (bundle != null) {
                stepDetailFragment.setArguments(bundle);
            }
            return stepDetailFragment;
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
            if (topViewHolder instanceof StepDetailTopViewHolder) {
                ((StepDetailTopViewHolder) topViewHolder).showLoadingView();
                StepDetailPresenter stepDetailPresenter = (StepDetailPresenter) getMPresenter();
                if (stepDetailPresenter != null) {
                    stepDetailPresenter.getDetailData();
                    return;
                }
                return;
            }
            return;
        }
        showToast(R.string.public_net_unuse);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailFragment
    public StepDetailPresenter getPresenter() {
        return new StepDetailPresenter(this);
    }
}