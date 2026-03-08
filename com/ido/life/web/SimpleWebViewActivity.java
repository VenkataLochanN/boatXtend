package com.ido.life.web;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;
import butterknife.BindView;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.boat.Xtend.two.R;
import com.ido.common.R2;
import com.ido.common.base.BaseCoreActivity;
import com.ido.common.utils.StatusBarUtil;

/* JADX INFO: loaded from: classes3.dex */
public class SimpleWebViewActivity extends BaseCoreActivity {
    private static final String INTENT_EXTRA_TITLE = "TITLE";
    private static final String INTENT_EXTRA_URL = "URL";
    private String mTitle;

    @BindView(R2.id.title_leftBtn)
    ImageButton mTitleLeftBtn;

    @BindView(R2.id.title_text)
    TextView mTitleText;
    private int mType;
    private String mUrl;
    private WebView mWebView;
    private WebViewClient mWebViewClient = new WebViewClient() { // from class: com.ido.life.web.SimpleWebViewActivity.6
        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
        }
    };
    private CustomWebChromClient mCustomWebChromClient = new CustomWebChromClient();
    WebChromeClient mWebChromeClient = new WebChromeClient() { // from class: com.ido.life.web.SimpleWebViewActivity.7
        @Override // android.webkit.WebChromeClient
        public void onProgressChanged(WebView webView, int i) {
        }

        @Override // android.webkit.WebChromeClient
        public void onReceivedTitle(WebView webView, String str) {
            super.onReceivedTitle(webView, str);
            SimpleWebViewActivity.this.refreshTitle(str);
        }
    };

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_simple_webview;
    }

    public static void startActivity(Activity activity, String str, String str2, int i) {
        Intent intent = new Intent();
        intent.setClass(activity, SimpleWebViewActivity.class);
        intent.putExtra(INTENT_EXTRA_TITLE, str);
        intent.putExtra(INTENT_EXTRA_URL, str2);
        activity.startActivity(intent);
    }

    public static void startActivity(Context context, String str, String str2) {
        Intent intent = new Intent();
        intent.setClass(context, SimpleWebViewActivity.class);
        intent.putExtra(INTENT_EXTRA_TITLE, str);
        intent.putExtra(INTENT_EXTRA_URL, str2);
        intent.setFlags(268435456);
        context.startActivity(intent);
    }

    public static void startActivityWithToken(Activity activity, String str, String str2) {
        String strGenerateUrl = generateUrl(str, str2);
        Intent intent = new Intent();
        intent.setClass(activity, SimpleWebViewActivity.class);
        intent.putExtra(INTENT_EXTRA_URL, strGenerateUrl);
        activity.startActivity(intent);
    }

    private static String generateUrl(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        if (str.contains("?")) {
            return str + "&token=" + str2;
        }
        return str + "?token=" + str2;
    }

    @Override // com.ido.common.base.BaseCoreActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initData();
        initTitleBar();
        initUI();
        initListener();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initTitleBar() {
        StatusBarUtil.StatusBarLightMode(this);
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        Intent intent = getIntent();
        this.mTitle = intent.getStringExtra(INTENT_EXTRA_TITLE);
        this.mUrl = intent.getStringExtra(INTENT_EXTRA_URL);
    }

    private void initUI() {
        this.mWebView = (WebView) findViewById(R.id.webview);
        if (!TextUtils.isEmpty(this.mTitle)) {
            this.mTitleText.setText(this.mTitle);
        }
        initWebView();
        String str = this.mUrl;
        if (str != null) {
            if (!str.startsWith("http")) {
                this.mUrl = "http://" + this.mUrl;
            }
            this.mWebView.loadUrl(this.mUrl);
        }
    }

    private void initListener() {
        this.mTitleLeftBtn.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.web.SimpleWebViewActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SimpleWebViewActivity.this.doback();
            }
        });
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        doback();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doback() {
        if (this.mWebView.canGoBack()) {
            this.mWebView.goBack();
        } else {
            releaseWebContent();
            finish();
        }
    }

    private void initWebView() {
        WebSettings settings = this.mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(2);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setSupportZoom(false);
        settings.setBuiltInZoomControls(false);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setBlockNetworkLoads(false);
        settings.setBlockNetworkImage(false);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setAppCacheEnabled(true);
        this.mWebView.setScrollBarStyle(33554432);
        this.mWebView.requestFocus();
        this.mWebView.setOnTouchListener(new View.OnTouchListener() { // from class: com.ido.life.web.SimpleWebViewActivity.2
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                view.requestFocus();
                return false;
            }
        });
        this.mWebView.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.ido.life.web.SimpleWebViewActivity.3
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                return true;
            }
        });
        this.mWebView.setOnClickListener(new View.OnClickListener() { // from class: com.ido.life.web.SimpleWebViewActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
            }
        });
        this.mWebView.setWebViewClient(this.mWebViewClient);
        this.mWebView.setWebChromeClient(this.mCustomWebChromClient);
        WebView webView = this.mWebView;
        webView.addJavascriptInterface(new SimpleJSInterface(this, webView), SimpleJSInterface.INTERFACE_NAME);
        this.mWebView.setDownloadListener(new DownloadListener() { // from class: com.ido.life.web.SimpleWebViewActivity.5
            @Override // android.webkit.DownloadListener
            public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
                if (intent.resolveActivity(SimpleWebViewActivity.this.getPackageManager()) != null) {
                    SimpleWebViewActivity.this.startActivity(intent);
                }
            }
        });
    }

    class CustomWebChromClient extends WebChromeClient {
        private static final int FILECHOOSER_RESULTCODE = 1;
        ValueCallback<Uri[]> uploadMessages;

        CustomWebChromClient() {
        }

        @Override // android.webkit.WebChromeClient
        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
            this.uploadMessages = valueCallback;
            Intent intent = new Intent("android.intent.action.GET_CONTENT");
            intent.addCategory("android.intent.category.OPENABLE");
            intent.setType(generateType(fileChooserParams.getAcceptTypes()));
            SimpleWebViewActivity.this.startActivityForResult(Intent.createChooser(intent, "File Chooser"), 1);
            return true;
        }

        private String generateType(String[] strArr) {
            StringBuilder sb = new StringBuilder();
            if (strArr != null && strArr.length > 0) {
                for (int i = 0; i < strArr.length; i++) {
                    if (i > 0) {
                        sb.append(AppInfo.DELIM);
                    }
                    sb.append(strArr[i]);
                }
            }
            String string = sb.toString();
            return TextUtils.isEmpty(string) ? "*/*" : string;
        }

        public void onActivityResult(int i, int i2, Intent intent) {
            if (i == 1) {
                Uri data = (intent == null || i2 != -1) ? null : intent.getData();
                ValueCallback<Uri[]> valueCallback = this.uploadMessages;
                if (valueCallback != null) {
                    if (data == null) {
                        valueCallback.onReceiveValue(null);
                    } else {
                        valueCallback.onReceiveValue(new Uri[]{data});
                    }
                    this.uploadMessages = null;
                }
            }
        }

        @Override // android.webkit.WebChromeClient
        public void onReceivedTitle(WebView webView, String str) {
            super.onReceivedTitle(webView, str);
            if (TextUtils.isEmpty(SimpleWebViewActivity.this.mTitle)) {
                SimpleWebViewActivity.this.refreshTitle(str);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshTitle(String str) {
        TextView textView;
        if (TextUtils.isEmpty(str) || (textView = this.mTitleText) == null) {
            return;
        }
        textView.setText(str);
    }

    private void releaseWebContent() {
        this.mWebView.setWebViewClient(null);
        this.mWebView.setWebChromeClient(null);
        this.mWebView.loadData("<html></html>", "text/html", "utf-8");
        this.mUrl = null;
    }

    private void reloadCurUrl() {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.ido.life.web.SimpleWebViewActivity.8
            @Override // java.lang.Runnable
            public void run() {
                SimpleWebViewActivity.this.mWebView.reload();
            }
        });
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        this.mCustomWebChromClient.onActivityResult(i, i2, intent);
    }
}