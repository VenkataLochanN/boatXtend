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
public class CallReminderActivity_ViewBinding implements Unbinder {
    private CallReminderActivity target;
    private View view7f0a0257;

    public CallReminderActivity_ViewBinding(CallReminderActivity callReminderActivity) {
        this(callReminderActivity, callReminderActivity.getWindow().getDecorView());
    }

    public CallReminderActivity_ViewBinding(final CallReminderActivity callReminderActivity, View view) {
        this.target = callReminderActivity;
        callReminderActivity.mItemCallReminderSwitch = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.item_call_reminder_switch, "field 'mItemCallReminderSwitch'", CustomItemLabelView.class);
        callReminderActivity.delay3CallSwitch = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.item_call_delay_reminder_switch, "field 'delay3CallSwitch'", CustomItemLabelView.class);
        callReminderActivity.mRtvCallReminderTip = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.rtv_call_reminder_tip, "field 'mRtvCallReminderTip'", RegularTextView.class);
        callReminderActivity.layoutPhone = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_phone, "field 'layoutPhone'", LinearLayout.class);
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.item_contract, "method 'onViewClicked'");
        this.view7f0a0257 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.CallReminderActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                callReminderActivity.onViewClicked(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        CallReminderActivity callReminderActivity = this.target;
        if (callReminderActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        callReminderActivity.mItemCallReminderSwitch = null;
        callReminderActivity.delay3CallSwitch = null;
        callReminderActivity.mRtvCallReminderTip = null;
        callReminderActivity.layoutPhone = null;
        this.view7f0a0257.setOnClickListener(null);
        this.view7f0a0257 = null;
    }
}