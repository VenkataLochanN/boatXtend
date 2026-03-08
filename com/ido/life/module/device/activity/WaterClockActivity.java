package com.ido.life.module.device.activity;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.ble.protocol.model.DrinkWaterReminder;
import com.ido.common.dialog.CommSelectDialogFragment;
import com.ido.common.dialog.TimeDialogFragment;
import com.ido.life.base.BaseActivity;
import com.ido.life.customview.CustomToggleButton;
import com.ido.life.customview.viewgroup.CommLoadingView;
import com.ido.life.customview.viewgroup.CustomItemLabelView;
import com.ido.life.module.device.presenter.WaterClockPresenter;
import com.ido.life.module.device.view.IWaterClockView;
import com.ido.life.module.home.HomeFragmentPresenter;

/* JADX INFO: loaded from: classes2.dex */
public class WaterClockActivity extends BaseActivity<WaterClockPresenter> implements CustomToggleButton.OnSwitchListener, TimeDialogFragment.OnItemSelectedListener, IWaterClockView, CommSelectDialogFragment.OnItemSelectedListener {
    private static final String[] INTERVAL_ARR = {"15", "30", "60", "90", "120"};
    private static final int ITEM_END_TIME = 1;
    private static final int ITEM_START_TIME = 0;
    private DrinkWaterReminder defaultState;

    @BindView(R.id.comm_loading_view)
    CommLoadingView mCommLoadingView;

    @BindView(R.id.item_end_time)
    CustomItemLabelView mItemEndTime;

    @BindView(R.id.item_reminder_interval)
    CustomItemLabelView mItemReminderInterval;

    @BindView(R.id.item_repetition_period)
    CustomItemLabelView mItemRepetitionPeriod;

    @BindView(R.id.item_start_time)
    CustomItemLabelView mItemStartTime;

    @BindView(R.id.item_water_clock_switch)
    CustomItemLabelView mItemWaterClockSwitch;

    @BindView(R.id.layout_content)
    LinearLayout mLayoutContent;
    private DrinkWaterReminder mWaterClockState;
    private int selected = 0;

    @Override // com.ido.common.base.BaseCoreActivity
    public int getLayoutResId() {
        return R.layout.activity_water_clock;
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        this.mWaterClockState = ((WaterClockPresenter) this.mPresenter).getWaterClockState();
        this.defaultState = ((WaterClockPresenter) this.mPresenter).getWaterClockState();
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        this.mTitleBar.initLayout(1).setRightOnClick(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$WaterClockActivity$5tZyixU6iR8zeyZ24ePpy5Wdi-k
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initEvent$0$WaterClockActivity(view);
            }
        });
        this.mItemWaterClockSwitch.setOnSwitchListener(this);
        initDataState();
    }

    public /* synthetic */ void lambda$initEvent$0$WaterClockActivity(View view) {
        saveData();
    }

    @Override // com.ido.life.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        this.mItemStartTime.setValue(((WaterClockPresenter) this.mPresenter).formatTimeByTimeMode(this.mWaterClockState.getStartHour(), this.mWaterClockState.getStartMinute()));
        this.mItemEndTime.setValue(((WaterClockPresenter) this.mPresenter).formatTimeByTimeMode(this.mWaterClockState.getEndHour(), this.mWaterClockState.getEndMinute()));
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        this.mTitleBar.setTitle(getLanguageText(R.string.device_water_clock));
        this.mItemWaterClockSwitch.setTitle(getLanguageText(R.string.device_water_clock));
        this.mItemStartTime.setTitle(getLanguageText(R.string.device_start_time));
        this.mItemEndTime.setTitle(getLanguageText(R.string.device_end_time));
        this.mItemReminderInterval.setTitle(getLanguageText(R.string.device_reminder_interval));
        this.mItemRepetitionPeriod.setTitle(getLanguageText(R.string.device_repetition_period));
    }

    private void initDataState() {
        this.mItemWaterClockSwitch.setSwitchStatus(this.mWaterClockState.isOnOff());
        this.mLayoutContent.setAlpha(this.mWaterClockState.isOnOff() ? 1.0f : 0.3f);
        this.mItemReminderInterval.setValue(((WaterClockPresenter) this.mPresenter).formInterval(this.mWaterClockState.getInterval(), getLanguageText(R.string.sport_time_unit)));
        this.mItemRepetitionPeriod.setValue(((WaterClockPresenter) this.mPresenter).formatWeekRepeat(this.mWaterClockState.getWeeks()));
    }

    @OnClick({R.id.item_start_time, R.id.item_end_time, R.id.item_reminder_interval, R.id.item_repetition_period})
    public void onViewClicked(View view) {
        if (this.mWaterClockState.isOnOff()) {
            switch (view.getId()) {
                case R.id.item_end_time /* 2131362405 */:
                    this.selected = 1;
                    showTimePickerDialog(this.mWaterClockState.getEndHour(), this.mWaterClockState.getEndMinute());
                    break;
                case R.id.item_reminder_interval /* 2131362470 */:
                    showIntervalSelectDialog();
                    break;
                case R.id.item_repetition_period /* 2131362475 */:
                    Intent intent = new Intent(this, (Class<?>) RepetitionPeriodSettingActivity.class);
                    intent.putExtra(RepetitionPeriodSettingActivity.REPETITION_PERIOD, this.mWaterClockState.getWeeks());
                    startActivityForResult(intent, 66);
                    break;
                case R.id.item_start_time /* 2131362486 */:
                    this.selected = 0;
                    showTimePickerDialog(this.mWaterClockState.getStartHour(), this.mWaterClockState.getStartMinute());
                    break;
            }
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 66 || intent == null) {
            return;
        }
        this.mWaterClockState.setWeeks(intent.getBooleanArrayExtra(RepetitionPeriodSettingActivity.REPETITION_PERIOD));
        this.mItemRepetitionPeriod.setValue(((WaterClockPresenter) this.mPresenter).formatWeekRepeat(this.mWaterClockState.getWeeks()));
    }

    private void showIntervalSelectDialog() {
        CommSelectDialogFragment commSelectDialogFragmentNewInstance = CommSelectDialogFragment.newInstance(INTERVAL_ARR, getLanguageText(R.string.sport_time_unit), this.mWaterClockState.getInterval() / 30, 5);
        commSelectDialogFragmentNewInstance.show(getSupportFragmentManager());
        commSelectDialogFragmentNewInstance.setOnItemSelectedListener(this);
    }

    private void showTimePickerDialog(int i, int i2) {
        TimeDialogFragment timeDialogFragmentNewInstance;
        if (((WaterClockPresenter) this.mPresenter).isTimeFormat24()) {
            timeDialogFragmentNewInstance = TimeDialogFragment.newInstance(i, i2);
        } else {
            timeDialogFragmentNewInstance = TimeDialogFragment.newInstance(i, i2, ((WaterClockPresenter) this.mPresenter).isTimeFormat24(), new String[]{getLanguageText(R.string.public_am), getLanguageText(R.string.public_pm)});
        }
        timeDialogFragmentNewInstance.show(getSupportFragmentManager());
        timeDialogFragmentNewInstance.setOnItemSelectedListener(this);
    }

    @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
    public void onSwitched(View view, boolean z) {
        this.mLayoutContent.setAlpha(z ? 1.0f : 0.3f);
        this.mWaterClockState.setOnOff(z);
    }

    @Override // com.ido.common.dialog.TimeDialogFragment.OnItemSelectedListener
    public void onTimeSelected(int i, int i2, int i3) {
        int i4 = this.selected;
        if (i4 == 0) {
            this.mWaterClockState.setStartHour(i2);
            this.mWaterClockState.setStartMinute(i3);
            this.mItemStartTime.setValue(((WaterClockPresenter) this.mPresenter).formatTimeByTimeMode(this.mWaterClockState.getStartHour(), this.mWaterClockState.getStartMinute()));
        } else {
            if (i4 != 1) {
                return;
            }
            this.mWaterClockState.setEndHour(i2);
            this.mWaterClockState.setEndMinute(i3);
            this.mItemEndTime.setValue(((WaterClockPresenter) this.mPresenter).formatTimeByTimeMode(this.mWaterClockState.getEndHour(), this.mWaterClockState.getEndMinute()));
        }
    }

    @Override // com.ido.common.dialog.CommSelectDialogFragment.OnItemSelectedListener
    public void onItemSelected(int i) {
        this.mWaterClockState.setInterval(Integer.valueOf(INTERVAL_ARR[i]).intValue());
        this.mItemReminderInterval.setValue(((WaterClockPresenter) this.mPresenter).formInterval(this.mWaterClockState.getInterval(), getLanguageText(R.string.sport_time_unit)));
    }

    private boolean isDataChanged() {
        return !this.mWaterClockState.toString().equals(this.defaultState.toString());
    }

    @Override // com.ido.life.base.BaseActivity
    protected void sendCmd() {
        super.sendCmd();
        if (isDataChanged()) {
            if (HomeFragmentPresenter.mIsSyncing) {
                showToast(getLanguageText(R.string.syncing_pls_try_again_later));
                return;
            } else {
                this.mCommLoadingView.setVisibility(0);
                ((WaterClockPresenter) this.mPresenter).sendWaterClock2Device(this.mWaterClockState);
                return;
            }
        }
        finish();
    }

    @Override // com.ido.life.module.device.view.IWaterClockView
    public void onSetWaterClockSuccess() {
        this.mCommLoadingView.setVisibility(8);
        showCmdResultToast(true);
        finish();
    }

    @Override // com.ido.life.module.device.view.IWaterClockView
    public void onSetWaterClockFailed() {
        this.mCommLoadingView.setVisibility(8);
        showCmdResultToast(false);
    }
}