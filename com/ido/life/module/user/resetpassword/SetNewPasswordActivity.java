package com.ido.life.module.user.resetpassword;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.common.IdoApp;
import com.ido.common.dialog.WaitingDialog;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.PassWordInputFilter;
import com.ido.common.utils.StatusBarUtil;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.base.BaseActivity;
import com.ido.life.customview.NormalToast;
import com.ido.life.database.model.UserInfo;
import com.ido.life.module.user.login.PreLoginAndRegisterActivity;
import com.ido.life.module.user.resetpassword.SetNewPasswordContract;
import com.ido.life.module.user.view.OnTextChangedListener;
import com.ido.life.module.user.view.ViewMePassword;

/* JADX INFO: loaded from: classes3.dex */
public class SetNewPasswordActivity extends BaseActivity implements SetNewPasswordContract.View {
    public static final String ACCOUNT = "account";
    public static final String EXTRA_USER = "extra_user";
    public static final int REQUEST_CODE = 1002;
    public static final int RESULT_CODE = 1003;
    private static final String TAG = "ResetPassActivity";
    public static final String VERIFYCODE = "verifyCode";
    private String mAccount;
    private String mCountryCode = "86";

    @BindView(R.id.me_submit)
    TextView mMeSubmit;

    @BindView(R.id.reset_password_first)
    ViewMePassword mPasswordViewFirst;

    @BindView(R.id.reset_password_second)
    ViewMePassword mPasswordViewSecond;
    private SetNewPasswordContract.Presenter mPresenter;

    @BindView(R.id.tv_password_tip)
    TextView mTvPasswordTip;

    @BindView(R.id.title_reset)
    RegularTextView mTvReset;
    private String mVerifyCode;

    @BindView(R.id.title_leftBtn)
    ImageButton mtitleLeftBtn;

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_set_new_password;
    }

    public static void startActivity(Activity activity, String str) {
        Intent intent = new Intent(activity, (Class<?>) SetNewPasswordActivity.class);
        intent.putExtra("account", str);
        activity.startActivity(intent);
    }

    public static void startActivityForResult(Activity activity, String str) {
        Intent intent = new Intent(activity, (Class<?>) SetNewPasswordActivity.class);
        intent.putExtra("account", str);
        activity.startActivityForResult(intent, 1002);
    }

    @Override // com.ido.common.base.BaseCoreActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStatusBarColor(getColor(R.color.color_1E1E1E), false);
        initListener();
        initData();
    }

    private void initListener() {
        this.mPasswordViewFirst.addFilter(new PassWordInputFilter(), new InputFilter.LengthFilter(16));
        this.mPasswordViewSecond.addFilter(new PassWordInputFilter(), new InputFilter.LengthFilter(16));
        this.mPasswordViewFirst.setOnTextChangedListener(new OnTextChangedListener() { // from class: com.ido.life.module.user.resetpassword.-$$Lambda$SetNewPasswordActivity$iPHOeil83NsME5sU3RfJfsfQnnU
            @Override // com.ido.life.module.user.view.OnTextChangedListener
            public final void changed(String str) {
                this.f$0.lambda$initListener$0$SetNewPasswordActivity(str);
            }
        });
        this.mPasswordViewSecond.setOnTextChangedListener(new OnTextChangedListener() { // from class: com.ido.life.module.user.resetpassword.-$$Lambda$SetNewPasswordActivity$HUR-v6L-R1nsmWO9pX8lQsvIxmU
            @Override // com.ido.life.module.user.view.OnTextChangedListener
            public final void changed(String str) {
                this.f$0.lambda$initListener$1$SetNewPasswordActivity(str);
            }
        });
    }

    public /* synthetic */ void lambda$initListener$0$SetNewPasswordActivity(String str) {
        this.mPresenter.checkSubmitEnable(this.mPasswordViewFirst.getPassword(), this.mPasswordViewSecond.getPassword());
    }

    public /* synthetic */ void lambda$initListener$1$SetNewPasswordActivity(String str) {
        this.mPresenter.checkSubmitEnable(this.mPasswordViewFirst.getPassword(), this.mPasswordViewSecond.getPassword());
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        this.mPresenter = new SetNewPasswordPresenter(this);
        this.mAccount = getIntent().getStringExtra("account");
        this.mVerifyCode = getIntent().getStringExtra("verifyCode");
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.common.base.BaseCoreActivity
    public void initViews() {
        super.initViews();
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        this.mTvReset.setText(getLanguageText(R.string.logn_set_new_password));
        this.mPasswordViewFirst.setNameHint(getLanguageText(R.string.register_set_new_password));
        this.mPasswordViewSecond.setNameHint(getLanguageText(R.string.register_confirm_password_again));
        this.mTvPasswordTip.setText(getLanguageText(R.string.register_input_password_tip));
        this.mMeSubmit.setText(getLanguageText(R.string.public_tip_confirm));
    }

    private void initTitleBar() {
        StatusBarUtil.StatusBarLightMode(this);
    }

    @OnClick({R.id.title_leftBtn})
    public void toBack(View view) {
        ActivityCompat.finishAfterTransition(this);
    }

    @OnClick({R.id.me_submit})
    public void toSetNewPassword(View view) {
        if (this.mPasswordViewFirst.getPassword().equals(this.mPasswordViewSecond.getPassword())) {
            this.mPresenter.doResetPassword(this.mAccount, this.mPasswordViewFirst.getPassword(), this.mVerifyCode);
        } else {
            NormalToast.showToast(getLanguageText(R.string.me_modify_password_inconsistent));
        }
    }

    @Override // com.ido.life.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override // com.ido.life.module.user.resetpassword.SetNewPasswordContract.View
    public void setSubmitEnable(boolean z) {
        CommonLogUtil.d(TAG, "setSubmitEnable: " + z);
        this.mMeSubmit.setEnabled(z);
    }

    @Override // com.ido.life.module.user.resetpassword.SetNewPasswordContract.View
    public void showLoading() {
        WaitingDialog.showDialogBack(this);
    }

    @Override // com.ido.life.module.user.resetpassword.SetNewPasswordContract.View
    public void showSuccess() {
        WaitingDialog.hideDialog();
        NormalToast.showToast(getLanguageText(R.string.me_reset_pass_success));
        Intent intent = new Intent(IdoApp.getAppContext(), (Class<?>) PreLoginAndRegisterActivity.class);
        intent.addFlags(32768);
        intent.addFlags(268435456);
        IdoApp.getAppContext().startActivity(intent);
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "从忘记密码页跳转到登录注册页 ");
        ActivityCompat.finishAfterTransition(this);
    }

    @Override // com.ido.life.module.user.resetpassword.SetNewPasswordContract.View
    public void showError(String str) {
        WaitingDialog.hideDialog();
        NormalToast.showToast(str);
    }

    @Override // com.ido.life.module.user.resetpassword.SetNewPasswordContract.View
    public void goback(String str) {
        NormalToast.showToast(getLanguageText(R.string.me_code_error_over));
        ActivityCompat.finishAfterTransition(this);
    }

    @Override // com.ido.life.module.user.resetpassword.SetNewPasswordContract.View
    public void showSuccess(UserInfo userInfo) {
        WaitingDialog.hideDialog();
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putParcelable("extra_user", userInfo);
        intent.putExtras(bundle);
        setResult(1003, intent);
        NormalToast.showToast(getLanguageText(R.string.me_reset_pass_success));
        ActivityCompat.finishAfterTransition(this);
    }

    @Override // com.ido.common.base.BaseView
    public void setPresenter(SetNewPasswordContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }
}