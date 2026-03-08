package com.ido.life.module.device.activity;

import android.content.Intent;
import android.view.View;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.common.log.CommonLogUtil;
import com.ido.life.base.BaseActivity;
import com.ido.life.base.BaseMessage;
import com.ido.life.base.SingleTopIntent;
import com.ido.life.constants.Constants;
import com.ido.life.customview.viewgroup.CustomItemLabelView;
import com.ido.life.module.device.presenter.HealthMonitoringPresenter;
import com.ido.life.module.device.view.IHealthMonitoringView;
import com.ido.life.module.home.menstrualcycle.guide.MenstrualCycleGuideActivity;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RunTimeUtil;

/* JADX INFO: loaded from: classes2.dex */
public class HealthMonitoringActivity extends BaseActivity<HealthMonitoringPresenter> implements IHealthMonitoringView {
    private static final int HEART_RATE_REQUEST_CODE = 1000;
    private static final int SETTING_MENSTRUAL_REQUEST = 1;
    private final int SETTING_NOISE_MONITORING = 2;
    private boolean isVisible;
    private HealthMonitoringActivity mActivity;
    private int mHeartRateMode;

    @BindView(R.id.device_blood_detection)
    CustomItemLabelView mItemBloodDetection;

    @BindView(R.id.item_heart_rate_detection)
    CustomItemLabelView mItemHeartRateDetection;

    @BindView(R.id.item_menstrual_cycle)
    CustomItemLabelView mItemMenstrualCycle;

    @BindView(R.id.item_pressure_detection)
    CustomItemLabelView mItemPressureDetection;

    @BindView(R.id.item_science_sleep)
    CustomItemLabelView mItemScienceSleep;

    @BindView(R.id.item_science_volume)
    CustomItemLabelView mItemVolumeReminder;

    @BindView(R.id.item_walk_reminder)
    CustomItemLabelView mItemWalkReminder;

    @BindView(R.id.item_water_clock)
    CustomItemLabelView mItemWaterClock;

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_health_monitoring;
    }

    @Override // com.ido.life.module.device.view.IHealthMonitoringView
    public void onSetFitnessGuidanceSuccess() {
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        this.mActivity = this;
        if (((HealthMonitoringPresenter) this.mPresenter).isSupportSmartHeartRate()) {
            setItemValueByStatus(this.mItemHeartRateDetection, ((HealthMonitoringPresenter) this.mPresenter).getSmartHeartMonitoringSwitch());
        } else {
            ((HealthMonitoringPresenter) this.mPresenter).getHeartRateMode(true);
        }
        setItemValueByStatus(this.mItemPressureDetection, ((HealthMonitoringPresenter) this.mPresenter).getPressureSwitch());
        setItemValueByStatus(this.mItemWaterClock, ((HealthMonitoringPresenter) this.mPresenter).getWaterClockSwitch());
        setItemValueByStatus(this.mItemWalkReminder, ((HealthMonitoringPresenter) this.mPresenter).getWalkReminderSwitch());
        setItemValueByStatus(this.mItemVolumeReminder, ((HealthMonitoringPresenter) this.mPresenter).getVolumeReminderSwitch());
        setItemValueByStatus(this.mItemMenstrualCycle, ((HealthMonitoringPresenter) this.mPresenter).getMenstrualCycleSwitch());
        setItemValueByStatus(this.mItemBloodDetection, ((HealthMonitoringPresenter) this.mPresenter).getBloodOxyDetectionSwitch());
        setItemValueByStatus(this.mItemScienceSleep, ((HealthMonitoringPresenter) this.mPresenter).getSleepMonitoringSwitch());
        initFunctionItem();
    }

    private void initFunctionItem() {
        this.mItemHeartRateDetection.setVisibility(((HealthMonitoringPresenter) this.mPresenter).isSupportHeartRateDetection() ? 0 : 8);
        this.mItemPressureDetection.setVisibility(((HealthMonitoringPresenter) this.mPresenter).isSupportPressureDetection() ? 0 : 8);
        this.mItemWaterClock.setVisibility(((HealthMonitoringPresenter) this.mPresenter).isSupportWaterClock() ? 0 : 8);
        this.mItemWalkReminder.setVisibility(((HealthMonitoringPresenter) this.mPresenter).isSupportWalkReminder() ? 0 : 8);
        this.mItemMenstrualCycle.setVisibility(((HealthMonitoringPresenter) this.mPresenter).isSupportMenstruation() ? 0 : 8);
        this.mItemBloodDetection.setVisibility(((HealthMonitoringPresenter) this.mPresenter).isSupportBloodOxygenDetection() ? 0 : 8);
        this.mItemScienceSleep.setVisibility(((HealthMonitoringPresenter) this.mPresenter).isSupportScienceSleep() ? 0 : 8);
        this.mItemVolumeReminder.setVisibility(((HealthMonitoringPresenter) this.mPresenter).isSupportVolume() ? 0 : 8);
    }

    @Override // com.ido.life.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        this.isVisible = true;
    }

    @Override // com.ido.life.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        this.isVisible = false;
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        this.mTitleBar.setTitle(getLanguageText(R.string.device_health_monitoring));
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1 && i2 == -1) {
            startActivity(new SingleTopIntent(this.mActivity, (Class<?>) MenstrualCycleSettingActivity.class));
        } else if (i == 2 && i2 == -1) {
            onSetNoiseMonitoringSuccess();
        }
    }

    @OnClick({R.id.item_heart_rate_detection, R.id.item_pressure_detection, R.id.item_water_clock, R.id.device_blood_detection, R.id.item_walk_reminder, R.id.item_science_volume, R.id.item_menstrual_cycle, R.id.item_science_sleep})
    public void onViewClicked(View view) {
        if (!((HealthMonitoringPresenter) this.mPresenter).isConnected()) {
            showToast(getLanguageText(R.string.device_pls_connect_device));
        }
        switch (view.getId()) {
            case R.id.device_blood_detection /* 2131362098 */:
                startActivity(new SingleTopIntent(this.mActivity, (Class<?>) BloodOxySettingActivity.class));
                break;
            case R.id.item_heart_rate_detection /* 2131362418 */:
                SingleTopIntent singleTopIntent = new SingleTopIntent(this.mActivity, (Class<?>) HeartRateMonitoringActivity.class);
                singleTopIntent.putExtra(HeartRateMonitoringActivity.MODE, this.mHeartRateMode);
                startActivity(singleTopIntent);
                break;
            case R.id.item_menstrual_cycle /* 2131362431 */:
                if (GreenDaoUtil.queryMenstruationConfig(RunTimeUtil.getInstance().getUserId()) == null) {
                    Intent intent = new Intent(this, (Class<?>) MenstrualCycleGuideActivity.class);
                    intent.putExtra(Constants.INTENT_USER_ID, RunTimeUtil.getInstance().getUserId());
                    intent.putExtra(Constants.INTENT_DATA_KEY, false);
                    startActivityForResult(intent, 1);
                } else {
                    SingleTopIntent singleTopIntent2 = new SingleTopIntent(this.mActivity, (Class<?>) MenstrualCycleSettingActivity.class);
                    singleTopIntent2.putExtra(Constants.INTENT_USER_ID, RunTimeUtil.getInstance().getUserId());
                    startActivity(singleTopIntent2);
                }
                break;
            case R.id.item_pressure_detection /* 2131362459 */:
                startActivity(new SingleTopIntent(this.mActivity, (Class<?>) PressureMonitoringActivity.class));
                break;
            case R.id.item_science_sleep /* 2131362478 */:
                startActivity(new SingleTopIntent(this.mActivity, (Class<?>) ScientificSleepMonitoringActivity.class));
                break;
            case R.id.item_science_volume /* 2131362479 */:
                startActivityForResult(new SingleTopIntent(this.mActivity, (Class<?>) NoiseMonitoringActivity.class), 2);
                break;
            case R.id.item_walk_reminder /* 2131362503 */:
                startActivity(new SingleTopIntent(this.mActivity, (Class<?>) WalkReminderActivity.class));
                break;
            case R.id.item_water_clock /* 2131362507 */:
                startActivity(new SingleTopIntent(this.mActivity, (Class<?>) WaterClockActivity.class));
                break;
        }
    }

    @Override // com.ido.life.module.device.view.IHealthMonitoringView
    public void onGetHeartRateModeSuccess(int i) {
        this.mHeartRateMode = i;
        CommonLogUtil.printAndSave("V2返回的onGetHeartRateModeSuccess是 = " + i);
        setItemValueByStatus(this.mItemHeartRateDetection, i == 136 || i == 153 || i == 204 || i == 221);
    }

    @Override // com.ido.life.module.device.view.IHealthMonitoringView
    public void onGetHeartRateModeV3Success(int i, int i2) {
        CommonLogUtil.printAndSave("V3返回的onGetHeartRateModeV3Success是 = " + i);
        if (i == 187 && i2 > 0) {
            i = 204;
        }
        this.mHeartRateMode = i;
        setItemValueByStatus(this.mItemHeartRateDetection, i == 136 || i == 153 || i == 204 || i == 221);
    }

    @Override // com.ido.life.module.device.view.IHealthMonitoringView
    public void onSetHeartRateModeSuccess() {
        ((HealthMonitoringPresenter) this.mPresenter).getHeartRateMode(false);
    }

    @Override // com.ido.life.module.device.view.IHealthMonitoringView
    public void onSetPressureModeSuccess() {
        setItemValueByStatus(this.mItemPressureDetection, ((HealthMonitoringPresenter) this.mPresenter).getPressureSwitch());
    }

    @Override // com.ido.life.module.device.view.IHealthMonitoringView
    public void onSetWaterClockSuccess() {
        setItemValueByStatus(this.mItemWaterClock, ((HealthMonitoringPresenter) this.mPresenter).getWaterClockSwitch());
    }

    @Override // com.ido.life.module.device.view.IHealthMonitoringView
    public void onSetWalkReminderSuccess() {
        setItemValueByStatus(this.mItemWalkReminder, ((HealthMonitoringPresenter) this.mPresenter).getWalkReminderSwitch());
    }

    @Override // com.ido.life.module.device.view.IHealthMonitoringView
    public void onSetMenstrualSuccess() {
        setItemValueByStatus(this.mItemMenstrualCycle, ((HealthMonitoringPresenter) this.mPresenter).getMenstrualCycleSwitch());
    }

    @Override // com.ido.life.module.device.view.IHealthMonitoringView
    public void onSetBloodOxygenSuccess() {
        setItemValueByStatus(this.mItemBloodDetection, ((HealthMonitoringPresenter) this.mPresenter).getBloodOxyDetectionSwitch());
    }

    @Override // com.ido.life.module.device.view.IHealthMonitoringView
    public void onSetScienceSleepSuccess() {
        setItemValueByStatus(this.mItemScienceSleep, ((HealthMonitoringPresenter) this.mPresenter).getSleepMonitoringSwitch());
    }

    public void onSetNoiseMonitoringSuccess() {
        setItemValueByStatus(this.mItemVolumeReminder, ((HealthMonitoringPresenter) this.mPresenter).getVolumeReminderSwitch());
    }

    @Override // com.ido.life.module.device.view.IHealthMonitoringView
    public void onsetCmdFailed() {
        if (this.isVisible) {
            showCmdResultToast(false);
        }
    }

    @Override // com.ido.life.module.device.view.IHealthMonitoringView
    public void onSetSmartHeartRateModeSuccess() {
        setItemValueByStatus(this.mItemHeartRateDetection, ((HealthMonitoringPresenter) this.mPresenter).getSmartHeartMonitoringSwitch());
    }

    private void setItemValueByStatus(CustomItemLabelView customItemLabelView, boolean z) {
        customItemLabelView.setValue(getLanguageText(z ? R.string.public_opened : R.string.public_closed));
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.life.util.eventbus.IHandlerEventBus
    public void handleMessage(BaseMessage baseMessage) {
        super.handleMessage(baseMessage);
        if (baseMessage.getType() == 915) {
            onSetSmartHeartRateModeSuccess();
        }
    }
}