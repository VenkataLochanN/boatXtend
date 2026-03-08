package com.ido.life.dialog;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes2.dex */
public class ChoosePhotoDialogFragment_ViewBinding implements Unbinder {
    private ChoosePhotoDialogFragment target;
    private View view7f0a07f9;
    private View view7f0a080e;

    public ChoosePhotoDialogFragment_ViewBinding(final ChoosePhotoDialogFragment choosePhotoDialogFragment, View view) {
        this.target = choosePhotoDialogFragment;
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.tv_cancel, "field 'mTvCancel' and method 'doClickCancel'");
        choosePhotoDialogFragment.mTvCancel = (TextView) Utils.castView(viewFindRequiredView, R.id.tv_cancel, "field 'mTvCancel'", TextView.class);
        this.view7f0a07f9 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.dialog.ChoosePhotoDialogFragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                choosePhotoDialogFragment.doClickCancel(view2);
            }
        });
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.tv_confirm, "field 'mTvConfirm' and method 'doClickConfirm'");
        choosePhotoDialogFragment.mTvConfirm = (TextView) Utils.castView(viewFindRequiredView2, R.id.tv_confirm, "field 'mTvConfirm'", TextView.class);
        this.view7f0a080e = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.dialog.ChoosePhotoDialogFragment_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                choosePhotoDialogFragment.doClickConfirm(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        ChoosePhotoDialogFragment choosePhotoDialogFragment = this.target;
        if (choosePhotoDialogFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        choosePhotoDialogFragment.mTvCancel = null;
        choosePhotoDialogFragment.mTvConfirm = null;
        this.view7f0a07f9.setOnClickListener(null);
        this.view7f0a07f9 = null;
        this.view7f0a080e.setOnClickListener(null);
        this.view7f0a080e = null;
    }
}