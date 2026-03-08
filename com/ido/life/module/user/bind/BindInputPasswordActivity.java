package com.ido.life.module.user.bind;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.common.dialog.WaitingDialog;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.InputMethodUtil;
import com.ido.common.utils.PassWordInputFilter;
import com.ido.common.utils.SpaceFilter;
import com.ido.common.utils.StatusBarUtil;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.base.BaseActivity;
import com.ido.life.customview.NormalToast;
import com.ido.life.database.model.UserInfo;
import com.ido.life.module.main.MainActivity;
import com.ido.life.module.user.bind.BindInputPasswordContract;
import com.ido.life.module.user.resetpassword.ResetPassActivity;
import com.ido.life.module.user.userdata.UserDataActivity;
import com.ido.life.module.user.usertarget.UserTargetActivity;
import com.ido.life.module.user.view.OnTextChangedListener;
import com.ido.life.module.user.view.ViewMePassword;
import com.ido.life.module.user.view.ViewMePhone;

/* JADX INFO: loaded from: classes3.dex */
public class BindInputPasswordActivity extends BaseActivity implements BindInputPasswordContract.View {
    public static final String EXTRA_ACCESS_TOKEN = "accessToke";
    public static final String EXTRA_OAUTH_ID = "oauthId";
    public static final String EXTRA_OPEN_ID = "openid";
    public static final String EXTRA_PLAT_NAME = "platName";
    public static final String EXTRA_USER = "extra_user";
    public static final int REQUEST_CODE = 1002;
    public static final int RESULT_CODE = 1003;
    private static final String TAG = "BindAccountActivity";
    private String mAccessToken;

    @BindView(R.id.btn_forgetpass)
    TextView mBtnForgetpass;
    private String mCountryCode = "86";
    private String mEmail;

    @BindView(R.id.me_submit_bind_account)
    TextView mMeSubmit;
    private Long mOauthId;
    private String mOpenId;

    @BindView(R.id.password_view)
    ViewMePassword mPasswordView;

    @BindView(R.id.phone_view)
    ViewMePhone mPhoneView;
    private String mPlatName;
    private BindInputPasswordContract.Presenter mPresenter;

    @BindView(R.id.title_leftBtn)
    ImageButton mTitleLeftBtn;

    @BindView(R.id.rtv_password_title)
    RegularTextView mTvPasswordTitle;

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_bind_input_password;
    }

    @Override // com.ido.life.module.user.bind.BindInputPasswordContract.View
    public void goPasswordCode() {
    }

    public static void startActivity(Activity activity) {
        activity.startActivity(new Intent(activity, (Class<?>) BindInputPasswordActivity.class));
    }

    public static void startActivityForResult(Activity activity, long j, String str, String str2, String str3) {
        Intent intent = new Intent(activity, (Class<?>) BindInputPasswordActivity.class);
        Bundle bundle = new Bundle();
        bundle.putLong("oauthId", j);
        bundle.putString("accessToke", str);
        bundle.putString("openid", str2);
        bundle.putString("platName", str3);
        intent.putExtras(bundle);
        activity.startActivityForResult(intent, 1002);
    }

    @Override // com.ido.common.base.BaseCoreActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStatusBarColor(getColor(R.color.color_1E1E1E), false);
        initListener();
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.common.base.BaseCoreActivity
    public void initViews() {
        super.initViews();
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        this.mTvPasswordTitle.setText(getLanguageText(R.string.login_input_password));
        this.mPasswordView.setNameHint(getLanguageText(R.string.login_input_password));
        this.mBtnForgetpass.setText(getLanguageText(R.string.logn_third_band_code));
        this.mMeSubmit.setText(getLanguageText(R.string.logn_next_step));
    }

    private void initTitleBar() {
        StatusBarUtil.StatusBarLightMode(this);
    }

    private void initListener() {
        this.mPasswordView.addFilter(new PassWordInputFilter(), new InputFilter.LengthFilter(16));
        this.mPhoneView.addFilter(new SpaceFilter(), new InputFilter.LengthFilter(64));
        this.mPhoneView.setOnTextChangedListener(new OnTextChangedListener() { // from class: com.ido.life.module.user.bind.-$$Lambda$BindInputPasswordActivity$RqGGNnpBnGraYEXzpLnUj6NoRdc
            @Override // com.ido.life.module.user.view.OnTextChangedListener
            public final void changed(String str) {
                this.f$0.lambda$initListener$0$BindInputPasswordActivity(str);
            }
        });
        this.mPasswordView.setOnTextChangedListener(new OnTextChangedListener() { // from class: com.ido.life.module.user.bind.-$$Lambda$BindInputPasswordActivity$ZMQML8-euGghwpMz83uoVuViryU
            @Override // com.ido.life.module.user.view.OnTextChangedListener
            public final void changed(String str) {
                this.f$0.lambda$initListener$1$BindInputPasswordActivity(str);
            }
        });
    }

    public /* synthetic */ void lambda$initListener$0$BindInputPasswordActivity(String str) {
        this.mPresenter.checkSubmitEnable(this.mPhoneView.getPhone());
    }

    public /* synthetic */ void lambda$initListener$1$BindInputPasswordActivity(String str) {
        this.mPresenter.checkSubmitEnable(this.mPasswordView.getPassword());
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        this.mPresenter = new BindInputPasswordPresenter(this);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.mOauthId = Long.valueOf(extras.getLong("oauthId"));
            this.mEmail = extras.getString("account");
            this.mAccessToken = extras.getString("accessToke");
            this.mOpenId = extras.getString("openid");
            this.mPlatName = extras.getString("platName");
        }
        CommonLogUtil.d(TAG, "注册并绑定界面接受的oauthid是：" + this.mOauthId);
        this.mPhoneView.setCountryCode(this.mCountryCode);
    }

    @Override // com.ido.life.module.user.bind.BindInputPasswordContract.View
    public void setSubmitEnable(boolean z) {
        this.mMeSubmit.setEnabled(z);
    }

    @Override // com.ido.life.module.user.bind.BindInputPasswordContract.View
    public void showLoading() {
        InputMethodUtil.hiddenInput(this, this.mPhoneView);
        InputMethodUtil.hiddenInput(this, this.mPasswordView);
        WaitingDialog.showDialogBack(this);
    }

    @Override // com.ido.life.module.user.bind.BindInputPasswordContract.View
    public void hideLoading() {
        WaitingDialog.hideDialog();
    }

    @Override // com.ido.life.module.user.bind.BindInputPasswordContract.View
    public void showError(String str) {
        if (!TextUtils.isEmpty(str)) {
            NormalToast.showToast(str, 0);
        }
        WaitingDialog.hideDialog();
    }

    @Override // com.ido.life.module.user.bind.BindInputPasswordContract.View
    public void showSuccess() {
        NormalToast.showToast(getString(R.string.device_bind_success), 0);
    }

    @Override // com.ido.life.module.user.bind.BindInputPasswordContract.View
    public void goForgetPassword(String str) {
        ResetPassActivity.startActivityForResult(this, str);
    }

    @Override // com.ido.life.module.user.bind.BindInputPasswordContract.View
    public void goMain() {
        MainActivity.startActivity(this);
        ActivityCompat.finishAfterTransition(this);
    }

    @Override // com.ido.life.module.user.bind.BindInputPasswordContract.View
    public void showSuccess(UserInfo userInfo) {
        WaitingDialog.hideDialog();
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putParcelable("extra_user", userInfo);
        intent.putExtras(bundle);
        setResult(1003, intent);
        NormalToast.showToast(getString(R.string.me_reset_pass_success), 0);
        ActivityCompat.finishAfterTransition(this);
    }

    @Override // com.ido.life.module.user.bind.BindInputPasswordContract.View
    public void goUserData(int i) {
        UserDataActivity.startActivity(this, i);
    }

    @Override // com.ido.life.module.user.bind.BindInputPasswordContract.View
    public void goUserTarget() {
        UserTargetActivity.startActivity(this);
    }

    @OnClick({R.id.title_leftBtn})
    public void doClickBack(View view) {
        ActivityCompat.finishAfterTransition(this);
    }

    @OnClick({R.id.me_submit_bind_account})
    public void doClickSubmitBindAccount(View view) {
        WaitingDialog.showDialogBack(this);
        this.mPresenter.doBindAlreadyRegister(this.mOauthId, this.mEmail, this.mPasswordView.getPassword(), this.mAccessToken, this.mOpenId, this.mPlatName);
    }

    @OnClick({R.id.btn_forgetpass})
    public void doClickForgetPass(View view) {
        NormalToast.showToast("暂不支持验证码绑定第三方账户功能！");
    }

    @Override // com.ido.common.base.BaseView
    public void setPresenter(BindInputPasswordContract.Presenter presenter) {
        this.mPresenter = presenter;
    }
}