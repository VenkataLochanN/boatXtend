package com.amazon.identity.auth.device.endpoint;

import com.amazon.identity.auth.device.AuthError;
import com.amazon.identity.auth.device.token.RefreshAtzToken;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class OauthCodeForTokenResponse extends OauthTokenResponse {
    private static final String LOG_TAG = OauthCodeForTokenResponse.class.getName();

    @Override // com.amazon.identity.auth.device.endpoint.OauthTokenResponse
    boolean isInvalidToken(String str, String str2) {
        return false;
    }

    OauthCodeForTokenResponse(HttpResponse httpResponse, String str, String str2) {
        super(httpResponse, str, str2);
        MAPLog.i(LOG_TAG, "Creating OauthCodeForTokenResponse appId=" + str);
    }

    @Override // com.amazon.identity.auth.device.endpoint.OauthTokenResponse
    public RefreshAtzToken extractRefreshAtzToken(JSONObject jSONObject) throws AuthError {
        RefreshAtzToken refreshAtzTokenExtractRefreshAtzToken = super.extractRefreshAtzToken(jSONObject);
        if (refreshAtzTokenExtractRefreshAtzToken != null) {
            return refreshAtzTokenExtractRefreshAtzToken;
        }
        throw new AuthError("JSON response did not contain an AccessAtzToken", AuthError.ERROR_TYPE.ERROR_JSON);
    }
}