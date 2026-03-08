package com.ido.life.module.user.set.privacyandsecurity;

import android.view.View;
import butterknife.BindView;
import com.boat.Xtend.two.R;
import com.ido.common.dialog.CommBottomConfirmDialog;
import com.ido.life.base.BaseActivity;
import com.ido.life.customview.CustomToggleButton;
import com.ido.life.customview.NormalToast;
import com.ido.life.customview.viewgroup.CustomItemLabelView;
import com.ido.life.database.model.PrivateSafeSetting;
import com.ido.life.util.RunTimeUtil;

/* JADX INFO: loaded from: classes3.dex */
public class PrivacyAndSecurityActivity extends BaseActivity<PrivacyAndSecurityPresenter> implements IPrivacyAndSecurityView, View.OnClickListener {

    @BindView(R.id.item_clear)
    CustomItemLabelView mItemClear;

    @BindView(R.id.item_health)
    CustomItemLabelView mItemHealth;

    @BindView(R.id.item_private)
    CustomItemLabelView mItemPrivate;

    @BindView(R.id.item_sport)
    CustomItemLabelView mItemSport;

    @BindView(R.id.item_walk_reminder_switch)
    CustomItemLabelView mItemWalkReminderSwitch;

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_privacy_and_security;
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.common.base.BaseCoreActivity
    public void initViews() {
        super.initViews();
        this.mTitleBar.initLayout(0).setTitle(getLanguageText(R.string.private_safe));
        if (RunTimeUtil.getInstance().hasLogin()) {
            this.mItemWalkReminderSwitch.setVisibility(0);
            this.mItemPrivate.setVisibility(0);
            this.mItemSport.setVisibility(0);
            this.mItemHealth.setVisibility(0);
            this.mItemClear.setVisibility(0);
            return;
        }
        this.mItemWalkReminderSwitch.setVisibility(8);
        this.mItemPrivate.setVisibility(8);
        this.mItemSport.setVisibility(8);
        this.mItemHealth.setVisibility(8);
        this.mItemClear.setVisibility(8);
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        this.mItemPrivate.setOnSwitchListener(new CustomToggleButton.OnSwitchListener() { // from class: com.ido.life.module.user.set.privacyandsecurity.-$$Lambda$PrivacyAndSecurityActivity$vAIL7KJJINr_9es9733wfB-nIvM
            @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
            public final void onSwitched(View view, boolean z) {
                this.f$0.lambda$initEvent$0$PrivacyAndSecurityActivity(view, z);
            }
        });
        this.mItemSport.setOnSwitchListener(new CustomToggleButton.OnSwitchListener() { // from class: com.ido.life.module.user.set.privacyandsecurity.-$$Lambda$PrivacyAndSecurityActivity$zU3vNhNy3zieFmDcFAZ_nBHqyws
            @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
            public final void onSwitched(View view, boolean z) {
                this.f$0.lambda$initEvent$1$PrivacyAndSecurityActivity(view, z);
            }
        });
        this.mItemHealth.setOnSwitchListener(new CustomToggleButton.OnSwitchListener() { // from class: com.ido.life.module.user.set.privacyandsecurity.-$$Lambda$PrivacyAndSecurityActivity$fG-6272StZxjN_pc74vRghkJJwQ
            @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
            public final void onSwitched(View view, boolean z) {
                this.f$0.lambda$initEvent$2$PrivacyAndSecurityActivity(view, z);
            }
        });
    }

    public /* synthetic */ void lambda$initEvent$0$PrivacyAndSecurityActivity(View view, boolean z) {
        if (z) {
            showPersonalMessageDialog(getLanguageText(R.string.mine_private_safe_personal_open), z);
        } else {
            showPersonalMessageDialog(getLanguageText(R.string.mine_private_safe_personal_close), z);
        }
    }

    public /* synthetic */ void lambda$initEvent$1$PrivacyAndSecurityActivity(View view, boolean z) {
        if (z) {
            showSportDataDialog(getLanguageText(R.string.mine_private_safe_sport_open), z);
        } else {
            showSportDataDialog(getLanguageText(R.string.mine_private_safe_sport_close), z);
        }
    }

    public /* synthetic */ void lambda$initEvent$2$PrivacyAndSecurityActivity(View view, boolean z) {
        if (z) {
            showHealthDataDialog(getLanguageText(R.string.mine_private_safe_health_open), z);
        } else {
            showHealthDataDialog(getLanguageText(R.string.mine_private_safe_health_close), z);
        }
    }

    @Override // com.ido.life.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        ((PrivacyAndSecurityPresenter) this.mPresenter).saveConfig(this.mItemPrivate.getSwitchStatus(), this.mItemSport.getSwitchStatus(), this.mItemHealth.getSwitchStatus());
        super.onDestroy();
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        ((PrivacyAndSecurityPresenter) this.mPresenter).getConfig();
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        this.mTitleBar.setTitle(getLanguageText(R.string.private_safe));
        this.mItemWalkReminderSwitch.setTitle(getLanguageText(R.string.me_data_manaage));
        this.mItemPrivate.setTitle(getLanguageText(R.string.me_sync_userinfo_clound));
        this.mItemSport.setTitle(getLanguageText(R.string.me_sync_sport_clound));
        this.mItemHealth.setTitle(getLanguageText(R.string.me_sync_health_clound));
        this.mItemClear.setTitle(getLanguageText(R.string.me_clear_personal_data));
    }

    @Override // com.ido.life.module.user.set.privacyandsecurity.IPrivacyAndSecurityView
    public void onConfigSuccess() {
        finish();
    }

    @Override // com.ido.life.module.user.set.privacyandsecurity.IPrivacyAndSecurityView
    public void onConfigFailed() {
        finish();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        enableSwitch(false);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        enableSwitch(true);
    }

    private void enableSwitch(boolean z) {
        this.mItemPrivate.setSwitchEnable(z);
        this.mItemSport.setSwitchEnable(z);
        this.mItemHealth.setSwitchEnable(z);
    }

    @Override // com.ido.life.module.user.set.privacyandsecurity.IPrivacyAndSecurityView
    public void getConfigSuccess(PrivateSafeSetting privateSafeSetting) {
        if (privateSafeSetting != null) {
            this.mItemPrivate.setSwitchStatus(privateSafeSetting.getSavePrivateData());
            this.mItemSport.setSwitchStatus(privateSafeSetting.getSaveSportData());
            this.mItemHealth.setSwitchStatus(privateSafeSetting.getSaveHealthData());
        }
    }

    @Override // com.ido.life.module.user.set.privacyandsecurity.IPrivacyAndSecurityView
    public void getConfigFailed() {
        this.mItemPrivate.setSwitchStatus(RunTimeUtil.getInstance().enableUploadPrivateData());
        this.mItemSport.setSwitchStatus(RunTimeUtil.getInstance().enableUploadSportData());
        this.mItemHealth.setSwitchStatus(RunTimeUtil.getInstance().enableUploadHealthData());
    }

    @Override // com.ido.life.module.user.set.privacyandsecurity.IPrivacyAndSecurityView
    public void deleteServerDataSuccess() {
        NormalToast.showToast(getLanguageText(R.string.clear_success), 2000);
    }

    @Override // com.ido.life.module.user.set.privacyandsecurity.IPrivacyAndSecurityView
    public void deleteServerDataFailed() {
        NormalToast.showToast(getLanguageText(R.string.clear_failed), 2000);
    }

    @Override // com.ido.life.base.IBaseLoadingView
    public void showLoading() {
        showLoadingDialog();
    }

    @Override // com.ido.life.base.IBaseLoadingView
    public void dismissLoading() {
        dismissLoadingDialog();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() != R.id.item_clear) {
            return;
        }
        showClearCacheDialog();
    }

    private void showClearCacheDialog() {
        final CommBottomConfirmDialog commBottomConfirmDialogNewInstance = CommBottomConfirmDialog.newInstance(getLanguageText(R.string.register_cloud_sync_title), getLanguageText(R.string.private_clear_data), getLanguageText(R.string.public_tip_confirm), getLanguageText(R.string.public_tip_cancel), true);
        commBottomConfirmDialogNewInstance.setCancelListener(new View.OnClickListener() { // from class: com.ido.life.module.user.set.privacyandsecurity.-$$Lambda$PrivacyAndSecurityActivity$JZGU5V42Qaj_Dr0OFhdMrkZ7gjc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                commBottomConfirmDialogNewInstance.dismissAllowingStateLoss();
            }
        });
        commBottomConfirmDialogNewInstance.setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.user.set.privacyandsecurity.-$$Lambda$PrivacyAndSecurityActivity$Wlub41ZE9cbz18V6QFfLqGHI_9g
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$showClearCacheDialog$4$PrivacyAndSecurityActivity(commBottomConfirmDialogNewInstance, view);
            }
        });
        commBottomConfirmDialogNewInstance.show(getSupportFragmentManager());
    }

    public /* synthetic */ void lambda$showClearCacheDialog$4$PrivacyAndSecurityActivity(CommBottomConfirmDialog commBottomConfirmDialog, View view) {
        commBottomConfirmDialog.dismissAllowingStateLoss();
        ((PrivacyAndSecurityPresenter) this.mPresenter).clearServerData();
    }

    private void showPersonalMessageDialog(String str, final boolean z) {
        final CommBottomConfirmDialog commBottomConfirmDialogNewInstance = CommBottomConfirmDialog.newInstance(getLanguageText(R.string.register_cloud_sync_title), str, getLanguageText(R.string.public_tip_confirm), getLanguageText(R.string.public_tip_cancel), true);
        commBottomConfirmDialogNewInstance.setCancelable(false);
        commBottomConfirmDialogNewInstance.setCancelListener(new View.OnClickListener() { // from class: com.ido.life.module.user.set.privacyandsecurity.-$$Lambda$PrivacyAndSecurityActivity$knP0UuXz6B7ZIgXJMsMahT5eY1w
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$showPersonalMessageDialog$5$PrivacyAndSecurityActivity(commBottomConfirmDialogNewInstance, z, view);
            }
        });
        commBottomConfirmDialogNewInstance.setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.user.set.privacyandsecurity.-$$Lambda$PrivacyAndSecurityActivity$X8gPnNjMCf8DF0NIe7alHVwN5_M
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                commBottomConfirmDialogNewInstance.dismissAllowingStateLoss();
            }
        });
        commBottomConfirmDialogNewInstance.show(getSupportFragmentManager());
    }

    public /* synthetic */ void lambda$showPersonalMessageDialog$5$PrivacyAndSecurityActivity(CommBottomConfirmDialog commBottomConfirmDialog, boolean z, View view) {
        commBottomConfirmDialog.dismissAllowingStateLoss();
        this.mItemPrivate.setSwitchStatus(!z);
    }

    private void showSportDataDialog(String str, final boolean z) {
        final CommBottomConfirmDialog commBottomConfirmDialogNewInstance = CommBottomConfirmDialog.newInstance(getLanguageText(R.string.register_cloud_sync_title), str, getLanguageText(R.string.public_tip_confirm), getLanguageText(R.string.public_tip_cancel), true);
        commBottomConfirmDialogNewInstance.setCancelable(false);
        commBottomConfirmDialogNewInstance.setCancelListener(new View.OnClickListener() { // from class: com.ido.life.module.user.set.privacyandsecurity.-$$Lambda$PrivacyAndSecurityActivity$-2jC4qJH97UUdpbGYAKhyM0SALs
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$showSportDataDialog$7$PrivacyAndSecurityActivity(commBottomConfirmDialogNewInstance, z, view);
            }
        });
        commBottomConfirmDialogNewInstance.setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.user.set.privacyandsecurity.-$$Lambda$PrivacyAndSecurityActivity$C6f_feHHoSvWpDQ6aqzkR1vMlOE
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                commBottomConfirmDialogNewInstance.dismissAllowingStateLoss();
            }
        });
        commBottomConfirmDialogNewInstance.show(getSupportFragmentManager());
    }

    public /* synthetic */ void lambda$showSportDataDialog$7$PrivacyAndSecurityActivity(CommBottomConfirmDialog commBottomConfirmDialog, boolean z, View view) {
        commBottomConfirmDialog.dismissAllowingStateLoss();
        this.mItemSport.setSwitchStatus(!z);
    }

    private void showHealthDataDialog(String str, final boolean z) {
        final CommBottomConfirmDialog commBottomConfirmDialogNewInstance = CommBottomConfirmDialog.newInstance(getLanguageText(R.string.register_cloud_sync_title), str, getLanguageText(R.string.public_tip_confirm), getLanguageText(R.string.public_tip_cancel), true);
        commBottomConfirmDialogNewInstance.setCancelable(false);
        commBottomConfirmDialogNewInstance.setCancelListener(new View.OnClickListener() { // from class: com.ido.life.module.user.set.privacyandsecurity.-$$Lambda$PrivacyAndSecurityActivity$L1pdVj7PWuD6Zv6lkZ2eJJ0e8TU
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$showHealthDataDialog$9$PrivacyAndSecurityActivity(commBottomConfirmDialogNewInstance, z, view);
            }
        });
        commBottomConfirmDialogNewInstance.setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.user.set.privacyandsecurity.-$$Lambda$PrivacyAndSecurityActivity$wTDLcI7Rz0NJfwNabRyWYPXkRjo
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                commBottomConfirmDialogNewInstance.dismissAllowingStateLoss();
            }
        });
        commBottomConfirmDialogNewInstance.show(getSupportFragmentManager());
    }

    public /* synthetic */ void lambda$showHealthDataDialog$9$PrivacyAndSecurityActivity(CommBottomConfirmDialog commBottomConfirmDialog, boolean z, View view) {
        commBottomConfirmDialog.dismissAllowingStateLoss();
        this.mItemHealth.setSwitchStatus(!z);
    }
}