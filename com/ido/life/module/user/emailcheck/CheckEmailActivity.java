package com.ido.life.module.user.emailcheck;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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
import com.ido.common.utils.StatusBarUtil;
import com.ido.life.constants.Constants;
import com.ido.life.customview.NormalToast;
import com.ido.life.database.model.UserInfo;
import com.ido.life.module.user.emailcheck.CheckEmailContract;
import com.ido.life.module.user.userdata.UserDataActivity;
import com.ido.life.module.user.view.OnTextChangedListener;
import com.ido.life.module.user.view.ViewMeGetCode;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RunTimeUtil;

/* JADX INFO: loaded from: classes3.dex */
public class CheckEmailActivity extends BaseCoreActivity implements CheckEmailContract.View {
    public static final int REQUEST_CODE = 5002;
    public static final int RESULT_CODE = 5003;
    private static final String TAG = "CheckEmailActivity";
    private boolean isFromHome = false;

    @BindView(R.id.view_get_code)
    ViewMeGetCode mGetCodeView;

    @BindView(R.id.title_leftBtn)
    ImageButton mLeftBtn;

    @BindView(R.id.tv_submit)
    TextView mMeSubmit;
    private CheckEmailContract.Presenter mPresenter;

    @BindView(R.id.title_rightBtn)
    Button mRightBtn;

    @BindView(R.id.check_email_tip)
    TextView mTipText;

    @BindView(R.id.title_text)
    TextView mTitleText;

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_check_email;
    }

    public static void startActivity(Activity activity) {
        activity.startActivity(new Intent(activity, (Class<?>) CheckEmailActivity.class));
    }

    public static void startActivityForResult(Activity activity) {
        activity.startActivityForResult(new Intent(activity, (Class<?>) CheckEmailActivity.class), 5002);
    }

    @Override // com.ido.common.base.BaseCoreActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStatusBarColor(getColor(R.color.color_1E1E1E), false);
        initView();
        initListener();
    }

    private void initListener() {
        this.mGetCodeView.setOnClickGetCodeListener(new ViewMeGetCode.OnGetCodeCallback() { // from class: com.ido.life.module.user.emailcheck.-$$Lambda$CheckEmailActivity$6yKh_Y6YRgm6v8V5To-fwEZGRR4
            @Override // com.ido.life.module.user.view.ViewMeGetCode.OnGetCodeCallback
            public final void onClick(View view) {
                this.f$0.lambda$initListener$0$CheckEmailActivity(view);
            }
        });
        this.mGetCodeView.setOnTextChangedListener(new OnTextChangedListener() { // from class: com.ido.life.module.user.emailcheck.-$$Lambda$CheckEmailActivity$Mr81QvTUNzpjCJj2H4J3ggHhvuE
            @Override // com.ido.life.module.user.view.OnTextChangedListener
            public final void changed(String str) {
                this.f$0.lambda$initListener$1$CheckEmailActivity(str);
            }
        });
    }

    public /* synthetic */ void lambda$initListener$0$CheckEmailActivity(View view) {
        this.mPresenter.doGetCode();
    }

    public /* synthetic */ void lambda$initListener$1$CheckEmailActivity(String str) {
        this.mPresenter.checkSubmitEnable(this.mGetCodeView.getCode());
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "initData() mIntent 不为空，isFromHome为： " + this.isFromHome);
            this.isFromHome = intent.getBooleanExtra(Constants.IS_FROM_HOME, false);
        }
        this.mPresenter = new CheckEmailPresenter(this);
        setGetCodeEnable(true);
        this.mPresenter.initData(this.isFromHome);
    }

    private void initView() {
        UserInfo userInfoQueryUserInfo = GreenDaoUtil.queryUserInfo(RunTimeUtil.getInstance().getUserId());
        if (userInfoQueryUserInfo != null) {
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "initView() userinfo 不为空，邮箱账号为： " + userInfoQueryUserInfo.getEmail());
            this.mTipText.setText(String.format(LanguageUtil.getLanguageText(R.string.register_check_email_tip), userInfoQueryUserInfo.getEmail()));
        }
        if (this.isFromHome) {
            this.mLeftBtn.setVisibility(0);
            this.mRightBtn.setVisibility(8);
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "从主页跳转： 显示标题栏返回按钮");
        } else {
            this.mLeftBtn.setVisibility(8);
            this.mRightBtn.setVisibility(0);
            this.mRightBtn.setText(LanguageUtil.getLanguageText(R.string.login_skip));
            this.mRightBtn.setTextColor(getResources().getColor(R.color.white));
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "从注册跳转： 显示标题栏跳过按钮");
        }
    }

    private void initTitleBar() {
        StatusBarUtil.StatusBarLightMode(this);
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

    @Override // com.ido.life.module.user.emailcheck.CheckEmailContract.View
    public void setSubmitEnable(boolean z) {
        this.mMeSubmit.setEnabled(z);
    }

    @Override // com.ido.life.module.user.emailcheck.CheckEmailContract.View
    public void showLoading() {
        InputMethodUtil.hiddenInput(this, this.mGetCodeView);
        WaitingDialog.showDialog(this);
    }

    @Override // com.ido.life.module.user.emailcheck.CheckEmailContract.View
    public void hideLoading() {
        WaitingDialog.hideDialog();
    }

    @Override // com.ido.life.module.user.emailcheck.CheckEmailContract.View
    public void showGetCodeError(String str) {
        WaitingDialog.hideDialog();
        NormalToast.showToast(str);
    }

    @Override // com.ido.life.module.user.emailcheck.CheckEmailContract.View
    public void showGetCodeSuccess() {
        WaitingDialog.hideDialog();
        NormalToast.showToast(LanguageUtil.getLanguageText(R.string.register_get_code_tip));
    }

    @Override // com.ido.life.module.user.emailcheck.CheckEmailContract.View
    public void showError(String str) {
        WaitingDialog.hideDialog();
        NormalToast.showToast(str);
    }

    @Override // com.ido.life.module.user.emailcheck.CheckEmailContract.View
    public void showSuccess() {
        WaitingDialog.hideDialog();
        if (this.isFromHome) {
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "从主页跳转： showSuccess() 关闭验证界面");
            ActivityCompat.finishAfterTransition(this);
        } else {
            goUserData();
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "从注册跳转： showSuccess() 跳转到填写资料界面");
        }
    }

    @Override // com.ido.life.module.user.emailcheck.CheckEmailContract.View
    public void startCountDown() {
        this.mGetCodeView.startCountDown();
    }

    @Override // com.ido.life.module.user.emailcheck.CheckEmailContract.View
    public void stopCountDown() {
        this.mGetCodeView.stopCountDown();
    }

    @Override // com.ido.life.module.user.emailcheck.CheckEmailContract.View
    public void setGetCodeEnable(boolean z) {
        this.mGetCodeView.setGetCodeEnable(z);
    }

    @Override // com.ido.life.module.user.emailcheck.CheckEmailContract.View
    public void goUserData() {
        Intent intent = new Intent(this, (Class<?>) UserDataActivity.class);
        intent.putExtra(UserDataActivity.FROM_WHERE, 1);
        startActivity(intent);
        ActivityCompat.finishAfterTransition(this);
    }

    @OnClick({R.id.tv_submit})
    public void toSubmit(View view) {
        this.mPresenter.doCheckEmailCode(this.mGetCodeView.getCode());
    }

    @OnClick({R.id.title_rightBtn})
    public void toJump(View view) {
        goUserData();
    }

    @Override // com.ido.common.base.BaseView
    public void setPresenter(CheckEmailContract.Presenter presenter) {
        this.mPresenter = presenter;
    }
}