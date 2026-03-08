package com.ido.life.module.device.activity;

import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.ido.life.customview.viewgroup.CommLoadingView;
import com.ido.smartrefresh.layout.SmartRefreshLayout;

/* JADX INFO: loaded from: classes2.dex */
public class CommonWebViewActivity_ViewBinding implements Unbinder {
    private CommonWebViewActivity target;

    public CommonWebViewActivity_ViewBinding(CommonWebViewActivity commonWebViewActivity) {
        this(commonWebViewActivity, commonWebViewActivity.getWindow().getDecorView());
    }

    public CommonWebViewActivity_ViewBinding(CommonWebViewActivity commonWebViewActivity, View view) {
        this.target = commonWebViewActivity;
        commonWebViewActivity.mWebView = (BridgeWebView) Utils.findRequiredViewAsType(view, R.id.web_view, "field 'mWebView'", BridgeWebView.class);
        commonWebViewActivity.mRefreshLayout = (SmartRefreshLayout) Utils.findRequiredViewAsType(view, R.id.refreshLayout, "field 'mRefreshLayout'", SmartRefreshLayout.class);
        commonWebViewActivity.mLayParent = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_parent, "field 'mLayParent'", LinearLayout.class);
        commonWebViewActivity.mLayoutNetworkError = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_network_error, "field 'mLayoutNetworkError'", LinearLayout.class);
        commonWebViewActivity.mCommLoadingView = (CommLoadingView) Utils.findRequiredViewAsType(view, R.id.comm_loading_view, "field 'mCommLoadingView'", CommLoadingView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        CommonWebViewActivity commonWebViewActivity = this.target;
        if (commonWebViewActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        commonWebViewActivity.mWebView = null;
        commonWebViewActivity.mRefreshLayout = null;
        commonWebViewActivity.mLayParent = null;
        commonWebViewActivity.mLayoutNetworkError = null;
        commonWebViewActivity.mCommLoadingView = null;
    }
}