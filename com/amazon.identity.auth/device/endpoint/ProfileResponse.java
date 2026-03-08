package com.amazon.identity.auth.device.endpoint;

import android.text.TextUtils;
import com.amazon.identity.auth.device.AuthError;
import com.amazon.identity.auth.device.InsufficientScopeAuthError;
import com.amazon.identity.auth.device.InvalidTokenAuthError;
import com.amazon.identity.auth.device.authorization.AuthorizationResponseParser;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class ProfileResponse extends AbstractJSONTokenResponse {
    private static final String LOG_TAG = ProfileResponse.class.getName();
    private JSONObject profileResponse;

    @Override // com.amazon.identity.auth.device.endpoint.AbstractJSONTokenResponse
    protected JSONObject extractResponseJSONObject(JSONObject jSONObject) throws JSONException {
        return jSONObject;
    }

    @Override // com.amazon.identity.auth.device.endpoint.AbstractJSONTokenResponse
    public String getVersion() {
        return "3.0.4";
    }

    public ProfileResponse(HttpResponse httpResponse) {
        super(httpResponse);
    }

    @Override // com.amazon.identity.auth.device.endpoint.AbstractJSONTokenResponse
    protected void doParse(JSONObject jSONObject) throws JSONException, AuthError, IOException {
        this.profileResponse = jSONObject;
    }

    public JSONObject getProfileResponse() {
        return this.profileResponse;
    }

    @Override // com.amazon.identity.auth.device.endpoint.AbstractJSONTokenResponse
    protected void handleJSONError(JSONObject jSONObject) throws JSONException, AuthError {
        try {
            String string = jSONObject.getString(AuthorizationResponseParser.ERROR);
            if (TextUtils.isEmpty(string)) {
                return;
            }
            String string2 = jSONObject.getString(AuthorizationResponseParser.ERROR_DESCRIPTION);
            if (isInsufficientScope(string, string2)) {
                MAPLog.pii(LOG_TAG, "Insufficient scope in token in exchange.", "info=" + jSONObject);
                throw new InsufficientScopeAuthError("Profile request not valid for authorized scopes");
            }
            if (isInvalidToken(string, string2)) {
                MAPLog.pii(LOG_TAG, "Invalid Token in exchange.", "info=" + jSONObject);
                throw new InvalidTokenAuthError("Invalid Token in exchange. " + jSONObject);
            }
            MAPLog.pii(LOG_TAG, "Server error doing authorization exchange.", "info=" + jSONObject);
            throw new AuthError("Server error doing authorization exchange. " + jSONObject, AuthError.ERROR_TYPE.ERROR_SERVER_REPSONSE);
        } catch (JSONException unused) {
            if (TextUtils.isEmpty(null)) {
                return;
            }
            throw new AuthError("Server Error : " + ((String) null), AuthError.ERROR_TYPE.ERROR_SERVER_REPSONSE);
        }
    }

    private boolean isInsufficientScope(String str, String str2) {
        return "insufficient_scope".equals(str);
    }

    private boolean isInvalidToken(String str, String str2) {
        return "invalid_token".equals(str) || ("invalid_request".equals(str) && !TextUtils.isEmpty(str2) && str2.contains("access_token"));
    }
}