package com.ido.life.web;

import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import com.ido.common.IdoApp;
import com.ido.common.base.BaseCoreActivity;
import com.ido.common.utils.AppInfoUtil;

/* JADX INFO: loaded from: classes3.dex */
public class SimpleJSInterface {
    public static final String INTERFACE_NAME = "eyesir_js";
    public BaseCoreActivity mActivity;
    private WebView mWebView;

    @JavascriptInterface
    public void setTitle(String str) {
    }

    public SimpleJSInterface(BaseCoreActivity baseCoreActivity, WebView webView) {
        this.mActivity = baseCoreActivity;
        this.mWebView = webView;
    }

    @JavascriptInterface
    public String getVersion() {
        return AppInfoUtil.getVersionName(IdoApp.getAppContext());
    }

    @JavascriptInterface
    public int getVersionCode() {
        return AppInfoUtil.getVersionCode(IdoApp.getAppContext());
    }
}