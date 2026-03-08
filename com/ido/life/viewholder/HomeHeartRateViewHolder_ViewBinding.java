package com.ido.life.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.life.customview.HomeIconAnimView;
import com.ido.life.customview.charter.HeartRateCubicChartBar;

/* JADX INFO: loaded from: classes3.dex */
public class HomeHeartRateViewHolder_ViewBinding extends BaseHomeItemViewHolder_ViewBinding {
    private HomeHeartRateViewHolder target;

    public HomeHeartRateViewHolder_ViewBinding(HomeHeartRateViewHolder homeHeartRateViewHolder, View view) {
        super(homeHeartRateViewHolder, view);
        this.target = homeHeartRateViewHolder;
        homeHeartRateViewHolder.mLayOut = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.lay_out, "field 'mLayOut'", LinearLayout.class);
        homeHeartRateViewHolder.mTvTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_title, "field 'mTvTitle'", TextView.class);
        homeHeartRateViewHolder.mTvDate = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_date, "field 'mTvDate'", TextView.class);
        homeHeartRateViewHolder.mLayContent = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.lay_content, "field 'mLayContent'", LinearLayout.class);
        homeHeartRateViewHolder.mImgEmpty = (ImageView) Utils.findRequiredViewAsType(view, R.id.img_empty, "field 'mImgEmpty'", ImageView.class);
        homeHeartRateViewHolder.mHeartRateChart = (HeartRateCubicChartBar) Utils.findRequiredViewAsType(view, R.id.chat_heart, "field 'mHeartRateChart'", HeartRateCubicChartBar.class);
        homeHeartRateViewHolder.mLayBottom = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.lay_bottom, "field 'mLayBottom'", LinearLayout.class);
        homeHeartRateViewHolder.mTvHeartRate = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_heart_rate, "field 'mTvHeartRate'", TextView.class);
        homeHeartRateViewHolder.mTvHeartRateUnit = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_heart_rate_unit, "field 'mTvHeartRateUnit'", TextView.class);
        homeHeartRateViewHolder.mAnimView = (HomeIconAnimView) Utils.findRequiredViewAsType(view, R.id.anim_view, "field 'mAnimView'", HomeIconAnimView.class);
    }

    @Override // com.ido.life.viewholder.BaseHomeItemViewHolder_ViewBinding, butterknife.Unbinder
    public void unbind() {
        HomeHeartRateViewHolder homeHeartRateViewHolder = this.target;
        if (homeHeartRateViewHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        homeHeartRateViewHolder.mLayOut = null;
        homeHeartRateViewHolder.mTvTitle = null;
        homeHeartRateViewHolder.mTvDate = null;
        homeHeartRateViewHolder.mLayContent = null;
        homeHeartRateViewHolder.mImgEmpty = null;
        homeHeartRateViewHolder.mHeartRateChart = null;
        homeHeartRateViewHolder.mLayBottom = null;
        homeHeartRateViewHolder.mTvHeartRate = null;
        homeHeartRateViewHolder.mTvHeartRateUnit = null;
        homeHeartRateViewHolder.mAnimView = null;
        super.unbind();
    }
}