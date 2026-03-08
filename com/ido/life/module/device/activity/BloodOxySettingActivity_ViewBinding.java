package com.ido.life.module.device.activity;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.life.base.BaseHealthMonitoringActivity_ViewBinding;
import com.ido.life.customview.ReminderSelectView;
import com.ido.life.customview.viewgroup.CommLoadingView;
import com.ido.life.customview.viewgroup.CustomItemLabelView;

/* JADX INFO: loaded from: classes2.dex */
public class BloodOxySettingActivity_ViewBinding extends BaseHealthMonitoringActivity_ViewBinding {
    private BloodOxySettingActivity target;
    private View view7f0a0589;

    public BloodOxySettingActivity_ViewBinding(BloodOxySettingActivity bloodOxySettingActivity) {
        this(bloodOxySettingActivity, bloodOxySettingActivity.getWindow().getDecorView());
    }

    public BloodOxySettingActivity_ViewBinding(final BloodOxySettingActivity bloodOxySettingActivity, View view) {
        super(bloodOxySettingActivity, view);
        this.target = bloodOxySettingActivity;
        bloodOxySettingActivity.mLayoutContent = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_content, "field 'mLayoutContent'", LinearLayout.class);
        bloodOxySettingActivity.mItemBloodOxySwitch = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.blood_oxygen_detect, "field 'mItemBloodOxySwitch'", CustomItemLabelView.class);
        bloodOxySettingActivity.mItemRemindSwitch = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.blood_remind_off, "field 'mItemRemindSwitch'", CustomItemLabelView.class);
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.oxygen_remind_value, "field 'mRemindValueItem' and method 'onClick'");
        bloodOxySettingActivity.mRemindValueItem = (CustomItemLabelView) Utils.castView(viewFindRequiredView, R.id.oxygen_remind_value, "field 'mRemindValueItem'", CustomItemLabelView.class);
        this.view7f0a0589 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.BloodOxySettingActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                bloodOxySettingActivity.onClick(view2);
            }
        });
        bloodOxySettingActivity.mTvBloodHint = (TextView) Utils.findRequiredViewAsType(view, R.id.low_blood_oxygen_hint, "field 'mTvBloodHint'", TextView.class);
        bloodOxySettingActivity.mCommLoadingView = (CommLoadingView) Utils.findRequiredViewAsType(view, R.id.comm_loading_view, "field 'mCommLoadingView'", CommLoadingView.class);
        bloodOxySettingActivity.vReminder = (ReminderSelectView) Utils.findRequiredViewAsType(view, R.id.vReminder, "field 'vReminder'", ReminderSelectView.class);
    }

    @Override // com.ido.life.base.BaseHealthMonitoringActivity_ViewBinding, butterknife.Unbinder
    public void unbind() {
        BloodOxySettingActivity bloodOxySettingActivity = this.target;
        if (bloodOxySettingActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        bloodOxySettingActivity.mLayoutContent = null;
        bloodOxySettingActivity.mItemBloodOxySwitch = null;
        bloodOxySettingActivity.mItemRemindSwitch = null;
        bloodOxySettingActivity.mRemindValueItem = null;
        bloodOxySettingActivity.mTvBloodHint = null;
        bloodOxySettingActivity.mCommLoadingView = null;
        bloodOxySettingActivity.vReminder = null;
        this.view7f0a0589.setOnClickListener(null);
        this.view7f0a0589 = null;
        super.unbind();
    }
}