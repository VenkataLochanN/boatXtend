package com.ido.life.dialog;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.watch.life.wheelview.view.WheelView;

/* JADX INFO: loaded from: classes2.dex */
public class ChooseSexDialogFragment_ViewBinding implements Unbinder {
    private ChooseSexDialogFragment target;

    public ChooseSexDialogFragment_ViewBinding(ChooseSexDialogFragment chooseSexDialogFragment, View view) {
        this.target = chooseSexDialogFragment;
        chooseSexDialogFragment.mWheelViewSex = (WheelView) Utils.findRequiredViewAsType(view, R.id.wheel_sex, "field 'mWheelViewSex'", WheelView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        ChooseSexDialogFragment chooseSexDialogFragment = this.target;
        if (chooseSexDialogFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        chooseSexDialogFragment.mWheelViewSex = null;
    }
}