package com.ido.life.module.device.activity;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.ble.protocol.model.WalkReminder;
import com.ido.common.dialog.CommSelectDialogFragment;
import com.ido.common.dialog.TimeDialogFragment;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.base.BaseActivity;
import com.ido.life.customview.CustomToggleButton;
import com.ido.life.customview.viewgroup.CommLoadingView;
import com.ido.life.customview.viewgroup.CustomItemLabelView;
import com.ido.life.module.device.presenter.WalkReminderPresenter;
import com.ido.life.module.device.view.IWalkReminderView;
import com.ido.life.module.home.HomeFragmentPresenter;

/* JADX INFO: loaded from: classes2.dex */
public class WalkReminderActivity extends BaseActivity<WalkReminderPresenter> implements CustomToggleButton.OnSwitchListener, TimeDialogFragment.OnItemSelectedListener, IWalkReminderView, CommSelectDialogFragment.OnItemSelectedListener {
    public static final int CODE_WALK_REMINDER = 99;
    private WalkReminder defaultState;

    @BindView(R.id.comm_loading_view)
    CommLoadingView mCommLoadingView;

    @BindView(R.id.item_end_time)
    CustomItemLabelView mItemEndTime;

    @BindView(R.id.item_hour_goal_steps)
    CustomItemLabelView mItemHourGoalSteps;

    @BindView(R.id.item_repetition_period)
    CustomItemLabelView mItemRepetitionPeriod;

    @BindView(R.id.item_start_time)
    CustomItemLabelView mItemStartTime;

    @BindView(R.id.item_walk_reminder_switch)
    CustomItemLabelView mItemWalkReminderSwitch;

    @BindView(R.id.layout_content)
    LinearLayout mLayoutContent;

    @BindView(R.id.rtv_walk_reminder_tip)
    RegularTextView mRtvWalkReminderTip;
    private WalkReminder mWalkReminderState;
    private CustomItemLabelView selectedItem;

    @Override // com.ido.common.base.BaseCoreActivity
    public int getLayoutResId() {
        return R.layout.activity_walk_reminder;
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        this.mWalkReminderState = ((WalkReminderPresenter) this.mPresenter).getWalkReminderState();
        this.defaultState = ((WalkReminderPresenter) this.mPresenter).getWalkReminderState();
        initDataState();
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        this.mTitleBar.initLayout(1).setRightOnClick(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$WalkReminderActivity$BOycRFujXWzOoB5O1se-eKGg4uc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initEvent$0$WalkReminderActivity(view);
            }
        });
        this.mItemWalkReminderSwitch.setOnSwitchListener(this);
    }

    public /* synthetic */ void lambda$initEvent$0$WalkReminderActivity(View view) {
        saveData();
    }

    private void initDataState() {
        this.mItemWalkReminderSwitch.setSwitchStatus(this.mWalkReminderState.getOnOff() == 1);
        this.mLayoutContent.setAlpha(this.mWalkReminderState.getOnOff() == 1 ? 1.0f : 0.3f);
        this.mItemHourGoalSteps.setValue(String.valueOf(this.mWalkReminderState.getGoalStep()));
        this.mItemRepetitionPeriod.setValue(((WalkReminderPresenter) this.mPresenter).formatWeekRepeat(this.mWalkReminderState.getWeeks()));
    }

    @Override // com.ido.life.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        this.mItemStartTime.setValue(((WalkReminderPresenter) this.mPresenter).formatTimeByTimeMode(this.mWalkReminderState.getStartHour(), this.mWalkReminderState.getStartMinute()));
        this.mItemEndTime.setValue(((WalkReminderPresenter) this.mPresenter).formatTimeByTimeMode(this.mWalkReminderState.getEndHour(), this.mWalkReminderState.getEndMinute()));
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        this.mTitleBar.setTitle(getLanguageText(R.string.device_walk_reminder));
        this.mItemWalkReminderSwitch.setTitle(getLanguageText(R.string.device_walk_reminder));
        this.mItemStartTime.setTitle(getLanguageText(R.string.device_start_time));
        this.mItemEndTime.setTitle(getLanguageText(R.string.device_end_time));
        this.mItemHourGoalSteps.setTitle(getLanguageText(R.string.device_goal_steps_hour));
        this.mItemRepetitionPeriod.setTitle(getLanguageText(R.string.device_repetition_period));
        this.mRtvWalkReminderTip.setText(getLanguageText(R.string.device_walk_reminder_tip));
    }

    @OnClick({R.id.item_start_time, R.id.item_end_time, R.id.item_hour_goal_steps, R.id.item_repetition_period})
    public void onViewClicked(View view) {
        if (this.mWalkReminderState.getOnOff() == 0) {
        }
        switch (view.getId()) {
            case R.id.item_end_time /* 2131362405 */:
                this.selectedItem = this.mItemEndTime;
                showTimePickerDialog(this.mWalkReminderState.getEndHour(), this.mWalkReminderState.getEndMinute());
                break;
            case R.id.item_hour_goal_steps /* 2131362420 */:
                showGoalStepSelectDialog();
                break;
            case R.id.item_repetition_period /* 2131362475 */:
                Intent intent = new Intent(this, (Class<?>) RepetitionPeriodSettingActivity.class);
                intent.putExtra(RepetitionPeriodSettingActivity.REPETITION_PERIOD, this.mWalkReminderState.getWeeks());
                startActivityForResult(intent, 66);
                break;
            case R.id.item_start_time /* 2131362486 */:
                this.selectedItem = this.mItemStartTime;
                showTimePickerDialog(this.mWalkReminderState.getStartHour(), this.mWalkReminderState.getStartMinute());
                break;
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 66 || intent == null) {
            return;
        }
        this.mWalkReminderState.setWeeks(intent.getBooleanArrayExtra(RepetitionPeriodSettingActivity.REPETITION_PERIOD));
        this.mItemRepetitionPeriod.setValue(((WalkReminderPresenter) this.mPresenter).formatWeekRepeat(this.mWalkReminderState.getWeeks()));
    }

    @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
    public void onSwitched(View view, boolean z) {
        this.mWalkReminderState.setOnOff(z ? 1 : 0);
        this.mLayoutContent.setAlpha(z ? 1.0f : 0.3f);
    }

    private void showGoalStepSelectDialog() {
        CommSelectDialogFragment commSelectDialogFragmentNewInstance = CommSelectDialogFragment.newInstance(((WalkReminderPresenter) this.mPresenter).getGoalStepItems(), "", (this.mWalkReminderState.getGoalStep() - 75) / 25, 5);
        commSelectDialogFragmentNewInstance.show(getSupportFragmentManager());
        commSelectDialogFragmentNewInstance.setOnItemSelectedListener(this);
    }

    private void showTimePickerDialog(int i, int i2) {
        TimeDialogFragment timeDialogFragmentNewInstance;
        if (((WalkReminderPresenter) this.mPresenter).isTimeFormat24()) {
            timeDialogFragmentNewInstance = TimeDialogFragment.newInstance(i, i2, false);
        } else {
            timeDialogFragmentNewInstance = TimeDialogFragment.newInstance(i, i2, ((WalkReminderPresenter) this.mPresenter).isTimeFormat24(), new String[]{getLanguageText(R.string.public_am), getLanguageText(R.string.public_pm)}, false);
        }
        timeDialogFragmentNewInstance.show(getSupportFragmentManager());
        timeDialogFragmentNewInstance.setOnItemSelectedListener(this);
    }

    @Override // com.ido.common.dialog.TimeDialogFragment.OnItemSelectedListener
    public void onTimeSelected(int i, int i2, int i3) {
        CustomItemLabelView customItemLabelView = this.selectedItem;
        if (customItemLabelView == null) {
            return;
        }
        if (customItemLabelView.equals(this.mItemStartTime)) {
            this.mWalkReminderState.setStartHour(i2);
            this.mWalkReminderState.setStartMinute(i3);
            this.mItemStartTime.setValue(((WalkReminderPresenter) this.mPresenter).formatTimeByTimeMode(this.mWalkReminderState.getStartHour(), this.mWalkReminderState.getStartMinute()));
        } else {
            this.mWalkReminderState.setEndHour(i2);
            this.mWalkReminderState.setEndMinute(i3);
            this.mItemEndTime.setValue(((WalkReminderPresenter) this.mPresenter).formatTimeByTimeMode(this.mWalkReminderState.getEndHour(), this.mWalkReminderState.getEndMinute()));
        }
    }

    @Override // com.ido.common.dialog.CommSelectDialogFragment.OnItemSelectedListener
    public void onItemSelected(int i) {
        int iIntValue;
        try {
            iIntValue = Integer.valueOf(((WalkReminderPresenter) this.mPresenter).getGoalStepItems()[i]).intValue();
        } catch (Exception e2) {
            e2.printStackTrace();
            iIntValue = 100;
        }
        this.mWalkReminderState.setGoalStep(iIntValue);
        this.mItemHourGoalSteps.setValue(String.valueOf(this.mWalkReminderState.getGoalStep()));
    }

    private boolean isDataChanged() {
        return (this.defaultState.toString().equals(this.mWalkReminderState.toString()) && this.defaultState.getOnOff() == this.mWalkReminderState.getOnOff()) ? false : true;
    }

    @Override // com.ido.life.base.BaseActivity
    protected void sendCmd() {
        super.sendCmd();
        if (this.mWalkReminderState.getEndHour() <= this.mWalkReminderState.getStartHour()) {
            showToast(getLanguageText(R.string.device_walk_time_tip));
            return;
        }
        if (isDataChanged()) {
            if (HomeFragmentPresenter.mIsSyncing) {
                showToast(getLanguageText(R.string.syncing_pls_try_again_later));
                return;
            } else {
                this.mCommLoadingView.setVisibility(0);
                ((WalkReminderPresenter) this.mPresenter).sendWalkReminder2Device(this.mWalkReminderState);
                return;
            }
        }
        finish();
    }

    @Override // com.ido.life.module.device.view.IWalkReminderView
    public void onSetWalkReminderSuccess() {
        this.mCommLoadingView.setVisibility(8);
        showCmdResultToast(true);
        setResult(99);
        finish();
    }

    @Override // com.ido.life.module.device.view.IWalkReminderView
    public void onSetWalkReminderFailed() {
        this.mCommLoadingView.setVisibility(8);
        showCmdResultToast(false);
    }
}