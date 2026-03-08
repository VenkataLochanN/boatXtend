package com.ido.life.module.home.weight;

import android.view.View;
import android.widget.TextView;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.life.module.home.chartdetail.vertical.BaseDetailTopViewHolder_ViewBinding;

/* JADX INFO: loaded from: classes2.dex */
public class WeightDetailTopViewHolder_ViewBinding extends BaseDetailTopViewHolder_ViewBinding {
    private WeightDetailTopViewHolder target;

    public WeightDetailTopViewHolder_ViewBinding(WeightDetailTopViewHolder weightDetailTopViewHolder, View view) {
        super(weightDetailTopViewHolder, view);
        this.target = weightDetailTopViewHolder;
        weightDetailTopViewHolder.mTvTitleAvg = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_title_avg, "field 'mTvTitleAvg'", TextView.class);
        weightDetailTopViewHolder.mTvAvg = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_avg, "field 'mTvAvg'", TextView.class);
        weightDetailTopViewHolder.mTvUnit = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_unit, "field 'mTvUnit'", TextView.class);
        weightDetailTopViewHolder.mTvDate = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_date, "field 'mTvDate'", TextView.class);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailTopViewHolder_ViewBinding, butterknife.Unbinder
    public void unbind() {
        WeightDetailTopViewHolder weightDetailTopViewHolder = this.target;
        if (weightDetailTopViewHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        weightDetailTopViewHolder.mTvTitleAvg = null;
        weightDetailTopViewHolder.mTvAvg = null;
        weightDetailTopViewHolder.mTvUnit = null;
        weightDetailTopViewHolder.mTvDate = null;
        super.unbind();
    }
}