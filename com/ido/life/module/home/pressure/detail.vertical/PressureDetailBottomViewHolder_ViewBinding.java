package com.ido.life.module.home.pressure.detail.vertical;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.life.module.sport.view.SportPieChart;

/* JADX INFO: loaded from: classes2.dex */
public class PressureDetailBottomViewHolder_ViewBinding implements Unbinder {
    private PressureDetailBottomViewHolder target;

    public PressureDetailBottomViewHolder_ViewBinding(PressureDetailBottomViewHolder pressureDetailBottomViewHolder, View view) {
        this.target = pressureDetailBottomViewHolder;
        pressureDetailBottomViewHolder.mLayPipe = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.lay_pipe, "field 'mLayPipe'", LinearLayout.class);
        pressureDetailBottomViewHolder.mSportPieChart = (SportPieChart) Utils.findRequiredViewAsType(view, R.id.spc_pressure, "field 'mSportPieChart'", SportPieChart.class);
        pressureDetailBottomViewHolder.mTvTypeHigh = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_type_high, "field 'mTvTypeHigh'", TextView.class);
        pressureDetailBottomViewHolder.mTvHighValue = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_high_value, "field 'mTvHighValue'", TextView.class);
        pressureDetailBottomViewHolder.mTvTypeMedium = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_type_menium, "field 'mTvTypeMedium'", TextView.class);
        pressureDetailBottomViewHolder.mTvMediumValue = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_medium_value, "field 'mTvMediumValue'", TextView.class);
        pressureDetailBottomViewHolder.mTvTypeNormal = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_type_normal, "field 'mTvTypeNormal'", TextView.class);
        pressureDetailBottomViewHolder.mTvNormalValue = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_normal_value, "field 'mTvNormalValue'", TextView.class);
        pressureDetailBottomViewHolder.mTvTypeRelax = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_type_relax, "field 'mTvTypeRelax'", TextView.class);
        pressureDetailBottomViewHolder.mTvRelaxValue = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_relax_value, "field 'mTvRelaxValue'", TextView.class);
        pressureDetailBottomViewHolder.mTvTip = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_tip, "field 'mTvTip'", TextView.class);
        pressureDetailBottomViewHolder.mTvHighArea = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_high_area, "field 'mTvHighArea'", TextView.class);
        pressureDetailBottomViewHolder.mTvMediumArea = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_medium_area, "field 'mTvMediumArea'", TextView.class);
        pressureDetailBottomViewHolder.mTvNormalArea = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_normal_area, "field 'mTvNormalArea'", TextView.class);
        pressureDetailBottomViewHolder.mTvRelaxArea = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_relax_area, "field 'mTvRelaxArea'", TextView.class);
        pressureDetailBottomViewHolder.mTvDotHigh = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_dot_high, "field 'mTvDotHigh'", TextView.class);
        pressureDetailBottomViewHolder.mTvDotMedium = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_dot_medium, "field 'mTvDotMedium'", TextView.class);
        pressureDetailBottomViewHolder.mTvDotNormal = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_dot_normal, "field 'mTvDotNormal'", TextView.class);
        pressureDetailBottomViewHolder.mTvDotRelax = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_dot_relax, "field 'mTvDotRelax'", TextView.class);
        pressureDetailBottomViewHolder.mLayAllPressureState = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.lay_all_pressure_state, "field 'mLayAllPressureState'", LinearLayout.class);
        pressureDetailBottomViewHolder.mTvAllPressureTip = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_all_pressure_tip, "field 'mTvAllPressureTip'", TextView.class);
        pressureDetailBottomViewHolder.mTvAllPressureState = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_all_pressure_state, "field 'mTvAllPressureState'", TextView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        PressureDetailBottomViewHolder pressureDetailBottomViewHolder = this.target;
        if (pressureDetailBottomViewHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        pressureDetailBottomViewHolder.mLayPipe = null;
        pressureDetailBottomViewHolder.mSportPieChart = null;
        pressureDetailBottomViewHolder.mTvTypeHigh = null;
        pressureDetailBottomViewHolder.mTvHighValue = null;
        pressureDetailBottomViewHolder.mTvTypeMedium = null;
        pressureDetailBottomViewHolder.mTvMediumValue = null;
        pressureDetailBottomViewHolder.mTvTypeNormal = null;
        pressureDetailBottomViewHolder.mTvNormalValue = null;
        pressureDetailBottomViewHolder.mTvTypeRelax = null;
        pressureDetailBottomViewHolder.mTvRelaxValue = null;
        pressureDetailBottomViewHolder.mTvTip = null;
        pressureDetailBottomViewHolder.mTvHighArea = null;
        pressureDetailBottomViewHolder.mTvMediumArea = null;
        pressureDetailBottomViewHolder.mTvNormalArea = null;
        pressureDetailBottomViewHolder.mTvRelaxArea = null;
        pressureDetailBottomViewHolder.mTvDotHigh = null;
        pressureDetailBottomViewHolder.mTvDotMedium = null;
        pressureDetailBottomViewHolder.mTvDotNormal = null;
        pressureDetailBottomViewHolder.mTvDotRelax = null;
        pressureDetailBottomViewHolder.mLayAllPressureState = null;
        pressureDetailBottomViewHolder.mTvAllPressureTip = null;
        pressureDetailBottomViewHolder.mTvAllPressureState = null;
    }
}