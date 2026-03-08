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
public class FitnessGuidanceActivity_ViewBinding extends BaseHealthMonitoringActivity_ViewBinding {
    private FitnessGuidanceActivity target;
    private View view7f0a0265;
    private View view7f0a0274;
    private View view7f0a02ab;
    private View view7f0a02b6;

    public FitnessGuidanceActivity_ViewBinding(FitnessGuidanceActivity fitnessGuidanceActivity) {
        this(fitnessGuidanceActivity, fitnessGuidanceActivity.getWindow().getDecorView());
    }

    public FitnessGuidanceActivity_ViewBinding(final FitnessGuidanceActivity fitnessGuidanceActivity, View view) {
        super(fitnessGuidanceActivity, view);
        this.target = fitnessGuidanceActivity;
        fitnessGuidanceActivity.mItemWalkReminderSwitch = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.item_walk_reminder_switch, "field 'mItemWalkReminderSwitch'", CustomItemLabelView.class);
        fitnessGuidanceActivity.mItemFitnessGuideReminderSwitch = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.item_fitness_coaching_reminder_switch, "field 'mItemFitnessGuideReminderSwitch'", CustomItemLabelView.class);
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.item_start_time, "field 'mItemStartTime' and method 'onViewClicked'");
        fitnessGuidanceActivity.mItemStartTime = (CustomItemLabelView) Utils.castView(viewFindRequiredView, R.id.item_start_time, "field 'mItemStartTime'", CustomItemLabelView.class);
        this.view7f0a02b6 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.FitnessGuidanceActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                fitnessGuidanceActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.item_end_time, "field 'mItemEndTime' and method 'onViewClicked'");
        fitnessGuidanceActivity.mItemEndTime = (CustomItemLabelView) Utils.castView(viewFindRequiredView2, R.id.item_end_time, "field 'mItemEndTime'", CustomItemLabelView.class);
        this.view7f0a0265 = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.FitnessGuidanceActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                fitnessGuidanceActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView3 = Utils.findRequiredView(view, R.id.item_hour_goal_steps, "field 'mItemHourGoalSteps' and method 'onViewClicked'");
        fitnessGuidanceActivity.mItemHourGoalSteps = (CustomItemLabelView) Utils.castView(viewFindRequiredView3, R.id.item_hour_goal_steps, "field 'mItemHourGoalSteps'", CustomItemLabelView.class);
        this.view7f0a0274 = viewFindRequiredView3;
        viewFindRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.FitnessGuidanceActivity_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                fitnessGuidanceActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView4 = Utils.findRequiredView(view, R.id.item_repetition_period, "field 'mItemRepetitionPeriod' and method 'onViewClicked'");
        fitnessGuidanceActivity.mItemRepetitionPeriod = (CustomItemLabelView) Utils.castView(viewFindRequiredView4, R.id.item_repetition_period, "field 'mItemRepetitionPeriod'", CustomItemLabelView.class);
        this.view7f0a02ab = viewFindRequiredView4;
        viewFindRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.FitnessGuidanceActivity_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                fitnessGuidanceActivity.onViewClicked(view2);
            }
        });
        fitnessGuidanceActivity.mLayoutContent = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_content, "field 'mLayoutContent'", LinearLayout.class);
        fitnessGuidanceActivity.mRtvWalkReminderTip = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.rtv_walk_reminder_tip, "field 'mRtvWalkReminderTip'", RegularTextView.class);
        fitnessGuidanceActivity.mCommLoadingView = (CommLoadingView) Utils.findRequiredViewAsType(view, R.id.comm_loading_view, "field 'mCommLoadingView'", CommLoadingView.class);
        fitnessGuidanceActivity.vReminder = (ReminderSelectView) Utils.findRequiredViewAsType(view, R.id.vReminder, "field 'vReminder'", ReminderSelectView.class);
    }

    @Override // com.ido.life.base.BaseHealthMonitoringActivity_ViewBinding, butterknife.Unbinder
    public void unbind() {
        FitnessGuidanceActivity fitnessGuidanceActivity = this.target;
        if (fitnessGuidanceActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        fitnessGuidanceActivity.mItemWalkReminderSwitch = null;
        fitnessGuidanceActivity.mItemFitnessGuideReminderSwitch = null;
        fitnessGuidanceActivity.mItemStartTime = null;
        fitnessGuidanceActivity.mItemEndTime = null;
        fitnessGuidanceActivity.mItemHourGoalSteps = null;
        fitnessGuidanceActivity.mItemRepetitionPeriod = null;
        fitnessGuidanceActivity.mLayoutContent = null;
        fitnessGuidanceActivity.mRtvWalkReminderTip = null;
        fitnessGuidanceActivity.mCommLoadingView = null;
        fitnessGuidanceActivity.vReminder = null;
        this.view7f0a02b6.setOnClickListener(null);
        this.view7f0a02b6 = null;
        this.view7f0a0265.setOnClickListener(null);
        this.view7f0a0265 = null;
        this.view7f0a0274.setOnClickListener(null);
        this.view7f0a0274 = null;
        this.view7f0a02ab.setOnClickListener(null);
        this.view7f0a02ab = null;
        super.unbind();
    }
}