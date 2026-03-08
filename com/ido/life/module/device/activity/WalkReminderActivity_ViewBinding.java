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
public class WalkReminderActivity_ViewBinding implements Unbinder {
    private WalkReminderActivity target;
    private View view7f0a0265;
    private View view7f0a0274;
    private View view7f0a02ab;
    private View view7f0a02b6;

    public WalkReminderActivity_ViewBinding(WalkReminderActivity walkReminderActivity) {
        this(walkReminderActivity, walkReminderActivity.getWindow().getDecorView());
    }

    public WalkReminderActivity_ViewBinding(final WalkReminderActivity walkReminderActivity, View view) {
        this.target = walkReminderActivity;
        walkReminderActivity.mItemWalkReminderSwitch = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.item_walk_reminder_switch, "field 'mItemWalkReminderSwitch'", CustomItemLabelView.class);
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.item_start_time, "field 'mItemStartTime' and method 'onViewClicked'");
        walkReminderActivity.mItemStartTime = (CustomItemLabelView) Utils.castView(viewFindRequiredView, R.id.item_start_time, "field 'mItemStartTime'", CustomItemLabelView.class);
        this.view7f0a02b6 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.WalkReminderActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                walkReminderActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.item_end_time, "field 'mItemEndTime' and method 'onViewClicked'");
        walkReminderActivity.mItemEndTime = (CustomItemLabelView) Utils.castView(viewFindRequiredView2, R.id.item_end_time, "field 'mItemEndTime'", CustomItemLabelView.class);
        this.view7f0a0265 = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.WalkReminderActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                walkReminderActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView3 = Utils.findRequiredView(view, R.id.item_hour_goal_steps, "field 'mItemHourGoalSteps' and method 'onViewClicked'");
        walkReminderActivity.mItemHourGoalSteps = (CustomItemLabelView) Utils.castView(viewFindRequiredView3, R.id.item_hour_goal_steps, "field 'mItemHourGoalSteps'", CustomItemLabelView.class);
        this.view7f0a0274 = viewFindRequiredView3;
        viewFindRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.WalkReminderActivity_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                walkReminderActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView4 = Utils.findRequiredView(view, R.id.item_repetition_period, "field 'mItemRepetitionPeriod' and method 'onViewClicked'");
        walkReminderActivity.mItemRepetitionPeriod = (CustomItemLabelView) Utils.castView(viewFindRequiredView4, R.id.item_repetition_period, "field 'mItemRepetitionPeriod'", CustomItemLabelView.class);
        this.view7f0a02ab = viewFindRequiredView4;
        viewFindRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.WalkReminderActivity_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                walkReminderActivity.onViewClicked(view2);
            }
        });
        walkReminderActivity.mLayoutContent = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_content, "field 'mLayoutContent'", LinearLayout.class);
        walkReminderActivity.mRtvWalkReminderTip = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.rtv_walk_reminder_tip, "field 'mRtvWalkReminderTip'", RegularTextView.class);
        walkReminderActivity.mCommLoadingView = (CommLoadingView) Utils.findRequiredViewAsType(view, R.id.comm_loading_view, "field 'mCommLoadingView'", CommLoadingView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        WalkReminderActivity walkReminderActivity = this.target;
        if (walkReminderActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        walkReminderActivity.mItemWalkReminderSwitch = null;
        walkReminderActivity.mItemStartTime = null;
        walkReminderActivity.mItemEndTime = null;
        walkReminderActivity.mItemHourGoalSteps = null;
        walkReminderActivity.mItemRepetitionPeriod = null;
        walkReminderActivity.mLayoutContent = null;
        walkReminderActivity.mRtvWalkReminderTip = null;
        walkReminderActivity.mCommLoadingView = null;
        this.view7f0a02b6.setOnClickListener(null);
        this.view7f0a02b6 = null;
        this.view7f0a0265.setOnClickListener(null);
        this.view7f0a0265 = null;
        this.view7f0a0274.setOnClickListener(null);
        this.view7f0a0274 = null;
        this.view7f0a02ab.setOnClickListener(null);
        this.view7f0a02ab = null;
    }
}