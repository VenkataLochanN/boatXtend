package com.ido.life.dialog;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.watch.life.wheelview.view.WheelView;

/* JADX INFO: loaded from: classes2.dex */
public class SportTargetDialogFragment_ViewBinding implements Unbinder {
    private SportTargetDialogFragment target;
    private View view7f0b0182;
    private View view7f0b0183;

    public SportTargetDialogFragment_ViewBinding(final SportTargetDialogFragment sportTargetDialogFragment, View view) {
        this.target = sportTargetDialogFragment;
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.tv_cancel, "field 'mTvCancel' and method 'toCancel'");
        sportTargetDialogFragment.mTvCancel = (TextView) Utils.castView(viewFindRequiredView, R.id.tv_cancel, "field 'mTvCancel'", TextView.class);
        this.view7f0b0182 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.dialog.SportTargetDialogFragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                sportTargetDialogFragment.toCancel(view2);
            }
        });
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.tv_confirm, "field 'mTvConfirm' and method 'toConfirm'");
        sportTargetDialogFragment.mTvConfirm = (TextView) Utils.castView(viewFindRequiredView2, R.id.tv_confirm, "field 'mTvConfirm'", TextView.class);
        this.view7f0b0183 = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.dialog.SportTargetDialogFragment_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                sportTargetDialogFragment.toConfirm(view2);
            }
        });
        sportTargetDialogFragment.mWheeTab = (WheelView) Utils.findRequiredViewAsType(view, R.id.wheel_tab, "field 'mWheeTab'", WheelView.class);
        sportTargetDialogFragment.mWheelValue = (WheelView) Utils.findRequiredViewAsType(view, R.id.wheel_value, "field 'mWheelValue'", WheelView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        SportTargetDialogFragment sportTargetDialogFragment = this.target;
        if (sportTargetDialogFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        sportTargetDialogFragment.mTvCancel = null;
        sportTargetDialogFragment.mTvConfirm = null;
        sportTargetDialogFragment.mWheeTab = null;
        sportTargetDialogFragment.mWheelValue = null;
        this.view7f0b0182.setOnClickListener(null);
        this.view7f0b0182 = null;
        this.view7f0b0183.setOnClickListener(null);
        this.view7f0b0183 = null;
    }
}