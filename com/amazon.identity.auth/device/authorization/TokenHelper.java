package com.amazon.identity.auth.device.authorization;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import com.amazon.identity.auth.device.AuthError;
import com.amazon.identity.auth.device.appid.AppIdentifier;
import com.amazon.identity.auth.device.authorization.ThirdPartyServiceHelper;
import com.amazon.identity.auth.device.authorization.api.AuthzConstants;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.amazon.identity.auth.device.datastore.DatabaseHelper;
import com.amazon.identity.auth.device.datastore.ProfileDataSource;
import com.amazon.identity.auth.device.endpoint.ServerCommunication;
import com.amazon.identity.auth.device.endpoint.TokenVendor;
import com.amazon.identity.auth.device.service.MAPServiceResult;
import com.amazon.identity.auth.device.shared.APIListener;
import com.amazon.identity.auth.device.utils.LWAServiceWrapper;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import java.io.IOException;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class TokenHelper {
    private static final String LOG_TAG = TokenHelper.class.getName();
    private static TokenVendor mTokenVendor = new TokenVendor();

    private TokenHelper() {
    }

    static void setServerCommunicaton(ServerCommunication serverCommunication) {
        mTokenVendor.setServerCommunication(serverCommunication);
    }

    public static void getToken(Context context, String str, String str2, String[] strArr, APIListener aPIListener, AppIdentifier appIdentifier, Bundle bundle) throws AuthError {
        Bundle onSuccessBundle;
        MAPLog.i(LOG_TAG, "GetToken pkg=" + str + " scopes=" + Arrays.toString(strArr));
        AppInfo appInfo = appIdentifier.getAppInfo(str, context);
        if (appInfo == null) {
            MAPLog.e(LOG_TAG, "appInfo is null for " + str);
            aPIListener.onError(new AuthError("APIKey info is unavailable for " + str, null, AuthError.ERROR_TYPE.ERROR_ACCESS_DENIED));
            return;
        }
        try {
            String tokenInternal = getTokenInternal(context, str, strArr, appInfo, bundle);
            if (tokenInternal == null) {
                onSuccessBundle = new Bundle();
            } else {
                onSuccessBundle = MAPServiceResult.getOnSuccessBundle(AuthzConstants.BUNDLE_KEY.TOKEN.val, tokenInternal);
            }
            aPIListener.onSuccess(onSuccessBundle);
        } catch (AuthError e2) {
            aPIListener.onError(e2);
        }
    }

    public static String getTokenInternal(Context context, String str, String[] strArr, AppInfo appInfo, Bundle bundle) throws AuthError {
        try {
            String strVendToken = mTokenVendor.vendToken(null, strArr, context, bundle, appInfo);
            if (strVendToken == null) {
                strVendToken = getTokenFromSSO(context, str, strArr);
            }
            MAPLog.pii(LOG_TAG, "GetToken", " appid=" + appInfo.getAppFamilyId() + " atzToken=" + strVendToken);
            return strVendToken;
        } catch (IOException e2) {
            MAPLog.e(LOG_TAG, e2.getMessage(), e2);
            throw new AuthError("Error communicating with server", e2, AuthError.ERROR_TYPE.ERROR_IO);
        }
    }

    public static void clearAuthStateServerSide(Context context, AppInfo appInfo, Bundle bundle) throws AuthError {
        try {
            mTokenVendor.clearAuthStateServerSide(context, appInfo, bundle);
        } catch (IOException e2) {
            MAPLog.e(LOG_TAG, e2.getMessage(), e2);
            throw new AuthError(e2.getMessage(), AuthError.ERROR_TYPE.ERROR_IO);
        }
    }

    private static String getTokenFromSSO(Context context, final String str, final String[] strArr) throws AuthError {
        return new LWAServiceWrapper<String>() { // from class: com.amazon.identity.auth.device.authorization.TokenHelper.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.amazon.identity.auth.device.utils.LWAServiceWrapper
            public String doWork(Context context2, AmazonAuthorizationServiceInterface amazonAuthorizationServiceInterface) throws AuthError, RemoteException {
                return TokenHelper.getTokenFromService(context2, strArr, str, amazonAuthorizationServiceInterface);
            }
        }.execute(context, new ThirdPartyServiceHelper());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getTokenFromService(Context context, String[] strArr, String str, AmazonAuthorizationServiceInterface amazonAuthorizationServiceInterface) throws AuthError, RemoteException {
        DatabaseHelper.clearAuthorizationState(context);
        ProfileDataSource.getInstance(context).deleteAllRows();
        Bundle token = amazonAuthorizationServiceInterface.getToken(null, str, strArr);
        if (token != null) {
            token.setClassLoader(context.getClassLoader());
            String string = token.getString(ThirdPartyServiceHelper.TOKEN_KEYS.ACCESS_ATZ_TOKEN);
            if (!TextUtils.isEmpty(string)) {
                return string;
            }
            AuthError authError = (AuthError) token.getParcelable(AuthError.AUTH_ERROR_EXECEPTION);
            if (authError != null) {
                if (AuthError.ERROR_TYPE.ERROR_INVALID_TOKEN == authError.getType()) {
                    MAPLog.e(LOG_TAG, "Invalid token. Cleaning up.");
                    ProfileDataSource.getInstance(context).deleteAllRows();
                } else {
                    MAPLog.i(LOG_TAG, "AuthError from service " + authError.getMessage());
                    ThirdPartyServiceHelper.clearCachedService(context);
                    throw authError;
                }
            } else {
                MAPLog.i(LOG_TAG, "No results from service");
            }
        }
        return null;
    }
}