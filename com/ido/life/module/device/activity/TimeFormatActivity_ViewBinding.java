package com.ido.life.module.device.activity;

import android.view.View;
import android.widget.RadioGroup;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.life.customview.MediumRadioButton;

/* JADX INFO: loaded from: classes2.dex */
public class TimeFormatActivity_ViewBinding implements Unbinder {
    private TimeFormatActivity target;

    public TimeFormatActivity_ViewBinding(TimeFormatActivity timeFormatActivity) {
        this(timeFormatActivity, timeFormatActivity.getWindow().getDecorView());
    }

    public TimeFormatActivity_ViewBinding(TimeFormatActivity timeFormatActivity, View view) {
        this.target = timeFormatActivity;
        timeFormatActivity.mMrbFollowSystem = (MediumRadioButton) Utils.findRequiredViewAsType(view, R.id.mrb_follow_system, "field 'mMrbFollowSystem'", MediumRadioButton.class);
        timeFormatActivity.mMrbFormat12 = (MediumRadioButton) Utils.findRequiredViewAsType(view, R.id.mrb_format_12, "field 'mMrbFormat12'", MediumRadioButton.class);
        timeFormatActivity.mMrbFormat24 = (MediumRadioButton) Utils.findRequiredViewAsType(view, R.id.mrb_format_24, "field 'mMrbFormat24'", MediumRadioButton.class);
        timeFormatActivity.mRadioGroup = (RadioGroup) Utils.findRequiredViewAsType(view, R.id.radio_group, "field 'mRadioGroup'", RadioGroup.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        TimeFormatActivity timeFormatActivity = this.target;
        if (timeFormatActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        timeFormatActivity.mMrbFollowSystem = null;
        timeFormatActivity.mMrbFormat12 = null;
        timeFormatActivity.mMrbFormat24 = null;
        timeFormatActivity.mRadioGroup = null;
    }
}