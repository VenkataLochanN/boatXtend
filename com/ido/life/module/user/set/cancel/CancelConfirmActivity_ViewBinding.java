package com.ido.life.module.user.set.cancel;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.life.module.user.view.ViewMeGetCode;

/* JADX INFO: loaded from: classes3.dex */
public class CancelConfirmActivity_ViewBinding implements Unbinder {
    private CancelConfirmActivity target;
    private View view7f0a0776;
    private View view7f0a07f9;
    private View view7f0a097e;

    public CancelConfirmActivity_ViewBinding(CancelConfirmActivity cancelConfirmActivity) {
        this(cancelConfirmActivity, cancelConfirmActivity.getWindow().getDecorView());
    }

    public CancelConfirmActivity_ViewBinding(final CancelConfirmActivity cancelConfirmActivity, View view) {
        this.target = cancelConfirmActivity;
        cancelConfirmActivity.mTitleText = (TextView) Utils.findRequiredViewAsType(view, R.id.title_text, "field 'mTitleText'", TextView.class);
        cancelConfirmActivity.mTvAreaCode = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_area_code, "field 'mTvAreaCode'", TextView.class);
        cancelConfirmActivity.mTvPhone = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_phone, "field 'mTvPhone'", TextView.class);
        cancelConfirmActivity.mGetCodeView = (ViewMeGetCode) Utils.findRequiredViewAsType(view, R.id.view_get_code, "field 'mGetCodeView'", ViewMeGetCode.class);
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.tv_submit, "field 'mMeSubmit' and method 'toSubmit'");
        cancelConfirmActivity.mMeSubmit = (TextView) Utils.castView(viewFindRequiredView, R.id.tv_submit, "field 'mMeSubmit'", TextView.class);
        this.view7f0a097e = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.set.cancel.CancelConfirmActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                cancelConfirmActivity.toSubmit(view2);
            }
        });
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.tv_cancel, "field 'mMeCancel' and method 'toCancel'");
        cancelConfirmActivity.mMeCancel = (TextView) Utils.castView(viewFindRequiredView2, R.id.tv_cancel, "field 'mMeCancel'", TextView.class);
        this.view7f0a07f9 = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.set.cancel.CancelConfirmActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                cancelConfirmActivity.toCancel(view2);
            }
        });
        View viewFindRequiredView3 = Utils.findRequiredView(view, R.id.title_leftBtn, "method 'toBack'");
        this.view7f0a0776 = viewFindRequiredView3;
        viewFindRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.set.cancel.CancelConfirmActivity_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                cancelConfirmActivity.toBack(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        CancelConfirmActivity cancelConfirmActivity = this.target;
        if (cancelConfirmActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        cancelConfirmActivity.mTitleText = null;
        cancelConfirmActivity.mTvAreaCode = null;
        cancelConfirmActivity.mTvPhone = null;
        cancelConfirmActivity.mGetCodeView = null;
        cancelConfirmActivity.mMeSubmit = null;
        cancelConfirmActivity.mMeCancel = null;
        this.view7f0a097e.setOnClickListener(null);
        this.view7f0a097e = null;
        this.view7f0a07f9.setOnClickListener(null);
        this.view7f0a07f9 = null;
        this.view7f0a0776.setOnClickListener(null);
        this.view7f0a0776 = null;
    }
}