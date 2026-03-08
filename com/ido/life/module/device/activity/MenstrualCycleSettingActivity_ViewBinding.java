package com.ido.life.module.device.activity;

import android.view.View;
import android.widget.LinearLayout;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.base.BaseHealthMonitoringActivity_ViewBinding;
import com.ido.life.customview.ReminderSelectView;
import com.ido.life.customview.viewgroup.CommLoadingView;
import com.ido.life.customview.viewgroup.CustomItemLabelView;

/* JADX INFO: loaded from: classes2.dex */
public class MenstrualCycleSettingActivity_ViewBinding extends BaseHealthMonitoringActivity_ViewBinding {
    private MenstrualCycleSettingActivity target;
    private View view7f0a027b;
    private View view7f0a0281;
    private View view7f0a0282;
    private View view7f0a0296;
    private View view7f0a0297;
    private View view7f0a029a;
    private View view7f0a02a7;

    public MenstrualCycleSettingActivity_ViewBinding(MenstrualCycleSettingActivity menstrualCycleSettingActivity) {
        this(menstrualCycleSettingActivity, menstrualCycleSettingActivity.getWindow().getDecorView());
    }

    public MenstrualCycleSettingActivity_ViewBinding(final MenstrualCycleSettingActivity menstrualCycleSettingActivity, View view) {
        super(menstrualCycleSettingActivity, view);
        this.target = menstrualCycleSettingActivity;
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.item_pregnancy_remind, "field 'mItemPregnancyRemind' and method 'onViewClicked'");
        menstrualCycleSettingActivity.mItemPregnancyRemind = (CustomItemLabelView) Utils.castView(viewFindRequiredView, R.id.item_pregnancy_remind, "field 'mItemPregnancyRemind'", CustomItemLabelView.class);
        this.view7f0a029a = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.MenstrualCycleSettingActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                menstrualCycleSettingActivity.onViewClicked(view2);
            }
        });
        menstrualCycleSettingActivity.mItemMenstrualCycleSwitch = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.item_menstrual_cycle_switch, "field 'mItemMenstrualCycleSwitch'", CustomItemLabelView.class);
        menstrualCycleSettingActivity.mRtvMenstrualCycleTip = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.rtv_menstrual_cycle_tip, "field 'mRtvMenstrualCycleTip'", RegularTextView.class);
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.item_menstrual_remind, "field 'mItemMenstrualRemind' and method 'onViewClicked'");
        menstrualCycleSettingActivity.mItemMenstrualRemind = (CustomItemLabelView) Utils.castView(viewFindRequiredView2, R.id.item_menstrual_remind, "field 'mItemMenstrualRemind'", CustomItemLabelView.class);
        this.view7f0a0282 = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.MenstrualCycleSettingActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                menstrualCycleSettingActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView3 = Utils.findRequiredView(view, R.id.item_ovulation_remind, "field 'mItemOvulationRemind' and method 'onViewClicked'");
        menstrualCycleSettingActivity.mItemOvulationRemind = (CustomItemLabelView) Utils.castView(viewFindRequiredView3, R.id.item_ovulation_remind, "field 'mItemOvulationRemind'", CustomItemLabelView.class);
        this.view7f0a0296 = viewFindRequiredView3;
        viewFindRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.MenstrualCycleSettingActivity_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                menstrualCycleSettingActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView4 = Utils.findRequiredView(view, R.id.item_reminder_time, "field 'mItemReminderTime' and method 'onViewClicked'");
        menstrualCycleSettingActivity.mItemReminderTime = (CustomItemLabelView) Utils.castView(viewFindRequiredView4, R.id.item_reminder_time, "field 'mItemReminderTime'", CustomItemLabelView.class);
        this.view7f0a02a7 = viewFindRequiredView4;
        viewFindRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.MenstrualCycleSettingActivity_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                menstrualCycleSettingActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView5 = Utils.findRequiredView(view, R.id.item_last_time, "field 'mItemLastTime' and method 'onViewClicked'");
        menstrualCycleSettingActivity.mItemLastTime = (CustomItemLabelView) Utils.castView(viewFindRequiredView5, R.id.item_last_time, "field 'mItemLastTime'", CustomItemLabelView.class);
        this.view7f0a027b = viewFindRequiredView5;
        viewFindRequiredView5.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.MenstrualCycleSettingActivity_ViewBinding.5
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                menstrualCycleSettingActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView6 = Utils.findRequiredView(view, R.id.item_menstrual_length, "field 'mItemMenstrualLength' and method 'onViewClicked'");
        menstrualCycleSettingActivity.mItemMenstrualLength = (CustomItemLabelView) Utils.castView(viewFindRequiredView6, R.id.item_menstrual_length, "field 'mItemMenstrualLength'", CustomItemLabelView.class);
        this.view7f0a0281 = viewFindRequiredView6;
        viewFindRequiredView6.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.MenstrualCycleSettingActivity_ViewBinding.6
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                menstrualCycleSettingActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView7 = Utils.findRequiredView(view, R.id.item_period_length, "field 'mItemPeriodLength' and method 'onViewClicked'");
        menstrualCycleSettingActivity.mItemPeriodLength = (CustomItemLabelView) Utils.castView(viewFindRequiredView7, R.id.item_period_length, "field 'mItemPeriodLength'", CustomItemLabelView.class);
        this.view7f0a0297 = viewFindRequiredView7;
        viewFindRequiredView7.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.MenstrualCycleSettingActivity_ViewBinding.7
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                menstrualCycleSettingActivity.onViewClicked(view2);
            }
        });
        menstrualCycleSettingActivity.mLayoutContent = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_content, "field 'mLayoutContent'", LinearLayout.class);
        menstrualCycleSettingActivity.mRtvMenstrualSetTip = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.rtv_menstrual_set_tip, "field 'mRtvMenstrualSetTip'", RegularTextView.class);
        menstrualCycleSettingActivity.mCommLoadingView = (CommLoadingView) Utils.findRequiredViewAsType(view, R.id.comm_loading_view, "field 'mCommLoadingView'", CommLoadingView.class);
        menstrualCycleSettingActivity.vReminder = (ReminderSelectView) Utils.findRequiredViewAsType(view, R.id.vReminder, "field 'vReminder'", ReminderSelectView.class);
    }

    @Override // com.ido.life.base.BaseHealthMonitoringActivity_ViewBinding, butterknife.Unbinder
    public void unbind() {
        MenstrualCycleSettingActivity menstrualCycleSettingActivity = this.target;
        if (menstrualCycleSettingActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        menstrualCycleSettingActivity.mItemPregnancyRemind = null;
        menstrualCycleSettingActivity.mItemMenstrualCycleSwitch = null;
        menstrualCycleSettingActivity.mRtvMenstrualCycleTip = null;
        menstrualCycleSettingActivity.mItemMenstrualRemind = null;
        menstrualCycleSettingActivity.mItemOvulationRemind = null;
        menstrualCycleSettingActivity.mItemReminderTime = null;
        menstrualCycleSettingActivity.mItemLastTime = null;
        menstrualCycleSettingActivity.mItemMenstrualLength = null;
        menstrualCycleSettingActivity.mItemPeriodLength = null;
        menstrualCycleSettingActivity.mLayoutContent = null;
        menstrualCycleSettingActivity.mRtvMenstrualSetTip = null;
        menstrualCycleSettingActivity.mCommLoadingView = null;
        menstrualCycleSettingActivity.vReminder = null;
        this.view7f0a029a.setOnClickListener(null);
        this.view7f0a029a = null;
        this.view7f0a0282.setOnClickListener(null);
        this.view7f0a0282 = null;
        this.view7f0a0296.setOnClickListener(null);
        this.view7f0a0296 = null;
        this.view7f0a02a7.setOnClickListener(null);
        this.view7f0a02a7 = null;
        this.view7f0a027b.setOnClickListener(null);
        this.view7f0a027b = null;
        this.view7f0a0281.setOnClickListener(null);
        this.view7f0a0281 = null;
        this.view7f0a0297.setOnClickListener(null);
        this.view7f0a0297 = null;
        super.unbind();
    }
}