package com.ido.life.module.device.activity;

import android.view.View;
import androidx.appcompat.widget.AppCompatCheckBox;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes2.dex */
public class RepetitionPeriodSettingActivity_ViewBinding implements Unbinder {
    private RepetitionPeriodSettingActivity target;

    public RepetitionPeriodSettingActivity_ViewBinding(RepetitionPeriodSettingActivity repetitionPeriodSettingActivity) {
        this(repetitionPeriodSettingActivity, repetitionPeriodSettingActivity.getWindow().getDecorView());
    }

    public RepetitionPeriodSettingActivity_ViewBinding(RepetitionPeriodSettingActivity repetitionPeriodSettingActivity, View view) {
        this.target = repetitionPeriodSettingActivity;
        repetitionPeriodSettingActivity.mRcbWeek_1 = (AppCompatCheckBox) Utils.findRequiredViewAsType(view, R.id.rcb_week_1, "field 'mRcbWeek_1'", AppCompatCheckBox.class);
        repetitionPeriodSettingActivity.mRcbWeek_2 = (AppCompatCheckBox) Utils.findRequiredViewAsType(view, R.id.rcb_week_2, "field 'mRcbWeek_2'", AppCompatCheckBox.class);
        repetitionPeriodSettingActivity.mRcbWeek_3 = (AppCompatCheckBox) Utils.findRequiredViewAsType(view, R.id.rcb_week_3, "field 'mRcbWeek_3'", AppCompatCheckBox.class);
        repetitionPeriodSettingActivity.mRcbWeek_4 = (AppCompatCheckBox) Utils.findRequiredViewAsType(view, R.id.rcb_week_4, "field 'mRcbWeek_4'", AppCompatCheckBox.class);
        repetitionPeriodSettingActivity.mRcbWeek_5 = (AppCompatCheckBox) Utils.findRequiredViewAsType(view, R.id.rcb_week_5, "field 'mRcbWeek_5'", AppCompatCheckBox.class);
        repetitionPeriodSettingActivity.mRcbWeek_6 = (AppCompatCheckBox) Utils.findRequiredViewAsType(view, R.id.rcb_week_6, "field 'mRcbWeek_6'", AppCompatCheckBox.class);
        repetitionPeriodSettingActivity.mRcbWeek_7 = (AppCompatCheckBox) Utils.findRequiredViewAsType(view, R.id.rcb_week_7, "field 'mRcbWeek_7'", AppCompatCheckBox.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        RepetitionPeriodSettingActivity repetitionPeriodSettingActivity = this.target;
        if (repetitionPeriodSettingActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        repetitionPeriodSettingActivity.mRcbWeek_1 = null;
        repetitionPeriodSettingActivity.mRcbWeek_2 = null;
        repetitionPeriodSettingActivity.mRcbWeek_3 = null;
        repetitionPeriodSettingActivity.mRcbWeek_4 = null;
        repetitionPeriodSettingActivity.mRcbWeek_5 = null;
        repetitionPeriodSettingActivity.mRcbWeek_6 = null;
        repetitionPeriodSettingActivity.mRcbWeek_7 = null;
    }
}