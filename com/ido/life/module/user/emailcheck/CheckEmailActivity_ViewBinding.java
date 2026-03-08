package com.ido.life.module.user.emailcheck;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.life.module.user.view.ViewMeGetCode;

/* JADX INFO: loaded from: classes3.dex */
public class CheckEmailActivity_ViewBinding implements Unbinder {
    private CheckEmailActivity target;
    private View view7f0a0776;
    private View view7f0a0778;
    private View view7f0a097e;

    public CheckEmailActivity_ViewBinding(CheckEmailActivity checkEmailActivity) {
        this(checkEmailActivity, checkEmailActivity.getWindow().getDecorView());
    }

    public CheckEmailActivity_ViewBinding(final CheckEmailActivity checkEmailActivity, View view) {
        this.target = checkEmailActivity;
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.title_leftBtn, "field 'mLeftBtn' and method 'toBack'");
        checkEmailActivity.mLeftBtn = (ImageButton) Utils.castView(viewFindRequiredView, R.id.title_leftBtn, "field 'mLeftBtn'", ImageButton.class);
        this.view7f0a0776 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.emailcheck.CheckEmailActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                checkEmailActivity.toBack(view2);
            }
        });
        checkEmailActivity.mTitleText = (TextView) Utils.findRequiredViewAsType(view, R.id.title_text, "field 'mTitleText'", TextView.class);
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.title_rightBtn, "field 'mRightBtn' and method 'toJump'");
        checkEmailActivity.mRightBtn = (Button) Utils.castView(viewFindRequiredView2, R.id.title_rightBtn, "field 'mRightBtn'", Button.class);
        this.view7f0a0778 = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.emailcheck.CheckEmailActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                checkEmailActivity.toJump(view2);
            }
        });
        checkEmailActivity.mTipText = (TextView) Utils.findRequiredViewAsType(view, R.id.check_email_tip, "field 'mTipText'", TextView.class);
        checkEmailActivity.mGetCodeView = (ViewMeGetCode) Utils.findRequiredViewAsType(view, R.id.view_get_code, "field 'mGetCodeView'", ViewMeGetCode.class);
        View viewFindRequiredView3 = Utils.findRequiredView(view, R.id.tv_submit, "field 'mMeSubmit' and method 'toSubmit'");
        checkEmailActivity.mMeSubmit = (TextView) Utils.castView(viewFindRequiredView3, R.id.tv_submit, "field 'mMeSubmit'", TextView.class);
        this.view7f0a097e = viewFindRequiredView3;
        viewFindRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.emailcheck.CheckEmailActivity_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                checkEmailActivity.toSubmit(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        CheckEmailActivity checkEmailActivity = this.target;
        if (checkEmailActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        checkEmailActivity.mLeftBtn = null;
        checkEmailActivity.mTitleText = null;
        checkEmailActivity.mRightBtn = null;
        checkEmailActivity.mTipText = null;
        checkEmailActivity.mGetCodeView = null;
        checkEmailActivity.mMeSubmit = null;
        this.view7f0a0776.setOnClickListener(null);
        this.view7f0a0776 = null;
        this.view7f0a0778.setOnClickListener(null);
        this.view7f0a0778 = null;
        this.view7f0a097e.setOnClickListener(null);
        this.view7f0a097e = null;
    }
}