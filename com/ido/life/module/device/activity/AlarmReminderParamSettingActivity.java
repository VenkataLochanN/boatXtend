package com.ido.life.module.device.activity;

import android.content.Intent;
import android.view.View;
import android.widget.RadioGroup;
import butterknife.BindView;
import com.boat.Xtend.two.R;
import com.ido.life.base.BaseActivity;
import com.ido.life.customview.MediumRadioButton;

/* JADX INFO: loaded from: classes2.dex */
public class AlarmReminderParamSettingActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    public static final int CODE_ALARM_REMINDER_PARAM = 11;
    public static final String INDEX_ALARM_PARAM_CHECKED = "checked_index";
    public static final String TYPE_ALARM_REMINDER_PARAM = "param_type";
    public static final int TYPE_REMINDER_INTERVAL = 10;
    public static final int TYPE_REMINDER_TIMES = 20;
    private int mIndex;

    @BindView(R.id.mrb_item_1)
    MediumRadioButton mMrbItem1;

    @BindView(R.id.mrb_item_2)
    MediumRadioButton mMrbItem2;

    @BindView(R.id.mrb_item_3)
    MediumRadioButton mMrbItem3;

    @BindView(R.id.mrb_item_4)
    MediumRadioButton mMrbItem4;

    @BindView(R.id.radio_group)
    RadioGroup mRadioGroup;
    private int mType;

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_alarm_reminder_param_setting;
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        Intent intent = getIntent();
        this.mType = intent.getIntExtra(TYPE_ALARM_REMINDER_PARAM, 10);
        this.mIndex = intent.getIntExtra(INDEX_ALARM_PARAM_CHECKED, 0);
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        this.mRadioGroup.setOnCheckedChangeListener(this);
        this.mTitleBar.setLeftOnClick(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$AlarmReminderParamSettingActivity$ppJ91yRmkl3XItigZstKPrAzly0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initEvent$0$AlarmReminderParamSettingActivity(view);
            }
        });
        if (this.mType == 10) {
            this.mMrbItem4.setVisibility(8);
        }
        int i = this.mIndex;
        if (i == 0) {
            this.mMrbItem1.setChecked(true);
            return;
        }
        if (i == 1) {
            this.mMrbItem2.setChecked(true);
        } else if (i == 2) {
            this.mMrbItem3.setChecked(true);
        } else {
            if (i != 3) {
                return;
            }
            this.mMrbItem4.setChecked(true);
        }
    }

    public /* synthetic */ void lambda$initEvent$0$AlarmReminderParamSettingActivity(View view) {
        onBackPressed();
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        if (this.mType == 10) {
            this.mTitleBar.setTitle(getLanguageText(R.string.device_remind_the_interval_later));
            this.mMrbItem1.setText(String.format(getLanguageText(R.string.device_x_minuter), 5));
            this.mMrbItem2.setText(String.format(getLanguageText(R.string.device_x_minuter), 10));
            this.mMrbItem3.setText(String.format(getLanguageText(R.string.device_x_minuter), 15));
            return;
        }
        this.mTitleBar.setTitle(getLanguageText(R.string.device_repeated_reminders_times));
        this.mMrbItem1.setText(getLanguageText(R.string.device_without_repetition));
        this.mMrbItem2.setText(String.format(getLanguageText(R.string.device_x_times), 1));
        this.mMrbItem3.setText(String.format(getLanguageText(R.string.device_x_times), 2));
        this.mMrbItem4.setText(String.format(getLanguageText(R.string.device_x_times), 3));
    }

    @Override // android.widget.RadioGroup.OnCheckedChangeListener
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.mrb_item_1 /* 2131363067 */:
                this.mIndex = 0;
                break;
            case R.id.mrb_item_2 /* 2131363068 */:
                this.mIndex = 1;
                break;
            case R.id.mrb_item_3 /* 2131363069 */:
                this.mIndex = 2;
                break;
            case R.id.mrb_item_4 /* 2131363070 */:
                this.mIndex = 3;
                break;
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra(TYPE_ALARM_REMINDER_PARAM, this.mType);
        intent.putExtra(INDEX_ALARM_PARAM_CHECKED, this.mIndex);
        setResult(11, intent);
        finish();
    }
}