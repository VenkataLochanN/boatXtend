package com.ido.life.module.sport.history.rate;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import com.boat.Xtend.two.R;
import com.ido.common.widget.textview.NumberTextView;
import com.ido.life.base.BaseFragment;
import com.ido.life.bean.BaseCharBean;
import com.ido.life.customview.charter.CubicChartBar;
import com.ido.life.customview.viewgroup.BottomSportView;
import com.ido.life.database.model.SportHealth;
import com.ido.life.module.sport.bean.PieChartBean;
import com.ido.life.module.sport.view.SportPieChart;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RateFragment.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u0000 82\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00012\u00020\u0003:\u00018B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0014J\b\u0010\u0007\u001a\u00020\bH\u0004J\u0012\u0010\t\u001a\u00020\b2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016J\u001a\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016J\u0012\u0010\u000f\u001a\u00020\b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\u0012\u0010\u0012\u001a\u00020\b2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0011H\u0016J\u001a\u0010\u0014\u001a\u00020\b2\u0010\u0010\u0015\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0017\u0018\u00010\u0016H\u0016J\u0012\u0010\u0018\u001a\u00020\b2\b\u0010\u0019\u001a\u0004\u0018\u00010\u0011H\u0016J\u0012\u0010\u001a\u001a\u00020\b2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0011H\u0016J\u001a\u0010\u001c\u001a\u00020\b2\u0010\u0010\u001d\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u001e\u0018\u00010\u0016H\u0016J\u0010\u0010\u001f\u001a\u00020\b2\u0006\u0010 \u001a\u00020\u0006H\u0016J\u0010\u0010!\u001a\u00020\b2\u0006\u0010\"\u001a\u00020\u0006H\u0016J\u0010\u0010#\u001a\u00020\b2\u0006\u0010$\u001a\u00020\u0006H\u0016J\u0010\u0010%\u001a\u00020\b2\u0006\u0010&\u001a\u00020\u0006H\u0016J\u0012\u0010'\u001a\u00020\b2\b\u0010(\u001a\u0004\u0018\u00010\u0011H\u0016J\u0012\u0010)\u001a\u00020\b2\b\u0010*\u001a\u0004\u0018\u00010\u0011H\u0016J\u0012\u0010+\u001a\u00020\b2\b\u0010,\u001a\u0004\u0018\u00010\u0011H\u0016J\u0012\u0010-\u001a\u00020\b2\b\u0010.\u001a\u0004\u0018\u00010\u0011H\u0016J\u001a\u0010/\u001a\u00020\b2\u0010\u00100\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0011\u0018\u00010\u0016H\u0016J\u0012\u00101\u001a\u00020\b2\b\u00102\u001a\u0004\u0018\u00010\u0011H\u0016J\u001a\u00103\u001a\u00020\b2\u0010\u00104\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0011\u0018\u00010\u0016H\u0016J\u0010\u00105\u001a\u00020\b2\u0006\u00106\u001a\u000207H\u0016¨\u00069"}, d2 = {"Lcom/ido/life/module/sport/history/rate/RateFragment;", "Lcom/ido/life/base/BaseFragment;", "Lcom/ido/life/module/sport/history/rate/RateFragmentPresenter;", "Lcom/ido/life/module/sport/history/rate/IRateView;", "()V", "getLayoutResId", "", "initChartData", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "view", "Landroid/view/View;", "setAerobicEnduranceTime", "aerobicEndurance", "", "setBurningHeartRateTime", "burningHeartRate", "setHeartPieChart", "pieChartBeanList", "", "Lcom/ido/life/module/sport/bean/PieChartBean;", "setMaxExerciseTime", "maxExerciseTime", "setNoEnduranceTime", "noEndurance", "setRateList", "baseCharBeans", "Lcom/ido/life/bean/BaseCharBean;", "setRateXMaxValue", "xMaxValue", "setRateXMinValue", "rateXMinValue", "setRateYMaxValue", "rateYMaxValue", "setRateYMinValue", "rateYMinValue", "setSportChartRateAvg", "sportRateAvg", "setSportRateMax", "sportRateMax", "setWarmUpHeartText", "warmUpHeartText", "setWarmUpHeartTime", "warmUpHeart", "setXLabelList", "xLabelList", "setXlabelUnit", "xLabelUnit", "setYLabelList", "yLabelList", "showSportItemRate", "isVisible", "", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
public final class RateFragment extends BaseFragment<RateFragmentPresenter> implements IRateView {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String EXTRA_SPORT = "extra_sport";
    private HashMap _$_findViewCache;

    public void _$_clearFindViewByIdCache() {
        HashMap map = this._$_findViewCache;
        if (map != null) {
            map.clear();
        }
    }

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
        return R.layout.fragment_rate;
    }

    @Override // com.ido.life.base.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override // com.ido.life.base.BaseFragment, com.ido.common.base.BaseCoreFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        super.onViewCreated(view, savedInstanceState);
        Bundle arguments = getArguments();
        initChartData();
        if (arguments == null) {
            Intrinsics.throwNpe();
        }
        SportHealth sportHealth = (SportHealth) arguments.getSerializable(EXTRA_SPORT);
        P p = this.mPresenter;
        if (p == 0) {
            Intrinsics.throwNpe();
        }
        RateFragmentPresenter rateFragmentPresenter = (RateFragmentPresenter) p;
        if (sportHealth == null) {
            Intrinsics.throwNpe();
        }
        rateFragmentPresenter.showRate(sportHealth);
    }

    protected final void initChartData() {
        CubicChartBar cubicChartBar = (CubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
        if (cubicChartBar == null) {
            Intrinsics.throwNpe();
        }
        cubicChartBar.setBottomLabelCenter(false);
        CubicChartBar cubicChartBar2 = (CubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
        if (cubicChartBar2 == null) {
            Intrinsics.throwNpe();
        }
        cubicChartBar2.setRateLineColor(getResources().getColor(R.color.color_E90B28));
        CubicChartBar cubicChartBar3 = (CubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
        if (cubicChartBar3 == null) {
            Intrinsics.throwNpe();
        }
        cubicChartBar3.setGradientStartColor(getResources().getColor(R.color.color_10E90B28));
        CubicChartBar cubicChartBar4 = (CubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
        if (cubicChartBar4 == null) {
            Intrinsics.throwNpe();
        }
        cubicChartBar4.setGradientEndColor(getResources().getColor(R.color.com_color_transparent));
        CubicChartBar cubicChartBar5 = (CubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
        if (cubicChartBar5 == null) {
            Intrinsics.throwNpe();
        }
        cubicChartBar5.invalidate();
    }

    @Override // com.ido.life.module.sport.history.rate.IRateView
    public void showSportItemRate(boolean isVisible) {
        LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(com.ido.life.R.id.ll_sport_detail_rate_item);
        if (linearLayout == null) {
            Intrinsics.throwNpe();
        }
        linearLayout.setVisibility(isVisible ? 0 : 8);
    }

    @Override // com.ido.life.module.sport.history.rate.IRateView
    public void setSportRateMax(String sportRateMax) {
        BottomSportView bottomSportView = (BottomSportView) _$_findCachedViewById(com.ido.life.R.id.bv_chart_sport_rate_max);
        if (bottomSportView == null) {
            Intrinsics.throwNpe();
        }
        bottomSportView.setDataText(sportRateMax);
    }

    @Override // com.ido.life.module.sport.history.rate.IRateView
    public void setSportChartRateAvg(String sportRateAvg) {
        BottomSportView bottomSportView = (BottomSportView) _$_findCachedViewById(com.ido.life.R.id.bv_chart_sport_rate_average);
        if (bottomSportView == null) {
            Intrinsics.throwNpe();
        }
        bottomSportView.setDataText(sportRateAvg);
    }

    @Override // com.ido.life.module.sport.history.rate.IRateView
    public void setHeartPieChart(List<? extends PieChartBean> pieChartBeanList) {
        List<? extends PieChartBean> list = pieChartBeanList;
        if (list == null || list.isEmpty()) {
            SportPieChart sportPieChart = (SportPieChart) _$_findCachedViewById(com.ido.life.R.id.pc_sport);
            if (sportPieChart == null) {
                Intrinsics.throwNpe();
            }
            sportPieChart.setData(null);
            return;
        }
        SportPieChart sportPieChart2 = (SportPieChart) _$_findCachedViewById(com.ido.life.R.id.pc_sport);
        if (sportPieChart2 == null) {
            Intrinsics.throwNpe();
        }
        sportPieChart2.setData(pieChartBeanList);
    }

    @Override // com.ido.life.module.sport.history.rate.IRateView
    public void setXlabelUnit(String xLabelUnit) {
        if (xLabelUnit != null) {
            CubicChartBar cubicChartBar = (CubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
            if (cubicChartBar == null) {
                Intrinsics.throwNpe();
            }
            cubicChartBar.setBottomTitle(xLabelUnit);
        }
    }

    @Override // com.ido.life.module.sport.history.rate.IRateView
    public void setMaxExerciseTime(String maxExerciseTime) {
        NumberTextView numberTextView = (NumberTextView) _$_findCachedViewById(com.ido.life.R.id.tv_max_exercise_time);
        if (numberTextView == null) {
            Intrinsics.throwNpe();
        }
        numberTextView.setText(maxExerciseTime);
    }

    @Override // com.ido.life.module.sport.history.rate.IRateView
    public void setWarmUpHeartText(String warmUpHeartText) {
        NumberTextView numberTextView = (NumberTextView) _$_findCachedViewById(com.ido.life.R.id.tv_warm_up_heart);
        if (numberTextView == null) {
            Intrinsics.throwNpe();
        }
        numberTextView.setText(warmUpHeartText);
    }

    @Override // com.ido.life.module.sport.history.rate.IRateView
    public void setYLabelList(List<String> yLabelList) {
        List<String> list = yLabelList;
        if (list == null || list.isEmpty()) {
            CubicChartBar cubicChartBar = (CubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
            if (cubicChartBar == null) {
                Intrinsics.throwNpe();
            }
            cubicChartBar.setLabelYLeftList(null);
            return;
        }
        CubicChartBar cubicChartBar2 = (CubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
        if (cubicChartBar2 == null) {
            Intrinsics.throwNpe();
        }
        if (yLabelList == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.List<kotlin.String>");
        }
        cubicChartBar2.setLabelYLeftList(yLabelList);
    }

    @Override // com.ido.life.module.sport.history.rate.IRateView
    public void setXLabelList(List<String> xLabelList) {
        List<String> list = xLabelList;
        if (list == null || list.isEmpty()) {
            CubicChartBar cubicChartBar = (CubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
            if (cubicChartBar == null) {
                Intrinsics.throwNpe();
            }
            cubicChartBar.setLabelXList(null);
            return;
        }
        CubicChartBar cubicChartBar2 = (CubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
        if (cubicChartBar2 == null) {
            Intrinsics.throwNpe();
        }
        if (xLabelList == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.List<kotlin.String>");
        }
        cubicChartBar2.setLabelXList(xLabelList);
    }

    @Override // com.ido.life.module.sport.history.rate.IRateView
    public void setRateList(List<? extends BaseCharBean> baseCharBeans) {
        List<? extends BaseCharBean> list = baseCharBeans;
        if (list == null || list.isEmpty()) {
            ((CubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart)).setList(null);
            return;
        }
        CubicChartBar cubicChartBar = (CubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
        if (baseCharBeans == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.List<kotlin.Nothing>");
        }
        cubicChartBar.setList(baseCharBeans);
    }

    @Override // com.ido.life.module.sport.history.rate.IRateView
    public void setRateXMinValue(int rateXMinValue) {
        CubicChartBar cubicChartBar = (CubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
        if (cubicChartBar == null) {
            Intrinsics.throwNpe();
        }
        cubicChartBar.setXMinValue(rateXMinValue);
    }

    @Override // com.ido.life.module.sport.history.rate.IRateView
    public void setRateXMaxValue(int xMaxValue) {
        CubicChartBar cubicChartBar = (CubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
        if (cubicChartBar == null) {
            Intrinsics.throwNpe();
        }
        cubicChartBar.setXMaxValue(xMaxValue);
    }

    @Override // com.ido.life.module.sport.history.rate.IRateView
    public void setRateYMaxValue(int rateYMaxValue) {
        CubicChartBar cubicChartBar = (CubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
        if (cubicChartBar == null) {
            Intrinsics.throwNpe();
        }
        cubicChartBar.setYMaxValue(rateYMaxValue);
    }

    @Override // com.ido.life.module.sport.history.rate.IRateView
    public void setRateYMinValue(int rateYMinValue) {
        CubicChartBar cubicChartBar = (CubicChartBar) _$_findCachedViewById(com.ido.life.R.id.cubic_chart);
        if (cubicChartBar == null) {
            Intrinsics.throwNpe();
        }
        cubicChartBar.setYMinValue(rateYMinValue);
    }

    @Override // com.ido.life.module.sport.history.rate.IRateView
    public void setNoEnduranceTime(String noEndurance) {
        NumberTextView numberTextView = (NumberTextView) _$_findCachedViewById(com.ido.life.R.id.tv_no_endurance);
        if (numberTextView == null) {
            Intrinsics.throwNpe();
        }
        numberTextView.setText(noEndurance);
    }

    @Override // com.ido.life.module.sport.history.rate.IRateView
    public void setAerobicEnduranceTime(String aerobicEndurance) {
        NumberTextView numberTextView = (NumberTextView) _$_findCachedViewById(com.ido.life.R.id.tv_aerobic_endurance);
        if (numberTextView == null) {
            Intrinsics.throwNpe();
        }
        numberTextView.setText(aerobicEndurance);
    }

    @Override // com.ido.life.module.sport.history.rate.IRateView
    public void setBurningHeartRateTime(String burningHeartRate) {
        NumberTextView numberTextView = (NumberTextView) _$_findCachedViewById(com.ido.life.R.id.tv_burning_heart_rate);
        if (numberTextView == null) {
            Intrinsics.throwNpe();
        }
        numberTextView.setText(burningHeartRate);
    }

    @Override // com.ido.life.module.sport.history.rate.IRateView
    public void setWarmUpHeartTime(String warmUpHeart) {
        NumberTextView numberTextView = (NumberTextView) _$_findCachedViewById(com.ido.life.R.id.tv_warm_up_heart);
        if (numberTextView == null) {
            Intrinsics.throwNpe();
        }
        numberTextView.setText(warmUpHeart);
    }

    /* JADX INFO: compiled from: RateFragment.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/ido/life/module/sport/history/rate/RateFragment$Companion;", "", "()V", "EXTRA_SPORT", "", "newInstance", "Lcom/ido/life/module/sport/history/rate/RateFragment;", "sportHealth", "Lcom/ido/life/database/model/SportHealth;", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final RateFragment newInstance(SportHealth sportHealth) {
            RateFragment rateFragment = new RateFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable(RateFragment.EXTRA_SPORT, sportHealth);
            rateFragment.setArguments(bundle);
            return rateFragment;
        }
    }
}