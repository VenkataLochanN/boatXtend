package com.ido.life.module.home.pressure.detail.vertical;

import android.content.res.ColorStateList;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import com.boat.Xtend.two.R;
import com.ido.alexa.AlexaCustomSkillConstant;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.enums.PressureEnum;
import com.ido.life.module.home.chartdetail.vertical.BaseDetailViewHolder;
import com.ido.life.module.sport.view.SportPieChart;

/* JADX INFO: loaded from: classes2.dex */
public class PressureDetailBottomViewHolder extends BaseDetailViewHolder {

    @BindView(R.id.lay_all_pressure_state)
    public LinearLayout mLayAllPressureState;

    @BindView(R.id.lay_pipe)
    public LinearLayout mLayPipe;

    @BindView(R.id.spc_pressure)
    public SportPieChart mSportPieChart;

    @BindView(R.id.tv_all_pressure_state)
    public TextView mTvAllPressureState;

    @BindView(R.id.tv_all_pressure_tip)
    public TextView mTvAllPressureTip;

    @BindView(R.id.tv_dot_high)
    public TextView mTvDotHigh;

    @BindView(R.id.tv_dot_medium)
    public TextView mTvDotMedium;

    @BindView(R.id.tv_dot_normal)
    public TextView mTvDotNormal;

    @BindView(R.id.tv_dot_relax)
    public TextView mTvDotRelax;

    @BindView(R.id.tv_high_area)
    public TextView mTvHighArea;

    @BindView(R.id.tv_high_value)
    public TextView mTvHighValue;

    @BindView(R.id.tv_medium_area)
    public TextView mTvMediumArea;

    @BindView(R.id.tv_medium_value)
    public TextView mTvMediumValue;

    @BindView(R.id.tv_normal_area)
    public TextView mTvNormalArea;

    @BindView(R.id.tv_normal_value)
    public TextView mTvNormalValue;

    @BindView(R.id.tv_relax_area)
    public TextView mTvRelaxArea;

    @BindView(R.id.tv_relax_value)
    public TextView mTvRelaxValue;

    @BindView(R.id.tv_tip)
    public TextView mTvTip;

    @BindView(R.id.tv_type_high)
    public TextView mTvTypeHigh;

    @BindView(R.id.tv_type_menium)
    public TextView mTvTypeMedium;

    @BindView(R.id.tv_type_normal)
    public TextView mTvTypeNormal;

    @BindView(R.id.tv_type_relax)
    public TextView mTvTypeRelax;

    public PressureDetailBottomViewHolder(View view) {
        super(view);
        this.mTvDotRelax.setCompoundDrawableTintList(ColorStateList.valueOf(PressureEnum.RELAX.Color));
        this.mTvDotNormal.setCompoundDrawableTintList(ColorStateList.valueOf(PressureEnum.NORMAL.Color));
        this.mTvDotMedium.setCompoundDrawableTintList(ColorStateList.valueOf(PressureEnum.MEDIUM.Color));
        this.mTvDotHigh.setCompoundDrawableTintList(ColorStateList.valueOf(PressureEnum.HIGH.Color));
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailViewHolder
    public void refreshLanguage() {
        String languageText = LanguageUtil.getLanguageText(R.string.home_pressure_high);
        String languageText2 = LanguageUtil.getLanguageText(R.string.home_pressure_middle);
        String languageText3 = LanguageUtil.getLanguageText(R.string.home_pressure_normal);
        String languageText4 = LanguageUtil.getLanguageText(R.string.home_pressure_relax);
        this.mTvAllPressureTip.setText(LanguageUtil.getLanguageText(R.string.tip_all_pressure_test));
        this.mTvAllPressureState.setText(LanguageUtil.getLanguageText(R.string.go_open));
        int iMin = Math.min(Math.max(languageText.length(), Math.max(languageText2.length(), Math.max(languageText3.length(), languageText4.length()))), 4);
        this.mTvTypeHigh.setEms(iMin);
        this.mTvTypeMedium.setEms(iMin);
        this.mTvTypeNormal.setEms(iMin);
        this.mTvTypeRelax.setEms(iMin);
        this.mTvTypeHigh.setText(languageText);
        this.mTvTypeMedium.setText(languageText2);
        this.mTvTypeNormal.setText(languageText3);
        this.mTvTypeRelax.setText(languageText4);
    }

    @Override // com.ido.life.module.home.chartdetail.vertical.BaseDetailViewHolder
    public void setDefaultValue() {
        refreshLanguage();
        this.mTvHighArea.setText(String.format("%d-%d", Integer.valueOf(PressureEnum.HIGH.Min), Integer.valueOf(PressureEnum.HIGH.Max)));
        this.mTvMediumArea.setText(String.format("%d-%d", Integer.valueOf(PressureEnum.MEDIUM.Min), Integer.valueOf(PressureEnum.MEDIUM.Max)));
        this.mTvNormalArea.setText(String.format("%d-%d", Integer.valueOf(PressureEnum.NORMAL.Min), Integer.valueOf(PressureEnum.NORMAL.Max)));
        this.mTvRelaxArea.setText(String.format("%d-%d", Integer.valueOf(PressureEnum.RELAX.Min), Integer.valueOf(PressureEnum.RELAX.Max)));
        this.mTvHighValue.setText(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
        this.mTvMediumValue.setText(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
        this.mTvNormalValue.setText(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
        this.mTvRelaxValue.setText(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
    }
}