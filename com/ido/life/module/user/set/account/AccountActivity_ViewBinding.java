package com.ido.life.module.user.set.account;

import android.view.View;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes3.dex */
public class AccountActivity_ViewBinding implements Unbinder {
    private AccountActivity target;
    private View view7f0a0776;
    private View view7f0a07f9;
    private View view7f0a089e;
    private View view7f0a08c2;

    public AccountActivity_ViewBinding(AccountActivity accountActivity) {
        this(accountActivity, accountActivity.getWindow().getDecorView());
    }

    public AccountActivity_ViewBinding(final AccountActivity accountActivity, View view) {
        this.target = accountActivity;
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.title_leftBtn, "field 'mtitleLeftBtn' and method 'toBack'");
        accountActivity.mtitleLeftBtn = (ImageButton) Utils.castView(viewFindRequiredView, R.id.title_leftBtn, "field 'mtitleLeftBtn'", ImageButton.class);
        this.view7f0a0776 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.set.account.AccountActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                accountActivity.toBack(view2);
            }
        });
        accountActivity.mTitleText = (TextView) Utils.findRequiredViewAsType(view, R.id.title_text, "field 'mTitleText'", TextView.class);
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.tv_modify_pwd, "field 'mTvModifyPwd' and method 'toModify'");
        accountActivity.mTvModifyPwd = (TextView) Utils.castView(viewFindRequiredView2, R.id.tv_modify_pwd, "field 'mTvModifyPwd'", TextView.class);
        this.view7f0a08c2 = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.set.account.AccountActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                accountActivity.toModify(view2);
            }
        });
        View viewFindRequiredView3 = Utils.findRequiredView(view, R.id.tv_cancel, "field 'mTvCancel' and method 'toCancel'");
        accountActivity.mTvCancel = (TextView) Utils.castView(viewFindRequiredView3, R.id.tv_cancel, "field 'mTvCancel'", TextView.class);
        this.view7f0a07f9 = viewFindRequiredView3;
        viewFindRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.set.account.AccountActivity_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                accountActivity.toCancel(view2);
            }
        });
        accountActivity.mSwitchWechat = (Switch) Utils.findRequiredViewAsType(view, R.id.switch_wechat, "field 'mSwitchWechat'", Switch.class);
        accountActivity.mSwitchQq = (Switch) Utils.findRequiredViewAsType(view, R.id.switch_qq, "field 'mSwitchQq'", Switch.class);
        accountActivity.mSwitchFacebook = (Switch) Utils.findRequiredViewAsType(view, R.id.switch_facebook, "field 'mSwitchFacebook'", Switch.class);
        accountActivity.mSwitchNoticeTwitter = (Switch) Utils.findRequiredViewAsType(view, R.id.switch_twitter, "field 'mSwitchNoticeTwitter'", Switch.class);
        accountActivity.mSwitchGoogle = (Switch) Utils.findRequiredViewAsType(view, R.id.switch_google, "field 'mSwitchGoogle'", Switch.class);
        accountActivity.mTvAccount = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_account, "field 'mTvAccount'", TextView.class);
        View viewFindRequiredView4 = Utils.findRequiredView(view, R.id.tv_login_out, "method 'toLoginOut'");
        this.view7f0a089e = viewFindRequiredView4;
        viewFindRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.set.account.AccountActivity_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                accountActivity.toLoginOut(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        AccountActivity accountActivity = this.target;
        if (accountActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        accountActivity.mtitleLeftBtn = null;
        accountActivity.mTitleText = null;
        accountActivity.mTvModifyPwd = null;
        accountActivity.mTvCancel = null;
        accountActivity.mSwitchWechat = null;
        accountActivity.mSwitchQq = null;
        accountActivity.mSwitchFacebook = null;
        accountActivity.mSwitchNoticeTwitter = null;
        accountActivity.mSwitchGoogle = null;
        accountActivity.mTvAccount = null;
        this.view7f0a0776.setOnClickListener(null);
        this.view7f0a0776 = null;
        this.view7f0a08c2.setOnClickListener(null);
        this.view7f0a08c2 = null;
        this.view7f0a07f9.setOnClickListener(null);
        this.view7f0a07f9 = null;
        this.view7f0a089e.setOnClickListener(null);
        this.view7f0a089e = null;
    }
}