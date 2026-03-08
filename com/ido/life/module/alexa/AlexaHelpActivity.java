package com.ido.life.module.alexa;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.alexa.AlexaApi;
import com.ido.alexa.bean.AvsException;
import com.ido.alexa.callbacks.IAlexaCallBack;
import com.ido.alexa.manager.AlexaManager;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.StatusBarUtil;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.base.BaseActivity;
import com.ido.life.base.SingleTopIntent;
import com.ido.life.dialog.AlexSupportPermissionDialog;
import com.ido.life.module.device.activity.NativeWebViewActivity;
import com.ido.life.module.main.MainActivity;

/* JADX INFO: loaded from: classes2.dex */
public class AlexaHelpActivity extends BaseActivity {
    public static final String MODE_BIND = "mode_bind";
    public static final String MODE_REGISTER = "mode_register";
    public static final String MODE_TOURIST = "mode_tourist";
    boolean isBindMode;
    boolean isModeTourist;
    boolean isRegisterMode;

    @BindView(R.id.rtv_close)
    RegularTextView mTvClose;

    @BindView(R.id.rtv_help_tips)
    RegularTextView mTvHelpTips;

    @BindView(R.id.rtv_login)
    RegularTextView mTvLogin;

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_alexa_help;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        this.isBindMode = getIntent().getBooleanExtra(MODE_BIND, false);
        this.isRegisterMode = getIntent().getBooleanExtra(MODE_REGISTER, false);
        this.isModeTourist = getIntent().getBooleanExtra(MODE_TOURIST, false);
        AlexaApi.getToken(this, new IAlexaCallBack() { // from class: com.ido.life.module.alexa.AlexaHelpActivity.1
            @Override // com.ido.alexa.callbacks.IAlexaCallBack
            public void success(String str) {
                AlexaHelpActivity.this.mTvLogin.setText(LanguageUtil.getLanguageText(R.string.main_already_logged_in));
                AlexaHelpActivity.this.mTvLogin.setEnabled(false);
            }

            @Override // com.ido.alexa.callbacks.IAlexaCallBack
            public void failure(AvsException avsException) {
                AlexaHelpActivity.this.mTvLogin.setText(LanguageUtil.getLanguageText(R.string.login_now));
                AlexaHelpActivity.this.mTvLogin.setEnabled(true);
            }
        });
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.common.base.BaseCoreActivity
    public void initViews() {
        super.initViews();
        StatusBarUtil.StatusBarLightMode((Activity) this, true);
        StatusBarUtil.transparencyBar(this, true);
    }

    @OnClick({R.id.rtv_close, R.id.rtv_login, R.id.rtv_help_tips, R.id.rtv_help_dialog})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rtv_close /* 2131363395 */:
                if (this.isBindMode || this.isRegisterMode) {
                    MainActivity.startActivity(this, 301);
                } else if (this.isModeTourist) {
                    MainActivity.startActivity(this);
                }
                finish();
                break;
            case R.id.rtv_help_dialog /* 2131363429 */:
                AlexSupportPermissionDialog.INSTANCE.newInstance().show(getSupportFragmentManager());
                break;
            case R.id.rtv_help_tips /* 2131363430 */:
                SingleTopIntent singleTopIntent = new SingleTopIntent(this, (Class<?>) NativeWebViewActivity.class);
                singleTopIntent.putExtra("type", 10);
                startActivity(singleTopIntent);
                break;
            case R.id.rtv_login /* 2131363439 */:
                startActivityForResult(new SingleTopIntent(this, (Class<?>) AlexaLoginActivity.class), 3);
                break;
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 != 3) {
            return;
        }
        if (!TextUtils.isEmpty(AlexaManager.getInstance().getToken())) {
            this.mTvLogin.setText(LanguageUtil.getLanguageText(R.string.main_already_logged_in));
            this.mTvLogin.setEnabled(false);
        } else {
            this.mTvLogin.setText(LanguageUtil.getLanguageText(R.string.login_now));
            this.mTvLogin.setEnabled(true);
        }
    }
}