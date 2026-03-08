package com.ido.life.module.home.calorie;

import android.view.View;
import android.widget.TextView;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.life.module.home.chartdetail.vertical.BaseDetailTopViewHolder_ViewBinding;

/* JADX INFO: loaded from: classes2.dex */
public class CalorieDetailTopViewHolder_ViewBinding extends BaseDetailTopViewHolder_ViewBinding {
    private CalorieDetailTopViewHolder target;

    public CalorieDetailTopViewHolder_ViewBinding(CalorieDetailTopViewHolder calorieDetailTopViewHolder, View view) {
        super(calorieDetailTopViewHolder, view);
        this.target = calorieDetailTopViewHolder;
        calorieDetailTopViewHolder.mTvTitleTotal = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_title_total, "field 'mTvTitleTotal'", TextView.class);
        calorieDetailTopViewHolder.mTvTotalCalorie = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_total_calorie, "field 'mTvTotalCalorie'", TextView.class);
        calorieDetailTopViewHolder.mTvLeftUnit = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_left_unit, "field 'mTvLeftUnit'", TextView.class);
        calorieDetailTopViewHolder.mTvDate = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_date, "field 'mTvDate'", TextView.class);
        calorieDetailTopViewHolder.mTvTitleAvg = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_title_avg, "field 'mTvTitleAvg'", TextView.class);
        calorieDetailTopViewHolder.mTvAvgCalorie = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_avg_calorie, "field 'mTvAvgCalorie'", TextView.class);
        calorieDetailTopViewHolder.mTvRightUnit = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_right_unit, "field 'mTvRightUnit'", TextView.class);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailTopViewHolder_ViewBinding, butterknife.Unbinder
    public void unbind() {
        CalorieDetailTopViewHolder calorieDetailTopViewHolder = this.target;
        if (calorieDetailTopViewHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        calorieDetailTopViewHolder.mTvTitleTotal = null;
        calorieDetailTopViewHolder.mTvTotalCalorie = null;
        calorieDetailTopViewHolder.mTvLeftUnit = null;
        calorieDetailTopViewHolder.mTvDate = null;
        calorieDetailTopViewHolder.mTvTitleAvg = null;
        calorieDetailTopViewHolder.mTvAvgCalorie = null;
        calorieDetailTopViewHolder.mTvRightUnit = null;
        super.unbind();
    }
}