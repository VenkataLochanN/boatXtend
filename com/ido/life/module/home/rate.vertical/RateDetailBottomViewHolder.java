package com.ido.life.module.home.rate.vertical;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import butterknife.BindView;
import com.boat.Xtend.two.R;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.ResourceUtil;
import com.ido.life.module.home.chartdetail.vertical.BaseDetailViewHolder;

/* JADX INFO: loaded from: classes2.dex */
public class RateDetailBottomViewHolder extends BaseDetailViewHolder {

    @BindView(R.id.card_left)
    public CardView mLeftCard;

    @BindView(R.id.bottom_left_content)
    public LinearLayout mLeftLayContent;

    @BindView(R.id.card_right)
    public CardView mRightCard;

    @BindView(R.id.right_lay_content)
    public LinearLayout mRightLayContent;

    @BindView(R.id.tv_left_rate)
    public TextView mTvLeftRate;

    @BindView(R.id.tv_left_rate_area)
    public TextView mTvLeftRateArea;

    @BindView(R.id.tv_left_rate_unit)
    public TextView mTvLeftRateUnit;

    @BindView(R.id.tv_right_rate)
    public TextView mTvRightRate;

    @BindView(R.id.tv_right_rate_area)
    public TextView mTvRightRateArea;

    @BindView(R.id.tv_right_rate_unit)
    public TextView mTvRightRateUnit;

    public RateDetailBottomViewHolder(View view) {
        super(view);
        setDefaultValue();
        refreshLanguage();
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailViewHolder
    public void refreshLanguage() {
        this.mTvLeftRateUnit.setText(LanguageUtil.getLanguageText(R.string.device_heart_rate_unit));
        this.mTvLeftRateArea.setText(LanguageUtil.getLanguageText(R.string.home_heartbeat_range_rate));
        this.mTvRightRateUnit.setText(LanguageUtil.getLanguageText(R.string.device_heart_rate_unit));
        this.mTvRightRateArea.setText(LanguageUtil.getLanguageText(R.string.home_heartbeat_quiet_rate));
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailViewHolder
    public void setDefaultValue() {
        this.mTvLeftRate.setText("--");
        this.mTvRightRate.setText("--");
        this.mLeftLayContent.setBackgroundResource(R.drawable.detail_rate_area_btn_bg);
        this.mRightLayContent.setBackground(null);
        int color = ResourceUtil.getColor(R.color.black);
        int color2 = ResourceUtil.getColor(R.color.white);
        this.mTvLeftRateArea.setTextColor(color2);
        this.mTvLeftRate.setTextColor(color2);
        this.mTvLeftRateUnit.setTextColor(color2);
        this.mTvRightRateArea.setTextColor(color);
        this.mTvRightRate.setTextColor(color);
        this.mTvRightRateUnit.setTextColor(color);
    }

    public void updateSelectStatus(boolean z) {
        int color = ResourceUtil.getColor(R.color.white);
        int color2 = ResourceUtil.getColor(R.color.black);
        if (z) {
            this.mLeftLayContent.setBackgroundResource(R.drawable.detail_rate_area_btn_bg);
            this.mRightLayContent.setBackground(null);
            this.mTvLeftRate.setTextColor(color);
            this.mTvLeftRateArea.setTextColor(color);
            this.mTvLeftRateUnit.setTextColor(color);
            this.mTvRightRate.setTextColor(color2);
            this.mTvRightRateUnit.setTextColor(color2);
            this.mTvRightRateArea.setTextColor(color2);
            return;
        }
        this.mLeftLayContent.setBackground(null);
        this.mRightLayContent.setBackgroundResource(R.drawable.detail_rate_area_btn_bg);
        this.mTvLeftRateUnit.setTextColor(color2);
        this.mTvLeftRate.setTextColor(color2);
        this.mTvLeftRateArea.setTextColor(color2);
        this.mTvRightRateArea.setTextColor(color);
        this.mTvRightRate.setTextColor(color);
        this.mTvRightRateUnit.setTextColor(color);
    }
}