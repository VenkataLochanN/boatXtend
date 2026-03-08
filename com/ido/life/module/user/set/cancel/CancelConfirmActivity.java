package com.ido.life.module.user.set.cancel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
import com.ido.common.utils.ResourceUtil;
import com.ido.common.utils.StatusBarUtil;
import com.ido.life.customview.NormalToast;
import com.ido.life.module.user.login.PreLoginAndRegisterActivity;
import com.ido.life.module.user.set.cancel.CancelConfirmContract;
import com.ido.life.module.user.view.OnTextChangedListener;
import com.ido.life.module.user.view.ViewMeGetCode;
import com.ido.life.util.eventbus.EventBusHelper;

/* JADX INFO: loaded from: classes3.dex */
public class CancelConfirmActivity extends BaseCoreActivity implements CancelConfirmContract.View {
    public static final int REQUEST_CODE = 5002;
    public static final int RESULT_CODE = 5003;
    private static final String TAG = "CancelConfirmActivity";

    @BindView(R.id.view_get_code)
    ViewMeGetCode mGetCodeView;

    @BindView(R.id.tv_cancel)
    TextView mMeCancel;

    @BindView(R.id.tv_submit)
    TextView mMeSubmit;
    private CancelConfirmContract.Presenter mPresenter;

    @BindView(R.id.title_text)
    TextView mTitleText;

    @BindView(R.id.tv_area_code)
    TextView mTvAreaCode;

    @BindView(R.id.tv_phone)
    TextView mTvPhone;

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_cancel_confirm;
    }

    public static void startActivity(Activity activity) {
        activity.startActivity(new Intent(activity, (Class<?>) CancelConfirmActivity.class));
    }

    public static void startActivityForResult(Activity activity) {
        activity.startActivityForResult(new Intent(activity, (Class<?>) CancelConfirmActivity.class), 5002);
    }

    @Override // com.ido.common.base.BaseCoreActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStatusBarColor(getColor(R.color.color_1E1E1E), false);
        initView();
        initListener();
    }

    private void initListener() {
        this.mGetCodeView.setOnClickGetCodeListener(new ViewMeGetCode.OnGetCodeCallback() { // from class: com.ido.life.module.user.set.cancel.CancelConfirmActivity.1
            @Override // com.ido.life.module.user.view.ViewMeGetCode.OnGetCodeCallback
            public void onClick(View view) {
                CancelConfirmActivity.this.mPresenter.doGetCode();
            }
        });
        this.mGetCodeView.setOnTextChangedListener(new OnTextChangedListener() { // from class: com.ido.life.module.user.set.cancel.-$$Lambda$CancelConfirmActivity$lN-CcW0ckGskTZYmbCUCiejfLqM
            @Override // com.ido.life.module.user.view.OnTextChangedListener
            public final void changed(String str) {
                this.f$0.lambda$initListener$0$CancelConfirmActivity(str);
            }
        });
    }

    public /* synthetic */ void lambda$initListener$0$CancelConfirmActivity(String str) {
        this.mPresenter.checkSubmitEnable(this.mGetCodeView.getCode());
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        this.mPresenter = new CancelConfirmPresenter(this);
        this.mPresenter.initData();
        setGetCodeEnable(true);
    }

    private void initView() {
        this.mTitleText.setText(ResourceUtil.getString(R.string.login_cancel_account));
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

    @Override // com.ido.life.module.user.set.cancel.CancelConfirmContract.View
    public void setSubmitEnable(boolean z) {
        CommonLogUtil.d(TAG, "setSubmitEnable: " + z);
        this.mMeSubmit.setEnabled(z);
    }

    @Override // com.ido.life.module.user.set.cancel.CancelConfirmContract.View
    public void showLoading() {
        InputMethodUtil.hiddenInput(this, this.mGetCodeView);
        WaitingDialog.showDialog(this);
    }

    @Override // com.ido.life.module.user.set.cancel.CancelConfirmContract.View
    public void goPreLoginAndRegister() {
        startActivity(new Intent(this, (Class<?>) PreLoginAndRegisterActivity.class));
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "注销账号，goPreLoginAndRegister()  跳转到登录注册页");
        EventBusHelper.post(305);
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "发送EVENT_TYPE_OTHER_LOGIN_FINISH 事件2");
    }

    @Override // com.ido.life.module.user.set.cancel.CancelConfirmContract.View
    public void hideLoading() {
        WaitingDialog.hideDialog();
    }

    @Override // com.ido.life.module.user.set.cancel.CancelConfirmContract.View
    public void showGetCodeError(String str) {
        WaitingDialog.hideDialog();
        NormalToast.showToast(str);
    }

    @Override // com.ido.life.module.user.set.cancel.CancelConfirmContract.View
    public void showGetCodeSuccess() {
        WaitingDialog.hideDialog();
        NormalToast.showToast(getString(R.string.register_get_code_tip));
    }

    @Override // com.ido.life.module.user.set.cancel.CancelConfirmContract.View
    public void showError(String str) {
        WaitingDialog.hideDialog();
        NormalToast.showToast(str);
    }

    @Override // com.ido.life.module.user.set.cancel.CancelConfirmContract.View
    public void showSuccess() {
        WaitingDialog.hideDialog();
        setResult(5003, new Intent());
        ActivityCompat.finishAfterTransition(this);
        NormalToast.showToast(getString(R.string.mine_cancel_account_success));
    }

    @Override // com.ido.life.module.user.set.cancel.CancelConfirmContract.View
    public void startCountDown() {
        this.mGetCodeView.startCountDown();
    }

    @Override // com.ido.life.module.user.set.cancel.CancelConfirmContract.View
    public void stopCountDown() {
        this.mGetCodeView.stopCountDown();
    }

    @Override // com.ido.life.module.user.set.cancel.CancelConfirmContract.View
    public void showUserAccount(String str) {
        this.mTvPhone.setText(str);
    }

    @Override // com.ido.life.module.user.set.cancel.CancelConfirmContract.View
    public void setAreaVisible(boolean z) {
        this.mTvAreaCode.setVisibility(z ? 0 : 8);
    }

    @Override // com.ido.life.module.user.set.cancel.CancelConfirmContract.View
    public void setAraeText(String str) {
        this.mTvAreaCode.setText(str);
    }

    @Override // com.ido.life.module.user.set.cancel.CancelConfirmContract.View
    public void setGetCodeEnable(boolean z) {
        this.mGetCodeView.setGetCodeEnable(z);
    }

    @Override // com.ido.life.module.user.set.cancel.CancelConfirmContract.View
    public String getCountryCode() {
        return this.mTvAreaCode.getText().toString();
    }

    @OnClick({R.id.tv_submit})
    public void toSubmit(View view) {
        this.mPresenter.doCancelUser(this.mGetCodeView.getCode(), getCountryCode());
    }

    @OnClick({R.id.tv_cancel})
    public void toCancel(View view) {
        ActivityCompat.finishAfterTransition(this);
    }

    @Override // com.ido.common.base.BaseView
    public void setPresenter(CancelConfirmContract.Presenter presenter) {
        this.mPresenter = presenter;
    }
}