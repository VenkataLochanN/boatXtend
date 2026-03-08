package com.ido.life.module.home.activity;

import android.view.View;
import android.widget.TextView;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.life.module.home.chartdetail.vertical.BaseDetailTopViewHolder_ViewBinding;

/* JADX INFO: loaded from: classes2.dex */
public class ActivityDetailTopViewHolder_ViewBinding extends BaseDetailTopViewHolder_ViewBinding {
    private ActivityDetailTopViewHolder target;

    public ActivityDetailTopViewHolder_ViewBinding(ActivityDetailTopViewHolder activityDetailTopViewHolder, View view) {
        super(activityDetailTopViewHolder, view);
        this.target = activityDetailTopViewHolder;
        activityDetailTopViewHolder.mTvTitleTotal = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_title_total, "field 'mTvTitleTotal'", TextView.class);
        activityDetailTopViewHolder.mTvTotalActivityTime = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_total_activity_time, "field 'mTvTotalActivityTime'", TextView.class);
        activityDetailTopViewHolder.mTvLeftUnit = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_left_unit, "field 'mTvLeftUnit'", TextView.class);
        activityDetailTopViewHolder.mTvDate = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_date, "field 'mTvDate'", TextView.class);
        activityDetailTopViewHolder.mTvTitleAvg = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_title_avg, "field 'mTvTitleAvg'", TextView.class);
        activityDetailTopViewHolder.mTvAvgActivityTime = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_avg_activity_time, "field 'mTvAvgActivityTime'", TextView.class);
        activityDetailTopViewHolder.mTvRightUnit = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_right_unit, "field 'mTvRightUnit'", TextView.class);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailTopViewHolder_ViewBinding, butterknife.Unbinder
    public void unbind() {
        ActivityDetailTopViewHolder activityDetailTopViewHolder = this.target;
        if (activityDetailTopViewHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        activityDetailTopViewHolder.mTvTitleTotal = null;
        activityDetailTopViewHolder.mTvTotalActivityTime = null;
        activityDetailTopViewHolder.mTvLeftUnit = null;
        activityDetailTopViewHolder.mTvDate = null;
        activityDetailTopViewHolder.mTvTitleAvg = null;
        activityDetailTopViewHolder.mTvAvgActivityTime = null;
        activityDetailTopViewHolder.mTvRightUnit = null;
        super.unbind();
    }
}