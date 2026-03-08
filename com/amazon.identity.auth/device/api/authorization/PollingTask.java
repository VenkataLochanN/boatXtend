package com.amazon.identity.auth.device.api.authorization;

import android.content.Context;
import com.amazon.identity.auth.device.AuthError;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.amazon.identity.auth.device.dataobject.AuthorizationToken;
import com.amazon.identity.auth.device.dataobject.CodePair;
import com.amazon.identity.auth.device.dataobject.RequestedScope;
import com.amazon.identity.auth.device.endpoint.ServerCommunication;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import java.io.IOException;
import java.util.concurrent.ScheduledExecutorService;

/* JADX INFO: loaded from: classes.dex */
public class PollingTask implements Runnable {
    private static final String LOG_TAG = PollingTask.class.getName();
    private AppInfo mAppInfo;
    private CodePair mCodePair;
    private Context mContext;
    private ScheduledExecutorService mExecutorService;
    private GetTokenListener mListener;
    private String mScopeString;
    private ServerCommunication mServerCommunication;

    public PollingTask(ServerCommunication serverCommunication, GetTokenListener getTokenListener, CodePair codePair, ScheduledExecutorService scheduledExecutorService, Context context, AppInfo appInfo, String str) {
        this.mServerCommunication = serverCommunication;
        this.mListener = getTokenListener;
        this.mCodePair = codePair;
        this.mExecutorService = scheduledExecutorService;
        this.mContext = context;
        this.mAppInfo = appInfo;
        this.mScopeString = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        MAPLog.i(LOG_TAG, "Start polling for the token");
        if (CodePairManager.hasCodePairExpired(this.mCodePair.getExpirationTime())) {
            MAPLog.e(LOG_TAG, "Code Pair has already expired");
            this.mListener.onError(new AuthError("Code Pair has already expired", AuthError.ERROR_TYPE.ERROR_BAD_PARAM));
            this.mExecutorService.shutdownNow();
            return;
        }
        AuthorizationToken[] tokensFromCodePair = null;
        try {
            tokensFromCodePair = this.mServerCommunication.getTokensFromCodePair(this.mCodePair.getUserCode(), this.mCodePair.getDeviceCode(), this.mContext, this.mAppInfo);
        } catch (AuthError unused) {
            MAPLog.i(LOG_TAG, "Code Pair has not been authorized, wait for " + this.mCodePair.getInterval() + " seconds to call the getToken API again");
            return;
        } catch (IOException e2) {
            MAPLog.e(LOG_TAG, "Failed to communicate with server", e2);
            this.mListener.onError(new AuthError("Failed to communicate with server", AuthError.ERROR_TYPE.ERROR_IO));
            this.mExecutorService.shutdownNow();
        }
        AuthorizationToken authorizationToken = tokensFromCodePair[0];
        AuthorizationToken authorizationToken2 = tokensFromCodePair[1];
        MAPLog.i(LOG_TAG, "Get the tokens from Code Pair successfully, update the database now");
        if (authorizationToken.insert(this.mContext) == -1) {
            this.mListener.onError(new AuthError("Failed to insert Access Token", AuthError.ERROR_TYPE.ERROR_DATA_STORAGE));
            this.mExecutorService.shutdownNow();
            return;
        }
        MAPLog.i(LOG_TAG, "Insert new access token successfully");
        if (authorizationToken2.insert(this.mContext) == -1) {
            MAPLog.i(LOG_TAG, "Failed to insert Refresh token");
            if (authorizationToken.delete(this.mContext)) {
                MAPLog.i(LOG_TAG, "Deleted the access token!");
            } else {
                this.mListener.onError(new AuthError("Failed to delete access token in db", AuthError.ERROR_TYPE.ERROR_DATA_STORAGE));
            }
            this.mListener.onError(new AuthError("Failed to insert Refresh Token", AuthError.ERROR_TYPE.ERROR_DATA_STORAGE));
            this.mExecutorService.shutdownNow();
            return;
        }
        MAPLog.i(LOG_TAG, "Insert new refresh token successfully");
        if (new RequestedScope(this.mScopeString, this.mAppInfo.getAppFamilyId(), null, authorizationToken.getRowId(), authorizationToken2.getRowId()).insert(this.mContext) == -1) {
            this.mListener.onError(new AuthError("Failed to insert Requested Scope", AuthError.ERROR_TYPE.ERROR_DATA_STORAGE));
            this.mExecutorService.shutdownNow();
        } else {
            MAPLog.i(LOG_TAG, "Insert new requestedScope successfully");
            this.mListener.onSuccess(new GetTokenResult(authorizationToken.getTokenValue()));
            this.mExecutorService.shutdown();
        }
    }
}