package com.ido.life.module.user.set.cancel;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes3.dex */
public class CancelActivity_ViewBinding implements Unbinder {
    private CancelActivity target;
    private View view7f0a0776;
    private View view7f0a07fa;

    public CancelActivity_ViewBinding(CancelActivity cancelActivity) {
        this(cancelActivity, cancelActivity.getWindow().getDecorView());
    }

    public CancelActivity_ViewBinding(final CancelActivity cancelActivity, View view) {
        this.target = cancelActivity;
        cancelActivity.mTitleText = (TextView) Utils.findRequiredViewAsType(view, R.id.title_text, "field 'mTitleText'", TextView.class);
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.tv_cancel_apply, "method 'toApplyCancel'");
        this.view7f0a07fa = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.set.cancel.CancelActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                cancelActivity.toApplyCancel(view2);
            }
        });
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.title_leftBtn, "method 'toBack'");
        this.view7f0a0776 = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.set.cancel.CancelActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                cancelActivity.toBack(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        CancelActivity cancelActivity = this.target;
        if (cancelActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        cancelActivity.mTitleText = null;
        this.view7f0a07fa.setOnClickListener(null);
        this.view7f0a07fa = null;
        this.view7f0a0776.setOnClickListener(null);
        this.view7f0a0776 = null;
    }
}