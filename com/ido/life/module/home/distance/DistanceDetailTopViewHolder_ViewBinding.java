package com.ido.life.module.home.distance;

import android.view.View;
import android.widget.TextView;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.life.module.home.chartdetail.vertical.BaseDetailTopViewHolder_ViewBinding;

/* JADX INFO: loaded from: classes2.dex */
public class DistanceDetailTopViewHolder_ViewBinding extends BaseDetailTopViewHolder_ViewBinding {
    private DistanceDetailTopViewHolder target;

    public DistanceDetailTopViewHolder_ViewBinding(DistanceDetailTopViewHolder distanceDetailTopViewHolder, View view) {
        super(distanceDetailTopViewHolder, view);
        this.target = distanceDetailTopViewHolder;
        distanceDetailTopViewHolder.mTvTitleTotal = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_title_total, "field 'mTvTitleTotal'", TextView.class);
        distanceDetailTopViewHolder.mTvTotalDistance = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_total_distance, "field 'mTvTotalDistance'", TextView.class);
        distanceDetailTopViewHolder.mTvLeftUnit = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_left_unit, "field 'mTvLeftUnit'", TextView.class);
        distanceDetailTopViewHolder.mTvDate = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_date, "field 'mTvDate'", TextView.class);
        distanceDetailTopViewHolder.mTvTitleAvg = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_title_avg, "field 'mTvTitleAvg'", TextView.class);
        distanceDetailTopViewHolder.mTvAvgDistance = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_avg_distance, "field 'mTvAvgDistance'", TextView.class);
        distanceDetailTopViewHolder.mTvRightUnit = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_right_unit, "field 'mTvRightUnit'", TextView.class);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailTopViewHolder_ViewBinding, butterknife.Unbinder
    public void unbind() {
        DistanceDetailTopViewHolder distanceDetailTopViewHolder = this.target;
        if (distanceDetailTopViewHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        distanceDetailTopViewHolder.mTvTitleTotal = null;
        distanceDetailTopViewHolder.mTvTotalDistance = null;
        distanceDetailTopViewHolder.mTvLeftUnit = null;
        distanceDetailTopViewHolder.mTvDate = null;
        distanceDetailTopViewHolder.mTvTitleAvg = null;
        distanceDetailTopViewHolder.mTvAvgDistance = null;
        distanceDetailTopViewHolder.mTvRightUnit = null;
        super.unbind();
    }
}