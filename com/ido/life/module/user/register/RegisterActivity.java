package com.ido.life.module.user.register;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.common.IdoApp;
import com.ido.common.base.BaseCoreActivity;
import com.ido.common.dialog.WaitingDialog;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.InputMethodUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.PassWordInputFilter;
import com.ido.common.utils.SpaceFilter;
import com.ido.common.utils.ValidateUtil;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.base.ILocationCallback;
import com.ido.life.constants.Constants;
import com.ido.life.customview.NormalToast;
import com.ido.life.location.LocationMessage;
import com.ido.life.module.device.activity.NativeWebViewActivity;
import com.ido.life.module.main.MainActivity;
import com.ido.life.module.user.bind.BindRegisterAccountActivity;
import com.ido.life.module.user.country.CountryChooseActivity;
import com.ido.life.module.user.country.CountryInfo;
import com.ido.life.module.user.emailcheck.CheckEmailActivity;
import com.ido.life.module.user.register.RegisterContract;
import com.ido.life.module.user.userdata.UserDataActivity;
import com.ido.life.module.user.usertarget.UserTargetActivity;
import com.ido.life.module.user.view.OnTextChangedListener;
import com.ido.life.module.user.view.ViewMeCountryArea;
import com.ido.life.module.user.view.ViewMePassword;
import com.ido.life.module.user.view.ViewMePhone;
import com.ido.life.util.SPUtils;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
public class RegisterActivity extends BaseCoreActivity implements RegisterContract.View, ILocationCallback {
    public static final int REQUEST_CHOOSE_CODE = 1003;
    public static final int REQUEST_CODE = 1001;
    public static final int RESULT_CODE = 1002;
    private static final String TAG = RegisterActivity.class.getSimpleName();

    @BindView(R.id.cb_agreement)
    CheckBox mCheckBox;
    private CountryInfo mCountryInfo;

    @BindView(R.id.me_submit)
    TextView mMeSubmit;

    @BindView(R.id.password_view)
    ViewMePassword mPasswordView;

    @BindView(R.id.phone_view)
    ViewMePhone mPhoneView;
    private RegisterContract.Presenter mPresenter;

    @BindView(R.id.title_leftBtn)
    ImageButton mTitleLeftBtn;

    @BindView(R.id.me_name_et)
    TextView mTvCountryArea;

    @BindView(R.id.tv_password_tip)
    TextView mTvPasswordTip;

    @BindView(R.id.tv_private_agreement)
    TextView mTvPrivateAgreement;

    @BindView(R.id.rtv_register_title)
    RegularTextView mTvRegisterTitle;

    @BindView(R.id.country_area_view)
    ViewMeCountryArea mViewMeCountryArea;

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_register;
    }

    @Override // com.ido.life.base.ILocationCallback
    public void onLocationUpdate(LocationMessage locationMessage) {
    }

    public static void startActivity(Activity activity, boolean z) {
        Intent intent = new Intent(activity, (Class<?>) RegisterActivity.class);
        intent.putExtra("isOversea", z);
        activity.startActivityForResult(intent, 1001);
    }

    @Override // com.ido.common.base.BaseCoreActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStatusBarColor(getColor(R.color.color_1E1E1E), false);
        initListener();
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        this.mPresenter = new RegisterPresenter(this);
    }

    @Override // com.ido.common.base.BaseCoreActivity
    public void initViews() {
        super.initViews();
        this.mTvPrivateAgreement.setMovementMethod(LinkMovementMethod.getInstance());
        String languageText = LanguageUtil.getLanguageText(R.string.splash_title_detail);
        String str = " " + LanguageUtil.getLanguageText(R.string.mine_user_agreement);
        String str2 = " " + LanguageUtil.getLanguageText(R.string.register_and);
        String str3 = " " + LanguageUtil.getLanguageText(R.string.logn_detail_policy_ios);
        SpannableString spannableString = new SpannableString(languageText + str + str2 + str3);
        ClickableSpan clickableSpan = new ClickableSpan() { // from class: com.ido.life.module.user.register.RegisterActivity.1
            @Override // android.text.style.ClickableSpan
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, (Class<?>) NativeWebViewActivity.class);
                intent.putExtra("type", 2);
                RegisterActivity.this.startActivity(intent);
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setColor(RegisterActivity.this.getResources().getColor(R.color.color_blue_text));
            }
        };
        ClickableSpan clickableSpan2 = new ClickableSpan() { // from class: com.ido.life.module.user.register.RegisterActivity.2
            @Override // android.text.style.ClickableSpan
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, (Class<?>) NativeWebViewActivity.class);
                intent.putExtra("type", 3);
                RegisterActivity.this.startActivity(intent);
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setColor(RegisterActivity.this.getResources().getColor(R.color.color_blue_text));
            }
        };
        spannableString.setSpan(clickableSpan, languageText.length(), languageText.length() + str.length(), 18);
        spannableString.setSpan(clickableSpan2, languageText.length() + str.length() + str2.length(), languageText.length() + str.length() + str2.length() + str3.length(), 18);
        this.mTvPrivateAgreement.setText(spannableString);
        String str4 = (String) SPUtils.get(Constants.CHOOSE_COUNTRY_CODE, "");
        if (TextUtils.isEmpty(str4)) {
            return;
        }
        try {
            this.mViewMeCountryArea.setText(getResources().getString(getResources().getIdentifier("country_" + str4, "string", IdoApp.getAppContext().getPackageName())));
        } catch (Exception e2) {
            e2.printStackTrace();
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "国家码存储失败。");
            SPUtils.put(Constants.CHOOSE_COUNTRY_CODE, "");
        }
    }

    private void initListener() {
        this.mPasswordView.addFilter(new PassWordInputFilter(), new InputFilter.LengthFilter(16));
        this.mPhoneView.addFilter(new SpaceFilter(), new InputFilter.LengthFilter(64));
        this.mViewMeCountryArea.setOnTextChangedListener(new OnTextChangedListener() { // from class: com.ido.life.module.user.register.-$$Lambda$RegisterActivity$CdDkKPIwi9BI-n1YQTKRkPM6tDw
            @Override // com.ido.life.module.user.view.OnTextChangedListener
            public final void changed(String str) {
                this.f$0.lambda$initListener$0$RegisterActivity(str);
            }
        });
        this.mPhoneView.setOnTextChangedListener(new OnTextChangedListener() { // from class: com.ido.life.module.user.register.-$$Lambda$RegisterActivity$F4chw1gP77-2j0FcIp0bHFQrkE8
            @Override // com.ido.life.module.user.view.OnTextChangedListener
            public final void changed(String str) {
                this.f$0.lambda$initListener$1$RegisterActivity(str);
            }
        });
        this.mPasswordView.setOnTextChangedListener(new OnTextChangedListener() { // from class: com.ido.life.module.user.register.-$$Lambda$RegisterActivity$Vze79BCipdYTumSmGyAI0b2pDS0
            @Override // com.ido.life.module.user.view.OnTextChangedListener
            public final void changed(String str) {
                this.f$0.lambda$initListener$2$RegisterActivity(str);
            }
        });
        this.mCheckBox.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.module.user.register.RegisterActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RegisterActivity.this.mPresenter.checkSubmitEnable(RegisterActivity.this.mViewMeCountryArea.getPhone(), RegisterActivity.this.mPhoneView.getPhone(), RegisterActivity.this.mPasswordView.getPassword(), RegisterActivity.this.mCheckBox.isChecked());
            }
        });
    }

    public /* synthetic */ void lambda$initListener$0$RegisterActivity(String str) {
        this.mPresenter.checkSubmitEnable(this.mViewMeCountryArea.getPhone(), this.mPhoneView.getPhone(), this.mPasswordView.getPassword(), this.mCheckBox.isChecked());
    }

    public /* synthetic */ void lambda$initListener$1$RegisterActivity(String str) {
        this.mPresenter.checkSubmitEnable(this.mViewMeCountryArea.getPhone(), this.mPhoneView.getPhone(), this.mPasswordView.getPassword(), this.mCheckBox.isChecked());
    }

    public /* synthetic */ void lambda$initListener$2$RegisterActivity(String str) {
        this.mPresenter.checkSubmitEnable(this.mViewMeCountryArea.getPhone(), this.mPhoneView.getPhone(), this.mPasswordView.getPassword(), this.mCheckBox.isChecked());
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
    }

    @OnClick({R.id.title_leftBtn})
    public void doClickBack(View view) {
        goBack();
    }

    @OnClick({R.id.me_submit})
    public void doClickSubmit(View view) {
        Locale locale = Locale.getDefault();
        this.mPresenter.doRegister(locale.getCountry(), locale.getDisplayName(), locale.getLanguage(), locale.getCountry());
    }

    @OnClick({R.id.country_area_view})
    public void chooseCountry(View view) {
        Intent intent = new Intent(this, (Class<?>) CountryChooseActivity.class);
        if (this.mCountryInfo == null) {
            this.mCountryInfo = new CountryInfo();
        }
        CommonLogUtil.d(TAG, "开始进入选择国家页面");
        intent.putExtra(Constants.FROM_WHICH_OPEN_COUNTRY, Constants.REGISTER_OPEN_COUNTRY);
        startActivityForResult(intent, 1003);
    }

    public void goBack() {
        ActivityCompat.finishAfterTransition(this);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 1003 || intent == null) {
            if (i == 1001 && i2 == 1002) {
                goUserData(-1);
                ActivityCompat.finishAfterTransition(this);
                return;
            }
            return;
        }
        this.mCountryInfo = (CountryInfo) intent.getSerializableExtra(CountryChooseActivity.COUNTRY);
        CountryInfo countryInfo = this.mCountryInfo;
        if (countryInfo != null) {
            this.mViewMeCountryArea.setText(countryInfo.countryName);
        }
    }

    @Override // com.ido.life.module.user.register.RegisterContract.View
    public boolean isPhoneNumberRegister() {
        return !ValidateUtil.checkEmail(this.mPhoneView.getPhone());
    }

    @Override // com.ido.life.module.user.register.RegisterContract.View
    public String getName() {
        return this.mPhoneView.getPhone();
    }

    @Override // com.ido.life.module.user.register.RegisterContract.View
    public String getPassword() {
        return this.mPasswordView.getPassword();
    }

    @Override // com.ido.life.module.user.register.RegisterContract.View
    public void showLoading() {
        InputMethodUtil.hiddenInput(this, this.mPhoneView);
        WaitingDialog.showDialogBack(this);
    }

    @Override // com.ido.life.module.user.register.RegisterContract.View
    public void hideLoading() {
        WaitingDialog.hideDialog();
    }

    @Override // com.ido.life.module.user.register.RegisterContract.View
    public void showError(String str) {
        WaitingDialog.hideDialog();
        NormalToast.showToast(str);
    }

    @Override // com.ido.life.module.user.register.RegisterContract.View
    public void showSuccess() {
        WaitingDialog.hideDialog();
    }

    @Override // com.ido.life.module.user.register.RegisterContract.View
    public void setViewNull() {
        this.mPhoneView.setText("");
        this.mPasswordView.setPassword("");
    }

    @Override // com.ido.life.module.user.register.RegisterContract.View
    public void showMessage(String str) {
        NormalToast.showToast(str, 3000);
    }

    @Override // com.ido.life.module.user.register.RegisterContract.View
    public void setNameHint(String str) {
        this.mPhoneView.setNameHint((CharSequence) str);
    }

    @Override // com.ido.life.module.user.register.RegisterContract.View
    public void setSubmitEnable(boolean z) {
        this.mMeSubmit.setEnabled(z);
    }

    @Override // com.ido.life.module.user.register.RegisterContract.View
    public void goUserData(int i) {
        Intent intent = new Intent(this, (Class<?>) UserDataActivity.class);
        intent.putExtra(UserDataActivity.FROM_WHERE, 1);
        startActivity(intent);
        ActivityCompat.finishAfterTransition(this);
    }

    @Override // com.ido.life.module.user.register.RegisterContract.View
    public void goUserTarget() {
        UserTargetActivity.startActivity(this);
        ActivityCompat.finishAfterTransition(this);
    }

    @Override // com.ido.life.module.user.register.RegisterContract.View
    public void goMain() {
        MainActivity.startActivity(this);
        ActivityCompat.finishAfterTransition(this);
    }

    @Override // com.ido.life.module.user.register.RegisterContract.View
    public void goBind(long j, String str, String str2, String str3) {
        BindRegisterAccountActivity.startActivityForResult(this, j, str, str2, str3);
    }

    @Override // com.ido.life.module.user.register.RegisterContract.View
    public boolean isAgreeCheckbox() {
        return this.mCheckBox.isChecked();
    }

    @Override // com.ido.life.module.user.register.RegisterContract.View
    public void setLocationCountryArea(String str) {
        this.mTvCountryArea.setText(str);
    }

    @Override // com.ido.life.module.user.register.RegisterContract.View
    public void goCheckEmail() {
        Intent intent = new Intent(this, (Class<?>) CheckEmailActivity.class);
        intent.putExtra(Constants.ACCOUNT_KEY, this.mPhoneView.getPhone());
        intent.putExtra(Constants.IS_FROM_HOME, false);
        startActivity(intent);
        ActivityCompat.finishAfterTransition(this);
    }

    @Override // com.ido.common.base.BaseView
    public void setPresenter(RegisterContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override // com.ido.life.base.ILocationCallback
    public void onLocationFailed(String str) {
        CommonLogUtil.d(TAG, str);
    }
}