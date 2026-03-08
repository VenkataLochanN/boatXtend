package com.ido.life.module.user.login;

import android.view.View;
import android.widget.VideoView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.common.widget.textview.RegularTextView;

/* JADX INFO: loaded from: classes3.dex */
public class PreLoginAndRegisterActivity_ViewBinding implements Unbinder {
    private PreLoginAndRegisterActivity target;
    private View view7f0a0084;
    private View view7f0a0089;
    private View view7f0a04b0;
    private View view7f0a04b1;
    private View view7f0a04b4;
    private View view7f0a087b;

    public PreLoginAndRegisterActivity_ViewBinding(PreLoginAndRegisterActivity preLoginAndRegisterActivity) {
        this(preLoginAndRegisterActivity, preLoginAndRegisterActivity.getWindow().getDecorView());
    }

    public PreLoginAndRegisterActivity_ViewBinding(final PreLoginAndRegisterActivity preLoginAndRegisterActivity, View view) {
        this.target = preLoginAndRegisterActivity;
        preLoginAndRegisterActivity.mVideoView = (VideoView) Utils.findRequiredViewAsType(view, R.id.video_view, "field 'mVideoView'", VideoView.class);
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.tv_jump, "field 'mTvJump' and method 'toPersonalData'");
        preLoginAndRegisterActivity.mTvJump = (RegularTextView) Utils.castView(viewFindRequiredView, R.id.tv_jump, "field 'mTvJump'", RegularTextView.class);
        this.view7f0a087b = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.login.PreLoginAndRegisterActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                preLoginAndRegisterActivity.toPersonalData(view2);
            }
        });
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.btn_login, "field 'btnLogin' and method 'toLogin'");
        preLoginAndRegisterActivity.btnLogin = (RegularTextView) Utils.castView(viewFindRequiredView2, R.id.btn_login, "field 'btnLogin'", RegularTextView.class);
        this.view7f0a0084 = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.login.PreLoginAndRegisterActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                preLoginAndRegisterActivity.toLogin(view2);
            }
        });
        View viewFindRequiredView3 = Utils.findRequiredView(view, R.id.btn_register, "field 'btnRegister' and method 'toRegister'");
        preLoginAndRegisterActivity.btnRegister = (RegularTextView) Utils.castView(viewFindRequiredView3, R.id.btn_register, "field 'btnRegister'", RegularTextView.class);
        this.view7f0a0089 = viewFindRequiredView3;
        viewFindRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.login.PreLoginAndRegisterActivity_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                preLoginAndRegisterActivity.toRegister(view2);
            }
        });
        preLoginAndRegisterActivity.btnOtherLogin = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.tv_other_login, "field 'btnOtherLogin'", RegularTextView.class);
        View viewFindRequiredView4 = Utils.findRequiredView(view, R.id.login_twitter, "method 'doClickThirdLogin'");
        this.view7f0a04b4 = viewFindRequiredView4;
        viewFindRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.login.PreLoginAndRegisterActivity_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                preLoginAndRegisterActivity.doClickThirdLogin(view2);
            }
        });
        View viewFindRequiredView5 = Utils.findRequiredView(view, R.id.login_facebook, "method 'doClickThirdLogin'");
        this.view7f0a04b0 = viewFindRequiredView5;
        viewFindRequiredView5.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.login.PreLoginAndRegisterActivity_ViewBinding.5
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                preLoginAndRegisterActivity.doClickThirdLogin(view2);
            }
        });
        View viewFindRequiredView6 = Utils.findRequiredView(view, R.id.login_google, "method 'doClickThirdLogin'");
        this.view7f0a04b1 = viewFindRequiredView6;
        viewFindRequiredView6.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.login.PreLoginAndRegisterActivity_ViewBinding.6
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                preLoginAndRegisterActivity.doClickThirdLogin(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        PreLoginAndRegisterActivity preLoginAndRegisterActivity = this.target;
        if (preLoginAndRegisterActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        preLoginAndRegisterActivity.mVideoView = null;
        preLoginAndRegisterActivity.mTvJump = null;
        preLoginAndRegisterActivity.btnLogin = null;
        preLoginAndRegisterActivity.btnRegister = null;
        preLoginAndRegisterActivity.btnOtherLogin = null;
        this.view7f0a087b.setOnClickListener(null);
        this.view7f0a087b = null;
        this.view7f0a0084.setOnClickListener(null);
        this.view7f0a0084 = null;
        this.view7f0a0089.setOnClickListener(null);
        this.view7f0a0089 = null;
        this.view7f0a04b4.setOnClickListener(null);
        this.view7f0a04b4 = null;
        this.view7f0a04b0.setOnClickListener(null);
        this.view7f0a04b0 = null;
        this.view7f0a04b1.setOnClickListener(null);
        this.view7f0a04b1 = null;
    }
}