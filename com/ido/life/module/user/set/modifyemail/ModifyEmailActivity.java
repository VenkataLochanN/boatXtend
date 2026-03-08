package com.ido.life.module.user.set.modifyemail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.common.base.BaseCoreActivity;
import com.ido.common.dialog.WaitingDialog;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.InputMethodUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.SpaceFilter;
import com.ido.life.customview.NormalToast;
import com.ido.life.database.model.UserInfo;
import com.ido.life.module.user.set.modifyemail.ModifyEmailContract;
import com.ido.life.module.user.view.OnTextChangedListener;
import com.ido.life.module.user.view.ViewMeGetCode;
import com.ido.life.module.user.view.ViewMePhone;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RunTimeUtil;

/* JADX INFO: loaded from: classes3.dex */
public class ModifyEmailActivity extends BaseCoreActivity implements ModifyEmailContract.View {
    public static final int REQUEST_CODE = 5002;
    public static final int RESULT_CODE = 5003;
    private static final String TAG = "ModifyEmailActivity";
    private String currentEmail;

    @BindView(R.id.view_get_code)
    ViewMeGetCode mGetCodeView;

    @BindView(R.id.tv_submit)
    TextView mMeSubmit;

    @BindView(R.id.tv_modify_email_tip)
    TextView mModifyEmailTip;
    private ModifyEmailContract.Presenter mPresenter;

    @BindView(R.id.title_text)
    TextView mTitleText;

    @BindView(R.id.tv_register_email_title)
    TextView mTvEmailTitle;
    private UserInfo mUserInfo;

    @BindView(R.id.phone_view)
    ViewMePhone mVMPhone;

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_modify_email;
    }

    public static void startActivity(Activity activity) {
        activity.startActivity(new Intent(activity, (Class<?>) ModifyEmailActivity.class));
    }

    public static void startActivityForResult(Activity activity) {
        activity.startActivityForResult(new Intent(activity, (Class<?>) ModifyEmailActivity.class), 5002);
    }

    @Override // com.ido.common.base.BaseCoreActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStatusBarColor(getColor(R.color.color_1E1E1E), false);
        initView();
        initListener();
    }

    private void initListener() {
        this.mVMPhone.addFilter(new SpaceFilter(), new InputFilter.LengthFilter(64));
        this.mGetCodeView.setOnClickGetCodeListener(new ViewMeGetCode.OnGetCodeCallback() { // from class: com.ido.life.module.user.set.modifyemail.ModifyEmailActivity.1
            @Override // com.ido.life.module.user.view.ViewMeGetCode.OnGetCodeCallback
            public void onClick(View view) {
                ModifyEmailActivity.this.mPresenter.doGetCode(ModifyEmailActivity.this.currentEmail);
            }
        });
        this.mGetCodeView.setOnTextChangedListener(new OnTextChangedListener() { // from class: com.ido.life.module.user.set.modifyemail.-$$Lambda$ModifyEmailActivity$s-3_iLoWToi-tirCvGLTHY--DfU
            @Override // com.ido.life.module.user.view.OnTextChangedListener
            public final void changed(String str) {
                this.f$0.lambda$initListener$0$ModifyEmailActivity(str);
            }
        });
    }

    public /* synthetic */ void lambda$initListener$0$ModifyEmailActivity(String str) {
        this.mPresenter.checkSubmitEnable(this.mGetCodeView.getCode());
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        this.mPresenter = new ModifyEmailPresenter(this);
        this.mPresenter.initData();
        setGetCodeEnable(true);
    }

    private void initView() {
        this.mTitleText.setText(LanguageUtil.getLanguageText(R.string.mine_modify_email));
        this.mVMPhone.setNameHint((CharSequence) LanguageUtil.getLanguageText(R.string.mine_input_new_email));
        this.mGetCodeView.setNameHint(LanguageUtil.getLanguageText(R.string.device_input_auth_code));
        this.mModifyEmailTip.setText(LanguageUtil.getLanguageText(R.string.mine_modify_email_tip));
        long userId = RunTimeUtil.getInstance().getUserId();
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "ModifyEmailActivity  initView()：运行时ID = " + userId);
        this.mUserInfo = GreenDaoUtil.queryUserInfo(userId);
        UserInfo userInfo = this.mUserInfo;
        if (userInfo != null) {
            this.currentEmail = userInfo.getEmail();
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "ModifyEmailActivity  initView()：currentEmail = " + this.currentEmail);
            this.mTvEmailTitle.setText(String.format(LanguageUtil.getLanguageText(R.string.mine_register_email_title), this.currentEmail));
        }
    }

    @OnClick({R.id.title_leftBtn})
    public void toBack(View view) {
        ActivityCompat.finishAfterTransition(this);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        InputMethodUtil.hiddenInput(this, this.mGetCodeView);
        super.onDestroy();
    }

    @Override // com.ido.life.module.user.set.modifyemail.ModifyEmailContract.View
    public void setSubmitEnable(boolean z) {
        this.mMeSubmit.setEnabled(z);
    }

    @Override // com.ido.life.module.user.set.modifyemail.ModifyEmailContract.View
    public void showLoading() {
        InputMethodUtil.hiddenInput(this, this.mGetCodeView);
        WaitingDialog.showDialog(this);
    }

    @Override // com.ido.life.module.user.set.modifyemail.ModifyEmailContract.View
    public void hideLoading() {
        WaitingDialog.hideDialog();
    }

    @Override // com.ido.life.module.user.set.modifyemail.ModifyEmailContract.View
    public void showGetCodeError(String str) {
        WaitingDialog.hideDialog();
        NormalToast.showToast(str);
    }

    @Override // com.ido.life.module.user.set.modifyemail.ModifyEmailContract.View
    public void showGetCodeSuccess() {
        WaitingDialog.hideDialog();
        NormalToast.showToast(LanguageUtil.getLanguageText(R.string.register_get_code_tip));
    }

    @Override // com.ido.life.module.user.set.modifyemail.ModifyEmailContract.View
    public void showError(String str) {
        WaitingDialog.hideDialog();
        NormalToast.showToast(str);
    }

    @Override // com.ido.life.module.user.set.modifyemail.ModifyEmailContract.View
    public void showSuccess(String str) {
        WaitingDialog.hideDialog();
        UserInfo userInfo = this.mUserInfo;
        if (userInfo != null) {
            userInfo.setEmail(str);
            this.mUserInfo.update();
        }
        ActivityCompat.finishAfterTransition(this);
        NormalToast.showToast(LanguageUtil.getLanguageText(R.string.mine_modify_email_success), 2000);
    }

    @Override // com.ido.life.module.user.set.modifyemail.ModifyEmailContract.View
    public void startCountDown() {
        this.mGetCodeView.startCountDown();
    }

    @Override // com.ido.life.module.user.set.modifyemail.ModifyEmailContract.View
    public void stopCountDown() {
        this.mGetCodeView.stopCountDown();
    }

    @Override // com.ido.life.module.user.set.modifyemail.ModifyEmailContract.View
    public void setGetCodeEnable(boolean z) {
        this.mGetCodeView.setGetCodeEnable(z);
    }

    @Override // com.ido.life.module.user.set.modifyemail.ModifyEmailContract.View
    public String getNewEmail() {
        return this.mVMPhone.getPhone();
    }

    @Override // com.ido.life.module.user.set.modifyemail.ModifyEmailContract.View
    public String getVerificationCode() {
        return this.mGetCodeView.getCode();
    }

    @OnClick({R.id.tv_submit})
    public void toSubmit(View view) {
        this.mPresenter.doModifyEmail();
    }

    @Override // com.ido.common.base.BaseView
    public void setPresenter(ModifyEmailContract.Presenter presenter) {
        this.mPresenter = presenter;
    }
}