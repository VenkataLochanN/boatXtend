package com.ido.life.module.device.activity;

import android.view.View;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.common.widget.textview.RegularTextView;

/* JADX INFO: loaded from: classes2.dex */
public class AlarmNameEditActivity_ViewBinding implements Unbinder {
    private AlarmNameEditActivity target;

    public AlarmNameEditActivity_ViewBinding(AlarmNameEditActivity alarmNameEditActivity) {
        this(alarmNameEditActivity, alarmNameEditActivity.getWindow().getDecorView());
    }

    public AlarmNameEditActivity_ViewBinding(AlarmNameEditActivity alarmNameEditActivity, View view) {
        this.target = alarmNameEditActivity;
        alarmNameEditActivity.mEtAlarmName = (EditText) Utils.findRequiredViewAsType(view, R.id.et_alarm_name, "field 'mEtAlarmName'", EditText.class);
        alarmNameEditActivity.mRtvAlarmTip = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.rtv_alarm_tip, "field 'mRtvAlarmTip'", RegularTextView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        AlarmNameEditActivity alarmNameEditActivity = this.target;
        if (alarmNameEditActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        alarmNameEditActivity.mEtAlarmName = null;
        alarmNameEditActivity.mRtvAlarmTip = null;
    }
}