package com.ido.life.module.user.bindsetpassword;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.module.user.view.ViewMeGetCode;

/* JADX INFO: loaded from: classes3.dex */
public class BindInputCodeActivity_ViewBinding implements Unbinder {
    private BindInputCodeActivity target;
    private View view7f0a04e1;
    private View view7f0a0776;

    public BindInputCodeActivity_ViewBinding(BindInputCodeActivity bindInputCodeActivity) {
        this(bindInputCodeActivity, bindInputCodeActivity.getWindow().getDecorView());
    }

    public BindInputCodeActivity_ViewBinding(final BindInputCodeActivity bindInputCodeActivity, View view) {
        this.target = bindInputCodeActivity;
        bindInputCodeActivity.mTvReset = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.title_reset, "field 'mTvReset'", RegularTextView.class);
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.title_leftBtn, "field 'mtitleLeftBtn' and method 'toBack'");
        bindInputCodeActivity.mtitleLeftBtn = (ImageButton) Utils.castView(viewFindRequiredView, R.id.title_leftBtn, "field 'mtitleLeftBtn'", ImageButton.class);
        this.view7f0a0776 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.bindsetpassword.BindInputCodeActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                bindInputCodeActivity.toBack(view2);
            }
        });
        bindInputCodeActivity.mGetCodeView = (ViewMeGetCode) Utils.findRequiredViewAsType(view, R.id.get_code_view, "field 'mGetCodeView'", ViewMeGetCode.class);
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.me_submit_bind_account, "field 'mMeSubmit' and method 'toThirdLoginAndBindUnRegister'");
        bindInputCodeActivity.mMeSubmit = (TextView) Utils.castView(viewFindRequiredView2, R.id.me_submit_bind_account, "field 'mMeSubmit'", TextView.class);
        this.view7f0a04e1 = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.bindsetpassword.BindInputCodeActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                bindInputCodeActivity.toThirdLoginAndBindUnRegister(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        BindInputCodeActivity bindInputCodeActivity = this.target;
        if (bindInputCodeActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        bindInputCodeActivity.mTvReset = null;
        bindInputCodeActivity.mtitleLeftBtn = null;
        bindInputCodeActivity.mGetCodeView = null;
        bindInputCodeActivity.mMeSubmit = null;
        this.view7f0a0776.setOnClickListener(null);
        this.view7f0a0776 = null;
        this.view7f0a04e1.setOnClickListener(null);
        this.view7f0a04e1 = null;
    }
}