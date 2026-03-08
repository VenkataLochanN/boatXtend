package com.ido.life.module.device.activity;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.PermissionUtil;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.base.BaseActivity;
import com.ido.life.boatservice.DeviceAssistService;
import com.ido.life.customview.CustomToggleButton;
import com.ido.life.customview.viewgroup.CustomItemLabelView;
import com.ido.life.module.device.contract.ContractActivity;
import com.ido.life.module.device.presenter.CallReminderPresenter;
import com.ido.life.module.device.view.ICallReminderView;

/* JADX INFO: loaded from: classes2.dex */
public class CallReminderActivity extends BaseActivity<CallReminderPresenter> implements CustomToggleButton.OnSwitchListener, ICallReminderView {

    @BindView(R.id.item_call_delay_reminder_switch)
    CustomItemLabelView delay3CallSwitch;
    private boolean isSupportCallAndRemind = false;

    @BindView(R.id.layout_phone)
    LinearLayout layoutPhone;

    @BindView(R.id.item_call_reminder_switch)
    CustomItemLabelView mItemCallReminderSwitch;

    @BindView(R.id.rtv_call_reminder_tip)
    RegularTextView mRtvCallReminderTip;

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_call_reminder;
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        this.isSupportCallAndRemind = ((CallReminderPresenter) this.mPresenter).isSupportCallAndRemind();
        this.mItemCallReminderSwitch.setSwitchStatus(((CallReminderPresenter) this.mPresenter).getCallReminderSwitch() && checkSelfPermission(PermissionUtil.getOnlyPhonePermission()));
        if (((CallReminderPresenter) this.mPresenter).getCallReminderSwitch() && !PermissionUtil.checkSelfPermission(this, PermissionUtil.getPhonePermission())) {
            requestPermissions(502, PermissionUtil.getPhonePermission());
        }
        this.delay3CallSwitch.setSwitchStatus(((CallReminderPresenter) this.mPresenter).getCallReminderDelay3Switch());
        this.mItemCallReminderSwitch.setOnSwitchListener(this);
        this.delay3CallSwitch.setOnSwitchListener(new CustomToggleButton.OnSwitchListener() { // from class: com.ido.life.module.device.activity.CallReminderActivity.1
            @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
            public void onSwitched(View view, boolean z) {
                ((CallReminderPresenter) CallReminderActivity.this.mPresenter).setCallReminderDelay3Switch(z);
            }
        });
        updateDelayStatus();
        this.layoutPhone.setVisibility(this.isSupportCallAndRemind ? 0 : 8);
    }

    private void updateDelayStatus() {
        if (this.mItemCallReminderSwitch.getSwitchStatus()) {
            this.delay3CallSwitch.setSwitchEnable(true);
            this.delay3CallSwitch.setAlpha(1.0f);
        } else {
            this.delay3CallSwitch.setSwitchEnable(false);
            this.delay3CallSwitch.setAlpha(0.3f);
        }
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        this.mTitleBar.setTitle(getLanguageText(this.isSupportCallAndRemind ? R.string.device_contract_conversation : R.string.device_call_reminder));
        this.mItemCallReminderSwitch.setTitle(getLanguageText(R.string.device_call_reminder));
        this.mRtvCallReminderTip.setText(getLanguageText(R.string.device_call_reminder_tip));
    }

    @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
    public void onSwitched(View view, boolean z) {
        if (z) {
            if (!PermissionUtil.checkSelfPermission(this, PermissionUtil.getPhonePermission())) {
                requestPermissions(502, PermissionUtil.getPhonePermission());
                return;
            }
            this.delay3CallSwitch.setSwitchEnable(true);
            ((CallReminderPresenter) this.mPresenter).setCallReminderSwitch(true);
            this.delay3CallSwitch.setAlpha(1.0f);
            startService(new Intent(this, (Class<?>) DeviceAssistService.class));
            return;
        }
        this.delay3CallSwitch.setSwitchEnable(false);
        ((CallReminderPresenter) this.mPresenter).setCallReminderSwitch(false);
        this.delay3CallSwitch.setAlpha(0.3f);
    }

    @Override // com.ido.common.base.BaseCoreActivity, androidx.fragment.app.FragmentActivity, android.app.Activity, androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i == 502) {
            if (PermissionUtil.checkSelfPermission(this, PermissionUtil.getOnlyPhonePermission())) {
                this.delay3CallSwitch.setSwitchEnable(true);
                ((CallReminderPresenter) this.mPresenter).setCallReminderSwitch(true);
                startService(new Intent(this, (Class<?>) DeviceAssistService.class));
            } else {
                ((CallReminderPresenter) this.mPresenter).setCallReminderSwitch(false);
                this.mItemCallReminderSwitch.setSwitchStatus(false);
            }
        }
    }

    @OnClick({R.id.item_contract})
    public void onViewClicked(View view) {
        if (view.getId() != R.id.item_contract) {
            return;
        }
        startActivity(new Intent(this, (Class<?>) ContractActivity.class));
    }

    @Override // com.ido.life.module.device.view.ICallReminderView
    public void showLoading() {
        showSettingLoading(true);
    }

    @Override // com.ido.life.module.device.view.ICallReminderView
    public void hideLoading() {
        dismissLoadingDialog();
    }

    @Override // com.ido.life.module.device.view.ICallReminderView
    public void onCallReminderStatusSetFailed() {
        hideLoading();
        CommonLogUtil.printAndSave("onCallReminderStatusSetFailed");
        this.mItemCallReminderSwitch.setSwitchStatus(!r0.getSwitchStatus());
        updateDelayStatus();
    }

    @Override // com.ido.life.module.device.view.ICallReminderView
    public void onCallReminderStatusSetSuccess() {
        hideLoading();
        CommonLogUtil.printAndSave("onCallReminderStatusSetSuccess");
    }
}