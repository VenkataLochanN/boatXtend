package com.ido.life.module.home.pressure.detail.vertical;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.view.KeyEventDispatcher;
import com.boat.Xtend.two.R;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.NetworkUtil;
import com.ido.life.bean.BaseCharBean;
import com.ido.life.bean.PressureBarChartPoint;
import com.ido.life.customview.charter.BarChartBar;
import com.ido.life.customview.charter.CustomChatBar;
import com.ido.life.customview.charter.LineChartBar;
import com.ido.life.database.model.HealthPressure;
import com.ido.life.enums.PressureEnum;
import com.ido.life.module.device.activity.HeartRateMonitoringActivity;
import com.ido.life.module.home.chartdetail.vertical.BaseDetailCoreFragment;
import com.ido.life.module.home.chartdetail.vertical.BaseDetailViewHolder;
import com.ido.life.module.home.chartdetail.vertical.IChartDetailCallback;
import com.ido.life.module.sport.bean.PieChartBean;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.math.MathKt;
import org.apache.commons.io.IOUtils;

/* JADX INFO: compiled from: PresureDetailFragment.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u001e\u0018\u0000 ?2>\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0002\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00012\u00020\u00072\u00020\b2\u00020\u0005:\u0001?B\u0005¢\u0006\u0002\u0010\tJ\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000bH\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u000fH\u0016J\b\u0010\u0015\u001a\u00020\u0012H\u0016J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u000bH\u0014J\b\u0010\u001b\u001a\u00020\u000bH\u0016J\b\u0010\u001c\u001a\u00020\u0006H\u0016J\n\u0010\u001d\u001a\u0004\u0018\u00010\u000fH\u0016J\u0012\u0010\u001e\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0010\u0010\u001f\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0010\u0010 \u001a\u00020\u00122\u0006\u0010!\u001a\u00020\"H\u0016J\b\u0010#\u001a\u00020\u0012H\u0014J\b\u0010$\u001a\u00020\"H\u0016J\b\u0010%\u001a\u00020\u0012H\u0016J\b\u0010&\u001a\u00020\u0012H\u0016J\u001e\u0010'\u001a\u00020\u00122\u0006\u0010(\u001a\u00020\"2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0016J \u0010*\u001a\u00020\u00122\u0006\u0010(\u001a\u00020\"2\u000e\u0010+\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0002H\u0016J\u001e\u0010,\u001a\u00020\u00122\u0006\u0010(\u001a\u00020\"2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00040\u0002H\u0016J\u001e\u0010.\u001a\u00020\u00122\u0006\u0010(\u001a\u00020\"2\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00040\u0002H\u0016J\b\u00100\u001a\u00020\u0012H\u0016J\u0012\u00101\u001a\u00020\u00122\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0014J\b\u00102\u001a\u00020\u0012H\u0014J\u0010\u00103\u001a\u00020\"2\u0006\u0010\u0010\u001a\u00020\u000bH\u0016J\u0012\u00104\u001a\u00020\u00122\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0014J\b\u00105\u001a\u00020\u0012H\u0014J\u0010\u00106\u001a\u00020\u00122\u0006\u00107\u001a\u00020\u000bH\u0016J\u0010\u00108\u001a\u00020\u00122\u0006\u00109\u001a\u00020\u000bH\u0016J\b\u0010:\u001a\u00020\u0012H\u0016J\b\u0010;\u001a\u00020\u0012H\u0016J\b\u0010<\u001a\u00020\u0012H\u0016J\b\u0010=\u001a\u00020\u0012H\u0016J\b\u0010>\u001a\u00020\"H\u0014R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006@"}, d2 = {"Lcom/ido/life/module/home/pressure/detail/vertical/PresureDetailFragment;", "Lcom/ido/life/module/home/chartdetail/vertical/BaseDetailCoreFragment;", "", "Lcom/ido/life/bean/PressureBarChartPoint;", "Lcom/ido/life/bean/BaseCharBean;", "Lcom/ido/life/module/home/pressure/detail/vertical/IPresureDetailView;", "Lcom/ido/life/module/home/pressure/detail/vertical/PresureDetailPresenter;", "Lcom/ido/life/customview/charter/CustomChatBar$CaluteXGridLineCallback;", "Landroid/view/View$OnClickListener;", "()V", "mHeartRateMode", "", "calculateXGridLineValue", "", "target", "Landroid/view/View;", "index", "clearCache", "", "clickAction", "view", "dismissLoading", "getBottomView", "Lcom/ido/life/module/home/chartdetail/vertical/BaseDetailViewHolder;", "context", "Landroid/content/Context;", "getLayoutResId", "getPageType", "getPresenter", "getTipLayContent", "getTipViewHolder", "getTopView", "hideSelectedUi", "resetDate", "", "initView", "needEventBus", "onBottomViewRefresh", "onDetailLoadFailed", "onLoadSuccessByDay", "showChartAnimator", "dayChartList", "onLoadSuccessByMonth", "monthChartList", "onLoadSuccessByWeek", "weekChartList", "onLoadSuccessByYear", "yearChartList", "onTopViewRefresh", "refreshBottomView", "refreshChart", "refreshChartTipView", "refreshTopView", "refreshUiByDateType", "setXMaxValue", "maxValue", "setXMinValue", "minValue", "showLoadFailedView", "showLoadSuccessView", "showLoading", "showLoadingView", "showTopRightLayout", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
public final class PresureDetailFragment extends BaseDetailCoreFragment<List<? extends PressureBarChartPoint>, List<? extends BaseCharBean>, List<? extends BaseCharBean>, List<? extends BaseCharBean>, IPresureDetailView, PresureDetailPresenter> implements CustomChatBar.CaluteXGridLineCallback, View.OnClickListener, IPresureDetailView {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = PresureDetailFragment.class.getSimpleName();
    private HashMap _$_findViewCache;
    private int mHeartRateMode = -1;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[PressureEnum.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            $EnumSwitchMapping$0[PressureEnum.RELAX.ordinal()] = 1;
            $EnumSwitchMapping$0[PressureEnum.NORMAL.ordinal()] = 2;
            $EnumSwitchMapping$0[PressureEnum.MEDIUM.ordinal()] = 3;
            $EnumSwitchMapping$0[PressureEnum.HIGH.ordinal()] = 4;
            $EnumSwitchMapping$1 = new int[PressureEnum.values().length];
            $EnumSwitchMapping$1[PressureEnum.MINZERO.ordinal()] = 1;
            $EnumSwitchMapping$1[PressureEnum.RELAX.ordinal()] = 2;
            $EnumSwitchMapping$1[PressureEnum.NORMAL.ordinal()] = 3;
            $EnumSwitchMapping$1[PressureEnum.MEDIUM.ordinal()] = 4;
            $EnumSwitchMapping$1[PressureEnum.HIGH.ordinal()] = 5;
        }
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
        return R.layout.fragment_presure_detail_layout;
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

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailFragment, com.ido.common.base.BaseCoreFragment
    protected void initView() {
        super.initView();
        PresureDetailFragment presureDetailFragment = this;
        ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.bar_chart)).mXGridLineCallback = presureDetailFragment;
        ((LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)).mXGridLineCallback = presureDetailFragment;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailFragment
    public PresureDetailPresenter getPresenter() {
        return new PresureDetailPresenter(this);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public BaseDetailViewHolder getTipViewHolder(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.detail_chart_tip_presure_layout, (ViewGroup) null);
        Intrinsics.checkExpressionValueIsNotNull(viewInflate, "LayoutInflater.from(cont…tip_presure_layout, null)");
        return new PressureDetailTipViewHolder(viewInflate);
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

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void setXMaxValue(int maxValue) {
        PresureDetailPresenter presureDetailPresenter = (PresureDetailPresenter) getMPresenter();
        if (presureDetailPresenter != null && presureDetailPresenter.getMDateType() == 1) {
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
        PresureDetailPresenter presureDetailPresenter = (PresureDetailPresenter) getMPresenter();
        if (presureDetailPresenter != null && presureDetailPresenter.getMDateType() == 1) {
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
        if (topViewHolder instanceof PressureDetailTopViewHolder) {
            ((PressureDetailTopViewHolder) topViewHolder).showLoadingView();
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void showLoadSuccessView() {
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder topViewHolder = mCallBack != null ? mCallBack.getTopViewHolder(this) : null;
        if (topViewHolder instanceof PressureDetailTopViewHolder) {
            ((PressureDetailTopViewHolder) topViewHolder).showSuccessView(showTopRightLayout());
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void showLoadFailedView() {
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder topViewHolder = mCallBack != null ? mCallBack.getTopViewHolder(this) : null;
        if (topViewHolder instanceof PressureDetailTopViewHolder) {
            ((PressureDetailTopViewHolder) topViewHolder).showLoadFailedView(this);
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public BaseDetailViewHolder getTopView(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        return new PressureDetailTopViewHolder(LayoutInflater.from(context).inflate(R.layout.detail_top_presure_layout, (ViewGroup) null));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCoreFragment
    protected void refreshTopView(Context context) {
        super.refreshTopView(context);
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder topViewHolder = mCallBack != null ? mCallBack.getTopViewHolder(this) : null;
        if (topViewHolder instanceof PressureDetailTopViewHolder) {
            topViewHolder.setDefaultValue();
            PressureDetailTopViewHolder pressureDetailTopViewHolder = (PressureDetailTopViewHolder) topViewHolder;
            TextView textView = pressureDetailTopViewHolder.mTvDate;
            Intrinsics.checkExpressionValueIsNotNull(textView, "topViewHolder.mTvDate");
            T mPresenter = getMPresenter();
            if (mPresenter == 0) {
                Intrinsics.throwNpe();
            }
            textView.setText(((PresureDetailPresenter) mPresenter).getDateText());
            T mPresenter2 = getMPresenter();
            if (mPresenter2 == 0) {
                Intrinsics.throwNpe();
            }
            int mAvgPressure = ((PresureDetailPresenter) mPresenter2).getMAvgPressure();
            if (mAvgPressure >= PressureEnum.RELAX.Min && mAvgPressure <= PressureEnum.HIGH.Max) {
                TextView textView2 = pressureDetailTopViewHolder.mTvAvg;
                Intrinsics.checkExpressionValueIsNotNull(textView2, "topViewHolder.mTvAvg");
                textView2.setText(String.valueOf(mAvgPressure));
                PressureEnum pressureEnumByValue = PressureEnum.getPressureEnumByValue(mAvgPressure);
                if (pressureEnumByValue != null) {
                    int i = WhenMappings.$EnumSwitchMapping$0[pressureEnumByValue.ordinal()];
                    if (i == 1) {
                        TextView textView3 = pressureDetailTopViewHolder.mTvState;
                        Intrinsics.checkExpressionValueIsNotNull(textView3, "topViewHolder.mTvState");
                        textView3.setText(LanguageUtil.getLanguageText(R.string.home_pressure_relax));
                    } else if (i == 2) {
                        TextView textView4 = pressureDetailTopViewHolder.mTvState;
                        Intrinsics.checkExpressionValueIsNotNull(textView4, "topViewHolder.mTvState");
                        textView4.setText(LanguageUtil.getLanguageText(R.string.home_pressure_normal));
                    } else if (i == 3) {
                        TextView textView5 = pressureDetailTopViewHolder.mTvState;
                        Intrinsics.checkExpressionValueIsNotNull(textView5, "topViewHolder.mTvState");
                        textView5.setText(LanguageUtil.getLanguageText(R.string.home_pressure_middle));
                    } else if (i == 4) {
                        TextView textView6 = pressureDetailTopViewHolder.mTvState;
                        Intrinsics.checkExpressionValueIsNotNull(textView6, "topViewHolder.mTvState");
                        textView6.setText(LanguageUtil.getLanguageText(R.string.home_pressure_high));
                    }
                }
            } else {
                TextView textView7 = pressureDetailTopViewHolder.mTvAvg;
                Intrinsics.checkExpressionValueIsNotNull(textView7, "topViewHolder.mTvAvg");
                textView7.setText("--");
            }
            T mPresenter3 = getMPresenter();
            if (mPresenter3 == 0) {
                Intrinsics.throwNpe();
            }
            if (((PresureDetailPresenter) mPresenter3).getMMinPressure() >= PressureEnum.RELAX.Min) {
                T mPresenter4 = getMPresenter();
                if (mPresenter4 == 0) {
                    Intrinsics.throwNpe();
                }
                if (((PresureDetailPresenter) mPresenter4).getMMaxPressure() <= PressureEnum.HIGH.Max) {
                    TextView textView8 = pressureDetailTopViewHolder.mTvMaxmin;
                    Intrinsics.checkExpressionValueIsNotNull(textView8, "topViewHolder.mTvMaxmin");
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    Object[] objArr = new Object[2];
                    T mPresenter5 = getMPresenter();
                    if (mPresenter5 == 0) {
                        Intrinsics.throwNpe();
                    }
                    objArr[0] = Integer.valueOf(((PresureDetailPresenter) mPresenter5).getMMinPressure());
                    T mPresenter6 = getMPresenter();
                    if (mPresenter6 == 0) {
                        Intrinsics.throwNpe();
                    }
                    objArr[1] = Integer.valueOf(((PresureDetailPresenter) mPresenter6).getMMaxPressure());
                    String str = String.format("%d-%d", Arrays.copyOf(objArr, objArr.length));
                    Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(format, *args)");
                    textView8.setText(str);
                    return;
                }
            }
            TextView textView9 = pressureDetailTopViewHolder.mTvMaxmin;
            Intrinsics.checkExpressionValueIsNotNull(textView9, "topViewHolder.mTvMaxmin");
            textView9.setText("--");
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCoreFragment, com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public BaseDetailViewHolder getBottomView(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        return new PressureDetailBottomViewHolder(LayoutInflater.from(context).inflate(R.layout.detail_bottom_pressure_layout, (ViewGroup) null));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public void hideSelectedUi(boolean resetDate) {
        IChartDetailCallback mCallBack;
        if (resetDate && (mCallBack = getMCallBack()) != null) {
            PresureDetailFragment presureDetailFragment = this;
            PresureDetailPresenter presureDetailPresenter = (PresureDetailPresenter) getMPresenter();
            mCallBack.updateSelectDate(presureDetailFragment, presureDetailPresenter != null ? presureDetailPresenter.getMStartDate() : null);
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

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCoreFragment
    protected void refreshBottomView(Context context) {
        super.refreshBottomView(context);
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder bottomViewHolder = mCallBack != null ? mCallBack.getBottomViewHolder(this) : null;
        if (bottomViewHolder instanceof PressureDetailBottomViewHolder) {
            bottomViewHolder.setDefaultValue();
            PressureDetailBottomViewHolder pressureDetailBottomViewHolder = (PressureDetailBottomViewHolder) bottomViewHolder;
            LinearLayout linearLayout = pressureDetailBottomViewHolder.mLayAllPressureState;
            Intrinsics.checkExpressionValueIsNotNull(linearLayout, "bottomViewHolder.mLayAllPressureState");
            linearLayout.setVisibility(8);
            pressureDetailBottomViewHolder.mTvAllPressureState.setOnClickListener(null);
            T mPresenter = getMPresenter();
            if (mPresenter == 0) {
                Intrinsics.throwNpe();
            }
            if (((PresureDetailPresenter) mPresenter).getMHigherRadio() == 0) {
                T mPresenter2 = getMPresenter();
                if (mPresenter2 == 0) {
                    Intrinsics.throwNpe();
                }
                if (((PresureDetailPresenter) mPresenter2).getMMediumRadio() == 0) {
                    T mPresenter3 = getMPresenter();
                    if (mPresenter3 == 0) {
                        Intrinsics.throwNpe();
                    }
                    if (((PresureDetailPresenter) mPresenter3).getMNormalRadio() == 0) {
                        T mPresenter4 = getMPresenter();
                        if (mPresenter4 == 0) {
                            Intrinsics.throwNpe();
                        }
                        if (((PresureDetailPresenter) mPresenter4).getMRelaxRadio() == 0) {
                            LinearLayout linearLayout2 = pressureDetailBottomViewHolder.mLayPipe;
                            Intrinsics.checkExpressionValueIsNotNull(linearLayout2, "bottomViewHolder.mLayPipe");
                            linearLayout2.setVisibility(8);
                            TextView textView = pressureDetailBottomViewHolder.mTvTip;
                            Intrinsics.checkExpressionValueIsNotNull(textView, "bottomViewHolder.mTvTip");
                            textView.setVisibility(8);
                            return;
                        }
                    }
                }
            }
            View itemView = pressureDetailBottomViewHolder.getItemView();
            Intrinsics.checkExpressionValueIsNotNull(itemView, "bottomViewHolder.itemView");
            itemView.setVisibility(0);
            LinearLayout linearLayout3 = pressureDetailBottomViewHolder.mLayPipe;
            Intrinsics.checkExpressionValueIsNotNull(linearLayout3, "bottomViewHolder.mLayPipe");
            linearLayout3.setVisibility(0);
            TextView textView2 = pressureDetailBottomViewHolder.mTvTip;
            Intrinsics.checkExpressionValueIsNotNull(textView2, "bottomViewHolder.mTvTip");
            textView2.setVisibility(0);
            TextView textView3 = pressureDetailBottomViewHolder.mTvRelaxValue;
            Intrinsics.checkExpressionValueIsNotNull(textView3, "bottomViewHolder.mTvRelaxValue");
            T mPresenter5 = getMPresenter();
            if (mPresenter5 == 0) {
                Intrinsics.throwNpe();
            }
            textView3.setText(String.valueOf(((PresureDetailPresenter) mPresenter5).getMRelaxRadio()));
            TextView textView4 = pressureDetailBottomViewHolder.mTvNormalValue;
            Intrinsics.checkExpressionValueIsNotNull(textView4, "bottomViewHolder.mTvNormalValue");
            T mPresenter6 = getMPresenter();
            if (mPresenter6 == 0) {
                Intrinsics.throwNpe();
            }
            textView4.setText(String.valueOf(((PresureDetailPresenter) mPresenter6).getMNormalRadio()));
            TextView textView5 = pressureDetailBottomViewHolder.mTvMediumValue;
            Intrinsics.checkExpressionValueIsNotNull(textView5, "bottomViewHolder.mTvMediumValue");
            T mPresenter7 = getMPresenter();
            if (mPresenter7 == 0) {
                Intrinsics.throwNpe();
            }
            textView5.setText(String.valueOf(((PresureDetailPresenter) mPresenter7).getMMediumRadio()));
            TextView textView6 = pressureDetailBottomViewHolder.mTvHighValue;
            Intrinsics.checkExpressionValueIsNotNull(textView6, "bottomViewHolder.mTvHighValue");
            T mPresenter8 = getMPresenter();
            if (mPresenter8 == 0) {
                Intrinsics.throwNpe();
            }
            textView6.setText(String.valueOf(((PresureDetailPresenter) mPresenter8).getMHigherRadio()));
            ArrayList arrayList = new ArrayList();
            if (((PresureDetailPresenter) getMPresenter()).getMNormalRadio() > 0) {
                int i = PressureEnum.NORMAL.Color;
                if (getMPresenter() == 0) {
                    Intrinsics.throwNpe();
                }
                arrayList.add(new PieChartBean(i, ((PresureDetailPresenter) r7).getMNormalRadio()));
            }
            if (((PresureDetailPresenter) getMPresenter()).getMMediumRadio() > 0) {
                int i2 = PressureEnum.MEDIUM.Color;
                if (getMPresenter() == 0) {
                    Intrinsics.throwNpe();
                }
                arrayList.add(new PieChartBean(i2, ((PresureDetailPresenter) r7).getMMediumRadio()));
            }
            if (((PresureDetailPresenter) getMPresenter()).getMHigherRadio() > 0) {
                int i3 = PressureEnum.HIGH.Color;
                if (getMPresenter() == 0) {
                    Intrinsics.throwNpe();
                }
                arrayList.add(new PieChartBean(i3, ((PresureDetailPresenter) r7).getMHigherRadio()));
            }
            if (((PresureDetailPresenter) getMPresenter()).getMRelaxRadio() > 0) {
                int i4 = PressureEnum.RELAX.Color;
                if (getMPresenter() == 0) {
                    Intrinsics.throwNpe();
                }
                arrayList.add(new PieChartBean(i4, ((PresureDetailPresenter) r7).getMRelaxRadio()));
            }
            pressureDetailBottomViewHolder.mSportPieChart.setData(arrayList);
            TextView textView7 = pressureDetailBottomViewHolder.mTvTip;
            Intrinsics.checkExpressionValueIsNotNull(textView7, "bottomViewHolder.mTvTip");
            textView7.setText("");
            int mDateType = ((PresureDetailPresenter) getMPresenter()).getMDateType();
            if (mDateType == 2) {
                TextView textView8 = pressureDetailBottomViewHolder.mTvTip;
                Intrinsics.checkExpressionValueIsNotNull(textView8, "bottomViewHolder.mTvTip");
                textView8.setVisibility(0);
                try {
                    TextView textView9 = ((PressureDetailBottomViewHolder) bottomViewHolder).mTvTip;
                    Intrinsics.checkExpressionValueIsNotNull(textView9, "bottomViewHolder.mTvTip");
                    T mPresenter9 = getMPresenter();
                    if (mPresenter9 == 0) {
                        Intrinsics.throwNpe();
                    }
                    textView9.setText(((PresureDetailPresenter) mPresenter9).getPressureTipResId(true, ((PresureDetailPresenter) getMPresenter()).getStartDayMonth(), ((PresureDetailPresenter) getMPresenter()).getEndDayMonth()));
                    return;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    TextView textView10 = pressureDetailBottomViewHolder.mTvTip;
                    Intrinsics.checkExpressionValueIsNotNull(textView10, "bottomViewHolder.mTvTip");
                    textView10.setVisibility(8);
                    return;
                }
            }
            if (mDateType == 3) {
                TextView textView11 = pressureDetailBottomViewHolder.mTvTip;
                Intrinsics.checkExpressionValueIsNotNull(textView11, "bottomViewHolder.mTvTip");
                textView11.setVisibility(0);
                try {
                    TextView textView12 = ((PressureDetailBottomViewHolder) bottomViewHolder).mTvTip;
                    Intrinsics.checkExpressionValueIsNotNull(textView12, "bottomViewHolder.mTvTip");
                    textView12.setText(((PresureDetailPresenter) getMPresenter()).getPressureTipResId(false, ((PresureDetailPresenter) getMPresenter()).getStartDayMonth(), ((PresureDetailPresenter) getMPresenter()).getEndDayMonth()));
                    return;
                } catch (Exception e3) {
                    e3.printStackTrace();
                    TextView textView13 = pressureDetailBottomViewHolder.mTvTip;
                    Intrinsics.checkExpressionValueIsNotNull(textView13, "bottomViewHolder.mTvTip");
                    textView13.setVisibility(8);
                    return;
                }
            }
            TextView textView14 = pressureDetailBottomViewHolder.mTvTip;
            Intrinsics.checkExpressionValueIsNotNull(textView14, "bottomViewHolder.mTvTip");
            textView14.setVisibility(8);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCoreFragment, com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public boolean refreshChartTipView(int index) {
        IChartDetailCallback mCallBack;
        String strValueOf;
        String strValueOf2;
        IChartDetailCallback mCallBack2;
        super.refreshChartTipView(index);
        if (index < 0) {
            return false;
        }
        IChartDetailCallback mCallBack3 = getMCallBack();
        BaseDetailViewHolder tipViewHolder = mCallBack3 != null ? mCallBack3.getTipViewHolder(this) : null;
        if (!(tipViewHolder instanceof PressureDetailTipViewHolder)) {
            return false;
        }
        PressureEnum pressureEnumByValue = PressureEnum.NORMAL;
        if (((PresureDetailPresenter) getMPresenter()).getMDateType() == 1) {
            if (isVisible() && (mCallBack = getMCallBack()) != null) {
                mCallBack.updateSelectDate(this, ((PresureDetailPresenter) getMPresenter()).getMStartDate());
            }
            PressureDetailTipViewHolder pressureDetailTipViewHolder = (PressureDetailTipViewHolder) tipViewHolder;
            pressureDetailTipViewHolder.getMTvTipTitleAvg().setVisibility(8);
            BarChartBar barChartBar = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.bar_chart);
            if (barChartBar == null) {
                Intrinsics.throwNpe();
            }
            List<T> list = barChartBar.getList();
            if (list != 0 && list.size() > index) {
                Object obj = list.get(index);
                if (obj == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.ido.life.bean.PressureBarChartPoint");
                }
                PressureBarChartPoint pressureBarChartPoint = (PressureBarChartPoint) obj;
                int iRoundToInt = MathKt.roundToInt(pressureBarChartPoint.getActualValue());
                pressureDetailTipViewHolder.getMTvTipAvg().setText(iRoundToInt > 0 ? String.valueOf(iRoundToInt) : "--");
                int startMinute = pressureBarChartPoint.getStartMinute();
                int endMinute = pressureBarChartPoint.getEndMinute();
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                Object[] objArr = {Integer.valueOf(startMinute / 60), Integer.valueOf(startMinute % 60)};
                String str = String.format("%02d:%02d", Arrays.copyOf(objArr, objArr.length));
                Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(format, *args)");
                StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                Object[] objArr2 = {Integer.valueOf(endMinute / 60), Integer.valueOf(endMinute % 60)};
                String str2 = String.format("%02d:%02d", Arrays.copyOf(objArr2, objArr2.length));
                Intrinsics.checkNotNullExpressionValue(str2, "java.lang.String.format(format, *args)");
                String str3 = str;
                if (TextUtils.equals(str3, str2)) {
                    pressureDetailTipViewHolder.getMTvTipDate().setText(str3);
                } else {
                    pressureDetailTipViewHolder.getMTvTipDate().setText(str + '-' + str2);
                }
                pressureEnumByValue = PressureEnum.getPressureEnumByValue(MathKt.roundToInt(pressureBarChartPoint.getActualValue()));
            }
        } else {
            PressureDetailTipViewHolder pressureDetailTipViewHolder2 = (PressureDetailTipViewHolder) tipViewHolder;
            pressureDetailTipViewHolder2.getMTvTipTitleAvg().setVisibility(0);
            pressureDetailTipViewHolder2.getMTvTipTitleAvg().setText(getLanguageText(R.string.detail_average_daily));
            LineChartBar lineChartBar = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            List list2 = lineChartBar != null ? lineChartBar.getList() : null;
            if (list2 != null && list2.size() > index) {
                BaseCharBean charBean = (BaseCharBean) list2.get(index);
                PresureDetailPresenter presureDetailPresenter = (PresureDetailPresenter) getMPresenter();
                Intrinsics.checkExpressionValueIsNotNull(charBean, "charBean");
                HealthPressure pressureItem = presureDetailPresenter.getPressureItem(charBean.getIndex());
                if (pressureItem != null) {
                    if (isVisible() && (mCallBack2 = getMCallBack()) != null) {
                        mCallBack2.updateSelectDate(this, pressureItem.getDate());
                    }
                    int avgPressure = pressureItem.getAvgPressure();
                    pressureDetailTipViewHolder2.getMTvTipAvg().setText(avgPressure > 0 ? String.valueOf(avgPressure) : "--");
                    String date = pressureItem.getDate();
                    PresureDetailPresenter presureDetailPresenter2 = (PresureDetailPresenter) getMPresenter();
                    Intrinsics.checkExpressionValueIsNotNull(date, "date");
                    int[] iArrConvertDateToIntArray = presureDetailPresenter2.convertDateToIntArray(date);
                    int mDateType = ((PresureDetailPresenter) getMPresenter()).getMDateType();
                    if (mDateType == 2 || mDateType == 3) {
                        if (iArrConvertDateToIntArray != null && iArrConvertDateToIntArray.length == 3) {
                            int i = iArrConvertDateToIntArray[1];
                            int i2 = iArrConvertDateToIntArray[2];
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
                            pressureDetailTipViewHolder2.getMTvTipDate().setText(strValueOf2 + IOUtils.DIR_SEPARATOR_UNIX + strValueOf);
                        }
                    } else if (mDateType == 4 && iArrConvertDateToIntArray != null && iArrConvertDateToIntArray.length == 3) {
                        int i3 = iArrConvertDateToIntArray[0];
                        int i4 = iArrConvertDateToIntArray[1];
                        TextView mTvTipDate = pressureDetailTipViewHolder2.getMTvTipDate();
                        StringCompanionObject stringCompanionObject3 = StringCompanionObject.INSTANCE;
                        Object[] objArr3 = {Integer.valueOf(i4), Integer.valueOf(i3)};
                        String str4 = String.format("%02d/%02d", Arrays.copyOf(objArr3, objArr3.length));
                        Intrinsics.checkNotNullExpressionValue(str4, "java.lang.String.format(format, *args)");
                        mTvTipDate.setText(str4);
                    }
                    pressureEnumByValue = PressureEnum.getPressureEnumByValue(pressureItem.getAvgPressure());
                }
            }
        }
        if (pressureEnumByValue != null) {
            int i5 = WhenMappings.$EnumSwitchMapping$1[pressureEnumByValue.ordinal()];
            if (i5 == 1) {
                ((PressureDetailTipViewHolder) tipViewHolder).getMTvTipState().setText("");
            } else if (i5 == 2) {
                ((PressureDetailTipViewHolder) tipViewHolder).getMTvTipState().setText(LanguageUtil.getLanguageText(R.string.home_pressure_relax));
            } else if (i5 == 3) {
                ((PressureDetailTipViewHolder) tipViewHolder).getMTvTipState().setText(LanguageUtil.getLanguageText(R.string.home_pressure_normal));
            } else if (i5 == 4) {
                ((PressureDetailTipViewHolder) tipViewHolder).getMTvTipState().setText(LanguageUtil.getLanguageText(R.string.home_pressure_middle));
            } else if (i5 == 5) {
                ((PressureDetailTipViewHolder) tipViewHolder).getMTvTipState().setText(LanguageUtil.getLanguageText(R.string.home_pressure_high));
            }
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void onLoadSuccessByDay(boolean showChartAnimator, List<? extends PressureBarChartPoint> dayChartList) {
        Intrinsics.checkParameterIsNotNull(dayChartList, "dayChartList");
        PresureDetailPresenter presureDetailPresenter = (PresureDetailPresenter) getMPresenter();
        if (presureDetailPresenter == null || presureDetailPresenter.getMDateType() != 1 || this.mRootView == null || ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.bar_chart)) == null) {
            return;
        }
        refreshTopView(getMActivity());
        refreshBottomView(getMActivity());
        PresureDetailPresenter presureDetailPresenter2 = (PresureDetailPresenter) getMPresenter();
        List<String> leftLabelList = presureDetailPresenter2 != null ? presureDetailPresenter2.getLeftLabelList() : null;
        BarChartBar barChartBar = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.bar_chart);
        if (barChartBar != null) {
            barChartBar.setLabelYLeftList(leftLabelList);
        }
        List<String> list = leftLabelList;
        if (!(list == null || list.isEmpty())) {
            BarChartBar barChartBar2 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.bar_chart);
            if (barChartBar2 != null) {
                barChartBar2.mYMinValue = Integer.parseInt(leftLabelList.get(0));
            }
            BarChartBar barChartBar3 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.bar_chart);
            if (barChartBar3 != null) {
                barChartBar3.mYMaxValue = Integer.parseInt(leftLabelList.get(leftLabelList.size() - 1));
            }
        }
        BarChartBar barChartBar4 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.bar_chart);
        if (barChartBar4 != null) {
            barChartBar4.setList(dayChartList);
        }
        BarChartBar barChartBar5 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.bar_chart);
        if (barChartBar5 != null) {
            barChartBar5.mXMinValue = 1;
        }
        BarChartBar barChartBar6 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.bar_chart);
        if (barChartBar6 != null) {
            barChartBar6.mXMaxValue = 48;
        }
        BarChartBar barChartBar7 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.bar_chart);
        if (barChartBar7 != null) {
            barChartBar7.refreshChart(showChartAnimator);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void onLoadSuccessByWeek(boolean showChartAnimator, List<? extends BaseCharBean> weekChartList) {
        PresureDetailPresenter presureDetailPresenter;
        Intrinsics.checkParameterIsNotNull(weekChartList, "weekChartList");
        if (this.mRootView == null || (presureDetailPresenter = (PresureDetailPresenter) getMPresenter()) == null || presureDetailPresenter.getMDateType() != 2 || getMPresenter() == 0 || ((LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) == null) {
            return;
        }
        refreshTopView(getMActivity());
        refreshBottomView(getMActivity());
        PresureDetailPresenter presureDetailPresenter2 = (PresureDetailPresenter) getMPresenter();
        List<String> leftLabelList = presureDetailPresenter2 != null ? presureDetailPresenter2.getLeftLabelList() : null;
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
            T mPresenter = getMPresenter();
            if (mPresenter == 0) {
                Intrinsics.throwNpe();
            }
            lineChartBar6.mXMinValue = ((PresureDetailPresenter) mPresenter).getMXMinValue();
        }
        LineChartBar lineChartBar7 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (lineChartBar7 != null) {
            T mPresenter2 = getMPresenter();
            if (mPresenter2 == 0) {
                Intrinsics.throwNpe();
            }
            lineChartBar7.mXMaxValue = ((PresureDetailPresenter) mPresenter2).getMXMaxValue();
        }
        LineChartBar lineChartBar8 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (lineChartBar8 != null) {
            lineChartBar8.refreshChart(showChartAnimator);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void onLoadSuccessByMonth(boolean showChartAnimator, List<? extends BaseCharBean> monthChartList) {
        PresureDetailPresenter presureDetailPresenter = (PresureDetailPresenter) getMPresenter();
        if (presureDetailPresenter == null || presureDetailPresenter.getMDateType() != 3 || this.mRootView == null || ((LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) == null) {
            return;
        }
        refreshTopView(getMActivity());
        refreshBottomView(getMActivity());
        PresureDetailPresenter presureDetailPresenter2 = (PresureDetailPresenter) getMPresenter();
        List<String> leftLabelList = presureDetailPresenter2 != null ? presureDetailPresenter2.getLeftLabelList() : null;
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
            if (monthChartList == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.List<kotlin.Nothing>");
            }
            lineChartBar4.setList(monthChartList);
        }
        LineChartBar lineChartBar5 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (lineChartBar5 != null) {
            lineChartBar5.mBottomTitle = (String) null;
        }
        LineChartBar lineChartBar6 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (lineChartBar6 != null) {
            T mPresenter = getMPresenter();
            if (mPresenter == 0) {
                Intrinsics.throwNpe();
            }
            lineChartBar6.mXMinValue = ((PresureDetailPresenter) mPresenter).getMXMinValue();
        }
        LineChartBar lineChartBar7 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (lineChartBar7 != null) {
            T mPresenter2 = getMPresenter();
            if (mPresenter2 == 0) {
                Intrinsics.throwNpe();
            }
            lineChartBar7.mXMaxValue = ((PresureDetailPresenter) mPresenter2).getMXMaxValue();
        }
        LineChartBar lineChartBar8 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (lineChartBar8 != null) {
            lineChartBar8.refreshChart(showChartAnimator);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void onLoadSuccessByYear(boolean showChartAnimator, List<? extends BaseCharBean> yearChartList) {
        Intrinsics.checkParameterIsNotNull(yearChartList, "yearChartList");
        PresureDetailPresenter presureDetailPresenter = (PresureDetailPresenter) getMPresenter();
        if (presureDetailPresenter == null || presureDetailPresenter.getMDateType() != 4 || this.mRootView == null || ((LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) == null) {
            return;
        }
        refreshTopView(getMActivity());
        refreshBottomView(getMActivity());
        PresureDetailPresenter presureDetailPresenter2 = (PresureDetailPresenter) getMPresenter();
        List<String> leftLabelList = presureDetailPresenter2 != null ? presureDetailPresenter2.getLeftLabelList() : null;
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
        LineChartBar lineChartBar5 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (lineChartBar5 != null) {
            lineChartBar5.mBottomTitle = (String) null;
        }
        LineChartBar lineChartBar6 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (lineChartBar6 != null) {
            T mPresenter = getMPresenter();
            if (mPresenter == 0) {
                Intrinsics.throwNpe();
            }
            lineChartBar6.mXMinValue = ((PresureDetailPresenter) mPresenter).getMXMinValue();
        }
        LineChartBar lineChartBar7 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (lineChartBar7 != null) {
            T mPresenter2 = getMPresenter();
            if (mPresenter2 == 0) {
                Intrinsics.throwNpe();
            }
            lineChartBar7.mXMaxValue = ((PresureDetailPresenter) mPresenter2).getMXMaxValue();
        }
        LineChartBar lineChartBar8 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (lineChartBar8 != null) {
            lineChartBar8.refreshChart(showChartAnimator);
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

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void onDetailLoadFailed() {
        if (getMPresenter() == 0 || ((LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) == null || ((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.bar_chart)) == null) {
            return;
        }
        T mPresenter = getMPresenter();
        if (mPresenter == 0) {
            Intrinsics.throwNpe();
        }
        List<String> leftLabelList = ((PresureDetailPresenter) mPresenter).getLeftLabelList();
        PresureDetailPresenter presureDetailPresenter = (PresureDetailPresenter) getMPresenter();
        int iIntValue = (presureDetailPresenter != null ? Integer.valueOf(presureDetailPresenter.getMDateType()) : null).intValue();
        if (iIntValue == 1) {
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
                T mPresenter2 = getMPresenter();
                if (mPresenter2 == 0) {
                    Intrinsics.throwNpe();
                }
                List<PressureBarChartPoint> dayDefaultList = ((PresureDetailPresenter) mPresenter2).getDayDefaultList();
                if (dayDefaultList == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.List<kotlin.Nothing>");
                }
                barChartBar4.setList(dayDefaultList);
            }
            BarChartBar barChartBar5 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.bar_chart);
            if (barChartBar5 != null) {
                barChartBar5.refreshChart(false);
            }
        } else if (iIntValue == 2 || iIntValue == 3) {
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
            LineChartBar lineChartBar4 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (lineChartBar4 != null) {
                lineChartBar4.mBottomTitle = (String) null;
            }
            LineChartBar lineChartBar5 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (lineChartBar5 != null) {
                lineChartBar5.refreshChart(false);
            }
        } else if (iIntValue == 4) {
            LineChartBar lineChartBar6 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (lineChartBar6 != null) {
                lineChartBar6.mBottomTitle = (String) null;
            }
            LineChartBar lineChartBar7 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (lineChartBar7 != null) {
                lineChartBar7.setLabelYLeftList(leftLabelList);
            }
            LineChartBar lineChartBar8 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (lineChartBar8 != null) {
                lineChartBar8.mYMinValue = Integer.parseInt(leftLabelList.get(0));
            }
            LineChartBar lineChartBar9 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (lineChartBar9 != null) {
                lineChartBar9.mYMaxValue = Integer.parseInt(leftLabelList.get(leftLabelList.size() - 1));
            }
            LineChartBar lineChartBar10 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (lineChartBar10 != null) {
                lineChartBar10.refreshChart(false);
            }
        }
        refreshTopView(getMActivity());
        refreshBottomView(getMActivity());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailFragment
    protected boolean showTopRightLayout() {
        return ((PresureDetailPresenter) getMPresenter()).getMDateType() == 1;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCoreFragment, com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public View getTipLayContent() {
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder tipViewHolder = mCallBack != null ? mCallBack.getTipViewHolder(this) : null;
        if (tipViewHolder instanceof PressureDetailTipViewHolder) {
            return ((PressureDetailTipViewHolder) tipViewHolder).getMLayTipContent();
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailFragment
    protected void refreshUiByDateType() {
        IChartDetailCallback mCallBack;
        LineChartBar lineChartBar;
        LineChartBar lineChartBar2;
        super.refreshUiByDateType();
        if (getMCallBack() != null) {
            IChartDetailCallback mCallBack2 = getMCallBack();
            if (mCallBack2 == null) {
                Intrinsics.throwNpe();
            }
            PresureDetailFragment presureDetailFragment = this;
            if (mCallBack2.isShow(presureDetailFragment)) {
                setMNeedRefreshPage(false);
                if (((BarChartBar) _$_findCachedViewById(com.ido.life.R.id.bar_chart)) == null) {
                    return;
                }
                refreshTopView(getMActivity());
                T mPresenter = getMPresenter();
                if (mPresenter == 0) {
                    Intrinsics.throwNpe();
                }
                List<String> leftLabelList = ((PresureDetailPresenter) mPresenter).getLeftLabelList();
                if (getMActivity() instanceof CustomChatBar.ChartClickListener) {
                    BarChartBar barChartBar = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.bar_chart);
                    KeyEventDispatcher.Component mActivity = getMActivity();
                    if (mActivity == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.ido.life.customview.charter.CustomChatBar.ChartClickListener");
                    }
                    barChartBar.setClickListener((CustomChatBar.ChartClickListener) mActivity);
                    LineChartBar lineChartBar3 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                    KeyEventDispatcher.Component mActivity2 = getMActivity();
                    if (mActivity2 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.ido.life.customview.charter.CustomChatBar.ChartClickListener");
                    }
                    lineChartBar3.setClickListener((CustomChatBar.ChartClickListener) mActivity2);
                }
                int mDateType = ((PresureDetailPresenter) getMPresenter()).getMDateType();
                if (mDateType == 1) {
                    refreshTopView(getMActivity());
                    BarChartBar barChartBar2 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.bar_chart);
                    if (barChartBar2 != null) {
                        barChartBar2.setLabelYLeftList(leftLabelList);
                    }
                    BarChartBar barChartBar3 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.bar_chart);
                    if (barChartBar3 != null) {
                        barChartBar3.setYMinValue(Integer.parseInt(leftLabelList.get(0)));
                    }
                    BarChartBar barChartBar4 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.bar_chart);
                    if (barChartBar4 != null) {
                        barChartBar4.setYMaxValue(Integer.parseInt(leftLabelList.get(leftLabelList.size() - 1)));
                    }
                    BarChartBar barChartBar5 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.bar_chart);
                    if (barChartBar5 != null) {
                        barChartBar5.setVisibility(0);
                    }
                    LineChartBar lineChartBar4 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                    if (lineChartBar4 != null) {
                        lineChartBar4.setVisibility(8);
                    }
                    BarChartBar barChartBar6 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.bar_chart);
                    if (barChartBar6 != null) {
                        T mPresenter2 = getMPresenter();
                        if (mPresenter2 == 0) {
                            Intrinsics.throwNpe();
                        }
                        barChartBar6.setLabelXList(((PresureDetailPresenter) mPresenter2).getBottomLabelList());
                    }
                    BarChartBar barChartBar7 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.bar_chart);
                    if (barChartBar7 != null) {
                        barChartBar7.refreshChart(false);
                    }
                } else if (mDateType == 2) {
                    LineChartBar lineChartBar5 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                    if (lineChartBar5 != null) {
                        lineChartBar5.setCircleStrokeSelectedColor(getResources().getColor(R.color.color_FC5D68));
                    }
                    BarChartBar barChartBar8 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.bar_chart);
                    if (barChartBar8 != null) {
                        barChartBar8.setVisibility(8);
                    }
                    LineChartBar lineChartBar6 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                    if (lineChartBar6 != null) {
                        lineChartBar6.setLabelYLeftList(leftLabelList);
                    }
                    LineChartBar lineChartBar7 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                    if (lineChartBar7 != null) {
                        lineChartBar7.setYMinValue(Integer.parseInt(leftLabelList.get(0)));
                    }
                    LineChartBar lineChartBar8 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                    if (lineChartBar8 != null) {
                        lineChartBar8.setYMaxValue(Integer.parseInt(leftLabelList.get(leftLabelList.size() - 1)));
                    }
                    LineChartBar lineChartBar9 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                    if (lineChartBar9 != null) {
                        lineChartBar9.setVisibility(0);
                    }
                    LineChartBar lineChartBar10 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                    if (lineChartBar10 != null) {
                        T mPresenter3 = getMPresenter();
                        if (mPresenter3 == 0) {
                            Intrinsics.throwNpe();
                        }
                        lineChartBar10.setLabelXList(((PresureDetailPresenter) mPresenter3).getBottomLabelList());
                    }
                    if ((getContext() instanceof CustomChatBar.ChartClickListener) && (lineChartBar = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) != null) {
                        lineChartBar.setClickListener((CustomChatBar.ChartClickListener) getContext());
                    }
                    T mPresenter4 = getMPresenter();
                    if (mPresenter4 == 0) {
                        Intrinsics.throwNpe();
                    }
                    int iYear = ((PresureDetailPresenter) mPresenter4).year();
                    if (iYear > 0) {
                        LineChartBar lineChartBar11 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                        if (lineChartBar11 != null) {
                            lineChartBar11.setBottomTitle(String.valueOf(iYear));
                        }
                    } else {
                        LineChartBar lineChartBar12 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                        if (lineChartBar12 != null) {
                            lineChartBar12.setBottomTitle(null);
                        }
                    }
                    LineChartBar lineChartBar13 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                    if (lineChartBar13 != null) {
                        lineChartBar13.refreshChart(false);
                    }
                } else if (mDateType == 3 || mDateType == 4) {
                    LineChartBar lineChartBar14 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                    if (lineChartBar14 != null) {
                        lineChartBar14.setCircleStrokeSelectedColor(getResources().getColor(R.color.color_FE9C5E));
                    }
                    BarChartBar barChartBar9 = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.bar_chart);
                    if (barChartBar9 != null) {
                        barChartBar9.setVisibility(8);
                    }
                    LineChartBar lineChartBar15 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                    if (lineChartBar15 != null) {
                        lineChartBar15.setLabelYLeftList(leftLabelList);
                    }
                    LineChartBar lineChartBar16 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                    if (lineChartBar16 != null) {
                        lineChartBar16.setYMinValue(Integer.parseInt(leftLabelList.get(0)));
                    }
                    LineChartBar lineChartBar17 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                    if (lineChartBar17 != null) {
                        lineChartBar17.setYMaxValue(Integer.parseInt(leftLabelList.get(leftLabelList.size() - 1)));
                    }
                    LineChartBar lineChartBar18 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                    if (lineChartBar18 != null) {
                        lineChartBar18.setVisibility(0);
                    }
                    LineChartBar lineChartBar19 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                    if (lineChartBar19 != null) {
                        T mPresenter5 = getMPresenter();
                        if (mPresenter5 == 0) {
                            Intrinsics.throwNpe();
                        }
                        lineChartBar19.setLabelXList(((PresureDetailPresenter) mPresenter5).getBottomLabelList());
                    }
                    if ((getContext() instanceof CustomChatBar.ChartClickListener) && (lineChartBar2 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) != null) {
                        lineChartBar2.setClickListener((CustomChatBar.ChartClickListener) getContext());
                    }
                    T mPresenter6 = getMPresenter();
                    if (mPresenter6 == 0) {
                        Intrinsics.throwNpe();
                    }
                    int iYear2 = ((PresureDetailPresenter) mPresenter6).year();
                    if (iYear2 > 0) {
                        LineChartBar lineChartBar20 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                        if (lineChartBar20 != null) {
                            lineChartBar20.setBottomTitle(String.valueOf(iYear2));
                        }
                    } else {
                        LineChartBar lineChartBar21 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                        if (lineChartBar21 != null) {
                            lineChartBar21.setBottomTitle(null);
                        }
                    }
                    LineChartBar lineChartBar22 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                    if (lineChartBar22 != null) {
                        lineChartBar22.refreshChart(false);
                    }
                }
                ((PresureDetailPresenter) getMPresenter()).getDetailData();
                if (!isVisible() || (mCallBack = getMCallBack()) == null) {
                    return;
                }
                T mPresenter7 = getMPresenter();
                if (mPresenter7 == 0) {
                    Intrinsics.throwNpe();
                }
                mCallBack.updateSelectDate(presureDetailFragment, ((PresureDetailPresenter) mPresenter7).getMStartDate());
            }
        }
    }

    @Override // com.ido.life.customview.charter.CustomChatBar.CaluteXGridLineCallback
    public float calculateXGridLineValue(View target, int index) {
        Intrinsics.checkParameterIsNotNull(target, "target");
        List<String> labelYLeftList = null;
        BarChartBar barChartBar = (BarChartBar) _$_findCachedViewById(com.ido.life.R.id.bar_chart);
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
        List<String> list = labelYLeftList;
        if ((list == null || list.isEmpty()) || labelYLeftList.size() <= index) {
            return 0.0f;
        }
        return Integer.parseInt(labelYLeftList.get(index));
    }

    /* JADX INFO: compiled from: PresureDetailFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tR\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/ido/life/module/home/pressure/detail/vertical/PresureDetailFragment$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "getInstance", "Lcom/ido/life/module/home/pressure/detail/vertical/PresureDetailFragment;", "bundle", "Landroid/os/Bundle;", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final PresureDetailFragment getInstance(Bundle bundle) {
            PresureDetailFragment presureDetailFragment = new PresureDetailFragment();
            if (bundle != null) {
                presureDetailFragment.setArguments(bundle);
            }
            return presureDetailFragment;
        }
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
            if (topViewHolder instanceof PressureDetailTopViewHolder) {
                ((PressureDetailTopViewHolder) topViewHolder).showLoadingView();
                ((PresureDetailPresenter) getMPresenter()).getDetailData();
                return;
            }
            return;
        }
        showToast(R.string.public_net_unuse);
    }
}