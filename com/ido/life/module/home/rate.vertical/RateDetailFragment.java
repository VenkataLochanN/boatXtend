package com.ido.life.module.home.rate.vertical;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.core.view.KeyEventDispatcher;
import com.boat.Xtend.two.R;
import com.ido.alexa.AlexaCustomSkillConstant;
import com.ido.common.utils.NetworkUtil;
import com.ido.life.bean.BaseCharBean;
import com.ido.life.bean.CubicChartBean;
import com.ido.life.bean.FloatBarPoint;
import com.ido.life.bean.GoalLineInfo;
import com.ido.life.customview.charter.CustomChatBar;
import com.ido.life.customview.charter.FloatBarChartBar;
import com.ido.life.customview.charter.HeartRateCubicChartBar;
import com.ido.life.customview.charter.LineChartBar;
import com.ido.life.database.model.ServerHeartRateDayData;
import com.ido.life.module.home.chartdetail.vertical.BaseDetailCoreFragment;
import com.ido.life.module.home.chartdetail.vertical.BaseDetailViewHolder;
import com.ido.life.module.home.chartdetail.vertical.IChartDetailCallback;
import com.ido.life.util.DateUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: RateDetailFragment.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u001d\u0018\u0000 =2>\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0002\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00012\u00020\u00072\u00020\b2\u00020\u0005:\u0001=B\u0005¢\u0006\u0002\u0010\tJ\u001a\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\u0010\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\rH\u0016J\b\u0010\u0014\u001a\u00020\u0011H\u0016J\u0012\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\b\u0010\u0019\u001a\u00020\u000fH\u0014J\b\u0010\u001a\u001a\u00020\u000fH\u0016J\b\u0010\u001b\u001a\u00020\u0006H\u0016J\n\u0010\u001c\u001a\u0004\u0018\u00010\rH\u0016J\u0012\u0010\u001d\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0012\u0010\u001e\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0010\u0010\u001f\u001a\u00020\u00112\u0006\u0010 \u001a\u00020!H\u0016J\b\u0010\"\u001a\u00020\u0011H\u0014J\b\u0010#\u001a\u00020!H\u0016J\b\u0010$\u001a\u00020\u0011H\u0016J\b\u0010%\u001a\u00020\u0011H\u0016J \u0010&\u001a\u00020\u00112\u0006\u0010'\u001a\u00020!2\u000e\u0010(\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002H\u0016J \u0010)\u001a\u00020\u00112\u0006\u0010'\u001a\u00020!2\u000e\u0010*\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0002H\u0016J \u0010+\u001a\u00020\u00112\u0006\u0010'\u001a\u00020!2\u000e\u0010*\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0002H\u0016J \u0010,\u001a\u00020\u00112\u0006\u0010'\u001a\u00020!2\u000e\u0010-\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0002H\u0016J\b\u0010.\u001a\u00020\u0011H\u0016J\u0012\u0010/\u001a\u00020\u00112\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0014J\b\u00100\u001a\u00020\u0011H\u0014J\u0010\u00101\u001a\u00020!2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0012\u00102\u001a\u00020\u00112\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0014J\b\u00103\u001a\u00020\u0011H\u0014J\u0010\u00104\u001a\u00020\u00112\u0006\u00105\u001a\u00020\u000fH\u0016J\u0010\u00106\u001a\u00020\u00112\u0006\u00107\u001a\u00020\u000fH\u0016J\b\u00108\u001a\u00020\u0011H\u0016J\b\u00109\u001a\u00020\u0011H\u0016J\b\u0010:\u001a\u00020\u0011H\u0016J\b\u0010;\u001a\u00020\u0011H\u0016J\b\u0010<\u001a\u00020!H\u0014¨\u0006>"}, d2 = {"Lcom/ido/life/module/home/rate/vertical/RateDetailFragment;", "Lcom/ido/life/module/home/chartdetail/vertical/BaseDetailCoreFragment;", "", "Lcom/ido/life/bean/CubicChartBean;", "Lcom/ido/life/bean/BaseCharBean;", "Lcom/ido/life/module/home/rate/vertical/IRateDetailView;", "Lcom/ido/life/module/home/rate/vertical/RateDetailPresenter;", "Lcom/ido/life/customview/charter/CustomChatBar$CaluteXGridLineCallback;", "Landroid/view/View$OnClickListener;", "()V", "calculateXGridLineValue", "", "target", "Landroid/view/View;", "index", "", "clearCache", "", "clickAction", "view", "dismissLoading", "getBottomView", "Lcom/ido/life/module/home/chartdetail/vertical/BaseDetailViewHolder;", "context", "Landroid/content/Context;", "getLayoutResId", "getPageType", "getPresenter", "getTipLayContent", "getTipViewHolder", "getTopView", "hideSelectedUi", "resetDate", "", "initView", "needEventBus", "onBottomViewRefresh", "onDetailLoadFailed", "onLoadSuccessByDay", "showChartAnimator", "day", "onLoadSuccessByMonth", "month", "onLoadSuccessByWeek", "onLoadSuccessByYear", "year", "onTopViewRefresh", "refreshBottomView", "refreshChart", "refreshChartTipView", "refreshTopView", "refreshUiByDateType", "setXMaxValue", "maxValue", "setXMinValue", "minValue", "showLoadFailedView", "showLoadSuccessView", "showLoading", "showLoadingView", "showTopRightLayout", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
public final class RateDetailFragment extends BaseDetailCoreFragment<List<? extends CubicChartBean>, List<? extends BaseCharBean>, List<? extends BaseCharBean>, List<? extends BaseCharBean>, IRateDetailView, RateDetailPresenter> implements CustomChatBar.CaluteXGridLineCallback, View.OnClickListener, IRateDetailView {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int RATE_AREA = 1;
    public static final int RATE_SLIENT = 2;
    private HashMap _$_findViewCache;

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
        return R.layout.fragment_rate_detail_layout;
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
    protected boolean showTopRightLayout() {
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailFragment, com.ido.common.base.BaseCoreFragment
    protected void initView() {
        if (getMCallBack() != null) {
            RateDetailPresenter rateDetailPresenter = (RateDetailPresenter) getMPresenter();
            IChartDetailCallback mCallBack = getMCallBack();
            if (mCallBack == null) {
                Intrinsics.throwNpe();
            }
            rateDetailPresenter.setRateType(mCallBack.getRateType(this));
        }
        super.initView();
        if (getMActivity() instanceof View.OnClickListener) {
            HeartRateCubicChartBar heartRateCubicChartBar = (HeartRateCubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
            if (heartRateCubicChartBar != null) {
                KeyEventDispatcher.Component mActivity = getMActivity();
                if (mActivity == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.ido.life.customview.charter.CustomChatBar.ChartClickListener");
                }
                heartRateCubicChartBar.setClickListener((CustomChatBar.ChartClickListener) mActivity);
            }
            FloatBarChartBar floatBarChartBar = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
            if (floatBarChartBar != null) {
                KeyEventDispatcher.Component mActivity2 = getMActivity();
                if (mActivity2 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.ido.life.customview.charter.CustomChatBar.ChartClickListener");
                }
                floatBarChartBar.setClickListener((CustomChatBar.ChartClickListener) mActivity2);
            }
            LineChartBar lineChartBar = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (lineChartBar != null) {
                KeyEventDispatcher.Component mActivity3 = getMActivity();
                if (mActivity3 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.ido.life.customview.charter.CustomChatBar.ChartClickListener");
                }
                lineChartBar.setClickListener((CustomChatBar.ChartClickListener) mActivity3);
            }
        }
        List<String> leftYLabelList = ((RateDetailPresenter) getMPresenter()).getLeftYLabelList();
        List<String> list = leftYLabelList;
        if (!(list == null || list.isEmpty())) {
            HeartRateCubicChartBar heartRateCubicChartBar2 = (HeartRateCubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
            if (heartRateCubicChartBar2 != null) {
                heartRateCubicChartBar2.setYMinValue(Integer.parseInt(leftYLabelList.get(0)));
            }
            HeartRateCubicChartBar heartRateCubicChartBar3 = (HeartRateCubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
            if (heartRateCubicChartBar3 != null) {
                heartRateCubicChartBar3.setYMaxValue(Integer.parseInt(leftYLabelList.get(leftYLabelList.size() - 1)));
            }
            FloatBarChartBar floatBarChartBar2 = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
            if (floatBarChartBar2 != null) {
                floatBarChartBar2.setYMinValue(Integer.parseInt(leftYLabelList.get(0)));
            }
            FloatBarChartBar floatBarChartBar3 = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
            if (floatBarChartBar3 != null) {
                floatBarChartBar3.setYMaxValue(Integer.parseInt(leftYLabelList.get(leftYLabelList.size() - 1)));
            }
            LineChartBar lineChartBar2 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (lineChartBar2 != null) {
                lineChartBar2.setYMinValue(Integer.parseInt(leftYLabelList.get(0)));
            }
            LineChartBar lineChartBar3 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (lineChartBar3 != null) {
                lineChartBar3.setYMaxValue(Integer.parseInt(leftYLabelList.get(leftYLabelList.size() - 1)));
            }
        }
        FloatBarChartBar floatBarChartBar4 = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
        if (floatBarChartBar4 != null) {
            floatBarChartBar4.setLabelYLeftList(leftYLabelList);
        }
        HeartRateCubicChartBar heartRateCubicChartBar4 = (HeartRateCubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
        if (heartRateCubicChartBar4 != null) {
            heartRateCubicChartBar4.setLabelYLeftList(leftYLabelList);
        }
        LineChartBar lineChartBar4 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (lineChartBar4 != null) {
            lineChartBar4.setLabelYLeftList(leftYLabelList);
        }
        FloatBarChartBar floatBarChartBar5 = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
        if (floatBarChartBar5 != null) {
            floatBarChartBar5.setXGridLineCallback(new CustomChatBar.CaluteXGridLineCallback() { // from class: com.ido.life.module.home.rate.vertical.RateDetailFragment.initView.1
                @Override // com.ido.life.customview.charter.CustomChatBar.CaluteXGridLineCallback
                public final float calculateXGridLineValue(View target, int i) {
                    Intrinsics.checkParameterIsNotNull(target, "target");
                    return RateDetailFragment.this.calculateXGridLineValue(target, i);
                }
            });
        }
        LineChartBar lineChartBar5 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (lineChartBar5 != null) {
            lineChartBar5.setXGridLineCallback(new CustomChatBar.CaluteXGridLineCallback() { // from class: com.ido.life.module.home.rate.vertical.RateDetailFragment.initView.2
                @Override // com.ido.life.customview.charter.CustomChatBar.CaluteXGridLineCallback
                public final float calculateXGridLineValue(View target, int i) {
                    Intrinsics.checkParameterIsNotNull(target, "target");
                    return RateDetailFragment.this.calculateXGridLineValue(target, i);
                }
            });
        }
        HeartRateCubicChartBar heartRateCubicChartBar5 = (HeartRateCubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
        if (heartRateCubicChartBar5 != null) {
            heartRateCubicChartBar5.setXGridLineCallback(new CustomChatBar.CaluteXGridLineCallback() { // from class: com.ido.life.module.home.rate.vertical.RateDetailFragment.initView.3
                @Override // com.ido.life.customview.charter.CustomChatBar.CaluteXGridLineCallback
                public final float calculateXGridLineValue(View target, int i) {
                    Intrinsics.checkParameterIsNotNull(target, "target");
                    return RateDetailFragment.this.calculateXGridLineValue(target, i);
                }
            });
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public BaseDetailViewHolder getTipViewHolder(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.detail_chart_tip_rate_layout, (ViewGroup) null);
        Intrinsics.checkExpressionValueIsNotNull(viewInflate, "LayoutInflater.from(cont…rt_tip_rate_layout, null)");
        return new RateDetailTipViewHolder(viewInflate);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public BaseDetailViewHolder getTopView(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        return new RateDetailTopViewHolder(LayoutInflater.from(context).inflate(R.layout.detail_top_rate_layout, (ViewGroup) null));
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCoreFragment, com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public BaseDetailViewHolder getBottomView(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        return new RateDetailBottomViewHolder(LayoutInflater.from(context).inflate(R.layout.detail_bottom_rate_layout, (ViewGroup) null));
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
        if (((FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart)) == null || ((LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) == null) {
            return;
        }
        refreshTopView(getMActivity());
        refreshBottomView(getMActivity());
        if (((RateDetailPresenter) getMPresenter()).getMDateType() == 4) {
            FloatBarChartBar floatBarChartBar = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
            if (floatBarChartBar == null) {
                Intrinsics.throwNpe();
            }
            floatBarChartBar.setBottomTitle(null);
            LineChartBar lineChartBar = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (lineChartBar == null) {
                Intrinsics.throwNpe();
            }
            lineChartBar.setBottomTitle(null);
            int iYear = ((RateDetailPresenter) getMPresenter()).year();
            if (iYear > 0) {
                IChartDetailCallback mCallBack = getMCallBack();
                if (mCallBack != null && mCallBack.getRateType(this) == 2) {
                    LineChartBar lineChartBar2 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                    if (lineChartBar2 == null) {
                        Intrinsics.throwNpe();
                    }
                    lineChartBar2.setBottomTitle(String.valueOf(iYear));
                } else {
                    FloatBarChartBar floatBarChartBar2 = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
                    if (floatBarChartBar2 == null) {
                        Intrinsics.throwNpe();
                    }
                    floatBarChartBar2.setBottomTitle(String.valueOf(iYear));
                }
            }
            FloatBarChartBar floatBarChartBar3 = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
            if (floatBarChartBar3 == null) {
                Intrinsics.throwNpe();
            }
            floatBarChartBar3.setXMinValue(((RateDetailPresenter) getMPresenter()).getMXMinValue());
            FloatBarChartBar floatBarChartBar4 = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
            if (floatBarChartBar4 == null) {
                Intrinsics.throwNpe();
            }
            floatBarChartBar4.setXMaxValue(((RateDetailPresenter) getMPresenter()).getMXMaxValue());
            LineChartBar lineChartBar3 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (lineChartBar3 == null) {
                Intrinsics.throwNpe();
            }
            lineChartBar3.setXMinValue(((RateDetailPresenter) getMPresenter()).getMXMinValue());
            LineChartBar lineChartBar4 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (lineChartBar4 == null) {
                Intrinsics.throwNpe();
            }
            lineChartBar4.setXMaxValue(((RateDetailPresenter) getMPresenter()).getMXMaxValue());
            FloatBarChartBar floatBarChartBar5 = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
            if (floatBarChartBar5 == null) {
                Intrinsics.throwNpe();
            }
            floatBarChartBar5.refreshChart(false);
            LineChartBar lineChartBar5 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (lineChartBar5 == null) {
                Intrinsics.throwNpe();
            }
            lineChartBar5.refreshChart(false);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void onLoadSuccessByDay(boolean showChartAnimator, List<? extends CubicChartBean> day) {
        if (((HeartRateCubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart)) == null || ((RateDetailPresenter) getMPresenter()).getMDateType() != 1) {
            return;
        }
        HeartRateCubicChartBar heartRateCubicChartBar = (HeartRateCubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
        if (heartRateCubicChartBar == null) {
            Intrinsics.throwNpe();
        }
        heartRateCubicChartBar.clearList();
        T mPresenter = getMPresenter();
        if (mPresenter == 0) {
            Intrinsics.throwNpe();
        }
        ServerHeartRateDayData dayHeartRate = ((RateDetailPresenter) mPresenter).getDayHeartRate();
        refreshTopView(getMActivity());
        refreshBottomView(getMActivity());
        IChartDetailCallback mCallBack = getMCallBack();
        if (mCallBack != null && mCallBack.getRateType(this) == 2 && dayHeartRate != null && dayHeartRate.getSilentValue() >= 20 && dayHeartRate.getSilentValue() <= 220) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new GoalLineInfo(0, dayHeartRate.getSilentValue(), dayHeartRate.getSilentValue(), ""));
            HeartRateCubicChartBar heartRateCubicChartBar2 = (HeartRateCubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
            if (heartRateCubicChartBar2 == null) {
                Intrinsics.throwNpe();
            }
            heartRateCubicChartBar2.setGoalLineList(arrayList);
        } else {
            HeartRateCubicChartBar heartRateCubicChartBar3 = (HeartRateCubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
            if (heartRateCubicChartBar3 == null) {
                Intrinsics.throwNpe();
            }
            heartRateCubicChartBar3.setGoalLineList(null);
        }
        T mPresenter2 = getMPresenter();
        if (mPresenter2 == 0) {
            Intrinsics.throwNpe();
        }
        List<String> leftYLabelList = ((RateDetailPresenter) mPresenter2).getLeftYLabelList();
        HeartRateCubicChartBar heartRateCubicChartBar4 = (HeartRateCubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
        if (heartRateCubicChartBar4 == null) {
            Intrinsics.throwNpe();
        }
        heartRateCubicChartBar4.setLabelYLeftList(leftYLabelList);
        List<String> list = leftYLabelList;
        if (!(list == null || list.isEmpty())) {
            HeartRateCubicChartBar heartRateCubicChartBar5 = (HeartRateCubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
            if (heartRateCubicChartBar5 == null) {
                Intrinsics.throwNpe();
            }
            heartRateCubicChartBar5.setYMinValue(Integer.parseInt(leftYLabelList.get(0)));
            HeartRateCubicChartBar heartRateCubicChartBar6 = (HeartRateCubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
            if (heartRateCubicChartBar6 == null) {
                Intrinsics.throwNpe();
            }
            heartRateCubicChartBar6.setYMaxValue(Integer.parseInt(leftYLabelList.get(leftYLabelList.size() - 1)));
        }
        HeartRateCubicChartBar heartRateCubicChartBar7 = (HeartRateCubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
        if (heartRateCubicChartBar7 == null) {
            Intrinsics.throwNpe();
        }
        T mPresenter3 = getMPresenter();
        if (mPresenter3 == 0) {
            Intrinsics.throwNpe();
        }
        heartRateCubicChartBar7.setList(((RateDetailPresenter) mPresenter3).getDayChartList());
        HeartRateCubicChartBar heartRateCubicChartBar8 = (HeartRateCubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
        if (heartRateCubicChartBar8 == null) {
            Intrinsics.throwNpe();
        }
        heartRateCubicChartBar8.setXMinValue(1);
        HeartRateCubicChartBar heartRateCubicChartBar9 = (HeartRateCubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
        if (heartRateCubicChartBar9 == null) {
            Intrinsics.throwNpe();
        }
        heartRateCubicChartBar9.setXMaxValue(288);
        HeartRateCubicChartBar heartRateCubicChartBar10 = (HeartRateCubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
        if (heartRateCubicChartBar10 == null) {
            Intrinsics.throwNpe();
        }
        heartRateCubicChartBar10.refreshChart(true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void onLoadSuccessByWeek(boolean showChartAnimator, List<? extends BaseCharBean> month) {
        if (((FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart)) == null || ((LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) == null || ((RateDetailPresenter) getMPresenter()).getMDateType() != 2) {
            return;
        }
        T mPresenter = getMPresenter();
        if (mPresenter == 0) {
            Intrinsics.throwNpe();
        }
        List<BaseCharBean> weekChartList = ((RateDetailPresenter) mPresenter).getWeekChartList();
        refreshBottomView(getMActivity());
        refreshTopView(getMActivity());
        List<BaseCharBean> list = weekChartList;
        if (list == null || list.isEmpty()) {
            return;
        }
        BaseCharBean baseCharBean = weekChartList.get(0);
        IChartDetailCallback mCallBack = getMCallBack();
        if (mCallBack != null && mCallBack.getRateType(this) == 1 && ((RateDetailPresenter) getMPresenter()).getMDateType() != 1 && (baseCharBean instanceof FloatBarPoint)) {
            FloatBarChartBar floatBarChartBar = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
            if (floatBarChartBar == null) {
                Intrinsics.throwNpe();
            }
            floatBarChartBar.setList(weekChartList);
            FloatBarChartBar floatBarChartBar2 = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
            if (floatBarChartBar2 == null) {
                Intrinsics.throwNpe();
            }
            floatBarChartBar2.setBottomTitle(null);
            FloatBarChartBar floatBarChartBar3 = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
            if (floatBarChartBar3 == null) {
                Intrinsics.throwNpe();
            }
            T mPresenter2 = getMPresenter();
            if (mPresenter2 == 0) {
                Intrinsics.throwNpe();
            }
            floatBarChartBar3.setXMinValue(((RateDetailPresenter) mPresenter2).getMXMinValue());
            FloatBarChartBar floatBarChartBar4 = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
            if (floatBarChartBar4 == null) {
                Intrinsics.throwNpe();
            }
            T mPresenter3 = getMPresenter();
            if (mPresenter3 == 0) {
                Intrinsics.throwNpe();
            }
            floatBarChartBar4.setXMaxValue(((RateDetailPresenter) mPresenter3).getMXMaxValue());
            T mPresenter4 = getMPresenter();
            if (mPresenter4 == 0) {
                Intrinsics.throwNpe();
            }
            List<String> leftYLabelList = ((RateDetailPresenter) mPresenter4).getLeftYLabelList();
            List<String> list2 = leftYLabelList;
            if (!(list2 == null || list2.isEmpty())) {
                FloatBarChartBar floatBarChartBar5 = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
                if (floatBarChartBar5 == null) {
                    Intrinsics.throwNpe();
                }
                floatBarChartBar5.setYMinValue(Integer.parseInt(leftYLabelList.get(0)));
                FloatBarChartBar floatBarChartBar6 = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
                if (floatBarChartBar6 == null) {
                    Intrinsics.throwNpe();
                }
                floatBarChartBar6.setYMaxValue(Integer.parseInt(leftYLabelList.get(leftYLabelList.size() - 1)));
            }
            FloatBarChartBar floatBarChartBar7 = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
            if (floatBarChartBar7 == null) {
                Intrinsics.throwNpe();
            }
            floatBarChartBar7.setLabelYLeftList(leftYLabelList);
            FloatBarChartBar floatBarChartBar8 = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
            if (floatBarChartBar8 == null) {
                Intrinsics.throwNpe();
            }
            floatBarChartBar8.refreshChart(true);
            return;
        }
        IChartDetailCallback mCallBack2 = getMCallBack();
        if (mCallBack2 == null || mCallBack2.getRateType(this) != 2 || ((RateDetailPresenter) getMPresenter()).getMDateType() == 1) {
            return;
        }
        LineChartBar lineChartBar = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (lineChartBar == null) {
            Intrinsics.throwNpe();
        }
        lineChartBar.setList(weekChartList);
        LineChartBar lineChartBar2 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (lineChartBar2 == null) {
            Intrinsics.throwNpe();
        }
        lineChartBar2.setBottomTitle(null);
        LineChartBar lineChartBar3 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (lineChartBar3 == null) {
            Intrinsics.throwNpe();
        }
        T mPresenter5 = getMPresenter();
        if (mPresenter5 == 0) {
            Intrinsics.throwNpe();
        }
        lineChartBar3.setXMinValue(((RateDetailPresenter) mPresenter5).getMXMinValue());
        LineChartBar lineChartBar4 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (lineChartBar4 == null) {
            Intrinsics.throwNpe();
        }
        T mPresenter6 = getMPresenter();
        if (mPresenter6 == 0) {
            Intrinsics.throwNpe();
        }
        lineChartBar4.setXMaxValue(((RateDetailPresenter) mPresenter6).getMXMaxValue());
        T mPresenter7 = getMPresenter();
        if (mPresenter7 == 0) {
            Intrinsics.throwNpe();
        }
        List<String> leftYLabelList2 = ((RateDetailPresenter) mPresenter7).getLeftYLabelList();
        LineChartBar lineChartBar5 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (lineChartBar5 == null) {
            Intrinsics.throwNpe();
        }
        lineChartBar5.setLabelYLeftList(leftYLabelList2);
        LineChartBar lineChartBar6 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (lineChartBar6 == null) {
            Intrinsics.throwNpe();
        }
        lineChartBar6.setYMinValue(Integer.parseInt(leftYLabelList2.get(0)));
        LineChartBar lineChartBar7 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (lineChartBar7 == null) {
            Intrinsics.throwNpe();
        }
        lineChartBar7.setYMaxValue(Integer.parseInt(leftYLabelList2.get(leftYLabelList2.size() - 1)));
        LineChartBar lineChartBar8 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (lineChartBar8 == null) {
            Intrinsics.throwNpe();
        }
        lineChartBar8.refreshChart(true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void onLoadSuccessByMonth(boolean showChartAnimator, List<? extends BaseCharBean> month) {
        if (((FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart)) == null || ((LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) == null || ((RateDetailPresenter) getMPresenter()).getMDateType() != 3) {
            return;
        }
        refreshTopView(getMActivity());
        refreshBottomView(getMActivity());
        T mPresenter = getMPresenter();
        if (mPresenter == 0) {
            Intrinsics.throwNpe();
        }
        List<BaseCharBean> monthChartList = ((RateDetailPresenter) mPresenter).getMonthChartList();
        List<BaseCharBean> list = monthChartList;
        if (list == null || list.isEmpty()) {
            return;
        }
        BaseCharBean baseCharBean = monthChartList.get(0);
        IChartDetailCallback mCallBack = getMCallBack();
        if (mCallBack != null && mCallBack.getRateType(this) == 1 && ((RateDetailPresenter) getMPresenter()).getMDateType() != 1 && (baseCharBean instanceof FloatBarPoint)) {
            FloatBarChartBar floatBarChartBar = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
            if (floatBarChartBar == null) {
                Intrinsics.throwNpe();
            }
            floatBarChartBar.setList(monthChartList);
            FloatBarChartBar floatBarChartBar2 = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
            if (floatBarChartBar2 == null) {
                Intrinsics.throwNpe();
            }
            floatBarChartBar2.setBottomTitle(null);
            FloatBarChartBar floatBarChartBar3 = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
            if (floatBarChartBar3 == null) {
                Intrinsics.throwNpe();
            }
            T mPresenter2 = getMPresenter();
            if (mPresenter2 == 0) {
                Intrinsics.throwNpe();
            }
            floatBarChartBar3.setXMinValue(((RateDetailPresenter) mPresenter2).getMXMinValue());
            FloatBarChartBar floatBarChartBar4 = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
            if (floatBarChartBar4 == null) {
                Intrinsics.throwNpe();
            }
            T mPresenter3 = getMPresenter();
            if (mPresenter3 == 0) {
                Intrinsics.throwNpe();
            }
            floatBarChartBar4.setXMaxValue(((RateDetailPresenter) mPresenter3).getMXMaxValue());
            T mPresenter4 = getMPresenter();
            if (mPresenter4 == 0) {
                Intrinsics.throwNpe();
            }
            List<String> leftYLabelList = ((RateDetailPresenter) mPresenter4).getLeftYLabelList();
            FloatBarChartBar floatBarChartBar5 = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
            if (floatBarChartBar5 == null) {
                Intrinsics.throwNpe();
            }
            floatBarChartBar5.setLabelYLeftList(leftYLabelList);
            List<String> list2 = leftYLabelList;
            if (!(list2 == null || list2.isEmpty())) {
                FloatBarChartBar floatBarChartBar6 = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
                if (floatBarChartBar6 == null) {
                    Intrinsics.throwNpe();
                }
                floatBarChartBar6.setYMinValue(Integer.parseInt(leftYLabelList.get(0)));
                FloatBarChartBar floatBarChartBar7 = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
                if (floatBarChartBar7 == null) {
                    Intrinsics.throwNpe();
                }
                floatBarChartBar7.setYMaxValue(Integer.parseInt(leftYLabelList.get(leftYLabelList.size() - 1)));
            }
            FloatBarChartBar floatBarChartBar8 = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
            if (floatBarChartBar8 == null) {
                Intrinsics.throwNpe();
            }
            floatBarChartBar8.refreshChart(true);
            return;
        }
        IChartDetailCallback mCallBack2 = getMCallBack();
        if (mCallBack2 == null || mCallBack2.getRateType(this) != 2 || ((RateDetailPresenter) getMPresenter()).getMDateType() == 1) {
            return;
        }
        LineChartBar lineChartBar = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (lineChartBar == null) {
            Intrinsics.throwNpe();
        }
        lineChartBar.setList(monthChartList);
        LineChartBar lineChartBar2 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (lineChartBar2 == null) {
            Intrinsics.throwNpe();
        }
        lineChartBar2.setBottomTitle(null);
        LineChartBar lineChartBar3 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (lineChartBar3 == null) {
            Intrinsics.throwNpe();
        }
        T mPresenter5 = getMPresenter();
        if (mPresenter5 == 0) {
            Intrinsics.throwNpe();
        }
        lineChartBar3.setXMinValue(((RateDetailPresenter) mPresenter5).getMXMinValue());
        LineChartBar lineChartBar4 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (lineChartBar4 == null) {
            Intrinsics.throwNpe();
        }
        T mPresenter6 = getMPresenter();
        if (mPresenter6 == 0) {
            Intrinsics.throwNpe();
        }
        lineChartBar4.setXMaxValue(((RateDetailPresenter) mPresenter6).getMXMaxValue());
        T mPresenter7 = getMPresenter();
        if (mPresenter7 == 0) {
            Intrinsics.throwNpe();
        }
        List<String> leftYLabelList2 = ((RateDetailPresenter) mPresenter7).getLeftYLabelList();
        LineChartBar lineChartBar5 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (lineChartBar5 == null) {
            Intrinsics.throwNpe();
        }
        lineChartBar5.setLabelYLeftList(leftYLabelList2);
        List<String> list3 = leftYLabelList2;
        if (!(list3 == null || list3.isEmpty())) {
            LineChartBar lineChartBar6 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (lineChartBar6 == null) {
                Intrinsics.throwNpe();
            }
            lineChartBar6.setYMinValue(Integer.parseInt(leftYLabelList2.get(0)));
            LineChartBar lineChartBar7 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (lineChartBar7 == null) {
                Intrinsics.throwNpe();
            }
            lineChartBar7.setYMaxValue(Integer.parseInt(leftYLabelList2.get(leftYLabelList2.size() - 1)));
        }
        LineChartBar lineChartBar8 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (lineChartBar8 == null) {
            Intrinsics.throwNpe();
        }
        lineChartBar8.refreshChart(true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void onLoadSuccessByYear(boolean showChartAnimator, List<? extends BaseCharBean> year) {
        if (((LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) == null || ((FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart)) == null || ((RateDetailPresenter) getMPresenter()).getMDateType() != 4) {
            return;
        }
        refreshBottomView(getMActivity());
        refreshTopView(getMActivity());
        T mPresenter = getMPresenter();
        if (mPresenter == 0) {
            Intrinsics.throwNpe();
        }
        List<BaseCharBean> yearChartList = ((RateDetailPresenter) mPresenter).getYearChartList();
        List<BaseCharBean> list = yearChartList;
        if (list == null || list.isEmpty()) {
            return;
        }
        BaseCharBean baseCharBean = yearChartList.get(0);
        IChartDetailCallback mCallBack = getMCallBack();
        if (mCallBack != null && mCallBack.getRateType(this) == 1 && ((RateDetailPresenter) getMPresenter()).getMDateType() != 1 && (baseCharBean instanceof FloatBarPoint)) {
            FloatBarChartBar floatBarChartBar = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
            if (floatBarChartBar == null) {
                Intrinsics.throwNpe();
            }
            floatBarChartBar.setList(yearChartList);
            FloatBarChartBar floatBarChartBar2 = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
            if (floatBarChartBar2 == null) {
                Intrinsics.throwNpe();
            }
            floatBarChartBar2.setBottomTitle(null);
            T mPresenter2 = getMPresenter();
            if (mPresenter2 == 0) {
                Intrinsics.throwNpe();
            }
            int iYear = ((RateDetailPresenter) mPresenter2).year();
            if (iYear > 0) {
                FloatBarChartBar floatBarChartBar3 = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
                if (floatBarChartBar3 == null) {
                    Intrinsics.throwNpe();
                }
                floatBarChartBar3.setBottomTitle(String.valueOf(iYear));
            }
            FloatBarChartBar floatBarChartBar4 = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
            if (floatBarChartBar4 == null) {
                Intrinsics.throwNpe();
            }
            T mPresenter3 = getMPresenter();
            if (mPresenter3 == 0) {
                Intrinsics.throwNpe();
            }
            floatBarChartBar4.setXMinValue(((RateDetailPresenter) mPresenter3).getMXMinValue());
            FloatBarChartBar floatBarChartBar5 = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
            if (floatBarChartBar5 == null) {
                Intrinsics.throwNpe();
            }
            T mPresenter4 = getMPresenter();
            if (mPresenter4 == 0) {
                Intrinsics.throwNpe();
            }
            floatBarChartBar5.setXMaxValue(((RateDetailPresenter) mPresenter4).getMXMaxValue());
            T mPresenter5 = getMPresenter();
            if (mPresenter5 == 0) {
                Intrinsics.throwNpe();
            }
            List<String> leftYLabelList = ((RateDetailPresenter) mPresenter5).getLeftYLabelList();
            if (!(leftYLabelList == null || leftYLabelList.isEmpty())) {
                FloatBarChartBar floatBarChartBar6 = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
                if (floatBarChartBar6 == null) {
                    Intrinsics.throwNpe();
                }
                floatBarChartBar6.setYMinValue(Integer.parseInt(r6.get(0)));
                FloatBarChartBar floatBarChartBar7 = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
                if (floatBarChartBar7 == null) {
                    Intrinsics.throwNpe();
                }
                floatBarChartBar7.setYMaxValue(Integer.parseInt(r6.get(r6.size() - 1)));
            }
            FloatBarChartBar floatBarChartBar8 = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
            if (floatBarChartBar8 == null) {
                Intrinsics.throwNpe();
            }
            floatBarChartBar8.refreshChart(true);
            return;
        }
        IChartDetailCallback mCallBack2 = getMCallBack();
        if (mCallBack2 == null || mCallBack2.getRateType(this) != 2 || ((RateDetailPresenter) getMPresenter()).getMDateType() == 1) {
            return;
        }
        LineChartBar lineChartBar = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (lineChartBar == null) {
            Intrinsics.throwNpe();
        }
        lineChartBar.setList(yearChartList);
        LineChartBar lineChartBar2 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (lineChartBar2 == null) {
            Intrinsics.throwNpe();
        }
        lineChartBar2.setBottomTitle(null);
        T mPresenter6 = getMPresenter();
        if (mPresenter6 == 0) {
            Intrinsics.throwNpe();
        }
        int iYear2 = ((RateDetailPresenter) mPresenter6).year();
        if (iYear2 > 0) {
            LineChartBar lineChartBar3 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (lineChartBar3 == null) {
                Intrinsics.throwNpe();
            }
            lineChartBar3.setBottomTitle(String.valueOf(iYear2));
        }
        LineChartBar lineChartBar4 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (lineChartBar4 == null) {
            Intrinsics.throwNpe();
        }
        T mPresenter7 = getMPresenter();
        if (mPresenter7 == 0) {
            Intrinsics.throwNpe();
        }
        lineChartBar4.setXMinValue(((RateDetailPresenter) mPresenter7).getMXMinValue());
        LineChartBar lineChartBar5 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (lineChartBar5 == null) {
            Intrinsics.throwNpe();
        }
        T mPresenter8 = getMPresenter();
        if (mPresenter8 == 0) {
            Intrinsics.throwNpe();
        }
        lineChartBar5.setXMaxValue(((RateDetailPresenter) mPresenter8).getMXMaxValue());
        T mPresenter9 = getMPresenter();
        if (mPresenter9 == 0) {
            Intrinsics.throwNpe();
        }
        List<String> leftYLabelList2 = ((RateDetailPresenter) mPresenter9).getLeftYLabelList();
        if (!(leftYLabelList2 == null || leftYLabelList2.isEmpty())) {
            LineChartBar lineChartBar6 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (lineChartBar6 == null) {
                Intrinsics.throwNpe();
            }
            lineChartBar6.setYMinValue(Integer.parseInt(r6.get(0)));
            LineChartBar lineChartBar7 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (lineChartBar7 == null) {
                Intrinsics.throwNpe();
            }
            lineChartBar7.setYMaxValue(Integer.parseInt(r6.get(r6.size() - 1)));
        }
        LineChartBar lineChartBar8 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (lineChartBar8 == null) {
            Intrinsics.throwNpe();
        }
        lineChartBar8.refreshChart(true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void setXMaxValue(int maxValue) {
        IChartDetailCallback mCallBack = getMCallBack();
        Integer numValueOf = mCallBack != null ? Integer.valueOf(mCallBack.getRateType(this)) : null;
        if (numValueOf != null && numValueOf.intValue() == 1) {
            if (((RateDetailPresenter) getMPresenter()).getMDateType() == 1) {
                HeartRateCubicChartBar heartRateCubicChartBar = (HeartRateCubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
                if (heartRateCubicChartBar != null) {
                    heartRateCubicChartBar.setXMaxValue(288);
                    return;
                }
                return;
            }
            FloatBarChartBar floatBarChartBar = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
            if (floatBarChartBar != null) {
                floatBarChartBar.setXMaxValue(maxValue);
                return;
            }
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == 2) {
            if (((RateDetailPresenter) getMPresenter()).getMDateType() == 1) {
                HeartRateCubicChartBar heartRateCubicChartBar2 = (HeartRateCubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
                if (heartRateCubicChartBar2 != null) {
                    heartRateCubicChartBar2.setXMaxValue(288);
                    return;
                }
                return;
            }
            LineChartBar lineChartBar = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (lineChartBar != null) {
                lineChartBar.setXMaxValue(maxValue);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void setXMinValue(int minValue) {
        IChartDetailCallback mCallBack = getMCallBack();
        Integer numValueOf = mCallBack != null ? Integer.valueOf(mCallBack.getRateType(this)) : null;
        if (numValueOf != null && numValueOf.intValue() == 1) {
            if (((RateDetailPresenter) getMPresenter()).getMDateType() == 1) {
                HeartRateCubicChartBar heartRateCubicChartBar = (HeartRateCubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
                if (heartRateCubicChartBar != null) {
                    heartRateCubicChartBar.setXMinValue(1);
                    return;
                }
                return;
            }
            FloatBarChartBar floatBarChartBar = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
            if (floatBarChartBar != null) {
                floatBarChartBar.setXMinValue(minValue);
                return;
            }
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == 2) {
            if (((RateDetailPresenter) getMPresenter()).getMDateType() == 1) {
                HeartRateCubicChartBar heartRateCubicChartBar2 = (HeartRateCubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
                if (heartRateCubicChartBar2 != null) {
                    heartRateCubicChartBar2.setXMinValue(1);
                    return;
                }
                return;
            }
            LineChartBar lineChartBar = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            if (lineChartBar != null) {
                lineChartBar.setXMinValue(minValue);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public void hideSelectedUi(boolean resetDate) {
        HeartRateCubicChartBar heartRateCubicChartBar;
        HeartRateCubicChartBar heartRateCubicChartBar2;
        LineChartBar lineChartBar;
        LineChartBar lineChartBar2;
        FloatBarChartBar floatBarChartBar;
        FloatBarChartBar floatBarChartBar2;
        IChartDetailCallback mCallBack;
        if (resetDate && (mCallBack = getMCallBack()) != null) {
            RateDetailFragment rateDetailFragment = this;
            T mPresenter = getMPresenter();
            if (mPresenter == 0) {
                Intrinsics.throwNpe();
            }
            mCallBack.updateSelectDate(rateDetailFragment, ((RateDetailPresenter) mPresenter).getMStartDate());
        }
        if (((FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart)) != null && (floatBarChartBar = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart)) != null && floatBarChartBar.getVisibility() == 0 && (floatBarChartBar2 = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart)) != null) {
            floatBarChartBar2.refreshChart(false);
        }
        if (((LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) != null && (lineChartBar = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) != null && lineChartBar.getVisibility() == 0 && (lineChartBar2 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) != null) {
            lineChartBar2.refreshChart(false);
        }
        if (((HeartRateCubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart)) == null || (heartRateCubicChartBar = (HeartRateCubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart)) == null || heartRateCubicChartBar.getVisibility() != 0 || (heartRateCubicChartBar2 = (HeartRateCubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart)) == null) {
            return;
        }
        heartRateCubicChartBar2.refreshChart(false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCoreFragment
    protected void refreshBottomView(Context context) {
        super.refreshBottomView(context);
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder bottomViewHolder = mCallBack != null ? mCallBack.getBottomViewHolder(this) : null;
        if (bottomViewHolder instanceof RateDetailBottomViewHolder) {
            bottomViewHolder.setDefaultValue();
            RateDetailBottomViewHolder rateDetailBottomViewHolder = (RateDetailBottomViewHolder) bottomViewHolder;
            IChartDetailCallback mCallBack2 = getMCallBack();
            if (mCallBack2 == null) {
                Intrinsics.throwNpe();
            }
            rateDetailBottomViewHolder.updateSelectStatus(mCallBack2.getRateType(this) == 1);
            T mPresenter = getMPresenter();
            if (mPresenter == 0) {
                Intrinsics.throwNpe();
            }
            String rateArea = ((RateDetailPresenter) mPresenter).getRateArea();
            T mPresenter2 = getMPresenter();
            if (mPresenter2 == 0) {
                Intrinsics.throwNpe();
            }
            String showSilentRate = ((RateDetailPresenter) mPresenter2).getShowSilentRate();
            String str = rateArea;
            if (!TextUtils.isEmpty(str)) {
                TextView textView = rateDetailBottomViewHolder.mTvLeftRate;
                Intrinsics.checkExpressionValueIsNotNull(textView, "bottomViewHolder.mTvLeftRate");
                textView.setText(str);
            }
            String str2 = showSilentRate;
            if (!TextUtils.isEmpty(str2) && !AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE.contentEquals(str2)) {
                TextView textView2 = rateDetailBottomViewHolder.mTvRightRate;
                Intrinsics.checkExpressionValueIsNotNull(textView2, "bottomViewHolder.mTvRightRate");
                textView2.setText(str2);
            }
            if (context instanceof View.OnClickListener) {
                View.OnClickListener onClickListener = (View.OnClickListener) context;
                rateDetailBottomViewHolder.mLeftCard.setOnClickListener(onClickListener);
                rateDetailBottomViewHolder.mRightCard.setOnClickListener(onClickListener);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailFragment
    protected void refreshUiByDateType() {
        LineChartBar lineChartBar;
        int iCaluteAvgHeartRate;
        FloatBarChartBar floatBarChartBar;
        super.refreshUiByDateType();
        if (getMCallBack() != null) {
            IChartDetailCallback mCallBack = getMCallBack();
            if (mCallBack == null) {
                Intrinsics.throwNpe();
            }
            RateDetailFragment rateDetailFragment = this;
            if (mCallBack.isShow(rateDetailFragment)) {
                IChartDetailCallback mCallBack2 = getMCallBack();
                BaseDetailViewHolder topViewHolder = mCallBack2 != null ? mCallBack2.getTopViewHolder(rateDetailFragment) : null;
                if (topViewHolder instanceof RateDetailTopViewHolder) {
                    refreshBottomView(getMActivity());
                    refreshTopView(getMActivity());
                    FloatBarChartBar floatBarChartBar2 = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
                    if (floatBarChartBar2 != null) {
                        floatBarChartBar2.setBottomTitle(null);
                        Unit unit = Unit.INSTANCE;
                    }
                    LineChartBar lineChartBar2 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                    if (lineChartBar2 != null) {
                        lineChartBar2.setBottomTitle(null);
                        Unit unit2 = Unit.INSTANCE;
                    }
                    HeartRateCubicChartBar heartRateCubicChartBar = (HeartRateCubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
                    if (heartRateCubicChartBar != null) {
                        heartRateCubicChartBar.setBottomTitle(null);
                        Unit unit3 = Unit.INSTANCE;
                    }
                    RateDetailPresenter rateDetailPresenter = (RateDetailPresenter) getMPresenter();
                    List<String> leftYLabelList = rateDetailPresenter != null ? rateDetailPresenter.getLeftYLabelList() : null;
                    IChartDetailCallback mCallBack3 = getMCallBack();
                    Integer numValueOf = mCallBack3 != null ? Integer.valueOf(mCallBack3.getRateType(rateDetailFragment)) : null;
                    if (numValueOf != null && numValueOf.intValue() == 1) {
                        int mDateType = ((RateDetailPresenter) getMPresenter()).getMDateType();
                        if (mDateType == 1) {
                            FloatBarChartBar floatBarChartBar3 = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
                            if (floatBarChartBar3 != null) {
                                floatBarChartBar3.setVisibility(8);
                            }
                            LineChartBar lineChartBar3 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                            if (lineChartBar3 != null) {
                                lineChartBar3.setVisibility(8);
                            }
                            HeartRateCubicChartBar heartRateCubicChartBar2 = (HeartRateCubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
                            if (heartRateCubicChartBar2 != null) {
                                heartRateCubicChartBar2.setVisibility(0);
                            }
                            HeartRateCubicChartBar heartRateCubicChartBar3 = (HeartRateCubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
                            if (heartRateCubicChartBar3 != null) {
                                heartRateCubicChartBar3.setBottomLabelCenter(false);
                                Unit unit4 = Unit.INSTANCE;
                            }
                            List<String> list = leftYLabelList;
                            if (!(list == null || list.isEmpty())) {
                                HeartRateCubicChartBar heartRateCubicChartBar4 = (HeartRateCubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
                                if (heartRateCubicChartBar4 != null) {
                                    heartRateCubicChartBar4.setYMinValue(Integer.parseInt(leftYLabelList.get(0)));
                                    Unit unit5 = Unit.INSTANCE;
                                }
                                HeartRateCubicChartBar heartRateCubicChartBar5 = (HeartRateCubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
                                if (heartRateCubicChartBar5 != null) {
                                    heartRateCubicChartBar5.setYMaxValue(Integer.parseInt(leftYLabelList.get(leftYLabelList.size() - 1)));
                                    Unit unit6 = Unit.INSTANCE;
                                }
                            }
                            HeartRateCubicChartBar heartRateCubicChartBar6 = (HeartRateCubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
                            if (heartRateCubicChartBar6 != null) {
                                heartRateCubicChartBar6.setLabelYLeftList(leftYLabelList);
                            }
                            HeartRateCubicChartBar heartRateCubicChartBar7 = (HeartRateCubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
                            if (heartRateCubicChartBar7 != null) {
                                RateDetailPresenter rateDetailPresenter2 = (RateDetailPresenter) getMPresenter();
                                heartRateCubicChartBar7.setLabelXList(rateDetailPresenter2 != null ? rateDetailPresenter2.getBottomLabelList() : null);
                                Unit unit7 = Unit.INSTANCE;
                            }
                            HeartRateCubicChartBar heartRateCubicChartBar8 = (HeartRateCubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
                            if (heartRateCubicChartBar8 != null) {
                                heartRateCubicChartBar8.setGoalLineList(null);
                                Unit unit8 = Unit.INSTANCE;
                            }
                            HeartRateCubicChartBar heartRateCubicChartBar9 = (HeartRateCubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
                            if (heartRateCubicChartBar9 != null) {
                                heartRateCubicChartBar9.setBottomTitle(null);
                                Unit unit9 = Unit.INSTANCE;
                            }
                            HeartRateCubicChartBar heartRateCubicChartBar10 = (HeartRateCubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
                            if (heartRateCubicChartBar10 != null) {
                                heartRateCubicChartBar10.setLineColor(getResources().getColor(R.color.color_F44452));
                                Unit unit10 = Unit.INSTANCE;
                            }
                            HeartRateCubicChartBar heartRateCubicChartBar11 = (HeartRateCubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
                            if (heartRateCubicChartBar11 != null) {
                                heartRateCubicChartBar11.setRateLineColor(getResources().getColor(R.color.color_F44452));
                                Unit unit11 = Unit.INSTANCE;
                            }
                            HeartRateCubicChartBar heartRateCubicChartBar12 = (HeartRateCubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
                            if (heartRateCubicChartBar12 != null) {
                                heartRateCubicChartBar12.setGradientStartColor(getResources().getColor(R.color.color_FC4B69));
                                Unit unit12 = Unit.INSTANCE;
                            }
                            HeartRateCubicChartBar heartRateCubicChartBar13 = (HeartRateCubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
                            if (heartRateCubicChartBar13 != null) {
                                heartRateCubicChartBar13.setGradientEndColor(getResources().getColor(R.color.white));
                                Unit unit13 = Unit.INSTANCE;
                            }
                            HeartRateCubicChartBar heartRateCubicChartBar14 = (HeartRateCubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
                            if (heartRateCubicChartBar14 != null) {
                                heartRateCubicChartBar14.setCircleColor(getResources().getColor(R.color.color_FC4B69));
                                Unit unit14 = Unit.INSTANCE;
                            }
                            HeartRateCubicChartBar heartRateCubicChartBar15 = (HeartRateCubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
                            if (heartRateCubicChartBar15 != null) {
                                heartRateCubicChartBar15.clearList();
                                Unit unit15 = Unit.INSTANCE;
                            }
                            HeartRateCubicChartBar heartRateCubicChartBar16 = (HeartRateCubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
                            if (heartRateCubicChartBar16 != null) {
                                heartRateCubicChartBar16.refreshChart(false);
                                Unit unit16 = Unit.INSTANCE;
                            }
                        } else if (mDateType == 2) {
                            LineChartBar lineChartBar4 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                            if (lineChartBar4 != null) {
                                lineChartBar4.setVisibility(8);
                            }
                            HeartRateCubicChartBar heartRateCubicChartBar17 = (HeartRateCubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
                            if (heartRateCubicChartBar17 != null) {
                                heartRateCubicChartBar17.setVisibility(8);
                            }
                            FloatBarChartBar floatBarChartBar4 = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
                            if (floatBarChartBar4 != null) {
                                floatBarChartBar4.setVisibility(0);
                            }
                            FloatBarChartBar floatBarChartBar5 = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
                            if (floatBarChartBar5 != null) {
                                floatBarChartBar5.setBottomLabelCenter(true);
                                Unit unit17 = Unit.INSTANCE;
                            }
                            List<String> list2 = leftYLabelList;
                            if (!(list2 == null || list2.isEmpty())) {
                                FloatBarChartBar floatBarChartBar6 = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
                                if (floatBarChartBar6 != null) {
                                    floatBarChartBar6.setYMinValue(Integer.parseInt(leftYLabelList.get(0)));
                                    Unit unit18 = Unit.INSTANCE;
                                }
                                FloatBarChartBar floatBarChartBar7 = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
                                if (floatBarChartBar7 != null) {
                                    floatBarChartBar7.setYMaxValue(Integer.parseInt(leftYLabelList.get(leftYLabelList.size() - 1)));
                                    Unit unit19 = Unit.INSTANCE;
                                }
                            }
                            FloatBarChartBar floatBarChartBar8 = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
                            if (floatBarChartBar8 != null) {
                                floatBarChartBar8.setLabelYLeftList(leftYLabelList);
                            }
                            FloatBarChartBar floatBarChartBar9 = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
                            if (floatBarChartBar9 != null) {
                                T mPresenter = getMPresenter();
                                if (mPresenter == 0) {
                                    Intrinsics.throwNpe();
                                }
                                floatBarChartBar9.setLabelXList(((RateDetailPresenter) mPresenter).getBottomLabelList());
                                Unit unit20 = Unit.INSTANCE;
                            }
                            FloatBarChartBar floatBarChartBar10 = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
                            if (floatBarChartBar10 != null) {
                                floatBarChartBar10.setLineColor(getResources().getColor(R.color.color_F44452));
                                Unit unit21 = Unit.INSTANCE;
                            }
                            FloatBarChartBar floatBarChartBar11 = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
                            if (floatBarChartBar11 != null) {
                                floatBarChartBar11.setBarWidth(getResources().getDimensionPixelSize(R.dimen.sw_dp_4));
                                Unit unit22 = Unit.INSTANCE;
                            }
                            FloatBarChartBar floatBarChartBar12 = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
                            if (floatBarChartBar12 != null) {
                                floatBarChartBar12.setBottomTitle(null);
                                Unit unit23 = Unit.INSTANCE;
                            }
                            FloatBarChartBar floatBarChartBar13 = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
                            if (floatBarChartBar13 != null) {
                                floatBarChartBar13.clearList();
                                Unit unit24 = Unit.INSTANCE;
                            }
                            FloatBarChartBar floatBarChartBar14 = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
                            if (floatBarChartBar14 != null) {
                                floatBarChartBar14.refreshChart(false);
                                Unit unit25 = Unit.INSTANCE;
                            }
                        } else if (mDateType == 3) {
                            LineChartBar lineChartBar5 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                            if (lineChartBar5 != null) {
                                lineChartBar5.setVisibility(8);
                            }
                            HeartRateCubicChartBar heartRateCubicChartBar18 = (HeartRateCubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
                            if (heartRateCubicChartBar18 != null) {
                                heartRateCubicChartBar18.setVisibility(8);
                            }
                            FloatBarChartBar floatBarChartBar15 = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
                            if (floatBarChartBar15 != null) {
                                floatBarChartBar15.setVisibility(0);
                            }
                            FloatBarChartBar floatBarChartBar16 = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
                            if (floatBarChartBar16 != null) {
                                floatBarChartBar16.setBottomLabelCenter(false);
                                Unit unit26 = Unit.INSTANCE;
                            }
                            List<String> list3 = leftYLabelList;
                            if (!(list3 == null || list3.isEmpty())) {
                                FloatBarChartBar floatBarChartBar17 = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
                                if (floatBarChartBar17 != null) {
                                    floatBarChartBar17.setYMinValue(Integer.parseInt(leftYLabelList.get(0)));
                                    Unit unit27 = Unit.INSTANCE;
                                }
                                FloatBarChartBar floatBarChartBar18 = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
                                if (floatBarChartBar18 != null) {
                                    floatBarChartBar18.setYMaxValue(Integer.parseInt(leftYLabelList.get(leftYLabelList.size() - 1)));
                                    Unit unit28 = Unit.INSTANCE;
                                }
                            }
                            FloatBarChartBar floatBarChartBar19 = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
                            if (floatBarChartBar19 != null) {
                                floatBarChartBar19.setLabelYLeftList(leftYLabelList);
                            }
                            FloatBarChartBar floatBarChartBar20 = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
                            if (floatBarChartBar20 != null) {
                                T mPresenter2 = getMPresenter();
                                if (mPresenter2 == 0) {
                                    Intrinsics.throwNpe();
                                }
                                floatBarChartBar20.setLabelXList(((RateDetailPresenter) mPresenter2).getBottomLabelList());
                                Unit unit29 = Unit.INSTANCE;
                            }
                            FloatBarChartBar floatBarChartBar21 = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
                            if (floatBarChartBar21 != null) {
                                floatBarChartBar21.setLineColor(getResources().getColor(R.color.color_F44452));
                                Unit unit30 = Unit.INSTANCE;
                            }
                            FloatBarChartBar floatBarChartBar22 = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
                            if (floatBarChartBar22 != null) {
                                floatBarChartBar22.setGoalLineList(null);
                                Unit unit31 = Unit.INSTANCE;
                            }
                            FloatBarChartBar floatBarChartBar23 = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
                            if (floatBarChartBar23 != null) {
                                floatBarChartBar23.setBarWidth(getResources().getDimensionPixelSize(R.dimen.sw_dp_3));
                                Unit unit32 = Unit.INSTANCE;
                            }
                            FloatBarChartBar floatBarChartBar24 = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
                            if (floatBarChartBar24 != null) {
                                floatBarChartBar24.setBottomTitle(null);
                                Unit unit33 = Unit.INSTANCE;
                            }
                            FloatBarChartBar floatBarChartBar25 = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
                            if (floatBarChartBar25 != null) {
                                floatBarChartBar25.clearList();
                                Unit unit34 = Unit.INSTANCE;
                            }
                            FloatBarChartBar floatBarChartBar26 = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
                            if (floatBarChartBar26 != null) {
                                floatBarChartBar26.refreshChart(false);
                                Unit unit35 = Unit.INSTANCE;
                            }
                        } else if (mDateType == 4) {
                            HeartRateCubicChartBar heartRateCubicChartBar19 = (HeartRateCubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
                            if (heartRateCubicChartBar19 != null) {
                                heartRateCubicChartBar19.setVisibility(8);
                            }
                            LineChartBar lineChartBar6 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                            if (lineChartBar6 != null) {
                                lineChartBar6.setVisibility(8);
                            }
                            FloatBarChartBar floatBarChartBar27 = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
                            if (floatBarChartBar27 != null) {
                                floatBarChartBar27.setVisibility(0);
                            }
                            FloatBarChartBar floatBarChartBar28 = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
                            if (floatBarChartBar28 != null) {
                                floatBarChartBar28.setBottomLabelCenter(true);
                                Unit unit36 = Unit.INSTANCE;
                            }
                            List<String> list4 = leftYLabelList;
                            if (!(list4 == null || list4.isEmpty())) {
                                FloatBarChartBar floatBarChartBar29 = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
                                if (floatBarChartBar29 != null) {
                                    floatBarChartBar29.setYMinValue(Integer.parseInt(leftYLabelList.get(0)));
                                    Unit unit37 = Unit.INSTANCE;
                                }
                                FloatBarChartBar floatBarChartBar30 = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
                                if (floatBarChartBar30 != null) {
                                    floatBarChartBar30.setYMaxValue(Integer.parseInt(leftYLabelList.get(leftYLabelList.size() - 1)));
                                    Unit unit38 = Unit.INSTANCE;
                                }
                            }
                            FloatBarChartBar floatBarChartBar31 = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
                            if (floatBarChartBar31 != null) {
                                floatBarChartBar31.setLabelYLeftList(leftYLabelList);
                            }
                            FloatBarChartBar floatBarChartBar32 = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
                            if (floatBarChartBar32 != null) {
                                T mPresenter3 = getMPresenter();
                                if (mPresenter3 == 0) {
                                    Intrinsics.throwNpe();
                                }
                                floatBarChartBar32.setLabelXList(((RateDetailPresenter) mPresenter3).getBottomLabelList());
                                Unit unit39 = Unit.INSTANCE;
                            }
                            FloatBarChartBar floatBarChartBar33 = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
                            if (floatBarChartBar33 != null) {
                                floatBarChartBar33.setLineColor(getResources().getColor(R.color.color_F44452));
                                Unit unit40 = Unit.INSTANCE;
                            }
                            FloatBarChartBar floatBarChartBar34 = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
                            if (floatBarChartBar34 != null) {
                                floatBarChartBar34.setBarWidth(getResources().getDimensionPixelSize(R.dimen.sw_dp_4));
                                Unit unit41 = Unit.INSTANCE;
                            }
                            FloatBarChartBar floatBarChartBar35 = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
                            if (floatBarChartBar35 != null) {
                                floatBarChartBar35.clearList();
                                Unit unit42 = Unit.INSTANCE;
                            }
                            T mPresenter4 = getMPresenter();
                            if (mPresenter4 == 0) {
                                Intrinsics.throwNpe();
                            }
                            int iYear = ((RateDetailPresenter) mPresenter4).year();
                            if (iYear > 0 && (floatBarChartBar = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart)) != null) {
                                floatBarChartBar.setBottomTitle(String.valueOf(iYear));
                                Unit unit43 = Unit.INSTANCE;
                            }
                            FloatBarChartBar floatBarChartBar36 = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
                            if (floatBarChartBar36 != null) {
                                floatBarChartBar36.refreshChart(false);
                                Unit unit44 = Unit.INSTANCE;
                            }
                        }
                    } else {
                        int mDateType2 = ((RateDetailPresenter) getMPresenter()).getMDateType();
                        if (mDateType2 == 1) {
                            FloatBarChartBar floatBarChartBar37 = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
                            if (floatBarChartBar37 != null) {
                                floatBarChartBar37.setVisibility(8);
                            }
                            LineChartBar lineChartBar7 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                            if (lineChartBar7 != null) {
                                lineChartBar7.setVisibility(8);
                            }
                            HeartRateCubicChartBar heartRateCubicChartBar20 = (HeartRateCubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
                            if (heartRateCubicChartBar20 != null) {
                                heartRateCubicChartBar20.setVisibility(0);
                            }
                            List<String> list5 = leftYLabelList;
                            if (!(list5 == null || list5.isEmpty())) {
                                HeartRateCubicChartBar heartRateCubicChartBar21 = (HeartRateCubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
                                if (heartRateCubicChartBar21 != null) {
                                    heartRateCubicChartBar21.setYMinValue(Integer.parseInt(leftYLabelList.get(0)));
                                    Unit unit45 = Unit.INSTANCE;
                                }
                                HeartRateCubicChartBar heartRateCubicChartBar22 = (HeartRateCubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
                                if (heartRateCubicChartBar22 != null) {
                                    heartRateCubicChartBar22.setYMaxValue(Integer.parseInt(leftYLabelList.get(leftYLabelList.size() - 1)));
                                    Unit unit46 = Unit.INSTANCE;
                                }
                            }
                            HeartRateCubicChartBar heartRateCubicChartBar23 = (HeartRateCubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
                            if (heartRateCubicChartBar23 != null) {
                                heartRateCubicChartBar23.setLabelYLeftList(leftYLabelList);
                            }
                            HeartRateCubicChartBar heartRateCubicChartBar24 = (HeartRateCubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
                            if (heartRateCubicChartBar24 != null) {
                                heartRateCubicChartBar24.setBottomLabelCenter(false);
                                Unit unit47 = Unit.INSTANCE;
                            }
                            HeartRateCubicChartBar heartRateCubicChartBar25 = (HeartRateCubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
                            if (heartRateCubicChartBar25 != null) {
                                T mPresenter5 = getMPresenter();
                                if (mPresenter5 == 0) {
                                    Intrinsics.throwNpe();
                                }
                                heartRateCubicChartBar25.setLabelXList(((RateDetailPresenter) mPresenter5).getBottomLabelList());
                                Unit unit48 = Unit.INSTANCE;
                            }
                            HeartRateCubicChartBar heartRateCubicChartBar26 = (HeartRateCubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
                            if (heartRateCubicChartBar26 != null) {
                                heartRateCubicChartBar26.setLineColor(getResources().getColor(R.color.color_F5F5F9));
                                Unit unit49 = Unit.INSTANCE;
                            }
                            HeartRateCubicChartBar heartRateCubicChartBar27 = (HeartRateCubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
                            if (heartRateCubicChartBar27 != null) {
                                heartRateCubicChartBar27.setRateLineColor(getResources().getColor(R.color.color_F5F5F9));
                                Unit unit50 = Unit.INSTANCE;
                            }
                            HeartRateCubicChartBar heartRateCubicChartBar28 = (HeartRateCubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
                            if (heartRateCubicChartBar28 != null) {
                                heartRateCubicChartBar28.setGradientStartColor(getResources().getColor(R.color.color_F5F5F9));
                                Unit unit51 = Unit.INSTANCE;
                            }
                            HeartRateCubicChartBar heartRateCubicChartBar29 = (HeartRateCubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
                            if (heartRateCubicChartBar29 != null) {
                                heartRateCubicChartBar29.setGradientEndColor(getResources().getColor(R.color.color_F5F5F9));
                                Unit unit52 = Unit.INSTANCE;
                            }
                            HeartRateCubicChartBar heartRateCubicChartBar30 = (HeartRateCubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
                            if (heartRateCubicChartBar30 != null) {
                                heartRateCubicChartBar30.setCircleColor(getResources().getColor(R.color.color_F5F5F9));
                                Unit unit53 = Unit.INSTANCE;
                            }
                            HeartRateCubicChartBar heartRateCubicChartBar31 = (HeartRateCubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
                            if (heartRateCubicChartBar31 != null) {
                                heartRateCubicChartBar31.setGoalLineColor(getResources().getColor(R.color.color_FC4B69));
                                Unit unit54 = Unit.INSTANCE;
                            }
                            HeartRateCubicChartBar heartRateCubicChartBar32 = (HeartRateCubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
                            if (heartRateCubicChartBar32 != null) {
                                heartRateCubicChartBar32.setGoalLineList(null);
                                Unit unit55 = Unit.INSTANCE;
                            }
                            HeartRateCubicChartBar heartRateCubicChartBar33 = (HeartRateCubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
                            if (heartRateCubicChartBar33 != null) {
                                heartRateCubicChartBar33.clearList();
                                Unit unit56 = Unit.INSTANCE;
                            }
                            HeartRateCubicChartBar heartRateCubicChartBar34 = (HeartRateCubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
                            if (heartRateCubicChartBar34 != null) {
                                heartRateCubicChartBar34.refreshChart(false);
                                Unit unit57 = Unit.INSTANCE;
                            }
                        } else if (mDateType2 == 2) {
                            FloatBarChartBar floatBarChartBar38 = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
                            if (floatBarChartBar38 != null) {
                                floatBarChartBar38.setVisibility(8);
                            }
                            HeartRateCubicChartBar heartRateCubicChartBar35 = (HeartRateCubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
                            if (heartRateCubicChartBar35 != null) {
                                heartRateCubicChartBar35.setVisibility(8);
                            }
                            LineChartBar lineChartBar8 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                            if (lineChartBar8 != null) {
                                lineChartBar8.setVisibility(0);
                            }
                            List<String> list6 = leftYLabelList;
                            if (!(list6 == null || list6.isEmpty())) {
                                LineChartBar lineChartBar9 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                                if (lineChartBar9 != null) {
                                    lineChartBar9.setYMinValue(Integer.parseInt(leftYLabelList.get(0)));
                                    Unit unit58 = Unit.INSTANCE;
                                }
                                LineChartBar lineChartBar10 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                                if (lineChartBar10 != null) {
                                    lineChartBar10.setYMaxValue(Integer.parseInt(leftYLabelList.get(leftYLabelList.size() - 1)));
                                    Unit unit59 = Unit.INSTANCE;
                                }
                            }
                            LineChartBar lineChartBar11 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                            if (lineChartBar11 != null) {
                                lineChartBar11.setLabelYLeftList(leftYLabelList);
                            }
                            LineChartBar lineChartBar12 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                            if (lineChartBar12 != null) {
                                lineChartBar12.setBottomLabelCenter(true);
                                Unit unit60 = Unit.INSTANCE;
                            }
                            LineChartBar lineChartBar13 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                            if (lineChartBar13 != null) {
                                T mPresenter6 = getMPresenter();
                                if (mPresenter6 == 0) {
                                    Intrinsics.throwNpe();
                                }
                                lineChartBar13.setLabelXList(((RateDetailPresenter) mPresenter6).getBottomLabelList());
                                Unit unit61 = Unit.INSTANCE;
                            }
                            LineChartBar lineChartBar14 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                            if (lineChartBar14 != null) {
                                lineChartBar14.setLineColor(getResources().getColor(R.color.color_F44451));
                                Unit unit62 = Unit.INSTANCE;
                            }
                            LineChartBar lineChartBar15 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                            if (lineChartBar15 != null) {
                                lineChartBar15.setCircleRadius(getResources().getDimensionPixelSize(R.dimen.sw_dp_3));
                                Unit unit63 = Unit.INSTANCE;
                            }
                            LineChartBar lineChartBar16 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                            if (lineChartBar16 != null) {
                                lineChartBar16.setCircleBorderWidth(getResources().getDimensionPixelSize(R.dimen.sw_dp_2));
                                Unit unit64 = Unit.INSTANCE;
                            }
                            LineChartBar lineChartBar17 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                            if (lineChartBar17 != null) {
                                lineChartBar17.setCircleColor(getResources().getColor(R.color.white));
                                Unit unit65 = Unit.INSTANCE;
                            }
                            LineChartBar lineChartBar18 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                            if (lineChartBar18 != null) {
                                lineChartBar18.setCircleSelectedColor(getResources().getColor(R.color.white));
                                Unit unit66 = Unit.INSTANCE;
                            }
                            LineChartBar lineChartBar19 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                            if (lineChartBar19 != null) {
                                lineChartBar19.setCircleStrokeColor(getResources().getColor(R.color.color_F44451));
                                Unit unit67 = Unit.INSTANCE;
                            }
                            LineChartBar lineChartBar20 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                            if (lineChartBar20 != null) {
                                lineChartBar20.setCircleStrokeSelectedColor(getResources().getColor(R.color.color_F44451));
                                Unit unit68 = Unit.INSTANCE;
                            }
                            LineChartBar lineChartBar21 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                            if (lineChartBar21 != null) {
                                lineChartBar21.clearList();
                                Unit unit69 = Unit.INSTANCE;
                            }
                            LineChartBar lineChartBar22 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                            if (lineChartBar22 != null) {
                                lineChartBar22.refreshChart(false);
                                Unit unit70 = Unit.INSTANCE;
                            }
                        } else if (mDateType2 == 3) {
                            FloatBarChartBar floatBarChartBar39 = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
                            if (floatBarChartBar39 != null) {
                                floatBarChartBar39.setVisibility(8);
                            }
                            HeartRateCubicChartBar heartRateCubicChartBar36 = (HeartRateCubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
                            if (heartRateCubicChartBar36 != null) {
                                heartRateCubicChartBar36.setVisibility(8);
                            }
                            LineChartBar lineChartBar23 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                            if (lineChartBar23 != null) {
                                lineChartBar23.setVisibility(0);
                            }
                            List<String> list7 = leftYLabelList;
                            if (!(list7 == null || list7.isEmpty())) {
                                LineChartBar lineChartBar24 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                                if (lineChartBar24 != null) {
                                    lineChartBar24.setYMinValue(Integer.parseInt(leftYLabelList.get(0)));
                                    Unit unit71 = Unit.INSTANCE;
                                }
                                LineChartBar lineChartBar25 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                                if (lineChartBar25 != null) {
                                    lineChartBar25.setYMaxValue(Integer.parseInt(leftYLabelList.get(leftYLabelList.size() - 1)));
                                    Unit unit72 = Unit.INSTANCE;
                                }
                            }
                            LineChartBar lineChartBar26 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                            if (lineChartBar26 != null) {
                                lineChartBar26.setLabelYLeftList(leftYLabelList);
                            }
                            LineChartBar lineChartBar27 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                            if (lineChartBar27 != null) {
                                lineChartBar27.setBottomLabelCenter(false);
                                Unit unit73 = Unit.INSTANCE;
                            }
                            LineChartBar lineChartBar28 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                            if (lineChartBar28 != null) {
                                T mPresenter7 = getMPresenter();
                                if (mPresenter7 == 0) {
                                    Intrinsics.throwNpe();
                                }
                                lineChartBar28.setLabelXList(((RateDetailPresenter) mPresenter7).getBottomLabelList());
                                Unit unit74 = Unit.INSTANCE;
                            }
                            LineChartBar lineChartBar29 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                            if (lineChartBar29 != null) {
                                lineChartBar29.setLineColor(getResources().getColor(R.color.color_F44451));
                                Unit unit75 = Unit.INSTANCE;
                            }
                            LineChartBar lineChartBar30 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                            if (lineChartBar30 != null) {
                                lineChartBar30.setCircleRadius(getResources().getDimensionPixelSize(R.dimen.sw_dp_3));
                                Unit unit76 = Unit.INSTANCE;
                            }
                            LineChartBar lineChartBar31 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                            if (lineChartBar31 != null) {
                                lineChartBar31.setCircleBorderWidth(getResources().getDimensionPixelSize(R.dimen.sw_dp_2));
                                Unit unit77 = Unit.INSTANCE;
                            }
                            LineChartBar lineChartBar32 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                            if (lineChartBar32 != null) {
                                lineChartBar32.setCircleColor(getResources().getColor(R.color.white));
                                Unit unit78 = Unit.INSTANCE;
                            }
                            LineChartBar lineChartBar33 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                            if (lineChartBar33 != null) {
                                lineChartBar33.setCircleSelectedColor(getResources().getColor(R.color.white));
                                Unit unit79 = Unit.INSTANCE;
                            }
                            LineChartBar lineChartBar34 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                            if (lineChartBar34 != null) {
                                lineChartBar34.setCircleStrokeColor(getResources().getColor(R.color.color_F44451));
                                Unit unit80 = Unit.INSTANCE;
                            }
                            LineChartBar lineChartBar35 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                            if (lineChartBar35 != null) {
                                lineChartBar35.setCircleStrokeSelectedColor(getResources().getColor(R.color.color_F44451));
                                Unit unit81 = Unit.INSTANCE;
                            }
                            LineChartBar lineChartBar36 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                            if (lineChartBar36 != null) {
                                lineChartBar36.clearList();
                                Unit unit82 = Unit.INSTANCE;
                            }
                            LineChartBar lineChartBar37 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                            if (lineChartBar37 != null) {
                                lineChartBar37.refreshChart(false);
                                Unit unit83 = Unit.INSTANCE;
                            }
                        } else if (mDateType2 == 4) {
                            FloatBarChartBar floatBarChartBar40 = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
                            if (floatBarChartBar40 != null) {
                                floatBarChartBar40.setVisibility(8);
                            }
                            HeartRateCubicChartBar heartRateCubicChartBar37 = (HeartRateCubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
                            if (heartRateCubicChartBar37 != null) {
                                heartRateCubicChartBar37.setVisibility(8);
                            }
                            LineChartBar lineChartBar38 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                            if (lineChartBar38 != null) {
                                lineChartBar38.setVisibility(0);
                            }
                            List<String> list8 = leftYLabelList;
                            if (!(list8 == null || list8.isEmpty())) {
                                LineChartBar lineChartBar39 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                                if (lineChartBar39 != null) {
                                    lineChartBar39.setYMinValue(Integer.parseInt(leftYLabelList.get(0)));
                                    Unit unit84 = Unit.INSTANCE;
                                }
                                LineChartBar lineChartBar40 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                                if (lineChartBar40 != null) {
                                    lineChartBar40.setYMaxValue(Integer.parseInt(leftYLabelList.get(leftYLabelList.size() - 1)));
                                    Unit unit85 = Unit.INSTANCE;
                                }
                            }
                            LineChartBar lineChartBar41 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                            if (lineChartBar41 != null) {
                                lineChartBar41.setLabelYLeftList(leftYLabelList);
                            }
                            LineChartBar lineChartBar42 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                            if (lineChartBar42 != null) {
                                lineChartBar42.setBottomLabelCenter(true);
                                Unit unit86 = Unit.INSTANCE;
                            }
                            LineChartBar lineChartBar43 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                            if (lineChartBar43 != null) {
                                T mPresenter8 = getMPresenter();
                                if (mPresenter8 == 0) {
                                    Intrinsics.throwNpe();
                                }
                                lineChartBar43.setLabelXList(((RateDetailPresenter) mPresenter8).getBottomLabelList());
                                Unit unit87 = Unit.INSTANCE;
                            }
                            LineChartBar lineChartBar44 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                            if (lineChartBar44 != null) {
                                lineChartBar44.setLineColor(getResources().getColor(R.color.color_F44451));
                                Unit unit88 = Unit.INSTANCE;
                            }
                            LineChartBar lineChartBar45 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                            if (lineChartBar45 != null) {
                                lineChartBar45.setCircleRadius(getResources().getDimensionPixelSize(R.dimen.sw_dp_3));
                                Unit unit89 = Unit.INSTANCE;
                            }
                            LineChartBar lineChartBar46 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                            if (lineChartBar46 != null) {
                                lineChartBar46.setCircleBorderWidth(getResources().getDimensionPixelSize(R.dimen.sw_dp_2));
                                Unit unit90 = Unit.INSTANCE;
                            }
                            LineChartBar lineChartBar47 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                            if (lineChartBar47 != null) {
                                lineChartBar47.setCircleColor(getResources().getColor(R.color.white));
                                Unit unit91 = Unit.INSTANCE;
                            }
                            LineChartBar lineChartBar48 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                            if (lineChartBar48 != null) {
                                lineChartBar48.setCircleSelectedColor(getResources().getColor(R.color.white));
                                Unit unit92 = Unit.INSTANCE;
                            }
                            LineChartBar lineChartBar49 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                            if (lineChartBar49 != null) {
                                lineChartBar49.setCircleStrokeColor(getResources().getColor(R.color.color_F44451));
                                Unit unit93 = Unit.INSTANCE;
                            }
                            LineChartBar lineChartBar50 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                            if (lineChartBar50 != null) {
                                lineChartBar50.setCircleStrokeSelectedColor(getResources().getColor(R.color.color_F44451));
                                Unit unit94 = Unit.INSTANCE;
                            }
                            T mPresenter9 = getMPresenter();
                            if (mPresenter9 == 0) {
                                Intrinsics.throwNpe();
                            }
                            int iYear2 = ((RateDetailPresenter) mPresenter9).year();
                            if (iYear2 > 0 && (lineChartBar = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) != null) {
                                lineChartBar.setBottomTitle(String.valueOf(iYear2));
                                Unit unit95 = Unit.INSTANCE;
                            }
                            LineChartBar lineChartBar51 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                            if (lineChartBar51 != null) {
                                lineChartBar51.clearList();
                                Unit unit96 = Unit.INSTANCE;
                            }
                            LineChartBar lineChartBar52 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                            if (lineChartBar52 != null) {
                                lineChartBar52.refreshChart(false);
                                Unit unit97 = Unit.INSTANCE;
                            }
                        }
                    }
                    refreshTopView(getMActivity());
                    IChartDetailCallback mCallBack4 = getMCallBack();
                    if (mCallBack4 != null && mCallBack4.getRateType(rateDetailFragment) == 2) {
                        T mPresenter10 = getMPresenter();
                        if (mPresenter10 == 0) {
                            Intrinsics.throwNpe();
                        }
                        iCaluteAvgHeartRate = ((RateDetailPresenter) mPresenter10).caluteAvgSilentHeartRate();
                    } else {
                        T mPresenter11 = getMPresenter();
                        if (mPresenter11 == 0) {
                            Intrinsics.throwNpe();
                        }
                        iCaluteAvgHeartRate = ((RateDetailPresenter) mPresenter11).caluteAvgHeartRate();
                    }
                    if (iCaluteAvgHeartRate > 0) {
                        TextView textView = ((RateDetailTopViewHolder) topViewHolder).mTvAvg;
                        Intrinsics.checkExpressionValueIsNotNull(textView, "topViewHolder.mTvAvg");
                        textView.setText(String.valueOf(iCaluteAvgHeartRate));
                    }
                    TextView textView2 = ((RateDetailTopViewHolder) topViewHolder).mTvDate;
                    Intrinsics.checkExpressionValueIsNotNull(textView2, "topViewHolder.mTvDate");
                    T mPresenter12 = getMPresenter();
                    if (mPresenter12 == 0) {
                        Intrinsics.throwNpe();
                    }
                    textView2.setText(((RateDetailPresenter) mPresenter12).getDateText());
                    ((RateDetailPresenter) getMPresenter()).getDetailData();
                    IChartDetailCallback mCallBack5 = getMCallBack();
                    if (mCallBack5 != null) {
                        T mPresenter13 = getMPresenter();
                        if (mPresenter13 == 0) {
                            Intrinsics.throwNpe();
                        }
                        mCallBack5.updateSelectDate(rateDetailFragment, ((RateDetailPresenter) mPresenter13).getMStartDate());
                        Unit unit98 = Unit.INSTANCE;
                    }
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCoreFragment
    protected void refreshTopView(Context context) {
        String showAreaRate;
        super.refreshTopView(context);
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder topViewHolder = mCallBack != null ? mCallBack.getTopViewHolder(this) : null;
        if (topViewHolder instanceof RateDetailTopViewHolder) {
            topViewHolder.setDefaultValue();
            IChartDetailCallback mCallBack2 = getMCallBack();
            if (mCallBack2 != null && mCallBack2.getRateType(this) == 2) {
                if (((RateDetailPresenter) getMPresenter()).getMDateType() == 1) {
                    TextView textView = ((RateDetailTopViewHolder) topViewHolder).mTvTitleAvg;
                    Intrinsics.checkExpressionValueIsNotNull(textView, "topViewHolder.mTvTitleAvg");
                    textView.setText(getLanguageText(R.string.title_silent_rate));
                } else {
                    TextView textView2 = ((RateDetailTopViewHolder) topViewHolder).mTvTitleAvg;
                    Intrinsics.checkExpressionValueIsNotNull(textView2, "topViewHolder.mTvTitleAvg");
                    textView2.setText(getLanguageText(R.string.home_detail_ave_ios));
                }
                T mPresenter = getMPresenter();
                if (mPresenter == 0) {
                    Intrinsics.throwNpe();
                }
                showAreaRate = ((RateDetailPresenter) mPresenter).getShowSilentRate();
            } else {
                TextView textView3 = ((RateDetailTopViewHolder) topViewHolder).mTvTitleAvg;
                Intrinsics.checkExpressionValueIsNotNull(textView3, "topViewHolder.mTvTitleAvg");
                textView3.setText(getLanguageText(R.string.home_detail_ave_ios));
                T mPresenter2 = getMPresenter();
                if (mPresenter2 == 0) {
                    Intrinsics.throwNpe();
                }
                showAreaRate = ((RateDetailPresenter) mPresenter2).getShowAreaRate();
            }
            String str = showAreaRate;
            if (!TextUtils.isEmpty(str) && !AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE.contentEquals(str)) {
                TextView textView4 = ((RateDetailTopViewHolder) topViewHolder).mTvAvg;
                Intrinsics.checkExpressionValueIsNotNull(textView4, "topViewHolder.mTvAvg");
                textView4.setText(str);
            }
            TextView textView5 = ((RateDetailTopViewHolder) topViewHolder).mTvDate;
            Intrinsics.checkExpressionValueIsNotNull(textView5, "topViewHolder.mTvDate");
            T mPresenter3 = getMPresenter();
            if (mPresenter3 == 0) {
                Intrinsics.throwNpe();
            }
            textView5.setText(((RateDetailPresenter) mPresenter3).getDateText());
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
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder topViewHolder = mCallBack != null ? mCallBack.getTopViewHolder(this) : null;
        IChartDetailCallback mCallBack2 = getMCallBack();
        BaseDetailViewHolder bottomViewHolder = mCallBack2 != null ? mCallBack2.getBottomViewHolder(this) : null;
        if ((topViewHolder instanceof RateDetailTopViewHolder) && (bottomViewHolder instanceof RateDetailBottomViewHolder)) {
            topViewHolder.setDefaultValue();
            bottomViewHolder.setDefaultValue();
            RateDetailBottomViewHolder rateDetailBottomViewHolder = (RateDetailBottomViewHolder) bottomViewHolder;
            IChartDetailCallback mCallBack3 = getMCallBack();
            rateDetailBottomViewHolder.updateSelectStatus(mCallBack3 != null && mCallBack3.getRateType(this) == 1);
            if (((LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart)) != null) {
                LineChartBar lineChartBar = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                if (lineChartBar == null) {
                    Intrinsics.throwNpe();
                }
                lineChartBar.clearList();
                LineChartBar lineChartBar2 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                if (lineChartBar2 == null) {
                    Intrinsics.throwNpe();
                }
                if (lineChartBar2.getVisibility() == 0) {
                    LineChartBar lineChartBar3 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                    if (lineChartBar3 == null) {
                        Intrinsics.throwNpe();
                    }
                    lineChartBar3.refreshChart(false);
                }
            }
            if (((FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart)) != null) {
                FloatBarChartBar floatBarChartBar = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
                if (floatBarChartBar == null) {
                    Intrinsics.throwNpe();
                }
                floatBarChartBar.clearList();
                FloatBarChartBar floatBarChartBar2 = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
                if (floatBarChartBar2 == null) {
                    Intrinsics.throwNpe();
                }
                if (floatBarChartBar2.getVisibility() == 0) {
                    FloatBarChartBar floatBarChartBar3 = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
                    if (floatBarChartBar3 == null) {
                        Intrinsics.throwNpe();
                    }
                    floatBarChartBar3.refreshChart(false);
                }
            }
            if (((HeartRateCubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart)) != null) {
                HeartRateCubicChartBar heartRateCubicChartBar = (HeartRateCubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
                if (heartRateCubicChartBar == null) {
                    Intrinsics.throwNpe();
                }
                heartRateCubicChartBar.clearList();
                HeartRateCubicChartBar heartRateCubicChartBar2 = (HeartRateCubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
                if (heartRateCubicChartBar2 == null) {
                    Intrinsics.throwNpe();
                }
                if (heartRateCubicChartBar2.getVisibility() == 0) {
                    HeartRateCubicChartBar heartRateCubicChartBar3 = (HeartRateCubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
                    if (heartRateCubicChartBar3 == null) {
                        Intrinsics.throwNpe();
                    }
                    heartRateCubicChartBar3.refreshChart(false);
                }
            }
            IChartDetailCallback mCallBack4 = getMCallBack();
            if (mCallBack4 != null) {
                mCallBack4.updateSelectDate(this, null);
            }
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public int getPageType() {
        IChartDetailCallback mCallBack = getMCallBack();
        if (mCallBack != null) {
            return mCallBack.getRateType(this);
        }
        return 1;
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void showLoadingView() {
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder topViewHolder = mCallBack != null ? mCallBack.getTopViewHolder(this) : null;
        if (topViewHolder instanceof RateDetailTopViewHolder) {
            ((RateDetailTopViewHolder) topViewHolder).showLoadingView();
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void showLoadSuccessView() {
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder topViewHolder = mCallBack != null ? mCallBack.getTopViewHolder(this) : null;
        if (topViewHolder instanceof RateDetailTopViewHolder) {
            ((RateDetailTopViewHolder) topViewHolder).showSuccessView(false);
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.IBaseDetailView
    public void showLoadFailedView() {
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder topViewHolder = mCallBack != null ? mCallBack.getTopViewHolder(this) : null;
        if (topViewHolder instanceof RateDetailTopViewHolder) {
            ((RateDetailTopViewHolder) topViewHolder).showLoadFailedView(this);
        }
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCoreFragment, com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public View getTipLayContent() {
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder tipViewHolder = mCallBack != null ? mCallBack.getTipViewHolder(this) : null;
        if (tipViewHolder instanceof RateDetailTipViewHolder) {
            return ((RateDetailTipViewHolder) tipViewHolder).getMLayContent();
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailCoreFragment, com.ido.life.module.home.chartdetail.vertical.BaseDetailCallback
    public boolean refreshChartTipView(int index) {
        super.refreshChartTipView(index);
        if (index < 0) {
            return false;
        }
        IChartDetailCallback mCallBack = getMCallBack();
        BaseDetailViewHolder tipViewHolder = mCallBack != null ? mCallBack.getTipViewHolder(this) : null;
        if (!(tipViewHolder instanceof RateDetailTipViewHolder)) {
            return false;
        }
        IChartDetailCallback mCallBack2 = getMCallBack();
        if (mCallBack2 == null) {
            Intrinsics.throwNpe();
        }
        RateDetailFragment rateDetailFragment = this;
        int dateType = mCallBack2.getDateType(rateDetailFragment);
        IChartDetailCallback mCallBack3 = getMCallBack();
        if (mCallBack3 == null) {
            Intrinsics.throwNpe();
        }
        int rateType = mCallBack3.getRateType(rateDetailFragment);
        if (dateType == 1) {
            IChartDetailCallback mCallBack4 = getMCallBack();
            if (mCallBack4 != null) {
                T mPresenter = getMPresenter();
                if (mPresenter == 0) {
                    Intrinsics.throwNpe();
                }
                mCallBack4.updateSelectDate(rateDetailFragment, ((RateDetailPresenter) mPresenter).getMStartDate());
            }
            HeartRateCubicChartBar heartRateCubicChartBar = (HeartRateCubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
            if (heartRateCubicChartBar == null) {
                Intrinsics.throwNpe();
            }
            List<T> list = heartRateCubicChartBar.getList();
            if (list != 0 && list.size() > index) {
                BaseCharBean chartBean = (BaseCharBean) list.get(index);
                if (chartBean.y < 20) {
                    List<T> list2 = list;
                    int size = list2.size();
                    int i = index + 1;
                    if (i >= 0 && size > i) {
                        chartBean = (BaseCharBean) list.get(i);
                    } else {
                        int size2 = list2.size();
                        int i2 = index - 1;
                        if (i2 < 0 || size2 <= i2) {
                            return false;
                        }
                        chartBean = (BaseCharBean) list.get(i2);
                    }
                }
                RateDetailTipViewHolder rateDetailTipViewHolder = (RateDetailTipViewHolder) tipViewHolder;
                TextView mTvDate = rateDetailTipViewHolder.getMTvDate();
                if (mTvDate != null) {
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    Intrinsics.checkExpressionValueIsNotNull(chartBean, "chartBean");
                    Object[] objArr = {Integer.valueOf((MathKt.roundToInt(chartBean.getActualValue()) / 60) / 60), Integer.valueOf((MathKt.roundToInt(chartBean.getActualValue()) / 60) % 60)};
                    String str = String.format("%02d:%02d", Arrays.copyOf(objArr, objArr.length));
                    Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(format, *args)");
                    mTvDate.setText(str);
                }
                if (rateType == 1) {
                    TextView mTvValue = rateDetailTipViewHolder.getMTvValue();
                    if (mTvValue != null) {
                        mTvValue.setText(String.valueOf(MathKt.roundToInt(chartBean.y)));
                    }
                } else {
                    T mPresenter2 = getMPresenter();
                    if (mPresenter2 == 0) {
                        Intrinsics.throwNpe();
                    }
                    String silentRate = ((RateDetailPresenter) mPresenter2).getSilentRate();
                    if (TextUtils.isEmpty(silentRate)) {
                        TextView mTvValue2 = rateDetailTipViewHolder.getMTvValue();
                        if (mTvValue2 != null) {
                            mTvValue2.setText(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
                        }
                    } else {
                        TextView mTvValue3 = rateDetailTipViewHolder.getMTvValue();
                        if (mTvValue3 != null) {
                            mTvValue3.setText(silentRate);
                        }
                    }
                }
            }
        } else {
            ServerHeartRateDayData itemHeart = (ServerHeartRateDayData) null;
            if (rateType == 1) {
                FloatBarChartBar floatBarChartBar = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
                if (floatBarChartBar == null) {
                    Intrinsics.throwNpe();
                }
                List<T> list3 = floatBarChartBar.getList();
                if (list3 != 0 && list3.size() > index) {
                    T mPresenter3 = getMPresenter();
                    if (mPresenter3 == 0) {
                        Intrinsics.throwNpe();
                    }
                    Object obj = list3.get(index);
                    Intrinsics.checkExpressionValueIsNotNull(obj, "floatList[index]");
                    itemHeart = ((RateDetailPresenter) mPresenter3).getItemHeart(((FloatBarPoint) obj).getIndex());
                    if (itemHeart != null) {
                        TextView mTvValue4 = ((RateDetailTipViewHolder) tipViewHolder).getMTvValue();
                        if (mTvValue4 != null) {
                            StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                            Object[] objArr2 = {Integer.valueOf(itemHeart.getMinValue()), Integer.valueOf(itemHeart.getMaxValue())};
                            String str2 = String.format("%d~%d", Arrays.copyOf(objArr2, objArr2.length));
                            Intrinsics.checkNotNullExpressionValue(str2, "java.lang.String.format(format, *args)");
                            mTvValue4.setText(str2);
                        }
                    } else {
                        TextView mTvValue5 = ((RateDetailTipViewHolder) tipViewHolder).getMTvValue();
                        if (mTvValue5 != null) {
                            mTvValue5.setText(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
                        }
                    }
                } else {
                    TextView mTvValue6 = ((RateDetailTipViewHolder) tipViewHolder).getMTvValue();
                    if (mTvValue6 != null) {
                        mTvValue6.setText(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
                    }
                }
            } else if (rateType == 2) {
                LineChartBar lineChartBar = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
                if (lineChartBar == null) {
                    Intrinsics.throwNpe();
                }
                List<T> list4 = lineChartBar.getList();
                if (list4 != 0 && list4.size() > index) {
                    T mPresenter4 = getMPresenter();
                    if (mPresenter4 == 0) {
                        Intrinsics.throwNpe();
                    }
                    Object obj2 = list4.get(index);
                    Intrinsics.checkExpressionValueIsNotNull(obj2, "lineList[index]");
                    itemHeart = ((RateDetailPresenter) mPresenter4).getItemHeart(((BaseCharBean) obj2).getIndex());
                    if (itemHeart != null) {
                        TextView mTvValue7 = ((RateDetailTipViewHolder) tipViewHolder).getMTvValue();
                        if (mTvValue7 != null) {
                            mTvValue7.setText(String.valueOf(itemHeart.getSilentValue()));
                        }
                    } else {
                        TextView mTvValue8 = ((RateDetailTipViewHolder) tipViewHolder).getMTvValue();
                        if (mTvValue8 != null) {
                            mTvValue8.setText(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
                        }
                    }
                } else {
                    TextView mTvValue9 = ((RateDetailTipViewHolder) tipViewHolder).getMTvValue();
                    if (mTvValue9 != null) {
                        mTvValue9.setText(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
                    }
                }
            }
            if (itemHeart != null) {
                String date = itemHeart.getDate();
                if (!TextUtils.isEmpty(date)) {
                    IChartDetailCallback mCallBack5 = getMCallBack();
                    if (mCallBack5 != null) {
                        mCallBack5.updateSelectDate(rateDetailFragment, date);
                    }
                    if (dateType == 2 || dateType == 3) {
                        TextView mTvDate2 = ((RateDetailTipViewHolder) tipViewHolder).getMTvDate();
                        if (mTvDate2 != null) {
                            mTvDate2.setText(DateUtil.format(DateUtil.string2Date(date, DateUtil.DATE_FORMAT_YMD), DateUtil.DATE_FORMAT_DM_1));
                        }
                    } else {
                        TextView mTvDate3 = ((RateDetailTipViewHolder) tipViewHolder).getMTvDate();
                        if (mTvDate3 != null) {
                            mTvDate3.setText(DateUtil.format(DateUtil.string2Date(date, DateUtil.DATE_FORMAT_YMD), DateUtil.DATE_FORMAT_MY));
                        }
                    }
                }
            }
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailFragment
    protected void refreshChart() {
        super.refreshChart();
        IChartDetailCallback mCallBack = getMCallBack();
        Integer numValueOf = mCallBack != null ? Integer.valueOf(mCallBack.getRateType(this)) : null;
        if (numValueOf != null && numValueOf.intValue() == 1) {
            if (((RateDetailPresenter) getMPresenter()).getMDateType() == 1) {
                HeartRateCubicChartBar heartRateCubicChartBar = (HeartRateCubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
                if (heartRateCubicChartBar != null) {
                    heartRateCubicChartBar.refreshChart(true);
                    return;
                }
                return;
            }
            HeartRateCubicChartBar heartRateCubicChartBar2 = (HeartRateCubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
            if (heartRateCubicChartBar2 != null) {
                heartRateCubicChartBar2.refreshChart(true);
                return;
            }
            return;
        }
        if (((RateDetailPresenter) getMPresenter()).getMDateType() == 1) {
            HeartRateCubicChartBar heartRateCubicChartBar3 = (HeartRateCubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
            if (heartRateCubicChartBar3 != null) {
                heartRateCubicChartBar3.refreshChart(true);
                return;
            }
            return;
        }
        HeartRateCubicChartBar heartRateCubicChartBar4 = (HeartRateCubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
        if (heartRateCubicChartBar4 != null) {
            heartRateCubicChartBar4.refreshChart(true);
        }
    }

    @Override // com.ido.life.customview.charter.CustomChatBar.CaluteXGridLineCallback
    public float calculateXGridLineValue(View target, int index) {
        List<String> labelYLeftList = (List) null;
        LineChartBar lineChartBar = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
        if (lineChartBar != null && lineChartBar.getVisibility() == 0) {
            LineChartBar lineChartBar2 = (LineChartBar) _$_findCachedViewById(com.ido.life.R.id.line_chart);
            labelYLeftList = lineChartBar2 != null ? lineChartBar2.getLabelYLeftList() : null;
        }
        FloatBarChartBar floatBarChartBar = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
        if (floatBarChartBar != null && floatBarChartBar.getVisibility() == 0) {
            FloatBarChartBar floatBarChartBar2 = (FloatBarChartBar) _$_findCachedViewById(com.ido.life.R.id.float_chart);
            labelYLeftList = floatBarChartBar2 != null ? floatBarChartBar2.getLabelYLeftList() : null;
        }
        HeartRateCubicChartBar heartRateCubicChartBar = (HeartRateCubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
        if (heartRateCubicChartBar != null && heartRateCubicChartBar.getVisibility() == 0) {
            HeartRateCubicChartBar heartRateCubicChartBar2 = (HeartRateCubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
            labelYLeftList = heartRateCubicChartBar2 != null ? heartRateCubicChartBar2.getLabelYLeftList() : null;
        }
        if (labelYLeftList == null || labelYLeftList.size() <= index) {
            return 0.0f;
        }
        return Integer.parseInt(labelYLeftList.get(index));
    }

    /* JADX INFO: compiled from: RateDetailFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/ido/life/module/home/rate/vertical/RateDetailFragment$Companion;", "", "()V", "RATE_AREA", "", "RATE_SLIENT", "getInstance", "Lcom/ido/life/module/home/rate/vertical/RateDetailFragment;", "bundle", "Landroid/os/Bundle;", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final RateDetailFragment getInstance(Bundle bundle) {
            RateDetailFragment rateDetailFragment = new RateDetailFragment();
            if (bundle != null) {
                rateDetailFragment.setArguments(bundle);
            }
            return rateDetailFragment;
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
            if (topViewHolder instanceof RateDetailTopViewHolder) {
                ((RateDetailTopViewHolder) topViewHolder).showLoadingView();
                ((RateDetailPresenter) getMPresenter()).getDetailData();
                return;
            }
            return;
        }
        showToast(R.string.public_net_unuse);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailFragment
    public RateDetailPresenter getPresenter() {
        return new RateDetailPresenter(this);
    }
}