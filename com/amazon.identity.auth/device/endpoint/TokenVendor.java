package com.amazon.identity.auth.device.endpoint;

import android.content.Context;
import android.os.Bundle;
import com.amazon.identity.auth.device.AuthError;
import com.amazon.identity.auth.device.authorization.ThirdPartyServiceHelper;
import com.amazon.identity.auth.device.authorization.api.AuthzConstants;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.amazon.identity.auth.device.dataobject.AuthorizationToken;
import com.amazon.identity.auth.device.dataobject.RequestedScope;
import com.amazon.identity.auth.device.datastore.AuthorizationTokenDataSource;
import com.amazon.identity.auth.device.datastore.ProfileDataSource;
import com.amazon.identity.auth.device.datastore.RequestedScopeDataSource;
import com.amazon.identity.auth.device.token.AccessAtzToken;
import com.amazon.identity.auth.device.token.RefreshAtzToken;
import com.amazon.identity.auth.device.utils.LWAConstants;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class TokenVendor {
    private static final int DEFAULT_MINIMUM_TOKEN_LIFETIME = 300;
    private static final String LOG_TAG = TokenVendor.class.getName();
    protected ServerCommunication mServerCommunication = new ServerCommunication();

    public void setServerCommunication(ServerCommunication serverCommunication) {
        this.mServerCommunication = serverCommunication;
    }

    public String vendToken(String str, String[] strArr, Context context, Bundle bundle, AppInfo appInfo) throws AuthError, IOException {
        MAPLog.pii(LOG_TAG, "Vending out token: appId=" + appInfo.getAppFamilyId() + ", scopes=" + Arrays.toString(strArr), "directedId=" + str);
        if (strArr == null || strArr.length == 0) {
            MAPLog.i(LOG_TAG, "Vend token - No scopes passed in");
        }
        RequestedScope[] requestedScopes = getRequestedScopes(str, appInfo.getAppFamilyId(), strArr, context);
        AccessAtzToken commonAccessToken = getCommonAccessToken(requestedScopes, context);
        RefreshAtzToken commonRefreshToken = getCommonRefreshToken(requestedScopes, context);
        if (isTokenAcceptable(commonAccessToken, bundle)) {
            MAPLog.i(LOG_TAG, "Common token still has acceptable life, returning it back to caller");
            return commonAccessToken.getTokenValue();
        }
        return updateExistingToken(commonRefreshToken, str, strArr, commonAccessToken, context, appInfo);
    }

    public RequestedScope[] getRequestedScopes(String str, String str2, String[] strArr, Context context) {
        RequestedScope[] requestedScopeArr = new RequestedScope[strArr.length];
        for (int i = 0; i < requestedScopeArr.length; i++) {
            RequestedScope requestedScopeFindByPrimaryKey = RequestedScopeDataSource.getInstance(context).findByPrimaryKey(strArr[i], str2, str);
            if (requestedScopeFindByPrimaryKey != null) {
                requestedScopeArr[i] = requestedScopeFindByPrimaryKey;
            } else {
                MAPLog.w(LOG_TAG, "RequestedScope shouldn't be null!!!! - " + requestedScopeFindByPrimaryKey + ", but continuing anyway...");
                requestedScopeArr[i] = new RequestedScope(strArr[i], str2, str);
            }
        }
        return requestedScopeArr;
    }

    public List<RequestedScope> getCachedScopes(Context context) {
        return RequestedScopeDataSource.getInstance(context).findAllRows();
    }

    public Bundle vendNewTokensFromCode(String str, String str2, String[] strArr, String str3, AppInfo appInfo, Context context) throws AuthError, IOException {
        return vendNewTokensFromCode(str, null, str2, strArr, str3, context, appInfo);
    }

    public Bundle vendNewTokensFromCode(String str, String str2, String str3, String[] strArr, String str4, Context context, AppInfo appInfo) throws AuthError, IOException {
        return vendNewTokensFromCode(str, str2, str3, strArr, str4, context, appInfo, Bundle.EMPTY);
    }

    public Bundle vendNewTokensFromCode(String str, String str2, String str3, String[] strArr, String str4, Context context, AppInfo appInfo, Bundle bundle) throws AuthError, IOException {
        if (strArr == null || strArr.length == 0) {
            throw new AuthError("No scopes provided in parameters", AuthError.ERROR_TYPE.ERROR_BAD_API_PARAM);
        }
        MAPLog.i(LOG_TAG, "Vending new tokens from Code");
        AuthorizationToken[] tokensFromCode = this.mServerCommunication.getTokensFromCode(str, str2, str3, strArr, str4, context, appInfo);
        if (tokensFromCode == null) {
            throw new AuthError("No tokens returned", AuthError.ERROR_TYPE.ERROR_SERVER_REPSONSE);
        }
        AccessAtzToken accessAtzToken = (AccessAtzToken) tokensFromCode[0];
        if (accessAtzToken == null) {
            throw new AuthError("Access Atz token was null from server communication", AuthError.ERROR_TYPE.ERROR_SERVER_REPSONSE);
        }
        insertToken(context, accessAtzToken);
        RefreshAtzToken refreshAtzToken = (RefreshAtzToken) tokensFromCode[1];
        if (refreshAtzToken == null) {
            throw new AuthError("Refresh Atz token was null from server communication", AuthError.ERROR_TYPE.ERROR_SERVER_REPSONSE);
        }
        insertToken(context, refreshAtzToken);
        updateRequestedScopes(appInfo.getAppFamilyId(), strArr, context, accessAtzToken, refreshAtzToken, str4);
        Bundle bundle2 = new Bundle();
        bundle2.putString(AuthzConstants.BUNDLE_KEY.AUTHORIZE.val, "authorized");
        if (accessAtzToken != null && bundle != null && bundle.getBoolean(LWAConstants.AUTHORIZE_BUNDLE_KEY.RETURN_ACCESS_TOKEN.val)) {
            bundle2.putString(AuthzConstants.BUNDLE_KEY.TOKEN.val, accessAtzToken.getTokenValue());
        }
        return bundle2;
    }

    protected void insertToken(Context context, AuthorizationToken authorizationToken) throws AuthError {
        if (authorizationToken.insert(context) != -1) {
            return;
        }
        throw new AuthError("Unable to insert " + authorizationToken.getType() + " token into db", AuthError.ERROR_TYPE.ERROR_DATA_STORAGE);
    }

    protected void updateRequestedScopes(String str, String[] strArr, Context context, AccessAtzToken accessAtzToken, RefreshAtzToken refreshAtzToken, String str2) {
        RequestedScope[] requestedScopes = getRequestedScopes(str2, str, strArr, context);
        for (RequestedScope requestedScope : requestedScopes) {
            if (requestedScope.getRowId() == -1) {
                requestedScope.setAuthorizationAccessTokenId(accessAtzToken.getRowId());
                requestedScope.setAuthorizationRefreshTokenId(refreshAtzToken.getRowId());
                MAPLog.i(LOG_TAG, "Inserting " + requestedScope + " : rowid=" + requestedScope.insert(context));
            } else {
                AuthorizationToken authorizationTokenFindByRowId = accessAtzToken.getDataSource(context).findByRowId(requestedScope.getAuthorizationAccessTokenId());
                if (authorizationTokenFindByRowId != null) {
                    MAPLog.pii(LOG_TAG, "Deleting old access token.", "accessAtzToken=" + authorizationTokenFindByRowId + " : " + authorizationTokenFindByRowId.delete(context));
                }
                requestedScope.setAuthorizationAccessTokenId(accessAtzToken.getRowId());
                AuthorizationToken authorizationTokenFindByRowId2 = refreshAtzToken.getDataSource(context).findByRowId(requestedScope.getAuthorizationRefreshTokenId());
                if (authorizationTokenFindByRowId2 != null) {
                    MAPLog.pii(LOG_TAG, "Deleting old refresh token ", "refreshAtzToken=" + authorizationTokenFindByRowId2 + " : " + authorizationTokenFindByRowId2.delete(context));
                }
                requestedScope.setAuthorizationRefreshTokenId(refreshAtzToken.getRowId());
                MAPLog.i(LOG_TAG, "Updating " + requestedScope + " : " + requestedScope.update(context));
            }
        }
    }

    private boolean isTokenAcceptable(AccessAtzToken accessAtzToken, Bundle bundle) {
        return accessAtzToken != null && accessAtzToken.isRemainingLifeAcceptable(bundle != null ? bundle.getInt(AuthzConstants.BUNDLE_KEY.MINIMUM_TOKEN_LIFETIME.val, 300) : 300);
    }

    private String updateExistingToken(RefreshAtzToken refreshAtzToken, String str, String[] strArr, AccessAtzToken accessAtzToken, Context context, AppInfo appInfo) throws AuthError, IOException {
        AuthorizationToken authorizationToken;
        RefreshAtzToken refreshAtzToken2 = refreshAtzToken;
        MAPLog.pii(LOG_TAG, "Updating existing token", "token=" + accessAtzToken);
        if (refreshAtzToken2 != null) {
            if (strArr != null) {
                try {
                    if (strArr.length != 0) {
                        AuthorizationToken[] authorizationTokens = this.mServerCommunication.getAuthorizationTokens(refreshAtzToken, str, strArr, context, null, appInfo);
                        boolean z = false;
                        authorizationToken = authorizationTokens[0];
                        if (authorizationTokens[1] != null) {
                            MAPLog.pii(LOG_TAG, "Refresh token", "token=" + refreshAtzToken2);
                            updateExistingRefreshToken(authorizationTokens[1], refreshAtzToken2, context);
                            refreshAtzToken2 = (RefreshAtzToken) authorizationTokens[1];
                        }
                        RefreshAtzToken refreshAtzToken3 = refreshAtzToken2;
                        if (authorizationToken != null) {
                            MAPLog.pii(LOG_TAG, "Refreshed token", "token=" + accessAtzToken);
                            if (accessAtzToken != null) {
                                authorizationToken.setRowId(accessAtzToken.getRowId());
                            } else {
                                z = true;
                            }
                            ProfileDataSource.getInstance(context).deleteAllRows();
                            if (authorizationToken.insertOrUpdate(context)) {
                                if (z) {
                                    updateRequestedScopes(appInfo.getAppFamilyId(), strArr, context, (AccessAtzToken) authorizationToken, refreshAtzToken3, str);
                                }
                                MAPLog.i(LOG_TAG, "Update success!");
                            } else {
                                throw new IOException("Updating token failed unexpectedly!");
                            }
                        }
                    }
                } finally {
                    ThirdPartyServiceHelper.unbind(context);
                }
            }
            return null;
        }
        authorizationToken = null;
        if (authorizationToken != null) {
            return authorizationToken.getTokenValue();
        }
        return null;
    }

    private static AccessAtzToken getCommonAccessToken(RequestedScope[] requestedScopeArr, Context context) {
        MAPLog.i(LOG_TAG, "Try finding a common access token for requested scopes");
        if (requestedScopeArr == null || requestedScopeArr.length == 0) {
            return null;
        }
        AuthorizationTokenDataSource authorizationTokenDataSource = AuthorizationTokenDataSource.getInstance(context);
        AccessAtzToken accessAtzToken = (AccessAtzToken) authorizationTokenDataSource.findById(requestedScopeArr[0].getAuthorizationAccessTokenId());
        if (accessAtzToken == null) {
            return null;
        }
        for (int i = 1; i < requestedScopeArr.length; i++) {
            AuthorizationToken authorizationTokenFindById = authorizationTokenDataSource.findById(requestedScopeArr[i].getAuthorizationAccessTokenId());
            if (authorizationTokenFindById == null || authorizationTokenFindById.getRowId() != accessAtzToken.getRowId()) {
                MAPLog.i(LOG_TAG, "Common access token not found!");
                return null;
            }
        }
        MAPLog.pii(LOG_TAG, "Common access token found.", "accessAtzToken=" + accessAtzToken);
        return accessAtzToken;
    }

    private static RefreshAtzToken getCommonRefreshToken(RequestedScope[] requestedScopeArr, Context context) {
        MAPLog.i(LOG_TAG, "Try finding a common refresh token for requested scopes");
        if (requestedScopeArr == null || requestedScopeArr.length == 0) {
            return null;
        }
        AuthorizationTokenDataSource authorizationTokenDataSource = AuthorizationTokenDataSource.getInstance(context);
        RefreshAtzToken refreshAtzToken = (RefreshAtzToken) authorizationTokenDataSource.findById(requestedScopeArr[0].getAuthorizationRefreshTokenId());
        if (refreshAtzToken == null) {
            return null;
        }
        for (int i = 1; i < requestedScopeArr.length; i++) {
            AuthorizationToken authorizationTokenFindById = authorizationTokenDataSource.findById(requestedScopeArr[i].getAuthorizationRefreshTokenId());
            if (authorizationTokenFindById == null || authorizationTokenFindById.getRowId() != refreshAtzToken.getRowId()) {
                MAPLog.i(LOG_TAG, "Common refresh token not found!");
                return null;
            }
        }
        MAPLog.pii(LOG_TAG, "Common refresh token found.", "refreshAtzToken=" + refreshAtzToken);
        return refreshAtzToken;
    }

    public void insertTokens(Context context, AccessAtzToken accessAtzToken, RefreshAtzToken refreshAtzToken, String str, String str2, String[] strArr) throws AuthError {
        long jInsert = accessAtzToken.insert(context);
        if (jInsert == -1) {
            throw new AuthError("Unable to insert access atz token into db", AuthError.ERROR_TYPE.ERROR_DATA_STORAGE);
        }
        accessAtzToken.setId(jInsert);
        long jInsert2 = refreshAtzToken.insert(context);
        if (jInsert2 == -1) {
            throw new AuthError("Unable to insert refresh token into db", AuthError.ERROR_TYPE.ERROR_DATA_STORAGE);
        }
        refreshAtzToken.setId(jInsert2);
        updateRequestedScopes(str2, strArr, context, accessAtzToken, refreshAtzToken, str);
    }

    public void clearAuthStateServerSide(Context context, AppInfo appInfo, Bundle bundle) throws AuthError, IOException {
        AccessAtzToken commonAccessToken;
        List<RequestedScope> cachedScopes = getCachedScopes(context);
        if (cachedScopes.isEmpty() || (commonAccessToken = getCommonAccessToken((RequestedScope[]) cachedScopes.toArray(new RequestedScope[cachedScopes.size()]), context)) == null) {
            return;
        }
        ((LogoutResponse) this.mServerCommunication.executeRequest(new LogoutRequest(context, appInfo, commonAccessToken.getTokenValue()), context)).getLogoutResponse();
    }

    private void updateExistingRefreshToken(AuthorizationToken authorizationToken, AuthorizationToken authorizationToken2, Context context) throws IOException {
        authorizationToken.setRowId(authorizationToken2.getRowId());
        if (!authorizationToken.update(context)) {
            throw new IOException("Updating token failed unexpectedly!");
        }
    }
}