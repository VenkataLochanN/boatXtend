package com.ido.life.module.user.set.data.strava;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.common.dialog.CommBottomConfirmDialog;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.net.http.IHttpCallback;
import com.ido.common.utils.AppUtil;
import com.ido.common.utils.GsonUtil;
import com.ido.common.utils.NetworkUtil;
import com.ido.common.utils.ResourceUtil;
import com.ido.common.utils.StatusBarUtil;
import com.ido.life.base.BaseActivity;
import com.ido.life.base.BaseMessage;
import com.ido.life.constants.Constants;
import com.ido.life.customview.NormalToast;
import com.ido.life.module.user.set.data.StravaData;
import com.ido.life.util.SPUtils;
import com.ido.life.util.eventbus.EventBusHelper;
import java.util.HashMap;

/* JADX INFO: loaded from: classes3.dex */
public class StravaAccreditActivity extends BaseActivity {
    private static final String TAG = "StravaAccreditActivity";
    private Activity mActivity;

    @BindView(R.id.progressbar)
    ProgressBar mProgressbar;
    private Resources mRes;
    private String mToken;

    @BindView(R.id.tv_agree)
    TextView mTvAgree;

    @BindView(R.id.tv_disagree)
    TextView mTvDisagree;

    @BindView(R.id.tv_strava_privacy_policy)
    TextView mTvStravaPrivacyPolicy;

    @BindView(R.id.tv_strava_tips)
    TextView mTvStravaTips;

    @BindView(R.id.tv_strava_title)
    TextView mTvStravaTitle;

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_strava_accredit;
    }

    @Override // com.ido.life.base.BaseActivity
    protected boolean needEventBus() {
        return true;
    }

    @Override // com.ido.common.base.BaseCoreActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        handleIntent(getIntent());
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.common.base.BaseCoreActivity
    public void initViews() {
        this.mTitleBar.setTitle(getLanguageText(R.string.me_strava));
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        Uri data;
        if (TextUtils.isEmpty(intent.getAction()) || (data = intent.getData()) == null) {
            return;
        }
        String[] strArrSplit = data.toString().split("=");
        setWaitBrowserState(true);
        StravaModel.getStravaRefteshToken(strArrSplit[1], new IHttpCallback<String>() { // from class: com.ido.life.module.user.set.data.strava.StravaAccreditActivity.1
            @Override // com.ido.common.net.http.IHttpCallback
            public void onSuccess(String str) {
                CommonLogUtil.d(StravaAccreditActivity.TAG, "onSuccess: " + str);
                StravaData stravaData = (StravaData) GsonUtil.fromJson(str, StravaData.class);
                if (stravaData == null) {
                    return;
                }
                SPUtils.put(Constants.STRAVA_ACCESS_TOKEN, stravaData.getAccess_token());
                EventBusHelper.post(601);
                EventBusHelper.post(602);
            }

            @Override // com.ido.common.net.http.IHttpCallback
            public void onFaild(String str) {
                CommonLogUtil.d(StravaAccreditActivity.TAG, "onFaild: " + str);
                EventBusHelper.post(601);
                EventBusHelper.post(603);
            }
        });
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.life.util.eventbus.IHandlerEventBus
    public void handleMessage(BaseMessage baseMessage) {
        super.handleMessage(baseMessage);
        switch (baseMessage.getType()) {
            case 601:
                setWaitBrowserState(false);
                initAccreditUi();
                break;
            case 602:
                NormalToast.showToast(ResourceUtil.getString(R.string.me_accredit_success));
                break;
            case 603:
                NormalToast.showToast(ResourceUtil.getString(R.string.me_accredit_failed));
                break;
            case 604:
                NormalToast.showToast(ResourceUtil.getString(R.string.me_accredit_cancel_success));
                break;
            case 605:
                NormalToast.showToast(ResourceUtil.getString(R.string.me_accredit_cancel_failed));
                break;
        }
    }

    private void setWaitBrowserState(boolean z) {
        ProgressBar progressBar = this.mProgressbar;
        if (progressBar != null) {
            progressBar.setVisibility(z ? 0 : 8);
        }
        TextView textView = this.mTvAgree;
        if (textView != null) {
            textView.setClickable(!z);
        }
        TextView textView2 = this.mTvDisagree;
        if (textView2 != null) {
            textView2.setClickable(!z);
        }
    }

    @Override // com.ido.common.base.BaseCoreActivity
    public void initData() {
        this.mActivity = this;
        this.mRes = this.mActivity.getResources();
    }

    @Override // com.ido.life.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        initAccreditUi();
    }

    @Override // com.ido.life.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initTitleBar() {
        StatusBarUtil.StatusBarLightMode(this);
    }

    private void initAccreditUi() {
        this.mToken = (String) SPUtils.get(Constants.STRAVA_ACCESS_TOKEN, "");
        if (!TextUtils.isEmpty(this.mToken)) {
            this.mTvAgree.setVisibility(8);
            this.mTvDisagree.setText(R.string.me_disconnect);
            this.mTvDisagree.setBackgroundResource(R.drawable.shape_btn_ok);
            this.mTvStravaTitle.setText(R.string.me_strava_connected);
            this.mTvStravaTips.setText(R.string.me_strava_connected_tips);
            return;
        }
        this.mTvAgree.setVisibility(0);
        this.mTvDisagree.setText(R.string.me_disagree);
        this.mTvDisagree.setBackgroundResource(R.drawable.shape_btn_no);
        this.mTvStravaTitle.setText(R.string.me_strava_accredit_title);
        this.mTvStravaTips.setText(R.string.me_strava_accredit_tips);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (100 != i || intent == null) {
            return;
        }
        if (intent.getBooleanExtra(Constants.STRAVA_ACCREDIT_RESULT, false)) {
            showToast(R.string.me_accredit_success);
        } else {
            showToast(R.string.me_accredit_failed);
        }
    }

    private void removeAllCookie() {
        CookieSyncManager cookieSyncManagerCreateInstance = CookieSyncManager.createInstance(this.mActivity);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.removeSessionCookie();
        cookieManager.removeAllCookie();
        cookieSyncManagerCreateInstance.sync();
    }

    @OnClick({R.id.tv_strava_privacy_policy, R.id.tv_disagree, R.id.tv_agree})
    public void onViewClicked(View view) {
        Intent intent = new Intent(this.mActivity, (Class<?>) StravaWebActivity.class);
        int id = view.getId();
        if (id == R.id.tv_agree) {
            showAgreeDialog();
            return;
        }
        if (id == R.id.tv_disagree) {
            showDisagreeDialog();
        } else {
            if (id != R.id.tv_strava_privacy_policy) {
                return;
            }
            intent.putExtra("type", 2);
            startActivity(intent);
        }
    }

    private void showDisagreeDialog() {
        CommBottomConfirmDialog.newInstance(getString(R.string.register_cloud_sync_title), getString(R.string.strava_dialog_disagree), getString(R.string.me_disagree), getString(R.string.sport_device_add_no), true).setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.user.set.data.strava.StravaAccreditActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                StravaAccreditActivity.this.mToken = (String) SPUtils.get(Constants.STRAVA_ACCESS_TOKEN, "");
                if (TextUtils.isEmpty(StravaAccreditActivity.this.mToken)) {
                    StravaAccreditActivity.this.finish();
                } else {
                    StravaAccreditActivity stravaAccreditActivity = StravaAccreditActivity.this;
                    stravaAccreditActivity.cancelAccredit(stravaAccreditActivity.mToken, new IHttpCallback<Boolean>() { // from class: com.ido.life.module.user.set.data.strava.StravaAccreditActivity.2.1
                        @Override // com.ido.common.net.http.IHttpCallback
                        public void onSuccess(Boolean bool) {
                            if (StravaAccreditActivity.this.isDestroyed()) {
                                return;
                            }
                            StravaAccreditActivity.this.mProgressbar.setVisibility(8);
                            if (bool.booleanValue()) {
                                EventBusHelper.post(601);
                                EventBusHelper.post(604);
                            } else {
                                EventBusHelper.post(605);
                            }
                        }

                        @Override // com.ido.common.net.http.IHttpCallback
                        public void onFaild(String str) {
                            if (StravaAccreditActivity.this.isDestroyed()) {
                                return;
                            }
                            StravaAccreditActivity.this.mProgressbar.setVisibility(8);
                        }
                    });
                }
            }
        }).show(getSupportFragmentManager());
    }

    private void showAgreeDialog() {
        CommBottomConfirmDialog.newInstance(getString(R.string.register_cloud_sync_title), getString(R.string.strava_dialog_agree), getString(R.string.login_agree_continue), getString(R.string.me_disagree), true).setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.user.set.data.strava.StravaAccreditActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (NetworkUtil.isConnected(StravaAccreditActivity.this.getBaseContext())) {
                    StravaAccreditActivity.this.openUrlByBrowser();
                } else {
                    StravaAccreditActivity stravaAccreditActivity = StravaAccreditActivity.this;
                    stravaAccreditActivity.showToast(stravaAccreditActivity.getLanguageText(R.string.logn_all_pleaseCheckNetWork_ios));
                }
            }
        }).show(getSupportFragmentManager());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openUrlByBrowser() {
        Intent intent = new Intent("android.intent.action.VIEW");
        if (AppUtil.isInstallChrome(this)) {
            intent.setPackage("com.android.chrome");
        } else if (AppUtil.isInstallFireFox(this)) {
            intent.setPackage("org.mozilla.firefox");
        } else if (AppUtil.isInstallOpera(this)) {
            intent.setPackage("com.opera.browser");
        }
        intent.setData(Uri.parse(StravaModel.URL_STRAVA));
        intent.putExtra("android:scheme", "verfit");
        startActivity(intent);
    }

    public void cancelAccredit(String str, final IHttpCallback<Boolean> iHttpCallback) {
        HashMap map = new HashMap();
        map.put("access_token", str);
        StravaModel.cancelStravaAuth(map, new IHttpCallback<String>() { // from class: com.ido.life.module.user.set.data.strava.StravaAccreditActivity.4
            @Override // com.ido.common.net.http.IHttpCallback
            public void onSuccess(String str2) {
                SPUtils.put(Constants.STRAVA_ACCESS_TOKEN, "");
                IHttpCallback iHttpCallback2 = iHttpCallback;
                if (iHttpCallback2 != null) {
                    iHttpCallback2.onSuccess(true);
                }
            }

            @Override // com.ido.common.net.http.IHttpCallback
            public void onFaild(String str2) {
                SPUtils.put(Constants.STRAVA_ACCESS_TOKEN, "");
                IHttpCallback iHttpCallback2 = iHttpCallback;
                if (iHttpCallback2 != null) {
                    iHttpCallback2.onSuccess(true);
                }
            }
        });
    }
}