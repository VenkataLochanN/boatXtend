package com.ido.life.dialog;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import androidx.appcompat.widget.AppCompatImageView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.common.widget.textview.MediumTextView;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.customview.DotView;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceUpgradeDialogFragment_ViewBinding implements Unbinder {
    private DeviceUpgradeDialogFragment target;
    private View view7f0a0806;
    private View view7f0a0919;

    public DeviceUpgradeDialogFragment_ViewBinding(final DeviceUpgradeDialogFragment deviceUpgradeDialogFragment, View view) {
        this.target = deviceUpgradeDialogFragment;
        deviceUpgradeDialogFragment.mTvUpgradeName = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.rtv_upgrade_type_name, "field 'mTvUpgradeName'", RegularTextView.class);
        deviceUpgradeDialogFragment.mTvUpgradeIng = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.rtv_upgrade_ing, "field 'mTvUpgradeIng'", RegularTextView.class);
        deviceUpgradeDialogFragment.mIvFirewareUpgradeSuccessWaiting = (AppCompatImageView) Utils.findRequiredViewAsType(view, R.id.iv_fireware_upgrade_result, "field 'mIvFirewareUpgradeSuccessWaiting'", AppCompatImageView.class);
        deviceUpgradeDialogFragment.mDotViewRight = (DotView) Utils.findRequiredViewAsType(view, R.id.dot_view_right, "field 'mDotViewRight'", DotView.class);
        deviceUpgradeDialogFragment.mIvStatusRight = (AppCompatImageView) Utils.findRequiredViewAsType(view, R.id.iv_status_right, "field 'mIvStatusRight'", AppCompatImageView.class);
        deviceUpgradeDialogFragment.mProgressBar = (ProgressBar) Utils.findRequiredViewAsType(view, R.id.progress_bar, "field 'mProgressBar'", ProgressBar.class);
        deviceUpgradeDialogFragment.mTvProgressValue = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.tv_progress_value, "field 'mTvProgressValue'", RegularTextView.class);
        deviceUpgradeDialogFragment.mLayoutUpgradeProgress = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_upgrade_progress, "field 'mLayoutUpgradeProgress'", LinearLayout.class);
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.tv_retry, "field 'mTvRetry' and method 'onViewClicked'");
        deviceUpgradeDialogFragment.mTvRetry = (MediumTextView) Utils.castView(viewFindRequiredView, R.id.tv_retry, "field 'mTvRetry'", MediumTextView.class);
        this.view7f0a0919 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.dialog.DeviceUpgradeDialogFragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                deviceUpgradeDialogFragment.onViewClicked(view2);
            }
        });
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.tv_close, "field 'mTvClose' and method 'onViewClicked'");
        deviceUpgradeDialogFragment.mTvClose = (MediumTextView) Utils.castView(viewFindRequiredView2, R.id.tv_close, "field 'mTvClose'", MediumTextView.class);
        this.view7f0a0806 = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.dialog.DeviceUpgradeDialogFragment_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                deviceUpgradeDialogFragment.onViewClicked(view2);
            }
        });
        deviceUpgradeDialogFragment.mLayoutUpgradeResult = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_upgrade_result, "field 'mLayoutUpgradeResult'", LinearLayout.class);
        deviceUpgradeDialogFragment.mLayoutUpgradeStatus = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_upgrade_status, "field 'mLayoutUpgradeStatus'", LinearLayout.class);
        deviceUpgradeDialogFragment.mTvFirewareSuccessWaitingTimeTip = (MediumTextView) Utils.findRequiredViewAsType(view, R.id.tv_fireware_success_waiting_time_tip, "field 'mTvFirewareSuccessWaitingTimeTip'", MediumTextView.class);
        deviceUpgradeDialogFragment.mTvFirewareSuccessWaitingTip = (MediumTextView) Utils.findRequiredViewAsType(view, R.id.tv_fireware_success_waiting_tip, "field 'mTvFirewareSuccessWaitingTip'", MediumTextView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        DeviceUpgradeDialogFragment deviceUpgradeDialogFragment = this.target;
        if (deviceUpgradeDialogFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        deviceUpgradeDialogFragment.mTvUpgradeName = null;
        deviceUpgradeDialogFragment.mTvUpgradeIng = null;
        deviceUpgradeDialogFragment.mIvFirewareUpgradeSuccessWaiting = null;
        deviceUpgradeDialogFragment.mDotViewRight = null;
        deviceUpgradeDialogFragment.mIvStatusRight = null;
        deviceUpgradeDialogFragment.mProgressBar = null;
        deviceUpgradeDialogFragment.mTvProgressValue = null;
        deviceUpgradeDialogFragment.mLayoutUpgradeProgress = null;
        deviceUpgradeDialogFragment.mTvRetry = null;
        deviceUpgradeDialogFragment.mTvClose = null;
        deviceUpgradeDialogFragment.mLayoutUpgradeResult = null;
        deviceUpgradeDialogFragment.mLayoutUpgradeStatus = null;
        deviceUpgradeDialogFragment.mTvFirewareSuccessWaitingTimeTip = null;
        deviceUpgradeDialogFragment.mTvFirewareSuccessWaitingTip = null;
        this.view7f0a0919.setOnClickListener(null);
        this.view7f0a0919 = null;
        this.view7f0a0806.setOnClickListener(null);
        this.view7f0a0806 = null;
    }
}