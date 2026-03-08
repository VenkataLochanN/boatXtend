package com.ido.life.viewholder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.life.customview.charter.HomeStepCharterBar;
import com.ido.life.customview.maincard.MainPannelCircleView;

/* JADX INFO: loaded from: classes3.dex */
public class HomeStepViewHolder_ViewBinding extends BaseHomeItemViewHolder_ViewBinding {
    private HomeStepViewHolder target;

    public HomeStepViewHolder_ViewBinding(HomeStepViewHolder homeStepViewHolder, View view) {
        super(homeStepViewHolder, view);
        this.target = homeStepViewHolder;
        homeStepViewHolder.mLayStep = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.lay_step, "field 'mLayStep'", LinearLayout.class);
        homeStepViewHolder.mStepCircleView = (MainPannelCircleView) Utils.findRequiredViewAsType(view, R.id.step_progress_circle, "field 'mStepCircleView'", MainPannelCircleView.class);
        homeStepViewHolder.mTvStep = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_step, "field 'mTvStep'", TextView.class);
        homeStepViewHolder.mTvStepUnit = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_step_unit, "field 'mTvStepUnit'", TextView.class);
        homeStepViewHolder.mChatStep = (HomeStepCharterBar) Utils.findRequiredViewAsType(view, R.id.chat_step, "field 'mChatStep'", HomeStepCharterBar.class);
        homeStepViewHolder.mTvDistanceTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_title_distance, "field 'mTvDistanceTitle'", TextView.class);
        homeStepViewHolder.mTvDistance = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_distance, "field 'mTvDistance'", TextView.class);
        homeStepViewHolder.mTvDistanceUnit = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_distance_unit, "field 'mTvDistanceUnit'", TextView.class);
        homeStepViewHolder.mLayDistance = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.lay_distance, "field 'mLayDistance'", LinearLayout.class);
        homeStepViewHolder.mLayStepTop = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.lay_step_top, "field 'mLayStepTop'", LinearLayout.class);
        homeStepViewHolder.mTvDataChange = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_data_change, "field 'mTvDataChange'", TextView.class);
    }

    @Override // com.ido.life.viewholder.BaseHomeItemViewHolder_ViewBinding, butterknife.Unbinder
    public void unbind() {
        HomeStepViewHolder homeStepViewHolder = this.target;
        if (homeStepViewHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        homeStepViewHolder.mLayStep = null;
        homeStepViewHolder.mStepCircleView = null;
        homeStepViewHolder.mTvStep = null;
        homeStepViewHolder.mTvStepUnit = null;
        homeStepViewHolder.mChatStep = null;
        homeStepViewHolder.mTvDistanceTitle = null;
        homeStepViewHolder.mTvDistance = null;
        homeStepViewHolder.mTvDistanceUnit = null;
        homeStepViewHolder.mLayDistance = null;
        homeStepViewHolder.mLayStepTop = null;
        homeStepViewHolder.mTvDataChange = null;
        super.unbind();
    }
}