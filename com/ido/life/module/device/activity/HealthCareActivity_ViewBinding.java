package com.ido.life.module.device.activity;

import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.customview.viewgroup.CustomItemLabelView;

/* JADX INFO: loaded from: classes2.dex */
public class HealthCareActivity_ViewBinding implements Unbinder {
    private HealthCareActivity target;
    private View view7f0a0246;
    private View view7f0a02a4;

    public HealthCareActivity_ViewBinding(HealthCareActivity healthCareActivity) {
        this(healthCareActivity, healthCareActivity.getWindow().getDecorView());
    }

    public HealthCareActivity_ViewBinding(final HealthCareActivity healthCareActivity, View view) {
        this.target = healthCareActivity;
        healthCareActivity.mItemHealthCareSwitch = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.item_health_care_switch, "field 'mItemHealthCareSwitch'", CustomItemLabelView.class);
        healthCareActivity.mLayoutContent = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_content, "field 'mLayoutContent'", LinearLayout.class);
        healthCareActivity.mRtvHealthCareTip = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.rtv_health_care_tip, "field 'mRtvHealthCareTip'", RegularTextView.class);
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.item_Menstrual_set, "field 'mItemMenstrualSet' and method 'onViewClicked'");
        healthCareActivity.mItemMenstrualSet = (CustomItemLabelView) Utils.castView(viewFindRequiredView, R.id.item_Menstrual_set, "field 'mItemMenstrualSet'", CustomItemLabelView.class);
        this.view7f0a0246 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.HealthCareActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                healthCareActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.item_remind_set, "field 'mItemRemindSet' and method 'onViewClicked'");
        healthCareActivity.mItemRemindSet = (CustomItemLabelView) Utils.castView(viewFindRequiredView2, R.id.item_remind_set, "field 'mItemRemindSet'", CustomItemLabelView.class);
        this.view7f0a02a4 = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.HealthCareActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                healthCareActivity.onViewClicked(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        HealthCareActivity healthCareActivity = this.target;
        if (healthCareActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        healthCareActivity.mItemHealthCareSwitch = null;
        healthCareActivity.mLayoutContent = null;
        healthCareActivity.mRtvHealthCareTip = null;
        healthCareActivity.mItemMenstrualSet = null;
        healthCareActivity.mItemRemindSet = null;
        this.view7f0a0246.setOnClickListener(null);
        this.view7f0a0246 = null;
        this.view7f0a02a4.setOnClickListener(null);
        this.view7f0a02a4 = null;
    }
}