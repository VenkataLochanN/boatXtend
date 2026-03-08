package com.ido.life.module.device.activity;

import android.content.Intent;
import android.view.View;
import androidx.appcompat.widget.AppCompatCheckBox;
import butterknife.BindView;
import com.boat.Xtend.two.R;
import com.ido.life.base.BaseActivity;
import com.ido.life.util.RunTimeUtil;

/* JADX INFO: loaded from: classes2.dex */
public class RepetitionPeriodSettingActivity extends BaseActivity {
    public static final String REPETITION_PERIOD = "repetition_period";
    public static final int REPETITION_PERIOD_CODE = 66;

    @BindView(R.id.rcb_week_1)
    AppCompatCheckBox mRcbWeek_1;

    @BindView(R.id.rcb_week_2)
    AppCompatCheckBox mRcbWeek_2;

    @BindView(R.id.rcb_week_3)
    AppCompatCheckBox mRcbWeek_3;

    @BindView(R.id.rcb_week_4)
    AppCompatCheckBox mRcbWeek_4;

    @BindView(R.id.rcb_week_5)
    AppCompatCheckBox mRcbWeek_5;

    @BindView(R.id.rcb_week_6)
    AppCompatCheckBox mRcbWeek_6;

    @BindView(R.id.rcb_week_7)
    AppCompatCheckBox mRcbWeek_7;
    private boolean[] mRepetitionDays;
    private int mWeekStart;

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_repetition_period_setting;
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        this.mWeekStart = RunTimeUtil.getInstance().getWeekStart();
        this.mRepetitionDays = getIntent().getBooleanArrayExtra(REPETITION_PERIOD);
        boolean[] zArr = this.mRepetitionDays;
        if (zArr == null || zArr.length != 7) {
            this.mRepetitionDays = new boolean[7];
        }
        int i = this.mWeekStart;
        if (i == 2) {
            this.mRcbWeek_1.setChecked(this.mRepetitionDays[0]);
            this.mRcbWeek_2.setChecked(this.mRepetitionDays[1]);
            this.mRcbWeek_3.setChecked(this.mRepetitionDays[2]);
            this.mRcbWeek_4.setChecked(this.mRepetitionDays[3]);
            this.mRcbWeek_5.setChecked(this.mRepetitionDays[4]);
            this.mRcbWeek_6.setChecked(this.mRepetitionDays[5]);
            this.mRcbWeek_7.setChecked(this.mRepetitionDays[6]);
            return;
        }
        if (i == 7) {
            this.mRcbWeek_1.setChecked(this.mRepetitionDays[5]);
            this.mRcbWeek_2.setChecked(this.mRepetitionDays[6]);
            this.mRcbWeek_3.setChecked(this.mRepetitionDays[0]);
            this.mRcbWeek_4.setChecked(this.mRepetitionDays[1]);
            this.mRcbWeek_5.setChecked(this.mRepetitionDays[2]);
            this.mRcbWeek_6.setChecked(this.mRepetitionDays[3]);
            this.mRcbWeek_7.setChecked(this.mRepetitionDays[4]);
            return;
        }
        this.mRcbWeek_1.setChecked(this.mRepetitionDays[6]);
        this.mRcbWeek_2.setChecked(this.mRepetitionDays[0]);
        this.mRcbWeek_3.setChecked(this.mRepetitionDays[1]);
        this.mRcbWeek_4.setChecked(this.mRepetitionDays[2]);
        this.mRcbWeek_5.setChecked(this.mRepetitionDays[3]);
        this.mRcbWeek_6.setChecked(this.mRepetitionDays[4]);
        this.mRcbWeek_7.setChecked(this.mRepetitionDays[5]);
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        this.mTitleBar.setLeftOnClick(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$RepetitionPeriodSettingActivity$q0Ret83u-MVsEEuwMMLX3x_S-1w
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initEvent$0$RepetitionPeriodSettingActivity(view);
            }
        });
    }

    public /* synthetic */ void lambda$initEvent$0$RepetitionPeriodSettingActivity(View view) {
        onBackPressed();
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        this.mTitleBar.setTitle(getLanguageText(R.string.device_repetition));
        int i = this.mWeekStart;
        if (i == 2) {
            this.mRcbWeek_1.setText(getLanguageText(R.string.device_week_every_monday));
            this.mRcbWeek_2.setText(getLanguageText(R.string.device_week_every_tuesday));
            this.mRcbWeek_3.setText(getLanguageText(R.string.device_week_every_wednesday));
            this.mRcbWeek_4.setText(getLanguageText(R.string.device_week_every_thursday));
            this.mRcbWeek_5.setText(getLanguageText(R.string.device_week_every_friday));
            this.mRcbWeek_6.setText(getLanguageText(R.string.device_week_every_saturday));
            this.mRcbWeek_7.setText(getLanguageText(R.string.device_week_every_sunday));
            return;
        }
        if (i == 7) {
            this.mRcbWeek_1.setText(getLanguageText(R.string.device_week_every_saturday));
            this.mRcbWeek_2.setText(getLanguageText(R.string.device_week_every_sunday));
            this.mRcbWeek_3.setText(getLanguageText(R.string.device_week_every_monday));
            this.mRcbWeek_4.setText(getLanguageText(R.string.device_week_every_tuesday));
            this.mRcbWeek_5.setText(getLanguageText(R.string.device_week_every_wednesday));
            this.mRcbWeek_6.setText(getLanguageText(R.string.device_week_every_thursday));
            this.mRcbWeek_7.setText(getLanguageText(R.string.device_week_every_friday));
            return;
        }
        this.mRcbWeek_1.setText(getLanguageText(R.string.device_week_every_sunday));
        this.mRcbWeek_2.setText(getLanguageText(R.string.device_week_every_monday));
        this.mRcbWeek_3.setText(getLanguageText(R.string.device_week_every_tuesday));
        this.mRcbWeek_4.setText(getLanguageText(R.string.device_week_every_wednesday));
        this.mRcbWeek_5.setText(getLanguageText(R.string.device_week_every_thursday));
        this.mRcbWeek_6.setText(getLanguageText(R.string.device_week_every_friday));
        this.mRcbWeek_7.setText(getLanguageText(R.string.device_week_every_saturday));
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        int i = this.mWeekStart;
        if (i == 2) {
            this.mRepetitionDays[0] = this.mRcbWeek_1.isChecked();
            this.mRepetitionDays[1] = this.mRcbWeek_2.isChecked();
            this.mRepetitionDays[2] = this.mRcbWeek_3.isChecked();
            this.mRepetitionDays[3] = this.mRcbWeek_4.isChecked();
            this.mRepetitionDays[4] = this.mRcbWeek_5.isChecked();
            this.mRepetitionDays[5] = this.mRcbWeek_6.isChecked();
            this.mRepetitionDays[6] = this.mRcbWeek_7.isChecked();
        } else if (i == 7) {
            this.mRepetitionDays[0] = this.mRcbWeek_3.isChecked();
            this.mRepetitionDays[1] = this.mRcbWeek_4.isChecked();
            this.mRepetitionDays[2] = this.mRcbWeek_5.isChecked();
            this.mRepetitionDays[3] = this.mRcbWeek_6.isChecked();
            this.mRepetitionDays[4] = this.mRcbWeek_7.isChecked();
            this.mRepetitionDays[5] = this.mRcbWeek_1.isChecked();
            this.mRepetitionDays[6] = this.mRcbWeek_2.isChecked();
        } else {
            this.mRepetitionDays[0] = this.mRcbWeek_2.isChecked();
            this.mRepetitionDays[1] = this.mRcbWeek_3.isChecked();
            this.mRepetitionDays[2] = this.mRcbWeek_4.isChecked();
            this.mRepetitionDays[3] = this.mRcbWeek_5.isChecked();
            this.mRepetitionDays[4] = this.mRcbWeek_6.isChecked();
            this.mRepetitionDays[5] = this.mRcbWeek_7.isChecked();
            this.mRepetitionDays[6] = this.mRcbWeek_1.isChecked();
        }
        Intent intent = new Intent();
        intent.putExtra(REPETITION_PERIOD, this.mRepetitionDays);
        setResult(66, intent);
        finish();
    }
}