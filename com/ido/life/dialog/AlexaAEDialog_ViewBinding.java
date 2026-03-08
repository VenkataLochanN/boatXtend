package com.ido.life.dialog;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes2.dex */
public class AlexaAEDialog_ViewBinding implements Unbinder {
    private AlexaAEDialog target;
    private View view7f0a063e;

    public AlexaAEDialog_ViewBinding(final AlexaAEDialog alexaAEDialog, View view) {
        this.target = alexaAEDialog;
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.rtv_btn, "method 'onClick'");
        this.view7f0a063e = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.dialog.AlexaAEDialog_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                alexaAEDialog.onClick(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        if (this.target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        this.view7f0a063e.setOnClickListener(null);
        this.view7f0a063e = null;
    }
}