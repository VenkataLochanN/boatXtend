package com.ido.life.module.device.activity;

import android.content.Intent;
import android.view.View;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.life.base.BaseActivity;
import com.ido.life.base.SingleTopIntent;
import com.ido.life.customview.viewgroup.CustomItemLabelView;
import com.ido.life.module.device.presenter.AlarmClockSettingPresenter;

/* JADX INFO: loaded from: classes2.dex */
public class AlarmClockSettingActivity extends BaseActivity<AlarmClockSettingPresenter> {
    public static final int ALARM_INTERVAL_DEFAULT = 10;
    public static final int ALARM_REPEAT_COUNT_DEFAULT = 1;

    @BindView(R.id.item_remind_interval)
    CustomItemLabelView mItemRemindInterval;

    @BindView(R.id.item_repeated_times)
    CustomItemLabelView mItemRepeatedTimes;
    private int mRemindInterval;
    private int mRepeatedTimes;

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_alarm_clock_setting;
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        this.mRemindInterval = ((AlarmClockSettingPresenter) this.mPresenter).getIntervalMinute();
        this.mRepeatedTimes = ((AlarmClockSettingPresenter) this.mPresenter).getRepeatCount();
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        this.mTitleBar.setLeftOnClick(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$AlarmClockSettingActivity$I_B84I70qL3oYDI6RhJvs7GhEF8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initEvent$0$AlarmClockSettingActivity(view);
            }
        });
    }

    public /* synthetic */ void lambda$initEvent$0$AlarmClockSettingActivity(View view) {
        onBackPressed();
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        this.mTitleBar.setTitle(getLanguageText(R.string.device_alarm_clock_setting));
        this.mItemRemindInterval.setValue(String.format(getLanguageText(R.string.device_x_minuter), Integer.valueOf(this.mRemindInterval)));
        if (this.mRepeatedTimes == 0) {
            this.mItemRepeatedTimes.setValue(getLanguageText(R.string.device_without_repetition));
        } else {
            this.mItemRepeatedTimes.setValue(String.format(getLanguageText(R.string.device_x_times), Integer.valueOf(this.mRepeatedTimes)));
        }
    }

    @OnClick({R.id.item_remind_interval, R.id.item_repeated_times})
    public void onViewClicked(View view) {
        SingleTopIntent singleTopIntent = new SingleTopIntent(this, (Class<?>) AlarmReminderParamSettingActivity.class);
        int id = view.getId();
        if (id == R.id.item_remind_interval) {
            singleTopIntent.putExtra(AlarmReminderParamSettingActivity.TYPE_ALARM_REMINDER_PARAM, 10);
            singleTopIntent.putExtra(AlarmReminderParamSettingActivity.INDEX_ALARM_PARAM_CHECKED, ((AlarmClockSettingPresenter) this.mPresenter).formParamChecked2Index(10, this.mRemindInterval));
        } else if (id == R.id.item_repeated_times) {
            singleTopIntent.putExtra(AlarmReminderParamSettingActivity.TYPE_ALARM_REMINDER_PARAM, 20);
            singleTopIntent.putExtra(AlarmReminderParamSettingActivity.INDEX_ALARM_PARAM_CHECKED, ((AlarmClockSettingPresenter) this.mPresenter).formParamChecked2Index(20, this.mRepeatedTimes));
        }
        startActivityForResult(singleTopIntent, 11);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 11 || intent == null) {
            return;
        }
        int intExtra = intent.getIntExtra(AlarmReminderParamSettingActivity.TYPE_ALARM_REMINDER_PARAM, 10);
        int iFormIndex2ParamChecked = ((AlarmClockSettingPresenter) this.mPresenter).formIndex2ParamChecked(intExtra, intent.getIntExtra(AlarmReminderParamSettingActivity.INDEX_ALARM_PARAM_CHECKED, 0));
        if (intExtra == 10) {
            this.mItemRemindInterval.setValue(String.format(getLanguageText(R.string.device_x_minuter), Integer.valueOf(iFormIndex2ParamChecked)));
            this.mRemindInterval = iFormIndex2ParamChecked;
            ((AlarmClockSettingPresenter) this.mPresenter).saveIntervalMinute(iFormIndex2ParamChecked);
        } else {
            if (intExtra != 20) {
                return;
            }
            if (iFormIndex2ParamChecked == 0) {
                this.mItemRepeatedTimes.setValue(getLanguageText(R.string.device_without_repetition));
            } else {
                this.mItemRepeatedTimes.setValue(String.format(getLanguageText(R.string.device_x_times), Integer.valueOf(iFormIndex2ParamChecked)));
            }
            this.mRepeatedTimes = iFormIndex2ParamChecked;
            ((AlarmClockSettingPresenter) this.mPresenter).saveRepeatCount(iFormIndex2ParamChecked);
        }
    }
}