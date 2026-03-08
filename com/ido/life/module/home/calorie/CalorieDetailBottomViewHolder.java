package com.ido.life.module.home.calorie;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import butterknife.BindView;
import com.boat.Xtend.two.R;
import com.ido.alexa.AlexaCustomSkillConstant;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.ResourceUtil;
import com.ido.life.module.home.chartdetail.vertical.BaseDetailViewHolder;

/* JADX INFO: loaded from: classes2.dex */
public class CalorieDetailBottomViewHolder extends BaseDetailViewHolder {

    @BindView(R.id.card_left)
    public CardView mLeftCard;

    @BindView(R.id.bottom_left_content)
    public LinearLayout mLeftLayContent;

    @BindView(R.id.card_right)
    public CardView mRightCard;

    @BindView(R.id.right_lay_content)
    public LinearLayout mRightLayContent;

    @BindView(R.id.tv_left_calorie)
    public TextView mTvLeftCalorie;

    @BindView(R.id.tv_left_calorie_area)
    public TextView mTvLeftCalorieArea;

    @BindView(R.id.tv_left_calorie_unit)
    public TextView mTvLeftCalorieUnit;

    @BindView(R.id.tv_right_calorie)
    public TextView mTvRightCalorie;

    @BindView(R.id.tv_right_calorie_area)
    public TextView mTvRightCalorieArea;

    @BindView(R.id.tv_right_calorie_unit)
    public TextView mTvRightCalorieUnit;

    public CalorieDetailBottomViewHolder(View view) {
        super(view);
        setDefaultValue();
        refreshLanguage();
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailViewHolder
    public void refreshLanguage() {
        this.mTvLeftCalorieUnit.setText(LanguageUtil.getLanguageText(R.string.public_heat_calorie));
        this.mTvLeftCalorieArea.setText(LanguageUtil.getLanguageText(R.string.home_calorie_all));
        this.mTvRightCalorieUnit.setText(LanguageUtil.getLanguageText(R.string.public_heat_calorie));
        this.mTvRightCalorieArea.setText(LanguageUtil.getLanguageText(R.string.home_calorie_activity));
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailViewHolder
    public void setDefaultValue() {
        this.mTvLeftCalorie.setText(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
        this.mTvRightCalorie.setText(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
        this.mRightLayContent.setBackgroundResource(R.drawable.shape_yellow_linear);
        this.mLeftLayContent.setBackground(null);
        int color = ResourceUtil.getColor(R.color.black);
        int color2 = ResourceUtil.getColor(R.color.white);
        this.mTvRightCalorieArea.setTextColor(color2);
        this.mTvRightCalorie.setTextColor(color2);
        this.mTvRightCalorieUnit.setTextColor(color2);
        this.mTvLeftCalorieArea.setTextColor(color);
        this.mTvLeftCalorie.setTextColor(color);
        this.mTvLeftCalorieUnit.setTextColor(color);
    }

    public void updateSelectStatus(boolean z) {
        int color = ResourceUtil.getColor(R.color.white);
        int color2 = ResourceUtil.getColor(R.color.black);
        if (z) {
            this.mLeftLayContent.setBackgroundResource(R.drawable.shape_yellow_linear);
            this.mRightLayContent.setBackground(null);
            this.mTvLeftCalorie.setTextColor(color);
            this.mTvLeftCalorieArea.setTextColor(color);
            this.mTvLeftCalorieUnit.setTextColor(color);
            this.mTvRightCalorie.setTextColor(color2);
            this.mTvRightCalorieUnit.setTextColor(color2);
            this.mTvRightCalorieArea.setTextColor(color2);
            return;
        }
        this.mLeftLayContent.setBackground(null);
        this.mRightLayContent.setBackgroundResource(R.drawable.shape_yellow_linear);
        this.mTvLeftCalorieUnit.setTextColor(color2);
        this.mTvLeftCalorie.setTextColor(color2);
        this.mTvLeftCalorieArea.setTextColor(color2);
        this.mTvRightCalorieArea.setTextColor(color);
        this.mTvRightCalorie.setTextColor(color);
        this.mTvRightCalorieUnit.setTextColor(color);
    }
}