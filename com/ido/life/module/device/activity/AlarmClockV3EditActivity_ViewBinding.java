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
public class AlarmClockV3EditActivity_ViewBinding implements Unbinder {
    private AlarmClockV3EditActivity target;
    private View view7f0a024c;
    private View view7f0a02aa;
    private View view7f0a0649;

    public AlarmClockV3EditActivity_ViewBinding(AlarmClockV3EditActivity alarmClockV3EditActivity) {
        this(alarmClockV3EditActivity, alarmClockV3EditActivity.getWindow().getDecorView());
    }

    public AlarmClockV3EditActivity_ViewBinding(final AlarmClockV3EditActivity alarmClockV3EditActivity, View view) {
        this.target = alarmClockV3EditActivity;
        alarmClockV3EditActivity.mWvHour = (WheelView) Utils.findRequiredViewAsType(view, R.id.wv_hour, "field 'mWvHour'", WheelView.class);
        alarmClockV3EditActivity.mWvMin = (WheelView) Utils.findRequiredViewAsType(view, R.id.wv_min, "field 'mWvMin'", WheelView.class);
        alarmClockV3EditActivity.mWvAmPm = (WheelView) Utils.findRequiredViewAsType(view, R.id.wv_am_pm, "field 'mWvAmPm'", WheelView.class);
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.rtv_delete_alarm, "field 'mRtvDeleteAlarm' and method 'onViewClicked'");
        alarmClockV3EditActivity.mRtvDeleteAlarm = (RegularTextView) Utils.castView(viewFindRequiredView, R.id.rtv_delete_alarm, "field 'mRtvDeleteAlarm'", RegularTextView.class);
        this.view7f0a0649 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.AlarmClockV3EditActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                alarmClockV3EditActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.item_repetition, "field 'mItemRepetition' and method 'onViewClicked'");
        alarmClockV3EditActivity.mItemRepetition = (CustomItemLabelView) Utils.castView(viewFindRequiredView2, R.id.item_repetition, "field 'mItemRepetition'", CustomItemLabelView.class);
        this.view7f0a02aa = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.AlarmClockV3EditActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                alarmClockV3EditActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView3 = Utils.findRequiredView(view, R.id.item_alarm_name, "field 'mItemAlarmName' and method 'onViewClicked'");
        alarmClockV3EditActivity.mItemAlarmName = (CustomItemLabelView) Utils.castView(viewFindRequiredView3, R.id.item_alarm_name, "field 'mItemAlarmName'", CustomItemLabelView.class);
        this.view7f0a024c = viewFindRequiredView3;
        viewFindRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.AlarmClockV3EditActivity_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                alarmClockV3EditActivity.onViewClicked(view2);
            }
        });
        alarmClockV3EditActivity.timeLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.timeLayout, "field 'timeLayout'", LinearLayout.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        AlarmClockV3EditActivity alarmClockV3EditActivity = this.target;
        if (alarmClockV3EditActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        alarmClockV3EditActivity.mWvHour = null;
        alarmClockV3EditActivity.mWvMin = null;
        alarmClockV3EditActivity.mWvAmPm = null;
        alarmClockV3EditActivity.mRtvDeleteAlarm = null;
        alarmClockV3EditActivity.mItemRepetition = null;
        alarmClockV3EditActivity.mItemAlarmName = null;
        alarmClockV3EditActivity.timeLayout = null;
        this.view7f0a0649.setOnClickListener(null);
        this.view7f0a0649 = null;
        this.view7f0a02aa.setOnClickListener(null);
        this.view7f0a02aa = null;
        this.view7f0a024c.setOnClickListener(null);
        this.view7f0a024c = null;
    }
}