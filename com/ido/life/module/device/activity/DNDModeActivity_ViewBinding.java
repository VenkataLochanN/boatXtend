package com.ido.life.module.device.activity;

import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.customview.viewgroup.CommLoadingView;
import com.ido.life.customview.viewgroup.CustomItemLabelView;

/* JADX INFO: loaded from: classes2.dex */
public class DNDModeActivity_ViewBinding implements Unbinder {
    private DNDModeActivity target;
    private View view7f0a0260;
    private View view7f0a0263;
    private View view7f0a0291;
    private View view7f0a0292;

    public DNDModeActivity_ViewBinding(DNDModeActivity dNDModeActivity) {
        this(dNDModeActivity, dNDModeActivity.getWindow().getDecorView());
    }

    public DNDModeActivity_ViewBinding(final DNDModeActivity dNDModeActivity, View view) {
        this.target = dNDModeActivity;
        dNDModeActivity.mItemDndDaytimeSwitch = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.item_dnd_daytime, "field 'mItemDndDaytimeSwitch'", CustomItemLabelView.class);
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.item_dnd_start_time, "field 'mItemDayStartTime' and method 'onViewClicked'");
        dNDModeActivity.mItemDayStartTime = (CustomItemLabelView) Utils.castView(viewFindRequiredView, R.id.item_dnd_start_time, "field 'mItemDayStartTime'", CustomItemLabelView.class);
        this.view7f0a0263 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DNDModeActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                dNDModeActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.item_dnd_end_time, "field 'mItemDayEndTime' and method 'onViewClicked'");
        dNDModeActivity.mItemDayEndTime = (CustomItemLabelView) Utils.castView(viewFindRequiredView2, R.id.item_dnd_end_time, "field 'mItemDayEndTime'", CustomItemLabelView.class);
        this.view7f0a0260 = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DNDModeActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                dNDModeActivity.onViewClicked(view2);
            }
        });
        dNDModeActivity.mLayoutDndTimeDaytime = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_dnd_time_daytime, "field 'mLayoutDndTimeDaytime'", LinearLayout.class);
        dNDModeActivity.mItemDndNighttimeSwitch = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.item_dnd_nighttime, "field 'mItemDndNighttimeSwitch'", CustomItemLabelView.class);
        View viewFindRequiredView3 = Utils.findRequiredView(view, R.id.item_night_light_start_time, "field 'mItemNightStartTime' and method 'onViewClicked'");
        dNDModeActivity.mItemNightStartTime = (CustomItemLabelView) Utils.castView(viewFindRequiredView3, R.id.item_night_light_start_time, "field 'mItemNightStartTime'", CustomItemLabelView.class);
        this.view7f0a0292 = viewFindRequiredView3;
        viewFindRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DNDModeActivity_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                dNDModeActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView4 = Utils.findRequiredView(view, R.id.item_night_light_end_time, "field 'mItemNightEndTime' and method 'onViewClicked'");
        dNDModeActivity.mItemNightEndTime = (CustomItemLabelView) Utils.castView(viewFindRequiredView4, R.id.item_night_light_end_time, "field 'mItemNightEndTime'", CustomItemLabelView.class);
        this.view7f0a0291 = viewFindRequiredView4;
        viewFindRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DNDModeActivity_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                dNDModeActivity.onViewClicked(view2);
            }
        });
        dNDModeActivity.mLayoutDndTimeNightTime = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_dnd_time_nighttime, "field 'mLayoutDndTimeNightTime'", LinearLayout.class);
        dNDModeActivity.mRtvDndModeTip = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.rtv_dnd_mode_tip, "field 'mRtvDndModeTip'", RegularTextView.class);
        dNDModeActivity.mCommLoadingView = (CommLoadingView) Utils.findRequiredViewAsType(view, R.id.comm_loading_view, "field 'mCommLoadingView'", CommLoadingView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        DNDModeActivity dNDModeActivity = this.target;
        if (dNDModeActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        dNDModeActivity.mItemDndDaytimeSwitch = null;
        dNDModeActivity.mItemDayStartTime = null;
        dNDModeActivity.mItemDayEndTime = null;
        dNDModeActivity.mLayoutDndTimeDaytime = null;
        dNDModeActivity.mItemDndNighttimeSwitch = null;
        dNDModeActivity.mItemNightStartTime = null;
        dNDModeActivity.mItemNightEndTime = null;
        dNDModeActivity.mLayoutDndTimeNightTime = null;
        dNDModeActivity.mRtvDndModeTip = null;
        dNDModeActivity.mCommLoadingView = null;
        this.view7f0a0263.setOnClickListener(null);
        this.view7f0a0263 = null;
        this.view7f0a0260.setOnClickListener(null);
        this.view7f0a0260 = null;
        this.view7f0a0292.setOnClickListener(null);
        this.view7f0a0292 = null;
        this.view7f0a0291.setOnClickListener(null);
        this.view7f0a0291 = null;
    }
}