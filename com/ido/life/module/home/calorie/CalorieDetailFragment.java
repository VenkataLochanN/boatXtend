package com.ido.life.module.home.calorie;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.boat.Xtend.two.R;
import com.ido.common.utils.NetworkUtil;
import com.ido.life.bean.BarChartPoint;
import com.ido.life.customview.charter.BarChartBar;
import com.ido.life.database.model.CalorieDayData;
import com.ido.life.module.home.chartdetail.vertical.BaseDetailCoreFragment;
import com.ido.life.module.home.chartdetail.vertical.BaseDetailViewHolder;
import com.ido.life.module.home.chartdetail.vertical.IChartDetailCallback;
import com.ido.life.util.DateUtil;
import com.ido.life.util.RunTimeUtil;
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

/* JADX INFO: compiled from: CalorieDetailFragment.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b!\u0018\u0000 ;2>\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00012\u00020\u0004:\u0001;B\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\nH\u0016J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\bH\u0014J\b\u0010\u0014\u001a\u00020\bH\u0016J\b\u0010\u0015\u001a\u00020\u0005H\u0016J\n\u0010\u0016\u001a\u0004\u0018\u00010\rH\u0016J\u0010\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u0019\u001a\u00020\n2\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\b\u0010\u001c\u001a\u00020\nH\u0014J\b\u0010\u001d\u001a\u00020\u001bH\u0016J\b\u0010\u001e\u001a\u00020\nH\u0016J\b\u0010\u001f\u001a\u00020\nH\u0016J\u001e\u0010 \u001a\u00020\n2\u0006\u0010!\u001a\u00020\u001b2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0016J\u001e\u0010#\u001a\u00020\n2\u0006\u0010!\u001a\u00020\u001b2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0016J\u001e\u0010%\u001a\u00020\n2\u0006\u0010!\u001a\u00020\u001b2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0016J\u001e\u0010'\u001a\u00020\n2\u0006\u0010!\u001a\u00020\u001b2\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0016J\b\u0010)\u001a\u00020\nH\u0016J\u0012\u0010*\u001a\u00020\n2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014J\u0010\u0010+\u001a\u00020\n2\u0006\u0010,\u001a\u00020\bH\u0016J\b\u0010-\u001a\u00020\nH\u0014J\u0010\u0010.\u001a\u00020\u001b2\u0006\u0010/\u001a\u00020\bH\u0016J\u0012\u00100\u001a\u00020\n2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014J\b\u00101\u001a\u00020\nH\u0014J\u0010\u00102\u001a\u00020\n2\u0006\u00103\u001a\u00020\bH\u0016J\u0010\u00104\u001a\u00020\n2\u0006\u00105\u001a\u00020\bH\u0016J\b\u00106\u001a\u00020\nH\u0016J\b\u00107\u001a\u00020\nH\u0016J\b\u00108\u001a\u00020\nH\u0016J\b\u00109\u001a\u00020\nH\u0016J\b\u0010:\u001a\u00020\u001bH\u0014R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006<"}, d2 = {"Lcom/ido/life/module/home/calorie/CalorieDetailFragment;", "Lcom/ido/life/module/home/chartdetail/vertical/BaseDetailCoreFragment;", "", "Lcom/ido/life/bean/BarChartPoint;", "Lcom/ido/life/module/home/calorie/ICalorieDetailView;", "Lcom/ido/life/module/home/calorie/CalorieDetailPresenter;", "()V", "mCalorieType", "", "clearCache", "", "clickAction", "view", "Landroid/view/View;", "dismissLoading", "getBottomView", "Lcom/ido/life/module/home/chartdetail/vertical/BaseDetailViewHolder;", "context", "Landroid/content/Context;", "getLayoutResId", "getPageType", "getPresenter", "getTipLayContent", "getTipViewHolder", "getTopView", "hideSelectedUi", "resetDate", "", "initView", "needEventBus", "onBottomViewRefresh", "onDetailLoadFailed", "onLoadSuccessByDay", "showChartAnimator", "dayChartList", "onLoadSuccessByMonth", "monthChartList", "onLoadSuccessByWeek", "weekChartList", "onLoadSuccessByYear", "yearChartList", "onTopViewRefresh", "refreshBottomView", "refreshCalorieType", "calorieType", "refreshChart", "refreshChartTipView", "index", "refreshTopView", "refreshUiByDateType", "setXMaxValue", "maxValue", "setXMinValue", "minValue", "showLoadFailedView", "showLoadSuccessView", "showLoading", "showLoadingView", "showTopRightLayout", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
public final class CalorieDetailFragment extends BaseDetailCoreFragment<List<? extends BarChartPoint>, List<? extends BarChartPoint>, List<? extends BarChartPoint>, List<? extends BarChartPoint>, ICalorieDetailView, CalorieDetailPresenter> implements ICalorieDetailView {
    public static final int CALORIE_ACTIVITY = 2;
    public static final int CALORIE_ALL = 1;
    public static final String DATA_CALORIE_TYPE = "calorie_type";
    private HashMap _$_findViewCache;
    private int mCalorieType = 2;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = CalorieDetailFragment.class.getSimpleName();

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
        return R.layout.fragment_calorie;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public int getPageType() {
        return -1;
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

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailFragment
    public CalorieDetailPresenter getPresenter() {
        return new CalorieDetailPresenter(this);
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
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailFragment, com.ido.common.base.BaseCoreFragment
    protected void initView() {
        super.initView();
        if (getMCallBack() != null) {
            IChartDetailCallback mCallBack = getMCallBack();
            if (mCallBack == null) {
                Intrinsics.throwNpe();
            }
            this.mCalorieType = mCallBack.getCalorieType(this);
        }
        ((CalorieDetailPresenter) getMPresenter()).setRateType(this.mCalorieType);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCoreFragment
    protected void refreshTopView(Context context) {
        super.refreshTopView(context);
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder topViewHolder = mCallBack != null ? mCallBack.getTopViewHolder(this) : null;
        if (topViewHolder instanceof CalorieDetailTopViewHolder) {
            topViewHolder.refreshLanguage();
            topViewHolder.setDefaultValue();
            CalorieDetailTopViewHolder calorieDetailTopViewHolder = (CalorieDetailTopViewHolder) topViewHolder;
            TextView textView = calorieDetailTopViewHolder.mTvDate;
            Intrinsics.checkExpressionValueIsNotNull(textView, "topViewHolder.mTvDate");
            textView.setText(((CalorieDetailPresenter) getMPresenter()).getDateText());
            if (this.mCalorieType == 1) {
                TextView textView2 = calorieDetailTopViewHolder.mTvTotalCalorie;
                Intrinsics.checkExpressionValueIsNotNull(textView2, "topViewHolder.mTvTotalCalorie");
                textView2.setText(String.valueOf(((CalorieDetailPresenter) getMPresenter()).getMTotalCalorie()));
                TextView textView3 = calorieDetailTopViewHolder.mTvAvgCalorie;
                Intrinsics.checkExpressionValueIsNotNull(textView3, "topViewHolder.mTvAvgCalorie");
                textView3.setText(String.valueOf(((CalorieDetailPresenter) getMPresenter()).getMAvgCalorie()));
                return;
            }
            TextView textView4 = calorieDetailTopViewHolder.mTvTotalCalorie;
            Intrinsics.checkExpressionValueIsNotNull(textView4, "topViewHolder.mTvTotalCalorie");
            textView4.setText(String.valueOf(((CalorieDetailPresenter) getMPresenter()).getMActivityCalorie()));
            TextView textView5 = calorieDetailTopViewHolder.mTvAvgCalorie;
            Intrinsics.checkExpressionValueIsNotNull(textView5, "topViewHolder.mTvAvgCalorie");
            T mPresenter = getMPresenter();
            if (mPresenter == 0) {
                Intrinsics.throwNpe();
            }
            textView5.setText(String.valueOf(((CalorieDetailPresenter) mPresenter).getMActivityAvgCalorie()));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCoreFragment, com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public BaseDetailViewHolder getBottomView(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        CalorieDetailBottomViewHolder calorieDetailBottomViewHolder = new CalorieDetailBottomViewHolder(LayoutInflater.from(context).inflate(R.layout.detail_bottom_calorie_layout, (ViewGroup) null));
        if (context instanceof View.OnClickListener) {
            View.OnClickListener onClickListener = (View.OnClickListener) context;
            calorieDetailBottomViewHolder.mLeftCard.setOnClickListener(onClickListener);
            calorieDetailBottomViewHolder.mRightCard.setOnClickListener(onClickListener);
        }
        return calorieDetailBottomViewHolder;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCoreFragment
    protected void refreshBottomView(Context context) {
        super.refreshBottomView(context);
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder bottomViewHolder = mCallBack != null ? mCallBack.getBottomViewHolder(this) : null;
        if (bottomViewHolder instanceof CalorieDetailBottomViewHolder) {
            bottomViewHolder.setDefaultValue();
            CalorieDetailBottomViewHolder calorieDetailBottomViewHolder = (CalorieDetailBottomViewHolder) bottomViewHolder;
            calorieDetailBottomViewHolder.updateSelectStatus(this.mCalorieType == 1);
            T mPresenter = getMPresenter();
            if (mPresenter == 0) {
                Intrinsics.throwNpe();
            }
            int mTotalCalorie = ((CalorieDetailPresenter) mPresenter).getMTotalCalorie();
            T mPresenter2 = getMPresenter();
            if (mPresenter2 == 0) {
                Intrinsics.throwNpe();
            }
            int mActivityCalorie = ((CalorieDetailPresenter) mPresenter2).getMActivityCalorie();
            TextView textView = calorieDetailBottomViewHolder.mTvLeftCalorie;
            Intrinsics.checkExpressionValueIsNotNull(textView, "bottomViewHolder.mTvLeftCalorie");
            textView.setText(String.valueOf(mTotalCalorie));
            TextView textView2 = calorieDetailBottomViewHolder.mTvRightCalorie;
            Intrinsics.checkExpressionValueIsNotNull(textView2, "bottomViewHolder.mTvRightCalorie");
            textView2.setText(String.valueOf(mActivityCalorie));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCoreFragment, com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public boolean refreshChartTipView(int index) {
        BarChartPoint barChartPoint;
        CalorieDayData calorieDayData;
        CalorieDayData calorieDayData2;
        super.refreshChartTipView(index);
        if (((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) != null && index >= 0) {
            IChartDetailCallback mCallBack = getMCallBack();
            BaseDetailViewHolder tipViewHolder = mCallBack != null ? mCallBack.getTipViewHolder(this) : null;
            if (!(tipViewHolder instanceof CalorieDetailTipViewHolder)) {
                return false;
            }
            BarChartBar line_chart = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            Intrinsics.checkExpressionValueIsNotNull(line_chart, "line_chart");
            List<T> list = line_chart.getList();
            if (list != 0 && list.size() > index && (barChartPoint = (BarChartPoint) list.get(index)) != null) {
                CalorieDetailTipViewHolder calorieDetailTipViewHolder = (CalorieDetailTipViewHolder) tipViewHolder;
                TextView mTvTipState = calorieDetailTipViewHolder.getMTvTipState();
                if (mTvTipState != null) {
                    mTvTipState.setText(getLanguageText(R.string.public_heat_calorie));
                }
                int mDateType = ((CalorieDetailPresenter) getMPresenter()).getMDateType();
                if (mDateType == 1) {
                    IChartDetailCallback mCallBack2 = getMCallBack();
                    if (mCallBack2 != null) {
                        CalorieDetailFragment calorieDetailFragment = this;
                        T mPresenter = getMPresenter();
                        if (mPresenter == 0) {
                            Intrinsics.throwNpe();
                        }
                        mCallBack2.updateSelectDate(calorieDetailFragment, ((CalorieDetailPresenter) mPresenter).getMStartDate());
                    }
                    TextView mTvTipTitleAvg = calorieDetailTipViewHolder.getMTvTipTitleAvg();
                    if (mTvTipTitleAvg != null) {
                        mTvTipTitleAvg.setVisibility(8);
                    }
                    int iRoundToInt = MathKt.roundToInt(barChartPoint.x) - 1;
                    int i = iRoundToInt + 1;
                    int actualValue = (int) barChartPoint.getActualValue();
                    TextView mTvTipAvg = calorieDetailTipViewHolder.getMTvTipAvg();
                    if (mTvTipAvg != null) {
                        mTvTipAvg.setText(String.valueOf(actualValue));
                    }
                    TextView mTvTipDate = calorieDetailTipViewHolder.getMTvTipDate();
                    if (mTvTipDate != null) {
                        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                        Object[] objArr = {Integer.valueOf(iRoundToInt), Integer.valueOf(i)};
                        String str = String.format("%02d:00-%02d:00", Arrays.copyOf(objArr, objArr.length));
                        Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(format, *args)");
                        mTvTipDate.setText(str);
                    }
                } else if (mDateType == 2) {
                    TextView mTvTipTitleAvg2 = calorieDetailTipViewHolder.getMTvTipTitleAvg();
                    if (mTvTipTitleAvg2 != null) {
                        mTvTipTitleAvg2.setVisibility(8);
                    }
                    if (barChartPoint.hasEffect()) {
                        TextView mTvTipAvg2 = calorieDetailTipViewHolder.getMTvTipAvg();
                        if (mTvTipAvg2 != null) {
                            mTvTipAvg2.setText(String.valueOf(MathKt.roundToInt(barChartPoint.getActualValue())));
                        }
                        Calendar calendar = Calendar.getInstance(Locale.getDefault());
                        Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
                        T mPresenter2 = getMPresenter();
                        if (mPresenter2 == 0) {
                            Intrinsics.throwNpe();
                        }
                        calendar.setTime(DateUtil.string2Date(((CalorieDetailPresenter) mPresenter2).getMStartDate(), DateUtil.DATE_FORMAT_YMD));
                        calendar.add(5, MathKt.roundToInt(barChartPoint.x) - 1);
                        TextView mTvTipDate2 = calorieDetailTipViewHolder.getMTvTipDate();
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
                        List<CalorieDayData> calorieList = ((CalorieDetailPresenter) mPresenter3).getCalorieList();
                        if (calorieList != null && calorieList.size() > barChartPoint.getIndex() && (calorieDayData = calorieList.get(barChartPoint.getIndex())) != null) {
                            if (this.mCalorieType == 1) {
                                TextView mTvTipAvg3 = calorieDetailTipViewHolder.getMTvTipAvg();
                                if (mTvTipAvg3 != null) {
                                    mTvTipAvg3.setText(String.valueOf(calorieDayData.getTotalCalorie()));
                                }
                            } else {
                                TextView mTvTipAvg4 = calorieDetailTipViewHolder.getMTvTipAvg();
                                if (mTvTipAvg4 != null) {
                                    mTvTipAvg4.setText(String.valueOf(calorieDayData.getActivityCalorie()));
                                }
                            }
                            String date = calorieDayData.getDate();
                            TextView mTvTipDate3 = calorieDetailTipViewHolder.getMTvTipDate();
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
                    TextView mTvTipTitleAvg3 = calorieDetailTipViewHolder.getMTvTipTitleAvg();
                    if (mTvTipTitleAvg3 != null) {
                        mTvTipTitleAvg3.setVisibility(8);
                    }
                    if (barChartPoint.hasEffect()) {
                        TextView mTvTipAvg5 = calorieDetailTipViewHolder.getMTvTipAvg();
                        if (mTvTipAvg5 != null) {
                            mTvTipAvg5.setText(String.valueOf(MathKt.roundToInt(barChartPoint.getActualValue())));
                        }
                        int iRoundToInt2 = MathKt.roundToInt(barChartPoint.x);
                        Calendar calendar2 = Calendar.getInstance(Locale.getDefault());
                        Intrinsics.checkExpressionValueIsNotNull(calendar2, "calendar");
                        T mPresenter4 = getMPresenter();
                        if (mPresenter4 == 0) {
                            Intrinsics.throwNpe();
                        }
                        calendar2.setTime(DateUtil.string2Date(((CalorieDetailPresenter) mPresenter4).getMStartDate(), DateUtil.DATE_FORMAT_YMD));
                        RunTimeUtil runTimeUtil = RunTimeUtil.getInstance();
                        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil, "RunTimeUtil.getInstance()");
                        calendar2.setFirstDayOfWeek(runTimeUtil.getWeekStart());
                        calendar2.set(5, iRoundToInt2);
                        TextView mTvTipDate4 = calorieDetailTipViewHolder.getMTvTipDate();
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
                        List<CalorieDayData> calorieList2 = ((CalorieDetailPresenter) mPresenter5).getCalorieList();
                        if (calorieList2 != null && calorieList2.size() > barChartPoint.getIndex() && (calorieDayData2 = calorieList2.get(barChartPoint.getIndex())) != null) {
                            if (this.mCalorieType == 1) {
                                TextView mTvTipAvg6 = calorieDetailTipViewHolder.getMTvTipAvg();
                                if (mTvTipAvg6 != null) {
                                    mTvTipAvg6.setText(String.valueOf(calorieDayData2.getTotalCalorie()));
                                }
                            } else {
                                TextView mTvTipAvg7 = calorieDetailTipViewHolder.getMTvTipAvg();
                                if (mTvTipAvg7 != null) {
                                    mTvTipAvg7.setText(String.valueOf(calorieDayData2.getActivityCalorie()));
                                }
                            }
                            String date2 = calorieDayData2.getDate();
                            TextView mTvTipDate5 = calorieDetailTipViewHolder.getMTvTipDate();
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
                    TextView mTvTipTitleAvg4 = calorieDetailTipViewHolder.getMTvTipTitleAvg();
                    if (mTvTipTitleAvg4 != null) {
                        mTvTipTitleAvg4.setText(getLanguageText(R.string.detail_average_daily));
                    }
                    if (barChartPoint.hasEffect()) {
                        TextView mTvTipAvg8 = calorieDetailTipViewHolder.getMTvTipAvg();
                        if (mTvTipAvg8 != null) {
                            mTvTipAvg8.setText(String.valueOf(MathKt.roundToInt(barChartPoint.getActualValue())));
                        }
                        Calendar calendar3 = Calendar.getInstance(Locale.getDefault());
                        Intrinsics.checkExpressionValueIsNotNull(calendar3, "calendar");
                        T mPresenter6 = getMPresenter();
                        if (mPresenter6 == 0) {
                            Intrinsics.throwNpe();
                        }
                        calendar3.setTime(DateUtil.string2Date(((CalorieDetailPresenter) mPresenter6).getMStartDate(), DateUtil.DATE_FORMAT_YMD));
                        RunTimeUtil runTimeUtil2 = RunTimeUtil.getInstance();
                        Intrinsics.checkExpressionValueIsNotNull(runTimeUtil2, "RunTimeUtil.getInstance()");
                        calendar3.setFirstDayOfWeek(runTimeUtil2.getWeekStart());
                        int i2 = calendar3.get(1);
                        int iRoundToInt3 = MathKt.roundToInt(barChartPoint.x);
                        TextView mTvTipDate6 = calorieDetailTipViewHolder.getMTvTipDate();
                        if (mTvTipDate6 != null) {
                            StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                            Object[] objArr2 = {Integer.valueOf(Math.round(barChartPoint.x)), Integer.valueOf(calendar3.get(1))};
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
                        List<CalorieDayData> calorieList3 = ((CalorieDetailPresenter) mPresenter7).getCalorieList();
                        if (calorieList3 != null && calorieList3.size() > barChartPoint.getIndex()) {
                            CalorieDayData calorieDayData3 = calorieList3.get(barChartPoint.getIndex());
                            if (this.mCalorieType == 1) {
                                TextView mTvTipAvg9 = calorieDetailTipViewHolder.getMTvTipAvg();
                                if (mTvTipAvg9 != null) {
                                    mTvTipAvg9.setText(String.valueOf(calorieDayData3.getDayAvgCalorie()));
                                }
                            } else {
                                TextView mTvTipAvg10 = calorieDetailTipViewHolder.getMTvTipAvg();
                                if (mTvTipAvg10 != null) {
                                    mTvTipAvg10.setText(String.valueOf(calorieDayData3.getDayAvgActivityCalorie()));
                                }
                            }
                            String date3 = calorieDayData3.getDate();
                            TextView mTvTipDate7 = calorieDetailTipViewHolder.getMTvTipDate();
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

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void onLoadSuccessByDay(boolean showChartAnimator, List<? extends BarChartPoint> dayChartList) {
        Intrinsics.checkParameterIsNotNull(dayChartList, "dayChartList");
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder bottomViewHolder = mCallBack != null ? mCallBack.getBottomViewHolder(this) : null;
        CalorieDetailPresenter calorieDetailPresenter = (CalorieDetailPresenter) getMPresenter();
        if (calorieDetailPresenter == null || calorieDetailPresenter.getMDateType() != 1 || this.mRootView == null || !(bottomViewHolder instanceof CalorieDetailBottomViewHolder) || ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) == null) {
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
        barChartBar2.mYMinValue = ((CalorieDetailPresenter) r1).getDefaultYMinValue();
        BarChartBar barChartBar3 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        T mPresenter = getMPresenter();
        if (mPresenter == 0) {
            Intrinsics.throwNpe();
        }
        barChartBar3.setLabelYLeftList(((CalorieDetailPresenter) mPresenter).defaultYLabelList());
        Intrinsics.checkExpressionValueIsNotNull(((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).getLabelYLeftList(), "line_chart.getLabelYLeftList()");
        if (!r7.isEmpty()) {
            BarChartBar barChartBar4 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            Intrinsics.checkExpressionValueIsNotNull(((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).getLabelYLeftList().get(((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).getLabelYLeftList().size() - 1), "line_chart.getLabelYLeft…abelYLeftList().size - 1]");
            barChartBar4.mYMaxValue = Integer.parseInt(r1);
        }
        BarChartBar barChartBar5 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        T mPresenter2 = getMPresenter();
        if (mPresenter2 == 0) {
            Intrinsics.throwNpe();
        }
        barChartBar5.mXMinValue = ((CalorieDetailPresenter) mPresenter2).getMXMinValue();
        BarChartBar barChartBar6 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        T mPresenter3 = getMPresenter();
        if (mPresenter3 == 0) {
            Intrinsics.throwNpe();
        }
        barChartBar6.mXMaxValue = ((CalorieDetailPresenter) mPresenter3).getMXMaxValue();
        ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).refreshChart(showChartAnimator);
        refreshTopView(getMActivity());
        T mPresenter4 = getMPresenter();
        if (mPresenter4 == 0) {
            Intrinsics.throwNpe();
        }
        int mTotalCalorie = ((CalorieDetailPresenter) mPresenter4).getMTotalCalorie();
        T mPresenter5 = getMPresenter();
        if (mPresenter5 == 0) {
            Intrinsics.throwNpe();
        }
        int mActivityCalorie = ((CalorieDetailPresenter) mPresenter5).getMActivityCalorie();
        CalorieDetailBottomViewHolder calorieDetailBottomViewHolder = (CalorieDetailBottomViewHolder) bottomViewHolder;
        TextView textView = calorieDetailBottomViewHolder.mTvLeftCalorie;
        Intrinsics.checkExpressionValueIsNotNull(textView, "bottomViewHolder.mTvLeftCalorie");
        textView.setText(String.valueOf(mTotalCalorie));
        TextView textView2 = calorieDetailBottomViewHolder.mTvRightCalorie;
        Intrinsics.checkExpressionValueIsNotNull(textView2, "bottomViewHolder.mTvRightCalorie");
        textView2.setText(String.valueOf(mActivityCalorie));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void onLoadSuccessByWeek(boolean showChartAnimator, List<? extends BarChartPoint> weekChartList) {
        CalorieDetailPresenter calorieDetailPresenter;
        Intrinsics.checkParameterIsNotNull(weekChartList, "weekChartList");
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder bottomViewHolder = mCallBack != null ? mCallBack.getBottomViewHolder(this) : null;
        if (this.mRootView == null || (calorieDetailPresenter = (CalorieDetailPresenter) getMPresenter()) == null || calorieDetailPresenter.getMDateType() != 2 || !(bottomViewHolder instanceof CalorieDetailBottomViewHolder) || ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) == null) {
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
        barChartBar2.setLabelYLeftList(((CalorieDetailPresenter) mPresenter).defaultYLabelList());
        Intrinsics.checkExpressionValueIsNotNull(((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).getLabelYLeftList(), "line_chart.getLabelYLeftList()");
        if (!r6.isEmpty()) {
            BarChartBar barChartBar3 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            Intrinsics.checkExpressionValueIsNotNull(((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).getLabelYLeftList().get(((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).getLabelYLeftList().size() - 1), "line_chart.getLabelYLeft…abelYLeftList().size - 1]");
            barChartBar3.mYMaxValue = Integer.parseInt(r1);
        }
        BarChartBar barChartBar4 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        T mPresenter2 = getMPresenter();
        if (mPresenter2 == 0) {
            Intrinsics.throwNpe();
        }
        barChartBar4.mXMinValue = ((CalorieDetailPresenter) mPresenter2).getMXMinValue();
        BarChartBar barChartBar5 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        T mPresenter3 = getMPresenter();
        if (mPresenter3 == 0) {
            Intrinsics.throwNpe();
        }
        barChartBar5.mXMaxValue = ((CalorieDetailPresenter) mPresenter3).getMXMaxValue();
        ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).refreshChart(showChartAnimator);
        refreshTopView(getMActivity());
        T mPresenter4 = getMPresenter();
        if (mPresenter4 == 0) {
            Intrinsics.throwNpe();
        }
        int mTotalCalorie = ((CalorieDetailPresenter) mPresenter4).getMTotalCalorie();
        T mPresenter5 = getMPresenter();
        if (mPresenter5 == 0) {
            Intrinsics.throwNpe();
        }
        int mActivityCalorie = ((CalorieDetailPresenter) mPresenter5).getMActivityCalorie();
        CalorieDetailBottomViewHolder calorieDetailBottomViewHolder = (CalorieDetailBottomViewHolder) bottomViewHolder;
        TextView textView = calorieDetailBottomViewHolder.mTvLeftCalorie;
        Intrinsics.checkExpressionValueIsNotNull(textView, "bottomViewHolder.mTvLeftCalorie");
        textView.setText(String.valueOf(mTotalCalorie));
        TextView textView2 = calorieDetailBottomViewHolder.mTvRightCalorie;
        Intrinsics.checkExpressionValueIsNotNull(textView2, "bottomViewHolder.mTvRightCalorie");
        textView2.setText(String.valueOf(mActivityCalorie));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void onLoadSuccessByMonth(boolean showChartAnimator, List<? extends BarChartPoint> monthChartList) {
        Intrinsics.checkParameterIsNotNull(monthChartList, "monthChartList");
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder bottomViewHolder = mCallBack != null ? mCallBack.getBottomViewHolder(this) : null;
        CalorieDetailPresenter calorieDetailPresenter = (CalorieDetailPresenter) getMPresenter();
        if (calorieDetailPresenter == null || calorieDetailPresenter.getMDateType() != 3 || this.mRootView == null || !(bottomViewHolder instanceof CalorieDetailBottomViewHolder) || ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) == null) {
            return;
        }
        BarChartBar barChartBar = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (monthChartList.isEmpty()) {
            monthChartList = null;
        }
        barChartBar.setList(monthChartList);
        BarChartBar barChartBar2 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        T mPresenter = getMPresenter();
        if (mPresenter == 0) {
            Intrinsics.throwNpe();
        }
        barChartBar2.setLabelYLeftList(((CalorieDetailPresenter) mPresenter).defaultYLabelList());
        Intrinsics.checkExpressionValueIsNotNull(((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).getLabelYLeftList(), "line_chart.getLabelYLeftList()");
        if (!r6.isEmpty()) {
            BarChartBar barChartBar3 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            Intrinsics.checkExpressionValueIsNotNull(((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).getLabelYLeftList().get(((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).getLabelYLeftList().size() - 1), "line_chart.getLabelYLeft…abelYLeftList().size - 1]");
            barChartBar3.mYMaxValue = Integer.parseInt(r1);
        }
        BarChartBar barChartBar4 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        T mPresenter2 = getMPresenter();
        if (mPresenter2 == 0) {
            Intrinsics.throwNpe();
        }
        barChartBar4.mXMinValue = ((CalorieDetailPresenter) mPresenter2).getMXMinValue();
        BarChartBar barChartBar5 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        T mPresenter3 = getMPresenter();
        if (mPresenter3 == 0) {
            Intrinsics.throwNpe();
        }
        barChartBar5.mXMaxValue = ((CalorieDetailPresenter) mPresenter3).getMXMaxValue();
        ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).refreshChart(showChartAnimator);
        refreshTopView(getMActivity());
        T mPresenter4 = getMPresenter();
        if (mPresenter4 == 0) {
            Intrinsics.throwNpe();
        }
        int mTotalCalorie = ((CalorieDetailPresenter) mPresenter4).getMTotalCalorie();
        T mPresenter5 = getMPresenter();
        if (mPresenter5 == 0) {
            Intrinsics.throwNpe();
        }
        int mActivityCalorie = ((CalorieDetailPresenter) mPresenter5).getMActivityCalorie();
        CalorieDetailBottomViewHolder calorieDetailBottomViewHolder = (CalorieDetailBottomViewHolder) bottomViewHolder;
        TextView textView = calorieDetailBottomViewHolder.mTvLeftCalorie;
        Intrinsics.checkExpressionValueIsNotNull(textView, "bottomViewHolder.mTvLeftCalorie");
        textView.setText(String.valueOf(mTotalCalorie));
        TextView textView2 = calorieDetailBottomViewHolder.mTvRightCalorie;
        Intrinsics.checkExpressionValueIsNotNull(textView2, "bottomViewHolder.mTvRightCalorie");
        textView2.setText(String.valueOf(mActivityCalorie));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void onLoadSuccessByYear(boolean showChartAnimator, List<? extends BarChartPoint> yearChartList) {
        Intrinsics.checkParameterIsNotNull(yearChartList, "yearChartList");
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder bottomViewHolder = mCallBack != null ? mCallBack.getBottomViewHolder(this) : null;
        CalorieDetailPresenter calorieDetailPresenter = (CalorieDetailPresenter) getMPresenter();
        if (calorieDetailPresenter == null || calorieDetailPresenter.getMDateType() != 4 || this.mRootView == null || !(bottomViewHolder instanceof CalorieDetailBottomViewHolder) || ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) == null) {
            return;
        }
        ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).setList(yearChartList);
        BarChartBar barChartBar = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        T mPresenter = getMPresenter();
        if (mPresenter == 0) {
            Intrinsics.throwNpe();
        }
        barChartBar.setLabelYLeftList(((CalorieDetailPresenter) mPresenter).defaultYLabelList());
        Intrinsics.checkExpressionValueIsNotNull(((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).getLabelYLeftList(), "line_chart.getLabelYLeftList()");
        if (!r6.isEmpty()) {
            BarChartBar barChartBar2 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            Intrinsics.checkExpressionValueIsNotNull(((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).getLabelYLeftList().get(((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).getLabelYLeftList().size() - 1), "line_chart.getLabelYLeft…abelYLeftList().size - 1]");
            barChartBar2.mYMaxValue = Integer.parseInt(r2);
        }
        ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).mBottomTitle = (String) null;
        T mPresenter2 = getMPresenter();
        if (mPresenter2 == 0) {
            Intrinsics.throwNpe();
        }
        int iYear = ((CalorieDetailPresenter) mPresenter2).year();
        if (iYear > 0) {
            ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).mBottomTitle = String.valueOf(iYear);
        }
        BarChartBar barChartBar3 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        T mPresenter3 = getMPresenter();
        if (mPresenter3 == 0) {
            Intrinsics.throwNpe();
        }
        barChartBar3.mXMinValue = ((CalorieDetailPresenter) mPresenter3).getMXMinValue();
        BarChartBar barChartBar4 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        T mPresenter4 = getMPresenter();
        if (mPresenter4 == 0) {
            Intrinsics.throwNpe();
        }
        barChartBar4.mXMaxValue = ((CalorieDetailPresenter) mPresenter4).getMXMaxValue();
        ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).refreshChart(showChartAnimator);
        refreshTopView(getMActivity());
        T mPresenter5 = getMPresenter();
        if (mPresenter5 == 0) {
            Intrinsics.throwNpe();
        }
        int mTotalCalorie = ((CalorieDetailPresenter) mPresenter5).getMTotalCalorie();
        T mPresenter6 = getMPresenter();
        if (mPresenter6 == 0) {
            Intrinsics.throwNpe();
        }
        int mActivityCalorie = ((CalorieDetailPresenter) mPresenter6).getMActivityCalorie();
        CalorieDetailBottomViewHolder calorieDetailBottomViewHolder = (CalorieDetailBottomViewHolder) bottomViewHolder;
        TextView textView = calorieDetailBottomViewHolder.mTvLeftCalorie;
        Intrinsics.checkExpressionValueIsNotNull(textView, "bottomViewHolder.mTvLeftCalorie");
        textView.setText(String.valueOf(mTotalCalorie));
        TextView textView2 = calorieDetailBottomViewHolder.mTvRightCalorie;
        Intrinsics.checkExpressionValueIsNotNull(textView2, "bottomViewHolder.mTvRightCalorie");
        textView2.setText(String.valueOf(mActivityCalorie));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void onDetailLoadFailed() {
        if (this.mRootView == null || getMPresenter() == 0 || ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) == null) {
            return;
        }
        BarChartBar barChartBar = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        T mPresenter = getMPresenter();
        if (mPresenter == 0) {
            Intrinsics.throwNpe();
        }
        List<BarChartPoint> defaultList = ((CalorieDetailPresenter) mPresenter).getDefaultList();
        if (defaultList == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.List<kotlin.Nothing>");
        }
        barChartBar.setList(defaultList);
        ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).mBottomTitle = (String) null;
        if (((CalorieDetailPresenter) getMPresenter()).getMDateType() == 4) {
            T mPresenter2 = getMPresenter();
            if (mPresenter2 == 0) {
                Intrinsics.throwNpe();
            }
            int iYear = ((CalorieDetailPresenter) mPresenter2).year();
            if (iYear > 0) {
                ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).mBottomTitle = String.valueOf(iYear);
            }
        }
        BarChartBar barChartBar2 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (barChartBar2 != null) {
            T mPresenter3 = getMPresenter();
            if (mPresenter3 == 0) {
                Intrinsics.throwNpe();
            }
            barChartBar2.setDefaultHeight(((CalorieDetailPresenter) mPresenter3).getMDefaultHeight());
        }
        ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).refreshChart(false);
        refreshTopView(getMActivity());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailFragment
    protected boolean showTopRightLayout() {
        CalorieDetailPresenter calorieDetailPresenter = (CalorieDetailPresenter) getMPresenter();
        return calorieDetailPresenter == null || calorieDetailPresenter.getMDateType() != 1;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCoreFragment, com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public View getTipLayContent() {
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder tipViewHolder = mCallBack != null ? mCallBack.getTipViewHolder(this) : null;
        if (tipViewHolder instanceof CalorieDetailTipViewHolder) {
            return ((CalorieDetailTipViewHolder) tipViewHolder).getMLayTipContent();
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:54:0x012f  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x013a  */
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailFragment
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void refreshUiByDateType() {
        /*
            Method dump skipped, instruction units count: 429
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.module.home.calorie.CalorieDetailFragment.refreshUiByDateType():void");
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
        if (topViewHolder instanceof CalorieDetailTopViewHolder) {
            ((CalorieDetailTopViewHolder) topViewHolder).showLoadingView();
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void showLoadSuccessView() {
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder topViewHolder = mCallBack != null ? mCallBack.getTopViewHolder(this) : null;
        if (topViewHolder instanceof CalorieDetailTopViewHolder) {
            ((CalorieDetailTopViewHolder) topViewHolder).showSuccessView(showTopRightLayout());
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void showLoadFailedView() {
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder topViewHolder = mCallBack != null ? mCallBack.getTopViewHolder(this) : null;
        if (topViewHolder instanceof CalorieDetailTopViewHolder) {
            ((CalorieDetailTopViewHolder) topViewHolder).showLoadFailedView(this);
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public BaseDetailViewHolder getTopView(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        return new CalorieDetailTopViewHolder(LayoutInflater.from(context).inflate(R.layout.detail_top_calorie_layout, (ViewGroup) null));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public void hideSelectedUi(boolean resetDate) {
        IChartDetailCallback mCallBack;
        if (resetDate && (mCallBack = getMCallBack()) != null) {
            CalorieDetailFragment calorieDetailFragment = this;
            CalorieDetailPresenter calorieDetailPresenter = (CalorieDetailPresenter) getMPresenter();
            mCallBack.updateSelectDate(calorieDetailFragment, calorieDetailPresenter != null ? calorieDetailPresenter.getMStartDate() : null);
        }
        BarChartBar barChartBar = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (barChartBar != null) {
            barChartBar.refreshChart(false);
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCoreFragment, com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public void refreshCalorieType(int calorieType) {
        super.refreshCalorieType(calorieType);
        if (this.mCalorieType == calorieType) {
            return;
        }
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder bottomViewHolder = mCallBack != null ? mCallBack.getBottomViewHolder(this) : null;
        if (bottomViewHolder instanceof CalorieDetailBottomViewHolder) {
            this.mCalorieType = calorieType;
            ((CalorieDetailBottomViewHolder) bottomViewHolder).updateSelectStatus(this.mCalorieType == 1);
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
            if (topViewHolder instanceof CalorieDetailTopViewHolder) {
                ((CalorieDetailTopViewHolder) topViewHolder).showLoadingView();
                ((CalorieDetailPresenter) getMPresenter()).getDetailData();
                return;
            }
            return;
        }
        showToast(R.string.public_net_unuse);
    }

    /* JADX INFO: compiled from: CalorieDetailFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n \t*\u0004\u0018\u00010\u00070\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/ido/life/module/home/calorie/CalorieDetailFragment$Companion;", "", "()V", "CALORIE_ACTIVITY", "", "CALORIE_ALL", "DATA_CALORIE_TYPE", "", "TAG", "kotlin.jvm.PlatformType", "getInstance", "Lcom/ido/life/module/home/calorie/CalorieDetailFragment;", "bundle", "Landroid/os/Bundle;", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final CalorieDetailFragment getInstance(Bundle bundle) {
            CalorieDetailFragment calorieDetailFragment = new CalorieDetailFragment();
            if (bundle != null) {
                calorieDetailFragment.setArguments(bundle);
            }
            return calorieDetailFragment;
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

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public BaseDetailViewHolder getTipViewHolder(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.detail_chart_tip_calorie_layout, (ViewGroup) null);
        Intrinsics.checkExpressionValueIsNotNull(viewInflate, "LayoutInflater.from(cont…tip_calorie_layout, null)");
        return new CalorieDetailTipViewHolder(viewInflate);
    }
}