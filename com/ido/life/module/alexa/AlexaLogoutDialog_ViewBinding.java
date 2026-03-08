package com.ido.life.module.alexa;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes2.dex */
public class AlexaLogoutDialog_ViewBinding implements Unbinder {
    private AlexaLogoutDialog target;
    private View view7f0b0182;
    private View view7f0b0183;

    public AlexaLogoutDialog_ViewBinding(final AlexaLogoutDialog alexaLogoutDialog, View view) {
        this.target = alexaLogoutDialog;
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.tv_confirm, "field 'mTvConfirm' and method 'doClickConfirm'");
        alexaLogoutDialog.mTvConfirm = (TextView) Utils.castView(viewFindRequiredView, R.id.tv_confirm, "field 'mTvConfirm'", TextView.class);
        this.view7f0b0183 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.alexa.AlexaLogoutDialog_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                alexaLogoutDialog.doClickConfirm(view2);
            }
        });
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.tv_cancel, "field 'mTvCancel' and method 'doClickCancel'");
        alexaLogoutDialog.mTvCancel = (TextView) Utils.castView(viewFindRequiredView2, R.id.tv_cancel, "field 'mTvCancel'", TextView.class);
        this.view7f0b0182 = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.alexa.AlexaLogoutDialog_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                alexaLogoutDialog.doClickCancel(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        AlexaLogoutDialog alexaLogoutDialog = this.target;
        if (alexaLogoutDialog == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        alexaLogoutDialog.mTvConfirm = null;
        alexaLogoutDialog.mTvCancel = null;
        this.view7f0b0183.setOnClickListener(null);
        this.view7f0b0183 = null;
        this.view7f0b0182.setOnClickListener(null);
        this.view7f0b0182 = null;
    }
}