package com.ido.life.module.home.oxygen;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.view.KeyEventDispatcher;
import androidx.fragment.app.FragmentActivity;
import com.boat.Xtend.two.R;
import com.ido.common.utils.DipPixelUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.NetworkUtil;
import com.ido.common.utils.ResourceUtil;
import com.ido.life.bean.BaseCharBean;
import com.ido.life.customview.AmbientVolumeProgressBar;
import com.ido.life.customview.TrainingEffectProgressView;
import com.ido.life.customview.charter.BarChartBar;
import com.ido.life.customview.charter.CustomChatBar;
import com.ido.life.customview.charter.LineChartBar;
import com.ido.life.database.model.SportHealth;
import com.ido.life.database.model.UserInfo;
import com.ido.life.module.device.activity.HeartRateMonitoringActivity;
import com.ido.life.module.home.chartdetail.vertical.BaseDetailCoreFragment;
import com.ido.life.module.home.chartdetail.vertical.BaseDetailViewHolder;
import com.ido.life.module.home.chartdetail.vertical.IChartDetailCallback;
import com.ido.life.module.home.rate.vertical.RateDetailTopViewHolder;
import com.ido.life.module.sport.bean.PracticeEffectBean;
import com.ido.life.util.DateUtil;
import com.ido.life.util.GreenDaoUtil;
import com.ido.smartrefresh.layoutkernel.util.SmartUtil;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.apache.commons.io.IOUtils;

/* JADX INFO: compiled from: OxygenDetailFragment.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\u0013\u0018\u0000 Y2>\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00012\u00020\u00062\u00020\u00072\u00020\u0004:\u0001YB\u0005¢\u0006\u0002\u0010\bJ\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0010H\u0016J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\u0010\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u0014H\u0016J\b\u0010\u001a\u001a\u00020\u0017H\u0016J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u0010\u0010\u001f\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\b\u0010 \u001a\u00020\u0010H\u0014J\b\u0010!\u001a\u00020\u0010H\u0016J\b\u0010\"\u001a\u00020\u0005H\u0016J\n\u0010#\u001a\u0004\u0018\u00010\u0014H\u0016J\u0010\u0010$\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u0010\u0010%\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u0010\u0010&\u001a\u00020\u00172\u0006\u0010'\u001a\u00020(H\u0016J.\u0010)\u001a\u00020\u00172\f\u0010*\u001a\b\u0012\u0004\u0012\u00020,0+2\u0006\u0010-\u001a\u00020\u00102\u0006\u0010.\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020/H\u0002J\b\u00100\u001a\u00020\u0017H\u0014J\b\u00101\u001a\u00020\u0017H\u0016J\b\u00102\u001a\u00020\u0017H\u0016J\u001e\u00103\u001a\u00020\u00172\u0006\u00104\u001a\u00020(2\f\u00105\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0016J\u001e\u00106\u001a\u00020\u00172\u0006\u00104\u001a\u00020(2\f\u00107\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0016J\u001e\u00108\u001a\u00020\u00172\u0006\u00104\u001a\u00020(2\f\u00109\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0016J\u001e\u0010:\u001a\u00020\u00172\u0006\u00104\u001a\u00020(2\f\u0010;\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0016J\b\u0010<\u001a\u00020\u0017H\u0016J\u0012\u0010=\u001a\u00020\u00172\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0014J\b\u0010>\u001a\u00020\u0017H\u0014J\u0010\u0010?\u001a\u00020(2\u0006\u0010\u0015\u001a\u00020\u0010H\u0016J\b\u0010@\u001a\u00020\u0017H\u0002J\b\u0010A\u001a\u00020\u0017H\u0016J\b\u0010B\u001a\u00020\u0017H\u0002J\u0012\u0010C\u001a\u00020\u00172\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0015J\b\u0010D\u001a\u00020\u0017H\u0014J \u0010E\u001a\u00020\u00172\u0006\u0010F\u001a\u00020G2\u0006\u0010H\u001a\u00020(2\u0006\u0010I\u001a\u00020\nH\u0002J8\u0010J\u001a\u00020\u00172\u000e\u0010K\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010,0\u00022\u0006\u0010L\u001a\u00020\u00102\u0006\u0010M\u001a\u00020G2\u0006\u0010I\u001a\u00020\n2\u0006\u0010.\u001a\u00020\u0010H\u0002J\u0010\u0010N\u001a\u00020\u00172\u0006\u0010O\u001a\u00020\u0010H\u0016J\u0010\u0010P\u001a\u00020\u00172\u0006\u0010Q\u001a\u00020\u0010H\u0016J\b\u0010R\u001a\u00020\u0017H\u0016J\b\u0010S\u001a\u00020\u0017H\u0016J\b\u0010T\u001a\u00020\u0017H\u0016J\b\u0010U\u001a\u00020\u0017H\u0016J\u0010\u0010V\u001a\u00020\u00172\u0006\u0010.\u001a\u00020\u0010H\u0002J\b\u0010W\u001a\u00020(H\u0014J\b\u0010X\u001a\u00020\u0017H\u0002R\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006Z"}, d2 = {"Lcom/ido/life/module/home/oxygen/OxygenDetailFragment;", "Lcom/ido/life/module/home/chartdetail/vertical/BaseDetailCoreFragment;", "", "Lcom/ido/life/bean/BaseCharBean;", "Lcom/ido/life/module/home/oxygen/IOxygenDetailView;", "Lcom/ido/life/module/home/oxygen/OxygenDetailPresenter;", "Lcom/ido/life/customview/charter/CustomChatBar$CaluteXGridLineCallback;", "Landroid/view/View$OnClickListener;", "()V", "ageIn", "", "getAgeIn", "()Ljava/lang/String;", "setAgeIn", "(Ljava/lang/String;)V", "mHeartRateMode", "", "calculateXGridLineValue", "", "target", "Landroid/view/View;", "index", "clearCache", "", "clickAction", "view", "dismissLoading", "getBottomView", "Lcom/ido/life/module/home/chartdetail/vertical/BaseDetailViewHolder;", "context", "Landroid/content/Context;", "getChartHeight", "getLayoutResId", "getPageType", "getPresenter", "getTipLayContent", "getTipViewHolder", "getTopView", "hideSelectedUi", "resetDate", "", "initProgressView", "effectList", "", "Lcom/ido/life/module/sport/bean/PracticeEffectBean;", "type", "position", "Landroid/widget/LinearLayout;", "initView", "onBottomViewRefresh", "onDetailLoadFailed", "onLoadSuccessByDay", "showChartAnimator", "dayChartList", "onLoadSuccessByMonth", "monthChartList", "onLoadSuccessByWeek", "weekChartList", "onLoadSuccessByYear", "yearChartList", "onTopViewRefresh", "refreshBottomView", "refreshChart", "refreshChartTipView", "refreshCircleChartLanguage", "refreshLanguage", "refreshOtherUiLanguage", "refreshTopView", "refreshUiByDateType", "setDrableCircle", "tv", "Landroid/widget/TextView;", "isleft", "color", "setProgressColor", "list_proper", "i", "tv_progress", "setXMaxValue", "maxValue", "setXMinValue", "minValue", "showLoadFailedView", "showLoadSuccessView", "showLoading", "showLoadingView", "showOxygenProgress", "showTopRightLayout", "toShowOxygenAboutDailog", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
public final class OxygenDetailFragment extends BaseDetailCoreFragment<List<? extends BaseCharBean>, List<? extends BaseCharBean>, List<? extends BaseCharBean>, List<? extends BaseCharBean>, IOxygenDetailView, OxygenDetailPresenter> implements CustomChatBar.CaluteXGridLineCallback, View.OnClickListener, IOxygenDetailView {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private HashMap _$_findViewCache;
    private int mHeartRateMode = -1;
    private String ageIn = "";

    private final void refreshCircleChartLanguage() {
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
        return R.layout.fragment_oxygen_detail_layout;
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

    public final String getAgeIn() {
        return this.ageIn;
    }

    public final void setAgeIn(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.ageIn = str;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailFragment, com.ido.common.base.BaseCoreFragment
    protected void initView() {
        super.initView();
        OxygenDetailFragment oxygenDetailFragment = this;
        ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.bar_chart)).mXGridLineCallback = oxygenDetailFragment;
        ((LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).mXGridLineCallback = oxygenDetailFragment;
        if (getMActivity() instanceof CustomChatBar.ChartClickListener) {
            BarChartBar barChartBar = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.bar_chart);
            KeyEventDispatcher.Component mActivity = getMActivity();
            if (mActivity == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.ido.life.customview.charter.CustomChatBar.ChartClickListener");
            }
            barChartBar.setClickListener((CustomChatBar.ChartClickListener) mActivity);
            LineChartBar lineChartBar = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            KeyEventDispatcher.Component mActivity2 = getMActivity();
            if (mActivity2 == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.ido.life.customview.charter.CustomChatBar.ChartClickListener");
            }
            lineChartBar.setClickListener((CustomChatBar.ChartClickListener) mActivity2);
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailFragment
    public OxygenDetailPresenter getPresenter() {
        return new OxygenDetailPresenter(this);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailFragment
    protected void refreshChart() {
        LineChartBar lineChartBar;
        LineChartBar lineChartBar2;
        super.refreshChart();
        if (((LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) == null || (lineChartBar = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) == null || lineChartBar.getVisibility() != 0 || (lineChartBar2 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) == null) {
            return;
        }
        lineChartBar2.refreshChart(true);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailFragment
    public void refreshLanguage() {
        super.refreshLanguage();
        refreshCircleChartLanguage();
        refreshOtherUiLanguage();
    }

    private final void refreshOtherUiLanguage() {
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder topViewHolder = mCallBack != null ? mCallBack.getTopViewHolder(this) : null;
        if (topViewHolder instanceof OxygenDetailTopViewHolder) {
            ((OxygenDetailTopViewHolder) topViewHolder).getMTvTitleAvg().setText(getLanguageText(R.string.home_detail_ave_ios));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void setXMaxValue(int maxValue) {
        if (((OxygenDetailPresenter) getMPresenter()).getMDateType() == 1) {
            BarChartBar barChartBar = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.bar_chart);
            if (barChartBar != null) {
                barChartBar.mXMaxValue = 48;
                return;
            }
            return;
        }
        LineChartBar lineChartBar = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (lineChartBar != null) {
            lineChartBar.mXMaxValue = maxValue;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void setXMinValue(int minValue) {
        if (((OxygenDetailPresenter) getMPresenter()).getMDateType() == 1) {
            BarChartBar barChartBar = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.bar_chart);
            if (barChartBar != null) {
                barChartBar.mXMinValue = 1;
                return;
            }
            return;
        }
        LineChartBar lineChartBar = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (lineChartBar != null) {
            lineChartBar.mXMinValue = minValue;
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
        LineChartBar lineChartBar;
        BarChartBar barChartBar;
        if (((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.bar_chart)) != null && (barChartBar = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.bar_chart)) != null && barChartBar.getVisibility() == 0) {
            BarChartBar barChartBar2 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.bar_chart);
            if (barChartBar2 != null) {
                barChartBar2.clearList();
            }
            BarChartBar barChartBar3 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.bar_chart);
            if (barChartBar3 != null) {
                barChartBar3.refreshChart(false);
            }
        }
        if (((LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) != null && (lineChartBar = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) != null && lineChartBar.getVisibility() == 0) {
            LineChartBar lineChartBar2 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (lineChartBar2 != null) {
                lineChartBar2.clearList();
            }
            LineChartBar lineChartBar3 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (lineChartBar3 != null) {
                lineChartBar3.refreshChart(false);
            }
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
        if (topViewHolder instanceof OxygenDetailTopViewHolder) {
            ((OxygenDetailTopViewHolder) topViewHolder).showLoadingView();
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void showLoadSuccessView() {
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder topViewHolder = mCallBack != null ? mCallBack.getTopViewHolder(this) : null;
        if (topViewHolder instanceof OxygenDetailTopViewHolder) {
            OxygenDetailTopViewHolder oxygenDetailTopViewHolder = (OxygenDetailTopViewHolder) topViewHolder;
            oxygenDetailTopViewHolder.showSuccessView(false);
            if (showTopRightLayout()) {
                oxygenDetailTopViewHolder.showRightLayout();
            } else {
                oxygenDetailTopViewHolder.hideRightLayout();
            }
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void showLoadFailedView() {
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder topViewHolder = mCallBack != null ? mCallBack.getTopViewHolder(this) : null;
        if (topViewHolder instanceof OxygenDetailTopViewHolder) {
            ((OxygenDetailTopViewHolder) topViewHolder).showLoadFailedView(this);
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public BaseDetailViewHolder getTopView(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.detail_top_oxygen_layout, (ViewGroup) null);
        Intrinsics.checkExpressionValueIsNotNull(viewInflate, "LayoutInflater.from(cont…_top_oxygen_layout, null)");
        return new OxygenDetailTopViewHolder(viewInflate);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCoreFragment
    protected void refreshTopView(Context context) {
        super.refreshTopView(context);
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder topViewHolder = mCallBack != null ? mCallBack.getTopViewHolder(this) : null;
        if (topViewHolder instanceof OxygenDetailTopViewHolder) {
            topViewHolder.setDefaultValue();
            int mAvgOxygen = ((OxygenDetailPresenter) getMPresenter()).getMAvgOxygen();
            if (((OxygenDetailPresenter) getMPresenter()).getMDateType() == 1) {
                OxygenDetailTopViewHolder oxygenDetailTopViewHolder = (OxygenDetailTopViewHolder) topViewHolder;
                oxygenDetailTopViewHolder.getMTvTitleAvg().setVisibility(8);
                oxygenDetailTopViewHolder.getMTvAvg().setText(String.valueOf(((OxygenDetailPresenter) getMPresenter()).getMDayLastOxygen()));
            } else {
                OxygenDetailTopViewHolder oxygenDetailTopViewHolder2 = (OxygenDetailTopViewHolder) topViewHolder;
                oxygenDetailTopViewHolder2.getMTvTitleAvg().setVisibility(0);
                oxygenDetailTopViewHolder2.getMTvAvg().setText(String.valueOf(mAvgOxygen));
            }
            if (((OxygenDetailPresenter) getMPresenter()).getMAvgOxygen() == 0) {
                OxygenDetailTopViewHolder oxygenDetailTopViewHolder3 = (OxygenDetailTopViewHolder) topViewHolder;
                oxygenDetailTopViewHolder3.getMTvState().setVisibility(8);
                oxygenDetailTopViewHolder3.getMTvAvg().setText("--");
                oxygenDetailTopViewHolder3.getMTvUnit().setVisibility(8);
                oxygenDetailTopViewHolder3.getMTvDate().setText(((OxygenDetailPresenter) getMPresenter()).getDateText());
                return;
            }
            OxygenDetailTopViewHolder oxygenDetailTopViewHolder4 = (OxygenDetailTopViewHolder) topViewHolder;
            oxygenDetailTopViewHolder4.getMTvState().setVisibility(0);
            oxygenDetailTopViewHolder4.getMTvUnit().setVisibility(0);
            oxygenDetailTopViewHolder4.getMTvState().setVisibility(0);
            oxygenDetailTopViewHolder4.getMTvDate().setText(((OxygenDetailPresenter) getMPresenter()).getDateText());
            ((OxygenDetailPresenter) getMPresenter()).oxygenAction(mAvgOxygen);
            int mPosition = ((OxygenDetailPresenter) getMPresenter()).getMPosition();
            if (mPosition == 0) {
                oxygenDetailTopViewHolder4.getMTvState().setText(LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_one));
                return;
            }
            if (mPosition == 1) {
                oxygenDetailTopViewHolder4.getMTvState().setText(LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_two));
                return;
            }
            if (mPosition == 2) {
                oxygenDetailTopViewHolder4.getMTvState().setText(LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_three));
                return;
            }
            if (mPosition == 3) {
                oxygenDetailTopViewHolder4.getMTvState().setText(LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_four));
            } else if (mPosition == 4) {
                oxygenDetailTopViewHolder4.getMTvState().setText(LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_five));
            } else {
                if (mPosition != 5) {
                    return;
                }
                oxygenDetailTopViewHolder4.getMTvState().setText(LanguageUtil.getLanguageText(R.string.sport_detail_train_oxygen_level_six));
            }
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCoreFragment, com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public BaseDetailViewHolder getBottomView(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.detail_bottom_oxygen_layout, (ViewGroup) null);
        Intrinsics.checkExpressionValueIsNotNull(viewInflate, "LayoutInflater.from(cont…ttom_oxygen_layout, null)");
        return new OxygenDetailBottomViewHolder(viewInflate);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public void hideSelectedUi(boolean resetDate) {
        IChartDetailCallback mCallBack;
        if (resetDate && (mCallBack = getMCallBack()) != null) {
            mCallBack.updateSelectDate(this, ((OxygenDetailPresenter) getMPresenter()).getMStartDate());
        }
        if (((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.bar_chart)) != null) {
            BarChartBar barChartBar = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.bar_chart);
            if (barChartBar == null) {
                Intrinsics.throwNpe();
            }
            if (barChartBar.getVisibility() == 0) {
                BarChartBar barChartBar2 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.bar_chart);
                if (barChartBar2 == null) {
                    Intrinsics.throwNpe();
                }
                barChartBar2.refreshChart(false);
            }
        }
        if (((LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) != null) {
            LineChartBar lineChartBar = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (lineChartBar == null) {
                Intrinsics.throwNpe();
            }
            if (lineChartBar.getVisibility() == 0) {
                LineChartBar lineChartBar2 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                if (lineChartBar2 == null) {
                    Intrinsics.throwNpe();
                }
                lineChartBar2.refreshChart(false);
            }
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCoreFragment, com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public int getChartHeight(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        return context.getResources().getDimensionPixelSize(R.dimen.sw_dp_200);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCoreFragment
    protected void refreshBottomView(Context context) {
        super.refreshBottomView(context);
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder bottomViewHolder = mCallBack != null ? mCallBack.getBottomViewHolder(this) : null;
        if (bottomViewHolder instanceof OxygenDetailBottomViewHolder) {
            ((OxygenDetailPresenter) getMPresenter()).getLatelyDayData();
            if (((OxygenDetailPresenter) getMPresenter()).getLastTwoDays().size() > 1) {
                OxygenDetailBottomViewHolder oxygenDetailBottomViewHolder = (OxygenDetailBottomViewHolder) bottomViewHolder;
                oxygenDetailBottomViewHolder.getMClVo2Appraise().setVisibility(0);
                oxygenDetailBottomViewHolder.getMRtvZj().setVisibility(0);
                TextView mRtvOxygenFine = oxygenDetailBottomViewHolder.getMRtvOxygenFine();
                SportHealth sportHealth = ((OxygenDetailPresenter) getMPresenter()).getLastTwoDays().get(0);
                Intrinsics.checkExpressionValueIsNotNull(sportHealth, "mPresenter.lastTwoDays[0]");
                mRtvOxygenFine.setText(String.valueOf(sportHealth.getVo2max()));
                TextView mRtvOxygenPoor = oxygenDetailBottomViewHolder.getMRtvOxygenPoor();
                SportHealth sportHealth2 = ((OxygenDetailPresenter) getMPresenter()).getLastTwoDays().get(1);
                Intrinsics.checkExpressionValueIsNotNull(sportHealth2, "mPresenter.lastTwoDays[1]");
                mRtvOxygenPoor.setText(String.valueOf(sportHealth2.getVo2max()));
                AmbientVolumeProgressBar mRtvUnitFineDate = oxygenDetailBottomViewHolder.getMRtvUnitFineDate();
                SportHealth sportHealth3 = ((OxygenDetailPresenter) getMPresenter()).getLastTwoDays().get(0);
                Intrinsics.checkExpressionValueIsNotNull(sportHealth3, "mPresenter.lastTwoDays[0]");
                String dateTime = sportHealth3.getDateTime();
                Intrinsics.checkExpressionValueIsNotNull(dateTime, "mPresenter.lastTwoDays[0].dateTime");
                mRtvUnitFineDate.setLeftLabel(dateTime);
                oxygenDetailBottomViewHolder.getMRtvUnitFineDate().setLeftLabelColor(ResourceUtil.getColor(R.color.white));
                AmbientVolumeProgressBar mRtvUnitPoorDate = oxygenDetailBottomViewHolder.getMRtvUnitPoorDate();
                SportHealth sportHealth4 = ((OxygenDetailPresenter) getMPresenter()).getLastTwoDays().get(1);
                Intrinsics.checkExpressionValueIsNotNull(sportHealth4, "mPresenter.lastTwoDays[1]");
                String dateTime2 = sportHealth4.getDateTime();
                Intrinsics.checkExpressionValueIsNotNull(dateTime2, "mPresenter.lastTwoDays[1].dateTime");
                mRtvUnitPoorDate.setLeftLabel(dateTime2);
                SportHealth sportHealth5 = ((OxygenDetailPresenter) getMPresenter()).getLastTwoDays().get(0);
                Intrinsics.checkExpressionValueIsNotNull(sportHealth5, "mPresenter.lastTwoDays[0]");
                int vo2max = sportHealth5.getVo2max();
                SportHealth sportHealth6 = ((OxygenDetailPresenter) getMPresenter()).getLastTwoDays().get(1);
                Intrinsics.checkExpressionValueIsNotNull(sportHealth6, "mPresenter.lastTwoDays[1]");
                if (vo2max > sportHealth6.getVo2max()) {
                    oxygenDetailBottomViewHolder.getMRtvUnitFineDate().setProgress(100);
                    AmbientVolumeProgressBar mRtvUnitPoorDate2 = oxygenDetailBottomViewHolder.getMRtvUnitPoorDate();
                    SportHealth sportHealth7 = ((OxygenDetailPresenter) getMPresenter()).getLastTwoDays().get(1);
                    Intrinsics.checkExpressionValueIsNotNull(sportHealth7, "mPresenter.lastTwoDays[1]");
                    int vo2max2 = sportHealth7.getVo2max() * 100;
                    SportHealth sportHealth8 = ((OxygenDetailPresenter) getMPresenter()).getLastTwoDays().get(0);
                    Intrinsics.checkExpressionValueIsNotNull(sportHealth8, "mPresenter.lastTwoDays[0]");
                    mRtvUnitPoorDate2.setProgress(vo2max2 / sportHealth8.getVo2max());
                } else {
                    oxygenDetailBottomViewHolder.getMRtvUnitPoorDate().setProgress(100);
                    AmbientVolumeProgressBar mRtvUnitFineDate2 = oxygenDetailBottomViewHolder.getMRtvUnitFineDate();
                    SportHealth sportHealth9 = ((OxygenDetailPresenter) getMPresenter()).getLastTwoDays().get(0);
                    Intrinsics.checkExpressionValueIsNotNull(sportHealth9, "mPresenter.lastTwoDays[0]");
                    int vo2max3 = sportHealth9.getVo2max() * 100;
                    SportHealth sportHealth10 = ((OxygenDetailPresenter) getMPresenter()).getLastTwoDays().get(1);
                    Intrinsics.checkExpressionValueIsNotNull(sportHealth10, "mPresenter.lastTwoDays[1]");
                    mRtvUnitFineDate2.setProgress(vo2max3 / sportHealth10.getVo2max());
                }
                oxygenDetailBottomViewHolder.getMRtvUnitPoorDate().invalidate();
                oxygenDetailBottomViewHolder.getMRtvUnitFineDate().invalidate();
                SportHealth sportHealth11 = ((OxygenDetailPresenter) getMPresenter()).getLastTwoDays().get(0);
                Intrinsics.checkExpressionValueIsNotNull(sportHealth11, "mPresenter.lastTwoDays[0]");
                int vo2max4 = sportHealth11.getVo2max();
                Intrinsics.checkExpressionValueIsNotNull(((OxygenDetailPresenter) getMPresenter()).getLastTwoDays().get(1), "mPresenter.lastTwoDays[1]");
                if (vo2max4 - r5.getVo2max() > 0.1d) {
                    oxygenDetailBottomViewHolder.getMRtvAppraise().setText(LanguageUtil.getLanguageText(R.string.oxygen_detail_bottom_appraise1));
                } else {
                    SportHealth sportHealth12 = ((OxygenDetailPresenter) getMPresenter()).getLastTwoDays().get(1);
                    Intrinsics.checkExpressionValueIsNotNull(sportHealth12, "mPresenter.lastTwoDays[1]");
                    int vo2max5 = sportHealth12.getVo2max();
                    Intrinsics.checkExpressionValueIsNotNull(((OxygenDetailPresenter) getMPresenter()).getLastTwoDays().get(0), "mPresenter.lastTwoDays[0]");
                    if (vo2max5 - r5.getVo2max() > 0.1d) {
                        oxygenDetailBottomViewHolder.getMRtvAppraise().setText(LanguageUtil.getLanguageText(R.string.oxygen_detail_bottom_appraise2));
                    } else {
                        oxygenDetailBottomViewHolder.getMRtvAppraise().setText(LanguageUtil.getLanguageText(R.string.oxygen_detail_bottom_appraise3));
                    }
                }
            } else {
                OxygenDetailBottomViewHolder oxygenDetailBottomViewHolder2 = (OxygenDetailBottomViewHolder) bottomViewHolder;
                oxygenDetailBottomViewHolder2.getMClVo2Appraise().setVisibility(8);
                oxygenDetailBottomViewHolder2.getMRtvZj().setVisibility(8);
            }
            OxygenDetailBottomViewHolder oxygenDetailBottomViewHolder3 = (OxygenDetailBottomViewHolder) bottomViewHolder;
            oxygenDetailBottomViewHolder3.getMIvOxygenAbout().setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.module.home.oxygen.OxygenDetailFragment.refreshBottomView.1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    OxygenDetailFragment.this.toShowOxygenAboutDailog();
                }
            });
            bottomViewHolder.setDefaultValue();
            if (((OxygenDetailPresenter) getMPresenter()).getMDateType() == 1) {
                oxygenDetailBottomViewHolder3.getMClVo2Detail().setVisibility(0);
                if (((OxygenDetailPresenter) getMPresenter()).getMAvgOxygen() == 0) {
                    oxygenDetailBottomViewHolder3.getMRtvOxygenScore().setVisibility(0);
                    oxygenDetailBottomViewHolder3.getMRtvOxygenScore().setText("--");
                    oxygenDetailBottomViewHolder3.getMRtvOxygenTip().setText(LanguageUtil.getLanguageText(R.string.oxygen_detail_bottom_no_data));
                    showOxygenProgress(-1);
                    return;
                }
                oxygenDetailBottomViewHolder3.getMRtvOxygenScore().setVisibility(0);
                oxygenDetailBottomViewHolder3.getMRtvOxygenScore().setText(String.valueOf(((OxygenDetailPresenter) getMPresenter()).getMAvgOxygen()));
                ((OxygenDetailPresenter) getMPresenter()).oxygenAction(((OxygenDetailPresenter) getMPresenter()).getMAvgOxygen());
                OxygenDetailPresenter oxygenDetailPresenter = (OxygenDetailPresenter) getMPresenter();
                int mAvgOxygen = ((OxygenDetailPresenter) getMPresenter()).getMAvgOxygen();
                UserInfo userInfoQueryUserInfo = GreenDaoUtil.queryUserInfo(((OxygenDetailPresenter) getMPresenter()).getUserId());
                Intrinsics.checkExpressionValueIsNotNull(userInfoQueryUserInfo, "GreenDaoUtil.queryUserInfo(mPresenter.getUserId())");
                oxygenDetailBottomViewHolder3.getMRtvOxygenTip().setText(oxygenDetailPresenter.getOxygenProgress(mAvgOxygen, userInfoQueryUserInfo.getGender()));
                showOxygenProgress(((OxygenDetailPresenter) getMPresenter()).getMPosition());
                return;
            }
            oxygenDetailBottomViewHolder3.getMClVo2Detail().setVisibility(8);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void showOxygenProgress(int position) {
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder bottomViewHolder = mCallBack != null ? mCallBack.getBottomViewHolder(this) : null;
        if (bottomViewHolder instanceof OxygenDetailBottomViewHolder) {
            OxygenDetailBottomViewHolder oxygenDetailBottomViewHolder = (OxygenDetailBottomViewHolder) bottomViewHolder;
            TrainingEffectProgressView mLlOxygenProgress = oxygenDetailBottomViewHolder.getMLlOxygenProgress();
            if (mLlOxygenProgress != null) {
                mLlOxygenProgress.setCurrProgress(position);
            }
            TrainingEffectProgressView mLlOxygenProgress2 = oxygenDetailBottomViewHolder.getMLlOxygenProgress();
            if (mLlOxygenProgress2 != null) {
                T mPresenter = getMPresenter();
                if (mPresenter == 0) {
                    Intrinsics.throwNpe();
                }
                mLlOxygenProgress2.setMPropertyList(((OxygenDetailPresenter) mPresenter).getOxygenList());
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCoreFragment, com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public boolean refreshChartTipView(int index) {
        String strValueOf;
        String strValueOf2;
        IChartDetailCallback mCallBack;
        super.refreshChartTipView(index);
        if (index < 0) {
            return false;
        }
        IChartDetailCallback mCallBack2 = getMCallBack();
        BaseDetailViewHolder tipViewHolder = mCallBack2 != null ? mCallBack2.getTipViewHolder(this) : null;
        if (!(tipViewHolder instanceof OxygenDetailTipViewHolder)) {
            return false;
        }
        if (((OxygenDetailPresenter) getMPresenter()).getMDateType() == 1) {
            LineChartBar lineChartBar = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            List list = lineChartBar != null ? lineChartBar.mList : null;
            if (list != null && list.size() > index) {
                BaseCharBean charBean = (BaseCharBean) list.get(index);
                OxygenDetailPresenter oxygenDetailPresenter = (OxygenDetailPresenter) getMPresenter();
                Intrinsics.checkExpressionValueIsNotNull(charBean, "charBean");
                SportHealth sportHealthItem = oxygenDetailPresenter.getSportHealthItem(charBean.getIndex());
                if (sportHealthItem != null) {
                    OxygenDetailTipViewHolder oxygenDetailTipViewHolder = (OxygenDetailTipViewHolder) tipViewHolder;
                    oxygenDetailTipViewHolder.getMTvTitle().setVisibility(8);
                    oxygenDetailTipViewHolder.getMTvTipAvg().setText(String.valueOf(sportHealthItem.getVo2max()));
                    Date timeMills2Date = DateUtil.formatTimeMills2Date(DateUtil.getLongFromDateStr(sportHealthItem.getDateTime()));
                    Intrinsics.checkExpressionValueIsNotNull(timeMills2Date, "formatTimeMills2Date(time)");
                    oxygenDetailTipViewHolder.getMTvTipDate().setText(String.valueOf(timeMills2Date.getHours()));
                }
            }
        } else {
            LineChartBar lineChartBar2 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (lineChartBar2 == null) {
                Intrinsics.throwNpe();
            }
            List<T> list2 = lineChartBar2.mList;
            if (list2 != 0 && list2.size() > index) {
                BaseCharBean charBean2 = (BaseCharBean) list2.get(index);
                OxygenDetailPresenter oxygenDetailPresenter2 = (OxygenDetailPresenter) getMPresenter();
                Intrinsics.checkExpressionValueIsNotNull(charBean2, "charBean");
                SportHealth sportHealthItem2 = oxygenDetailPresenter2.getSportHealthItem(charBean2.getIndex());
                if (sportHealthItem2 != null) {
                    if (isVisible() && (mCallBack = getMCallBack()) != null) {
                        mCallBack.updateSelectDate(this, sportHealthItem2.getDateTime());
                    }
                    OxygenDetailTipViewHolder oxygenDetailTipViewHolder2 = (OxygenDetailTipViewHolder) tipViewHolder;
                    oxygenDetailTipViewHolder2.getMTvTitle().setVisibility(0);
                    oxygenDetailTipViewHolder2.getMTvTipAvg().setText(String.valueOf(sportHealthItem2.getVo2max()));
                    String date = sportHealthItem2.getDateTime();
                    OxygenDetailPresenter oxygenDetailPresenter3 = (OxygenDetailPresenter) getMPresenter();
                    Intrinsics.checkExpressionValueIsNotNull(date, "date");
                    int[] iArrConvertOxygenDateToIntArray = oxygenDetailPresenter3.convertOxygenDateToIntArray(date);
                    int mDateType = ((OxygenDetailPresenter) getMPresenter()).getMDateType();
                    if (mDateType == 2 || mDateType == 3) {
                        if (iArrConvertOxygenDateToIntArray != null && iArrConvertOxygenDateToIntArray.length == 3) {
                            int i = iArrConvertOxygenDateToIntArray[1];
                            int i2 = iArrConvertOxygenDateToIntArray[2];
                            if (i < 10) {
                                StringBuilder sb = new StringBuilder();
                                sb.append('0');
                                sb.append(i);
                                strValueOf = sb.toString();
                            } else {
                                strValueOf = String.valueOf(i);
                            }
                            if (i2 < 10) {
                                StringBuilder sb2 = new StringBuilder();
                                sb2.append('0');
                                sb2.append(i2);
                                strValueOf2 = sb2.toString();
                            } else {
                                strValueOf2 = String.valueOf(i2);
                            }
                            oxygenDetailTipViewHolder2.getMTvTipDate().setText(DateUtil.getReversalTime(strValueOf + IOUtils.DIR_SEPARATOR_UNIX + strValueOf2));
                        }
                    } else if (mDateType == 4 && iArrConvertOxygenDateToIntArray != null && iArrConvertOxygenDateToIntArray.length == 3) {
                        int i3 = iArrConvertOxygenDateToIntArray[0];
                        int i4 = iArrConvertOxygenDateToIntArray[1];
                        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                        Object[] objArr = {Integer.valueOf(i3), Integer.valueOf(i4)};
                        String str = String.format("%02d/%02d", Arrays.copyOf(objArr, objArr.length));
                        Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(format, *args)");
                        oxygenDetailTipViewHolder2.getMTvTipDate().setText(DateUtil.getReversalTime(str));
                    }
                }
            }
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void onLoadSuccessByDay(boolean showChartAnimator, List<? extends BaseCharBean> dayChartList) {
        Intrinsics.checkParameterIsNotNull(dayChartList, "dayChartList");
        if (((OxygenDetailPresenter) getMPresenter()).getMDateType() != 1 || this.mRootView == null || ((LinearLayout) _$_findCachedViewById(com.ido.life.R.id.lay_chart)) == null) {
            return;
        }
        refreshTopView(getMActivity());
        refreshBottomView(getMActivity());
        List<String> leftLabelList = ((OxygenDetailPresenter) getMPresenter()).getLeftLabelList();
        LineChartBar lineChartBar = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (lineChartBar != null) {
            lineChartBar.setLabelYLeftList(leftLabelList);
        }
        List<String> list = leftLabelList;
        if (!(list == null || list.isEmpty())) {
            LineChartBar lineChartBar2 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (lineChartBar2 != null) {
                lineChartBar2.mYMinValue = Integer.parseInt(leftLabelList.get(0));
            }
            LineChartBar lineChartBar3 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (lineChartBar3 != null) {
                lineChartBar3.mYMaxValue = Integer.parseInt(leftLabelList.get(leftLabelList.size() - 1));
            }
        }
        LineChartBar lineChartBar4 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (lineChartBar4 != null) {
            lineChartBar4.mXMinValue = ((OxygenDetailPresenter) getMPresenter()).getMXMinValue();
        }
        LineChartBar lineChartBar5 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (lineChartBar5 != null) {
            lineChartBar5.mXMaxValue = ((OxygenDetailPresenter) getMPresenter()).getMXMaxValue();
        }
        LineChartBar lineChartBar6 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (lineChartBar6 != null) {
            lineChartBar6.setList(dayChartList);
        }
        LineChartBar lineChartBar7 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (lineChartBar7 != null) {
            lineChartBar7.mOxygenRectBgEnable = true;
        }
        LineChartBar lineChartBar8 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (lineChartBar8 != null) {
            lineChartBar8.refreshChart(showChartAnimator);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void onLoadSuccessByWeek(boolean showChartAnimator, List<? extends BaseCharBean> weekChartList) {
        Intrinsics.checkParameterIsNotNull(weekChartList, "weekChartList");
        if (this.mRootView == null || ((OxygenDetailPresenter) getMPresenter()).getMDateType() != 2 || ((LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) == null) {
            return;
        }
        refreshTopView(getMActivity());
        refreshBottomView(getMActivity());
        OxygenDetailPresenter oxygenDetailPresenter = (OxygenDetailPresenter) getMPresenter();
        List<String> leftLabelList = oxygenDetailPresenter != null ? oxygenDetailPresenter.getLeftLabelList() : null;
        LineChartBar lineChartBar = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (lineChartBar != null) {
            lineChartBar.setLabelYLeftList(leftLabelList);
        }
        List<String> list = leftLabelList;
        if (!(list == null || list.isEmpty())) {
            LineChartBar lineChartBar2 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (lineChartBar2 != null) {
                lineChartBar2.mYMinValue = Integer.parseInt(leftLabelList.get(0));
            }
            LineChartBar lineChartBar3 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (lineChartBar3 != null) {
                lineChartBar3.mYMaxValue = Integer.parseInt(leftLabelList.get(leftLabelList.size() - 1));
            }
        }
        LineChartBar lineChartBar4 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (lineChartBar4 != null) {
            lineChartBar4.setList(weekChartList);
        }
        LineChartBar lineChartBar5 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (lineChartBar5 != null) {
            lineChartBar5.mBottomTitle = (String) null;
        }
        LineChartBar lineChartBar6 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (lineChartBar6 != null) {
            lineChartBar6.mOxygenRectBgEnable = false;
        }
        LineChartBar lineChartBar7 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (lineChartBar7 != null) {
            lineChartBar7.mXMinValue = ((OxygenDetailPresenter) getMPresenter()).getMXMinValue();
        }
        LineChartBar lineChartBar8 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (lineChartBar8 != null) {
            lineChartBar8.mXMaxValue = ((OxygenDetailPresenter) getMPresenter()).getMXMaxValue();
        }
        LineChartBar lineChartBar9 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (lineChartBar9 != null) {
            lineChartBar9.refreshChart(showChartAnimator);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void onLoadSuccessByMonth(boolean showChartAnimator, List<? extends BaseCharBean> monthChartList) {
        Intrinsics.checkParameterIsNotNull(monthChartList, "monthChartList");
        if (((OxygenDetailPresenter) getMPresenter()).getMDateType() != 3 || this.mRootView == null || ((LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) == null) {
            return;
        }
        refreshTopView(getMActivity());
        refreshBottomView(getMActivity());
        List<String> leftLabelList = ((OxygenDetailPresenter) getMPresenter()).getLeftLabelList();
        LineChartBar lineChartBar = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (lineChartBar != null) {
            lineChartBar.setLabelYLeftList(leftLabelList);
        }
        List<String> list = leftLabelList;
        if (!(list == null || list.isEmpty())) {
            LineChartBar lineChartBar2 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (lineChartBar2 != null) {
                lineChartBar2.mYMinValue = Integer.parseInt(leftLabelList.get(0));
            }
            LineChartBar lineChartBar3 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (lineChartBar3 != null) {
                lineChartBar3.mYMaxValue = Integer.parseInt(leftLabelList.get(leftLabelList.size() - 1));
            }
        }
        LineChartBar lineChartBar4 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (lineChartBar4 != null) {
            lineChartBar4.setList(monthChartList);
        }
        LineChartBar lineChartBar5 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (lineChartBar5 != null) {
            lineChartBar5.mOxygenRectBgEnable = false;
        }
        LineChartBar lineChartBar6 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (lineChartBar6 != null) {
            lineChartBar6.mBottomTitle = (String) null;
        }
        LineChartBar lineChartBar7 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (lineChartBar7 != null) {
            lineChartBar7.mXMinValue = ((OxygenDetailPresenter) getMPresenter()).getMXMinValue();
        }
        LineChartBar lineChartBar8 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (lineChartBar8 != null) {
            lineChartBar8.mXMaxValue = ((OxygenDetailPresenter) getMPresenter()).getMXMaxValue();
        }
        LineChartBar lineChartBar9 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (lineChartBar9 != null) {
            lineChartBar9.refreshChart(showChartAnimator);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void onLoadSuccessByYear(boolean showChartAnimator, List<? extends BaseCharBean> yearChartList) {
        Intrinsics.checkParameterIsNotNull(yearChartList, "yearChartList");
        if (((OxygenDetailPresenter) getMPresenter()).getMDateType() != 4 || this.mRootView == null || ((LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) == null) {
            return;
        }
        refreshTopView(getMActivity());
        refreshBottomView(getMActivity());
        List<String> leftLabelList = ((OxygenDetailPresenter) getMPresenter()).getLeftLabelList();
        List<String> list = leftLabelList;
        if (!(list == null || list.isEmpty())) {
            LineChartBar lineChartBar = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (lineChartBar != null) {
                lineChartBar.setLabelYLeftList(leftLabelList);
            }
            LineChartBar lineChartBar2 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (lineChartBar2 != null) {
                lineChartBar2.mYMinValue = Integer.parseInt(leftLabelList.get(0));
            }
            LineChartBar lineChartBar3 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (lineChartBar3 != null) {
                lineChartBar3.mYMaxValue = Integer.parseInt(leftLabelList.get(leftLabelList.size() - 1));
            }
        }
        LineChartBar lineChartBar4 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (lineChartBar4 != null) {
            lineChartBar4.setList(yearChartList);
        }
        int iYear = ((OxygenDetailPresenter) getMPresenter()).year();
        if (iYear > 0) {
            LineChartBar lineChartBar5 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (lineChartBar5 != null) {
                lineChartBar5.mBottomTitle = String.valueOf(iYear);
            }
        } else {
            LineChartBar lineChartBar6 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (lineChartBar6 != null) {
                lineChartBar6.mBottomTitle = (String) null;
            }
        }
        LineChartBar lineChartBar7 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (lineChartBar7 != null) {
            lineChartBar7.mOxygenRectBgEnable = false;
        }
        LineChartBar lineChartBar8 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (lineChartBar8 != null) {
            lineChartBar8.mXMinValue = ((OxygenDetailPresenter) getMPresenter()).getMXMinValue();
        }
        LineChartBar lineChartBar9 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (lineChartBar9 != null) {
            lineChartBar9.mXMaxValue = ((OxygenDetailPresenter) getMPresenter()).getMXMaxValue();
        }
        LineChartBar lineChartBar10 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (lineChartBar10 != null) {
            lineChartBar10.refreshChart(showChartAnimator);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void onDetailLoadFailed() {
        LineChartBar lineChartBar;
        if (this.mRootView == null || ((LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) == null || ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.bar_chart)) == null) {
            return;
        }
        List<String> leftLabelList = ((OxygenDetailPresenter) getMPresenter()).getLeftLabelList();
        int mDateType = ((OxygenDetailPresenter) getMPresenter()).getMDateType();
        if (mDateType == 1) {
            BarChartBar barChartBar = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.bar_chart);
            if (barChartBar != null) {
                barChartBar.setLabelYLeftList(leftLabelList);
            }
            BarChartBar barChartBar2 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.bar_chart);
            if (barChartBar2 != null) {
                barChartBar2.mYMinValue = Integer.parseInt(leftLabelList.get(0));
            }
            BarChartBar barChartBar3 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.bar_chart);
            if (barChartBar3 != null) {
                barChartBar3.mYMaxValue = Integer.parseInt(leftLabelList.get(leftLabelList.size() - 1));
            }
            BarChartBar barChartBar4 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.bar_chart);
            if (barChartBar4 != null) {
                barChartBar4.refreshChart(false);
            }
        } else if (mDateType == 2 || mDateType == 3) {
            LineChartBar lineChartBar2 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (lineChartBar2 != null) {
                lineChartBar2.setLabelYLeftList(leftLabelList);
            }
            LineChartBar lineChartBar3 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (lineChartBar3 != null) {
                lineChartBar3.mYMinValue = Integer.parseInt(leftLabelList.get(0));
            }
            LineChartBar lineChartBar4 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (lineChartBar4 != null) {
                lineChartBar4.mYMaxValue = Integer.parseInt(leftLabelList.get(leftLabelList.size() - 1));
            }
            LineChartBar lineChartBar5 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (lineChartBar5 != null) {
                lineChartBar5.mBottomTitle = (String) null;
            }
            LineChartBar lineChartBar6 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (lineChartBar6 != null) {
                lineChartBar6.refreshChart(false);
            }
        } else if (mDateType == 4) {
            LineChartBar lineChartBar7 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (lineChartBar7 != null) {
                lineChartBar7.mBottomTitle = (String) null;
            }
            LineChartBar lineChartBar8 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (lineChartBar8 != null) {
                lineChartBar8.setLabelYLeftList(leftLabelList);
            }
            LineChartBar lineChartBar9 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (lineChartBar9 != null) {
                lineChartBar9.mYMinValue = Integer.parseInt(leftLabelList.get(0));
            }
            LineChartBar lineChartBar10 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (lineChartBar10 != null) {
                lineChartBar10.mYMaxValue = Integer.parseInt(leftLabelList.get(leftLabelList.size() - 1));
            }
            int iYear = ((OxygenDetailPresenter) getMPresenter()).year();
            if (iYear > 0 && (lineChartBar = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) != null) {
                lineChartBar.mBottomTitle = String.valueOf(iYear);
            }
            LineChartBar lineChartBar11 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (lineChartBar11 != null) {
                lineChartBar11.refreshChart(false);
            }
        }
        refreshTopView(getMActivity());
        refreshBottomView(getMActivity());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailFragment
    protected boolean showTopRightLayout() {
        return ((OxygenDetailPresenter) getMPresenter()).getMDateType() == 1;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCoreFragment, com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public View getTipLayContent() {
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder tipViewHolder = mCallBack != null ? mCallBack.getTipViewHolder(this) : null;
        if (tipViewHolder instanceof OxygenDetailTipViewHolder) {
            return ((OxygenDetailTipViewHolder) tipViewHolder).getMLayTipContent();
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailFragment
    protected void refreshUiByDateType() {
        LineChartBar lineChartBar;
        LineChartBar lineChartBar2;
        super.refreshUiByDateType();
        if (getMCallBack() != null) {
            IChartDetailCallback mCallBack = getMCallBack();
            if (mCallBack == null) {
                Intrinsics.throwNpe();
            }
            OxygenDetailFragment oxygenDetailFragment = this;
            if (mCallBack.isShow(oxygenDetailFragment)) {
                setMNeedRefreshPage(false);
                if (((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.bar_chart)) == null) {
                    return;
                }
                List<String> leftLabelList = ((OxygenDetailPresenter) getMPresenter()).getLeftLabelList();
                ((LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).mCircleStrokeColor = getResources().getColor(R.color.color_E83D1D);
                ((LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).mLineColor = getResources().getColor(R.color.color_E83D1D);
                int mDateType = ((OxygenDetailPresenter) getMPresenter()).getMDateType();
                if (mDateType == 1) {
                    LineChartBar lineChartBar3 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                    if (lineChartBar3 != null) {
                        lineChartBar3.mCircleStrokeSelectedColor = getResources().getColor(R.color.color_FC5D68);
                    }
                    BarChartBar barChartBar = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.bar_chart);
                    if (barChartBar != null) {
                        barChartBar.setVisibility(8);
                    }
                    LineChartBar lineChartBar4 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                    if (lineChartBar4 != null) {
                        lineChartBar4.setLabelYLeftList(leftLabelList);
                    }
                    LineChartBar lineChartBar5 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                    if (lineChartBar5 != null) {
                        lineChartBar5.mYMinValue = Integer.parseInt(leftLabelList.get(0));
                    }
                    LineChartBar lineChartBar6 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                    if (lineChartBar6 != null) {
                        lineChartBar6.mYMaxValue = Integer.parseInt(leftLabelList.get(leftLabelList.size() - 1));
                    }
                    LineChartBar lineChartBar7 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                    if (lineChartBar7 != null) {
                        lineChartBar7.setVisibility(0);
                    }
                    LineChartBar lineChartBar8 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                    if (lineChartBar8 != null) {
                        lineChartBar8.setLabelXList(((OxygenDetailPresenter) getMPresenter()).getBottomLabelList());
                    }
                    LineChartBar lineChartBar9 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                    if (lineChartBar9 != null) {
                        lineChartBar9.refreshChart(false);
                    }
                } else if (mDateType == 2) {
                    LineChartBar lineChartBar10 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                    if (lineChartBar10 != null) {
                        lineChartBar10.mCircleStrokeSelectedColor = getResources().getColor(R.color.color_FC5D68);
                    }
                    BarChartBar barChartBar2 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.bar_chart);
                    if (barChartBar2 != null) {
                        barChartBar2.setVisibility(8);
                    }
                    LineChartBar lineChartBar11 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                    if (lineChartBar11 != null) {
                        lineChartBar11.setLabelYLeftList(leftLabelList);
                    }
                    LineChartBar lineChartBar12 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                    if (lineChartBar12 != null) {
                        lineChartBar12.mYMinValue = Integer.parseInt(leftLabelList.get(0));
                    }
                    LineChartBar lineChartBar13 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                    if (lineChartBar13 != null) {
                        lineChartBar13.mYMaxValue = Integer.parseInt(leftLabelList.get(leftLabelList.size() - 1));
                    }
                    LineChartBar lineChartBar14 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                    if (lineChartBar14 != null) {
                        lineChartBar14.setVisibility(0);
                    }
                    LineChartBar lineChartBar15 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                    if (lineChartBar15 != null) {
                        lineChartBar15.setLabelXList(((OxygenDetailPresenter) getMPresenter()).getBottomLabelList());
                    }
                    if ((getContext() instanceof CustomChatBar.ChartClickListener) && (lineChartBar = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) != null) {
                        Object context = getContext();
                        if (context == null) {
                            throw new TypeCastException("null cannot be cast to non-null type com.ido.life.customview.charter.CustomChatBar.ChartClickListener");
                        }
                        lineChartBar.setClickListener((CustomChatBar.ChartClickListener) context);
                    }
                    int iYear = ((OxygenDetailPresenter) getMPresenter()).year();
                    if (iYear > 0) {
                        LineChartBar lineChartBar16 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                        if (lineChartBar16 != null) {
                            lineChartBar16.mBottomTitle = String.valueOf(iYear);
                        }
                    } else {
                        LineChartBar lineChartBar17 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                        if (lineChartBar17 != null) {
                            lineChartBar17.mBottomTitle = (String) null;
                        }
                    }
                    LineChartBar lineChartBar18 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                    if (lineChartBar18 != null) {
                        lineChartBar18.refreshChart(false);
                    }
                } else if (mDateType == 3 || mDateType == 4) {
                    LineChartBar lineChartBar19 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                    if (lineChartBar19 != null) {
                        lineChartBar19.mCircleStrokeSelectedColor = getResources().getColor(R.color.color_FE9C5E);
                    }
                    BarChartBar barChartBar3 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.bar_chart);
                    if (barChartBar3 != null) {
                        barChartBar3.setVisibility(8);
                    }
                    LineChartBar lineChartBar20 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                    if (lineChartBar20 != null) {
                        lineChartBar20.setLabelYLeftList(leftLabelList);
                    }
                    LineChartBar lineChartBar21 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                    if (lineChartBar21 != null) {
                        lineChartBar21.mYMinValue = Integer.parseInt(leftLabelList.get(0));
                    }
                    LineChartBar lineChartBar22 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                    if (lineChartBar22 != null) {
                        lineChartBar22.mYMaxValue = Integer.parseInt(leftLabelList.get(leftLabelList.size() - 1));
                    }
                    LineChartBar lineChartBar23 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                    if (lineChartBar23 != null) {
                        lineChartBar23.setVisibility(0);
                    }
                    LineChartBar lineChartBar24 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                    if (lineChartBar24 != null) {
                        lineChartBar24.setLabelXList(((OxygenDetailPresenter) getMPresenter()).getBottomLabelList());
                    }
                    if ((getContext() instanceof CustomChatBar.ChartClickListener) && (lineChartBar2 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) != null) {
                        Object context2 = getContext();
                        if (context2 == null) {
                            throw new TypeCastException("null cannot be cast to non-null type com.ido.life.customview.charter.CustomChatBar.ChartClickListener");
                        }
                        lineChartBar2.setClickListener((CustomChatBar.ChartClickListener) context2);
                    }
                    int iYear2 = ((OxygenDetailPresenter) getMPresenter()).year();
                    if (iYear2 > 0) {
                        LineChartBar lineChartBar25 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                        if (lineChartBar25 != null) {
                            lineChartBar25.mBottomTitle = String.valueOf(iYear2);
                        }
                    } else {
                        LineChartBar lineChartBar26 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                        if (lineChartBar26 != null) {
                            lineChartBar26.mBottomTitle = (String) null;
                        }
                    }
                    LineChartBar lineChartBar27 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                    if (lineChartBar27 != null) {
                        lineChartBar27.refreshChart(false);
                    }
                }
                ((OxygenDetailPresenter) getMPresenter()).getDetailData();
                IChartDetailCallback mCallBack2 = getMCallBack();
                if (mCallBack2 != null) {
                    mCallBack2.updateSelectDate(oxygenDetailFragment, ((OxygenDetailPresenter) getMPresenter()).getMStartDate());
                }
            }
        }
    }

    @Override // com.ido.life.customview.charter.CustomChatBar.CaluteXGridLineCallback
    public float calculateXGridLineValue(View target, int index) {
        Intrinsics.checkParameterIsNotNull(target, "target");
        BarChartBar barChartBar = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.bar_chart);
        List<String> labelYLeftList = null;
        if (barChartBar != null && barChartBar.getVisibility() == 0) {
            BarChartBar barChartBar2 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.bar_chart);
            if (barChartBar2 != null) {
                labelYLeftList = barChartBar2.getLabelYLeftList();
            }
        } else {
            LineChartBar lineChartBar = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (lineChartBar != null) {
                labelYLeftList = lineChartBar.getLabelYLeftList();
            }
        }
        if (labelYLeftList == null || labelYLeftList.size() <= index) {
            return 0.0f;
        }
        Intrinsics.checkExpressionValueIsNotNull(labelYLeftList.get(index), "labelList[index]");
        return Integer.parseInt(r2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCoreFragment
    public void clickAction(View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        int id = view.getId();
        if (id != R.id.lay_loading) {
            if (id != R.id.tv_all_pressure_state) {
                return;
            }
            Intent intent = new Intent(getMActivity(), (Class<?>) HeartRateMonitoringActivity.class);
            intent.putExtra(HeartRateMonitoringActivity.MODE, this.mHeartRateMode);
            startActivity(intent);
            return;
        }
        if (NetworkUtil.isConnected(getMActivity())) {
            IChartDetailCallback mCallBack = getMCallBack();
            BaseDetailViewHolder topViewHolder = mCallBack != null ? mCallBack.getTopViewHolder(this) : null;
            if (topViewHolder instanceof RateDetailTopViewHolder) {
                ((RateDetailTopViewHolder) topViewHolder).showLoadingView();
                ((OxygenDetailPresenter) getMPresenter()).getDetailData();
                return;
            }
            return;
        }
        showToast(R.string.public_net_unuse);
    }

    /* JADX INFO: compiled from: OxygenDetailFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¨\u0006\u0007"}, d2 = {"Lcom/ido/life/module/home/oxygen/OxygenDetailFragment$Companion;", "", "()V", "getInstance", "Lcom/ido/life/module/home/oxygen/OxygenDetailFragment;", "bundle", "Landroid/os/Bundle;", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final OxygenDetailFragment getInstance(Bundle bundle) {
            OxygenDetailFragment oxygenDetailFragment = new OxygenDetailFragment();
            if (bundle != null) {
                oxygenDetailFragment.setArguments(bundle);
            }
            return oxygenDetailFragment;
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public BaseDetailViewHolder getTipViewHolder(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.detail_chart_tip_oxygen_layout, (ViewGroup) null);
        Intrinsics.checkExpressionValueIsNotNull(viewInflate, "LayoutInflater.from(cont…_tip_oxygen_layout, null)");
        return new OxygenDetailTipViewHolder(viewInflate);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void showLoading() {
        showLoadingDialog();
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void dismissLoading() {
        dismissLoadingDialog();
    }

    private final void initProgressView(List<PracticeEffectBean> effectList, int type, int position, LinearLayout view) {
        view.removeAllViews();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -1, 2.0f);
        if (getContext() == null) {
            return;
        }
        int i = 0;
        for (PracticeEffectBean practiceEffectBean : effectList) {
            View itemview = LayoutInflater.from(getContext()).inflate(R.layout.item_practice, (ViewGroup) null);
            Intrinsics.checkExpressionValueIsNotNull(itemview, "itemview");
            itemview.setLayoutParams(layoutParams);
            TextView tv_title = (TextView) itemview.findViewById(R.id.rtv_practice_title);
            RelativeLayout layout = (RelativeLayout) itemview.findViewById(R.id.practice_top_ll);
            TextView tv_progress = (TextView) itemview.findViewById(R.id.tv_pactice_progress);
            Intrinsics.checkExpressionValueIsNotNull(tv_title, "tv_title");
            tv_title.setText(practiceEffectBean.getName());
            if (type == 1) {
                Intrinsics.checkExpressionValueIsNotNull(tv_progress, "tv_progress");
                setProgressColor(effectList, i, tv_progress, "#8E8E93", position);
                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -1);
                layoutParams2.setMargins(DipPixelUtil.dip2px(10.0f), 0, 0, 0);
                view.setLayoutParams(layoutParams2);
            } else if (type == 2) {
                Intrinsics.checkExpressionValueIsNotNull(tv_progress, "tv_progress");
                String color = practiceEffectBean.getColor();
                Intrinsics.checkExpressionValueIsNotNull(color, "item.color");
                setProgressColor(effectList, i, tv_progress, color, position);
                if (position == i) {
                    Intrinsics.checkExpressionValueIsNotNull(layout, "layout");
                    layout.setVisibility(0);
                }
                LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, -1);
                layoutParams3.setMargins(DipPixelUtil.dip2px(10.0f), 0, 0, 0);
                view.setLayoutParams(layoutParams3);
            } else if (type == 3) {
                Intrinsics.checkExpressionValueIsNotNull(tv_progress, "tv_progress");
                setProgressColor(effectList, i, tv_progress, "#00DE6D", position);
                LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(-1, SmartUtil.dp2px(4.0f));
                layoutParams4.setMargins(DipPixelUtil.dip2px(80.0f), 0, 0, 0);
                view.setLayoutParams(layoutParams4);
            }
            view.addView(itemview);
            i++;
        }
    }

    private final void setProgressColor(List<? extends PracticeEffectBean> list_proper, int i, TextView tv_progress, String color, int position) {
        if (i == 0) {
            setDrableCircle(tv_progress, true, color);
        } else if (i == list_proper.size() - 1) {
            setDrableCircle(tv_progress, false, color);
        } else {
            tv_progress.setBackgroundColor(Color.parseColor(color));
        }
    }

    private final void setDrableCircle(TextView tv, boolean isleft, String color) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setColor(Color.parseColor(color));
        gradientDrawable.setCornerRadii(isleft ? new float[]{SmartUtil.dp2px(3.0f), SmartUtil.dp2px(3.0f), 0.0f, 0.0f, 0.0f, 0.0f, SmartUtil.dp2px(3.0f), SmartUtil.dp2px(3.0f)} : new float[]{0.0f, 0.0f, SmartUtil.dp2px(3.0f), SmartUtil.dp2px(3.0f), SmartUtil.dp2px(3.0f), SmartUtil.dp2px(3.0f), 0.0f, 0.0f});
        tv.setBackground(gradientDrawable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final void toShowOxygenAboutDailog() {
        ((OxygenDetailPresenter) getMPresenter()).getOxygenates();
        final OxygenAboutDialogFragment oxygenAbout = OxygenAboutDialogFragment.newInstance(((OxygenDetailPresenter) getMPresenter()).getMPosition(), ((OxygenDetailPresenter) getMPresenter()).getAgeIn(), ((OxygenDetailPresenter) getMPresenter()).getOxygenates());
        Intrinsics.checkExpressionValueIsNotNull(oxygenAbout, "oxygenAbout");
        oxygenAbout.setCancelable(false);
        oxygenAbout.setCancelListener(new View.OnClickListener() { // from class: com.ido.life.module.home.oxygen.OxygenDetailFragment.toShowOxygenAboutDailog.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                oxygenAbout.dismissAllowingStateLoss();
            }
        });
        FragmentActivity activity = getActivity();
        oxygenAbout.show(activity != null ? activity.getSupportFragmentManager() : null);
    }
}