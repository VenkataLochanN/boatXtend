package com.ido.life.module.device.activity;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.ble.protocol.model.FitnessGuidance;
import com.ido.common.dialog.CommSelectDialogFragment;
import com.ido.common.dialog.TimeDialogFragment;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.base.BaseHealthMonitoringActivity;
import com.ido.life.customview.CustomToggleButton;
import com.ido.life.customview.ReminderSelectView;
import com.ido.life.customview.viewgroup.CommLoadingView;
import com.ido.life.customview.viewgroup.CustomItemLabelView;
import com.ido.life.database.model.UserTargetNew;
import com.ido.life.module.device.presenter.FitnessGuidancePresenter;
import com.ido.life.module.device.view.IFitnessGuidanceView;
import com.ido.life.util.DateUtil;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RunTimeUtil;
import java.util.Calendar;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class FitnessGuidanceActivity extends BaseHealthMonitoringActivity<FitnessGuidancePresenter> implements TimeDialogFragment.OnItemSelectedListener, IFitnessGuidanceView, CommSelectDialogFragment.OnItemSelectedListener, ReminderSelectView.OnReminderChangedListener {
    public static final int CODE_WALK_REMINDER = 99;
    private FitnessGuidance defaultState;
    private boolean isSupportFitnessGuidance = false;

    @BindView(R.id.comm_loading_view)
    CommLoadingView mCommLoadingView;
    private FitnessGuidance mFitnessGuidance;

    @BindView(R.id.item_end_time)
    CustomItemLabelView mItemEndTime;

    @BindView(R.id.item_fitness_coaching_reminder_switch)
    CustomItemLabelView mItemFitnessGuideReminderSwitch;

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
    private CustomItemLabelView selectedItem;

    @BindView(R.id.vReminder)
    ReminderSelectView vReminder;

    @Override // com.ido.common.base.BaseCoreActivity
    public int getLayoutResId() {
        return R.layout.activity_fitness_guidance;
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        this.mFitnessGuidance = ((FitnessGuidancePresenter) this.mPresenter).getFitnessGuidance();
        this.defaultState = ((FitnessGuidancePresenter) this.mPresenter).getFitnessGuidance();
        this.isSupportFitnessGuidance = ((FitnessGuidancePresenter) this.mPresenter).isSupportFitnessGuidance();
        initDataState();
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        this.mTitleBar.setLeftOnClick(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$FitnessGuidanceActivity$fz_JuRDr8IIR90XPJqqPYd9lK94
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initEvent$0$FitnessGuidanceActivity(view);
            }
        });
        this.mItemWalkReminderSwitch.setOnSwitchListener(new CustomToggleButton.OnSwitchListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$FitnessGuidanceActivity$P1S_45CGuR6XD47BOWilH_1YZ7Q
            @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
            public final void onSwitched(View view, boolean z) {
                this.f$0.lambda$initEvent$1$FitnessGuidanceActivity(view, z);
            }
        });
        this.mItemFitnessGuideReminderSwitch.setOnSwitchListener(new CustomToggleButton.OnSwitchListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$FitnessGuidanceActivity$V-NEkbHwMPaU3sfEeqJA75l-9PA
            @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
            public final void onSwitched(View view, boolean z) {
                this.f$0.lambda$initEvent$2$FitnessGuidanceActivity(view, z);
            }
        });
        this.vReminder.setOnReminderChangedListener(this);
    }

    public /* synthetic */ void lambda$initEvent$0$FitnessGuidanceActivity(View view) {
        onBackPressed();
    }

    public /* synthetic */ void lambda$initEvent$1$FitnessGuidanceActivity(View view, boolean z) {
        this.mFitnessGuidance.go_mode = z ? 170 : 85;
        this.mLayoutContent.setAlpha(z ? 1.0f : 0.3f);
    }

    public /* synthetic */ void lambda$initEvent$2$FitnessGuidanceActivity(View view, boolean z) {
        this.mFitnessGuidance.mode = z ? 170 : 85;
    }

    private boolean isFitnessGuidanceOpen() {
        FitnessGuidance fitnessGuidance;
        return this.isSupportFitnessGuidance && (fitnessGuidance = this.mFitnessGuidance) != null && fitnessGuidance.mode == 170;
    }

    private boolean isWalkReminderOpen() {
        return this.mFitnessGuidance.go_mode == 170;
    }

    private void initDataState() {
        this.mItemFitnessGuideReminderSwitch.setVisibility(this.isSupportFitnessGuidance ? 0 : 8);
        this.vReminder.setVisibility(this.isSupportFitnessGuidance ? 0 : 8);
        this.mItemHourGoalSteps.setValue(String.valueOf(this.mFitnessGuidance.target_steps));
        this.mItemRepetitionPeriod.setValue(((FitnessGuidancePresenter) this.mPresenter).formatWeekRepeat(this.mFitnessGuidance.getWeeks()));
        if (((FitnessGuidancePresenter) this.mPresenter).getSupportFunctionInfo().V3_set_walk_reminder_goal_time) {
            this.mItemHourGoalSteps.setVisibility(8);
        } else {
            this.mItemHourGoalSteps.setVisibility(0);
        }
        this.vReminder.select(this.mFitnessGuidance.notify_flag);
        updateSwitchStatus(!this.vReminder.isDeny());
    }

    @Override // com.ido.life.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        this.mItemStartTime.setValue(((FitnessGuidancePresenter) this.mPresenter).formatTimeByTimeMode(this.mFitnessGuidance.start_hour, this.mFitnessGuidance.start_minute));
        this.mItemEndTime.setValue(((FitnessGuidancePresenter) this.mPresenter).formatTimeByTimeMode(this.mFitnessGuidance.end_hour, this.mFitnessGuidance.end_minute));
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        this.mTitleBar.setTitle(getLanguageText(R.string.public_fitness));
        this.mItemFitnessGuideReminderSwitch.setTitle(getLanguageText(R.string.notification_reminder_fitness_coaching));
        this.mItemWalkReminderSwitch.setTitle(getLanguageText(R.string.device_walk_reminder));
        this.mItemStartTime.setTitle(getLanguageText(R.string.device_start_time));
        this.mItemEndTime.setTitle(getLanguageText(R.string.device_end_time));
        this.mItemHourGoalSteps.setTitle(getLanguageText(R.string.device_goal_steps_hour));
        this.mItemRepetitionPeriod.setTitle(getLanguageText(R.string.device_repetition_period));
        this.mRtvWalkReminderTip.setText(getLanguageText(R.string.device_walk_reminder_tip));
    }

    @OnClick({R.id.item_start_time, R.id.item_end_time, R.id.item_hour_goal_steps, R.id.item_repetition_period})
    public void onViewClicked(View view) {
        if (this.vReminder.isDeny() || !isWalkReminderOpen()) {
            return;
        }
        switch (view.getId()) {
            case R.id.item_end_time /* 2131362405 */:
                this.selectedItem = this.mItemEndTime;
                showTimePickerDialog(this.mFitnessGuidance.end_hour, this.mFitnessGuidance.end_minute);
                break;
            case R.id.item_hour_goal_steps /* 2131362420 */:
                showGoalStepSelectDialog();
                break;
            case R.id.item_repetition_period /* 2131362475 */:
                Intent intent = new Intent(this, (Class<?>) RepetitionPeriodSettingActivity.class);
                intent.putExtra(RepetitionPeriodSettingActivity.REPETITION_PERIOD, this.mFitnessGuidance.getWeeks());
                startActivityForResult(intent, 66);
                break;
            case R.id.item_start_time /* 2131362486 */:
                this.selectedItem = this.mItemStartTime;
                showTimePickerDialog(this.mFitnessGuidance.start_hour, this.mFitnessGuidance.start_minute);
                break;
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 66 || intent == null) {
            return;
        }
        this.mFitnessGuidance.setWeeks(intent.getBooleanArrayExtra(RepetitionPeriodSettingActivity.REPETITION_PERIOD));
        this.mItemRepetitionPeriod.setValue(((FitnessGuidancePresenter) this.mPresenter).formatWeekRepeat(this.mFitnessGuidance.getWeeks()));
    }

    private void showGoalStepSelectDialog() {
        CommSelectDialogFragment commSelectDialogFragmentNewInstance = CommSelectDialogFragment.newInstance(((FitnessGuidancePresenter) this.mPresenter).getGoalStepItems(), "", (this.mFitnessGuidance.target_steps - 75) / 25, 5);
        commSelectDialogFragmentNewInstance.show(getSupportFragmentManager());
        commSelectDialogFragmentNewInstance.setOnItemSelectedListener(this);
    }

    private void showTimePickerDialog(int i, int i2) {
        TimeDialogFragment timeDialogFragmentNewInstance;
        if (((FitnessGuidancePresenter) this.mPresenter).isTimeFormat24()) {
            timeDialogFragmentNewInstance = TimeDialogFragment.newInstance(i, i2, false);
        } else {
            timeDialogFragmentNewInstance = TimeDialogFragment.newInstance(i, i2, ((FitnessGuidancePresenter) this.mPresenter).isTimeFormat24(), new String[]{getLanguageText(R.string.public_am), getLanguageText(R.string.public_pm)}, false);
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
            FitnessGuidance fitnessGuidance = this.mFitnessGuidance;
            fitnessGuidance.start_hour = i2;
            fitnessGuidance.start_minute = i3;
            this.mItemStartTime.setValue(((FitnessGuidancePresenter) this.mPresenter).formatTimeByTimeMode(this.mFitnessGuidance.start_hour, this.mFitnessGuidance.start_minute));
            return;
        }
        FitnessGuidance fitnessGuidance2 = this.mFitnessGuidance;
        fitnessGuidance2.end_hour = i2;
        fitnessGuidance2.end_minute = i3;
        this.mItemEndTime.setValue(((FitnessGuidancePresenter) this.mPresenter).formatTimeByTimeMode(this.mFitnessGuidance.end_hour, this.mFitnessGuidance.end_minute));
    }

    @Override // com.ido.common.dialog.CommSelectDialogFragment.OnItemSelectedListener
    public void onItemSelected(int i) {
        int i2;
        try {
            i2 = Integer.parseInt(((FitnessGuidancePresenter) this.mPresenter).getGoalStepItems()[i]);
        } catch (Exception e2) {
            e2.printStackTrace();
            i2 = 100;
        }
        this.mFitnessGuidance.target_steps = i2;
        this.mItemHourGoalSteps.setValue(String.valueOf(i2));
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        saveData();
    }

    private boolean isDataChanged() {
        return !this.defaultState.toString().equals(this.mFitnessGuidance.toString());
    }

    @Override // com.ido.life.base.BaseActivity
    protected void sendCmd() {
        super.sendCmd();
        this.mFitnessGuidance.mode = this.mItemFitnessGuideReminderSwitch.getSwitchStatus() ? 170 : 85;
        this.mFitnessGuidance.go_mode = this.mItemWalkReminderSwitch.getSwitchStatus() ? 170 : 85;
        if (this.mFitnessGuidance.go_mode == 170 && this.mFitnessGuidance.end_hour <= this.mFitnessGuidance.start_hour) {
            showToast(getLanguageText(R.string.device_walk_time_tip));
            return;
        }
        if (isDataChanged()) {
            showSettingLoading(false);
            saveToUserTarget();
            ((FitnessGuidancePresenter) this.mPresenter).addCallBack();
            ((FitnessGuidancePresenter) this.mPresenter).sendFitnessGuide2Device(this.mFitnessGuidance);
            return;
        }
        setResult();
    }

    private void setResult() {
        setResult(99);
        finish();
    }

    private void saveToUserTarget() {
        if (((FitnessGuidancePresenter) this.mPresenter).getSupportFunctionInfo().V3_set_walk_reminder_goal_time) {
            return;
        }
        UserTargetNew userTargetNewQueryUserTarget = GreenDaoUtil.queryUserTarget(RunTimeUtil.getInstance().getUserId(), DateUtil.format(Calendar.getInstance(Locale.CHINA), DateUtil.DATE_FORMAT_YMD));
        if (this.mFitnessGuidance.go_mode != 170) {
            if (userTargetNewQueryUserTarget != null) {
                userTargetNewQueryUserTarget.setSportStep(100);
                userTargetNewQueryUserTarget.setWalk(43200);
                userTargetNewQueryUserTarget.setHasUpload(false);
                userTargetNewQueryUserTarget.update();
                return;
            }
            return;
        }
        if (userTargetNewQueryUserTarget == null) {
            UserTargetNew userTargetNewGenerateDefaultUserTargetNew = RunTimeUtil.generateDefaultUserTargetNew(RunTimeUtil.getInstance().getUserId());
            userTargetNewGenerateDefaultUserTargetNew.setSportStep(this.mFitnessGuidance.target_steps);
            userTargetNewGenerateDefaultUserTargetNew.setWalk(((this.mFitnessGuidance.end_hour - this.mFitnessGuidance.start_hour) + 1) * 3600);
            GreenDaoUtil.addUserTarget(userTargetNewGenerateDefaultUserTargetNew);
            return;
        }
        userTargetNewQueryUserTarget.setSportStep(this.mFitnessGuidance.target_steps);
        userTargetNewQueryUserTarget.setWalk(((this.mFitnessGuidance.end_hour - this.mFitnessGuidance.start_hour) + 1) * 3600);
        userTargetNewQueryUserTarget.setHasUpload(false);
        userTargetNewQueryUserTarget.update();
    }

    @Override // com.ido.life.module.device.view.IFitnessGuidanceView
    public void onSetFitnessGuidanceSuccess() {
        CommonLogUtil.printAndSave("onSetFitnessGuidanceSuccess");
        this.defaultState = this.mFitnessGuidance;
        dismissLoadingDialog();
        setResult();
    }

    @Override // com.ido.life.module.device.view.IFitnessGuidanceView
    public void onSetFitnessGuidanceFailed() {
        CommonLogUtil.printAndSave("onSetFitnessGuidanceFailed");
        dismissLoadingDialog();
        showCmdResultToast(false);
    }

    @Override // com.ido.life.customview.ReminderSelectView.OnReminderChangedListener
    public void onReminderChanged(int i) {
        FitnessGuidance fitnessGuidance = this.mFitnessGuidance;
        if (fitnessGuidance != null) {
            fitnessGuidance.notify_flag = i;
        }
        updateSwitchStatus(i != 3);
    }

    private void updateSwitchStatus(boolean z) {
        this.mItemFitnessGuideReminderSwitch.setSwitchStatus(z && isFitnessGuidanceOpen());
        this.mItemFitnessGuideReminderSwitch.setSwitchEnable(z);
        boolean z2 = z && isWalkReminderOpen();
        this.mItemWalkReminderSwitch.setSwitchStatus(z2);
        this.mItemWalkReminderSwitch.setSwitchEnable(z);
        this.mLayoutContent.setAlpha(z2 ? 1.0f : 0.3f);
    }
}