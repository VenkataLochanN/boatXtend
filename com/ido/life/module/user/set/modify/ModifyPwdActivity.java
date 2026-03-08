package com.ido.life.module.user.set.modify;

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
import com.ido.common.base.BaseCoreActivity;
import com.ido.common.dialog.WaitingDialog;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.InputMethodUtil;
import com.ido.common.utils.PassWordInputFilter;
import com.ido.common.utils.ResourceUtil;
import com.ido.common.utils.StatusBarUtil;
import com.ido.life.customview.NormalToast;
import com.ido.life.module.user.resetpassword.ResetPassActivity;
import com.ido.life.module.user.set.modify.ModifyPwdContract;
import com.ido.life.module.user.view.OnTextChangedListener;
import com.ido.life.module.user.view.ViewMePassword;

/* JADX INFO: loaded from: classes3.dex */
public class ModifyPwdActivity extends BaseCoreActivity implements ModifyPwdContract.View {
    private static final String TAG = "ModifyPwdActivity";

    @BindView(R.id.tv_submit)
    TextView mMeSubmit;
    private ModifyPwdContract.Presenter mPresenter;

    @BindView(R.id.reset_password_first)
    ViewMePassword mResetPasswordFirst;

    @BindView(R.id.reset_password_old)
    ViewMePassword mResetPasswordOld;

    @BindView(R.id.reset_password_second)
    ViewMePassword mResetPasswordSecond;

    @BindView(R.id.title_text)
    TextView mTitleText;

    @BindView(R.id.tv_forget_password)
    TextView mTvForgetPassword;

    @BindView(R.id.title_leftBtn)
    ImageButton mtitleLeftBtn;

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_modify_pwd;
    }

    public static void startActivity(Activity activity) {
        activity.startActivity(new Intent(activity, (Class<?>) ModifyPwdActivity.class));
    }

    @Override // com.ido.common.base.BaseCoreActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initTitleBar();
        setStatusBarColor(getColor(R.color.color_1E1E1E), false);
        initView();
        initListener();
    }

    private void initListener() {
        this.mResetPasswordOld.addFilter(new PassWordInputFilter(), new InputFilter.LengthFilter(16));
        this.mResetPasswordFirst.addFilter(new PassWordInputFilter(), new InputFilter.LengthFilter(16));
        this.mResetPasswordSecond.addFilter(new PassWordInputFilter(), new InputFilter.LengthFilter(16));
        this.mResetPasswordOld.setOnTextChangedListener(new OnTextChangedListener() { // from class: com.ido.life.module.user.set.modify.-$$Lambda$ModifyPwdActivity$K0upJErDHR9azOSMbvjPXBO0exI
            @Override // com.ido.life.module.user.view.OnTextChangedListener
            public final void changed(String str) {
                this.f$0.lambda$initListener$0$ModifyPwdActivity(str);
            }
        });
        this.mResetPasswordFirst.setOnTextChangedListener(new OnTextChangedListener() { // from class: com.ido.life.module.user.set.modify.-$$Lambda$ModifyPwdActivity$Bs0J-IcirALu8iENkFTbvoxexGs
            @Override // com.ido.life.module.user.view.OnTextChangedListener
            public final void changed(String str) {
                this.f$0.lambda$initListener$1$ModifyPwdActivity(str);
            }
        });
        this.mResetPasswordSecond.setOnTextChangedListener(new OnTextChangedListener() { // from class: com.ido.life.module.user.set.modify.-$$Lambda$ModifyPwdActivity$ruvW_xoWh7oajCzxjNNGdPg9ZdU
            @Override // com.ido.life.module.user.view.OnTextChangedListener
            public final void changed(String str) {
                this.f$0.lambda$initListener$2$ModifyPwdActivity(str);
            }
        });
    }

    public /* synthetic */ void lambda$initListener$0$ModifyPwdActivity(String str) {
        this.mPresenter.checkSubmitEnable(this.mResetPasswordOld.getPassword(), this.mResetPasswordFirst.getPassword(), this.mResetPasswordSecond.getPassword());
    }

    public /* synthetic */ void lambda$initListener$1$ModifyPwdActivity(String str) {
        this.mPresenter.checkSubmitEnable(this.mResetPasswordOld.getPassword(), this.mResetPasswordFirst.getPassword(), this.mResetPasswordSecond.getPassword());
    }

    public /* synthetic */ void lambda$initListener$2$ModifyPwdActivity(String str) {
        this.mPresenter.checkSubmitEnable(this.mResetPasswordOld.getPassword(), this.mResetPasswordFirst.getPassword(), this.mResetPasswordSecond.getPassword());
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        this.mPresenter = new ModifyPwdPresenter(this);
        this.mPresenter.initData();
    }

    private void initView() {
        this.mTitleText.setText(ResourceUtil.getString(R.string.mine_modify_password));
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
        InputMethodUtil.hiddenInput(this, this.mResetPasswordFirst);
        super.onDestroy();
    }

    @Override // com.ido.life.module.user.set.modify.ModifyPwdContract.View
    public void setSubmitEnable(boolean z) {
        CommonLogUtil.d(TAG, "setSubmitEnable: " + z);
        this.mMeSubmit.setEnabled(z);
    }

    @Override // com.ido.life.module.user.set.modify.ModifyPwdContract.View
    public void showLoading() {
        InputMethodUtil.hiddenInput(this, this.mResetPasswordFirst);
    }

    @Override // com.ido.life.module.user.set.modify.ModifyPwdContract.View
    public void hideLoading() {
        WaitingDialog.hideDialog();
    }

    @Override // com.ido.life.module.user.set.modify.ModifyPwdContract.View
    public void showError(String str) {
        WaitingDialog.hideDialog();
        NormalToast.showToast(str);
    }

    @Override // com.ido.life.module.user.set.modify.ModifyPwdContract.View
    public void showSuccess() {
        WaitingDialog.hideDialog();
        ActivityCompat.finishAfterTransition(this);
        NormalToast.showToast(ResourceUtil.getString(R.string.mine_modify_password_done));
    }

    @Override // com.ido.common.base.BaseView
    public void setPresenter(ModifyPwdContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @OnClick({R.id.tv_submit})
    public void toSubmit(View view) {
        this.mPresenter.doResetPassword(this.mResetPasswordOld.getPassword(), this.mResetPasswordFirst.getPassword(), this.mResetPasswordSecond.getPassword());
    }

    @OnClick({R.id.tv_forget_password})
    public void toForgetPassword(View view) {
        startActivity(new Intent(this, (Class<?>) ResetPassActivity.class));
    }
}