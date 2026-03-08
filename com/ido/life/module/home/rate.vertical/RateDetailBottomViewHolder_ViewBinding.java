package com.ido.life.module.home.rate.vertical;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes2.dex */
public class RateDetailBottomViewHolder_ViewBinding implements Unbinder {
    private RateDetailBottomViewHolder target;

    public RateDetailBottomViewHolder_ViewBinding(RateDetailBottomViewHolder rateDetailBottomViewHolder, View view) {
        this.target = rateDetailBottomViewHolder;
        rateDetailBottomViewHolder.mLeftCard = (CardView) Utils.findRequiredViewAsType(view, R.id.card_left, "field 'mLeftCard'", CardView.class);
        rateDetailBottomViewHolder.mTvLeftRate = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_left_rate, "field 'mTvLeftRate'", TextView.class);
        rateDetailBottomViewHolder.mTvLeftRateUnit = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_left_rate_unit, "field 'mTvLeftRateUnit'", TextView.class);
        rateDetailBottomViewHolder.mTvLeftRateArea = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_left_rate_area, "field 'mTvLeftRateArea'", TextView.class);
        rateDetailBottomViewHolder.mRightCard = (CardView) Utils.findRequiredViewAsType(view, R.id.card_right, "field 'mRightCard'", CardView.class);
        rateDetailBottomViewHolder.mTvRightRate = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_right_rate, "field 'mTvRightRate'", TextView.class);
        rateDetailBottomViewHolder.mTvRightRateUnit = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_right_rate_unit, "field 'mTvRightRateUnit'", TextView.class);
        rateDetailBottomViewHolder.mTvRightRateArea = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_right_rate_area, "field 'mTvRightRateArea'", TextView.class);
        rateDetailBottomViewHolder.mLeftLayContent = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.bottom_left_content, "field 'mLeftLayContent'", LinearLayout.class);
        rateDetailBottomViewHolder.mRightLayContent = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.right_lay_content, "field 'mRightLayContent'", LinearLayout.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        RateDetailBottomViewHolder rateDetailBottomViewHolder = this.target;
        if (rateDetailBottomViewHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        rateDetailBottomViewHolder.mLeftCard = null;
        rateDetailBottomViewHolder.mTvLeftRate = null;
        rateDetailBottomViewHolder.mTvLeftRateUnit = null;
        rateDetailBottomViewHolder.mTvLeftRateArea = null;
        rateDetailBottomViewHolder.mRightCard = null;
        rateDetailBottomViewHolder.mTvRightRate = null;
        rateDetailBottomViewHolder.mTvRightRateUnit = null;
        rateDetailBottomViewHolder.mTvRightRateArea = null;
        rateDetailBottomViewHolder.mLeftLayContent = null;
        rateDetailBottomViewHolder.mRightLayContent = null;
    }
}