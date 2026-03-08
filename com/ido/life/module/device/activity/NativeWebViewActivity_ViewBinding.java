package com.ido.life.module.device.activity;

import android.view.View;
import android.webkit.WebView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes2.dex */
public class NativeWebViewActivity_ViewBinding implements Unbinder {
    private NativeWebViewActivity target;

    public NativeWebViewActivity_ViewBinding(NativeWebViewActivity nativeWebViewActivity) {
        this(nativeWebViewActivity, nativeWebViewActivity.getWindow().getDecorView());
    }

    public NativeWebViewActivity_ViewBinding(NativeWebViewActivity nativeWebViewActivity, View view) {
        this.target = nativeWebViewActivity;
        nativeWebViewActivity.mWebView = (WebView) Utils.findRequiredViewAsType(view, R.id.native_webview, "field 'mWebView'", WebView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        NativeWebViewActivity nativeWebViewActivity = this.target;
        if (nativeWebViewActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        nativeWebViewActivity.mWebView = null;
    }
}