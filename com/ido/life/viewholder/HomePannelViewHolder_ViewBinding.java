package com.ido.life.viewholder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.life.customview.maincard.MainPannelCircleView;

/* JADX INFO: loaded from: classes3.dex */
public class HomePannelViewHolder_ViewBinding extends BaseHomeItemViewHolder_ViewBinding {
    private HomePannelViewHolder target;

    public HomePannelViewHolder_ViewBinding(HomePannelViewHolder homePannelViewHolder, View view) {
        super(homePannelViewHolder, view);
        this.target = homePannelViewHolder;
        homePannelViewHolder.ll_activity_top = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_activity_top, "field 'll_activity_top'", LinearLayout.class);
        homePannelViewHolder.ll_activity_bottom = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_activity_bottom, "field 'll_activity_bottom'", LinearLayout.class);
        homePannelViewHolder.mTvActiveTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_active, "field 'mTvActiveTitle'", TextView.class);
        homePannelViewHolder.mActiveCircleView = (MainPannelCircleView) Utils.findRequiredViewAsType(view, R.id.img_active, "field 'mActiveCircleView'", MainPannelCircleView.class);
        homePannelViewHolder.mTvCalory = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_calory, "field 'mTvCalory'", TextView.class);
        homePannelViewHolder.mTvCaloryUnit = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_calory_unit, "field 'mTvCaloryUnit'", TextView.class);
        homePannelViewHolder.llPressTop = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_press_top, "field 'llPressTop'", LinearLayout.class);
        homePannelViewHolder.llPressBottom = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_press_bottom, "field 'llPressBottom'", LinearLayout.class);
        homePannelViewHolder.mTvPressTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_press, "field 'mTvPressTitle'", TextView.class);
        homePannelViewHolder.mPressCircleView = (MainPannelCircleView) Utils.findRequiredViewAsType(view, R.id.img_press, "field 'mPressCircleView'", MainPannelCircleView.class);
        homePannelViewHolder.mTvMin = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_min, "field 'mTvMin'", TextView.class);
        homePannelViewHolder.mTvMinUnit = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_min_unit, "field 'mTvMinUnit'", TextView.class);
        homePannelViewHolder.llWalkTop = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_walk_top, "field 'llWalkTop'", LinearLayout.class);
        homePannelViewHolder.llWalkBottom = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_walk_bottom, "field 'llWalkBottom'", LinearLayout.class);
        homePannelViewHolder.mTvWalkTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_walk, "field 'mTvWalkTitle'", TextView.class);
        homePannelViewHolder.mWalkCircleView = (MainPannelCircleView) Utils.findRequiredViewAsType(view, R.id.img_walk, "field 'mWalkCircleView'", MainPannelCircleView.class);
        homePannelViewHolder.mTvWalkHour = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_hour, "field 'mTvWalkHour'", TextView.class);
        homePannelViewHolder.mTvWalkUnit = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_hour_unit, "field 'mTvWalkUnit'", TextView.class);
        homePannelViewHolder.mTvCalorieDataChange = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_calorie_data_change, "field 'mTvCalorieDataChange'", TextView.class);
        homePannelViewHolder.mTvStrengthDataChange = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_strength_data_change, "field 'mTvStrengthDataChange'", TextView.class);
        homePannelViewHolder.mTvWalkHourDataChange = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_hour_data_change, "field 'mTvWalkHourDataChange'", TextView.class);
    }

    @Override // com.ido.life.viewholder.BaseHomeItemViewHolder_ViewBinding, butterknife.Unbinder
    public void unbind() {
        HomePannelViewHolder homePannelViewHolder = this.target;
        if (homePannelViewHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        homePannelViewHolder.ll_activity_top = null;
        homePannelViewHolder.ll_activity_bottom = null;
        homePannelViewHolder.mTvActiveTitle = null;
        homePannelViewHolder.mActiveCircleView = null;
        homePannelViewHolder.mTvCalory = null;
        homePannelViewHolder.mTvCaloryUnit = null;
        homePannelViewHolder.llPressTop = null;
        homePannelViewHolder.llPressBottom = null;
        homePannelViewHolder.mTvPressTitle = null;
        homePannelViewHolder.mPressCircleView = null;
        homePannelViewHolder.mTvMin = null;
        homePannelViewHolder.mTvMinUnit = null;
        homePannelViewHolder.llWalkTop = null;
        homePannelViewHolder.llWalkBottom = null;
        homePannelViewHolder.mTvWalkTitle = null;
        homePannelViewHolder.mWalkCircleView = null;
        homePannelViewHolder.mTvWalkHour = null;
        homePannelViewHolder.mTvWalkUnit = null;
        homePannelViewHolder.mTvCalorieDataChange = null;
        homePannelViewHolder.mTvStrengthDataChange = null;
        homePannelViewHolder.mTvWalkHourDataChange = null;
        super.unbind();
    }
}