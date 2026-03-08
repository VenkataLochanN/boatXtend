package com.ido.life.data.me.remote;

import android.os.Handler;
import android.os.Message;
import com.boat.Xtend.two.R;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.ido.common.log.CommonLogUtil;
import com.ido.life.data.me.remote.AccountManager;
import okhttp3.internal.platform.Platform;

/* JADX INFO: loaded from: classes2.dex */
public class ThirdLoginManager {
    private static final int MSG_FAILED = 102;
    private static final int MSG_SUCCESS = 101;
    private static final String PLATFORM_FACEBOOK = "facebook";
    private static final String PLATFORM_GOOGLE_PLUS = "google";
    private static final String PLATFORM_QQ = "qq";
    private static final String PLATFORM_TWITTER = "twitter";
    private static final String PLATFORM_WEIXIN = "weixin";
    public static final String TAG = "ThirdLoginManager";
    private static Handler mHandler = new Handler(new Handler.Callback() { // from class: com.ido.life.data.me.remote.ThirdLoginManager.1
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i = message.what;
            if (i != 101) {
                if (i != 102 || ThirdLoginManager.sThirdCallback == null) {
                    return false;
                }
                ThirdLoginManager.sThirdCallback.onFailed((String) message.obj);
                return false;
            }
            if (ThirdLoginManager.sThirdCallback == null) {
                return false;
            }
            ThirdLoginManager.sThirdCallback.onSuccess(ThirdLoginManager.mPlatformName, (AccountManager.AuthData) message.obj);
            return false;
        }
    });
    private static String mPlatformName;
    private static OnThirdCallback sThirdCallback;
    GoogleSignInAccount account;

    public interface OnThirdCallback {
        void onFailed(String str);

        void onSuccess(String str, AccountManager.AuthData authData);
    }

    private static void authorize(Platform platform) {
    }

    private static String generatePlatFormName(int i) {
        return i == R.id.login_qq ? "qq" : i == R.id.login_twitter ? "twitter" : i == R.id.login_facebook ? "facebook" : i == R.id.login_weixin ? PLATFORM_WEIXIN : i == R.id.login_google ? PLATFORM_GOOGLE_PLUS : "";
    }

    public static void thirdLogin(int i, OnThirdCallback onThirdCallback) {
    }

    public static void thirdLogin(String str, OnThirdCallback onThirdCallback) {
    }

    private static void requestFacebook(AccountManager.AuthData authData) {
        CommonLogUtil.d(TAG, "requestFacebook: " + authData.toJsonString());
    }
}