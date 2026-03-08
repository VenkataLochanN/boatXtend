package com.ido.life.dialog;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes2.dex */
public class ChooseBloodReminderDialogFragment_ViewBinding implements Unbinder {
    private ChooseBloodReminderDialogFragment target;
    private View view7f0b0182;
    private View view7f0b0183;

    public ChooseBloodReminderDialogFragment_ViewBinding(final ChooseBloodReminderDialogFragment chooseBloodReminderDialogFragment, View view) {
        this.target = chooseBloodReminderDialogFragment;
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.tv_cancel, "method 'onViewClicked'");
        this.view7f0b0182 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.dialog.ChooseBloodReminderDialogFragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                chooseBloodReminderDialogFragment.onViewClicked(view2);
            }
        });
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.tv_confirm, "method 'onViewClicked'");
        this.view7f0b0183 = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.dialog.ChooseBloodReminderDialogFragment_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                chooseBloodReminderDialogFragment.onViewClicked(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        if (this.target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        this.view7f0b0182.setOnClickListener(null);
        this.view7f0b0182 = null;
        this.view7f0b0183.setOnClickListener(null);
        this.view7f0b0183 = null;
    }
}