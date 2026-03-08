package com.amazon.identity.auth.device.authorization;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import com.amazon.identity.auth.device.AuthError;
import com.amazon.identity.auth.device.RequestManager;
import com.amazon.identity.auth.device.StoredPreferences;
import com.amazon.identity.auth.device.api.authorization.AuthorizationManager;
import com.amazon.identity.auth.device.api.authorization.AuthorizeRequest;
import com.amazon.identity.auth.device.appid.ThirdPartyAppIdentifier;
import com.amazon.identity.auth.device.authorization.api.AuthorizationListener;
import com.amazon.identity.auth.device.authorization.api.AuthzConstants;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.amazon.identity.auth.device.dataobject.RequestedScope;
import com.amazon.identity.auth.device.datastore.DatabaseHelper;
import com.amazon.identity.auth.device.datastore.ProfileDataSource;
import com.amazon.identity.auth.device.endpoint.TokenVendor;
import com.amazon.identity.auth.device.thread.ThreadUtils;
import com.amazon.identity.auth.device.utils.DefaultLibraryInfo;
import com.amazon.identity.auth.device.utils.LWAConstants;
import com.amazon.identity.auth.device.utils.LWAServiceWrapper;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ThirdPartyAuthorizationHelper extends AuthorizationHelper {
    private static final String CLIENT_ID_PARAM_NAME = "client_id";
    private static final String CODE_KEY = "code";
    private static final String LOG_TAG = ThirdPartyAuthorizationHelper.class.getName();
    private static final String SCOPE_DATA_KEY = "scope_data";
    private CodeChallengeWorkflow codeChallengeWorkflow;
    private ThirdPartyServiceHelper mThirdPartyServiceHelper;

    public ThirdPartyAuthorizationHelper() {
        this(new ThirdPartyServiceHelper());
    }

    public ThirdPartyAuthorizationHelper(ThirdPartyServiceHelper thirdPartyServiceHelper) {
        this.codeChallengeWorkflow = CodeChallengeWorkflow.getInstance();
        this.mThirdPartyServiceHelper = thirdPartyServiceHelper;
    }

    public void authorize(Context context, String str, String str2, String str3, String[] strArr, boolean z, TokenVendor tokenVendor, AuthorizationListener authorizationListener) throws AuthError {
        authorize(null, context, str, str2, str3, strArr, z, tokenVendor, authorizationListener, Bundle.EMPTY);
    }

    public void authorize(Context context, String str, String str2, String str3, String[] strArr, boolean z, TokenVendor tokenVendor, AuthorizationListener authorizationListener, Bundle bundle) throws AuthError {
        authorize(null, context, str, str2, str3, strArr, z, tokenVendor, authorizationListener, bundle);
    }

    public void authorize(final AuthorizeRequest authorizeRequest, final Context context, String str, final String str2, String str3, String[] strArr, final boolean z, TokenVendor tokenVendor, final AuthorizationListener authorizationListener, Bundle bundle) throws AuthError {
        Bundle bundle2 = bundle;
        if (ThreadUtils.isRunningOnMainThread()) {
            MAPLog.e(LOG_TAG, "authorize started on main thread");
            throw new IllegalStateException("authorize started on main thread");
        }
        final AppInfo appInfo = new ThirdPartyAppIdentifier().getAppInfo(str, context);
        List<RequestedScope> cachedScopes = tokenVendor.getCachedScopes(context);
        final String[] commonScopesForAuthorization = getCommonScopesForAuthorization(context, strArr, cachedScopes);
        final boolean z2 = bundle2.getBoolean(AuthzConstants.BUNDLE_KEY.SANDBOX.val, false);
        if (bundle2 == Bundle.EMPTY) {
            bundle2 = new Bundle();
        }
        final Bundle bundle3 = bundle2;
        bundle3.putBoolean(AuthzConstants.BUNDLE_KEY.CHECK_API_KEY.val, false);
        bundle3.putBoolean(AuthzConstants.BUNDLE_KEY.RETURN_CODE.val, true);
        bundle3.putString(LWAConstants.AUTHORIZE_BUNDLE_KEY.REGION.val, AuthorizationManager.getRegion(context).getStringValue());
        bundle3.putString(LWAConstants.AUTHORIZE_BUNDLE_KEY.STAGE.val, DefaultLibraryInfo.getOverrideLibraryStage().name());
        bundle3.putString(AuthzConstants.BUNDLE_KEY.CLIENT_ID.val, str2);
        bundle3.putString(AuthzConstants.BUNDLE_KEY.SDK_VERSION.val, "LWAAndroidSDK3.0.4");
        try {
            bundle3.putBundle(AuthzConstants.BUNDLE_KEY.EXTRA_URL_PARAMS.val, getExtraUrlParams(bundle3));
            Bundle bundleStartAuthorizationWithService = Bundle.EMPTY;
            if (!z2 && (StoredPreferences.isTokenObtainedFromSSO(context) || cachedScopes == null || cachedScopes.size() == 0)) {
                bundleStartAuthorizationWithService = startAuthorizationWithService(context, commonScopesForAuthorization, bundle3);
            }
            Bundle bundle4 = bundleStartAuthorizationWithService;
            if (bundle4.containsKey("code") && !TextUtils.isEmpty(bundle4.getString("code"))) {
                if (bundle3.getBoolean(AuthzConstants.BUNDLE_KEY.GET_AUTH_CODE.val, false)) {
                    AuthorizationHelper.sendAuthorizationCodeAsResponse(bundle4.getString("code"), str2, str3, authorizationListener);
                    return;
                } else {
                    handleCodeForTokenExchange(context, str, this.codeChallengeWorkflow.getCodeVerifier(), bundle4, bundle3, authorizationListener);
                    StoredPreferences.setTokenObtainedFromSSO(context, true);
                    return;
                }
            }
            if (!bundle4.containsKey(AuthError.AUTH_ERROR_EXECEPTION) && !bundle4.containsKey(AuthzConstants.BUNDLE_KEY.AUTHORIZE.val) && !bundle4.containsKey(AuthzConstants.BUNDLE_KEY.CAUSE_ID.val)) {
                ProfileDataSource.getInstance(context).deleteAllRows();
                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.amazon.identity.auth.device.authorization.ThirdPartyAuthorizationHelper.1
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            if (z || z2) {
                                ThirdPartyAuthorizationHelper.this.authorizeWithBrowser(authorizeRequest, context, context.getPackageName(), str2, commonScopesForAuthorization, authorizationListener, bundle3, appInfo);
                                StoredPreferences.setTokenObtainedFromSSO(context, false);
                            } else {
                                authorizationListener.onError(new AuthError("WebView is not allowed for Authorization", AuthError.ERROR_TYPE.ERROR_BAD_PARAM));
                            }
                        } catch (AuthError e2) {
                            authorizationListener.onError(e2);
                        }
                    }
                });
                return;
            }
            bundle4.setClassLoader(context.getClassLoader());
            if (bundle4.containsKey(AuthzConstants.BUNDLE_KEY.CAUSE_ID.val)) {
                authorizationListener.onCancel(bundle4);
                return;
            }
            if (bundle4.containsKey(AuthError.AUTH_ERROR_EXECEPTION)) {
                authorizationListener.onError(AuthError.extractError(bundle4));
                return;
            }
            DatabaseHelper.clearAuthorizationState(context);
            Bundle bundle5 = new Bundle();
            bundle5.putString(AuthzConstants.BUNDLE_KEY.AUTHORIZE.val, "authorized via service");
            authorizationListener.onSuccess(bundle5);
        } catch (AuthError e2) {
            authorizationListener.onError(e2);
        }
    }

    private void handleCodeForTokenExchange(Context context, String str, String str2, Bundle bundle, Bundle bundle2, final AuthorizationListener authorizationListener) {
        doCodeForTokenExchange(context, str, str2, bundle, false, null, new TokenVendor(), new ThirdPartyAppIdentifier(), bundle2, new AuthorizationListener() { // from class: com.amazon.identity.auth.device.authorization.ThirdPartyAuthorizationHelper.2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.identity.auth.device.api.Listener
            public void onSuccess(Bundle bundle3) {
                MAPLog.i(ThirdPartyAuthorizationHelper.LOG_TAG, "Code for Token Exchange success");
                AuthorizationListener authorizationListener2 = authorizationListener;
                if (authorizationListener2 != null) {
                    authorizationListener2.onSuccess(bundle3);
                }
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.identity.auth.device.api.Listener
            public void onError(AuthError authError) {
                MAPLog.e(ThirdPartyAuthorizationHelper.LOG_TAG, "Code for Token Exchange Error. " + authError.getMessage());
                AuthorizationListener authorizationListener2 = authorizationListener;
                if (authorizationListener2 != null) {
                    authorizationListener2.onError(authError);
                }
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.identity.auth.device.api.CancellableListener
            public void onCancel(Bundle bundle3) {
                MAPLog.w(ThirdPartyAuthorizationHelper.LOG_TAG, "Code for Token Exchange Cancel");
                AuthorizationListener authorizationListener2 = authorizationListener;
                if (authorizationListener2 != null) {
                    authorizationListener2.onCancel(bundle3);
                }
            }
        });
    }

    private Bundle startAuthorizationWithService(Context context, final String[] strArr, final Bundle bundle) throws AuthError {
        Bundle bundleExecute = new LWAServiceWrapper<Bundle>() { // from class: com.amazon.identity.auth.device.authorization.ThirdPartyAuthorizationHelper.3
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.identity.auth.device.utils.LWAServiceWrapper
            public Bundle doWork(Context context2, AmazonAuthorizationServiceInterface amazonAuthorizationServiceInterface) throws AuthError, RemoteException {
                return ThirdPartyAuthorizationHelper.authorizeWithService(context2, strArr, amazonAuthorizationServiceInterface, bundle);
            }
        }.execute(context, this.mThirdPartyServiceHelper);
        return bundleExecute != null ? bundleExecute : new Bundle();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Bundle authorizeWithService(Context context, String[] strArr, AmazonAuthorizationServiceInterface amazonAuthorizationServiceInterface, Bundle bundle) throws AuthError, RemoteException {
        Bundle bundleAuthorize = amazonAuthorizationServiceInterface.authorize(bundle, context.getPackageName(), strArr);
        if (bundleAuthorize != null) {
            bundleAuthorize.setClassLoader(context.getClassLoader());
        }
        return bundleAuthorize;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void authorizeWithBrowser(AuthorizeRequest authorizeRequest, Context context, String str, String str2, String[] strArr, AuthorizationListener authorizationListener, Bundle bundle, AppInfo appInfo) throws AuthError {
        bundle.getBundle(AuthzConstants.BUNDLE_KEY.EXTRA_URL_PARAMS.val).remove("client_id");
        RequestManager.getInstance().executeRequest(new AuthorizationRequest(authorizeRequest, str2, strArr, bundle, appInfo, authorizationListener), context);
    }

    private Bundle getExtraUrlParams(Bundle bundle) throws AuthError {
        Bundle proofKeyParameters;
        if (bundle.getBoolean(AuthzConstants.BUNDLE_KEY.GET_AUTH_CODE.val, false)) {
            String string = bundle.getString(AuthzConstants.BUNDLE_KEY.CODE_CHALLENGE.val);
            String string2 = bundle.getString(AuthzConstants.BUNDLE_KEY.CODE_CHALLENGE_METHOD.val);
            if (TextUtils.isEmpty(string)) {
                throw new AuthError("Must provide code challenge parameter.", AuthError.ERROR_TYPE.ERROR_MISSING_CODE_CHALLENGE);
            }
            proofKeyParameters = new Bundle();
            proofKeyParameters.putString(CodeChallengeWorkflow.CODE_CHALLENGE_KEY, string);
            proofKeyParameters.putString(CodeChallengeWorkflow.CODE_CHALLENGE_METHOD_KEY, string2);
        } else {
            proofKeyParameters = this.codeChallengeWorkflow.getProofKeyParameters();
        }
        if (bundle.getString(AuthzConstants.BUNDLE_KEY.SCOPE_DATA.val) != null) {
            proofKeyParameters.putString(SCOPE_DATA_KEY, bundle.getString(AuthzConstants.BUNDLE_KEY.SCOPE_DATA.val));
        }
        proofKeyParameters.putString("client_id", bundle.getString(AuthzConstants.BUNDLE_KEY.CLIENT_ID.val));
        return proofKeyParameters;
    }
}