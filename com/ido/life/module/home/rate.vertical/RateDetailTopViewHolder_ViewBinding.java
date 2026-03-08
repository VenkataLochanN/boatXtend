package com.ido.life.module.home.rate.vertical;

import android.view.View;
import android.widget.TextView;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.life.module.home.chartdetail.vertical.BaseDetailTopViewHolder_ViewBinding;

/* JADX INFO: loaded from: classes2.dex */
public class RateDetailTopViewHolder_ViewBinding extends BaseDetailTopViewHolder_ViewBinding {
    private RateDetailTopViewHolder target;

    public RateDetailTopViewHolder_ViewBinding(RateDetailTopViewHolder rateDetailTopViewHolder, View view) {
        super(rateDetailTopViewHolder, view);
        this.target = rateDetailTopViewHolder;
        rateDetailTopViewHolder.mTvTitleAvg = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_title_avg, "field 'mTvTitleAvg'", TextView.class);
        rateDetailTopViewHolder.mTvAvg = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_avg, "field 'mTvAvg'", TextView.class);
        rateDetailTopViewHolder.mTvUnit = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_unit, "field 'mTvUnit'", TextView.class);
        rateDetailTopViewHolder.mTvDate = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_date, "field 'mTvDate'", TextView.class);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailTopViewHolder_ViewBinding, butterknife.Unbinder
    public void unbind() {
        RateDetailTopViewHolder rateDetailTopViewHolder = this.target;
        if (rateDetailTopViewHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        rateDetailTopViewHolder.mTvTitleAvg = null;
        rateDetailTopViewHolder.mTvAvg = null;
        rateDetailTopViewHolder.mTvUnit = null;
        rateDetailTopViewHolder.mTvDate = null;
        super.unbind();
    }
}