package com.ido.life.dialog;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.alexa.AlexaApi;
import com.ido.alexa.bean.AvsException;
import com.ido.alexa.callbacks.IAlexaCallBack;
import com.ido.common.IdoApp;
import com.ido.common.base.BaseDialogFragment;
import com.ido.common.utils.DipPixelUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.PermissionUtil;
import com.ido.common.utils.ScreenUtil;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.base.SingleTopIntent;
import com.ido.life.boatservice.DeviceAssistService;
import com.ido.life.customview.CustomToggleButton;
import com.ido.life.customview.viewgroup.CustomItemLabelView;
import com.ido.life.module.alexa.AlexaLoginActivity;
import com.ido.life.module.device.activity.CommonWebViewActivity;
import com.ido.life.module.device.activity.NativeWebViewActivity;
import com.ido.life.module.device.activity.NotificationActivity;
import com.ido.life.module.device.activity.NotificationSettingActivity;
import com.ido.life.module.main.MainQuicklySettingPresenter;
import com.ido.life.module.user.set.settingitem.SettingItemActivity;
import com.ido.life.module.user.usertarget.UserTargetActivity;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.NoticeAppUtil;
import com.ido.life.util.RunTimeUtil;
import com.ido.life.util.eventbus.EventBusHelper;

/* JADX INFO: loaded from: classes2.dex */
public class MainQuicklySettingDialog extends BaseDialogFragment implements CustomToggleButton.OnSwitchListener, MainQuicklySettingPresenter.QuicklySettingCallback {

    @BindView(R.id.item_amazon_alexa)
    CustomItemLabelView mItemAmazonAlexa;

    @BindView(R.id.item_call_remind)
    CustomItemLabelView mItemCallRemind;

    @BindView(R.id.item_msg_notification)
    CustomItemLabelView mItemMsgNotification;

    @BindView(R.id.item_preferences)
    CustomItemLabelView mItemPreferences;

    @BindView(R.id.item_target)
    RelativeLayout mItemTarget;
    private long mLastClickTime;

    @BindView(R.id.layout_dialog_root)
    LinearLayout mLayoutDialogRoot;

    @BindView(R.id.rtv_target_step)
    RegularTextView mRtvTargetStep;

    @BindView(R.id.rtv_target_weight)
    RegularTextView mRtvTargetWeight;

    @BindView(R.id.tv_device_name)
    TextView mTvDeviceName;

    @BindView(R.id.tv_electronic_instruction)
    RegularTextView mTvElectronicInstruction;

    @Override // com.ido.common.base.BaseDialogFragment
    protected int getLayoutResId() {
        return R.layout.dialog_main_quickly_setting;
    }

    public static MainQuicklySettingDialog getInstance() {
        Bundle bundle = new Bundle();
        MainQuicklySettingDialog mainQuicklySettingDialog = new MainQuicklySettingDialog();
        mainQuicklySettingDialog.setArguments(bundle);
        mainQuicklySettingDialog.setStyle(1, 2131886083);
        mainQuicklySettingDialog.setCancelable(false);
        return mainQuicklySettingDialog;
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected void initData() {
        super.initData();
        this.mTvElectronicInstruction.setVisibility(0);
        MainQuicklySettingPresenter.getInstance().requestInstructionInfo(this);
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected void initUI(View view) {
        if (getDialog() == null || getDialog().getWindow() == null) {
            return;
        }
        Window window = getDialog().getWindow();
        window.getDecorView().setPadding(DipPixelUtil.dip2px(10.0f), (int) (ScreenUtil.getScreenH() * 0.2f), DipPixelUtil.dip2px(10.0f), DipPixelUtil.dip2px(15.0f));
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = 80;
        attributes.width = -1;
        window.setAttributes(attributes);
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected void initListener(View view) {
        super.initListener(view);
        if (MainQuicklySettingPresenter.getInstance().isSupportCallRemind()) {
            this.mItemCallRemind.setOnSwitchListener(this);
        }
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        initItemDisplayStatus();
    }

    private void initItemDisplayStatus() {
        this.mTvDeviceName.setText(MainQuicklySettingPresenter.getInstance().getDeviceName());
        this.mItemTarget.setVisibility(8);
        this.mRtvTargetStep.setText(String.valueOf(MainQuicklySettingPresenter.getInstance().getStepTarget()));
        this.mItemMsgNotification.setVisibility(0);
        this.mItemMsgNotification.setValue(MainQuicklySettingPresenter.getInstance().isNotificationTurnOn() ? R.string.public_opened : R.string.public_closed);
        if (MainQuicklySettingPresenter.getInstance().isSupportCallRemind()) {
            this.mItemCallRemind.setVisibility(0);
            this.mItemCallRemind.setSwitchStatus(MainQuicklySettingPresenter.getInstance().isCallRemindTurnOn() && PermissionUtil.checkSelfPermission(IdoApp.getAppContext(), PermissionUtil.getOnlyPhonePermission()));
        } else {
            this.mItemCallRemind.setVisibility(8);
        }
        if (MainQuicklySettingPresenter.getInstance().isSupportAlexa()) {
            this.mItemAmazonAlexa.setVisibility(0);
            AlexaApi.getToken(IdoApp.getAppContext(), new IAlexaCallBack() { // from class: com.ido.life.dialog.MainQuicklySettingDialog.1
                @Override // com.ido.alexa.callbacks.IAlexaCallBack
                public void success(String str) {
                    MainQuicklySettingDialog.this.mItemAmazonAlexa.setValue(R.string.main_already_logged_in);
                }

                @Override // com.ido.alexa.callbacks.IAlexaCallBack
                public void failure(AvsException avsException) {
                    MainQuicklySettingDialog.this.mItemAmazonAlexa.setValue(R.string.not_log_in);
                }
            });
        } else {
            this.mItemAmazonAlexa.setVisibility(8);
        }
        this.mItemPreferences.setVisibility(0);
        setUnit();
    }

    @OnClick({R.id.tv_electronic_instruction, R.id.item_target, R.id.item_msg_notification, R.id.item_amazon_alexa, R.id.item_preferences, R.id.tv_confirm})
    public void onViewClicked(View view) {
        if (clickValid()) {
            switch (view.getId()) {
                case R.id.item_amazon_alexa /* 2131362382 */:
                    startActivityForResult(new SingleTopIntent(getActivity(), (Class<?>) AlexaLoginActivity.class), 3);
                    break;
                case R.id.item_msg_notification /* 2131362443 */:
                    startActivityForResult(new SingleTopIntent(getActivity(), (Class<?>) (NoticeAppUtil.isSupportV3Notify() ? NotificationSettingActivity.class : NotificationActivity.class)), 2);
                    break;
                case R.id.item_preferences /* 2131362457 */:
                    SingleTopIntent singleTopIntent = new SingleTopIntent(getActivity(), (Class<?>) SettingItemActivity.class);
                    singleTopIntent.putExtra(SettingItemActivity.getSETTING_TYPE(), SettingItemActivity.getTYPE_SETTING_UNIT());
                    startActivityForResult(singleTopIntent, 3);
                    break;
                case R.id.item_target /* 2131362489 */:
                    startActivityForResult(new SingleTopIntent(getActivity(), (Class<?>) UserTargetActivity.class), 1);
                    break;
                case R.id.tv_confirm /* 2131363854 */:
                    dismissAllowingStateLoss();
                    MainQuicklySettingPresenter.getInstance().setBindNewDeviceState(false);
                    EventBusHelper.post(103);
                    break;
                case R.id.tv_electronic_instruction /* 2131363916 */:
                    startActivity(new SingleTopIntent(getActivity(), (Class<?>) NativeWebViewActivity.class).putExtra("type", 8).putExtra(CommonWebViewActivity.FORM_URL, MainQuicklySettingPresenter.getInstance().mInstructionUrl + "?app=boatWave"));
                    break;
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == 2) {
            this.mItemMsgNotification.setValue(MainQuicklySettingPresenter.getInstance().isNotificationTurnOn() ? R.string.public_opened : R.string.public_closed);
        } else if (i2 == 3) {
            this.mItemAmazonAlexa.setValue(MainQuicklySettingPresenter.getInstance().isAlexaLoggedIn() ? R.string.main_already_logged_in : R.string.not_log_in);
        } else {
            if (i2 != 4) {
                return;
            }
            setUnit();
        }
    }

    private void setUnit() {
        if (GreenDaoUtil.queryUnitSetting(RunTimeUtil.getInstance().getUserId()).getUnit() == 1) {
            this.mItemPreferences.setValue(LanguageUtil.getLanguageText(R.string.mine_unit_metric_system));
        } else {
            this.mItemPreferences.setValue(LanguageUtil.getLanguageText(R.string.mine_unit_british_system));
        }
    }

    @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
    public void onSwitched(View view, boolean z) {
        if (z) {
            if (!PermissionUtil.checkSelfPermission(IdoApp.getAppContext(), PermissionUtil.getPhonePermission())) {
                PermissionUtil.requestPermissions(this, 502, PermissionUtil.getPhonePermission());
                return;
            } else {
                MainQuicklySettingPresenter.getInstance().setCallReminderSwitch(true);
                IdoApp.getAppContext().startService(new Intent(IdoApp.getAppContext(), (Class<?>) DeviceAssistService.class));
                return;
            }
        }
        MainQuicklySettingPresenter.getInstance().setCallReminderSwitch(false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i == 502) {
            if (PermissionUtil.checkSelfPermission(IdoApp.getAppContext(), PermissionUtil.getOnlyPhonePermission())) {
                MainQuicklySettingPresenter.getInstance().setCallReminderSwitch(true);
                IdoApp.getAppContext().startService(new Intent(IdoApp.getAppContext(), (Class<?>) DeviceAssistService.class));
            } else {
                MainQuicklySettingPresenter.getInstance().setCallReminderSwitch(false);
                this.mItemCallRemind.setSwitchStatus(false);
            }
        }
    }

    @Override // com.ido.life.module.main.MainQuicklySettingPresenter.QuicklySettingCallback
    public void onGetInstructionInfo(String str) {
        if (this.mTvElectronicInstruction == null) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            this.mTvElectronicInstruction.setVisibility(8);
        } else {
            this.mTvElectronicInstruction.setVisibility(0);
        }
    }

    private boolean clickValid() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (Math.abs(this.mLastClickTime - jCurrentTimeMillis) < 500) {
            return false;
        }
        this.mLastClickTime = jCurrentTimeMillis;
        return true;
    }
}