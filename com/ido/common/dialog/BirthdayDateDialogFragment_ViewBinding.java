package com.ido.common.dialog;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.ido.common.R;
import com.watch.life.wheelview.view.WheelView;

/* JADX INFO: loaded from: classes2.dex */
public class BirthdayDateDialogFragment_ViewBinding implements Unbinder {
    private BirthdayDateDialogFragment target;
    private View view7f0b0182;
    private View view7f0b0183;

    public BirthdayDateDialogFragment_ViewBinding(final BirthdayDateDialogFragment birthdayDateDialogFragment, View view) {
        this.target = birthdayDateDialogFragment;
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.tv_cancel, "field 'mTvCancel' and method 'toCancel'");
        birthdayDateDialogFragment.mTvCancel = (TextView) Utils.castView(viewFindRequiredView, R.id.tv_cancel, "field 'mTvCancel'", TextView.class);
        this.view7f0b0182 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.common.dialog.BirthdayDateDialogFragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                birthdayDateDialogFragment.toCancel(view2);
            }
        });
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.tv_confirm, "field 'mTvConfirm' and method 'toConfirm'");
        birthdayDateDialogFragment.mTvConfirm = (TextView) Utils.castView(viewFindRequiredView2, R.id.tv_confirm, "field 'mTvConfirm'", TextView.class);
        this.view7f0b0183 = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.common.dialog.BirthdayDateDialogFragment_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                birthdayDateDialogFragment.toConfirm(view2);
            }
        });
        birthdayDateDialogFragment.mWheelYear = (WheelView) Utils.findRequiredViewAsType(view, R.id.wheel_year, "field 'mWheelYear'", WheelView.class);
        birthdayDateDialogFragment.mWheelMonth = (WheelView) Utils.findRequiredViewAsType(view, R.id.wheel_month, "field 'mWheelMonth'", WheelView.class);
        birthdayDateDialogFragment.mWheelDay = (WheelView) Utils.findRequiredViewAsType(view, R.id.wheel_day, "field 'mWheelDay'", WheelView.class);
        birthdayDateDialogFragment.mTvDesc = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_desc, "field 'mTvDesc'", TextView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        BirthdayDateDialogFragment birthdayDateDialogFragment = this.target;
        if (birthdayDateDialogFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        birthdayDateDialogFragment.mTvCancel = null;
        birthdayDateDialogFragment.mTvConfirm = null;
        birthdayDateDialogFragment.mWheelYear = null;
        birthdayDateDialogFragment.mWheelMonth = null;
        birthdayDateDialogFragment.mWheelDay = null;
        birthdayDateDialogFragment.mTvDesc = null;
        this.view7f0b0182.setOnClickListener(null);
        this.view7f0b0182 = null;
        this.view7f0b0183.setOnClickListener(null);
        this.view7f0b0183 = null;
    }
}