package com.ido.common.dialog;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.ido.common.R;
import com.ido.common.widget.textview.RegularTextView;
import com.watch.life.wheelview.view.WheelView;

/* JADX INFO: loaded from: classes2.dex */
public class CommSelectDialogFragment_ViewBinding implements Unbinder {
    private CommSelectDialogFragment target;
    private View view7f0b0182;
    private View view7f0b0183;

    public CommSelectDialogFragment_ViewBinding(final CommSelectDialogFragment commSelectDialogFragment, View view) {
        this.target = commSelectDialogFragment;
        commSelectDialogFragment.mRtvUnit = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.rtv_unit, "field 'mRtvUnit'", RegularTextView.class);
        commSelectDialogFragment.mWheelView = (WheelView) Utils.findRequiredViewAsType(view, R.id.wheel_view, "field 'mWheelView'", WheelView.class);
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.tv_cancel, "field 'mTvCancel' and method 'onViewClicked'");
        commSelectDialogFragment.mTvCancel = (TextView) Utils.castView(viewFindRequiredView, R.id.tv_cancel, "field 'mTvCancel'", TextView.class);
        this.view7f0b0182 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.common.dialog.CommSelectDialogFragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                commSelectDialogFragment.onViewClicked(view2);
            }
        });
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.tv_confirm, "field 'mTvConfirm' and method 'onViewClicked'");
        commSelectDialogFragment.mTvConfirm = (TextView) Utils.castView(viewFindRequiredView2, R.id.tv_confirm, "field 'mTvConfirm'", TextView.class);
        this.view7f0b0183 = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.common.dialog.CommSelectDialogFragment_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                commSelectDialogFragment.onViewClicked(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        CommSelectDialogFragment commSelectDialogFragment = this.target;
        if (commSelectDialogFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        commSelectDialogFragment.mRtvUnit = null;
        commSelectDialogFragment.mWheelView = null;
        commSelectDialogFragment.mTvCancel = null;
        commSelectDialogFragment.mTvConfirm = null;
        this.view7f0b0182.setOnClickListener(null);
        this.view7f0b0182 = null;
        this.view7f0b0183.setOnClickListener(null);
        this.view7f0b0183 = null;
    }
}