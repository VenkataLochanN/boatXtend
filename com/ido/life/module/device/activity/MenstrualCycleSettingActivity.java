package com.ido.life.module.device.activity;

import android.text.format.DateFormat;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.ble.BLEManager;
import com.ido.ble.protocol.model.Menstrual;
import com.ido.ble.protocol.model.MenstrualRemind;
import com.ido.common.dialog.CommSelectDialogFragment;
import com.ido.common.dialog.DateDialogFragment;
import com.ido.common.dialog.TimeDialogFragment;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.base.BaseHealthMonitoringActivity;
import com.ido.life.customview.CustomToggleButton;
import com.ido.life.customview.ReminderSelectView;
import com.ido.life.customview.viewgroup.CommLoadingView;
import com.ido.life.customview.viewgroup.CustomItemLabelView;
import com.ido.life.database.model.MenstruationConfig;
import com.ido.life.dialog.CommonDialog;
import com.ido.life.module.device.presenter.MenstrualCycleSettingPresenter;
import com.ido.life.module.device.view.IMenstrualCycleSettingView;
import com.ido.life.module.home.HomeFragmentPresenter;
import com.ido.life.util.DateUtil;
import com.ido.life.util.DialogUtils;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.HealthCareUtil;
import com.ido.life.util.MsgNotificationHelper;
import com.ido.life.util.RunTimeUtil;
import com.ido.life.util.SPHelper;
import com.ido.life.util.TimeUtil;
import java.util.Calendar;

/* JADX INFO: loaded from: classes2.dex */
public class MenstrualCycleSettingActivity extends BaseHealthMonitoringActivity<MenstrualCycleSettingPresenter> implements CustomToggleButton.OnSwitchListener, CommSelectDialogFragment.OnItemSelectedListener, TimeDialogFragment.OnItemSelectedListener, DateDialogFragment.OnDateSelectedListener, IMenstrualCycleSettingView, ReminderSelectView.OnReminderChangedListener {
    private static final int ITEM_MENSTRUAL_LENGTH = 2;
    private static final int ITEM_MENSTRUAL_REMIND = 0;
    private static final int ITEM_OVULATION_REMIND = 1;
    private static final int ITEM_PERIOD_LENGTH = 3;
    private static final int ITEM_PREGNANCY_REMIND = 4;
    public static final String USERID = "user_id";
    private String[] mAmOrPm;

    @BindView(R.id.comm_loading_view)
    CommLoadingView mCommLoadingView;
    private String[] mCycleDataArray;
    private String mDayUnit;
    private String[] mDaysArr;
    private Menstrual mDefaultMenstrual;
    private MenstrualRemind mDefaultMenstrualRemind;

    @BindView(R.id.item_last_time)
    CustomItemLabelView mItemLastTime;

    @BindView(R.id.item_menstrual_cycle_switch)
    CustomItemLabelView mItemMenstrualCycleSwitch;

    @BindView(R.id.item_menstrual_length)
    CustomItemLabelView mItemMenstrualLength;

    @BindView(R.id.item_menstrual_remind)
    CustomItemLabelView mItemMenstrualRemind;

    @BindView(R.id.item_ovulation_remind)
    CustomItemLabelView mItemOvulationRemind;

    @BindView(R.id.item_period_length)
    CustomItemLabelView mItemPeriodLength;

    @BindView(R.id.item_pregnancy_remind)
    CustomItemLabelView mItemPregnancyRemind;

    @BindView(R.id.item_reminder_time)
    CustomItemLabelView mItemReminderTime;

    @BindView(R.id.layout_content)
    LinearLayout mLayoutContent;
    private Menstrual mMenstrual;
    private int mMenstrualCycleIndex;
    private String[] mMenstrualLengthArray;
    private int mMenstrualLengthIndex;
    private MenstrualRemind mMenstrualRemind;
    private int mMenstrualRemindDaysIndex;
    private int mOvulationRemindDaysIndex;
    private int mPregnancyRemindDaysIndex;

    @BindView(R.id.rtv_menstrual_cycle_tip)
    RegularTextView mRtvMenstrualCycleTip;

    @BindView(R.id.rtv_menstrual_set_tip)
    RegularTextView mRtvMenstrualSetTip;
    private int mSelected;
    private int[] mSelectedDate;

    @BindView(R.id.vReminder)
    ReminderSelectView vReminder;

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_menstrual_cycle_setting;
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        this.mDayUnit = getLanguageText(R.string.public_unit_day);
        this.mDaysArr = getResources().getStringArray(R.array.items_remind);
        this.mAmOrPm = new String[]{getLanguageText(R.string.public_am), getLanguageText(R.string.public_pm)};
        this.mMenstrual = ((MenstrualCycleSettingPresenter) this.mPresenter).getMenstrual();
        this.mDefaultMenstrual = ((MenstrualCycleSettingPresenter) this.mPresenter).getMenstrual();
        this.mMenstrualRemind = ((MenstrualCycleSettingPresenter) this.mPresenter).getMenstrualRemind();
        this.mDefaultMenstrualRemind = ((MenstrualCycleSettingPresenter) this.mPresenter).getMenstrualRemind();
        this.mMenstrualRemindDaysIndex = this.mMenstrualRemind.start_day - 1;
        int i = this.mMenstrualRemindDaysIndex;
        if (i < 0) {
            i = 0;
        }
        this.mMenstrualRemindDaysIndex = i;
        int length = this.mMenstrualRemindDaysIndex;
        String[] strArr = this.mDaysArr;
        if (length > strArr.length - 1) {
            length = strArr.length - 1;
        }
        this.mMenstrualRemindDaysIndex = length;
        this.mOvulationRemindDaysIndex = this.mMenstrualRemind.ovulation_day - 1;
        int i2 = this.mOvulationRemindDaysIndex;
        if (i2 < 0) {
            i2 = 0;
        }
        this.mOvulationRemindDaysIndex = i2;
        int length2 = this.mOvulationRemindDaysIndex;
        String[] strArr2 = this.mDaysArr;
        if (length2 > strArr2.length - 1) {
            length2 = strArr2.length - 1;
        }
        this.mOvulationRemindDaysIndex = length2;
        this.mMenstrualLengthArray = HealthCareUtil.initArray(1, 14, "");
        this.mCycleDataArray = HealthCareUtil.initArray(20, 90, "");
        this.mMenstrualLengthIndex = this.mMenstrual.menstrual_length - 1;
        this.mMenstrualCycleIndex = this.mMenstrual.menstrual_cycle - 20;
        this.mSelectedDate = new int[]{this.mMenstrual.last_menstrual_year, this.mMenstrual.last_menstrual_month, this.mMenstrual.last_menstrual_day};
        this.mPregnancyRemindDaysIndex = this.mMenstrualRemind.pregnancy_day_before_remind - 1;
        this.mPregnancyRemindDaysIndex = Math.max(this.mPregnancyRemindDaysIndex, 0);
        this.mPregnancyRemindDaysIndex = Math.min(this.mPregnancyRemindDaysIndex, this.mDaysArr.length - 1);
        this.vReminder.select(this.mMenstrual.notify_flag);
        this.mItemMenstrualCycleSwitch.setSwitchEnable((((MenstrualCycleSettingPresenter) this.mPresenter).isSupportSetMenstrualNotifyFlag() && this.vReminder.isDeny()) ? false : true);
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        this.mTitleBar.initLayout(4).setLeftOnClick(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$MenstrualCycleSettingActivity$Mg-8wzXgG5EEWk9vEQS7V3Y3xOQ
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initEvent$0$MenstrualCycleSettingActivity(view);
            }
        });
        this.mItemMenstrualCycleSwitch.setOnSwitchListener(this);
        initViewStatus();
    }

    public /* synthetic */ void lambda$initEvent$0$MenstrualCycleSettingActivity(View view) {
        onBackPressed();
    }

    @Override // com.ido.life.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        this.mAmOrPm[0] = getLanguageText(R.string.public_am);
        this.mAmOrPm[1] = getLanguageText(R.string.public_pm);
        this.mDaysArr[0] = getLanguageText(R.string.device_advance_one_day);
        this.mDaysArr[1] = getLanguageText(R.string.device_advance_two_day);
        this.mDaysArr[2] = getLanguageText(R.string.device_advance_three_day);
        setRemindTime();
    }

    private void setRemindTime() {
        String strTimeFormatter;
        CustomItemLabelView customItemLabelView = this.mItemReminderTime;
        if (TimeUtil.is24Hour()) {
            strTimeFormatter = TimeUtil.timeFormatter(this.mMenstrualRemind.hour, this.mMenstrualRemind.minute, true, this.mAmOrPm);
        } else {
            strTimeFormatter = TimeUtil.timeFormatter(this.mMenstrualRemind.hour, this.mMenstrualRemind.minute, false, this.mAmOrPm);
        }
        customItemLabelView.setValue(strTimeFormatter);
    }

    private void initViewStatus() {
        this.mItemMenstrualRemind.setValue(this.mDaysArr[this.mMenstrualRemindDaysIndex]);
        this.mItemOvulationRemind.setValue(this.mDaysArr[this.mOvulationRemindDaysIndex]);
        Calendar calendar = Calendar.getInstance();
        calendar.set(1, this.mMenstrual.last_menstrual_year);
        calendar.set(2, this.mMenstrual.last_menstrual_month);
        calendar.set(5, this.mMenstrual.last_menstrual_day);
        this.mItemMenstrualLength.setValue(this.mMenstrualLengthArray[this.mMenstrualLengthIndex] + this.mDayUnit);
        this.mItemPeriodLength.setValue(this.mCycleDataArray[this.mMenstrualCycleIndex] + this.mDayUnit);
        this.vReminder.setVisibility(((MenstrualCycleSettingPresenter) this.mPresenter).isSupportSetMenstrualNotifyFlag() ? 0 : 8);
        this.mItemPregnancyRemind.setVisibility(((MenstrualCycleSettingPresenter) this.mPresenter).isSupportPregnancyRemind() ? 0 : 8);
        this.mItemPregnancyRemind.setValue(this.mDaysArr[this.mPregnancyRemindDaysIndex]);
        this.vReminder.setOnReminderChangedListener(this);
        updateRemindLayout();
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        this.mTitleBar.setTitle(getLanguageText(R.string.menstrual_tracking));
        this.mItemMenstrualCycleSwitch.setTitle(getLanguageText(R.string.public_forecast_reminder));
        this.mRtvMenstrualCycleTip.setText(getLanguageText(R.string.device_menstrual_cycle_tip));
        this.mItemMenstrualRemind.setTitle(getLanguageText(R.string.device_menstrual_remind));
        this.mItemOvulationRemind.setTitle(getLanguageText(R.string.device_ovulation_day_remind));
        this.mItemPregnancyRemind.setTitle(getLanguageText(R.string.device_pregnancy_remind));
        this.mItemReminderTime.setTitle(getLanguageText(R.string.device_reminder_time));
        this.mItemMenstrualLength.setTitle(getLanguageText(R.string.device_menstrual_length));
        this.mItemPeriodLength.setTitle(getLanguageText(R.string.device_period_length));
        this.mRtvMenstrualSetTip.setText(getLanguageText(R.string.device_menstrual_set_tip));
        this.mDayUnit = getLanguageText(R.string.public_unit_day);
    }

    @OnClick({R.id.item_menstrual_remind, R.id.item_ovulation_remind, R.id.item_reminder_time, R.id.item_last_time, R.id.item_menstrual_length, R.id.item_period_length, R.id.item_pregnancy_remind})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.item_last_time /* 2131362427 */:
                showDatePickerDialog();
                break;
            case R.id.item_menstrual_length /* 2131362433 */:
                this.mSelected = 2;
                showCycleLengthDialog(this.mMenstrualLengthArray, this.mMenstrualLengthIndex);
                break;
            case R.id.item_menstrual_remind /* 2131362434 */:
                if (this.mItemMenstrualCycleSwitch.getSwitchStatus()) {
                    this.mSelected = 0;
                    showRemindSelectDialog(this.mMenstrualRemindDaysIndex);
                    break;
                }
                break;
            case R.id.item_ovulation_remind /* 2131362454 */:
                if (this.mItemMenstrualCycleSwitch.getSwitchStatus()) {
                    this.mSelected = 1;
                    showRemindSelectDialog(this.mOvulationRemindDaysIndex);
                    break;
                }
                break;
            case R.id.item_period_length /* 2131362455 */:
                this.mSelected = 3;
                showCycleLengthDialog(this.mCycleDataArray, this.mMenstrualCycleIndex);
                break;
            case R.id.item_pregnancy_remind /* 2131362458 */:
                if (this.mItemMenstrualCycleSwitch.getSwitchStatus()) {
                    this.mSelected = 4;
                    showRemindSelectDialog(this.mPregnancyRemindDaysIndex);
                    break;
                }
                break;
            case R.id.item_reminder_time /* 2131362471 */:
                if (this.mItemMenstrualCycleSwitch.getSwitchStatus()) {
                    showTimePickerDialog(this.mMenstrualRemind.hour, this.mMenstrualRemind.minute);
                    break;
                }
                break;
        }
    }

    private void showRemindSelectDialog(int i) {
        CommSelectDialogFragment commSelectDialogFragmentNewInstance = CommSelectDialogFragment.newInstance(this.mDaysArr, i);
        commSelectDialogFragmentNewInstance.show(getSupportFragmentManager());
        commSelectDialogFragmentNewInstance.setOnItemSelectedListener(this);
    }

    private void showTimePickerDialog(int i, int i2) {
        TimeDialogFragment timeDialogFragmentNewInstance;
        if (DateFormat.is24HourFormat(this)) {
            timeDialogFragmentNewInstance = TimeDialogFragment.newInstance(i, i2);
        } else {
            timeDialogFragmentNewInstance = TimeDialogFragment.newInstance(i, i2, DateFormat.is24HourFormat(this), this.mAmOrPm);
        }
        timeDialogFragmentNewInstance.show(getSupportFragmentManager());
        timeDialogFragmentNewInstance.setOnItemSelectedListener(this);
    }

    private void showDatePickerDialog() {
        int[] currentDate = DateUtil.getCurrentDate();
        DateDialogFragment dateDialogFragmentNewInstance = DateDialogFragment.newInstance(new int[]{HealthCareUtil.DEFAULT_START_YEAR, 1, 1}, new int[]{currentDate[0], currentDate[1], currentDate[2]}, this.mSelectedDate);
        dateDialogFragmentNewInstance.show(getSupportFragmentManager());
        dateDialogFragmentNewInstance.setOnDateSelectedListener(this);
    }

    private void showCycleLengthDialog(String[] strArr, int i) {
        CommSelectDialogFragment commSelectDialogFragmentNewInstance = CommSelectDialogFragment.newInstance(strArr, this.mDayUnit, i, 5);
        commSelectDialogFragmentNewInstance.show(getSupportFragmentManager());
        commSelectDialogFragmentNewInstance.setOnItemSelectedListener(this);
    }

    @Override // com.ido.life.customview.ReminderSelectView.OnReminderChangedListener
    public void onReminderChanged(int i) {
        boolean z = i != 3;
        Menstrual menstrual = this.mMenstrual;
        menstrual.notify_flag = i;
        this.mLayoutContent.setAlpha((z && menstrual.on_off == 170) ? 1.0f : 0.3f);
        this.mItemMenstrualCycleSwitch.setSwitchEnable(z);
        this.mItemMenstrualCycleSwitch.setSwitchStatus(z && this.mMenstrual.on_off == 170);
    }

    @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
    public void onSwitched(View view, boolean z) {
        MsgNotificationHelper.saveLog(BLEManager.isBind() + "---" + SPHelper.getDeviceList().isEmpty() + "----" + BLEManager.isConnected());
        if (!BLEManager.isConnected()) {
            this.mItemMenstrualCycleSwitch.setSwitchStatus(!r14.getSwitchStatus());
            DialogUtils.INSTANCE.showTipsDialog(getSupportFragmentManager(), "", LanguageUtil.getLanguageText(R.string.menstrual_cycle_setting_tip), "", LanguageUtil.getLanguageText(R.string.public_confirm), 17, true, false, false, true, 17, new CommonDialog.SampleDialogEventListener());
        } else {
            this.mMenstrual.on_off = z ? 170 : 85;
            this.mLayoutContent.setAlpha(z ? 1.0f : 0.3f);
        }
    }

    private boolean isReminderOpen() {
        Menstrual menstrual = this.mMenstrual;
        return (menstrual == null || menstrual.on_off != 170 || (((MenstrualCycleSettingPresenter) this.mPresenter).isSupportSetMenstrualNotifyFlag() && this.mMenstrual.notify_flag == 3)) ? false : true;
    }

    public boolean isBindDevice() {
        return (BLEManager.isBind() && !SPHelper.getDeviceList().isEmpty() && BLEManager.isConnected()) ? false : true;
    }

    private void updateRemindLayout() {
        boolean zIsReminderOpen = isReminderOpen();
        this.mLayoutContent.setAlpha(zIsReminderOpen ? 1.0f : 0.3f);
        this.mItemMenstrualCycleSwitch.setSwitchStatus(zIsReminderOpen);
    }

    @Override // com.ido.common.dialog.CommSelectDialogFragment.OnItemSelectedListener
    public void onItemSelected(int i) {
        int i2 = this.mSelected;
        if (i2 == 0) {
            this.mMenstrualRemindDaysIndex = i;
            MenstrualRemind menstrualRemind = this.mMenstrualRemind;
            int i3 = this.mMenstrualRemindDaysIndex;
            menstrualRemind.start_day = i3 + 1;
            this.mItemMenstrualRemind.setValue(this.mDaysArr[i3]);
            return;
        }
        if (i2 == 1) {
            this.mOvulationRemindDaysIndex = i;
            MenstrualRemind menstrualRemind2 = this.mMenstrualRemind;
            int i4 = this.mOvulationRemindDaysIndex;
            menstrualRemind2.ovulation_day = i4 + 1;
            this.mItemOvulationRemind.setValue(this.mDaysArr[i4]);
            return;
        }
        if (i2 == 2) {
            this.mMenstrualLengthIndex = i;
            this.mItemMenstrualLength.setValue(this.mMenstrualLengthArray[i] + this.mDayUnit);
            this.mMenstrual.menstrual_length = i + 1;
            return;
        }
        if (i2 != 3) {
            if (i2 != 4) {
                return;
            }
            this.mPregnancyRemindDaysIndex = i;
            MenstrualRemind menstrualRemind3 = this.mMenstrualRemind;
            int i5 = this.mPregnancyRemindDaysIndex;
            menstrualRemind3.pregnancy_day_before_remind = i5 + 1;
            this.mItemPregnancyRemind.setValue(this.mDaysArr[i5]);
            return;
        }
        this.mMenstrualCycleIndex = i;
        this.mItemPeriodLength.setValue(this.mCycleDataArray[i] + this.mDayUnit);
        this.mMenstrual.menstrual_cycle = i + 20;
    }

    @Override // com.ido.common.dialog.TimeDialogFragment.OnItemSelectedListener
    public void onTimeSelected(int i, int i2, int i3) {
        MenstrualRemind menstrualRemind = this.mMenstrualRemind;
        menstrualRemind.hour = i2;
        menstrualRemind.minute = i3;
        setRemindTime();
    }

    @Override // com.ido.life.base.BaseActivity
    protected void sendCmd() {
        super.sendCmd();
        this.mMenstrual.on_off = this.mItemMenstrualCycleSwitch.getSwitchStatus() ? 170 : 85;
        if (isDataChanged()) {
            if (HomeFragmentPresenter.mIsSyncing) {
                showToast(getLanguageText(R.string.syncing_pls_try_again_later));
                return;
            } else {
                showSettingLoading(false);
                ((MenstrualCycleSettingPresenter) this.mPresenter).sendMenstrualRemind2Device(this.mMenstrualRemind);
                return;
            }
        }
        finish();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (isDataChanged()) {
            saveData();
        } else {
            super.onBackPressed();
        }
    }

    private boolean isDataChanged() {
        return (this.mMenstrual.toString().equals(this.mDefaultMenstrual.toString()) && this.mMenstrualRemind.toString().equals(this.mDefaultMenstrualRemind.toString())) ? false : true;
    }

    @Override // com.ido.common.dialog.DateDialogFragment.OnDateSelectedListener
    public void onDateSelected(int i, int i2, int i3) {
        Menstrual menstrual = this.mMenstrual;
        menstrual.last_menstrual_year = i;
        menstrual.last_menstrual_month = i2;
        menstrual.last_menstrual_day = i3;
        int[] iArr = this.mSelectedDate;
        if (iArr == null) {
            this.mSelectedDate = new int[]{i, i2, i3};
        } else {
            iArr[0] = i;
            iArr[1] = i2;
            iArr[2] = i3;
        }
        this.mItemLastTime.setValue(DateUtil.format(DateUtil.getDate(this.mMenstrual.last_menstrual_year, this.mMenstrual.last_menstrual_month, this.mMenstrual.last_menstrual_day), DateUtil.DATE_FORMAT_DMY_1));
    }

    @Override // com.ido.life.module.device.view.IMenstrualCycleSettingView
    public void onsetMenstrualReminderSuccess() {
        ((MenstrualCycleSettingPresenter) this.mPresenter).sendMenstrual2Device(this.mMenstrual);
    }

    @Override // com.ido.life.module.device.view.IMenstrualCycleSettingView
    public void onsetMenstrualFailed() {
        this.mCommLoadingView.setVisibility(8);
        dismissLoadingDialog();
        showCmdResultToast(false);
        finish();
    }

    @Override // com.ido.life.module.device.view.IMenstrualCycleSettingView
    public void onSetMenstrualSuccess() {
        this.mCommLoadingView.setVisibility(8);
        dismissLoadingDialog();
        MenstruationConfig menstruationConfigQueryMenstruationConfig = GreenDaoUtil.queryMenstruationConfig(RunTimeUtil.getInstance().getUserId());
        if (menstruationConfigQueryMenstruationConfig == null) {
            menstruationConfigQueryMenstruationConfig = new MenstruationConfig();
            menstruationConfigQueryMenstruationConfig.setUserId(RunTimeUtil.getInstance().getUserId());
        }
        menstruationConfigQueryMenstruationConfig.setUploadSuccess(false);
        menstruationConfigQueryMenstruationConfig.setMensCycle(this.mMenstrual.menstrual_cycle);
        menstruationConfigQueryMenstruationConfig.setMensLength(this.mMenstrual.menstrual_length);
        ((MenstrualCycleSettingPresenter) this.mPresenter).uploadToServer(menstruationConfigQueryMenstruationConfig);
        setResult(1);
        finish();
    }
}