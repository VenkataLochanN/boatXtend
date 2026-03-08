package com.amazon.identity.auth.device.authorization;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import com.amazon.identity.auth.device.AuthError;
import com.amazon.identity.auth.device.appid.ThirdPartyAppIdentifier;
import com.amazon.identity.auth.device.authorization.api.AuthorizationListener;
import com.amazon.identity.auth.device.authorization.api.AuthzConstants;
import com.amazon.identity.auth.device.endpoint.TokenVendor;
import com.amazon.identity.auth.device.utils.LWAConstants;
import com.amazon.identity.auth.map.device.utils.MAPLog;

/* JADX INFO: loaded from: classes.dex */
public final class AuthorizationResponseProcessor {
    private static final String CODE_KEY = "code";
    private static final String LOG_TAG = AuthorizationResponseProcessor.class.getName();

    public static void handleResponse(Context context, Uri uri, String[] strArr, boolean z, final AuthorizationListener authorizationListener) {
        AuthorizationResponseParser authorizationResponseParser = new AuthorizationResponseParser();
        MAPLog.pii(LOG_TAG, "Received response from WebBroswer for OAuth2 flow", "response=" + uri.toString());
        try {
            Bundle bundleExtractResultsBundle = authorizationResponseParser.extractResultsBundle(uri, strArr);
            if (bundleExtractResultsBundle.containsKey(AuthzConstants.BUNDLE_KEY.CAUSE_ID.val)) {
                authorizationListener.onCancel(bundleExtractResultsBundle);
                return;
            }
            if (bundleExtractResultsBundle.getBoolean(AuthzConstants.BUNDLE_KEY.GET_AUTH_CODE.val, false)) {
                AuthorizationHelper.sendAuthorizationCodeAsResponse(bundleExtractResultsBundle.getString("code"), InternalAuthManager.getInstance(context).getClientId(), InternalAuthManager.getInstance(context).getRedirectURI(context), authorizationListener);
            } else {
                Bundle bundle = new Bundle();
                bundle.putBoolean(LWAConstants.AUTHORIZE_BUNDLE_KEY.RETURN_ACCESS_TOKEN.val, z);
                new AuthorizationHelper().doCodeForTokenExchange(context, context.getPackageName(), CodeChallengeWorkflow.getInstance().getCodeVerifier(), bundleExtractResultsBundle, false, null, new TokenVendor(), new ThirdPartyAppIdentifier(), bundle, new AuthorizationListener() { // from class: com.amazon.identity.auth.device.authorization.AuthorizationResponseProcessor.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.amazon.identity.auth.device.api.Listener
                    public void onSuccess(Bundle bundle2) {
                        MAPLog.w(AuthorizationResponseProcessor.LOG_TAG, "Code for Token Exchange success");
                        AuthorizationListener authorizationListener2 = authorizationListener;
                        if (authorizationListener2 != null) {
                            authorizationListener2.onSuccess(bundle2);
                        }
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.amazon.identity.auth.device.api.Listener
                    public void onError(AuthError authError) {
                        MAPLog.w(AuthorizationResponseProcessor.LOG_TAG, "Code for Token Exchange Error. " + authError.getMessage());
                        AuthorizationListener authorizationListener2 = authorizationListener;
                        if (authorizationListener2 != null) {
                            authorizationListener2.onError(authError);
                        }
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.amazon.identity.auth.device.api.CancellableListener
                    public void onCancel(Bundle bundle2) {
                        MAPLog.w(AuthorizationResponseProcessor.LOG_TAG, "Code for Token Exchange Cancel");
                        AuthorizationListener authorizationListener2 = authorizationListener;
                        if (authorizationListener2 != null) {
                            authorizationListener2.onCancel(bundle2);
                        }
                    }
                });
            }
        } catch (AuthError e2) {
            if (authorizationListener != null) {
                authorizationListener.onError(e2);
            }
        }
    }
}