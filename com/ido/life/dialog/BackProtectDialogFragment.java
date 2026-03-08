package com.ido.life.dialog;

import android.content.ComponentName;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.ble.event.stat.one.d;
import com.ido.common.base.BaseDialogFragment;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.AppInfoUtil;
import com.ido.common.utils.DipPixelUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.RomUtils;

/* JADX INFO: loaded from: classes2.dex */
public class BackProtectDialogFragment extends BaseDialogFragment {
    private static final String TAG = "BackProtectDialogFragme";

    @BindView(R.id.ll_associated)
    LinearLayout mLlAssociated;

    @BindView(R.id.ll_back_layout)
    LinearLayout mLlBackLayout;

    @BindView(R.id.ll_launch)
    LinearLayout mLlLaunch;

    @BindView(R.id.ll_power)
    LinearLayout mLlPower;
    private OnShareChooseListener mOnShareChooseListener;

    @BindView(R.id.tv_associated_title)
    TextView mTvAssociatedTitle;

    @BindView(R.id.tv_content)
    TextView mTvContent;

    @BindView(R.id.tv_launch_title)
    TextView mTvLaunchTitle;

    @BindView(R.id.tv_title)
    TextView mTvTitle;

    @BindView(R.id.tv_title_power)
    TextView mTvTitlePower;

    public interface OnShareChooseListener {
        void onSharePlatChoose(int i);
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected int getLayoutResId() {
        return R.layout.dialog_back_protect;
    }

    public static BackProtectDialogFragment newInstance() {
        Bundle bundle = new Bundle();
        BackProtectDialogFragment backProtectDialogFragment = new BackProtectDialogFragment();
        backProtectDialogFragment.setStyle(1, 2131886083);
        backProtectDialogFragment.setArguments(bundle);
        return backProtectDialogFragment;
    }

    public void setOnShareResultListener(OnShareChooseListener onShareChooseListener) {
        this.mOnShareChooseListener = onShareChooseListener;
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected void initUI(View view) {
        super.initUI(view);
        if (getDialog() != null && getDialog().getWindow() != null) {
            Window window = getDialog().getWindow();
            window.getDecorView().setPadding(0, 0, 0, 0);
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.gravity = 80;
            attributes.width = -1;
            attributes.height = 300;
            window.setAttributes(attributes);
        }
        showTypeByMobile();
    }

    private void showTypeByMobile() {
        if (RomUtils.isHuawei()) {
            this.mLlLaunch.setVisibility(8);
            this.mLlPower.setVisibility(8);
            this.mTvAssociatedTitle.setText(LanguageUtil.getLanguageText(R.string.sport_setting_powers_set_remind));
        } else if (RomUtils.isXiaomi()) {
            this.mTvAssociatedTitle.setText(LanguageUtil.getLanguageText(R.string.sport_setting_powers_set_permissions_title));
        } else if (RomUtils.isVivo()) {
            this.mTvAssociatedTitle.setText(LanguageUtil.getLanguageText(R.string.sport_setting_powers_set_permissions_title));
        } else if (RomUtils.isOppo()) {
            this.mLlPower.setVisibility(8);
        }
    }

    @Override // com.ido.common.base.BaseDialogFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.mLlBackLayout.measure(0, 0);
        int measuredHeight = this.mLlBackLayout.getMeasuredHeight() + DipPixelUtil.dip2px(15.0f);
        if (getDialog().getWindow() != null) {
            getDialog().getWindow().setLayout(-1, measuredHeight);
        }
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (configuration.orientation == 2) {
            dismissAllowingStateLoss();
        }
    }

    @OnClick({R.id.tv_cancel})
    public void toCancel(View view) {
        dismissAllowingStateLoss();
    }

    @OnClick({R.id.tv_set_power})
    public void toPower(View view) {
        setPower();
        dismissAllowingStateLoss();
    }

    @OnClick({R.id.tv_set_associated})
    public void toAssociate(View view) {
        settingSportAssociate();
        dismissAllowingStateLoss();
    }

    @OnClick({R.id.tv_set_launch})
    public void toSetStart(View view) {
        settingSportAssociate();
        dismissAllowingStateLoss();
    }

    private void settingSportAssociate() {
        ComponentName componentNameUnflattenFromString = null;
        try {
            if (RomUtils.isHuawei()) {
                int i = Build.VERSION.SDK_INT;
                Intent intent = new Intent();
                intent.addFlags(268435456);
                if (i >= 28) {
                    componentNameUnflattenFromString = ComponentName.unflattenFromString("com.huawei.systemmanager/.startupmgr.ui.StartupNormalAppListActivity");
                } else if (i >= 26) {
                    componentNameUnflattenFromString = ComponentName.unflattenFromString("com.huawei.systemmanager/.appcontrol.activity.StartupAppControlActivity");
                } else if (i >= 23) {
                    componentNameUnflattenFromString = ComponentName.unflattenFromString("com.huawei.systemmanager/.startupmgr.ui.StartupNormalAppListActivity");
                } else if (i >= 21) {
                    componentNameUnflattenFromString = ComponentName.unflattenFromString("com.huawei.systemmanager/com.huawei.permissionmanager.ui.MainActivity");
                }
                intent.setComponent(componentNameUnflattenFromString);
                startActivity(intent);
                return;
            }
            if (RomUtils.isXiaomi()) {
                Intent intent2 = new Intent();
                intent2.addFlags(268435456);
                intent2.setComponent(new ComponentName("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity"));
                startActivity(intent2);
                return;
            }
            if (RomUtils.isVivo()) {
                try {
                    if ((Build.MODEL.contains("Y85") && !Build.MODEL.contains("Y85A")) || Build.MODEL.contains("vivo Y53L")) {
                        CommonLogUtil.d(TAG, "settingSportRight: +++++++++");
                        Intent intent3 = new Intent();
                        intent3.setClassName("com.vivo.permissionmanager", "com.vivo.permissionmanager.activity.PurviewTabActivity");
                        intent3.putExtra("packagename", getActivity().getPackageName());
                        intent3.putExtra("tabId", "1");
                        startActivity(intent3);
                        return;
                    }
                    CommonLogUtil.d(TAG, "settingSportRight: ------------");
                    Intent intent4 = new Intent();
                    intent4.setClassName("com.vivo.permissionmanager", "com.vivo.permissionmanager.activity.SoftPermissionDetailActivity");
                    intent4.setAction("secure.intent.action.softPermissionDetail");
                    intent4.putExtra("packagename", getActivity().getPackageName());
                    startActivity(intent4);
                    return;
                } catch (Exception unused) {
                    Intent intent5 = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
                    intent5.setData(Uri.fromParts("package", getActivity().getPackageName(), null));
                    startActivity(intent5);
                    return;
                }
            }
            if (RomUtils.isOppo()) {
                Intent intent6 = new Intent();
                try {
                    try {
                        if (Build.VERSION.SDK_INT >= 23) {
                            CommonLogUtil.d(TAG, "settingSportRight: 1");
                            intent6.setFlags(268435456);
                            intent6.setComponent(new ComponentName("com.coloros.safecenter", "com.coloros.safecenter.startupapp.StartupAppListActivity"));
                            startActivity(intent6);
                        } else {
                            CommonLogUtil.d(TAG, "settingSportRight: 1");
                            intent6.setFlags(268435456);
                            intent6.setComponent(new ComponentName("com.color.safecenter", "com.color.safecenter.permission.startup.StartupAppListActivity"));
                            startActivity(intent6);
                        }
                    } catch (Exception unused2) {
                        intent6.setFlags(268435456);
                        intent6.putExtra("pkg_name", getActivity().getPackageName());
                        intent6.putExtra(d.j, getString(R.string.app_name));
                        intent6.putExtra("class_name", "com.welab.notificationdemo.MainActivity");
                        intent6.setComponent(new ComponentName("com.coloros.notificationmanager", "com.coloros.notificationmanager.AppDetailPreferenceActivity"));
                        startActivity(intent6);
                    }
                } catch (Exception unused3) {
                    CommonLogUtil.d(TAG, "settingSportRight: 3");
                    Intent intent7 = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
                    intent7.setData(Uri.fromParts("package", getActivity().getPackageName(), null));
                    startActivity(intent7);
                }
            }
        } catch (Exception unused4) {
        }
    }

    private void setPower() {
        if (RomUtils.isXiaomi()) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.miui.powerkeeper", "com.miui.powerkeeper.ui.HiddenAppsConfigActivity"));
            intent.putExtra("package_name", AppInfoUtil.getPackageName(getActivity()));
            intent.putExtra("package_label", AppInfoUtil.getAppName(getActivity()));
            startActivity(intent);
            return;
        }
        if (RomUtils.isVivo()) {
            CommonLogUtil.d(TAG, "setPower: ++");
            Intent intent2 = new Intent();
            intent2.setClassName("com.iqoo.powersaving", "com.iqoo.powersaving.PowerSavingManagerActivity");
            intent2.putExtra("package_name", AppInfoUtil.getPackageName(getActivity()));
            startActivity(intent2);
            return;
        }
        RomUtils.isOppo();
    }
}