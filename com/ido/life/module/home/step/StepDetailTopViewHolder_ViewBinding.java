package com.ido.life.module.home.step;

import android.view.View;
import android.widget.TextView;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.life.module.home.chartdetail.vertical.BaseDetailTopViewHolder_ViewBinding;

/* JADX INFO: loaded from: classes2.dex */
public class StepDetailTopViewHolder_ViewBinding extends BaseDetailTopViewHolder_ViewBinding {
    private StepDetailTopViewHolder target;

    public StepDetailTopViewHolder_ViewBinding(StepDetailTopViewHolder stepDetailTopViewHolder, View view) {
        super(stepDetailTopViewHolder, view);
        this.target = stepDetailTopViewHolder;
        stepDetailTopViewHolder.mTvTitleTotal = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_title_total, "field 'mTvTitleTotal'", TextView.class);
        stepDetailTopViewHolder.mTvTotalStep = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_total_step, "field 'mTvTotalStep'", TextView.class);
        stepDetailTopViewHolder.mTvLeftUnit = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_left_unit, "field 'mTvLeftUnit'", TextView.class);
        stepDetailTopViewHolder.mTvDate = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_date, "field 'mTvDate'", TextView.class);
        stepDetailTopViewHolder.mTvTitleAvg = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_title_avg, "field 'mTvTitleAvg'", TextView.class);
        stepDetailTopViewHolder.mTvAvgStep = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_avg_step, "field 'mTvAvgStep'", TextView.class);
        stepDetailTopViewHolder.mTvRightUnit = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_right_unit, "field 'mTvRightUnit'", TextView.class);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailTopViewHolder_ViewBinding, butterknife.Unbinder
    public void unbind() {
        StepDetailTopViewHolder stepDetailTopViewHolder = this.target;
        if (stepDetailTopViewHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        stepDetailTopViewHolder.mTvTitleTotal = null;
        stepDetailTopViewHolder.mTvTotalStep = null;
        stepDetailTopViewHolder.mTvLeftUnit = null;
        stepDetailTopViewHolder.mTvDate = null;
        stepDetailTopViewHolder.mTvTitleAvg = null;
        stepDetailTopViewHolder.mTvAvgStep = null;
        stepDetailTopViewHolder.mTvRightUnit = null;
        super.unbind();
    }
}