package com.ido.life.module.sport.setting.explain;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.common.base.BaseCoreActivity;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.customview.viewgroup.BottomRateView;
import com.ido.life.customview.viewgroup.LeftMiddleRightView;
import com.ido.life.customview.viewgroup.OptionView;

/* JADX INFO: loaded from: classes2.dex */
public class RateExplainActivity extends BaseCoreActivity {

    @BindView(R.id.opt_sport_heart_rate)
    OptionView mOptSportHeartRate;

    @BindView(R.id.title_text)
    TextView mTitleText;

    @BindView(R.id.tv_rate_range)
    TextView mTvRateRange;

    @BindView(R.id.tv_rate_range_explain)
    TextView mTvRateRangeExplain;

    @BindView(R.id.view_bottom_aerobic_endurance)
    BottomRateView mViewBottomAerobicEndurance;

    @BindView(R.id.view_bottom_anaerobic_endurance)
    BottomRateView mViewBottomAnaerobicEndurance;

    @BindView(R.id.view_bottom_burning_grease)
    BottomRateView mViewBottomBurningGrease;

    @BindView(R.id.view_bottom_limit)
    BottomRateView mViewBottomLimit;

    @BindView(R.id.view_bottom_warm_up)
    BottomRateView mViewBottomWarmUp;

    @BindView(R.id.view_lmr_aerobic_endurance)
    LeftMiddleRightView mViewLmrAerobicEndurance;

    @BindView(R.id.view_lmr_anaerobic_endurance)
    LeftMiddleRightView mViewLmrAnaerobicEndurance;

    @BindView(R.id.view_lmr_burning_grease)
    LeftMiddleRightView mViewLmrBurningGrease;

    @BindView(R.id.view_lmr_limit)
    LeftMiddleRightView mViewLmrLimit;

    @BindView(R.id.view_lmr_title)
    LeftMiddleRightView mViewLmrTitle;

    @BindView(R.id.view_lmt_warm_up)
    LeftMiddleRightView mViewLmtWarmUp;

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_rate_explain;
    }

    public static void start(Context context) {
        context.startActivity(new Intent(context, (Class<?>) RateExplainActivity.class));
    }

    @Override // com.ido.common.base.BaseCoreActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        this.mTitleText.setText(LanguageUtil.getLanguageText(R.string.sport_setting_rate_explain_title));
        this.mOptSportHeartRate.setStartText(LanguageUtil.getLanguageText(R.string.sport_setting_rate_explain_calculation_formula));
        this.mOptSportHeartRate.setEndText(LanguageUtil.getLanguageText(R.string.sport_setting_rate_explain_calculation_formula_value));
        this.mTvRateRange.setText(LanguageUtil.getLanguageText(R.string.sport_setting_rate_explain_rate_range));
        this.mViewLmrTitle.setStartText(LanguageUtil.getLanguageText(R.string.sport_setting_rate_explain_range));
        this.mViewLmrTitle.setMiddleText(LanguageUtil.getLanguageText(R.string.sport_setting_rate_explain_rate_max));
        this.mViewLmrTitle.setEndText(LanguageUtil.getLanguageText(R.string.sport_setting_rate_explain_suggest_time));
        this.mViewLmtWarmUp.setStartText(LanguageUtil.getLanguageText(R.string.sport_setting_warm_up));
        this.mViewLmtWarmUp.setEndText(LanguageUtil.getLanguageText(R.string.sport_setting_warm_up_time));
        this.mViewLmrBurningGrease.setStartText(LanguageUtil.getLanguageText(R.string.sport_setting_fat_burning));
        this.mViewLmrBurningGrease.setEndText(LanguageUtil.getLanguageText(R.string.sport_setting_fat_burning_time));
        this.mViewLmrAerobicEndurance.setStartText(LanguageUtil.getLanguageText(R.string.sport_setting_aerobic_endurance));
        this.mViewLmrAerobicEndurance.setEndText(LanguageUtil.getLanguageText(R.string.sport_setting_aerobic_endurance_time));
        this.mViewLmrAnaerobicEndurance.setStartText(LanguageUtil.getLanguageText(R.string.sport_setting_anaerobic_endurance));
        this.mViewLmrAnaerobicEndurance.setEndText(LanguageUtil.getLanguageText(R.string.sport_setting_anaerobic_endurance_time));
        this.mTvRateRangeExplain.setText(LanguageUtil.getLanguageText(R.string.sport_setting_rate_explain_rate_range_diff));
        this.mViewBottomWarmUp.setTopText(LanguageUtil.getLanguageText(R.string.sport_setting_warm_up));
        this.mViewBottomWarmUp.setBottomText(LanguageUtil.getLanguageText(R.string.sport_setting_warm_up_explain));
        this.mViewBottomBurningGrease.setTopText(LanguageUtil.getLanguageText(R.string.sport_setting_fat_burning));
        this.mViewBottomBurningGrease.setBottomText(LanguageUtil.getLanguageText(R.string.sport_setting_fat_burning_explain));
        this.mViewBottomAerobicEndurance.setTopText(LanguageUtil.getLanguageText(R.string.sport_setting_aerobic_endurance));
        this.mViewBottomAerobicEndurance.setBottomText(LanguageUtil.getLanguageText(R.string.sport_setting_aerobic_endurance_explain));
        this.mViewBottomAnaerobicEndurance.setTopText(LanguageUtil.getLanguageText(R.string.sport_setting_anaerobic_endurance));
        this.mViewBottomAnaerobicEndurance.setBottomText(LanguageUtil.getLanguageText(R.string.sport_setting_anaerobic_endurance_explain));
        this.mViewBottomLimit.setTopText(LanguageUtil.getLanguageText(R.string.sport_setting_limitation));
        this.mViewBottomLimit.setBottomText(LanguageUtil.getLanguageText(R.string.sport_setting_limitation_explain));
    }

    @OnClick({R.id.title_leftBtn})
    public void back(View view) {
        ActivityCompat.finishAfterTransition(this);
    }
}