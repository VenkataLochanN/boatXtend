package com.ido.life.module.device.activity;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.life.customview.viewgroup.CustomItemLabelView;

/* JADX INFO: loaded from: classes2.dex */
public class AlarmClockSettingActivity_ViewBinding implements Unbinder {
    private AlarmClockSettingActivity target;
    private View view7f0a02a3;
    private View view7f0a02a9;

    public AlarmClockSettingActivity_ViewBinding(AlarmClockSettingActivity alarmClockSettingActivity) {
        this(alarmClockSettingActivity, alarmClockSettingActivity.getWindow().getDecorView());
    }

    public AlarmClockSettingActivity_ViewBinding(final AlarmClockSettingActivity alarmClockSettingActivity, View view) {
        this.target = alarmClockSettingActivity;
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.item_remind_interval, "field 'mItemRemindInterval' and method 'onViewClicked'");
        alarmClockSettingActivity.mItemRemindInterval = (CustomItemLabelView) Utils.castView(viewFindRequiredView, R.id.item_remind_interval, "field 'mItemRemindInterval'", CustomItemLabelView.class);
        this.view7f0a02a3 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.AlarmClockSettingActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                alarmClockSettingActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.item_repeated_times, "field 'mItemRepeatedTimes' and method 'onViewClicked'");
        alarmClockSettingActivity.mItemRepeatedTimes = (CustomItemLabelView) Utils.castView(viewFindRequiredView2, R.id.item_repeated_times, "field 'mItemRepeatedTimes'", CustomItemLabelView.class);
        this.view7f0a02a9 = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.AlarmClockSettingActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                alarmClockSettingActivity.onViewClicked(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        AlarmClockSettingActivity alarmClockSettingActivity = this.target;
        if (alarmClockSettingActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        alarmClockSettingActivity.mItemRemindInterval = null;
        alarmClockSettingActivity.mItemRepeatedTimes = null;
        this.view7f0a02a3.setOnClickListener(null);
        this.view7f0a02a3 = null;
        this.view7f0a02a9.setOnClickListener(null);
        this.view7f0a02a9 = null;
    }
}