package com.ido.life.module.device.activity;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import butterknife.BindView;
import com.boat.Xtend.two.R;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.BridgeWebViewClient;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.NetworkUtil;
import com.ido.life.base.BaseActivity;
import com.ido.life.customview.viewgroup.CommLoadingView;
import com.ido.life.module.device.presenter.WebViewPresenter;
import com.ido.life.module.device.view.ICommWebView;
import com.ido.life.util.FileUtil;
import com.ido.smartrefresh.layout.SmartRefreshLayout;
import com.ido.smartrefresh.layout.api.RefreshLayout;
import com.ido.smartrefresh.layout.listener.OnRefreshListener;

/* JADX INFO: loaded from: classes2.dex */
public class CommonWebViewActivity extends BaseActivity<WebViewPresenter> implements ICommWebView, OnRefreshListener {
    public static final String FORM_URL = "form_url";
    private static final String TAG = CommonWebViewActivity.class.getSimpleName();
    public static final String TYPE = "type";
    private boolean isCurrentHelpHome = false;
    private boolean loadError;

    @BindView(R.id.comm_loading_view)
    CommLoadingView mCommLoadingView;

    @BindView(R.id.layout_parent)
    LinearLayout mLayParent;

    @BindView(R.id.layout_network_error)
    LinearLayout mLayoutNetworkError;

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    private int mType;
    private String mUrl;

    @BindView(R.id.web_view)
    BridgeWebView mWebView;

    @Override // com.ido.common.base.BaseCoreActivity
    public int getLayoutResId() {
        return R.layout.activity_comm_webview;
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        this.mType = getIntent().getIntExtra("type", 1);
        this.mUrl = getIntent().getStringExtra(FORM_URL);
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.common.base.BaseCoreActivity
    public void initViews() {
        super.initViews();
        this.mTitleBar.setLeftOnClick(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$CommonWebViewActivity$3k6JbKjGntafMpD2Un3yYYdhIl8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initViews$0$CommonWebViewActivity(view);
            }
        });
    }

    public /* synthetic */ void lambda$initViews$0$CommonWebViewActivity(View view) {
        onBackPressed();
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        initWebView();
        this.mRefreshLayout.setOnRefreshListener(this);
        this.mCommLoadingView.setVisibility(0);
        this.mUrl = ((WebViewPresenter) this.mPresenter).getUrlByType(this.mType, this.mUrl);
        CommonLogUtil.d(TAG, this.mUrl);
        this.mWebView.loadUrl(this.mUrl);
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        initTitle();
    }

    private void initWebView() {
        WebSettings settings = this.mWebView.getSettings();
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        settings.setAllowFileAccess(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setLoadsImagesAutomatically(true);
        settings.setDefaultTextEncodingName("utf-8");
        this.mWebView.setBackgroundColor(getColor(R.color.black));
        this.mWebView.getBackground().setAlpha(255);
        this.mWebView.setLayerType(2, null);
        if (NetworkUtil.isConnected(this)) {
            settings.setCacheMode(2);
        } else {
            settings.setCacheMode(1);
        }
        BridgeWebView bridgeWebView = this.mWebView;
        bridgeWebView.setWebViewClient(new BridgeWebViewClient(bridgeWebView) { // from class: com.ido.life.module.device.activity.CommonWebViewActivity.1
            @Override // com.github.lzyzsd.jsbridge.BridgeWebViewClient, android.webkit.WebViewClient
            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                super.onPageStarted(webView, str, bitmap);
                if (CommonWebViewActivity.this.isDestroyed()) {
                    return;
                }
                CommonWebViewActivity.this.loadError = false;
                if (CommonWebViewActivity.this.mLayoutNetworkError != null) {
                    CommonWebViewActivity.this.mLayoutNetworkError.setVisibility(8);
                }
            }

            @Override // com.github.lzyzsd.jsbridge.BridgeWebViewClient, android.webkit.WebViewClient
            public void onPageFinished(WebView webView, String str) throws Throwable {
                super.onPageFinished(webView, str);
                if (CommonWebViewActivity.this.isDestroyed() || CommonWebViewActivity.this.mRefreshLayout == null) {
                    return;
                }
                CommonWebViewActivity.this.mRefreshLayout.finishRefresh();
                CommonWebViewActivity.this.mCommLoadingView.setVisibility(8);
                if (CommonWebViewActivity.this.loadError) {
                    CommonWebViewActivity.this.mRefreshLayout.setEnableRefresh(true);
                    CommonWebViewActivity.this.mWebView.setVisibility(8);
                    CommonWebViewActivity.this.mLayoutNetworkError.setVisibility(0);
                } else {
                    CommonWebViewActivity.this.mLayoutNetworkError.setVisibility(8);
                    CommonWebViewActivity.this.mWebView.setVisibility(0);
                    CommonWebViewActivity.this.mRefreshLayout.setEnableRefresh(false);
                }
            }

            @Override // com.github.lzyzsd.jsbridge.BridgeWebViewClient, android.webkit.WebViewClient
            public void onReceivedError(WebView webView, int i, String str, String str2) {
                super.onReceivedError(webView, i, str, str2);
                CommonWebViewActivity.this.loadError = true;
                if (CommonWebViewActivity.this.isDestroyed()) {
                    return;
                }
                CommonWebViewActivity.this.mRefreshLayout.finishRefresh();
                CommonWebViewActivity.this.mCommLoadingView.setVisibility(8);
            }
        });
    }

    private void initTitle() {
        int i = this.mType;
        String languageText = "";
        if (i == 1) {
            languageText = getLanguageText(R.string.device_tutorial);
        } else if (i == 2) {
            languageText = getLanguageText(R.string.mine_user_agreement);
        } else if (i == 3) {
            languageText = getLanguageText(R.string.logn_detail_policy_ios);
        } else {
            switch (i) {
                case 5:
                    languageText = getLanguageText(R.string.bind_link_help);
                    break;
                case 6:
                    languageText = getLanguageText(R.string.help);
                    this.mRefreshLayout.setEnableRefresh(false);
                    this.mRefreshLayout.setEnableLoadMore(false);
                    this.mRefreshLayout.setEnableAutoLoadMore(false);
                    break;
                case 7:
                    languageText = getLanguageText(R.string.user_background_help);
                    break;
                case 8:
                    languageText = getLanguageText(R.string.user_manual);
                    break;
                case 9:
                    languageText = getLanguageText(R.string.about_fitness);
                    break;
                case 10:
                    languageText = getLanguageText(R.string.alexa_help_tip_child_str);
                    break;
                case 11:
                    break;
                case 12:
                    languageText = "Amazon";
                    break;
                default:
                    switch (i) {
                        case 18:
                            languageText = getLanguageText(R.string.sport_training_explain);
                            break;
                        case 19:
                            languageText = getLanguageText(R.string.sport_name_explain);
                            break;
                        case 20:
                            languageText = getLanguageText(R.string.sport_type_explain);
                            break;
                    }
                    break;
            }
        }
        this.mTitleBar.setTitle(languageText);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.mType == 12) {
            super.onBackPressed();
            return;
        }
        if (TextUtils.equals(this.mWebView.getUrl(), ((WebViewPresenter) this.mPresenter).getQuestionHelpUrl())) {
            super.onBackPressed();
            finish();
        } else if (!TextUtils.isEmpty(((WebViewPresenter) this.mPresenter).getQuestionHelpSearchUrl()) && this.mWebView.getUrl().contains(((WebViewPresenter) this.mPresenter).getQuestionHelpSearchUrl())) {
            this.isCurrentHelpHome = true;
            this.mWebView.clearHistory();
            this.mWebView.loadUrl(this.mUrl);
        } else if (this.mWebView.canGoBack()) {
            this.mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override // com.ido.life.module.device.view.ICommWebView
    public void onGetH5(String str) {
        this.mRefreshLayout.finishRefresh();
        this.mCommLoadingView.setVisibility(8);
        if (FileUtil.fileExists(str)) {
            this.mWebView.loadUrl("file:///android_asset/" + str);
        }
    }

    @Override // com.ido.smartrefresh.layout.listener.OnRefreshListener
    public void onRefresh(RefreshLayout refreshLayout) {
        this.mWebView.loadUrl(this.mUrl);
    }
}