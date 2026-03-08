package com.ido.life.module.user.bind;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.common.base.BaseCoreActivity;
import com.ido.common.dialog.WaitingDialog;
import com.ido.common.utils.InputMethodUtil;
import com.ido.common.utils.PassWordInputFilter;
import com.ido.common.utils.ResourceUtil;
import com.ido.common.utils.SpaceFilter;
import com.ido.common.utils.StatusBarUtil;
import com.ido.common.utils.ValidateUtil;
import com.ido.life.customview.NormalToast;
import com.ido.life.module.device.activity.NativeWebViewActivity;
import com.ido.life.module.main.MainActivity;
import com.ido.life.module.user.CodeType;
import com.ido.life.module.user.bind.BindRegisterAccountContract;
import com.ido.life.module.user.usertarget.UserTargetActivity;
import com.ido.life.module.user.view.OnTextChangedListener;
import com.ido.life.module.user.view.ViewMeGetCode;
import com.ido.life.module.user.view.ViewMePassword;
import com.ido.life.module.user.view.ViewMePhone;

/* JADX INFO: loaded from: classes3.dex */
public class BindRegisterAccountActivity extends BaseCoreActivity implements BindRegisterAccountContract.View {
    public static final String EXTRA_ACCESS_TOKEN = "accessToke";
    public static final String EXTRA_OAUTH_ID = "oauthId";
    public static final String EXTRA_OPEN_ID = "openid";
    public static final String EXTRA_PLAT_NAME = "platName";
    public static final String EXTRA_SUCCESS = "success";
    public static final String EXTRA_USER = "extra_user";
    public static final int REQUEST_CODE = 1001;
    public static final int RESULT_CODE = 1002;
    private static final String TAG = "BindRegisterAccountActivity";
    private String mAccessToken;
    private String mCountryCode = "86";

    @BindView(R.id.get_code_view)
    ViewMeGetCode mGetCodeView;

    @BindView(R.id.me_submit)
    TextView mMeSubmit;
    private Long mOauthId;
    private String mOpenId;

    @BindView(R.id.password_view)
    ViewMePassword mPasswordView;

    @BindView(R.id.phone_view)
    ViewMePhone mPhoneView;
    private String mPlatName;
    private BindRegisterAccountContract.Presenter mPresenter;

    @BindView(R.id.title_leftBtn)
    ImageButton mTitleLeftBtn;

    @BindView(R.id.tv_agreement)
    TextView mTvAgreement;

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_bind_register_account;
    }

    public static void startActivityForResult(Activity activity, long j, String str, String str2, String str3) {
        Intent intent = new Intent(activity, (Class<?>) BindRegisterAccountActivity.class);
        Bundle bundle = new Bundle();
        bundle.putLong("oauthId", j);
        bundle.putString("accessToke", str);
        bundle.putString("openid", str2);
        bundle.putString("platName", str3);
        intent.putExtras(bundle);
        activity.startActivityForResult(intent, 1001);
    }

    @Override // com.ido.common.base.BaseCoreActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_bind_register_account);
        setStatusBarColor(getColor(R.color.color_1E1E1E), false);
        initListener();
        setAgreementText(this, this.mTvAgreement);
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        this.mPresenter = new BindRegisterAccountPresenter(this);
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

    private void initTitleBar() {
        StatusBarUtil.StatusBarLightMode(this);
    }

    private void initListener() {
        this.mPasswordView.addFilter(new PassWordInputFilter(), new InputFilter.LengthFilter(16));
        this.mPhoneView.addFilter(new SpaceFilter(), new InputFilter.LengthFilter(64));
        this.mGetCodeView.setOnClickGetCodeListener(new ViewMeGetCode.OnGetCodeCallback() { // from class: com.ido.life.module.user.bind.-$$Lambda$BindRegisterAccountActivity$2-Ji3hL6E18iBN2R_sXmwNcxfOE
            @Override // com.ido.life.module.user.view.ViewMeGetCode.OnGetCodeCallback
            public final void onClick(View view) {
                this.f$0.lambda$initListener$0$BindRegisterAccountActivity(view);
            }
        });
        this.mPhoneView.setOnTextChangedListener(new OnTextChangedListener() { // from class: com.ido.life.module.user.bind.-$$Lambda$BindRegisterAccountActivity$4gYa1Xy1wV1Qk1TtKlZmt1MDaLs
            @Override // com.ido.life.module.user.view.OnTextChangedListener
            public final void changed(String str) {
                this.f$0.lambda$initListener$1$BindRegisterAccountActivity(str);
            }
        });
        this.mGetCodeView.setOnTextChangedListener(new OnTextChangedListener() { // from class: com.ido.life.module.user.bind.-$$Lambda$BindRegisterAccountActivity$jT6zQ6ztT_qfZRrM53f3zw9wlFs
            @Override // com.ido.life.module.user.view.OnTextChangedListener
            public final void changed(String str) {
                this.f$0.lambda$initListener$2$BindRegisterAccountActivity(str);
            }
        });
        this.mPasswordView.setOnTextChangedListener(new OnTextChangedListener() { // from class: com.ido.life.module.user.bind.-$$Lambda$BindRegisterAccountActivity$oxgeSeSCzrSham3gCpUPBOIvME8
            @Override // com.ido.life.module.user.view.OnTextChangedListener
            public final void changed(String str) {
                this.f$0.lambda$initListener$3$BindRegisterAccountActivity(str);
            }
        });
    }

    public /* synthetic */ void lambda$initListener$0$BindRegisterAccountActivity(View view) {
        this.mPresenter.doGetCode(this.mCountryCode, this.mPhoneView.getPhone(), CodeType.REG.getType());
    }

    public /* synthetic */ void lambda$initListener$1$BindRegisterAccountActivity(String str) {
        this.mGetCodeView.setGetCodeEnable(!TextUtils.isEmpty(str));
        this.mPresenter.checkSubmitEnable(this.mPhoneView.getPhone(), this.mGetCodeView.getCode(), this.mPasswordView.getPassword());
    }

    public /* synthetic */ void lambda$initListener$2$BindRegisterAccountActivity(String str) {
        this.mPresenter.checkSubmitEnable(this.mPhoneView.getPhone(), this.mGetCodeView.getCode(), this.mPasswordView.getPassword());
    }

    public /* synthetic */ void lambda$initListener$3$BindRegisterAccountActivity(String str) {
        this.mPresenter.checkSubmitEnable(this.mPhoneView.getPhone(), this.mGetCodeView.getCode(), this.mPasswordView.getPassword());
    }

    @OnClick({R.id.title_leftBtn})
    public void doClickBack(View view) {
        goBack();
    }

    @OnClick({R.id.me_submit})
    public void doClickSubmit(View view) {
        this.mPresenter.doRegister(this.mOauthId.longValue());
    }

    public void goBack() {
        ActivityCompat.finishAfterTransition(this);
    }

    @Override // com.ido.life.module.user.bind.BindRegisterAccountContract.View
    public void setSubmitEnable(boolean z) {
        this.mMeSubmit.setEnabled(z);
    }

    @Override // com.ido.life.module.user.bind.BindRegisterAccountContract.View
    public void startCountDown() {
        this.mGetCodeView.startCountDown();
    }

    @Override // com.ido.life.module.user.bind.BindRegisterAccountContract.View
    public void stopCountDown() {
        this.mGetCodeView.stopCountDown();
    }

    @Override // com.ido.life.module.user.bind.BindRegisterAccountContract.View
    public void goUserData(int i) {
        WaitingDialog.hideDialog();
        Intent intent = new Intent();
        intent.putExtras(new Bundle());
        setResult(1002, intent);
        NormalToast.showToast(getString(R.string.register_register_bind), 0);
        ActivityCompat.finishAfterTransition(this);
    }

    @Override // com.ido.life.module.user.bind.BindRegisterAccountContract.View
    public void goUserTarget() {
        UserTargetActivity.startActivity(this);
        ActivityCompat.finishAfterTransition(this);
    }

    @Override // com.ido.life.module.user.bind.BindRegisterAccountContract.View
    public void goMain() {
        MainActivity.startActivity(this);
        ActivityCompat.finishAfterTransition(this);
    }

    @Override // com.ido.life.module.user.bind.BindRegisterAccountContract.View
    public boolean isPhoneNumberRegister() {
        return !ValidateUtil.checkEmail(this.mPhoneView.getPhone());
    }

    @Override // com.ido.life.module.user.bind.BindRegisterAccountContract.View
    public String getName() {
        return this.mPhoneView.getPhone();
    }

    @Override // com.ido.life.module.user.bind.BindRegisterAccountContract.View
    public String getCountryCode() {
        return this.mCountryCode;
    }

    @Override // com.ido.life.module.user.bind.BindRegisterAccountContract.View
    public String getPassword() {
        return this.mPasswordView.getPassword();
    }

    @Override // com.ido.life.module.user.bind.BindRegisterAccountContract.View
    public String getCode() {
        return this.mGetCodeView.getCode();
    }

    @Override // com.ido.life.module.user.bind.BindRegisterAccountContract.View
    public void showLoading() {
        InputMethodUtil.hiddenInput(this, this.mPhoneView);
        WaitingDialog.showDialogBack(this);
    }

    @Override // com.ido.life.module.user.bind.BindRegisterAccountContract.View
    public void hideLoading() {
        WaitingDialog.hideDialog();
    }

    @Override // com.ido.life.module.user.bind.BindRegisterAccountContract.View
    public void showError(String str) {
        if (!TextUtils.isEmpty(str)) {
            NormalToast.showToast(str, 0);
        }
        WaitingDialog.hideDialog();
    }

    @Override // com.ido.life.module.user.bind.BindRegisterAccountContract.View
    public void showSuccess() {
        NormalToast.showToast(getString(R.string.device_bind_success), 0);
    }

    @Override // com.ido.life.module.user.bind.BindRegisterAccountContract.View
    public void setViewNull() {
        this.mPhoneView.setText("");
        this.mPasswordView.setPassword("");
        this.mGetCodeView.setCode("");
        this.mGetCodeView.stopCountDown();
    }

    @Override // com.ido.life.module.user.bind.BindRegisterAccountContract.View
    public void showMessage(String str) {
        NormalToast.showToast(str, 0);
    }

    @Override // com.ido.life.module.user.bind.BindRegisterAccountContract.View
    public void setNameHint(String str) {
        this.mPhoneView.setNameHint((CharSequence) str);
    }

    @Override // com.ido.life.module.user.bind.BindRegisterAccountContract.View
    public void showNameCountry() {
        this.mPhoneView.setShowCountryCode(true);
    }

    @Override // com.ido.life.module.user.bind.BindRegisterAccountContract.View
    public void hideNameCountry() {
        this.mPhoneView.setShowCountryCode(false);
    }

    @Override // com.ido.common.base.BaseView
    public void setPresenter(BindRegisterAccountContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    private void setAgreementText(Activity activity, TextView textView) {
        String string = ResourceUtil.getString(R.string.register_agree_agreement_privacy);
        SpannableString agreementSpan = getAgreementSpan(activity, null, string, ResourceUtil.getString(R.string.mine_user_agreement), ResourceUtil.getString(R.string.mine_user_agreement));
        String string2 = ResourceUtil.getString(R.string.logn_detail_policy_ios);
        String string3 = ResourceUtil.getString(R.string.logn_detail_policy_ios);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setText(getAgreementSpan(activity, agreementSpan, string, string2, string3));
    }

    private SpannableString getAgreementSpan(final Activity activity, SpannableString spannableString, String str, final String str2, String str3) {
        if (spannableString == null) {
            spannableString = new SpannableString(str);
        }
        int iIndexOf = str.indexOf(str2);
        spannableString.setSpan(new ClickableSpan() { // from class: com.ido.life.module.user.bind.BindRegisterAccountActivity.1
            @Override // android.text.style.ClickableSpan
            public void onClick(View view) {
                if (str2.equals(ResourceUtil.getString(R.string.mine_user_agreement))) {
                    Intent intent = new Intent(activity, (Class<?>) NativeWebViewActivity.class);
                    intent.putExtra("type", 2);
                    BindRegisterAccountActivity.this.startActivity(intent);
                } else {
                    Intent intent2 = new Intent(activity, (Class<?>) NativeWebViewActivity.class);
                    intent2.putExtra("type", 3);
                    BindRegisterAccountActivity.this.startActivity(intent2);
                }
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(TextPaint textPaint) {
                textPaint.setColor(ResourceUtil.getColor(R.color.color_blue_text));
            }
        }, iIndexOf, str2.length() + iIndexOf, 17);
        return spannableString;
    }
}