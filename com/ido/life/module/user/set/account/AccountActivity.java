package com.ido.life.module.user.set.account;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.common.base.BaseCoreActivity;
import com.ido.common.dialog.CommBottomConfirmDialog;
import com.ido.common.dialog.WaitingDialog;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.ResourceUtil;
import com.ido.common.utils.StatusBarUtil;
import com.ido.life.VeryFitApp;
import com.ido.life.customview.NormalToast;
import com.ido.life.module.user.set.account.AccountContract;
import com.ido.life.module.user.set.cancel.CancelActivity;
import com.ido.life.module.user.set.modify.ModifyPwdActivity;

/* JADX INFO: loaded from: classes3.dex */
public class AccountActivity extends BaseCoreActivity implements AccountContract.View {
    public static final int REQUEST_CODE = 1001;
    public static final int RESULT_CODE = 1002;
    private boolean isTouch = false;
    private AccountContract.Presenter mPresenter;

    @BindView(R.id.switch_facebook)
    Switch mSwitchFacebook;

    @BindView(R.id.switch_google)
    Switch mSwitchGoogle;

    @BindView(R.id.switch_twitter)
    Switch mSwitchNoticeTwitter;

    @BindView(R.id.switch_qq)
    Switch mSwitchQq;

    @BindView(R.id.switch_wechat)
    Switch mSwitchWechat;

    @BindView(R.id.title_text)
    TextView mTitleText;

    @BindView(R.id.tv_account)
    TextView mTvAccount;

    @BindView(R.id.tv_cancel)
    TextView mTvCancel;

    @BindView(R.id.tv_modify_pwd)
    TextView mTvModifyPwd;

    @BindView(R.id.title_leftBtn)
    ImageButton mtitleLeftBtn;

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_acount;
    }

    public static void startActivity(Activity activity) {
        activity.startActivity(new Intent(activity, (Class<?>) AccountActivity.class));
    }

    public static void startActivityForResult(Activity activity) {
        activity.startActivityForResult(new Intent(activity, (Class<?>) AccountActivity.class), 1001);
    }

    @Override // com.ido.common.base.BaseCoreActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initTitleBar();
        initView();
        initListener();
    }

    private void initListener() {
        this.mSwitchQq.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.ido.life.module.user.set.account.AccountActivity.1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    AccountActivity.this.mPresenter.getOpenId(AccountPresenter.thirdNames[1]);
                } else {
                    AccountActivity.this.mPresenter.cancelThirdAccount(AccountPresenter.thirdNames[1]);
                }
            }
        });
        this.mSwitchFacebook.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.ido.life.module.user.set.account.AccountActivity.2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    AccountActivity.this.mPresenter.getOpenId(AccountPresenter.thirdNames[2]);
                } else {
                    AccountActivity.this.mPresenter.cancelThirdAccount(AccountPresenter.thirdNames[2]);
                }
            }
        });
        this.mSwitchNoticeTwitter.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.ido.life.module.user.set.account.AccountActivity.3
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    AccountActivity.this.mPresenter.getOpenId(AccountPresenter.thirdNames[3]);
                } else {
                    AccountActivity.this.mPresenter.cancelThirdAccount(AccountPresenter.thirdNames[3]);
                }
            }
        });
        this.mSwitchGoogle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.ido.life.module.user.set.account.AccountActivity.4
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    AccountActivity.this.mPresenter.getOpenId(AccountPresenter.thirdNames[4]);
                } else {
                    AccountActivity.this.mPresenter.cancelThirdAccount(AccountPresenter.thirdNames[4]);
                }
            }
        });
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        this.mPresenter = new AccountPresenter(this);
        this.mPresenter.initData();
        this.mPresenter.queryThirdAccount();
    }

    private void initView() {
        this.mTitleText.setText(ResourceUtil.getString(R.string.mine_account_set));
    }

    private void initTitleBar() {
        StatusBarUtil.StatusBarLightMode(this);
    }

    @OnClick({R.id.tv_cancel})
    public void toCancel(View view) {
        CancelActivity.startActivityForResult(this);
    }

    @OnClick({R.id.tv_modify_pwd})
    public void toModify(View view) {
        ModifyPwdActivity.startActivity(this);
    }

    @OnClick({R.id.title_leftBtn})
    public void toBack(View view) {
        ActivityCompat.finishAfterTransition(this);
    }

    @Override // com.ido.life.module.user.set.account.AccountContract.View
    public void showUserAccount(String str) {
        this.mTvAccount.setText(str);
    }

    @Override // com.ido.life.module.user.set.account.AccountContract.View
    public void goBack() {
        ActivityCompat.finishAfterTransition(this);
    }

    @Override // com.ido.life.module.user.set.account.AccountContract.View
    public void showLoading() {
        WaitingDialog.showDialog(this);
    }

    @Override // com.ido.life.module.user.set.account.AccountContract.View
    public void hideLoading() {
        WaitingDialog.hideDialog();
    }

    @Override // com.ido.life.module.user.set.account.AccountContract.View
    public void showMessage(String str) {
        NormalToast.showToast(str);
    }

    @Override // com.ido.life.module.user.set.account.AccountContract.View
    public void setCheckWeChat(boolean z) {
        this.mSwitchWechat.setOnCheckedChangeListener(null);
        this.mSwitchWechat.setChecked(z);
        this.mSwitchWechat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.ido.life.module.user.set.account.AccountActivity.5
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z2) {
                if (z2) {
                    AccountActivity.this.mPresenter.getOpenId(AccountPresenter.thirdNames[0]);
                } else {
                    AccountActivity.this.mPresenter.cancelThirdAccount(AccountPresenter.thirdNames[0]);
                }
            }
        });
    }

    @Override // com.ido.life.module.user.set.account.AccountContract.View
    public void setCheckQQ(boolean z) {
        this.mSwitchQq.setOnCheckedChangeListener(null);
        this.mSwitchQq.setChecked(z);
        this.mSwitchQq.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.ido.life.module.user.set.account.AccountActivity.6
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z2) {
                if (z2) {
                    AccountActivity.this.mPresenter.getOpenId(AccountPresenter.thirdNames[1]);
                } else {
                    AccountActivity.this.mPresenter.cancelThirdAccount(AccountPresenter.thirdNames[1]);
                }
            }
        });
    }

    @Override // com.ido.life.module.user.set.account.AccountContract.View
    public void setCheckFacebook(boolean z) {
        this.mSwitchFacebook.setChecked(z);
    }

    @Override // com.ido.life.module.user.set.account.AccountContract.View
    public void setCheckTwitter(boolean z) {
        this.mSwitchNoticeTwitter.setChecked(z);
    }

    @Override // com.ido.life.module.user.set.account.AccountContract.View
    public void setCheckGoogle(boolean z) {
        this.mSwitchGoogle.setChecked(z);
    }

    @OnClick({R.id.tv_login_out})
    public void toLoginOut(View view) {
        final CommBottomConfirmDialog commBottomConfirmDialogNewInstance = CommBottomConfirmDialog.newInstance(ResourceUtil.getString(R.string.me_are_sure_exit_ios), ResourceUtil.getString(R.string.public_tip_confirm), ResourceUtil.getString(R.string.public_tip_cancel), true);
        commBottomConfirmDialogNewInstance.show(getSupportFragmentManager());
        commBottomConfirmDialogNewInstance.setCancelListener(new View.OnClickListener() { // from class: com.ido.life.module.user.set.account.AccountActivity.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                commBottomConfirmDialogNewInstance.dismissAllowingStateLoss();
            }
        });
        commBottomConfirmDialogNewInstance.setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.user.set.account.AccountActivity.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                commBottomConfirmDialogNewInstance.dismissAllowingStateLoss();
                AccountActivity.this.mPresenter.loginOut();
            }
        });
    }

    @Override // com.ido.life.module.user.set.account.AccountContract.View
    public void loginOutSuccess() {
        VeryFitApp.getApp().quitClearCacheThread(new VeryFitApp.ClearCacheCallBack() { // from class: com.ido.life.module.user.set.account.AccountActivity.9
            @Override // com.ido.life.VeryFitApp.ClearCacheCallBack
            public void clearSuccess() {
                AccountActivity.this.toGuide();
            }
        });
    }

    @Override // com.ido.life.module.user.set.account.AccountContract.View
    public void loginOutFailed() {
        CommonLogUtil.d("退出登录失败");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toGuide() {
        setResult(1002, new Intent());
        goBack();
    }

    @Override // com.ido.common.base.BaseView
    public void setPresenter(AccountContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1001 && i2 == 1002) {
            setResult(1002, new Intent());
            ActivityCompat.finishAfterTransition(this);
        }
    }
}