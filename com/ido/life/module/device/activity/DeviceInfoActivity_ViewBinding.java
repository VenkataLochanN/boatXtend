package com.ido.life.module.device.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.common.widget.textview.MediumTextView;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.customview.viewgroup.CustomItemLabelView;
import com.ido.life.customview.viewgroup.CustomValueUnitView;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceInfoActivity_ViewBinding implements Unbinder {
    private DeviceInfoActivity target;
    private View view7f0a0247;
    private View view7f0a024e;
    private View view7f0a0253;
    private View view7f0a025b;
    private View view7f0a025c;
    private View view7f0a025d;
    private View view7f0a0277;
    private View view7f0a0286;
    private View view7f0a029e;
    private View view7f0a02a1;
    private View view7f0a02fd;
    private View view7f0a02fe;
    private View view7f0a02ff;
    private View view7f0a0300;
    private View view7f0a030c;
    private View view7f0a064a;
    private View view7f0a068b;

    public DeviceInfoActivity_ViewBinding(DeviceInfoActivity deviceInfoActivity) {
        this(deviceInfoActivity, deviceInfoActivity.getWindow().getDecorView());
    }

    public DeviceInfoActivity_ViewBinding(final DeviceInfoActivity deviceInfoActivity, View view) {
        this.target = deviceInfoActivity;
        deviceInfoActivity.mLayoutRoot = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_root, "field 'mLayoutRoot'", LinearLayout.class);
        deviceInfoActivity.mScrollView = (NestedScrollView) Utils.findRequiredViewAsType(view, R.id.scroll_view, "field 'mScrollView'", NestedScrollView.class);
        deviceInfoActivity.mLayoutDisconnect = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.layout_disconnect, "field 'mLayoutDisconnect'", RelativeLayout.class);
        deviceInfoActivity.mLayoutConnect = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_connect, "field 'mLayoutConnect'", LinearLayout.class);
        deviceInfoActivity.mLayoutEdit = (TableLayout) Utils.findRequiredViewAsType(view, R.id.layout_edit, "field 'mLayoutEdit'", TableLayout.class);
        deviceInfoActivity.mMtvDeviceCustomName = (MediumTextView) Utils.findRequiredViewAsType(view, R.id.mtv_device_custom_name, "field 'mMtvDeviceCustomName'", MediumTextView.class);
        deviceInfoActivity.mEtDeviceCustomName = (EditText) Utils.findRequiredViewAsType(view, R.id.et_device_custom_name, "field 'mEtDeviceCustomName'", EditText.class);
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.iv_editor, "field 'mIvEditor' and method 'onViewClicked'");
        deviceInfoActivity.mIvEditor = (ImageView) Utils.castView(viewFindRequiredView, R.id.iv_editor, "field 'mIvEditor'", ImageView.class);
        this.view7f0a030c = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DeviceInfoActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                deviceInfoActivity.onViewClicked(view2);
            }
        });
        deviceInfoActivity.mMtvDeviceName = (MediumTextView) Utils.findRequiredViewAsType(view, R.id.mtv_device_name, "field 'mMtvDeviceName'", MediumTextView.class);
        deviceInfoActivity.mRtvBattery = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.rtv_battery, "field 'mRtvBattery'", RegularTextView.class);
        deviceInfoActivity.mMtvStatus = (MediumTextView) Utils.findRequiredViewAsType(view, R.id.mtv_status, "field 'mMtvStatus'", MediumTextView.class);
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.item_remind, "field 'mItemRemind' and method 'onViewClicked'");
        deviceInfoActivity.mItemRemind = (CustomItemLabelView) Utils.castView(viewFindRequiredView2, R.id.item_remind, "field 'mItemRemind'", CustomItemLabelView.class);
        this.view7f0a02a1 = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DeviceInfoActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                deviceInfoActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView3 = Utils.findRequiredView(view, R.id.item_call_reminder, "field 'mCallReminder' and method 'onViewClicked'");
        deviceInfoActivity.mCallReminder = (CustomItemLabelView) Utils.castView(viewFindRequiredView3, R.id.item_call_reminder, "field 'mCallReminder'", CustomItemLabelView.class);
        this.view7f0a0253 = viewFindRequiredView3;
        viewFindRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DeviceInfoActivity_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                deviceInfoActivity.onViewClicked(view2);
            }
        });
        deviceInfoActivity.mItemStep = (CustomValueUnitView) Utils.findRequiredViewAsType(view, R.id.item_step, "field 'mItemStep'", CustomValueUnitView.class);
        deviceInfoActivity.mItemCalorie = (CustomValueUnitView) Utils.findRequiredViewAsType(view, R.id.item_calorie, "field 'mItemCalorie'", CustomValueUnitView.class);
        deviceInfoActivity.mItemHeartRate = (CustomValueUnitView) Utils.findRequiredViewAsType(view, R.id.item_heart_rate, "field 'mItemHeartRate'", CustomValueUnitView.class);
        deviceInfoActivity.mLayoutDialMarket = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_dial_market, "field 'mLayoutDialMarket'", LinearLayout.class);
        deviceInfoActivity.mLayoutDial = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_dial, "field 'mLayoutDial'", LinearLayout.class);
        View viewFindRequiredView4 = Utils.findRequiredView(view, R.id.iv_dial_1, "field 'mIvDial1' and method 'onViewClicked'");
        deviceInfoActivity.mIvDial1 = (AppCompatImageView) Utils.castView(viewFindRequiredView4, R.id.iv_dial_1, "field 'mIvDial1'", AppCompatImageView.class);
        this.view7f0a02fd = viewFindRequiredView4;
        viewFindRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DeviceInfoActivity_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                deviceInfoActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView5 = Utils.findRequiredView(view, R.id.iv_dial_2, "field 'mIvDial2' and method 'onViewClicked'");
        deviceInfoActivity.mIvDial2 = (AppCompatImageView) Utils.castView(viewFindRequiredView5, R.id.iv_dial_2, "field 'mIvDial2'", AppCompatImageView.class);
        this.view7f0a02fe = viewFindRequiredView5;
        viewFindRequiredView5.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DeviceInfoActivity_ViewBinding.5
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                deviceInfoActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView6 = Utils.findRequiredView(view, R.id.iv_dial_3, "field 'mIvDial3' and method 'onViewClicked'");
        deviceInfoActivity.mIvDial3 = (AppCompatImageView) Utils.castView(viewFindRequiredView6, R.id.iv_dial_3, "field 'mIvDial3'", AppCompatImageView.class);
        this.view7f0a02ff = viewFindRequiredView6;
        viewFindRequiredView6.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DeviceInfoActivity_ViewBinding.6
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                deviceInfoActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView7 = Utils.findRequiredView(view, R.id.iv_dial_4, "field 'mIvDial4' and method 'onViewClicked'");
        deviceInfoActivity.mIvDial4 = (AppCompatImageView) Utils.castView(viewFindRequiredView7, R.id.iv_dial_4, "field 'mIvDial4'", AppCompatImageView.class);
        this.view7f0a0300 = viewFindRequiredView7;
        viewFindRequiredView7.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DeviceInfoActivity_ViewBinding.7
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                deviceInfoActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView8 = Utils.findRequiredView(view, R.id.item_amazon_alexa, "field 'mItemAlexa' and method 'onViewClicked'");
        deviceInfoActivity.mItemAlexa = (CustomItemLabelView) Utils.castView(viewFindRequiredView8, R.id.item_amazon_alexa, "field 'mItemAlexa'", CustomItemLabelView.class);
        this.view7f0a024e = viewFindRequiredView8;
        viewFindRequiredView8.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DeviceInfoActivity_ViewBinding.8
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                deviceInfoActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView9 = Utils.findRequiredView(view, R.id.item_device_upgrade, "field 'mItemDeviceUpgrade' and method 'onViewClicked'");
        deviceInfoActivity.mItemDeviceUpgrade = (CustomItemLabelView) Utils.castView(viewFindRequiredView9, R.id.item_device_upgrade, "field 'mItemDeviceUpgrade'", CustomItemLabelView.class);
        this.view7f0a025c = viewFindRequiredView9;
        viewFindRequiredView9.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DeviceInfoActivity_ViewBinding.9
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                deviceInfoActivity.onViewClicked(view2);
            }
        });
        deviceInfoActivity.mRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.recycler_view, "field 'mRecyclerView'", RecyclerView.class);
        View viewFindRequiredView10 = Utils.findRequiredView(view, R.id.item_intelligent_motion, "field 'mItemIntelligentMotion' and method 'onViewClicked'");
        deviceInfoActivity.mItemIntelligentMotion = (CustomItemLabelView) Utils.castView(viewFindRequiredView10, R.id.item_intelligent_motion, "field 'mItemIntelligentMotion'", CustomItemLabelView.class);
        this.view7f0a0277 = viewFindRequiredView10;
        viewFindRequiredView10.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DeviceInfoActivity_ViewBinding.10
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                deviceInfoActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView11 = Utils.findRequiredView(view, R.id.item_problems_and_suggestions, "field 'mItemProblemsAndSuggestions' and method 'onViewClicked'");
        deviceInfoActivity.mItemProblemsAndSuggestions = (CustomItemLabelView) Utils.castView(viewFindRequiredView11, R.id.item_problems_and_suggestions, "field 'mItemProblemsAndSuggestions'", CustomItemLabelView.class);
        this.view7f0a029e = viewFindRequiredView11;
        viewFindRequiredView11.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DeviceInfoActivity_ViewBinding.11
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                deviceInfoActivity.onViewClicked(view2);
            }
        });
        deviceInfoActivity.mIvDeviceImg = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_device_img, "field 'mIvDeviceImg'", ImageView.class);
        View viewFindRequiredView12 = Utils.findRequiredView(view, R.id.rtv_delete_device, "method 'onViewClick'");
        this.view7f0a064a = viewFindRequiredView12;
        viewFindRequiredView12.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DeviceInfoActivity_ViewBinding.12
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                deviceInfoActivity.onViewClick(view2);
            }
        });
        View viewFindRequiredView13 = Utils.findRequiredView(view, R.id.rtv_relink, "method 'onViewClick'");
        this.view7f0a068b = viewFindRequiredView13;
        viewFindRequiredView13.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DeviceInfoActivity_ViewBinding.13
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                deviceInfoActivity.onViewClick(view2);
            }
        });
        View viewFindRequiredView14 = Utils.findRequiredView(view, R.id.item_dial_market, "method 'onViewClicked'");
        this.view7f0a025d = viewFindRequiredView14;
        viewFindRequiredView14.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DeviceInfoActivity_ViewBinding.14
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                deviceInfoActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView15 = Utils.findRequiredView(view, R.id.item_more, "method 'onViewClicked'");
        this.view7f0a0286 = viewFindRequiredView15;
        viewFindRequiredView15.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DeviceInfoActivity_ViewBinding.15
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                deviceInfoActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView16 = Utils.findRequiredView(view, R.id.item_device_setting, "method 'onViewClicked'");
        this.view7f0a025b = viewFindRequiredView16;
        viewFindRequiredView16.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DeviceInfoActivity_ViewBinding.16
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                deviceInfoActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView17 = Utils.findRequiredView(view, R.id.item_about, "method 'onViewClicked'");
        this.view7f0a0247 = viewFindRequiredView17;
        viewFindRequiredView17.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DeviceInfoActivity_ViewBinding.17
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                deviceInfoActivity.onViewClicked(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        DeviceInfoActivity deviceInfoActivity = this.target;
        if (deviceInfoActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        deviceInfoActivity.mLayoutRoot = null;
        deviceInfoActivity.mScrollView = null;
        deviceInfoActivity.mLayoutDisconnect = null;
        deviceInfoActivity.mLayoutConnect = null;
        deviceInfoActivity.mLayoutEdit = null;
        deviceInfoActivity.mMtvDeviceCustomName = null;
        deviceInfoActivity.mEtDeviceCustomName = null;
        deviceInfoActivity.mIvEditor = null;
        deviceInfoActivity.mMtvDeviceName = null;
        deviceInfoActivity.mRtvBattery = null;
        deviceInfoActivity.mMtvStatus = null;
        deviceInfoActivity.mItemRemind = null;
        deviceInfoActivity.mCallReminder = null;
        deviceInfoActivity.mItemStep = null;
        deviceInfoActivity.mItemCalorie = null;
        deviceInfoActivity.mItemHeartRate = null;
        deviceInfoActivity.mLayoutDialMarket = null;
        deviceInfoActivity.mLayoutDial = null;
        deviceInfoActivity.mIvDial1 = null;
        deviceInfoActivity.mIvDial2 = null;
        deviceInfoActivity.mIvDial3 = null;
        deviceInfoActivity.mIvDial4 = null;
        deviceInfoActivity.mItemAlexa = null;
        deviceInfoActivity.mItemDeviceUpgrade = null;
        deviceInfoActivity.mRecyclerView = null;
        deviceInfoActivity.mItemIntelligentMotion = null;
        deviceInfoActivity.mItemProblemsAndSuggestions = null;
        deviceInfoActivity.mIvDeviceImg = null;
        this.view7f0a030c.setOnClickListener(null);
        this.view7f0a030c = null;
        this.view7f0a02a1.setOnClickListener(null);
        this.view7f0a02a1 = null;
        this.view7f0a0253.setOnClickListener(null);
        this.view7f0a0253 = null;
        this.view7f0a02fd.setOnClickListener(null);
        this.view7f0a02fd = null;
        this.view7f0a02fe.setOnClickListener(null);
        this.view7f0a02fe = null;
        this.view7f0a02ff.setOnClickListener(null);
        this.view7f0a02ff = null;
        this.view7f0a0300.setOnClickListener(null);
        this.view7f0a0300 = null;
        this.view7f0a024e.setOnClickListener(null);
        this.view7f0a024e = null;
        this.view7f0a025c.setOnClickListener(null);
        this.view7f0a025c = null;
        this.view7f0a0277.setOnClickListener(null);
        this.view7f0a0277 = null;
        this.view7f0a029e.setOnClickListener(null);
        this.view7f0a029e = null;
        this.view7f0a064a.setOnClickListener(null);
        this.view7f0a064a = null;
        this.view7f0a068b.setOnClickListener(null);
        this.view7f0a068b = null;
        this.view7f0a025d.setOnClickListener(null);
        this.view7f0a025d = null;
        this.view7f0a0286.setOnClickListener(null);
        this.view7f0a0286 = null;
        this.view7f0a025b.setOnClickListener(null);
        this.view7f0a025b = null;
        this.view7f0a0247.setOnClickListener(null);
        this.view7f0a0247 = null;
    }
}