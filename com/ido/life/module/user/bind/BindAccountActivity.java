package com.ido.life.module.user.bind;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ido.common.dialog.WaitingDialog;
import com.ido.common.utils.InputMethodUtil;
import com.ido.common.utils.PassWordInputFilter;
import com.ido.common.utils.SpaceFilter;
import com.ido.common.utils.StatusBarUtil;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.base.BaseActivity;
import com.ido.life.customview.NormalToast;
import com.ido.life.database.model.UserInfo;
import com.ido.life.module.main.MainActivity;
import com.ido.life.module.user.bind.BindAccountContract;
import com.ido.life.module.user.bindsetpassword.ThirdBandSetPasswordActivity;
import com.ido.life.module.user.resetpassword.ResetPassActivity;
import com.ido.life.module.user.userdata.UserDataActivity;
import com.ido.life.module.user.usertarget.UserTargetActivity;
import com.ido.life.module.user.view.OnTextChangedListener;
import com.ido.life.module.user.view.ViewMePassword;
import com.ido.life.module.user.view.ViewMePhone;

/* JADX INFO: loaded from: classes3.dex */
public class BindAccountActivity extends BaseActivity implements BindAccountContract.View {
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

    @BindView(R.id.me_submit_bind_account)
    TextView mMeSubmit;
    private Long mOauthId;
    private String mOpenId;

    @BindView(R.id.password_view)
    ViewMePassword mPasswordView;

    @BindView(R.id.phone_view)
    ViewMePhone mPhoneView;
    private String mPlatName;
    private BindAccountContract.Presenter mPresenter;

    @BindView(R.id.rtv_bind_title)
    RegularTextView mTVBindTitle;

    @BindView(R.id.title_leftBtn)
    ImageButton mTitleLeftBtn;

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_bind_account;
    }

    public static void startActivity(Activity activity) {
        activity.startActivity(new Intent(activity, (Class<?>) BindAccountActivity.class));
    }

    public static void startActivityForResult(Activity activity, long j, String str, String str2, String str3) {
        Intent intent = new Intent(activity, (Class<?>) BindAccountActivity.class);
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
        this.mTVBindTitle.setText(getLanguageText(R.string.logn_third_band_account_ios));
        this.mPhoneView.setNameHint((CharSequence) getLanguageText(R.string.register_input_phone_email));
        this.mMeSubmit.setText(getLanguageText(R.string.logn_next_step));
    }

    private void initTitleBar() {
        StatusBarUtil.StatusBarLightMode(this);
    }

    private void initListener() {
        this.mPasswordView.addFilter(new PassWordInputFilter(), new InputFilter.LengthFilter(16));
        this.mPhoneView.addFilter(new SpaceFilter(), new InputFilter.LengthFilter(64));
        this.mPhoneView.setOnTextChangedListener(new OnTextChangedListener() { // from class: com.ido.life.module.user.bind.-$$Lambda$BindAccountActivity$n-OUvqzISK9HK_-IQkem-IKaO8E
            @Override // com.ido.life.module.user.view.OnTextChangedListener
            public final void changed(String str) {
                this.f$0.lambda$initListener$0$BindAccountActivity(str);
            }
        });
        this.mPasswordView.setOnTextChangedListener(new OnTextChangedListener() { // from class: com.ido.life.module.user.bind.-$$Lambda$BindAccountActivity$wGNdAWdv5EDYawFrQ7X-VK50mC4
            @Override // com.ido.life.module.user.view.OnTextChangedListener
            public final void changed(String str) {
                this.f$0.lambda$initListener$1$BindAccountActivity(str);
            }
        });
    }

    public /* synthetic */ void lambda$initListener$0$BindAccountActivity(String str) {
        this.mPresenter.checkSubmitEnable(this.mPhoneView.getPhone());
    }

    public /* synthetic */ void lambda$initListener$1$BindAccountActivity(String str) {
        this.mPresenter.checkSubmitEnable(this.mPhoneView.getPhone());
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        this.mPresenter = new BindAccountPresenter(this);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.mOauthId = Long.valueOf(extras.getLong("oauthId"));
            this.mAccessToken = extras.getString("accessToke");
            this.mOpenId = extras.getString("openid");
            this.mPlatName = extras.getString("platName");
        }
        Log.d(TAG, "注册并绑定界面接受的oauthid是：" + this.mOauthId);
        this.mPhoneView.setCountryCode(this.mCountryCode);
    }

    @Override // com.ido.life.module.user.bind.BindAccountContract.View
    public void setSubmitEnable(boolean z) {
        this.mMeSubmit.setEnabled(z);
    }

    @Override // com.ido.life.module.user.bind.BindAccountContract.View
    public void showLoading() {
        InputMethodUtil.hiddenInput(this, this.mPhoneView);
        InputMethodUtil.hiddenInput(this, this.mPasswordView);
        WaitingDialog.showDialogBack(this);
    }

    @Override // com.ido.life.module.user.bind.BindAccountContract.View
    public void hideLoading() {
        WaitingDialog.hideDialog();
    }

    @Override // com.ido.life.module.user.bind.BindAccountContract.View
    public void showError(String str) {
        if (!TextUtils.isEmpty(str)) {
            NormalToast.showToast(str, 0);
        }
        WaitingDialog.hideDialog();
    }

    @Override // com.ido.life.module.user.bind.BindAccountContract.View
    public void showSuccess() {
        WaitingDialog.hideDialog();
        NormalToast.showToast(getLanguageText(R.string.device_bind_success), 0);
    }

    @Override // com.ido.life.module.user.bind.BindAccountContract.View
    public void goForgetPassword(String str) {
        ResetPassActivity.startActivityForResult(this, str);
    }

    @Override // com.ido.life.module.user.bind.BindAccountContract.View
    public void goMain() {
        MainActivity.startActivity(this);
        ActivityCompat.finishAfterTransition(this);
    }

    @Override // com.ido.life.module.user.bind.BindAccountContract.View
    public void goPasswordCode() {
        WaitingDialog.hideDialog();
        Intent intent = new Intent(this, (Class<?>) BindInputPasswordActivity.class);
        Bundle bundle = new Bundle();
        bundle.putLong("oauthId", this.mOauthId.longValue());
        bundle.putString("accessToken", this.mAccessToken);
        bundle.putString("openId", this.mOpenId);
        bundle.putString(FirebaseAnalytics.Param.SOURCE, this.mPlatName);
        bundle.putString("account", this.mPhoneView.getPhone());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override // com.ido.life.module.user.bind.BindAccountContract.View
    public void goUnRegisterSetPassword() {
        Intent intent = new Intent(this, (Class<?>) ThirdBandSetPasswordActivity.class);
        Bundle bundle = new Bundle();
        bundle.putLong("oauthId", this.mOauthId.longValue());
        bundle.putString("account", this.mPhoneView.getPhone());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override // com.ido.life.module.user.bind.BindAccountContract.View
    public void showSuccess(UserInfo userInfo) {
        WaitingDialog.hideDialog();
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putParcelable("extra_user", userInfo);
        intent.putExtras(bundle);
        setResult(1003, intent);
        NormalToast.showToast(getLanguageText(R.string.me_reset_pass_success), 0);
        ActivityCompat.finishAfterTransition(this);
    }

    @Override // com.ido.life.module.user.bind.BindAccountContract.View
    public void goUserData(int i) {
        UserDataActivity.startActivity(this, i);
    }

    @Override // com.ido.life.module.user.bind.BindAccountContract.View
    public void goUserTarget() {
        UserTargetActivity.startActivity(this);
    }

    @OnClick({R.id.title_leftBtn})
    public void doClickBack(View view) {
        ActivityCompat.finishAfterTransition(this);
    }

    @OnClick({R.id.me_submit_bind_account})
    public void doClickSubmitBindAccount(View view) {
        this.mPresenter.doJudgeEamilIsExist(this.mPhoneView.getPhone());
    }

    @OnClick({R.id.btn_forgetpass})
    public void doClickForgetPass(View view) {
        this.mPresenter.clickResetPassword(this.mPhoneView.getPhone());
    }

    @Override // com.ido.common.base.BaseView
    public void setPresenter(BindAccountContract.Presenter presenter) {
        this.mPresenter = presenter;
    }
}