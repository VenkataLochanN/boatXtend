package com.ido.life.module.device.activity;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import androidx.appcompat.widget.AppCompatImageView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.common.widget.textview.MediumTextView;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.customview.viewgroup.OptionView;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceUpgradeNewActivity_ViewBinding implements Unbinder {
    private DeviceUpgradeNewActivity target;
    private View view7f0a0919;
    private View view7f0a09e0;

    public DeviceUpgradeNewActivity_ViewBinding(DeviceUpgradeNewActivity deviceUpgradeNewActivity) {
        this(deviceUpgradeNewActivity, deviceUpgradeNewActivity.getWindow().getDecorView());
    }

    public DeviceUpgradeNewActivity_ViewBinding(final DeviceUpgradeNewActivity deviceUpgradeNewActivity, View view) {
        this.target = deviceUpgradeNewActivity;
        deviceUpgradeNewActivity.mIvTop = (AppCompatImageView) Utils.findRequiredViewAsType(view, R.id.iv_top, "field 'mIvTop'", AppCompatImageView.class);
        deviceUpgradeNewActivity.mProgressBar = (ProgressBar) Utils.findRequiredViewAsType(view, R.id.progress_bar, "field 'mProgressBar'", ProgressBar.class);
        deviceUpgradeNewActivity.mIvStatus = (AppCompatImageView) Utils.findRequiredViewAsType(view, R.id.iv_status_right, "field 'mIvStatus'", AppCompatImageView.class);
        deviceUpgradeNewActivity.mTvTipTop = (MediumTextView) Utils.findRequiredViewAsType(view, R.id.tv_tip_top, "field 'mTvTipTop'", MediumTextView.class);
        deviceUpgradeNewActivity.mTvCurrentVersion = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.tv_current_version, "field 'mTvCurrentVersion'", RegularTextView.class);
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.tv_retry, "field 'mTvRetry' and method 'onViewClicked'");
        deviceUpgradeNewActivity.mTvRetry = (MediumTextView) Utils.castView(viewFindRequiredView, R.id.tv_retry, "field 'mTvRetry'", MediumTextView.class);
        this.view7f0a0919 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DeviceUpgradeNewActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                deviceUpgradeNewActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.tv_upgrade, "field 'mTvUpgrade' and method 'onViewClicked'");
        deviceUpgradeNewActivity.mTvUpgrade = (MediumTextView) Utils.castView(viewFindRequiredView2, R.id.tv_upgrade, "field 'mTvUpgrade'", MediumTextView.class);
        this.view7f0a09e0 = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DeviceUpgradeNewActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                deviceUpgradeNewActivity.onViewClicked(view2);
            }
        });
        deviceUpgradeNewActivity.layFireware = (OptionView) Utils.findRequiredViewAsType(view, R.id.lay_fireware, "field 'layFireware'", OptionView.class);
        deviceUpgradeNewActivity.layFone = (OptionView) Utils.findRequiredViewAsType(view, R.id.lay_font, "field 'layFone'", OptionView.class);
        deviceUpgradeNewActivity.laySystemCom = (OptionView) Utils.findRequiredViewAsType(view, R.id.lay_system, "field 'laySystemCom'", OptionView.class);
        deviceUpgradeNewActivity.upgradeStatusll = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.upgrade_stauts_ll, "field 'upgradeStatusll'", LinearLayout.class);
        deviceUpgradeNewActivity.upgradeResultll = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.upgrade_result_ll, "field 'upgradeResultll'", LinearLayout.class);
        deviceUpgradeNewActivity.lineView = Utils.findRequiredView(view, R.id.upgrade_line, "field 'lineView'");
        deviceUpgradeNewActivity.lineViewSystem = Utils.findRequiredView(view, R.id.upgrade_system_line, "field 'lineViewSystem'");
        deviceUpgradeNewActivity.content_rl = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.upgrade_content_rl, "field 'content_rl'", RelativeLayout.class);
        deviceUpgradeNewActivity.tip_ll = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.tip_ll, "field 'tip_ll'", LinearLayout.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        DeviceUpgradeNewActivity deviceUpgradeNewActivity = this.target;
        if (deviceUpgradeNewActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        deviceUpgradeNewActivity.mIvTop = null;
        deviceUpgradeNewActivity.mProgressBar = null;
        deviceUpgradeNewActivity.mIvStatus = null;
        deviceUpgradeNewActivity.mTvTipTop = null;
        deviceUpgradeNewActivity.mTvCurrentVersion = null;
        deviceUpgradeNewActivity.mTvRetry = null;
        deviceUpgradeNewActivity.mTvUpgrade = null;
        deviceUpgradeNewActivity.layFireware = null;
        deviceUpgradeNewActivity.layFone = null;
        deviceUpgradeNewActivity.laySystemCom = null;
        deviceUpgradeNewActivity.upgradeStatusll = null;
        deviceUpgradeNewActivity.upgradeResultll = null;
        deviceUpgradeNewActivity.lineView = null;
        deviceUpgradeNewActivity.lineViewSystem = null;
        deviceUpgradeNewActivity.content_rl = null;
        deviceUpgradeNewActivity.tip_ll = null;
        this.view7f0a0919.setOnClickListener(null);
        this.view7f0a0919 = null;
        this.view7f0a09e0.setOnClickListener(null);
        this.view7f0a09e0 = null;
    }
}