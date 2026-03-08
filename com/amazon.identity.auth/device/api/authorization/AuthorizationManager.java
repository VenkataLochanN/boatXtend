package com.amazon.identity.auth.device.api.authorization;

import android.content.Context;
import android.os.Bundle;
import com.amazon.identity.auth.device.AuthError;
import com.amazon.identity.auth.device.StoredPreferences;
import com.amazon.identity.auth.device.api.Listener;
import com.amazon.identity.auth.device.api.authorization.AuthorizeRequest;
import com.amazon.identity.auth.device.authorization.InternalAuthManager;
import com.amazon.identity.auth.device.authorization.api.AuthorizationListener;
import com.amazon.identity.auth.device.authorization.api.AuthzConstants;
import com.amazon.identity.auth.device.shared.APIListener;
import com.amazon.identity.auth.device.utils.LWAConstants;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class AuthorizationManager {
    private static final String LOG_TAG = AuthorizationManager.class.getName();
    private static Boolean sandboxMode;

    public static void setSandboxMode(Context context, boolean z) {
        boolean zIsSandboxMode = isSandboxMode(context);
        MAPLog.i(LOG_TAG, "Changing sandbox mode from " + zIsSandboxMode + " to " + z);
        if (zIsSandboxMode != z) {
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            signOut(context, new Listener<Void, AuthError>() { // from class: com.amazon.identity.auth.device.api.authorization.AuthorizationManager.1
                @Override // com.amazon.identity.auth.device.api.Listener
                public void onSuccess(Void r1) {
                    countDownLatch.countDown();
                }

                @Override // com.amazon.identity.auth.device.api.Listener
                public void onError(AuthError authError) {
                    countDownLatch.countDown();
                }
            });
            try {
                try {
                    countDownLatch.await();
                } catch (InterruptedException e2) {
                    MAPLog.e(LOG_TAG, "Interrupted waiting to sign out. Local auth state may be invalid", e2);
                }
            } finally {
                StoredPreferences.setSandboxMode(context, z);
            }
        }
        sandboxMode = Boolean.valueOf(z);
        MAPLog.i(LOG_TAG, "Sandbox mode changed to: " + z);
    }

    public static boolean isSandboxMode(Context context) {
        if (sandboxMode == null) {
            sandboxMode = Boolean.valueOf(StoredPreferences.isSandboxMode(context));
        }
        return sandboxMode.booleanValue();
    }

    public static void setRegion(Context context, Region region) {
        InternalAuthManager.getInstance(context).setRegion(context, region);
    }

    public static Region getRegion(Context context) {
        return InternalAuthManager.getInstance(context).getRegion(context);
    }

    public static void authorize(final AuthorizeRequest authorizeRequest) {
        final Context context = authorizeRequest.getContext();
        MAPLog.i(LOG_TAG, context.getPackageName() + " calling authorize");
        List<Scope> scopes = authorizeRequest.getScopes();
        int size = scopes.size();
        String[] strArr = new String[size];
        JSONObject jSONObject = new JSONObject();
        for (int i = 0; i < size; i++) {
            Scope scope = scopes.get(i);
            String name = scope.getName();
            strArr[i] = name;
            if (scope.getScopeData() != null) {
                try {
                    jSONObject.put(name, scope.getScopeData());
                } catch (JSONException e2) {
                    MAPLog.pii(LOG_TAG, "Unable to serialize scope data for scope \"" + name + "\"", scope.getScopeData().toString(), e2);
                }
            }
        }
        Bundle bundle = new Bundle();
        if (jSONObject.length() > 0) {
            bundle.putString(AuthzConstants.BUNDLE_KEY.SCOPE_DATA.val, jSONObject.toString());
        }
        if (authorizeRequest.getGrantType() == AuthorizeRequest.GrantType.AUTHORIZATION_CODE) {
            bundle.putBoolean(AuthzConstants.BUNDLE_KEY.GET_AUTH_CODE.val, true);
        }
        if (authorizeRequest.getCodeChallenge() != null) {
            bundle.putString(AuthzConstants.BUNDLE_KEY.CODE_CHALLENGE.val, authorizeRequest.getCodeChallenge());
        }
        if (authorizeRequest.getCodeChallengeMethod() != null) {
            bundle.putString(AuthzConstants.BUNDLE_KEY.CODE_CHALLENGE_METHOD.val, authorizeRequest.getCodeChallengeMethod());
        }
        bundle.putBoolean(LWAConstants.AUTHORIZE_BUNDLE_KEY.RETURN_ACCESS_TOKEN.val, true);
        bundle.putBoolean(LWAConstants.AUTHORIZE_BUNDLE_KEY.SHOW_PROGRESS.val, authorizeRequest.shouldShowProgress());
        InternalAuthManager.getInstance(context).authorize(authorizeRequest, context, strArr, bundle, new AuthorizationListener() { // from class: com.amazon.identity.auth.device.api.authorization.AuthorizationManager.2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.identity.auth.device.api.Listener
            public void onSuccess(Bundle bundle2) {
                Context context2 = context;
                AuthorizeRequest authorizeRequest2 = authorizeRequest;
                AuthorizeListener.onAuthorizationSuccess(context2, bundle2, authorizeRequest2, authorizeRequest2.shouldReturnUserData());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.identity.auth.device.api.Listener
            public void onError(AuthError authError) {
                authorizeRequest.onError(authError);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.identity.auth.device.api.CancellableListener
            public void onCancel(Bundle bundle2) {
                authorizeRequest.onCancel(new AuthCancellation(bundle2));
            }
        });
    }

    public static void getToken(Context context, Scope[] scopeArr, final Listener<AuthorizeResult, AuthError> listener) {
        MAPLog.i(LOG_TAG, context.getPackageName() + " calling getToken");
        String[] strArr = new String[scopeArr.length];
        for (int i = 0; i < scopeArr.length; i++) {
            strArr[i] = scopeArr[i].getName();
        }
        InternalAuthManager.getInstance(context).getToken(context, strArr, new APIListener() { // from class: com.amazon.identity.auth.device.api.authorization.AuthorizationManager.3
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.identity.auth.device.api.Listener
            public void onSuccess(Bundle bundle) {
                listener.onSuccess(new AuthorizeResult(bundle));
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.identity.auth.device.api.Listener
            public void onError(AuthError authError) {
                listener.onError(authError);
            }
        });
    }

    public static void signOut(Context context, final Listener<Void, AuthError> listener) {
        MAPLog.i(LOG_TAG, context.getPackageName() + " calling signOut");
        InternalAuthManager.getInstance(context).clearAuthorizationState(context, new APIListener() { // from class: com.amazon.identity.auth.device.api.authorization.AuthorizationManager.4
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.identity.auth.device.api.Listener
            public void onSuccess(Bundle bundle) {
                listener.onSuccess(null);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.identity.auth.device.api.Listener
            public void onError(AuthError authError) {
                listener.onError(authError);
            }
        });
    }
}