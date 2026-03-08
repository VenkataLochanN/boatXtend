package com.ido.life.module.alexa;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.alexa.AlexaApp;
import com.ido.alexa.AlexaConstant;
import com.ido.alexa.bean.AvsException;
import com.ido.alexa.data.UserCodeResponse;
import com.ido.alexa.log.AlexaLogPathImpl;
import com.ido.alexa.log.AlexaLogUtil;
import com.ido.alexa.manager.AlexaManager;
import com.ido.alexa.manager.SpManager;
import com.ido.alexa.util.AsyncTaskUtil;
import com.ido.alexa.util.ComUtil;
import com.ido.alexa.util.WavUtils;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.common.dialog.CommBottomConfirmDialog;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.AppUtil;
import com.ido.common.utils.FileUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.ResourceUtil;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.base.BaseActivity;
import com.ido.life.base.BaseMessage;
import com.ido.life.base.SingleTopIntent;
import com.ido.life.ble.DeviceConfigHelper;
import com.ido.life.boatservice.AlexaDataService;
import com.ido.life.customview.viewgroup.CommLoadingView;
import com.ido.life.module.alexa.SpinnerTextView;
import com.ido.life.module.device.activity.NativeWebViewActivity;
import com.ido.life.module.device.presenter.WebViewPresenter;
import com.ido.record.AudioRecorder;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import no.nordicsemi.android.dfu.internal.scanner.BootloaderScanner;

/* JADX INFO: loaded from: classes2.dex */
public class AlexaLoginActivity extends BaseActivity<AlexaLoginPresenter> implements IAlexaView, View.OnTouchListener {
    private static final String AE_USER = "ae_user";
    private static final int AUTH_TIMEOUT = 15000;
    public static final String COUNTRY_CODE = "country_code";
    private static final String DEFALUT_DEVICE_NAME = "GT01";
    private static final int LOST_PACKAGE_EVENT = 7505;
    private static final int MAX_CLICK_COUNT = 3;
    private static final int MAX_CLICK_DURATION = 5000;
    private static final int REQUEST_CBL_CODE = 102;
    private static final int REQUEST_CODE = 101;
    private static final int[] guideLayouts = {R.layout.alexa_guide_layout_first, R.layout.alexa_guide_layout_second, R.layout.alexa_guide_layout_third, R.layout.alexa_guide_layout_forth};

    @BindView(R.id.alexaAppTipTv)
    TextView alexaAppTipTv;

    @BindView(R.id.alexaLanguageTv)
    TextView alexaLanguageTv;

    @BindView(R.id.alexaProductIdEt)
    EditText alexaProductIdEt;

    @BindView(R.id.alexaSetTipTv)
    TextView alexaSetTipTv;

    @BindView(R.id.alexa_guide_layout)
    View alexa_guide_layout;

    @BindView(R.id.arrowImg)
    ImageView arrowImg;
    private int currentDotIndex;
    private ImageView[] dots;

    @BindView(R.id.dots)
    LinearLayout dotsLayout;
    private ArrayList<View> guideViews;
    private boolean isClickAuth;
    private boolean isFirstLogin;
    private boolean isTest;

    @BindView(R.id.languageLayout)
    View languageLayout;

    @BindView(R.id.loadLayout)
    View loadLayout;

    @BindView(R.id.loginedLayout)
    View loginedLayout;

    @BindView(R.id.btn_lostpackagerate)
    Button mBtnOpenLostPackage;

    @BindView(R.id.comm_loading_view)
    CommLoadingView mCommLoadingView;

    @BindView(R.id.rtv_endpoint)
    Button mEndpointTv;

    @BindView(R.id.rtv_continue)
    RegularTextView mRtvContiune;

    @BindView(R.id.rtv_login_with_amazon)
    RegularTextView mRtvLoginWithAmazon;

    @BindView(R.id.rtv_logout_with_amazon)
    RegularTextView mRtvLogoutWithAmazon;

    @BindView(R.id.rtv_record)
    Button mRtvRecord;

    @BindView(R.id.alexaSmartHomeTipTv)
    TextView mSmartHomeTipTv;

    @BindView(R.id.tv_content)
    RegularTextView mTvContent;

    @BindView(R.id.tv_lostPackage)
    RegularTextView mTvLostPackage;
    private UserCodeResponse mUserCodeResponse;

    @BindView(R.id.mediaLayout)
    LinearLayout mediaLayout;
    private boolean openLostPackage;
    String pcmFileName;
    MediaPlayer player;

    @BindView(R.id.languageSpinner)
    SpinnerTextView spinner;

    @BindView(R.id.testLayout)
    View testLayout;

    @BindView(R.id.alexa_guide_viewpager)
    ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private int defaultPosition = 4;
    private int mLastPosition = -1;
    private int mSelPosition = -1;
    private int mClickCount = 0;
    private final Runnable mShowShareRunable = new Runnable() { // from class: com.ido.life.module.alexa.-$$Lambda$AlexaLoginActivity$nPFtGawFlczwVmZ6kjEktBGFig0
        @Override // java.lang.Runnable
        public final void run() {
            this.f$0.lambda$new$0$AlexaLoginActivity();
        }
    };
    private int tempEndpoint = 0;
    private final Handler mHandler = new Handler();
    private AlexaLoginType mAlexaLoginType = AlexaLoginType.SDK;
    private AlexaAuthStatus mAlexaAuthStatus = AlexaAuthStatus.NONE;
    Runnable mRunnable = new Runnable() { // from class: com.ido.life.module.alexa.-$$Lambda$AlexaLoginActivity$U60wht2I3HB0qPc4dXXpKu-I4Bk
        @Override // java.lang.Runnable
        public final void run() {
            this.f$0.lambda$new$3$AlexaLoginActivity();
        }
    };
    String voicePath = AlexaLogPathImpl.getInstance().getAlexaPCMPath();

    @Override // com.ido.common.base.BaseCoreActivity
    public int getLayoutResId() {
        return R.layout.activity_alexa;
    }

    public /* synthetic */ void lambda$new$0$AlexaLoginActivity() {
        this.mClickCount = 0;
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        this.mRtvRecord.setOnTouchListener(this);
        initLanguage();
        initGuideView();
        updateAlexaTips();
        updateAlexaHelpTips();
        this.testLayout.setVisibility(8);
        AlexaApp.setIsTest(this.isTest);
        this.mTitleBar.getTitleLayoutRight(this).setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.module.alexa.-$$Lambda$AlexaLoginActivity$GqXhtA6eJ3LYIAQIymz3zQiR0oI
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initEvent$1$AlexaLoginActivity(view);
            }
        });
        this.mTitleBar.setLeftOnClick(new View.OnClickListener() { // from class: com.ido.life.module.alexa.-$$Lambda$AlexaLoginActivity$eoBm_uXAuoHfH28zWjqYxBZw2OY
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initEvent$2$AlexaLoginActivity(view);
            }
        });
        ((AlexaLoginPresenter) this.mPresenter).registerLostPackageCallBack();
    }

    public /* synthetic */ void lambda$initEvent$1$AlexaLoginActivity(View view) {
        if (this.mClickCount == 0) {
            view.postDelayed(this.mShowShareRunable, BootloaderScanner.TIMEOUT);
        }
        this.mClickCount++;
        CommonLogUtil.d("Alexa---clickcount=" + this.mClickCount);
        if (this.mClickCount > 3) {
            this.isTest = true;
            this.testLayout.setVisibility(0);
            String[] strArr = {"android.permission.RECORD_AUDIO"};
            if (!checkSelfPermission(strArr)) {
                requestPermissions(101, strArr);
            }
            view.removeCallbacks(this.mShowShareRunable);
            this.mClickCount = 0;
            AlexaApp.setIsTest(this.isTest);
        }
    }

    public /* synthetic */ void lambda$initEvent$2$AlexaLoginActivity(View view) {
        onBackPressed();
    }

    private void initGuideView() {
        this.guideViews = new ArrayList<>();
        BLEDevice deviceInfo = ((AlexaLoginPresenter) this.mPresenter).getDeviceInfo();
        String str = TextUtils.isEmpty(deviceInfo.mDeviceName) ? DEFALUT_DEVICE_NAME : deviceInfo.mDeviceName;
        for (int i = 0; i < guideLayouts.length; i++) {
            View viewInflate = LayoutInflater.from(this).inflate(guideLayouts[i], (ViewGroup) null);
            TextView textView = (TextView) viewInflate.findViewById(R.id.titleTv);
            TextView textView2 = (TextView) viewInflate.findViewById(R.id.contentTv);
            String strReplaceAll = textView.getText().toString().replaceAll(DEFALUT_DEVICE_NAME, str);
            String strReplaceAll2 = textView2.getText().toString().replaceAll(DEFALUT_DEVICE_NAME, str);
            textView.setText(strReplaceAll);
            textView2.setText(strReplaceAll2);
            this.guideViews.add(viewInflate);
        }
        this.viewPagerAdapter = new ViewPagerAdapter(this.guideViews);
        this.viewPager.setAdapter(this.viewPagerAdapter);
        this.dots = new ImageView[this.guideViews.size()];
        for (int i2 = 0; i2 < this.guideViews.size(); i2++) {
            this.dots[i2] = (ImageView) this.dotsLayout.getChildAt(i2);
            this.dots[i2].setEnabled(false);
        }
        this.currentDotIndex = 0;
        this.dots[this.currentDotIndex].setEnabled(true);
        this.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.ido.life.module.alexa.AlexaLoginActivity.1
            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i3) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i3, float f2, int i4) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int i3) {
                if (i3 < 0 || i3 > AlexaLoginActivity.this.guideViews.size() - 1 || AlexaLoginActivity.this.currentDotIndex == i3) {
                    return;
                }
                AlexaLoginActivity.this.dots[i3].setEnabled(true);
                AlexaLoginActivity.this.dots[AlexaLoginActivity.this.currentDotIndex].setEnabled(false);
                AlexaLoginActivity.this.currentDotIndex = i3;
            }
        });
    }

    private void updateAlexaTips() {
        String languageText = LanguageUtil.getLanguageText(R.string.alexa_set_tip_child_str);
        String str = String.format(LanguageUtil.getLanguageText(R.string.alexa_app_tip), languageText);
        int iIndexOf = str.indexOf(languageText);
        this.alexaAppTipTv.setHighlightColor(getResources().getColor(android.R.color.transparent));
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new ClickableSpan() { // from class: com.ido.life.module.alexa.AlexaLoginActivity.2
            @Override // android.text.style.ClickableSpan
            public void onClick(View view) {
                if (!AppUtil.isInstallAppByPackageName(AlexaLoginActivity.this, AlexaConstant.AMAZON_APP_PKG)) {
                    AlexaLoginActivity.this.openUrlByBrowser();
                } else {
                    AlexaLoginActivity.this.startActivity(AlexaLoginActivity.this.getPackageManager().getLaunchIntentForPackage(AlexaConstant.AMAZON_APP_PKG));
                }
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setColor(ResourceUtil.getColor(R.color.color_FF4A00));
                textPaint.setUnderlineText(false);
            }
        }, iIndexOf, languageText.length() + iIndexOf, 33);
        this.alexaAppTipTv.setMovementMethod(LinkMovementMethod.getInstance());
        this.alexaAppTipTv.setText(spannableString);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openUrlByBrowser() {
        String urlByType = new WebViewPresenter().getUrlByType(11, "");
        Intent intent = new Intent("android.intent.action.VIEW");
        if (AppUtil.isInstallChrome(this)) {
            intent.setPackage("com.android.chrome");
        } else if (AppUtil.isInstallFireFox(this)) {
            intent.setPackage("org.mozilla.firefox");
        } else if (AppUtil.isInstallOpera(this)) {
            intent.setPackage("com.opera.browser");
        }
        intent.setData(Uri.parse(urlByType));
        startActivity(intent);
    }

    private void updateAlexaHelpTips() {
        AlexaLogUtil.printAndSave("是否支持Alexa二期功能：" + DeviceConfigHelper.getSupportFunctionInfo().V3_alexa_set_jump_ui);
        String languageText = LanguageUtil.getLanguageText(R.string.alexa_help_tip_child_str);
        String str = String.format(LanguageUtil.getLanguageText(R.string.alexa_help_tip), languageText);
        int iIndexOf = str.indexOf(languageText);
        this.mSmartHomeTipTv.setHighlightColor(getResources().getColor(android.R.color.transparent));
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new ClickableSpan() { // from class: com.ido.life.module.alexa.AlexaLoginActivity.3
            @Override // android.text.style.ClickableSpan
            public void onClick(View view) {
                SingleTopIntent singleTopIntent = new SingleTopIntent(AlexaLoginActivity.this, (Class<?>) NativeWebViewActivity.class);
                singleTopIntent.putExtra("type", 10);
                AlexaLoginActivity.this.startActivity(singleTopIntent);
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setColor(ResourceUtil.getColor(R.color.color_FF4A00));
                textPaint.setUnderlineText(false);
            }
        }, iIndexOf, languageText.length() + iIndexOf, 33);
        this.mSmartHomeTipTv.setMovementMethod(LinkMovementMethod.getInstance());
        this.mSmartHomeTipTv.setText(spannableString);
    }

    @Override // com.ido.life.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        ((AlexaLoginPresenter) this.mPresenter).getToken();
        checkAndRequestPermissions();
        AlexaLogUtil.printAndSave("alexa login onStart");
        if (this.isClickAuth) {
            this.isClickAuth = false;
            AlexaLogUtil.printAndSave("start auth timeout runnable");
            this.mHandler.postDelayed(this.mRunnable, 15000L);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        this.mHandler.removeCallbacks(this.mRunnable);
        AlexaLogUtil.printAndSave("alexa login onPause");
    }

    public /* synthetic */ void lambda$new$3$AlexaLoginActivity() {
        onAuthorizeCancel();
        AlexaLogUtil.printAndSave("auth timeout");
    }

    private void checkAndRequestPermissions() {
        String[] strArr = {"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE"};
        if (checkSelfPermission(strArr)) {
            return;
        }
        requestPermissions(100, strArr);
    }

    private void initLanguage() {
        String alexaLanguage = SpManager.getAlexaLanguage();
        if (alexaLanguage.length() == 0) {
            alexaLanguage = SpinnerData.languages[this.defaultPosition];
        }
        for (int i = 0; i < SpinnerData.languages.length; i++) {
            if (TextUtils.equals(SpinnerData.languages[i], alexaLanguage)) {
                this.mLastPosition = i;
                this.mSelPosition = i;
            }
        }
        this.spinner.setDataList(SpinnerData.data);
        this.spinner.setOnItemSelectListener(new SpinnerTextView.OnItemSelectListener() { // from class: com.ido.life.module.alexa.-$$Lambda$AlexaLoginActivity$wNXpReidjdznrI7Cemm_QZwKToQ
            @Override // com.ido.life.module.alexa.SpinnerTextView.OnItemSelectListener
            public final void OnItemSelect(int i2, SpinnerData spinnerData) {
                this.f$0.lambda$initLanguage$4$AlexaLoginActivity(i2, spinnerData);
            }
        });
        this.arrowImg.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.module.alexa.-$$Lambda$AlexaLoginActivity$-Iz3cByGsH5POyzc7lBychss0AE
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initLanguage$5$AlexaLoginActivity(view);
            }
        });
        updateSpinnerText(this.mSelPosition);
        this.alexaSetTipTv.setText(String.format(LanguageUtil.getLanguageText(R.string.alexa_set_tip), SpinnerData.data.get(this.mLastPosition).getName(), LanguageUtil.getLanguageText(R.string.app_name)));
    }

    public /* synthetic */ void lambda$initLanguage$4$AlexaLoginActivity(int i, SpinnerData spinnerData) {
        ((AlexaLoginPresenter) this.mPresenter).switchLanguage(spinnerData.getCode());
        this.mSelPosition = i;
    }

    public /* synthetic */ void lambda$initLanguage$5$AlexaLoginActivity(View view) {
        this.spinner.performClick();
    }

    @Override // com.ido.life.module.alexa.IAlexaView
    public void switchLanguageResult(final boolean z) {
        runOnUiThread(new Runnable() { // from class: com.ido.life.module.alexa.-$$Lambda$AlexaLoginActivity$Gs3ajShg9PwFH92iot-1DoCybEk
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$switchLanguageResult$6$AlexaLoginActivity(z);
            }
        });
    }

    public /* synthetic */ void lambda$switchLanguageResult$6$AlexaLoginActivity(boolean z) {
        if (z) {
            this.mLastPosition = this.mSelPosition;
        }
        updateSpinnerText(this.mLastPosition);
        this.alexaSetTipTv.setText(String.format(LanguageUtil.getLanguageText(R.string.alexa_set_tip), SpinnerData.data.get(this.mLastPosition).getName(), LanguageUtil.getLanguageText(R.string.app_name)));
    }

    @Override // com.ido.life.module.alexa.IAlexaView
    public void loadDefaultAlexaLanguage(String str) {
        AlexaLogUtil.printAndSave("loadDefaultAlexaLanguage= " + str);
        for (int i = 0; i < SpinnerData.languages.length; i++) {
            if (TextUtils.equals(SpinnerData.languages[i], str)) {
                this.mLastPosition = i;
                this.mSelPosition = i;
                this.defaultPosition = this.mSelPosition;
            }
        }
    }

    private void updateSpinnerText(int i) {
        if (this.spinner != null) {
            SpinnerData spinnerData = SpinnerData.data.get(i);
            this.spinner.setText(spinnerData.getName());
            this.spinner.setCompoundDrawablesWithIntrinsicBounds(getDrawable(spinnerData.getImageRes()), (Drawable) null, (Drawable) null, (Drawable) null);
        }
    }

    @Override // com.ido.life.module.alexa.IAlexaView
    public void onAuthorizeSuccess(UserCodeResponse userCodeResponse) {
        this.mUserCodeResponse = userCodeResponse;
        if (this.mAlexaLoginType == AlexaLoginType.CBL) {
            Intent intent = new Intent(this, (Class<?>) CBLLoginGuideActivity.class);
            intent.putExtra(CBLLoginGuideActivity.KEY_BEAN, this.mUserCodeResponse);
            startActivityForResult(intent, 102);
        } else {
            refreshViewForAuthSuccess();
            AlexaLogUtil.printAndSave("Alexa 官方 SDK onAuthorizeSuccess");
        }
    }

    @Override // com.ido.life.module.alexa.IAlexaView
    public void onAuthorizeFailed(Exception exc) {
        refreshViewForAuthFailed(exc);
        AlexaLogUtil.printAndSave("onAuthorizeFailed  ：" + exc);
    }

    @Override // com.ido.life.module.alexa.IAlexaView
    public void onAuthorizeCancel() {
        this.mHandler.removeCallbacksAndMessages(null);
        ((AlexaLoginPresenter) this.mPresenter).uploadLoginFailEvent();
        runOnUiThread(new Runnable() { // from class: com.ido.life.module.alexa.-$$Lambda$AlexaLoginActivity$ehAXYBOkOd6_k2EzsRDGV_xJZDg
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onAuthorizeCancel$7$AlexaLoginActivity();
            }
        });
        AlexaLogUtil.printAndSave("onAuthorizeCancel");
    }

    public /* synthetic */ void lambda$onAuthorizeCancel$7$AlexaLoginActivity() {
        showToast(LanguageUtil.getLanguageText(R.string.alexa_login_cancel));
        View view = this.loadLayout;
        if (view != null) {
            view.setVisibility(8);
        }
    }

    @Override // com.ido.life.module.alexa.IAlexaView
    public void onGetDeviceTokenByCBLAuthSuccess() {
        refreshViewForAuthSuccess();
        ComUtil.startService(this, AlexaDataService.class);
        AlexaLogUtil.printAndSave("requestDeviceTokenByCBLAuth-成功");
    }

    @Override // com.ido.life.module.alexa.IAlexaView
    public void onGetDeviceTokenByCBLAuthFailed(AvsException avsException) {
        refreshViewForAuthFailed(avsException);
        AlexaLogUtil.printAndSave("获requestDeviceTokenByCBLAuth-失败 ：" + avsException);
    }

    private void refreshViewForAuthSuccess() {
        this.mHandler.removeCallbacksAndMessages(null);
        this.mUserCodeResponse = null;
        this.mAlexaAuthStatus = AlexaAuthStatus.AUTH_END;
        if (this.loadLayout != null) {
            runOnUiThread(new Runnable() { // from class: com.ido.life.module.alexa.-$$Lambda$AlexaLoginActivity$3eJ0IcUQDK7763a6i-oFSt3zGIA
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$refreshViewForAuthSuccess$8$AlexaLoginActivity();
                }
            });
        }
    }

    public /* synthetic */ void lambda$refreshViewForAuthSuccess$8$AlexaLoginActivity() {
        if (isFinishing()) {
            return;
        }
        ((AlexaLoginPresenter) this.mPresenter).switchLanguage(SpinnerData.data.get(this.defaultPosition).getCode());
        this.mSelPosition = this.defaultPosition;
        this.loadLayout.setVisibility(8);
        setLoginState(true);
        this.isFirstLogin = true;
        this.alexaLanguageTv.setVisibility(8);
        this.languageLayout.setVisibility(8);
        this.mTitleBar.setLeftImg(0);
        this.mTitleBar.setLeftOnClick(null);
        this.mRtvLogoutWithAmazon.setVisibility(8);
    }

    private void refreshViewForAuthFailed(final Exception exc) {
        this.mHandler.removeCallbacksAndMessages(null);
        ((AlexaLoginPresenter) this.mPresenter).uploadLoginFailEvent();
        this.mUserCodeResponse = null;
        this.mAlexaAuthStatus = AlexaAuthStatus.AUTH_END;
        runOnUiThread(new Runnable() { // from class: com.ido.life.module.alexa.-$$Lambda$AlexaLoginActivity$JT51J52x1jF5w9_YD_cbap2IunQ
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$refreshViewForAuthFailed$9$AlexaLoginActivity(exc);
            }
        });
    }

    public /* synthetic */ void lambda$refreshViewForAuthFailed$9$AlexaLoginActivity(Exception exc) {
        showToast(LanguageUtil.getLanguageText(R.string.alexa_login_fail) + " :" + exc);
        View view = this.loadLayout;
        if (view != null) {
            view.setVisibility(8);
        }
        setLoginState(false);
    }

    @Override // com.ido.life.module.alexa.IAlexaView
    public void onGetTokenSuccess(String str) {
        AlexaLogUtil.printAndSave("alexa onGetTokenSuccess");
        runOnUiThread(new Runnable() { // from class: com.ido.life.module.alexa.-$$Lambda$AlexaLoginActivity$Bvlu5p_y8FnzVu5bOB4q9-wbeQQ
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onGetTokenSuccess$10$AlexaLoginActivity();
            }
        });
    }

    public /* synthetic */ void lambda$onGetTokenSuccess$10$AlexaLoginActivity() {
        setLoginState(true);
        ComUtil.startService(this, AlexaDataService.class);
    }

    @Override // com.ido.life.module.alexa.IAlexaView
    public void onGetTokenFailed(AvsException avsException) {
        AlexaLogUtil.printAndSave("alexa onGetTokenFailed= " + avsException.getMessage());
        runOnUiThread(new Runnable() { // from class: com.ido.life.module.alexa.-$$Lambda$AlexaLoginActivity$aodowZqbVF6EK6SJs9ytbUwCiAQ
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onGetTokenFailed$11$AlexaLoginActivity();
            }
        });
    }

    public /* synthetic */ void lambda$onGetTokenFailed$11$AlexaLoginActivity() {
        dismissLoadingDialog();
        setLoginState(false);
    }

    @Override // com.ido.life.module.alexa.IAlexaView
    public void onLogoutSuccess() {
        AlexaLogUtil.printAndSave("alexa onLogoutSuccess");
        runOnUiThread(new Runnable() { // from class: com.ido.life.module.alexa.-$$Lambda$AlexaLoginActivity$PG_jSSkRVGjlAcfg2fs3yGymCok
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onLogoutSuccess$12$AlexaLoginActivity();
            }
        });
    }

    public /* synthetic */ void lambda$onLogoutSuccess$12$AlexaLoginActivity() {
        this.loadLayout.setVisibility(8);
        setLoginState(false);
        this.viewPager.setCurrentItem(0);
    }

    @Override // com.ido.life.module.alexa.IAlexaView
    public void onLogoutFailed(final AvsException avsException) {
        AlexaLogUtil.printAndSave("alexa onLogoutFailed");
        runOnUiThread(new Runnable() { // from class: com.ido.life.module.alexa.-$$Lambda$AlexaLoginActivity$MsOo0wCdOIRhJDfm5OnAQBoHsq0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onLogoutFailed$13$AlexaLoginActivity(avsException);
            }
        });
    }

    public /* synthetic */ void lambda$onLogoutFailed$13$AlexaLoginActivity(AvsException avsException) {
        showToast(LanguageUtil.getLanguageText(R.string.alexa_logout_fail) + " ：" + avsException);
    }

    private void setLoginState(boolean z) {
        if (isFinishing()) {
            return;
        }
        this.mTitleBar.setTitle(z ? LanguageUtil.getLanguageText(R.string.alexa_title) : "");
        this.alexa_guide_layout.setVisibility(z ? 8 : 0);
        this.loginedLayout.setVisibility(z ? 0 : 8);
        this.mRtvLoginWithAmazon.setVisibility(z ? 8 : 0);
        this.mRtvLogoutWithAmazon.setVisibility(z ? 0 : 8);
        this.mRtvRecord.setVisibility(z ? 0 : 8);
        this.alexaLanguageTv.setVisibility(z ? 0 : 8);
        this.languageLayout.setVisibility(z ? 0 : 8);
        this.mTvContent.setVisibility(z ? 0 : 8);
        this.mRtvContiune.setVisibility(z ? 0 : 8);
        if (this.isTest && z) {
            this.testLayout.setVisibility(0);
        }
    }

    @OnClick({R.id.rtv_login_with_amazon, R.id.rtv_logout_with_amazon, R.id.loginedLayout, R.id.audioTipLayout, R.id.rtv_continue})
    public void onViewClicked(View view) {
        SpinnerTextView spinnerTextView = this.spinner;
        if (spinnerTextView != null) {
            spinnerTextView.dismissPopWindow();
        }
        switch (view.getId()) {
            case R.id.rtv_continue /* 2131363398 */:
                if (this.isFirstLogin) {
                    this.isFirstLogin = false;
                    startActivityForResult(new Intent(this, (Class<?>) AwakeAlexaActivity.class), 101);
                } else {
                    onBackPressed();
                }
                break;
            case R.id.rtv_login_with_amazon /* 2131363440 */:
                this.mAlexaAuthStatus = AlexaAuthStatus.AUTH_ING;
                this.isClickAuth = true;
                if (this.isTest && this.alexaProductIdEt.getText().length() != 0) {
                    ((AlexaLoginPresenter) this.mPresenter).testAuthorize(this.alexaProductIdEt.getText().toString(), this.mAlexaLoginType);
                } else {
                    this.mCommLoadingView.setLoadingText("");
                    this.loadLayout.setVisibility(0);
                    ((AlexaLoginPresenter) this.mPresenter).authorize(this.mAlexaLoginType);
                }
                break;
            case R.id.rtv_logout_with_amazon /* 2131363441 */:
                final CommBottomConfirmDialog commBottomConfirmDialogNewInstance = CommBottomConfirmDialog.newInstance(LanguageUtil.getLanguageText(R.string.alexa_logout_title), LanguageUtil.getLanguageText(R.string.alexa_logout_tip), LanguageUtil.getLanguageText(R.string.alexa_logout_ok), LanguageUtil.getLanguageText(R.string.alexa_logout_cancel), true);
                commBottomConfirmDialogNewInstance.setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.alexa.-$$Lambda$AlexaLoginActivity$O78cDICsec8h1IlZVprV_edkXJE
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        this.f$0.lambda$onViewClicked$14$AlexaLoginActivity(commBottomConfirmDialogNewInstance, view2);
                    }
                });
                commBottomConfirmDialogNewInstance.show(getSupportFragmentManager());
                break;
        }
    }

    public /* synthetic */ void lambda$onViewClicked$14$AlexaLoginActivity(CommBottomConfirmDialog commBottomConfirmDialog, View view) {
        AlexaLogUtil.printAndSave("用户主动退出alexa");
        ((AlexaLoginPresenter) this.mPresenter).logoutWithAmazon();
        this.loadLayout.setVisibility(0);
        commBottomConfirmDialog.dismiss();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 101 || i2 != -1) {
            if (i != 102 || i2 == -1) {
                return;
            }
            this.mUserCodeResponse = null;
            return;
        }
        this.mRtvLogoutWithAmazon.setText(LanguageUtil.getLanguageText(R.string.login_exit_login));
        this.alexaLanguageTv.setVisibility(0);
        this.languageLayout.setVisibility(0);
        this.mTitleBar.setLeftImg(R.mipmap.arrow_left);
        this.mTitleBar.setLeftOnClick(new View.OnClickListener() { // from class: com.ido.life.module.alexa.-$$Lambda$AlexaLoginActivity$v-24hi-pQ1lO2XZZAaqxWNsbi90
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onActivityResult$15$AlexaLoginActivity(view);
            }
        });
    }

    public /* synthetic */ void lambda$onActivityResult$15$AlexaLoginActivity(View view) {
        onBackPressed();
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.life.util.eventbus.IHandlerEventBus
    public void handleMessage(BaseMessage baseMessage) {
        super.handleMessage(baseMessage);
        int type = baseMessage.getType();
        if (type == 819) {
            CommonLogUtil.d("AlexaService 收到传来的录音数据");
            this.mCommLoadingView.setLoadingText(LanguageUtil.getLanguageText(R.string.alexa_data_parse));
            this.loadLayout.setVisibility(this.isTest ? 0 : 8);
        } else if (type == 820) {
            this.loadLayout.setVisibility(8);
            this.mTvContent.setText((String) baseMessage.getData());
            CommonLogUtil.d("AlexaService 收到alexa的解析结果");
        } else if (type == 842) {
            this.pcmFileName = (String) baseMessage.getData();
            this.mediaLayout.setVisibility(0);
        } else {
            if (type != 848) {
                return;
            }
            this.loadLayout.setVisibility(8);
            onLogoutSuccess();
        }
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            ((AlexaLoginPresenter) this.mPresenter).startRecord();
        } else if (action == 1) {
            ((AlexaLoginPresenter) this.mPresenter).stopRecord();
        }
        return true;
    }

    @OnClick({R.id.playVoice, R.id.stopVoice, R.id.exportVoice, R.id.rtv_endpoint, R.id.btn_clear, R.id.btn_lostpackagerate})
    public void stopVoice(View view) throws Throwable {
        switch (view.getId()) {
            case R.id.btn_clear /* 2131361921 */:
                this.mTvLostPackage.setText("");
                this.mTvContent.setText("");
                break;
            case R.id.btn_lostpackagerate /* 2131361925 */:
                if (!this.openLostPackage) {
                    this.mBtnOpenLostPackage.setText("关闭丢包率");
                } else {
                    this.mBtnOpenLostPackage.setText("查看丢包率");
                }
                this.openLostPackage = !this.openLostPackage;
                break;
            case R.id.exportVoice /* 2131362204 */:
                String str = Environment.getExternalStorageDirectory() + "/boatWave";
                final String str2 = str + "/PCM文件_log.zip";
                final File file = new File(str2);
                if (file.exists()) {
                    FileUtil.deleteFile(str2);
                }
                if (!new File(str).exists()) {
                    new File(str).mkdir();
                }
                new AsyncTaskUtil(new AsyncTaskUtil.IAsyncTaskCallBack() { // from class: com.ido.life.module.alexa.AlexaLoginActivity.4
                    @Override // com.ido.alexa.util.AsyncTaskUtil.IAsyncTaskCallBack
                    public Object doInBackground(String... strArr) {
                        AlexaLoginActivity.this.showLoadingDialog();
                        FileUtil.zipFolder(AlexaLoginActivity.this.voicePath, str2);
                        return null;
                    }

                    @Override // com.ido.alexa.util.AsyncTaskUtil.IAsyncTaskCallBack
                    public void onPostExecute(Object obj) {
                        AlexaLoginActivity.this.dismissLoadingDialog();
                        if (file.exists()) {
                            Intent intent = new Intent("android.intent.action.SEND");
                            intent.setType("text/plain");
                            intent.putExtra("android.intent.extra.STREAM", Uri.fromFile(file));
                            List<ResolveInfo> listQueryIntentActivities = AlexaLoginActivity.this.getPackageManager().queryIntentActivities(intent, 65536);
                            if (listQueryIntentActivities == null || listQueryIntentActivities.isEmpty()) {
                                return;
                            }
                            com.ido.life.util.FileUtil.deleteDirectory(AlexaLoginActivity.this.voicePath);
                            AlexaLoginActivity.this.startActivity(intent);
                        }
                    }
                }).execute("");
                break;
            case R.id.playVoice /* 2131363227 */:
                String strConcat = this.voicePath.concat(this.pcmFileName);
                File file2 = new File(strConcat);
                if (file2.exists() && file2.length() > 0) {
                    String strConcat2 = this.voicePath.concat("temp.wav");
                    WavUtils.convertPcm2Wav(strConcat, strConcat2, AudioRecorder.DEFAULT_SAMPLE_RATE, 1, 16);
                    CommonLogUtil.d("pcmFileName.length=" + file2.length());
                    try {
                        this.player = new MediaPlayer();
                        this.player.setDataSource(strConcat2);
                        this.player.prepare();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                    this.player.start();
                    break;
                }
                break;
            case R.id.rtv_endpoint /* 2131363412 */:
                this.tempEndpoint++;
                int i = this.tempEndpoint;
                if (i == 0) {
                    this.mEndpointTv.setText("亚洲");
                    AlexaManager.getInstance().createAlexaUrl(AlexaConstant.REGION_FE);
                } else if (i == 1) {
                    this.mEndpointTv.setText("美国");
                    AlexaManager.getInstance().createAlexaUrl(AlexaConstant.REGION_NA);
                } else {
                    this.mEndpointTv.setText("欧洲");
                    AlexaManager.getInstance().createAlexaUrl("eu");
                    this.tempEndpoint = -1;
                }
                break;
            case R.id.stopVoice /* 2131363620 */:
                MediaPlayer mediaPlayer = this.player;
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    this.player.stop();
                    this.player.release();
                    break;
                }
                break;
        }
    }

    @Override // com.ido.life.module.alexa.IAlexaView
    public void onLostPackageData(String str) {
        RegularTextView regularTextView;
        if (!this.openLostPackage || (regularTextView = this.mTvLostPackage) == null) {
            return;
        }
        regularTextView.append(str);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        setResult(3);
        finishAfterTransition();
    }

    @Override // com.ido.life.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        ((AlexaLoginPresenter) this.mPresenter).unRegisterLostPackageCallBack();
        super.onDestroy();
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        if (this.mAlexaLoginType == AlexaLoginType.CBL && this.mAlexaAuthStatus == AlexaAuthStatus.AUTH_ING) {
            if (this.mUserCodeResponse != null) {
                this.mHandler.removeCallbacksAndMessages(null);
                ((AlexaLoginPresenter) this.mPresenter).requestDeviceTokenByCBLAuth(this.mUserCodeResponse);
            } else {
                onAuthorizeCancel();
            }
        }
    }
}