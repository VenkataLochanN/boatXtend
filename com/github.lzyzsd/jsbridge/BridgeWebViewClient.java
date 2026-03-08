package com.github.lzyzsd.jsbridge;

import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.bumptech.glide.load.Key;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class BridgeWebViewClient extends WebViewClient {
    private BridgeWebView webView;

    public BridgeWebViewClient(BridgeWebView bridgeWebView) {
        this.webView = bridgeWebView;
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        try {
            str = URLDecoder.decode(str, Key.STRING_CHARSET_NAME);
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        if (str.startsWith("yy://return/")) {
            this.webView.handlerReturnData(str);
            return true;
        }
        if (str.startsWith("yy://")) {
            this.webView.flushMessageQueue();
            return true;
        }
        return super.shouldOverrideUrlLoading(webView, str);
    }

    @Override // android.webkit.WebViewClient
    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        super.onPageStarted(webView, str, bitmap);
    }

    @Override // android.webkit.WebViewClient
    public void onPageFinished(WebView webView, String str) throws Throwable {
        super.onPageFinished(webView, str);
        BridgeUtil.webViewLoadLocalJs(webView, BridgeWebView.toLoadJs);
        if (this.webView.getStartupMessage() != null) {
            Iterator<Message> it = this.webView.getStartupMessage().iterator();
            while (it.hasNext()) {
                this.webView.dispatchMessage(it.next());
            }
            this.webView.setStartupMessage(null);
        }
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedError(WebView webView, int i, String str, String str2) {
        super.onReceivedError(webView, i, str, str2);
    }
}