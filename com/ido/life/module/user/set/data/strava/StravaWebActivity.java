package com.ido.life.module.user.set.data.strava;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import butterknife.BindView;
import com.boat.Xtend.two.R;
import com.ido.common.utils.NetworkUtil;
import com.ido.life.base.BaseActivity;
import com.ido.life.constants.Constants;
import com.ido.life.util.SPUtils;

/* JADX INFO: loaded from: classes3.dex */
public class StravaWebActivity extends BaseActivity {

    @BindView(R.id.layout_reload)
    LinearLayout mLayoutReload;

    @BindView(R.id.progressbar)
    ProgressBar mProgressbar;
    private Resources mRes;
    private int mType;

    @BindView(R.id.wv_strava)
    WebView mWvStrava;

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_strava_web;
    }

    @Override // com.ido.common.base.BaseCoreActivity
    public void initData() {
        this.mType = getIntent().getIntExtra("type", -1);
        initEvent();
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.common.base.BaseCoreActivity
    public void initViews() {
        this.mTitleBar.setTitle(getLanguageText(R.string.me_strava));
    }

    @Override // com.ido.common.base.BaseCoreActivity
    public void initEvent() {
        WebSettings settings = this.mWvStrava.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setLoadWithOverviewMode(true);
        if (NetworkUtil.isConnected(this)) {
            settings.setCacheMode(2);
        } else {
            settings.setCacheMode(1);
        }
        this.mWvStrava.setWebViewClient(new WebViewClient() { // from class: com.ido.life.module.user.set.data.strava.StravaWebActivity.1
            @Override // android.webkit.WebViewClient
            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                super.onPageStarted(webView, str, bitmap);
                StravaWebActivity stravaWebActivity = StravaWebActivity.this;
                stravaWebActivity.setViewVisibility(stravaWebActivity.mProgressbar, 0);
                StravaWebActivity stravaWebActivity2 = StravaWebActivity.this;
                stravaWebActivity2.setViewVisibility(stravaWebActivity2.mLayoutReload, 8);
            }

            @Override // android.webkit.WebViewClient
            public void onPageFinished(WebView webView, String str) {
                Log.d("url=", "onPageFinished:" + str);
                super.onPageFinished(webView, str);
                StravaWebActivity stravaWebActivity = StravaWebActivity.this;
                stravaWebActivity.setViewVisibility(stravaWebActivity.mProgressbar, 8);
            }

            @Override // android.webkit.WebViewClient
            public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
                super.onReceivedError(webView, webResourceRequest, webResourceError);
                StravaWebActivity stravaWebActivity = StravaWebActivity.this;
                stravaWebActivity.setViewVisibility(stravaWebActivity.mProgressbar, 8);
            }

            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                int unused = StravaWebActivity.this.mType;
                Log.d("url=", "shouldOverrideUrlLoading:" + str);
                return super.shouldOverrideUrlLoading(webView, str);
            }

            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
                if (1 == StravaWebActivity.this.mType) {
                    int i = Build.VERSION.SDK_INT;
                }
                return super.shouldOverrideUrlLoading(webView, webResourceRequest);
            }
        });
        int i = this.mType;
        if (1 == i) {
            this.mWvStrava.loadUrl(StravaModel.URL_STRAVA);
        } else if (2 == i) {
            this.mWvStrava.loadUrl("https://www.strava.com/legal/privacy");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setViewVisibility(View view, int i) {
        if (isDestroyed() || view == null) {
            return;
        }
        view.setVisibility(i);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        setAccreditResult();
    }

    private void setAccreditResult() {
        String str = (String) SPUtils.get(Constants.STRAVA_ACCESS_TOKEN, "");
        Intent intent = new Intent();
        intent.putExtra(Constants.STRAVA_ACCREDIT_RESULT, !TextUtils.isEmpty(str));
        setResult(100, intent);
        finish();
    }
}