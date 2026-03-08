package com.ido.life.module.device.activity;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.ble.protocol.model.PressureParam;
import com.ido.common.dialog.CommSelectDialogFragment;
import com.ido.common.dialog.TimeDialogFragment;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.base.BaseHealthMonitoringActivity;
import com.ido.life.customview.CustomToggleButton;
import com.ido.life.customview.ReminderSelectView;
import com.ido.life.customview.viewgroup.CustomItemLabelView;
import com.ido.life.module.device.presenter.PressurePresenter;
import com.ido.life.module.device.view.IPressureView;

/* JADX INFO: loaded from: classes2.dex */
public class PressureMonitoringActivity extends BaseHealthMonitoringActivity<PressurePresenter> implements IPressureView, CommSelectDialogFragment.OnItemSelectedListener, TimeDialogFragment.OnItemSelectedListener {
    private static final String[] INTERVAL_ARR = {"15", "30", "60", "90", "120"};
    private static final int ITEM_END_TIME = 1;
    private static final int ITEM_START_TIME = 0;

    @BindView(R.id.item_end_time)
    CustomItemLabelView mItemEndTime;

    @BindView(R.id.item_overstress_reminder_switch)
    CustomItemLabelView mItemOverstressReminderSwitch;

    @BindView(R.id.item_pressure_switch)
    CustomItemLabelView mItemPressureSwitch;

    @BindView(R.id.item_reminder_interval)
    CustomItemLabelView mItemReminderInterval;

    @BindView(R.id.item_repetition_period)
    CustomItemLabelView mItemRepetitionPeriod;

    @BindView(R.id.item_start_time)
    CustomItemLabelView mItemStartTime;

    @BindView(R.id.layout_overstress_reminder)
    LinearLayout mLayoutOverstressReminder;

    @BindView(R.id.layout_root)
    LinearLayout mLayoutRoot;
    private PressureParam mPressureParam;
    private int selected = 0;

    @BindView(R.id.tv_overstress_reminder_tip)
    RegularTextView tvOverstressReminderTip;

    @BindView(R.id.vReminder)
    ReminderSelectView vReminder;

    @Override // com.ido.common.base.BaseCoreActivity
    public int getLayoutResId() {
        return R.layout.activity_pressure_monitoring;
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        this.mPressureParam = ((PressurePresenter) this.mPresenter).getLocalPressureParam();
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        this.mTitleBar.setLeftOnClick(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$PressureMonitoringActivity$U9eXQqkt4kT2HCmZQaVXrcWh3Ps
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initEvent$0$PressureMonitoringActivity(view);
            }
        });
        initSwitchEvent();
        boolean zIsSupportOverstressReminder = ((PressurePresenter) this.mPresenter).isSupportOverstressReminder();
        this.mItemPressureSwitch.setSwitchStatus(this.mPressureParam.onOff == 170);
        this.mLayoutOverstressReminder.setVisibility(zIsSupportOverstressReminder ? 0 : 8);
        this.tvOverstressReminderTip.setVisibility(zIsSupportOverstressReminder ? 0 : 8);
        this.mItemReminderInterval.setValue(((PressurePresenter) this.mPresenter).formInterval(this.mPressureParam.interval, getLanguageText(R.string.sport_time_unit)));
        this.mItemRepetitionPeriod.setValue(((PressurePresenter) this.mPresenter).formatWeekRepeat(this.mPressureParam.getWeekRepeat()));
        this.vReminder.setVisibility(((PressurePresenter) this.mPresenter).isSupportSetRemindMode() ? 0 : 8);
        this.vReminder.select(this.mPressureParam.notifyFlag);
        updateRemindLayout();
    }

    public /* synthetic */ void lambda$initEvent$0$PressureMonitoringActivity(View view) {
        onBackPressed();
    }

    private void initSwitchEvent() {
        this.mItemPressureSwitch.setOnSwitchListener(new CustomToggleButton.OnSwitchListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$PressureMonitoringActivity$odA7yxVjKm35Wjewq3gA6po9EuA
            @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
            public final void onSwitched(View view, boolean z) {
                this.f$0.lambda$initSwitchEvent$1$PressureMonitoringActivity(view, z);
            }
        });
        this.mItemOverstressReminderSwitch.setOnSwitchListener(new CustomToggleButton.OnSwitchListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$PressureMonitoringActivity$3Li1SjcRVMFeAX96DPdUkIrXF_I
            @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
            public final void onSwitched(View view, boolean z) {
                this.f$0.lambda$initSwitchEvent$2$PressureMonitoringActivity(view, z);
            }
        });
        this.vReminder.setOnReminderChangedListener(new ReminderSelectView.OnReminderChangedListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$PressureMonitoringActivity$k32yjlO4ZuZ8ecGIAD96WGeWj-M
            @Override // com.ido.life.customview.ReminderSelectView.OnReminderChangedListener
            public final void onReminderChanged(int i) {
                this.f$0.lambda$initSwitchEvent$3$PressureMonitoringActivity(i);
            }
        });
    }

    public /* synthetic */ void lambda$initSwitchEvent$1$PressureMonitoringActivity(View view, boolean z) {
        this.mPressureParam.onOff = z ? 170 : 85;
        updateRemindLayout();
    }

    public /* synthetic */ void lambda$initSwitchEvent$2$PressureMonitoringActivity(View view, boolean z) {
        this.mPressureParam.remindOnOff = z ? 170 : 85;
        updateRemindLayout();
    }

    public /* synthetic */ void lambda$initSwitchEvent$3$PressureMonitoringActivity(int i) {
        this.mPressureParam.notifyFlag = i;
        updateRemindLayout();
    }

    private boolean isOverstressRemindOpen() {
        PressureParam pressureParam = this.mPressureParam;
        return (pressureParam == null || pressureParam.onOff != 170 || (((PressurePresenter) this.mPresenter).isSupportSetRemindMode() && this.mPressureParam.notifyFlag == 3)) ? false : true;
    }

    private void updateRemindLayout() {
        boolean zIsOverstressRemindOpen = isOverstressRemindOpen();
        this.mLayoutOverstressReminder.setAlpha(zIsOverstressRemindOpen ? 1.0f : 0.3f);
        this.mItemOverstressReminderSwitch.setSwitchEnable(zIsOverstressRemindOpen);
        this.mItemOverstressReminderSwitch.setSwitchStatus(zIsOverstressRemindOpen && this.mPressureParam.remindOnOff == 170);
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        this.mTitleBar.setTitle(getLanguageText(R.string.device_pressure_monitoring));
        this.mItemPressureSwitch.setTitle(getLanguageText(R.string.device_pressure_monitoring));
    }

    @Override // com.ido.life.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        this.mItemStartTime.setValue(((PressurePresenter) this.mPresenter).formatTimeByTimeMode(this.mPressureParam.startHour, this.mPressureParam.startMinute));
        this.mItemEndTime.setValue(((PressurePresenter) this.mPresenter).formatTimeByTimeMode(this.mPressureParam.endHour, this.mPressureParam.endMinute));
    }

    @Override // com.ido.life.base.BaseActivity
    protected void sendCmd() {
        super.sendCmd();
        this.mPressureParam.remindOnOff = this.mItemOverstressReminderSwitch.getSwitchStatus() ? 170 : 85;
        if (!this.mPressureParam.toString().equals(((PressurePresenter) this.mPresenter).getLocalPressureParam().toString())) {
            showSettingLoading(false);
            ((PressurePresenter) this.mPresenter).sendPressure2Device(this.mPressureParam);
        } else {
            finish();
        }
    }

    @Override // com.ido.life.module.device.view.IPressureView
    public void onSetPressureModeSuccess() {
        dismissLoadingDialog();
        CommonLogUtil.d("onSetPressureModeSuccess");
        finish();
    }

    @Override // com.ido.life.module.device.view.IPressureView
    public void onSetPressureModeFailed() {
        dismissLoadingDialog();
        showCmdResultToast(false);
        finish();
    }

    @OnClick({R.id.item_start_time, R.id.item_end_time, R.id.item_reminder_interval, R.id.item_repetition_period})
    public void onViewClicked(View view) {
        if (isOverstressRemindOpen() && this.mPressureParam.remindOnOff == 170) {
            switch (view.getId()) {
                case R.id.item_end_time /* 2131362405 */:
                    this.selected = 1;
                    showTimePickerDialog(this.mPressureParam.endHour, this.mPressureParam.endMinute);
                    break;
                case R.id.item_reminder_interval /* 2131362470 */:
                    showIntervalSelectDialog();
                    break;
                case R.id.item_repetition_period /* 2131362475 */:
                    Intent intent = new Intent(this, (Class<?>) RepetitionPeriodSettingActivity.class);
                    intent.putExtra(RepetitionPeriodSettingActivity.REPETITION_PERIOD, this.mPressureParam.getWeekRepeat());
                    startActivityForResult(intent, 66);
                    break;
                case R.id.item_start_time /* 2131362486 */:
                    this.selected = 0;
                    showTimePickerDialog(this.mPressureParam.startHour, this.mPressureParam.startMinute);
                    break;
            }
        }
    }

    private void showTimePickerDialog(int i, int i2) {
        TimeDialogFragment timeDialogFragmentNewInstance;
        if (((PressurePresenter) this.mPresenter).isTimeFormat24()) {
            timeDialogFragmentNewInstance = TimeDialogFragment.newInstance(i, i2);
        } else {
            timeDialogFragmentNewInstance = TimeDialogFragment.newInstance(i, i2, ((PressurePresenter) this.mPresenter).isTimeFormat24(), new String[]{getLanguageText(R.string.public_am), getLanguageText(R.string.public_pm)});
        }
        timeDialogFragmentNewInstance.show(getSupportFragmentManager());
        timeDialogFragmentNewInstance.setOnItemSelectedListener(this);
    }

    private void showIntervalSelectDialog() {
        CommSelectDialogFragment commSelectDialogFragmentNewInstance = CommSelectDialogFragment.newInstance(INTERVAL_ARR, getLanguageText(R.string.sport_time_unit), this.mPressureParam.interval / 30, 5);
        commSelectDialogFragmentNewInstance.show(getSupportFragmentManager());
        commSelectDialogFragmentNewInstance.setOnItemSelectedListener(this);
    }

    @Override // com.ido.common.dialog.TimeDialogFragment.OnItemSelectedListener
    public void onTimeSelected(int i, int i2, int i3) {
        int i4 = this.selected;
        if (i4 == 0) {
            PressureParam pressureParam = this.mPressureParam;
            pressureParam.startHour = i2;
            pressureParam.startMinute = i3;
            this.mItemStartTime.setValue(((PressurePresenter) this.mPresenter).formatTimeByTimeMode(i2, i3));
            return;
        }
        if (i4 != 1) {
            return;
        }
        PressureParam pressureParam2 = this.mPressureParam;
        pressureParam2.endHour = i2;
        pressureParam2.endMinute = i3;
        this.mItemEndTime.setValue(((PressurePresenter) this.mPresenter).formatTimeByTimeMode(i2, i3));
    }

    @Override // com.ido.common.dialog.CommSelectDialogFragment.OnItemSelectedListener
    public void onItemSelected(int i) {
        this.mPressureParam.interval = Integer.valueOf(INTERVAL_ARR[i]).intValue();
        this.mItemReminderInterval.setValue(((PressurePresenter) this.mPresenter).formInterval(this.mPressureParam.interval, getLanguageText(R.string.sport_time_unit)));
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 66 || intent == null) {
            return;
        }
        this.mPressureParam.setWeekRepeat(intent.getBooleanArrayExtra(RepetitionPeriodSettingActivity.REPETITION_PERIOD));
        this.mItemRepetitionPeriod.setValue(((PressurePresenter) this.mPresenter).formatWeekRepeat(this.mPressureParam.getWeekRepeat()));
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (!this.mPressureParam.toString().equals(((PressurePresenter) this.mPresenter).getLocalPressureParam().toString())) {
            saveData();
        } else {
            super.onBackPressed();
        }
    }
}