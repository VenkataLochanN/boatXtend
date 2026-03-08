package com.ido.life.module.home.ambientvolume;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.boat.Xtend.two.R;
import com.ido.ble.event.stat.one.d;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.widget.textview.MediumTextView;
import com.ido.life.customview.AmbientVolumePassedChartView;
import com.ido.life.customview.AmbientVolumeProgressBar;
import com.ido.life.customview.charter.CustomChatBar;
import com.ido.life.customview.charter.FloatLineChartBar;
import com.ido.life.dialog.AmbientVolumeLevelInfoDialog;
import com.ido.life.module.home.chartdetail.vertical.BaseDetailCoreFragment;
import com.ido.life.module.home.chartdetail.vertical.BaseDetailViewHolder;
import com.ido.life.module.home.chartdetail.vertical.IChartDetailCallback;
import com.ido.life.util.DateUtil;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: AmbientVolumeDetailFragment.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0016\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\b\u001b\u0018\u0000 g2>\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00012\u00020\u0004:\u0001gB\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\t\u001a\u00020\nH\u0002J\b\u0010\u000b\u001a\u00020\nH\u0016J\u0010\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\nH\u0016J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0010\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0015\u001a\u00020\bH\u0014J\b\u0010\u0016\u001a\u00020\bH\u0016J\b\u0010\u0017\u001a\u00020\u0005H\u0016J\n\u0010\u0018\u001a\u0004\u0018\u00010\u000eH\u0016J\u0010\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0010\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0010\u0010\u001b\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\b\u0010\u001e\u001a\u00020\nH\u0014J\b\u0010\u001f\u001a\u00020\nH\u0016J\b\u0010 \u001a\u00020\nH\u0016J\u001e\u0010!\u001a\u00020\n2\u0006\u0010\"\u001a\u00020\u001d2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0016J\u001e\u0010$\u001a\u00020\n2\u0006\u0010\"\u001a\u00020\u001d2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0016J\u001e\u0010&\u001a\u00020\n2\u0006\u0010\"\u001a\u00020\u001d2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0016J\u001e\u0010(\u001a\u00020\n2\u0006\u0010\"\u001a\u00020\u001d2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0016J\b\u0010*\u001a\u00020\nH\u0016J\u0010\u0010+\u001a\u00020\u001d2\u0006\u0010,\u001a\u00020\bH\u0016J\u0010\u0010-\u001a\u00020\n2\u0006\u0010.\u001a\u00020\u001dH\u0016J\b\u0010/\u001a\u00020\nH\u0014J\u0010\u00100\u001a\u00020\n2\u0006\u00101\u001a\u00020\u001dH\u0016J\u0016\u00102\u001a\u00020\n2\f\u00103\u001a\b\u0012\u0004\u0012\u0002040\u0002H\u0016J\u0016\u00105\u001a\u00020\n2\f\u00106\u001a\b\u0012\u0004\u0012\u0002040\u0002H\u0016J\u0010\u00107\u001a\u00020\n2\u0006\u00108\u001a\u000204H\u0016J\u0018\u00109\u001a\u00020\n2\u0006\u0010:\u001a\u00020\b2\u0006\u0010;\u001a\u00020\bH\u0016J\u0010\u0010<\u001a\u00020\n2\u0006\u00101\u001a\u00020\u001dH\u0016J\u0010\u0010=\u001a\u00020\n2\u0006\u0010>\u001a\u00020\bH\u0016J\u0018\u0010?\u001a\u00020\n2\u0006\u0010@\u001a\u00020\b2\u0006\u0010A\u001a\u00020\bH\u0016J\u0010\u0010B\u001a\u00020\n2\u0006\u0010C\u001a\u00020\bH\u0016J\u001e\u0010D\u001a\u00020\n2\u0006\u0010>\u001a\u00020\b2\f\u0010E\u001a\b\u0012\u0004\u0012\u00020F0\u0002H\u0016J\u0010\u0010G\u001a\u00020\n2\u0006\u00101\u001a\u00020\u001dH\u0016J\u0010\u0010H\u001a\u00020\n2\u0006\u0010I\u001a\u00020\bH\u0016J \u0010J\u001a\u00020\n2\u0006\u0010K\u001a\u00020\b2\u0006\u0010@\u001a\u00020\b2\u0006\u0010A\u001a\u00020\bH\u0016J\u0016\u0010L\u001a\u00020\n2\f\u00103\u001a\b\u0012\u0004\u0012\u0002040MH\u0016J\u0018\u0010N\u001a\u00020\n2\u0006\u0010A\u001a\u00020\b2\u0006\u0010O\u001a\u00020\bH\u0016J\"\u0010P\u001a\u00020\n2\b\u0010Q\u001a\u0004\u0018\u0001042\u0006\u0010R\u001a\u00020\b2\u0006\u0010S\u001a\u00020\bH\u0016J\u0010\u0010T\u001a\u00020\n2\u0006\u00101\u001a\u00020\u001dH\u0016J\"\u0010U\u001a\u00020\n2\b\u0010Q\u001a\u0004\u0018\u0001042\u0006\u0010R\u001a\u00020\b2\u0006\u0010S\u001a\u00020\bH\u0016J\"\u0010V\u001a\u00020\n2\b\u0010Q\u001a\u0004\u0018\u0001042\u0006\u0010R\u001a\u00020\b2\u0006\u0010S\u001a\u00020\bH\u0016J\u0018\u0010W\u001a\u00020\n2\u0006\u0010X\u001a\u00020\b2\u0006\u0010Y\u001a\u00020\bH\u0016J\u0010\u0010Z\u001a\u00020\n2\u0006\u00101\u001a\u00020\u001dH\u0016J\u0010\u0010[\u001a\u00020\n2\u0006\u0010\\\u001a\u00020\bH\u0016J\u0010\u0010]\u001a\u00020\n2\u0006\u0010^\u001a\u00020\bH\u0016J\b\u0010_\u001a\u00020\nH\u0002J\b\u0010`\u001a\u00020\nH\u0016J\b\u0010a\u001a\u00020\nH\u0016J\b\u0010b\u001a\u00020\nH\u0016J\b\u0010c\u001a\u00020\nH\u0016J\b\u0010d\u001a\u00020\u001dH\u0014J\b\u0010e\u001a\u00020\nH\u0002J\b\u0010f\u001a\u00020\nH\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006h"}, d2 = {"Lcom/ido/life/module/home/ambientvolume/AmbientVolumeDetailFragment;", "Lcom/ido/life/module/home/chartdetail/vertical/BaseDetailCoreFragment;", "", "Lcom/ido/life/customview/charter/FloatLineChartBar$ChartBean;", "Lcom/ido/life/module/home/ambientvolume/IAmbientVolumeDetailView;", "Lcom/ido/life/module/home/ambientvolume/AmbientVolumeDetailPresenter;", "()V", "mChartStyle", "", "changeLineVisibility", "", "clearCache", "clickAction", "view", "Landroid/view/View;", "dismissLoading", "getBottomView", "Lcom/ido/life/module/home/chartdetail/vertical/BaseDetailViewHolder;", "context", "Landroid/content/Context;", "getChartHeight", "getLayoutResId", "getPageType", "getPresenter", "getTipLayContent", "getTipViewHolder", "getTopView", "hideSelectedUi", "resetDate", "", "initView", "onBottomViewRefresh", "onDetailLoadFailed", "onLoadSuccessByDay", "showChartAnimator", "dayChartList", "onLoadSuccessByMonth", "monthChartList", "onLoadSuccessByWeek", "weekChartList", "onLoadSuccessByYear", "yearChartList", "onTopViewRefresh", "refreshChartTipView", "index", "refreshTypeAndOffset", "refreshPage", "refreshUiByDateType", "setAboutAmbientVolumeUIVisibility", "visibility", "setChartXLabelList", "xLabelList", "", "setChartYLabelList", "yLabelList", "setDate", "dateDesc", "setDayVolumeCompare", "todayVolume", "yesterdayVolume", "setDayVolumeCompareUIVisibility", "setExposureAvg", "avgVolume", "setExposureDuration", "hour", "min", "setExposureState", "exposureState", "setPassedSevenDaysChartList", "passedChartList", "Lcom/ido/life/customview/AmbientVolumePassedChartView$ChartBean;", "setPassedSevenDaysChartVisibility", "setPassedSevenDaysVolumeState", "volumeState", "setPassedSevenDaysVolumeValue", "volumeValue", "setPassedSevenDaysXLabelList", "", "setPerHourVolumeMaxmin", "max", "setVolumeHighLevelExposureInfo", "desc", d.C, "maxDuration", "setVolumeLevelExposureVisibility", "setVolumeMediumLevelExposureInfo", "setVolumeNormalLevelExposureInfo", "setWeekVolumeCompare", "currentWeekVolume", "previousWeekVolume", "setWeekVolumeCompareUIVisibility", "setXMaxValue", "maxValue", "setXMinValue", "minValue", "showBottomView", "showLoadFailedView", "showLoadSuccessView", "showLoading", "showLoadingView", "showTopRightLayout", "switchToBarChart", "switchToLineChart", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
public final class AmbientVolumeDetailFragment extends BaseDetailCoreFragment<List<? extends FloatLineChartBar.ChartBean>, List<? extends FloatLineChartBar.ChartBean>, List<? extends FloatLineChartBar.ChartBean>, List<? extends FloatLineChartBar.ChartBean>, IAmbientVolumeDetailView, AmbientVolumeDetailPresenter> implements IAmbientVolumeDetailView {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String KEY_CHART_STYLE = "chart_style";
    private HashMap _$_findViewCache;
    private int mChartStyle = 1;

    @JvmStatic
    public static final AmbientVolumeDetailFragment getInstance(Bundle bundle) {
        return INSTANCE.getInstance(bundle);
    }

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
        return R.layout.fragment_ambient_volume_detail_layout;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public int getPageType() {
        return -1;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCoreFragment, com.ido.life.module.home.chartdetail.vertical.BaseDetailFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailFragment
    protected boolean showTopRightLayout() {
        return true;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public /* bridge */ /* synthetic */ void onLoadSuccessByDay(boolean z, List<? extends FloatLineChartBar.ChartBean> list) {
        onLoadSuccessByDay2(z, (List<FloatLineChartBar.ChartBean>) list);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public /* bridge */ /* synthetic */ void onLoadSuccessByMonth(boolean z, List<? extends FloatLineChartBar.ChartBean> list) {
        onLoadSuccessByMonth2(z, (List<FloatLineChartBar.ChartBean>) list);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public /* bridge */ /* synthetic */ void onLoadSuccessByWeek(boolean z, List<? extends FloatLineChartBar.ChartBean> list) {
        onLoadSuccessByWeek2(z, (List<FloatLineChartBar.ChartBean>) list);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public /* bridge */ /* synthetic */ void onLoadSuccessByYear(boolean z, List<? extends FloatLineChartBar.ChartBean> list) {
        onLoadSuccessByYear2(z, (List<FloatLineChartBar.ChartBean>) list);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public BaseDetailViewHolder getTipViewHolder(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.detail_tip_ambient_volume_layout, (ViewGroup) null);
        Intrinsics.checkExpressionValueIsNotNull(viewInflate, "LayoutInflater.from(cont…ient_volume_layout, null)");
        return new AmbientVolumeDetailTipViewHolder(viewInflate);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCoreFragment, com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public View getTipLayContent() {
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder tipViewHolder = mCallBack != null ? mCallBack.getTipViewHolder(this) : null;
        if (tipViewHolder instanceof AmbientVolumeDetailTipViewHolder) {
            return ((AmbientVolumeDetailTipViewHolder) tipViewHolder).getMLayContent();
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public void hideSelectedUi(boolean resetDate) {
        IChartDetailCallback mCallBack;
        if (getMPresenter() != 0 && resetDate && (mCallBack = getMCallBack()) != null) {
            AmbientVolumeDetailFragment ambientVolumeDetailFragment = this;
            T mPresenter = getMPresenter();
            if (mPresenter == 0) {
                Intrinsics.throwNpe();
            }
            mCallBack.updateSelectDate(ambientVolumeDetailFragment, ((AmbientVolumeDetailPresenter) mPresenter).getMStartDate());
        }
        FloatLineChartBar floatLineChartBar = (FloatLineChartBar) _$_findCachedViewById(com.ido.life.R.id.float_line_chart);
        if (floatLineChartBar != null) {
            floatLineChartBar.refreshChart(false);
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCoreFragment, com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public int getChartHeight(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        return context.getResources().getDimensionPixelSize(R.dimen.sw_dp_230);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void setXMaxValue(int maxValue) {
        FloatLineChartBar floatLineChartBar = (FloatLineChartBar) _$_findCachedViewById(com.ido.life.R.id.float_line_chart);
        if (floatLineChartBar != null) {
            floatLineChartBar.mXMaxValue = maxValue;
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void setXMinValue(int minValue) {
        FloatLineChartBar floatLineChartBar = (FloatLineChartBar) _$_findCachedViewById(com.ido.life.R.id.float_line_chart);
        if (floatLineChartBar != null) {
            floatLineChartBar.mXMinValue = minValue;
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void onTopViewRefresh() {
        refreshTopView(getContext());
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void onBottomViewRefresh() {
        refreshBottomView(getContext());
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void clearCache() {
        IChartDetailCallback mCallBack;
        FloatLineChartBar floatLineChartBar = (FloatLineChartBar) _$_findCachedViewById(com.ido.life.R.id.float_line_chart);
        if (floatLineChartBar != null) {
            floatLineChartBar.clearList();
        }
        FloatLineChartBar floatLineChartBar2 = (FloatLineChartBar) _$_findCachedViewById(com.ido.life.R.id.float_line_chart);
        if (floatLineChartBar2 != null) {
            floatLineChartBar2.refreshChart(false);
        }
        if (!isVisible() || (mCallBack = getMCallBack()) == null) {
            return;
        }
        mCallBack.updateSelectDate(this, null);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void showLoadingView() {
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder topViewHolder = mCallBack != null ? mCallBack.getTopViewHolder(this) : null;
        if (topViewHolder instanceof AmbientVolumeDetailTopViewHolder) {
            ((AmbientVolumeDetailTopViewHolder) topViewHolder).showLoadingView();
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void showLoadSuccessView() {
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder topViewHolder = mCallBack != null ? mCallBack.getTopViewHolder(this) : null;
        if (topViewHolder instanceof AmbientVolumeDetailTopViewHolder) {
            ((AmbientVolumeDetailTopViewHolder) topViewHolder).showSuccessView(false);
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void showLoadFailedView() {
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder topViewHolder = mCallBack != null ? mCallBack.getTopViewHolder(this) : null;
        if (topViewHolder instanceof AmbientVolumeDetailTopViewHolder) {
            ((AmbientVolumeDetailTopViewHolder) topViewHolder).showLoadFailedView(this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: onLoadSuccessByDay, reason: avoid collision after fix types in other method */
    public void onLoadSuccessByDay2(boolean showChartAnimator, List<FloatLineChartBar.ChartBean> dayChartList) {
        Intrinsics.checkParameterIsNotNull(dayChartList, "dayChartList");
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder topViewHolder = mCallBack != null ? mCallBack.getTopViewHolder(this) : null;
        if (getMPresenter() == 0 || ((AmbientVolumeDetailPresenter) getMPresenter()).getMDateType() != 1 || this.mRootView == null || !(topViewHolder instanceof AmbientVolumeDetailTopViewHolder)) {
            return;
        }
        showBottomView();
        ((AmbientVolumeDetailTopViewHolder) topViewHolder).showNormalUI();
        TextView textView = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_title_hour_per);
        if (textView != null) {
            textView.setText(getLanguageText(R.string.title_hour_avg));
        }
        FloatLineChartBar floatLineChartBar = (FloatLineChartBar) _$_findCachedViewById(com.ido.life.R.id.float_line_chart);
        if (floatLineChartBar != null) {
            floatLineChartBar.setChartStyle(this.mChartStyle);
        }
        FloatLineChartBar floatLineChartBar2 = (FloatLineChartBar) _$_findCachedViewById(com.ido.life.R.id.float_line_chart);
        if (floatLineChartBar2 != null) {
            floatLineChartBar2.setBarWidthSpaceRadius(0.9f);
        }
        FloatLineChartBar floatLineChartBar3 = (FloatLineChartBar) _$_findCachedViewById(com.ido.life.R.id.float_line_chart);
        if (floatLineChartBar3 != null) {
            floatLineChartBar3.setList(dayChartList);
        }
        FloatLineChartBar floatLineChartBar4 = (FloatLineChartBar) _$_findCachedViewById(com.ido.life.R.id.float_line_chart);
        if (floatLineChartBar4 != null) {
            floatLineChartBar4.refreshChart(showChartAnimator);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: onLoadSuccessByWeek, reason: avoid collision after fix types in other method */
    public void onLoadSuccessByWeek2(boolean showChartAnimator, List<FloatLineChartBar.ChartBean> weekChartList) {
        Intrinsics.checkParameterIsNotNull(weekChartList, "weekChartList");
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder topViewHolder = mCallBack != null ? mCallBack.getTopViewHolder(this) : null;
        if (this.mRootView == null || getMPresenter() == 0 || ((AmbientVolumeDetailPresenter) getMPresenter()).getMDateType() != 2 || !(topViewHolder instanceof AmbientVolumeDetailTopViewHolder)) {
            return;
        }
        showBottomView();
        ((AmbientVolumeDetailTopViewHolder) topViewHolder).showNormalUI();
        TextView textView = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_title_hour_per);
        if (textView != null) {
            textView.setText(getLanguageText(R.string.detail_average_daily));
        }
        FloatLineChartBar floatLineChartBar = (FloatLineChartBar) _$_findCachedViewById(com.ido.life.R.id.float_line_chart);
        if (floatLineChartBar != null) {
            floatLineChartBar.setChartStyle(this.mChartStyle);
        }
        FloatLineChartBar floatLineChartBar2 = (FloatLineChartBar) _$_findCachedViewById(com.ido.life.R.id.float_line_chart);
        if (floatLineChartBar2 != null) {
            floatLineChartBar2.setBarWidthSpaceRadius(0.3f);
        }
        FloatLineChartBar floatLineChartBar3 = (FloatLineChartBar) _$_findCachedViewById(com.ido.life.R.id.float_line_chart);
        if (floatLineChartBar3 != null) {
            floatLineChartBar3.setList(weekChartList);
        }
        FloatLineChartBar floatLineChartBar4 = (FloatLineChartBar) _$_findCachedViewById(com.ido.life.R.id.float_line_chart);
        if (floatLineChartBar4 != null) {
            floatLineChartBar4.refreshChart(showChartAnimator);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: onLoadSuccessByMonth, reason: avoid collision after fix types in other method */
    public void onLoadSuccessByMonth2(boolean showChartAnimator, List<FloatLineChartBar.ChartBean> monthChartList) {
        Intrinsics.checkParameterIsNotNull(monthChartList, "monthChartList");
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder topViewHolder = mCallBack != null ? mCallBack.getTopViewHolder(this) : null;
        if (getMPresenter() == 0 || ((AmbientVolumeDetailPresenter) getMPresenter()).getMDateType() != 3 || this.mRootView == null || !(topViewHolder instanceof AmbientVolumeDetailTopViewHolder)) {
            return;
        }
        showBottomView();
        ((AmbientVolumeDetailTopViewHolder) topViewHolder).showNormalUI();
        TextView textView = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_title_hour_per);
        if (textView != null) {
            textView.setText(getLanguageText(R.string.detail_average_daily));
        }
        FloatLineChartBar floatLineChartBar = (FloatLineChartBar) _$_findCachedViewById(com.ido.life.R.id.float_line_chart);
        if (floatLineChartBar != null) {
            floatLineChartBar.setChartStyle(this.mChartStyle);
        }
        FloatLineChartBar floatLineChartBar2 = (FloatLineChartBar) _$_findCachedViewById(com.ido.life.R.id.float_line_chart);
        if (floatLineChartBar2 != null) {
            floatLineChartBar2.setBarWidthSpaceRadius(1.5f);
        }
        FloatLineChartBar floatLineChartBar3 = (FloatLineChartBar) _$_findCachedViewById(com.ido.life.R.id.float_line_chart);
        if (floatLineChartBar3 != null) {
            floatLineChartBar3.setList(monthChartList);
        }
        FloatLineChartBar floatLineChartBar4 = (FloatLineChartBar) _$_findCachedViewById(com.ido.life.R.id.float_line_chart);
        if (floatLineChartBar4 != null) {
            floatLineChartBar4.refreshChart(showChartAnimator);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: onLoadSuccessByYear, reason: avoid collision after fix types in other method */
    public void onLoadSuccessByYear2(boolean showChartAnimator, List<FloatLineChartBar.ChartBean> yearChartList) {
        Intrinsics.checkParameterIsNotNull(yearChartList, "yearChartList");
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder topViewHolder = mCallBack != null ? mCallBack.getTopViewHolder(this) : null;
        if (getMPresenter() == 0 || ((AmbientVolumeDetailPresenter) getMPresenter()).getMDateType() != 4 || this.mRootView == null || !(topViewHolder instanceof AmbientVolumeDetailTopViewHolder)) {
            return;
        }
        showBottomView();
        ((AmbientVolumeDetailTopViewHolder) topViewHolder).showNormalUI();
        TextView textView = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_title_hour_per);
        if (textView != null) {
            textView.setText(getLanguageText(R.string.detail_average_month));
        }
        FloatLineChartBar floatLineChartBar = (FloatLineChartBar) _$_findCachedViewById(com.ido.life.R.id.float_line_chart);
        if (floatLineChartBar != null) {
            floatLineChartBar.setChartStyle(this.mChartStyle);
        }
        FloatLineChartBar floatLineChartBar2 = (FloatLineChartBar) _$_findCachedViewById(com.ido.life.R.id.float_line_chart);
        if (floatLineChartBar2 != null) {
            floatLineChartBar2.setBarWidthSpaceRadius(0.4f);
        }
        FloatLineChartBar floatLineChartBar3 = (FloatLineChartBar) _$_findCachedViewById(com.ido.life.R.id.float_line_chart);
        if (floatLineChartBar3 != null) {
            floatLineChartBar3.setList(yearChartList);
        }
        FloatLineChartBar floatLineChartBar4 = (FloatLineChartBar) _$_findCachedViewById(com.ido.life.R.id.float_line_chart);
        if (floatLineChartBar4 != null) {
            floatLineChartBar4.refreshChart(showChartAnimator);
        }
    }

    private final void showBottomView() {
        View itemView;
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder bottomViewHolder = mCallBack != null ? mCallBack.getBottomViewHolder(this) : null;
        if (!(bottomViewHolder instanceof AmbientVolumeDetailBottomViewHolder) || (itemView = ((AmbientVolumeDetailBottomViewHolder) bottomViewHolder).getItemView()) == null) {
            return;
        }
        itemView.setVisibility(0);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void onDetailLoadFailed() {
        List<T> list;
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder topViewHolder = mCallBack != null ? mCallBack.getTopViewHolder(this) : null;
        IChartDetailCallback mCallBack2 = getMCallBack();
        BaseDetailViewHolder bottomViewHolder = mCallBack2 != null ? mCallBack2.getBottomViewHolder(this) : null;
        if ((topViewHolder instanceof AmbientVolumeDetailTopViewHolder) && (bottomViewHolder instanceof AmbientVolumeDetailBottomViewHolder) && this.mRootView != null) {
            FloatLineChartBar floatLineChartBar = (FloatLineChartBar) _$_findCachedViewById(com.ido.life.R.id.float_line_chart);
            if (floatLineChartBar != null && (list = floatLineChartBar.mList) != 0) {
                list.clear();
            }
            FloatLineChartBar floatLineChartBar2 = (FloatLineChartBar) _$_findCachedViewById(com.ido.life.R.id.float_line_chart);
            if (floatLineChartBar2 != null) {
                floatLineChartBar2.refreshChart(false);
            }
            View itemView = ((AmbientVolumeDetailBottomViewHolder) bottomViewHolder).getItemView();
            Intrinsics.checkExpressionValueIsNotNull(itemView, "bottomViewHolder.itemView");
            itemView.setVisibility(8);
            ((AmbientVolumeDetailTopViewHolder) topViewHolder).showNoDataUI();
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public BaseDetailViewHolder getTopView(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.detail_top_ambient_volume_layout, (ViewGroup) null);
        Intrinsics.checkExpressionValueIsNotNull(viewInflate, "LayoutInflater.from(cont…ient_volume_layout, null)");
        return new AmbientVolumeDetailTopViewHolder(viewInflate);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCoreFragment, com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public BaseDetailViewHolder getBottomView(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.detail_bottom_ambient_volume_layout, (ViewGroup) null);
        Intrinsics.checkExpressionValueIsNotNull(viewInflate, "LayoutInflater.from(cont…ient_volume_layout, null)");
        return new AmbientVolumeDetailBottomViewHolder(viewInflate);
    }

    @Override // com.ido.life.module.home.ambientvolume.IAmbientVolumeDetailView
    public void setExposureState(int exposureState) {
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder topViewHolder = mCallBack != null ? mCallBack.getTopViewHolder(this) : null;
        if (topViewHolder instanceof AmbientVolumeDetailTopViewHolder) {
            if (exposureState == 0) {
                AmbientVolumeDetailTopViewHolder ambientVolumeDetailTopViewHolder = (AmbientVolumeDetailTopViewHolder) topViewHolder;
                ambientVolumeDetailTopViewHolder.getMImgVolumeState().setVisibility(0);
                ambientVolumeDetailTopViewHolder.getMImgVolumeState().setImageResource(R.mipmap.volume_state_normal);
                ambientVolumeDetailTopViewHolder.getMTvVolumeState().setText(getLanguageText(R.string.home_pressure_normal));
                return;
            }
            if (exposureState == 1) {
                AmbientVolumeDetailTopViewHolder ambientVolumeDetailTopViewHolder2 = (AmbientVolumeDetailTopViewHolder) topViewHolder;
                ambientVolumeDetailTopViewHolder2.getMImgVolumeState().setVisibility(0);
                ambientVolumeDetailTopViewHolder2.getMImgVolumeState().setImageResource(R.mipmap.volume_state_high);
                ambientVolumeDetailTopViewHolder2.getMTvVolumeState().setText(getLanguageText(R.string.volume_high));
                return;
            }
            AmbientVolumeDetailTopViewHolder ambientVolumeDetailTopViewHolder3 = (AmbientVolumeDetailTopViewHolder) topViewHolder;
            ambientVolumeDetailTopViewHolder3.getMImgVolumeState().setVisibility(8);
            ambientVolumeDetailTopViewHolder3.getMTvVolumeState().setText(getLanguageText(R.string.no_data));
        }
    }

    @Override // com.ido.life.module.home.ambientvolume.IAmbientVolumeDetailView
    public void setDate(String dateDesc) {
        Intrinsics.checkParameterIsNotNull(dateDesc, "dateDesc");
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder topViewHolder = mCallBack != null ? mCallBack.getTopViewHolder(this) : null;
        if (topViewHolder instanceof AmbientVolumeDetailTopViewHolder) {
            ((AmbientVolumeDetailTopViewHolder) topViewHolder).getMTvDate().setText(dateDesc);
        }
    }

    @Override // com.ido.life.module.home.ambientvolume.IAmbientVolumeDetailView
    public void setExposureAvg(int avgVolume) {
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder topViewHolder = mCallBack != null ? mCallBack.getTopViewHolder(this) : null;
        if (topViewHolder instanceof AmbientVolumeDetailTopViewHolder) {
            ((AmbientVolumeDetailTopViewHolder) topViewHolder).getMTvVolumeValue().setText(String.valueOf(avgVolume));
        }
    }

    @Override // com.ido.life.module.home.ambientvolume.IAmbientVolumeDetailView
    public void setExposureDuration(int hour, int min) {
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder topViewHolder = mCallBack != null ? mCallBack.getTopViewHolder(this) : null;
        if (topViewHolder instanceof AmbientVolumeDetailTopViewHolder) {
            AmbientVolumeDetailTopViewHolder ambientVolumeDetailTopViewHolder = (AmbientVolumeDetailTopViewHolder) topViewHolder;
            ambientVolumeDetailTopViewHolder.getMTvVolumeUnit().setText(ambientVolumeDetailTopViewHolder.getVolumeUnitSpannable(hour, min));
        }
    }

    @Override // com.ido.life.module.home.ambientvolume.IAmbientVolumeDetailView
    public void setPerHourVolumeMaxmin(int min, int max) {
        if (max > min) {
            MediumTextView mediumTextView = (MediumTextView) _$_findCachedViewById(com.ido.life.R.id.tv_hour_avg_value);
            if (mediumTextView != null) {
                StringBuilder sb = new StringBuilder();
                sb.append(min);
                sb.append('-');
                sb.append(max);
                mediumTextView.setText(sb.toString());
                return;
            }
            return;
        }
        MediumTextView mediumTextView2 = (MediumTextView) _$_findCachedViewById(com.ido.life.R.id.tv_hour_avg_value);
        if (mediumTextView2 != null) {
            mediumTextView2.setText(String.valueOf(min));
        }
    }

    @Override // com.ido.life.module.home.ambientvolume.IAmbientVolumeDetailView
    public void setPassedSevenDaysChartVisibility(boolean visibility) {
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder bottomViewHolder = mCallBack != null ? mCallBack.getBottomViewHolder(this) : null;
        if (bottomViewHolder instanceof AmbientVolumeDetailBottomViewHolder) {
            ((AmbientVolumeDetailBottomViewHolder) bottomViewHolder).getMLayRecent().setVisibility(visibility ? 0 : 8);
        }
    }

    private final void changeLineVisibility() {
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder bottomViewHolder = mCallBack != null ? mCallBack.getBottomViewHolder(this) : null;
        if (bottomViewHolder instanceof AmbientVolumeDetailBottomViewHolder) {
            AmbientVolumeDetailBottomViewHolder ambientVolumeDetailBottomViewHolder = (AmbientVolumeDetailBottomViewHolder) bottomViewHolder;
            int i = 0;
            ambientVolumeDetailBottomViewHolder.getMLineWeekCompare().setVisibility((ambientVolumeDetailBottomViewHolder.getMLayDayVolumeLevelCompare().getVisibility() == 0 && ambientVolumeDetailBottomViewHolder.getMLayWeekCompare().getVisibility() == 0) ? 0 : 8);
            LinearLayout mLayCompare = ambientVolumeDetailBottomViewHolder.getMLayCompare();
            if (ambientVolumeDetailBottomViewHolder.getMLayDayVolumeLevelCompare().getVisibility() != 0 && ambientVolumeDetailBottomViewHolder.getMLayWeekCompare().getVisibility() != 0) {
                i = 8;
            }
            mLayCompare.setVisibility(i);
        }
    }

    @Override // com.ido.life.module.home.ambientvolume.IAmbientVolumeDetailView
    public void setAboutAmbientVolumeUIVisibility(boolean visibility) {
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder bottomViewHolder = mCallBack != null ? mCallBack.getBottomViewHolder(this) : null;
        if (bottomViewHolder instanceof AmbientVolumeDetailBottomViewHolder) {
            ((AmbientVolumeDetailBottomViewHolder) bottomViewHolder).getMLayAbortAmbientVolume().setVisibility(visibility ? 0 : 8);
        }
    }

    @Override // com.ido.life.module.home.ambientvolume.IAmbientVolumeDetailView
    public void setPassedSevenDaysChartList(int avgVolume, List<AmbientVolumePassedChartView.ChartBean> passedChartList) {
        Intrinsics.checkParameterIsNotNull(passedChartList, "passedChartList");
        IChartDetailCallback mCallBack = getMCallBack();
        final BaseDetailViewHolder bottomViewHolder = mCallBack != null ? mCallBack.getBottomViewHolder(this) : null;
        if (bottomViewHolder instanceof AmbientVolumeDetailBottomViewHolder) {
            AmbientVolumeDetailBottomViewHolder ambientVolumeDetailBottomViewHolder = (AmbientVolumeDetailBottomViewHolder) bottomViewHolder;
            if (ambientVolumeDetailBottomViewHolder.getMLayRecent().getVisibility() == 0) {
                ambientVolumeDetailBottomViewHolder.getMRecentVolumeChartView().getChartList().clear();
                List<AmbientVolumePassedChartView.ChartBean> chartList = ambientVolumeDetailBottomViewHolder.getMRecentVolumeChartView().getChartList();
                List<AmbientVolumePassedChartView.ChartBean> list = passedChartList;
                if (!list.isEmpty()) {
                    chartList.addAll(list);
                    ambientVolumeDetailBottomViewHolder.getMRecentVolumeChartView().setChartList(chartList);
                    ambientVolumeDetailBottomViewHolder.getMRecentVolumeChartView().setDrawCallback(new AmbientVolumePassedChartView.DrawCallback() { // from class: com.ido.life.module.home.ambientvolume.AmbientVolumeDetailFragment.setPassedSevenDaysChartList.1
                        @Override // com.ido.life.customview.AmbientVolumePassedChartView.DrawCallback
                        public void drawComplete(float centerY) {
                            float height = ((AmbientVolumeDetailBottomViewHolder) bottomViewHolder).getMRecentVolumeChartView().getHeight() / 2.0f;
                            ViewGroup.LayoutParams layoutParams = ((AmbientVolumeDetailBottomViewHolder) bottomViewHolder).getMRecentGuideLine().getLayoutParams();
                            if (layoutParams == null) {
                                throw new TypeCastException("null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout.LayoutParams");
                            }
                            ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) layoutParams;
                            if (height >= centerY) {
                                layoutParams2.bottomMargin = MathKt.roundToInt((height - centerY) - ((AmbientVolumeDetailBottomViewHolder) bottomViewHolder).getMRecentGuideLine().getHeight());
                            } else {
                                layoutParams2.bottomMargin = MathKt.roundToInt((height - centerY) + ((AmbientVolumeDetailBottomViewHolder) bottomViewHolder).getMRecentGuideLine().getHeight());
                            }
                            ((AmbientVolumeDetailBottomViewHolder) bottomViewHolder).getMRecentGuideLine().setLayoutParams(layoutParams2);
                            ((AmbientVolumeDetailBottomViewHolder) bottomViewHolder).getMRecentGuideLine().setVisibility(0);
                        }
                    });
                    ambientVolumeDetailBottomViewHolder.getMRecentVolumeChartView().refresh(true);
                    return;
                }
                ViewGroup.LayoutParams layoutParams = ambientVolumeDetailBottomViewHolder.getMRecentGuideLine().getLayoutParams();
                if (layoutParams == null) {
                    throw new TypeCastException("null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout.LayoutParams");
                }
                ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) layoutParams;
                layoutParams2.bottomMargin = 0;
                ambientVolumeDetailBottomViewHolder.getMRecentGuideLine().setLayoutParams(layoutParams2);
                ambientVolumeDetailBottomViewHolder.getMRecentGuideLine().setVisibility(4);
            }
        }
    }

    @Override // com.ido.life.module.home.ambientvolume.IAmbientVolumeDetailView
    public void setPassedSevenDaysXLabelList(List<String> xLabelList) {
        Intrinsics.checkParameterIsNotNull(xLabelList, "xLabelList");
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder bottomViewHolder = mCallBack != null ? mCallBack.getBottomViewHolder(this) : null;
        if (bottomViewHolder instanceof AmbientVolumeDetailBottomViewHolder) {
            AmbientVolumeDetailBottomViewHolder ambientVolumeDetailBottomViewHolder = (AmbientVolumeDetailBottomViewHolder) bottomViewHolder;
            ambientVolumeDetailBottomViewHolder.getMRecentVolumeChartView().getBottomLabelList().clear();
            if (xLabelList.isEmpty()) {
                return;
            }
            ambientVolumeDetailBottomViewHolder.getMRecentVolumeChartView().getBottomLabelList().addAll(xLabelList);
        }
    }

    @Override // com.ido.life.module.home.ambientvolume.IAmbientVolumeDetailView
    public void setPassedSevenDaysVolumeState(int volumeState) {
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder bottomViewHolder = mCallBack != null ? mCallBack.getBottomViewHolder(this) : null;
        if (bottomViewHolder instanceof AmbientVolumeDetailBottomViewHolder) {
            if (volumeState == 1) {
                AmbientVolumeDetailBottomViewHolder ambientVolumeDetailBottomViewHolder = (AmbientVolumeDetailBottomViewHolder) bottomViewHolder;
                ambientVolumeDetailBottomViewHolder.getMImgRecentVolumeState().setImageResource(R.mipmap.volume_state_high);
                ambientVolumeDetailBottomViewHolder.getMTvRecentVolumeState().setText(getLanguageText(R.string.volume_high));
            } else {
                AmbientVolumeDetailBottomViewHolder ambientVolumeDetailBottomViewHolder2 = (AmbientVolumeDetailBottomViewHolder) bottomViewHolder;
                ambientVolumeDetailBottomViewHolder2.getMImgRecentVolumeState().setImageResource(R.mipmap.volume_state_normal);
                ambientVolumeDetailBottomViewHolder2.getMTvRecentVolumeState().setText(getLanguageText(R.string.home_pressure_normal));
            }
        }
    }

    @Override // com.ido.life.module.home.ambientvolume.IAmbientVolumeDetailView
    public void setPassedSevenDaysVolumeValue(int volumeValue, int hour, int min) {
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder bottomViewHolder = mCallBack != null ? mCallBack.getBottomViewHolder(this) : null;
        if (bottomViewHolder instanceof AmbientVolumeDetailBottomViewHolder) {
            AmbientVolumeDetailBottomViewHolder ambientVolumeDetailBottomViewHolder = (AmbientVolumeDetailBottomViewHolder) bottomViewHolder;
            TextView mTvRecentVolumeAvg = ambientVolumeDetailBottomViewHolder.getMTvRecentVolumeAvg();
            StringBuilder sb = new StringBuilder();
            sb.append(getLanguageText(R.string.home_detail_ave_ios));
            sb.append(volumeValue);
            String languageText = getLanguageText(R.string.public_volume_unit);
            Locale locale = Locale.CHINA;
            Intrinsics.checkExpressionValueIsNotNull(locale, "Locale.CHINA");
            if (languageText != null) {
                String lowerCase = languageText.toLowerCase(locale);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "(this as java.lang.String).toLowerCase(locale)");
                sb.append(lowerCase);
                mTvRecentVolumeAvg.setText(sb.toString());
                ambientVolumeDetailBottomViewHolder.getMTvRecentVolumeDuration().setText(hour + getLanguageText(R.string.public_time_hour) + min + getLanguageText(R.string.public_time_minute));
                return;
            }
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
    }

    @Override // com.ido.life.module.home.ambientvolume.IAmbientVolumeDetailView
    public void setVolumeLevelExposureVisibility(boolean visibility) {
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder bottomViewHolder = mCallBack != null ? mCallBack.getBottomViewHolder(this) : null;
        if (bottomViewHolder instanceof AmbientVolumeDetailBottomViewHolder) {
            ((AmbientVolumeDetailBottomViewHolder) bottomViewHolder).getMLayExposure().setVisibility(visibility ? 0 : 8);
        }
    }

    @Override // com.ido.life.module.home.ambientvolume.IAmbientVolumeDetailView
    public void setVolumeHighLevelExposureInfo(String desc, int duration, int maxDuration) {
        int i = maxDuration * 60 * 60;
        int iMax = duration > 0 ? Math.max((int) (i * 0.05f), duration) : duration;
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder bottomViewHolder = mCallBack != null ? mCallBack.getBottomViewHolder(this) : null;
        if (bottomViewHolder instanceof AmbientVolumeDetailBottomViewHolder) {
            AmbientVolumeDetailBottomViewHolder ambientVolumeDetailBottomViewHolder = (AmbientVolumeDetailBottomViewHolder) bottomViewHolder;
            ambientVolumeDetailBottomViewHolder.getMTvVolumeExposureLevelHigh().setText(desc);
            AmbientVolumeProgressBar mProgressHighExposure = ambientVolumeDetailBottomViewHolder.getMProgressHighExposure();
            if (i <= duration) {
                i = duration;
            }
            mProgressHighExposure.setMaxProgress(i);
            ambientVolumeDetailBottomViewHolder.getMProgressHighExposure().setProgress(iMax);
            int i2 = duration / 3600;
            int i3 = (duration / 60) % 60;
            if (i2 > 0 && i3 > 0) {
                ambientVolumeDetailBottomViewHolder.getMProgressHighExposure().setLeftLabel(i2 + getLanguageText(R.string.public_time_hour) + i3 + getLanguageText(R.string.public_time_minute));
            } else if (i2 > 0) {
                ambientVolumeDetailBottomViewHolder.getMProgressHighExposure().setLeftLabel(i2 + getLanguageText(R.string.public_time_hour));
            } else if (duration <= 0) {
                ambientVolumeDetailBottomViewHolder.getMProgressHighExposure().setLeftLabel('0' + getLanguageText(R.string.public_time_minute));
            } else if (i3 > 1) {
                ambientVolumeDetailBottomViewHolder.getMProgressHighExposure().setLeftLabel(i3 + getLanguageText(R.string.public_time_minute));
            } else {
                ambientVolumeDetailBottomViewHolder.getMProgressHighExposure().setLeftLabel("<1" + getLanguageText(R.string.public_time_minute));
            }
            ambientVolumeDetailBottomViewHolder.getMProgressHighExposure().refresh(true);
        }
    }

    @Override // com.ido.life.module.home.ambientvolume.IAmbientVolumeDetailView
    public void setVolumeMediumLevelExposureInfo(String desc, int duration, int maxDuration) {
        int i = maxDuration * 60 * 60;
        int iMax = duration > 0 ? Math.max((int) (i * 0.05f), duration) : duration;
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder bottomViewHolder = mCallBack != null ? mCallBack.getBottomViewHolder(this) : null;
        if (bottomViewHolder instanceof AmbientVolumeDetailBottomViewHolder) {
            AmbientVolumeDetailBottomViewHolder ambientVolumeDetailBottomViewHolder = (AmbientVolumeDetailBottomViewHolder) bottomViewHolder;
            ambientVolumeDetailBottomViewHolder.getMTvVolumeExposureLevelMedium().setText(desc);
            AmbientVolumeProgressBar mProgressMediumExposure = ambientVolumeDetailBottomViewHolder.getMProgressMediumExposure();
            if (i <= duration) {
                i = duration;
            }
            mProgressMediumExposure.setMaxProgress(i);
            ambientVolumeDetailBottomViewHolder.getMProgressMediumExposure().setProgress(iMax);
            int i2 = duration / 3600;
            int i3 = (duration / 60) % 60;
            if (i2 > 0 && i3 > 0) {
                ambientVolumeDetailBottomViewHolder.getMProgressMediumExposure().setLeftLabel(i2 + getLanguageText(R.string.public_time_hour) + i3 + getLanguageText(R.string.public_time_minute));
            } else if (i2 > 0) {
                ambientVolumeDetailBottomViewHolder.getMProgressMediumExposure().setLeftLabel(i2 + getLanguageText(R.string.public_time_hour));
            } else if (duration <= 0) {
                ambientVolumeDetailBottomViewHolder.getMProgressMediumExposure().setLeftLabel('0' + getLanguageText(R.string.public_time_minute));
            } else if (i3 > 1) {
                ambientVolumeDetailBottomViewHolder.getMProgressMediumExposure().setLeftLabel(i3 + getLanguageText(R.string.public_time_minute));
            } else {
                ambientVolumeDetailBottomViewHolder.getMProgressMediumExposure().setLeftLabel("<1" + getLanguageText(R.string.public_time_minute));
            }
            ambientVolumeDetailBottomViewHolder.getMProgressMediumExposure().refresh(true);
        }
    }

    @Override // com.ido.life.module.home.ambientvolume.IAmbientVolumeDetailView
    public void setVolumeNormalLevelExposureInfo(String desc, int duration, int maxDuration) {
        int i = maxDuration * 60 * 60;
        int iMax = duration > 0 ? Math.max((int) (i * 0.05f), duration) : duration;
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder bottomViewHolder = mCallBack != null ? mCallBack.getBottomViewHolder(this) : null;
        if (bottomViewHolder instanceof AmbientVolumeDetailBottomViewHolder) {
            AmbientVolumeDetailBottomViewHolder ambientVolumeDetailBottomViewHolder = (AmbientVolumeDetailBottomViewHolder) bottomViewHolder;
            ambientVolumeDetailBottomViewHolder.getMTvVolumeExposureLevelNormal().setText(desc);
            AmbientVolumeProgressBar mProgressNormalExposure = ambientVolumeDetailBottomViewHolder.getMProgressNormalExposure();
            if (i <= duration) {
                i = duration;
            }
            mProgressNormalExposure.setMaxProgress(i);
            ambientVolumeDetailBottomViewHolder.getMProgressNormalExposure().setProgress(iMax);
            int i2 = duration / 3600;
            int i3 = (duration / 60) % 60;
            if (i2 > 0 && i3 > 0) {
                ambientVolumeDetailBottomViewHolder.getMProgressNormalExposure().setLeftLabel(i2 + getLanguageText(R.string.public_time_hour) + i3 + getLanguageText(R.string.public_time_minute));
            } else if (i2 > 0) {
                ambientVolumeDetailBottomViewHolder.getMProgressNormalExposure().setLeftLabel(i2 + getLanguageText(R.string.public_time_hour));
            } else if (duration <= 0) {
                ambientVolumeDetailBottomViewHolder.getMProgressNormalExposure().setLeftLabel('0' + getLanguageText(R.string.public_time_minute));
            } else if (i3 > 1) {
                ambientVolumeDetailBottomViewHolder.getMProgressNormalExposure().setLeftLabel(i3 + getLanguageText(R.string.public_time_minute));
            } else {
                ambientVolumeDetailBottomViewHolder.getMProgressNormalExposure().setLeftLabel("<1" + getLanguageText(R.string.public_time_minute));
            }
            ambientVolumeDetailBottomViewHolder.getMProgressNormalExposure().refresh(true);
        }
    }

    @Override // com.ido.life.module.home.ambientvolume.IAmbientVolumeDetailView
    public void setDayVolumeCompareUIVisibility(boolean visibility) {
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder bottomViewHolder = mCallBack != null ? mCallBack.getBottomViewHolder(this) : null;
        if (bottomViewHolder instanceof AmbientVolumeDetailBottomViewHolder) {
            ((AmbientVolumeDetailBottomViewHolder) bottomViewHolder).getMLayDayVolumeLevelCompare().setVisibility(visibility ? 0 : 8);
            changeLineVisibility();
        }
    }

    @Override // com.ido.life.module.home.ambientvolume.IAmbientVolumeDetailView
    public void setDayVolumeCompare(int todayVolume, int yesterdayVolume) {
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder bottomViewHolder = mCallBack != null ? mCallBack.getBottomViewHolder(this) : null;
        if (bottomViewHolder instanceof AmbientVolumeDetailBottomViewHolder) {
            int iMax = Math.max(todayVolume, yesterdayVolume);
            AmbientVolumeDetailBottomViewHolder ambientVolumeDetailBottomViewHolder = (AmbientVolumeDetailBottomViewHolder) bottomViewHolder;
            ambientVolumeDetailBottomViewHolder.getMDayLevelCompareTodayProgress().setMaxProgress(iMax);
            ambientVolumeDetailBottomViewHolder.getMDayLevelCompareYesterdayProgress().setMaxProgress(iMax);
            if (todayVolume > 0) {
                ambientVolumeDetailBottomViewHolder.getMTvDayLevelCompareTodayVolume().setText(String.valueOf(todayVolume));
                ambientVolumeDetailBottomViewHolder.getMDayLevelCompareTodayProgress().setProgress(todayVolume);
            } else {
                ambientVolumeDetailBottomViewHolder.getMTvDayLevelCompareTodayVolume().setText("-");
                ambientVolumeDetailBottomViewHolder.getMDayLevelCompareTodayProgress().setProgress(0);
            }
            if (yesterdayVolume > 0) {
                ambientVolumeDetailBottomViewHolder.getMTvDayLevelCompareYesterdayVolume().setText(String.valueOf(yesterdayVolume));
                ambientVolumeDetailBottomViewHolder.getMDayLevelCompareYesterdayProgress().setProgress(yesterdayVolume);
            } else {
                ambientVolumeDetailBottomViewHolder.getMTvDayLevelCompareYesterdayVolume().setText("-");
                ambientVolumeDetailBottomViewHolder.getMDayLevelCompareYesterdayProgress().setProgress(0);
            }
            if (todayVolume > yesterdayVolume) {
                ambientVolumeDetailBottomViewHolder.getMTvDayVolumeLevelCompareDesc().setText(getLanguageText(R.string.volume_day_compare_high));
                ambientVolumeDetailBottomViewHolder.getMDayLevelCompareTodayProgress().setProgressColor(Color.parseColor("#16CE90"));
                ambientVolumeDetailBottomViewHolder.getMDayLevelCompareYesterdayProgress().setProgressColor(Color.parseColor("#51EAEBED"));
            } else if (todayVolume < yesterdayVolume) {
                ambientVolumeDetailBottomViewHolder.getMTvDayVolumeLevelCompareDesc().setText(getLanguageText(R.string.volume_day_compare_low));
                ambientVolumeDetailBottomViewHolder.getMDayLevelCompareYesterdayProgress().setProgressColor(Color.parseColor("#16CE90"));
                ambientVolumeDetailBottomViewHolder.getMDayLevelCompareTodayProgress().setProgressColor(Color.parseColor("#51EAEBED"));
            } else {
                ambientVolumeDetailBottomViewHolder.getMTvDayVolumeLevelCompareDesc().setText(getLanguageText(R.string.volume_day_compare_equal));
                ambientVolumeDetailBottomViewHolder.getMDayLevelCompareTodayProgress().setProgressColor(Color.parseColor("#51EAEBED"));
                ambientVolumeDetailBottomViewHolder.getMDayLevelCompareYesterdayProgress().setProgressColor(Color.parseColor("#51EAEBED"));
            }
            ambientVolumeDetailBottomViewHolder.getMDayLevelCompareTodayProgress().refresh(true);
            ambientVolumeDetailBottomViewHolder.getMDayLevelCompareYesterdayProgress().refresh(true);
        }
    }

    @Override // com.ido.life.module.home.ambientvolume.IAmbientVolumeDetailView
    public void setWeekVolumeCompareUIVisibility(boolean visibility) {
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder bottomViewHolder = mCallBack != null ? mCallBack.getBottomViewHolder(this) : null;
        if (bottomViewHolder instanceof AmbientVolumeDetailBottomViewHolder) {
            AmbientVolumeDetailBottomViewHolder ambientVolumeDetailBottomViewHolder = (AmbientVolumeDetailBottomViewHolder) bottomViewHolder;
            ambientVolumeDetailBottomViewHolder.getMLayWeekCompare().setVisibility(visibility ? 0 : 8);
            if (ambientVolumeDetailBottomViewHolder.getMLayDayVolumeLevelCompare().getVisibility() == 0 && ambientVolumeDetailBottomViewHolder.getMLayWeekCompare().getVisibility() == 0) {
                ambientVolumeDetailBottomViewHolder.getMTvTitleWeekLevelCompare().setVisibility(8);
            } else {
                ambientVolumeDetailBottomViewHolder.getMTvTitleWeekLevelCompare().setVisibility(0);
            }
            changeLineVisibility();
        }
    }

    @Override // com.ido.life.module.home.ambientvolume.IAmbientVolumeDetailView
    public void setWeekVolumeCompare(int currentWeekVolume, int previousWeekVolume) {
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder bottomViewHolder = mCallBack != null ? mCallBack.getBottomViewHolder(this) : null;
        if (bottomViewHolder instanceof AmbientVolumeDetailBottomViewHolder) {
            int iMax = Math.max(currentWeekVolume, previousWeekVolume);
            if (currentWeekVolume > previousWeekVolume) {
                AmbientVolumeDetailBottomViewHolder ambientVolumeDetailBottomViewHolder = (AmbientVolumeDetailBottomViewHolder) bottomViewHolder;
                ambientVolumeDetailBottomViewHolder.getMTvWeekLevelCompareDesc().setText(getLanguageText(R.string.volume_week_compare_high));
                ambientVolumeDetailBottomViewHolder.getMWeekLevelCompareCurrentProgress().setProgressColor(Color.parseColor("#16CE90"));
                ambientVolumeDetailBottomViewHolder.getMWeekLevelComparePreviousProgress().setProgressColor(Color.parseColor("#51EAEBED"));
            } else if (currentWeekVolume < previousWeekVolume) {
                AmbientVolumeDetailBottomViewHolder ambientVolumeDetailBottomViewHolder2 = (AmbientVolumeDetailBottomViewHolder) bottomViewHolder;
                ambientVolumeDetailBottomViewHolder2.getMTvWeekLevelCompareDesc().setText(getLanguageText(R.string.volume_week_compare_low));
                ambientVolumeDetailBottomViewHolder2.getMWeekLevelCompareCurrentProgress().setProgressColor(Color.parseColor("#51EAEBED"));
                ambientVolumeDetailBottomViewHolder2.getMWeekLevelComparePreviousProgress().setProgressColor(Color.parseColor("#16CE90"));
            } else {
                AmbientVolumeDetailBottomViewHolder ambientVolumeDetailBottomViewHolder3 = (AmbientVolumeDetailBottomViewHolder) bottomViewHolder;
                ambientVolumeDetailBottomViewHolder3.getMTvWeekLevelCompareDesc().setText(getLanguageText(R.string.volume_week_compare_equal));
                ambientVolumeDetailBottomViewHolder3.getMWeekLevelCompareCurrentProgress().setProgressColor(Color.parseColor("#51EAEBED"));
                ambientVolumeDetailBottomViewHolder3.getMWeekLevelComparePreviousProgress().setProgressColor(Color.parseColor("#51EAEBED"));
            }
            AmbientVolumeDetailBottomViewHolder ambientVolumeDetailBottomViewHolder4 = (AmbientVolumeDetailBottomViewHolder) bottomViewHolder;
            ambientVolumeDetailBottomViewHolder4.getMWeekLevelCompareCurrentProgress().setMaxProgress(iMax);
            ambientVolumeDetailBottomViewHolder4.getMWeekLevelComparePreviousProgress().setMaxProgress(iMax);
            if (currentWeekVolume > 0) {
                ambientVolumeDetailBottomViewHolder4.getMTvWeekLevelCompareCurrentVolume().setText(String.valueOf(currentWeekVolume));
                ambientVolumeDetailBottomViewHolder4.getMWeekLevelCompareCurrentProgress().setProgress(currentWeekVolume);
            } else {
                ambientVolumeDetailBottomViewHolder4.getMTvWeekLevelCompareCurrentVolume().setText("-");
                ambientVolumeDetailBottomViewHolder4.getMWeekLevelCompareCurrentProgress().setProgress(0);
            }
            if (previousWeekVolume > 0) {
                ambientVolumeDetailBottomViewHolder4.getMTvWeekLevelComparePreviousVolume().setText(String.valueOf(previousWeekVolume));
                ambientVolumeDetailBottomViewHolder4.getMWeekLevelComparePreviousProgress().setProgress(previousWeekVolume);
            } else {
                ambientVolumeDetailBottomViewHolder4.getMTvWeekLevelComparePreviousVolume().setText("-");
                ambientVolumeDetailBottomViewHolder4.getMWeekLevelComparePreviousProgress().setProgress(0);
            }
            ambientVolumeDetailBottomViewHolder4.getMWeekLevelCompareCurrentProgress().refresh(true);
            ambientVolumeDetailBottomViewHolder4.getMWeekLevelComparePreviousProgress().refresh(true);
        }
    }

    @Override // com.ido.life.module.home.ambientvolume.IAmbientVolumeDetailView
    public void setChartXLabelList(List<String> xLabelList) {
        Intrinsics.checkParameterIsNotNull(xLabelList, "xLabelList");
        FloatLineChartBar floatLineChartBar = (FloatLineChartBar) _$_findCachedViewById(com.ido.life.R.id.float_line_chart);
        if (floatLineChartBar != null) {
            floatLineChartBar.setLabelXList(xLabelList);
        }
    }

    @Override // com.ido.life.module.home.ambientvolume.IAmbientVolumeDetailView
    public void setChartYLabelList(List<String> yLabelList) {
        Intrinsics.checkParameterIsNotNull(yLabelList, "yLabelList");
        if (yLabelList.size() > 1) {
            FloatLineChartBar floatLineChartBar = (FloatLineChartBar) _$_findCachedViewById(com.ido.life.R.id.float_line_chart);
            if (floatLineChartBar != null) {
                floatLineChartBar.mYMaxValue = Integer.parseInt(yLabelList.get(yLabelList.size() - 1));
            }
            FloatLineChartBar floatLineChartBar2 = (FloatLineChartBar) _$_findCachedViewById(com.ido.life.R.id.float_line_chart);
            if (floatLineChartBar2 != null) {
                floatLineChartBar2.mYMinValue = Integer.parseInt(yLabelList.get(0));
            }
            FloatLineChartBar floatLineChartBar3 = (FloatLineChartBar) _$_findCachedViewById(com.ido.life.R.id.float_line_chart);
            if (floatLineChartBar3 != null) {
                floatLineChartBar3.setLabelYLeftList(yLabelList);
            }
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

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailFragment, com.ido.common.base.BaseCoreFragment
    protected void initView() {
        FloatLineChartBar floatLineChartBar;
        super.initView();
        LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_per_hour_avg);
        if (linearLayout != null) {
            linearLayout.setOnClickListener(this);
        }
        if (!(getContext() instanceof CustomChatBar.ChartClickListener) || (floatLineChartBar = (FloatLineChartBar) _$_findCachedViewById(com.ido.life.R.id.float_line_chart)) == null) {
            return;
        }
        Object context = getContext();
        if (context == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.ido.life.customview.charter.CustomChatBar.ChartClickListener");
        }
        floatLineChartBar.setClickListener((CustomChatBar.ChartClickListener) context);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCoreFragment, com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public void refreshTypeAndOffset(boolean refreshPage) {
        if (getMCallBack() == null || getMPresenter() == 0) {
            return;
        }
        IChartDetailCallback mCallBack = getMCallBack();
        if (mCallBack == null) {
            Intrinsics.throwNpe();
        }
        int dateType = mCallBack.getDateType(this);
        T mPresenter = getMPresenter();
        if (mPresenter == 0) {
            Intrinsics.throwNpe();
        }
        if (dateType != ((AmbientVolumeDetailPresenter) mPresenter).getMDateType()) {
            clearCache();
        }
        super.refreshTypeAndOffset(refreshPage);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailFragment
    protected void refreshUiByDateType() {
        IChartDetailCallback mCallBack;
        FloatLineChartBar floatLineChartBar;
        super.refreshUiByDateType();
        if (getMCallBack() != null) {
            IChartDetailCallback mCallBack2 = getMCallBack();
            if (mCallBack2 == null) {
                Intrinsics.throwNpe();
            }
            AmbientVolumeDetailFragment ambientVolumeDetailFragment = this;
            if (mCallBack2.isShow(ambientVolumeDetailFragment)) {
                if ((getContext() instanceof CustomChatBar.ChartClickListener) && (floatLineChartBar = (FloatLineChartBar) _$_findCachedViewById(com.ido.life.R.id.float_line_chart)) != null) {
                    Object context = getContext();
                    if (context == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.ido.life.customview.charter.CustomChatBar.ChartClickListener");
                    }
                    floatLineChartBar.setClickListener((CustomChatBar.ChartClickListener) context);
                }
                IChartDetailCallback mCallBack3 = getMCallBack();
                BaseDetailViewHolder topViewHolder = mCallBack3 != null ? mCallBack3.getTopViewHolder(ambientVolumeDetailFragment) : null;
                if (topViewHolder instanceof AmbientVolumeDetailTopViewHolder) {
                    ((AmbientVolumeDetailTopViewHolder) topViewHolder).getMImgVolumeHelp().setOnClickListener(this);
                }
                int mDateType = ((AmbientVolumeDetailPresenter) getMPresenter()).getMDateType();
                if (mDateType == 1 || mDateType == 3) {
                    FloatLineChartBar floatLineChartBar2 = (FloatLineChartBar) _$_findCachedViewById(com.ido.life.R.id.float_line_chart);
                    if (floatLineChartBar2 != null) {
                        floatLineChartBar2.mBottomLabelCenter = false;
                    }
                } else {
                    FloatLineChartBar floatLineChartBar3 = (FloatLineChartBar) _$_findCachedViewById(com.ido.life.R.id.float_line_chart);
                    if (floatLineChartBar3 != null) {
                        floatLineChartBar3.mBottomLabelCenter = true;
                    }
                }
                if (getMCallBack() != null) {
                    IChartDetailCallback mCallBack4 = getMCallBack();
                    if (mCallBack4 == null) {
                        Intrinsics.throwNpe();
                    }
                    this.mChartStyle = mCallBack4.getPageData(1).getInt(KEY_CHART_STYLE, this.mChartStyle);
                }
                if (this.mChartStyle == 1) {
                    switchToBarChart();
                } else {
                    switchToLineChart();
                }
                AmbientVolumeDetailPresenter ambientVolumeDetailPresenter = (AmbientVolumeDetailPresenter) getMPresenter();
                if (ambientVolumeDetailPresenter != null) {
                    ambientVolumeDetailPresenter.getDetailData();
                }
                if (!isVisible() || (mCallBack = getMCallBack()) == null) {
                    return;
                }
                AmbientVolumeDetailPresenter ambientVolumeDetailPresenter2 = (AmbientVolumeDetailPresenter) getMPresenter();
                mCallBack.updateSelectDate(ambientVolumeDetailFragment, ambientVolumeDetailPresenter2 != null ? ambientVolumeDetailPresenter2.getMStartDate() : null);
            }
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCoreFragment
    public void clickAction(View view) {
        int i;
        Bundle pageData;
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onClick(view);
        int id = view.getId();
        if (id == R.id.img_volume_help) {
            new AmbientVolumeLevelInfoDialog().show(getFragmentManager());
            return;
        }
        if (id != R.id.lay_per_hour_avg) {
            return;
        }
        FloatLineChartBar floatLineChartBar = (FloatLineChartBar) _$_findCachedViewById(com.ido.life.R.id.float_line_chart);
        if (floatLineChartBar != null && floatLineChartBar.getMChartStyle() == 1) {
            switchToLineChart();
            i = 2;
        } else {
            switchToBarChart();
            i = 1;
        }
        this.mChartStyle = i;
        IChartDetailCallback mCallBack = getMCallBack();
        if (mCallBack != null && (pageData = mCallBack.getPageData(1)) != null) {
            pageData.putInt(KEY_CHART_STYLE, this.mChartStyle);
        }
        IChartDetailCallback mCallBack2 = getMCallBack();
        if (mCallBack2 != null) {
            mCallBack2.hideTipUI();
        }
    }

    private final void switchToBarChart() {
        FloatLineChartBar floatLineChartBar = (FloatLineChartBar) _$_findCachedViewById(com.ido.life.R.id.float_line_chart);
        if (floatLineChartBar != null) {
            floatLineChartBar.setChartStyle(1);
        }
        LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_per_hour_avg);
        if (linearLayout != null) {
            linearLayout.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.color_text_side_bar_55)));
        }
        TextView textView = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_title_hour_per);
        if (textView != null) {
            textView.setTextColor(getResources().getColor(R.color.color_A8A9B5));
        }
        MediumTextView mediumTextView = (MediumTextView) _$_findCachedViewById(com.ido.life.R.id.tv_hour_avg_value);
        if (mediumTextView != null) {
            mediumTextView.setTextColor(getResources().getColor(R.color.white));
        }
        TextView textView2 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_hour_avg_unit);
        if (textView2 != null) {
            textView2.setTextColor(getResources().getColor(R.color.white));
        }
        FloatLineChartBar floatLineChartBar2 = (FloatLineChartBar) _$_findCachedViewById(com.ido.life.R.id.float_line_chart);
        if (floatLineChartBar2 != null) {
            floatLineChartBar2.refreshChart(true);
        }
    }

    private final void switchToLineChart() {
        FloatLineChartBar floatLineChartBar = (FloatLineChartBar) _$_findCachedViewById(com.ido.life.R.id.float_line_chart);
        if (floatLineChartBar != null) {
            floatLineChartBar.setChartStyle(2);
        }
        LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_per_hour_avg);
        if (linearLayout != null) {
            linearLayout.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.color_16CE90)));
        }
        TextView textView = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_title_hour_per);
        if (textView != null) {
            textView.setTextColor(getResources().getColor(R.color.white));
        }
        MediumTextView mediumTextView = (MediumTextView) _$_findCachedViewById(com.ido.life.R.id.tv_hour_avg_value);
        if (mediumTextView != null) {
            mediumTextView.setTextColor(getResources().getColor(R.color.white));
        }
        TextView textView2 = (TextView) _$_findCachedViewById(com.ido.life.R.id.tv_hour_avg_unit);
        if (textView2 != null) {
            textView2.setTextColor(getResources().getColor(R.color.white));
        }
        FloatLineChartBar floatLineChartBar2 = (FloatLineChartBar) _$_findCachedViewById(com.ido.life.R.id.float_line_chart);
        if (floatLineChartBar2 != null) {
            floatLineChartBar2.refreshChart(true);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCoreFragment, com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public boolean refreshChartTipView(int index) {
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder tipViewHolder = mCallBack != null ? mCallBack.getTipViewHolder(this) : null;
        if ((tipViewHolder instanceof AmbientVolumeDetailTipViewHolder) && getMPresenter() != 0) {
            FloatLineChartBar floatLineChartBar = (FloatLineChartBar) _$_findCachedViewById(com.ido.life.R.id.float_line_chart);
            List list = floatLineChartBar != null ? floatLineChartBar.mList : null;
            List list2 = list;
            if (!(list2 == null || list2.isEmpty()) && list.size() > index) {
                FloatLineChartBar.ChartBean chartBean = (FloatLineChartBar.ChartBean) list.get(index);
                FloatLineChartBar floatLineChartBar2 = (FloatLineChartBar) _$_findCachedViewById(com.ido.life.R.id.float_line_chart);
                Integer numValueOf = floatLineChartBar2 != null ? Integer.valueOf(floatLineChartBar2.getMChartStyle()) : null;
                if (numValueOf != null && numValueOf.intValue() == 2) {
                    AmbientVolumeDetailTipViewHolder ambientVolumeDetailTipViewHolder = (AmbientVolumeDetailTipViewHolder) tipViewHolder;
                    ambientVolumeDetailTipViewHolder.showLineTipUI();
                    ambientVolumeDetailTipViewHolder.getMTvTitleVolumeArea().setText(LanguageUtil.getLanguageText(R.string.home_detail_ave_ios));
                    ambientVolumeDetailTipViewHolder.getMTvVolumeDurationHour().setText(String.valueOf(chartBean.getDurHour()));
                    ambientVolumeDetailTipViewHolder.getMTvVolumeDurationMin().setText(String.valueOf(chartBean.getDurMinute()));
                    ambientVolumeDetailTipViewHolder.getMTvVolumeAreaValue().setText(String.valueOf(MathKt.roundToInt(chartBean.getAvgValue())));
                } else {
                    AmbientVolumeDetailTipViewHolder ambientVolumeDetailTipViewHolder2 = (AmbientVolumeDetailTipViewHolder) tipViewHolder;
                    ambientVolumeDetailTipViewHolder2.getMTvTitleVolumeArea().setText(LanguageUtil.getLanguageText(R.string.title_area));
                    TextView mTvVolumeAreaValue = ambientVolumeDetailTipViewHolder2.getMTvVolumeAreaValue();
                    StringBuilder sb = new StringBuilder();
                    sb.append(MathKt.roundToInt(chartBean.getMinValue()));
                    sb.append('-');
                    sb.append(MathKt.roundToInt(chartBean.getMaxValue()));
                    mTvVolumeAreaValue.setText(sb.toString());
                    ambientVolumeDetailTipViewHolder2.showBarTipUI();
                }
                int mDateType = ((AmbientVolumeDetailPresenter) getMPresenter()).getMDateType();
                if (mDateType == 1) {
                    IChartDetailCallback mCallBack2 = getMCallBack();
                    if (mCallBack2 != null) {
                        AmbientVolumeDetailFragment ambientVolumeDetailFragment = this;
                        AmbientVolumeDetailPresenter ambientVolumeDetailPresenter = (AmbientVolumeDetailPresenter) getMPresenter();
                        mCallBack2.updateSelectDate(ambientVolumeDetailFragment, ambientVolumeDetailPresenter != null ? ambientVolumeDetailPresenter.getMStartDate() : null);
                    }
                    TextView mTvDate = ((AmbientVolumeDetailTipViewHolder) tipViewHolder).getMTvDate();
                    Object[] objArr = {Integer.valueOf(chartBean.getDataIndex())};
                    String str = String.format("%02d:00", Arrays.copyOf(objArr, objArr.length));
                    Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(this, *args)");
                    mTvDate.setText(str);
                } else if (mDateType == 4) {
                    Calendar calendar = Calendar.getInstance(Locale.CHINA);
                    try {
                        Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
                        T mPresenter = getMPresenter();
                        if (mPresenter == 0) {
                            Intrinsics.throwNpe();
                        }
                        calendar.setTime(DateUtil.string2Date(((AmbientVolumeDetailPresenter) mPresenter).getMStartDate(), DateUtil.DATE_FORMAT_YMD));
                        calendar.set(5, 1);
                        calendar.add(2, chartBean.getDataIndex());
                        IChartDetailCallback mCallBack3 = getMCallBack();
                        if (mCallBack3 != null) {
                            mCallBack3.updateSelectDate(this, DateUtil.format(calendar, DateUtil.DATE_FORMAT_YMD));
                        }
                    } catch (Exception unused) {
                    }
                    ((AmbientVolumeDetailTipViewHolder) tipViewHolder).getMTvDate().setText(DateUtil.format(calendar, DateUtil.DATE_FORMAT_YM_2));
                } else {
                    Calendar calendar2 = Calendar.getInstance(Locale.CHINA);
                    try {
                        Intrinsics.checkExpressionValueIsNotNull(calendar2, "calendar");
                        T mPresenter2 = getMPresenter();
                        if (mPresenter2 == 0) {
                            Intrinsics.throwNpe();
                        }
                        calendar2.setTime(DateUtil.string2Date(((AmbientVolumeDetailPresenter) mPresenter2).getMStartDate(), DateUtil.DATE_FORMAT_YMD));
                    } catch (Exception unused2) {
                    }
                    calendar2.add(5, chartBean.getDataIndex());
                    IChartDetailCallback mCallBack4 = getMCallBack();
                    if (mCallBack4 != null) {
                        mCallBack4.updateSelectDate(this, DateUtil.format(calendar2, DateUtil.DATE_FORMAT_YMD));
                    }
                    ((AmbientVolumeDetailTipViewHolder) tipViewHolder).getMTvDate().setText(DateUtil.format(calendar2, DateUtil.DATE_FORMAT_MD_2));
                }
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: compiled from: AmbientVolumeDetailFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/ido/life/module/home/ambientvolume/AmbientVolumeDetailFragment$Companion;", "", "()V", "KEY_CHART_STYLE", "", "getInstance", "Lcom/ido/life/module/home/ambientvolume/AmbientVolumeDetailFragment;", "args", "Landroid/os/Bundle;", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final AmbientVolumeDetailFragment getInstance(Bundle args) {
            AmbientVolumeDetailFragment ambientVolumeDetailFragment = new AmbientVolumeDetailFragment();
            if (args != null) {
                ambientVolumeDetailFragment.setArguments(args);
            }
            return ambientVolumeDetailFragment;
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailFragment
    public AmbientVolumeDetailPresenter getPresenter() {
        return new AmbientVolumeDetailPresenter(this);
    }
}