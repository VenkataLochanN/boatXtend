package com.ido.life.module.home.pressure.detail.vertical;

import android.view.View;
import android.widget.TextView;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.life.module.home.chartdetail.vertical.BaseDetailTopViewHolder_ViewBinding;

/* JADX INFO: loaded from: classes2.dex */
public class PressureDetailTopViewHolder_ViewBinding extends BaseDetailTopViewHolder_ViewBinding {
    private PressureDetailTopViewHolder target;

    public PressureDetailTopViewHolder_ViewBinding(PressureDetailTopViewHolder pressureDetailTopViewHolder, View view) {
        super(pressureDetailTopViewHolder, view);
        this.target = pressureDetailTopViewHolder;
        pressureDetailTopViewHolder.mTvTitleAvg = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_title_avg, "field 'mTvTitleAvg'", TextView.class);
        pressureDetailTopViewHolder.mTvAvg = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_avg, "field 'mTvAvg'", TextView.class);
        pressureDetailTopViewHolder.mTvState = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_state, "field 'mTvState'", TextView.class);
        pressureDetailTopViewHolder.mTvDate = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_date, "field 'mTvDate'", TextView.class);
        pressureDetailTopViewHolder.mTvTitleMaxmin = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_title_maxmin, "field 'mTvTitleMaxmin'", TextView.class);
        pressureDetailTopViewHolder.mTvMaxmin = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_max_min, "field 'mTvMaxmin'", TextView.class);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailTopViewHolder_ViewBinding, butterknife.Unbinder
    public void unbind() {
        PressureDetailTopViewHolder pressureDetailTopViewHolder = this.target;
        if (pressureDetailTopViewHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        pressureDetailTopViewHolder.mTvTitleAvg = null;
        pressureDetailTopViewHolder.mTvAvg = null;
        pressureDetailTopViewHolder.mTvState = null;
        pressureDetailTopViewHolder.mTvDate = null;
        pressureDetailTopViewHolder.mTvTitleMaxmin = null;
        pressureDetailTopViewHolder.mTvMaxmin = null;
        super.unbind();
    }
}