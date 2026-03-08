package com.ido.life.module.user.bindsetpassword;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.common.dialog.WaitingDialog;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.StatusBarUtil;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.base.BaseActivity;
import com.ido.life.customview.NormalToast;
import com.ido.life.database.model.UserInfo;
import com.ido.life.module.user.CodeType;
import com.ido.life.module.user.bindsetpassword.BindInputCodeContract;
import com.ido.life.module.user.userdata.UserDataActivity;
import com.ido.life.module.user.view.OnTextChangedListener;
import com.ido.life.module.user.view.ViewMeGetCode;

/* JADX INFO: loaded from: classes3.dex */
public class BindInputCodeActivity extends BaseActivity implements BindInputCodeContract.View {
    public static final String ACCOUNT = "account";
    public static final String EXTRA_USER = "extra_user";
    public static final String OAUTHID = "oauthId";
    public static final String PASSWORD = "password";
    public static final int REQUEST_CODE = 1002;
    public static final int RESULT_CODE = 1003;
    private static final String TAG = "BindInputCodeActivity";
    private String mAccount;
    private String mCountryCode = "86";

    @BindView(R.id.get_code_view)
    ViewMeGetCode mGetCodeView;

    @BindView(R.id.me_submit_bind_account)
    TextView mMeSubmit;
    private Long mOauthId;
    private String mPassword;
    private BindInputCodeContract.Presenter mPresenter;

    @BindView(R.id.title_reset)
    RegularTextView mTvReset;

    @BindView(R.id.title_leftBtn)
    ImageButton mtitleLeftBtn;

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_bind_code_register;
    }

    public static void startActivity(Activity activity, String str) {
        Intent intent = new Intent(activity, (Class<?>) BindInputCodeActivity.class);
        intent.putExtra("account", str);
        activity.startActivity(intent);
    }

    public static void startActivityForResult(Activity activity, String str) {
        Intent intent = new Intent(activity, (Class<?>) BindInputCodeActivity.class);
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
        this.mGetCodeView.setOnClickGetCodeListener(new ViewMeGetCode.OnGetCodeCallback() { // from class: com.ido.life.module.user.bindsetpassword.BindInputCodeActivity.1
            @Override // com.ido.life.module.user.view.ViewMeGetCode.OnGetCodeCallback
            public void onClick(View view) {
                BindInputCodeActivity.this.mPresenter.doGetCode(BindInputCodeActivity.this.mAccount, CodeType.REG.getType());
                CommonLogUtil.printAndSave("BindInputCodeActivity获取验证码 doGetCode ");
            }
        });
        this.mGetCodeView.setOnTextChangedListener(new OnTextChangedListener() { // from class: com.ido.life.module.user.bindsetpassword.-$$Lambda$BindInputCodeActivity$4Pzw6j1Xv9Tkw6fmO_V9s8WfZy0
            @Override // com.ido.life.module.user.view.OnTextChangedListener
            public final void changed(String str) {
                this.f$0.lambda$initListener$0$BindInputCodeActivity(str);
            }
        });
    }

    public /* synthetic */ void lambda$initListener$0$BindInputCodeActivity(String str) {
        this.mPresenter.checkSubmitEnable(this.mGetCodeView.getCode());
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        this.mPresenter = new BindInputCodePresenter(this);
        this.mAccount = getIntent().getStringExtra("account");
        this.mOauthId = Long.valueOf(getIntent().getLongExtra("oauthId", 0L));
        this.mPassword = getIntent().getStringExtra(PASSWORD);
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.common.base.BaseCoreActivity
    public void initViews() {
        super.initViews();
        this.mGetCodeView.setGetCodeEnable(true);
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        this.mTvReset.setText(getLanguageText(R.string.mine_tip_input_verification));
        this.mGetCodeView.setNameHint(getLanguageText(R.string.mine_tip_input_verification));
        this.mMeSubmit.setText(getLanguageText(R.string.logn_next_step));
    }

    private void initTitleBar() {
        StatusBarUtil.StatusBarLightMode(this);
    }

    @OnClick({R.id.title_leftBtn})
    public void toBack(View view) {
        ActivityCompat.finishAfterTransition(this);
    }

    @OnClick({R.id.me_submit_bind_account})
    public void toThirdLoginAndBindUnRegister(View view) {
        toThirdLoginAndBindUnRegister();
    }

    @Override // com.ido.life.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override // com.ido.life.module.user.bindsetpassword.BindInputCodeContract.View
    public void setSubmitEnable(boolean z) {
        CommonLogUtil.d(TAG, "setSubmitEnable: " + z);
        this.mMeSubmit.setEnabled(z);
    }

    @Override // com.ido.life.module.user.bindsetpassword.BindInputCodeContract.View
    public void showLoading() {
        WaitingDialog.showDialogBack(this);
    }

    @Override // com.ido.life.module.user.bindsetpassword.BindInputCodeContract.View
    public void hideLoading() {
        WaitingDialog.hideDialog();
    }

    @Override // com.ido.life.module.user.bindsetpassword.BindInputCodeContract.View
    public void showGetCodeError(String str) {
        WaitingDialog.hideDialog();
        NormalToast.showToast(str);
    }

    @Override // com.ido.life.module.user.bindsetpassword.BindInputCodeContract.View
    public void showGetCodeSuccess() {
        WaitingDialog.hideDialog();
    }

    @Override // com.ido.life.module.user.bindsetpassword.BindInputCodeContract.View
    public void showError(String str) {
        WaitingDialog.hideDialog();
        NormalToast.showToast(str);
    }

    @Override // com.ido.life.module.user.bindsetpassword.BindInputCodeContract.View
    public void showSuccess() {
        WaitingDialog.hideDialog();
        setResult(1003, new Intent());
        NormalToast.showToast(getLanguageText(R.string.me_reset_pass_success));
        ActivityCompat.finishAfterTransition(this);
    }

    @Override // com.ido.life.module.user.bindsetpassword.BindInputCodeContract.View
    public void showMessage(String str) {
        NormalToast.showToast(str);
    }

    @Override // com.ido.life.module.user.bindsetpassword.BindInputCodeContract.View
    public void toUserData() {
        UserDataActivity.startActivity(this, -1);
    }

    @Override // com.ido.life.module.user.bindsetpassword.BindInputCodeContract.View
    public void startCountDown() {
        this.mGetCodeView.startCountDown();
    }

    @Override // com.ido.life.module.user.bindsetpassword.BindInputCodeContract.View
    public void stopCountDown() {
        this.mGetCodeView.stopCountDown();
    }

    @Override // com.ido.life.module.user.bindsetpassword.BindInputCodeContract.View
    public void toThirdLoginAndBindUnRegister() {
        this.mPresenter.doThirdLoginAndBindUnRegister(this.mOauthId.longValue(), "", this.mAccount, this.mGetCodeView.getCode(), this.mPassword);
    }

    @Override // com.ido.life.module.user.bindsetpassword.BindInputCodeContract.View
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
    public void setPresenter(BindInputCodeContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }
}