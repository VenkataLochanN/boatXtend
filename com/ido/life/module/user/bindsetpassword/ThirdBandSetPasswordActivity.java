package com.ido.life.module.user.bindsetpassword;

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
import com.ido.common.dialog.WaitingDialog;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.PassWordInputFilter;
import com.ido.common.utils.SpaceFilter;
import com.ido.common.utils.StatusBarUtil;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.base.BaseActivity;
import com.ido.life.customview.NormalToast;
import com.ido.life.database.model.UserInfo;
import com.ido.life.module.user.bindsetpassword.ThirdBandSetPasswordContract;
import com.ido.life.module.user.login.LoginActivity;
import com.ido.life.module.user.view.OnTextChangedListener;
import com.ido.life.module.user.view.ViewMePassword;

/* JADX INFO: loaded from: classes3.dex */
public class ThirdBandSetPasswordActivity extends BaseActivity implements ThirdBandSetPasswordContract.View {
    public static final String ACCOUNT = "account";
    public static final String EXTRA_USER = "extra_user";
    public static final String OAUTHID = "oauthId";
    public static final int REQUEST_CODE = 1002;
    public static final int RESULT_CODE = 1003;
    private static final String TAG = "ThirdBandSetPasswordActivity";
    public static final String VERIFYCODE = "verifyCode";
    private String mAccount;
    private String mCountryCode = "86";

    @BindView(R.id.me_submit)
    TextView mMeSubmit;
    private Long mOauthId;

    @BindView(R.id.reset_password_first)
    ViewMePassword mPasswordViewFirst;

    @BindView(R.id.reset_password_second)
    ViewMePassword mPasswordViewSecond;
    private ThirdBandSetPasswordContract.Presenter mPresenter;

    @BindView(R.id.tv_password_tip)
    TextView mTvPasswordTip;

    @BindView(R.id.title_reset)
    RegularTextView mTvReset;
    private String mVerifyCode;

    @BindView(R.id.title_leftBtn)
    ImageButton mtitleLeftBtn;

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_third_bind_set_password;
    }

    @Override // com.ido.life.module.user.bindsetpassword.ThirdBandSetPasswordContract.View
    public void showError(String str) {
    }

    public static void startActivity(Activity activity, String str) {
        Intent intent = new Intent(activity, (Class<?>) ThirdBandSetPasswordActivity.class);
        intent.putExtra("account", str);
        activity.startActivity(intent);
    }

    public static void startActivityForResult(Activity activity, String str) {
        Intent intent = new Intent(activity, (Class<?>) ThirdBandSetPasswordActivity.class);
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
        this.mPasswordViewSecond.addFilter(new SpaceFilter(), new InputFilter.LengthFilter(64));
        this.mPasswordViewFirst.setOnTextChangedListener(new OnTextChangedListener() { // from class: com.ido.life.module.user.bindsetpassword.-$$Lambda$ThirdBandSetPasswordActivity$ixq5PTMeFp9u5eovHx_w5rOqjUA
            @Override // com.ido.life.module.user.view.OnTextChangedListener
            public final void changed(String str) {
                this.f$0.lambda$initListener$0$ThirdBandSetPasswordActivity(str);
            }
        });
        this.mPasswordViewSecond.setOnTextChangedListener(new OnTextChangedListener() { // from class: com.ido.life.module.user.bindsetpassword.-$$Lambda$ThirdBandSetPasswordActivity$5hjwoh6WFAgqUFFttiXu8wsKBXU
            @Override // com.ido.life.module.user.view.OnTextChangedListener
            public final void changed(String str) {
                this.f$0.lambda$initListener$1$ThirdBandSetPasswordActivity(str);
            }
        });
    }

    public /* synthetic */ void lambda$initListener$0$ThirdBandSetPasswordActivity(String str) {
        this.mPresenter.checkSubmitEnable(this.mPasswordViewFirst.getPassword(), this.mPasswordViewSecond.getPassword());
    }

    public /* synthetic */ void lambda$initListener$1$ThirdBandSetPasswordActivity(String str) {
        this.mPresenter.checkSubmitEnable(this.mPasswordViewFirst.getPassword(), this.mPasswordViewSecond.getPassword());
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        this.mPresenter = new ThirdBandSetPasswordPresenter(this);
        this.mAccount = getIntent().getStringExtra("account");
        this.mOauthId = Long.valueOf(getIntent().getLongExtra("oauthId", 0L));
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.common.base.BaseCoreActivity
    public void initViews() {
        super.initViews();
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        this.mTvReset.setText(getLanguageText(R.string.third_band_password_title));
        this.mPasswordViewFirst.setNameHint(getLanguageText(R.string.register_set_new_password));
        this.mPasswordViewSecond.setNameHint(getLanguageText(R.string.register_confirm_password_again));
        this.mTvPasswordTip.setText(getLanguageText(R.string.register_input_password_tip));
        this.mMeSubmit.setText(getLanguageText(R.string.logn_next_step));
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
            Intent intent = new Intent(this, (Class<?>) BindInputCodeActivity.class);
            Bundle bundle = new Bundle();
            bundle.putLong("oauthId", this.mOauthId.longValue());
            bundle.putString("account", this.mAccount);
            bundle.putString(BindInputCodeActivity.PASSWORD, this.mPasswordViewFirst.getPassword());
            intent.putExtras(bundle);
            startActivity(intent);
            return;
        }
        NormalToast.showToast(getLanguageText(R.string.me_modify_password_inconsistent));
    }

    @Override // com.ido.life.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override // com.ido.life.module.user.bindsetpassword.ThirdBandSetPasswordContract.View
    public void setSubmitEnable(boolean z) {
        CommonLogUtil.d(TAG, "setSubmitEnable: " + z);
        this.mMeSubmit.setEnabled(z);
    }

    @Override // com.ido.life.module.user.bindsetpassword.ThirdBandSetPasswordContract.View
    public void showLoading() {
        WaitingDialog.showDialogBack(this);
    }

    @Override // com.ido.life.module.user.bindsetpassword.ThirdBandSetPasswordContract.View
    public void showSuccess() {
        WaitingDialog.hideDialog();
        setResult(1003, new Intent());
        NormalToast.showToast(getLanguageText(R.string.me_reset_pass_success));
        ActivityCompat.finishAfterTransition(this);
        startActivity(new Intent(this, (Class<?>) LoginActivity.class));
    }

    @Override // com.ido.life.module.user.bindsetpassword.ThirdBandSetPasswordContract.View
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
    public void setPresenter(ThirdBandSetPasswordContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }
}