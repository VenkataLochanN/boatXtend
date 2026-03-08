package com.ido.life.dialog;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes2.dex */
public class AlexaSupportDialog_ViewBinding implements Unbinder {
    private AlexaSupportDialog target;
    private View view7f0a0880;
    private View view7f0a09d0;

    public AlexaSupportDialog_ViewBinding(final AlexaSupportDialog alexaSupportDialog, View view) {
        this.target = alexaSupportDialog;
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.tv_later_on, "field 'mTvCancel' and method 'doClickConfirm'");
        alexaSupportDialog.mTvCancel = (TextView) Utils.castView(viewFindRequiredView, R.id.tv_later_on, "field 'mTvCancel'", TextView.class);
        this.view7f0a0880 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.dialog.AlexaSupportDialog_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                alexaSupportDialog.doClickConfirm(view2);
            }
        });
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.tv_to_alexa, "field 'mTvConfirm' and method 'doClickCancel'");
        alexaSupportDialog.mTvConfirm = (TextView) Utils.castView(viewFindRequiredView2, R.id.tv_to_alexa, "field 'mTvConfirm'", TextView.class);
        this.view7f0a09d0 = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.dialog.AlexaSupportDialog_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                alexaSupportDialog.doClickCancel(view2);
            }
        });
        alexaSupportDialog.mTvMessage = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_message, "field 'mTvMessage'", TextView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        AlexaSupportDialog alexaSupportDialog = this.target;
        if (alexaSupportDialog == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        alexaSupportDialog.mTvCancel = null;
        alexaSupportDialog.mTvConfirm = null;
        alexaSupportDialog.mTvMessage = null;
        this.view7f0a0880.setOnClickListener(null);
        this.view7f0a0880 = null;
        this.view7f0a09d0.setOnClickListener(null);
        this.view7f0a09d0 = null;
    }
}