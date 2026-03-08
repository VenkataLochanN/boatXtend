package com.ido.life.module.sport.setting.voice;

import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.customview.MediumRadioButton;

/* JADX INFO: loaded from: classes2.dex */
public class SportVoiceActivity_ViewBinding implements Unbinder {
    private SportVoiceActivity target;
    private View view7f0a0776;

    public SportVoiceActivity_ViewBinding(SportVoiceActivity sportVoiceActivity) {
        this(sportVoiceActivity, sportVoiceActivity.getWindow().getDecorView());
    }

    public SportVoiceActivity_ViewBinding(final SportVoiceActivity sportVoiceActivity, View view) {
        this.target = sportVoiceActivity;
        sportVoiceActivity.mRadioGroup = (RadioGroup) Utils.findRequiredViewAsType(view, R.id.radio_group, "field 'mRadioGroup'", RadioGroup.class);
        sportVoiceActivity.mTitleText = (TextView) Utils.findRequiredViewAsType(view, R.id.title_text, "field 'mTitleText'", TextView.class);
        sportVoiceActivity.mTvRemindFrequency = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.tv_remind_frequency, "field 'mTvRemindFrequency'", RegularTextView.class);
        sportVoiceActivity.mRbNoRemind = (MediumRadioButton) Utils.findRequiredViewAsType(view, R.id.mrb_no_remind, "field 'mRbNoRemind'", MediumRadioButton.class);
        sportVoiceActivity.mRbOneKm = (MediumRadioButton) Utils.findRequiredViewAsType(view, R.id.mrb_one_km, "field 'mRbOneKm'", MediumRadioButton.class);
        sportVoiceActivity.mRbThreeKm = (MediumRadioButton) Utils.findRequiredViewAsType(view, R.id.mrb_three_km, "field 'mRbThreeKm'", MediumRadioButton.class);
        sportVoiceActivity.mRbFiveKm = (MediumRadioButton) Utils.findRequiredViewAsType(view, R.id.mrb_five_km, "field 'mRbFiveKm'", MediumRadioButton.class);
        sportVoiceActivity.mRbTenKm = (MediumRadioButton) Utils.findRequiredViewAsType(view, R.id.mrb_ten_km, "field 'mRbTenKm'", MediumRadioButton.class);
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.title_leftBtn, "method 'toBack'");
        this.view7f0a0776 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.sport.setting.voice.SportVoiceActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                sportVoiceActivity.toBack(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        SportVoiceActivity sportVoiceActivity = this.target;
        if (sportVoiceActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        sportVoiceActivity.mRadioGroup = null;
        sportVoiceActivity.mTitleText = null;
        sportVoiceActivity.mTvRemindFrequency = null;
        sportVoiceActivity.mRbNoRemind = null;
        sportVoiceActivity.mRbOneKm = null;
        sportVoiceActivity.mRbThreeKm = null;
        sportVoiceActivity.mRbFiveKm = null;
        sportVoiceActivity.mRbTenKm = null;
        this.view7f0a0776.setOnClickListener(null);
        this.view7f0a0776 = null;
    }
}