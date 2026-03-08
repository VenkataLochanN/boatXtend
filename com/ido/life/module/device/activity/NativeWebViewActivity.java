package com.ido.life.module.device.activity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import butterknife.BindView;
import com.boat.Xtend.two.R;
import com.ido.life.base.BaseActivity;
import com.ido.life.module.device.presenter.WebViewPresenter;

/* JADX INFO: loaded from: classes2.dex */
public class NativeWebViewActivity extends BaseActivity<WebViewPresenter> {
    private static final String TAG = NativeWebViewActivity.class.getSimpleName();
    private int mType;
    private String mUrl;

    @BindView(R.id.native_webview)
    WebView mWebView;

    @Override // com.ido.common.base.BaseCoreActivity
    public int getLayoutResId() {
        return R.layout.activity_native_webview;
    }

    @Override // com.ido.common.base.BaseCoreActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mUrl = getIntent().getStringExtra(CommonWebViewActivity.FORM_URL);
        this.mType = getIntent().getIntExtra("type", 1);
        this.mUrl = ((WebViewPresenter) this.mPresenter).getUrlByType(this.mType, this.mUrl);
        this.mWebView = (WebView) findViewById(R.id.native_webview);
        this.mWebView.setBackgroundColor(getColor(R.color.black));
        this.mWebView.getBackground().setAlpha(255);
        this.mWebView.setLayerType(2, null);
        this.mWebView.getSettings().setJavaScriptEnabled(true);
        this.mWebView.setWebViewClient(new WebViewClient());
        this.mWebView.loadUrl(this.mUrl);
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        initTitle();
    }

    private void initTitle() {
        String languageText = "";
        switch (this.mType) {
            case 1:
                languageText = getLanguageText(R.string.device_tutorial);
                break;
            case 2:
                languageText = getLanguageText(R.string.mine_user_agreement);
                break;
            case 3:
                languageText = getLanguageText(R.string.logn_detail_policy_ios);
                break;
            case 5:
                languageText = getLanguageText(R.string.bind_link_help);
                break;
            case 6:
                languageText = getLanguageText(R.string.help);
                break;
            case 7:
                languageText = getLanguageText(R.string.user_background_help);
                break;
            case 8:
                languageText = getLanguageText(R.string.user_manual);
                break;
            case 10:
                languageText = getLanguageText(R.string.alexa_help_tip_child_str);
                break;
        }
        this.mTitleBar.setTitle(languageText);
    }
}