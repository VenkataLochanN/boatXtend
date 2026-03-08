package com.ido.life.module.device.activity;

import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.customview.viewgroup.CustomItemLabelView;
import com.watch.life.wheelview.view.WheelView;

/* JADX INFO: loaded from: classes2.dex */
public class AlarmClockEditActivity_ViewBinding implements Unbinder {
    private AlarmClockEditActivity target;
    private View view7f0a024c;
    private View view7f0a02aa;
    private View view7f0a0649;

    public AlarmClockEditActivity_ViewBinding(AlarmClockEditActivity alarmClockEditActivity) {
        this(alarmClockEditActivity, alarmClockEditActivity.getWindow().getDecorView());
    }

    public AlarmClockEditActivity_ViewBinding(final AlarmClockEditActivity alarmClockEditActivity, View view) {
        this.target = alarmClockEditActivity;
        alarmClockEditActivity.mWvHour = (WheelView) Utils.findRequiredViewAsType(view, R.id.wv_hour, "field 'mWvHour'", WheelView.class);
        alarmClockEditActivity.mWvMin = (WheelView) Utils.findRequiredViewAsType(view, R.id.wv_min, "field 'mWvMin'", WheelView.class);
        alarmClockEditActivity.mWvAmPm = (WheelView) Utils.findRequiredViewAsType(view, R.id.wv_am_pm, "field 'mWvAmPm'", WheelView.class);
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.rtv_delete_alarm, "field 'mRtvDeleteAlarm' and method 'onViewClicked'");
        alarmClockEditActivity.mRtvDeleteAlarm = (RegularTextView) Utils.castView(viewFindRequiredView, R.id.rtv_delete_alarm, "field 'mRtvDeleteAlarm'", RegularTextView.class);
        this.view7f0a0649 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.AlarmClockEditActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                alarmClockEditActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.item_repetition, "field 'mItemRepetition' and method 'onViewClicked'");
        alarmClockEditActivity.mItemRepetition = (CustomItemLabelView) Utils.castView(viewFindRequiredView2, R.id.item_repetition, "field 'mItemRepetition'", CustomItemLabelView.class);
        this.view7f0a02aa = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.AlarmClockEditActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                alarmClockEditActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView3 = Utils.findRequiredView(view, R.id.item_alarm_name, "field 'mItemAlarmName' and method 'onViewClicked'");
        alarmClockEditActivity.mItemAlarmName = (CustomItemLabelView) Utils.castView(viewFindRequiredView3, R.id.item_alarm_name, "field 'mItemAlarmName'", CustomItemLabelView.class);
        this.view7f0a024c = viewFindRequiredView3;
        viewFindRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.AlarmClockEditActivity_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                alarmClockEditActivity.onViewClicked(view2);
            }
        });
        alarmClockEditActivity.timeLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.timeLayout, "field 'timeLayout'", LinearLayout.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        AlarmClockEditActivity alarmClockEditActivity = this.target;
        if (alarmClockEditActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        alarmClockEditActivity.mWvHour = null;
        alarmClockEditActivity.mWvMin = null;
        alarmClockEditActivity.mWvAmPm = null;
        alarmClockEditActivity.mRtvDeleteAlarm = null;
        alarmClockEditActivity.mItemRepetition = null;
        alarmClockEditActivity.mItemAlarmName = null;
        alarmClockEditActivity.timeLayout = null;
        this.view7f0a0649.setOnClickListener(null);
        this.view7f0a0649 = null;
        this.view7f0a02aa.setOnClickListener(null);
        this.view7f0a02aa = null;
        this.view7f0a024c.setOnClickListener(null);
        this.view7f0a024c = null;
    }
}