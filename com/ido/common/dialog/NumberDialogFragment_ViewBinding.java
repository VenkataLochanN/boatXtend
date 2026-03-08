package com.ido.common.dialog;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.ido.common.R;
import com.ido.common.widget.view.CustomPickerView;

/* JADX INFO: loaded from: classes2.dex */
public class NumberDialogFragment_ViewBinding implements Unbinder {
    private NumberDialogFragment target;
    private View view7f0b0182;
    private View view7f0b0183;

    public NumberDialogFragment_ViewBinding(final NumberDialogFragment numberDialogFragment, View view) {
        this.target = numberDialogFragment;
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.tv_cancel, "field 'mTvCancel' and method 'toCancel'");
        numberDialogFragment.mTvCancel = (TextView) Utils.castView(viewFindRequiredView, R.id.tv_cancel, "field 'mTvCancel'", TextView.class);
        this.view7f0b0182 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.common.dialog.NumberDialogFragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                numberDialogFragment.toCancel(view2);
            }
        });
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.tv_confirm, "field 'mTvConfirm' and method 'toConfirm'");
        numberDialogFragment.mTvConfirm = (TextView) Utils.castView(viewFindRequiredView2, R.id.tv_confirm, "field 'mTvConfirm'", TextView.class);
        this.view7f0b0183 = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.common.dialog.NumberDialogFragment_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                numberDialogFragment.toConfirm(view2);
            }
        });
        numberDialogFragment.mCustomPickerView = (CustomPickerView) Utils.findRequiredViewAsType(view, R.id.custom_picker, "field 'mCustomPickerView'", CustomPickerView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        NumberDialogFragment numberDialogFragment = this.target;
        if (numberDialogFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        numberDialogFragment.mTvCancel = null;
        numberDialogFragment.mTvConfirm = null;
        numberDialogFragment.mCustomPickerView = null;
        this.view7f0b0182.setOnClickListener(null);
        this.view7f0b0182 = null;
        this.view7f0b0183.setOnClickListener(null);
        this.view7f0b0183 = null;
    }
}