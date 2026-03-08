package com.ido.life.module.device.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.smartrefresh.layout.SmartRefreshLayout;

/* JADX INFO: loaded from: classes2.dex */
public class DeviceFragment_ViewBinding implements Unbinder {
    private DeviceFragment target;
    private View view7f0a032f;
    private View view7f0a0416;

    public DeviceFragment_ViewBinding(final DeviceFragment deviceFragment, View view) {
        this.target = deviceFragment;
        deviceFragment.rlFgDevice = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.rl_fg_device, "field 'rlFgDevice'", RelativeLayout.class);
        deviceFragment.mRtvTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.rtv_device_title, "field 'mRtvTitle'", TextView.class);
        deviceFragment.mRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.recycler_view_device, "field 'mRecyclerView'", RecyclerView.class);
        deviceFragment.mLayoutDeviceList = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_device_list, "field 'mLayoutDeviceList'", LinearLayout.class);
        deviceFragment.mRefreshLayout = (SmartRefreshLayout) Utils.findRequiredViewAsType(view, R.id.refreshLayout, "field 'mRefreshLayout'", SmartRefreshLayout.class);
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.layout_header_add_device, "field 'mLayoutHeaderAddDevice' and method 'onViewClicked'");
        deviceFragment.mLayoutHeaderAddDevice = (RelativeLayout) Utils.castView(viewFindRequiredView, R.id.layout_header_add_device, "field 'mLayoutHeaderAddDevice'", RelativeLayout.class);
        this.view7f0a0416 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.fragment.DeviceFragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                deviceFragment.onViewClicked(view2);
            }
        });
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.iv_scan, "field 'mIvScan' and method 'onViewClicked'");
        deviceFragment.mIvScan = (ImageView) Utils.castView(viewFindRequiredView2, R.id.iv_scan, "field 'mIvScan'", ImageView.class);
        this.view7f0a032f = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.fragment.DeviceFragment_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                deviceFragment.onViewClicked(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        DeviceFragment deviceFragment = this.target;
        if (deviceFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        deviceFragment.rlFgDevice = null;
        deviceFragment.mRtvTitle = null;
        deviceFragment.mRecyclerView = null;
        deviceFragment.mLayoutDeviceList = null;
        deviceFragment.mRefreshLayout = null;
        deviceFragment.mLayoutHeaderAddDevice = null;
        deviceFragment.mIvScan = null;
        this.view7f0a0416.setOnClickListener(null);
        this.view7f0a0416 = null;
        this.view7f0a032f.setOnClickListener(null);
        this.view7f0a032f = null;
    }
}