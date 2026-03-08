package com.ido.life.module.home.detail;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.common.widget.textview.RegularTextView;

/* JADX INFO: loaded from: classes2.dex */
public class BreathingQualityActivity_ViewBinding implements Unbinder {
    private BreathingQualityActivity target;

    public BreathingQualityActivity_ViewBinding(BreathingQualityActivity breathingQualityActivity) {
        this(breathingQualityActivity, breathingQualityActivity.getWindow().getDecorView());
    }

    public BreathingQualityActivity_ViewBinding(BreathingQualityActivity breathingQualityActivity, View view) {
        this.target = breathingQualityActivity;
        breathingQualityActivity.mRtvDescription1 = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.rtv_description_1, "field 'mRtvDescription1'", RegularTextView.class);
        breathingQualityActivity.mRtvDescription2 = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.rtv_description_2, "field 'mRtvDescription2'", RegularTextView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        BreathingQualityActivity breathingQualityActivity = this.target;
        if (breathingQualityActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        breathingQualityActivity.mRtvDescription1 = null;
        breathingQualityActivity.mRtvDescription2 = null;
    }
}