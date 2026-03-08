package com.ido.life.module.bind;

import android.content.Intent;
import android.os.IBinder;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.alexa.AlexaApi;
import com.ido.alexa.bean.AvsException;
import com.ido.alexa.callbacks.IAlexaCallBack;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.common.dialog.CommBottomConfirmDialog;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.AppUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.widget.textview.MediumTextView;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.base.BaseActivity;
import com.ido.life.base.SingleTopIntent;
import com.ido.life.ble.BleHelper;
import com.ido.life.ble.DeviceConfigHelper;
import com.ido.life.constants.Constants;
import com.ido.life.customview.DotView;
import com.ido.life.customview.authcodeview.AuthCodeView;
import com.ido.life.dialog.AlexaSupportDialog;
import com.ido.life.dialog.BindConfirmTipDialog;
import com.ido.life.dialog.BindSupportDialog;
import com.ido.life.module.alexa.AlexaHelpActivity;
import com.ido.life.module.alexa.AlexaLoginActivity;
import com.ido.life.module.device.activity.DeviceUpgradeNewActivity;
import com.ido.life.module.main.MainActivity;
import com.ido.life.util.AppLogUploadManager;
import com.ido.life.util.eventbus.EventBusHelper;
import java.util.Arrays;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class BindActivity extends BaseActivity<BindPresenter> implements IBindView, AuthCodeView.InputListener {
    private static final int STATUS_BIND_FAILED = 2;
    private static final int STATUS_BIND_REJECT = 3;
    private static final int STATUS_BIND_START = 0;
    private static final int STATUS_BIND_SUCCESS = 1;
    private static final int STATUS_CONNECT_FAILED = 4;

    @BindView(R.id.auth_code_view)
    AuthCodeView mAuthCodeView;
    private BindConfirmTipDialog mBindConfirmTipDialog;
    private BLEDevice mDevice;

    @BindView(R.id.dot_view)
    DotView mDotView;

    @BindView(R.id.iv_status)
    ImageView mIvStatus;

    @BindView(R.id.layout_auth_code)
    LinearLayout mLayoutAuthCode;

    @BindView(R.id.layout_bind_failed)
    LinearLayout mLayoutBindFailed;

    @BindView(R.id.layout_in_verification)
    LinearLayout mLayoutInVerification;

    @BindView(R.id.rtv_finish)
    RegularTextView mRtvFinish;

    @BindView(R.id.tv_auth_code_error)
    MediumTextView mTvAuthCodeError;

    @BindView(R.id.tv_bind_tip)
    TextView mTvBindTip;

    @BindView(R.id.tv_status)
    TextView mTvStatus;

    @Override // com.ido.common.base.BaseCoreActivity
    public int getLayoutResId() {
        return R.layout.activity_bind;
    }

    @Override // com.ido.life.base.BaseActivity
    protected boolean needEventBus() {
        return false;
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.common.base.BaseCoreActivity
    public void initViews() {
        super.initViews();
        this.mDotView.setVisibility(0);
        this.mIvStatus.setVisibility(8);
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        this.mDevice = (BLEDevice) getIntent().getSerializableExtra(Constants.INTENT_DATA_KEY);
        if (this.mDevice != null) {
            this.mTitleBar.setTitle(this.mDevice.mDeviceName);
        }
        ((BindPresenter) this.mPresenter).saveUserDevice(this.mDevice);
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        this.mTitleBar.setLeftOnClick(new View.OnClickListener() { // from class: com.ido.life.module.bind.-$$Lambda$BindActivity$_mvf6g5OL3-Bm6E_VxSYFljzuVc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initEvent$0$BindActivity(view);
            }
        });
        if (this.mDevice != null) {
            ((BindPresenter) this.mPresenter).clearBTpairedInfo();
            ((BindPresenter) this.mPresenter).connectDevice(this.mDevice, false);
        }
        this.mAuthCodeView.setInputListener(this);
    }

    public /* synthetic */ void lambda$initEvent$0$BindActivity(View view) {
        onBackPressed();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 101 && ((BindPresenter) this.mPresenter).isBleEnable()) {
            ((BindPresenter) this.mPresenter).connectDevice(this.mDevice, true);
        }
    }

    @Override // com.ido.life.module.bind.IBindView
    public void onNeedOpenBle() {
        BleHelper.openBLE(this);
        refreshStatusUi(2, 403);
    }

    @Override // com.ido.life.module.bind.IBindView
    public void onConnectStart(BLEDevice bLEDevice) {
        refreshStatusUi(0, 0);
    }

    @Override // com.ido.life.module.bind.IBindView
    public void onConnectSuccess(boolean z) {
        ((BindPresenter) this.mPresenter).getBasicInfoFromDevice();
    }

    @Override // com.ido.life.module.bind.IBindView
    public void onConnectFailed(int i) {
        refreshStatusUi(4, i);
    }

    @Override // com.ido.life.module.bind.IBindView
    public void onInDfuMode(final BLEDevice bLEDevice) {
        ((BindPresenter) this.mPresenter).disconnect();
        CommBottomConfirmDialog.newInstance(getLanguageText(R.string.dfu_mode_tip), getLanguageText(R.string.public_tip_confirm), getLanguageText(R.string.public_tip_cancel), true).setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.bind.-$$Lambda$BindActivity$laP-utbiG590KgrCRQtMdQoMAHo
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onInDfuMode$1$BindActivity(bLEDevice, view);
            }
        }).show(getSupportFragmentManager());
    }

    public /* synthetic */ void lambda$onInDfuMode$1$BindActivity(BLEDevice bLEDevice, View view) {
        Intent intent = new Intent(this, (Class<?>) DeviceUpgradeNewActivity.class);
        intent.putExtra("device_info", bLEDevice);
        startActivity(intent);
    }

    @Override // com.ido.life.module.bind.IBindView
    public void onBindWrongDevice(BLEDevice bLEDevice) {
        showWrongBindDialog(bLEDevice);
    }

    @Override // com.ido.life.module.bind.IBindView
    public void onGetDeviceInfoSuccess() {
        ((BindPresenter) this.mPresenter).bindDevice();
    }

    @Override // com.ido.life.module.bind.IBindView
    public void onNeedAuthCode(int i) {
        this.mAuthCodeView.setInputEnable(true);
        this.mTvStatus.setVisibility(8);
        this.mTvBindTip.setVisibility(8);
        this.mAuthCodeView.setCodeCount(i);
        this.mLayoutAuthCode.setVisibility(0);
        this.mTvAuthCodeError.setVisibility(8);
        this.mLayoutInVerification.setVisibility(8);
        this.mAuthCodeView.clear();
    }

    @Override // com.ido.life.module.bind.IBindView
    public void onNeedConfirm(int i, String str) {
        showBindConfirmTipDialog(i, str);
    }

    @Override // com.ido.life.module.bind.IBindView
    public void onBindSuccess() {
        refreshStatusUi(1, 0);
    }

    @Override // com.ido.life.module.bind.IBindView
    public void onBindTimeOut(int i) {
        refreshStatusUi(2, i);
    }

    @Override // com.ido.life.module.bind.IBindView
    public void onBindFailed(int i, boolean z) {
        if (z) {
            refreshStatusUi(3, i);
        } else {
            refreshStatusUi(2, i);
        }
    }

    @Override // com.ido.life.customview.authcodeview.AuthCodeView.InputListener
    public void onInputted() {
        this.mTvAuthCodeError.setVisibility(8);
    }

    @Override // com.ido.life.customview.authcodeview.AuthCodeView.InputListener
    public void onInputComplete(int[] iArr) {
        CommonLogUtil.d("onInputComplete = " + Arrays.toString(iArr));
        this.mLayoutInVerification.setVisibility(0);
        this.mTvStatus.setVisibility(8);
        this.mTvBindTip.setVisibility(8);
        ((BindPresenter) this.mPresenter).sendAuthCode2Device(iArr);
        hideKeyboardAndSaveData(this.mAuthCodeView.getWindowToken());
        this.mAuthCodeView.setInputEnable(false);
    }

    private void showBindConfirmTipDialog(int i, String str) {
        this.mBindConfirmTipDialog = BindConfirmTipDialog.getInstance(i, str);
        this.mBindConfirmTipDialog.show(getSupportFragmentManager());
    }

    public void hideKeyboardAndSaveData(IBinder iBinder) {
        InputMethodManager inputMethodManager;
        if (iBinder == null || (inputMethodManager = (InputMethodManager) getSystemService("input_method")) == null) {
            return;
        }
        inputMethodManager.hideSoftInputFromWindow(iBinder, 2);
    }

    /* JADX INFO: renamed from: com.ido.life.module.bind.BindActivity$1, reason: invalid class name */
    class AnonymousClass1 implements IAlexaCallBack {
        AnonymousClass1() {
        }

        @Override // com.ido.alexa.callbacks.IAlexaCallBack
        public void success(String str) {
            BindActivity.this.runOnUiThread(new Runnable() { // from class: com.ido.life.module.bind.-$$Lambda$BindActivity$1$zQPWAJA-J-EuSeZwByszMgl2DTY
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$success$0$BindActivity$1();
                }
            });
        }

        public /* synthetic */ void lambda$success$0$BindActivity$1() {
            MainActivity.startActivity(BindActivity.this, 301);
            BindActivity.this.supportFinishAfterTransition();
        }

        @Override // com.ido.alexa.callbacks.IAlexaCallBack
        public void failure(AvsException avsException) {
            if (!BindActivity.this.mRtvFinish.isShown()) {
                BindActivity.this.mRtvFinish.setVisibility(0);
            }
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getBindLogPath(), "BindActivity", "绑定成功 ,alexa未登录跳到 AlexaHelpActivity");
            SingleTopIntent singleTopIntent = new SingleTopIntent(BindActivity.this, (Class<?>) AlexaHelpActivity.class);
            singleTopIntent.putExtra(AlexaHelpActivity.MODE_BIND, true);
            BindActivity.this.startActivity(singleTopIntent);
        }
    }

    private void refreshStatusUi(int i, int i2) {
        if (i == 0) {
            startBindAnim();
            this.mTvStatus.setVisibility(0);
            this.mTvBindTip.setVisibility(0);
            this.mTvStatus.setText(R.string.device_binding);
            this.mTvBindTip.setText(R.string.bind_tip);
            if (this.mLayoutBindFailed.isShown()) {
                this.mLayoutBindFailed.setVisibility(8);
            }
            if (this.mRtvFinish.isShown()) {
                this.mRtvFinish.setVisibility(8);
                return;
            }
            return;
        }
        if (i != 1) {
            if (i == 2) {
                bindFailed();
                this.mTvStatus.setText(String.format(Locale.CHINA, "%s %d", getLanguageText(R.string.device_bind_failed), Integer.valueOf(i2)));
                return;
            } else if (i == 3) {
                bindFailed();
                this.mTvStatus.setText(String.format(Locale.CHINA, "%s %d", getLanguageText(R.string.device_bind_failed_rejected), Integer.valueOf(i2)));
                return;
            } else {
                if (i != 4) {
                    return;
                }
                bindFailed();
                this.mTvStatus.setText(String.format(Locale.CHINA, "%s %d", getLanguageText(R.string.device_connect_timeout), Integer.valueOf(i2)));
                return;
            }
        }
        BindConfirmTipDialog bindConfirmTipDialog = this.mBindConfirmTipDialog;
        if (bindConfirmTipDialog != null) {
            bindConfirmTipDialog.dismiss();
        }
        stopBindAnim();
        this.mTvStatus.setText(R.string.device_bind_success);
        this.mIvStatus.setImageResource(R.mipmap.icon_bind_success);
        this.mTvBindTip.setVisibility(8);
        showToast(getLanguageText(R.string.device_bind_success));
        if (this.mLayoutBindFailed.isShown()) {
            this.mLayoutBindFailed.setVisibility(8);
        }
        EventBusHelper.post(Constants.EventConstants.EVENT_BIND_SUCCESS_START_SERVICE);
        if (DeviceConfigHelper.getSupportFunctionInfo().ex_table_main7_voice_transmission) {
            AlexaApi.getToken(this, new AnonymousClass1());
        } else {
            MainActivity.startActivity(this, 301);
            supportFinishAfterTransition();
        }
    }

    private void bindFailed() {
        BindConfirmTipDialog bindConfirmTipDialog = this.mBindConfirmTipDialog;
        if (bindConfirmTipDialog != null) {
            bindConfirmTipDialog.dismiss();
        }
        stopBindAnim();
        this.mTvStatus.setVisibility(0);
        this.mTvBindTip.setVisibility(0);
        this.mLayoutAuthCode.setVisibility(8);
        this.mTvBindTip.setText(R.string.device_tip_bind_failed);
        setBindFailedSpannable(this.mTvBindTip.getText().toString());
        this.mIvStatus.setImageResource(R.mipmap.icon_bind_failed);
        if (!this.mLayoutBindFailed.isShown()) {
            this.mLayoutBindFailed.setVisibility(0);
        }
        if (this.mRtvFinish.isShown()) {
            this.mRtvFinish.setVisibility(8);
        }
    }

    private void showBindRejectTip() {
        final CommBottomConfirmDialog commBottomConfirmDialogNewInstance = CommBottomConfirmDialog.newInstance(LanguageUtil.getLanguageText(R.string.device_bind_failed_rejected), LanguageUtil.getLanguageText(R.string.i_see), "", false);
        commBottomConfirmDialogNewInstance.setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.bind.-$$Lambda$BindActivity$n4y-pwNAvQR6PMDrDJ_kPlFzmbA
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                commBottomConfirmDialogNewInstance.dismissAllowingStateLoss();
            }
        });
        commBottomConfirmDialogNewInstance.show(getSupportFragmentManager());
    }

    private void setBindFailedSpannable(String str) {
        String string = getString(R.string.bind_failed_help);
        if (str.contains(string)) {
            int iIndexOf = str.indexOf(string);
            if (iIndexOf == -1) {
                iIndexOf = 0;
            }
            SpannableString spannableString = new SpannableString(str);
            spannableString.setSpan(new ClickableSpan() { // from class: com.ido.life.module.bind.BindActivity.2
                @Override // android.text.style.ClickableSpan
                public void onClick(View view) {
                    BindActivity bindActivity = BindActivity.this;
                    bindActivity.startActivity(new Intent(bindActivity, (Class<?>) BindHelpActivity.class));
                }

                @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
                public void updateDrawState(TextPaint textPaint) {
                    super.updateDrawState(textPaint);
                    textPaint.setUnderlineText(true);
                    textPaint.setColor(BindActivity.this.getColor(R.color.color_027AFF));
                }
            }, iIndexOf, string.length() + iIndexOf, 33);
            this.mTvBindTip.setText(spannableString);
            this.mTvBindTip.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }

    private void startBindAnim() {
        if (!this.mDotView.isShown()) {
            this.mDotView.setVisibility(0);
        }
        if (this.mIvStatus.isShown()) {
            this.mIvStatus.setVisibility(8);
        }
        this.mDotView.setCheckedIndex(1, true);
        this.mDotView.startAnimation();
    }

    private void stopBindAnim() {
        this.mDotView.stopAnimation();
        this.mDotView.setVisibility(8);
        this.mIvStatus.setVisibility(0);
    }

    @OnClick({R.id.rtv_retry, R.id.tv_not_bind, R.id.rtv_finish})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.rtv_finish) {
            MainActivity.startActivity(this, 301);
            supportFinishAfterTransition();
        } else {
            if (id != R.id.rtv_retry) {
                return;
            }
            ((BindPresenter) this.mPresenter).connectDevice(this.mDevice, true);
        }
    }

    private void showWrongBindDialog(BLEDevice bLEDevice) {
        AppLogUploadManager.uploadLog(bLEDevice, AppLogUploadManager.LogInfo.DEVICE_MISTAKE_DEVICE_BINDING, "", "", null);
        BindSupportDialog.newInstance(LanguageUtil.getLanguageText(R.string.device_get_better_experience), LanguageUtil.getLanguageText(R.string.device_only_support_pro_bind), LanguageUtil.getLanguageText(R.string.device_go_to_download), LanguageUtil.getLanguageText(R.string.action_delay), true, false).setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.bind.-$$Lambda$BindActivity$i2cvKoksGpjQCPWo0_6gOWT8o_M
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$showWrongBindDialog$3$BindActivity(view);
            }
        }).show(getSupportFragmentManager());
    }

    public /* synthetic */ void lambda$showWrongBindDialog$3$BindActivity(View view) {
        jump2VeryFitPro();
    }

    private void showAlexaDialog() {
        AlexaSupportDialog.newInstance(LanguageUtil.getLanguageText(R.string.device_alexa_dialog_msg), LanguageUtil.getLanguageText(R.string.device_go_to_alexa), LanguageUtil.getLanguageText(R.string.action_delay), true, false).setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.bind.-$$Lambda$BindActivity$H-TeOyRKE50yHXHLbKLXDrgbkPM
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$showAlexaDialog$4$BindActivity(view);
            }
        }).show(getSupportFragmentManager());
    }

    public /* synthetic */ void lambda$showAlexaDialog$4$BindActivity(View view) {
        startActivity(new SingleTopIntent(getApplicationContext(), (Class<?>) AlexaLoginActivity.class));
    }

    private void jump2VeryFitPro() {
        try {
            Intent intent = new Intent();
            intent.setClassName("com.veryfit2hr.second", "com.ido.veryfitpro.module.bind.WelcomeActivity");
            startActivity(intent);
        } catch (Exception e2) {
            e2.printStackTrace();
            AppUtil.toAPPMarket(this, "com.veryfit2hr.second");
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (((BindPresenter) this.mPresenter).isBinding) {
            showToast(getLanguageText(R.string.device_binding_tip));
            return;
        }
        if (((BindPresenter) this.mPresenter).isBindSuccess()) {
            startActivity(new Intent(this, (Class<?>) MainActivity.class));
        } else {
            ((BindPresenter) this.mPresenter).disconnect();
        }
        super.onBackPressed();
    }
}