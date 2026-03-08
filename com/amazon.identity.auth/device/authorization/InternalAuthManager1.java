package com.amazon.identity.auth.device.authorization;

import android.content.Context;
import android.os.Bundle;
import com.amazon.identity.auth.device.AuthError;
import com.amazon.identity.auth.device.StoredPreferences;
import com.amazon.identity.auth.device.api.authorization.AuthorizationManager;
import com.amazon.identity.auth.device.api.authorization.AuthorizeRequest;
import com.amazon.identity.auth.device.api.authorization.Region;
import com.amazon.identity.auth.device.appid.ThirdPartyAppIdentifier;
import com.amazon.identity.auth.device.authorization.api.AuthorizationListener;
import com.amazon.identity.auth.device.authorization.api.AuthzConstants;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.amazon.identity.auth.device.datastore.DatabaseHelper;
import com.amazon.identity.auth.device.endpoint.TokenVendor;
import com.amazon.identity.auth.device.shared.APIListener;
import com.amazon.identity.auth.device.thread.AuthzCallbackFuture;
import com.amazon.identity.auth.device.thread.ThreadUtils;
import com.amazon.identity.auth.device.utils.DefaultLibraryInfo;
import com.amazon.identity.auth.device.utils.MAPUtils;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import com.ido.alexa.log.AlexaLogUtil;
import java.util.Arrays;
import java.util.concurrent.Future;

/* JADX INFO: loaded from: classes.dex */
public class InternalAuthManager1 {
    private static InternalAuthManager1 sharedInstance;
    private AppInfo appInfo;
    private String clientId;
    private static final String LOG_TAG = InternalAuthManager1.class.getName();
    private static final ThirdPartyAppIdentifier appIdentifier = new ThirdPartyAppIdentifier();
    private static final TokenVendor tokenVendor = new TokenVendor();

    public static InternalAuthManager1 getInstance(Context context) {
        if (sharedInstance == null) {
            synchronized (InternalAuthManager1.class) {
                if (sharedInstance == null) {
                    sharedInstance = new InternalAuthManager1(context);
                }
            }
        }
        return sharedInstance;
    }

    public InternalAuthManager1(Context context) {
        this.appInfo = appIdentifier.getAppInfo(context.getPackageName(), context);
        AppInfo appInfo = this.appInfo;
        if (appInfo != null && appInfo.getClientId() != null) {
            this.clientId = this.appInfo.getClientId();
            updateAppState(context);
            return;
        }
        throw new IllegalArgumentException("Invalid API Key");
    }

    public Future<Bundle> authorize(final AuthorizeRequest authorizeRequest, final Context context, final String[] strArr, final Bundle bundle, final AuthorizationListener authorizationListener) {
        if (strArr != null && strArr.length != 0) {
            AlexaLogUtil.printAndSave("Alexa authorize :" + context.getPackageName() + " ,scopes=" + Arrays.toString(strArr) + " ,clientId:" + this.clientId);
            String str = LOG_TAG;
            StringBuilder sb = new StringBuilder();
            sb.append(context.getPackageName());
            sb.append(" calling authorize: scopes=");
            sb.append(Arrays.toString(strArr));
            MAPLog.i(str, sb.toString());
            ThreadUtils.THREAD_POOL.execute(new Runnable() { // from class: com.amazon.identity.auth.device.authorization.InternalAuthManager1.1
                @Override // java.lang.Runnable
                public void run() {
                    if (!InternalAuthManager1.this.isAPIKeyValid(context)) {
                        authorizationListener.onError(new AuthError("APIKey is invalid", AuthError.ERROR_TYPE.ERROR_ACCESS_DENIED));
                        return;
                    }
                    Bundle bundle2 = bundle;
                    Bundle bundle3 = bundle2 == null ? new Bundle() : new Bundle(bundle2);
                    if (!bundle3.containsKey(AuthzConstants.BUNDLE_KEY.SANDBOX.val)) {
                        bundle3.putBoolean(AuthzConstants.BUNDLE_KEY.SANDBOX.val, false);
                    }
                    try {
                        new ThirdPartyAuthorizationHelper1().authorize(authorizeRequest, context, context.getPackageName(), InternalAuthManager1.this.clientId, InternalAuthManager1.this.getRedirectURI(context), strArr, true, InternalAuthManager1.tokenVendor, authorizationListener, bundle3);
                    } catch (AuthError e2) {
                        authorizationListener.onError(e2);
                    }
                }
            });
            return null;
        }
        throw new IllegalArgumentException("scopes must not be null or empty!");
    }

    public Future<Bundle> getToken(final Context context, final String[] strArr, APIListener aPIListener) {
        if (strArr != null && strArr.length != 0) {
            MAPLog.i(LOG_TAG, context.getPackageName() + " calling getToken: scopes=" + Arrays.toString(strArr));
            final AuthzCallbackFuture authzCallbackFuture = new AuthzCallbackFuture(aPIListener);
            ThreadUtils.THREAD_POOL.execute(new Runnable() { // from class: com.amazon.identity.auth.device.authorization.InternalAuthManager1.2
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        if (!InternalAuthManager1.this.isAPIKeyValid(context)) {
                            authzCallbackFuture.onError(new AuthError("APIKey is invalid", AuthError.ERROR_TYPE.ERROR_ACCESS_DENIED));
                            return;
                        }
                        Bundle bundle = new Bundle();
                        bundle.putBoolean(AuthzConstants.BUNDLE_KEY.SANDBOX.val, AuthorizationManager.isSandboxMode(context));
                        TokenHelper.getToken(context, context.getPackageName(), InternalAuthManager1.this.clientId, strArr, new APIListener() { // from class: com.amazon.identity.auth.device.authorization.InternalAuthManager1.2.1
                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // com.amazon.identity.auth.device.api.Listener
                            public void onSuccess(Bundle bundle2) {
                                authzCallbackFuture.onSuccess(bundle2);
                            }

                            /* JADX WARN: Can't rename method to resolve collision */
                            @Override // com.amazon.identity.auth.device.api.Listener
                            public void onError(AuthError authError) {
                                authzCallbackFuture.onError(authError);
                            }
                        }, new ThirdPartyAppIdentifier(), bundle);
                    } catch (AuthError e2) {
                        authzCallbackFuture.onError(e2);
                    }
                }
            });
            return authzCallbackFuture;
        }
        throw new IllegalArgumentException("scopes must not be null or empty!");
    }

    public Future<Bundle> getProfile(final Context context, final Bundle bundle, APIListener aPIListener) {
        MAPLog.i(LOG_TAG, context.getPackageName() + " calling getProfile");
        final AuthzCallbackFuture authzCallbackFuture = new AuthzCallbackFuture(aPIListener);
        ThreadUtils.THREAD_POOL.execute(new Runnable() { // from class: com.amazon.identity.auth.device.authorization.InternalAuthManager1.3
            @Override // java.lang.Runnable
            public void run() {
                if (!InternalAuthManager1.this.isAPIKeyValid(context)) {
                    authzCallbackFuture.onError(new AuthError("APIKey is invalid", AuthError.ERROR_TYPE.ERROR_ACCESS_DENIED));
                    return;
                }
                Bundle bundle2 = bundle;
                Bundle bundle3 = bundle2 == null ? new Bundle() : new Bundle(bundle2);
                if (!bundle3.containsKey(AuthzConstants.BUNDLE_KEY.SANDBOX.val)) {
                    bundle3.putBoolean(AuthzConstants.BUNDLE_KEY.SANDBOX.val, AuthorizationManager.isSandboxMode(context));
                }
                Context context2 = context;
                ProfileHelper.getProfile(context2, context2.getPackageName(), bundle3, new APIListener() { // from class: com.amazon.identity.auth.device.authorization.InternalAuthManager1.3.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.amazon.identity.auth.device.api.Listener
                    public void onSuccess(Bundle bundle4) {
                        authzCallbackFuture.onSuccess(bundle4);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // com.amazon.identity.auth.device.api.Listener
                    public void onError(AuthError authError) {
                        authzCallbackFuture.onError(authError);
                    }
                });
            }
        });
        return authzCallbackFuture;
    }

    public Future<Bundle> clearAuthorizationState(final Context context, APIListener aPIListener) {
        final AuthzCallbackFuture authzCallbackFuture = new AuthzCallbackFuture(aPIListener);
        MAPLog.i(LOG_TAG, context.getPackageName() + " calling clearAuthorizationState");
        ThreadUtils.THREAD_POOL.execute(new Runnable() { // from class: com.amazon.identity.auth.device.authorization.InternalAuthManager1.4
            @Override // java.lang.Runnable
            public void run() {
                if (InternalAuthManager1.this.isAPIKeyValid(context)) {
                    AuthError authErrorClearServerSideAuthorizationState = InternalAuthManager1.this.clearServerSideAuthorizationState(context);
                    AuthError authErrorClearSSOSideAuthorizationState = InternalAuthManager1.this.clearSSOSideAuthorizationState(context);
                    DatabaseHelper.clearAuthorizationState(context);
                    if (authErrorClearServerSideAuthorizationState == null && authErrorClearSSOSideAuthorizationState == null) {
                        authzCallbackFuture.onSuccess(new Bundle());
                        return;
                    } else if (authErrorClearServerSideAuthorizationState != null) {
                        authzCallbackFuture.onError(authErrorClearServerSideAuthorizationState);
                        return;
                    } else {
                        if (authErrorClearSSOSideAuthorizationState != null) {
                            authzCallbackFuture.onError(authErrorClearSSOSideAuthorizationState);
                            return;
                        }
                        return;
                    }
                }
                authzCallbackFuture.onError(new AuthError("APIKey is invalid", AuthError.ERROR_TYPE.ERROR_ACCESS_DENIED));
            }
        });
        return authzCallbackFuture;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AuthError clearSSOSideAuthorizationState(Context context) {
        try {
            DatabaseHelper.clearServiceAuthorizationState(context);
            return null;
        } catch (AuthError e2) {
            return e2;
        }
    }

    public boolean isAPIKeyValid(Context context) {
        return appIdentifier.isAPIKeyValid(context) && this.clientId != null;
    }

    public String getClientId() {
        return this.clientId;
    }

    public String getRedirectURI(Context context) {
        return appIdentifier.getRedirectUrl(context);
    }

    public void setRegion(Context context, Region region) {
        if (DefaultLibraryInfo.getLibraryRegion() != region) {
            StoredPreferences.setRegion(context, region);
            DefaultLibraryInfo.setLibraryRegion(region);
        }
    }

    public Region getRegion(Context context) {
        Region region = StoredPreferences.getRegion(context);
        return Region.AUTO == region ? new EndpointDomainBuilder(context, this.appInfo).getRegionForAPIKey() : region;
    }

    private void updateAppState(Context context) {
        String hostType = MAPUtils.getHostType(context, context.getPackageName());
        if ("development".equalsIgnoreCase(hostType)) {
            DefaultLibraryInfo.setOverrideAppStage(Stage.DEVO);
        } else if ("gamma".equalsIgnoreCase(hostType)) {
            DefaultLibraryInfo.setOverrideAppStage(Stage.PRE_PROD);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AuthError clearServerSideAuthorizationState(Context context) {
        try {
            Bundle bundle = new Bundle();
            bundle.putBoolean(AuthzConstants.BUNDLE_KEY.SANDBOX.val, AuthorizationManager.isSandboxMode(context));
            TokenHelper.clearAuthStateServerSide(context, this.appInfo, bundle);
            return null;
        } catch (AuthError e2) {
            return e2;
        }
    }
}