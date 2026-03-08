package com.ido.life.module.user.resetpassword;

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
import com.ido.common.utils.SpaceFilter;
import com.ido.life.base.BaseActivity;
import com.ido.life.customview.NormalToast;
import com.ido.life.database.model.UserInfo;
import com.ido.life.module.user.resetpassword.ResetPassContract;
import com.ido.life.module.user.view.OnTextChangedListener;
import com.ido.life.module.user.view.ViewMeGetCode;
import com.ido.life.module.user.view.ViewMePhone;

/* JADX INFO: loaded from: classes3.dex */
public class ResetPassActivity extends BaseActivity implements ResetPassContract.View {
    public static final String ACCOUNT = "account";
    public static final String EXTRA_USER = "extra_user";
    public static final int REQUEST_CODE = 1002;
    public static final int RESULT_CODE = 1003;
    private static final String TAG = "ResetPassActivity";
    public static final String VERIFYCODE = "verifyCode";

    @BindView(R.id.get_code_view)
    ViewMeGetCode mGetCodeView;

    @BindView(R.id.me_submit)
    TextView mMeSubmit;

    @BindView(R.id.name_view)
    ViewMePhone mNameView;
    private ResetPassContract.Presenter mPresenter;

    @BindView(R.id.title_reset)
    TextView mTvReset;

    @BindView(R.id.title_leftBtn)
    ImageButton mtitleLeftBtn;

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_reset_pass;
    }

    public static void startActivity(Activity activity, String str) {
        Intent intent = new Intent(activity, (Class<?>) ResetPassActivity.class);
        intent.putExtra("account", str);
        activity.startActivity(intent);
    }

    public static void startActivityForResult(Activity activity, String str) {
        Intent intent = new Intent(activity, (Class<?>) ResetPassActivity.class);
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
        this.mNameView.addFilter(new SpaceFilter(), new InputFilter.LengthFilter(64));
        this.mGetCodeView.setOnClickGetCodeListener(new ViewMeGetCode.OnGetCodeCallback() { // from class: com.ido.life.module.user.resetpassword.-$$Lambda$ResetPassActivity$GTYwJsLYacJLO-li6kN9hmqaycQ
            @Override // com.ido.life.module.user.view.ViewMeGetCode.OnGetCodeCallback
            public final void onClick(View view) {
                this.f$0.lambda$initListener$0$ResetPassActivity(view);
            }
        });
        this.mNameView.setOnTextChangedListener(new OnTextChangedListener() { // from class: com.ido.life.module.user.resetpassword.-$$Lambda$ResetPassActivity$3CBZnov07xxksibrE8BerPOhHNE
            @Override // com.ido.life.module.user.view.OnTextChangedListener
            public final void changed(String str) {
                this.f$0.lambda$initListener$1$ResetPassActivity(str);
            }
        });
        this.mGetCodeView.setOnTextChangedListener(new OnTextChangedListener() { // from class: com.ido.life.module.user.resetpassword.-$$Lambda$ResetPassActivity$I7MBGB0gF6Rq3RwRjI_fjMn-PCQ
            @Override // com.ido.life.module.user.view.OnTextChangedListener
            public final void changed(String str) {
                this.f$0.lambda$initListener$2$ResetPassActivity(str);
            }
        });
    }

    public /* synthetic */ void lambda$initListener$0$ResetPassActivity(View view) {
        this.mPresenter.doGetCode(this.mNameView.getPhone());
    }

    public /* synthetic */ void lambda$initListener$1$ResetPassActivity(String str) {
        this.mGetCodeView.setGetCodeEnable(!TextUtils.isEmpty(str));
    }

    public /* synthetic */ void lambda$initListener$2$ResetPassActivity(String str) {
        this.mPresenter.checkSubmitEnable(this.mNameView.getPhone(), this.mGetCodeView.getCode());
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        this.mPresenter = new ResetPassPresenter(this);
        this.mNameView.setCountryCode("");
        String stringExtra = getIntent().getStringExtra("account");
        if (TextUtils.isEmpty(stringExtra)) {
            return;
        }
        this.mNameView.setText(stringExtra);
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.common.base.BaseCoreActivity
    public void initViews() {
        super.initViews();
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        this.mTvReset.setText(getLanguageText(R.string.register_reset_password));
        this.mNameView.setNameHint((CharSequence) getLanguageText(R.string.register_input_phone_email));
        this.mGetCodeView.setNameHint(getLanguageText(R.string.mine_tip_input_verification));
        this.mMeSubmit.setText(getLanguageText(R.string.logn_next_step));
    }

    private void initTitleBar() {
        setStatusBarColor(getColor(R.color.color_1E1E1E), false);
    }

    @OnClick({R.id.title_leftBtn})
    public void toBack(View view) {
        ActivityCompat.finishAfterTransition(this);
    }

    @OnClick({R.id.me_submit})
    public void toForget(View view) {
        showLoading();
        this.mPresenter.doCheckAccountIsExist(this.mNameView.getPhone());
    }

    @Override // com.ido.life.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        InputMethodUtil.hiddenInput(this, this.mNameView);
        super.onDestroy();
    }

    @Override // com.ido.life.module.user.resetpassword.ResetPassContract.View
    public void setSubmitEnable(boolean z) {
        CommonLogUtil.d(TAG, "setSubmitEnable: " + z);
        this.mMeSubmit.setEnabled(z);
    }

    @Override // com.ido.life.module.user.resetpassword.ResetPassContract.View
    public void showLoading() {
        InputMethodUtil.hiddenInput(this, this.mNameView);
        WaitingDialog.showDialogBack(this);
    }

    @Override // com.ido.life.module.user.resetpassword.ResetPassContract.View
    public void hideLoading() {
        WaitingDialog.hideDialog();
    }

    @Override // com.ido.life.module.user.resetpassword.ResetPassContract.View
    public void showGetCodeError(String str) {
        WaitingDialog.hideDialog();
        NormalToast.showToast(str);
    }

    @Override // com.ido.life.module.user.resetpassword.ResetPassContract.View
    public void showGetCodeSuccess() {
        WaitingDialog.hideDialog();
        NormalToast.showToast(getLanguageText(R.string.register_get_code_tip), 3000);
    }

    @Override // com.ido.life.module.user.resetpassword.ResetPassContract.View
    public void showError(String str) {
        WaitingDialog.hideDialog();
        NormalToast.showToast(str);
    }

    @Override // com.ido.life.module.user.resetpassword.ResetPassContract.View
    public void showSuccess() {
        WaitingDialog.hideDialog();
        setResult(1003, new Intent());
        NormalToast.showToast(getLanguageText(R.string.me_reset_pass_success));
        ActivityCompat.finishAfterTransition(this);
    }

    @Override // com.ido.life.module.user.resetpassword.ResetPassContract.View
    public void startCountDown() {
        this.mGetCodeView.startCountDown();
    }

    @Override // com.ido.life.module.user.resetpassword.ResetPassContract.View
    public void stopCountDown() {
        this.mGetCodeView.stopCountDown();
    }

    @Override // com.ido.life.module.user.resetpassword.ResetPassContract.View
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

    @Override // com.ido.life.module.user.resetpassword.ResetPassContract.View
    public void toSetNewPassword() {
        Intent intent = new Intent(this, (Class<?>) SetNewPasswordActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("account", this.mNameView.getPhone());
        bundle.putString("verifyCode", this.mGetCodeView.getCode());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override // com.ido.life.module.user.resetpassword.ResetPassContract.View
    public void toCheckCode() {
        this.mPresenter.checkCodeIsRight(this.mNameView.getPhone(), "FORGOT", this.mGetCodeView.getCode());
    }

    @Override // com.ido.common.base.BaseView
    public void setPresenter(ResetPassContract.Presenter presenter) {
        this.mPresenter = presenter;
    }
}