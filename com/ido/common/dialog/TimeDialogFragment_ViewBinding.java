package com.ido.common.dialog;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.ido.common.R;
import com.watch.life.wheelview.view.WheelView;

/* JADX INFO: loaded from: classes2.dex */
public class TimeDialogFragment_ViewBinding implements Unbinder {
    private TimeDialogFragment target;
    private View view7f0b0182;
    private View view7f0b0183;

    public TimeDialogFragment_ViewBinding(final TimeDialogFragment timeDialogFragment, View view) {
        this.target = timeDialogFragment;
        timeDialogFragment.mWheelTimeFormat = (WheelView) Utils.findRequiredViewAsType(view, R.id.wheel_time_format, "field 'mWheelTimeFormat'", WheelView.class);
        timeDialogFragment.mWheelHour = (WheelView) Utils.findRequiredViewAsType(view, R.id.wheel_hour, "field 'mWheelHour'", WheelView.class);
        timeDialogFragment.mWheelMinuter = (WheelView) Utils.findRequiredViewAsType(view, R.id.wheel_minuter, "field 'mWheelMinuter'", WheelView.class);
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.tv_cancel, "field 'mTvCancel' and method 'onCancelClicked'");
        timeDialogFragment.mTvCancel = (TextView) Utils.castView(viewFindRequiredView, R.id.tv_cancel, "field 'mTvCancel'", TextView.class);
        this.view7f0b0182 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.common.dialog.TimeDialogFragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                timeDialogFragment.onCancelClicked();
            }
        });
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.tv_confirm, "field 'mTvConfirm' and method 'onConfirmClicked'");
        timeDialogFragment.mTvConfirm = (TextView) Utils.castView(viewFindRequiredView2, R.id.tv_confirm, "field 'mTvConfirm'", TextView.class);
        this.view7f0b0183 = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.common.dialog.TimeDialogFragment_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                timeDialogFragment.onConfirmClicked();
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        TimeDialogFragment timeDialogFragment = this.target;
        if (timeDialogFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        timeDialogFragment.mWheelTimeFormat = null;
        timeDialogFragment.mWheelHour = null;
        timeDialogFragment.mWheelMinuter = null;
        timeDialogFragment.mTvCancel = null;
        timeDialogFragment.mTvConfirm = null;
        this.view7f0b0182.setOnClickListener(null);
        this.view7f0b0182 = null;
        this.view7f0b0183.setOnClickListener(null);
        this.view7f0b0183 = null;
    }
}