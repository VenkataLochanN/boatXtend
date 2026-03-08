package com.ido.life.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.life.customview.HomeIconAnimView;
import com.ido.life.customview.charter.BarChartBar;

/* JADX INFO: loaded from: classes3.dex */
public class HomePressureViewHolder_ViewBinding extends BaseHomeItemViewHolder_ViewBinding {
    private HomePressureViewHolder target;

    public HomePressureViewHolder_ViewBinding(HomePressureViewHolder homePressureViewHolder, View view) {
        super(homePressureViewHolder, view);
        this.target = homePressureViewHolder;
        homePressureViewHolder.mLayOut = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.lay_out, "field 'mLayOut'", LinearLayout.class);
        homePressureViewHolder.mTvTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_title, "field 'mTvTitle'", TextView.class);
        homePressureViewHolder.mTvDate = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_date, "field 'mTvDate'", TextView.class);
        homePressureViewHolder.mImgEmpty = (ImageView) Utils.findRequiredViewAsType(view, R.id.img_empty, "field 'mImgEmpty'", ImageView.class);
        homePressureViewHolder.mPresureChart = (BarChartBar) Utils.findRequiredViewAsType(view, R.id.chat_presure, "field 'mPresureChart'", BarChartBar.class);
        homePressureViewHolder.mLayBottom = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.lay_bottom, "field 'mLayBottom'", LinearLayout.class);
        homePressureViewHolder.mLayContent = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.lay_content, "field 'mLayContent'", LinearLayout.class);
        homePressureViewHolder.mTvPressure = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_pressure, "field 'mTvPressure'", TextView.class);
        homePressureViewHolder.mTvPressureState = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_pressure_state, "field 'mTvPressureState'", TextView.class);
        homePressureViewHolder.mAnimView = (HomeIconAnimView) Utils.findRequiredViewAsType(view, R.id.anim_view, "field 'mAnimView'", HomeIconAnimView.class);
    }

    @Override // com.ido.life.viewholder.BaseHomeItemViewHolder_ViewBinding, butterknife.Unbinder
    public void unbind() {
        HomePressureViewHolder homePressureViewHolder = this.target;
        if (homePressureViewHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        homePressureViewHolder.mLayOut = null;
        homePressureViewHolder.mTvTitle = null;
        homePressureViewHolder.mTvDate = null;
        homePressureViewHolder.mImgEmpty = null;
        homePressureViewHolder.mPresureChart = null;
        homePressureViewHolder.mLayBottom = null;
        homePressureViewHolder.mLayContent = null;
        homePressureViewHolder.mTvPressure = null;
        homePressureViewHolder.mTvPressureState = null;
        homePressureViewHolder.mAnimView = null;
        super.unbind();
    }
}