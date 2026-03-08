package com.ido.life.module.device.activity;

import android.view.View;
import android.widget.RadioGroup;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.life.customview.MediumRadioButton;

/* JADX INFO: loaded from: classes2.dex */
public class AlarmReminderParamSettingActivity_ViewBinding implements Unbinder {
    private AlarmReminderParamSettingActivity target;

    public AlarmReminderParamSettingActivity_ViewBinding(AlarmReminderParamSettingActivity alarmReminderParamSettingActivity) {
        this(alarmReminderParamSettingActivity, alarmReminderParamSettingActivity.getWindow().getDecorView());
    }

    public AlarmReminderParamSettingActivity_ViewBinding(AlarmReminderParamSettingActivity alarmReminderParamSettingActivity, View view) {
        this.target = alarmReminderParamSettingActivity;
        alarmReminderParamSettingActivity.mMrbItem1 = (MediumRadioButton) Utils.findRequiredViewAsType(view, R.id.mrb_item_1, "field 'mMrbItem1'", MediumRadioButton.class);
        alarmReminderParamSettingActivity.mMrbItem2 = (MediumRadioButton) Utils.findRequiredViewAsType(view, R.id.mrb_item_2, "field 'mMrbItem2'", MediumRadioButton.class);
        alarmReminderParamSettingActivity.mMrbItem3 = (MediumRadioButton) Utils.findRequiredViewAsType(view, R.id.mrb_item_3, "field 'mMrbItem3'", MediumRadioButton.class);
        alarmReminderParamSettingActivity.mMrbItem4 = (MediumRadioButton) Utils.findRequiredViewAsType(view, R.id.mrb_item_4, "field 'mMrbItem4'", MediumRadioButton.class);
        alarmReminderParamSettingActivity.mRadioGroup = (RadioGroup) Utils.findRequiredViewAsType(view, R.id.radio_group, "field 'mRadioGroup'", RadioGroup.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        AlarmReminderParamSettingActivity alarmReminderParamSettingActivity = this.target;
        if (alarmReminderParamSettingActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        alarmReminderParamSettingActivity.mMrbItem1 = null;
        alarmReminderParamSettingActivity.mMrbItem2 = null;
        alarmReminderParamSettingActivity.mMrbItem3 = null;
        alarmReminderParamSettingActivity.mMrbItem4 = null;
        alarmReminderParamSettingActivity.mRadioGroup = null;
    }
}