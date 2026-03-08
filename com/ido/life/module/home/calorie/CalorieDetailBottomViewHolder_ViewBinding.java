package com.ido.life.module.home.calorie;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes2.dex */
public class CalorieDetailBottomViewHolder_ViewBinding implements Unbinder {
    private CalorieDetailBottomViewHolder target;

    public CalorieDetailBottomViewHolder_ViewBinding(CalorieDetailBottomViewHolder calorieDetailBottomViewHolder, View view) {
        this.target = calorieDetailBottomViewHolder;
        calorieDetailBottomViewHolder.mLeftCard = (CardView) Utils.findRequiredViewAsType(view, R.id.card_left, "field 'mLeftCard'", CardView.class);
        calorieDetailBottomViewHolder.mTvLeftCalorie = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_left_calorie, "field 'mTvLeftCalorie'", TextView.class);
        calorieDetailBottomViewHolder.mTvLeftCalorieUnit = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_left_calorie_unit, "field 'mTvLeftCalorieUnit'", TextView.class);
        calorieDetailBottomViewHolder.mTvLeftCalorieArea = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_left_calorie_area, "field 'mTvLeftCalorieArea'", TextView.class);
        calorieDetailBottomViewHolder.mRightCard = (CardView) Utils.findRequiredViewAsType(view, R.id.card_right, "field 'mRightCard'", CardView.class);
        calorieDetailBottomViewHolder.mTvRightCalorie = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_right_calorie, "field 'mTvRightCalorie'", TextView.class);
        calorieDetailBottomViewHolder.mTvRightCalorieUnit = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_right_calorie_unit, "field 'mTvRightCalorieUnit'", TextView.class);
        calorieDetailBottomViewHolder.mTvRightCalorieArea = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_right_calorie_area, "field 'mTvRightCalorieArea'", TextView.class);
        calorieDetailBottomViewHolder.mLeftLayContent = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.bottom_left_content, "field 'mLeftLayContent'", LinearLayout.class);
        calorieDetailBottomViewHolder.mRightLayContent = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.right_lay_content, "field 'mRightLayContent'", LinearLayout.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        CalorieDetailBottomViewHolder calorieDetailBottomViewHolder = this.target;
        if (calorieDetailBottomViewHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        calorieDetailBottomViewHolder.mLeftCard = null;
        calorieDetailBottomViewHolder.mTvLeftCalorie = null;
        calorieDetailBottomViewHolder.mTvLeftCalorieUnit = null;
        calorieDetailBottomViewHolder.mTvLeftCalorieArea = null;
        calorieDetailBottomViewHolder.mRightCard = null;
        calorieDetailBottomViewHolder.mTvRightCalorie = null;
        calorieDetailBottomViewHolder.mTvRightCalorieUnit = null;
        calorieDetailBottomViewHolder.mTvRightCalorieArea = null;
        calorieDetailBottomViewHolder.mLeftLayContent = null;
        calorieDetailBottomViewHolder.mRightLayContent = null;
    }
}