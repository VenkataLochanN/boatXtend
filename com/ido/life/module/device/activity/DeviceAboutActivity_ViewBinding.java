package com.ido.life.module.device.activity;

import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.common.widget.textview.MediumTextView;
import com.ido.common.widget.textview.RegularTextView;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceAboutActivity_ViewBinding implements Unbinder {
    private DeviceAboutActivity target;

    public DeviceAboutActivity_ViewBinding(DeviceAboutActivity deviceAboutActivity) {
        this(deviceAboutActivity, deviceAboutActivity.getWindow().getDecorView());
    }

    public DeviceAboutActivity_ViewBinding(DeviceAboutActivity deviceAboutActivity, View view) {
        this.target = deviceAboutActivity;
        deviceAboutActivity.mMtvTagLine = (MediumTextView) Utils.findRequiredViewAsType(view, R.id.mtv_tag_line, "field 'mMtvTagLine'", MediumTextView.class);
        deviceAboutActivity.mMtvModelLabel = (MediumTextView) Utils.findRequiredViewAsType(view, R.id.mtv_model_label, "field 'mMtvModelLabel'", MediumTextView.class);
        deviceAboutActivity.mRtvModel = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.rtv_model, "field 'mRtvModel'", RegularTextView.class);
        deviceAboutActivity.mMtvVersionLabel = (MediumTextView) Utils.findRequiredViewAsType(view, R.id.mtv_version_label, "field 'mMtvVersionLabel'", MediumTextView.class);
        deviceAboutActivity.mRtvVersion = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.rtv_version, "field 'mRtvVersion'", RegularTextView.class);
        deviceAboutActivity.mMtvBleNameLabel = (MediumTextView) Utils.findRequiredViewAsType(view, R.id.mtv_ble_name_label, "field 'mMtvBleNameLabel'", MediumTextView.class);
        deviceAboutActivity.mRtvBleName = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.rtv_ble_name, "field 'mRtvBleName'", RegularTextView.class);
        deviceAboutActivity.mMtvMacLabel = (MediumTextView) Utils.findRequiredViewAsType(view, R.id.mtv_mac_label, "field 'mMtvMacLabel'", MediumTextView.class);
        deviceAboutActivity.mRtvMac = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.rtv_mac, "field 'mRtvMac'", RegularTextView.class);
        deviceAboutActivity.mMtvDataSyncTimeLabel = (MediumTextView) Utils.findRequiredViewAsType(view, R.id.mtv_data_sync_time_label, "field 'mMtvDataSyncTimeLabel'", MediumTextView.class);
        deviceAboutActivity.mRtvDataSyncTime = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.rtv_data_sync_time, "field 'mRtvDataSyncTime'", RegularTextView.class);
        deviceAboutActivity.mIvDeviceImg = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_device_img, "field 'mIvDeviceImg'", ImageView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        DeviceAboutActivity deviceAboutActivity = this.target;
        if (deviceAboutActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        deviceAboutActivity.mMtvTagLine = null;
        deviceAboutActivity.mMtvModelLabel = null;
        deviceAboutActivity.mRtvModel = null;
        deviceAboutActivity.mMtvVersionLabel = null;
        deviceAboutActivity.mRtvVersion = null;
        deviceAboutActivity.mMtvBleNameLabel = null;
        deviceAboutActivity.mRtvBleName = null;
        deviceAboutActivity.mMtvMacLabel = null;
        deviceAboutActivity.mRtvMac = null;
        deviceAboutActivity.mMtvDataSyncTimeLabel = null;
        deviceAboutActivity.mRtvDataSyncTime = null;
        deviceAboutActivity.mIvDeviceImg = null;
    }
}