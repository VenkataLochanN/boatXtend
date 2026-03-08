package com.ido.life.module.device.activity;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.life.customview.viewgroup.CustomItemLabelView;

/* JADX INFO: loaded from: classes2.dex */
public class NightLightActivity_ViewBinding implements Unbinder {
    private NightLightActivity target;
    private View view7f0a0265;
    private View view7f0a02b6;

    public NightLightActivity_ViewBinding(NightLightActivity nightLightActivity) {
        this(nightLightActivity, nightLightActivity.getWindow().getDecorView());
    }

    public NightLightActivity_ViewBinding(final NightLightActivity nightLightActivity, View view) {
        this.target = nightLightActivity;
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.item_start_time, "field 'mItemStartTime' and method 'onViewClicked'");
        nightLightActivity.mItemStartTime = (CustomItemLabelView) Utils.castView(viewFindRequiredView, R.id.item_start_time, "field 'mItemStartTime'", CustomItemLabelView.class);
        this.view7f0a02b6 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.NightLightActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                nightLightActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.item_end_time, "field 'mItemEndTime' and method 'onViewClicked'");
        nightLightActivity.mItemEndTime = (CustomItemLabelView) Utils.castView(viewFindRequiredView2, R.id.item_end_time, "field 'mItemEndTime'", CustomItemLabelView.class);
        this.view7f0a0265 = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.NightLightActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                nightLightActivity.onViewClicked(view2);
            }
        });
        nightLightActivity.mItemNightLightSwitch = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.item_night_light_switch, "field 'mItemNightLightSwitch'", CustomItemLabelView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        NightLightActivity nightLightActivity = this.target;
        if (nightLightActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        nightLightActivity.mItemStartTime = null;
        nightLightActivity.mItemEndTime = null;
        nightLightActivity.mItemNightLightSwitch = null;
        this.view7f0a02b6.setOnClickListener(null);
        this.view7f0a02b6 = null;
        this.view7f0a0265.setOnClickListener(null);
        this.view7f0a0265 = null;
    }
}