package com.ido.life.module.bind;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.common.widget.textview.MediumTextView;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.customview.DotView;
import com.ido.life.customview.authcodeview.AuthCodeView;

/* JADX INFO: loaded from: classes2.dex */
public class BindActivity_ViewBinding implements Unbinder {
    private BindActivity target;
    private View view7f0a0656;
    private View view7f0a0691;
    private View view7f0a08d4;

    public BindActivity_ViewBinding(BindActivity bindActivity) {
        this(bindActivity, bindActivity.getWindow().getDecorView());
    }

    public BindActivity_ViewBinding(final BindActivity bindActivity, View view) {
        this.target = bindActivity;
        bindActivity.mIvStatus = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_status, "field 'mIvStatus'", ImageView.class);
        bindActivity.mTvStatus = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_status, "field 'mTvStatus'", TextView.class);
        bindActivity.mTvBindTip = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_bind_tip, "field 'mTvBindTip'", TextView.class);
        bindActivity.mLayoutBindFailed = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_bind_failed, "field 'mLayoutBindFailed'", LinearLayout.class);
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.rtv_finish, "field 'mRtvFinish' and method 'onViewClicked'");
        bindActivity.mRtvFinish = (RegularTextView) Utils.castView(viewFindRequiredView, R.id.rtv_finish, "field 'mRtvFinish'", RegularTextView.class);
        this.view7f0a0656 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.bind.BindActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                bindActivity.onViewClicked(view2);
            }
        });
        bindActivity.mDotView = (DotView) Utils.findRequiredViewAsType(view, R.id.dot_view, "field 'mDotView'", DotView.class);
        bindActivity.mAuthCodeView = (AuthCodeView) Utils.findRequiredViewAsType(view, R.id.auth_code_view, "field 'mAuthCodeView'", AuthCodeView.class);
        bindActivity.mTvAuthCodeError = (MediumTextView) Utils.findRequiredViewAsType(view, R.id.tv_auth_code_error, "field 'mTvAuthCodeError'", MediumTextView.class);
        bindActivity.mLayoutInVerification = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_in_verification, "field 'mLayoutInVerification'", LinearLayout.class);
        bindActivity.mLayoutAuthCode = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_auth_code, "field 'mLayoutAuthCode'", LinearLayout.class);
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.rtv_retry, "method 'onViewClicked'");
        this.view7f0a0691 = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.bind.BindActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                bindActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView3 = Utils.findRequiredView(view, R.id.tv_not_bind, "method 'onViewClicked'");
        this.view7f0a08d4 = viewFindRequiredView3;
        viewFindRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.bind.BindActivity_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                bindActivity.onViewClicked(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        BindActivity bindActivity = this.target;
        if (bindActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        bindActivity.mIvStatus = null;
        bindActivity.mTvStatus = null;
        bindActivity.mTvBindTip = null;
        bindActivity.mLayoutBindFailed = null;
        bindActivity.mRtvFinish = null;
        bindActivity.mDotView = null;
        bindActivity.mAuthCodeView = null;
        bindActivity.mTvAuthCodeError = null;
        bindActivity.mLayoutInVerification = null;
        bindActivity.mLayoutAuthCode = null;
        this.view7f0a0656.setOnClickListener(null);
        this.view7f0a0656 = null;
        this.view7f0a0691.setOnClickListener(null);
        this.view7f0a0691 = null;
        this.view7f0a08d4.setOnClickListener(null);
        this.view7f0a08d4 = null;
    }
}