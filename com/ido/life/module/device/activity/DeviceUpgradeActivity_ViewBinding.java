package com.ido.life.module.device.activity;

import android.view.View;
import androidx.core.widget.NestedScrollView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.customview.WaveLoadingView;
import com.ido.life.customview.viewgroup.CommLoadingView;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceUpgradeActivity_ViewBinding implements Unbinder {
    private DeviceUpgradeActivity target;
    private View view7f0a0692;

    public DeviceUpgradeActivity_ViewBinding(DeviceUpgradeActivity deviceUpgradeActivity) {
        this(deviceUpgradeActivity, deviceUpgradeActivity.getWindow().getDecorView());
    }

    public DeviceUpgradeActivity_ViewBinding(final DeviceUpgradeActivity deviceUpgradeActivity, View view) {
        this.target = deviceUpgradeActivity;
        deviceUpgradeActivity.mWaveView = (WaveLoadingView) Utils.findRequiredViewAsType(view, R.id.wave_view, "field 'mWaveView'", WaveLoadingView.class);
        deviceUpgradeActivity.mRtvOtaStatus = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.rtv_ota_status, "field 'mRtvOtaStatus'", RegularTextView.class);
        deviceUpgradeActivity.mRtvVersion = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.rtv_version, "field 'mRtvVersion'", RegularTextView.class);
        deviceUpgradeActivity.mRtvAttention = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.rtv_upgrade_attention, "field 'mRtvAttention'", RegularTextView.class);
        deviceUpgradeActivity.mRtvUpgradeInstructions = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.rtv_upgrade_instructions, "field 'mRtvUpgradeInstructions'", RegularTextView.class);
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.rtv_start_upgrade, "field 'mRtvStartUpgrade' and method 'onViewClicked'");
        deviceUpgradeActivity.mRtvStartUpgrade = (RegularTextView) Utils.castView(viewFindRequiredView, R.id.rtv_start_upgrade, "field 'mRtvStartUpgrade'", RegularTextView.class);
        this.view7f0a0692 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DeviceUpgradeActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                deviceUpgradeActivity.onViewClicked();
            }
        });
        deviceUpgradeActivity.mCommLoadingView = (CommLoadingView) Utils.findRequiredViewAsType(view, R.id.comm_loading_view, "field 'mCommLoadingView'", CommLoadingView.class);
        deviceUpgradeActivity.mScrollView = (NestedScrollView) Utils.findRequiredViewAsType(view, R.id.scroll_view, "field 'mScrollView'", NestedScrollView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        DeviceUpgradeActivity deviceUpgradeActivity = this.target;
        if (deviceUpgradeActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        deviceUpgradeActivity.mWaveView = null;
        deviceUpgradeActivity.mRtvOtaStatus = null;
        deviceUpgradeActivity.mRtvVersion = null;
        deviceUpgradeActivity.mRtvAttention = null;
        deviceUpgradeActivity.mRtvUpgradeInstructions = null;
        deviceUpgradeActivity.mRtvStartUpgrade = null;
        deviceUpgradeActivity.mCommLoadingView = null;
        deviceUpgradeActivity.mScrollView = null;
        this.view7f0a0692.setOnClickListener(null);
        this.view7f0a0692 = null;
    }
}