package com.ido.life.module.device.activity;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.ble.protocol.model.SPO2Param;
import com.ido.common.dialog.CommSelectDialogFragment;
import com.ido.common.log.CommonLogUtil;
import com.ido.life.base.BaseHealthMonitoringActivity;
import com.ido.life.customview.CustomToggleButton;
import com.ido.life.customview.ReminderSelectView;
import com.ido.life.customview.viewgroup.CommLoadingView;
import com.ido.life.customview.viewgroup.CustomItemLabelView;
import com.ido.life.module.device.presenter.BloodOxygenSettingPresenter;
import com.ido.life.module.device.view.IBloodOxygenView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class BloodOxySettingActivity extends BaseHealthMonitoringActivity<BloodOxygenSettingPresenter> implements IBloodOxygenView, CommSelectDialogFragment.OnItemSelectedListener {
    private List<String> ARR;
    private boolean isSupportSetBloodOxygenNotifyFlag = false;

    @BindView(R.id.comm_loading_view)
    CommLoadingView mCommLoadingView;

    @BindView(R.id.blood_oxygen_detect)
    CustomItemLabelView mItemBloodOxySwitch;

    @BindView(R.id.blood_remind_off)
    CustomItemLabelView mItemRemindSwitch;

    @BindView(R.id.layout_content)
    LinearLayout mLayoutContent;

    @BindView(R.id.oxygen_remind_value)
    CustomItemLabelView mRemindValueItem;
    private SPO2Param mSpO2Param;

    @BindView(R.id.low_blood_oxygen_hint)
    TextView mTvBloodHint;

    @BindView(R.id.vReminder)
    ReminderSelectView vReminder;

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_blood_oxy_setting;
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        this.mSpO2Param = ((BloodOxygenSettingPresenter) this.mPresenter).getLocationBloodOxyData();
        this.ARR = new ArrayList();
        for (int i = 75; i <= 90; i++) {
            this.ARR.add(i + "%");
        }
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        this.mTitleBar.setLeftOnClick(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$BloodOxySettingActivity$yqcgouFEq6qnO0PPfSCqYskG1_A
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initEvent$0$BloodOxySettingActivity(view);
            }
        });
        this.isSupportSetBloodOxygenNotifyFlag = ((BloodOxygenSettingPresenter) this.mPresenter).isSupportSetBloodOxygenNotifyFlag();
        this.vReminder.setVisibility(this.isSupportSetBloodOxygenNotifyFlag ? 0 : 8);
        this.vReminder.setOnReminderChangedListener(new ReminderSelectView.OnReminderChangedListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$BloodOxySettingActivity$aaygcdY99Ng3PpZgfDg4JiVH0-U
            @Override // com.ido.life.customview.ReminderSelectView.OnReminderChangedListener
            public final void onReminderChanged(int i) {
                this.f$0.lambda$initEvent$1$BloodOxySettingActivity(i);
            }
        });
        this.vReminder.select(this.mSpO2Param.notifyFlag);
        this.mItemBloodOxySwitch.setOnSwitchListener(new CustomToggleButton.OnSwitchListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$BloodOxySettingActivity$UYZbja8rPZl60Rd5yt7_CB29xnU
            @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
            public final void onSwitched(View view, boolean z) {
                this.f$0.lambda$initEvent$2$BloodOxySettingActivity(view, z);
            }
        });
        boolean z = (this.isSupportSetBloodOxygenNotifyFlag && this.vReminder.isDeny()) ? false : true;
        this.mItemRemindSwitch.setOnSwitchListener(new CustomToggleButton.OnSwitchListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$BloodOxySettingActivity$N4S2ploOPMDUcKAshTyhHDbFoo8
            @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
            public final void onSwitched(View view, boolean z2) {
                this.f$0.lambda$initEvent$3$BloodOxySettingActivity(view, z2);
            }
        });
        this.mItemBloodOxySwitch.setSwitchStatus(this.mSpO2Param.onOff == 170);
        this.mLayoutContent.setAlpha((z && this.mSpO2Param.onOff == 170) ? 1.0f : 0.3f);
        if (!((BloodOxygenSettingPresenter) this.mPresenter).isSupportLowValueRemind()) {
            this.mTvBloodHint.setVisibility(8);
            this.mItemRemindSwitch.setVisibility(8);
            this.mRemindValueItem.setVisibility(8);
        }
        updateRemindLayout();
    }

    public /* synthetic */ void lambda$initEvent$0$BloodOxySettingActivity(View view) {
        onBackPressed();
    }

    public /* synthetic */ void lambda$initEvent$1$BloodOxySettingActivity(int i) {
        this.mSpO2Param.notifyFlag = i;
        updateRemindLayout();
    }

    public /* synthetic */ void lambda$initEvent$2$BloodOxySettingActivity(View view, boolean z) {
        this.mSpO2Param.onOff = z ? 170 : 85;
        updateRemindLayout();
    }

    public /* synthetic */ void lambda$initEvent$3$BloodOxySettingActivity(View view, boolean z) {
        this.mSpO2Param.lowSpo2OnOff = z ? 170 : 85;
    }

    private boolean isRemindOpen() {
        SPO2Param sPO2Param = this.mSpO2Param;
        return (sPO2Param == null || sPO2Param.onOff != 170 || (((BloodOxygenSettingPresenter) this.mPresenter).isSupportSetBloodOxygenNotifyFlag() && this.mSpO2Param.notifyFlag == 3)) ? false : true;
    }

    private void updateRemindLayout() {
        boolean zIsRemindOpen = isRemindOpen();
        this.mLayoutContent.setAlpha(zIsRemindOpen ? 1.0f : 0.3f);
        this.mItemRemindSwitch.setSwitchEnable(zIsRemindOpen);
        this.mItemRemindSwitch.setSwitchStatus(zIsRemindOpen && this.mSpO2Param.lowSpo2OnOff == 170);
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        this.mTitleBar.setTitle(getLanguageText(R.string.public_spo2));
        this.mItemBloodOxySwitch.setTitle(getLanguageText(R.string.device_blood_monitoring));
        this.mItemRemindSwitch.setTitle(getLanguageText(R.string.blood_oxy_hint));
        this.mTvBloodHint.setText(getLanguageText(R.string.low_blood_oxygen_explained));
        this.mRemindValueItem.setValue(String.format(getString(R.string.battery_percent), Integer.valueOf(this.mSpO2Param.lowSpo2OnValue)));
    }

    @OnClick({R.id.oxygen_remind_value})
    public void onClick(View view) {
        if (isRemindOpen() && this.mSpO2Param.lowSpo2OnOff != 85) {
            showLowValueSelectDialog();
        }
    }

    private void showLowValueSelectDialog() {
        int iIndexOf = this.ARR.indexOf(this.mSpO2Param.lowSpo2OnValue + "%");
        if (iIndexOf < 0) {
            iIndexOf = 0;
        }
        CommSelectDialogFragment commSelectDialogFragmentNewInstance = CommSelectDialogFragment.newInstance((String[]) this.ARR.toArray(new String[0]), "", iIndexOf, 5);
        commSelectDialogFragmentNewInstance.show(getSupportFragmentManager());
        commSelectDialogFragmentNewInstance.setOnItemSelectedListener(this);
    }

    @Override // com.ido.common.dialog.CommSelectDialogFragment.OnItemSelectedListener
    public void onItemSelected(int i) {
        this.mSpO2Param.lowSpo2OnValue = Integer.parseInt(this.ARR.get(i).replace("%", ""));
        this.mRemindValueItem.setValue(String.format(getString(R.string.battery_percent), Integer.valueOf(this.mSpO2Param.lowSpo2OnValue)));
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
        return !this.mSpO2Param.toString().equals(((BloodOxygenSettingPresenter) this.mPresenter).getLocationBloodOxyData().toString());
    }

    @Override // com.ido.life.base.BaseActivity
    protected void sendCmd() {
        super.sendCmd();
        this.mSpO2Param.lowSpo2OnOff = this.mItemRemindSwitch.getSwitchStatus() ? 170 : 85;
        if (isDataChanged()) {
            showSettingLoading(false);
            ((BloodOxygenSettingPresenter) this.mPresenter).setBloodOxy2Device(this.mSpO2Param);
        } else {
            finish();
        }
    }

    @Override // com.ido.life.module.device.view.IBloodOxygenView
    public void onBloodSettingSuccess() {
        this.mCommLoadingView.setVisibility(8);
        dismissLoadingDialog();
        finish();
        CommonLogUtil.d("onBloodSettingSuccess");
    }

    @Override // com.ido.life.module.device.view.IBloodOxygenView
    public void onBloodSettingFailed() {
        this.mCommLoadingView.setVisibility(8);
        dismissLoadingDialog();
        showCmdResultToast(false);
        finish();
    }
}